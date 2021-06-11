package org.tain.working.step;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.zip.GZIPInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.properties.ProjEnvParamProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.StringTools;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Step11Job {

	@Autowired
	private ProjEnvParamProperties projEnvParamProperties;
	
	private String workingPath = null;
	
	public void doing() throws Exception {
		log.info("KANG-20210405 {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			this.workingPath = this.projEnvParamProperties.getWorkingPath();
			log.info("KANG-20210405 -----> workingPath. {}", this.workingPath);
		}
		
		if (Boolean.TRUE) getFileNames();
		if (Boolean.TRUE) doingTransfer();
		
		log.info("");
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private String moAf71B64File = null;
	private String moAf71GzFile = null;
	private String moAf71EncFile = null;
	private String moAf71DatFile = null;
	
	private void getFileNames() throws Exception {
		log.info("KANG-20210405 {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			this.moAf71B64File = this.workingPath + File.separator + this.projEnvParamProperties.getMoAf71B64();
			this.moAf71GzFile  = this.workingPath + File.separator + this.projEnvParamProperties.getMoAf71Gz();
			this.moAf71EncFile = this.workingPath + File.separator + this.projEnvParamProperties.getMoAf71Enc();
			this.moAf71DatFile = this.workingPath + File.separator + this.projEnvParamProperties.getMoAf71Dat();
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private void doingTransfer() throws Exception {
		log.info("KANG-20210405 {} {}", CurrentInfo.get());
		
		byte[] byteGzText = null;
		if (Boolean.TRUE) {
			// AF71.b64 -> AF71.gz
			byte[] byteB64Text = StringTools.bytesFromFile(this.moAf71B64File);
			byteGzText = Base64.getDecoder().decode(byteB64Text);
			StringTools.bytesToFile(byteGzText, this.moAf71GzFile);
		}
		
		if (Boolean.TRUE) {
			// AF71.gz -> AF71.enc
			InputStream in = new GZIPInputStream(new ByteArrayInputStream(byteGzText));
			OutputStream out = new FileOutputStream(this.moAf71EncFile);
			byte[] buf = new byte[2048];
			int n;
			while ((n = in.read(buf)) != -1)
				out.write(buf, 0, n);
			in.close();
			out.close();
		}
		
		if (Boolean.TRUE) {
			// AF71.enc -> AF71.dat: SEED decrypt using MO_OTK.bin(16)
		}
		
		log.info("KANG-20210405 -----> 1. transfer file: {}", this.moAf71B64File);
		log.info("KANG-20210405 -----> 2. transfer file: {}", this.moAf71GzFile);
		log.info("KANG-20210405 -----> 3. transfer file: {}", this.moAf71EncFile);
		log.info("KANG-20210405 -----> 4. transfer file: {}", this.moAf71DatFile);
	}
}
