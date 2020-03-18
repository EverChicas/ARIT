print(3+4)
print(5-5)
print(3*6)
print(7/4)
print(3^45)
print(-9)
print(43>54)
print(34>=6)
print(34<2)
print(4<=34)
print(TRUE == TRUE)
print(false != true)
print(true and false or true)
print(not true)
print(false or (true and not false))
print((43-40)%(3))
print((3+54-543*4543/4)*(434)+(758%4))


.er = (4+3)
print(.er)
d = true;
Var1 = 5;
Var2 = Var1 * 4 - 2

print("Hola "+"mundo!")
print("Hola "+true)
print("Hola "+false)
print("Hola "+4.5)
print("Hola "+10)
print(33-45)
print(84*43)
print(443/0)
print(455/9)
print(4^6)
print(10 %2)
print("hola" == "hola")
print("hola" != "hola")
print("hola" > "hola")
print("hola" >= "hola")
print("hola" < "hola")
print("hola" <= "hola")
print("hola" == 4)
print("hola" != 4)
print("hola" > 4)
print("hola" >= 4)
print("hola" < 4)
print("hola" <= 4)

print(4+6+7+1+3+6+8+1+2+3+4+5+6+7+8+9+10+9+8+7+6+5+4+3+2+1)

print(4<5?"si":"no")


If( 35 < 4) {
print("3 < 4")
} else if (52 < 5){
print("2 < 5")
} else {
print("no se pudo hacer match")
}

If(true){
print(true)
}

If(false){
print(false)
}

If(false){
print(false+" 2")
}

operacion = 3 - (1 + 3) * 32 / 90 # 1.58


var1 = 1
var2 = 2
var3 = 3
var4 = 4
var5 = 5
print(var1)


switch (operacion) {
case 2:
print(operacion)
break
case 1:

break
default:
print("imprimio default")
break
}


var1 = "hola mundo"
if(5 > 3){
switch (10*8) {
case 10:
	print(10)
	break
case 80:
	print(80)
	break
case 90:
	print(90)
	break
default:
var = "estoy dentro de switch en el if verdadero"
print(var)
print(var1)
break
}
}
else{
switch (10*8) {
case 10:
	print(10)
	break
case 80:
	print(80)
	break
case 90:
	print(90)
	break
default:
var = "estoy dentro de switch en el esle"
print(var)
print(var1)
break
}
}

While(false){
Print("hola")
}

boolean = true
contador = 1
while(boolean){
	if(contador > 10){
		boolean = false
		print("llegue a valor mayor de 10: " + contador)
	}else if(contador == 5){
		contador = contador + 1
		continue
		print("imprimio despues de continue")
	}else if(contador == 7){
		break;
	}else{
		print(boolean + " " + contador)
		contador = contador + 1
	}
}
print("Sali del while")
print(contador)


var = true
contador = 0
do{
contador = contador + 1

if(contador == 5){
print("dentro de condicion del break")
break;
}

}while(var)

var = c(3,2,5,8,c(9,10,11))
print(var)
print("##########")
var  = 5
print(var)var = c(3,2,5,8,c(9,10,11))
print(var)
print("##########")
var  = 5
print(var)



Vector1 = c(1,2,true, "HOLA", 4.5);
print(vector1)
print("------------")
# vector1 pasa a ser de tipo string debido a que contiene un elemento de tipo string
# Contenido ["1", "2", "true", "HOLA", "4.5"]
Vector1 = c(Vector1, c(5,6));
print(vector1)
print("------------")
# vector1 se convierte en una lista
# contenido ["1", "2", "true", "HOLA", "4.5", 5, 6]
Vector1 = c(c(4,5), c(7,8));
print(vector1)
print("------------")
# vector1 se convierte en una lista
# contenido [4, 5, 7, 8]
Lista1 = c(1,2,3)
Lista2 = c("hola",4, c(9,8));
Lista3 = c(lista1, lista2);
# Lista3 se convierte en una lista
# contenido [1,2,3, "hola", 4, 9, 8]
Lista4 = c(c(c(1,2,3)),5)
# Lista4 se convierte en una lista
# contenido [(1,2,3), 5]
# Notar que la posición 1 de la lista es un vector
print(vector1)
print(lista1)
print(lista2)
print(lista3)
print(lista4)


