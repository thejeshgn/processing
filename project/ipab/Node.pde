/* Code heavily inspired/borrowed from Ben Fry's original code example */ 
/* By Thejesh GN  */


class Node extends NodeElement implements MapModel {
  MapLayout algorithm = new PivotBySplitSize();
  Mappable[] items;
  boolean contentsVisible;
  boolean layoutValid;
  float darkness;


  public Node(Node parent, XMLElement content, int level, int order) {
    super(parent,content, order);
    int kids = content.getChildCount();

    println("Now working for"+content.getString("name")+"  Totoal children="+kids);
    
    if (kids > 0) {
      items = new Mappable[kids];
      int count = 0;
      for (int i = 0; i < kids; i++) {
        String colorString =  "";
        NodeElement newItem = new Node(this, content.getChild(i), content.getChild(i).getInt("level"), count);
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

  void updateColors() {
    super.updateColors();

    for (int i = 0; i < items.length; i++) {
      NodeElement fi = (NodeElement) items[i];
      fi.updateColors();
    }
  }

  void checkLayout() {
    if (!layoutValid) {
      if (getItemCount() != 0) {
        algorithm.layout(this, bounds);
      }
      layoutValid = true;
    }
  }


  boolean mousePressed() {
    if (mouseInside()) {
      if (contentsVisible) {
        // Pass the mouse press to the child items
        for (int i = 0; i < items.length; i++) {
          NodeElement fi = (NodeElement) items[i];
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
  void zoomOut() {
    if (parent != null) {
      // Close contents of any opened children
      for (int i = 0; i < items.length; i++) {
        if (items[i] instanceof Node) {
          ((Node)items[i]).hideContents();
        }
      }
      parent.zoomIn();
    }
  }


  void zoomIn() {
    zoomItem = this;
    zoomBounds.target(x, y, w, h); ///width, h/height);
  }


  void showContents() {
    contentsVisible = true;
  }


  void hideContents() {
    // Prevent the user from closing the root level
    if (parent != null) {
      contentsVisible = false;
    }
  }

  
  void draw() {
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
      darkness *= 0.05;
    } else {
      darkness += (150 - darkness) * 0.05;
    }
    if (parent == zoomItem) {
      colorMode(RGB, 255);
      fill(0, darkness);
      rect(boxLeft, boxTop, boxRight, boxBottom);
    }
  }


  void drawTitle() {
    if (!contentsVisible) {
      super.drawTitle();
    }
  }


  void drawTag() {
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


  Mappable[] getItems() {
    return items;
  }


  int getItemCount() {
    return items.length;
  }
}
