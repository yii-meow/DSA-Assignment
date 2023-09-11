package adt;

/**
 * An interface for the ADT stack.
 *
 * @author Chong Yik Soon
 */
public interface StackInterface<T> {

    public void push(T newEntry);

    public T pop();

    public T peek();

    public boolean isEmpty();

    public void clear();
}
