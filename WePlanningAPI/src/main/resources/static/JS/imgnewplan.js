function readURL(input) {

    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#img-big').attr('src', e.target.result);
        }

        reader.readAsDataURL(input.files[0]);
    }
}

$("#ejemplo_archivo_1").change(function(){
    readURL(this);
});