
void setup(){
 size(500,500); 
 background(255);
 frameRate(1);

}

void draw()
{
}

void mouseClicked() {
    line(mouseX, mouseY, pmouseX, pmouseY); 
    println(mouseY);    
    println(pmouseY);
    
}
