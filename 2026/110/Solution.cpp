class Solution {
public:
    bool isBalanced(TreeNode* root) {
      
        return checkHeight(root) != -1;
    }
    
private:
    int checkHeight(TreeNode* node) {
      
        if (node == nullptr) {
            return 0;
        }
        
        int leftHeight = checkHeight(node->left);
        
        if (leftHeight == -1) {
            return -1;
        }
        
        int rightHeight = checkHeight(node->right);
        
        if (rightHeight == -1) {
            return -1;
        }
        
        int heightDifference = abs(leftHeight - rightHeight);
        
        if (heightDifference > 1) {
            return -1;
        }
        
        int currentHeight = max(leftHeight, rightHeight) + 1;
        return currentHeight;
    }
};
