package parser;

import java.util.Collection;
import java.util.List;

public class ListUtils {

	private ListUtils() {
	}

	public static <E> List<E> getLongestList(final Collection<? extends List<E>> lists) {
		List<E> longestList = null;
		int longestLength = -1;

		if (lists.isEmpty()) {
			throw new IllegalArgumentException("List cannot be empty");
		}

		for (List<E> list : lists) {
			if (list.size() > longestLength) {
				longestLength = list.size();
				longestList = list;
			}
		}

		return longestList;
	}

}
