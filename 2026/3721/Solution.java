class SegmentTree {

    public int arraySize;
    public int treeSize;
    public int[] subtreeSum;          // total sum of values in this node's range
    public int[] subtreeMinPrefix;    // minimum prefix sum achievable within this node's range
    public int[] subtreeMaxPrefix;    // maximum prefix sum achievable within this node's range

    SegmentTree(int arraySize) {

        this.arraySize = arraySize;
        treeSize = 4 * arraySize;
        subtreeSum = new int[treeSize];
        subtreeMinPrefix = new int[treeSize];
        subtreeMaxPrefix = new int[treeSize];
    }

    void pullUp(int node) {
        
        // Recompute a node's values from its two children.
        // Min/max prefix: either the extreme is entirely in the left child,
        // or it crosses into the right child (left sum + right child's extreme).
         
        int leftChild = node * 2;
        int rightChild = node * 2 + 1;

        subtreeSum[node] = subtreeSum[leftChild] + subtreeSum[rightChild];

        subtreeMinPrefix[node] = Math.min(
            subtreeMinPrefix[leftChild],
            subtreeSum[leftChild] + subtreeMinPrefix[rightChild]
        );

        subtreeMaxPrefix[node] = Math.max(
            subtreeMaxPrefix[leftChild],
            subtreeSum[leftChild] + subtreeMaxPrefix[rightChild]
        );
    }

    void update(int targetIndex, int newValue) {

        // Walk down to the leaf at targetIndex, set its value, then pull up on the way back.

        int currentNode = 1;
        int rangeLeft = 0;
        int rangeRight = arraySize - 1;

        // Store the path of ancestor nodes so we can pull up after updating the leaf
        int[] ancestorPath = new int[32]; // supports arrays up to size 2^31
        int pathSize = 0;

        // Descend to the correct leaf
        while (rangeLeft != rangeRight) {

            ancestorPath[pathSize++] = currentNode;

            int rangeMid = rangeLeft + (rangeRight - rangeLeft) / 2;

            if (targetIndex <= rangeMid) {
                currentNode = currentNode * 2;
                rangeRight = rangeMid;
            }
            else {
                currentNode = currentNode * 2 + 1;
                rangeLeft = rangeMid + 1;
            }
        }

        // Set the leaf node's values (single element, so sum = min = max = newValue)
        subtreeSum[currentNode] = newValue;
        subtreeMinPrefix[currentNode] = newValue;
        subtreeMaxPrefix[currentNode] = newValue;

        // Pull up through all ancestors to keep internal nodes consistent
        while (pathSize > 0) {
            pullUp(ancestorPath[--pathSize]);
        }
    }

    int findRightmostIndexWithPrefixSum(int targetPrefixSum) {

        // Find the rightmost index r such that prefixSum(0..r) == targetPrefixSum.
        // At each node we check the RIGHT child first to find the rightmost match.
        // Returns -1 if no such index exists.

        int currentNode = 1;
        int rangeLeft = 0;
        int rangeRight = arraySize - 1;
        int prefixSumBeforeNode = 0; // accumulated sum from index 0 up to the start of this node's range

        // Check if the target is reachable at all within the entire tree
        boolean targetInRange = subtreeMinPrefix[currentNode] <= targetPrefixSum
                             && targetPrefixSum <= subtreeMaxPrefix[currentNode];

        if (!targetInRange) {
            return -1;
        }

        while (rangeLeft != rangeRight) {

            int rangeMid = rangeLeft + (rangeRight - rangeLeft) / 2;
            int leftChild = currentNode * 2;
            int rightChild = currentNode * 2 + 1;

            // The prefix sum at the start of the right child includes everything in the left child
            int prefixSumBeforeRightChild = prefixSumBeforeNode + subtreeSum[leftChild];

            // Amount the right child alone needs to contribute to hit the target
            int neededSumInRightChild = targetPrefixSum - prefixSumBeforeRightChild;

            // Prefer going right to find the rightmost valid index
            boolean targetReachableInRight = subtreeMinPrefix[rightChild] <= neededSumInRightChild
                                          && neededSumInRightChild <= subtreeMaxPrefix[rightChild];

            if (targetReachableInRight) {

                currentNode = rightChild;
                rangeLeft = rangeMid + 1;
                prefixSumBeforeNode = prefixSumBeforeRightChild;
            }
            else {
                currentNode = leftChild;
                rangeRight = rangeMid;
            }
        }

        return rangeLeft;
    }
}

class Solution {
    public int longestBalanced(int[] nums) {
        
        int n = nums.length;

        // Balance array segment tree: +1 for first-occurrence even, -1 for first-occurrence odd, 0 for duplicates
        SegmentTree segTree = new SegmentTree(n);

        // Maps each number to the index of its most recent first-occurrence marker in the balance array
        java.util.HashMap<Integer, Integer> firstOccurrenceIndex = new java.util.HashMap<>();

        int longestLength = 0;

        // Sweep left index from right to left
        for (int leftIndex = n - 1; leftIndex >= 0; leftIndex--) {

            int currentNum = nums[leftIndex];

            // If this number was already marked at some position to the right,
            // remove that marker — it is no longer the first occurrence
            Integer previousIndex = firstOccurrenceIndex.get(currentNum);

            if (previousIndex != null) {
                segTree.update(previousIndex, 0);
            }

            // Mark the current position as the first occurrence, +1 for even, -1 for odd
            firstOccurrenceIndex.put(currentNum, leftIndex);
            int balanceValue = (currentNum % 2 == 0) ? 1 : -1;

            segTree.update(leftIndex, balanceValue);

            // All positions before leftIndex are 0, so prefixSum(0..r) == subarray sum(leftIndex..r)
            // Find rightmost r where that sum is 0 (equal distinct evens and odds)
            int rightmostBalancedIndex = segTree.findRightmostIndexWithPrefixSum(0);

            if (rightmostBalancedIndex >= leftIndex) {

                int windowLength = rightmostBalancedIndex - leftIndex + 1;
                longestLength = Math.max(longestLength, windowLength);
            }
        }

        return longestLength;
    }
}
