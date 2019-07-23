package com.ecommerce.api.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static List<Integer> decodeIntList(String str){
		
		String[] vet = str.split(",");
		List<Integer> list = new ArrayList<>();
		
		for(int i = 0; i < vet.length; i++) {
			list.add(Integer.parseInt(vet[i]));
		}
		
		return list;
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String decodeParam(String str) {
		try {
			return URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}