A = c(1,2,3,4,5)
b = 4
c = c(c(c(c(c(1,2,3),4),5),6),7)
d = c(1,2,3,4,5)
#print(a)
#print(b)
#print(c)
#print(d)
e = a
a = a + b
f = e + c
g = e + d
#print(a)
#print(b)
#print(c)
#print(d)
#print(e)
print(f)
print(g)

# Creando un vector de tamaño 1
Vector1 = "hola mundo"; # Vector de tipo String
# Creando un vector de tamaño 1 con la función C
Vector2 = c("hola mundo");
# Creando un vector de múltiples valores
Vector3 = c("Casa", "Gato");
# Creando un vector a partir de otro vector
Vector4 = c(Vector1, Vector3); # Contenido del vector vector4 -> ["hola mundo", "Casa", "Gato"]
# Creado un vector a partir de otros vectores de tamaño 1
Perro = "Perro"
Gato = "Gato"
Pez = "Pescado"
Animales = c(Perro, Gato, Pez); # Contenido del vector Animales ->["Perro", "Gato", "Pescado"]
# Creando un vector de tamaño 1
print(vector1)
print(vector2)
print(vector3)
print(vector4)
print(animales)
Vector1 = NULL # Vector de tipo String

A = c(1,2,3,4,5)
b = 4
c = c(c(c(c(c(1,2,3),4),5),6),7)
d = c(1,2,3,4,5)
print("---------------")
print(a)
print(b)
print(c)
print(d)
print("---------------")
e = a + d
f = a - d
g = a * d
h = a / d
i = -a
j = -i
print(e)
print(f)
print(g)
print(h)
print(i)
print(j)


a = c(1,2,3,4,5)
b = a[2]
print(a)
print(b)
a[1] = 5

a = c(1,2,3,4,5)
a[3] = 15
print(a)



# Creando un vector de tamaño 1
Vector1 = "hola mundo"; # Vector de tipo String
print(vector1)
# Creando un vector de tamaño 1 con la función C
Vector2 = c("hola mundo");
print(vector2)
# Creando un vector de múltiples valores
Vector3 = c("Casa", "Gato");
print(vector3)
# Creando un vector a partir de otro vector
Vector4 = c(Vector1, Vector3); # Contenido del vector vector4 -> ["hola mundo", "Casa", "Gato"]
print(vector4)
# Creado un vector a partir de otros vectores de tamaño 1
Perro = "Perro"
Gato = "Gato"
Pez = "Pescado"
Animales = c(Perro, Gato, Pez); # Contenido del vector Animales ->["Perro", "Gato", "Pescado"]
print(animales)
# Creando un vector de tamaño 1
Vector1 = NULL # Vector de tipo String
print(vector1)
print("------------")

# Declarando vectores
Perro = "Perro"
Gato = "Gato"
Animales = c(Perro, Gato) # Contenido del vector animales -> ["Perro", "Gato"]
print(animales)
# Acceso vector
Aux = Animales[1] # Recordar que el índice inicia en 1, contenido del vector Aux -> ["Perro"]
print(aux)
# Creando otro vector
Animales = c(Aux, Animales) # Contenido del vector animales -> ["Perro", "Perro", "Gato"]
print(animales)
# Creando otro vector
Animales = c(Animales[3], Animales) # Contenido del vector animales -> ["Gato", "Perro", "Perro","Gato"]
print(animales)



print("----------")
# Creando un vector
Vector1 = "hola"; # Se crea un nuevo vector de tamaño 1
# Agregando un nuevo valor al vector
print(vector1)
Vector1[4] = "45" # Contenido del vector Vector1 -> ["hola", NULL, NULL, "45"]
print(vector1)
# Reemplazando un valor existente
Vector1[3] = "cambio" # Contenido del vector Vector1 -> ["hola", NULL, "cambio", "45"]
print(vector1)
# Agregando un nuevo valor sin índice
Vector1 = c(Vector1, "nuevo") # Contenido del vector Vector1 -> ["hola", NULL, "cambio", "45","nuevo"]
print(vector1)
# Reemplazando un valor existente
Vector1[3] = c(4,"cambio") # Error, porque estoy tratando de asignar más de un valor a una posición
# del vector
# Reemplazando un valor existente
print(vector1)
Vector1[3] = c("otro valor") # Contenido del vector Vector1 -> ["hola", NULL, "otro valor", "45","nuevo"]
print(vector1)


