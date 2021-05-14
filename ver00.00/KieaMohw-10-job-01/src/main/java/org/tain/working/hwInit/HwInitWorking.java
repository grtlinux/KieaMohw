package org.tain.working.hwInit;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.tools.properties.ProjEnvParamProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.StringTools;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class HwInitWorking {

	@Autowired
	private ProjEnvParamProperties projEnvParamProperties;
	
	public void work() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) this.getFolderNames();
		if (Boolean.TRUE) this.createDateFolder();
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private String strInfoSignPath = null;
	private String strTodayDataPath = null;
	
	public void getFolderNames() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			String home = this.projEnvParamProperties.getHome();
			String base = this.projEnvParamProperties.getBase();
			String dataPath = this.projEnvParamProperties.getDataPath();
			String infoSignPath = this.projEnvParamProperties.getInfoSignPath();
			String today = StringTools.getYYYYMMDD();
			this.strInfoSignPath = home + base + dataPath + infoSignPath;
			this.strTodayDataPath = home + base + dataPath + File.separator + today;
			log.info("KANG-20210405 >>>>> strInfoSignPath: {} {}", this.strInfoSignPath, CurrentInfo.get());
			log.info("KANG-20210405 >>>>> strTodayDataPath: {} {}", this.strTodayDataPath, CurrentInfo.get());
		}
	}
	
	public void createDateFolder() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			File file = new File(this.strInfoSignPath);
			if (!file.exists()) {
				Files.createDirectories(Paths.get(this.strInfoSignPath));
			}
		}
		
		if (Boolean.TRUE) {
			File file = new File(this.strTodayDataPath);
			if (!file.exists()) {
				Files.createDirectories(Paths.get(this.strTodayDataPath));
			}
		}
	}
	
	public String getInfoSignPath() {
		return this.strInfoSignPath;
	}
	
	public String getTodayDataPath() {
		return this.strTodayDataPath;
	}
}