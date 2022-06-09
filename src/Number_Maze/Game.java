package Number_Maze;

	import enigma.console.TextAttributes;
	import enigma.core.Enigma;
	import enigma.event.TextMouseEvent;
	import enigma.event.TextMouseListener;
	import java.awt.event.KeyEvent;
	import java.awt.event.KeyListener;
	import java.io.*;
	import java.util.*;
	import java.awt.Color;

	 public class Game {
		 ///
		 public static Number[] numbers = new Number[1000];
		 public static int numbercounter =0;
		 ///
		 public static enigma.console.Console cn = Enigma.getConsole("Number Maze",100,25,20);
		 public static TextAttributes att0;
		 public static TextAttributes att1 = new TextAttributes(Color.green, Color.black);
		 public static TextAttributes att2 = new TextAttributes(Color.yellow, Color.black);
		 public static TextAttributes chosed = new TextAttributes(Color.black, Color.white); 
		 public static TextAttributes game = new TextAttributes(Color.white, Color.red); 
		 public static TextAttributes att3 = new TextAttributes(Color.red, Color.black);
		 public static TextAttributes att4 = new TextAttributes(Color.blue, Color.black);
		 public static boolean gameover=false;
		 private static final String Integer = null;
		private static char [][] table=new char[23][69];
		Stack bag1 = new Stack(8);
		Stack bag1temp = new Stack(8);
		Stack bag2 = new Stack(8);
		Stack bag2temp = new Stack(8);

	 public TextMouseListener tmlis; 
	 public KeyListener klis; 

	 // ------ Standard variables for mouse and keyboard ------
	 public int mousepr;          // mouse pressed?
	 public int mousex, mousey;   // mouse text coords.
	 public int keypr;   // key pressed?
	 public int rkey;    // key   (for press/release)
	 // ----------------------------------------------------
	 
	   
	 
	 
	 Game() throws Exception {
		 
			 
	    // ------ Standard code for mouse and keyboard ------ Do not change
	    tmlis=new TextMouseListener() {
	       public void mouseClicked(TextMouseEvent arg0) {}
	       public void mousePressed(TextMouseEvent arg0) {
	          if(mousepr==0) {
	             mousepr=1;
	             mousex=arg0.getX();
	             mousey=arg0.getY();
	          }
	       }
	       public void mouseReleased(TextMouseEvent arg0) {}
	    };
	    cn.getTextWindow().addTextMouseListener(tmlis);
	  
	    klis=new KeyListener() {
	       public void keyTyped(KeyEvent e) {}
	       public void keyPressed(KeyEvent e) {
	          if(keypr==0) {
	             keypr=1;
	             rkey=e.getKeyCode();
	          }
	       }
	       public void keyReleased(KeyEvent e) {}
	    };
	    cn.getTextWindow().addKeyListener(klis);
	    // ----------------------------------------------------
	    
	    int px=1,py=1;
	    cn.getTextWindow().output(px,py,'X'); 
	    reader(table); ////////////////////////////////////////////////////
	    ////// ilk 25 sayının yerleştirilmesi
	    for (int i = 0; i < 25; i++) {
			numberplacer(rndnumbergenarator(table), table);
		}
	  
	    
	    
	    ////-------------------------------------------
	    /// İLK 10 input ve yerleştirilmesi aşşığıda
	    Queue input = new Queue(1000);
	    for (int i = 0; i < 10; i++) {
			input.enqueue(rndnumbergenarator(table));			
		}
	    for (int i = 0; i < 10; i++) {
	    	int a=(Integer)input.peek();			
            char numberss=(char) (48+a);		
	    	input.enqueue(input.dequeue());
	    	//ilk 10 input
		}
	    
	    ////
	    int score = 0;
	    //
	    int timecounter = 0;
	    Number playernum = new Number(5,px,py);
	    int gamestopper = 0;
	    boolean gamestoped =false;
	    
	    while(gameover==false) {	    		    	
	    	table[py][px] = 'X'; 
	    	//System.out.println(System.currentTimeMillis());
	    	//System.out.println(a);
	    	// burada bagi yazd�r�yorum
	    	boolean numyendi = false;
	    	boolean numyendi2 = false;	
	    	long a = System.currentTimeMillis() % 10000;	    	  	
	    	int time = 0;	    		
	    	cn.getTextWindow().setCursorPosition(62, 21);
	    	cn.getTextWindow().output("Time: " +(String.valueOf(timecounter/10)));
	    	timecounter++;
	    	
	         
	    	
	    	if ((a <200 && a > 0) || (a <5200 && a > 5000)) {	    		
	    		numberplacer((int)input.dequeue(),table);
	    		input.enqueue(rndnumbergenarator(table));
	    		rednumbers(py,px);
	    		//----------------------------------Checks to be made if the red number reaches our number------------------------------
	    		if(py>0&&px>0&&px<54 && py<22 && ((table[py+1][px] == '7' && playernum.getNumber() < 7)||(table[py+1][px] == '8' && playernum.getNumber() < 8)||(table[py+1][px] == '9' && playernum.getNumber() < 9)||
	    				(table[py-1][px] == '7' && playernum.getNumber() < 7)||(table[py-1][px] == '8' && playernum.getNumber() < 8)||(table[py-1][px] == '9' && playernum.getNumber() < 9)||
	    				(table[py][px+1] == '7' && playernum.getNumber() < 7)||(table[py][px+1] == '8' && playernum.getNumber() < 8)||(table[py][px+1] == '9' && playernum.getNumber() < 9)||
	    				(table[py][px-1] == '7' && playernum.getNumber() < 7)||(table[py][px-1] == '8' && playernum.getNumber() < 8)||(table[py][px-1] == '9' && playernum.getNumber() < 9))) {
	    			if(playernum.getNumber() < 7) {
	    				cn.getTextWindow().setCursorPosition(22, 23);
		        		cn.getTextWindow().output("-GAME OVER-", game);
	    				gameover=true; 
	    			}
	    		}
	    	
	    		for (int i = 0; i < 10; i++) {
			    	int b=(Integer)input.peek();			
		            char numberss=(char) (48+b);
					cn.getTextWindow().output(60 +i, 3,numberss);
			    	input.enqueue(input.dequeue());			    		
			    	//for first 10 input		    				    	
				}
	
	    	}
	    	 if(playernum.getNumber() == 11) {
	    		  gamestopper++;
	       		  cn.getTextWindow().setCursorPosition(56, 6);
	       		  cn.getTextWindow().output("You are wait "+ (int) (20 - gamestopper)/5 + " seconds", game);
       		  if (gamestopper >= 20) {
       			  playernum.setNumber(2);
       			  gamestoped = false;
       			  gamestopper = 0;
       			  cn.getTextWindow().setCursorPosition(56, 6);
         		  cn.getWriter().print("                                        ");      			  
       		  }     		 	        		  
       	  }
	    		    
	       if(keypr==1 && px <= 55 && py <= 23) {
	    	  cn.getTextWindow().output(px,py,' ');// if keyboard button pressed	
	    	  if(rkey == KeyEvent.VK_Q) {
	    		  if (!bag2.isEmpty()) {
				  bag1.push(bag2.pop());
	    		  numyendi2 = true;	    		  
				}
	    		  cn.getTextWindow().output(px,py,(char)(playernum.getNumber() + 48),att4);
	    		 		  
	    	  }
	    	  if(rkey == KeyEvent.VK_W) {
	    		  if (!bag1.isEmpty()) {		    		  
		    		  bag2.push(bag1.pop());
		    		  numyendi2 = true;	 				
				}
	    		  cn.getTextWindow().output(px,py,(char)(playernum.getNumber() + 48),att4);
   		 
	    	  }
	          if(rkey==KeyEvent.VK_LEFT && table [py][px-1] !='#' && gamestoped == false) {	        	
	        	  if (!Character.isWhitespace(table[py][px-1])) {
	        		  if(bag1.isFull() == true){
	        			  bag1.pop();
	        		  }
	        		  bag1.push(table[py][px-1]);
	        		  
	        		  numyendi = true;
	        	  }	        	  
	        	  px--;
	        	  table[py][px+1] = ' ';
	        	  
	          }
	          
	          if(rkey==KeyEvent.VK_RIGHT && table [py][px+1] !='#' && gamestoped == false) {
	        	 
	        	  if (table[py][px+1] != ' ') {
	        		  if(bag1.isFull() == true){
	        			  bag1.pop();
	        		  }
	        		  bag1.push(table[py][px+1]);	        		  
	        		  numyendi = true;
	        	  }
	        	  
	        	  px++;
	        	  table[py][px-1] = ' ';
	          }
	          if(rkey==KeyEvent.VK_UP &&  table[py-1][px] !='#' && gamestoped == false) {	        	  
	        	  if (table[py-1][px] != ' ') {
	        		  if(bag1.isFull() == true){
	        			  bag1.pop();
	        		  }
	        		  bag1.push(table[py-1][px]);	        		  
	        		  numyendi = true;
	        	  }
	        	  
	        	  py--;
	        	  table[py+1][px] = ' ';
	        	
	          }
	          if(rkey==KeyEvent.VK_DOWN && table[py+1][px] !='#'&& gamestoped == false) {	        	  
	        	  if (table[py+1][px] != ' ') {
	        		  if(bag1.isFull() == true){
	        			  bag1.pop();
	        		  }
	        		  bag1.push(table[py+1][px]);	        		  
	        		  numyendi = true;
	        	  }
	        	  
	        	  py++;
	        	  table[py-1][px] = ' ';
	        	
	          }
	          ///////
	          playernum.setX(px);
	          playernum.setY(py);	          
	          ////////////////////////////////////////////////// In the below we are deleting eaten numbers from numberlist.	          	       
	          if (numyendi == true || numyendi2==true ) {	        	  
	        	  for (int i = 0; i < numbercounter; i++) {
					if(numbers[i].getX() ==px && numbers[i].getY() == py) {
						numbers[i].setDeleted(true);
					}
				}	        	  
	           //////////////////////////////////////////////////////////////////////////////////////////  
	        	  if(!bag1.isEmpty() && ((char)bag1.peek() - 48 )>(Integer) playernum.getNumber()) {
	        		  cn.getTextWindow().setCursorPosition(22, 23);
	        		  cn.getTextWindow().output("-GAME OVER-", game);
		       		  gameover=true;
	        	  }
	        	  if (numyendi==true) {
	        		 playernum.setNumber(playernum.getNumber() + 1);
					
				}
	           /////////////////////////////////////////////////// In here we are detecting if player exceeded 9.
	        	  if(playernum.getNumber() == 10) {
	        		  gamestoped = true;
	        		  playernum.setNumber(11);
	        	  }	        	  
	           /////////////////////////////////////////////////////////////////
	        	  for (int i = 0; i < numbercounter; i++) {
					if(numbers[i].getX() == px && numbers[i].getY()== py) {
						numbers[i].setDeleted(true); 
					}
				}
	        	  
	      	  ////// We are printing bags here.
	        	  int size1 = bag1.size();
	        	  int size2 = bag2.size();
	        	  for (int i = 0; i < 8; i++) {
	        		  cn.getTextWindow().output(62, 15 - i , ' ');
	        		  cn.getTextWindow().output(68, 15 - i, ' ');
				}		          	        	  
	        	  ///////////////////////////	        	
	        	 
        		  for (int i = 0; i < 8; i++) {
        			if(!bag1.isEmpty()) {							       								
					bag1temp.push(bag1.pop());
					cn.getTextWindow().output(62, 6 + i+ (10- size1), (char)bag1temp.peek());
					
        			}									
					if(!bag2.isEmpty()) {						
						bag2temp.push(bag2.pop());
						cn.getTextWindow().output(68, 6 + i + (10-size2)  , (char)bag2temp.peek());
					}
        		  }
        		
        		  ////////////////////////	    	  
	          
	          for (int i = 0; i < 8; i++) { // score calculations.
				if(!bag1temp.isEmpty() && !bag2temp.isEmpty()) {
					if(bag1temp.peek() == bag2temp.peek()) {
						
						if (gamestoped==true) {
							 playernum.setNumber(1);
			       			  gamestoped = false;
			       			  gamestopper = 0;
			       			  cn.getTextWindow().setCursorPosition(56, 6);
			         		  cn.getWriter().print("                                        ");     
			         		 cn.getTextWindow().output(px,py,'2',att4); 
							
						}

						if((char)bag1temp.peek() == 49 || (char)bag1temp.peek() == 50 || (char)bag1temp.peek() == 51) {
							score += ((char)bag1temp.peek() -48) * 1;												
						}
						if((char)bag1temp.peek() == 52 || (char)bag1temp.peek() == 53 || (char)bag1temp.peek() == 54) {
							score += ((char)bag1temp.peek() - 48) * 5;
						}
						if((char)bag1temp.peek() == 55 || (char)bag1temp.peek() == 56 || (char)bag1temp.peek() == 57) {
							score += ((char)bag1temp.peek()- 48) * 25;					
						}
						cn.getTextWindow().setCursorPosition(62, 15 - i);
						cn.getTextWindow().output((char)bag1temp.peek(), chosed);
						cn.getTextWindow().setCursorPosition(68, 15 - i);
						cn.getTextWindow().output((char)bag1temp.peek(), chosed);	
						bag1temp.pop();
						bag2temp.pop();
						 playernum.setNumber(playernum.getNumber() + 1);
				}					
				}
				if(!bag1temp.isEmpty()) 
					bag1.push(bag1temp.pop());
				if(!bag2temp.isEmpty())
					bag2.push(bag2temp.pop());
			}
	         
	          cn.getTextWindow().setCursorPosition(62, 20);
	          cn.getTextWindow().output("Score:" +(String.valueOf(score)));
	    
	          }
	          char rckey=(char)rkey;
	          //        left          right          up            down
	          if(rckey=='%' || rckey=='\'' || rckey=='&' || rckey=='(')
	        	  if(playernum.getNumber() >=10)
	        		  cn.getTextWindow().output(px,py,(char)(49),att4); // VK kullanmadan test teknigi
	        	  else	        	  
	        		  cn.getTextWindow().output(px,py,(char)(playernum.getNumber() + 48),att4); // VK kullanmadan test teknigi
	          //else 
	        	  //cn.getTextWindow().output(rckey);
	       
	          keypr=0;    // last action
	          
	       }	       
	          if(timecounter % 5 ==0) {
		    		randommoving(table);
		    		
		    	}
	       Thread.sleep(200);
	       timecounter++;
	    }
	 }

	
	public static void reader(char[][] chararray) throws FileNotFoundException {
		String [] strarray=new String[23];
		int forarray=0;
		 try {
		      File myObj = new File("maze.txt");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        strarray[forarray]=data;
		        forarray++;
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		 for (int i = 0; i < 23; i++) {
			 for (int j = 0; j < 55; j++) {

				 table[i][j]=strarray[i].charAt(j); //  maze
				
			}
		 }


	      for (int i = 0; i < 23; i++) {  // Printing MAZE
						 for (int j = 0; j < 55; j++) 
						 {	  
							System.out.print(table[i][j]);
						 }
					System.out.println();
				
					}
	      cn.getTextWindow().setCursorPosition(62, 1);
	      cn.getTextWindow().output("Input",att3);
	      cn.getTextWindow().setCursorPosition(60, 2);
	      cn.getTextWindow().output("<<<<<<<<<<", chosed);
	      cn.getTextWindow().setCursorPosition(60, 4);
	      cn.getTextWindow().output("<<<<<<<<<<", chosed);
	      cn.getTextWindow().setCursorPosition(61, 7);
	      cn.getTextWindow().output("Backpacks",att4);
	      for (int i = 0; i <= 8; i++) {
	    	  cn.getTextWindow().setCursorPosition(60, 8+i);		    
		      if (i==8) { 
		    	  cn.getTextWindow().output("+---+ +---+");
			}
		      else
		    	  cn.getTextWindow().output("|   | |   |");
		}
	      cn.getTextWindow().setCursorPosition(60, 17);
	      cn.getTextWindow().output("Left  Right",att4);
	      cn.getTextWindow().setCursorPosition(61, 18);
	      cn.getTextWindow().output(" Q     W",att1);

 
	}
	public static int rndnumbergenarator(char[][] chararray) {
		int numbers = 0;
		Random rand=new Random();	  	    	  
	    		  int rndposib = rand.nextInt(20);
	    		  if (rndposib <15) {
	    			   numbers=rand.nextInt(3)+1;
	    		  }
	    		  if (15 <= rndposib&&	rndposib <19) {
	    			   numbers=rand.nextInt(3)+4;
	    		  }
	    		  if (rndposib == 19) {
	    			  numbers=rand.nextInt(3)+7;
	    		  }	    
	   		  
	   return numbers; 
	}  	  
	public static void numberplacer(int a, char[][] chararray ) {
		Random rand=new Random();
		int x;
		int y;
		while (true) {
			x=rand.nextInt(55);
			y=rand.nextInt(23);			
			if (chararray[y][x]!='#') {   		  
	    	char numberss=(char) (48+a);
  			  if (0<a && a<4) {
  				  cn.setTextAttributes(att1);    
  				  cn.getTextWindow().output(x, y,numberss , att1);
  				  table[y][x] = numberss;

				}
  			  if (3<a && a<7) {
  				  cn.setTextAttributes(att2);
  				  cn.getTextWindow().output(x, y, numberss, att2);
  				  table[y][x] = numberss;
				}
  			  if (6<a && a<10) {
  				  cn.setTextAttributes(att3);
  				  cn.getTextWindow().output(x, y, numberss, att3);
  				  table[y][x] = numberss;
				} 			   			 
				break;			
		}		
	}
		numbers[numbercounter] = new Number(a,x,y);
		numbercounter++;
	 }
	
    public static char[][] randommoving(char[][] table) {
    	Random rnd = new Random();
    	for (int i = 0; i < numbercounter; i++) {
			if((numbers[i].getNumber() == 4 ||numbers[i].getNumber() == 5 ||numbers[i].getNumber() == 6) && numbers[i].isDeleted() == false){
				if(numbers[i].getWay() == "unassigned") {
					int way = rnd.nextInt(4);
					if(way == 0) {
						numbers[i].setWay("left");
					}
					if(way == 1) {
						numbers[i].setWay("right");
					}
					if(way == 2) {
						numbers[i].setWay("up");
					}
					if(way == 3) {
						numbers[i].setWay("down");
					}
				}
				if(numbers[i].getWay() != "unassigned" ) {
					if(numbers[i].getWay() == "left"&& numbers[i].isDeleted() == false) {
						if(table[numbers[i].getY()][numbers[i].getX() - 1] != ' ') {
							numbers[i].setWay("unassigned");
						}
						else {					
							table[numbers[i].getY()][numbers[i].getX() - 1] = table[numbers[i].getY()][numbers[i].getX()];
							table[numbers[i].getY()][numbers[i].getX()] = ' ';							
							cn.getTextWindow().output(numbers[i].getX() - 1, numbers[i].getY(),(char)(numbers[i].getNumber()+48), att2);
							cn.getTextWindow().output(numbers[i].getX(), numbers[i].getY(),' ', att2);
							numbers[i].setX(numbers[i].getX()-1);
						}													
					}					
					if(numbers[i].getWay() == "right"&& numbers[i].isDeleted() == false) {
						if(table[numbers[i].getY()][numbers[i].getX() + 1] != ' ') {
							numbers[i].setWay("unassigned");
						}
						else {					
							table[numbers[i].getY()][numbers[i].getX() + 1] = table[numbers[i].getY()][numbers[i].getX()];
							table[numbers[i].getY()][numbers[i].getX()] = ' ';							
							cn.getTextWindow().output(numbers[i].getX() + 1, numbers[i].getY(),(char)(numbers[i].getNumber()+48), att2);
							cn.getTextWindow().output(numbers[i].getX(), numbers[i].getY(),' ', att2);
							numbers[i].setX(numbers[i].getX()+1);
						}													
					}
					if(numbers[i].getWay() == "up"&& numbers[i].isDeleted() == false) {
						if(table[numbers[i].getY()-1][numbers[i].getX()] != ' ') {
							numbers[i].setWay("unassigned");
						}
						else {							
							table[numbers[i].getY()-1][numbers[i].getX()] = table[numbers[i].getY()][numbers[i].getX()];
							table[numbers[i].getY()][numbers[i].getX()] = ' ';							
							cn.getTextWindow().output(numbers[i].getX(), numbers[i].getY()-1,(char)(numbers[i].getNumber()+48), att2);
							cn.getTextWindow().output(numbers[i].getX(), numbers[i].getY(),' ', att2);
							numbers[i].setY(numbers[i].getY() - 1);
						}													
					}
					if(numbers[i].getWay() == "down" && numbers[i].isDeleted() == false) {
						if(table[numbers[i].getY()+1][numbers[i].getX()] != ' ') {
							numbers[i].setWay("unassigned");
						}
						else {							
							table[numbers[i].getY()+1][numbers[i].getX()] = table[numbers[i].getY()][numbers[i].getX()];
							table[numbers[i].getY()][numbers[i].getX()] = ' ';							
							cn.getTextWindow().output(numbers[i].getX(), numbers[i].getY()+1,(char)(numbers[i].getNumber()+48), att2);
							cn.getTextWindow().output(numbers[i].getX(), numbers[i].getY(),' ', att2);
							numbers[i].setY(numbers[i].getY()+1);
						}													
					}
				}
			}
		}
    	
    	return table;
    }

    public static void rednumbers (int a,int b) {
    	
    	char[][] temptable = new char[23][55];
    	
    	for (int i = 0; i < 23; i++) {
			for (int j = 0; j < 55; j++) {
				temptable[i][j] = table[i][j];
				if(table[i][j] == ' ') {
					cn.getTextWindow().output(j, i, ' ');
				}
			}
		}

    	
    	int x=b;
    	int y=a;

    	for (int i = 0; i < numbercounter; i++) {
			if((numbers[i].getNumber() == 7 || numbers[i].getNumber() == 8 ||numbers[i].getNumber() == 9) && numbers[i].isDeleted() == false) {
				Stack xler = new Stack(100000);
		    	Stack yler = new Stack(100000);
				int locationx  = numbers[i].getX();
				int locationy = numbers[i].getY();
				Stack prexler = new Stack(100000);
				Stack preyler = new Stack(100000);
				Stack tempx=new Stack(10000);
				xler.push(locationx);
				yler.push(locationy);				
				int counter = 0;		
            	boolean yflag=true;
            	boolean xflag=true;

            	


				while(true) {
					counter++;
					boolean flag1=true;
                	boolean flag2=true;
                	boolean flag3=true;
                	boolean flag4=true;
               

                   	if (locationx==x) {
						yflag=false;	
						xflag=true;
					}
                	if (locationy==y) {
						xflag=false;
						yflag=true;
					}
                	if (locationy > y && xflag==true) {
                		yflag=false;
                		
                  	   	if ( locationy < 22 && temptable[locationy + 1][locationx]==' '||temptable[locationy + 1][locationx]=='X') 
                  	   		{
            				xler.push(locationx);
            				yler.push(locationy + 1);	
            				flag4=false;
            				}
        				if (locationx<=x) {

    						if (locationx > 0 &&temptable[locationy][locationx - 1] == ' '||temptable[locationy][locationx - 1] == 'X') {
        						xler.push(locationx - 1);
        						yler.push(locationy);
        						flag3=false;
        						}
    						if (locationx < 53 &&temptable[locationy][locationx + 1]==' ' ||temptable[locationy][locationx + 1]=='X') {
        						xler.push(locationx + 1);
        						yler.push(locationy);
        						flag1=false;
        						}
							
						}
        				if (locationx>x) {
	        				if (locationx < 53 &&temptable[locationy][locationx + 1]==' ' ||temptable[locationy][locationx + 1]=='X') {
	    						xler.push(locationx + 1);
	    						yler.push(locationy);
	    						flag1=false;
	    						}
        					if (locationx > 0 &&temptable[locationy][locationx - 1] == ' '||temptable[locationy][locationx - 1] == 'X') {
        						xler.push(locationx - 1);
        						yler.push(locationy);
        						flag3=false;
        						}

        				}

    					if (locationy > 0 &&temptable[locationy-1][locationx]==' ' ||temptable[locationy-1][locationx]=='X' ) {
    						xler.push(locationx);
    						yler.push(locationy - 1);
    						flag2=false;
    						}
    							
					}
                	if (locationy < y && xflag==true ) {     
                  		yflag=false;
        				if (locationy > 0 &&temptable[locationy-1][locationx]==' ' ||temptable[locationy-1][locationx]=='X' ) {
    						xler.push(locationx);
    						yler.push(locationy - 1);
    						flag2=false;

    					}
        				if (locationx<=x) {
        					if (locationx > 0 &&temptable[locationy][locationx - 1] == ' '||temptable[locationy][locationx - 1] == 'X') {
        						xler.push(locationx - 1);
        						yler.push(locationy);
        						flag3=false;
        						}
    						if (locationx < 53 &&temptable[locationy][locationx + 1]==' ' ||temptable[locationy][locationx + 1]=='X') {
        						xler.push(locationx + 1);
        						yler.push(locationy);
        						flag1=false;
        						}
    											
						}
        				if (locationx>x) {

	        				if (locationx < 53 &&temptable[locationy][locationx + 1]==' ' ||temptable[locationy][locationx + 1]=='X') {
	    						xler.push(locationx + 1);
	    						yler.push(locationy);
	    						flag1=false;
	    						}
        					if (locationx > 0 &&temptable[locationy][locationx - 1] == ' '||temptable[locationy][locationx - 1] == 'X') {
        						xler.push(locationx - 1);
        						yler.push(locationy);
        						flag3=false;
        						}
        				}
    	
                	   	if ( locationy < 22 && temptable[locationy + 1][locationx]==' '||temptable[locationy + 1][locationx]=='X') {
    						xler.push(locationx);
    						yler.push(locationy + 1);	
    						flag4=false;
    						}
			
					}


                	if (locationx < x && yflag==true) {
                		 xflag=false; 
             			if (locationx > 0 &&temptable[locationy][locationx - 1] == ' '||temptable[locationy][locationx - 1] == 'X') {
    						xler.push(locationx - 1);
    						yler.push(locationy);
    						flag3=false;						
    						}
                		if (locationy<=y) {

                			if (locationy > 0 &&temptable[locationy-1][locationx]==' ' ||temptable[locationy-1][locationx]=='X' ) {
        						xler.push(locationx);
        						yler.push(locationy - 1);
        						flag2=false;
        						}
                		 	if ( locationy < 22 && temptable[locationy + 1][locationx]==' '||temptable[locationy + 1][locationx]=='X') {
        						xler.push(locationx);
        						yler.push(locationy + 1);	
        						flag4=false;
        						}
          
						}
                		else {

                    	   	if ( locationy < 22 && temptable[locationy + 1][locationx]==' '||temptable[locationy + 1][locationx]=='X') {
        						xler.push(locationx);
        						yler.push(locationy + 1);	
        						flag4=false;
        						}
                  			if (locationy > 0 &&temptable[locationy-1][locationx]==' ' ||temptable[locationy-1][locationx]=='X' ) {
        						xler.push(locationx);
        						yler.push(locationy - 1);
        						flag2=false;
        						}
                		}

    					if (locationx < 53 &&temptable[locationy][locationx + 1]==' ' ||temptable[locationy][locationx + 1]=='X') {
    						xler.push(locationx + 1);
    						yler.push(locationy);
    						flag1=false;
    						}

					}
              	
                	if (locationx > x && yflag==true) {
                		xflag=false;
            			if (locationx < 53 &&temptable[locationy][locationx + 1]==' ' ||temptable[locationy][locationx + 1]=='X') {
    						xler.push(locationx + 1);
    						yler.push(locationy);
    						flag1=false;

    					}
                		if (locationy<=y) {

                			if (locationy > 0 &&temptable[locationy-1][locationx]==' ' ||temptable[locationy-1][locationx]=='X' ) {
        						xler.push(locationx);
        						yler.push(locationy - 1);
        						flag2=false;
        						}
                		 	if ( locationy < 22 && temptable[locationy + 1][locationx]==' '||temptable[locationy + 1][locationx]=='X') {
        						xler.push(locationx);
        						yler.push(locationy + 1);	
        						flag4=false;
        						}
          
						}
                		else {

                    	   	if ( locationy < 22 && temptable[locationy + 1][locationx]==' '||temptable[locationy + 1][locationx]=='X') {
        						xler.push(locationx);
        						yler.push(locationy + 1);	
        						flag4=false;
        						}
                  			if (locationy > 0 &&temptable[locationy-1][locationx]==' ' ||temptable[locationy-1][locationx]=='X' ) {
        						xler.push(locationx);
        						yler.push(locationy - 1);
        						flag2=false;
        						}
                		}
    											
    					if (locationx > 0 &&temptable[locationy][locationx - 1] == ' '||temptable[locationy][locationx - 1] == 'X') {
    						xler.push(locationx - 1);
    						yler.push(locationy);
    						flag3=false;
  							
    						}
						
					}
                	
                	temptable[locationy][locationx] = '3';
                	

				
					if(!xler.isEmpty() && (flag1!=true || flag2!=true ||flag3!=true ||flag4!=true)) {	
	                    prexler.push(locationx); 
	                    preyler.push(locationy); 
	                    locationx = (Integer) xler.pop(); 
	                    locationy = (Integer) yler.pop(); 
	                  
	                }
					else if (!prexler.isEmpty() && !xler.isEmpty()&& flag1==true && flag2==true && flag3==true && flag4==true)  {     
						 locationx =(int)prexler.peek(); 
	                     locationy = (int)preyler.peek();  
	                	 prexler.pop();
	                	 preyler.pop();
	                 	                	
	                } 
					

					if(temptable[locationy][locationx] == 'X') {						
					
						while(!prexler.isEmpty()) {
							if(prexler.size() == 2) {
								table[numbers[i].getY()][numbers[i].getX()] = ' ';
								cn.getTextWindow().output((Integer) numbers[i].getX(),(int)numbers[i].getY(),' ');
								numbers[i].setX((int)prexler.peek());
								numbers[i].setY((int)preyler.peek());								
								table[(int) preyler.pop()][(int)prexler.pop()] = (char) (numbers[i].getNumber()+48);																		
								cn.getTextWindow().output((Integer) numbers[i].getX(),numbers[i].getY(),(char) (numbers[i].getNumber()+48),att3);						
								break;
							}							
							cn.getTextWindow().output((Integer) prexler.pop(),(Integer) preyler.pop(),'.');							
						}
						break;
					}



				if (prexler.isEmpty()) {
					break;
				}
					
				}
				
			}
				
			}
			}
    	}