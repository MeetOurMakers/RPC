package com.mom.projectrpc;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
 
 
/**
 * Created by amosli on 14-8-12.
 */
public class Serverdemo {
    public static final int SERVER_PORT = 8090;
 
    /**
     * @param args
     */
    public static void main(String[] args) {
        Serverdemo server = new Serverdemo();
        server.startServer();
    }
 
    public void startServer() {
		try {
			System.out.println("HelloWorld TThreadPoolServer start ....");
 
			TProcessor tprocessor = new KeyValueOperateService.Processor<KeyValueOperateService.Iface>(
					new KeyValueOperateImpl());
 
			 TServerSocket serverTransport = new TServerSocket(SERVER_PORT);
			 TThreadPoolServer.Args ttpsArgs = new TThreadPoolServer.Args(
			 serverTransport);
			 ttpsArgs.processor(tprocessor);
			 ttpsArgs.protocolFactory(new TBinaryProtocol.Factory());
 
			 TServer server = new TThreadPoolServer(ttpsArgs);
			 server.serve();
 
		} catch (Exception e) {
			System.out.println("Server start error!!!");
			e.printStackTrace();
		}
	}
 
}
