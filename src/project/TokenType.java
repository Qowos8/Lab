package project;
public class TokenType {
    String nameType;
    String regex;

    public TokenType(String nameType, String regex) {
        this.nameType = nameType;
        this.regex = regex;
    }
    public static TokenType[] listOfToken = {
            new TokenType("NUM", "^0|[1-9][0-9]*"),
            new TokenType("SPACE", "\\ "),
            new TokenType("EndL", "[\\n]"),
            new TokenType("Carriage", "[\\r]"),
            new TokenType("EQUAL", "(?i)=="),
            new TokenType("LESS", "[<]"),
            new TokenType("MORE", "[>]"),
            new TokenType("ASSIGN", "[=]"),
            new TokenType("PLUS", "[+]"),
            new TokenType("MINUS", "[-]"),
            new TokenType("MULT", "[*]"),
            new TokenType("DIV", "[/]"),
            new TokenType("PRINT", "(?i)print"),
            new TokenType("IF","(?i)if"),
            new TokenType("ELSE","(?i)else"),
            new TokenType("FOR", "(?i)for"),
            new TokenType("WHILE","(?i)while"),
            new TokenType("END", "[;]"),
            new TokenType("LPAR", "[(]"),
            new TokenType("RPAR", "[)]"),
            new TokenType("LRectPar", "[{]"),
            new TokenType("RRectPAR", "[}]"),
            new TokenType("ADD","(?i)ADD"),
            new TokenType("GET","(?i)GET"),
            new TokenType("CONTAINS","(?i)CONTAINS"),
            new TokenType("VAR", "[a-z][a-z]*"),
    };
}