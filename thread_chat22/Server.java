package N410977008_HW3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
	ServerAppGui gui;
	
	Server(ServerAppGui gui) {
		super();
		this.gui = gui;
	}
	
	public void run() {
		byte[] buf = new byte[1024];
		int n = 0;
		ServerSocket svs = null;
		Socket s = null;
		try {
			svs = new ServerSocket(3000);
			s = svs.accept();
			gui.taBoard.append("Clinet connected\n");
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		try {
			InputStream in = s.getInputStream();
			OutputStream out = s.getOutputStream();
			while (true) {
				synchronized (this.gui) {
					if (gui.send_flag == 1) {
						if(gui.first_flag!=1) {
							String message = gui.tfMessage.getText();
							gui.taBoard.append("Server: " + message + "\n");
							out.write(message.getBytes());
							gui.tfMessage.setText("");
						}
						n = in.read(buf);
						String returnedMessage = new String(buf, 0, n);
						gui.taBoard.append("Client: " + returnedMessage + "\n");
						gui.first_flag=0;
						gui.send_flag = 0;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			svs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}


