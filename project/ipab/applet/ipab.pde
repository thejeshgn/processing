/* Code heavily inspired/borrowed from Ben Fry's original code example */ 
/* By Thejesh GN  */

import treemap.*;

Node rootItem;
NodeElement rolloverItem;
Node taggedItem;

BoundsIntegrator zoomBounds;
Node zoomItem;

RankedLongArray modTimes = new RankedLongArray();

PFont font;


void setup() {
    size(500, 500);
    zoomBounds = new BoundsIntegrator(0, 0, width, height);
    
    cursor(CROSS);
    rectMode(CORNERS);
    smooth();
    noStroke();
  
   font = createFont("SansSerif", 13);
   setRoot("Locations");
}

  
void setRoot(String folder) {
  Node tm = new Node(null, folder, 0, 0);
  tm.setBounds(0, 0, width, height);
  tm.contentsVisible = true;
    
  rootItem = tm;
  rootItem.zoomIn();
  rootItem.updateColors();
}


void draw() {
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


void mousePressed() {
    if (zoomItem != null) {
      zoomItem.mousePressed();
    }
}

