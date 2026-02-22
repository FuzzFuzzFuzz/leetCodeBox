class Solution {
public:
    int binaryGap(int n) {
        
        // Track the maximum distance found
        int maxDistance = 0;
        
        // Track the position of the last 1 bit we encountered
        // Initialize to -1 to indicate we haven't found a 1 yet
        int lastOnePosition = -1;
        
        // Track the current bit position (0-indexed from right)
        int currentPosition = 0;
        
        // Process each bit of n until n becomes 0
        while (n > 0) {

            // Check if the rightmost bit is 1
            if ((n & 1) == 1) {

                // If we've seen a 1 before, calculate the distance
                if (lastOnePosition != -1) {

                    int distance = currentPosition - lastOnePosition;
                    maxDistance = std::max(maxDistance, distance);
                }

                // Update the position of the last 1 we found
                lastOnePosition = currentPosition;
            }
            
            // Shift n right to process the next bit
            n >>= 1;
            
            // Move to the next position
            currentPosition++;
        }
        
        return maxDistance;
    }
};
