package project;

public class Token {
    TokenType type;
    String text;
    int position;

    public Token(TokenType type, String text, int position){
        this.type = type;
        this.text = text;
        this.position = position;
    }
}
