/**
 * Created by wenxuan on 2/20/17.
 */
public class LinkedListDeque<Item> {
    public class Node {
        public Node prev;
        public Item item;
        public Node next;

        public Node(Item i, Node p, Node n)
        {
            prev = p;
            item = i;
            next = n;
        }
    }

    private Node sentinel;
    private int size;


    public LinkedListDeque(Item i)
    {
        sentinel = new Node(i, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }
    public void addFirst(Item i)
    {
        Node oldNext = sentinel.next;
        sentinel.next = new Node(i, sentinel, sentinel.next);
        oldNext.prev = sentinel.next;
        size++;
    }
    public void addLast(Item i)
    {
        Node oldLast = sentinel.prev;
        sentinel.prev = new Node(i, sentinel.prev, sentinel);
        oldLast.next = sentinel.prev;
        size++;
    }
    public boolean isEmpty()
    {
        return size == 0;
    }

    public int size()
    {
        return size;
    }

    public void printDeque()
    {
        Node p = sentinel.next;
        for(int i = 0; i < size; i++)
        {
            System.out.print(p.item + " ");
            p = p.next;
        }
    }

    public Item removeFirst()
    {
        Item item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        return item;
    }

    public Item removeLast()
    {
        Item item = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return item;
    }

    public Item get(int index)
    {
        Node first = sentinel;
        for(int i = 0; i < index; i++)
        {
            first = first.next;
        }
        Item item = first.item;
        return item;
    }

    public Item getRecursive(int index)
    {
        sentinel = sentinel.next;
        if (index == 0)
        {

            return sentinel.prev.item;
        }
        return this.getRecursive(index - 1);
    }
    public static void main(String[] args)
    {
        LinkedListDeque<Integer> test = new LinkedListDeque<>(5);
        test.addLast(6);
        test.addLast(7);
        int i = test.get(2);
        i = test.getRecursive(2);
    }
}
