package net.argus.api.freebox.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import net.argus.api.freebox.FbxPackage;
import net.argus.api.freebox.FreeboxProperties;
import net.argus.util.StringManager;


public class FbxIO {
	
	private static boolean init = false;
	
	public static FbxRequestReturn sendPOST(String path, Map<String, String> properties, FbxPackage value, FreeboxProperties fbxProperties) throws IOException {
		URL url = getURL(path, fbxProperties);
		return sendPOST(url, properties, value);
	}
	
	public static FbxRequestReturn sendGET(String path, Map<String, String> properties, FreeboxProperties fbxProperties) throws IOException {
		URL url = getURL(path, fbxProperties);
		return sendGET(url, properties);
	}
	
	public static URL getURL(String path, FreeboxProperties fbxProperties) throws MalformedURLException {
		String version = fbxProperties.getApiVersion();
		version = version.substring(0, version.indexOf('.'));
		URL url = null;

		if(fbxProperties.isHttpsAvailable())
			url = new URL("https://" + fbxProperties.getApiDomain() + ":" + fbxProperties.getHttpsPort() + fbxProperties.getApiBaseUrl() + "v" + version + (!path.startsWith("/")?"/":"") + path);
		else
			url = new URL("http://mafreebox.freebox.fr" + fbxProperties.getApiBaseUrl() + "v" + version + (!path.startsWith("/")?"/":"") + path);
		return url;
	}
	
	public static FbxRequestReturn sendGET(URL url, Map<String, String> properties) throws IOException {
		HttpURLConnection  con = openConnection(url);
		
		con.setRequestMethod("GET");
		if(properties != null)
			for(Entry<String, String> entry : properties.entrySet())
				con.addRequestProperty(entry.getKey(), entry.getValue());
			
		return new FbxRequestReturn(convertStreamToString(con.getInputStream()));
	}
	
	public static FbxRequestReturn sendPOST(URL url, Map<String, String> properties, FbxPackage value) throws IOException {
		HttpURLConnection  con = openConnection(url);
		
		con.setRequestMethod("POST");
		con.setDoOutput(true);

		byte[] out = value.toString().getBytes(StandardCharsets.UTF_8);
		int length = out.length;

		con.setFixedLengthStreamingMode(length);
		con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		con.connect();
		try(OutputStream os = con.getOutputStream()) {
		    os.write(out);
		}
		
		return new FbxRequestReturn(convertStreamToString(con.getInputStream()));
	}
	
	private static String convertStreamToString(InputStream is) {
	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    StringBuilder sb = new StringBuilder();

	    String line = null;
	    try {
	        while ((line = reader.readLine()) != null) {
	            sb.append(line + "\n");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            is.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	   
	    return  StringManager.remplace(sb.toString(), "\\/", "");
	}
	
	private static HttpURLConnection  openConnection(URL url) throws IOException {
		if(url.getProtocol().toUpperCase().equals("HTTP"))
			return openHttpUrlConnection(url);
		else if(url.getProtocol().toUpperCase().equals("HTTPS"))
			return openHttpsURLConnection(url);
		else
			return null;
	}
	
	private static HttpURLConnection openHttpUrlConnection(URL url) throws IOException {
		if(!url.getProtocol().toUpperCase().equals("HTTP"))
			return null;
		
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		return con;
	}
	
	private static HttpsURLConnection openHttpsURLConnection(URL url) throws IOException {
		if(!url.getProtocol().toUpperCase().equals("HTTPS"))
			return null;
		initiate();
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
		
		return con;
	}

	public static void initiate() {
		if(init)
			return;
		
		SSLContext sc;
		try {
			TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
				
				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
				
				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}
				
				@Override
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {					
				}
			}};
			sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			HostnameVerifier allHostsValid = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			};
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		} catch (Exception e) {}
		
		init = true;
		
	}

}
