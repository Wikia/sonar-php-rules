package com.wikia.sonarphp.rules.checks;

import com.wikia.sonarphp.rules.PHPRuleDefinitions;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.hasItem;

import org.junit.Test;
import org.sonar.plugins.php.api.tests.PHPCheckTest;

import java.io.File;

public class LegacyWfMsgUsageCheckTest {
	@Test
	public void ruleIsLoaded() {
		PHPRuleDefinitions phpRuleDefinitions = new PHPRuleDefinitions();

		assertThat(phpRuleDefinitions.checkClasses(), hasItem(LegacyWfMsgUsageCheck.class));
	}

	@Test
	public void wfMsgFunctionCallsAreNonCompliant() {
		PHPCheckTest.check(new LegacyWfMsgUsageCheck(), new File("src/test/resources/LegacyWfMsgUsageCheck.php"));
	}
}
