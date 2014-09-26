package simlang.tokens;

public class EofToken extends Token {
  public EofToken(String value) {
    super(255, "eof", value);
  }
}
