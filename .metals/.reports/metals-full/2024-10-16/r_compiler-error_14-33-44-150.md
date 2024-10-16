file:///D:/GitHub/ProgrammierkonzepteDataMining/UdemyScalatoMaster/src/main/scala/List_Arrays/List_Array.scala
### java.lang.IndexOutOfBoundsException: 0

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 3.3.3
Classpath:
<HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\scala3-library_3\3.3.3\scala3-library_3-3.3.3.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\scala-library\2.13.12\scala-library-2.13.12.jar [exists ]
Options:



action parameters:
offset: 333
uri: file:///D:/GitHub/ProgrammierkonzepteDataMining/UdemyScalatoMaster/src/main/scala/List_Arrays/List_Array.scala
text:
```scala
@main
def List_Array(): Unit = {
 val l = List(1,2,3)
 println(l)

 val m = List(1,true ,"hello")

 println(m)
 val n = List()
 val o = Nil

 println(n)
 println(o)

 //val p =  List[Int](1,22,3,3 , "Hello")
 //println(p)

 val matrix = List(
    List(1,0,0)
    ,List(0,1,0),
    List(0,0,1)
 )

 println(matrix)
  println(matrix()(@@))
}
```



#### Error stacktrace:

```
scala.collection.LinearSeqOps.apply(LinearSeq.scala:131)
	scala.collection.LinearSeqOps.apply$(LinearSeq.scala:128)
	scala.collection.immutable.List.apply(List.scala:79)
	dotty.tools.dotc.util.Signatures$.countParams(Signatures.scala:501)
	dotty.tools.dotc.util.Signatures$.applyCallInfo(Signatures.scala:186)
	dotty.tools.dotc.util.Signatures$.computeSignatureHelp(Signatures.scala:94)
	dotty.tools.dotc.util.Signatures$.signatureHelp(Signatures.scala:63)
	scala.meta.internal.pc.MetalsSignatures$.signatures(MetalsSignatures.scala:17)
	scala.meta.internal.pc.SignatureHelpProvider$.signatureHelp(SignatureHelpProvider.scala:51)
	scala.meta.internal.pc.ScalaPresentationCompiler.signatureHelp$$anonfun$1(ScalaPresentationCompiler.scala:435)
```
#### Short summary: 

java.lang.IndexOutOfBoundsException: 0