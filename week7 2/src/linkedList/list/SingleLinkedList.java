package linkedList.list;

/***
 * Implements the List<T></T> interface using single linked lists.
 *
 * @author Ben Scott
 * @version December 2019
 */

import java.util.Iterator;

import linkedList.node.NodeIterator;
import linkedList.node.SingleLinkNode;

public class SingleLinkedList<T> extends BasicList<SingleLinkNode<T>, T> implements List<T>, Iterable<T>
{
  private int size = 0;

  @Override
  public void add(int index, T value) throws ListAccessError
  {
    SingleLinkNode<T> newNode = new SingleLinkNode<T>(value);
    if (index == 0)
    {
      newNode.setNext(getRoot());
      setRoot(newNode);
    }
    else
    {
      SingleLinkNode<T> previous = getNode(index-1);
      newNode.setNext(previous.getNext());
      previous.setNext(newNode);
    }


    size++;
  }
  //Utility overload
  public void add(T value) throws ListAccessError
  {
    add( size, value);
  }

  @Override
  public T remove(int index) throws ListAccessError
  {
    SingleLinkNode<T> node = null;
    switch (size)
    {
      case 0: throw new ListAccessError("The array is empty.");
      case 1:
        node = getRoot();
        setRoot(null);
        break;
      default:
        SingleLinkNode<T> previous = getNode(index-1);
        node = previous.getNext();
        previous.setNext(node.getNext());
        break;
    }
    size--;

    return node.getValue();
  }

  public int getSize() {
        return size;
    }

  @Override
  public T get(int index) throws ListAccessError
  {
    return getNode(index).getValue();
  }

  private SingleLinkNode<T> getNode(int index) throws ListAccessError
  {
    if (isEmpty())
      throw new ListAccessError("List is empty.");

    if (index < 0 || index >= size)
      throw new ListAccessError("Index is not present in the list.");

    SingleLinkNode<T> inUse = getRoot();
    while (index > 0)
    {
      inUse = inUse.getNext();
      index--;
    }

    return inUse;
  }

  @Override
  public Iterator<T> iterator()
  {
    return new NodeIterator<T>(getRoot());
  }
}
