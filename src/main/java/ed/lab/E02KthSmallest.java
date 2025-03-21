package ed.lab;

public class E02KthSmallest {
    int count = 0;
    int result = -1;

    public int kthSmallest(TreeNode<Integer> root, int k)
    {
        Travesal(root, k);
        return result;
    }

    public void Travesal(TreeNode<Integer> root, int k)
    {
        if(root == null)
        {
            return;
        }

        Travesal(root.left, k);
        count++;

        if(count == k)
        {
            result = root.value;
            return;
        }
        Travesal(root.right, k);
    }
}