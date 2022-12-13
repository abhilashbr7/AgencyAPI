package com.qa.tests;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.IExecutionListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.AgencySave;
import com.qa.data.Individuo_Certificate;
//import com.qa.data.Users;
import com.qa.util.TestUtil;

public class PostAPItest extends TestBase implements IExecutionListener {

	TestBase testbase;
	RestClient restclient;
	String serviceurl;
	String apiurl;
	String url;
	String Token,Fondo_Token, citizen_referenceId, corporate_referenceId,get_token,DTOP_tocken;
	String api_test_token,justicia_url,caprequest,Retrieve,Validate,Justicia_CriminalRecord;
	String Patrono_Certificate,CFSE_serviceurl,api_CFSEtest_toke,InformationDDeuda,CertificateDDeuda,CertificateDVegencia,FondoPing,Compliance,EstadoPing,Certificado,api_CFSEtest_token;
	String Individuo_Certificate,serviceurl_hacienda,ping,compliance,authorize,complianceDocuments,serviceurl_Ind_hacienda,ping_ind,compliance_ind,authorize_ind,complianceDocuments_ind;
	String crimurl,crimauthenticate,crimcertificate,crimcompliance,crimfilecontent,FileContentId,FileContent;
	CloseableHttpResponse closeableHttpResponse;
	

	@BeforeMethod

	public void setup() throws ClientProtocolException, IOException {
		testbase = new TestBase();
		serviceurl = prop.getProperty("URL");
		api_test_token = prop.getProperty("api_test_token");
		api_test_token = serviceurl + api_test_token;
		Individuo_Certificate = prop.getProperty("Individuo_Certificate");
		Individuo_Certificate = serviceurl + Individuo_Certificate;
		serviceurl_hacienda = prop.getProperty("HACIENDAURL");
		ping = prop.getProperty("ping");
		ping = serviceurl_hacienda + ping;
		serviceurl_Ind_hacienda = prop.getProperty("hacieda_ind_url");
		ping_ind = prop.getProperty("ping");
		ping_ind = serviceurl_Ind_hacienda + ping_ind;
		compliance = prop.getProperty("compliance");
		compliance = serviceurl_hacienda + compliance;
		compliance_ind = serviceurl_Ind_hacienda + compliance;
		authorize = prop.getProperty("authorize");
		authorize = serviceurl_hacienda + authorize;
		authorize_ind = prop.getProperty("authorize");
		authorize_ind = serviceurl_Ind_hacienda + authorize_ind; 
		complianceDocuments = prop.getProperty("complianceDocuments");
		complianceDocuments = serviceurl_hacienda + complianceDocuments;
		complianceDocuments_ind = prop.getProperty("complianceDocuments");
		complianceDocuments_ind = serviceurl_Ind_hacienda + complianceDocuments_ind;
		justicia_url = prop.getProperty("justicia_url");
		caprequest = prop.getProperty("caprequest");
		caprequest = justicia_url + caprequest;
		Retrieve = prop.getProperty("Retrieve");
		Retrieve = justicia_url + Retrieve;
		Validate = prop.getProperty("Validate");
		Validate = justicia_url + Validate;
		Justicia_CriminalRecord = prop.getProperty("Justicia_CriminalRecord");
		//Justicia_CriminalRecord = Justicia_CriminalRecord;
		Patrono_Certificate = prop.getProperty("Patrono_Certificate");
		Patrono_Certificate = serviceurl + Patrono_Certificate;
		CFSE_serviceurl = prop.getProperty("URL3");
		api_CFSEtest_token = prop.getProperty("api_CFSEtest_token");
		api_CFSEtest_token = CFSE_serviceurl + api_CFSEtest_token;
		InformationDDeuda = prop.getProperty("InformationDDeuda");
		InformationDDeuda = CFSE_serviceurl + InformationDDeuda;
		CertificateDDeuda = prop.getProperty("CertificateDDeuda");
		CertificateDDeuda = CFSE_serviceurl + CertificateDDeuda;
		CertificateDVegencia = prop.getProperty("CertificateDVegencia");
		CertificateDVegencia = CFSE_serviceurl + CertificateDVegencia;
		FondoPing = prop.getProperty("FondoPing");
		FondoPing = CFSE_serviceurl + FondoPing;
		serviceurl = prop.getProperty("URL4");
		Compliance = prop.getProperty("Compliance");
		Compliance = serviceurl + Compliance;
		EstadoPing = prop.getProperty("EstadoPing");
		EstadoPing = serviceurl + EstadoPing;
		Certificado = prop.getProperty("Certificado");
		Certificado = serviceurl + Certificado;
		crimurl = prop.getProperty("crimurl");
		crimauthenticate = prop.getProperty("crimauthenticate");
		crimauthenticate = crimurl + crimauthenticate;
		crimcertificate = prop.getProperty("crimcertificate");
		crimcertificate = crimurl + crimcertificate;
		crimcompliance = prop.getProperty("crimcompliance");
		crimcompliance = crimurl + crimcompliance;
		crimfilecontent = prop.getProperty("crimfilecontent");
		crimfilecontent = crimurl + crimfilecontent;
		get_token = "https://auth.cescodigital.dtop.pr.gov/auth/realms/cescodigital/protocol/openid-connect/token";
	}

	@Test(priority = 1)
	public void Assume_get_access_token() throws ClientProtocolException, IOException {

				
		  List<NameValuePair> params = new ArrayList<>();
		  params.add(new BasicNameValuePair("grant_type", "password"));
		  params.add(new BasicNameValuePair("username", "dToplicenciaS"));
		  params.add(new BasicNameValuePair("password", "aldf@23901FTOPasd23"));
		  params.add(new BasicNameValuePair("client_id", "OGP20180821&*990899XX"));
		  params.add(new BasicNameValuePair("client_secret", "5&t%$xZHpr9800"));
		  // add to request
		  
		  CloseableHttpClient httpclient = HttpClients.createDefault();
	      HttpPost httppost = new HttpPost(api_test_token);  //post request
	      System.out.println(httppost);
		  httppost.setEntity(new UrlEncodedFormEntity(params)); //for payload
		 CloseableHttpResponse closeableHttpResponse = httpclient.execute(httppost);
		  
		

//			//1.GET status code
		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code:" + statuscode);
		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");

//			//2.Json String
		String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responsestring);
		System.out.println("JSON response from API---->" + responseJson);

