package com.appcoders.menus_everywhere

import java.io.Serializable

//Clase que despliega el menú de un restaurante después de escanear y procesar el código QR correspondiente.
// Cada menú tiene distintas categorías, cada categoría tiene distintos productos.

class Restaurante(var restauranteID :RestauranteID)

class RestauranteID (val nombre :String) {

    val menu = Menu();
    val informacion = Informacion();
}

class Menu(){
    var alimentos = mutableListOf<Alimento>()
}
class Alimento(val nombre: String){
    var platillos = mutableListOf<Platillo>()
}
class Platillo(var nombre: String,
               var calorias: String,
               var descripcion: String,
               var precio: String ){

}
class Informacion() {
    var Admin: String = ""
    var Contacto: String = ""
    var Direccion: String = ""
}

