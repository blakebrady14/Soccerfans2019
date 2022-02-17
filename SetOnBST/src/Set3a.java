import java.util.Iterator;

import components.binarytree.BinaryTree;
import components.binarytree.BinaryTree1;
import components.set.Set;
import components.set.SetSecondary;

/**
 * {@code Set} represented as a {@code BinaryTree} (maintained as a binary
 * search tree) of elements with implementations of primary methods.
 *
 * @param <T>
 *            type of {@code Set} elements
 * @mathdefinitions <pre>
 * IS_BST(
 *   tree: binary tree of T
 *  ): boolean satisfies
 *  [tree satisfies the binary search tree properties as described in the
 *   slides with the ordering reported by compareTo for T, including that
 *   it has no duplicate labels]
 * </pre>
 * @convention IS_BST($this.tree)
 * @correspondence this = labels($this.tree)
 *
 * @author Put your name here
 *
 */
public class Set3a<T extends Comparable<T>> extends SetSecondary<T> {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Elements included in {@code this}.
     */
    private BinaryTree<T> tree;

    /**
     * Returns whether {@code x} is in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} to be searched
     * @param x
     *            the label to be searched for
     * @return true if t contains x, false otherwise
     * @requires IS_BST(t)
     * @ensures isInTree = (x is in labels(t))
     */
    private static <T extends Comparable<T>> boolean isInTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";

        /*
         * Initially setting check as 0 in case the tree is empty
         */
        boolean check = false;

        /*
         * Creating two new trees for the disassemble call
         */
        BinaryTree<T> left = t.newInstance();
        BinaryTree<T> right = t.newInstance();

        /*
         * Checking if the tree is not empty
         */
        if (t.size() != 0) {

            /*
             * Disassembling the tree and storing label as label
             */
            T label = t.disassemble(left, right);

            /*
             * If the label equals x, returning true
             */
            if (x.compareTo(label) == 0) {
                check = true;
            }

            /*
             * Else if x<label, checking left tree recursively
             */
            else if (x.compareTo(label) < 0) {
                check = isInTree(left, x);
            }

            /*
             * Else, x>label, so checking right tree
             */
            else {
                check = isInTree(right, x);
            }

            /*
             * Reassembling tree
             */
            t.assemble(label, left, right);

        }