		// to validate
//			Users userobj = mapper.readValue(responsestring, Users.class);
//			System.out.println(userobj);

//			//To get value from JSON Array
		Token = TestUtil.getValueByjpath(responseJson, "/access_token");
		System.out.println("Token is : " + Token);
//		    			
//			//3.All Headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for (Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());

		}
		System.out.println("Headers Array-->" + allHeaders);

	}

	@Test(priority = 2)
	public void Assume_Individuo_Certificate() throws ClientProtocolException, IOException {

	  CloseableHttpClient httpclient = HttpClients.createDefault();
	      HttpPost httppost = new HttpPost(Individuo_Certificate);
	      System.out.println(httppost);
	      System.out.println(Individuo_Certificate);
	      
	      String jsonpayload = "{\r\n"
		      		+ "    \"SolicitantData\": null,\r\n"
		      		+ "    \"DemographicalData\": {\r\n"
		      		+ "        \"Name\": \"oscar\",\r\n"
		      		+ "        \"MiddleInitial\": \"\",\r\n"
		      		+ "        \"LastName\": \"rivera\",\r\n"
		      		+ "        \"LastName2\": \"perello\",\r\n"
		      		+ "        \"NickName\": \"\",\r\n"
		      		+ "        \"SSN\": \"598408444\",\r\n"
		      		+ "        \"BirthDate\": \"1993-09-21\",\r\n"
		      		+ "        \"EmailAddress\": null,\r\n"
		      		+ "        \"DriverLicense\": \"4970687\",\r\n"
		      		+ "        \"DeathDate\": null,\r\n"
		      		+ "        \"Addresses\": [],\r\n"
		      		+ "        \"PhoneNumbers\": \" \"\r\n"
		      		+ "    },\r\n"
		      		+ "    \"TerminalId\": 1,\r\n"
		      		+ "    \"OGPCorrelationID\": \"\",\r\n"
		      		+ "    \"OGPATGNumber\": \"\",\r\n"
		      		+ "    \"Source\": \"\",\r\n"
		      		+ "    \"UserId\": \"\",\r\n"
		      		+ "    \"IndividualId\": \"\",\r\n"
		      		+ "    \"sendCertificate\": true,\r\n"
		      		+ "    \"SendByEmail\": false\r\n"
		      		+ "}";
		      
		      
		   StringEntity entity = new StringEntity(jsonpayload);
		   httppost.setEntity(entity);
	      
	          
	      httppost.setHeader("content-type", "application/json");
	      httppost.setHeader("Authorization", "Bearer " + Token);

	      
	     
	      
	      CloseableHttpResponse closeableHttpResponse = httpclient.execute(httppost);
//			//1.GET status code
		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code:" + statuscode);
		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");

//			//2.Json String
		String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responsestring);
		System.out.println("JSON response from API---->" + responseJson);

		// To get value from JSON Array
		String responseFromJson = TestUtil.getValueByjpath(responseJson, "/Success");
		System.out.println("Response From Json is : " + responseFromJson);

		// 3.All Headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for (Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());

		}
		System.out.println("Headers Array-->" + allHeaders);

	}
	
	@Test(priority = 3)
	public void Assume_Patroons_Certificate() throws ClientProtocolException, IOException {

		restclient = new RestClient();
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("Content-Type", "application/json");
		headermap.put("Authorization", "Bearer " + Token);
	     
	     String jsonpayload = "{\r\n"
	      + "    \"FEIN\":\"660962981\",\r\n"
	      + "    \"EmployerName\":\"Code Dog Technology Group LLC\",\r\n"
	      + "    \"sendCertificate\": true\r\n"
	      + "}";
	     
	     
	     closeableHttpResponse = restclient.post(Patrono_Certificate, jsonpayload, headermap);
	// //1.GET status code
	int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
	System.out.println("Status code:" + statuscode);
	Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");

	// //2.Json String
	String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
	JSONObject responseJson = new JSONObject(responsestring);
	System.out.println("JSON response from API---->" + responseJson);

	// To get value from JSON Array
	String responseFromJson = TestUtil.getValueByjpath(responseJson, "/Success");
	System.out.println("Response From Json is : " + responseFromJson);

	// 3.All Headers
	Header[] headersArray = closeableHttpResponse.getAllHeaders();
	HashMap<String, String> allHeaders = new HashMap<String, String>();

	for (Header header : headersArray) {
	allHeaders.put(header.getName(), header.getValue());

	}
	System.out.println("Headers Array-->" + allHeaders);

	}

	@Test(priority = 4)
	public void CFSE_Test_get_access_token() throws ClientProtocolException, IOException {
	 
	 CloseableHttpClient httpclient = HttpClients.createDefault();
	     HttpPost httppost = new HttpPost(api_CFSEtest_token);  //post request
	     System.out.println(httppost);
	     
	     
	     MultipartEntityBuilder builder = MultipartEntityBuilder.create();
	     builder.addTextBody("username", "testuser");
	     builder.addTextBody("password", "1qaz@wsX");
	    // builder.addBinaryBody(
	    //   "file", new File("test.txt"), ContentType.APPLICATION_OCTET_STREAM, "file.ext");

	     HttpEntity multipart = builder.build();
	     httppost.setEntity(multipart);

	     
	// httppost.setEntity(new UrlEncodedFormEntity(params)); //for payload
	CloseableHttpResponse closeableHttpResponse = httpclient.execute(httppost);
	 


	// //1.GET status code
	int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
	System.out.println("Status code:" + statuscode);
	Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");

	// //2.Json String
	String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
	JSONObject responseJson = new JSONObject(responsestring);
	System.out.println("JSON response from API---->" + responseJson);

	// to validate
	// Users userobj = mapper.readValue(responsestring, Users.class);
	// System.out.println(userobj);

	// //To get value from JSON Array
	Fondo_Token = TestUtil.getValueByjpath(responseJson, "/token");
	System.out.println("Token is : " + Token);
	//    
	// //3.All Headers
	Header[] headersArray = closeableHttpResponse.getAllHeaders();
	HashMap<String, String> allHeaders = new HashMap<String, String>();

	for (Header header : headersArray) {
	allHeaders.put(header.getName(), header.getValue());

	}
	System.out.println("Headers Array-->" + allHeaders);

	}

	@Test (priority = 5)
	  public void CFSE_get_informacionDeDeauda() throws ClientProtocolException, IOException
	{
	restclient = new RestClient();
	HashMap<String, String> headermap = new HashMap<String, String>();
	headermap.put("content-type", "application/json");
	//headermap.put("referenceId", "d286928c-9cd9-4b40-af4b-631d07312b0c");
	headermap.put("Authorization", "Bearer " +Fondo_Token);
	closeableHttpResponse = restclient.get(InformationDDeuda,headermap);

	//1.GET status code
	int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
	System.out.println("Status code:" +statuscode);
	Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");

	//2.Json String
	String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

	JSONObject responseJson = new JSONObject(responsestring);
	System.out.println("JSON response from API---->" +responseJson);
	String s = TestUtil.getValueByjpath(responseJson, "/success");
	System.out.println(s);
	String expected = "true";
	Assert.assertEquals(s, expected);


	//To get value from JSON Array
	//String lastname = TestUtil.getValueByjpath(responseJson, "/data[0]/last_name");
	//System.out.println("last name is : " + lastname);
	   
	//3.All Headers
	Header[] headersArray = closeableHttpResponse.getAllHeaders();
	HashMap<String,String> allHeaders = new HashMap<String,String>();

	for(Header header : headersArray)
	{
	 allHeaders.put(header.getName(), header.getValue());

	}
	}
	@Test (priority = 6)
	  public void CFSE_get_certificacionDeDeauda() throws ClientProtocolException, IOException
	{
	restclient = new RestClient();
	HashMap<String, String> headermap = new HashMap<String, String>();
	headermap.put("content-type", "application/json");
	//headermap.put("referenceId", "d286928c-9cd9-4b40-af4b-631d07312b0c");
	headermap.put("Authorization", "Bearer " +Fondo_Token);
	closeableHttpResponse = restclient.get(CertificateDDeuda,headermap);

	//1.GET status code
	int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
	System.out.println("Status code:" +statuscode);
	Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_500, "status code is not 200");

	//2.Json String
	String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

	JSONObject responseJson = new JSONObject(responsestring);
	System.out.println("JSON response from API---->" +responseJson);
	String s = TestUtil.getValueByjpath(responseJson, "/success");
	String expected = "false";
	Assert.assertEquals(s, expected);

	//To get value from JSON Array
	//String lastname = TestUtil.getValueByjpath(responseJson, "/data[0]/last_name");
	//System.out.println("last name is : " + lastname);
	   
	//3.All Headers
	Header[] headersArray = closeableHttpResponse.getAllHeaders();
	HashMap<String,String> allHeaders = new HashMap<String,String>();

	for(Header header : headersArray)
	{
	 allHeaders.put(header.getName(), header.getValue());

	}
	}
	@Test (priority = 7)
	  public void CFSE_get_certificacionDeVegencia() throws ClientProtocolException, IOException
	{
	restclient = new RestClient();
	HashMap<String, String> headermap = new HashMap<String, String>();
	headermap.put("content-type", "application/json");
	//headermap.put("referenceId", "d286928c-9cd9-4b40-af4b-631d07312b0c");
	headermap.put("Authorization", "Bearer " +Fondo_Token);
	closeableHttpResponse = restclient.get(CertificateDVegencia,headermap);

	//1.GET status code
	int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
	System.out.println("Status code:" +statuscode);
	Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_500, "status code is not 200");

	//2.Json String
	String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

	JSONObject responseJson = new JSONObject(responsestring);
	System.out.println("JSON response from API---->" +responseJson);
	String s = TestUtil.getValueByjpath(responseJson, "/success");
	String expected = "false";
	Assert.assertEquals(s, expected);

	//To get value from JSON Array
	//String lastname = TestUtil.getValueByjpath(responseJson, "/data[0]/last_name");
	//System.out.println("last name is : " + lastname);
	   
	//3.All Headers
	Header[] headersArray = closeableHttpResponse.getAllHeaders();
	HashMap<String,String> allHeaders = new HashMap<String,String>();

	for(Header header : headersArray)
	{
	 allHeaders.put(header.getName(), header.getValue());

	}
	}
	@Test (priority = 8)
	  public void CFSE_get_fondoPing() throws ClientProtocolException, IOException
	{
	restclient = new RestClient();
	HashMap<String, String> headermap = new HashMap<String, String>();
	headermap.put("content-type", "application/json");
	//headermap.put("referenceId", "d286928c-9cd9-4b40-af4b-631d07312b0c");
	headermap.put("Authorization", "Bearer " +Fondo_Token);
	closeableHttpResponse = restclient.get(FondoPing,headermap);

	//1.GET status code
	int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
	System.out.println("Status code:" +statuscode);
	Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");

	//2.Json String
	//String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

	//JSONObject responseJson = new JSONObject(responsestring);
	//System.out.println("JSON response from API---->" +responseJson);
	//String s = TestUtil.getValueByjpath(responseJson, "/certificateId");
	//System.out.println(s);
	
	   
	//3.All Headers
	Header[] headersArray = closeableHttpResponse.getAllHeaders();
	HashMap<String,String> allHeaders = new HashMap<String,String>();

	for(Header header : headersArray)
	{
	 allHeaders.put(header.getName(), header.getValue());

	}
	}

	
	@Test(priority = 9)
	public void Estado_Compliance() throws ClientProtocolException, IOException {

		restclient = new RestClient();
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("Content-Type", "application/json");
	     
	     String jsonpayload = "{\r\n"
	     		+ "    \"registerNumber\": 455749,\r\n"
	     		+ "    \"corpClass\": 15,\r\n"
	     		+ "    \"corpType\": 1,\r\n"
	     		+ "    \"jurisdiction\": 1,\r\n"
	     		+ "    \"IDEALId\": 1324234\r\n"
	     		+ "}";
	     
	     closeableHttpResponse = restclient.post(Compliance, jsonpayload, headermap);
	     
	     
	
	// //1.GET status code
	int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
	System.out.println("Status code:" + statuscode);
	Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");

	// //2.Json String
	String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
	JSONObject responseJson = new JSONObject(responsestring);
	System.out.println("JSON response from API---->" + responseJson);

	// To get value from JSON Array
	String responseFromJson = TestUtil.getValueByjpath(responseJson, "/status");
	System.out.println("Response From Json is : " + responseFromJson);

	// 3.All Headers
	Header[] headersArray = closeableHttpResponse.getAllHeaders();
	HashMap<String, String> allHeaders = new HashMap<String, String>();

	for (Header header : headersArray) {
	allHeaders.put(header.getName(), header.getValue());

	}
	System.out.println("Headers Array-->" + allHeaders);

	}


	@Test(priority = 10)
	public void Estado_Ping() throws ClientProtocolException, IOException {

		restclient = new RestClient();
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("Content-Type", "application/json");
	     System.out.println(EstadoPing);
	     
	     String jsonpayload = "{\r\n"
	      + "    registerNumber: 3992,\r\n"
	      + "    corpClass: 7,\r\n"
	      + "    corpType: 1,\r\n"
	      + "    jurisdiction: 1\r\n"
	      + "}";
	     
	     
	     closeableHttpResponse = restclient.post(EstadoPing, jsonpayload, headermap);
	// //1.GET status code
	int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
	System.out.println("Status code:" + statuscode);
	Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");

	// //2.Json String
	String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
	JSONObject responseJson = new JSONObject(responsestring);
	System.out.println("JSON response from API---->" + responseJson);

	// To get value from JSON Array
	String responseFromJson = TestUtil.getValueByjpath(responseJson, "/status");
	System.out.println("Response From Json is : " + responseFromJson);

	// 3.All Headers
	Header[] headersArray = closeableHttpResponse.getAllHeaders();
	HashMap<String, String> allHeaders = new HashMap<String, String>();

	for (Header header : headersArray) {
	allHeaders.put(header.getName(), header.getValue());

	}
	System.out.println("Headers Array-->" + allHeaders);

	}

	@Test(priority = 11)
	public void Estado_Certificado() throws ClientProtocolException, IOException {

	 CloseableHttpClient httpclient = HttpClients.createDefault();
	     HttpPost httppost = new HttpPost(Certificado);
	     System.out.println(httppost);
	     System.out.println(Certificado);
	     
	     String jsonpayload = "{\r\n"
	      + "    \"registerNumber\": 455749,\r\n"
	      + "    \"corpClass\": 15,\r\n"
	      + "    \"corpType\": 1,\r\n"
	      + "    \"jurisdiction\": 1,\r\n"
	      + "    \"IDEALId\": 1324234\r\n"
	      + "}";
	     
	     
	  StringEntity entity = new StringEntity(jsonpayload);
	  httppost.setEntity(entity);
	     
	         
	     httppost.setHeader("content-type", "application/json");
	   //httppost.setHeader("Authorization", "Bearer " + Token);

	     
	   
	     
	     CloseableHttpResponse closeableHttpResponse = httpclient.execute(httppost);
	// //1.GET status code
	int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
	System.out.println("Status code:" + statuscode);
	Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");

	// //2.Json String
	String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
	JSONObject responseJson = new JSONObject(responsestring);
	System.out.println("JSON response from API---->" + responseJson);

	// To get value from JSON Array
	String responseFromJson = TestUtil.getValueByjpath(responseJson, "/certificateOfGoodStandingBase64");
	System.out.println("Response From Json is : " + responseFromJson);

	// 3.All Headers
	Header[] headersArray = closeableHttpResponse.getAllHeaders();
	HashMap<String, String> allHeaders = new HashMap<String, String>();

	for (Header header : headersArray) {
	allHeaders.put(header.getName(), header.getValue());

	}
	System.out.println("Headers Array-->" + allHeaders);

	}
	
	@Test(priority = 12)
	public void Hacienda_corporate_ping() throws ClientProtocolException, IOException {

		restclient = new RestClient();
		String encoding = Base64.getEncoder().encodeToString(("ideal_dev:q?3:t>5bi1J~>6~").getBytes());
		HashMap<String, String> headermap = new HashMap<String, String>();
		//headermap.put("content-type", "application/json");
		headermap.put("Authorization", "Basic " + encoding);

		closeableHttpResponse = restclient.get(ping, headermap);

		// 1.GET status code
		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code:" + statuscode);
		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");

//		// 2.Json String
//		String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
//		JSONObject responseJson = new JSONObject(responsestring);
//		System.out.println("JSON response from API---->" + responseJson);

//		// To get value from JSON Array
//		String Responsefromjson = TestUtil.getValueByjpath(responseJson, "/status");
//		System.out.println("Resonse from json payload is: " + Responsefromjson);

		// 3.All Headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for (Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Headers Array-->" + allHeaders);

	}
	
	@Test(priority = 13)
	public void Hacienda_corporate_compliance() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		String encoding = Base64.getEncoder().encodeToString(("ideal_dev:q?3:t>5bi1J~>6~").getBytes());
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("Content-Type", "application/json");
		headermap.put("Authorization", "Basic " + encoding);
		
		String usersJsonString = "{\r\n"
				+ "    \"idType\": \"2\",\r\n"
				+ "    \"id\": \"660962981\",\r\n"
				+ "    \"firstName\": \"\",\r\n"
				+ "    \"lastName\": \"\",\r\n"
				+ "    \"secondLastName\": \"\",\r\n"
				+ "    \"businessName\": \"Code Dog Technology Group LLC\",\r\n"
				+ "    \"merchantId\": \"\"\r\n"
				+ "}";

		closeableHttpResponse = restclient.post(compliance, usersJsonString, headermap);

