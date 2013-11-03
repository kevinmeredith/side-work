trait Monoid[A] {
  def op(a1: A, a2: A): A
  def zero: A
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
  override def foldRight[A, B](as: IndexedSeq[A])(z: B)(f: (A, B) => B): B = sys.error("TODO")
  override def foldLeft[A, B](as: IndexedSeq[A])(z: B)(f: (B, A) => B): B = {
  	def go(bs: IndexedSeq[A], acc: B): B = {
  		if (bs.isEmpty) acc
  		else go(bs.tail, f(acc, bs.head))
  	}
	go(as, z)
  }

  def foldLeftPM[A, B](as: IndexedSeq[A])(z: B)(f: (B, A) => B): B = {
  	def go(bs: IndexedSeq[A], acc: B): B = bs match {
  		case x +: xs => println("x: " + x + "; xs: " + xs)
  						go(xs, f(acc, x))
  		case _ => acc
  	}
  	go(as, z)
  }

  override def foldMap[A, B](as: IndexedSeq[A])(f: A => B)(mb: Monoid[B]): B = sys.error("TODO")
  override def concatenate[A](as: IndexedSeq[A])(m: Monoid[A]): A = sys.error("TODO")
}

object FoldableTest { 
	
	val intAddition = new Monoid[Int] {
		def op(a1: Int, a2: Int) = a1 + a2
		val zero = 0
	}

	def main(args: Array[String]) = {
		val list = List(1,2,3,4,5)

		val listStr = List("1", "2", "3", "4", "5")
		assert(ListFoldable.foldRight(list)(0)((s, i) => s + i) == 15)
		assert(ListFoldable.foldLeft(list)(0)((i, s) => i + s) == 15)
		assert(ListFoldable.foldMap(listStr)(x => x.toInt)(intAddition) == 15)
		
		val indexedSeq = IndexedSeq(1,2,3)
		assert(IndexedSeqFoldable.foldLeft(indexedSeq)(0)((i, s) => i + s) == 6)
		assert(IndexedSeqFoldable.foldLeftPM(indexedSeq)(0)((i, s) => i + s) == 6)
		println("success")
	}
}

