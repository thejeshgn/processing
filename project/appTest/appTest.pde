String textDisp = "";
void setup(){
 textDisp = "";
 background(255);
 frameRate(10); 
 loop();
}

void draw(){
  fill(0);
  text(textDisp, 10,10);
  println(textDisp);
}


public void setMyText(String t){
  textDisp = t;
}

void mouseClicked() {
  println("----------CLICKED--------------");
  setMyText("clicked on applet");
}
