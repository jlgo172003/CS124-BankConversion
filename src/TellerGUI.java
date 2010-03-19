
/**
 * TellerGUI , an application that models a standard teller workplace
 * 
 * @author (Richard Locsin) 
 * @author (Sheena dela Cruz)
 * @version (Feb. 25, 2005)
 */
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
public class TellerGUI extends JFrame implements ActionListener, TellerInterface
{
    private JButton createAccount = new JButton("Create Account");
    private JButton removeAccount = new JButton("Remove Account");
    private JButton getBalance = new JButton("Get Balance");
    private JButton withdraw = new JButton("Withdraw");
    private JButton deposit = new JButton("Deposit");
    private JButton transfer = new JButton("Transfer");
    private JButton enter = new JButton("Enter");
    private JButton newSession = new JButton("New Transaction");
      
    private JTextArea screen = new JTextArea();
    private JTextField input = new JTextField();
    private JLabel logo = new JLabel("");
    
    private double amount, balance;
    private String accountName, pin, destAccountName,tempName, tempPin;
    private int check, action, point, ref;
    
    private Color color1 = new Color(109, 207, 246);//bg
    private Color color2 = new Color(0, 174, 239);
    private Color color3 = new Color(255, 247, 153);
    private JLabel loggedAs = new JLabel();
    
    private String id = "";
    private BankDriver bankdriver = null;
    
    /**
     * calls the init() method and frame-specific code
     */
    public TellerGUI(String id, BankDriver bank)
    {
        this.init();
        this.setSize(500,300); 
        this.setTitle("Teller GUI");
        this.id = id;
        this.bankdriver = bank;
        this.changeButtonState(false);
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
        if(e.getSource() == newSession)                  //New Transaction
        {
            this.newSession();
        }
        
        else if(e.getSource() == createAccount)          //Create Account
        {
            this.addAccount();
        }
        
        else if(e.getSource() == removeAccount)          //Remove Account
        {
            this.removeAccount();
        }
        
        else if(e.getSource() == getBalance)             //Get Balance
        {
            this.balance();
        }
        
        else if(e.getSource() == withdraw)               //Withdraw
        {
            this.withdrawMoney();
        }
        
        else if(e.getSource() == deposit)                //Deposit
        {
            this.depositMoney();
        }
        
        else if(e.getSource() == transfer)               //Transfer
        {
            this.transferMoney();
        }
        
        else if(e.getSource() == enter)                 //Enter
        {
            this.enter();
        }
    }
    
    /**
     * New Transaction...
     * Prompts for bank account name to be used in all transactions
     */    
    public void newSession()
    {
        check = 1;
        screen.setText("Welcome to Expressnet \n");
        screen.append("Please enter bank account name" + "\n");
        action = 1;   
        changeButtonState(false);
        loggedAs.setText("");
    }
    
    /**
     * Enable buttons when transaction is valid
     */
    public void changeButtonState(boolean x)
    {
        getBalance.setEnabled(x);
        withdraw.setEnabled(x);
        deposit.setEnabled(x);
        transfer.setEnabled(x);
    }
    
    /**
     * Checks if accountName and pin are not null.
     * If valid, prompts for amount to be withdrawn
     * The rest of the withdraw method is located in the enter()
     */
    public void withdrawMoney()
    {
        if( (accountName != null) && (pin != null) )
        {
            check = 2;
            screen.append("Please enter amount to be withdrawn.... \n");      
        }
    }
    
    /**
     * Checks if accountName and pin are not null.
     * If valid, prompts for amount to be deposited
     * The rest of the deposit method is located in the enter()
     */
    public void depositMoney()
    {
        if( (accountName != null) && (pin != null) )
        {
            check = 3;
            screen.append("Please enter amount to be deposited.... \n");      
        }
    }
    
    /**
     * Checks if accountName and pin are not null.
     * If valid, prompts for the destination account name
     * The rest of the transfer method is located in the enter()
     */
    public void transferMoney()
    {
        if( (accountName != null) && (pin != null) )
        {
            check = 4;
            screen.append("TRANSFER currently does not support multiple banks.\n");
            screen.append("Please enter destination account name...\n");
            point = 1;
        }
    }
    
