
package com.check;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.mockito.Matchers.startsWith;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.check.RuleProperty;
import org.sonar.duplications.statement.Statement;
import org.sonar.java.ast.parser.BlockStatementListTreeImpl;
import org.sonar.java.model.declaration.MethodTreeImpl;
import org.sonar.java.model.statement.ExpressionStatementTreeImpl;
import org.sonar.java.tag.Tag;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.AssertStatementTree;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.BlockTree;
import org.sonar.plugins.java.api.tree.CaseGroupTree;
import org.sonar.plugins.java.api.tree.CatchTree;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.DoWhileStatementTree;
import org.sonar.plugins.java.api.tree.ExpressionStatementTree;
import org.sonar.plugins.java.api.tree.ExpressionTree;
import org.sonar.plugins.java.api.tree.ForEachStatement;
import org.sonar.plugins.java.api.tree.ForStatementTree;
import org.sonar.plugins.java.api.tree.IdentifierTree;
import org.sonar.plugins.java.api.tree.IfStatementTree;
import org.sonar.plugins.java.api.tree.LabeledStatementTree;
import org.sonar.plugins.java.api.tree.MemberSelectExpressionTree;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.StatementTree;
import org.sonar.plugins.java.api.tree.SwitchStatementTree;
import org.sonar.plugins.java.api.tree.ThrowStatementTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.TypeTree;
import org.sonar.plugins.java.api.tree.VariableTree;
import org.sonar.plugins.java.api.tree.Tree.Kind;
import org.sonar.plugins.java.api.tree.TryStatementTree;
import org.sonar.squidbridge.annotations.ActivatedByDefault;
import org.sonar.squidbridge.annotations.SqaleConstantRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;
import org.sonar.sslr.internal.ast.select.EmptyAstSelect;

@Rule(key = "TestIsDebugEnableCheckRule", name = "TestC Check isDebugEnable Present before logging", description = "TestC - This rule detects existence of isDebugEnable before logging.", priority = Priority.MAJOR, tags = {
		Tag.CONVENTION })

@ActivatedByDefault
@SqaleSubCharacteristic(RulesDefinition.SubCharacteristics.READABILITY)
@SqaleConstantRemediation("2min")
//public class TestAvoidLargerLengthVariableNameRule extends IssuableSubscriptionVisitor implements JavaFileScanner{//extends BaseTreeVisitor implements JavaFileScanner {
public class SymIsDebugEnableCheckRule2 extends BaseTreeVisitor implements JavaFileScanner {

	private static final String DEFAULT_VALUE = "isDebugEnableCheck";

	private JavaFileScannerContext context;

	private Deque<StatementTree> statementsStack = new LinkedList<>();

	/**
	 * Avoid usage of the larger length in local variable name in Quality profiles.
	 * The key
	 */
	@RuleProperty(key = "Testcnew4", defaultValue = DEFAULT_VALUE, description = "Check isDebugEnable Present before logging.")
	protected String name;

	@Override
	public void scanFile(JavaFileScannerContext context) {
		this.context = context;

		scan(context.getTree());

	}

//	@Override
//	public void visitClass(ClassTree tree) {
//		System.out.println("In method visitClass" + tree.members().size());
//		for (Tree member : tree.members()) {
//			System.out.println("1"+tree.simpleName().name());
//			System.out.println("2"+member.kind().name().toString());
//			if (member.is(Tree.Kind.EXPRESSION_STATEMENT)) {
//				System.out.println("YES" + member.toString());
//			}
//			if (member.is(Tree.Kind.METHOD_INVOCATION)) {
//				System.out.println("METHOD_INVOCATION" + member.toString());
//			}
//			if (member.is(Tree.Kind.VARIABLE)) {
//				System.out.println("VARIABLE" + member.toString());
//			}
//		}
//	}

