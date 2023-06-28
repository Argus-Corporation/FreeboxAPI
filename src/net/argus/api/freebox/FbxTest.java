package net.argus.api.freebox;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import net.argus.api.freebox.api.FbxAPI;


public class FbxTest {
	
	public static void main(String[] args) throws IOException, InvalidKeyException, SignatureException, NoSuchAlgorithmException {
		Freebox free = Freebox.getLocalFreebox();
		FreeboxApp app = new FreeboxApp("cardinal-freebox", "NUflefjAqvvjoCdG6+3RA4bdQA3kbf51YyU8qiV0gnxlRtvDnZBIq3k8a23awePa");
		FreeboxSession session = free.openSession(app);
		
		FbxAPI.setCallSession(free.getProperties(), session);
		
		System.out.println(FbxAPI.getCall().getVoiceMails());
		
		session.close();
	}

}
