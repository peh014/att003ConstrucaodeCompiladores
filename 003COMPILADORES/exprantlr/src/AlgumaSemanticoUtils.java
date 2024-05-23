
public class AlgumaSemanticoUtils {



	public class AlgumaSemanticoUtils {
	    public static List<String> errosSemanticos = new ArrayList<>();
	    
	    public static void adicionarErroSemantico(Token t, String mensagem) {
	        int linha = t.getLine();
	        int coluna = t.getCharPositionInLine();
	        errosSemanticos.add(String.format("Erro %d:%d - %s", linha, coluna, mensagem));
	    }
	
	    public static TabelaDeSimbolos.TipoAlguma verificarTipo(TabelaDeSimbolos tabela, AlgumaParser.ExpressaoAritmeticaContext ctx) {
	        TabelaDeSimbolos.TipoAlguma ret = null;
	        for (var ta : ctx.termoAritmetico()) {
	            TabelaDeSimbolos.TipoAlguma aux = verificarTipo(tabela, ta);
	            if (ret == null) {
	                ret = aux;
	            } else if (ret != aux && aux != TabelaDeSimbolos.TipoAlguma.INVALIDO) {
	                adicionarErroSemantico(ctx.start, "Expressão " + ctx.getText() + " contém tipos incompatíveis");
	                ret = TabelaDeSimbolos.TipoAlguma.INVALIDO;
	            }
	        }

	        return ret;
	    }

	    public static TabelaDeSimbolos.TipoAlguma verificarTipo(TabelaDeSimbolos tabela, AlgumaParser.TermoAritmeticoContext ctx) {
	        TabelaDeSimbolos.TipoAlguma ret = null;

	        for (var fa : ctx.fatorAritmetico()) {
	            TabelaDeSimbolos.TipoAlguma aux = verificarTipo(tabela, fa);
	            if (ret == null) {
	                ret = aux;
	            } else if (ret != aux && aux != TabelaDeSimbolos.TipoAlguma.INVALIDO) {
	                adicionarErroSemantico(ctx.start, "Termo " + ctx.getText() + " contém tipos incompatíveis");
	                ret = TabelaDeSimbolos.TipoAlguma.INVALIDO;
	            }
	        }
	        return ret;
	    }

	    public static TabelaDeSimbolos.TipoAlguma verificarTipo(TabelaDeSimbolos tabela, AlgumaParser.FatorAritmeticoContext ctx) {
	        if (ctx.NUMINT() != null) {
	            return TabelaDeSimbolos.TipoAlguma.INTEIRO;
	        }
	        if (ctx.NUMREAL() != null) {
	            return TabelaDeSimbolos.TipoAlguma.REAL;
	        }
	        if (ctx.VARIAVEL() != null) {
	            String nomeVar = ctx.VARIAVEL().getText();
	            if (!tabela.existe(nomeVar)) {
	                adicionarErroSemantico(ctx.VARIAVEL().getSymbol(), "Variável " + nomeVar + " não foi declarada antes do uso");
	                return TabelaDeSimbolos.TipoAlguma.INVALIDO;
	            }
	            return verificarTipo(tabela, nomeVar);
	        }
	        return verificarTipo(tabela, ctx.expressaoAritmetica());
	    }
	    


		
	    public static TabelaDeSimbolos.TipoAlguma verificarTipo(TabelaDeSimbolos tabela, String nomeVar) {
	        return tabela.verificar(nomeVar);
	    }
	}
}
