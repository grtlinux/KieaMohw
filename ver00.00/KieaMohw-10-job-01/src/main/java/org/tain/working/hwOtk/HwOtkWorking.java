package org.tain.working.hwOtk;

import java.io.File;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.utils.CipherUtils;
import org.tain.utils.CurrentInfo;
import org.tain.utils.StringTools;
import org.tain.working.hwInit.HwInitWorking;
import org.tain.working.moB64PubKey.MoB64PubKeyWorking;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class HwOtkWorking {

	@Autowired
	private HwInitWorking hwInitWorking;
	
	@Autowired
	private MoB64PubKeyWorking moB64PubKeyWorking;
	
	public void work() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			if (!this.moB64PubKeyWorking.isMoPubKey())
				return;
		}
		
		PublicKey moPubKey = null;
		if (Boolean.TRUE) {
			byte[] bMoPubKey = StringTools.bytesFromFile(this.moB64PubKeyWorking.getMoPubKey());
			moPubKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(bMoPubKey));
			//PrivateKey priKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(bMoPriKey));
		}
		
		String pathHwOtk = null;
		String pathHwB64PubOtk = null;
		if (Boolean.TRUE) {
			pathHwOtk = this.hwInitWorking.getTodayDataPath() + File.separator + "HW_Otk.bin";
			pathHwB64PubOtk = this.hwInitWorking.getTodayDataPath() + File.separator + "HW_B64PubOtk.bin";
		}
		
		if (Boolean.TRUE) {
			byte[] bHwOtk = StringTools.bytesFromFile(pathHwOtk);
			byte[] bHwEncOtk = CipherUtils.encryptRSA(bHwOtk, moPubKey);
			byte[] bHwB64EncOtk = Base64.getEncoder().encode(bHwEncOtk);
			StringTools.bytesToFile(bHwB64EncOtk, pathHwB64PubOtk);
		}
	}
}
