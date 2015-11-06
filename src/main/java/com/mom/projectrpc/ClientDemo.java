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
 * Created by amosli on 14-8-12.
 */
public class ClientDemo {
 
    public static final String SERVER_IP = "localhost";
    public static final int SERVER_PORT = 8090;
    public static final int TIMEOUT = 30000;
 
    /**
     * @param args
     */
    public static void main(String[] args) {
        ClientDemo client = new ClientDemo();
        try {
			client.startClient("amosli");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
    }
 
    /**
     * @param userName
     * @throws IOException 
     */
    public void startClient(String userName) throws IOException {
        TTransport transport = null;
        try {
            transport = new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT);
            // 协议要和服务端一致
            TProtocol protocol = new TBinaryProtocol(transport);
//            TProtocol protocol = new TCompactProtocol(transport);
            // TProtocol protocol = new TJSONProtocol(transport);
            KeyValueOperateService.Client client = new KeyValueOperateService.Client(
                    protocol);
            transport.open();
            String result = client.executeAndReply(userName);
    		boolean flag = true;
    		BufferedReader mBrsend = new BufferedReader(new InputStreamReader(System.in));
    		while(flag){
    			System.out.println("Please input your command:"
    					+ "\n 1. put key value"
    					+ "\n 2. get key"
    					+ "\n 3. delete key"
    					+ "\n 4. disconnect");
    			String str = mBrsend.readLine();
    			if(str.equals("disconnect") || str==null ||str.equals("")){
    				flag = false;
    				System.out.println("Disconnect.");
    				}
    			else{
    				String[] parts = str.split(" ");
    				System.out.println(parts[0]);
    				switch(parts[0]){
    				case "put":
    					System.out.println("put =: " + result);
    					break;
    				case "get":
    					System.out.println("get =: " + result);
    					break;
    				case "delete":
    					System.out.println("delete =: " + result);
    					break;
    				default:
    					System.out.println("Insufficient command. Please follow the format.");
    					
    				}
    			}
    				}
            System.out.println("Thrift client result =: " + result);
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
