package main;

public class ThreePair<L, M, R> {

    private final L left;
    private final M middle;
    private final R right;

    public ThreePair(L left, M middle, R right) {
        this.left = left;
        this.middle = middle;
        this.right = right;
    }

    public L getLeft() { return left; }
    public M getMiddle() { return middle; }
    public R getRight() { return right; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ThreePair<?, ?, ?> threePair = (ThreePair<?, ?, ?>) o;

        if (left != null ? !left.equals(threePair.left) : threePair.left != null) return false;
        if (middle != null ? !middle.equals(threePair.middle) : threePair.middle != null) return false;
        return right != null ? right.equals(threePair.right) : threePair.right == null;

    }

    @Override
    public int hashCode() {
        int result = left != null ? left.hashCode() : 0;
        result = 31 * result + (middle != null ? middle.hashCode() : 0);
        result = 31 * result + (right != null ? right.hashCode() : 0);
        return result;
    }
}
