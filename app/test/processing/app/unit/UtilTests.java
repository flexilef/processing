package processing.app.unit;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import processing.app.Util;
import junit.framework.Assert;

public class UtilTests {
  
  private static String ORIGINAL_FILE = "test/resources/unit/UtilCopyFile.pde";
  private static String COPIED_FILE = "test/resources/unit/UtilCopyFile_copy.pde";
  private static String SAVE_FILE = "test/resources/unit/UtilSaveFile.pde";

  private static String EMPTY_PROGRAM = "";
  private static String PROGRAM = "int a;\n"
    + "a = 3;\n"
    + "System.out.println(\"Hello World\");\n";
  
  private static int PROGRAM_LINE_COUNT = 4;
  private static int EMPTY_PROGRAM_LINE_COUNT = 1;
  
  public UtilTests() {
    
  }
  
  @Before
  public void setup() {
    
  }
  
  @After
  public void teardown() {   
    File copiedFile = new File(COPIED_FILE);
    
    if(copiedFile.exists()) {
      copiedFile.delete();
    }
  }
  
  @Test
  public void emptyProgramShouldCountAsOneLine() {
    int lines = Util.countLines(EMPTY_PROGRAM);
    
    Assert.assertEquals(EMPTY_PROGRAM_LINE_COUNT, lines);
  }
  
  @Test
  public void shouldRetrieveProgramLineCount() {
    int lines = Util.countLines(PROGRAM);
    
    Assert.assertEquals(PROGRAM_LINE_COUNT, lines);
  }

  //23 lines covered
  
  @Test
  public void shouldCopyFile() {
    File originalFile = new File(ORIGINAL_FILE);
    File copyFile = new File(COPIED_FILE);
    
    try {
      Util.copyFile(originalFile, copyFile);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    try {
      Assert.assertTrue(FileUtils.contentEquals(originalFile, copyFile));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  //20 lines covered
  
  
  @Test
  public void shouldSaveStringIntoFile() {
    File saveFile = new File(SAVE_FILE);
    
    try {
      Util.saveFile(PROGRAM, saveFile);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
        
    String encoding = null;
    try {
      Assert.assertEquals(PROGRAM, FileUtils.readFileToString(saveFile, encoding));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}

