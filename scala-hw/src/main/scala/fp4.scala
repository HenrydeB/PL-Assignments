  // GENERATED
/* INSTRUCTIONS
 *
 * Complete the exercises below.  For each "EXERCISE" comment, add
 * code immediately below the comment.
 *
 * Please see README.md for instructions, including compilation and testing.
 *
 * GRADING
 *
 * 1. Submissions MUST compile using SBT with UNCHANGED configuration and
 *    tests with no compilation errors.  Submissions with compilation errors
 *    will receive 0 points.  Note that refactoring the code will cause the
 *    tests to fail.
 *
 * 2. You MUST NOT edit the SBT configuration and tests.  Altering it in your
 *    submission will result in 0 points for this assignment.
 *
 * 3. You MUST NOT use while loops.  You must use recursion or higher-order
 *    functions instead.
 *
 * 4. You may declare auxiliary functions if you like.
 *
 * SUBMISSION
 *
 * 1. Submit this file on D2L before the deadline.
 *
 * 2. Late submissions will not be permitted because solutions will be
 *    discussed in class.
 *
 */

object fp4:

 // EXERCISE 1: Complete the following definition, so that "constant5" is a
  // function that returns 5 whenever it is invoked.
  val constant5: () => Int =
      // TODO: Complete the definition.
      () => 5

  // EXERCISE 2: Complete the following definition, so that "constant" is a
  // function that when invoked with integer n returns a function that
  // returns n whenever it is invoked.
  val constant: Int => () => Int =
        // TODO: Complete the definition.
        (n: Int) => () => n //here, n is given when the function is invoked

  
  val sharableConstant: Int => () => Int =
    var x = 0 //here, x has the same lifetime of the constant function itself, so it will be shared
    (n: Int)=>
      x=n
      ()=> x

  // EXERCISE 3: Complete the following definition, so that "counter0" is a
  // (stateful) function that returns 0 when it is first invoked, then 1,
  // then 2, etc.
  //
  // REMEMBER: you can use "var" but everything you add has to be inside the
  // "{...}" body of "counter0".

  // This rule applies throughout this assignment.
  val counter0: () => Int =
    var counter: Int = -1;
    () => {counter += 1; counter}

  // EXERCISE 4: Complete the following definition, so that "counter" is a
  // (stateless) function that when invoked with integer n returns a
  // (stateful) function that returns n when it is first invoked, then n+1,
  // then n+2, etc.
  //
  // The counters must be independent, i.e., running "counter (0)" twice
  // should yield two functions that do not interfere with one another's
  // state.
  val counter: Int => () => Int =
    (n: Int) => {
      var counter = n - 1; //I know, I know, it's not pretty
      () => {counter += 1; counter}
    }

  // EXERCISE 5: Complete the following definition, so that "getAndSet" is a
  // (stateless) function that when invoked with integer n returns a pair of
  // functions (that share state) that allow reading and writing a var that
  // is initialized with integer n.
  //
  // The first function in the pair should be the reader.  The second
  // function in the pair should be the writer.
  //
  // For example, the following expression should return 10:
  // { val (get, set) = getAndSet (5); set (10); get () }
  //
  // Multiple calls to "getAndSet" should yield independent pairs, i.e., the
  // first pair returned should not share any state with the second pair
  // returned.
  val getAndSet: Int => (() => Int, Int => Unit) =
    (n: Int) => {
      var state = n
      var get: () => Int = () => {state}
      var set: Int => Unit = (insert: Int) => {
        state = insert
      }

      (get, set)
    }


  // The remaining problems use the following Counter class.
  // Instances of Counter have a integer field that can be incremented,
  // decremented, or read.
  class Counter:
    private var n = 0
    def increment() = { n = n + 1; n}
    def decrement() = { n = n - 1; n}

  // EXERCISE 6: complete the following function.  The observeCounter
  // function has one parameter f: a function that accepts (a reference to) a
  // Counter instance but returns nothing.
  //
  // The observeCounter function should call f with (a reference to) an
  // object (of a class extending Counter).
  //
  // Your class that extends Counter must keep track of the total number of
  // times that increment/decrement have been called.
  // I.e., if the increment method is called 3 times on an instance, and the
  // decrement method is called 2 times on the same instance, then it should
  // store 5 (somewhere other than the existing field n).
  //
  // observeCounter should call f, and then return the total number of times
  // that increment/decrement were called on the instance by f.
  def observeCounter(f: Counter => Unit): Int =
    class Observer extends Counter {
      var tracker = 0;
      def getTracker() = {tracker}
      override def increment() = {tracker += 1; super.increment()}
      override def decrement() = {tracker += 1; super.decrement()}
    }
    val observer = new Observer;
    f(observer);
    var observerTracker = observer.getTracker();
    observerTracker;

    

  // EXERCISE 7: complete the following function.  It is the same as
  // observeCounter except that f has a parameter of type List[Counter] not
  // Counter.
  //
  // f will insist that the List[Counter] has length 3.
  //
  // You must return a List[Int] not an Int.
  //
  // The first element of the result List[Int] must correspond to the number
  // of times that increment/decrement were called on the first element of
  // type List[Counter], similarly for the second and third elements.
  def observeCounterList(f: List[Counter] => Unit): List[Int] =
      class Observer extends Counter {
            var tracker = 0;
            def getTracker() = {tracker}
            override def increment() = {tracker += 1; super.increment()}
            override def decrement() = {tracker += 1; super.decrement()}
        }
      val o1 = new Observer();
      val o2 = new Observer();
      val o3 = new Observer();

      var counters = List(o1, o2, o3);
      f(counters);
      var incrementerList = counters.map(_.getTracker())
      incrementerList


  // EXERCISE 8: complete the following function.  It is the same as
  // observeCounterList except that f has a parameter of type Array[Counter]
  // not List[Counter].
  //
  // f will insist that the Array[Counter] has length 3.
  //
  // You must return a Array[Int] not a List[Int].
  //
  // The first element of the result Array[Int] must correspond to the number
  // of times that increment/decrement were called on the first element of
  // type Array[Counter], similarly for the second and third elements.
  //
  // In this exercise you are NOT allowed to asInstanceOf.
  def observeCounterArray(f: Array[Counter] => Unit): Array[Int] =
    class Observer  extends Counter {
            var tracker = 0;
            def getTracker() = {tracker}
            override def increment() = {tracker += 1; super.increment()}
            override def decrement() = {tracker += 1; super.decrement()}
        }
      val o1 = new Observer();
      val o2 = new Observer();
      val o3 = new Observer();

      var counters:List[Observer] = o1::o2::o3::Nil
      f(counters.toArray)
      var result = counters.map(_.getTracker())
      result.toArray
      
