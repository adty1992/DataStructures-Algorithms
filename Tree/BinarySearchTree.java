import java.util.*;
//这里的二叉树默认是二叉查找树，普通的二叉树基本没什么用，最常用的是二叉查找树
public class BinarySearchTree {

  //节点类
  private static class Node {
    public int key;
    public Node left;
    public Node right;

    public Node(int key) {
      this.key = key;
      this.left = null;
      this.right = null;
    }
  }

  private Node mRoot;//当前二叉树的根节点

  public static void main(String[] args) {
    BinarySearchTree tree = new BinarySearchTree();
    tree.addNode(20);
    tree.addNode(12);
    tree.addNode(22);
    tree.addNode(8);
    tree.addNode(16);
    tree.addNode(14);
    tree.addNode(18);
    tree.addNode(17);
    tree.addNode(19);

    //中序遍历
    inorderTraversal1(tree.mRoot);
    System.out.println("mRoot : " + tree.mRoot.key);

    tree.removeNode(16);
    System.out.println("after remove node 16 : ");
    inorderTraversal1(tree.mRoot);


    // Node dllHead = convertBst2Dll(tree.root);
    // Node focusNode = dllHead;
    // System.out.println("dllHead : " + dllHead.key);
    // while(focusNode != null) {
    //     System.out.println(focusNode.key + " ");
    //     focusNode = focusNode.right;
    //  }
  }

  /*
  向当前二叉树中增加节点
  */
  private void addNode(int key) {
    Node newNode = new Node(key);

    if (mRoot == null) {
      mRoot = newNode;
    } else {
      Node focusNode = mRoot;
      Node parent;

      while (true) { //这个循环保证可以结束，因为每次循环都会更新focusNode直至为null；树不可能无限大，所以总能到达null

        parent = focusNode;

        if (key < focusNode.key) {
          focusNode = focusNode.left;
          if (focusNode == null) {
            parent.left = newNode;
            return;
          }

        } else {//这里允许添加相同键值(key)的节点，并且放在右子树上；当然也可以不允许添加相同键值的节点
          focusNode = focusNode.right;
          if (focusNode == null) {
            parent.right = newNode;
            return;
          }
        }
      }
    }
  }

  //删除节点
  //成功删除返回true
  private boolean removeNode(int key) {
    if (mRoot == null) {
      return false;
    }

    Node focusNode = mRoot;
    Node parent = mRoot;
    boolean isLeftChild = true;

    while (key != focusNode.key) {
      parent = focusNode;

      if (key < focusNode.key) {
        focusNode = focusNode.left;
        isLeftChild = true;
      } else if (key > focusNode.key) {
        focusNode = focusNode.right;
        isLeftChild = false;
      }

      if (focusNode == null) {//not find the node we wanna delete, just return false
        return false;
      }
    }
    System.out.println("i find the node :" + focusNode.key);
    //to this, we find the node we wanna delete
    if (focusNode.left == null && focusNode.right == null) {//待删除节点是叶子
      if (focusNode == mRoot) mRoot =  null;
      else if (isLeftChild) parent.left = null;
      else parent.right = null;
    } else if (focusNode.right == null) {//待删除节点只含有左子树
      if (focusNode == mRoot) mRoot = focusNode.left;
      else if (isLeftChild) parent.left = focusNode.left;
      else parent.right = focusNode.left;
    } else if (focusNode.left == null) { //待删除节点只含有右子树
      if (focusNode == mRoot) mRoot = focusNode.right;
      if (isLeftChild) parent.left = focusNode.right;
      else parent.right = focusNode.right;
    } else {//待删除节点即含有左子树，又含有右子树
      System.out.println("delete node has left and right child");
      Node replacementNode = getReplacementNode(focusNode);//从从待删除右子树中选择一个节点(最小值的节点)作为替代待删除节点的节点
      System.out.println("replacemetNode is :" + replacementNode.key);
      if (focusNode == mRoot) mRoot = replacementNode;
      else if (isLeftChild) parent.left = replacementNode;
      else parent.right = replacementNode;

      replacementNode.left = focusNode.left;//将待删除节点的左孩子作为replacement节点的左孩子，不破坏原有的树结构
    }
    return true;
  }

  /*
  从以replacedNode为根节点的右子树中寻找最小值的节点返回，并将其从右子树中删除
  */
  private Node getReplacementNode(Node replacedNode) {
    Node rightRoot = replacedNode.right;
    Node replacementParent = replacedNode;
    Node replacementNode = replacedNode.right;

    while (replacementNode.left != null) {
      replacementParent = replacementNode;

      replacementNode = replacementNode.left;
    }

    if (replacementNode != rightRoot) {//如果替代节点不是右子树的根节点
      replacementParent.left = replacementNode.right;
      replacementNode.right = rightRoot;
    }

    return replacementNode;
  }

