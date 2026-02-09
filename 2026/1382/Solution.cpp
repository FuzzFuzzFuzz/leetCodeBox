/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    TreeNode* balanceBST(TreeNode* root) {
        
        // Vector to store nodes in sorted order
        vector<int> sortedValues;
        
        // Perform in-order traversal to get sorted values
        inOrderTraversal(root, sortedValues);
        
        // Build balanced BST from sorted values
        int startIndex = 0;
        int endIndex = sortedValues.size() - 1;
        TreeNode* balancedRoot = 
            buildBalancedBST(sortedValues, startIndex, endIndex);
        
        return balancedRoot;
    }
    
private:
    // Helper method to perform in-order traversal and collect values
    void inOrderTraversal(TreeNode* node, vector<int>& values) {
        
        // Base case: empty node
        if (node == nullptr) {
            return;
        }
        
        // Traverse left subtree
        inOrderTraversal(node->left, values);
        
        // Add current node value
        values.push_back(node->val);
        
        // Traverse right subtree
        inOrderTraversal(node->right, values);
    }
    
    // Helper method to build balanced BST from sorted array
    TreeNode* buildBalancedBST(vector<int>& values, int start, int end) {
        
        // Base case: invalid range
        if (start > end) {
            return nullptr;
        }
        
        // Find middle index to use as root
        int middleIndex = start + (end - start) / 2;
        
        // Create root node with middle value
        int rootValue = values[middleIndex];
        TreeNode* root = new TreeNode(rootValue);
        
        // Recursively build left subtree with left half
        int leftEnd = middleIndex - 1;
        root->left = buildBalancedBST(values, start, leftEnd);
        
        // Recursively build right subtree with right half
        int rightStart = middleIndex + 1;
        root->right = buildBalancedBST(values, rightStart, end);
        
        return root;
    }
};
