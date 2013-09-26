size(600, 400);  
background(100);  
smooth();
noStroke(); 
pushMatrix();
translate(100, 100);  
for(int i = 0; i < 7; i++) {    
//fill(195, 200, 250, 60);    
fill(245,205,220,80);
pushMatrix();    
// rotate each petal 62 degrees  = 360/5 loops  
rotate(radians(i*360/7));    
ellipse(0,0,100,20);    
popMatrix();  
}  
fill(244,0,0);
ellipse(0,0,18,18);
popMatrix();
fill(244,0,0);
text("Anju",20,20);
save("myflower.jpg");
//-------------------
pushMatrix();
translate(200,200);
//-----------A
pushMatrix();    
rotate(radians(360/7));    
ellipse(0,0,100,20);    
rotate(radians(360/5));    
ellipse(20,20,100,20);    
popMatrix();

popMatrix();
//----------------------


//----------N
pushMatrix();    
rotate(radians(360/7));    
ellipse(0,0,100,20);    
rotate(radians(360/5));    
ellipse(20,20,100,20);    
rotate(radians(360/5));    
ellipse(-20,-20,100,20);    

popMatrix();




//--------------------------

popMatrix();
