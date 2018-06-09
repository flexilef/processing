package processing.app.unit;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import junit.framework.Assert;
import processing.app.Base;
import processing.app.Console;
import processing.app.Platform;
import processing.app.Sketch;
import processing.app.SketchCode;
import processing.app.ui.Editor;

public class SketchCodeTests {

  private static String SKETCH_PATH = "test/resources/SketchTests.pde";
  private static String NEW_SKETCH_NAME = "SketchTestsNew.pde";
  private static String SKETCH_PATH_NEW = "test/resources/" + NEW_SKETCH_NAME;

  public SketchCodeTests() {

  }

  @Before
  public void setup() {
    // Set up something for the test.
  }

  @After
  public void teardown() {
    // Tear down something for the test.
    
    //delete generated test files
    File newSketch = new File(SKETCH_PATH_NEW);
    newSketch.delete();
  }

  @Test
  public void shouldSaveAsNewFile() {
    SketchCode sketchCode = new SketchCode(new File(SKETCH_PATH), ".pde");
    File newSketch = new File(SKETCH_PATH_NEW);
    
    try {
      sketchCode.saveAs(newSketch);
    } catch (IOException e) {   
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    Assert.assertTrue(newSketch.exists());
    Assert.assertEquals(sketchCode.getFileName(), NEW_SKETCH_NAME);
  }
}
