package com.gmail.ansxodud238.network;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketProgrammingEvent implements ActionListener {

	String s = "오케이";

	SocketProgrammingWindow window;

	public SocketProgrammingEvent(SocketProgrammingWindow window) {
		super();
		this.window = window;
	}

	InetAddress serverIp;
	Socket socket;

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == window.btnServerOn) {
			Thread th = new Thread() {
				public void run() {
					try {
						ServerSocket ss = new ServerSocket(5555);
						while (true) {
							window.ta.append("서버에 접속중 입니다.......\n");
							Socket socket1 = ss.accept();
							window.ta.append("접속자:" + socket1.toString() + "\n");
							BufferedReader br = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
							while (true) {
								String line = br.readLine();
								if (line == null) {
									break;
								}
								window.ta.append(line + "\n");
								System.out.println(line);
							}

							br.close();
						}
					} catch (Exception e1) {
						System.out.printf("%s\n", e1.getMessage());
						e1.printStackTrace();
					}
				}
			};
			th.start();
		} else if (e.getSource() == window.btnServerContect) {
			Thread th = new Thread() {
				public void run() {
					try {
						// 클라이언트 소켓 생성
						serverIp = InetAddress.getByName("192.168.200.178");
						socket = new Socket(serverIp, 5555);
						socket.setSoTimeout(30000);

					} catch (Exception e1) {
						System.out.printf("%s\n", e1.getMessage());
						e1.printStackTrace();

					}
				}
			};
			th.start();
		} else if (e.getSource() == window.btnClient) {
			Thread th = new Thread() {
				public void run() {

					try {

						// 기록을 해주는 스트림을 생성
						PrintWriter pw = new PrintWriter(socket.getOutputStream());
						while (true) {
							// 메세지 입력받기
							String taText = window.tf.getText();

							// 메세지 전송
							pw.println(taText);
							pw.flush();
							pw.close();
							if (window.ta.getText().equals("end")) {
								window.ta.append("서버 전송 중지\n");
								break;
							}
						}
						// 사용이 끝나서 종료
						socket.close();

					} catch (Exception e1) {
						System.out.printf("%s\n", e1.getMessage());
						e1.printStackTrace();
					}
				}
			};
			th.start();
		}

	}

}
