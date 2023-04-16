public class TreeNode {
    private Object element;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(){}
    public TreeNode(Object element){
        this.element=element;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public void setRight(TreeNode right){
        this.right=right;
    }

    public void setElement(Object element){
        this.element=element;
    }

    public Object getElement() {
        return element;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(4);
        root.setLeft(node1);
        root.setRight(node2);
        TreeNode node3 = new TreeNode(3);
        node1.setLeft(node3);
        TreeNode node4 = new TreeNode(2);
        node1.setRight(node4);
        TreeNode node5 = new TreeNode(1);
        node2.setLeft(node5);
    }

    /**
     * 前序遍历：根左右。先输出当前节点；如果左子节点不为空，则递归进行前序遍历；如果右子节点不为空，则继续递归前序遍历。
     * 中序遍历：左根右。如果左子节点不为空，则递归中序遍历；输出当前节点；如果右子节点不为空，则递归中序遍历。
     * 后序遍历：左右根。如果左子节点不为空，递归后序遍历；如果右子节点不为空，递归后序遍历；输出当前节点。
     */

    //前序遍历
    public static void preOrder(TreeNode root){
        System.out.println(root.getElement());
        //左不为空 继续递归
        if (root.getLeft()!=null)
            preOrder(root.getLeft());
        if (root.getRight()!=null)
            preOrder(root.getRight());
    }

    //中序遍历
    public static void infixOrder(TreeNode root){
        if (root.getLeft()!=null)
            infixOrder(root.getLeft());
        System.out.println(root.getElement());
        if (root.getRight()!=null)
            infixOrder(root.getRight());
    }

    //后序遍历
    public static void postOrder(TreeNode root){
        if (root.getLeft()!=null)
            postOrder(root.getLeft());
        if (root.getRight()!=null)
            postOrder(root.getRight());
        System.out.println(root.getElement());
    }

    /**
     * 删除节点
     * 不考虑子节点上浮，即如果删除的是非叶子节点 -》 删除整颗子树
     */
    public static void delNode(TreeNode root, Object ele){
        //二叉树为空，直接return
        if (root==null)
            return;

        //如果只有一个节点，或者root即为要删除的节点，直接设空
        if ((root.getRight()==null && root.getLeft()==null) || root.getElement()==ele){
            root.setElement(null);
            root.setLeft(null);
            root.setRight(null);
        }

        //判断左节点是否要删除
        if (root.getLeft()!=null && root.getLeft().getElement()==ele){
            root.setLeft(null);
            return;
        }

        //判断右节点是否要删除
        if (root.getRight()==null && root.getRight().getElement()==ele){
            root.setRight(null);
            return;
        }

        //向左子树递归
        if (root.getLeft() != null) {
            delNode(root.getLeft(), ele);
        }
        // 向右子树递归
        if (root.getRight() != null) {
            delNode(root.getRight(), ele);
        }

    }
}
