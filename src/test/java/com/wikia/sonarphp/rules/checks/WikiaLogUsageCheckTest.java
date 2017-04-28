package com.wikia.sonarphp.rules.checks;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;

import com.wikia.sonarphp.rules.PHPRuleDefinitions;

import org.junit.Test;
import org.sonar.plugins.php.api.tests.PHPCheckTest;

import java.io.File;

public class WikiaLogUsageCheckTest {
	@Test
	public void ruleIsLoaded() {
		PHPRuleDefinitions phpRuleDefinitions = new PHPRuleDefinitions();

		assertThat(phpRuleDefinitions.checkClasses(), hasItem(WikiaLogUsageCheck.class));
	}

	@Test
	public void wikiaLogMethodIsNonCompliant() {
		PHPCheckTest.check(new WikiaLogUsageCheck(), new File("src/test/resources/WikiaLogUsageCheck.php"));
	}
}
