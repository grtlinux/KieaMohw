package org.tain.working.hwFiles;

import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class HwFilesWorking {

	public void work() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			
		}
	}
}
