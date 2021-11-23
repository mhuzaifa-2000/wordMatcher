package assignment3;


//BST Credits: https://www.geeksforgeeks.org/binary-search-tree-set-1-search-and-insertion/ 
// Modified BST to work for the Word class object and do string comparisons
class BinarySearchTree {
	private static final int COUNT = 10;
    class Node
    {
        Word word;
        Node left, right;
 
        public Node(String item)
        {
        	this.word = new Word(item);
            left = right = null;
        }
        public Word  getWord() {
        	return this.word;
        }
    }
 
    // Root of BST
    Node root;
 
    // Constructor
    BinarySearchTree()
    {
         root = null;
    }
 
    // This method mainly calls insertRec()
    void add(String item)
    {
    	if(this.search(item) == null) {
    		root = insertRec(root, item);
    	}  
    }
 
    /* A recursive function to
       insert a new key in BST */
    Node insertRec(Node root, String item)
    {
 
        /* If the tree is empty,
           return a new node */
        if (root == null)
        {
            root = new Node(item);
            return root;
        }
 
        /* Otherwise, recur down the tree */
        if (item.compareToIgnoreCase(root.word.getWord())<0)
            root.left = insertRec(root.left, item);
        else
            root.right = insertRec(root.right, item);
 
        /* return the (unchanged) node pointer */
        return root;
    }
 
    // This method mainly calls InorderRec()
    void inorder()
    {
         inorderRec(root);
    }
    public Node search(String item) {
    	Node temp = root;
    	if (root == null)
        {
            return null;
        }
    	while (temp != null) {
            // pass right subtree as new tree
            if (item.compareToIgnoreCase(temp.word.getWord())>0)
                temp = temp.right;
            else if (item.compareToIgnoreCase(temp.word.getWord())<0)
                temp = temp.left;
            else
                return temp;
        }
        return null;
    }
    // A utility function to
    // do inorder traversal of BST
    void inorderRec(Node root)
    {
        if (root != null) {
            inorderRec(root.left);
            System.out.println(root.word);
            inorderRec(root.right);
        }
    }
    void inorderFreq(Node root)
    {
        if (root != null) {
            inorderFreq(root.left);
            if(root.word.getFrequency()>0)
            {
            	System.out.println("Word: "+root.word+" Frequency: "+Integer.toString(root.word.getFrequency()));
            }
            inorderFreq(root.right);
        }
    }
    static void print2DUtil(Node root, int space)
    {
        // Base case
        if (root == null)
            return;
     
        // Increase distance between levels
        space += COUNT;
     
        // Process right child first
        print2DUtil(root.right, space);
     
        // Print current node after space
        // count
        System.out.print("\n");
        for (int i = COUNT; i < space; i++)
            System.out.print(" ");
        System.out.print(root.word + "\n");
     
        // Process left child
        print2DUtil(root.left, space);
    }
    
    public void displayMatchedWordsAndFrequencies() {
    	inorderFreq(root);
    }
    // Wrapper over print2DUtil()
    public void print2D()
    {
        // Pass initial space count as 0
    	System.out.println("2D print of BST(left most is the root node)");
        print2DUtil(this.root, 0);
    }
    
}