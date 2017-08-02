package com.wikia.sonarphp.rules.checks;

import org.sonar.check.Rule;
import org.sonar.plugins.php.api.tree.Tree.Kind;
import org.sonar.plugins.php.api.tree.expression.FunctionCallTree;
import org.sonar.plugins.php.api.tree.expression.MemberAccessTree;
import org.sonar.plugins.php.api.visitors.PHPVisitorCheck;

@Rule(key = "PlainFormatWfMessage")
public class PlainFormatWfMessageCheck extends PHPVisitorCheck {
	private final static String MESSAGE = "Change the output format of this i18n string away from plain().";
	private final static String PLAIN = "plain";
	private final static String MSG = "msg";
	private final static String WF_MESSAGE = "wfMessage";

	private MemberAccessTree plainMethodAccess;
	private FunctionCallTree msgMethodCall;

	@Override
	public void visitFunctionCall(FunctionCallTree tree) {
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
	public void visitMemberAccess(MemberAccessTree tree) {
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
