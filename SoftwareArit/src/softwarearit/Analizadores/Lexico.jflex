
package softwarearit.Analizadores;

import java_cup.runtime.Symbol;
import java.util.Collections;
import java.util.List;
import softwarearit.Arbol.Estructura.Base.*;

%%
%cupsym sym
%class Lexico
%cup
%public
%unicode
%line
%column
%char
%ignorecase
//#### posicion de curso ##########
%init{
    yyline = 1;
    yychar = 1;
%init}

//#######  codigo java ############
%{
    StringBuilder cadena = new StringBuilder();
%}

//#### EXPRESIONES REGULARES ######
    DIGITO =[0-9]
    LETRA = [A-Za-z]
    IDENTIFICADOR = ({LETRA}|"."{LETRA})("."|"_"|{LETRA}|{DIGITO})*
    NUMERO_ENTERO = ({DIGITO})+
    NUMERO_DECIMAL = ({DIGITO})+(".")({DIGITO})+

    BLANCOS = [ \r\t]+
    FIN_LINEA = \r|\n|\r\n
    COMENTARIO_LINEA = "#" .* {FIN_LINEA}

//### ERRORES DE IDENTIFICADORES
    ERROR_ID1 = (".")({DIGITO})({LETRA}|{DIGITO}|".")+
    ERROR_ID2 = ({DIGITO})({LETRA}|{DIGITO}|".")+

//##### Estados ##########
%state INICIO, COMENTARIO_MULTILINEA, ESTADO_CADENA

%%

<YYINITIAL> {BLANCOS} {}
<YYINITIAL> {FIN_LINEA} { yychar = 1; }
<YYINITIAL> {COMENTARIO_LINEA} { yychar=1; }
<YYINITIAL> "#*" { yybegin(COMENTARIO_MULTILINEA); }

<COMENTARIO_MULTILINEA> "*#" { yybegin(YYINITIAL); }
<COMENTARIO_MULTILINEA> \n { yychar = 1; }
<COMENTARIO_MULTILINEA> {BLANCOS} {}
<COMENTARIO_MULTILINEA> . {}

<YYINITIAL> "\"" { yybegin(ESTADO_CADENA); cadena.delete(0,cadena.length()); }
<ESTADO_CADENA> "\"" {yybegin(YYINITIAL); return new Symbol(sym._string,yyline,yychar,cadena.toString());}
<ESTADO_CADENA> "\\\"" {cadena.append("\""); }
<ESTADO_CADENA> "\\" {cadena.append("\\"); }
<ESTADO_CADENA> "\\n" {cadena.append("\n"); }
<ESTADO_CADENA> "\\r" {cadena.append("\r"); }
<ESTADO_CADENA> "\\t" {cadena.append("\t"); }
<ESTADO_CADENA> . {cadena.append(yytext()); }

<YYINITIAL> "true" { return new Symbol(sym.valorbooleano, yyline,yychar,yytext()); }
<YYINITIAL> "false" { return new Symbol(sym.valorbooleano, yyline,yychar,yytext()); }

