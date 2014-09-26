package simlang.tokens;

public class ErrorToken extends Token {
  public ErrorToken(String value) {
    super(0, "error", value);
  }
}
