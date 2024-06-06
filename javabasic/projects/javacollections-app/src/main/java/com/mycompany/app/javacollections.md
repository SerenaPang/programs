#Delete in BST
delete (root, 8) for example  

```
		  3									   3
		/	\							     	/	\
		2	 8							    2	      10
			/ \				->					 / \
		     6  10									 6  12
		     		\								
		     		12
	
```
Step 1: Find the node to be deleted
Step 2: Delete the node, with 4 possible situations:
	  
#case 1
The node to be deleted has no child
we just need to disconnect the node with its parent
   
```
		  3							  3			
		/   \				->			/   
	    2		8	     					2

```
#case 2
The node to be deleted has no left child, and has right child
which means the right child is the smallest, we replace it with the right child

```		 
		  3							  3			
		/   \							/   \
		2	8					    2    10
		      \			->				 \
		       10							  12
		         \
		     	    12
		     	    
```

#case 3
The node to be deleted has no right child, and has left child
which means the left child is the smallest, we can replace it with the left child

```		 
		  3							  3			
		/   \							/   \
		2	8					    2     6
		    / 		      ->			    /
		   6  						   5  
		  /
		 5        	    
		     	    
```


#case 4
The node to be deleted has both left child and right child, but the left child has right/left subtree, we need to move some nodes from right/left subtree to replace it
So we need to figure out which node to replace for example 8:
- either from the left subtree, the largest of the left subtree
- or from the right subtree, the smallest of the right subtree

#case 4.1
The node to be deleted has both left child and right child, but the RIGHT SUBTREE does not have LEFT child.
Which means the right child is the smallest child in the right

```		 
		  3							  3			
		/   \							/   \
	    2		8					    2		10						
		     / \			->			     / \	
		    6   10						   6    12
		   /      \					  /
		  5  	     12					 5
		     	    
```

#case 4.2
The node to be deleted has both left child and right child, but the RIGHT SUBTREE has LEFT child.
Which means the right child is not the smallest child, we need to find the smallest to move it up

```		 
		  3							  3			
		/   \							/   \
		2	8						2	9
		     / \			->			     / \	
		    6   12						    6   12
		        / \						   / \
		      11   14						 11   14	
		     / 							 / 
		    9 							10
		     \
		      10 	    
```
		     
		     
		     		
		     		