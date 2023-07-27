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

public class HaciendaAgencyAPI_Prod extends TestBase implements IExecutionListener {

	TestBase testbase;
	RestClient restclient;
	String serviceurl;
	String apiurl;
	String url;
	String Token,Fondo_Token, citizen_referenceId, corporate_referenceId,get_token,DTOP_tocken;
	String api_test_token,justicia_url,caprequest,Retrieve,Validate;
	String Patrono_Certificate,CFSE_serviceurl,api_CFSEtest_toke,InformationDDeuda,CertificateDDeuda,CertificateDVegencia,FondoPing,Compliance,EstadoPing,Certificado,api_CFSEtest_token;
	String Individuo_Certificate,Hacienda_income,serviceurl_hacienda,ping,compliance,authorize,complianceDocuments,serviceurl_Ind_hacienda,ping_ind,compliance_ind,authorize_ind,complianceDocuments_ind;
	String crimurl,crimauthenticate,crimcertificate,crimcompliance,crimfilecontent,FileContentId,FileContent,DTOP_License,Prod_Justicia_CriminalRecord,Justicia_CriminalRecord;
	CloseableHttpResponse closeableHttpResponse;
	

	@BeforeMethod

	public void setup() throws ClientProtocolException, IOException {
		testbase = new TestBase();
		serviceurl = prop.getProperty("ProdURL");
		api_test_token = prop.getProperty("Prod_api_test_token");
		api_test_token = serviceurl + api_test_token;
		Hacienda_income = prop.getProperty("Prod_hacienda_income");
		Hacienda_income = serviceurl + Hacienda_income;
		
		Individuo_Certificate = prop.getProperty("Prod_Individuo_Certificate");
		Individuo_Certificate = serviceurl + Individuo_Certificate;
		serviceurl_hacienda = prop.getProperty("Prod_HACIENDAURL");
		Hacienda_income = prop.getProperty("Prod_hacienda_income");
		Hacienda_income = serviceurl_hacienda + Hacienda_income;
		ping = prop.getProperty("Prod_ping");
		ping = serviceurl_hacienda + ping;
		serviceurl_Ind_hacienda = prop.getProperty("Prod_hacieda_ind_url");
		ping_ind = prop.getProperty("Prod_ping");
		ping_ind = serviceurl_Ind_hacienda + ping_ind;
		compliance = prop.getProperty("Prod_compliance");
		compliance = serviceurl_hacienda + compliance;
		compliance_ind = serviceurl_Ind_hacienda + compliance;
		authorize = prop.getProperty("Prod_authorize");
		authorize = serviceurl_hacienda + authorize;
		authorize_ind = prop.getProperty("Prod_authorize");
		authorize_ind = serviceurl_Ind_hacienda + authorize_ind; 
		complianceDocuments = prop.getProperty("Prod_complianceDocuments");
		complianceDocuments = serviceurl_hacienda + complianceDocuments;
		complianceDocuments_ind = prop.getProperty("Prod_complianceDocuments");
		complianceDocuments_ind = serviceurl_Ind_hacienda + complianceDocuments_ind;
		justicia_url = prop.getProperty("Prod_justicia_url");
		caprequest = prop.getProperty("Prod_caprequest");
		caprequest = justicia_url + caprequest;
		Retrieve = prop.getProperty("Prod_Retrieve");
		Retrieve = justicia_url + Retrieve;
		Validate = prop.getProperty("Prod_Validate");
		Validate = justicia_url + Validate;
		Prod_Justicia_CriminalRecord = prop.getProperty("Prod_Justicia_CriminalRecord");
		Justicia_CriminalRecord = Prod_Justicia_CriminalRecord;
		DTOP_License = prop.getProperty("Prod_DTOP_License");
		Patrono_Certificate = prop.getProperty("Prod_Patrono_Certificate");
		Patrono_Certificate = serviceurl + Patrono_Certificate;
		CFSE_serviceurl = prop.getProperty("Prod_URL3");
		api_CFSEtest_token = prop.getProperty("Prod_api_CFSEtest_token");
		api_CFSEtest_token = CFSE_serviceurl + api_CFSEtest_token;
		InformationDDeuda = prop.getProperty("Prod_InformationDDeuda");
		InformationDDeuda = CFSE_serviceurl + InformationDDeuda;
		CertificateDDeuda = prop.getProperty("Prod_CertificateDDeuda");
		CertificateDDeuda = CFSE_serviceurl + CertificateDDeuda;
		CertificateDVegencia = prop.getProperty("Prod_CertificateDVegencia");
		CertificateDVegencia = CFSE_serviceurl + CertificateDVegencia;
		FondoPing = prop.getProperty("Prod_FondoPing");
		FondoPing = CFSE_serviceurl + FondoPing;
		
	}

		
		
