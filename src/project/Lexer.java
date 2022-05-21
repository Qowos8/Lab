package project;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    String code;
    int position = 0;
    ArrayList<Token> tokenList = new ArrayList<>();

    public Lexer(String code){

        this.code = code;
    }
    public ArrayList<Token> analyze(){
        while(findTokens()){}
        for (Token token : tokenList)
            if (!(token.text.equals(" ") || token.text.equals("\\r")))
                System.out.println(token.type.nameType + " " + token.text + " " + "(" + token.position + ")-position");
        return this.tokenList;
    }
    public boolean findTokens(){
        if(this.position >= code.length()) {
            return false;
        }
        TokenType[] tokenTypes = TokenType.listOfToken;
        for (int i = 0; i < tokenTypes.length; i++){
            TokenType tokenType = tokenTypes[i];
            String regex = tokenType.regex;
            Matcher matcher = Pattern.compile(regex).matcher(code);
            if(matcher.find(this.position) && matcher.start() == this.position)
            {
                String result = this.code.substring(this.position,this.position + matcher.group().length());
                Token token = new Token(tokenType, result, this.position);
                this.position += result.length();
                if(token.type!= TokenType.listOfToken[3] && token.type!= TokenType.listOfToken[2] && token.type!= TokenType.listOfToken[1])
                    tokenList.add(token);
                return true;
            }
        }
        throw new Error("Error on position: " + this.position);
    }

}