    /**
     * Prompts for account name to be removed 
     * The rest of the remove method is located in the enter()
     */
    public void removeAccount()
    {
        check = 5;
        screen.append("Enter account name to be removed...\n");
    }
	
	/**
	 * Prompts for a new account name
	 * The rest of the add method is located in the enter() 
	 */
    public void addAccount()
    {
        check = 6;
        screen.append("Enter desired account name...\n");
        ref = 1;
    }
    
    /**
     * Displays balance on screen
     */
    public void balance()
    {
        double x = bankdriver.getBalance(accountName, pin );
        if (!Double.isNaN(x))
        {
            screen.append("Balance: " + x + "\n");
        }
    }
    
     /**
     * Contains the remaining parts of the newSession(), addAccount(), removeAccount(), 
     * depositMoney(), withdrawMoney() and transferMoney()
     */
    public void enter()
    {
    	//continuation of newSession()
        if(check == 1)
        {
        	//Gets supplied account name and prompts for pin
            if(action == 1)
            {
                String x = input.getText();
                if (!x.equals(""))
                {
                    tempName = x;
                    screen.append("Account Name: " + x + "\n");
                    screen.append("Please enter PIN... \n");
                    action = 2;
                }
                input.setText("");
            }
            
            //Gets supplied pin and prompts 
            else if(action == 2)
            {
                tempPin = input.getText();
                screen.append("PIN: " + tempPin + "\n");
                if (bankdriver.checkAccount(tempName, tempPin))
                {
                    accountName = tempName;
                    pin = tempPin;
                    screen.append("Welcome, " + accountName + "!\n");
                    loggedAs.setText("Current account: " + accountName + "     ");
                    changeButtonState(true);
                }
                else
                {
                    JOptionPane.showMessageDialog( this, "Account not found. Check the "
                                                   + "account name and pin and try again.", 
                                                    "Error!", JOptionPane.ERROR_MESSAGE );                    
                }
                input.setText("");
                check = 0;
                action = 0;
            }
        }
        
        //continuation of withdrawMoney()
        else if(check == 2)
        {
            try
            {
            	//checks if amount is double
                amount = Double.parseDouble((String)input.getText());
                //asks for confirmation
                int n = JOptionPane.showConfirmDialog(
                            this, "Are you sure you want to withdraw Php" + amount + "?" ,
                            "Confirm", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION)
                {
                    boolean success = withdraw( accountName, pin, amount );
                    if (success)
                    {
                        screen.append("Withdrew Php " + amount + " from " + accountName + "\n");
                        check = 0;
                    }
                    else
                    {
                        screen.append("Withdraw failed!\n");
                    }
                }
            }
         
            catch (Exception e)
            {
                JOptionPane.showMessageDialog( this, "Amount to be withdrawn should be numerical.", 
                                                    "Error!", JOptionPane.ERROR_MESSAGE );
                screen.append("Withdraw failed!\n");
            }
            input.setText("");
        }
        
        //continuation of depositMoney()
        else if(check == 3)
        {
            try
            {
            	//checks if amount is a double
                amount = Double.parseDouble((String)input.getText());
                //asks for confirmation
                int n = JOptionPane.showConfirmDialog(
                            this, "Are you sure you want to deposit Php" + amount + "?",
                            "Confirm", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION)
                {
                    boolean success = deposit(accountName, amount );
                    if (success)
                    {
                        screen.append("Deposited Php " + amount + " to " + accountName + "\n");
                        check = 0;
                    }
                    else
                    {
                        screen.append("Despoit failed!\n");
                    }
                }
             }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog( this, "Amount to be deposited should be numerical.", 
                                                    "Error!", JOptionPane.ERROR_MESSAGE );
                screen.append("Despoit failed!\n");
            }
            input.setText("");
        }
        
