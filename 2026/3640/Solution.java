class Solution {
    public long maxSumTrionic(int[] nums) {
        int n = nums.length;

        long result = 0L;
        if (n < 4) {
            return result;
        }

        final long NEG_INF = Long.MIN_VALUE;

        long incSum = NEG_INF;
        long incDecSum = NEG_INF;
        long trionicSum = NEG_INF;

        long best = NEG_INF;

        for (int i = 1; i < n; i++) {
            long nextIncSum = NEG_INF;
            long nextIncDecSum = NEG_INF;
            long nextTrionicSum = NEG_INF;

            int prev = nums[i - 1];
            int curr = nums[i];

            if (curr > prev) {
                long startInc = (long) prev + (long) curr;
                long extendInc = addIfValid(incSum, curr, NEG_INF);
                nextIncSum = Math.max(startInc, extendInc);

                long extendTrionic = addIfValid(trionicSum, curr, NEG_INF);
                long startFinalInc = addIfValid(incDecSum, curr, NEG_INF);
                nextTrionicSum = Math.max(extendTrionic, startFinalInc);
            }

            if (curr < prev) {
                long extendDec = addIfValid(incDecSum, curr, NEG_INF);
                long startDec = addIfValid(incSum, curr, NEG_INF);
                nextIncDecSum = Math.max(extendDec, startDec);
            }

            if (nextTrionicSum > best) {
                best = nextTrionicSum;
            }

            incSum = nextIncSum;
            incDecSum = nextIncDecSum;
            trionicSum = nextTrionicSum;
        }

        if (best != NEG_INF) {
            result = best;
        }

        return result;
    }

    private static long addIfValid(long base, int add, long NEG_INF) {
        long value = NEG_INF;
        if (base != NEG_INF) {
            value = base + (long) add;
        }
        return value;
    }
}
