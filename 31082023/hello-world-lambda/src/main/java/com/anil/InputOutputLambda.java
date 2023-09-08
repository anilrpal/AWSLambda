package com.anil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

//import com.amazonaws.services.lambda.runtime.events.S3Event;

public class InputOutputLambda {
	
//	Example 1
//	public int handler(int num) {
//		System.out.println("Input : "+num);
//		return num+100;
//	}
	
//	Example 2
//	public boolean handler(boolean flag) {
//		System.out.println("Input : "+flag);
//		return !flag;
//	}
	
//	Example 3
//	public List<Integer> handler(List<Integer> list) {
//		System.out.println("Input : "+list);
//		List<Integer> updatedList=new ArrayList<>();
//		list.forEach(a-> updatedList.add(a+100));
//		return updatedList;
//	}
	
	
//	Example 4
//	public Map<String,String> handler(Map<String,String> input) {
//		System.out.println("Input : "+input);
//		Map<String,String> newMap=new HashMap<>();
//		input.forEach((k,v)->newMap.put("New Map -> " +k, v));
//		return newMap;
//	}
	
//	Example 5
//	public Map<String,Map<String,Integer>> handler(List<Map<String,Integer>> input) {
//		System.out.println("Input : "+input);
//		Map<String,Map<String,Integer>> newMap=new HashMap<>();
//		
//		IntStream.range(0, input.size())
//			.forEach(i->newMap.put("Nested at posion "+i, input.get(i)));
//		
//		return newMap;
//	}
	
	
//	Example 6
//	public PojoResponse handler(PojoInput input) {
//		return new PojoResponse("Input was "+input.getA());
//	}
//	
//	public static class PojoInput{
//		private String a;
//
//		public String getA() {
//			return a;
//		}
//
//		public void setA(String a) {
//			this.a = a;
//		}
//		
//	}
//	
//	
//	public static class PojoResponse{
//		private String b;
//
//		public PojoResponse(String b) {
//			super();
//			this.b = b;
//		}
//
//		public String getB() {
//			return b;
//		}
//
//		public void setB(String b) {
//			this.b = b;
//		}
//		
//	}
	
	
//	Example 7
//	public void handler(S3Event s3Event) {
//		System.out.println("New S3 object is created. key is "+s3Event.getRecords().get(0).getS3().getObject().getKey());
//	}
	
//	Example 8
	
	public void handler(InputStream inputStream,OutputStream outputStream) throws IOException {
		int letter;
		while((letter=inputStream.read())!=-1) {
			outputStream.write(Character.toUpperCase(letter));
		}
	}
	
	
}
