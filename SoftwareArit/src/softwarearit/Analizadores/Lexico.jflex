
package softwarearit.Analizadores;

import java_cup.runtime.Symbol;
import java.util.Collections;
import java.util.List;
import softwarearit.Arbol.Estructura.*;
import softwarearit.Arbol.Expresiones.*;
import softwarearit.Arbol.Instrucciones.*;

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

//### ERRORES DE IDENTIFICADORES
    ERROR_ID1 = (".")({DIGITO})({LETRA}|{DIGITO}|".")+
    ERROR_ID2 = ({DIGITO})({LETRA}|{DIGITO}|".")+

//##### Estados ##########
%state INICIO, COMENTARIO_MULTILINEA, ESTADO_CADENA, COMENTARIO_LINEA

%%

<YYINITIAL> {BLANCOS} {}
<YYINITIAL> {FIN_LINEA} { yychar = 1; }
<YYINITIAL> "#" { yybegin(COMENTARIO_LINEA); }
<YYINITIAL> "#*" { yybegin(COMENTARIO_MULTILINEA); }

<COMENTARIO_LINEA> {FIN_LINEA} {yybegin(YYINITIAL); yychar = 1; }
<COMENTARIO_LINEA> {BLANCOS} {}
<COMENTARIO_LINEA> . {}

<COMENTARIO_MULTILINEA> "*#" { yybegin(YYINITIAL); }
<COMENTARIO_MULTILINEA> \n { yychar = 1; }
<COMENTARIO_MULTILINEA> {BLANCOS} {}
<COMENTARIO_MULTILINEA> . {}

<YYINITIAL> "\"" { yybegin(ESTADO_CADENA); cadena.delete(0,cadena.length()); }
<ESTADO_CADENA> "\"" {yybegin(YYINITIAL); return new Symbol(sym.string,yyline,yychar,cadena.toString());}
<ESTADO_CADENA> "\\\"" {cadena.append("\""); }
<ESTADO_CADENA> "\\" {cadena.append("\\"); }
<ESTADO_CADENA> "\\n" {cadena.append("\n"); }
<ESTADO_CADENA> "\\r" {cadena.append("\r"); }
<ESTADO_CADENA> "\\t" {cadena.append("\t"); }
<ESTADO_CADENA> . {cadena.append(yytext()); }

<YYINITIAL> "true" { return new Symbol(sym.valorbooleano, yyline,yychar,yytext()); }
<YYINITIAL> "false" { return new Symbol(sym.valorbooleano, yyline,yychar,yytext()); }

<YYINITIAL> "+" { return new Symbol(sym.mas,yyline,yychar,yytext()); } 
<YYINITIAL> "-" { return new Symbol(sym.menos,yyline,yychar,yytext()); } 
<YYINITIAL> "*" { return new Symbol(sym.multiplicacion,yyline,yychar,yytext()); } 
<YYINITIAL> "/" { return new Symbol(sym.division,yyline,yychar,yytext()); } 
<YYINITIAL> "%" { return new Symbol(sym.modulo,yyline,yychar,yytext()); } 
<YYINITIAL> "^" { return new Symbol(sym.potencia,yyline,yychar,yytext()); } 

<YYINITIAL> "," { return new Symbol(sym.coma,yyline,yychar,yytext()); } 
<YYINITIAL> ";" { return new Symbol(sym.puntoycoma,yyline,yychar,yytext()); }
<YYINITIAL> ":" { return new Symbol(sym.dospuntos,yyline,yychar,yytext()); } 
<YYINITIAL> "{" { return new Symbol(sym.llaveizquierda,yyline,yychar,yytext()); } 
<YYINITIAL> "}" { return new Symbol(sym.llavederecha,yyline,yychar,yytext()); } 
<YYINITIAL> "[" { return new Symbol(sym.corcheteizquierda,yyline,yychar,yytext()); } 
<YYINITIAL> "]" { return new Symbol(sym.corchetederecha,yyline,yychar,yytext()); } 
<YYINITIAL> "(" { return new Symbol(sym.parentesisizquierda,yyline,yychar,yytext()); } 
<YYINITIAL> ")" { return new Symbol(sym.parentesisderecha,yyline,yychar,yytext()); } 

<YYINITIAL> "==" { return new Symbol(sym.igualigual,yyline,yychar,yytext()); } 
<YYINITIAL> "!=" { return new Symbol(sym.diferenteque,yyline,yychar,yytext()); } 
<YYINITIAL> "=" { return new Symbol(sym.igual,yyline,yychar,yytext()); }
<YYINITIAL> "=>" { return new Symbol(sym.flecha,yyline,yychar,yytext()); } 

<YYINITIAL> "<" { return new Symbol(sym.menorque,yyline,yychar,yytext()); } 
<YYINITIAL> ">" { return new Symbol(sym.mayorque,yyline,yychar,yytext()); } 
<YYINITIAL> "<=" { return new Symbol(sym.menorigual,yyline,yychar,yytext()); } 
<YYINITIAL> ">=" { return new Symbol(sym.mayorigual,yyline,yychar,yytext()); }

