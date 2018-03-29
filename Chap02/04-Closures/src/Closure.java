public class Closure {

	public String foo = "";

	// Example of a closure

	/* The Closure parameter t should be declared as 
	final to be accessible from the anonymous Runnable
	object (the actual closure), but removing the final
	identifier seems not to cause any compile-time or
	runtime error. */
	public static void process(final Closure t) {
		System.out.println(t.toString() + " = " + t.foo);
		t.foo = "bar";

		new Runnable() {
			public void run() {
				/* t is being "closed-over". It is accessed from
				the inner scope of the closure, without being
				passed as an argument. */
				System.out.println(t.toString() + " = " + t.foo);
				t.foo = "baz";
			}
		}.run();

		System.out.println(t.toString() + " = " + t.foo);
	}

	public static void main(String[] args) {
		process(new Closure());
	}
}