        //Continuation of transferMoney()
        else if(check == 4)
        {
        	//Gets supplied destination account name and prompts for amount to be transferred
            if(point == 1)
            {
                String x = input.getText();
                destAccountName = x;
                screen.append("Destination account name:" + destAccountName + "\n");
                screen.append("Enter amount to be transfered " + "\n");
                point = 2;
                input.setText("");
            }
            
            //Gets supplied amount 
            else if(point == 2)
            {
                try
                {
                	//checks if amount is a double
                    amount = Double.parseDouble((String)input.getText());
                    //asks for confirmation
                    int n = JOptionPane.showConfirmDialog(
                            this, "Are you sure you want to transfer Php" + amount + "?",
                            "Confirm", JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION)
                    {
                        boolean success = transfer(accountName, pin, destAccountName, amount );
                        if (success)
                        {
                            screen.append("Transfered Php " + amount + " to " + destAccountName + "\n");
                            check = 0;
                			point = 0;
                        }
                        else
                        {
                            screen.append("Transfer failed!\n");
                        }
                    }
                }
         
                catch (Exception e)
                {
                    JOptionPane.showMessageDialog( this, "Amount to be transferred" +
                                                   " should be numerical.", 
                                                   "Error!", JOptionPane.ERROR_MESSAGE );
                    screen.append("Transfer failed!\n");
                }
                input.setText("");
            }

        }
        
        //Continuation of removeAccount()
        else if(check == 5)
        {
            String x = input.getText();
            if (!x.equals(""))
            {
            	//asks for confirmation
                int n = JOptionPane.showConfirmDialog(
                    this, "Are you sure you want to remove " + x +"?",
                    "Confirm", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION)
                {
                    boolean success = removeBankAccount(x);
                    if (success)
                    {
                        screen.append(x + " was successfuly removed!.\n");
                        if (x.equals(accountName))
                        {
                            loggedAs.setText("");
                            changeButtonState(false);
                        }
                        check = 0;
                    }
                    else
                    {
                        screen.append(x + " was not removed!\n");
                    }
                }
            }
            input.setText("");
        }
        