        /*
         * Returning check value
         */
        return check;
    }

    /**
     * Inserts {@code x} in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} to be searched
     * @param x
     *            the label to be inserted
     * @aliases reference {@code x}
     * @updates t
     * @requires IS_BST(t) and x is not in labels(t)
     * @ensures IS_BST(t) and labels(t) = labels(#t) union {x}
     */
    private static <T extends Comparable<T>> void insertInTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";

        /*
         * Creating new trees for use with assemble method
         */
        BinaryTree<T> left = t.newInstance();
        BinaryTree<T> right = t.newInstance();

        /*
         * If the size of t is 0, t is assembled with left and right as empty
         * subtrees
         */
        if (t.size() == 0) {
            t.assemble(x, left, right);
        } else {

            /*
             * If not, t is disassembled, and the label is stored as label
             */
            T label = t.disassemble(left, right);

            /*
             * If x is < the label, it is inserted into the left tree
             * recursively
             */
            if (x.compareTo(label) < 0) {
                insertInTree(left, x);
            } else {

                /*
                 * If x is > the label, it is inserted into the left tree
                 * recursively
                 */
                insertInTree(right, x);
            }
            t.assemble(label, left, right);
        }

    }

    /**
     * Removes and returns the smallest (left-most) label in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} from which to remove the label
     * @return the smallest label in the given {@code BinaryTree}
     * @updates t
     * @requires IS_BST(t) and |t| > 0
     * @ensures <pre>
     * IS_BST(t)  and  removeSmallest = [the smallest label in #t]  and
     *  labels(t) = labels(#t) \ {removeSmallest}
     * </pre>
     */
    private static <T> T removeSmallest(BinaryTree<T> t) {
        assert t != null : "Violation of: t is not null";
        assert t.size() > 0 : "Violation of: |t| > 0";

        /*
         * Creating a new tree for the disassemble call, and also to search
         */
        BinaryTree<T> left = t.newInstance();
        BinaryTree<T> right = t.newInstance();

        /*
         * Disassembling the tree, storing the label as "smallest"
         */
        T smallest = t.disassemble(left, right);

        /*
         * If left is not empty, recursively finding the smallest label, and
         * storing that as smallestInside. After that, t is reassembled with its
         * original label and the left and right tree, and smallestInside is set
         * to the original smallest, removing the smallest label
         */
        if (left.size() != 0) {

            /*
             * Removes the smallest label recursively
             */
            T smallestInside = removeSmallest(left);

            /*
             * Reassembles the tree with the smallest label before the first
             * disassemble call, removing the smallestInsude label from the
             * if-statements disassemble call but keeping the two cubtrees
             */
            t.assemble(smallest, left, right);

            /*
             * Setting smallest to smallestInside, because this is what we want
             * to return
             */
            smallest = smallestInside;

            /*
             * If the left tree is empty and the right has contents after the
             * first disassemble call on line 129, that means there were
             * contents below the removed label. To add them back into the tree,
             * right is transferred into t, replacing the smallest label
             */
        } else if (right.size() != 0) {
            t.transferFrom(right);

        }

        /*
         * Smallest label is returned
         */
        return smallest;
    }

    /**
     * Finds label {@code x} in {@code t}, removes it from {@code t}, and
     * returns it.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} from which to remove label {@code x}
     * @param x
     *            the label to be removed
     * @return the removed label
     * @updates t
     * @requires IS_BST(t) and x is in labels(t)
     * @ensures <pre>
     * IS_BST(t)  and  removeFromTree = x  and
     *  labels(t) = labels(#t) \ {x}
     * </pre>
     */
    private static <T extends Comparable<T>> T removeFromTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";
        assert t.size() > 0 : "Violation of: x is in labels(t)";

        /*
         * Instantiating a new variable to store the removed value in
         */
        T toRemove = (T) "";

        /*
         * If the tree size is > 1, the method will search for the corrrect
         * label
         */
        if (t.size() > 1) {

            /*
             * Disassembling t
             */
            BinaryTree<T> left = t.newInstance();
            BinaryTree<T> right = t.newInstance();
            T label = t.disassemble(left, right);

            /*
             * If the label is what we are looking for, this loop is entered.
             * toRemove is set to label, then t is reassembled with the smallest
             * value of the right tree as the label, the left tree, and the
             * (newly-edited) right tree. If the right tree is empty, the left
             * tree is transferred in as the label
             */
            if (label.equals(x)) {
                toRemove = label;
                if (right.size() != 0) {
                    T newLabel = removeSmallest(right);
                    t.assemble(newLabel, left, right);
                } else {

                    t.transferFrom(left);
                }

            }
            /*
             * If the label is not x, the value of x is compared to the label
             * removed. If it is less than the original label, the label to
             * remove must be in left tree, so the method is resursively called
             * on left tree. If not, it must be in the right tree, so the right
             * tree is recursively checked
             */
            else {

                /*
                 * checking left
                 */
                if (x.compareTo(label) < 0) {

                    toRemove = removeFromTree(left, x);
                    t.assemble(label, left, right);
                }
                /*
                 * checking right
                 */
                else {

                    toRemove = removeFromTree(right, x);
                    t.assemble(label, left, right);

                }

            }

        }

        /*
         * Checking if there is only one label, in this case, it must be what we
         * are looking for because of the precondition that the label is in the
         * tree
         */
        else {
            BinaryTree<T> left = t.newInstance();
            BinaryTree<T> right = t.newInstance();
            T label = t.disassemble(left, right);

            toRemove = label;
        }
        return toRemove;

    }

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {

        /*
         * Creating empty binary tree representation
         */
        this.tree = new BinaryTree1<>();

    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Set3a() {

        /*
         * Calling createNewRep
         */
        this.createNewRep();

    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @SuppressWarnings("unchecked")
    @Override
    public final Set<T> newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(Set<T> source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof Set3a<?> : ""
                + "Violation of: source is of dynamic type Set3<?>";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case: source must be of dynamic type Set3a<?>, and
         * the ? must be T or the call would not have compiled.
         */
        Set3a<T> localSource = (Set3a<T>) source;
        this.tree = localSource.tree;
        localSource.createNewRep();
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public final void add(T x) {
        assert x != null : "Violation of: x is not null";
        assert !this.contains(x) : "Violation of: x is not in this";

        /*
         * Adding x using insertInTree method
         */
        insertInTree(this.tree, x);

    }

    @Override
    public final T remove(T x) {
        assert x != null : "Violation of: x is not null";
        assert this.contains(x) : "Violation of: x is in this";

        /*
         * Removing and returning x using removeFromTree
         */
        return removeFromTree(this.tree, x);
    }

    @Override
    public final T removeAny() {
        assert this.size() > 0 : "Violation of: this /= empty_set";

        /*
         * RemoveAny using removeSmallest
         */
        return removeSmallest(this.tree);
    }

    @Override
    public final boolean contains(T x) {
        assert x != null : "Violation of: x is not null";

        /*
         * Contains method using isInTree
         */
        return isInTree(this.tree, x);
    }

    /*
     * Size method using for each loop
     */
    @Override
    public final int size() {
//         int size = 0;
//         for (T x : this.tree) {
//             size++;
//         }
//         return size;
        return this.tree.size();
    }

    @Override
    public final Iterator<T> iterator() {
        return this.tree.iterator();
    }

}
