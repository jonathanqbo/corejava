package bq.demo.util;

import java.util.Stack;

public class TimeCost {

	private static Stack<Operation> items = new Stack<Operation>();
	
	public static void begin(String operation){
		Operation oneoperation = new Operation(operation, System.currentTimeMillis());
		items.push(oneoperation);
	}
	
	public static void end(){
		long endtime = System.currentTimeMillis();
		Operation lastOperation = items.pop();
		System.out.println(lastOperation.name + " cost time(ms) : " + (endtime - lastOperation.begintime));
	}
	
	static class Operation{
		String name;
		long begintime;
		
		Operation(String name, long begintime){
			this.name = name;
			this.begintime = begintime;
		}
	}
	
}
