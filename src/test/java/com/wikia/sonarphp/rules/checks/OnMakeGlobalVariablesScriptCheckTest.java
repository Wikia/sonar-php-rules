package com.wikia.sonarphp.rules.checks;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;

import com.wikia.sonarphp.rules.PHPRuleDefinitions;

import org.junit.Test;
import org.sonar.plugins.php.api.tests.PHPCheckTest;

import java.io.File;

public class OnMakeGlobalVariablesScriptCheckTest {
	@Test
	public void ruleIsLoaded() {
		PHPRuleDefinitions phpRuleDefinitions = new PHPRuleDefinitions();

		assertThat(phpRuleDefinitions.checkClasses(), hasItem(OnMakeGlobalVariablesScriptCheck.class));
	}

	@Test
	public void onMakeGlobalVariablesScriptHookIsNonCompliant() {
		PHPCheckTest.check(new OnMakeGlobalVariablesScriptCheck(), new File("src/test/resources/OnMakeGlobalVariablesScriptCheck.php"));
	}
}
