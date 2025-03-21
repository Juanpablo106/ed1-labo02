package ed.lab;
import java.util.LinkedList;
import java.util.List;

public class E01InvertBT {
    public TreeNode<Integer> invertTree(TreeNode<Integer> root)
    {
        if(root != null)
        {
            TreeNode<Integer> left = root.left;
            root.left = root.right;
            root.right = left;

            invertTree(root.left);
            invertTree(root.right);
        } return root;
    }
    }