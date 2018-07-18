package com.github.jakz.gsm.data;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import javax.swing.JFileChooser;

public class SavePath
{
  private final SaveLocation parent;
  private final Path path;
  
  public SavePath(SaveLocation parent, Path path)
  {
    this.parent = parent;
    this.path = path;
  }
  
  private Path resolve()
  {
    switch (parent)
    {
      case DOCUMENTS_FOLDER:
      {
        Path documents = new JFileChooser().getFileSystemView().getDefaultDirectory().toPath();
        return documents.resolve(path);
      }
      
      default:
        throw new IllegalArgumentException("Unknown MappedPath type");
    }
  }
  
  public boolean exists() 
  {
    return Files.exists(resolve());
  }
  
  public boolean equals(Object obj)
  {
    if (obj instanceof SavePath)
    {
      SavePath other = (SavePath)obj;
      return other.parent == this.parent && other.path.equals(this.path);
    }
    else
      return false;
  }
  
  public int hashCode()
  {
    return Objects.hash(parent, path);
  }
  
  public String toString() { return path.toString(); }
  public Path path() { return resolve(); }
  
  
  public static SavePath ofDocuments(Path subPath)
  {
    return new SavePath(SaveLocation.DOCUMENTS_FOLDER, subPath);
  }
  
  public static SavePath ofMyGamesFolder(Path subPath)
  {
    return new SavePath(SaveLocation.MY_GAMES_FOLDER, subPath);
  }
  
  public static SavePath ofAppDataRoaming(Path subPath)
  {
    return new SavePath(SaveLocation.APPDATA_ROAMING, subPath);
  }
  
  public static SavePath ofType(SaveLocation type, Path subPath)
  {
    return new SavePath(type, subPath);
  }
  

}