# SUMA DE VECTORES
# Creando un nuevo vector
A = c(1, 2, 3, 4, 5)
B = c(5, -2, 1, 2, 8)
print(a)
print(b)
# Sumando un numero a un vector
A = A + (5*3-2) # nuevo contenido del vector A -> [14, 15, 16, 17, 18]
print(a)
# Suma entre vectores
A = A + A + A # nuevo contenido del vector A -> [42, 45, 48, 51, 54]
print(a)
# RESTA DE VECTORES
# Creando un vector
A = c(1, 2, 3, 4, 5)
print(a)
# Restando un numero a un vector
A = A - 5 # nuevo contenido del vector A -> [-4, -3, -2, -1, 0]# Resta entre vectores
print(a)
A = A - A - A # nuevo contenido del vector A -> [4, 3, 2, 1, 0]
print(a)
# Operaciones de comparación entre vectores
# Creando un vector
A = c(1, 2, 3, 4, 5)
print(a)
# Verificando si cada elemento del vector es menor a 3
C= A < 3 # nuevo contenido del vector C -> [true, true, false, false, false]
print(c)
# Comparando los elementos de cada vector
C= A < B # nuevo contenido del vector C -> [true, false, false, false, true]
print(c)
# Operaciones lógicas entre vectores
# Creando un vector
D = c(true, false, false, false, false)
print(d)
# Aplicando operación OR
B = A or true # nuevo contenido del vector B -> [true, true, true, true, true]
print(b)
# Comparando los elementos de cada vector
A = B and D # nuevo contenido del vector A -> [true, false, false, false, false]
print(a)


a = c(true,true,true,true,true)
contador = 0

while(a){
print(a)
if(contador == 1){
a = c(1,2,3,4,5)
print(a)
}else if(contador ==2){
a[3] = false
print(a)
}
print(contador)
contador = contador + 1
}
print(A)


Lista1 = list("hola mundo");
# Creando otra lista
Lista2 = list("hola mundo", 43, TRUE, 32.3)
# Combinando listas, el tipo de la variable Lista3 es list
Lista3 = c(Lista1, Lista2) # Contenido de Lista3 -> ["hola mundo", "hola mundo", 43, TRUE, 32.3]
# Creando una lista con la función C, el tipo de la variable Lista4 es list
Lista4 = c(List(1,2,3), 4, "hola mundo") # Contenido de Lista4 -> [1,2,3,4, "hola mundo"]


lista = list(1,2,list(3,4,5),6,7)
print(lista[3])
print(lista[[3]])

# Creando una lista
Lista1 = list(1,2,35,4)
print(lista1)
# Acceso a la lista tipo 1
Lista2 = Lista1[2] # Nos devuelve una nueva lista, pero con un vector de tamaño 1 y un valor de 2
# Contenido de list lista2 = [2]
# Tipo de lista2 -> list
# Acceso a la lista tipo 2
print(lista2)
Num = Lista1[[3]] # Nos devuelve un vector de tamaño 1 y con un valor de 3.
# Contenido del vector Num = [3]
# Tipo de Num = Vector de integer# Creando otra lista
print(num)
Lista3 = list(1,2, 3, 4, c(5,6)) # Contenido de Lista3 = [1,2,3,4, (5,6)]
# Tipo de Lista3 -> list
# Acceso a la lista tipo 1
print(lista3)
Lista4 = Lista3[5] # Nos devuelve una nueva lista, pero con un vector en su interior
# Contenido de list Lista4 = [(5,6)]
# Tipo de Lista4 -> list
# Acceso a la lista tipo 2
print(lista4)
Vector1 = Lista3[[5]] # Nos devuelve un vector
# Contenido de Vector1 = [5,6]
# Tipo de Vector1 -> Vector de integer
# Imprimiendo un valor dentro de la lista
print(vector1)
Print(Lista3[[5]][2]) # imprime 6
# Imprimiendo un valor dentro de la lista
Print(Lista3[5][[1]]) # imprime 5



factorial = (n=3) => {
Return(n)
}

factorial1 = (n=3) => {
Return
}

factorial2 = function(n){
if(n <= 0){
return(result = 1);
}
return(result = n*factorial(n-1));
}

