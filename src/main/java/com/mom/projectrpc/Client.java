package com.mom.projectrpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
 
/**
 * Created by Bo Ding & Sisi Wang on 15-11-2.
 */

public class Client{
	public String server_ip = null;
	public static final int SERVER_PORT = 8090;
	public static final int TIMEOUT = 30000;
	public Client(String address){

    	this.server_ip = address;
    }
    public String getServerIp(){
    	return this.server_ip;
    }
	/**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
    	boolean flag = true;
		BufferedReader mBrsend = new BufferedReader(new InputStreamReader(System.in));
    	System.out.println("Please input your IP address:");
    	String add = mBrsend.readLine();
    	Client client = new Client(add);
        
		while(flag){
			System.out.println("Please input your command:"
					+ "\n 1. put key value"
					+ "\n 2. get key"
					+ "\n 3. delete key"
					+ "\n 4. disconnect");
			String str = mBrsend.readLine();
			if(str.equals("disconnect")){
				System.out.println("Disconnect.");
				flag = false;
			}
			client.startClient(str);
		}
	}
 
    
    /**
     * @param order
     * @throws IOException 
     */
    public void startClient(String order) throws IOException {
        TTransport transport = null;
        try {
            transport = new TSocket(this.getServerIp(), SERVER_PORT, TIMEOUT);
            // 协议要和服务端一致
            TProtocol protocol = new TBinaryProtocol(transport);
//            TProtocol protocol = new TCompactProtocol(transport);
            // TProtocol protocol = new TJSONProtocol(transport);
            KeyValueOperateService.Client client = new KeyValueOperateService.Client(
                    protocol);
            transport.open();
            String result = client.executeAndReply(order);
    		
            System.out.println("Thrift client result =: " + result);
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            if (null != transport) {
                transport.close();
            }
        }
    }
}

