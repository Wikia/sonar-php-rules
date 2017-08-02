package com.wikia.sonarphp.rules.checks;

import static com.wikia.sonarphp.TestHelper.getUselessWrapperClassForFile;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;

import com.wikia.sonarphp.rules.PHPRuleDefinitions;
import org.junit.Test;
import org.sonar.plugins.php.api.tests.PHPCheckTest;

public class ThisPassedByReferenceCheckTest {
  @Test
  public void ruleIsLoaded() {
    PHPRuleDefinitions phpRuleDefinitions = new PHPRuleDefinitions();

    assertThat(phpRuleDefinitions.checkClasses(), hasItem(ThisPassedByReferenceCheck.class));
  }

  @Test
  public void plainFormatMessagesAreNonCompliant() {
    PHPCheckTest.check(
        new ThisPassedByReferenceCheck(),
        getUselessWrapperClassForFile("src/test/resources/ThisPassedByReferenceCheck.php")
    );
  }
}
