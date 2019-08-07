package com.rule.test;

import org.apache.log4j.Logger;

public class ClientFour{
	private static Logger log = Logger.getLogger(ClientFour.class);

	
	public void firstMethod()
	{
		log.debug("Message");
		 if (log.isDebugEnabled()) { log.debug("Message"); }
	}
	
}
