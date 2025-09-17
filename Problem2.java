// Time Complexity : O(v + e)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach

/**
 * Iterated through edge list and created an array which give us the number of indegrees for an index. Entries with 0 have no indegree so it is an independent course that can be taken. We add it to the queue to process.
 * We traverse through all dependent nodes from independent nodes and reduce their counter by one. If the counter becomes 0 for any node, we add it to the queue since it has become indepedent now.
 */
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        int[] courses = new int[numCourses];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            courses[prerequisite[0]]++;
            if (!map.containsKey(prerequisite[1])) {
                map.put(prerequisite[1], new ArrayList<>());
            }
            map.get(prerequisite[1]).add(prerequisite[0]);
        }

        // Adding courses which have no dependency to start the logic
        int size = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (courses[i] == 0) {
                queue.add(i);
                size++;
            }
        }

        // All courses are independent
        if (size == numCourses)
            return true;

        // no courses are independent
        if (queue.isEmpty())
            return false;

        while (!queue.isEmpty()) {
            int num = queue.poll();
            List<Integer> dependent = map.get(num);
            if (dependent != null) {
                for (int node : dependent) {
                    courses[node]--;
                    if (courses[node] == 0) {
                        queue.add(node);
                        size++;
                    }
                }
            }

        }

        // if all courses are taken, then return true
        return size == numCourses;
    }
}