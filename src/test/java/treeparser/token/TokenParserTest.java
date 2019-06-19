package treeparser.token;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import treeparser.token.Token;
import treeparser.token.TokenParser;

public class TokenParserTest {

	private static final String STRING = "abc ( ) , : def\\( g, hij()";

	@Test
	public void test() throws ParseException {
		List<Character> list = new ArrayList<>();
		for (char c : TokenParserTest.STRING.toCharArray()) {
			list.add(c);
		}

		TokenParser tokenizer = new TokenParser(list);
		List<Token> tokens = tokenizer.getTokens();

		System.out.println(tokens);
	}

}
