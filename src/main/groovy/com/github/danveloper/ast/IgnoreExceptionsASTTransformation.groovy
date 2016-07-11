package com.github.danveloper.ast

import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.ast.Parameter
import org.codehaus.groovy.ast.stmt.BlockStatement
import org.codehaus.groovy.ast.stmt.CatchStatement
import org.codehaus.groovy.ast.stmt.TryCatchStatement
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

@GroovyASTTransformation(phase=CompilePhase.SEMANTIC_ANALYSIS)
class IgnoreExceptionsASTTransformation implements ASTTransformation {
  @Override
  void visit(ASTNode[] nodes, SourceUnit source) {
    def method = (MethodNode)nodes[1]
    def existingStatements = ((BlockStatement)method.code).statements

    def tryStatement = new BlockStatement(existingStatements, method.variableScope)
    def catchStatement = new CatchStatement(new Parameter(new ClassNode(Exception), "e"), new BlockStatement())
    def tryCatchStatement = new TryCatchStatement(tryStatement, new BlockStatement())
    tryCatchStatement.addCatch(catchStatement)
    method.setCode(new BlockStatement([tryCatchStatement], method.variableScope))
  }
}
