package org.tain.working.step;

import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.properties.ProjEnvParamProperties;
import org.tain.utils.CipherUtils;
import org.tain.utils.CurrentInfo;
import org.tain.utils.StringTools;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Step00Job {

	@Autowired
	private ProjEnvParamProperties projEnvParamProperties;
	
	private String imsiKeyPath = null;
	private String workingPath = null;
	
	public void doing() throws Exception {
		log.info("KANG-20210405 {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			this.imsiKeyPath = this.projEnvParamProperties.getImsiKeyPath();
			this.workingPath = this.projEnvParamProperties.getWorkingPath();
			log.info("KANG-20210405 -----> imsiKeyPath. {}", this.imsiKeyPath);
			log.info("KANG-20210405 -----> workingPath. {}", this.workingPath);
		}
		
		if (Boolean.TRUE) doing_testPadding();
		
		if (!Boolean.TRUE) doing_HW_PriKey();
		
		if (!Boolean.TRUE) getPriKey01();
		if (!Boolean.TRUE) getPubKey01();
		
		if (!Boolean.TRUE) doingBase64DecodingOfHwPubKeyB64();
		if (!Boolean.TRUE) doingBase64DecodingOfHwPriKeyB64();
		
		log.info("");
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private void doing_testPadding() throws Exception {
		CipherUtils.testPadding();
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private void doing_HW_PriKey() throws Exception {
		log.info("KANG-20210405 {} {}", CurrentInfo.get());
		
		String binFile = null;
		String b64File = null;
		if (Boolean.TRUE) {
			binFile = this.imsiKeyPath + File.separator + "HW_PriKey.bin";
			b64File = this.imsiKeyPath + File.separator + "HW_PriKey.b64";
			log.info("KANG-20210405 -----> {}", binFile);
			log.info("KANG-20210405 -----> {}", b64File);
		}
		
		byte[] byteBin = null;
		@SuppressWarnings("unused")
		byte[] byteB64 = null;
		if (Boolean.TRUE) {
			byteBin = StringTools.bytesFromFile(binFile);
			byteB64 = Base64.getEncoder().encode(byteBin);
		}
		
		PrivateKey priKey = null;
		if (Boolean.TRUE) {
			//priKey = CipherUtils.getPrivateKeyFromBase64(byteB64);
			priKey = CipherUtils.getPrivateKeyFromBase64String(StringTools.stringFromFile(b64File));
		}
		
		String b64OtkFile = null;
		String encOtkFile = null;
		String binOtkFile = null;
		if (Boolean.TRUE) {
			b64OtkFile = this.imsiKeyPath + File.separator + "MO_OTK.b64";
			encOtkFile = this.imsiKeyPath + File.separator + "MO_OTK.enc";
			binOtkFile = this.imsiKeyPath + File.separator + "MO_OTK.bin";
			log.info("KANG-20210405 -----> {}", b64OtkFile);
			log.info("KANG-20210405 -----> {}", encOtkFile);
			log.info("KANG-20210405 -----> {}", binOtkFile);
		}
		
		byte[] byteB64Otk = null;
		byte[] byteEncOtk = null;
		byte[] byteBinOtk = null;
		if (Boolean.TRUE) {
			byteB64Otk = StringTools.bytesFromFile(b64OtkFile);
			byteEncOtk = Base64.getDecoder().decode(byteB64Otk);
			byteBinOtk = CipherUtils.decryptRSA(byteEncOtk, priKey);
			
			StringTools.printHex(byteBinOtk);
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private void getPriKey01() throws Exception {
		log.info("KANG-20210405 {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			String b64File = this.imsiKeyPath + File.separator + "HW_PriKey.b64";
			log.info("KANG-20210405 -----> {}", b64File);
			@SuppressWarnings("unused")
			PrivateKey priKey = null;
			try {
				priKey = CipherUtils.getPrivateKeyFromBase64(StringTools.bytesFromFile(b64File));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//Sleep.run(1 * 1000);
		
		if (Boolean.TRUE) {
			/*
			String b64File = this.imsiKeyPath + File.separator + "_privateKey.b64.txt";
			log.info("KANG-20210405 -----> {}", b64File);
			@SuppressWarnings("unused")
			PrivateKey priKey = null;
			try {
				priKey = CipherUtils.getPrivateKeyFromBase64(StringTools.bytesFromFile(b64File));
			} catch (Exception e) {
				e.printStackTrace();
			}
			*/
		}
		
		//Sleep.run(1 * 1000);
		
		if (Boolean.TRUE) {
			/*
			String b64File = this.workingPath + File.separator + "HW_PriKey.b64";
			log.info("KANG-20210405 -----> {}", b64File);
			@SuppressWarnings("unused")
			PrivateKey priKey = null;
			try {
				priKey = CipherUtils.getPrivateKeyFromBase64(StringTools.bytesFromFile(b64File));
			} catch (Exception e) {
				e.printStackTrace();
			}
			*/
		}
	}
	
	private void getPubKey01() throws Exception {
		log.info("KANG-20210405 {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			String b64File = this.imsiKeyPath + File.separator + "HW_PubKey.b64";
			log.info("KANG-20210405 -----> {}", b64File);
			@SuppressWarnings("unused")
			PublicKey pubKey = null;
			try {
				pubKey = CipherUtils.getPublicKeyFromBase64(StringTools.bytesFromFile(b64File));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//Sleep.run(1 * 1000);
		
		if (Boolean.TRUE) {
			/*
			String b64File = this.imsiKeyPath + File.separator + "MO_PubKey.b64";
			log.info("KANG-20210405 -----> {}", b64File);
			@SuppressWarnings("unused")
			PublicKey pubKey = null;
			try {
				pubKey = CipherUtils.getPublicKeyFromBase64(StringTools.bytesFromFile(b64File));
			} catch (Exception e) {
				e.printStackTrace();
			}
			*/
		}
		
		//Sleep.run(1 * 1000);
		
		if (Boolean.TRUE) {
			/*
			String b64File = this.imsiKeyPath + File.separator + "_publicKey.b64.txt";
			log.info("KANG-20210405 -----> {}", b64File);
			@SuppressWarnings("unused")
			PublicKey pubKey = null;
			try {
				pubKey = CipherUtils.getPublicKeyFromBase64(StringTools.bytesFromFile(b64File));
			} catch (Exception e) {
				e.printStackTrace();
			}
			*/
		}
	}
	
	private void doingBase64DecodingOfHwPubKeyB64() throws Exception {
		log.info("KANG-20210405 {} {}", CurrentInfo.get());
		
		String b64File = null;
		String binFile = null;
		if (Boolean.TRUE) {
			b64File = this.imsiKeyPath + File.separator + this.projEnvParamProperties.getHwPubkeyB64();
			binFile = this.imsiKeyPath + File.separator + this.projEnvParamProperties.getHwPubkeyBin();
		}
		
		if (Boolean.TRUE) {
			byte[] b64Data = StringTools.bytesFromFile(b64File);
			PublicKey pubKey = CipherUtils.getPublicKeyFromBase64(b64Data);
			byte[] binData = pubKey.getEncoded();
			StringTools.bytesToFile(binData, binFile);
			log.info("KANG-20210405 -----> {} -> {}", b64File, binFile);
			StringTools.printHex(b64Data);
			StringTools.printHex(binData);
		}
	}
	
	private void doingBase64DecodingOfHwPriKeyB64() throws Exception {
		log.info("KANG-20210405 {} {}", CurrentInfo.get());
		
		String b64File = null;
		String binFile = null;
		if (Boolean.TRUE) {
			b64File = this.imsiKeyPath + File.separator + this.projEnvParamProperties.getHwPrikeyB64();
			binFile = this.imsiKeyPath + File.separator + this.projEnvParamProperties.getHwPrikeyBin();
		}
		
		if (Boolean.TRUE) {
			byte[] b64Data = StringTools.bytesFromFile(b64File);
			PrivateKey priKey = CipherUtils.getPrivateKeyFromBase64(b64Data);
			byte[] binData = priKey.getEncoded();
			StringTools.bytesToFile(binData, binFile);
			log.info("KANG-20210405 -----> {} -> {}", b64File, binFile);
			StringTools.printHex(b64Data);
			StringTools.printHex(binData);
		}
	}
}
