

public abstract class Command {
	
	private Listener listener = null;
	public void setListener( Listener l ) {
		this.listener = l;
	}
	
	public void signal( Result r ) {
		listener.signal(r);
	}
	
	public abstract Result execute();
	
	//public Object clone();
}
