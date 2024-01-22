package tree_structures;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import tree_structures.BinaryTree.Node;

/**
 * a tree for decoding Huffman codes
 * */
public class HuffmanTree {
	private Node root;
	/**
	 * constructs a Huffman tree from given character frequencies
	 * @param frequencies a map whose keys are the characters to be encoded 
	 * and whose values are the frequencies of the 
	 * characters
	 * */
	public HuffmanTree(Map<Character, Integer> frequencies) {
		PriorityQueue<Node> nodes = new PriorityQueue<>();
		for (char ch : frequencies.keySet()) {
			Node newNode = new Node();
			newNode.character = ch;
			newNode.frequency = frequencies.get(ch);
			nodes.add(newNode);
		}
		while (nodes.size() > 1) {
			Node smallest = nodes.remove();
			Node nextSmallest = nodes.remove();
			Node newNode = new Node();
			newNode.frequency = smallest.frequency + nextSmallest.frequency;
			newNode.left = smallest;
			newNode.right = nextSmallest;
			nodes.add(newNode);
		}
		root = nodes.remove();
	}
	
	class Node implements Comparable<Node> {
		public char character;
		public int frequency;
		public Node left;
		public Node right;
		
		@Override
		public int compareTo(Node other) {
			return frequency - other.frequency;
		}
		
		public void fillEncodingMap(Map<Character, String> map, String prefix) {
			if (left == null) { //it's a leaf
				map.put(character, prefix);
			} else {
				left.fillEncodingMap(map, prefix + "0");
				right.fillEncodingMap(map, prefix + "1");
			}
		}
	}
	
}
