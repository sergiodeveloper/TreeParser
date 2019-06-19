package parser;

import java.text.ParseException;

import sequencepattern.pattern.Pattern;

public interface Parser<FromType, ToType> {

	Pattern<FromType> getPattern();

	ToType parse(ParseableList<FromType, ToType> list, int offset) throws ParseException;

}
