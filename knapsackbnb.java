import java.util.PriorityQueue;

public class knapsackbnb {
    static class Node implements Comparable<Node> {
        int level;
        int profit;
        int weight;
        double bound;

        public Node(int level, int profit, int weight, double bound) {
            this.level = level;
            this.profit = profit;
            this.weight = weight;
            this.bound = bound;
        }

        @Override
        public int compareTo(Node other) {
            return Double.compare(other.bound, this.bound);
        }
    }

    public static int getMaxValue(int[] weights, int[] values, int maxWeight) {
        int n = weights.length;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(-1, 0, 0, 0));

        int maxProfit = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (node.bound < maxProfit) {
                continue;
            }
            if (node.level == n-1) {
                continue;
            }

            int nextLevel = node.level + 1;

            // Exclude next item
            Node excludeNode = new Node(nextLevel, node.profit, node.weight, node.bound);
            pq.add(excludeNode);

            // Include next item
            int nextWeight = node.weight + weights[nextLevel];
            if (nextWeight <= maxWeight) {
                int nextProfit = node.profit + values[nextLevel];
                double nextBound = getBound(nextProfit, nextWeight, values, weights, maxWeight, nextLevel);
                if (nextProfit > maxProfit) {
                    maxProfit = nextProfit;
                }
                Node includeNode = new Node(nextLevel, nextProfit, nextWeight, nextBound);
                pq.add(includeNode);
            }
        }
        return maxProfit;
    }

    private static double getBound(int profit, int weight, int[] values, int[] weights, int maxWeight, int level) {
        double bound = profit;
        int remainingWeight = maxWeight - weight;
        while (level < values.length && remainingWeight > 0) {
            if (weights[level] <= remainingWeight) {
                bound += values[level];
                remainingWeight -= weights[level];
            } else {
                bound += (remainingWeight * ((double)values[level] / weights[level]));
                remainingWeight = 0;
            }
            level++;
        }
        return bound;
    }

    public static void main(String[] args) {
        System.out.println("I am Aryan Kumar 21BCT0275 and this is my code for 0-1 knapsack branch and bound problem.");
        System.out.println("The weights are based on my phone number 7003145004.");
        int[] weights = { 70, 03, 14, 50, 04 };
        int[] values = { 9, 4, 1, 2, 1 };
        int maxWeight = 15;
        int maxValue = getMaxValue(weights, values, maxWeight);
        System.out.println("Maximum value for the given weight limit: " + maxValue);
    }
}

