/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vmanchala
 */
public class Context
{
  private Character s;
  private Character e;
  private Character c;
  
  public Context() {}
  
  public Context(Character paramCharacter1, Character paramCharacter2, Character paramCharacter3)
  {
    this.s = paramCharacter1;
    this.e = paramCharacter2;
    this.c = paramCharacter3;
  }
  
  public Character getCompositeElementSeparator()
  {
    return this.c;
  }
  
  public Character getElementSeparator()
  {
    return this.e;
  }
  
  public Character getSegmentSeparator()
  {
    return this.s;
  }
  
  public void setCompositeElementSeparator(Character paramCharacter)
  {
    this.c = paramCharacter;
  }
  
  public void setElementSeparator(Character paramCharacter)
  {
    this.e = paramCharacter;
  }
  
  public void setSegmentSeparator(Character paramCharacter)
  {
    this.s = paramCharacter;
  }
  
  public String toString()
  {
    return "[" + this.s + "," + this.e + "," + this.c + "]";
  }
}