//			//1.GET status code
		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code:" + statuscode);
		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_400, "status code is not 400");

//			//2.Json String
		String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responsestring);
		System.out.println("JSON response from API---->" + responseJson);

		// To get value from JSON Array
		String responseFromJson = TestUtil.getValueByjpath(responseJson, "/successful");
		System.out.println("Response From Json is : " + responseFromJson);
//		    			
//			//3.All Headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for (Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());

		}
		System.out.println("Headers Array-->" + allHeaders);

	}
	
	@Test(priority = 14)
	public void Hacienda_corporate_authorize() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		String encoding = Base64.getEncoder().encodeToString(("ideal_dev:q?3:t>5bi1J~>6~").getBytes());
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("Content-Type", "application/json");
		headermap.put("Authorization", "Basic " + encoding);
		
		String usersJsonString = "{\r\n"
				+ "   \"idType\": \"2\",\r\n"
				+ "   \"id\": \"660962981\",\r\n"
				+ "   \"firstName\": \"\",\r\n"
				+ "   \"lastName\": \"\",\r\n"
				+ "   \"secondLastName\": \"\",\r\n"
				+ "   \"businessName\": \"Code Dog Technology Group LLC\",\r\n"
				+ "   \"email\": \"dpolanco@prits.pr.gov\",\r\n"
				+ "   \"agency\": \"PRITS\" \r\n"
				+ "}";

		closeableHttpResponse = restclient.post(authorize, usersJsonString, headermap);

