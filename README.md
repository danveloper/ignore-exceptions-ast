[![Coverage Status](https://coveralls.io/repos/github/danveloper/ignore-exceptions-ast/badge.svg?branch=master)](https://coveralls.io/github/danveloper/ignore-exceptions-ast?branch=master)

Ignore Exceptions Groovy AST Transformation
---

This provides provides a local AST transform to ignore all exceptions thrown from a method.

Usage
---

```groovy
package app

import com.github.danveloper.ast.IgnoreExceptions

class Main {

  @IgnoreExceptions // <1>
  public static void main(String[] args) {
    throw new RuntimeException() // <2>
  }
}
```

<1> Annotate your method with `@IgnoreExceptions`.
<2> Have your method throw an exception.

Run the code and notice that no exception is thrown!

License
---

STEAL THIS CODE PL™

Author
---

https://twitter.com/danveloper[@danveloper]
