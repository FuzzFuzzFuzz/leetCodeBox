class Solution {
    public boolean hasAlternatingBits(int n) {

        // Iterate through all bits of the number
        while (n != 0) {

            // Extract the current (rightmost) bit
            int currentBit = (n & 1);

            // Shift right to move to the next bit
            n >>= 1;

            // Extract the next bit
            int nextBit = (n & 1);

            // If two adjacent bits are equal, the bits are not alternating
            if (currentBit == nextBit) {

                return false;
            }
        }

        return true;
    }
}
