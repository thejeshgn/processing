int i = 1;

void setup(){
size(600, 400);  
background(100);  
smooth();
noStroke(); 
frameRate(1);
}
void draw(){
background(100);  

  translate(300, 200);  
fill(195, 200, 250, 60);    
pushMatrix();    
rotate(radians(i*360/10));    
ellipse(0,0,300,50);    
popMatrix();  
if(i == 10){
 i = 0; 
}

i++;
}


