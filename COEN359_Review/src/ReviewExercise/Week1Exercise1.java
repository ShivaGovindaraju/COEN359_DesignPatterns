package ReviewExercise;

import java.awt.*; import java.awt.event.*; import javax.swing.*;

/**class AccountControlPanel extends JFrame{
	JButton button1;
	public AccountControlPanel(){
		Container container = getContentPane();      // set the layout
		container.setLayout( new FlowLayout() );      // create a button and add to the content pane
		button1 = new JButton( "Invest" );
		container.add( button1 );
		button1.addActionListener( new AccountManager() );
		setSize(600,300);
		setVisible( true );
		}
	}

class AccountManager implements ActionListener{
	Account ac;
	public void actionPerformed( ActionEvent event ){
		System.out.println("AccountManager:actionPerformed");
		// Note an InvestmentAccount object is assigned to Account object
		ac = new InvestmentAccount();          // Now, the method invest() should work polymorphically
		// and call invest() in InvestmentAccount class.
		// Does the output show the result you expected?
		// Based on your understanding of inheritance and
		// polymorphism in Java, explain the reasons for the output.
		ac.invest(new MoneyMarketFunds());
	}
}

class Account  {
	public void invest (Funds f){
		System.out.println("Account:invest");
		f.buy();
		}
	}
class InvestmentAccount extends Account {
	public void invest (MoneyMarketFunds mf){
		System.out.println("InvestmentAccount:invest");
		mf.buy(5000);
		}
	}
class Funds {
	public void buy(){
		System.out.println("Funds::buy");
		}
	}

class MoneyMarketFunds extends Funds {
	double basicAmount = 1000.00;
	public void buy(){
		System.out.println("MoneyMarketFunds::buy " +basicAmount);
		}
	public void buy(double amount){
		System.out.println("MoneyMarketFunds::buy " +amount);
		}
	}
public class Week1Exercise1 {
	public static void main(String[] args) {
		AccountControlPanel application = new AccountControlPanel();
		application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		}
	}
**/
public class Week1Exercise1 {

	public Week1Exercise1() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
