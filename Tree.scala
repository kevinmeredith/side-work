object Tree extends App {

	sealed trait Tree[+A] 
	case class Leaf[A](value: A) extends Tree[A]
	case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

	def size[A](root: Leaf[A], left: Branch[A], right: Branch[A]) : Int = {
	   def go(branch: Branch[A], acc: Int) : Int = branch match {
		   case Branch(x: Tree[A], y: Tree[A]) => go(x, 1 + acc) + go(y, 0)
		   case Branch(x: Tree[A], _) => go(x, 1 + acc)
		   case Branch(_, y: Tree[A]) => go(y, 1 + acc)
		   case Branch(_, _) => acc
	   }
	   root match {
		case Leaf(_) => 1 + go(left, 0) + go(right, 0)
		case _ => 0
	  }
	}
}