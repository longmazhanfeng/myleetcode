import java.util.ArrayList;
import java.util.List;



public class leetcode1 {

}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }

class Solution {
    List<Integer> list = new ArrayList();
    public List<Integer> inorderTraversal(TreeNode root) {
//        List<Integer> list = new ArrayList();
        getNodeVal(root);
        return list;
    }
    
    public void getNodeVal(TreeNode node) {
    	list.add(node.val);
    	if (node.left != null) {
			getNodeVal(node.left); 
		}
    	
    	if (node.right != null) {
			getNodeVal(node.right);
		}
    }
}