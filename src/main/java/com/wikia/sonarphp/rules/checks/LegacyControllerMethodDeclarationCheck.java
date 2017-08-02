package com.wikia.sonarphp.rules.checks;

import com.google.common.collect.ImmutableSet;
import org.sonar.check.Rule;
import org.sonar.plugins.php.api.tree.declaration.ClassDeclarationTree;
import org.sonar.plugins.php.api.tree.declaration.MethodDeclarationTree;
import org.sonar.plugins.php.api.tree.declaration.NamespaceNameTree;
import org.sonar.plugins.php.api.visitors.PHPVisitorCheck;

import java.util.Set;

@Rule(key = "LegacyControllerMethodDeclaration")
public class LegacyControllerMethodDeclarationCheck extends PHPVisitorCheck {
	private final static String MESSAGE = "Change this method declaration to follow Nirvana conventions";
	private final static String EXECUTE = "execute";
	private final static Set<String> NIRVANA_MVC_CLASSES = ImmutableSet.of("WikiaController", "WikiaService");

	private boolean isNirvanaMvcClass = false;

	@Override
	public void visitClassDeclaration(ClassDeclarationTree tree) {
		NamespaceNameTree superClass = tree.superClass();

		if (superClass != null && NIRVANA_MVC_CLASSES.contains(superClass.fullName())) {
			isNirvanaMvcClass = true;
			super.visitClassDeclaration(tree);
			isNirvanaMvcClass = false;

			return;
		}

		super.visitClassDeclaration(tree);
	}

	@Override
	public void visitMethodDeclaration(MethodDeclarationTree tree) {
		if (isNirvanaMvcClass && tree.name().text().startsWith(EXECUTE) && !tree.parameters().parameters().isEmpty()) {
			context().newIssue(this, tree, MESSAGE);
		}

		super.visitMethodDeclaration(tree);
	}
}
