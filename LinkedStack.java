public class LinkedStack<E> {
   private static class Node<E> { 
      private E element;
      private Node<E> next;
      public Node(E e, Node<E> n) {
         element=e;
         next=n;
      }
      public E getElement() { return element;}
      public Node<E> getNext() { return next;}
      public void setNext(Node<E> n) { next=n;}
   } //end of inner class Node
   
   
   private Node<E> top= null;
   private int size=0;
   public int size() { return size;}
   public boolean isEmpty(){return size==0;} //or return top==null
   public E top(){
      if(isEmpty()) return null;
      return top.getElement();
   }
   
   public LinkedStack(){}
   public void push(E e) {
      top=new Node<>(e,top); 
      size++;
   }
   
   
   public void pop() { 
      if(isEmpty()) 
         System.out.println("Stack is Empty");
      else {
      top=top.getNext();
      size--;
      }
   }
   
   public String toString() { 
      Node<E> loc=top;
      String s="";
      while(loc!=null) {
         s+=loc.element+ " ";
         loc=loc.next;
      }
      return s;
   }

   
      //not part of the stack class will be used in lab
   public static boolean isMatched(String expression) {  
      final String opening = "({["; // opening delimiters
      final String closing = ")}]"; // respective closing delimiters
      LinkedStack<Character> buffer = new LinkedStack<Character>( );
      System.out.print("Checking : ");
      for (char c : expression.toCharArray( )) {  
         System.out.printf("%s ", c);
         if (opening.indexOf(c) != -1) // this is a left delimiter
            buffer.push(c);
         else if (closing.indexOf(c) != -1) { // this is a right delimiter
            if (buffer.isEmpty( )) { // nothing to match with
               System.out.println();
               return false;
            }
            if (closing.indexOf(c) != opening.indexOf(buffer.top( ))){
               System.out.println();    
               return false; // mismatched delimiter
            }
            buffer.pop();
         }
      }
      return buffer.isEmpty( ); // were all opening delimiters matched?
   }
   
   public  void reverse() {
	   ArrayStack1<E> stack1 = new ArrayStack1<E>();
	      ArrayStack1<E> stack2 = new ArrayStack1<E>();
	      
	      while(!isEmpty()) {
	    	  stack1.push(top());
	    	  pop();
	      }
	      
	      while(!stack1.isEmpty()) {
	    	  stack2.push(stack1.top());
	    	  stack1.pop();
	      }
	      
	      while(!stack2.isEmpty()) {
	    	  push(stack2.top());
	    	  stack2.pop();
	      }
   }
     
   public boolean eliminate (E e) {
	   ArrayStack1<E> stack1 = new ArrayStack1<E>();
	      boolean b = false;
	      while(!isEmpty()) {
	    	  E item = top();
	    	  pop();
	    	  if(item == e) {
	    		  b = true;
	    	  }
	    	  else {
	    		  stack1.push(item);
	    	  }
	      }
	      while(!stack1.isEmpty()) {
	    	  push(stack1.top());
	    	  stack1.pop();
	      }
	      return b;
   }

   public static void main(String args[]) {
	   LinkedStack<Integer> stack = new LinkedStack<Integer>();
		 stack.pop();
		 int[] array = {6, 3, 7, 9, 80, 22, 12, 13, 1, 2, 4};
		 for(int i:array) {
			 stack.push(i);
			 System.out.println("Pushing "+i+": Stack has "+stack.size()+" items: "+stack);
		 }
		 int item = stack.top();
		 stack.pop();
		 System.out.println("topped and popped item "+item+": Stack has "+stack.size()+" items: "+stack);
		 if(stack.eliminate(12)) System.out.println("12 is found in stack");
		 else System.out.println("12 is not found in stack");
		 if(stack.eliminate(14)) System.out.println("14 is found in stack");
		 else System.out.println("14 is not found in stack");
		 String str = "{(()[])}";
		 if(stack.isMatched(str)) {
			 System.out.println();
			 System.out.println(str+" has Matching brackets");
		 }
		 else {
			 System.out.println(str+" has no Matching brackets");
		 }
		 String str1 = "{(()}";
		 if(stack.isMatched(str1)) {
			 System.out.println();
			 System.out.println(str1+" has Matching brackets");
		 }
		 else {
			 System.out.println(str1+" has no Matching brackets");
		 }
		 stack.reverse();
		 System.out.println("Reversed Stack has "+stack.size()+" items and is: "+stack);
	 }
	}