	public void processingStatement(StatementTree st) {
		IfStatementTree ist1 = (IfStatementTree) st;
		MethodInvocationTree conditionTree = (MethodInvocationTree) ist1.condition();
		MemberSelectExpressionTree imset = (MemberSelectExpressionTree) conditionTree.methodSelect();
		IdentifierTree id = (IdentifierTree) imset.identifier();
		String value = id.name();
		if ("isDebugEnabled".equals(value)) {
			BlockTree bt = (BlockTree) ist1.thenStatement();
			List<StatementTree> bslt = bt.body();
			for (StatementTree et : bslt) {
				ExpressionStatementTree est = (ExpressionStatementTree) et;
				if (est.expression() instanceof MethodInvocationTree) {
					MethodInvocationTree est1 = (MethodInvocationTree) est.expression();
					MemberSelectExpressionTree mset = (MemberSelectExpressionTree) est1.methodSelect();
					IdentifierTree it = (IdentifierTree) mset.identifier();
					String name = it.name();
					if ("debug".equals(name)) {
						System.out.println("--->::NO ISSUE");
					}
				}
			}
		}
	}

	private String processingFirstExpression(ExpressionStatementTree est) {

		MethodInvocationTree est1 = (MethodInvocationTree) est.expression();
		MemberSelectExpressionTree mset = (MemberSelectExpressionTree) est1.methodSelect();
		IdentifierTree it = (IdentifierTree) mset.identifier();
		return it.name();
	}

	private String processingSecondExpression(StatementTree lastStmt) {

		IfStatementTree ist = (IfStatementTree) lastStmt;
		MethodInvocationTree conditionTree = (MethodInvocationTree) ist.condition();
		MemberSelectExpressionTree imset = (MemberSelectExpressionTree) conditionTree.methodSelect();
		IdentifierTree id = (IdentifierTree) imset.identifier();
		return id.name();
	}

	/**
	 * visitMethod
	 */

	private static SymIsDebugEnableCheckRule2 getInstance() {
		return new SymIsDebugEnableCheckRule2();
	}

	/*
	 * @Override public void visitMethod(MethodTree tree) { List<StatementTree> list
	 * = tree.block().body(); for (StatementTree st : list) {
	 * System.out.println("statementsStack.size()" + statementsStack.size()); if
	 * (st.is(Tree.Kind.IF_STATEMENT)) { // calling statement
	 * SymIsDebugEnableCheckRule.getInstance().processingStatement(st); } if
	 * (st.is(Tree.Kind.EXPRESSION_STATEMENT)) { ExpressionStatementTree est =
	 * (ExpressionStatementTree) st; if (est.expression() instanceof
	 * MethodInvocationTree) { // calling expression-1 String name =
	 * SymIsDebugEnableCheckRule.getInstance().processingFirstExpression(est); if
	 * ("debug".equals(name)) { if (!statementsStack.isEmpty()) { StatementTree
	 * lastStmt = statementsStack.pop(); System.out.println("statementsStack.size()"
	 * + statementsStack.size()); if (lastStmt.is(Tree.Kind.IF_STATEMENT)) {
	 * 
	 * // calling expression-2 String value =
	 * SymIsDebugEnableCheckRule.getInstance()
	 * .processingSecondExpression(lastStmt); if (!"isDebugEnabled".equals(value)) {
	 * context.reportIssue(this, tree,
	 * "TestC LOG RULE: isDebugEnable missing missing before logger statement");
	 * System.out.println(
	 * "visitMethod::REPORT ISSUE: isDebugEnable missing before logger statement");
	 * } System.out.println("visitMethod::NO ISSUE"); } else {
	 * context.reportIssue(this, tree,
	 * "TestC LOG RULE: isDebugEnable missing before logger statement");
	 * System.out.println(
	 * "visitMethod::REPORT ISSUE: isDebugEnable missing before logger statement");
	 * } } } } else { if (!statementsStack.isEmpty()) {// POP is done as only last
	 * element is sufficient statementsStack.pop(); } statementsStack.push(st); }
	 * System.out.println("statementsStack.size()" + statementsStack.size()); } else
	 * { if (!statementsStack.isEmpty()) {// POP is done as only last element is
	 * sufficient statementsStack.pop(); } statementsStack.push(st);
	 * System.out.println("statementsStack.size()" + statementsStack.size()); } }
	 * super.visitMethod(tree); }
	 */