        //Continuation of addAccount()
        else if(check == 6)
        {
        	//Gets supplied account name and prompts for pin
            if(ref == 1)
            {   
                tempName = input.getText();
                screen.append("Account Name: " + tempName + "\n");
                input.setText("");
                screen.append("Please enter desired PIN...\n");
                ref = 2;
            }
            
            //Gets supplied pin and prompts for balance
            else if(ref == 2)
            {
                tempPin = input.getText();
                screen.append("PIN: " + tempPin +"\n");
                input.setText("");
                screen.append("Please enter initial balance...\n");
                ref = 3;
            }
            
            //gets supplied balance
            else if(ref == 3)
            {
                try
                {
                	//checks if balance is a double
                    balance = Double.parseDouble((String)input.getText());
                    screen.append("Balance: " + balance + "\n");
                    boolean success = bankdriver.createBankAccount(tempName, balance, tempPin);
                    if (success)
                    {
                        accountName = tempName;
                        pin = tempPin;
                        screen.append("Account successfully created!\n");
                        changeButtonState(true);
                        //displays current account in
                        loggedAs.setText("Current account: " + accountName + "     ");
                        ref = 0;
                	    check = 0;
                    }
                    else
                    {
                        screen.append("An account with the same name (" 
                                      + tempName + ") already exists!\n");
                    }
                }
         
                catch (Exception e)
                {
                	e.printStackTrace();
                    JOptionPane.showMessageDialog( this, "Balance should be numerical.", 
                                                    "Error!", JOptionPane.ERROR_MESSAGE );
                }
                input.setText("");                
            }
        }
    }
    
    /**
     * Contains the components need for the GUI
     */
    public void initializeComponents()
    {
        Container c = this.getContentPane();
        c.setLayout(new BorderLayout(2,2));
        c.setBackground(color1);
        
        JScrollPane scrollPane = new JScrollPane(screen);
        screen.setEditable(false);
        newSession.setBackground(color2);
        getBalance.setBackground(color2);
        deposit.setBackground(color2);
        withdraw.setBackground(color2);
        transfer.setBackground(color2);
        enter.setBackground(color2);
        createAccount.setBackground(color2);
        removeAccount.setBackground(color2);
        screen.setBackground(color1);
        newSession.setBorder(BorderFactory.createMatteBorder(2,2,2,2, color3));
        getBalance.setBorder(BorderFactory.createMatteBorder(2,2,2,2, color3));
        deposit.setBorder(BorderFactory.createMatteBorder(2,2,2,2, color3));
        withdraw.setBorder(BorderFactory.createMatteBorder(2,2,2,2, color3));
        transfer.setBorder(BorderFactory.createMatteBorder(2,2,2,2, color3));
        enter.setBorder(BorderFactory.createMatteBorder(2,2,2,2, color3));
        createAccount.setBorder(BorderFactory.createMatteBorder(2,2,2,2, color3));
        removeAccount.setBorder(BorderFactory.createMatteBorder(2,2,2,2, color3));
        
        JPanel button = new JPanel(new GridLayout(5,1,0,5));
        button.setBorder(new EmptyBorder(3,3,3,3));
        button.setBackground(color1);
        button.add(newSession);
        button.add(getBalance);
        button.add(deposit);
        button.add(withdraw);
        button.add(transfer);
        
        JPanel blah = new JPanel(new FlowLayout());
        blah.setBackground(color1);
        blah.add(loggedAs);
        blah.add(createAccount);
        blah.add(removeAccount);
        
        JPanel title = new JPanel(new BorderLayout());
        title.setBackground(color1);
        title.add(logo);
        title.add("East", blah);        
        
        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setBackground(color1);
        bottom.add(input);
        bottom.add("East", enter);
                
        JPanel side = new JPanel(new BorderLayout());
        side.setBackground(color1);
        side.add(scrollPane);
        side.add("South", bottom);
        
        c.add(side);
        c.add("West", button);
        c.add("South", title);
        
    }
    
    /**
     * Contains listeners for the GUI
     */
    public void addListeners()
    {
        enter.addActionListener(this);
        withdraw.addActionListener(this);
        deposit.addActionListener(this);
        createAccount.addActionListener(this);
        removeAccount.addActionListener(this);
        getBalance.addActionListener(this);
        transfer.addActionListener(this);
        newSession.addActionListener(this);
    }
    
    /**
     * Gets teller id
     */
    public String getTellerId()
    {
        return id;
    }
    
    /**
     * Creates a new bank account 
     */
    public boolean createBankAccount(String name, 
                                     double balance, 
                                     String pin)
    {
        return bankdriver.createBankAccount(name, balance, pin);
    }
	
	/**
	 * removes an existing bank account
	 */
    public boolean removeBankAccount(String name)
    {
        return bankdriver.removeBankAccount(name);

    }      
    
    /**
     * checks if bankName is valid
     */    
    public boolean checkBank(String bankName)
    {
        if (!bankdriver.getBankName().equalsIgnoreCase(bankName))
        {
            screen.append("MULTIPLE BANKS NOT CURRENTLY SUPPORTED\n");
            return false;            
        }
        return true;
        
    }
    
	/**
	 * Retrieves balance of current account
	 */
    public double getBalance(String accountName, 
                             String pin )
    {
        return bankdriver.getBalance(accountName, pin);
    }

	/**
	 * Deposits a specific amount to the supplied accountName
	 */
    public boolean deposit(String accountName, 
                           double amount )
    {
        return bankdriver.deposit(accountName, amount);
    }
    
    /**
     * Withdraws a specific amount to the supplied accountName
     */
    public boolean withdraw(String accountName, 
                            String pin, 
                            double amount )
    {
        return bankdriver.withdraw(accountName, pin, amount);
    }
    
	/**
	 * Transfers a specified amount to a supplied destAccountName given a 
	 * destinationBank
	 */
    public boolean transfer(String srcAccountName, 
                            String srcPin,
                            String destinationBank,
                            String destAccountName, 
                            double amount )
    {
        screen.append("MULTIPLE BANKS NOT CURRENTLY SUPPORTED\n");
        return false;
    }
    
    /**
	 * Transfers a specified amount to a supplied destAccountName
	 */
    public boolean transfer(String srcAccountName, 
                            String srcPin, 
                            String destAccountName, 
                            double amount )
    {
        return bankdriver.transfer(srcAccountName, srcPin, destAccountName, amount);
    }
}
