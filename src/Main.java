public class Main {
    public static void main(String[] args) {

SkipList obj = new SkipList();
obj.insert(11);

obj.insert (12);
obj.insert(33);

obj.insert (43);
obj.insert(390);
//
obj.printSkipList();
//
//System.out.println( obj.remove(33) ) ;
//System.out.println(obj.search(33) ) ;
//
//System.out.println(obj.remove(390));


System.out.println(obj.lookup(390));

    }
    }
