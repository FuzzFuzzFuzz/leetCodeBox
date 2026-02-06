class Solution {
    public int minRemoval(int[] nums, int k) {
        
        Arrays.sort(nums);
        
        int n = nums.length;
        int maxLength = 1;
        int left = 0;
        
        for (int right = 0; right < n; right++) {
            
            while (left < right && nums[right] > (long) k * nums[left]) {
                left++;
            }
            
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return n - maxLength;
    }
}