//			//1.GET status code
		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code:" + statuscode);
		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");

//			//2.Json String
		String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responsestring);
		System.out.println("JSON response from API---->" + responseJson);

		// To get value from JSON Array
		String responseFromJson = TestUtil.getValueByjpath(responseJson, "/Successful");
		System.out.println("Response From Json is : " + responseFromJson);
//		    			
//			//3.All Headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for (Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());

		}
		System.out.println("Headers Array-->" + allHeaders);

	}

	@Test(priority = 15)
	public void Hacienda_Corporate_complianceDocuments() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		String encoding = Base64.getEncoder().encodeToString(("ideal_dev:q?3:t>5bi1J~>6~").getBytes());
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("Content-Type", "application/json");
		headermap.put("Authorization", "Basic " + encoding);
		
		String usersJsonString = "{\r\n"
				+ "    \"idType\": \"2\",\r\n"
				+ "    \"id\": \"016825647\",\r\n"
				+ "    \"firstName\": \"\",\r\n"
				+ "    \"lastName\": \"\",\r\n"
				+ "    \"secondLastName\": \"\",\r\n"
				+ "    \"businessName\": \"IBANEZ HELI-SKI\",\r\n"
				+ "    \"merchantId\": \"\",\r\n"
				+ "    \"filingCertificate\": true,\r\n"
				+ "    \"filingCertificateSUT\": true,\r\n"
				+ "    \"debtCertificate\": true,\r\n"
				+ "    \"merchantCertificate\": true,\r\n"
				+ "    \"waiver\": true,\r\n"
				+ "    \"requestorEmail\": \"testuser1\"\r\n"
				+ "}";

		closeableHttpResponse = restclient.post(complianceDocuments, usersJsonString, headermap);

