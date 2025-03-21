package ed.lab;

import java.util.Comparator;

public class E03AVLTree<T> {

    private final Comparator<T> comparator;
    private TreeNode root;
    private int size;

    public E03AVLTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    private class TreeNode {
        T value;
        TreeNode left, right;
        int height;

        TreeNode(T value) {
            this.value = value;
            this.height = 1;
        }
    }

    private int height(TreeNode root) {
        return root == null ? 0 : root.height;
    }

    private int getBalance(TreeNode root) {
        return root == null ? 0 : height(root.left) - height(root.right);
    }

    private TreeNode rotateRight(TreeNode root) {
        TreeNode newRoot = root.left;
        TreeNode t = newRoot.right;
        newRoot.right = root;
        root.left = t;
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        newRoot.height = Math.max(height(newRoot.left), height(newRoot.right)) + 1;
        return newRoot;
    }

    private TreeNode rotateLeft(TreeNode root) {
        TreeNode newRoot = root.right;
        TreeNode t = newRoot.left;
        newRoot.left = root;
        root.right = t;
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        newRoot.height = Math.max(height(newRoot.left), height(newRoot.right)) + 1;
        return newRoot;
    }

    public void insert(T value) {
        if (value == null) return;
        root = insert(root, value);
    }

    private TreeNode insert(TreeNode root, T value) {
        if (root == null) {
            size++;
            return new TreeNode(value);
        }

        if (comparator.compare(value, root.value) < 0) {
            root.left = insert(root.left, value);
        } else if (comparator.compare(value, root.value) > 0) {
            root.right = insert(root.right, value);
        } else {
            return root;
        }

        root.height = Math.max(height(root.left), height(root.right)) + 1;

        return balance(root);
    }

    private TreeNode balance(TreeNode root) {
        int balanceFactor = getBalance(root);

        if (balanceFactor > 1 && getBalance(root.left) >= 0)
            return rotateRight(root);

        if (balanceFactor > 1 && getBalance(root.left) < 0) {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }

        if (balanceFactor < -1 && getBalance(root.right) <= 0)
            return rotateLeft(root);

        if (balanceFactor < -1 && getBalance(root.right) > 0) {
            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }

        return root;
    }

    public void delete(T value) {
        if (value == null) return;
        root = delete(root, value, true);
    }

    private TreeNode delete(TreeNode root, T value, boolean decrementSize) {
        if (root == null) return null;

        int valor = comparator.compare(value, root.value);

        if (valor < 0)
        {
            root.left = delete(root.left, value, decrementSize);
        }

        else if (valor > 0)
        {
            root.right = delete(root.right, value, decrementSize);
        }

        else {
            if (decrementSize) {
                size--;
            }

            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            TreeNode t = findMin(root.right);
            root.value = t.value;

            root.right = delete(root.right, t.value, false);
        }

        root.height = Math.max(height(root.left), height(root.right)) + 1;

        return balance(root);
    }

    private TreeNode findMin(TreeNode root) {
        TreeNode min = root;
        while (min.left != null) {
            min = min.left;
        }
        return min;
    }

    public T search(T value) {
        if (value == null) return null;
        return search(root, value);
    }

    private T search(TreeNode root, T value) {
        if (root == null)
            return null;

        int valor = comparator.compare(value, root.value);

        if (valor == 0)
            return root.value;

        return (valor < 0) ? search(root.left, value) : search(root.right, value);
    }

    public int size() {
        return size;
    }

    public int height() {
        return height(root);
    }
}