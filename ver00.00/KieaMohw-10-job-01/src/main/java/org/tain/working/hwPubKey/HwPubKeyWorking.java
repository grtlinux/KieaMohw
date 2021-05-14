package org.tain.working.hwPubKey;

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
public class HwPubKeyWorking {

	@Autowired
	private HwInitWorking hwInitWorking;
	
	public void work() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		String strHwPubKey = null;
		if (Boolean.TRUE) {
			strHwPubKey = this.hwInitWorking.getTodayDataPath() + File.separator + "HW_PubKey.bin";
			File file = new File(strHwPubKey);
			if (!file.exists()) {
				throw new Exception("KANG-ERROR: file not found..." + strHwPubKey);
			}
		}
		
		String strHwB64PubKey = null;
		if (Boolean.TRUE) {
			strHwB64PubKey = this.hwInitWorking.getTodayDataPath() + File.separator + "HW_B64PubKey.bin";
			File file = new File(strHwPubKey);
			if (!file.exists()) {
				byte[] bHwPubKey = StringTools.bytesFromFile(strHwPubKey);
				byte[] bHwB64PubKey = Base64.getEncoder().encode(bHwPubKey);
				StringTools.bytesToFile(bHwB64PubKey, strHwB64PubKey);
			}
		}
	}
}
