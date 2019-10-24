package com.appcoders.menus_everywhere

//Clase que despliega el menú de un restaurante después de escanear y procesar el código QR correspondiente.
// Cada menú tiene distintas categorías, cada categoría tiene distintos productos.

class Restaurante(var restauranteID :RestauranteID)
class RestauranteID(var informacion :String ,var menu :Menu, var nombre :String){
    constructor() : this("",Menu(),"")
}

class Menu(var bebidas :Bebida, var hamburgesas :Hamburgesa){
    constructor() : this(Bebida(), Hamburgesa())
}
class Bebida( var calorias :Int, var descripcion :String, var precio :Double){
    constructor() : this(0,"",0.0)
}
class Hamburgesa(var calorias :Int, var descripcion :String, var precio :Double ){
    constructor() : this(0,"",0.0)
}
