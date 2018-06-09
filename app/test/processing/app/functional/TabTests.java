package processing.app.functional;

import java.io.File;
import java.io.IOException;

import javax.swing.JPopupMenu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import processing.app.Base;
import processing.app.Console;
import processing.app.Language;
import processing.app.Messages;
import processing.app.Platform;
import processing.app.Preferences;
import processing.app.SingleInstance;
import processing.app.Sketch;
import processing.app.Util;
import processing.app.ui.Editor;
import processing.app.ui.Welcome;
import processing.core.PApplet;

public class TabTests {
  
  private Sketch sketch;
  private Base base;
  private Editor editor;
  
  private static final String NEW_TAB_NAME = "newTab";
  private static final String INIT_TAB_NAME = "untitled";
  
  public TabTests() {
   
//    try {
//      Platform.initBase(base);
//    } catch (Exception e1) {
//      // TODO Auto-generated catch block
//      e1.printStackTrace();
//    }
//    editor = base.getActiveEditor();
//    System.out.println("EDITOR" + editor);
//    
//    try {
//      sketch = new Sketch(INIT_TAB_NAME, editor);
//    } catch (IOException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
  }
  
  @Before
  public void setup() {
    
  }
  
  @After
  public void tearndown() {
    
  }
  
  @Test
  public void shouldOpenNewTab() {
    sketch.handleNewCode();
  }
}
