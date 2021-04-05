package com.rueggerllc.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

public class FindJars {

  // private static final String SEARCH_PATH = "C:\\workspace\\RPLaunch";
  private static final String SEARCH_PATH = ".";
  private static String CLASS_FILE_TO_FIND = "javax.ejb.SessionBean";
  private static List<String> foundIn = new LinkedList<String>();
  private File start;

  /**
   * @param args the first argument is the path of the file to search in. The second may be the
   *     class file to find.
   */
  public static void main(String[] args) {
    File start;
    new Scanner(args[0]);
    if (args.length > 0) {
      start = new File(args[0]);
      if (args.length > 1) {
        CLASS_FILE_TO_FIND = args[1];
      }
    } else {
      start = new File(SEARCH_PATH);
    }
    if (!CLASS_FILE_TO_FIND.endsWith(".class")) {
      CLASS_FILE_TO_FIND = CLASS_FILE_TO_FIND.replace('.', '/') + ".class";
    }
    search(start);
    System.out.println("------RESULTS------");
    for (String s : foundIn) {
      System.out.println(s);
    }
  }

  private static void search(File start) {
    try {
      final FileFilter filter =
          new FileFilter() {
            public boolean accept(File pathname) {
              return pathname.getName().endsWith(".jar") || pathname.isDirectory();
            }
          };
      for (File f : start.listFiles(filter)) {
        if (f.isDirectory()) {
          search(f);
        } else {
          searchJar(f);
        }
      }
    } catch (Exception e) {
      System.err.println("Error at: " + start.getPath() + " " + e.getMessage());
    }
  }

  private static void searchJar(File f) {
    try {
      System.out.println("Searching: " + f.getPath());
      JarFile jar = new JarFile(f);
      ZipEntry e = jar.getEntry(CLASS_FILE_TO_FIND);
      if (e == null) {
        e = jar.getJarEntry(CLASS_FILE_TO_FIND);
      }
      if (e != null) {
        foundIn.add(f.getPath());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
