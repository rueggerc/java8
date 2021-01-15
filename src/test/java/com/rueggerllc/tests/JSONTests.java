package com.rueggerllc.tests;

import com.rueggerllc.tests.util.TestSupport;
import org.apache.log4j.Logger;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.junit.Test;

public class JSONTests {

  private static Logger logger = Logger.getLogger(JSONTests.class);

  @Test
  public void testCreateObject() throws Exception {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("Records:", "fred");
    jsonObject.put("temperature:", 93.22);
    logger.info("jsonObject=" + jsonObject);
  }

  @Test
  public void testReadObject() throws Exception {
    String personJSON = TestSupport.getResourceFileContents("/person.json");
    JSONObject person = new JSONObject(personJSON);
    logger.info(person);
  }

  @Test
  public void testSchemaValidPerson() throws Exception {
    try {
      JSONObject rawSchema =
          new JSONObject(TestSupport.getResourceFileContents("/person_schema.json"));
      logger.info("Got Raw Schema");
      Schema schema = SchemaLoader.load(rawSchema);
      logger.info("Schema Loaded, doing Validation");
      schema.validate(getPersonObject("/person.json"));
      logger.info("Person Object Validated");
    } catch (Exception e) {
      logger.error("ERROR:" + e);
    }
  }

  @Test
  public void testSchemaInvalidPerson() throws Exception {
    try {
      JSONObject rawSchema =
              new JSONObject(TestSupport.getResourceFileContents("/person_schema.json"));
      logger.info("Got Raw Schema");
      Schema schema = SchemaLoader.load(rawSchema);
      logger.info("Schema Loaded, doing Validation");
      schema.validate(getPersonObject("/person_errors.json"));
      logger.info("Person Object Validated");
    } catch (Exception e) {
      logger.error("ERROR:" + e);
    }
  }

  private JSONObject getPersonObject(String fileName) throws Exception {
    String personJSON = TestSupport.getResourceFileContents(fileName);
    logger.info("PersonString=" + personJSON);
    JSONObject person = new JSONObject(personJSON);
    return person;
  }
}
