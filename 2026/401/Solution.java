class Solution {
    public List<String> readBinaryWatch(int turnedOn) {

        List<String> result = new ArrayList<>();
        
        // Try all possible hours (0-11)
        for (int hour = 0; hour < 12; hour++) {

            // Try all possible minutes (0-59)
            for (int minute = 0; minute < 60; minute++) {

                // Count total bits set in hour and minute
                int bitsCount = Integer.bitCount(hour) + Integer.bitCount(minute);
                
                // If matches the required number of turned on LEDs
                if (bitsCount == turnedOn) {
                    
                    // Format: "H:MM" (no leading zero for hour, leading zero for minute if needed)
                    result.add(String.format("%d:%02d", hour, minute));
                }
            }
        }
        
        return result;
    }
}
