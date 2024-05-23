grammar Expressoes;

programa returns [ int val ]
	:	expressao EOF { $val = $expressao.val; }
	;
	
expressao returns  [ int val ]
	:	termo expressao2[$termo.val] { $val = $expressao2.sint; }
	;
	
expressao2 [ int her ] returns [ int sint ]
	:	'+' termo exp=expressao2[$termo.val+$her] { $sint = $exp.sint; }
	|	'-' termo exp=expressao2[$her-$termo.val] { $sint = $exp.sint; }
	|	{ $sint = $her; }
	;
	
termo returns [ int val ]
	:	fator termo2[$fator.val] { $val = $termo2.sint; }
	;
	
termo2 [ int her ] returns [ int sint ]
	:	'*' fator term=termo2[$fator.val*$her] { $sint = $term.sint; }
	|	{ $sint = $her; }
	;
	
fator returns [ int val ]
	:	'(' expressao ')' { $val = $expressao.val; }
	|	NUM { $val = Integer.parseInt($NUM.getText()); }
	;

NUM	:	'0'..'9'+
	;
	
WS	:	( ' ' | '\n' | '\r' | '\t' ) { skip(); }
	;