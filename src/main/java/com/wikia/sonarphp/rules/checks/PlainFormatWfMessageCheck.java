package com.wikia.sonarphp.rules.checks;

import org.sonar.check.Rule;
import org.sonar.plugins.php.api.tree.Tree.Kind;
import org.sonar.plugins.php.api.tree.expression.FunctionCallTree;
import org.sonar.plugins.php.api.tree.expression.MemberAccessTree;
import org.sonar.plugins.php.api.visitors.PHPVisitorCheck;

import javax.annotation.Nonnull;

@Rule(key = "PlainFormatWfMessage")
public class PlainFormatWfMessageCheck extends PHPVisitorCheck {
	final private static String MESSAGE = "Change the output format of this i18n string away from plain().";
	final private static String PLAIN = "plain";
	final private static String MSG = "msg";
	final private static String WF_MESSAGE = "wfMessage";

	private MemberAccessTree plainMethodAccess;
	private FunctionCallTree msgMethodCall;

	@Override
	public void visitFunctionCall(@Nonnull FunctionCallTree tree) {
		if (plainMethodAccess != null && plainMethodAccess.object().equals(tree)) {
			if (tree.callee().is(Kind.NAMESPACE_NAME) && tree.callee().toString().equals(WF_MESSAGE)) {
				context().newIssue(this, tree, MESSAGE);
			} else if (tree.callee().is(Kind.OBJECT_MEMBER_ACCESS)) {
				msgMethodCall = tree;
			}
		}
		super.visitFunctionCall(tree);
	}

	@Override
	public void visitMemberAccess(@Nonnull MemberAccessTree tree) {
		if (tree.member().is(Kind.NAME_IDENTIFIER)) {
			String propName = tree.member().toString().toLowerCase();

			if (propName.equals(MSG) && msgMethodCall != null && msgMethodCall.callee().equals(tree)) {
				context().newIssue(this, tree, MESSAGE);
			} else if (propName.equals(PLAIN)) {
				plainMethodAccess = tree;
			}
		}

		super.visitMemberAccess(tree);
	}
}
