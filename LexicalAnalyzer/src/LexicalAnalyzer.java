import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.BufferedReader;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class LexicalAnalyzer {


	  public static enum TokenType {
	   
	    NUMBER("-?[0-9]+"), UNARYPLUS("[+][+]+"),UNARYMINUS("[-][-]+"), OP("[%|*|[+]|[-]|/]"), WHITESPACE("[ \t\f\r\n]+"),
	    KEYWORD("if|for|else|break|while|continue|cout<<|cin>>|void|int|return|char|string|main"),
	    LOP(">=|<=|==|!=|<|>"), PAREN("[(|)]"), DELEM("[;|,|{|}]"),ID("[a-zA-Z][a-zA-Z0-9]*"),Literals("[']|[\"][[a-zA-Z0-9]|[ ]]*[\"]|[']"),
	     AssignmentOP("[=]");
	     
	    public final String pattern;

	    private TokenType(String pattern) {
	      this.pattern = pattern;
	    }
	  }

public static class Token {
	
    public TokenType type;
    public String data;

    public Token(TokenType type, String data) {
      this.type = type;
      this.data = data;
    }

    @Override
    public String toString() {
      return String.format("<%s , %s >", type.name(), data);
    }
  }

  public static ArrayList<Token> lex(String input) {
  
    ArrayList<Token> tokens = new ArrayList<Token>();
ArrayList al = new ArrayList();
  
    StringBuffer tokenPatternsBuffer = new StringBuffer();
    for (TokenType tokenType : TokenType.values())
      tokenPatternsBuffer.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern));
    Pattern tokenPatterns = Pattern.compile(new String(tokenPatternsBuffer.substring(1)));

  
    Matcher matcher = tokenPatterns.matcher(input);
    while (matcher.find()) {
      if (matcher.group(TokenType.NUMBER.name()) != null) {
        tokens.add(new Token(TokenType.NUMBER, matcher.group(TokenType.NUMBER.name())));
        continue;
      }
      else if (matcher.group(TokenType.OP.name()) != null) {
          tokens.add(new Token(TokenType.OP, matcher.group(TokenType.OP.name())));
          continue;
          
        }
      else if (matcher.group(TokenType.UNARYMINUS.name()) != null) {
          tokens.add(new Token(TokenType.UNARYMINUS, matcher.group(TokenType.UNARYMINUS.name())));

          continue;
          
        }
      else if (matcher.group(TokenType.UNARYPLUS.name()) != null) {
          tokens.add(new Token(TokenType.UNARYPLUS, matcher.group(TokenType.UNARYPLUS.name())));

          continue;
          
        }
      else if (matcher.group(TokenType.KEYWORD.name()) != null) {
          tokens.add(new Token(TokenType.KEYWORD, matcher.group(TokenType.KEYWORD.name())));

          continue;
          
        }
      else if (matcher.group(TokenType.Literals.name()) != null) {
          tokens.add(new Token(TokenType.Literals, matcher.group(TokenType.Literals.name())));

          continue;
          
        }
      else if (matcher.group(TokenType.LOP.name()) != null) {
          tokens.add(new Token(TokenType.LOP, matcher.group(TokenType.LOP.name())));

          continue;
          
        }
      else if (matcher.group(TokenType.PAREN.name()) != null) {
          tokens.add(new Token(TokenType.PAREN, matcher.group(TokenType.PAREN.name())));

          continue;
          
        }
      else if (matcher.group(TokenType.DELEM.name()) != null) {
          tokens.add(new Token(TokenType.DELEM, matcher.group(TokenType.DELEM.name())));

          continue;
          
        }
    
      else if (matcher.group(TokenType.ID.name()) != null) {
          tokens.add(new Token(TokenType.ID, matcher.group(TokenType.ID.name())));

          continue;
          
        }
      else if (matcher.group(TokenType.AssignmentOP.name()) != null) {
          tokens.add(new Token(TokenType.AssignmentOP, matcher.group(TokenType.AssignmentOP.name())));

          continue;
          
        }
    
      
      else if (matcher.group(TokenType.WHITESPACE.name()) != null)
        continue;
    }
    

    return tokens;
  }

  public static void main(String[] args) {
    
    String inputFile = "C://Users//Ami//Desktop//input.txt";
    String outputFile = "C://Users//Ami//Desktop//output.txt";

    String line = "";
String merge = "";
    try {
       
        FileReader fileReader = 
            new FileReader(inputFile);


        BufferedReader bufferedReader = 
            new BufferedReader(fileReader);

        while((line = bufferedReader.readLine()) != null) {
          merge=merge+line;
          
        }   
      

        bufferedReader.close();
        
        ArrayList<Token> tokens = lex(merge);
        
        FileWriter fileWriter =
                new FileWriter(outputFile);

       
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);

            for (Token token : tokens)
            {
            
               bufferedWriter.write(token.toString());
               bufferedWriter.newLine();
            }
          


            bufferedWriter.close();
     
    }
    catch(FileNotFoundException ex) {
               ex.printStackTrace();
    }
    catch(IOException ex) {
               
    ex.printStackTrace();
    
   
  }
}
}