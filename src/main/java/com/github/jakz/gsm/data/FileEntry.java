package com.github.jakz.gsm.data;

import java.nio.file.Path;

import com.pixbits.lib.io.archive.ArchiveFormat;

public class FileEntry implements SaveEntry
{
  private final MappedPath path;
  
  public FileEntry(MappedPath path)
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
