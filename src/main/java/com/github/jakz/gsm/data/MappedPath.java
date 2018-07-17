package com.github.jakz.gsm.data;

import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JFileChooser;

public class MappedPath
{
  private enum Parent
  {
    DOCUMENTS_FOLDER
  };
  
  private final Parent parent;
  private final Path path;
  
  private MappedPath(Parent parent, Path path)
  {
    this.parent = parent;
    this.path = path;
  }
  
  public boolean exists() 
  {
    switch (parent)
    {
      case DOCUMENTS_FOLDER:
      {
        Path documents = new JFileChooser().getFileSystemView().getDefaultDirectory().toPath();
        return Files.exists(documents.resolve(path));
      }
      
      default:
        return false;
    }
  }
  
  public static MappedPath ofDocuments(Path subPath)
  {
    return new MappedPath(Parent.DOCUMENTS_FOLDER, subPath);
  }
}