	/**
	 * visitTryStatement
	 */
	@Override
	public void visitTryStatement(TryStatementTree tree) {
		List<StatementTree> list = tree.block().body();
		for (StatementTree st : list) {
			System.out.println("statementsStack.size()" + statementsStack.size());
			if (st.is(Tree.Kind.IF_STATEMENT)) {
				// calling statement
				SymIsDebugEnableCheckRule2.getInstance().processingStatement(st);
			}
			if (st.is(Tree.Kind.EXPRESSION_STATEMENT)) {
				ExpressionStatementTree est = (ExpressionStatementTree) st;
				if (est.expression() instanceof MethodInvocationTree) {
					// calling expression-1
					String name = SymIsDebugEnableCheckRule2.getInstance().processingFirstExpression(est);
					if ("debug".equals(name)) {
						if (!statementsStack.isEmpty()) {
							StatementTree lastStmt = statementsStack.pop();
							System.out.println("statementsStack.size()" + statementsStack.size());
							if (lastStmt.is(Tree.Kind.IF_STATEMENT)) {

								// calling expression-2
								String value = SymIsDebugEnableCheckRule2.getInstance()
										.processingSecondExpression(lastStmt);
								if (!"isDebugEnabled".equals(value)) {
									context.reportIssue(this, tree,
											"TestC LOG RULE: isDebugEnable missing missing before logger statement");
									System.out.println(
											"visitTryStatement::REPORT ISSUE: isDebugEnable missing before logger statement");
								}
								System.out.println("visitTryStatement::NO ISSUE");
							} else {
								context.reportIssue(this, tree,
										"TestC LOG RULE: isDebugEnable missing before logger statement");
								System.out.println(
										"visitTryStatement::REPORT ISSUE: isDebugEnable missing before logger statement");
							}
						}
					}
				} else {
					if (!statementsStack.isEmpty()) {// POP is done as only last element is sufficient
						statementsStack.pop();
					}
					statementsStack.push(st);
				}
				System.out.println("statementsStack.size()" + statementsStack.size());
			} else {
				if (!statementsStack.isEmpty()) {// POP is done as only last element is sufficient
					statementsStack.pop();
				}
				statementsStack.push(st);
				System.out.println("statementsStack.size()" + statementsStack.size());
			}
		}
		super.visitTryStatement(tree);
	}

	/**
	 * visitCatch
	 */
	@Override
	public void visitCatch(CatchTree tree) {
		List<StatementTree> list = tree.block().body();
		for (StatementTree st : list) {
			System.out.println("statementsStack.size()" + statementsStack.size());
			if (st.is(Tree.Kind.IF_STATEMENT)) {
				IfStatementTree ist1 = (IfStatementTree) st;
				MethodInvocationTree conditionTree = (MethodInvocationTree) ist1.condition();
				MemberSelectExpressionTree imset = (MemberSelectExpressionTree) conditionTree.methodSelect();
				IdentifierTree id = (IdentifierTree) imset.identifier();
				String value = id.name();
				if ("isDebugEnabled".equals(value)) {
					BlockTree bt = (BlockTree) ist1.thenStatement();
					List<StatementTree> bslt = bt.body();
					for (StatementTree et : bslt) {
						ExpressionStatementTree est = (ExpressionStatementTree) et;
						if (est.expression() instanceof MethodInvocationTree) {
							MethodInvocationTree est1 = (MethodInvocationTree) est.expression();
							MemberSelectExpressionTree mset = (MemberSelectExpressionTree) est1.methodSelect();
							IdentifierTree it = (IdentifierTree) mset.identifier();
							String name = it.name();
							if ("debug".equals(name)) {
								System.out.println("visitCatch::NO ISSUE");
							}
						}
					}
				}
			}
			if (st.is(Tree.Kind.EXPRESSION_STATEMENT)) {
				ExpressionStatementTree est = (ExpressionStatementTree) st;
				if (est.expression() instanceof MethodInvocationTree) {
					MethodInvocationTree est1 = (MethodInvocationTree) est.expression();
					MemberSelectExpressionTree mset = (MemberSelectExpressionTree) est1.methodSelect();
					IdentifierTree it = (IdentifierTree) mset.identifier();
					String name = it.name();
					if ("debug".equals(name)) {
						if (!statementsStack.isEmpty()) {
							StatementTree lastStmt = statementsStack.pop();
							System.out.println("statementsStack.size()" + statementsStack.size());
							if (lastStmt.is(Tree.Kind.IF_STATEMENT)) {
								IfStatementTree ist = (IfStatementTree) lastStmt;
								MethodInvocationTree conditionTree = (MethodInvocationTree) ist.condition();
								MemberSelectExpressionTree imset = (MemberSelectExpressionTree) conditionTree
										.methodSelect();
								IdentifierTree id = (IdentifierTree) imset.identifier();
								String value = id.name();
								if (!"isDebugEnabled".equals(value)) {
									context.reportIssue(this, tree,
											"TestC LOG RULE: isDebugEnable missing missing before logger statement");
									System.out.println(
											"visitCatch::REPORT ISSUE: isDebugEnable missing before logger statement");
								}
								System.out.println("visitCatch::NO ISSUE");
							} else {
								context.reportIssue(this, tree,
										"TestC LOG RULE: isDebugEnable missing before logger statement");
								System.out.println(
										"visitCatch::REPORT ISSUE: isDebugEnable missing before logger statement");
							}
						}
					}
				} else {
					if (!statementsStack.isEmpty()) {// POP is done as only last element is sufficient
						statementsStack.pop();
					}
					statementsStack.push(st);
				}
				System.out.println("statementsStack.size()" + statementsStack.size());
			} else {
				if (!statementsStack.isEmpty()) {// POP is done as only last element is sufficient
					statementsStack.pop();
				}
				statementsStack.push(st);
				System.out.println("statementsStack.size()" + statementsStack.size());
			}
		}
		super.visitCatch(tree);
	}

