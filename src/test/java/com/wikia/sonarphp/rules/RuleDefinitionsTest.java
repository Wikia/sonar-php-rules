package com.wikia.sonarphp.rules;

import com.wikia.sonarphp.rules.xml.RulesXmlReaderFactory;

import java.io.InputStreamReader;
import java.io.Reader;
import org.junit.Test;
import org.sonar.api.server.rule.RulesDefinition.Context;

public class RuleDefinitionsTest {

	@Test(expected = IllegalStateException.class)
	public void invalidRulesXmlIsRejected() {
		PHPRuleDefinitions phpRuleDefinitions = new PHPRuleDefinitions(new InvalidRulesXmlFactory());
		Context context = new Context();

		phpRuleDefinitions.define(context);
	}

	private static class InvalidRulesXmlFactory extends RulesXmlReaderFactory {
		@Override
		public Reader newRulesXmlReader() {
			return new InputStreamReader(getClass().getResourceAsStream("/xml/invalidRules.xml"));
		}
	}
}
