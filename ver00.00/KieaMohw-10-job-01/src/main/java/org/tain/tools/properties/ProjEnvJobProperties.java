package org.tain.tools.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "proj-env.job")
@Data
public class ProjEnvJobProperties {

	private String name;  // default
	
	private boolean hwInit;        // create YYYYMMDD folder
	private boolean hwDateFolder;  // copy files from INFO_SIGN
	private boolean hwPubKey;      // HW_PubKey.bin  ->  HW_B64PubKey.bin
	private boolean hwPriKey;      // HW_PriKey.bin  ->  HW_B64PriKey.bin
	private boolean moB64PubKey;   // MO_PubKey.bin  <-  MO_B64PubKey.bin
	private boolean hwOtk;         // HW_Otk.bin     ->  HW_B64PubOtk.bin
	private boolean moB64PubOtk;   // MO_Otk.bin     <-  MO_B64PubOtk.bin
	private boolean moFilesB64;    // MO_FILES  <-  MO_FILES.gz  <-  MO_FILES.b64
	private boolean hwFiles;       // HW_FILES  ->  HW_FILES.gz  ->  HW_FILES.b64
	
	private String dummy;  // null
}
