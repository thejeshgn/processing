import ddf.minim.*;
import ddf.minim.signals.*;
import ddf.minim.analysis.*;
import ddf.minim.effects.*;

// Learning Processing
// Daniel Shiffman
// http://www.learningprocessing.com

// Example 10-2: Bouncing ball class

// Two ball variables
Ball ball1;
Ball ball2;
AudioOutput out;
Minim minim;
AudioPlayer small;
AudioPlayer big;
AudioPlayer p;
void setup() {
  size(400,400);
  smooth();
  minim = new Minim(this);
 // out = minim.getLineOut(Minim.STEREO, 2048);
    //big = minim.loadFile("small.mp3");
    //small = minim.loadFile("big.mp3");    
  // Initialize balls
  ball1 = new Ball(64, minim);
  ball2 = new Ball(32, minim);

  
}

void draw() {
  background(255);
  
  // Move and display balls
  ball1.bounce();
  ball2.bounce();
  ball1.display();
  ball2.display();
  
}


void stop()
{
  big.close();
  small.close();
  out.close();
  minim.stop();
  
  super.stop();
}
