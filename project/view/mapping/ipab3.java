import processing.core.*; 
import processing.xml.*; 

import net.nexttext.*; 
import net.nexttext.behaviour.*; 
import net.nexttext.behaviour.control.*; 
import net.nexttext.behaviour.physics.*; 
import net.nexttext.behaviour.standard.*; 
import net.nexttext.behaviour.dform.*; 

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

public class ipab3 extends PApplet {










// attributes
Book book;
PFont steelfish;

int SPEED = 0;
float FRAMERATES= 60;
String PAGES = "Second";
int BAR_SIZE = 80;
int LABEL_HEIGHT= 80;
int LABEL_WIDTH= 200;
String cities[] = {
  "Bangalore",
  "Delhi",
  "Kolkatta",
  "Mumbai",
  "Chennai"
};

int no_of_cities = cities.length;
int no_of_cites_added = 0;

String depts[] = {
  "Revenue Department",
  "Police Department",
  "Electricity Department",
  "Income Tax",
  "Indian Railways"
};

int bribe_value[] = {
  10,
  5,
  3,
  2,
  15
};

int destination[] = {
  1,
  3,
  2,
  0,
  4
};


Behaviour behaviourTree;
IsInside isInside;
MoveBy moveBy;
MoveTo moveTo;

Behaviour behaviourTree1;
IsInside isInside1;
MoveBy moveBy1;
MoveTo moveTo1;

boolean stop = false;

public void setup() {
  // init the applet
  size(600, 400);
  smooth();
  frameRate(FRAMERATES);
  rectMode(CORNER);
  strokeWeight(2);
  
  // create the book
  book = new Book(this);
  for(int i=0; i < cities.length; i++){
    book.addPage(cities[i]);
    //book.addPage(depts[i]); 
  }
  
  
  // init and set the font
  steelfish = createFont("Steelfish.ttf", 48, true);
  textFont(steelfish);
  
  // add the starting stats
 addStat(0);
 addDept(0);
 no_of_cites_added =  no_of_cites_added +1;
  
}

public void addStat(int type) {
  fill(0);
  noStroke();
  
  // create the Behaviour tree
  moveBy = new MoveBy(0, (height-(type*LABEL_HEIGHT)));
  moveTo = new MoveTo(0, height-(type*LABEL_HEIGHT));
  isInside = new IsInside(this.getBounds(), moveBy, moveTo);
  behaviourTree = isInside.makeBehaviour();
    
  book.addGroupBehaviour(behaviourTree);
  
  // add the text to the appropriate page
  book.addText(cities[type], 2, 40, cities[type]);

  // clean up
  book.removeGroupBehaviour(behaviourTree);
}

public void addDept(int type) {
  fill(0);
  noStroke();
  
  // create the Behaviour tree
  moveBy1 = new MoveBy(width-LABEL_WIDTH, (height-(type*LABEL_HEIGHT)));
  moveTo1 = new MoveTo(width-LABEL_WIDTH, (height-(type*LABEL_HEIGHT)));
  isInside1 = new IsInside(this.getBounds(), moveBy1, moveTo1);
  behaviourTree1 = isInside1.makeBehaviour();
    
  book.addGroupBehaviour(behaviourTree1);
  // add the text to the appropriate page
  book.addText(depts[type], 2, 40, cities[type]);  

  // clean up
  book.removeGroupBehaviour(behaviourTree1);
}

public void drawBar(int type) {
  // draw a line from the bottom-right of the applet to the top-left of the bar
  if(!stop){
          println("lining="+type);
          //int destination=1;
          stroke(255, 100, 0);
          strokeWeight(type);
          line((width/FRAMERATES)*(frameCount%FRAMERATES),(height-(LABEL_HEIGHT*destination[type-1])), 100, (height-(LABEL_HEIGHT*(type-1))));
          // draw a bar representing the appropriate time frame
          noStroke();
          //fill(255, 243-26*type, 0);
          fill(255, 100, 0);

          rect((width/FRAMERATES)*(frameCount%FRAMERATES), 0, BAR_SIZE, height);
          for(int i=0; i < type-1; i++){
                stroke(0, 0, 0);
                strokeWeight(bribe_value[i]);
                smooth();
                //line(100,(height-(LABEL_HEIGHT*destination[i])),100,(height-(LABEL_HEIGHT*(destination[i]))));
                 line(100,(height-(LABEL_HEIGHT*i)), 400, (height-(LABEL_HEIGHT*(destination[i]))));
          }
  }
}

public void draw() {
  fill(255, 183, 0, 200);
  rect(0, 0, width, height);
  
 
  // apply the behaviours to the text
  book.step();
  
  // draw the layers in order
  for(int i=0; i < cities.length; i++){
      book.drawPage(cities[i]);
      //book.drawPage(depts[i]);
  }
  
  if(no_of_cites_added < no_of_cities+1){
    drawBar(no_of_cites_added);
  }
  
  // add new stats if necessary
  if (frameCount%FRAMERATES == 0) {
        if(no_of_cites_added < no_of_cities){
          addStat(no_of_cites_added);
          addDept(no_of_cites_added);
        }
        if( no_of_cites_added > no_of_cities){
              stop = true;  
        }
       no_of_cites_added = no_of_cites_added + 1;
  }

  if(stop){
      noLoop(); 
      for(int i=0; i <  no_of_cities; i++){
                stroke(0, 0, 0);
                smooth();
                strokeWeight(bribe_value[i]);
                //line(100,(height-LABEL_HEIGHT-LABEL_HEIGHT*i)+50,width-LABEL_WIDTH,height);
                 //line(100,(height-(LABEL_HEIGHT*destination[i])), 100, (height-(LABEL_HEIGHT*(i))));
                  line(100,(height-(LABEL_HEIGHT*i)), 400, (height-(LABEL_HEIGHT*(destination[i]))));

     }
  }

  
}
  static public void main(String args[]) {
    PApplet.main(new String[] { "--present", "--bgcolor=#666666", "--stop-color=#cccccc", "ipab3" });
  }
}
