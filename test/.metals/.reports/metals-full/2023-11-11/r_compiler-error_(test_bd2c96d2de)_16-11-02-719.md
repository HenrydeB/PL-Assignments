file:///C:/Users/henry/source/repos/ProgrammingLanguages/test/Algebraic.scala
### java.lang.AssertionError: NoDenotation.owner

occurred in the presentation compiler.

action parameters:
offset: 1837
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
    
def fromList[X](xs:LIst[@@])
```



#### Error stacktrace:

```
dotty.tools.dotc.core.SymDenotations$NoDenotation$.owner(SymDenotations.scala:2576)
	scala.meta.internal.pc.SignatureHelpProvider$.isValid(SignatureHelpProvider.scala:83)
	scala.meta.internal.pc.SignatureHelpProvider$.notCurrentApply(SignatureHelpProvider.scala:94)
	scala.meta.internal.pc.SignatureHelpProvider$.$anonfun$1(SignatureHelpProvider.scala:48)
	scala.collection.StrictOptimizedLinearSeqOps.loop$3(LinearSeq.scala:280)
	scala.collection.StrictOptimizedLinearSeqOps.dropWhile(LinearSeq.scala:282)
	scala.collection.StrictOptimizedLinearSeqOps.dropWhile$(LinearSeq.scala:278)
	scala.collection.immutable.List.dropWhile(List.scala:79)
	scala.meta.internal.pc.SignatureHelpProvider$.signatureHelp(SignatureHelpProvider.scala:48)
	scala.meta.internal.pc.ScalaPresentationCompiler.signatureHelp$$anonfun$1(ScalaPresentationCompiler.scala:375)
```
#### Short summary: 

java.lang.AssertionError: NoDenotation.owner