/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vmanchala
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class X12Simple
  implements EDI, Iterable<Segment>
{
  private static final long serialVersionUID = 1L;
  private Context context;
  private List<Segment> segments;
  
  public X12Simple(Context paramContext)
  {
    this.context = paramContext;
    this.segments = new ArrayList();
  }
  
  public Segment addSegment()
  {
    Segment localSegment = new Segment(this.context);
    this.segments.add(localSegment);
    return localSegment;
  }
  
  public Segment addSegment(String paramString)
  {
    Segment localSegment = new Segment(this.context);
    String[] arrayOfString = paramString.split("\\" + this.context.getElementSeparator());
    localSegment.addElements(arrayOfString);
    this.segments.add(localSegment);
    return localSegment;
  }
  
  public Segment addSegment(Segment paramSegment)
  {
    this.segments.add(paramSegment);
    return paramSegment;
  }
  
  public Segment addSegment(int paramInt)
  {
    Segment localSegment = new Segment(this.context);
    this.segments.add(paramInt, localSegment);
    return localSegment;
  }
  
  public Segment addSegment(int paramInt, String paramString)
  {
    Segment localSegment = new Segment(this.context);
    String[] arrayOfString = paramString.split("\\" + this.context.getElementSeparator());
    localSegment.addElements(arrayOfString);
    this.segments.add(paramInt, localSegment);
    return localSegment;
  }
  
  public Segment addSegment(int paramInt, Segment paramSegment)
  {
    this.segments.add(paramInt, paramSegment);
    return paramSegment;
  }
  
  public List<Segment> findSegment(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.segments.iterator();
    while (localIterator.hasNext())
    {
      Segment localSegment = (Segment)localIterator.next();
      if (paramString.equals(localSegment.getElement(0))) {
        localArrayList.add(localSegment);
      }
    }
    return localArrayList;
  }
  
  public Context getContext()
  {
    return this.context;
  }
  
  public Segment getSegment(int paramInt)
  {
    return (Segment)this.segments.get(paramInt);
  }
  
  public List<Segment> getSegments()
  {
    return this.segments;
  }
  
  public Iterator<Segment> iterator()
  {
    return this.segments.iterator();
  }
  
  public Segment removeSegment(int paramInt)
  {
    return (Segment)this.segments.remove(paramInt);
  }
  
  public void setContext(Context paramContext)
  {
    this.context = paramContext;
  }
  
  public Segment setSegment(int paramInt)
  {
    Segment localSegment = new Segment(this.context);
    this.segments.set(paramInt, localSegment);
    return localSegment;
  }
  
  public Segment setSegment(int paramInt, String paramString)
  {
    Segment localSegment = new Segment(this.context);
    String[] arrayOfString = paramString.split("\\" + this.context.getElementSeparator());
    localSegment.addElements(arrayOfString);
    this.segments.set(paramInt, localSegment);
    return localSegment;
  }
  
  public Segment setSegment(int paramInt, Segment paramSegment)
  {
    this.segments.set(paramInt, paramSegment);
    return paramSegment;
  }
  
  public int size()
  {
    return this.segments.size();
  }
  
  public String toString()
  {
    return toString(false);
  }
  
  public String toString(boolean paramBoolean)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = this.segments.iterator();
    while (localIterator.hasNext())
    {
      Segment localSegment = (Segment)localIterator.next();
      localStringBuilder.append(localSegment.toString(paramBoolean));
      localStringBuilder.append(this.context.getSegmentSeparator());
    }
    return localStringBuilder.toString();
  }
  
  public String toXML()
  {
    return toXML(false);
  }
  
  public String toXML(boolean paramBoolean)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("<X12>");
    Iterator localIterator = this.segments.iterator();
    while (localIterator.hasNext())
    {
      Segment localSegment = (Segment)localIterator.next();
      localStringBuilder.append(localSegment.toXML(paramBoolean));
    }
    localStringBuilder.append("</X12>");
    return localStringBuilder.toString();
  }
}

