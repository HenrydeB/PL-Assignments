file:///C:/Users/henry/source/repos/ProgrammingLanguages/test/Algebraic.scala
### java.nio.file.InvalidPathException: Illegal char <:> at index 3: jar:file:///C:/Users/henry/AppData/Local/Coursier/cache/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.10/scala-library-2.13.10-sources.jar!/scala/collection/immutable/List.scala

occurred in the presentation compiler.

action parameters:
offset: 1863
uri: file:///C:/Users/henry/source/repos/ProgrammingLanguages/test/Algebraic.scala
text:
```scala
def maximum(xs:List[Int]): Option[Int] ={
    xs match{
        case Nil => None
        case y::ys => maximum(ys) match
            case None => Some(y)
            case Some(z) => Some(y max z)
    }
}

def find[X](cs:List[X], p:X=>Boolean):Option[X] =
    cs match 
        case Nil => None
        case y::ys if p(y) => Some(y)
        case _::ys => find(ys, p)

def oneFind (xs:List[String], s:String) : Option[String] = {
  xs.find ((x:String) => (x.length > s.length) && x.contains (s))
}
        
def twoFind (xs:List[String], s:String) : Option[String]= {
    oneFind(xs, s) match 
        case None => None
        case Some(t) => oneFind(xs, t)
}

def flatTwoFind(xs:List[String], s:String) : Option[String] = {
    oneFind(xs, s).flatMap((t:String) => oneFind(xs, t))
} //take the result of the first call of oneFind and apply a flatMap to it

def PotatoList(counter:Int): Unit = 
    if counter <= 4 then 
        println("%d potatio".format(counter))
        PotatoList(counter+1)


def counter(xs:List[Int], low:Int, high:Int) : Int = {
    def helper(cs:List[Int], count:Int) : Int = {
        cs match 
            case Nil => count
            case head::tail => if low >= head && head <= high then helper(tail, count+1) else helper(tail, count)
    }
    helper(xs, 0)
}

/* 
Which functions are tail recursive?
- no
- no?
- no
- yes
- no
- no
- yes
- yes
- yes
 */
enum MyList[+X]:
    case MyNil
    case MyCons(head:X, tail:MyList[X])
import MyList.* 

def length[X] (xs:MyList[X]) : Int = 
    xs match
        case MyNil => 0
        case MyCons(_, tail) => 1 + length(tail)

def toList[X] (xs:MyList[X]) : List[X] =
    xs match
        case MyNil => List()
        case MyCons(head, tail) => head::toList(tail)
    
def fromList[X](xs:List[X]): MyList[X] =
    xs m@@
```



#### Error stacktrace:

```
java.base/sun.nio.fs.WindowsPathParser.normalize(WindowsPathParser.java:182)
	java.base/sun.nio.fs.WindowsPathParser.parse(WindowsPathParser.java:153)
	java.base/sun.nio.fs.WindowsPathParser.parse(WindowsPathParser.java:77)
	java.base/sun.nio.fs.WindowsPath.parse(WindowsPath.java:92)
	java.base/sun.nio.fs.WindowsFileSystem.getPath(WindowsFileSystem.java:232)
	java.base/java.nio.file.Path.of(Path.java:147)
	java.base/java.nio.file.Paths.get(Paths.java:69)
	scala.meta.io.AbsolutePath$.apply(AbsolutePath.scala:60)
	scala.meta.internal.metals.MetalsSymbolSearch.$anonfun$definitionSourceToplevels$2(MetalsSymbolSearch.scala:62)
	scala.Option.map(Option.scala:242)
	scala.meta.internal.metals.MetalsSymbolSearch.definitionSourceToplevels(MetalsSymbolSearch.scala:61)
	scala.meta.internal.pc.completions.CaseKeywordCompletion$.sortSubclasses(MatchCaseCompletions.scala:306)
	scala.meta.internal.pc.completions.CaseKeywordCompletion$.matchContribute(MatchCaseCompletions.scala:254)
	scala.meta.internal.pc.completions.Completions.advancedCompletions(Completions.scala:375)
	scala.meta.internal.pc.completions.Completions.completions(Completions.scala:183)
	scala.meta.internal.pc.completions.CompletionProvider.completions(CompletionProvider.scala:86)
	scala.meta.internal.pc.ScalaPresentationCompiler.complete$$anonfun$1(ScalaPresentationCompiler.scala:123)
```
#### Short summary: 

java.nio.file.InvalidPathException: Illegal char <:> at index 3: jar:file:///C:/Users/henry/AppData/Local/Coursier/cache/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.10/scala-library-2.13.10-sources.jar!/scala/collection/immutable/List.scala