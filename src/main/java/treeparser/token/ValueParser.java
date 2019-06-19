package treeparser.token;

import java.text.ParseException;

import parser.ParseableList;
import parser.Parser;
import sequencepattern.pattern.Pattern;
import treeparser.TreeParser;

public class ValueParser implements Parser<Character, Token> {

	@Override
	public Pattern<Character> getPattern() {
		return TokenPatterns.VALUE;
	}

	@Override
	public Token parse(final ParseableList<Character, Token> list, final int offset) throws ParseException {
		list.pop(':');
		StringBuilder stringBuilder = new StringBuilder();
		for (char c : list) {
			stringBuilder.append(c);
		}
		String value = TreeParser.unescape(stringBuilder.toString().trim());
		return new Value(value, offset);
	}

}