//			//1.GET status code
		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code:" + statuscode);
		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");

//			//2.Json String
		String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responsestring);
		System.out.println("JSON response from API---->" + responseJson);

		// To get value from JSON Array
		String responseFromJson = TestUtil.getValueByjpath(responseJson, "/successful");
		System.out.println("Response From Json is : " + responseFromJson);
//		    			
//			//3.All Headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for (Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());

		}
		System.out.println("Headers Array-->" + allHeaders);

	}
	
	@Test(priority = 16)
	public void Hacienda_individual_ping() throws ClientProtocolException, IOException {

		restclient = new RestClient();
		String encoding = Base64.getEncoder().encodeToString(("ideal_dev:q?3:t>5bi1J~>6~").getBytes());
		HashMap<String, String> headermap = new HashMap<String, String>();
		//headermap.put("content-type", "application/json");
		headermap.put("Authorization", "Basic " + encoding);

		closeableHttpResponse = restclient.get(ping_ind, headermap);

		// 1.GET status code
		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code:" + statuscode);
		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");

//		// 2.Json String
//		String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
//		JSONObject responseJson = new JSONObject(responsestring);
//		System.out.println("JSON response from API---->" + responseJson);

//		// To get value from JSON Array
//		String Responsefromjson = TestUtil.getValueByjpath(responseJson, "/status");
//		System.out.println("Resonse from json payload is: " + Responsefromjson);

		// 3.All Headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for (Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Headers Array-->" + allHeaders);

	}
	
	@Test(enabled = false)
	public void Hacienda_compliance_individual() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		String encoding = Base64.getEncoder().encodeToString(("ideal_dev:q?3:t>5bi1J~>6~").getBytes());
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("Content-Type", "application/json");
		headermap.put("Authorization", "Basic " + encoding);
		
		String usersJsonString = "{\r\n"
				+ "   \"idType\": \"1\",\r\n"
				+ "   \"id\": \"598408444\",\r\n"
				+ "   \"firstName\": \"OSCAR\",\r\n"
				+ "   \"lastName\": \"RIVERA\",\r\n"
				+ "   \"secondLastName\": \"\",\r\n"
				+ "   \"businessName\":\"\",\r\n"
				+ "    \"merchantId\": \"05578230020\"\r\n"
				+ "}";

		closeableHttpResponse = restclient.post(compliance_ind, usersJsonString, headermap);

//			//1.GET status code
		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code:" + statuscode);
		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_400, "status code is not 400");

//			//2.Json String
		String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responsestring);
		System.out.println("JSON response from API---->" + responseJson);

		// To get value from JSON Array
		String responseFromJson = TestUtil.getValueByjpath(responseJson, "/successful");
		System.out.println("Response From Json is : " + responseFromJson);
//		    			
//			//3.All Headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for (Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());

		}
		System.out.println("Headers Array-->" + allHeaders);

	}
	
	@Test(priority = 17)
	public void Hacienda_authorize_individual() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		String encoding = Base64.getEncoder().encodeToString(("ideal_dev:q?3:t>5bi1J~>6~").getBytes());
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("Content-Type", "application/json");
		headermap.put("Authorization", "Basic " + encoding);
		
		String usersJsonString = "{\r\n"
				+ "   \"idType\": \"1\",\r\n"
				+ "   \"id\": \"598408444\",\r\n"
				+ "   \"firstName\": \"OSCAR\",\r\n"
				+ "   \"lastName\": \"RIVERA\",\r\n"
				+ "   \"secondLastName\": \"\",\r\n"
				+ "   \"businessName\":\"\",\r\n"
				+ "   \"email\": \"dpolanco@prits.pr.gov\",\r\n"
				+ "   \"agency\": \"PRITS\" \r\n"
				+ "}";

		closeableHttpResponse = restclient.post(authorize_ind, usersJsonString, headermap);

