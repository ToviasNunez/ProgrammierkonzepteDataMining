file:///D:/GitHub/ProgrammierkonzepteDataMining/scalatest-example-vscode/src/main/scala/com/example/App.scala
### java.lang.StringIndexOutOfBoundsException: String index out of range: 109

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
offset: 108
uri: file:///D:/GitHub/ProgrammierkonzepteDataMining/scalatest-example-vscode/src/main/scala/com/example/App.scala
text:
```scala
package com.example



@main def runCube() =
  println("Hello I am here")

var cube1 = new CubeCalculator(3)@@

```



#### Error stacktrace:

```
java.base/java.lang.StringLatin1.charAt(Unknown Source)
	java.base/java.lang.String.charAt(Unknown Source)
	scala.meta.internal.mtags.CommonMtagsEnrichments$XtensionRangeParams.isWhitespace$1(CommonMtagsEnrichments.scala:85)
	scala.meta.internal.mtags.CommonMtagsEnrichments$XtensionRangeParams.trim$1$$anonfun$1(CommonMtagsEnrichments.scala:89)
	scala.Option.filter(Option.scala:319)
	scala.meta.internal.mtags.CommonMtagsEnrichments$XtensionRangeParams.trim$1(CommonMtagsEnrichments.scala:89)
	scala.meta.internal.mtags.CommonMtagsEnrichments$XtensionRangeParams.trimWhitespaceInRange(CommonMtagsEnrichments.scala:94)
	dotty.tools.pc.utils.InteractiveEnrichments$.sourcePosition(InteractiveEnrichments.scala:46)
	dotty.tools.pc.HoverProvider$.hover(HoverProvider.scala:46)
	dotty.tools.pc.ScalaPresentationCompiler.hover$$anonfun$1(ScalaPresentationCompiler.scala:363)
```
#### Short summary: 

java.lang.StringIndexOutOfBoundsException: String index out of range: 109