//==============================================================================
/*
      >> Did my best to  implement traits for this, but I'm not sure I quite get it yet
      >> Or maybe my approach is incorrect? 

     def observeCounterArray(f: Array[Counter] => Unit): Array[Int] =
      class Observer  extends Counter {
              var tracker = 0;
              def getTracker():Int = {tracker}
              override def increment() = {tracker += 1; super.increment()}
              override def decrement() = {tracker += 1; super.decrement()}
          }

      trait CounterObserver[-T] {
        def addToArray(array: Array[Counter], element: Observer): Unit ={
          array:+element
        }
        def applyMethod(array: Array[Counter]): Array[Int]: Array[Int] = {
          array.map{
            case obs: Observer => 
              val tracker = obs.getTracker();
           }
        }
      }
      val o1 = new Observer();
      val o2 = new Observer();
      val o3 = new Observer();

      val counterObserverTrait: CounterObserver = new CounterObserver[Counter];

      val counterArray = Array();

      val res1: Array[Counter] = counterObserverTrait.addToArray(counterArray, o1);
      val res2: Array[Counter] = counterObserverTrait.addToArray(counterArray, o2);
      val res3: Array[Counter] = counterObserverTrait.addToArray(counterArray, o3);

      f(res3)
      var result =  counterObserverTrait.applyMethod(res3); */

      

      
  
  
  
