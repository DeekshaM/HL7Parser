/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vmanchala
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class X12Parser
  implements Parser
{
  private static final int SIZE = 106;
  public static final int POS_SEGMENT = 105;
  public static final int POS_ELEMENT = 3;
  public static final int POS_COMPOSITE_ELEMENT = 104;
  private Cf x12Cf;
  private Cf cfMarker;
  private Loop loopMarker;
  
  public X12Parser(Cf paramCf)
  {
    this.x12Cf = paramCf;
  }
  
  public EDI parse(File paramFile)
    throws FormatException, IOException
  {
    char[] arrayOfChar = new char[106];
    FileReader localFileReader = new FileReader(paramFile);
    int i = localFileReader.read(arrayOfChar);
    localFileReader.close();
    if (i != 106) {
      throw new FormatException();
    }
    Context localContext = new Context();
    localContext.setSegmentSeparator(Character.valueOf(arrayOfChar[105]));
    localContext.setElementSeparator(Character.valueOf(arrayOfChar[3]));
    localContext.setCompositeElementSeparator(Character.valueOf(arrayOfChar[104]));
    Scanner localScanner = new Scanner(paramFile);
    X12 localX12 = scanSource(localScanner, localContext);
    localScanner.close();
    return localX12;
  }
  
  private X12 scanSource(Scanner paramScanner, Context paramContext)
  {
    Character localCharacter = paramContext.getSegmentSeparator();
    String str1 = Pattern.quote(localCharacter.toString());
    paramScanner.useDelimiter(str1 + "\r\n|" + str1 + "\n|" + str1);
    this.cfMarker = this.x12Cf;
    X12 localX12 = new X12(paramContext);
    this.loopMarker = localX12;
    Object localObject = localX12;
    while (paramScanner.hasNext())
    {
      String str2 = paramScanner.next();
      String[] arrayOfString = str2.split("\\" + paramContext.getElementSeparator());
      if (doesChildLoopMatch(this.cfMarker, arrayOfString))
      {
        localObject = ((Loop)localObject).addChild(this.cfMarker.getName());
        ((Loop)localObject).addSegment(str2);
      }
      else if (doesParentLoopMatch(this.cfMarker, arrayOfString, (Loop)localObject))
      {
        localObject = this.loopMarker.addChild(this.cfMarker.getName());
        ((Loop)localObject).addSegment(str2);
      }
      else
      {
        ((Loop)localObject).addSegment(str2);
      }
    }
    return localX12;
  }
  
  public EDI parse(InputStream paramInputStream)
    throws FormatException, IOException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    char[] arrayOfChar = new char[1024];
    int i = -1;
    BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramInputStream));
    while ((i = localBufferedReader.read(arrayOfChar)) != -1) {
      localStringBuilder.append(arrayOfChar, 0, i);
    }
    String str = localStringBuilder.toString();
    return parse(str);
  }
  
  public EDI parse(String paramString)
    throws FormatException
  {
    if (paramString.length() < 106) {
      throw new FormatException();
    }
    Context localContext = new Context();
    localContext.setSegmentSeparator(Character.valueOf(paramString.charAt(105)));
    localContext.setElementSeparator(Character.valueOf(paramString.charAt(3)));
    localContext.setCompositeElementSeparator(Character.valueOf(paramString.charAt(104)));
    Scanner localScanner = new Scanner(paramString);
    X12 localX12 = scanSource(localScanner, localContext);
    localScanner.close();
    return localX12;
  }
  
  private boolean doesLoopMatch(Cf paramCf, String[] paramArrayOfString)
  {
    if (paramCf.getSegment().equals(paramArrayOfString[0]))
    {
      if (null == paramCf.getSegmentQualPos()) {
        return true;
      }
      for (String str : paramCf.getSegmentQuals()) {
        if (str.equals(paramArrayOfString[paramCf.getSegmentQualPos().intValue()])) {
          return true;
        }
      }
    }
    return false;
  }
  
  boolean doesChildLoopMatch(Cf paramCf, String[] paramArrayOfString)
  {
    Iterator localIterator = paramCf.childList().iterator();
    while (localIterator.hasNext())
    {
      Cf localCf = (Cf)localIterator.next();
      if (doesLoopMatch(localCf, paramArrayOfString))
      {
        this.cfMarker = localCf;
        return true;
      }
    }
    return false;
  }
  
  private boolean doesParentLoopMatch(Cf paramCf, String[] paramArrayOfString, Loop paramLoop)
  {
    Cf localCf1 = paramCf.getParent();
    if (localCf1 == null) {
      return false;
    }
    this.loopMarker = paramLoop.getParent();
    Iterator localIterator = localCf1.childList().iterator();
    while (localIterator.hasNext())
    {
      Cf localCf2 = (Cf)localIterator.next();
      if (doesLoopMatch(localCf2, paramArrayOfString))
      {
        this.cfMarker = localCf2;
        return true;
      }
    }
    return doesParentLoopMatch(localCf1, paramArrayOfString, this.loopMarker);
  }
}

