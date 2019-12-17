package linkedList.node;

import java.util.Iterator;

public final class NodeIterator<T> implements Iterator<T>
{
    private ListNode<T> node;

    public NodeIterator(ListNode<T> root)
    {
        node = root;
    }

    @Override
    public boolean hasNext()
    {
        return node.getNext() != null;
    }

    @Override
    public T next()
    {
        T val = node.getValue();
        node = node.getNext();

        return val;
    }

}