package com.wikia.sonarphp.rules.checks;

import org.sonar.plugins.php.api.tree.Tree.Kind;
import org.sonar.plugins.php.api.tree.expression.MemberAccessTree;
import org.sonar.plugins.php.api.tree.expression.VariableIdentifierTree;
import org.sonar.plugins.php.api.tree.statement.GlobalStatementTree;
import org.sonar.plugins.php.api.visitors.PHPVisitorCheck;

import java.util.Set;

public abstract class AbstractGlobalVariableUsageCheck extends PHPVisitorCheck {
	private final static String WG = "wg";

	private Set<String> wgVars;
	private String message;

	private MemberAccessTree wgVariableLookup;

	AbstractGlobalVariableUsageCheck(String message, Set<String> wgVars) {
		this.message = message;
		this.wgVars = wgVars;
	}

	@Override
	public void visitGlobalStatement(GlobalStatementTree tree) {
		tree.variables().forEach(var -> {
			if (var.is(Kind.VARIABLE_IDENTIFIER)) {
				String varName = var.toString().substring(1).toLowerCase();

				if (wgVars.contains(varName)) {
					context().newIssue(this, var, message);
				}
			}
		});

		super.visitGlobalStatement(tree);
	}

	@Override
	public void visitVariableIdentifier(VariableIdentifierTree tree) {
		if (
			tree.text().toLowerCase().substring(1).equals(WG) &&
			wgVariableLookup != null &&
			wgVariableLookup.object().equals(tree)
			) {
			context().newIssue(this, tree, message);
		}

		super.visitVariableIdentifier(tree);
	}

	@Override
	public void visitMemberAccess(MemberAccessTree tree) {
		if (tree.member().is(Kind.NAME_IDENTIFIER)) {
			String propName = tree.member().toString().toLowerCase();

			if (wgVars.contains(WG + propName)) {
				wgVariableLookup = tree;
			} else if (propName.equals(WG) && wgVariableLookup != null && wgVariableLookup.object().equals(tree)) {
				context().newIssue(this, tree, message);
			}
		}

		super.visitMemberAccess(tree);
	}
}
