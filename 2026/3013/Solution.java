class Solution {
    public long minimumCost(int[] nums, int k, int dist) {
        int n = nums.length;
        TreeSet<Integer> using = new TreeSet<>((a, b) -> nums[a] != nums[b] ? nums[a] - nums[b] : a - b);
        TreeSet<Integer> waiting = new TreeSet<>((a, b) -> nums[a] != nums[b] ? nums[a] - nums[b] : a - b);

        long windowSum = 0;

        for (int i = 1; i <= dist + 1; i++) {
            using.add(i);
            windowSum += nums[i];
        }

        while (using.size() > k - 1) {
            int index = using.pollLast();
            windowSum -= nums[index];
            waiting.add(index);
        }

        long result = windowSum;

        for (int leftIndex = 1; leftIndex + dist + 1 < n; leftIndex++) {
            int rightIndex = leftIndex + dist + 1;
            waiting.add(rightIndex);

            if (using.remove(leftIndex)) {
                windowSum -= nums[leftIndex];
                int movedIndex = waiting.pollFirst();
                windowSum += nums[movedIndex];
                using.add(movedIndex);
            } else {
                waiting.remove(leftIndex);
                if (!waiting.isEmpty()) {
                    int waitingMin = waiting.first();
                    int usingMax = using.last();
                    if (nums[waitingMin] < nums[usingMax]) {
                        windowSum += nums[waitingMin] - nums[usingMax];
                        using.remove(usingMax);
                        using.add(waitingMin);
                        waiting.remove(waitingMin);
                        waiting.add(usingMax);
                    }
                }
            }

            result = Math.min(result, windowSum);
        }

        return result + nums[0];
    }
}
