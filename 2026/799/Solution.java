class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {

        // Create a 2D array to represent the champagne tower
        // tower[row][glass] stores the amount of champagne in that glass
        double[][] tower = new double[101][101];
        
        // Pour all champagne into the topmost glass
        tower[0][0] = poured;
        
        // Simulate champagne flowing down the pyramid
        for (int currentRow = 0; currentRow <= query_row; currentRow++) {
            
            for (int currentGlass = 0; currentGlass <= currentRow; currentGlass++) {

                // Calculate the excess champagne that overflows from current glass
                // A glass can only hold 1 cup, so excess = (total - 1) / 2
                // Divide by 2 because it splits equally to left and right
                double excessChampagne = (tower[currentRow][currentGlass] - 1.0) / 2.0;
                
                // If there is excess champagne, distribute it to glasses below
                if (excessChampagne > 0) {

                    // Pour to the glass directly below-left
                    tower[currentRow + 1][currentGlass] += excessChampagne;
                    
                    // Pour to the glass directly below-right
                    tower[currentRow + 1][currentGlass + 1] += excessChampagne;
                }
            }
        }
        
        // Return the amount in the queried glass, capped at 1.0
        // (a glass cannot hold more than 1 cup)
        return Math.min(1.0, tower[query_row][query_glass]);
    }
}
