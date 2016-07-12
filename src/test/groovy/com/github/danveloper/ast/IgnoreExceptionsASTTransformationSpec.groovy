package com.github.danveloper.ast

import spock.lang.Specification
import spock.lang.Unroll

import java.util.concurrent.Callable

class IgnoreExceptionsASTTransformationSpec extends Specification {

  @Unroll
  void "should work for #annotation"() {
    given:
    def clazz = (Class<Callable>)new GroovyClassLoader(this.class.classLoader).parseClass("""
      class Any implements java.util.concurrent.Callable {
        @com.github.danveloper.ast.${annotation}
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

    where:
    annotation << ["IgnoreExceptions", "Pokemon"]
  }
}
