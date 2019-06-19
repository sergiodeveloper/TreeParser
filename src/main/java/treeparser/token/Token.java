package treeparser.token;

public abstract class Token {

	private final String value;
	private final int offset;

	public Token(final String value, final int offset) {
		this.value = value;
		this.offset = offset;
	}

	public String getValue() {
		return value;
	}

	public int getOffset() {
		return offset;
	}

	@Override
	public String toString() {
		return value;
	}

}
