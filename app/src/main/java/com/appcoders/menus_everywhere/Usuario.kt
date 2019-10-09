package com.appcoders.menus_everywhere

data class Usuario(var username:String, var email:String) {
    constructor() : this("","");
}