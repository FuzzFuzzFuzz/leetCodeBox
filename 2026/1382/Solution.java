/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode balanceBST(TreeNode root) {
        // List to store nodes in sorted order
        List<Integer> sortedValues = new ArrayList<>();
        
        // Perform in-order traversal to get sorted values
        inOrderTraversal(root, sortedValues);
        
        // Build balanced BST from sorted values
        int startIndex = 0;
        int endIndex = sortedValues.size() - 1;
        TreeNode balancedRoot = buildBalancedBST(sortedValues, startIndex, endIndex);
        
        return balancedRoot;
    }
    
    // Helper method to perform in-order traversal and collect values
    private void inOrderTraversal(TreeNode node, List<Integer> values) {
        // Base case: empty node
        if (node == null) {
            return;
        }
        
        // Traverse left subtree
        inOrderTraversal(node.left, values);
        
        // Add current node value
        values.add(node.val);
        
        // Traverse right subtree
        inOrderTraversal(node.right, values);
    }
    
    // Helper method to build balanced BST from sorted array
    private TreeNode buildBalancedBST(List<Integer> values, int start, int end) {
        // Base case: invalid range
        if (start > end) {
            return null;
        }
        
        // Find middle index to use as root
        int middleIndex = start + (end - start) / 2;
        
        // Create root node with middle value
        int rootValue = values.get(middleIndex);
        TreeNode root = new TreeNode(rootValue);
        
        // Recursively build left subtree with left half
        int leftEnd = middleIndex - 1;
        root.left = buildBalancedBST(values, start, leftEnd);
        
        // Recursively build right subtree with right half
        int rightStart = middleIndex + 1;
        root.right = buildBalancedBST(values, rightStart, end);
        
        return root;
    }
}
