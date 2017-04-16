import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Parser {

	public static void main(String[] args) {
		List<String> parse = new ArrayList();
		String inputFile = "C://Users//Talha Amin//Desktop//output.txt";
		String line = " ";
		int i = 0;
		int val;
		Stack<String> stack = new Stack();

		boolean check = true;

		FileReader fileReader;
		try {
			fileReader = new FileReader(inputFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				parse.add(line);

			}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			String[] parser = new String[parse.size()];
			parser = parse.toArray(parser);
	try{
			for(i = 0; i<parser.length && i>=0;i++)
			{
				if (parser[i].contains("cout<<")) {
					val = checkcout(parser, i);
					if (val >= 0) {
						i = val;
					} else {
						System.out.println("syntax error in cout statement at " + i);

					}
				}
					else if (parser[i].contains("cin>>")) {
						val = checkcin(parser, i);
						if (val >= 0) {
							i = val;
						} else {
							System.out.println("syntax error in cin>> statement at " + i);

						}
					}
					else if (parser[i].contains("UNARY")) {
						val = iterator(parser, i);
						if (val >= 0) {
							i = val;
						} else {
							System.out.println("syntax error in iteration statement at "+i);

						}
					}
				
					else if(parser[i].contains("if"))
					{
						val = checkif(parser,i);
						if(val >=0)
							i=val;
						else
							System.out.println("syntax error in if statement at " + i);
					}
					else if(parser[i].contains("while"))
					{
						val = checkif(parser,i);
						if(val >=0)
							i=val;
						else
							System.out.println("syntax error in while statement at " + i);
					}
					else if(parser[i].contains("for"))
					{
						val = checkfor(parser,i);
						if(val >=0)
							i=val;
						else
							System.out.println("syntax error in for statement at " + i);
					}
				
					else if(parser[i].contains("int")||parser[i].contains("char")||parser[i].contains("string"))
					{
						val = checkdeclaration(parser, i);
						if (val >= 0) {
							i = val;
						}
						
						else
							System.out.println("syntax error in declaration statement at " + i);
					}
					else if(parser[i].contains("ID"))
					{
						val = checkexpression(parser, i);
						if (val >= 0) {
							i = val;
						}
						
						else{
							System.out.println("syntax error in expression statement at " + i);
					}
					}
				
				
					else if(parser[i].contains("{"))
					{
						stack.push(parser[i]);
					}
					else if(parser[i].contains("}"))
					{
						stack.pop();
					}
					else
						continue;
				
		
		}
	}
	catch(ArrayIndexOutOfBoundsException e)
	{
		System.out.println("Parsing Unsuccessful, Error on last line");
	}
			if(!stack.empty())
			{
				System.out.println("Mismatch of Body Brackets");
			}
		
			
	
		}
	
	private static int checkexpression(String[] parser, int i) {
		i=i+1;
		if(parser[i].contains("="))
		{
			i++;
			if(parser[i].contains("ID")||parser[i].contains("NUMBER"))
			{
				i++;
				if(parser[i].contains("OP"))
				{
					i++;
					if(parser[i].contains("ID")||parser[i].contains("NUMBER"))
					{
						i++;
						if(parser[i].contains(";"))
						{
							return i;
						}
					}
				}
			}
					
		}
		else
			return -1;
		return -1;
	}

	private static int checkfor(String[] parser, int i) {
		i=i+1;
		if(parser[i].contains("(")){
			i++;
			if (parser[i].contains("ID")) {
				i++;
			
				if(parser[i].contains("="))
				{
					i++;
					
					if(parser[i].contains("ID")||parser[i].contains("NUMBER"))
					{
						i++;
						
						if(parser[i].contains(";"))
						{
							i++;
							
							if(parser[i].contains("ID"))
							{
								i++;
								
								if(parser[i].contains("LOP"))
								{
									i++;
									if(parser[i].contains("ID")||parser[i].contains("NUMBER"))
									{
										i++;
										if(parser[i].contains(";"))
										{
											i++;
											if(parser[i].contains("ID"))
											{
												i++;
												if(parser[i].contains("UNARY"))
												{
													i++;
													if(parser[i].contains(")"))
													{
														return i;
													}
												}
											}
											else if(parser[i].contains("UNARY"))
											{
												i++;
												if(parser[i].contains("ID"))
												{
													i++;
													if(parser[i].contains(")"))
													{
														return i;
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}	
		
		 else
			return -1;
		return -1;
	}

	public static int checkcout(String[] parser, int i) {
		i = i + 1;
		
			if (parser[i].contains("Literals") | parser[i].contains("ID")) {
				i++;
				if(parser[i].contains(";"))
				{
					return i;
				}
				
//			} else if (parser[i].contains("+")) {
//				i++;
//				checkcout(parser, i);
			}
			else
				return -1;
		
		return -1;
	}
	public static int checkcin(String[] parser, int i) {
		i = i + 1;
			if (parser[i].contains("ID")) {
				i++;
				if(parser[i].contains(";"))
				{
					return i;
				}
			} else
				return -1;
		
		return -1;
	}
	
	public static int iterator(String[] parser, int i) {
		if (parser[i].contains("UNARY")) {
			i = i - 1;
		}

		if (parser[i].contains("ID")) {
			i++;
			if (parser[i].contains("UNARY")) {
				i++;
				if(parser[i].contains(";")||parser[i].contains(")"))
				{
					return i;
				}
			}
		} else {
			i = i + 1;
			if (parser[i].contains("UNARY")) {
				i++;
				if (parser[i].contains("ID")) {
					i++;
					if(parser[i].contains(";")||parser[i].contains(")"))
					{
						return i;
					}
				}
			}
		}
		return -1;
	}
	
	public static int checkif(String[] parser, int i)
	{
		i=i+1;
		if(parser[i].contains("("))
		{
			i++;
			if(parser[i].contains("ID"))
			{
				i++;
				if(parser[i].contains("LOP"))
				{
					i++;
					if(parser[i].contains("ID")||parser[i].contains("NUMBER"))
					{
						i++;
						if(parser[i].contains(")"))
						{
							return i;
						}
					}
				}
			}
		}
		return -1;
	}
	
	
	public static int checkdeclaration(String[] parser,int i)
	{
		i++;
		if(parser[i].contains("ID"))
		{
			i++;
			if(parser[i].contains("("))
			{
				i++;
				if(parser[i].contains(")"))
				{
					return i;
				}
			}
			else if(parser[i].contains(";"))
			{
				return i;
			}
		}
		else{
			return -1;
		}
		
		
		return -1;
	}
	
	

	
	
}

