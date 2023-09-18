package N410977008_HW3;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
public class ServerAppGui{
	private JFrame frame;
	private JTextField tfPort;
	JTextField tfMessage;
	JTextArea taBoard;
	ServerAppGui window;
	ServerSocket svs;
	
	int send_flag=1;
	int first_flag=1;
	
	public ServerAppGui() {};
	void setWindow(ServerAppGui window) {
		this.window = window;
		frame.setVisible(true);
	}
	void initialize() {
		frame = new JFrame();
		frame.setTitle("P2P聊天室 Server");
		frame.setBounds(100,100,500,400);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		JLabel lbPort = new JLabel("Server Port");
		lbPort.setBounds(22, 21, 90, 15);
		frame.getContentPane().add(lbPort);
		
		tfPort  = new JTextField();
		tfPort.setText("3000");
		tfPort.setBounds(110,18,105,20);
		tfPort.setColumns(10);
		frame.getContentPane().add(tfPort);
		
		JButton btnConnect = new JButton("開始聆聽");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Server(window).start();
				taBoard.append("開始聆聽於 : 3000\n");
			}
		});
		btnConnect.setBounds(369, 17, 87, 23);
		frame.getContentPane().add( btnConnect);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