<YYINITIAL> "or" { return new Symbol(sym.or,yyline,yychar,yytext()); } 
<YYINITIAL> "and" { return new Symbol(sym.and,yyline,yychar,yytext()); } 
<YYINITIAL> "not" { return new Symbol(sym.not,yyline,yychar,yytext()); }

<YYINITIAL> "?" { return new Symbol(sym.ternario,yyline,yychar,yytext()); } 

<YYINITIAL> "if" { return new Symbol(sym._if,yyline,yychar,yytext()); } 
<YYINITIAL> "else" { return new Symbol(sym._else,yyline,yychar,yytext()); } 

<YYINITIAL> "switch" { return new Symbol(sym._switch,yyline,yychar,yytext()); } 
<YYINITIAL> "case" { return new Symbol(sym._case,yyline,yychar,yytext()); }
<YYINITIAL> "default" { return new Symbol(sym._default,yyline,yychar,yytext()); }

<YYINITIAL> "while" { return new Symbol(sym._while,yyline,yychar,yytext()); } 
<YYINITIAL> "do" { return new Symbol(sym._do,yyline,yychar,yytext()); } 

<YYINITIAL> "for" { return new Symbol(sym._for,yyline,yychar,yytext()); } 
<YYINITIAL> "in" { return new Symbol(sym._in,yyline,yychar,yytext()); } 

<YYINITIAL> "break" { return new Symbol(sym._break,yyline,yychar,yytext()); } 
<YYINITIAL> "continue" { return new Symbol(sym._continue,yyline,yychar,yytext()); } 
<YYINITIAL> "return" { return new Symbol(sym._return,yyline,yychar,yytext()); } 

<YYINITIAL> "function" { return new Symbol(sym._funcion,yyline,yychar,yytext()); } 

<YYINITIAL> "print" { return new Symbol(sym._print,yyline,yychar,yytext()); }
<YYINITIAL> "null" { return new Symbol(sym._null,yyline,yychar,yytext()); }

/*
<YYINITIAL> "c" { return new Symbol(sym._c,yyline,yychar,yytext()); }
<YYINITIAL> "list" { return new Symbol(sym._list,yyline,yychar,yytext()); }
<YYINITIAL> "matrix" { return new Symbol(sym._matrix,yyline,yychar,yytext()); }
<YYINITIAL> "array" { return new Symbol(sym._array,yyline,yychar,yytext()); }

<YYINITIAL> "typeof" { return new Symbol(sym._typeof,yyline,yychar,yytext()); }
<YYINITIAL> "length" { return new Symbol(sym._length,yyline,yychar,yytext()); }
<YYINITIAL> "ncol" { return new Symbol(sym._ncol,yyline,yychar,yytext()); }
<YYINITIAL> "nrow" { return new Symbol(sym._nrow,yyline,yychar,yytext()); }
<YYINITIAL> "stringlength" { return new Symbol(sym._stringlength,yyline,yychar,yytext()); }
<YYINITIAL> "remove" { return new Symbol(sym._remove,yyline,yychar,yytext()); }

<YYINITIAL> "tolowercase" { return new Symbol(sym._tolowercase,yyline,yychar,yytext()); }
<YYINITIAL> "touppercase" { return new Symbol(sym._touppercase,yyline,yychar,yytext()); }
<YYINITIAL> "trunk" { return new Symbol(sym._trunk,yyline,yychar,yytext()); }
<YYINITIAL> "round" { return new Symbol(sym._round,yyline,yychar,yytext()); }

<YYINITIAL> "mean" { return new Symbol(sym._mean,yyline,yychar,yytext()); }
<YYINITIAL> "median" { return new Symbol(sym._median,yyline,yychar,yytext()); }
<YYINITIAL> "mode" { return new Symbol(sym._mode,yyline,yychar,yytext()); }

<YYINITIAL> "pie" { return new Symbol(sym._pie,yyline,yychar,yytext()); }
<YYINITIAL> "barplot" { return new Symbol(sym._barplot,yyline,yychar,yytext()); }
<YYINITIAL> "plot" { return new Symbol(sym._plot,yyline,yychar,yytext()); }
<YYINITIAL> "hist" { return new Symbol(sym._hist,yyline,yychar,yytext()); }
*/

<YYINITIAL> {NUMERO_ENTERO} { return new Symbol(sym.entero,yyline,yychar,yytext()); } 
<YYINITIAL> {NUMERO_DECIMAL} { return new Symbol(sym.decimal,yyline,yychar,yytext()); } 
<YYINITIAL> {IDENTIFICADOR}  {return new Symbol(sym._id,yyline,yychar,yytext());}
<YYINITIAL> {ERROR_ID1} { softwarearit.Frame.Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.LEXICO), "Identificador incorrecto "  + "\"" + yytext() + "\"", yyline, yychar)); }
<YYINITIAL> {ERROR_ID2} { softwarearit.Frame.Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.LEXICO), "Identificador incorrecto "  + "\"" + yytext() + "\"", yyline, yychar)); }

. {
    softwarearit.Frame.Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.LEXICO), "Simbolo no conocido " + "\"" + yytext() + "\"", yyline, yychar));
}
