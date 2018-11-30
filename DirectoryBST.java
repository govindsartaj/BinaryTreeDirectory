package trees;

/**
 * class that builds a binary search tree of directory Entries 
 *   with students and faculty for the purpose of testing methods
 *	
 */

public class DirectoryBST {

    public static void main (String [] argv) {
        // test of various methods
        // constructors
        BSTree <Entry> tree = new BSTree<Entry>();

       Student stu1 = new Student("Terry", "Walker", "walkert@math.grinnell.edu",
                                   1970, "off-campus");
        Student stu2 = new Student("Barbara", "Ellen", "barbara@cs.grinnell.edu",
                                   2002, "12-34-56");
        Student stu3 = new Student("Donna", "Marie", "donna@math.grinnell.edu",
                                   1998, "3.1415926535");
        Student stu4 = new Student("Shamrock", "The Cat", "none",
                                   3000, "varies");
        Student stu5 = new Student("Muffin", "The Cat", "none",
                                   3000, "varies");

        Faculty fac1 = new Faculty("John", "Stone", "stone@cs.grinnell.edu",
                                 "Science 2418", 3181,
                                 "Computer Science", 1983);
        Faculty fac2 = new Faculty("Henry", "Walker", "walker@cs.grinnell.edu",
                                 "Science 2420", 4208, 
                                 "Computer Science", 1973);
        Faculty fac3 = new Faculty("Janet", "Gibson", "gibson@grinnell.edu",
                                 "Science 0420", 3168,
                                 "Psychology", 1989);
        Faculty fac4 = new Faculty("Samuel", "Rebelsky","rebelsky@cs.grinnell.edu",
                                 "Science 2427", 4410,
                                 "Computer Science", 1997);
       
        
        
        // testing methods
        
        // testing nodeLevel on empty tree
        System.out.println(tree.nodeLevel(stu1));
        
        // testing height on empty tree
        System.out.println(tree.height());
        
        // testing isBalanced on empty tree
        System.out.println(tree.isBalanced());
        
        // testing isHeightBalanced on empty tree
        System.out.println(tree.isHeightBalanced());
        
        // testing remove on empty tree
        //tree.remove(fac1);
        
        // testing findLongestEntry on empty tree
        System.out.println(tree.findLongest(tree));
        

        // insert entries to directory
        tree.insert(stu1);
        
        // testing nodeLevel with data at root
        System.out.println(tree.nodeLevel(stu1));
        
        // testing nodeLevel with data not in tree
        System.out.println(tree.nodeLevel(stu3));

        // testing height on tree with only one 
        //   node: root, and no subtrees
        System.out.println(tree.height());
        
        // testing isBalanced on tree with only one
        //   node: root, and no subtrees
        System.out.println(tree.isBalanced());
        
        // testing isHeightBalanced on tree with only
        //   one node: root, and no subtrees
        System.out.println(tree.isHeightBalanced());
        
        // testing findLongest on tree with 
        //   only one node: root, and no subtrees
        System.out.println(tree.findLongest(tree));
        
        // testing remove on tree with only one node: root, 
        //   and no subtrees
        tree.remove(stu1);
        
        // re-inserting previous root 
        tree.insert(stu1);
        
        // adding node (left child of root)      
        tree.insert(fac1);
        
        // testing isBalanced on tree with 
        //   node which has left data but not right data
        System.out.println(tree.isBalanced());
        
        // testing isHeightBalanced on tree with 
        //   node which has left data but not right data
        System.out.println(tree.isHeightBalanced());
        
        
        // removing left child of root
        tree.remove(fac1);
        
        // initializing entry for testing purposes
        Student stuTest = new Student("Zuffinay", "Ze Cat", "none",
                9000, "varies");
        
        // adding right child of root
        tree.insert(stuTest);
        
        
        // testing isBalanced on tree with 
        //   node which has right data but not left data
        System.out.println(tree.isBalanced());
        
        // testing isHeightBalanced on tree with 
        //   node which has right data but not left data
        System.out.println(tree.isHeightBalanced());
        
        // removing test entry
        tree.remove(stuTest);
        
        // adding remaining entries
        tree.insert(fac1);
        tree.insert(stu2);
        tree.insert(fac2);
        tree.insert(stu3);
        tree.insert(fac3);
        tree.insert(stu4);
        tree.insert(fac4);
        tree.insert(stu5);
        
        // testing nodeLevel with data at level 2
        System.out.println(tree.nodeLevel(stu2));
        
        // testing height on tree with lab data
        System.out.println(tree.height());
        
        // testing isBalanced on tree with lab data
        System.out.println(tree.isBalanced());

        // testing isHeightBalanced on tree with lab data
        System.out.println(tree.isHeightBalanced());
        
        // testing isBalanced on tree with lab data + test node
        tree.insert(stuTest);
        System.out.println(tree.isBalanced());
        
        // testing isHeightBalanced on tree with lab data + test node
        System.out.println(tree.isHeightBalanced());
        
        // testing findLongest on tree with lab data + test node
        System.out.println(tree.findLongest(tree));
        
        // removing testNode
        tree.remove(stuTest);

        // testing findLongest on tree with lab data
        System.out.println(tree.findLongest(tree));
        
        // testing remove on non-root node where node
        //   has no right child but has left child
        tree.remove(stu4);
        
        // testing remove on non-root node where node
        //   has no left child but has right child
        tree.remove(stu2);
        
        // testing remove on root
        tree.remove(stu1);
        
        // testing remove on non-root node where node
        //   has both right and left children
        tree.remove(stu3);
                
        // testing remove on node that is a leaf
        tree.remove(fac3);
        
        // printing tree
        tree.print();

        
    }
}