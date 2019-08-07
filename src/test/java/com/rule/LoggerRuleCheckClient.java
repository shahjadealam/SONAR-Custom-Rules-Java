package com.rule;

import java.lang.reflect.Method;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.rule.test.ClientFour;

public class LoggerRuleCheckClient {

	private static Logger log = Logger.getLogger(LoggerRuleCheckClient.class);

	public static void main(String[] args) {
		// Emp logging = new Emp();
		// log.debug();
		int i = 4;
		// log.debug("Message");
		/*
		 * if (log.isDebugEnabled() && i <= 5) { log.debug("Message"); }
		 * 
		 * if (i <= 1 && log.isDebugEnabled()) { log.debug("Message"); }
		 * 
		 * if (i == 4 || log.isDebugEnabled()) { log.info("Message"); }
		 */

		log.debug("Message");
		/*
		 * if (i<5 && log.isDebugEnabled() ) { String name = "HCL";
		 * log.debug("Message"); }
		 */
		
		if (log.isDebugEnabled() || i==5 ) {
			String name = "HCL";
			log.debug("Message");
		}
		
		if ( log.isDebugEnabled() && i<5 ) {
			String name = "HCL";
			log.info("Message");
		}
		/*
		 * int[] marks = { 125, 132, 95, 116, 110 }; for (int mark : marks) {
		 * System.out.println(mark); log.debug("Message"); }
		 */
		/*
		 * Emp e1=new Emp();
		 * 
		 * Class tClass = log.getClass(); Method[] methods = tClass.getMethods(); for
		 * (int i = 0; i < methods.length; i++) { System.out.println("public method: " +
		 * methods[i]); } log.debug("Message");
		 * 
		 * if (log.isDebugEnabled()) { log.info("Message"); }
		 */
		System.out.println("test sonar DAAAAAAAAAAAAAAAAAAAAA");
		/*
		 * int[] marks = { 125, 132, 95, 116, 110 }; for (int mark : marks) {
		 * System.out.println(mark);
		 * 
		 * log.debug("Message");
		 * 
		 * System.out.println("inside for each loop"); if (log.isDebugEnabled()) {
		 * log.debug("Message"); } //log.info("Info"); } }
		 */

		// log.info("Info");
		// log.debug("Message"); // log.debug("Message without condition");

		/*
		 * ClientFour clientFour = new ClientFour(); clientFour.firstMethod();
		 * 
		 * ClientTwo client2 = new ClientTwo(); ClientThree clientThree = new
		 * ClientThree();
		 * 
		 * client2.firstMethod(); client2.secondMethod(); clientThree.firstMethod();
		 */

		// log.debug("Message");

		// ** try **

		/*
		 * try { log.debug("Message"); int j = 10 / 0; System.out.println(j);
		 * 
		 * if (log.isDebugEnabled()) { log.debug("Message"); } } catch (Exception e) {
		 * System.out.println(e.getMessage()); System.out.println("inside catch block");
		 * log.debug("Message"); if (log.isDebugEnabled()) { log.debug("Message"); } }
		 */

		/*
		 * // ** ForEach loop ** int[] marks = { 125, 132, 95, 116, 110 }; for (int mark
		 * : marks) { System.out.println(mark); log.debug("Message");
		 * System.out.println("inside for each loop"); if (log.isDebugEnabled()) {
		 * log.debug("Message"); } log.info("Info"); }
		 */
		/*
		 * // ** switch ** String case1 = "A"; String case2 = "B";
		 * 
		 * switch (case1) {
		 * 
		 * case "A": System.out.println("Case-1"); log.debug("Message"); break; case
		 * "B": System.out.println("Case-2"); break;
		 * 
		 * default: log.debug("Message"); break;
		 * 
		 * }
		 */
		/*
		 * if(false) { } else if(false) { System.out.println("IF"); if
		 * (log.isDebugEnabled()) { log.debug("Message"); } log.debug("Message"); } else
		 * { log.debug("Message"); }
		 * 
		 * 
		 * /* // ** For loop ** for (int count = 0; i < 5; i++) {
		 * System.out.println(count); log.debug("Message"); }
		 * 
		 * 
		 * // ** ForEach loop ** int[] marks = { 125, 132, 95, 116, 110 }; for (int mark
		 * : marks) { System.out.println(mark); log.debug("Message");
		 * System.out.println("inside for each loop"); if (log.isDebugEnabled()) {
		 * log.debug("Message"); } log.info("Info"); }
		 * 
		 * int a=1;
		 * 
		 * do {
		 * 
		 * log.debug("Message"); a++; System.out.println(a); log.debug("Message");
		 * }while(a<5);
		 * 
		 * }
		 */

		/*
		 * private static void methodOne() {
		 * 
		 * log.debug("Message"); if (log.isDebugEnabled()) { log.debug("Message"); }
		 * 
		 * }
		 * 
		 * private static void methodTwo() { log.debug("Message"); if
		 * (log.isDebugEnabled()) { log.debug("Message"); } }
		 * 
		 * private static void methodRThird() { log.debug("Message"); if
		 * (log.isDebugEnabled()) { log.debug("Message"); } }
		 */
		/*
		 * static { System.out.println("Static block"); log.debug("Message");
		 * if(log.isDebugEnabled()) { log.debug("Message"); } }
		 */

	}

}
