let img = document.getElementById('id_img');
let count = 1

function carosel(){
    img.src = './img/image-'+count+'.jpg';
    //console.log("Ciao"+ count);

    count++;

    if(count > 3){
        count = 1;
    }
}

setInterval(carosel, 6000)