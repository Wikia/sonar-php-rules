package com.wikia.sonarphp.rules;

import com.wikia.sonarphp.rules.xml.RulesXmlInputStreamFactory;

import org.junit.Test;
import org.sonar.api.server.rule.RulesDefinition.Context;

import java.io.InputStream;

public class RuleDefinitionsTest {

	@Test(expected = IllegalStateException.class)
	public void invalidRulesXmlIsRejected() {
		PHPRuleDefinitions phpRuleDefinitions = new PHPRuleDefinitions(new InvalidRulesXmlFactory());
		Context context = new Context();

		phpRuleDefinitions.define(context);
	}

	private static class InvalidRulesXmlFactory extends RulesXmlInputStreamFactory {
		@Override
		public InputStream getRulesXmlStream() {
			return getClass().getResourceAsStream("/xml/invalidRules.xml");
		}
	}
}
