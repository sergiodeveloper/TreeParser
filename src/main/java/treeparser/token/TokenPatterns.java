package treeparser.token;

import sequencepattern.condition.Condition;
import sequencepattern.condition.ConditionBuilder;
import sequencepattern.condition.NumberOf;
import sequencepattern.condition.NumberOfElements;
import sequencepattern.condition.SumOf;
import sequencepattern.pattern.EqualToAnyPattern;
import sequencepattern.pattern.Pattern;
import sequencepattern.pattern.PatternBuilder;

public class TokenPatterns {

	private TokenPatterns() {
	}

	private static final Condition<Character> hasNonSpace = new ConditionBuilder<Character>()
			.isSmallerThan(new SumOf<>(new NumberOf<>(' '), new NumberOf<>('\t'), new NumberOf<>('\n')),
					new NumberOfElements<>())
			.build();

	private static final Pattern<Character> ESCAPED_BACK_SLASH = new PatternBuilder<Character>().a('\\').a('\\')
			.build();
	private static final Pattern<Character> ESCAPED_OPEN_PARENTHESIS = new PatternBuilder<Character>().a('\\').a('(')
			.build();
	private static final Pattern<Character> ESCAPED_CLOSE_PARENTHESIS = new PatternBuilder<Character>().a('\\').a(')')
			.build();
	private static final Pattern<Character> ESCAPED_COLLON = new PatternBuilder<Character>().a('\\').a(':').build();
	private static final Pattern<Character> ESCAPED_COMMA = new PatternBuilder<Character>().a('\\').a(',').build();

	public static final Pattern<Character> NAME = new PatternBuilder<Character>().startBlock()
			.a(TokenPatterns.ESCAPED_BACK_SLASH).a(TokenPatterns.ESCAPED_OPEN_PARENTHESIS)
			.a(TokenPatterns.ESCAPED_CLOSE_PARENTHESIS).a(TokenPatterns.ESCAPED_COLLON).a(TokenPatterns.ESCAPED_COMMA)
			.not('(', ')', ',', ':').endOrBlock().repeatAtLeast(1).ifTrue(hasNonSpace).build();
	public static final Pattern<Character> VALUE = new PatternBuilder<Character>().a(':').startBlock().startBlock()
			.a(TokenPatterns.ESCAPED_BACK_SLASH).a(TokenPatterns.ESCAPED_OPEN_PARENTHESIS)
			.a(TokenPatterns.ESCAPED_CLOSE_PARENTHESIS).a(TokenPatterns.ESCAPED_COLLON).a(TokenPatterns.ESCAPED_COMMA)
			.not('(', ')', ',', ':').endOrBlock().repeatIndefinitely().build();
	public static final Pattern<Character> OPEN_PARENTHESIS = new EqualToAnyPattern<>('(');
	public static final Pattern<Character> CLOSE_PARENTHESIS = new EqualToAnyPattern<>(')');
	public static final Pattern<Character> COMMA = new EqualToAnyPattern<>(',');

	public static final Pattern<Character> TOKEN = new PatternBuilder<Character>().startBlock().a(TokenPatterns.VALUE)
			.a(TokenPatterns.OPEN_PARENTHESIS).a(TokenPatterns.CLOSE_PARENTHESIS).a(TokenPatterns.NAME)
			.a(TokenPatterns.COMMA).endOrBlock().build();

}
