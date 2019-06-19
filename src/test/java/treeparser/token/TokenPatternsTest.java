package treeparser.token;

import org.junit.Assert;
import org.junit.Test;

import sequencepattern.pattern.ElementSequence;
import treeparser.token.TokenPatterns;

public class TokenPatternsTest {

	public static final String VALID_NAME = "abcde\\(\\)e\\:";
	public static final String INVALID_VALUE = "abcde\\\\(\\)e";
	public static final String OPEN_PARENTHESIS = "(";

	@Test
	public void validNameTest() {
		ElementSequence<Character> list = new ElementSequence<>();
		for (char c : TokenPatternsTest.VALID_NAME.toCharArray()) {
			list.add(c);
		}
		Assert.assertTrue(list.matchesExactly(TokenPatterns.NAME));
	}

	@Test
	public void invalidValueTest() {
		ElementSequence<Character> list = new ElementSequence<>();
		for (char c : TokenPatternsTest.INVALID_VALUE.toCharArray()) {
			list.add(c);
		}
		Assert.assertFalse(list.matchesExactly(TokenPatterns.VALUE));
	}

	@Test
	public void openParenthesisTest() {
		ElementSequence<Character> list = new ElementSequence<>();
		for (char c : TokenPatternsTest.OPEN_PARENTHESIS.toCharArray()) {
			list.add(c);
		}
		Assert.assertTrue(list.matchesExactly(TokenPatterns.OPEN_PARENTHESIS));
	}

}
