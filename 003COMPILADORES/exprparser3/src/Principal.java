
public class Principal {

    

	public static void main(String[] args) {
		CharStream cs = CharStreams.fromFileName(args[0]);
        ExpressoesLexer lexer = new ExpressoesLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExpressoesParser parser = new ExpressoesParser(tokens);
        ProgramaContext arvore = parser.programa();
        Calculador c = new Calculador();
        double val = c.visitPrograma(arvore);
        System.out.println("Valor calculado: " + val);
	}

}
