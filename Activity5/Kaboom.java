/**
 * Kaboom is an explosion that has a lifetime. It is the thing that is left
 * when a boulder explodes.
 * 
 * @author Aarav Prakash
 * @since  March 25, 2025
 */
 
package info.gridworld.actor;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
 
public class Kaboom extends Actor
{
	private static final int THRESHOLD = 3;
	private int steps;
	
	public Kaboom()
	{
		setColor(null);
		steps = 0;
	}
	
	public void act()
	{
		if(steps < THRESHOLD)
			steps++;
		else
			removeSelfFromGrid();
	}
	
}
