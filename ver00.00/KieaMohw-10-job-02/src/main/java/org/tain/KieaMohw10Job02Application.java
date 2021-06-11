package org.tain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tain.properties.ProjEnvBaseProperties;
import org.tain.working.Working;

@SpringBootApplication
public class KieaMohw10Job02Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(KieaMohw10Job02Application.class, args);
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private ProjEnvBaseProperties projEnvBaseProperties;
	
	@Autowired
	private Working working;
	
	@Override
	public void run(String... args) throws Exception {
		this.working.printProperties();
		this.working.stepJob();
		
		if (this.projEnvBaseProperties.isTestFlag()) {
			System.exit(0);
		}
	}
}
