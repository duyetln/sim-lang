package simlang.tokens;

public class IdentToken extends Token {
  public IdentToken(String value) {
    super(61, "identifier", value);
  }
}
