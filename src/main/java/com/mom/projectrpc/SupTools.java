/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mom.projectrpc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Xiao
 */
public class SupTools {
	public static String executeOrder(String order, Map<String, String> hashmap) {
		String[] parts = order.split("\\s+");
		String value = null;
		String result = null;
		String reply = null;
		if (parts.length < 1) {
			reply = "Please input your command!";
			return reply;
		}
		switch (parts[0]) {
		case "put":// put operation
			if (parts.length < 3) {// order less than 3 parts
				reply = "cannot read your command! Maybe you forget the value.";
				return reply;
			}
			if (parts.length > 3) {// order more than 3 parts
				reply = "cannot read your command! Maybe you offer more than one value.";
				return reply;
			}
			hashmap.put(parts[1], parts[2]);
			reply = parts[0] + " " + parts[1] + " " + parts[2] + " success!";
			break;
		case "get":
			if (parts.length > 2) {// order less than 2 parts
				reply = "cannot read your command! Maybe you offer more than one key.";
				return reply;
			}
			if (parts.length < 2) {// order more than 2 parts
				reply = "cannot read your command! Maybe you forget the key.";
				return reply;
			}
			value = hashmap.get(parts[1]);
			if (value == null)
				reply = parts[0] + " " + parts[1] + " fails because no matching key!";
			else
				reply = "the value of " + parts[1] + " is " + value;
			break;
		case "delete":
			if (parts.length > 2) {// order less than 2 parts
				reply = "cannot read your command! Maybe you offer more than one key.";
				return reply;
			}
			if (parts.length < 2) {// order more than 2 parts
				reply = "cannot read your command! Maybe you forget the key.";
				return reply;
			}
			result = hashmap.remove(parts[1]);
			if (result == null)
				reply = parts[0] + " " + parts[1] + " fails because no matching keys!";
			else
				reply = "delete the key " + parts[1] + " success!";
			break;
		default:
			reply = "cannot read your command!";
		}
		return reply;
	}
	/**
	 * To show the message with time
	 * 
	 * @author Fengyuan Zhang
	 */
	public static void showMeswithTime(String str) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = df.format(new Date());
		System.out.println(date + " : " + str);
	}

	/**
	 * To show the command format you will use
	 * 
	 * @author Fengyuan Zhang
	 */
	public static void showInstruction() {
		System.out.println("put key value : put operation");
		System.out.println("get key : get the value of this key");
		System.out.println("delete key : delete this key-value pairs");
	}

}
