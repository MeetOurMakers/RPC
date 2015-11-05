package com.mom.projectrpc;

import org.apache.thrift.TException;

public class KeyValueOperateImpl implements KeyValueOperateService.Iface {
	 
    public KeyValueOperateImpl() {
    }
 
    public String executeAndReply(String order) throws org.apache.thrift.TException{
    	/*deal with the command*/
        return "Hi!";
    }
 
}
