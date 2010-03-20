

public abstract class Command {
	
	private Listener listener = null;
	public void setListener( Listener l ) {
		if( listener != null ) this.listener = l;
	}
	
	public void signal( Result r ) {
		listener.signal(r);
	}
	
	public abstract Result execute();
	
	//public Object clone();
}