	/**
	 * visitSwitchStatement
	 */
	@Override
	public void visitSwitchStatement(SwitchStatementTree tree) {
		List<CaseGroupTree> caseGroupTrees = tree.cases();
		List<StatementTree> statementTrees = new ArrayList<StatementTree>();
		for (CaseGroupTree caseGroupTree : caseGroupTrees) {
			statementTrees.addAll(caseGroupTree.body());
		}

		for (StatementTree st : statementTrees) {
			System.out.println("statementsStack.size()" + statementsStack.size() + "statementTrees" + st);
			if (st.is(Tree.Kind.IF_STATEMENT)) {
				IfStatementTree ist1 = (IfStatementTree) st;
				MethodInvocationTree conditionTree = (MethodInvocationTree) ist1.condition();
				MemberSelectExpressionTree imset = (MemberSelectExpressionTree) conditionTree.methodSelect();
				IdentifierTree id = (IdentifierTree) imset.identifier();
				String value = id.name();
				if ("isDebugEnabled".equals(value)) {
					BlockTree bt = (BlockTree) ist1.thenStatement();
					List<StatementTree> bslt = bt.body();
					for (StatementTree et : bslt) {
						ExpressionStatementTree est = (ExpressionStatementTree) et;
						if (est.expression() instanceof MethodInvocationTree) {
							MethodInvocationTree est1 = (MethodInvocationTree) est.expression();
							MemberSelectExpressionTree mset = (MemberSelectExpressionTree) est1.methodSelect();
							IdentifierTree it = (IdentifierTree) mset.identifier();
							String name = it.name();
							if ("debug".equals(name)) {
								System.out.println("visitSwitchStatement::NO ISSUE");
							}
						}
					}
				}
			}
			if (st.is(Tree.Kind.EXPRESSION_STATEMENT)) {
				ExpressionStatementTree est = (ExpressionStatementTree) st;
				if (est.expression() instanceof MethodInvocationTree) {
					MethodInvocationTree est1 = (MethodInvocationTree) est.expression();
					MemberSelectExpressionTree mset = (MemberSelectExpressionTree) est1.methodSelect();
					IdentifierTree it = (IdentifierTree) mset.identifier();
					String name = it.name();
					if ("debug".equals(name)) {
						if (!statementsStack.isEmpty()) {
							StatementTree lastStmt = statementsStack.pop();
							System.out.println("statementsStack.size()" + statementsStack.size());
							if (lastStmt.is(Tree.Kind.IF_STATEMENT)) {
								IfStatementTree ist = (IfStatementTree) lastStmt;
								MethodInvocationTree conditionTree = (MethodInvocationTree) ist.condition();
								MemberSelectExpressionTree imset = (MemberSelectExpressionTree) conditionTree
										.methodSelect();
								IdentifierTree id = (IdentifierTree) imset.identifier();
								String value = id.name();
								if (!"isDebugEnabled".equals(value)) {
									context.reportIssue(this, tree,
											"TestC LOG RULE: isDebugEnable missing missing before logger statement");
									System.out.println(
											"visitSwitchStatement::REPORT ISSUE: isDebugEnable missing before logger statement");
								}
								System.out.println("visitSwitchStatement::NO ISSUE");
							} else {
								context.reportIssue(this, tree,
										"TestC LOG RULE: isDebugEnable missing before logger statement");
								System.out.println(
										"visitSwitchStatement::REPORT ISSUE: isDebugEnable missing before logger statement");
							}
						}
					}
				} else {
					if (!statementsStack.isEmpty()) {// POP is done as only last element is sufficient
						statementsStack.pop();
					}
					statementsStack.push(st);
				}
				System.out.println("statementsStack.size()" + statementsStack.size());
			} else {
				if (!statementsStack.isEmpty()) {// POP is done as only last element is sufficient
					statementsStack.pop();
				}
				statementsStack.push(st);
				System.out.println("statementsStack.size()" + statementsStack.size());
			}
		}
		super.visitSwitchStatement(tree);
	}

