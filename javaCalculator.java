
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class A410977008_HW1 extends JFrame {
	JPanel upPan = new JPanel();
	JPanel downPan = new JPanel();
	JTextField tfDisplay = new JTextField();
	JButton btnReset = new JButton("Reset");
//	JButton [] Nbtn =  new JButton[10];
//	for(int n=0;n<10;n++) {
//		 Nbtn[n]=new JButton("f") ;
//	}
//	Nbtn[0] = new JButton("first"); 
	JButton btn0 = new JButton("0");
	JButton btn1 = new JButton("1");
	JButton btn2 = new JButton("2");
	JButton btn3 = new JButton("3");
	JButton btn4 = new JButton("4");
	JButton btn5 = new JButton("5");
	JButton btn6 = new JButton("6");
	JButton btn7 = new JButton("7");
	JButton btn8 = new JButton("8");
	JButton btn9 = new JButton("9");
	JButton btnPlus = new JButton("+");
	JButton btnMinus = new JButton("-");
	JButton btnMultiply = new JButton("*");
	JButton btnDivide = new JButton("/");
	JButton btnPoint = new JButton(".");
	JButton btnEqual = new JButton("=");
	String num1 = "";
	String num2 = "";
	boolean isFirst = true;
	boolean pointAdded = false;
	int operation = -1;

	A410977008_HW1() {
		setLayout(new BorderLayout());
		upPan.setLayout(new GridLayout(2, 1));
		upPan.add(tfDisplay);
		upPan.add(btnReset);
		add(upPan, BorderLayout.NORTH);
		downPan.setLayout(new GridLayout(4, 4));
		downPan.add(btn1);
		downPan.add(btn2);
		downPan.add(btn3);
		downPan.add(btnPlus);

		downPan.add(btn4);
		downPan.add(btn5);
		downPan.add(btn6);
		downPan.add(btnMinus);

		downPan.add(btn7);
		downPan.add(btn8);
		downPan.add(btn9);
		downPan.add(btnMultiply);
		downPan.add(btn0);
		downPan.add(btnPoint);
		downPan.add(btnDivide);
		downPan.add(btnEqual);
		add(downPan, BorderLayout.CENTER);
		Lis lis = new Lis();
		btn1.addActionListener(lis);
		btn2.addActionListener(lis);
		btn3.addActionListener(lis);
		btn4.addActionListener(lis);
		btn5.addActionListener(lis);
		btn6.addActionListener(lis);
		btn7.addActionListener(lis);
		btn8.addActionListener(lis);
		btn9.addActionListener(lis);
		btn0.addActionListener(lis);
		btnPoint.addActionListener(lis);
		btnPlus.addActionListener(lis);
		btnMinus.addActionListener(lis);
		btnMultiply.addActionListener(lis);
		btnDivide.addActionListener(lis);
		btnEqual.addActionListener(lis);
		btnReset.addActionListener(lis);
	}

    //Inner class
	class Lis implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			JButton btn = (JButton)arg0.getSource();
			String name = btn.getName();
			String str="";
			if(name=="btn0") {
				str+="0";
			}
			else if(name=="btn1") {
				str+="1";
			}
			else if(name=="btn2") {
				str+="2";
			}
			else if(name=="btn3") {
				str+="3";
			}
			else if(name=="btn4") {
				str+="4";
			}
			else if(name=="btn5") {
				str+="5";
			}
			else if(name=="btn6") {
				str+="6";
			}
			else if(name=="btn7") {
				str+="7";
			}
			else if(name=="btn8") {
				str+="8";
			}
			else if(name=="btn9") {
				str+="9";
			}
			else if(name=="btnPlus") {
				
			}
			else if(name=="btnMinus") {
				
			}
			else if(name=="btnMultiply") {
				
			}
			else if(name=="btnDivide") {
				
			}
			else if(name=="btnPoint") {
				
			}
			else if(name=="btnEqual") {
				
			}
			tfDisplay.setText(str);
	   }
	}
	
	public static void main(String[] args){
		A410977008_HW1 calculator = new A410977008_HW1();
		calculator.setSize(350, 350);
		calculator.setTitle( "HW1");
		calculator.setVisible(true);
		calculator.setLocationRelativeTo(null);;
		calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