/*
<YYINITIAL> "imprimir" { return new Symbol(sym.imprimir,yyline,yychar,yytext()); } 

<YYINITIAL> "++" { return new Symbol(sym.masmas,yyline,yychar,yytext()); } 
<YYINITIAL> "--" { return new Symbol(sym.menosmenos,yyline,yychar,yytext()); } 

<YYINITIAL> "+" { return new Symbol(sym.mas,yyline,yychar,yytext()); } 
<YYINITIAL> "-" { return new Symbol(sym.menos,yyline,yychar,yytext()); } 
<YYINITIAL> "*" { return new Symbol(sym.multiplicacion,yyline,yychar,yytext()); } 
<YYINITIAL> "/" { return new Symbol(sym.division,yyline,yychar,yytext()); } 
<YYINITIAL> "%" { return new Symbol(sym.modulo,yyline,yychar,yytext()); } 

<YYINITIAL> "<" { return new Symbol(sym.menorque,yyline,yychar,yytext()); } 
<YYINITIAL> ">" { return new Symbol(sym.mayorque,yyline,yychar,yytext()); } 
<YYINITIAL> "<=" { return new Symbol(sym.menorigual,yyline,yychar,yytext()); } 
<YYINITIAL> ">=" { return new Symbol(sym.mayorigual,yyline,yychar,yytext()); } 
<YYINITIAL> "==" { return new Symbol(sym.igualigual,yyline,yychar,yytext()); } 
<YYINITIAL> "!=" { return new Symbol(sym.diferenteque,yyline,yychar,yytext()); } 
<YYINITIAL> "=" { return new Symbol(sym.igual,yyline,yychar,yytext()); } 

<YYINITIAL> "||" { return new Symbol(sym.or,yyline,yychar,yytext()); } 
<YYINITIAL> "&&" { return new Symbol(sym.and,yyline,yychar,yytext()); } 
<YYINITIAL> "!" { return new Symbol(sym.not,yyline,yychar,yytext()); } 

<YYINITIAL> "," { return new Symbol(sym.coma,yyline,yychar,yytext()); } 
<YYINITIAL> ";" { return new Symbol(sym.puntoycoma,yyline,yychar,yytext()); } 
<YYINITIAL> "{" { return new Symbol(sym.llaveizquierda,yyline,yychar,yytext()); } 
<YYINITIAL> "}" { return new Symbol(sym.llavederecha,yyline,yychar,yytext()); } 
<YYINITIAL> "[" { return new Symbol(sym.corcheteizquierda,yyline,yychar,yytext()); } 
<YYINITIAL> "]" { return new Symbol(sym.corchetederecha,yyline,yychar,yytext()); } 
<YYINITIAL> "(" { return new Symbol(sym.parentesisizquierda,yyline,yychar,yytext()); } 
<YYINITIAL> ")" { return new Symbol(sym.parentesisderecha,yyline,yychar,yytext()); } 
<YYINITIAL> "." { return new Symbol(sym.punto,yyline,yychar,yytext()); } 

<YYINITIAL> "int" { return new Symbol(sym._int,yyline,yychar,yytext()); } 
<YYINITIAL> "char" { return new Symbol(sym._char,yyline,yychar,yytext()); }  
<YYINITIAL> "float" { return new Symbol(sym._float,yyline,yychar,yytext()); }  

<YYINITIAL> "do" { return new Symbol(sym._do,yyline,yychar,yytext()); } 
<YYINITIAL> "if" { return new Symbol(sym._if,yyline,yychar,yytext()); } 
<YYINITIAL> "case" { return new Symbol(sym._case,yyline,yychar,yytext()); } 
<YYINITIAL> "else" { return new Symbol(sym._else,yyline,yychar,yytext()); } 
<YYINITIAL> "while" { return new Symbol(sym._while,yyline,yychar,yytext()); } 
<YYINITIAL> "for" { return new Symbol(sym._for,yyline,yychar,yytext()); } 

<YYINITIAL> "break" { return new Symbol(sym._break,yyline,yychar,yytext()); } 
<YYINITIAL> "continue" { return new Symbol(sym._continue,yyline,yychar,yytext()); } 
<YYINITIAL> "return" { return new Symbol(sym._return,yyline,yychar,yytext()); } 

<YYINITIAL> "void" { return new Symbol(sym._void,yyline,yychar,yytext()); } 
<YYINITIAL> "static" { return new Symbol(sym._static,yyline,yychar,yytext()); } 

*/
<YYINITIAL> {NUMERO_ENTERO} { return new Symbol(sym.entero,yyline,yychar,yytext()); } 
<YYINITIAL> {NUMERO_DECIMAL} { return new Symbol(sym.decimal,yyline,yychar,yytext()); } 
<YYINITIAL> {IDENTIFICADOR}  {return new Symbol(sym._id,yyline,yychar,yytext());}
<YYINITIAL> {ERROR_ID1} { softwarearit.Frame.Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.LEXICO), "Identificador incorrecto " + yytext(), yyline, yychar)); }
<YYINITIAL> {ERROR_ID2} { softwarearit.Frame.Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.LEXICO), "Identificador incorrecto " + yytext(), yyline, yychar)); }

. {
    softwarearit.Frame.Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.LEXICO), "Simbolo no conocido " + yytext(), yyline, yychar));
}