	@Test(priority = 1)
	public void Hacienda_corporate_ping() throws ClientProtocolException, IOException {

		restclient = new RestClient();
		String encoding = Base64.getEncoder().encodeToString(("prits_prod:&x(De$Ar!d3F]1w").getBytes());
		HashMap<String, String> headermap = new HashMap<String, String>();
		//headermap.put("content-type", "application/json");
		headermap.put("Authorization", "Basic " + encoding);
		
		         
		  List<NameValuePair> formData = new ArrayList<>();
		  formData.add(new BasicNameValuePair("grant_type", "password"));
		  formData.add(new BasicNameValuePair("username", "dToplicenciaS"));
		  formData.add(new BasicNameValuePair("password", "aldf@23901FTOPasd23"));
		  formData.add(new BasicNameValuePair("client_id", "OGP20180821&*990899XX"));
		  formData.add(new BasicNameValuePair("client_secret", "5&t%$xZHpr9800"));
////		  formData.add(new BasicNameValuePair("grant_type", "client_credentials"));
//		 formData.add(new BasicNameValuePair("scope", "roles"));
//		 formData.add(new BasicNameValuePair("Client_ID", "app1"));
//		 formData.add(new BasicNameValuePair("Client_Secret", "2d8fa992-09b1-4795-bf31-ef60de70abe7"));
//		 formData.add(new BasicNameValuePair("Client Authentication", "Send as Basic Auth header"));
//		 formData.add(new BasicNameValuePair("CLIENT_AUTHENTICATION", "Send client credentials in body"));
		  // add to request
		  
 	  CloseableHttpClient httpclient = HttpClients.createDefault();
		      HttpPost httppost = new HttpPost(api_test_token);  //post request
		      System.out.println(httppost);
		  httppost.setEntity(new UrlEncodedFormEntity(formData)); //for payload
		 CloseableHttpResponse closeableHttpResponse = httpclient.execute(httppost);

//		closeableHttpResponse = restclient.get(api_test_token, headermap);

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
	
	
	@Test(priority = 2)
	public void Hacienda_corporate_authorize() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		String encoding = Base64.getEncoder().encodeToString(("prits_prod:&x(De$Ar!d3F]1w").getBytes());
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("Content-Type", "application/json");
		headermap.put("Authorization", "Basic " + encoding);
		
		String usersJsonString = "{\r\n"
				+ "   \"idType\": \"1\",\r\n"
				+ "    \"id\": \"333224444\",\r\n"
				+ "    \"firstName\": \"jon\",\r\n"
				+ "    \"lastName\": \"doe\",\r\n"
				+ "   \"secondLastName\": \"\",\r\n"
				+ "   \"businessName\":\"PRITS\",\r\n"
				+ "   \"email\": \"orivera@prits.pr.gov\",\r\n"
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
	
	@Test(priority = 3)
	public void Hacienda_corporate_compliance() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		String encoding = Base64.getEncoder().encodeToString(("prits_prod:&x(De$Ar!d3F]1w").getBytes());
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("Content-Type", "application/json");
		headermap.put("Authorization", "Basic " + encoding);
		
		String usersJsonString = "{\r\n"
				+ "    \"idType\": \"1\",\r\n"
				+ "    \"id\": \"333224444\",\r\n"
				+ "    \"firstName\": \"jon\",\r\n"
				+ "    \"lastName\": \"doe\",\r\n"
				+ "    \"secondLastName\": \"PRITS\",\r\n"
				+ "    \"businessName\": \"PRITS\",\r\n"
				+ "    \"merchantId\": \"\",\r\n"
				+ "    \"requestorEmail\":\"orivera@prits.pr.gov\"\r\n"
				+ "}";

		closeableHttpResponse = restclient.post(compliance, usersJsonString, headermap);

//			//1.GET status code
		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code:" + statuscode);
		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not ");

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
	
	
	
	
	@Test(priority = 4)
	public void Hacienda_Income() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		String encoding = Base64.getEncoder().encodeToString(("prits_prod:&x(De$Ar!d3F]1w").getBytes());
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("Content-Type", "application/json");
		headermap.put("Authorization", "Basic " + encoding);
		
		String usersJsonString = "{\r\n"
				+ "   \"idType\":\"1\",\r\n"
				+ "   \"id\":\"333224444\",\r\n"
				+ "   \"firstName\":\"jon\",\r\n"
				+ "   \"lastName\":\"doe\",\r\n"
				+ "   \"secondLastName\":\"\",\r\n"
				+ "   \"dateOfBirth\":\"1999-12-31\",\r\n"
				+ "   \"businessName\":\"\",\r\n"
				+ "   \"taxYear\":2018,\r\n"
				+ "   \"requestorEmail\":\"orivera@prits.pr.gov\"\r\n"
				+ "}";

		closeableHttpResponse = restclient.post(Hacienda_income, usersJsonString, headermap);

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

	@Test(priority = 5)
	public void Hacienda_Corporate_complianceDocuments() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		String encoding = Base64.getEncoder().encodeToString(("prits_prod:&x(De$Ar!d3F]1w").getBytes());
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("Content-Type", "application/json");
		headermap.put("Authorization", "Basic " + encoding);
		
		String usersJsonString = "{\r\n"
				+ "    \"idType\": \"1\",\r\n"
				+ "    \"id\": \"333224444\",\r\n"
				+ "    \"firstName\": \"jon\",\r\n"
				+ "    \"lastName\": \"doe\",\r\n"
				+ "    \"secondLastName\": \"\",\r\n"
				+ "    \"businessName\": \"\",\r\n"
				+ "    \"merchantId\": \"\",\r\n"
				+ "    \"filingCertificate\": true,\r\n"
				+ "    \"filingCertificateSUT\": true,\r\n"
				+ "    \"debtCertificate\": true,\r\n"
				+ "    \"merchantCertificate\": true,\r\n"
				+ "    \"waiver\": true,\r\n"
				+ "    \"requestorEmail\": \"orivera@prits.pr.gov\"\r\n"
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
	

}