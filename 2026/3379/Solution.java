class Solution {
    public int[] constructTransformedArray(int[] nums) {
        
        int numsLength = nums.length;
        int[] result = new int[numsLength];

        for (int i = 0; i < numsLength; i++) {

            int currentNum = nums[i];

            if (currentNum > 0) {
                int newNum = i + currentNum;
                newNum = (newNum % numsLength);
                result[i] = nums[newNum];
            }
            else if (currentNum < 0) {
                int newNum = i - Math.abs(currentNum);
                while (newNum < 0) {
                    newNum = numsLength + newNum;
                }
                result[i] = nums[newNum];
            }
            else {
                result[i] = currentNum;
            }
        }
        return result;
    }
}