  /*
  判断一个二叉树是否是二叉搜索树
  */
  private boolean isBST(Node root, int min, int max) {
    if (root == null) return true;//空树也是二叉搜索树
    if (root.key <= min || root.key > max) return false; //不符合左子树都小于节点，右子树都大于节点的要求
    //这个区间也可以是左开右闭的，要看BST的实现对键值相等的节点的处理
    return isBST(root.left, min, root.key) && isBST(root.right, root.key, max);//recursive implementation
  }

  //获取全部节点数
  //1. 递归实现
  private static int getNodesNum1(Node rootNode) {
    if (rootNode == null) {
      return 0;
    }
    return getNodesNum1(rootNode.left) + getNodesNum1(rootNode.right) + 1;

  }
  //2. 循环实现
  private static int getNodesNum2(Node rootNode) {
    if (rootNode == null) {
      return 0;
    }
    int sum = 0;//计数器
    LinkedList<Node> nodeQueue = new LinkedList<>();
    nodeQueue.addLast(rootNode);
    while (!nodeQueue.isEmpty()) {
      Node node = nodeQueue.removeFirst();//从队头取出节点
      sum++;//弹出一个节点计数器加1
      if (node.left != null) {
        nodeQueue.addLast(node.left);
      }
      if (node.right != null) {
        nodeQueue.addLast(node.right);
      }
    }
    return sum;
  }

  /**
    * 求二叉树的深度（高度） 递归解法： O(n)
    * （1）如果二叉树为空，二叉树的深度为0
    * （2）如果二叉树不为空，二叉树的深度 = max(左子树深度， 右子树深度) + 1
    */
  private static int getHeight1(Node rootNode) {
    if (rootNode == null) {
      return 0;
    }
    int leftHeight = getHeight1(rootNode.left);
    int rightHeight = getHeight1(rootNode.right);
    return Math.max(leftHeight, rightHeight) + 1;
  }
  private static int getHeight2(Node rootNode) {
    if (rootNode == null) {
      return 0;
    }
    int height = 0;
    int curLevNodes = 1;//当前层的节点数
    int nextLevNodes = 0;//下一层的节点数
    LinkedList<Node> nodeQueue = new LinkedList<>();
    nodeQueue.addLast(rootNode);

    while (!nodeQueue.isEmpty()) {
      Node node = nodeQueue.removeFirst();
      curLevNodes--;//因为队头移除一个节点，所以当前层的节点数要减去1
      if (node.left != null) {
        nodeQueue.addLast(node.left);
        nextLevNodes++;//因为在队尾增加了一个当前节点的孩子节点，所以下一层节点数要加1
      }
      if (node.right != null) {
        nodeQueue.addLast(node.right);
        nextLevNodes++;//同上
      }

      if (curLevNodes == 0) {//当前层的节点数为0，即已经遍历完了当前层了，就要将二叉树高度加1，并且重置下一层节点数为当前层节点数
        height++;
        curLevNodes = nextLevNodes;
        nextLevNodes = 0;
      }
    }
    return height;
  }

  /**
     * 前序遍历，中序遍历，后序遍历 前序遍历递归解法：
     * （1）如果二叉树为空，空操作
     * （2）如果二叉树不为空，访问根节点，前序遍历左子树，前序遍历右子树
     */
  private static void preorderTraversal1(Node rootNode) {
    if (rootNode == null) {
      return;
    }
    System.out.println(rootNode.key + " ");
    preorderTraversal1(rootNode.left);
    preorderTraversal1(rootNode.right);
  }

  /**
     *  前序遍历迭代解法：用一个辅助stack，总是先把右孩子放进栈
     *  http://www.youtube.com/watch?v=uPTCbdHSFg4
     */
  private static void preorderTraversal2(Node rootNode) {
    if (rootNode == null) {
      return;
    }
    Stack<Node> stack = new Stack<>();//用栈的思想
    stack.push(rootNode);//入栈
    while (!stack.isEmpty()) {
      Node node = stack.pop();//出栈
      System.out.print(node.key + " ");
      // 关键点：要先压入右孩子，再压入左孩子，这样在出栈时会先打印左孩子再打印右孩子
      if (node.right != null) {
        stack.push(node.right);
      }
      if (node.left != null) {
        stack.push(node.left);
      }
    }
  }

  /**
    * 中序遍历递归解法
    * （1）如果二叉树为空，空操作。
    * （2）如果二叉树不为空，中序遍历左子树，访问根节点，中序遍历右子树
    */
  private static void inorderTraversal1(Node rootNode) {
    if (rootNode == null) {
      return;
    }
    inorderTraversal1(rootNode.left);
    System.out.println(rootNode.key + " ");
    inorderTraversal1(rootNode.right);
  }

