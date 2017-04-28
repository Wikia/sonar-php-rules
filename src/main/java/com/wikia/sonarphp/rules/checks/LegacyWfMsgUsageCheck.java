package com.wikia.sonarphp.rules.checks;

import org.sonar.check.Rule;
import org.sonar.plugins.php.api.tree.Tree.Kind;
import org.sonar.plugins.php.api.tree.expression.FunctionCallTree;
import org.sonar.plugins.php.api.visitors.PHPVisitorCheck;

import javax.annotation.Nonnull;

@Rule(key = "LegacyWfMsgUsage")
public class LegacyWfMsgUsageCheck extends PHPVisitorCheck {
	final private static String MESSAGE = "Replace this legacy wfMsg* function call with one of the new i18n functions.";
	final private static String WF_MSG = "wfMsg";

	@Override
	public void visitFunctionCall(@Nonnull FunctionCallTree tree) {
		if (tree.callee().is(Kind.NAMESPACE_NAME) && tree.callee().toString().startsWith(WF_MSG)) {
			context().newIssue(this, tree, MESSAGE);
		}

		super.visitFunctionCall(tree);
	}
}
