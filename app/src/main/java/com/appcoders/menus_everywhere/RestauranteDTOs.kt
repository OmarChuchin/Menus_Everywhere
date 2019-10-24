package com.appcoders.menus_everywhere

class Restaurante(var restauranteID :RestauranteID)
class RestauranteID(var Informacion :Int ,var Menu :Menu, var Nombre :String)
class Menu(var Bebidas :Bebida, var Hamburgesa :Hamburgesa)
class Bebida( var Calorias :Int, var Descripcion :String, var Precio :Float)
class Hamburgesa(var Calorias :Int, var Descripcion :String, var Precio :Float )
