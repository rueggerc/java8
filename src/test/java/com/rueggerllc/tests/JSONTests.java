package com.rueggerllc.tests;

import com.rueggerllc.tests.util.TestSupport;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Test;

public class JSONTests {

    private static Logger logger = Logger.getLogger(JSONTests.class);

    @Test
    public void testSchema() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Records:", "fred");
        jsonObject.put("temperature:", 93.22);
        logger.info("jsonObject=" +jsonObject);

        String personJSON = TestSupport.getResourceFileContents("/person.json");
        logger.info(personJSON);

        logger.info("Get JSON Object from String");
        JSONObject person = new JSONObject(personJSON);
        logger.info(person);
    }




}