	/**
	 * visitForStatement
	 */
	public void visitForEachStatement(ForEachStatement tree) {

		BlockTree btf = (BlockTree) tree.statement();
		List<StatementTree> bsltf = btf.body();
		for (StatementTree et : bsltf) {
			if (et instanceof ExpressionStatementTree) {
				if (et.is(Tree.Kind.EXPRESSION_STATEMENT)) {
					ExpressionStatementTree est = (ExpressionStatementTree) et;
					if (est.expression() instanceof MethodInvocationTree) {
						MethodInvocationTree est1 = (MethodInvocationTree) est.expression();
						if (est1.methodSelect() instanceof MemberSelectExpressionTree) {
							MemberSelectExpressionTree mset = (MemberSelectExpressionTree) est1.methodSelect();
							IdentifierTree it = (IdentifierTree) mset.identifier();
							String name = it.name();
							if ("debug".equals(name)) {
								context.reportIssue(this, tree,
										" LOG RULE FOR: visitForEachStatement -->isDebugEnable missing missing before logger statement");
								System.out.println("REPORT ISSUE FOR: isDebugEnable missing before logger statement");
							}
						}
					}
				}
			} else {
				if (et.is(Tree.Kind.IF_STATEMENT)) {
					IfStatementTree ist = (IfStatementTree) et;
					visitIfStatement(ist);
				}
			}
		}
	}

