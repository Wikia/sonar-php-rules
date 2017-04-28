package com.wikia.sonarphp.rules.checks;

import com.google.common.collect.ImmutableSet;
import org.sonar.check.Rule;

import java.util.Set;

@Rule(key = "WgParserUsage")
public class WgParserUsageCheck extends AbstractGlobalVariableUsageCheck {
	final private static String MESSAGE = "Do not use the global wgParser Parser instance.";
	final private static Set<String> WG_PARSER = ImmutableSet.of("wgparser");

	public WgParserUsageCheck() {
		super(MESSAGE, WG_PARSER);
	}
}
