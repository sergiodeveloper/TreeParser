package parser;

import java.text.ParseException;

import sequencepattern.pattern.Pattern;

/**
 * @param <F> From type
 * @param <T> To type
 */
public interface Parser<F, T> {

	Pattern<F> getPattern();

	T parse(ParseableList<F, T> list, int offset) throws ParseException;

}
