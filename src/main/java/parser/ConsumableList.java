package parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ConsumableList<E> implements List<E> {

	private int consumedFromStart = 0;

	private List<E> list = new ArrayList<>();

	public ConsumableList() {
	}

	public ConsumableList(final List<E> list) {
		for (int i = 0; i < list.size(); i++) {
			this.list.add(list.get(i));
		}
	}

	@SafeVarargs
	public ConsumableList(final E... list) {
		for (E e : list) {
			this.list.add(e);
		}
	}

	@Override
	public E get(final int index) {
		return list.get(index);
	}

	public void add(final ConsumableList<E> sequence) {
		list.addAll(sequence.list);
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return size() <= 0;
	}

	public boolean isNotEmpty() {
		return size() > 0;
	}

	public boolean startsWith(final E element) {
		return isNotEmpty() && (get(0) == element || get(0).equals(element));
	}

	public boolean endsWith(final E element) {
		return isNotEmpty() && (getLast() == element || getLast().equals(element));
	}

	public boolean startsWith(final Class<? extends E> type) {
		return isNotEmpty() && type.isInstance(getFirst());
	}

	public boolean endsWith(final Class<? extends E> type) {
		return isNotEmpty() && type.isInstance(getLast());
	}

	public E getFirst() {
		return get(0);
	}

	public E getLast() {
		return get(size() - 1);
	}

	public void consume(final int numberOfItems) {
		consumeStart(numberOfItems);
	}

	public void consume(final E element) {
		consumeStart(element);
	}

	public boolean consumeIfExistent(final E element) {
		boolean contains = false;
		if (startsWith(element)) {
			consumeStart(element);
			contains = true;
		}
		return contains;
	}

	public boolean consumeIfExistent(final Class<? extends E> type) {
		boolean contains = false;
		if (startsWith(type)) {
			consumeStart(type);
			contains = true;
		}
		return contains;
	}

	public void consume(final Class<? extends E> type) {
		consumeStart(type);
	}

	public void consumeStart(final int numberOfItems) {
		list = subListFrom(numberOfItems);
		consumedFromStart += numberOfItems;
	}

	public void consumeStart(final E element) {
		if (!isEmpty() && get(0).equals(element)) {
			consumeStart(1);
		} else {
			throw new IllegalStateException();
		}
	}

	public void consumeStart(final Class<? extends E> type) {
		if (!isEmpty() && type.isInstance(get(0))) {
			consumeStart(1);
		} else {
			throw new IllegalStateException();
		}
	}

	public void consumeEnd(final int numberOfItems) {
		list = subListTo(list.size() - numberOfItems);
	}

	public void consumeEnd(final E element) {
		if (!isEmpty() && get(size() - 1).equals(element)) {
			consumeEnd(1);
		} else {
			throw new IllegalStateException();
		}
	}

	public void consumeEnd(final Class<? extends E> type) {
		if (!isEmpty() && type.isInstance(get(size() - 1))) {
			consumeEnd(1);
		} else {
			throw new IllegalStateException();
		}
	}

	public ConsumableList<E> subListFrom(final int start) {
		if (start == 0) {
			return new ConsumableList<>(list);
		}
		if (start >= list.size()) {
			return new ConsumableList<>();
		}
		return new ConsumableList<>(list.subList(start, list.size()));
	}

	public ConsumableList<E> subListTo(final int end) {
		if (end == list.size()) {
			return new ConsumableList<>(list);
		}
		if (end <= 0) {
			return new ConsumableList<>();
		}
		return new ConsumableList<>(list.subList(0, end));
	}

	@Override
	public String toString() {
		return list.toString();
	}

	@Override
	public boolean add(final E e) {
		return list.add(e);
	}

	@Override
	public void add(final int index, final E element) {
		list.add(index, element);
	}

	@Override
	public boolean addAll(final Collection<? extends E> c) {
		return list.addAll(c);
	}

	@Override
	public boolean addAll(final int index, final Collection<? extends E> c) {
		return list.addAll(index, c);
	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public boolean contains(final Object o) {
		return list.contains(o);
	}

	@Override
	public boolean containsAll(final Collection<?> c) {
		return list.containsAll(c);
	}

	@Override
	public int indexOf(final Object o) {
		return list.indexOf(o);
	}

	@Override
	public Iterator<E> iterator() {
		return list.iterator();
	}

	@Override
	public int lastIndexOf(final Object o) {
		return list.lastIndexOf(o);
	}

	@Override
	public ListIterator<E> listIterator() {
		return list.listIterator();
	}

	@Override
	public ListIterator<E> listIterator(final int index) {
		return list.listIterator(index);
	}

	@Override
	public boolean remove(final Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public E remove(final int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(final Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(final Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public E set(final int index, final E element) {
		return list.set(index, element);
	}

	@Override
	public List<E> subList(final int fromIndex, final int toIndex) {
		return list.subList(fromIndex, toIndex);
	}

	@Override
	public Object[] toArray() {
		return list.toArray();
	}

	@Override
	public <T> T[] toArray(final T[] a) {
		return list.toArray(a);
	}

	public int getConsumedFromStart() {
		return consumedFromStart;
	}

}
