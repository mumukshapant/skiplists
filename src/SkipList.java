import java.util.Random;

public class SkipList {

    private Node head= new Node(Integer.MIN_VALUE, 0 , null , null );
    Random ran = new Random();

    public class Node
    {

        public Node next;
        public Node down;
        Integer level ;
        Integer key;

        public Node(Integer key, Integer level, Node next, Node down) {
            this.key = key;
             this.level = level;
            this.down = down;
            this.next = next;

        }
    }

    public int search (int key )
    {

        Node curr = this.head;

        while (true) {
            while (curr != null) {

                if (curr.key != null && curr.key == key) {
                    return curr.key; // return the value
                }

                Node next = curr.next; // create NEXT as pointer to the node current

                if (next != null && next.key <= key) // to ensure next is not null and next KEY is smaller than key to search , because sorted order
                {       curr = curr.next;    }

                else curr = curr.down; // if next is null means we have no option in the horizontal line, we will have to move downwards
            }
            return -1;
        }

    }

    public boolean lookup (int key )
    {
        Node curr = head;
        while(true) {
            while(curr != null) {
                System.out.print(curr.key);
                if (curr.key != null && curr.key == key) {
                    System.out.println();
                    return true;
                }

                Node next = curr.next;
                if (next != null && next.key <= key) {
                    curr = curr.next;
                    System.out.print("-> right ->");
                } else
                {
                    curr = curr.down;
                    System.out.print("-> down ->");
                }
            }

            return false;
        }


    }


    private int randomgen() // found online . probablity of coin is 1/2 . Max levels can be 10
    {
        int level ;

        for ( level = 0 ; this.ran.nextDouble() < 0.5 && level <=10 ; ++level) {

        }
return level ;
    }

    public boolean insert(int n)
    {
     if (search(n)!=-1)
     {
         return false; // key already exists, we cant do insertion
     }
     else
     {
         int insertionLevel = randomgen();  // give a value between 0 to 10

         if (insertionLevel > head.level) // move head to that insertion level
         {
             head= new Node( null , insertionLevel, null , head   ); // key, level , next, down

         }

         Node curr = head;
         Node goingdown = null ;

         while (true)
         {
             while (curr!= null )
             {
                 Node next = curr.next;
                 if ( next!= null && next.key <= n )  // accross -- if next == null means we have reached +infinity and we need to go down
                     curr = curr.next;


                 else
                 {
                     if (curr.level <= insertionLevel)  // 1<4
                     {
                         Node newnode = new Node (n, curr.level, next , null) ;

                         curr.next = newnode; // now since we are not at level , we are at insertion level ,
                         // at every level below it we have to enter the same node.
                         // so we go down and enter newnode

                         if (goingdown!=null )
                             goingdown.down = newnode;

                         goingdown= newnode;

                     }

                     curr= curr.down ;

                 }

             }

             return true ;
         }
     }
    }

    public boolean remove(int n )
    {
        Node curr = head;
        Node prev = null ;
        boolean element_to_be_removed = false; //element to be removed , if found then true else false

            while (curr != null)
        {
            if (curr.key!=null && curr.key == n) //item to be removed
            {
                element_to_be_removed = true;
                break;
            }

            if (curr.next !=null && curr.next.key <= n ) // accross- we have to find the largest element smaller to n . EG : 17. 18 19 --30-- 42 .
                // // element to remove is 30, we stop at 19 // when curr.next is null we mmove down
            {
                prev = curr ; //prev = curr , curr moves fwd
                curr= curr.next;
            }

            else    //  curr.key is null AND curr.key is greater than n ,,, means it cannot go across so it has to go down //move down
            {
                prev= curr ;
                curr = curr.down ;

            }

        }

            if (element_to_be_removed == false ) // still not found the element
            {
                return false ;
            }

         // ELSE means element to be removed is TRUE . TRUE means we have found the element and then remove its references .
            else {

                while(true) {

                    while(curr != null) // to remove the references ,we again go to the position where we found the element.
                    {
                        if (prev.next != null && prev.next.key == curr.key)
                        {
                            prev.next = curr.next;
                        }
                        else if (prev.next != null &&    prev.next.key != curr.key)
                        {
                            prev = prev.next;
                            continue;
                        }

                        curr = curr.down;
                        prev = prev.down;
                    }

                    return true;
                }
            }

    }







    //collaboreated with a friend + online
    public void printSkipList() {
        System.out.println("Printing skipList Structure: ");

        Node curdown;
        for(curdown = this.head; curdown.down != null; curdown = curdown.down) {
        }

        curdown = curdown.next;
        Node top = this.head;

        while(true) {
            while(curdown != null) {
                if (top.key == curdown.key) {
                    for(int i = 0; i <= top.level; ++i) {
                        System.out.print(curdown.key + " ");
                    }

                    System.out.println();
                    curdown = curdown.next;
                    top = this.head;
                } else if (top.next != null && top.next.key <= curdown.key) {
                    top = top.next;
                } else {
                    top = top.down;
                }
            }

            return;
        }
    }






}








