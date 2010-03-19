
/**
 * ATMGUI, an application that models a standard ATM machine 
 * 
 * @author (Richard Locsin) 
 * @author (Sheena dela Cruz)
 * @version (Feb. 25, 2005)
 */
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class ATMGUI extends JFrame implements ActionListener, AtmInterface
{
    private JButton balance = new JButton("Get Balance");
    private JButton withdraw = new JButton("Withdraw");
    private JButton deposit = new JButton("Deposit");
    private JButton transfer = new JButton("Transfer");
    private JButton enter = new JButton("Enter");
    private JButton newTransaction = new JButton("New Transaction");
    
    private JTextArea area = new JTextArea();
    
    private JTextField field = new JTextField();
    
    private JLabel logo = new JLabel("");
    
    private int check, action, point;
    private String bankName, accountName, pin, destAccountName, destinationBank, tempName, tempPin;
    private double amount;
    
    private Color color1 = new Color(255, 247, 153);
    private Color color2 = new Color(246, 137, 55);
    private Color color3 = new Color(255, 198, 8);
    
    private String id = "";
    private BankDriver bankdriver = null;
    private JLabel loggedAs = new JLabel();
    
    /**
     * calls the init() method and frame-specific code
     */
    public ATMGUI(String id, BankDriver bank)
    {
        this.init();
        this.setSize(500,300); 
        this.setTitle("ATM GUI");
        this.id = id;
        this.bankdriver = bank;
        changeButtonState(false);
        this.addWindowListener(new WindowAdapter()
        {
             public void windowClosing( WindowEvent we )
             {
                 System.exit(0);
             }
        });
    }
        
    /**
     * The 'look and feel'of the GUI
     */
    public void init()
    {
        this.initializeComponents();
        this.addListeners();
    }
    
    /**
     * Assigns actions to components
     */
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == newTransaction)
        {
            this.refresh();
        }
        
        else if(e.getSource() == balance)
        {
            this.balance();
        }
        
        else if(e.getSource() == withdraw)
        {
            this.withdrawMoney();
        }
        
        else if(e.getSource() == deposit)
        {
            this.depositMoney();
        }
        
        else if(e.getSource() == transfer)
        {
            this.transferMoney();
        }
        
        else if(e.getSource() == enter)
        {
            this.enter();
        }
    }
    
    /**
     * New Transaction
     * Prompts for bank account name to be used in all transactions
     */    
    public void refresh() 
    {
        check = 1;
        area.setText("Welcome to Expressnet \n");
        area.append("Please enter the bank you would be transacting with..." + "\n");
        action = 1;         
        changeButtonState(false);
        loggedAs.setText("");
    }
    
    /**
     * Enable buttons when transaction is valid
     */
    public void changeButtonState(boolean x)
    {
        balance.setEnabled(x);
        withdraw.setEnabled(x);
        deposit.setEnabled(x);
        transfer.setEnabled(x);
    }        
    
    /**
     * Displays the balance left on the screen
     */
    public void balance()  
    {
    	Listener l = new Listener() {
    		public void signal( Result r ) {
    			if( !Double.isNaN(r.getD()) ) {
    				area.append("Balance: " + r.getD() + "\n");
    			}
    		}
    	} ;
    	
        bankdriver.getBalance(null,accountName, pin, l );
        
        /*double x = bankDriver.getBalance(bankName,accountName, pin );
        if (!Double.isNaN(x))
        {
            area.append("Your balance is: " + x + "\n");
        }*/
    }
    
    /**
     * Checks if accountName and pin are not null.
     * If valid, prompts for amount to be withdrawn
     * The rest of the withdraw method is located in the enter()
     */
    public void withdrawMoney()
    {
        if( (bankName != null) && (accountName != null) )
        {
            check = 2;
            area.append("Please enter amount to be withdrawn.... \n");         
        }
    }
    
    /**
     * Checks if accountName and pin are not null.
     * If valid, prompts for amount to be deposited
     * The rest of the deposit method is located in the enter()
     */
    public void depositMoney()
    {
        if( (bankName != null) && (accountName != null) )
        {
            check = 3;
            area.append("Please enter amount to be deposited.... \n");
        }
    }
    
    /**
     * Checks if accountName and pin are not null.
     * If valid, prompts for the destination account name
     * The rest of the transfer method is located in the enter()
     */
    public void transferMoney()
    {
        if( (bankName != null) && (accountName != null) )
        {
            check = 4;
            area.append("TRANSFER currently does not support multiple banks.\n");
            area.append("Please enter destination account name...\n");
            point = 1;
        }
    }
    
    /**
     * Contains the remaining parts of the refresh(), addAccount(), removeAccount(), 
     * depositMoney(), withdrawMoney() and transferMoney()
     */
    public void enter()
    {
    	//continuation of refresh()
        if(check == 1)
        {
        	//Gets supplied bank name and prompts for account name
            if(action == 1)
            {
                String x = field.getText();
                if (x.equalsIgnoreCase(bankdriver.getBankName()))
                {
                    bankName = x;
                    area.append("Transacting with " + bankName + "\n");
                    field.setText("");
                    area.append("Please enter account name..." + "\n");
                    action = 2;
                }
                else if (!x.equals(""))
                {
                    area.append("Multiple banks currently not supported\n");
                    field.setText("");
                }
                else
                {
                    area.append("Please enter a valid bank name\n");
                    field.setText("");
                }
            }
            
            //Gets supplied account name and prompts for pin
            else if(action == 2)
            {
                String x = field.getText();
                tempName = x;
                area.append("Transacting with " + tempName + "\n");
                field.setText("");
                area.append("Please enter your PIN... \n");
                action = 3;
            }
            
            //Gets supplied pin
            else if(action == 3)
            {
                String x = field.getText();
                tempPin = x;
                area.append("Your pin is " + tempPin + "\n");
                //supposed to check the bank & account...but multiple banks aren't supported yet
                if (bankdriver.checkAccount(bankName,tempName, tempPin))
                {
                    accountName = tempName;
                    pin = tempPin;
                    area.append("Welcome, " + accountName + "!\n");
                    loggedAs.setText("Current account: " + accountName + "     Bank: " + bankName + "     ");
                    changeButtonState(true);                    
                }
                else
                {
                    JOptionPane.showMessageDialog( this, "Account not found. Check the account name and pin and try again.", 
                                                    "Error!", JOptionPane.ERROR_MESSAGE );                    
                }
                field.setText("");
                check = 0;
                action = 0;
            }
            
        }
        
        //continuation of withdrawMoney()
        else if(check == 2)
        {
            try
            {
            	//checks if supplied amount is a double
                amount = Double.parseDouble((String)field.getText());
				//asks for confirmation
                int n = JOptionPane.showConfirmDialog(
                            this, "Are you sure you want to withdraw Php" + amount ,
                            "Confirm", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION)
                {
                	Listener l = new Listener() {
                		public void signal( Result r ) {
                			if (r.getB())
                            {
                                area.append("Withdrew Php " + amount + " from " + accountName + "\n");
                                check = 0;
                            }
                            else
                            {
                                area.append("Withdraw failed!\n");
                            }
                		}
                	};
                	withdraw( bankName, accountName, pin, amount, l);
                }
            }
         
            catch (Exception e)
            {
                JOptionPane.showMessageDialog( this, "Amount to be withdrawn should be numerical.", 
                                                    "Error!", JOptionPane.ERROR_MESSAGE );
            }   
            field.setText("");
        }
        
        //continuation of depositMoney()
        else if(check == 3)
        {
            try
            {
            	//checks if supplied amount is a double
                amount = Double.parseDouble((String)field.getText());
                //asks for confirmation
                int n = JOptionPane.showConfirmDialog(
                            this, "Are you sure you want to deposit Php" + amount,
                            "Confirm", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION)
                {
                	Listener l = new Listener() {
                		public void signal( Result r) {
                			if (r.getB())
                            {
                                area.append("Deposited Php " + amount + " to " + accountName + "\n");
                                check = 0;
                            }
                            else
                            {
                                area.append("Despoit failed!\n");
                            }	
                		}
                	};
                    deposit(bankName, accountName,amount, l );
                    
                }
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog( this, "Amount to be deposited should be numerical.", 
                                                    "Error!", JOptionPane.ERROR_MESSAGE );
                area.append("Deposit failed!\n");
            }
            field.setText("");
        }
        
        //Continuation of transferMoney()
        else if(check == 4)
        {
        	//Gets supplied destination account name and prompts for amount to be transferred
            if(point == 1)
            {
                String x = field.getText();
                destAccountName = x;
                area.append("Destination account name is " + destAccountName + "\n");
                area.append("Enter amount to be transfered " + "\n");
                point = 2;
            }
            
            //gets supplied amount
            else if(point == 2)
            {
                try
                {
                	//checks if amount is a double
                    amount = Double.parseDouble((String)field.getText());
                    //asks for confirmation
                    int n = JOptionPane.showConfirmDialog(
                            this, "Are you sure you want to transfer Php" + amount,
                            "Confirm", JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION)
                    {
                    	Listener l = new Listener() {
                    		public void signal(Result r) {
                    			if (r.getB())
                                {
                                	area.append("You transfered Php " + amount + "\n");
                                	check = 0;
                       			    point = 0;
                                }
                                else
                                {
                                    area.append("Transfer failed!\n");
                                }
                    		}
                    	};
                        transfer(bankName, accountName, 
                                                   pin, destAccountName, amount, l);
                        
                    }
                }
                catch (Exception e)
                {
                    JOptionPane.showMessageDialog( this, "Amount to be transferred" 
                                                   + "should be numerical.", 
                                                   "Error!", JOptionPane.ERROR_MESSAGE );
                    area.append("Transfer failed!\n");
                }
            }
            field.setText("");
        }
    }
    
    /**
     * Contains the components need for the GUI
     */
    public void initializeComponents()
    {
        Container c = this.getContentPane();
        c.setLayout(new BorderLayout(5,5));
        c.setBackground(color1);
        
        area.setEditable(false);
        area.setBackground(color1);
        newTransaction.setBackground(color3);
        balance.setBackground(color3);
        deposit.setBackground(color3);
        withdraw.setBackground(color3);
        transfer.setBackground(color3);
        enter.setBackground(color3);
        enter.setBorder(BorderFactory.createMatteBorder(2,2,2,2, color2));
        newTransaction.setBorder(BorderFactory.createMatteBorder(2,2,2,2, color2));
        balance.setBorder(BorderFactory.createMatteBorder(2,2,2,2, color2));
        deposit.setBorder(BorderFactory.createMatteBorder(2,2,2,2, color2));
        withdraw.setBorder(BorderFactory.createMatteBorder(2,2,2,2, color2));
        transfer.setBorder(BorderFactory.createMatteBorder(2,2,2,2, color2));
        JScrollPane scrollPane = new JScrollPane(area);
        
        JPanel buttons = new JPanel(new GridLayout(5,1,5,5));
        buttons.setBorder(new EmptyBorder(5,5,5,5));
        buttons.add(newTransaction);
        buttons.add(balance);
        buttons.add(deposit);
        buttons.add(withdraw);
        buttons.add(transfer);
        buttons.setBackground(color1);
        
        JPanel bottom = new JPanel(new BorderLayout(5,5));
        bottom.setBorder(new EmptyBorder(3,3,3,3));
        bottom.add(field);
        bottom.add("East", enter);
        bottom.setBackground(color1);
       
        JPanel side = new JPanel(new BorderLayout());
        side.add(scrollPane);
        side.add("South", bottom);
        side.setBackground(color1);
        
        JPanel title = new JPanel(new FlowLayout());
        title.add(logo);
        title.setBackground(color1);
        
        c.add("West", buttons);
        c.add("South", title);
        c.add(side);
    }
    
    /**
     * Contains listeners for the GUI
     */
    public void addListeners()
    {
        balance.addActionListener(this);
        withdraw.addActionListener(this);
        deposit.addActionListener(this);
        enter.addActionListener(this);
        newTransaction.addActionListener(this);
        transfer.addActionListener(this);
    }
    
    /**
     * checks if bankName is valid
     */  
    public boolean checkBank(String bankName)
    {
        if (!bankdriver.getBankName().equalsIgnoreCase(bankName))
        {
            area.append("MULTIPLE BANKS NOT CURRENTLY SUPPORTED\n");
            return false;            
        }
        return true;
    }
    
    /**
     * Gets ATM id
     */
    public String getATMID()
    {
        return id;
    }
    
    /**
	 * Retrieves balance of current account
	 * same question. difference with balance()?
	 */
    public double getBalance(String bankName,
                             String accountName, 
                             String pin )
    {
        if (checkBank(bankName))
        {
        	return 0.0;
            //return bankDriver.getBalance(bankName,accountName, pin);
        }
        else
        {
            return Double.NaN;
        }
    }

	/**
	 * Deposits a specific amount to the supplied accountName
	 */
    public void deposit(String bankName,
                           String accountName, 
                           double amount, Listener l )
    {
        if (checkBank(bankName))
        {
            bankdriver.deposit(bankName,accountName, amount, l);
        }
    }
    
    /**
     * Withdraws a specific amount to the supplied accountName
     */
    public void withdraw(String bankName,
                            String accountName, 
                            String pin, 
                            double amount, Listener l )
    {
        if (checkBank(bankName))
        {
        	bankdriver.withdraw(bankName,accountName, pin, amount, l);
        }

    }
    
    /**
	 * Transfers a specified amount to a supplied destAccountName given a 
	 * destinationBank
	 */
    public void transfer(String bankName,
                            String srcAccountName, 
                            String srcPin,
                            String destAccountName, 
                            double amount, Listener l )
    {
        if (checkBank(bankName))
        {
            bankdriver.transfer(bankName,srcAccountName, srcPin, destAccountName, amount, l);
        }
    }

	/**
	 * Transfers a specified amount to a supplied destAccountName
	 */
    public boolean transfer(String bankName,
                            String srcAccountName, 
                            String srcPin,
                            String destinationBank,
                            String destAccountName, 
                            double amount )
    {
        area.append("MULTIPLE BANKS NOT CURRENTLY SUPPORTED\n");
        return false;
    }
}
