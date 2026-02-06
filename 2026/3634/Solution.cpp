class Solution {
public:
    int minRemoval(vector<int>& nums, int k) {
        
        sort(nums.begin(), nums.end());
        
        int n = nums.size();
        int maxLength = 1;
        int left = 0;
        
        for (int right = 0; right < n; right++) {
            while (left < right && nums[right] > (long long) k * nums[left]) {
                left++;
            }
            
            maxLength = max(maxLength, right - left + 1);
        }
        
        return n - maxLength;
    }
};
