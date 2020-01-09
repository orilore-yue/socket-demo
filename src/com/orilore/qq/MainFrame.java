package com.orilore.qq;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	JPanel contentPane;
    JTextArea txtRecord = new JTextArea();
    JTextField txtMsg = new JTextField();
    JButton btnSend = new JButton();
    final int server_port = 2021;
    final int client_port = 2022;
    
    public MainFrame() {
        try {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            jbInit();
            Thread thread = new Thread(
            	new Runnable() {
					public void run() {
						try {
							DatagramSocket server = new DatagramSocket(server_port);
	                        while(true){
	                            byte[] buf = new byte[1024];
	                            DatagramPacket dp = new DatagramPacket(buf,buf.length);
	                            server.receive(dp);
	                            String msg = new String(dp.getData());
	                            txtRecord.append("对方说:" + msg + "\n");
	                        }
	                    } catch (IOException ex) {
	                        ex.printStackTrace();
	                    }
					}
				}
            );
            thread.start();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);
        setSize(new Dimension(400, 318));
        setTitle("Frame Title");
        txtRecord.setBorder(BorderFactory.createLoweredBevelBorder());
        txtRecord.setBounds(new Rectangle(26, 17, 344, 202));
        txtMsg.setBounds(new Rectangle(26, 242, 277, 24));
        btnSend.setBounds(new Rectangle(316, 240, 61, 26));
        btnSend.setText("发送");
        btnSend.addActionListener(new MainFrame_btnSend_actionAdapter(this));
        contentPane.add(txtRecord);
        contentPane.add(txtMsg);
        contentPane.add(btnSend);
    }
    
    private DatagramSocket client;
    public void btnSend_actionPerformed(ActionEvent e) {
        try {
        	if(client==null) {
        		client = new DatagramSocket();
        	}
            byte[] buf = txtMsg.getText().getBytes();
            DatagramPacket dp = new DatagramPacket(buf,buf.length,InetAddress.getByName("127.0.0.1"),client_port);
            client.send(dp);
            txtRecord.append("\t\t\t\t 自己说:"+txtMsg.getText()+"\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}


class MainFrame_btnSend_actionAdapter implements ActionListener {
    private MainFrame adaptee;
    MainFrame_btnSend_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.btnSend_actionPerformed(e);
    }
}

