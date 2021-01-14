package com.rueggerllc.beans;



import com.rueggerllc.functions.MyFunctionInterface;
import org.apache.log4j.Logger;

public class BusinessObject {
	
	private static Logger logger = Logger.getLogger(BusinessObject.class);
	
	public void execute(MyFunctionInterface theFunction) {
		logger.info("BusinessObject.execute BEGIN");
		theFunction.doSomeWork();
	}

}
