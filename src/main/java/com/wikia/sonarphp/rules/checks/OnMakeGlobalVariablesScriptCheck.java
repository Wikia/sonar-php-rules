package com.wikia.sonarphp.rules.checks;

import org.sonar.check.Rule;
import org.sonar.plugins.php.api.tree.Tree.Kind;
import org.sonar.plugins.php.api.tree.declaration.MethodDeclarationTree;
import org.sonar.plugins.php.api.tree.expression.ArrayAccessTree;
import org.sonar.plugins.php.api.tree.expression.LiteralTree;
import org.sonar.plugins.php.api.visitors.PHPVisitorCheck;

import javax.annotation.Nonnull;

@Rule(key = "OnMakeGlobalVariablesScript")
public class OnMakeGlobalVariablesScriptCheck extends PHPVisitorCheck {
	final private static String MESSAGE = "Don't use an OnMakeGlobalVariablesScript hook handler to register JS variables.";
	final private static String WG_HOOKS = "$wgHooks";
	final private static String ON_MAKE_GLOBAL_VARIABLES_SCRIPT = "onmakeglobalvariablesscript";

	@Override
	public void visitArrayAccess(@Nonnull ArrayAccessTree tree) {
		if (
		    tree.object().is(Kind.VARIABLE_IDENTIFIER) &&
		    tree.object().toString().equals(WG_HOOKS) &&
		    tree.offset() != null &&
		    tree.offset().is(Kind.REGULAR_STRING_LITERAL) &&
		    tree.offset().toString().toLowerCase().replaceAll("['\"]", "").equals(ON_MAKE_GLOBAL_VARIABLES_SCRIPT)
		) {
			context().newIssue(this, tree, MESSAGE);
		}

		super.visitArrayAccess(tree);
	}

	@Override
	public void visitMethodDeclaration(@Nonnull MethodDeclarationTree tree) {
		if (tree.name().text().toLowerCase().equals(ON_MAKE_GLOBAL_VARIABLES_SCRIPT)) {
			context().newIssue(this, tree, MESSAGE);
		}

		super.visitMethodDeclaration(tree);
	}
}
