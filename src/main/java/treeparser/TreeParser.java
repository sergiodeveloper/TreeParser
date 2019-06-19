package treeparser;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import parser.ParseableList;
import treeparser.parser.ElementParser;
import treeparser.token.Token;
import treeparser.token.TokenParser;

public class TreeParser {

	private final String string;

	public TreeParser(final String string) {
		this.string = string;
	}

	public Element parse() throws ParseException {
		List<Character> characters = new ArrayList<>();
		for (char c : string.toCharArray()) {
			characters.add(c);
		}

		List<Token> tokens = new TokenParser(characters).getTokens();

		ParseableList<Token, Object> parseableList = new ParseableList<>(tokens);

		Component element;
		try {
			element = parseableList.parse(new ElementParser());
			if (!(element instanceof Element)) {
				throw new IllegalStateException();
			}
		} catch (ParseException e) {
			int[] lineCol = TreeParser.calculateLineCol(string, e.getErrorOffset());
			throw new ParseException(e.getMessage() + ". Line " + lineCol[0] + ", column " + lineCol[1],
					e.getErrorOffset());
		}

		if (!parseableList.isEmpty()) {
			int[] lineCol = TreeParser.calculateLineCol(string, parseableList.getFirst().getOffset());
			throw new ParseException("Unexpected token \"" + parseableList.getFirst() + "\". Line " + lineCol[0]
					+ ", column " + lineCol[1], parseableList.getFirst().getOffset());
		}

		return (Element) element;
	}

	public static int[] calculateLineCol(final String string, final int offset) {
		boolean doubleSeparator = string.contains("\\r\\n") || string.contains("\\n\\r");

		String[] split = string.split("((\\r\\n)|(\\n\\r)|(\\n)|(\\r))");

		int currentOffset = 0;
		for (int line = 0; line < split.length; line++, currentOffset += doubleSeparator ? 2 : 1) {
			currentOffset += split[line].length();
			if (currentOffset >= offset) {
				return new int[] { line + 1, split[line].length() - (currentOffset - offset) };
			}
		}

		return new int[] { split.length + 1, 0 };
	}

	public static String escape(final String string) {
		return string.replaceAll("\\\\", "\\\\").replaceAll(",", "\\,").replaceAll(":", "\\:").replaceAll("\\)", "\\)")
				.replaceAll("\\(", "\\(");
	}

	public static String unescape(final String string) {
		return string.replaceAll("\\\\\\(", "(").replaceAll("\\\\\\)", ")").replaceAll("\\\\:", ":")
				.replaceAll("\\\\,", ",").replaceAll("\\\\\\\\", "\\");
	}

}
