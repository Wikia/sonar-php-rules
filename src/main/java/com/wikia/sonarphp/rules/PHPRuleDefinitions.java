package com.wikia.sonarphp.rules;

import com.wikia.sonarphp.rules.checks.ContextualWgVariableUsageCheck;
import com.wikia.sonarphp.rules.checks.LegacyControllerMethodDeclarationCheck;
import com.wikia.sonarphp.rules.checks.LegacyWfMsgUsageCheck;
import com.wikia.sonarphp.rules.checks.OnMakeGlobalVariablesScriptCheck;
import com.wikia.sonarphp.rules.checks.PlainFormatWfMessageCheck;
import com.wikia.sonarphp.rules.checks.WgParserUsageCheck;
import com.wikia.sonarphp.rules.checks.WikiaLogUsageCheck;

import com.google.common.collect.ImmutableList;
import org.sonar.plugins.php.api.visitors.PHPCustomRulesDefinition;

public class PHPRuleDefinitions extends PHPCustomRulesDefinition {

	@Override
	public String repositoryName() {
		return "Wikia PHP Rules";
	}

	@Override
	public String repositoryKey() {
		return "wikia-php-rules";
	}

	@Override
	public ImmutableList<Class> checkClasses() {
		return ImmutableList.of(
			ContextualWgVariableUsageCheck.class, LegacyWfMsgUsageCheck.class,
			OnMakeGlobalVariablesScriptCheck.class, LegacyControllerMethodDeclarationCheck.class,
			WgParserUsageCheck.class, PlainFormatWfMessageCheck.class, WikiaLogUsageCheck.class
		);
	}
}
