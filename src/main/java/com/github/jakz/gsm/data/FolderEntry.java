package com.github.jakz.gsm.data;

import java.nio.file.Path;

import com.pixbits.lib.io.archive.ArchiveFormat;

public class FolderEntry implements SaveEntry
{
  private final SavePath path;
  
  public FolderEntry(SavePath path)
  {
    this.path = path;
  }
  
  @Override
  public boolean exists()
  {
    return path.exists();
  }

  @Override
  public void copyTo(Path folder)
  {
    
  }

  @Override
  public void compressTo(Path file, ArchiveFormat format)
  {
    
  }

}
