package org.hyacinth.examples.treeOp;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/** 
 * @author cdbaironglin 
 * @version 2017年2月8日 上午9:59:08 
 */

public class TreeUtils {
	static ThreadLocal<Map<String, Integer>> param = new ThreadLocal<Map<String, Integer>>();
	static int markCount = 0;
	static int[] maxVals = null;
	static int maxCount = 0;
	static int index = 0;
	
	public static TreeNode convertToTree(String s) {
		String[] nodes = s.split(",");
		int length = nodes.length;
		if (length == 0 || nodes[0].equalsIgnoreCase("null")) return null;
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		int i = 0;
		TreeNode root = new TreeNode(Integer.valueOf(nodes[i++]));
		queue.offer(root);
		while (i < length) {
			String val = nodes[i++];
			TreeNode node = queue.poll();
			if (!val.equalsIgnoreCase("null")) {
				node.left  = new TreeNode(Integer.valueOf(val));
				queue.offer(node.left);
			}
			if (i >= length) break;
			val = nodes[i++];
			if (!val.equalsIgnoreCase("null")) {
				node.right  = new TreeNode(Integer.valueOf(val));
				queue.offer(node.right);
			}
		}
		return root;
	}
	
	public static void main(String[] args) {
		TreeNode root = convertToTree("3,1,3,1,2,null,4,null,1,null,3,3");
//		System.out.println(root.val);
		
//		Map<String, Integer> paramMap = new HashMap<String, Integer>();
//		paramMap.put("markVal", 0);
//		param.set(paramMap);
//		inOrder(root);
//		maxVals = new int[index];
//		index = 0;
//		markCount = 0;
//		inOrder(root);
//		for(int i : maxVals) System.out.println(i);
		morrisOrder(root);
	}
	
	public static void inOrder(TreeNode root) {
		if (root == null) return;
		
		inOrder(root.left);
		System.out.println(root.val);
		handleValue(root.val);
		inOrder(root.right);
		
	}
	
	public static void handleValue(int val) {
		if (param.get().get("markVal") != val) {
			param.get().put("markVal", val);
			markCount = 0;
		}
		markCount++;
		if (markCount > maxCount) {
			maxCount = markCount;
			index = 1;
		} else if (markCount == maxCount) {
			if (maxVals != null) {
				maxVals[index] = val;
			}
			index++;
		}
	}
	
	public static void morrisOrder(TreeNode root) {
		TreeNode node = root;
		while (node != null) {
			if (node.left == null) {
				System.out.println(node.val);
				node = node.right;
			} else {
				TreeNode pre = node.left;
				while (pre.right != null && pre.right != node) pre = pre.right;
				if (pre.right == null) {
					pre.right = node;
					node = node.left;
				} else {
					pre.right = null;
					System.out.println(node.val);
					node = node.right;
				}
			}
		}
	}
}
