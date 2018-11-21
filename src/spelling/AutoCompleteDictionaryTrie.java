package spelling;

import java.util.List;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word)
	{
		word = word.toLowerCase();
		TrieNode node = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (node.getValidNextCharacters().contains(c)) {
				node = node.getChild(c);
			} else {
				node = node.insert(c);
			}
		}
		if (!node.endsWord()) {
			node.setEndsWord(true);
			size++;
			return true;
		}
	    return false;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    return this.size;
	}
	
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) 
	{
		s= s.toLowerCase();
		TrieNode node = root;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (node.getValidNextCharacters().contains(c)) {
				node = node.getChild(c);
			} else {
				return false;
			}
		}
		if (node.endsWord()) {
			return true;
		}
		return false;
	}

	/** 
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {

       	 prefix = prefix.toLowerCase();
    	 List<String> result = new LinkedList<String>();
    	 TrieNode node = root;
    	 // find the stem
    	 for (int i = 0; i < prefix.length(); i++) {
    		 char c = prefix.charAt(i);
    		 if (node.getValidNextCharacters().contains(c)) {
 				node = node.getChild(c);
 			} else {
 				return result;
 			}
    	 }
    	 
    	 int count = 0;
    	 if (node.endsWord()) {
    		 result.add(node.getText());
    		 count++;
    	 }
    	 
    	 // create queue
    	 List<TrieNode> queue = new LinkedList<TrieNode>();
    	 List<Character> childrenC = new LinkedList<Character>(node.getValidNextCharacters());
    	 
    	 for (int i = 0; i < childrenC.size(); i++) {
    		 char c = childrenC.get(i);
    		 queue.add(node.getChild(c));
    	 }
    	 while (!queue.isEmpty() && count < numCompletions) {
    		 TrieNode trie = queue.remove(0);  // remove first node
    		 if (trie.endsWord()) {  
    			 result.add(trie.getText());  // add to the completions list
    			 count++;
    		 }
    		 
    		 List<Character> cs = new LinkedList<Character>(trie.getValidNextCharacters());
        	 for (int i = 0; i < cs.size(); i++) {
        		 char c = cs.get(i);
        		 queue.add(trie.getChild(c));   // add child to the bacl of the queue
        	 }
    	 }
         return result;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}