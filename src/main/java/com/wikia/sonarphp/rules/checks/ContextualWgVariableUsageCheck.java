package com.wikia.sonarphp.rules.checks;

import com.google.common.collect.ImmutableSet;
import org.sonar.check.Rule;

import java.util.Set;

@Rule(key = "ContextualWgVariableUsage")
public class ContextualWgVariableUsageCheck extends AbstractGlobalVariableUsageCheck {
	private final static String MESSAGE = "Refactor this code to rely on RequestContext instead of context-dependent globals.";
	private final static Set<String> CONTEXTUAL_WG_VARS = ImmutableSet.of(
		"wgtitle", "wguser", "wgrequest", "wgout", "wgarticle"
		);

	public ContextualWgVariableUsageCheck() {
		super(MESSAGE, CONTEXTUAL_WG_VARS);
	}

}
