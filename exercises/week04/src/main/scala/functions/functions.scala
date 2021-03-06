package functions

object Funcs {

  // FUNCTIONAL BASICS:

  /**
    * tail that takes a list and removes the first element, returning the rest
    * of the list.
    * Calling tail on an empty list throws an IllegalArgumentException.
    *
    * @param ls : List[A] the list to process
    * @return A list containing all but the first element of ls
    */
  def tail[A](ls: List[A]): List[A] = ls match {
    case head :: tail => tail
    case tail => tail
    case Nil => throw new IllegalArgumentException
    }

//Keith's answer:
//  def tail[A](ls: List[A]): List[A] =
//    ls match {
//        case _ :: tl => tl
//        case Nil => throw new IllegalArgumentException
//    }

  /**
    * setHead replaces the first value in a list with a given value. If the
    * list is empty, it adds the value to the front of the list.
    *
    * @param ls : List[A] the list to be changed
    * @param a  : A the value that will replace the head of ls
    * @return a list whose head is `a' and whose tail is all but the first
    *         element of ls.
    **/

  def setHead[A](ls: List[A], a: A): List[A] = ls match {
    case Nil => List(a)
    case head :: tail => a :: tail
  }

  /**
    * drop removes n elements from the given list. If n is greater than the
    * length of the list, the function returns an empty list.
    *
    * @param ls : List[A] the list to be changed
    * @param n  : Int the number of elements to drop.
    * @return a list with the first n elements of ls removed, or an empty list.
    */

  def drop[A](ls: List[A], n: Int): List[A] = ls match {
    case Nil => Nil
    case head :: tail => n match {
      case 1 => tail
      case _ => drop(tail, n - 1)
    }
  }

  /**
    * init takes a list and removes the last element.
    * Like tail, init(Nil) throws an IllegalArgumentException.
    * Implement this function recursively, preferably using match.
    *
    * @param ls : List[A] the list to be changed.
    * @return a list with the last element of ls removed.
    */
  def init[A](ls: List[A]): List[A] = ls match {
    case Nil => throw new IllegalArgumentException // empty so error
    case head :: Nil => Nil //Nil represents the empty list.  If only one item in list (i.e. head), can return Nil as are dropping that item
    case head :: tail => head :: init(tail) // take the last item of list then look within it recursively to get last item of it
  }

  //Keith's answer
//  def init[A](ls: List[A]): List[A] = ls match {
//    case Nil => throw new IllegalArgumentException
//    case _ :: Nil => Nil
//    case hd :: tl => hd :: init(tl)
//  }

  // LIST FOLDING

  /*
 * foldLeft reduces a list down to a single value by iteratively applying a
 * function over the elements of the list and carrying the cumulative result
 * along.
 *
 * The function is tail recursive.
 * z is the accumulative result
 * ls is the current list which we are reducing
 * f is the function being used to do the reduction e.g. add a to B and return B
 * two params are going in and the first one is a pair - foldLeft(list,accumulator)(function)
 * foldLeft starts on the left whereas foldRight starts on the right
 * @param ls: List[A] the list to be reduced.
 * @param z: B the initial value
 * @param f: (B, A) => B the binary function applied to the elements of the
 * list and the cumulative value.
 * @return the final valued.
 */
  def foldLeft[A, B](ls: List[A], z: B)(f: (B, A) => B): B = ls match {
    case head :: tail => foldLeft(tail, f(z, head))(f)
    // if list contains head and tail, apply the function to (z and the head) (e.g add head to z) and then recursively call func again
    // with the result of that as a new z, and the tail
    // dont need an extra case for head :: Nil as we are using two items together for the function (e.g. to add them)
    case Nil => z
  }

  //keith's answer
//  def foldLeft[A, B](ls: List[A], z: B)(f: (B, A) => B): B = ls match {
//    case hd :: tl => foldLeft(tl, f(z,hd))(f)
//    case Nil => z
//  }

  /**
    * Use your implementation of foldLeft to implement these functions:
    * - sum: Takes a List[Double] and produces the sum of all elements
    * - product: Takes a List[Double] and produces the product of all elements
    * - length: Takes a List[A] and finds the length of the list.
    * - reverse: Takes a List[A] and produces a new list with the elements of
    * the first list in reverse order. That is, reverse(List(1,2,3)) =
    * List(3,2,1).
    * - flatten: Takes a List[List[A]] and produces a List[A] by joining all
    * the sublists into one long list. For example, flatten(List(List(1,2,3),
    * List(4,5,6))) produces List(1,2,3,4,5,6).
    */
  def sum(ls: List[Double]): Double = ls match {
    case Nil => 0.0
    case _ => foldLeft(ls, 0.0)((b, a) => b + a)
  }

  def product(ls: List[Double]): Double = ls match {
    case Nil => 0.0
    case head::tail => foldLeft(tail, head)((b, a) => b * a)
  }

  def length[A](ls: List[A]): Int = ls match {
    case Nil => 0
    case ls => foldLeft(ls, 0)((b, a) => 1 + b)
  }

  // calls fold left with the left hand item as the intial accumulator
//  e.g. if the list is (1,2,3) then foldLeft is called with 1 as the accumulator and (2,3) as the rest
//  accumulator becomes the new reversed list, as new items are added to the end of it after being removed from the start of the original list
  def reverse[A](ls: List[A]): List[A] = ls match {
    case Nil => Nil
    case head::tail => foldLeft(tail, List[A](head))((b: List[A], a: A) => List(a) ::: b)
  }

  def flatten[A](ls: List[A]): List[A] = ls match {
    case Nil => Nil
    case (head: List[A]) :: tail => flatten(head) ::: flatten(tail)
    case head :: tail => foldLeft(flatten(tail), List[A](head))((b: List[A], a: A) => b ::: List(a))
    case _ => throw new UnknownError("???")
  }

