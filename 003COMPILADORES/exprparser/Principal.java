public class Principal {


    
	public static void main(String[] args) {
	    CharStream cs = CharStreams.fromFileName(args[0]);
        ExpressoesLexer lexer = new ExpressoesLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExpressoesParser parser = new ExpressoesParser(tokens);
        int val = parser.programa().val;
        System.out.println("Valor = " + val);
	}

}
