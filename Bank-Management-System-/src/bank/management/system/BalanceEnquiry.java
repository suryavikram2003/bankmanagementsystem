package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class BalanceEnquiry extends JFrame implements ActionListener {

    String pin, accountNumber;
    JLabel label1, label2, accountLabel;
    JButton backButton;

    BalanceEnquiry(String pin, String accountNumber) {
        this.pin = pin;
        this.accountNumber = accountNumber;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource(""));
        Image i2 = i1.getImage().getScaledInstance(1550,830,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0,0,1550,830);
        add(l3);

        JLabel label1 = new JLabel("Your Current Balance is Rs ");
        label1.setForeground(Color.BLACK);
        label1.setFont(new Font("System", Font.BOLD, 16));
        label1.setBounds(430,180,700,35);
        l3.add(label1);
        
        accountLabel = new JLabel("Account Number: " + accountNumber);
        accountLabel.setForeground(Color.BLACK);
        accountLabel.setFont(new Font("System", Font.BOLD, 16));
        accountLabel.setBounds(430, 140, 400, 35);
        l3.add(accountLabel);

        label2 = new JLabel();
        label2.setForeground(Color.BLACK);
        label2.setFont(new Font("System", Font.BOLD, 16));
        label2.setBounds(430,220,400,35);
        l3.add(label2);

        backButton = new JButton("Back");
        backButton.setBounds(700,406,150,35);
        backButton.setBackground(new Color(65,125,128));
        backButton.setForeground(Color.BLACK);
        backButton.addActionListener(this);
        l3.add(backButton);

        int balance =0;
        try{
            Connn c = new Connn();
            ResultSet resultSet = c.statement.executeQuery("Select * from bank where pin = '"+pin+"'");
            while (resultSet.next()){
                if (resultSet.getString("type").equals("Deposit")){
                    balance += Integer.parseInt(resultSet.getString("amount"));
                }else {
                    balance -= Integer.parseInt(resultSet.getString("amount"));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        label2.setText(""+balance);

        setSize(1550, 830);
        setLocation(0, 0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
		new main_Class(pin,accountNumber);
    }

    public static void main(String[] args) {
    	String pin = "1198";
        String accountNumber = "149451";
        new BalanceEnquiry(pin, accountNumber);
  
    }
}
