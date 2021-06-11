package org.tain.working.step;

import java.io.File;
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
public class Step07Job {

	@Autowired
	private ProjEnvParamProperties projEnvParamProperties;
	
	private String workingPath = null;
	
	public void doing() throws Exception {
		log.info("KANG-20210405 {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			this.workingPath = this.projEnvParamProperties.getWorkingPath();
			log.info("KANG-20210405 -----> workingPath. {}", this.workingPath);
		}
		
		if (Boolean.TRUE) getMoPubKey();
		if (Boolean.TRUE) getFileNames();
		if (Boolean.TRUE) doingTransfer();
		
		log.info("");
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private String moPubKeyB64Path = null;
	private PublicKey moPubKey = null;
	
	private void getMoPubKey() throws Exception {
		log.info("KANG-20210405 {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			this.moPubKeyB64Path = this.workingPath + File.separator + this.projEnvParamProperties.getMoPubkeyB64();
			this.moPubKey = CipherUtils.getPublicKeyFromBase64(StringTools.bytesFromFile(this.moPubKeyB64Path));
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private String hwOtkBinFile = null;
	private String hwOtkEncFile = null;
	private String hwOtkB64File = null;
	
	private void getFileNames() throws Exception {
		log.info("KANG-20210405 {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			this.hwOtkBinFile = this.workingPath + File.separator + this.projEnvParamProperties.getHwOtkBin();
			this.hwOtkEncFile = this.workingPath + File.separator + this.projEnvParamProperties.getHwOtkEnc();
			this.hwOtkB64File = this.workingPath + File.separator + this.projEnvParamProperties.getHwOtkB64();
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private void doingTransfer() throws Exception {
		log.info("KANG-20210405 {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			byte[] byteText = StringTools.bytesFromFile(this.hwOtkBinFile);
			byte[] byteEncText = CipherUtils.encryptRSA(byteText, this.moPubKey);
			StringTools.bytesToFile(byteEncText, this.hwOtkEncFile);
			byte[] byteB64Text = Base64.getEncoder().encode(byteEncText);
			StringTools.bytesToFile(byteB64Text, this.hwOtkB64File);
		}
		
		log.info("KANG-20210405 -----> 1. transfer file: {}", this.hwOtkBinFile);
		log.info("KANG-20210405 -----> 2. transfer file: {}", this.hwOtkEncFile);
		log.info("KANG-20210405 -----> 3. transfer file: {}", this.hwOtkB64File);
	}
}
