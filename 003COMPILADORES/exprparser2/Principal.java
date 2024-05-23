public class Principal {



	public static void main(String[] args) {
		    ExpressoesLexer lexer = new ExpressoesLexer(cs);
	        CommonTokenStream tokens = new CommonTokenStream(lexer);
	        ExpressoesParser parser = new ExpressoesParser(tokens);
	        ExpressoesParser.ProgramaContext arvore = parser.programa();
	        Calculador c = new Calculador();
	        double val = c.visitPrograma(arvore);
	        System.out.println("Valor2 = " + val);
	}
}
