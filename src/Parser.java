/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vmanchala
 */
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public abstract interface Parser
{
  public abstract EDI parse(File paramFile)
    throws FormatException, IOException;
  
  public abstract EDI parse(String paramString)
    throws FormatException;
  
  public abstract EDI parse(InputStream paramInputStream)
    throws FormatException, IOException;
}
