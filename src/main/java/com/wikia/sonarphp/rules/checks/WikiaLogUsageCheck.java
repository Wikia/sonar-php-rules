package com.wikia.sonarphp.rules.checks;

import org.sonar.check.Rule;
import org.sonar.plugins.php.api.tree.Tree.Kind;
import org.sonar.plugins.php.api.tree.expression.MemberAccessTree;
import org.sonar.plugins.php.api.visitors.PHPVisitorCheck;

@Rule(key = "WikiaLogUsage")
public class WikiaLogUsageCheck extends PHPVisitorCheck {
	final private static String MESSAGE = "Replace or remove this deprecated Wikia::log logging statement.";
	final private static String WIKIA = "Wikia";
	final private static String LOG = "log";

	@Override
	public void visitMemberAccess(MemberAccessTree tree) {
		if (
			tree.member().is(Kind.NAME_IDENTIFIER) &&
			tree.member().toString().toLowerCase().equals(LOG) &&
			tree.object().is(Kind.NAMESPACE_NAME) &&
			tree.object().toString().equals(WIKIA)
		) {
			context().newIssue(this, tree, MESSAGE);
		}

		super.visitMemberAccess(tree);
	}
}
