size(600, 400);  
background(100);  
smooth();
noStroke(); 
translate(300, 200);  

for(int i = 0; i < 10; i++) {    
fill(195, 200, 250, 60);    
pushMatrix();    
// rotate each petal 62 degrees  = 360/5 loops  
rotate(radians(i*360/10));    
ellipse(0,0,300,50);    
popMatrix();  

}  


