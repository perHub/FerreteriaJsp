/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function check() {

//    var DniReg = '/^\d+$/';
    var DniReg = new RegExp('^[0-9]+$');
    var cdni = f1.dni;
    if (cdni) {
        var dni = f1.dni.value;
        var res = DniReg.test(dni);
    }


    if (f1.username.value === "") {
        alert("Por favor complete el nombre de usuario.");
        exit();
    }
    if (f1.password.value.length < 4) {
        alert("El password no puede contener menos de 4 caracteres.");
        exit();
    }
    if (f1.nombre.value === "") {
        alert("Por favor introduzca el nombre.");
        exit();
    }
    if (f1.apellido.value === "") {
        alert("Por favor introduzca el apellido.");
        exit();
    }
    if (dni && !res) {
        alert("Introduzca valores numéricos para el DNI.");
        exit();
    }
    f1.submit();
}