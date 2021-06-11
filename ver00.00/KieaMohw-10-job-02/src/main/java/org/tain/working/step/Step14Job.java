package org.tain.working.step;

import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Step14Job {

	public void doing() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
	}
}
