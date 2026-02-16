class Solution {
    public int reverseBits(int n) {
        
        int result = 0;
        
        // Process all 32 bits
        for (int i = 0; i < 32; i++) {

            // Shift result left to make room for the next bit
            // Extract the rightmost bit from n using (n & 1)
            // Add this bit to result using OR operation
            result = (result << 1) | (n & 1);
            
            // Shift n right to process the next bit
            // Use >>> for logical shift (zero-fill) instead of >> (sign-extend)
            n = n >>> 1;
        }
        
        return result;
    }
}
