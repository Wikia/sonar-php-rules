package com.wikia.sonarphp.rules;

import org.sonar.api.Plugin;

import javax.annotation.Nonnull;

public class PHPRulesPlugin implements Plugin {

	@Override
	public void define(@Nonnull Context context) {
		context.addExtension(PHPRuleDefinitions.class);
	}
}
