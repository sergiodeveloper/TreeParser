package treeparser.token;

import java.text.ParseException;

import parser.ParseableList;
import parser.Parser;
import sequencepattern.pattern.Pattern;
import treeparser.TreeParser;

public class NameParser implements Parser<Character, Token> {

	@Override
	public Pattern<Character> getPattern() {
		return TokenPatterns.NAME;
	}

	@Override
	public Token parse(final ParseableList<Character, Token> list, final int offset) throws ParseException {
		StringBuilder stringBuilder = new StringBuilder();
		for (char c : list) {
			stringBuilder.append(c);
		}
		String name = TreeParser.unescape(stringBuilder.toString().trim());
		return new Name(name, offset);
	}

}