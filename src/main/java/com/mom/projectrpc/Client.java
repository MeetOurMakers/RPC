package com.mom.projectrpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * @author Sisi Wang & Bo Ding
 *
 *         Nov 5, 2015
 */
public class Client {
	public String server_ip = null;// server IP address
	public static final int SERVER_PORT = 8090;// server port number
	public static final int TIMEOUT = 30000;// threshold = 30s

	/*
	 * Client constructor
	 */
	public Client(String address) {

		this.server_ip = address;
	}

	/*
	 * Get server_ip
	 */
	public String getServerIp() {
		return this.server_ip;
	}

	public static void main(String[] args) throws IOException {

		Client client = new Client(args[0]);// new client instance with running
											// Argument: the IP address
		client.startClient();
	}

	/**
	 * @param order
	 * @throws IOException
	 */
	public void startClient() throws IOException {
		SupTools.showInstruction();
		TTransport transport = null;// Thrift transport
		boolean flag = true;
		BufferedReader mBrsend = null;
		try {
			mBrsend = new BufferedReader(new InputStreamReader(System.in));
			// blocking I/O transport
			transport = new TSocket(this.getServerIp(), SERVER_PORT, TIMEOUT);
			// protocol should be the same with the server
			TProtocol protocol = new TBinaryProtocol(transport);
			// automatically generated by Thrift
			KeyValueOperateService.Client client = new KeyValueOperateService.Client(protocol);
			transport.open();
			while (flag) {
				System.out.println("Please input your command:");
				String order = mBrsend.readLine();
				if (order.equals("bye")) {
					flag = false;
					SupTools.showMeswithTime("client closed");
				} else {
					String result = client.executeAndReply(order);// call remote function
					SupTools.showMeswithTime("Server reply : " + result);
				}
			}
		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		} finally {
			if (null != transport) {
				transport.close();
			}
		}
	}
}
