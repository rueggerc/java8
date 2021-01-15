package com.rueggerllc.tests;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Test;

public class JSONTests {

    private static Logger logger = Logger.getLogger(JSONTests.class);

    @Test
    public void testSchema() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Records:", "fred");
        jsonObject.put("temperature:", 93.22);
        logger.info("jsonObject=" +jsonObject);
    }
}