//			//1.GET status code
		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code:" + statuscode);
		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");

//			//2.Json String
		String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responsestring);
		System.out.println("JSON response from API---->" + responseJson);

		// To get value from JSON Array
		String responseFromJson = TestUtil.getValueByjpath(responseJson, "/Successful");
		System.out.println("Response From Json is : " + responseFromJson);
//		    			
//			//3.All Headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for (Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());

		}
		System.out.println("Headers Array-->" + allHeaders);

	}
	
	@Test(priority = 18)
	public void Hacienda_complianceDocuments_ind() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		String encoding = Base64.getEncoder().encodeToString(("ideal_dev:q?3:t>5bi1J~>6~").getBytes());
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("Content-Type", "application/json");
		headermap.put("Authorization", "Basic " + encoding);
		
		String usersJsonString = "{\r\n"
				+ "    \"idType\": \"1\",\r\n"
				+ "    \"id\": \"598408444\",\r\n"
				+ "    \"firstName\": \"oscar\",\r\n"
				+ "    \"lastName\": \"rivera\",\r\n"
				+ "    \"secondLastName\": \"\",\r\n"
				+ "    \"businessName\": \"\",\r\n"
				+ "    \"merchantId\": \"\",\r\n"
				+ "    \"filingCertificate\": true,\r\n"
				+ "    \"filingCertificateSUT\": true,\r\n"
				+ "    \"debtCertificate\": true,\r\n"
				+ "    \"merchantCertificate\": true,\r\n"
				+ "    \"waiver\": true,\r\n"
				+ "    \"requestorEmail\": \"dpolanco@prits.pr.gov\"\r\n"
				+ "}";

		closeableHttpResponse = restclient.post(complianceDocuments_ind, usersJsonString, headermap);

//			//1.GET status code
		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code:" + statuscode);
		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");

//			//2.Json String
		String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responsestring);
		System.out.println("JSON response from API---->" + responseJson);

		// To get value from JSON Array
		String responseFromJson = TestUtil.getValueByjpath(responseJson, "/successful");
		System.out.println("Response From Json is : " + responseFromJson);
//		    			
//			//3.All Headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for (Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());

		}
		System.out.println("Headers Array-->" + allHeaders);

	}
	
	@Test(priority = 19)
	public void PostAPITest_cap_request() throws ClientProtocolException, IOException {

		String encoding = Base64.getEncoder().encodeToString(("prgov:PrG0v!T3St").getBytes());
		HashMap<String, String> headermap = new HashMap<String, String>();
		//headermap.put("content-type", "application/json");
		headermap.put("Authorization", "Basic " + encoding);
		
		CloseableHttpClient httpclient =  getCloseableHttpClient();
		HttpGet httpget = new HttpGet(caprequest);  //http get request
		for(Map.Entry<String, String> entry : headermap.entrySet()) {
			httpget.addHeader(entry.getKey(), entry.getValue());
		}
		
		CloseableHttpResponse closeableHttpResponse = httpclient.execute(httpget);

		// 1.GET status code
		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code:" + statuscode);
		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");

//		// 2.Json String
		String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responsestring);
		System.out.println("JSON response from API---->" + responseJson);

//		// To get value from JSON Array
		String Responsefromjson = TestUtil.getValueByjpath(responseJson, "/tx_id");
		System.out.println("tx_id is: " + Responsefromjson);

		// 3.All Headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for (Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Headers Array-->" + allHeaders);

	}
	
	
	public static CloseableHttpClient getCloseableHttpClient()
	{
	  CloseableHttpClient httpClient = null;
	  try {
	    httpClient = HttpClients.custom().
	        setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).
	            setSSLContext(new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy()
	            {
	                public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException
	                {
	                    return true;
	                }
	            }).build()).build();
	  } catch (KeyManagementException e) {
	   
	  } catch (NoSuchAlgorithmException e) {
	  
	  } catch (KeyStoreException e) {
	  
	  }
	  return httpClient;
	}
	
	@Test(priority = 20)
	public void PostAPITest_Retrieve() throws ClientProtocolException, IOException {
		
		String encoding = Base64.getEncoder().encodeToString(("prgov:PrG0v!T3St").getBytes());
		HashMap<String, String> headermap = new HashMap<String, String>();
		//headermap.put("content-type", "application/json");
		headermap.put("Authorization", "Basic " + encoding);
		
		CloseableHttpClient httpclient =  getCloseableHttpClient();
		HttpGet httpget = new HttpGet(Retrieve);  //http get request
		System.out.println("httpget:" + httpget);
		for(Map.Entry<String, String> entry : headermap.entrySet()) {
			httpget.addHeader(entry.getKey(), entry.getValue());
		}
		
		CloseableHttpResponse closeableHttpResponse = httpclient.execute(httpget);
		System.out.println("closeableHttpResponse:" + closeableHttpResponse);

		// 1.GET status code
		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code:" + statuscode);
		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");

//		// 2.Json String
		String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responsestring);
		System.out.println("JSON response from API---->" + responseJson);

//		// To get value from JSON Array
		String Responsefromjson = TestUtil.getValueByjpath(responseJson, "/generated_date");
		System.out.println("generated_date is: " + Responsefromjson);

		// 3.All Headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for (Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Headers Array-->" + allHeaders);

	}
	

	@Test(priority = 21)
	public void PostAPITest_Justicia_Validate() throws ClientProtocolException, IOException {
		
		String encoding = Base64.getEncoder().encodeToString(("prgov:PrG0v!T3St").getBytes());
		HashMap<String, String> headermap = new HashMap<String, String>();
		//headermap.put("content-type", "application/json");
		headermap.put("Authorization", "Basic " + encoding);
		
		CloseableHttpClient httpclient =  getCloseableHttpClient();
		HttpGet httpget = new HttpGet(Validate);  //http get request
		System.out.println("httpget:" + httpget);
		for(Map.Entry<String, String> entry : headermap.entrySet()) {
			httpget.addHeader(entry.getKey(), entry.getValue());
		}
		
		CloseableHttpResponse closeableHttpResponse = httpclient.execute(httpget);
		System.out.println("closeableHttpResponse:" + closeableHttpResponse);

		// 1.GET status code
		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code:" + statuscode);
		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");

//		// 2.Json String
		String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responsestring);
		System.out.println("JSON response from API---->" + responseJson);

