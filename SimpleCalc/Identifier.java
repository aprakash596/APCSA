/**
 * This includes getter and setter methods for variables,
 * including their names and values.
 *
 * @author Aarav Prakash
 * @since March 3, 2025
 */

public class Identifier 
{
    private String name;
    private double value;
    
    public Identifier(String name, double value) 
    {
        this.name = name;
        this.value = value;
    }
    
    /** get the name of the variable */
    public String getName() 
    {
        return name;
    }
    
    /** gets the value assigned to the variable */
    public double getValue() 
    {
        return value;
    }
    
    /**
     * Sets the value of the variable
     * 
     * @param value the value that the variable should be set to
     */
    public void setValue(double value) 
    {
        this.value = value;
    }
}