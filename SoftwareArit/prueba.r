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
# Contenido [“1”, “2”, “true”, “HOLA”, “4.5”]
Vector1 = c(Vector1, c(5,6));
print(vector1)
print("------------")
# vector1 se convierte en una lista
# contenido [“1”, “2”, “true”, “HOLA”, “4.5”, 5, 6]
Vector1 = c(c(4,5), c(7,8));
print(vector1)
print("------------")
# vector1 se convierte en una lista
# contenido [4, 5, 7, 8]
Lista1 = c(1,2,3)
Lista2 = c("hola",4, c(9,8));
Lista3 = c(lista1, lista2);
# Lista3 se convierte en una lista
# contenido [1,2,3, “hola”, 4, 9, 8]
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
Vector4 = c(Vector1, Vector3); # Contenido del vector vector4 -> [“hola mundo”, “Casa”, “Gato”]
# Creado un vector a partir de otros vectores de tamaño 1
Perro = "Perro"
Gato = "Gato"
Pez = "Pescado"
Animales = c(Perro, Gato, Pez); # Contenido del vector Animales ->[“Perro”, “Gato”, “Pescado”]
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