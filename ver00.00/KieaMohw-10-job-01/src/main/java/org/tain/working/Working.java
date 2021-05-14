package org.tain.working;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.tools.properties.ProjEnvJobProperties;
import org.tain.utils.CurrentInfo;
import org.tain.working.hwDateFolder.HwDateFolderWorking;
import org.tain.working.hwFiles.HwFilesWorking;
import org.tain.working.hwInit.HwInitWorking;
import org.tain.working.hwOtk.HwOtkWorking;
import org.tain.working.hwPriKey.HwPriKeyWorking;
import org.tain.working.hwPubKey.HwPubKeyWorking;
import org.tain.working.moB64PubKey.MoB64PubKeyWorking;
import org.tain.working.moB64PubOtk.MoB64PubOtkWorking;
import org.tain.working.moFilesB64.MoFilesB64Working;
import org.tain.working.properties.PropertiesWorking;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Working {

	public void work() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		// properties
		if (Boolean.TRUE) jobForProperties();
		
		if (Boolean.TRUE) jobForWorking();
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private PropertiesWorking propertiesWorking;
	
	private void jobForProperties() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) this.propertiesWorking.print();
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private ProjEnvJobProperties projEnvJobProperties;
	
	@Autowired private HwInitWorking hwInitWorking;
	@Autowired private HwDateFolderWorking hwDateFolderWorking;
	@Autowired private HwPubKeyWorking hwPubKeyWorking;
	@Autowired private HwPriKeyWorking hwPriKeyWorking;
	@Autowired private MoB64PubKeyWorking moB64PubKeyWorking;
	@Autowired private HwOtkWorking hwOtkWorking;
	@Autowired private MoB64PubOtkWorking moB64PubOtkWorking;
	@Autowired private MoFilesB64Working moFilesB64Working;
	@Autowired private HwFilesWorking hwFilesWorking;
	
	private void jobForWorking() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		// 0. create folder YYYYMMDD
		if (this.projEnvJobProperties.isHwInit())       this.hwInitWorking.work();
		
		// 1. copy files from INFO_SIGN
		if (this.projEnvJobProperties.isHwDateFolder()) this.hwDateFolderWorking.work();
		
		// 2. HW_B64PubKey.bin  <-  HW_PubKey.bin
		if (this.projEnvJobProperties.isHwPubKey())     this.hwPubKeyWorking.work();
		
		// 3. HW_B64PriKey.bin  <-  HW_PriKey.bin
		if (this.projEnvJobProperties.isHwPriKey())     this.hwPriKeyWorking.work();
		
		// 4. MO_B64PubKey.bin  ->  MO_PubKey.bin
		if (this.projEnvJobProperties.isMoB64PubKey())  this.moB64PubKeyWorking.work();
		
		// 5. HW_B64EncOtk.bin  <-  HW_Otk.bin
		if (this.projEnvJobProperties.isHwOtk())        this.hwOtkWorking.work();
		
		/////////////////////////////
		// 6. MO_B64EncOtk.bin  ->  MO_Otk.bin
		if (this.projEnvJobProperties.isMoB64PubOtk())  this.moB64PubOtkWorking.work();
		
		/////////////////////////////
		// 7.
		if (this.projEnvJobProperties.isMoFilesB64())   this.moFilesB64Working.work();
		
		/////////////////////////////
		// 8.
		if (this.projEnvJobProperties.isHwFiles())      this.hwFilesWorking.work();
	}
}
