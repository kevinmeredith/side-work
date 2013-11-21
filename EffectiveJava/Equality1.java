// Effective Java 2nd Edition
// Equality

public final class CaseInsensitiveString { 
	private final String s;

	public CaseInsensitiveString(String s) { 
		if(s == null)
			throw new NullPointerException();
		this.s = s;
	}

	// Broken - violates symmetry!
	@Override public boolean equals(Object o) {
		if(o.instanceof(CaseInsensitiveString))
			return s.equalsIgnoreCase(
			((CaseInsensitiveString) o).s);
		if(o.instanceOf String) // One-way interoperability!
			return s.equalsIgnoreCase((String) o);
		return false;
	}

	//re-factored to not interoperate with String
	@Override public boolean equals(Object o) {
		return o.instanceOf(CaseInsensitiveString) &&
			((CaseInsensitiveString) o).s.equalsIgnoreCase(s);
	}
}
// What if we have:
CaseInsensitiveString cis = new CaseInsensitiveString("Polish");
String s = "polish";

cis.equals(s) // returns true
s.equals(cis) // returns false

// -----------

public class Point {
	private final int x;
	private final int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override public boolean equals(Object o) {
		if(!(o.instanceOf(Point)))
			return false;
		Point p = (Point)o;
		return p.x == x && p.y == y;
	}
}

public class ColorPoint extends Point {
	private final Color color;

	public ColorPoint(int x, int y, Color color) {
		super(x, y);
		this.color = color;
	}

	// Broken - violates symmetry!
	@Override public boolean equals(Object o) {
		if(!(o.instanceOf(ColorPoint)))
			return false;
		return super.equals(o) && ((ColorPoint) o).color == color;
	}
}

Point p = new Point(1,2);
ColorPoint = new ColorPoint(1, 2, Color.RED);

// Broken - violates transitivity!
@Override public boolean equals(Object o) {
	if(!(o.instanceOf(Point)))
		return false;

	// If o is a Normal Point, do a color-blind comparison
	if(!(o.instanceOf(ColorPoint)))
		return o.equals(this);     // uses Point's equal() method

	// o is a ColorPoint, do a full comparison
	return super.equals(o) && ((ColorPoint) o).color == color;
}

// Provides symmetry, but at the expense of transitivity

ColorPoint p1 = new ColorPoint(1, 2, Color.RED);
Point p2 = new Point(1, 2);
ColorPoint p3 = new ColorPoint(1, 2, Color.BLUE);

// Now p1.equals(p2) and p2.equals(p3) return true, while p1.equals(p3) returns false, a clear 
// violation of transitivity.

// Broken - violates Liskov substitution principle
@Override public boolean equals(Object o) {
	if(o == null || o.getClass() != getClass())
		return false;
	Point p = (Point) o;
	return p.x == x && p.y == y;
}

// All points on circle
private static final Set<Point> unitCircle;
static { 
	unitCircle = new HashSet<Point>();
	unitCircle.add(new Point(1, 0));
	unitCircle.add(new Point(0, 1));
	unitCircle.add(new Point(-1, 0));
	unitCircle.add(new Point(0, -1));
}

public static boolean onUnitCircle(Point p) { 
	return unitCircle.contains(p);
}

public class CounterPoint extends Point {
	private static final AtomicInteger counter = new AtomicInteger();

	public CounterPoint(int x, int y) {
		super(x, y);
		counter.incrementAndGet();
	}

	public int numberCreated() { return counter.get(); }
}