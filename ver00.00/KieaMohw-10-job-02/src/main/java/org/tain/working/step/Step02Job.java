package org.tain.working.step;

import java.io.File;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.properties.ProjEnvParamProperties;
import org.tain.utils.CipherUtils;
import org.tain.utils.CurrentInfo;
import org.tain.utils.StringTools;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Step02Job {

	@Autowired
	private ProjEnvParamProperties projEnvParamProperties;
	
	public void doing() throws Exception {
		log.info("KANG-20210405 {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) createHwKeyPair();
		if (Boolean.TRUE) createHwOtk();
		if (Boolean.TRUE) checkMoPubKey();
		
		log.info("");
	}
	
	private void createHwKeyPair() throws Exception {
		log.info("KANG-20210405 {} {}", CurrentInfo.get());
		
		String hwPrikeyBinName = null;
		String hwPubkeyBinName = null;
		if (Boolean.TRUE) {
			hwPrikeyBinName = this.projEnvParamProperties.getConfigPath() + File.separator + this.projEnvParamProperties.getHwPrikeyBin();
			hwPubkeyBinName = this.projEnvParamProperties.getConfigPath() + File.separator + this.projEnvParamProperties.getHwPubkeyBin();
			
			File priFile = new File(hwPrikeyBinName);
			File pubFile = new File(hwPubkeyBinName);
			if (priFile.exists() && pubFile.exists()) {
				log.info("KANG-20210405 -----> files exist. {} {}", priFile.getName(), pubFile.getName());
				return;
			}
		}
		
		if (Boolean.TRUE) {
			KeyPair keyPair = CipherUtils.generateRSAKeyPair();
			
			PrivateKey priKey = keyPair.getPrivate();
			PublicKey pubKey = keyPair.getPublic();
			
			byte[] binPriKey = priKey.getEncoded();
			byte[] binPubKey = pubKey.getEncoded();
			
			StringTools.bytesToFile(binPriKey, hwPrikeyBinName);
			StringTools.bytesToFile(binPubKey, hwPubkeyBinName);
			
			log.info("KANG-20210405 -----> KeyPair creation OK!!. {} {}", hwPrikeyBinName, hwPubkeyBinName);
			
			//byte[] b64PriKey = Base64.getEncoder().encode(binPriKey);
			//byte[] b64PubKey = Base64.getEncoder().encode(binPubKey);
		}
	}
	
	private void createHwOtk() throws Exception {
		log.info("KANG-20210405 {} {}", CurrentInfo.get());
		
		String hwOtkBinName = null;
		if (Boolean.TRUE) {
			hwOtkBinName = this.projEnvParamProperties.getConfigPath() + File.separator + this.projEnvParamProperties.getHwOtkBin();
			
			File file = new File(hwOtkBinName);
			if (file.exists()) {
				log.info("KANG-20210405 -----> files exist. {}", file.getName());
				StringTools.printHex(StringTools.bytesFromFile(hwOtkBinName));
				return;
			}
		}
		
		if (Boolean.TRUE) {
			byte[] binHwOtk = new byte[16];
			new Random(new Date().getTime()).nextBytes(binHwOtk);
			StringTools.bytesToFile(binHwOtk, hwOtkBinName);
			
			log.info("KANG-20210405 -----> OTK creation OK!!. {}", hwOtkBinName);
			
			StringTools.printHex(binHwOtk);
		}
	}
	
	private void checkMoPubKey() throws Exception {
		log.info("KANG-20210405 {} {}", CurrentInfo.get());
		
		String hwPubkeyBinName = null;
		if (Boolean.TRUE) {
			hwPubkeyBinName = this.projEnvParamProperties.getConfigPath() + File.separator + this.projEnvParamProperties.getMoPubkeyBin();
			
			File file = new File(hwPubkeyBinName);
			if (file.exists()) {
				log.info("KANG-20210405 -----> file exist. {}", file.getName());
			} else {
				log.info("KANG-20210405 -----> file dosn't exist. {}", file.getName());
			}
		}
	}
}
