package org.tain.working.step;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.properties.ProjEnvParamProperties;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Step03Job {

	@Autowired
	private ProjEnvParamProperties projEnvParamProperties;
	
	private String configPath = null;
	private String workingPath = null;
	
	public void doing() throws Exception {
		log.info("KANG-20210405 {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			this.configPath = this.projEnvParamProperties.getConfigPath();
			this.workingPath = this.projEnvParamProperties.getWorkingPath();
			log.info("KANG-20210405 -----> configPath. {}", this.configPath);
			log.info("KANG-20210405 -----> workingPath. {}", this.workingPath);
		}
		
		if (Boolean.TRUE) copyHwKeyPair();
		if (Boolean.TRUE) copyHwOtk();
		if (Boolean.TRUE) copyMoPubKey();
		
		log.info("");
	}
	
	
	private void copyHwKeyPair() throws Exception {
		log.info("KANG-20210405 {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			Path srcFile = Paths.get(this.configPath + File.separator + this.projEnvParamProperties.getHwPrikeyBin());
			Path dstFile = Paths.get(this.workingPath + File.separator + this.projEnvParamProperties.getHwPrikeyBin());
			
			if (!Files.exists(dstFile.getParent())) {
				Files.createDirectories(dstFile.getParent());
			}
			
			Files.copy(srcFile, dstFile, StandardCopyOption.REPLACE_EXISTING);
			log.info("KANG-20210405 -----> file copy. {} {}", srcFile, dstFile);
		}
		
		if (Boolean.TRUE) {
			Path srcFile = Paths.get(this.configPath + File.separator + this.projEnvParamProperties.getHwPubkeyBin());
			Path dstFile = Paths.get(this.workingPath + File.separator + this.projEnvParamProperties.getHwPubkeyBin());
			
			if (!Files.exists(dstFile.getParent())) {
				Files.createDirectories(dstFile.getParent());
			}
			
			Files.copy(srcFile, dstFile, StandardCopyOption.REPLACE_EXISTING);
			log.info("KANG-20210405 -----> file copy. {} {}", srcFile, dstFile);
		}
	}
	
	private void copyHwOtk() throws Exception {
		log.info("KANG-20210405 {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			Path srcFile = Paths.get(this.configPath + File.separator + this.projEnvParamProperties.getHwOtkBin());
			Path dstFile = Paths.get(this.workingPath + File.separator + this.projEnvParamProperties.getHwOtkBin());
			
			if (!Files.exists(dstFile.getParent())) {
				Files.createDirectories(dstFile.getParent());
			}
			
			Files.copy(srcFile, dstFile, StandardCopyOption.REPLACE_EXISTING);
			log.info("KANG-20210405 -----> file copy. {} {}", srcFile, dstFile);
		}
	}
	
	private void copyMoPubKey() throws Exception {
		log.info("KANG-20210405 {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			Path srcFile = Paths.get(this.configPath + File.separator + this.projEnvParamProperties.getMoPubkeyBin());
			Path dstFile = Paths.get(this.workingPath + File.separator + this.projEnvParamProperties.getMoPubkeyBin());
			
			if (!Files.exists(srcFile)) {
				log.info("KANG-20210405 -----> file couldn't copy. {} not exist {}", srcFile);
				return;
			}
			
			if (!Files.exists(dstFile.getParent())) {
				Files.createDirectories(dstFile.getParent());
			}
			
			Files.copy(srcFile, dstFile, StandardCopyOption.REPLACE_EXISTING);
			log.info("KANG-20210405 -----> file copy. {} {}", srcFile.getName(0), dstFile);
		}
	}
}
