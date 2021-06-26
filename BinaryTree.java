import java.util.Scanner;

class Node 
{

	int data;
	Node left, right;

	Node(int d)
	{
		data = d;
		left = right = null;
	}
}

class Index 
{

	int index = 0;
}

public class BinaryTree 
{

	Index index = new Index();

	Node constructTreeUtil(int pre[], Index preIndex,
						int low, int high, int size)
	{

		if (preIndex.index >= size || low > high) 
		{
			return null;
		}

		Node root = new Node(pre[preIndex.index]);
		preIndex.index = preIndex.index + 1;

		if (low == high) 
		{
			return root;
		}

		int i;
		for (i = low; i <= high; ++i) 
		{
			if (pre[i] > root.data) 
			{
				break;
			}
		}

		root.left = constructTreeUtil(
			pre, preIndex, preIndex.index, i - 1, size);
		root.right = constructTreeUtil(pre, preIndex, i,
									high, size);

		return root;
	}

	Node constructTree(int pre[], int size)
	{
		return constructTreeUtil(pre, index, 0, size - 1,
								size);
	}

    void printLevelOrder(Node root) 
	{ 
		int h = height(root); 
		int i; 
		for (i=1; i<=h; i++) 
			printGivenLevel(root, i); 
	} 

	int height(Node root) 
	{ 
		if (root == null) 
		return 0; 
		else
		{ 
			int lheight = height(root.left); 
			int rheight = height(root.right); 
			
			if (lheight > rheight) 
				return(lheight+1); 
			else return(rheight+1); 
		} 
	} 

	void printGivenLevel (Node root ,int level) 
	{ 
		if (root == null) 
			return; 
		if (level == 1) 
			System.out.print(root.data + " "); 
		else if (level > 1) 
		{ 
			printGivenLevel(root.left, level-1); 
			printGivenLevel(root.right, level-1); 
		} 
	}

	void printPostorder(Node node) 
    { 
    	if (node == null) 
    		return; 
    	printPostorder(node.left); 
    	printPostorder(node.right);
    	System.out.print(node.data + " "); 
    } 
    
    void printInorder(Node node) 
    { 
    	if (node == null) 
    		return; 
    	printInorder(node.left); 
    	System.out.print(node.data + " "); 
    	printInorder(node.right); 
    } 
    
    void printPreorder(Node node) 
    { 
    	if (node == null) 
    		return; 
    	System.out.print(node.data + " "); 
    	printPreorder(node.left); 
    	printPreorder(node.right); 
    }


	public static void main(String[] args)
	{
	    
	    int n;
        Scanner s = new Scanner(System.in);
        System.out.print("Enter no. of nodes you want in binary tree:");
        n = s.nextInt();
        int pre[] = new int[n];
        System.out.println("Enter all the nodes:");
	    for(int i = 0; i < n; i++)
        {
            int position = i + 1 ;
            System.out.println("Enter node " + position + " :");
            pre[i] = s.nextInt();
        }
	    
		BinaryTree tree = new BinaryTree();
		//int pre[] = new int[] { 90, 25, 1, 7, 25, 50 };
		int size = pre.length;
		Node root = tree.constructTree(pre, size);
		
		System.out.println("Breadth First Traversal:"); 
		tree.printLevelOrder(root); 
		
        System.out.println("\n\nDepth First Traversals:\n\n"); 
        
        System.out.println("Preorder traversal:"); 
        tree.printPreorder(root); 
  
        System.out.println("\n\nInorder traversal:"); 
        tree.printInorder(root); 
  
        System.out.println("\n\nPostorder traversal:"); 
        tree.printPostorder(root);
	}
}