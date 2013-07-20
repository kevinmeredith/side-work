// check if an array is sorted in ascending order ex: (1,2,3)
 def isSorted[A](as: Array[A], gt: (A,A) => Boolean): Boolean = {
     if  (as.length == 1) true
     else if (gt(as(0), as(1))) false
     else { isSorted(as tail, gt) }
 }