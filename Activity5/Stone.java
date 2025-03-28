/**
 * Boulder replaces a stone when the coyote passes by it, and it explodes
 * after it reaches the end of its lifetime.
 * 
 * @author Aarav Prakash
 * @since  March 25, 2025
 */
 
package info.gridworld.actor;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
 
public class Boulder extends Actor
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
		{
			removeSelfFromGrid();
			Kaboom kaboom = new Kaboom();
			kaboom.putSelfInGrid(getGrid(), getLocation());
		}
	}
	
}
