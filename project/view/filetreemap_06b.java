import processing.core.*; 
import processing.xml.*; 

import treemap.*; 
import javax.swing.*; 

import java.applet.*; 
import java.awt.Dimension; 
import java.awt.Frame; 
import java.awt.event.MouseEvent; 
import java.awt.event.KeyEvent; 
import java.awt.event.FocusEvent; 
import java.awt.Image; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class filetreemap_06b extends PApplet {

/*
This book is here to help you get your job done. In general, you may use the 
code in this book in your programs and documentation. You do not need to contact 
us for permission unless you\ufffdre reproducing a significant portion of the code. 
For example, writing a program that uses several chunks of code from this book 
does not require permission. Selling or distributing a CD-ROM of examples from 
O\ufffdReilly books does require permission. Answering a question by citing this book 
and quoting example code does not require permission. Incorporating a significant
amount of example code from this book into your product\ufffds documentation does 
require permission.

We appreciate, but do not require, attribution. An attribution usually includes
the title, author, publisher, and ISBN. For example: \ufffdVisualizing Data, First 
Edition by Ben Fry. Copyright 2008 Ben Fry, 9780596514556.\ufffd

If you feel your use of code examples falls outside fair use or the permission
given above, feel free to contact us at permissions@oreilly.com.
*/




FolderItem rootItem;
FileItem rolloverItem;
FolderItem taggedItem;

BoundsIntegrator zoomBounds;
FolderItem zoomItem;

RankedLongArray modTimes = new RankedLongArray();

PFont font;


public void setup() {
  size(900, 500);
  zoomBounds = new BoundsIntegrator(0, 0, width, height);
  
  cursor(CROSS);
  rectMode(CORNERS);
  smooth();
  noStroke();

  font = createFont("SansSerif", 13);

 // selectRoot();
 setRoot("Locations");
}

/*
void selectRoot() {
  SwingUtilities.invokeLater(new Runnable() {
    public void run() {
      JFileChooser fc = new JFileChooser();
      fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      fc.setDialogTitle("Choose a folder to browse...");

      int returned = fc.showOpenDialog(frame);
      if (returned == JFileChooser.APPROVE_OPTION) {
        File file = fc.getSelectedFile();
        setRoot(file);
      }
    }
  });
}
*/
  
public void setRoot(String folder) {
  FolderItem tm = new FolderItem(null, folder, 0, 0);
  tm.setBounds(0, 0, width, height);
  tm.contentsVisible = true;
    
  rootItem = tm;
  rootItem.zoomIn();
  rootItem.updateColors();
}


public void draw() {
  background(0);
  textFont(font);
  
  frameRate(30);
  zoomBounds.update();

  rolloverItem = null;
  taggedItem = null;

  if (rootItem != null) {
    rootItem.draw();
  }
  if (rolloverItem != null) {
    rolloverItem.drawTitle();
  }
  if (taggedItem != null) {
    taggedItem.drawTag();
  }
}


public void mousePressed() {
  if (zoomItem != null) {
    zoomItem.mousePressed();
  }
}

// Code from Visualizing Data, First Edition, Copyright 2008 Ben Fry.


class FileItem extends SimpleMapItem {
  FolderItem parent;    
  //File file;
    String file;
  String name;
  int level;
  
  int c;
  float hue;
  float brightness;
    
  float textPadding = 8;
    
  float boxLeft, boxTop;
  float boxRight, boxBottom;


  FileItem(FolderItem parent, String file, int level, int order) {
    this.parent = parent;
    this.file = file;
    this.order = order;
    this.level = level;
      
    name = file.toString();
    size =  random(1, 150);

   // modTimes.add(file.lastModified());
   long a = PApplet.parseInt(random(10000,200000));
    modTimes.add(a);
  }

  
  public void updateColors() {
    if (parent != null) {
      hue = map(order, 0, parent.getItemCount(), 0, 360);
    }
    //brightness = modTimes.percentile(file.lastModified()) * 100;
     long a = PApplet.parseInt(random(100,200));
    brightness = modTimes.percentile(a) * 100;

    colorMode(HSB, 360, 100, 100);
    if (parent == zoomItem) {
      //c = color(hue, 80, 80);
        c = color(PApplet.parseInt(random(0,255)),PApplet.parseInt(random(0,255)),PApplet.parseInt(random(0,255)));
    } else if (parent != null) {
        c = color(PApplet.parseInt(random(0,255)),PApplet.parseInt(random(0,255)),PApplet.parseInt(random(0,255)));
      //c = color(parent.hue, 80, brightness);
    }
    colorMode(RGB, 255);
  }
  
  
  public void calcBox() {
    boxLeft = zoomBounds.spanX(x, 0, width);
    boxRight = zoomBounds.spanX(x+w, 0, width);
    boxTop = zoomBounds.spanY(y, 0, height);
    boxBottom = zoomBounds.spanY(y+h, 0, height);
  }


  public void draw() {
    calcBox();

    fill(c);
    rect(boxLeft, boxTop, boxRight, boxBottom);

    if (textFits()) {
      drawTitle();
    } else if (mouseInside()) {
      rolloverItem = this;
    }
   }
    
    
  public void drawTitle() {
    fill(255, 200);
    
    float middleX = (boxLeft + boxRight) / 2;
    float middleY = (boxTop + boxBottom) / 2;
    if (middleX > 0 && middleX < width && middleY > 0 && middleY < height) {
      if (boxLeft + textWidth(name) + textPadding*2 > width) {
        textAlign(RIGHT);
        text(name, width - textPadding, boxBottom - textPadding);
      } else {
        textAlign(LEFT);
        text(name, boxLeft + textPadding, boxBottom - textPadding);
      }
    }
  }


  public boolean textFits() {
    float wide = textWidth(name) + textPadding*2;
    float high = textAscent() + textDescent() + textPadding*2;
    return (boxRight - boxLeft > wide) && (boxBottom - boxTop > high); 
  }
    
 
  public boolean mouseInside() {
    return (mouseX > boxLeft && mouseX < boxRight && 
            mouseY > boxTop && mouseY < boxBottom);    
  }


  public boolean mousePressed() {
    if (mouseInside()) {
      if (mouseButton == LEFT) {
        parent.zoomIn();
        return true;

      } else if (mouseButton == RIGHT) {
        if (parent == zoomItem) {
          parent.zoomOut();
        } else {
          parent.hideContents();
        }
        return true;
      }
    }
    return false;
  }
}
// Code from Visualizing Data, First Edition, Copyright 2008 Ben Fry.


class FolderItem extends FileItem implements MapModel {
  MapLayout algorithm = new PivotBySplitSize();
  Mappable[] items;
  boolean contentsVisible;
  boolean layoutValid;
  float darkness;


  public FolderItem(FolderItem parent, String folder, int level, int order) {
    super(parent, folder, level, order);
    String[] contents = new String[5];
    ArrayList cities = new ArrayList();
    cities.add("Bangalore");
    cities.add("Mysore");
    cities.add("Delhi");
    cities.add("Bombay");
    cities.add("Kolkatta");
    
    ArrayList dept = new ArrayList();
    dept.add("Police");
    dept.add("Registration");
    dept.add("Revenue");
    dept.add("Income Tax");
    dept.add("Health");
    
    if(level == 0){
      dept.toArray(contents);
    }else{
       cities.toArray(contents);
    }  

    
    if (contents != null) {
      contents = sort(contents);
      items = new Mappable[contents.length];
      int count = 0;
      for (int i = 0; i < contents.length; i++) {
        String fileItem = contents[i];
        FileItem newItem = null;
       if (dept.contains(fileItem)) {
          newItem = new FolderItem(this, fileItem, level+1, count);
        } else {
          newItem = new FileItem(this, fileItem, level+1, count);
        }
        items[count++] = newItem;
        size += newItem.getSize();
      }
      if (count != items.length) {
        items = (Mappable[]) subset(items, 0, count);
      }
    } else {
      // If no items found in this folder, create a dummy array so that 
      // items will not be null, which will ensure that items.length will
      // return 0 rather than causing a NullPointerException.
      items = new Mappable[0];
    }
  }

  public void updateColors() {
    super.updateColors();

    for (int i = 0; i < items.length; i++) {
      FileItem fi = (FileItem) items[i];
      fi.updateColors();
    }
  }

  public void checkLayout() {
    if (!layoutValid) {
      if (getItemCount() != 0) {
        algorithm.layout(this, bounds);
      }
      layoutValid = true;
    }
  }


  public boolean mousePressed() {
    if (mouseInside()) {
      if (contentsVisible) {
        // Pass the mouse press to the child items
        for (int i = 0; i < items.length; i++) {
          FileItem fi = (FileItem) items[i];
          if (fi.mousePressed()) {
            return true;
          }
        }
      } else {  // not opened
        if (mouseButton == LEFT) {
          if (parent == zoomItem) {
            showContents();
          } else {
            parent.zoomIn();
          }            
        } else if (mouseButton == RIGHT) {
          if (parent == zoomItem) {
            parent.zoomOut();
          } else {
            parent.hideContents();
          }
        }
        return true;
      }
    }
    return false;
  }


  // Zoom to the parent's boundary, zooming out from this item
  public void zoomOut() {
    if (parent != null) {
      // Close contents of any opened children
      for (int i = 0; i < items.length; i++) {
        if (items[i] instanceof FolderItem) {
          ((FolderItem)items[i]).hideContents();
        }
      }
      parent.zoomIn();
    }
  }


  public void zoomIn() {
    zoomItem = this;
    zoomBounds.target(x, y, w, h); ///width, h/height);
  }


  public void showContents() {
    contentsVisible = true;
  }


  public void hideContents() {
    // Prevent the user from closing the root level
    if (parent != null) {
      contentsVisible = false;
    }
  }

  
  public void draw() {
    checkLayout();
    calcBox();
    
    if (contentsVisible) {
      for (int i = 0; i < items.length; i++) {
        items[i].draw();
      }
    } else {
      super.draw();
    }

    if (contentsVisible) {
      if (mouseInside()) {
        if (parent == zoomItem) {
          taggedItem = this;
        }
      }
    }
    if (mouseInside()) {
      darkness *= 0.05f;
    } else {
      darkness += (150 - darkness) * 0.05f;
    }
    if (parent == zoomItem) {
      colorMode(RGB, 255);
      fill(0, darkness);
      rect(boxLeft, boxTop, boxRight, boxBottom);
    }
  }


  public void drawTitle() {
    if (!contentsVisible) {
      super.drawTitle();
    }
  }


  public void drawTag() {
    float boxHeight = textAscent() + textPadding*2;

    if (boxBottom - boxTop > boxHeight*2) {
      // if the height of the box is at least twice the height of the tag,
      // draw the tag inside the box itself
      fill(0, 128);
      rect(boxLeft, boxTop, boxRight, boxTop+boxHeight);
      fill(255);
      textAlign(LEFT, TOP);
      text(name, boxLeft+textPadding, boxTop+textPadding);

    } else if (boxTop > boxHeight) {
      // if there's enough room to draw above, draw it there
      fill(0, 128);
      rect(boxLeft, boxTop-boxHeight, boxRight, boxTop);
      fill(255);
      text(name, boxLeft+textPadding, boxTop-textPadding);

    } else if (boxBottom + boxHeight < height) {
      // otherwise draw the tag below
      fill(0, 128);
      rect(boxLeft, boxBottom, boxRight, boxBottom+boxHeight);
      fill(255);
      textAlign(LEFT, TOP);
      text(name, boxLeft+textPadding, boxBottom+textPadding);
    }
  }


  public Mappable[] getItems() {
    return items;
  }


  public int getItemCount() {
    return items.length;
  }
}
// Code from Visualizing Data, First Edition, Copyright 2008 Ben Fry.


class RankedLongArray {
  long[] values = new long[100];
  int count;
  boolean dirty;

  public void add(long what) {
    if (count == values.length) {
      values = (long[]) expand(values);
    }
    values[count++] = what;
    dirty = true;
  }

  public void remove(long what) {
    int index = find(what, 0, count-1);
    arraycopy(values, index+1, values, index, count-index-1);
    count--;
  }

  private void update() {
    Arrays.sort(values, 0, count);
    dirty = false;
  }

  public float percentile(long what) {
    int index = find(what);
    return index / (float)count;
  }

  public int find(long what) {
    return find(what, 0, count-1);
  }

  private int find(long num, int start, int stop) {
    if (dirty) update();
      
    int middle = (start + stop) / 2;

    // if this is the char, then return it
    if (num == values[middle]) return middle;

    // doesn't exist, otherwise would have been the middle
    if (start >= stop) return -1;

    // if it's in the lower half, continue searching that
    if (num < values[middle]) {
      return find(num, start, middle-1);
    }
    // otherwise continue in the upper half
    return find(num, middle+1, stop);
  }
}
  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#F0F0F0", "filetreemap_06b" });
  }
}
