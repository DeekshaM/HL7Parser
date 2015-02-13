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

public class Segment
  implements Iterable<String>
{
  private static final long serialVersionUID = 1L;
  private static final String EMPTY_STRING = "";
  private Context context;
  private List<String> elements = new ArrayList();
  
  public Segment(Context paramContext)
  {
    this.context = paramContext;
  }
  
  public boolean addElement(String paramString)
  {
    return this.elements.add(paramString);
  }
  
  public boolean addElements(String paramString)
  {
    String[] arrayOfString = paramString.split("\\" + this.context.getElementSeparator());
    return addElements(arrayOfString);
  }
  
  public boolean addElements(String... paramVarArgs)
  {
    for (String str : paramVarArgs) {
      if (!this.elements.add(str)) {
        return false;
      }
    }
    return true;
  }
  
  public boolean addCompositeElement(String... paramVarArgs)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (String str : paramVarArgs)
    {
      localStringBuilder.append(str);
      localStringBuilder.append(this.context.getCompositeElementSeparator());
    }
    return this.elements.add(localStringBuilder.substring(0, localStringBuilder.length() - 1));
  }
  
  public boolean addElement(int paramInt, String paramString)
  {
    return this.elements.add(paramString);
  }
  
  public void addCompositeElement(int paramInt, String... paramVarArgs)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (String str : paramVarArgs)
    {
      localStringBuilder.append(str);
      localStringBuilder.append(this.context.getCompositeElementSeparator());
    }
    this.elements.add(paramInt, localStringBuilder.substring(0, localStringBuilder.length() - 1));
  }
  
  public Context getContext()
  {
    return this.context;
  }
  
  public String getElement(int paramInt)
  {
    return (String)this.elements.get(paramInt);
  }
  
  public List<String> getElements()
  {
    return this.elements;
  }
  
  public Iterator<String> iterator()
  {
    return this.elements.iterator();
  }
  
  public String removeElement(int paramInt)
  {
    return (String)this.elements.remove(paramInt);
  }
  
  private void removeTrailingEmptyElements()
  {
    for (int i = this.elements.size() - 1; (i >= 0) && ((this.elements.get(i) == null) || (((String)this.elements.get(i)).length() == 0)); i--) {
      this.elements.remove(i);
    }
  }
  
  public void setContext(Context paramContext)
  {
    this.context = paramContext;
  }
  
  public void setElement(int paramInt, String paramString)
  {
    this.elements.set(paramInt, paramString);
  }
  
  public void setCompositeElement(int paramInt, String... paramVarArgs)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (String str : paramVarArgs)
    {
      localStringBuilder.append(str);
      localStringBuilder.append(this.context.getCompositeElementSeparator());
    }
    this.elements.set(paramInt, localStringBuilder.substring(0, localStringBuilder.length() - 1));
  }
  
  public int size()
  {
    return this.elements.size();
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = this.elements.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      localStringBuilder.append(str);
      localStringBuilder.append(this.context.getElementSeparator());
    }
    if (localStringBuilder.length() == 0) {
      return "";
    }
    return localStringBuilder.substring(0, localStringBuilder.length() - 1);
  }
  
  public String toString(boolean paramBoolean)
  {
    if (paramBoolean) {
      removeTrailingEmptyElements();
    }
    return toString();
  }
  
  public String toXML()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("<" + (String)this.elements.get(0) + ">");
    for (int i = 1; i < this.elements.size(); i++)
    {
      localStringBuilder.append("<" + (String)this.elements.get(0) + String.format("%1$02d", new Object[] { Integer.valueOf(i) }) + "><![CDATA[");
      localStringBuilder.append((String)this.elements.get(i));
      localStringBuilder.append("]]></" + (String)this.elements.get(0) + String.format("%1$02d", new Object[] { Integer.valueOf(i) }) + ">");
    }
    localStringBuilder.append("</" + (String)this.elements.get(0) + ">");
    return localStringBuilder.toString();
  }
  
  public String toXML(boolean paramBoolean)
  {
    if (paramBoolean) {
      removeTrailingEmptyElements();
    }
    return toXML();
  }
}

