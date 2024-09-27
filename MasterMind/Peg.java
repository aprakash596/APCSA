/**
 *	Defines the peg used in the MasterMind game.
 *	Used inside the PegArray class.
 *
 *	@author	Mr Greenstein
 */
public class Peg {
	private char letter;
	
	/* Default constructor, letter is X */
	public Peg() {
		letter = 'X';
	}
	
	/* Constructor in which you assign letter */
	public Peg(char l) {
		letter = l;
	}
	
	// Accessor and modifier methods
	public char getLetter() { return letter; }
	public void setLetter(char c) { letter = c; }
}