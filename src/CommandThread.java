import java.io.File;
import java.util.*;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CommandThread extends Thread {
	//private ArrayList<Command> commands;
	private Queue<Command> commands;
	private BankDao bankDao;
	private static CommandThread commmandThread= new CommandThread();
	private CommandThread() {
	}
	public static CommandThread getInstance() {
		return commmandThread;
	}
	public BankDao getDao() {
		return bankDao;
	}
	public void addCommand(Command c) {
		commands.add(c);
	}
	public void run() {
		commands=new LinkedList<Command>();
		Configuration configuration;
		configuration = new Configuration();
        configuration.configure(new File("hibernate.cfg.xml"));
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        SessionFactorySingleton.setSessionFactory(sessionFactory);
		bankDao=new BankDaoImpl();

		while (true) {
			while (!commands.isEmpty())
				commands.remove().execute();
			System.out.println("running");
			try {
				Thread.sleep(1000);
			} catch (Exception e) {}
		}
	}
	//pub
}
