public class BinarySearchTree {
    private Node root;

    public void add(int value){
        if (root==null)
            root=new Node(value);
        else
            root.add(new Node(value));
    }

    public Node findNode(int value){
        if (root==null)
            return null;
        else
            return root.findNode(value);
    }

    public Node findParentNode(int value){
        if (root==null)
            return null;
        else
            return root.findParentNode(value);
    }

    public Node findMinNode(Node node){
        Node temp=node;
        while (temp.left!=null)
            temp=temp.left;
        return temp;
    }

    public void deleteLeafNode(Node targetNode, Node parentNode){
        if (parentNode==null)
            root=null;
        else {
            if (parentNode.left!=null && parentNode.left.value== targetNode.value)
                parentNode.left=null;
            if (parentNode.right!=null && parentNode.right.value== targetNode.value)
                parentNode.right=null;
            else
                return;
        }
    }

    public void deleteDoubleSonNode(Node targetNode){
        // 从targetNode的右子树找到值最小的节点
        Node rightMinNode = findMinNode(targetNode.right);
        // 删除找到的最小的节点，此时的rightMinNode一定是叶子节点
        deleteNode(rightMinNode.value);
        // 将rightMinNode的值赋给targetNode
        targetNode.value = rightMinNode.value;
    }

    public void deleteSingleSonNode(Node targetNode, Node parentNode){
        if (parentNode == null){
            // 要删除的是根节点，且根节点有一棵子树
            Node sonNode = root.left == null ? root.right : root.left;
            root.value = sonNode.value;
            root.left = null;
            root.right = null;
        } else {
            Node sonNode = targetNode.left == null ? targetNode.right : targetNode.left;
            // 要删除的节点有一棵子树且不是根节点
            if (parentNode.left != null && parentNode.left.value == targetNode.value){
                parentNode.left = sonNode;
            } else {
                parentNode.right = sonNode;
            }
        }
    }

    public void deleteNode(int value){
        if (root == null){
            return;
        }
        // 找到要删除的节点和要删除节点的父节点
        Node targetNode = findNode(value);
        Node parentNode = findParentNode(value);
        if (targetNode == null){
            return;
        }
        if (targetNode.left == null && targetNode.right == null){
            // 要删除的是叶子节点
            deleteLeafNode(targetNode, parentNode);
        } else if(targetNode.left != null && targetNode.right != null){
            // 要删除的是有两棵子树的节点
            deleteDoubleSonNode(targetNode);
        } else {
            // 要删除的是只有一棵子树的节点
            deleteSingleSonNode(targetNode, parentNode);
        }
    }



    public class Node{
        int value;
        Node left;
        Node right;
        Node(int value){
            this.value=value;
        }

        public void add(Node node){
            if (node==null)
                return;
            if (node.value<this.value){
                if (this.left==null)
                    this.left=node;
                else
                    this.left.add(node);
            }
            else {
                if (this.right==null)
                    this.right=node;
                else
                    this.right.add(node);
            }
        }

        public Node findNode(int value){
            if (this.value==value)
                return this;
            else if (value<this.value && this.left!=null) {
                return this.left.findNode(value);
            } else if (value>this.value && this.right!=null) {
                return this.right.findNode(value);
            }
            else
                return null;
        }

        public Node findParentNode(int value){
            if ((this.left!=null && this.left.value==value) || (this.right!=null && this.right.value==value))
                return this;
            else {
                if (this.left!=null && value<this.left.value)
                    return this.left.findParentNode(value);
                if (this.right!=null && this.right.value<value)
                    return this.right.findParentNode(value);
                else
                    return null;
            }
        }

        //前序遍历
        public static void preOrder(Node root){
            System.out.println(root.value);
            //左不为空 继续递归
            if (root.left!=null)
                preOrder(root.left);
            if (root.right!=null)
                preOrder(root.right);
        }

        //中序遍历
        public static void infixOrder(Node root){
            if (root.left!=null)
                infixOrder(root.left);
            System.out.println(root.value);
            if (root.right!=null)
                infixOrder(root.right);
        }

        //后序遍历
        public static void postOrder(Node root){
            if (root.left!=null)
                postOrder(root.left);
            if (root.right!=null)
                postOrder(root.right);
            System.out.println(root.value);
        }
    }
}


