trait Monoid[A] {
  def op(a1: A, a2: A): A
  def zero: A
}

// Define operator `:++` for pattern matching of foldRight
object :++ {
  def unapply[T](s: Seq[T]) = s.lastOption.map(last => (last, s.take(s.length - 1)))
}

trait Foldable[F[_]] {
  def foldRight[A, B](as: F[A])(z: B)(f: (A, B) => B): B
  def foldLeft[A, B](as: F[A])(z: B)(f: (B, A) => B): B 
  def foldMap[A, B](as: F[A])(f: A => B)(mb: Monoid[B]): B 
  def concatenate[A](as: F[A])(m: Monoid[A]): A 
}

object ListFoldable extends Foldable[List] {
	override def foldRight[A, B](as: List[A])(z: B)(f: (A, B) => B): B = foldLeft(as.reverse)(z)((i, s) => f(s, i))
	override def foldLeft[A, B](as: List[A])(z: B)(f: (B, A) => B): B  = {
		def go(bs: List[A], acc: B): B = bs match {
			case x :: xs => go(xs, f(acc, x))
			case Nil => acc
		}
		go(as, z)
	}
	override def foldMap[A, B](as: List[A])(f: A => B)(mb: Monoid[B]): B = {
		val bs = as.map(f)
		foldLeft(bs)(mb.zero)(mb.op)
	}
	override def concatenate[A](as: List[A])(m: Monoid[A]): A = as.foldLeft(m.zero)(m.op)
}

object IndexedSeqFoldable extends Foldable[IndexedSeq] {
  override def foldRight[A, B](as: IndexedSeq[A])(z: B)(f: (A, B) => B): B = {
  	def go(bs: Seq[A], acc: B): B = bs match {
  		case x :++ xs => go(xs, f(x, acc)) // x = tail AND xs = List(head, ..., last-1,)
  		case _ => acc
  	}
  	go(as, z)
  }

  override def foldLeft[A, B](as: IndexedSeq[A])(z: B)(f: (B, A) => B): B = {
  	def go(bs: IndexedSeq[A], acc: B): B = {
  		if (bs.isEmpty) acc
  		else go(bs.tail, f(acc, bs.head))
  	}
	go(as, z)
  }

  // Additional implementation of foldLeft using pattern matching
  def foldLeftPM[A, B](as: IndexedSeq[A])(z: B)(f: (B, A) => B): B = {
  	def go(bs: IndexedSeq[A], acc: B): B = bs match {
  		case x +: xs => go(xs, f(acc, x))
  		case _ => acc
  	}
  	go(as, z)
  }

  override def foldMap[A, B](as: IndexedSeq[A])(f: A => B)(mb: Monoid[B]): B = {
  	val bs = as.map(f)
  	foldLeft(bs)(mb.zero)(mb.op)
  }
  override def concatenate[A](as: IndexedSeq[A])(m: Monoid[A]): A = foldLeft(as)(m.zero)(m.op)
}

object StreamFoldable extends Foldable[Stream] {
  def foldRight[A, B](as: Stream[A])(z: B)(f: (A, B) => B): B = sys.error("todo")
  def foldLeft[A, B](as: Stream[A])(z: B)(f: (B, A) => B): B  = {
  	def go(bs: Stream[A], acc: B) : B = bs match {
  	   case x #:: xs => go(xs, f(acc, x))
       case Stream() => acc
  	}
    go(as, z)
  }
  def foldMap[A, B](as: Stream[A])(f: A => B)(mb: Monoid[B]): B = {
    foldLeft(as.map(f))(mb.zero)(mb.op)
  }
  def concatenate[A](as: Stream[A])(m: Monoid[A]): A = sys.error("todo")
}

object OptionFoldable extends Foldable[Option] {
  override def foldRight[A, B](as: Option[A])(z: B)(f: (A, B) => B): B = as match {
    case None => z
    case Some(x) => f(x, z)
  }
  override def foldLeft[A, B](as: Option[A])(z: B)(f: (B, A) => B): B = as match {
    case None => z
    case Some(x) => f(z, x)
  }
  override def foldMap[A, B](as: Option[A])(f: A => B)(mb: Monoid[B]): B = as match {
    case None => mb.zero
    case Some(x) => f(x)
  }
  override def concatenate[A](as: Option[A])(m: Monoid[A]): A  = as match {
    case None => m.zero
    case Some(x) => m.op(x, m.zero)
  }
}

object FoldableTest { 
	
	val intAddition = new Monoid[Int] {
		def op(a1: Int, a2: Int) = a1 + a2
		val zero = 0
	}

	val concatMonad = new Monoid[String] {
		def op(a1: String, a2: String) = a1 + a2
		def zero = ""
	}

	def main(args: Array[String]) = {
		val list = List(1,2,3,4,5)

		assert(ListFoldable.foldRight(list)(0)((s, i) => s + i) == 15)
		assert(ListFoldable.foldLeft(list)(0)((i, s) => i + s) == 15)

		val listStr = List("1", "2", "3", "4", "5")
		assert(ListFoldable.foldMap(listStr)(x => x.toInt)(intAddition) == 15)
		assert(ListFoldable.concatenate(listStr)(concatMonad) == "12345")

		def foo(x: String, y: Int) = x.toInt + y

		val indexedSeqInt = IndexedSeq(1,2,3)
		val indexedSeqStr = IndexedSeq("1", "2", "3")
		assert(IndexedSeqFoldable.foldLeft(indexedSeqInt)(0)((i, s) => i + s) == 6)
		assert(IndexedSeqFoldable.foldLeftPM(indexedSeqInt)(0)((i, s) => i + s) == 6)
		assert(IndexedSeqFoldable.foldRight(indexedSeqStr)(0)((s, i) => foo(s, i)) == 6)
		println("success")

    val someInt: Option[Int] = Some(1)
    val optRes = OptionFoldable.foldLeft(someInt)(1)(_ + _)
    assert(optRes == Some(2))

    val noneInt: Option[Int] = None
    val noneRes = OptionFoldable.foldLeft(noneInt)(1)(_ + _)
    assert(noneRes == None)

    val someStr: Option[String] = Some("2")
    val strOptRes: Option[Int] = OptionFoldable.foldMap(someStr)(x => x.toInt)(intAddition)
    assert(strOptRes == Some(2))

    println("success")
	}
}


