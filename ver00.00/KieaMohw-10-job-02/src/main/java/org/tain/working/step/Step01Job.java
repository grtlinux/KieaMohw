package org.tain.working.step;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.properties.ProjEnvParamProperties;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Step01Job {

	@Autowired
	private ProjEnvParamProperties projEnvParamProperties;
	
	public void doing() throws Exception {
		log.info("KANG-20210405 {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) this.createDirConf();
		if (Boolean.TRUE) this.createDirToday();
		
		log.info("");
	}
	
	private void createDirConf() throws Exception {
		log.info("KANG-20210405 {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			String dirName = this.projEnvParamProperties.getConfigPath();
			
			File dir = new File(dirName);
			if (!dir.exists()) {
				boolean isRet = dir.mkdirs();
				log.info("KANG-20210405 -----> [{}] -> mkdir.isRet: {}", dirName, isRet);
			} else {
				log.info("KANG-20210405 -----> [{}] -> exist", dirName);
			}
		}
	}
	
	private void createDirToday() throws Exception {
		log.info("KANG-20210405 {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			String dirName = this.projEnvParamProperties.getWorkingPath();
			
			File dir = new File(dirName);
			if (!dir.exists()) {
				boolean isRet = dir.mkdirs();
				log.info("KANG-20210405 -----> [{}] -> mkdir.isRet: {}", dirName, isRet);
			} else {
				log.info("KANG-20210405 -----> [{}] -> exist", dirName);
			}
		}
	}
}
