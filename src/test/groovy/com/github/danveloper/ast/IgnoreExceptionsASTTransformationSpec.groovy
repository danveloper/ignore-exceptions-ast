package com.github.danveloper.ast

import spock.lang.Specification

import java.util.concurrent.Callable

class IgnoreExceptionsASTTransformationSpec extends Specification {

  void "should work"() {
    given:
    def clazz = (Class<Callable>)new GroovyClassLoader(this.class.classLoader).parseClass("""
      class Any implements java.util.concurrent.Callable {
        @com.github.danveloper.ast.IgnoreExceptions
        def call() {
          throw new RuntimeException()
        }
      }
    """)

    and:
    def obj = clazz.newInstance()

    when:
    obj.call()

    then:
    notThrown(RuntimeException)
  }
}
