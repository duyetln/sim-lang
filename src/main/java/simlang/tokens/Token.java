package simlang.tokens;

public abstract class Token {
  public final int type;
  public final String name;
  public final String value;

  protected Token(int type, String name, String value) {
    this.type = type;
    this.name = name;
    this.value = value;
  }

  public static final int ERROR = 0;

  public static final int TIMES = 1;
  public static final int DIV = 2;
  public static final int PLUS = 11;
  public static final int MINUS = 12;

  public static final int EQL = 20;
  public static final int NEQ = 21;
  public static final int LSS = 22;
  public static final int GEQ = 23;
  public static final int LEQ = 24;
  public static final int GTR = 25;

  public static final int PERIOD = 30;
  public static final int COMMA = 31;
  public static final int OPENBRACKET = 32;
  public static final int CLOSEBRACKET = 34;
  public static final int CLOSEPAREN = 35;

  public static final int BECOMES = 40;
  public static final int THEN = 41;
  public static final int DO = 42;

  public static final int OPENPAREN = 50;
  public static final int NUMBER = 60;
  public static final int IDENT = 61;

  public static final int SEMI = 70;
  public static final int END = 80;
  public static final int OD = 81;
  public static final int FI = 82;
  public static final int ELSE = 90;
  public static final int CALL = 100;
  public static final int IF = 101;
  public static final int WHILE = 102;
  public static final int RETURN = 103;

  public static final int VAR = 110;
  public static final int ARR = 111;
  public static final int FUNCTION = 112;
  public static final int PROC = 113;

  public static final int BEGIN = 150;
  public static final int MAIN = 200;
  public static final int EOF = 255;

  public static Token build(int type, String value) {
    switch (type) {
      case Token.ERROR: return new ErrorToken(value);
      case Token.TIMES: return new TimesToken(value);
      case Token.DIV: return new DivToken(value);
      case Token.PLUS: return new PlusToken(value);
      case Token.MINUS: return new MinusToken(value);
      case Token.EQL: return new EqlToken(value);
      case Token.NEQ: return new NeqToken(value);
      case Token.LSS: return new LssToken(value);
      case Token.GEQ: return new GeqToken(value);
      case Token.LEQ: return new LeqToken(value);
      case Token.GTR: return new GtrToken(value);
      case Token.PERIOD: return new PeriodToken(value);
      case Token.COMMA: return new CommaToken(value);
      case Token.OPENBRACKET: return new OpenBracketToken(value);
      case Token.CLOSEBRACKET: return new CloseBracketToken(value);
      case Token.CLOSEPAREN: return new CloseParenToken(value);
      case Token.BECOMES: return new BecomesToken(value);
      case Token.THEN: return new ThenToken(value);
      case Token.DO: return new DoToken(value);
      case Token.OPENPAREN: return new OpenParenToken(value);
      case Token.NUMBER: return new NumberToken(value);
      case Token.IDENT: return new IdentToken(value);
      case Token.SEMI: return new SemiToken(value);
      case Token.END: return new EndToken(value);
      case Token.OD: return new OdToken(value);
      case Token.FI: return new FiToken(value);
      case Token.ELSE: return new ElseToken(value);
      case Token.CALL: return new CallToken(value);
      case Token.IF: return new IfToken(value);
      case Token.WHILE: return new WhileToken(value);
      case Token.RETURN: return new ReturnToken(value);
      case Token.VAR: return new VarToken(value);
      case Token.ARR: return new ArrToken(value);
      case Token.FUNCTION: return new FunctionToken(value);
      case Token.PROC: return new ProcToken(value);
      case Token.BEGIN: return new BeginToken(value);
      case Token.MAIN: return new MainToken(value);
      case Token.EOF: return new EofToken(value);
      default: return null;
    }
  }

  public boolean equals(Object object) {
    return object == this || object != null && object.getClass() == this.getClass();
  }
}
