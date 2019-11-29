package com.itesm.menus_everywhere

//Clase para registrar, construir y almacenar usuarios en la base de datos.

data class Usuario(var username:String, var email:String) {
    constructor() : this("","");
}
