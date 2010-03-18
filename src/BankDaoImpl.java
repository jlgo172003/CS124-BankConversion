import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BankDaoImpl implements BankDao {

	public void delete(Bank b) {
		Session session = null;
        Transaction tx = null;
        
        try 
        {
            session = SessionFactorySingleton.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(b);
            tx.commit(); 
        }
        catch(HibernateException ex) 
        {
            ex.printStackTrace();
            tx.rollback();
        } 
        finally 
        {
            session.close();
        }
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bank> getAllBank() {
		Session session = null;
        
        try 
        {
            session = SessionFactorySingleton.getSessionFactory().openSession();
            Query q = session.createQuery("from Bank");
            List<Bank> results = (List<Bank>)q.list();
            return results;
        }
        finally 
        {
            session.close();
        }
	}

	@Override
	public Bank getBank(String bankName) {
		return null;
	}

	@Override
	public BankAccount getBankAccount(String bankName, String accountName) {
		return null;
	}

	@Override
	public void save(Bank b) {
		Session session = null;
        Transaction tx = null;
        
        try 
        {
            session = SessionFactorySingleton.getSessionFactory().openSession();
            tx = session.beginTransaction();
          	session.saveOrUpdate(b);
            
            tx.commit(); 
        }
        catch(HibernateException ex) 
        {
            ex.printStackTrace();
            tx.rollback();
        }
        finally 
        {
            session.close();
        }		
	}

}
