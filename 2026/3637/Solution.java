class Solution {
    public boolean isTrionic(int[] nums) {

        int numsLength = nums.length;
        if ((numsLength < 4) || (nums[1] < nums[0])) {
            return false;
        }

        boolean firstInc = false;
        boolean secondDec = false;
        boolean thirdInc = false;

        for (int i = 1; i < numsLength; i++) {
            if (!firstInc) {
                if (nums[i] > nums[i - 1]) {
                    firstInc = true;
                    continue;
                } else if (nums[i] == nums[i - 1]) {
                    return false;
                }
            } else if (!secondDec) {
                if (nums[i] < nums[i - 1]) {
                    secondDec = true;
                    continue;
                } else if (nums[i] == nums[i - 1]) {
                    return false;
                }
            } else if (!thirdInc) {
                if (nums[i] > nums[i - 1]) {
                    thirdInc = true;
                    continue;
                } else if (nums[i] == nums[i - 1]) {
                    return false;
                }
            } else {
                if ((nums[i] <= nums[i - 1]) || (nums[i] == nums[i - 1])) {
                    return false;
                }
            }
        }
        if ((firstInc) && (secondDec) && (thirdInc)) {
            return true;
        }
        return false;
    }
}
