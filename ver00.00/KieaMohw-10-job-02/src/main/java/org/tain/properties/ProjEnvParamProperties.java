package org.tain.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.tain.utils.StringTools;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "proj-env.param")
@Data
public class ProjEnvParamProperties {

	private String name;  // default

	private String home;
	private String base;
	private String keyPath;
	private String workPath;
	private String confPath;
	private String todayPath;

	private String hwPrikeyBin;
	private String hwPrikeyB64;

	private String hwPubkeyBin;
	private String hwPubkeyB64;

	private String hwOtkBin;
	private String hwOtkEnc;
	private String hwOtkB64;

	private String moPubkeyBin;
	private String moPubkeyB64;

	private String moOtkBin;
	private String moOtkEnc;
	private String moOtkB64;
	
	private String moAf71B64;
	private String moAf71Gz;
	private String moAf71Enc;
	private String moAf71Dat;

	private String dummy;  // null

	private String imsiKeyPath;
	private String workingPath;
	private String configPath;

	private boolean step00Flag;  // 기타 작업
	private boolean step01Flag;  // 작업폴더를 생성한다.
	private boolean step02Flag;  // Key 없으면 생성한다. KeyPair
	private boolean step03Flag;  // Key를 작업폴더에 복사한다.
	private boolean step04Flag;  // KeyPair에 BASE64 처리
	private boolean step05Flag;  // FI0000(1000) HW공개키 송신
	private boolean step06Flag;  // FI0001(1000) MO공개키 수신
	private boolean step07Flag;  // 변환: HW_OTK.b64 생성 using MO_PubKey.bin
	private boolean step08Flag;  // FI0071(1000:300:AF71) 요구정보 수신
	private boolean step09Flag;  // FI0072(1000) OTK 수신
	private boolean step10Flag;  // 변환:
	private boolean step11Flag;  // 변환:
	private boolean step12Flag;  // FI0076(400) 제공정보 송신
	private boolean step13Flag;  // FI0077(1000) OTK 송신
	private boolean step14Flag;  // FI1100(1000) PubKey 에러 수신
	private boolean step15Flag;  // FI1176(400) 제공정보 에러 수신

	@Bean
	public void init() throws Exception {
		this.todayPath = String.format("/%s", StringTools.getYYYYMMDD());

		this.imsiKeyPath = this.home + this.base + this.keyPath;
		this.workingPath = this.home + this.base + this.workPath + this.todayPath;
		this.configPath = this.home + this.base + this.workPath + this.confPath;
	}
}
