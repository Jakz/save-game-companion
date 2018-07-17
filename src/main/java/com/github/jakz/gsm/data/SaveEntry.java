package com.github.jakz.gsm.data;

import java.nio.file.Path;
import java.util.stream.Stream;

import com.pixbits.lib.io.archive.ArchiveFormat;

public interface SaveEntry
{
  public boolean exists();
  public void copyTo(Path folder);
  public void compressTo(Path file, ArchiveFormat format);
}
