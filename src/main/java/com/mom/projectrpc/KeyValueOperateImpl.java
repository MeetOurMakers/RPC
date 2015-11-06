package com.mom.projectrpc;

import java.util.HashMap;
import java.util.Map;

import org.apache.thrift.TException;

public class KeyValueOperateImpl implements KeyValueOperateService.Iface {

	Map<String,String> hashmap = null;
    public KeyValueOperateImpl() {
    	hashmap = new HashMap<String,String>();
    }

	@Override
	public String executeAndReply(String order) throws TException {
		return SupTools.executeOrder(order, hashmap);
	}
 
}
