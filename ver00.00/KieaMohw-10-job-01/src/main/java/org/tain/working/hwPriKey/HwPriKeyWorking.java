package org.tain.working.hwPriKey;

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
public class HwPriKeyWorking {

	@Autowired
	private HwInitWorking hwInitWorking;
	
	public void work() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		String strHwPriKey = null;
		if (Boolean.TRUE) {
			strHwPriKey = this.hwInitWorking.getTodayDataPath() + File.separator + "HW_PriKey.bin";
			File file = new File(strHwPriKey);
			if (!file.exists()) {
				throw new Exception("KANG-ERROR: file not found..." + strHwPriKey);
			}
		}
		
		String strHwB64PriKey = null;
		if (Boolean.TRUE) {
			strHwB64PriKey = this.hwInitWorking.getTodayDataPath() + File.separator + "HW_B64PriKey.bin";
			File file = new File(strHwPriKey);
			if (!file.exists()) {
				byte[] bHwPriKey = StringTools.bytesFromFile(strHwPriKey);
				byte[] bHwB64PriKey = Base64.getEncoder().encode(bHwPriKey);
				StringTools.bytesToFile(bHwB64PriKey, strHwB64PriKey);
			}
		}
	}
}
