package org.tain.tools.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "proj-env.param")
@Data
public class ProjEnvParamProperties {

	private String name;  // default
	
	private String home;
	private String base;
	private String dataPath;
	private String infoSignPath;
	
	private String dummy;  // null
}
