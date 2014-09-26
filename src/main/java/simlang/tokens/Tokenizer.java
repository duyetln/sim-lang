package simlang.tokens;

import java.io.*;
import java.util.LinkedList;
import java.util.ListIterator;

public class Tokenizer {
  private Token current;
  private ListIterator<Token> tokens;
  private BufferedReader reader;

  public Tokenizer(File file) throws FileNotFoundException, IOException {
    try {
      reader = new BufferedReader(new FileReader(file));
      tokens = tokenize();
      current = tokens.next();
    } finally {
      if (reader != null) {
        reader.close();
      }
    }
  }

  public Token current() {
    return current;
  }

  public Token next() {
    current = tokens.next();
    return current;
  }

  public boolean hasNext() {
    return tokens.hasNext();
  }

  public Token previous() {
    current = tokens.previous();
    return current;
  }

  public boolean hasPrevious() {
    return tokens.hasPrevious();
  }

  private ListIterator<Token> tokenize() throws IOException {
    LinkedList<Token> tokenList = new LinkedList<Token>();
    int readValue;
    String character;

    readValue = reader.read();
    character = String.valueOf(readValue);

    while (readValue != -1) {
      if (character.equals("*")) tokens.add(Token.build(Token.TIMES, character));
      else if (character.equals("/")) tokens.add(Token.build(Token.DIV, character));
      else if (character.equals("+")) tokens.add(Token.build(Token.PLUS, character));
      else if (character.equals("-")) tokens.add(Token.build(Token.MINUS, character));
      else if (character.equals(".")) tokens.add(Token.build(Token.PERIOD, character));
      else if (character.equals(",")) tokens.add(Token.build(Token.COMMA, character));
      else if (character.equals("[")) tokens.add(Token.build(Token.OPENBRACKET, character));
      else if (character.equals("]")) tokens.add(Token.build(Token.CLOSEBRACKET, character));
      else if (character.equals("(")) tokens.add(Token.build(Token.OPENPAREN, character));
      else if (character.equals(")")) tokens.add(Token.build(Token.CLOSEPAREN, character));
      else if (character.equals(";")) tokens.add(Token.build(Token.SEMI, character));
      else if (character.equals("{")) tokens.add(Token.build(Token.BEGIN, character));
      else if (character.equals("}")) tokens.add(Token.build(Token.END, character));
      else if (character.equals("=")) tokens.add(tokenizeEql(character));
      else if (character.equals("!")) tokens.add(tokenizeEcp(character));
      else if (character.equals("<")) tokens.add(tokenizeLss(character));
      else if (character.equals(">")) tokens.add(tokenizeGtr(character));
      else if (character.matches("[a-z]")) tokens.add(tokenizeChar(character));
      else if (character.matches("[0-9]")) tokens.add(tokenizeNum(character));
      else tokens.add(Token.build(Token.ERROR, character));

      readValue = reader.read();
      character = String.valueOf(readValue);
    }

    tokenList.add(Token.build(Token.EOF, character));

    return tokenList.listIterator(0);
  }

  private Token tokenizeEql(String previous) throws IOException {
    reader.mark(1);
    String character = String.valueOf(reader.read());
    String whole = previous + character;
    if (character.equals("=")) {
      return Token.build(Token.EQL, whole);
    } else {
      reader.reset();
      return Token.build(Token.ERROR, character);
    }
  }

  private Token tokenizeEcp(String previous) throws IOException {
    reader.mark(1);
    String character = String.valueOf(reader.read());
    String whole = previous + character;
    if (character.equals("=")) {
      return Token.build(Token.NEQ, whole);
    } else {
      reader.reset();
      return Token.build(Token.ERROR, character);
    }
  }

  private Token tokenizeLss(String previous) throws IOException {
    reader.mark(1);
    String character = String.valueOf(reader.read());
    String whole = previous + character;
    if (character.equals("=")) {
      return Token.build(Token.LEQ, whole);
    } else if (character.equals("-")) {
      return Token.build(Token.BECOMES, whole);
    } else {
      reader.reset();
      return Token.build(Token.LSS, previous);
    }
  }

  private Token tokenizeGtr(String previous) throws IOException {
    reader.mark(1);
    String character = String.valueOf(reader.read());
    String whole = previous + character;
    if (character.equals("=")) {
      return Token.build(Token.GEQ, whole);
    } else {
      reader.reset();
      return Token.build(Token.GTR, previous);
    }
  }

  private Token tokenizeChar(String previous) throws IOException {
    String character = "";
    String whole = previous + character;

    do {
      whole += character;
      reader.mark(1);
      character = String.valueOf(reader.read());
    } while(character.matches("[a-z0-9]"));

    reader.reset();

    if (whole.equals("then")) return Token.build(Token.THEN, whole);
    else if (whole.equals("do")) return Token.build(Token.DO, whole);
    else if (whole.equals("od")) return Token.build(Token.OD, whole);
    else if (whole.equals("fi")) return Token.build(Token.FI, whole);
    else if (whole.equals("else")) return Token.build(Token.ELSE, whole);
    else if (whole.equals("call")) return Token.build(Token.CALL, whole);
    else if (whole.equals("if")) return Token.build(Token.IF, whole);
    else if (whole.equals("while")) return Token.build(Token.WHILE, whole);
    else if (whole.equals("return")) return Token.build(Token.RETURN, whole);
    else if (whole.equals("var")) return Token.build(Token.VAR, whole);
    else if (whole.equals("array")) return Token.build(Token.ARR, whole);
    else if (whole.equals("function")) return Token.build(Token.FUNCTION, whole);
    else if (whole.equals("procedure")) return Token.build(Token.PROC, whole);
    else if (whole.equals("main")) return Token.build(Token.MAIN, whole);
    else return Token.build(Token.IDENT, whole);
  }

  private Token tokenizeNum(String previous) throws IOException {
    String character = "";
    String whole = previous + character;

    do {
      whole += character;
      reader.mark(1);
      character = String.valueOf(reader.read());
    } while(character.matches("[0-9]"));

    reader.reset();

    return Token.build(Token.NUMBER, whole);
  }
}
