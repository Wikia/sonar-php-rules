package com.wikia.sonarphp.rules.checks;

import com.google.common.collect.ImmutableSet;
import org.sonar.check.Rule;
import org.sonar.plugins.php.api.tree.Tree.Kind;
import org.sonar.plugins.php.api.tree.declaration.ClassDeclarationTree;
import org.sonar.plugins.php.api.tree.declaration.MethodDeclarationTree;
import org.sonar.plugins.php.api.visitors.PHPVisitorCheck;

import java.util.Set;

import javax.annotation.Nonnull;

@Rule(key = "LegacyControllerMethodDeclaration")
public class LegacyControllerMethodDeclarationCheck extends PHPVisitorCheck {
	final private static String MESSAGE = "Change this method declaration to follow Nirvana conventions";
	final private static String EXECUTE = "execute";
	final private static Set<String> NIRVANA_MVC_CLASSES = ImmutableSet.of("WikiaController", "WikiaService");

	@Override
	public void visitClassDeclaration(@Nonnull ClassDeclarationTree tree) {
		if (
			tree.superClass() != null &&
			NIRVANA_MVC_CLASSES.contains(tree.superClass().fullName())
		) {
			tree.members().forEach(member -> {
				if (member.is(Kind.METHOD_DECLARATION)) {
					MethodDeclarationTree method = (MethodDeclarationTree) member;

					if (method.name().text().startsWith(EXECUTE) && !method.parameters().parameters().isEmpty()) {
						context().newIssue(this, method, MESSAGE);
					}
				}
			});
		}

		super.visitClassDeclaration(tree);
	}
}
