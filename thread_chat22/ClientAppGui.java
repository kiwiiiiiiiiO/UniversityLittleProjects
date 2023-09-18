package N410977008_HW3;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
public class ClientAppGui {
	private JFrame frame;
	private JTextField tfIP;
	private JTextField tfPort;
	JTextField tfMessage;
	JTextArea taBoard;
	ClientAppGui window;
	Socket s;
	
	
	int send_flag;
	
	public ClientAppGui() {};
	void setWindow(ClientAppGui window) {
		this.window = window;
		frame.setVisible(true);
	}
	void initialize() {
		frame = new JFrame();
		frame.setTitle("P2P聊天室 Client(First)");
		frame.setBounds(100,100,500,400);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lbIP = new JLabel("Server IP");
		lbIP.setBounds(10, 21, 65, 15);
		frame.getContentPane().add(lbIP);
		
		JLabel lbPort = new JLabel("Server Port");
		lbPort.setBounds(211, 21, 72, 15);
		frame.getContentPane().add(lbPort);
		
		tfIP = new JTextField();
		tfIP.setText("127.0.0.1");
		tfIP.setBounds(74,18,105,20);
		tfIP.setColumns(10);
		frame.getContentPane().add(tfIP);
		
		tfPort  = new JTextField();
		tfPort.setText("3000");
		tfPort.setBounds(282,18,51,21);
		tfPort.setColumns(10);
		frame.getContentPane().add(tfPort);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					s = new Socket(tfIP.getText(), Integer.parseInt(tfPort
							.getText()));
					taBoard.append("Connected\n");
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				new Client(window).start();
			}
		});
		btnConnect.setBounds(369, 17, 87, 23);
		frame.getContentPane().add( btnConnect);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                try {
					s.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.exit(0);
			}
		});
		btnClose.setBounds(369, 47, 87, 23);
		frame.getContentPane().add(btnClose);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				send_flag = 1;
			}
		});
		btnSend.setBounds(350, 342, 87, 21);
		frame.getContentPane().add(btnSend);
		
		taBoard = new JTextArea();
		taBoard.setBounds(22, 71, 446, 247);
		frame.getContentPane().add(taBoard);

		tfMessage = new JTextField();
		tfMessage.setBounds(22, 342, 320, 21);
		frame.getContentPane().add(tfMessage);
		tfMessage.setColumns(10);
	}
}
