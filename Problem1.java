// Time Complexity : O(n)
// Space Complexity : O(h)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach

/**
 * Using dfs to visit every node and add the element to the relevant list based on the depth by increasing the level by 1 every time child node is visited
 */
class Solution {
    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> levelOrder(TreeNode root) {
        dfs(root, 0);
        return result;
    }

    private void dfs(TreeNode node, int level) {
        if (node == null)
            return;

        // This will insert a new sub list for the first time a level is visited
        if (result.size() == level) {
            result.add(new ArrayList<>());
        }

        result.get(level).add(node.val);

        dfs(node.left, level +1);
        dfs(node.right, level +1);
    }

}