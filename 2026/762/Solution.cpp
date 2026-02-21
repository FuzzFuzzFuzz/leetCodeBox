class Solution {
public:
    int countPrimeSetBits(int left, int right) {

        // Counter for numbers with prime set bits
        int count = 0;
        
        // Iterate through each number in the range
        for (int number = left; number <= right; number++) {

            // Count the number of set bits (1's) in binary representation
            int setBits = countSetBits(number);
            
            // Check if the count is a prime number
            bool isPrime = isPrimeNumber(setBits);
            
            // If prime, increment our counter
            if (isPrime) {
                count++;
            }
        }
        
        return count;
    }
    
private:
    // Helper method to count set bits in a number
    int countSetBits(int n) {

        int count = 0;
        
        // Count each set bit by checking rightmost bit and shifting
        while (n > 0) {

            // Add 1 if rightmost bit is set
            count += n & 1;

            // Right shift to check next bit
            n >>= 1;
        }
        
        return count;
    }
    
    // Helper method to check if a number is prime
    bool isPrimeNumber(int n) {

        // Numbers less than 2 are not prime
        if (n < 2) {
            return false;
        }
        
        // 2 is the only even prime
        if (n == 2) {
            return true;
        }
        
        // Even numbers greater than 2 are not prime
        if (n % 2 == 0) {
            return false;
        }
        
        // Check odd divisors up to square root of n
        for (int divisor = 3; divisor * divisor <= n; divisor += 2) {

            if (n % divisor == 0) {
                return false;
            }
        }
        
        return true;
    }
};