  def flattenThis[A](l: List[A]): List[Any] = {
    val output = l match{
      case Nil => Nil
      case (head: List[_]) :: tail => flattenThis(head) ::: flattenThis(tail)
      case head :: tail => head :: flattenThis(tail)
      case _ => throw new IllegalArgumentException()
      //TODO as for construct
    }
    output
  }

  //keith old answers do not use
//  def sum(ls: List[Double]): Double = ls match {
//    case Nil => 0
//    case hd :: tl => hd + sum(tl)
//  }
//  def product(ls: List[Double]): Double = ls match {
//    case Nil => 1
//    case hd :: tl => hd * product(tl)
//  }
//
//  def length[A](ls: List[A]): Int = ls match {
//    case Nil => 0
//    case hd :: tl => 1 + length(tl)
//  }
//
//  def reverse[A](ls: List[A]): List[A] = ls match {
//    case Nil => Nil
//    case hd :: tl => reverse(tl) ::: List(hd)
//  }
//
//  def flatten[A](ls: List[A]): List[A] = ls match {
//    case Nil => Nil
//    case (head: List[A]) :: tail => flatten(head) ::: flatten(tail)
//    case head :: tail => head :: flatten(tail)
//  }

  // MAP AND FILTER

//  Keith's answers
  /**
    * map applies a function to a list, producing a new list of the functions'
    * values.
    * As with the other functions, implement this recursively.
    *
    * @param ls : List[A] the list to be changed.
    * @param f  : A => B the function to be applied to each element of the input.
    * @return the resulting list from applying f to each element of ls.
    */
  def map[A, B](ls: List[A])(f: A => B): List[B] = ls match {
    case Nil => Nil
    case hd :: tl => f(hd) :: map(tl)(f)
  }

  /**
    * filter removes all elements from a list for which a given predicate
    * returns false.
    * As usual, this should be recursive.
    *
    * @param ls : List[A] the list to be filtered.
    * @param f  : A => Boolean the predicate
    * @return the filtered list.
    */
  def filter[A](ls: List[A])(f: A => Boolean): List[A] = ls match {
    case Nil => Nil
    case hd :: tl => if (f(hd)) hd :: filter(tl)(f) else filter(tl)(f)
  }

//  my version

  def myFilter(func: (Int => Boolean), l: List[Int]): List[Int] = l match {
    case head :: tail if(func(head)) => head :: myFilter(func, tail)
    case head :: tail => myFilter(func, tail)
    case _ => l
  }

  def isEven(i: Int) = i match{
    case int if(i % 2 == 0) => true
    case _ => false
  }

  /**
    * flatMap is very similar to map. However, the function returns a List,
    * and flatMap flattens all of the resulting lists into one.
    *
    * @param ls : List[A] the list to be changed.
    * @param f  : A => List[B] the function to be applied.
    * @return a List[B] containing the flattened results of applying f to all
    *         elements of ls.
    */

    // Two versions - one using pattern matching and recursion
    // The second version also uses pm and recursion but in this case it is "tail recursion"

  // def flatMap[A, B](ls: List[A])(f: A => List[B]): List[B] = ls match {
  //   case (x::xs) => f(x) ++ flatMap(xs)(f)
  //   case _ => Nil
  // }

  import scala.annotation.tailrec

  def flatMap[A, B](ls: List[A])(f: A => List[B]): List[B] = {
    @tailrec
    def helper(result: List[B])(input: List[A])(f: A => List[B]): List[B] = input match {
      case (x::xs) => helper(result ++ f(x))(xs)(f)
      case _ => result
    }
    helper(List[B]())(ls)(f)
  }


  // COMBINING FUNCTIONS
  /**
    * maxAverage takes a List[(Double,Double)] (a list of pairs of real
    * numbers) and returns the average value of the largest value in each pair.
    * For example, the maxAverage of List((1,4), (8, 0)) is (8 + 4)/2 = 6.0.
    * You must use the methods you wrote above, particularly map and foldLeft.
    *
    * @param ls : List[(Double,Double)] a list of pairs of real numbers, whose
    *           length is greater than 0.
    * @return the average value of the largest values in the pairs.
    */
  def maxAverage(ls: List[(Double, Double)]): Double = {
    val list = map(ls)({case (x,y) => if(x > y) x else y}) // using the map function from above. passing in list ls and the function
    sum(list) / length(list)
  }

///keith's answer
//  def maxAverage(ls: List[(Double, Double)]): Double = {
//    val resultList = map(ls){case (x,y) => if (x < y) y else x}
//    sum(resultList) / length(resultList)
//  }

  /**
    * variance takes a List[Double] and calculates the squared distance
    * of each value from the mean. This is the *variance*, as used in
    * statistics.
    * 1) Find the mean M of the input.
    *
    * 2) For each value V in the input, calculate (V - M)^2.
    * 3) Find the variance.
    * Which methods that we've already defined can you use? (At least one!)
    * @param ls     : List[Double] a list of values, whose length is greater than 0.
    */
  def variance(ls: List[Double]): Double = {
    val sumElements = foldLeft(ls, 0.0)((b, a) => b + a) // or could just call existing sum method above which uses foldLeft
    val average = sumElements / length(ls)
    val variances: List[Double] = map(ls)(x => (x - average)* (x - average))
    sum(variances) / length(variances)
  }
}
//Keith's answer
//  import scala.math.pow
//
//  def variance(ls: List[Double]): Double = {
//    val mean = sum(ls) / length(ls)
//    val lst = map(ls)(v => pow((v - mean),2))
//    sum(lst) / length(lst)
//  }

