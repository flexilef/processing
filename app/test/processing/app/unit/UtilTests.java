package processing.app;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import processing.app.Util;
//import junit.framework.Assert; //TEST
import org.junit.Assert;
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
  
  private static String TEST_FILE = "test/resources/unit1/UtilTestFile.pde";
  private static String ORIGINAL_DIR = "test/resources/unit1";
  private static String COPIED_DIR = "test/resources/unit2";
  private static String DELETE_DIR = "test/resources/unit3";
  private static String DELETE_FILE = "test/resources/unit3/UtilFileToDelete.pde";
  private static String MAIN_DIR = "test/resources/unit4/";
  private static String FILE_WITH_EXT = "test/resources/unit4/UtilValidFile.pde";
  private static String FILE_WITHOUT_EXT = "test/resources/unit4/UtilValidFile";
  
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
  
  //@ameza
  @Test
  public void shouldCopyDir() {    
    
    File originalDir = new File(ORIGINAL_DIR);
    File copyDir= new File(COPIED_DIR);
    File testFile = new File(TEST_FILE);
    
    try {
      originalDir.mkdir();
      copyDir.mkdir();
      
      if (originalDir.exists()) {
        if(!testFile.exists()) {
          testFile.createNewFile();
        } 
      }
      
      Assert.assertFalse(FileUtils.directoryContains(copyDir, testFile));
      Util.copyDir(originalDir, copyDir);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
   try {
      Assert.assertTrue(FileUtils.directoryContains(copyDir, testFile));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  //@ameza
  @Test
  public void shouldDeleteDir() {
    File deleteDir = new File(DELETE_DIR);
    
    //Remove empty dir
    deleteDir.mkdir();
    Assert.assertTrue(deleteDir.exists());
    Util.removeDir(deleteDir);
    Assert.assertFalse(deleteDir.exists());
    
    //Remove dir with a file
    deleteDir.mkdir();
    Assert.assertTrue(deleteDir.exists());
    File deleteFile = new File(DELETE_FILE);
    
    try {
      deleteFile.createNewFile();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    Assert.assertTrue(deleteFile.exists());
    Util.removeDir(deleteDir);
    Assert.assertFalse(deleteDir.exists());
  }
  
  //@ameza
  @Test
  public void shouldCreateTempFile() {
    File temp; 
    try {
      temp=Util.createTempFolder("t", "temp", null); //name less than 3 characters
      Assert.assertTrue(temp.exists()); //folder created
      Assert.assertTrue(temp.getName().contains("_")); //name contains "_"
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  //@ameza
  @Test
  public void shouldListFiles() {
    File mainDir=new File(MAIN_DIR);
    String[] lstAbsolute, lstRelative;
      
    mainDir.mkdir();
    Assert.assertTrue(mainDir.exists());
    
    File testFile1 = new File(FILE_WITH_EXT);
    File testFile2 = new File(FILE_WITHOUT_EXT);
    try {
      testFile1.createNewFile();
      testFile2.createNewFile();
      
      Assert.assertTrue(testFile1.exists() && testFile2.exists());
      
      lstAbsolute = Util.listFiles(mainDir, false);  
      lstRelative = Util.listFiles(mainDir, true);
      
      Assert.assertTrue(lstAbsolute.length==2 && lstRelative.length==2);
      
      //System.out.println(Arrays.toString(lstAbsolute));
      //System.out.println(Arrays.toString(lstRelative));
      
      for( int i = 0; i < lstAbsolute.length - 1; i++)
      {
        Assert.assertTrue(lstAbsolute[i].length()>lstRelative[i].length());
      }     
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}

