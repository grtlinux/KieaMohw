package org.tain.working.moB64PubKey;

import java.io.File;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;
import org.tain.utils.StringTools;
import org.tain.working.hwInit.HwInitWorking;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MoB64PubKeyWorking {

	@Autowired
	private HwInitWorking hwInitWorking;
	
	public boolean work() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			this.moPubKey = this.hwInitWorking.getTodayDataPath() + File.separator + "MO_PubKey.bin";
			File file = new File(this.moPubKey);
			if (file.exists()) {
				this.bMoPubKey = true;
				return true;
			}
		}
		
		String strMoB64PubKey = null;
		if (Boolean.TRUE) {
			strMoB64PubKey = this.hwInitWorking.getTodayDataPath() + File.separator + "MO_B64PubKey.bin";
			File file = new File(strMoB64PubKey);
			if (file.exists())
				return false;
			
			byte[] bMoB64PubKey = StringTools.bytesFromFile(strMoB64PubKey);
			byte[] bMoPubKey = Base64.getDecoder().decode(bMoB64PubKey);
			StringTools.bytesToFile(bMoPubKey, this.moPubKey);
		}
		
		this.bMoPubKey = true;
		return true;
	}
	
	private boolean bMoPubKey = false;
	
	public boolean isMoPubKey() {
		return this.bMoPubKey;
	}
	
	private String moPubKey = null;
	
	public String getMoPubKey() {
		return this.moPubKey;
	}
}
