public class L1Q5 {

    public static void main(final String[] args) {
        final Combination c1 = new Combination(1, 2, 3);
        final Combination c2 = new Combination(1, 2, 3);
        final Combination c3 = new Combination(3, 2, 1);
        System.out.println(c1.equals(c2));
        System.out.println(c1.equals(c3));
        System.out.println(c1.toString());
    }

}