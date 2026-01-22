package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

@SuppressWarnings("serial")
public abstract class Deposit extends JFrame implements ActionListener
{
	private JTextField senderField,receiverField,amountField;
	private JButton transferButtion;
	public Deposit()
	{
		setLayout(null);
        setSize(1550,1080);
        setLocation(0,0);
        setVisible(true);
	}
}
/*
public class Deposit extends JFrame implements ActionListener {
   String pin,accountNumber;
   TextField textField;

   JButton b1, b2;
private String accountnumber;
    Deposit(String accountNumber,String pin){
    	this.accountnumber = accountNumber;
        this.pin = pin;
        

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource(""));
        Image i2 = i1.getImage().getScaledInstance(1550,830,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0,0,1550,830);
        add(l3);
        JLabel label2 = new JLabel("ACCOUNT NUMBER");
        label2.setForeground(Color.BLACK);
        label2.setFont(new Font("System", Font.BOLD, 16));
        label2.setBounds(200, 190, 250, 35);
        l3.add(label2);

        JTextField accountTextField = new JTextField();
        accountTextField.setBackground(new Color(65, 125, 128));
        accountTextField.setForeground(Color.BLACK);
        accountTextField.setBounds(460, 190, 320, 25);
        accountTextField.setFont(new Font("Raleway", Font.BOLD, 22));
        l3.add(accountTextField);

        JLabel label1 = new JLabel("AMOUNT TO DEPOSIT");
        label1.setForeground(Color.BLACK);
        label1.setFont(new Font("System", Font.BOLD, 16));
        label1.setBounds(200,230,250,35);
        l3.add(label1);

        textField = new TextField();
        textField.setBackground(new Color(65,125,128));
        textField.setForeground(Color.BLACK);
        textField.setBounds(460,230,320,25);
        textField.setFont(new Font("Raleway", Font.BOLD,22));
        l3.add(textField);

        b1 = new JButton("DEPOSIT");
        b1.setBounds(700,362,150,35);
        b1.setBackground(new Color(65,125,128));
        b1.setForeground(Color.BLACK);
        b1.addActionListener(this);
        l3.add(b1);

        b2 = new JButton("BACK");
        b2.setBounds(700,406,150,35);
        b2.setBackground(new Color(65,125,128));
        b2.setForeground(Color.BLACK);
        b2.addActionListener(this);
        l3.add(b2);




        setLayout(null);
        setSize(1550,1080);
        setLocation(0,0);
        setVisible(true);

    }


    public void actionPerformed(ActionEvent e) {
        try {
        	
            String amount = textField.getText();
            String accountNumber = textField.getText();
            Date date = new Date();
            if (e.getSource()==b1){
                if (textField.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Please enter the Amount you want to Deposit");
                }else {
                    Connn c = new Connn();
                    c.statement.executeUpdate("insert into bank values('" + accountnumber + "','" + pin + "', '" + date + "','Deposit', '" + amount + "')");
                    JOptionPane.showMessageDialog(textField, "AccountNumber:"+accountnumber+"Rs. "+amount+" Deposited Successfully", accountNumber, getDefaultCloseOperation());
                    setVisible(false);
                    new main_Class(accountnumber,pin);
                }
            }else if (e.getSource()==b2){
                setVisible(false);
                new main_Class(accountnumber,pin);
            }
        }catch (Exception E){
            E.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Deposit("149451","");
    }
}
*/