  /**
     * 中序遍历迭代解法 ，用栈先把根节点的所有左孩子都添加到栈内，
     * 然后输出栈顶元素，再处理栈顶元素的右子树
     * http://www.youtube.com/watch?v=50v1sJkjxoc
     *
     * 还有一种方法能不用递归和栈，基于线索二叉树的方法，较麻烦以后补上
     * http://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion-and-without-stack/
     */
  private static void inorderTraversal2(Node rootNode) {
    if (rootNode == null) {
      return;
    }
    Stack<Node> stack = new Stack<>();
    Node curNode = rootNode;
    while (true) {
      if (curNode != null) {
        stack.push(curNode);
        curNode = curNode.left;
      } else {
        if (stack.isEmpty()) {
          break;
        }
        curNode = stack.pop();
        System.out.println(curNode.key + "  ");
        curNode = curNode.right;
      }
    }
  }

  /**
     * 后序遍历递归解法
     * （1）如果二叉树为空，空操作
     * （2）如果二叉树不为空，后序遍历左子树，后序遍历右子树，访问根节点
     */
  private static void postorderTraversal1(Node rootNode) {
    if (rootNode == null) {
      return;
    }
    postorderTraversal1(rootNode.left);
    postorderTraversal1(rootNode.right);
    System.out.println(rootNode.key + " ");
  }

  /**
     *  后序遍历迭代解法
     *  使用两个栈
     *  https://www.youtube.com/watch?v=qT65HltK2uE
     */
  private static void postorderTraversal2(Node rootNode) {
    if (rootNode == null) {
      return;
    }
    Stack<Node> stack1 = new Stack<>();
    Stack<Node> stack2 = new Stack<>();
    Node curNode = rootNode;
    stack1.push(curNode);
    while (!stack1.isEmpty()) {
      curNode = stack1.pop();
      stack2.push(curNode);
      if (curNode.left != null) {
        stack1.push(curNode.left);
      }
      if (curNode.right != null) {
        stack1.push(curNode.right);
      }
    }
    while (!stack2.isEmpty()) {
      System.out.println(stack2.pop().key + "  ");
    }
  }

  /**
    * 分层遍历二叉树（按层次从上往下，从左往右）迭代
    * 相当于广度优先搜索，使用队列实现。队列初始化，将根节点压入队列。当队列不为空，进行如下操作：弹出一个节点
    * ，访问，若左子节点或右子节点不为空，将其压入队列
    */
  public static void levelTraversal(Node rootNode) {
    if (rootNode == null) {
      return;
    }
    LinkedList<Node> nodeQueue = new LinkedList<>();
    Node focusNode = rootNode;
    nodeQueue.addLast(focusNode);
    while (!nodeQueue.isEmpty()) {
      focusNode = nodeQueue.removeFirst();
      System.out.println(focusNode.key + "  ");
      if (focusNode.left != null) {
        nodeQueue.addLast(focusNode.left);
      }
      if (focusNode.right != null) {
        nodeQueue.addLast(focusNode.right);
      }
    }
  }

  /**
   * 题目：输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
   * 要求不能创建任何新的结点，只能调整树中结点指针的指向。
   *
   * @param root 二叉树的根结点
   * @return 双向链表的头结点
   */
  public static Node convertBst2Dll(Node root) {
    //1.new一个数组用于保存处理过程中的双向链表的尾结点
    Node[] lastNode = new Node[1];

    //2.开始转换
    convertNode(root, lastNode);

    //3.转换结束，找到双向链表的头结点
    Node head = lastNode[0];
    while (head != null && head.left != null) {
      head = head.left;
    }
    return head;
  }
  /**
   * 链表表转换操作
   *
   * @param node     当前的根结点
   * @param lastNode 已经处理好的双向链表的尾结点，使用一个长度为1的数组，类似C++中的二级指针
   */
  public static void convertNode(Node node, Node[] lastNode) {
    // 结点不为空
    if (node != null) {
      // 如果有左子树就先处理左子树
      if (node.left != null) {
        convertNode(node.left, lastNode);
      }

      // 将当前结点的前驱指向已经处理好的双向链表（由当前结点的左子树构成）的尾结点
      node.left = lastNode[0];
      // 如果左子树转换成的双向链表不为空，设置尾结点的后继
      if (lastNode[0] != null) {
        lastNode[0].right = node;
      }
      // 记录当前结点为尾结点
      lastNode[0] = node;

      // 处理右子树
      if (node.right != null) {
        convertNode(node.right, lastNode);
      }
    }
  }

}