import java.io.IOException;
import java.io.PrintWriter;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;




public class Principal {
	public static void main(String[] args) {
		 CharStream cs = CharStreams.fromFileName(args[0]);
	     AlgumaLexer lexer = new AlgumaLexer(cs);
	     CommonTokenStream tokens = new CommonTokenStream(lexer);
	     AlgumaParser parser = new AlgumaParser(tokens);
	     ProgramaContext arvore = parser.programa();
	     AlgumaSemantico as = new AlgumaSemantico();
	     as.visitPrograma(arvore);
	     AlgumaSemanticoUtils.errosSemanticos.forEach((s) -> System.out.println(s));
	    }
	}


