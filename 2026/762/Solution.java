class Solution {
    public int countPrimeSetBits(int left, int right) {

        // Counter for numbers with prime set bits
        int count = 0;
        
        // Iterate through each number in the range
        for (int number = left; number <= right; number++) {

            // Count the number of set bits (1's) in binary representation
            int setBits = Integer.bitCount(number);
            
            // Check if the count is a prime number
            boolean isPrime = isPrimeNumber(setBits);
            
            // If prime, increment our counter
            if (isPrime) {
                count++;
            }
        }
        
        return count;
    }
    
    // Helper method to check if a number is prime
    private boolean isPrimeNumber(int n) {

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
}
