import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BankDaoImpl implements BankDao {

	public void deleteBank(Bank b) {
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
	
	public void deleteBankAccount(BankAccount b) {
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
		Session session = null;
        Transaction tx = null;
        
        try 
        {
            session = SessionFactorySingleton.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
            Query q = session.createQuery("from Bank where name = ? ");
            q.setParameter(0, bankName);
            return (Bank)q.uniqueResult();
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
		return null;
	}

	@Override
	public Bank getBank(Long id) {
		Session session = null;
        Transaction tx = null;
        
        try 
        {
            session = SessionFactorySingleton.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
            Bank b = (Bank) session.get(Bank.class, id);
            return b;
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
		return null;
	}
	
	@Override
	public BankAccount getBankAccount(String bankName, String accountName) {
		Session session = null;
        Transaction tx = null;
        
        try 
        {
            session = SessionFactorySingleton.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
            Query q = session.createQuery("select balance from Bank b left join b.bal as balance " +
            								"where b.name= ? and balance.accountName= ?");
            		//"from Bank where name = ? ");
            q.setParameter(0, bankName);
            q.setParameter(1, accountName);
            return (BankAccount)q.uniqueResult();
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
		return null;
	}

	
	@Override
	public void saveBank(Bank b) {
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
	
	public void saveBankAccount(BankAccount ba) {
		Session session = null;
        Transaction tx = null;
        
        try 
        {
            session = SessionFactorySingleton.getSessionFactory().openSession();
            tx = session.beginTransaction();
          	session.saveOrUpdate(ba);
            
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
