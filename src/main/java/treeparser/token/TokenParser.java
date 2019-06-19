package treeparser.token;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import parser.ParseableList;

public class TokenParser {

	private final List<Character> characters;

	public TokenParser(final List<Character> characters) {
		this.characters = characters;
	}

	public List<Token> getTokens() throws ParseException {
		List<Token> tokens = new ArrayList<>();

		ParseableList<Character, Token> list = new ParseableList<>(characters);

		while (list.parse(new ValueParser(), tokens) || list.parse(new NameParser(), tokens)
				|| list.parse(new OpenParenthesisParser(), tokens) || list.parse(new CloseParenthesisParser(), tokens)
				|| list.parse(new CommaParser(), tokens)) {
			while (list.consumeIfExistent(new Character(' ')) || list.consumeIfExistent(new Character('\t'))
					|| list.consumeIfExistent(new Character('\n')))
				;
		}

		if (list.isNotEmpty()) {
			throw new ParseException("Unexpected character \"" + list.getFirst() + "\"",
					characters.size() - list.size());
		}

		return tokens;
	}

}
