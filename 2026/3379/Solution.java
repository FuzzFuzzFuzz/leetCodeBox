class Solution {
    public int[] constructTransformedArray(int[] nums) {
        
        int numsLength = nums.length;
        int[] result = new int[numsLength];
        
        for (int i = 0; i < numsLength; i++) {
            int currentNum = nums[i];
            
            if (currentNum > 0) {
                int newIndex = (i + currentNum) % numsLength;
                result[i] = nums[newIndex];
            }
            else if (currentNum < 0) {
                int newIndex = ((i + currentNum) % numsLength + numsLength) % numsLength;
                result[i] = nums[newIndex];
            }
            else {
                result[i] = currentNum;
            }
        }
        return result;
    }
}
