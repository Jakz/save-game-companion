package com.github.jakz.gsm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.esotericsoftware.yamlbeans.YamlReader;
import com.github.jakz.gsm.data.SaveLocation;
import com.github.jakz.gsm.data.FolderEntry;
import com.github.jakz.gsm.data.Game;
import com.github.jakz.gsm.data.SavePath;
import com.github.jakz.gsm.data.SaveEntry;
import com.pixbits.lib.parser.ParseException;

public class GameDB
{
  private final Map<Game, List<SaveEntry>> entries;
  
  GameDB()
  {
    entries = new HashMap<>();
  }
  
  public int size() { return entries.size(); }
  
  
  @SuppressWarnings("unchecked")
  public static GameDB ofYAML(Path path) throws IOException
  {
    GameDB db = new GameDB();
        
    try (BufferedReader frdr = Files.newBufferedReader(path))
    {
      YamlReader rdr = new YamlReader(frdr);
      
      List<Map<String, ?>> ygames = (List<Map<String, ?>>)rdr.read();
      
      for (Map<String, ?> ygame : ygames)
      {
        String gameName = (String) ygame.get("name");
        String gameYear = (String) ygame.get("year");
        List<Map<String, ?>> ysaves = (List<Map<String, ?>>) ygame.get("save");
        
        Game game = new Game(gameName, Integer.valueOf(gameYear));
        List<SaveEntry> saves = db.entries.computeIfAbsent(game, g -> new ArrayList<>());
        
        for (Map<String, ?> ysave : ysaves)
        {
          List<String> ypath = (List<String>) ysave.get("path");
          
          SaveLocation base = SaveLocation.ofYAML(ypath.get(0));
          Path param = Paths.get(ypath.get(1));
          
          SavePath mpath = new SavePath(base, param);
          SaveEntry entry = null;
          
          String ytype = (String) ysave.get("type");
          
          switch (ytype)
          {
            case "whole-folder":
            {
              entry = new FolderEntry(mpath);
              break;
            }
            
            default:
            {
              throw new IllegalArgumentException("Unknown save entry type: "+ytype);
            }
          }
          
          if (entry != null)
            saves.add(entry);
        }
  
        db.entries.put(game, null);
      }
    
    }
    
    return db;
  }
}
