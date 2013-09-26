import napplet.*;

NAppletManager nappletManager;


void setup() {
  size(500, 500,P2D);
  
  
  nappletManager = new NAppletManager(this);
  nappletManager.createEmbeddedNApplet("MouseFollow", 25, 200);
    nappletManager.createNApplet("Animator", 275, 200);
}

void draw() {
  background(50);
  stroke(255);
  fill(255);
 // hint(DISABLE_DEPTH_TEST);
  rect(30,230,50,500);

}

public class MouseFollow extends NApplet {
  
  int x, y;
  
  void setup() {
    size(200, 200);
    x = width/2;
    y = height/2;
  }
  
  void draw() {
    if (focused) {
      x = (7*x + mouseX)/8;
      y = (7*y + mouseY)/8;
    }
    
    background(0);
    
    stroke(255);
    fill(150);

    ellipse(x, y, 50, 50);
  }    
}
