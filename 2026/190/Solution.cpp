class Solution {
public:
    uint32_t reverseBits(uint32_t n) {

        uint32_t result = 0;
        
        // Process all 32 bits
        for (int i = 0; i < 32; i++) {
            
            // Shift result left to make room for the next bit
            // Extract the rightmost bit from n using (n & 1)
            // Add this bit to result using OR operation
            result = (result << 1) | (n & 1);
            
            // Shift n right to process the next bit
            n = n >> 1;
        }
        
        return result;
    }
};
