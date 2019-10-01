package com.gmail.ansxodud238.network;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SocketProgrammingWindow extends JFrame {
	JButton btnClient, btnServerOn, btnServerContect ;
	JLabel clientIn, serverLog;
	JTextField tf;
	JTextArea ta;
	
	public SocketProgrammingWindow() {
		
		JPanel p1 = new JPanel();
		btnClient = new JButton("서버 데이터 전송");
		btnServerOn = new JButton("서버 온");
		btnServerContect = new JButton("서버 접속");
		p1.add(btnServerOn);
		p1.add(btnServerContect);
		p1.add(btnClient);
		p1.setLayout(new FlowLayout());
		add("North", p1);
		
		JPanel p2 = new JPanel();
		clientIn = new JLabel("메세지 입력");
		tf = new JTextField(30);
		p2.add(clientIn);
		p2.add(tf);
		add("Center", p2);
		
		JPanel p3 = new JPanel();
		serverLog = new JLabel("서버 데이터 로그");
		ta = new JTextArea(50,50);
		ta.setLayout(new FlowLayout());
		p3.add(serverLog);
		p3.add(ta);
		add("South", p3);
		
		
		
		
		SocketProgrammingEvent handler = new SocketProgrammingEvent(this);
		btnServerOn.addActionListener(handler);
		btnServerContect.addActionListener(handler);
		btnClient.addActionListener(handler);
		
		
		setBounds(100,100,300,300);
		pack();
		setTitle("SocketProgramming");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

}
