package ReviewExercise;

import java.awt.*; import java.awt.event.*; import javax.swing.*;

class AccountControlPanel extends JFrame{
	JButton button1;
	String string1;
	JLabel label1;
	
	public AccountControlPanel(){
		Container container = getContentPane();      // set the layout
		container.setLayout( new FlowLayout() );      // create a button and add to the content pane
		button1 = new JButton( "Invest" );
		container.add( button1 );
		button1.addActionListener( new AccountManager() );
		
		string1 = "Shiva Govindaraju Week 1 Exercise 2\n---Account Control Panel---";
		label1 = new JLabel("<html>" + string1.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
		container.add(label1);
		
		setSize(600,300);
		setVisible( true );
		}
	//}

	class AccountManager implements ActionListener{
		Account ac;
		public void actionPerformed( ActionEvent event ){
			//System.out.println("AccountManager:actionPerformed");
			string1 = "Shiva Govindaraju Week 1 Exercise 2\n---Account Control Panel---";
			string1 = string1 + "\n\nAccountManager:actionPerformed\n";
			label1.setText("<html>" + string1.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
			// Note an InvestmentAccount object is assigned to Account object
			ac = new InvestmentAccount();          // Now, the method invest() should work polymorphically
			// and call invest() in InvestmentAccount class.
			ac.invest(new MoneyMarketFunds());
		}
	}

	class Account  {
		public void invest (Funds f){
			//System.out.println("Account:invest");
			string1 = string1 + "\nAccount:invest\n";
			label1.setText("<html>" + string1.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
			f.buy();
			}
		}
	class InvestmentAccount extends Account {
		public void invest (MoneyMarketFunds mf){
			//System.out.println("InvestmentAccount:invest");
			string1 = string1 + "\nInvestmentAccount:invest\n";
			label1.setText("<html>" + string1.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
			mf.buy(5000);
			}
		}

	class Funds {
		public void buy(){
			//System.out.println("Funds::buy");
			string1 = string1 + "\nFunds::buy\n";
			label1.setText("<html>" + string1.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
			}
		}
	
	
	class MoneyMarketFunds extends Funds {
		double basicAmount = 1000.00;
		public void buy(){
			//System.out.println("MoneyMarketFunds::buy " +basicAmount);
			string1 = string1 + "\nMoneyMarketFunds::buy " + basicAmount + "\n";
			label1.setText("<html>" + string1.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
			}
		public void buy(double amount){
			//System.out.println("MoneyMarketFunds::buy " +amount);
			string1 = string1 + "\nMoneyMarketFunds::buy " + amount + "\n";
			label1.setText("<html>" + string1.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
			}
		}
}

public class Week1Exercise2 {
	public static void main(String[] args) {
		AccountControlPanel application = new AccountControlPanel();
		application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		}
	}

