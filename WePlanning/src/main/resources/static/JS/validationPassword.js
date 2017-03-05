function checkForm(form) {
	if (form.pass.value != "" && form.pass.value == form.passwordConfirm.value) {
		if (form.pass.value.length < 6) {
			alert("Error:¡La contraseña debe contener mínimo 6 caracteres!");
			form.pass.focus();
			return false;
		}
		/*
		 * re = /[0-9]/; if(!re.test(form.pass.value)) { alert("Error: password
		 * must contain at least one number (0-9)!"); form.pass.focus(); return
		 * false; }
		 */
		re = /[a-z]/;
		if (!re.test(form.pass.value)) {
			alert("Error:¡La contraseña debe contener al menos una letra en minúscula (a-z)!");
			form.pass.focus();
			return false;
		}
	} else {
		alert("Error al confirmar la contraseña: Por favor verefique que la ha escrito correctamente");
		form.pass.focus();
		return false;
	}
	return true;
}