	/*
	 * @Override public void visitExpressionStatement(ExpressionStatementTree tree)
	 * { if (tree.expression() instanceof MethodInvocationTree) {
	 * MethodInvocationTree est1 = (MethodInvocationTree) tree.expression(); if
	 * (est1.methodSelect() instanceof MemberSelectExpressionTree) {
	 * MemberSelectExpressionTree mset = (MemberSelectExpressionTree)
	 * est1.methodSelect(); IdentifierTree it = (IdentifierTree) mset.identifier();
	 * String name = it.name(); if ("debug".equals(name)) {
	 * context.reportIssue(this, tree,
	 * " LOG RULE EXPRESSION:isDebugEnable missing missing before logger statement"
	 * ); System.out.
	 * println("REPORT ISSUE EXPRESSION: isDebugEnable missing before logger statement"
	 * ); } } } }
	 */
	/*
	 * boolean flag=false;
	 * 
	 * @Override public void visitClass(ClassTree tree) { System.out.println(tree);
	 * List<Tree> members=tree.members(); for(Tree member:members) {
	 * if(member.is(Kind.VARIABLE)) { VariableTree vt = (VariableTree) member;
	 * if(vt.type().equals("Logger")) { flag=true; }
	 * System.out.println("type--"+vt.type()); System.out.println("variable"); }
	 * System.out.println("---> member"+member.getClass().getName()); } }
	 */
	@Override
	public void visitMethod(MethodTree tree)  {
		List<StatementTree> bsltf= tree.block().body();
		//BlockTree btf = (BlockTree) tree.statement();
		//List<StatementTree> bsltf = btf.body();
		for (StatementTree et : bsltf) {
			if (et instanceof ExpressionStatementTree) {
				if(et.is(Tree.Kind.EXPRESSION_STATEMENT)) {
					ExpressionStatementTree est = (ExpressionStatementTree) et;
					if (est.expression() instanceof MethodInvocationTree) {
						MethodInvocationTree est1 = (MethodInvocationTree) est.expression();
						if (est1.methodSelect() instanceof MemberSelectExpressionTree) {
							MemberSelectExpressionTree mset = (MemberSelectExpressionTree) est1.methodSelect();
							IdentifierTree it = (IdentifierTree) mset.identifier();
							String name = it.name();
							if ("debug".equals(name)) {
								context.reportIssue(this, tree,
										" LOG RULE FOR: isDebugEnable missing missing before logger statement");
								System.out.println("REPORT ISSUE FOR: isDebugEnable missing before logger statement");
							}
						}
					}
				}
			} else {
				if (et.is(Tree.Kind.IF_STATEMENT)) {
					IfStatementTree ist = (IfStatementTree) et;
					visitIfStatement(ist);
				}
			}
		}
	}
	
	@Override
	public void visitExpressionStatement(ExpressionStatementTree tree) {
		if (tree.expression() instanceof MethodInvocationTree) {
			MethodInvocationTree est1 = (MethodInvocationTree) tree.expression();
			if (est1.methodSelect() instanceof MemberSelectExpressionTree) {
				MemberSelectExpressionTree mset = (MemberSelectExpressionTree) est1.methodSelect();
				IdentifierTree it = (IdentifierTree) mset.identifier();
				String name = it.name();
				if ("debug".equals(name)) {
					context.reportIssue(this, tree,
							" LOG RULE EXPRESSION: isDebugEnable missing missing before logger statement");
					System.out.println("REPORT ISSUE EXPRESSION: isDebugEnable missing before logger statement");
				}
			}
		}
	}
}

//	@Override
//	  public void visitMethodInvocation(MethodInvocationTree tree) {
//		System.out.println("METHODINV:"+tree.methodSelect().toString());
//		System.out.println("METHODINV:"+tree.methodSelect().TestbolType());
//	  }
//	
//	@Override
//	public void visitLabeledStatement(LabeledStatementTree tree) {
//		System.out.println("METHODLAB:"+tree.Testbol().name());
//	    scan(tree.label());
//	    scan(tree.statement());
//	}
//	
//	 @Override
//	 public void visitVariable(VariableTree tree) {
//		 String variableName = tree.simpleName().name();
//		 if(tree.Testbol().type().is("org.apache.log4j.Logger")) {
//			 System.out.println("VARIABLE IS LOGGER" + variableName);
//		 }
//		 System.out.println("VARIABLE : " + variableName);
//	 }

//	  @Override
//	  public void visitNode(Tree tree) {
//	        ExpressionTree expressionTree = ((ExpressionStatementTree) tree).expression();
//	        System.out.println("AA" + expressionTree.kind());
//	        System.out.println("BB" + expressionTree.TestbolType().name());
//	        System.out.println("DD" + expressionTree.toString());
//	  }
//
//	@Override
//	public List<Kind> nodesToVisit() {
//		// TODO Auto-generated method stub
//		return null;
//	}

// @Override
// public void visitVariable(VariableTree tree) {
// String variableName = tree.simpleName().name();
// System.out.println("BBBBBBBB Scanning the variable : " + variableName);
//
// if(variableName.length() > 10) {
// //context.addIssue(tree, this, "ZZZZVariable length is less than 4
// characters");
// context.reportIssue(this, tree, "TestC NEW2 Variable length is more than 10
// characters");
// System.out.println("BBBBBBB Rule Issue for variable : " + variableName);
// }
//
// super.visitVariable(tree);
// }