//		// To get value from JSON Array
		String Responsefromjson = TestUtil.getValueByjpath(responseJson, "/generated_date");
		System.out.println("generated_date is: " + Responsefromjson);

		// 3.All Headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for (Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Headers Array-->" + allHeaders);

	}
	

	@Test(priority = 22)
	public void PostAPITest_Justicia_CriminalRecord_CheckInquiry() throws ClientProtocolException, IOException {
		
		restclient = new RestClient();
		String encoding = Base64.getEncoder().encodeToString(("test:ch3ckC@d").getBytes());
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("content-type", "application/json");
		headermap.put("Authorization", "Basic " + encoding);
		System.out.println("closeableHttpResponse:" + restclient);

		closeableHttpResponse = restclient.get(Justicia_CriminalRecord, headermap);
		System.out.println("closeableHttpResponse:" + closeableHttpResponse);

		// 1.GET status code
		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code:" + statuscode);
		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");

		

//		// 2.Json String
//		String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
//		JSONObject responseJson = new JSONObject(responsestring);
//		System.out.println("JSON response from API---->" + responseJson);

//		// To get value from JSON Array
//		String Responsefromjson = TestUtil.getValueByjpath(responseJson, "/generated_date");
//		System.out.println("generated_date is: " + Responsefromjson);

		// 3.All Headers
//		Header[] headersArray = closeableHttpResponse.getAllHeaders();
//		HashMap<String, String> allHeaders = new HashMap<String, String>();

//		for (Header header : headersArray) {
//			allHeaders.put(header.getName(), header.getValue());
//		}
//		System.out.println("Headers Array-->" + allHeaders);

	}
	
	@Test(enabled = false)
	public void PostAPITest_Validate() throws ClientProtocolException, IOException {

		restclient = new RestClient();
		String encoding = Base64.getEncoder().encodeToString(("prgov:PrG0v!T3St").getBytes());
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("content-type", "application/json");
		headermap.put("Authorization", "Basic " + encoding);

		closeableHttpResponse = restclient.get(Validate, headermap);

		// 1.GET status code
		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code:" + statuscode);
		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");

//		// 2.Json String
//		String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
//		JSONObject responseJson = new JSONObject(responsestring);
//		System.out.println("JSON response from API---->" + responseJson);

//		// To get value from JSON Array
//		String Responsefromjson = TestUtil.getValueByjpath(responseJson, "/status");
//		System.out.println("Resonse from json payload is: " + Responsefromjson);

		// 3.All Headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for (Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Headers Array-->" + allHeaders);

	}
	
	@Test(priority = 24)
	public void Crim360_Authenticate() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("content-type", "application/json");
		
		String usersJsonString = "{\r\n"
				+ "    \"Username\": \"pritstest\",\r\n"
				+ "    \"Password\": \"9r175p@55w0rd\"\r\n"
				+ "}";

		

		closeableHttpResponse = restclient.post(crimauthenticate, usersJsonString, headermap);

//			//1.GET status code
		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code:" + statuscode);
		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");

//			//2.Json String
		String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responsestring);
		System.out.println("JSON response from API---->" + responseJson);

		// to validate
//			Users userobj = mapper.readValue(responsestring, Users.class);
//			System.out.println(userobj);

//			//To get value from JSON Array
		Token = TestUtil.getValueByjpath(responseJson, "/Token");
		System.out.println("Token is : " + Token);
