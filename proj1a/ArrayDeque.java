/**
 * Created by wenxuan on 2/20/17.
 */
public class ArrayDeque<Item>
{
    private int RFACTOR;
    private int size;
    private Item[] items;
    private int first = 4;
    private int last = 5;

    public ArrayDeque()  // Create an empty Deque
    {
        items = (Item []) new Object[8];
    }
    public void addFirst(Item i)
    {
        if (size == items.length)
            resize(2 * size);
        items[getIndex(first)] = i;
        first--;
        size++;
    }

    public void addLast(Item i)
    {
        if (size == items.length)
            resize(2 * size);
        items[getIndex(last)] = i;
        last++;
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
        for(int i = first + 1; i < last; i++)
        {
            System.out.print(items[getIndex(i)] + " ");
        }
    }

    public Item removeFirst()
    {
        Item result = items[getIndex(first + 1 )];
        items[getIndex(first + 1 )] = null;
        first++;
        size--;
        if (size <= 0.25 * items.length)
            resize(2 * size);
        return result;
    }

    public Item removeLast()
    {
        Item result = items[getIndex( last+ 1 )];
        items[getIndex(last - 1)] =null;
        last--;
        size--;
        if (size <= 0.25 * items.length)
            resize(2 * size);
        return result;
    }

    public Item get(int index)
    {
        return items[getIndex(first + index)];
    }

    private void resize(int length)
    {
        Item[] newItems = (Item []) new Object[length];
        System.arraycopy(items, 0, newItems, 0, size);
        items = newItems;
        first = -1;
        last = size;
    }

    private int getIndex(int index)
    {
        return (index + items.length) % items.length;
    }

    public static void main(String[] args)
    {
        ArrayDeque test = new ArrayDeque();
        test.resize(3);
        test.addLast(1);
        test.addLast(1);
        test.addLast(2);
        test.addLast(5);
        test.addFirst(6);
    }
}
