package org.tain.working.step;

import java.io.File;
import java.security.PrivateKey;
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
public class Step10Job {

	@Autowired
	private ProjEnvParamProperties projEnvParamProperties;
	
	private String workingPath = null;
	
	public void doing() throws Exception {
		log.info("KANG-20210405 {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			this.workingPath = this.projEnvParamProperties.getWorkingPath();
			log.info("KANG-20210405 -----> workingPath. {}", this.workingPath);
		}
		
		if (Boolean.TRUE) getHwPriKey();
		if (Boolean.TRUE) getFileNames();
		if (Boolean.TRUE) doingTransfer();
		
		log.info("");
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private String hwPriKeyB64Path = null;
	private PrivateKey hwPriKey = null;
	
	private void getHwPriKey() throws Exception {
		log.info("KANG-20210405 {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			this.hwPriKeyB64Path = this.workingPath + File.separator + this.projEnvParamProperties.getHwPrikeyB64();
			this.hwPriKey = CipherUtils.getPrivateKeyFromBase64(StringTools.bytesFromFile(this.hwPriKeyB64Path));
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private String moOtkB64File = null;
	private String moOtkEncFile = null;
	private String moOtkBinFile = null;
	
	private void getFileNames() throws Exception {
		log.info("KANG-20210405 {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			this.moOtkB64File = this.workingPath + File.separator + this.projEnvParamProperties.getMoOtkB64();
			this.moOtkEncFile = this.workingPath + File.separator + this.projEnvParamProperties.getMoOtkEnc();
			this.moOtkBinFile = this.workingPath + File.separator + this.projEnvParamProperties.getMoOtkBin();
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private void doingTransfer() throws Exception {
		log.info("KANG-20210405 {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			byte[] byteB64Text = StringTools.bytesFromFile(this.moOtkB64File);
			byte[] byteEncText = Base64.getDecoder().decode(byteB64Text);
			StringTools.bytesToFile(byteEncText, this.moOtkEncFile);
			byte[] byteBinText = CipherUtils.decryptRSA(byteEncText, this.hwPriKey);
			StringTools.bytesToFile(byteBinText, this.moOtkBinFile);
			
			log.info("KANG-20210405 -----> files exist. {}", this.moOtkBinFile);
			StringTools.printHex(byteBinText);
		}
		
		log.info("KANG-20210405 -----> 1. transfer file: {}", this.moOtkB64File);
		log.info("KANG-20210405 -----> 2. transfer file: {}", this.moOtkEncFile);
		log.info("KANG-20210405 -----> 3. transfer file: {}", this.moOtkBinFile);
	}
}
