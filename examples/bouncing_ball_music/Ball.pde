class Ball {

  float r;   // radius
  float x,y; // current location of the ball
  float xspeed,yspeed; // speed
  Minim out;
  
  // Constructor
  Ball(float beginingRadius, Minim out) {
    r = beginingRadius;
    x = random(width);
    y = random(height);
    this.out = out;
    xspeed = random( - 5,5);
    yspeed = random( - 5,5);
  }
  
  void bounce() {
    x += xspeed; 
    y += yspeed; 
      AudioPlayer big = null;    
    // Check horizontal edges
    if (x > width || x < 0) {
      xspeed *= - 1;
      big = out.loadFile("small.mp3");
      big.loop();
      big.close();
      println("------Bouncing--- ");
    }
    //Check vertical edges
    if (y > height || y < 0) {
      yspeed *= - 1;
       //big = out.loadFile("small.mp3");
      //big.play();
      //big.close();
    }
  }
  
  // Draw the ball
  void display() {
    stroke(0);
    fill(0,50);
    ellipse(x,y,r*2,r*2);
  }
}
