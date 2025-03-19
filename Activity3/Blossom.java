import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;

public class Blossom extends Flower
{
    private int lifetime;
    private static final double DARKENING_FACTOR = 0.05;

    public Blossom()
    {
        setColor(Color.GREEN);
        lifetime = 10;
    }

    public Blossom(int time)
    {
        setColor(Color.GREEN);
        lifetime = time;
    }

    public void act()
    {
        Color c = getColor();
        int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
        int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
        int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));

        setColor(new Color(red, green, blue));

        lifetime--;
        if(lifetime == 0)
            this.removeSelfFromGrid();
    }
}
