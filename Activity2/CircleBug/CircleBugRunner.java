/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Cay Horstmann
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 */

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.grid.UnboundedGrid;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains box bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class CircleBugRunner
{
    public static void main(String[] args)
    {
        UnboundedGrid grid = new UnboundedGrid<Actor>();
        ActorWorld world2 = new ActorWorld(grid); 

        //ActorWorld world2 = new ActorWorld(new BoundedGrid<Actor>(10, 10));
        CircleBug alice2 = new CircleBug(6);
        alice2.setColor(Color.RED);
        CircleBug bob2 = new CircleBug(3);
        bob2.setColor(Color.BLUE);
        world2.add(new Location(6, 7), alice2);
        world2.add(new Location(4, 4), bob2);
        world2.show();
    }
}