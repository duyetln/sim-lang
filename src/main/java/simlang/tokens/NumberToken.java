package simlang.tokens;

public class NumberToken extends Token {
  public NumberToken(String value) {
    super(60, "number", value);
  }
}
