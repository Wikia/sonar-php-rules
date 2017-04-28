package com.wikia.sonarphp.rules.checks;

import org.sonar.plugins.php.api.tree.Tree.Kind;
import org.sonar.plugins.php.api.tree.expression.MemberAccessTree;
import org.sonar.plugins.php.api.tree.expression.VariableIdentifierTree;
import org.sonar.plugins.php.api.tree.statement.GlobalStatementTree;
import org.sonar.plugins.php.api.visitors.PHPVisitorCheck;

import java.util.Set;

import javax.annotation.Nonnull;

public abstract class AbstractGlobalVariableUsageCheck extends PHPVisitorCheck {
	final private static String WG = "wg";

	private Set<String> wgVars;
	private String message;

	private MemberAccessTree wgVariableLookup;

	public AbstractGlobalVariableUsageCheck(String message, Set<String> wgVars) {
		this.message = message;
		this.wgVars = wgVars;
	}

	@Override
	public void visitGlobalStatement(@Nonnull GlobalStatementTree tree) {
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
	public void visitVariableIdentifier(@Nonnull VariableIdentifierTree tree) {
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
	public void visitMemberAccess(@Nonnull MemberAccessTree tree) {
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