//		    			
//			//3.All Headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for (Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());

		}
		System.out.println("Headers Array-->" + allHeaders);

	}
	
	
	@Test(priority = 25)
	public void CRIM360_Certification() throws ClientProtocolException, IOException {

		restclient = new RestClient();
//		var client = HttpClient.newHttpClient();
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("content-type", "application/json");
		headermap.put("Authorization", "Bearer " + Token);

		String JsonPayload = "{\r\n"
				+ " \"CertificationTypeCode\": \"1\",\r\n"
				+ " \"RequesterIdentificationNumber\": \"454-33-3232\",\r\n"
				+ " \"FullName\": \"Juan Del Pueblo\",\r\n"
				+ " \"CertificationRequestPaymentTypeCode\": \"2\",\r\n"
				+ " \"SourceCode\" : \"5\",\r\n"
				+ " \"Quantity\": \"1\"\r\n"
				+ "}\r\n"
				+ "";
		
		
		closeableHttpResponse = restclient.post(crimcertificate, JsonPayload, headermap);

//			//1.GET status code
		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code:" + statuscode);
		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");

//			//2.Json String
		String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responsestring);
		System.out.println("JSON response from API---->" + responseJson);

		// To get value from JSON Array
		FileContentId = TestUtil.getValueByjpath(responseJson, "/FileContentId");
		System.out.println("FileContentId is : " + FileContentId);

		// 3.All Headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for (Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());

		}
		System.out.println("Headers Array-->" + allHeaders);

	}
	
	@Test(priority = 26)
	public void CRIM360_Crimcompliance() throws ClientProtocolException, IOException {

	
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String JsonPayload = "{\r\n"
				+ "    \"id\": \"58897953\"\r\n"
				+ "}";
		
		HttpUriRequest request = RequestBuilder.get(crimcompliance)
		        .setEntity(new StringEntity(JsonPayload))
		        .setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
		        .setHeader("Authorization", "Bearer " + Token)
		        .build();
		
		
		CloseableHttpResponse closeableHttpResponse = httpclient.execute(request);
		
//		 var response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
//         System.out.println(response.body());

//		closeableHttpResponse = restclient.post(crimcompliance, JsonPayload, headermap);

//			//1.GET status code
		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code:" + statuscode);
		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");
//
////			//2.Json String
//		String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
//		JSONObject responseJson = new JSONObject(responsestring);
//		System.out.println("JSON response from API---->" + responseJson);
//
//		// To get value from JSON Array
//		String responseFromJson = TestUtil.getValueByjpath(responseJson, "/debtCompliance");
//		System.out.println("Response From Json is : " + responseFromJson);
//
//		// 3.All Headers
//		Header[] headersArray = closeableHttpResponse.getAllHeaders();
//		HashMap<String, String> allHeaders = new HashMap<String, String>();
//
//		for (Header header : headersArray) {
//			allHeaders.put(header.getName(), header.getValue());
//
//		}
//		System.out.println("Headers Array-->" + allHeaders);

	}
	
	
	
	@Test(priority = 27)
	public void CRIM360_FileContent() throws ClientProtocolException, IOException {

		restclient = new RestClient();
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("Authorization", "Bearer " + Token);
		headermap.put("content-type", "application/json");

		String JsonPayload = "{\r\n"
				+ "\"FileContentId\":"+FileContentId+"\r\n"
				+ "}";
		System.out.println("JsonPayload" + JsonPayload);
		closeableHttpResponse = restclient.post(crimfilecontent, JsonPayload, headermap);

//			//1.GET status code
		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code:" + statuscode);
		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");

//			//2.Json String
		String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responsestring);
		System.out.println("JSON response from API---->" + responseJson);

		// To get value from JSON Array
		FileContent = TestUtil.getValueByjpath(responseJson, "/FileContent");
		System.out.println("FileContentId is : " + FileContent);

		// 3.All Headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for (Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());

		}
		System.out.println("Headers Array-->" + allHeaders);

	}
	
	
	
	@Test(priority = 28)
	 public void DTOP_token() throws ClientProtocolException, IOException {

		 String get_tocken = "https://auth.cescodigital.dtop.pr.gov/auth/realms/cescodigital/protocol/openid-connect/token";


		  List<NameValuePair> formData = new ArrayList<>();
		 //params.add(new BasicNameValuePair("grant_type", "password"));
		  //params.add(new BasicNameValuePair("username", "dToplicenciaS"));
		  //params.add(new BasicNameValuePair("password", "aldf@23901FTOPasd23"));
		  //params.add(new BasicNameValuePair("client_id", "OGP20180821&*990899XX"));
		  //params.add(new BasicNameValuePair("client_secret", "5&t%$xZHpr9800"));
		  formData.add(new BasicNameValuePair("grant_type", "client_credentials"));
		 formData.add(new BasicNameValuePair("scope", "roles"));
		 formData.add(new BasicNameValuePair("client_id", "app1"));
		 formData.add(new BasicNameValuePair("client_secret", "2d8fa992-09b1-4795-bf31-ef60de70abe7"));
		 //formData.add(new BasicNameValuePair("Client Authentication", "Send as Basic Auth header"));
		 formData.add(new BasicNameValuePair("CLIENT_AUTHENTICATION", "Send client credentials in body"));
		  // add to request
		  
		  CloseableHttpClient httpclient = HttpClients.createDefault();
		      HttpPost httppost = new HttpPost(get_tocken);  //post request
		      System.out.println(httppost);
		  httppost.setEntity(new UrlEncodedFormEntity(formData)); //for payload
		 CloseableHttpResponse closeableHttpResponse = httpclient.execute(httppost);
		  


		 // //1.GET status code
		 int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		 System.out.println("Status code:" + statuscode);
		 Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");

		 // //2.Json String
		 String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		 JSONObject responseJson = new JSONObject(responsestring);
		 System.out.println("JSON response from API---->" + responseJson);

		 // to validatef
		 // Users userobj = mapper.readValue(responsestring, Users.class);
		 // System.out.println(userobj);

		 // //To get value from JSON Array
		 DTOP_tocken = TestUtil.getValueByjpath(responseJson, "/access_token");
		 System.out.println("Token is : " + DTOP_tocken);
		 //    
		 // //3.All Headers
		 Header[] headersArray = closeableHttpResponse.getAllHeaders();
		 HashMap<String, String> allHeaders = new HashMap<String, String>();

		 for (Header header : headersArray) {
		 allHeaders.put(header.getName(), header.getValue());

		 }
		 System.out.println("Headers Array-->" + allHeaders);
		 

		 }

		 @Test (priority = 29)
		   public void get_DTOPGetPhoto() throws ClientProtocolException, IOException
		 {
//		 Agency b1 =  new Agency();
		 String DTOP_GetPhoto = "https://ideal.cescodigital.dtop.pr.gov/api/photo/4970687";


		 restclient = new RestClient();
		 HashMap<String, String> headermap = new HashMap<String, String>();
		 //headermap.put("content-type", "application/json");
		 //headermap.put("referenceId", "d286928c-9cd9-4b40-af4b-631d07312b0c");
		 headermap.put("Authorization", "Bearer " + DTOP_tocken);
		 closeableHttpResponse = restclient.get(DTOP_GetPhoto,headermap);

		 //1.GET status code
		 int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		 System.out.println("Status code:" +statuscode);
		 Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");


		 }

		 @Test (enabled = false)
		   public void get_GetLicense() throws ClientProtocolException, IOException
		 {
//		 Agency b1 =  new Agency();
		 String DTOP_GetLicense = "https://ideal.cescodigital.dtop.pr.gov/api/license/4970687";


		 restclient = new RestClient();
		 HashMap<String, String> headermap = new HashMap<String, String>();
		 //headermap.put("content-type", "application/json");
		 //headermap.put("referenceId", "d286928c-9cd9-4b40-af4b-631d07312b0c");
		 headermap.put("Authorization", "Bearer " + DTOP_tocken);
		 closeableHttpResponse = restclient.get(DTOP_GetLicense,headermap);

		 //1.GET status code
		 int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		 System.out.println("Status code:" +statuscode);
		 Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");

		 //2.Json String
		 String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

		 JSONObject responseJson = new JSONObject(responsestring);
		 System.out.println("JSON response from DTOP License API---->" +responseJson);

		 //To get value from JSON Array
		 String id = TestUtil.getValueByjpath(responseJson, "/license/category");
		 System.out.println(id);
		    
		 //3.All Headers
		 Header[] headersArray = closeableHttpResponse.getAllHeaders();
		 HashMap<String,String> allHeaders = new HashMap<String,String>();

		 for(Header header : headersArray)
		 {
		  allHeaders.put(header.getName(), header.getValue());

		 }


		 }

}