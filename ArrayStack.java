public class ArrayStack<E> {
   public static int CAPACITY=10;
   private  E[] data;
   private int top = -1;
   public ArrayStack1() {this(CAPACITY);} // default constructor
   public ArrayStack1(int capacity) {// constructor
      CAPACITY=capacity;
      data = (E[]) new Object[capacity];
   }
   public boolean isEmpty() {
	   return top==-1;
   }
   public boolean isFull() {
	   return data.length==size();
   }
   public int size() {
	   return top+1;
   }
   public void pop() { 
	   if(isEmpty()) {
		   System.out.println("The stack is empty");
	   }
	   else top--;
   }
   public E top() {
	   return data[top];
   }
   public String toString() {
	   String str = "";
	   for(int i = size()-1; i>=0; i--) {
		   str = str + data[i] + " ";
	   }
	   	return str;
   }
   public void push(E e) throws IllegalStateException {
	   if (isFull()) System.out.println("Stack is full");
	   else data[++top] = e;
   }
       
   //not part of the stack class but used here as an application method 
   public static boolean isMatched(String expression) {  
      final String opening = "({["; // opening delimiters
      final String closing = ")}]"; // respective closing delimiters
      ArrayStack1<Character> buffer = new ArrayStack1<Character>( );
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

   //implement this method to reverse a stack using only methods of the class
   // This will allow you to use it in both array and linked list. Use 2 extra 
   // stack objects to achieve this as discussed in class

   public  void reverse() {
   ArrayStack1<E> stack1 = new ArrayStack1<E>();
   ArrayStack1<E> stack2 = new ArrayStack1<E>();
   
   while(!isEmpty()) {
	   stack1.push(top());
	   pop();
   }
   
   while(!stack1.isEmpty()) {
	   stack2.push(stack2.top());
	   stack1.pop();
   }
   while(!stack2.isEmpty()) {
	   push(stack1.top());
	   stack1.pop();
   }
   }
   public boolean eliminate (E e) {
   }

	public static void main(String args[]) {
	// insert code here
	}
}
