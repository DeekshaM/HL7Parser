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
import java.util.Scanner;
import java.util.regex.Pattern;

public class X12SimpleParser
  implements Parser
{
  static final int SIZE = 106;
  static final int POS_SEGMENT = 105;
  static final int POS_ELEMENT = 3;
  static final int POS_COMPOSITE_ELEMENT = 104;
  
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
    Character localCharacter = localContext.getSegmentSeparator();
    String str1 = Pattern.quote(localCharacter.toString());
    Scanner localScanner = new Scanner(paramFile);
    localScanner.useDelimiter(str1 + "\r\n|" + str1 + "\n|" + str1);
    X12Simple localX12Simple = new X12Simple(localContext);
    while (localScanner.hasNext())
    {
      String str2 = localScanner.next();
      localX12Simple.addSegment(str2);
    }
    localScanner.close();
    return localX12Simple;
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
    Character localCharacter = localContext.getSegmentSeparator();
    String str1 = Pattern.quote(localCharacter.toString());
    Scanner localScanner = new Scanner(paramString);
    localScanner.useDelimiter(str1 + "\r\n|" + str1 + "\n|" + str1);
    X12Simple localX12Simple = new X12Simple(localContext);
    while (localScanner.hasNext())
    {
      String str2 = localScanner.next();
      Segment localSegment = localX12Simple.addSegment();
      String[] arrayOfString = str2.split("\\" + localContext.getElementSeparator());
      localSegment.addElements(arrayOfString);
    }
    localScanner.close();
    return localX12Simple;
  }
}

