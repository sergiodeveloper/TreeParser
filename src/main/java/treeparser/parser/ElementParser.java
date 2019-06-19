package treeparser.parser;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import parser.ParseableList;
import parser.Parser;
import sequencepattern.condition.Condition;
import sequencepattern.condition.ConditionBuilder;
import sequencepattern.condition.NumberOfInstancesOf;
import sequencepattern.pattern.Pattern;
import sequencepattern.pattern.PatternBuilder;
import treeparser.Attribute;
import treeparser.Component;
import treeparser.Element;
import treeparser.token.CloseParenthesis;
import treeparser.token.Comma;
import treeparser.token.Name;
import treeparser.token.OpenParenthesis;
import treeparser.token.Token;

public class ElementParser implements Parser<Token, Component> {

	private final Condition<Token> condition = new ConditionBuilder<Token>().startsWith(OpenParenthesis.class)
			.isEqual(new NumberOfInstancesOf<Token>(OpenParenthesis.class),
					new NumberOfInstancesOf<Token>(CloseParenthesis.class))
			.build();

	@Override
	public Pattern<Token> getPattern() {
		return new PatternBuilder<Token>().a(Name.class).startBlock().startBlock().any().repeatWhileFalse(condition)
				.optional().build();
	}

	@Override
	public Component parse(final ParseableList<Token, Component> list, final int offset) throws ParseException {
		Name name = list.pop(Name.class);

		List<Component> attributes = new ArrayList<>();
		List<Component> children = new ArrayList<>();

		if (list.isNotEmpty()) {
			list.pop(OpenParenthesis.class);
			while (list.parse(new AttributeParser(), attributes) || list.parse(new ElementParser(), children)) {
				list.consumeIfExistent(Comma.class);
			}

			if (!list.consumeIfExistent(CloseParenthesis.class)) {
				throw new ParseException("Unexpected token in element \"" + list.getFirst() + "\"",
						list.getFirst().getOffset());
			}
		}

		Element element = new Element(name.getValue());

		for (Component c : attributes) {
			if (!(c instanceof Attribute)) {
				throw new IllegalStateException();
			}
			element.setAttribute(((Attribute) c).getName(), ((Attribute) c).getValue());
		}

		for (Component c : children) {
			if (!(c instanceof Element)) {
				throw new IllegalStateException();
			}
			element.addChild((Element) c);
		}

		return element;
	}

}
