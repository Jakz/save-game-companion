package com.github.jakz.gsm;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main(String[] args)
    {
      try
      {
        GameDB db = GameDB.ofYAML(Paths.get("db.yaml"));
        System.out.println("Loaded DB with "+db.size()+" games.");
      } 
      catch (IOException e) 
      {
        e.printStackTrace();
      }
    }
}
