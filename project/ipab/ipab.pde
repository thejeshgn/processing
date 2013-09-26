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
XMLElement xml;

void setup() {
    size(500, 500);
    zoomBounds = new BoundsIntegrator(0, 0, width, height);
    
    cursor(CROSS);
    rectMode(CORNERS);
    smooth();
    noStroke();
  
   font = createFont("SansSerif", 13);
   xml = new XMLElement(this, "tree.xml");
   String title =  xml.getString("name");
   print(title);
   setRoot(xml);
}

  
void setRoot(XMLElement content) {
  println("Root is set");
  color c = #233456;
  Node tm = new Node(null, content,  0, 0);
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

