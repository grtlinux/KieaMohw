package org.tain.working.hwDateFolder;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.utils.CipherUtils;
import org.tain.utils.CurrentInfo;
import org.tain.utils.StringTools;
import org.tain.working.hwInit.HwInitWorking;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class HwDateFolderWorking {

	@Autowired
	private HwInitWorking hwInitWorking;
	
	private String strInfoSignPath = null;
	private String strTodayDataPath = null;
	
	public void work() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			this.strInfoSignPath = this.hwInitWorking.getInfoSignPath();
			this.strTodayDataPath = this.hwInitWorking.getTodayDataPath();
		}
		
		if (Boolean.TRUE) this.createKeyPair();
		if (Boolean.TRUE) this.copyKeyPairFiles();
		
		if (Boolean.TRUE) this.createCopyOtk();
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private void createKeyPair() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		boolean bCreateKeyPair = false;
		if (Boolean.TRUE) {
			File file1 = new File(this.strInfoSignPath + File.separator + "HW_PriKey.bin");
			File file2 = new File(this.strInfoSignPath + File.separator + "HW_PubKey.bin");
			File file3 = new File(this.strInfoSignPath + File.separator + "HW_B64PriKey.bin");
			File file4 = new File(this.strInfoSignPath + File.separator + "HW_B64PubKey.bin");
			bCreateKeyPair = !file1.exists() || !file2.exists() || !file3.exists() || !file4.exists();
		}
		
		if (Boolean.TRUE && bCreateKeyPair) {
			KeyPair keyPair = CipherUtils.generateRSAKeyPair();
			PrivateKey priKey = keyPair.getPrivate();
			PublicKey pubKey = keyPair.getPublic();
			byte[] bPriKey = priKey.getEncoded();
			byte[] bPubKey = pubKey.getEncoded();
			StringTools.bytesToFile(bPriKey, this.strInfoSignPath + File.separator + "HW_PriKey.bin");
			StringTools.bytesToFile(bPubKey, this.strInfoSignPath + File.separator + "HW_PubKey.bin");
		}
		
		if (Boolean.TRUE && bCreateKeyPair) {
			byte[] bPriKey = StringTools.bytesFromFile(this.strInfoSignPath + File.separator + "HW_PriKey.bin");
			byte[] bPubKey = StringTools.bytesFromFile(this.strInfoSignPath + File.separator + "HW_PubKey.bin");
			byte[] bB64PriKey = Base64.getEncoder().encode(bPriKey);
			byte[] bB64PubKey = Base64.getEncoder().encode(bPubKey);
			StringTools.bytesToFile(bB64PriKey, this.strInfoSignPath + File.separator + "HW_B64PriKey.bin");
			StringTools.bytesToFile(bB64PubKey, this.strInfoSignPath + File.separator + "HW_B64PubKey.bin");
		}
	}
	
	private void copyKeyPairFiles() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			Files.copy(Paths.get(this.strInfoSignPath + File.separator + "HW_PriKey.bin")
					, Paths.get(this.strTodayDataPath + File.separator + "HW_PriKey.bin")
					, StandardCopyOption.REPLACE_EXISTING);
			Files.copy(Paths.get(this.strInfoSignPath + File.separator + "HW_PubKey.bin")
					, Paths.get(this.strTodayDataPath + File.separator + "HW_PubKey.bin")
					, StandardCopyOption.REPLACE_EXISTING);
			Files.copy(Paths.get(this.strInfoSignPath + File.separator + "HW_B64PriKey.bin")
					, Paths.get(this.strTodayDataPath + File.separator + "HW_B64PriKey.bin")
					, StandardCopyOption.REPLACE_EXISTING);
			Files.copy(Paths.get(this.strInfoSignPath + File.separator + "HW_B64PubKey.bin")
					, Paths.get(this.strTodayDataPath + File.separator + "HW_B64PubKey.bin")
					, StandardCopyOption.REPLACE_EXISTING);
		}
	}
	
	private void createCopyOtk() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		byte[] bOtk = new byte[16];
		if (Boolean.TRUE) {
			new Random(new Date().getTime()).nextBytes(bOtk);
			//bOtk = "20210513HW202135".getBytes();  // TODO-KANG20210513
			System.out.println(">>>>> create HW_Otk.bin <<<<<");
			StringTools.printHex(bOtk);
			StringTools.bytesToFile(bOtk, this.strInfoSignPath + File.separator + "HW_Otk.bin");
		}
		
		if (Boolean.TRUE) {
			/*
			///////////////////////////////////////////////////////////////////////
			// (7) 송신기관은 대칭키 암호화에 사용한 대칭키를 수신기관의 공개키로 암호화(RSA)
			byte[] bEncASKey = RSAEncrypt(MOremotePubKey, localASKey);

			// BASE64 인코딩
			byte[] b64EncASKey = Base64.getEncoder().encode(bEncASKey);
			*/
		}
		
		if (Boolean.TRUE) {
			Files.copy(Paths.get(this.strInfoSignPath + File.separator + "HW_Otk.bin")
					, Paths.get(this.strTodayDataPath + File.separator + "HW_Otk.bin")
					, StandardCopyOption.REPLACE_EXISTING);
		}
	}
}
