package treeparser.token;

import java.text.ParseException;

import parser.ParseableList;
import parser.Parser;
import sequencepattern.pattern.Pattern;

public class OpenParenthesisParser implements Parser<Character, Token> {

	@Override
	public Pattern<Character> getPattern() {
		return TokenPatterns.OPEN_PARENTHESIS;
	}

	@Override
	public Token parse(final ParseableList<Character, Token> list, final int offset) throws ParseException {
		return new OpenParenthesis(offset);
	}

}