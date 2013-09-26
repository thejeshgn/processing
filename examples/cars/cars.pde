class car{

	//properties
	String my_make;
	String my_num;
	int my_curPosx;
	int my_curPosY;
	int my_speed;




	//building our car
	car(String make, String num,  int curX, int curY ){
		 my_make = make;
		 my_num = num;
		 my_curPosx = curX;
		 my_curPosY = curY;
	}

	
	void drive(int speed){
          my_speed= speed;
          my_curPosx  = my_curPosx  + my_speed;
            if (my_curPosx  > width) {
              my_curPosx  = 0;
            }
        }

	

	void stop(){
		my_speed = 0;
	}


        void display(){
              stroke(0);
              fill(0,0,0);
              rectMode(CENTER);
              rect(my_curPosx,my_curPosY,20,10);
        }

}



/*---------------------------Lets race-----------------------------------------*/

car thejsCar = null;
car batmansCar = null;
void setup(){
  size(500,600);
  //lets construct a new maruthi car for me
  thejsCar = new car("Maruthi", "KA51", 10,10);
  //in the mean time @ batmans cave	 
  batmansCar = new car("BatMobile","1",10,200);
}

void draw(){
   background(255);
   //let me start driving it
   thejsCar.drive(10);
   //let me display the car on screen
   thejsCar.display();
   //batman is faster than me
   batmansCar.drive(30);
  //let me display batmans car on screen
  batmansCar.display();
}
