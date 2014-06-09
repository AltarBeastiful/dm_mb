package simpleGridScenario;

public class OutOfBondsException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2731382167002030627L;
	
	public OutOfBondsException(String mess){
		super(mess);
	}
}