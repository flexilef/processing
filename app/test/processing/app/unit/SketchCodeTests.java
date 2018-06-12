package processing.app.unit;

import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
//import junit.framework.Assert; //temp

import org.junit.Assert;
import processing.app.SketchCode;


public class SketchCodeTests {

  private static String SKETCH_PATH = "test/resources/SketchTests.pde";
  private static String NEW_SKETCH_NAME = "SketchTestsNew.pde";
  private static String SKETCH_PATH_NEW = "test/resources/" + NEW_SKETCH_NAME;
  private static String PROGRAM = "System.out.println(\"Hello World\")";
  private static String SKETCH_ORIGINAL_NAME ="SketchTests";
  private static String SKETCH_CHANGED_NAME ="SketchTestsNew";
  private static int EMPTY_PROGRAM_LINE_COUNT = 1;
  
  
  private File sketch;
  private File newSketch;
  private SketchCode sketchCode;

  public SketchCodeTests() {
    sketch = new File(SKETCH_PATH);
    newSketch = new File(SKETCH_PATH_NEW);
  }

  @Before
  public void setup() {
    try {
      sketch.createNewFile();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @After
  public void teardown() {    
    //delete generated test files    
    if(newSketch.exists()) {
      newSketch.delete();
    }
    
    if(sketch.exists()) {
      sketch.delete();
    }
  }
  
  //Coverage - app: 72 lines; core: 59 lines
  @Test
  public void shouldSaveAsNewFile() {    
    sketchCode = new SketchCode(sketch, ".pde");

    try {
      sketchCode.saveAs(newSketch);
    } catch (IOException e) {   
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    Assert.assertTrue(newSketch.exists());
    Assert.assertEquals(sketchCode.getFileName(), NEW_SKETCH_NAME);
  }
  
  //Coverage is increased by 25 lines when executing shouldSaveProgram()
  //and shouldRetrieveLineCountOfEmptyProgram()
  //Coverage - app: 97 lines; core: 59 lines 
  @Test
  public void shouldSaveProgram() {
    sketchCode = new SketchCode(sketch, ".pde");
    sketchCode.setProgram(PROGRAM);
    
    try {
      sketchCode.save();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    Assert.assertEquals(sketchCode.getSavedProgram(), PROGRAM);
  }
  
  @Test
  public void shouldRetrieveLineCountOfEmptyProgram() {
    sketchCode = new SketchCode(sketch, ".pde");
    int lines = sketchCode.getLineCount();
        
    Assert.assertEquals(EMPTY_PROGRAM_LINE_COUNT, lines);
  }
  
  //@ameza: sketch is renamed
  @Test
  public void shouldRenameTo() throws IOException {    
    SketchCode sketchCode = new SketchCode(sketch, ".pde");

    if(sketch.exists()) {     
      //System.out.println("renaming " + sketchCode.getPrettyName() + " to "+ newSketch.getName());
      Assert.assertEquals( sketchCode.getPrettyName(), SKETCH_ORIGINAL_NAME);
      sketchCode.renameTo(newSketch, ".pde");
      //System.out.println(sketchCode.getPrettyName()+ " name changed");
    }
    Assert.assertEquals( sketchCode.getPrettyName(), SKETCH_CHANGED_NAME);
   
  }
}
