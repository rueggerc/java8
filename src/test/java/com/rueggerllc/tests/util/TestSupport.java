package com.rueggerllc.tests.util;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class TestSupport {

  private static Logger log = Logger.getLogger(TestSupport.class);

  public static String createMockS3ObjectCreatedPayload(String bucketName, String fileName)
      throws Exception {
    JSONObject root = new JSONObject();
    JSONArray records = new JSONArray();
    root.put("Records", records);

    JSONObject message1 = new JSONObject();
    records.put(0, message1);
    message1.put("eventVersion", "2.1");
    message1.put("eventSource", "aws:s3");
    message1.put("awsRegion", "us-east-1");
    message1.put("eventTime", "2020-06-02T18:03:24.346Z");
    message1.put("eventName", "ObjectCreated:Put");
    JSONObject userIdentity = new JSONObject();
    message1.put("userIdentity", userIdentity);
    userIdentity.put("principalId", "AWS:AIDAJKQSI3XJFWKBHOHHE");

    JSONObject requestParameters = new JSONObject();
    message1.put("requestParameters", requestParameters);
    requestParameters.put("sourceIPAddress", "73.99.119.193");

    JSONObject responseElements = new JSONObject();
    message1.put("responseElements", responseElements);
    responseElements.put("x-amz-request-id", "775C161E41C05E3C");
    responseElements.put(
        "x-amz-id-2",
        "xrLjnKkWKzkLPc4k36dcJjxY5bmFcQcVphO+tTCtzL5KcUru4+Fj/P4Azjex7VPNmTaCDFo7FJXO8OG4+7seH8Mom7Krf22g");

    JSONObject s3 = new JSONObject();
    message1.put("s3", s3);
    s3.put("s3SchemaVersion", "1.0");
    s3.put("configurationId", "NzZjNzUyODMtZTRkMy00ZWEyLWE4YzAtMDdhNzVmMjIxZmZi");

    JSONObject bucket = new JSONObject();
    s3.put("bucket", bucket);
    bucket.put("name", bucketName);
    JSONObject ownerIdentity = new JSONObject();
    bucket.put("ownerIdentity", ownerIdentity);
    ownerIdentity.put("principalId", "A3HIUF3I0GVUD9");
    bucket.put("arn", String.format("arn:aws:s3:::%s", bucketName));

    JSONObject object = new JSONObject();
    s3.put("object", object);
    object.put("key", fileName);
    object.put("size", 68);
    object.put("eTag", "19d14f8a34a54cf86e67fc593793fe7b");
    object.put("sequencer", "005ED6947386AA22FB");

    return root.toString();
  }

  public static String getResourceFileContents(String fileName) throws Exception {
    try {
      InputStream is = TestSupport.class.getResourceAsStream(fileName);
      InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
      BufferedReader reader = new BufferedReader(streamReader);
      StringBuilder buffer = new StringBuilder();
      for (String line; (line = reader.readLine()) != null; ) {
        buffer.append(line);
      }
      return buffer.toString();
    } catch (Exception e) {
      System.out.println("Error Reading File:" + fileName + "\n" + e);
      throw e;
    }
  }

  public static Properties getProperties(String fileName) throws Exception {
    try {
      InputStream inputStream = TestSupport.class.getResourceAsStream(fileName);
      Properties props = new Properties();
      props.load(inputStream);
      return props;
    } catch (Exception e) {
      log.error("ERROR:\n", e);
      throw e;
    }
  }
}
