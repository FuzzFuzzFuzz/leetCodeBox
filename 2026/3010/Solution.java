class Solution {
    public int minimumCost(int[] nums) {
        
        int numsLength = nums.length;
        int smallest = Integer.MAX_VALUE;
        int secondSmallest = Integer.MAX_VALUE;

        for (int i = 1; i < numsLength; i++) {

            if (nums[i] < smallest) {
                secondSmallest = smallest;
                smallest = nums[i];
            } else if (nums[i] < secondSmallest) {
                secondSmallest = nums[i];
            }
        }
        int sum = nums[0] + smallest + secondSmallest;
        
        return sum;
    }
}
