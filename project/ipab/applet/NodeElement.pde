/* Code heavily inspired/borrowed from Ben Fry's original code example */ 
/* By Thejesh GN  */

class NodeElement extends SimpleMapItem {
  Node parent;    
  //File file;
    String file;
  String name;
  int level;
  
  color c;
  float hue;
  float brightness;
    
  float textPadding = 8;
    
  float boxLeft, boxTop;
  float boxRight, boxBottom;


  NodeElement(Node parent, String file, int level, int order) {
    this.parent = parent;
    this.file = file;
    this.order = order;
    this.level = level;
      
    name = file.toString();
    size =  random(1, 150);

   // modTimes.add(file.lastModified());
   long a = int(random(10000,200000));
    modTimes.add(a);
  }

  
  void updateColors() {
    if (parent != null) {
      hue = map(order, 0, parent.getItemCount(), 0, 360);
    }
    //brightness = modTimes.percentile(file.lastModified()) * 100;
     long a = int(random(100,200));
    brightness = modTimes.percentile(a) * 100;

    colorMode(HSB, 360, 100, 100);
    if (parent == zoomItem) {
      //c = color(hue, 80, 80);
        c = color(int(random(0,255)),int(random(0,255)),int(random(0,255)));
    } else if (parent != null) {
        c = color(int(random(0,255)),int(random(0,255)),int(random(0,255)));
      //c = color(parent.hue, 80, brightness);
    }
    colorMode(RGB, 255);
  }
  
  
  void calcBox() {
    boxLeft = zoomBounds.spanX(x, 0, width);
    boxRight = zoomBounds.spanX(x+w, 0, width);
    boxTop = zoomBounds.spanY(y, 0, height);
    boxBottom = zoomBounds.spanY(y+h, 0, height);
  }


  void draw() {
    calcBox();

    fill(c);
    rect(boxLeft, boxTop, boxRight, boxBottom);

    if (textFits()) {
      drawTitle();
    } else if (mouseInside()) {
      rolloverItem = this;
    }
   }
    
    
  void drawTitle() {
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


  boolean textFits() {
    float wide = textWidth(name) + textPadding*2;
    float high = textAscent() + textDescent() + textPadding*2;
    return (boxRight - boxLeft > wide) && (boxBottom - boxTop > high); 
  }
    
 
  boolean mouseInside() {
    return (mouseX > boxLeft && mouseX < boxRight && 
            mouseY > boxTop && mouseY < boxBottom);    
  }


  boolean mousePressed() {
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
