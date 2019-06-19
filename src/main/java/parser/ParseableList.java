package parser;

import java.text.ParseException;
import java.util.List;

import sequencepattern.pattern.ElementSequence;

/**
 * @param <F> From type
 * @param <T> To type
 */
public class ParseableList<F, T> extends ConsumableList<F> {

	public ParseableList(final List<F> elements) {
		super(elements);
	}

	public <U extends T> boolean parse(final Parser<F, U> parser, final List<U> parsedElements) throws ParseException {
		U parsedElement = parse(parser);
		if (parsedElement != null) {
			parsedElements.add(parsedElement);
		}
		return parsedElement != null;
	}

	public <U extends T> U parse(final Parser<F, U> parser) throws ParseException {
		List<ElementSequence<F>> result = parser.getPattern().execute(this);
		boolean hasResult = !result.isEmpty();
		U parsedElement = null;
		if (hasResult) {
			List<F> longestList = ListUtils.getLongestList(result);
			consumeStart(longestList.size());
			parsedElement = parser.parse(new ParseableList<F, U>(longestList), getConsumedFromStart() - 1);
		}
		return parsedElement;
	}

	public <U extends F> U pop(final Class<U> type) {
		U cast = type.cast(this.getFirst());
		consume(type);
		return cast;
	}

	public F pop() {
		F top = this.getFirst();
		consume(1);
		return top;
	}

	public <U extends F> void pop(final U element) {
		consume(element);
	}

}
