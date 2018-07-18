package com.github.jakz.gsm.data;

import java.util.Arrays;

public enum SaveLocation
{
  DOCUMENTS_FOLDER("documents", "Documents"),
  MY_GAMES_FOLDER("my-games", "My Games"),
  APPDATA_ROAMING("appdata-roaming", "AppData/Roaming")
  
  ;
  
  private SaveLocation(String yaml, String caption)
  {
    this.yaml = yaml;
    this.caption = caption;
  }
  
  public final String yaml;
  public final String caption;
  
  public static SaveLocation ofYAML(String value)
  {
    return Arrays.stream(values())
      .filter(p -> p.yaml.equals(value))
      .findFirst()
      .orElse(null);
  }
}