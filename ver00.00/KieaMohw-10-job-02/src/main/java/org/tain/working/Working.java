package org.tain.working;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.properties.ProjEnvParamProperties;
import org.tain.working.step.Step00Job;
import org.tain.working.step.Step01Job;
import org.tain.working.step.Step02Job;
import org.tain.working.step.Step03Job;
import org.tain.working.step.Step04Job;
import org.tain.working.step.Step05Job;
import org.tain.working.step.Step06Job;
import org.tain.working.step.Step07Job;
import org.tain.working.step.Step08Job;
import org.tain.working.step.Step09Job;
import org.tain.working.step.Step10Job;
import org.tain.working.step.Step11Job;
import org.tain.working.step.Step12Job;
import org.tain.working.step.Step13Job;
import org.tain.working.step.Step14Job;
import org.tain.working.step.Step15Job;
import org.tain.working.workProperties.WorkProperties;

@Component
public class Working {

	@Autowired
	private WorkProperties workProperties;
	
	public void printProperties() throws Exception {
		this.workProperties.print();
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private ProjEnvParamProperties projEnvParamProperties;
	
	@Autowired private Step00Job step00Job;
	@Autowired private Step01Job step01Job;
	@Autowired private Step02Job step02Job;
	@Autowired private Step03Job step03Job;
	@Autowired private Step04Job step04Job;
	@Autowired private Step05Job step05Job;
	@Autowired private Step06Job step06Job;
	@Autowired private Step07Job step07Job;
	@Autowired private Step08Job step08Job;
	@Autowired private Step09Job step09Job;
	@Autowired private Step10Job step10Job;
	@Autowired private Step11Job step11Job;
	@Autowired private Step12Job step12Job;
	@Autowired private Step13Job step13Job;
	@Autowired private Step14Job step14Job;
	@Autowired private Step15Job step15Job;
	
	public void stepJob() throws Exception {
		if (this.projEnvParamProperties.isStep00Flag()) this.step00Job.doing();
		if (this.projEnvParamProperties.isStep01Flag()) this.step01Job.doing();
		if (this.projEnvParamProperties.isStep02Flag()) this.step02Job.doing();
		if (this.projEnvParamProperties.isStep03Flag()) this.step03Job.doing();
		if (this.projEnvParamProperties.isStep04Flag()) this.step04Job.doing();
		if (this.projEnvParamProperties.isStep05Flag()) this.step05Job.doing();
		if (this.projEnvParamProperties.isStep06Flag()) this.step06Job.doing();
		if (this.projEnvParamProperties.isStep07Flag()) this.step07Job.doing();
		if (this.projEnvParamProperties.isStep08Flag()) this.step08Job.doing();
		if (this.projEnvParamProperties.isStep09Flag()) this.step09Job.doing();
		if (this.projEnvParamProperties.isStep10Flag()) this.step10Job.doing();
		if (this.projEnvParamProperties.isStep11Flag()) this.step11Job.doing();
		if (this.projEnvParamProperties.isStep12Flag()) this.step12Job.doing();
		if (this.projEnvParamProperties.isStep13Flag()) this.step13Job.doing();
		if (this.projEnvParamProperties.isStep14Flag()) this.step14Job.doing();
		if (this.projEnvParamProperties.isStep15Flag()) this.step15Job.doing();
	}
}
