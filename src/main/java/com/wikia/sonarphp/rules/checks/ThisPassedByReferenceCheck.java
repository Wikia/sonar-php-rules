package com.wikia.sonarphp.rules.checks;

import org.sonar.check.Rule;
import org.sonar.plugins.php.api.tree.expression.ReferenceVariableTree;
import org.sonar.plugins.php.api.visitors.PHPVisitorCheck;

@Rule(key = "ThisPassedByReference")
public class ThisPassedByReferenceCheck extends PHPVisitorCheck {
  private final static String MESSAGE = "\"$this\" should not be passed by reference.";
  private final static String THIS_REFERENCE = "$this";

  @Override
  public void visitReferenceVariable(ReferenceVariableTree tree) {
    if (THIS_REFERENCE.equals(tree.variableExpression().toString())) {
      context().newIssue(this, tree, MESSAGE);
    }

    super.visitReferenceVariable(tree);
  }

}