factorial3 = (n) => {
if(n <= 0){
return(result = 1);
}
return(result = n*factorial(n-1));
}

# La función ahora posee un parámetro con un valor por defecto de 3, si en la llamada no agregamos
#el valor del parámetro se tomará el valor de 3.
factorial4 = (n=3) => {
if(n <= 0){
return(result = 1);
}
return(result = n*factorial(n-1));
}
print(factorial(2))
print(factorial1(2))
print(factorial2(2))
print(factorial3(2))
print(factorial4(2))
print(factorial5(2))


saludar = (saludo="Hola ",nombre)=>{
	return (saludo + nombre)
}

print(saludar(default,"Ever"))
print(saludar("Buenas noches ","Ever"))



 ------------------------------------------------------------------
 IMPRIMIENDO ESTA LISTA CON CIERTO MENSAJE
 ------------------------------------------------------------------



 
puntero = 0;

llenarLista = (lista,cd = c(1,2,3,4,5,6,7,8,9)) =>{
	if(lista == null){
	return list(cd)
	}else{
		for(i in cd){
			lista = list(lista,i)
		}
	}
	return (lista)
}

lista = list(1)
lista = llenarlista(lista,default)
print(lista)


#--------------RECURSION ANIDADA---------------------------
ackermann = function(m=0, n=0){
	if (m == 0){
	    return (n + 1);
	}else if (m > 0 and n == 0) {
	    return (ackermann(m - 1, 1));
	}else{
	    return (ackermann(m - 1, ackermann(m, n - 1)));
	}
}

print(ackermann(3,2))

#--------------RECURSION CRUZADA---------------------------
par = (nump) => {
        if (nump == 0) {
            return (true);
        }
        return (impar(nump - 1));
    }

impar = (numi) => {
        if (numi == 0) {
            return (false);
        }
        return (par(numi - 1));
    }

print(par(4))


hofstaderFemenina = (n)=> {
    if (n < 0) {
        return (0);
    } else {
        return ((n == 0) ? 1 : n - hofstaderFemenina(n - 1));
    }
}

hofstaderMasculino = (n) => {
    if (n < 0) {
        return (0);
    } else {
        return (n == 0 ? 0 : n - hofstaderMasculino(n - 1));
    }
}

for(i in c(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19)){
	print(hofstadermasculino(i)+ " ")
}


#---------------RECURSION MULTIPLE---------------------------
fibonacci = function(n) {
   if (n <= 1) {
       return (n);
   }
   return (fibonacci(n - 1) + fibonacci(n - 2));
}

print(fibonacci(19))

#*
Hanoi = function (discos, origen, destino, auxiliar) {
   if (discos == 1) {
       print("Mueva el disco " + discos + " desde la torre " + origen + " hasta la torre " + destino);
   } else {
       Hanoi(discos - 1, origen, auxiliar, destino);
       print("Mueva el disco " + discos + " desde la torre " + origen + " hasta la torre " + destino);
       Hanoi(discos - 1, auxiliar, destino, origen);
   }
}

Hanoi(4,1,3,2)

#---------------RECURSION SIMPLE---------------------------
mcd = (n1, n2) => {
   if (n2 != 0) {
       return (mcd(n2, n1 % n2));
   } else {
       return (n1);
   }
}

print(mcd(1440,408))

#No se que deveria retornar
reverse = (x, length) => {
   if (length == 1) {
       return (x);
   } else {
       b = x % 10;
       x = x / 10;
       return (b * 10 ^ (length - 1) + reverse(x, length -1));
   }
}
print(reverse(5,2))


potencia = function(base, exp) {
   return (exp == 0 ? 1 : (base * potencia(base, exp - 1)));
}

print(potencia(5,3))

factorial = (n) => {
   return (n == 0 ? 1 : n * factorial(n - 1));
}

print(factorial(5))

#------------------PASO DE PARAMETROS POR VALOR---------------------
a = list(5,4,3,"Hola")
print(A)
cambio(A)
print(A)

cambio = function(list){
	print(list)
	list[1] = true
	print(list)
}
#--------------------------------------------------------------------
a = 2
f=()=>{
	if(a > 0){
		a = a -1
		print(A)
		f()
	}else{
		return (a)
	}
}
f()
*#