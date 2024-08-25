/**
 *	FirstAssignment.java
 *	Display a brief description of your summer vacation on the screen.
 *
 *	To compile Linux:	javac -cp .:mvAcm.jar FirstAssignment.java
 *	To execute Linux:	java -cp .:mvAcm.jar FirstAssignment
 *
 *	To compile MS Powershell:	javac -cp ".;mvAcm.jar" FirstAssignment.java
 *	To execute MS Powershell:	java -cp ".;mvAcm.jar" FirstAssignment
 *
 *	@author	Aarav Prakash
 *	@since	August 23, 2024
 */
import java.awt.Font;

import acm.program.GraphicsProgram;
import acm.graphics.GLabel;

public class FirstAssignment extends GraphicsProgram {
    
    public void run() {
    	//	The font to be used
    	Font f = new Font("Serif", Font.BOLD, 18);
    	
    	//	Line 1
    	GLabel s1 = new GLabel("What I did on my summer vacation ...", 10, 20);
    	s1.setFont(f);
    	add(s1);
    	//	Line 2 
    	GLabel s2 = new GLabel("At the beginning of the summer, I went " + 
		"to my friend's house. I stayed there for a couple of", 10, 40);
    	s2.setFont(f);
    	add(s2);
    	//	Line 3
    	GLabel s3 = new GLabel("days, in which we played lots of videogames and " + 
    	"watched some movies. Soon after that was my ", 10, 60);
    	s3.setFont(f);
    	add(s3);
    	//	Line 4
    	GLabel s4 = new GLabel("birthday, my brother and I only were able to pick" + 
    	" up my friends by 9 PM. Since it was so late,", 10, 80);
    	s4.setFont(f);
    	add(s4);
    	//	Line 5
    	GLabel s5 = new GLabel("we only had time for dinner. After we " +
    	"got back home, we stayed up til 3 AM playing cards.", 10, 100);
    	s5.setFont(f);
    	add(s5);
    	//	Line 6
    	GLabel s6 = new GLabel("In the morning, we ate breakfast and played some" +
    	" videogames before everyone went back to", 10, 120);
		s6.setFont(f);
		add(s6);
		//	Line 7
		GLabel s7 = new GLabel("their houses. Soon after that, I started a" +
		" summer program. In this program, I had to create", 10, 140);
		s7.setFont(f);
		add(s7);
		//	Line 8
		GLabel s8 = new GLabel("a research paper. After a week, I worked with" + 
		" my mentor and decided to write about chatbots.", 10, 160);
		s8.setFont(f);
		add(s8);
		//	Line 9
		GLabel s9 = new GLabel("I then spent the next few weeks working on" + 
		" my paper. In these weeks, my brother had gone", 10, 180);
		s9.setFont(f);
		add(s9);
		//	Line 10
		GLabel s10 = new GLabel("back to his college to continue his research." +
		" In this time, another one of my friends moved", 10, 200);
		s10.setFont(f);
		add(s10);
		//	Line 11
		GLabel s11 = new GLabel("and I went to visit his new house. " + 
		"There, I attended a prayer ceremony to welcome his family", 10, 220);
		s11.setFont(f);
		add(s11);
		//	Line 12 
		GLabel s12 = new GLabel("to their new home. A few weeks later, I went back" +
		" for a sleepover, in which we made smores and", 10, 240);
		s12.setFont(f);
		add(s12);
		//	Line 13
		GLabel s13 = new GLabel("played some videogames. We ended up staying up" + 
		" till 5 AM, at which we saw the sun rise. After", 10, 260);
		s13.setFont(f);
		add(s13);
		//	Line 14 
		GLabel s14 = new GLabel("that, I joined a class on building basic AI " + 
		"models in python. Towards the end of the summer,", 10, 280);
		s14.setFont(f);
		add(s14);
		//	Line 15
		GLabel s15 = new GLabel("my friends and I drove 40 minutes into the " +
		"mountains at 1 AM. On the mountains, we were able", 10, 300);
		s15.setFont(f);
		add(s15);
		// Line 16
		GLabel s16 = new GLabel("to see the stars clearly. In fact, we even " +
		" could see the clouds from the side and the city under.", 10, 320);
		s16.setFont(f);
		add(s16);
    }
    
}
