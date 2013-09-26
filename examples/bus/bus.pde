int pixel_move = 1;
void setup(){
  size(800,400);
  frameRate(10);
}

void draw(){
  translate(100+pixel_move,100);
 background(100,100,100); 
 fill(255,0,0);
 rect(0,0,100,40);
  fill(0,0,0);
  ellipse(20,40,25,25);
  ellipse(60,40,25,25);
  pixel_move = pixel_move+1;
}
