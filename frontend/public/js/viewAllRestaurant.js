let cartIcon = document.querySelector(".cart");
let cart = document.querySelector(".isicart");
let closeCart = document.querySelector(".tombolclose");

let historyIcon = document.querySelector(".history");
let history = document.querySelector(".isihistory");
let closeHistory = document.querySelector(".tombolclosehistory");

cartIcon.onclick = () => {
    cart.classList.add("active");
};

closeCart.onclick = () => {
    cart.classList.remove("active");
};

historyIcon.onclick = () => {
    history.classList.add("historyactive");
};

closeHistory.onclick = () => {
    history.classList.remove("historyactive");
};

if(document.readyState == 'loading'){
    document.addEventListener('DOMContentLoaded' , ready);
} else{
    ready();
}

function ready(){
    var removeCartButtons = document.getElementsByClassName('itemremove')
    console.log(removeCartButtons);

    for(var i = 0; i < removeCartButtons.length; i++){
        var button = removeCartButtons[i];

        button.addEventListener('click', removeCartItem);
    }
    var quantityInput = document.getElementsByClassName('quantity');
    for (var i = 0; i < quantityInput.length; i++){
        var input = quantityInput[i];
        input.addEventListener("change", quantityChanged);

}

// var addCart = document.getElementsByClassName('tombolBuy')
// for (var i = 0; i < addCart.length; i++){
//     var button = addCart[i];
//     button.addEventListener("click" , addCartClicked )

function removeCartItem(event){
    var buttonClicked = event.target;
    buttonClicked.parentElement.remove();
    updatetotal();
}

function quantityChanged(event){
    var input = event.target;
    if (isNaN(input.value) || input.value <= 0){
        input.value = 1;

}
updatetotal();
}

// function addCartClicked(event){
//     var button = event.target;
//     var shopProducts = button.parentElement;
//     var title = shopProducts.getElementsByClassName('tulisan')[0].innerText;
//     console.log(title);
// }

function updatetotal(){
    var cartContent = document.getElementsByClassName("itemcontent")[0];
    var cartBoxes = cartContent.getElementsByClassName("itemcart");
    var total = 0;
    for(var i = 0; i < cartBoxes.length; i++){
        var cartBox = cartBoxes[i];
        var priceElement = cartBox.getElementsByClassName('itemprize')[0];
        var quantityElement = cartBox.getElementsByClassName('quantity')[0];
        var price = parseFloat(priceElement.innerText.replace("Rp.", "" ));
        var quantity = quantityElement.value;
        total= total + (price * quantity);

        total = Math.round(total *100) / 100;

        document.getElementsByClassName('totalprize')[0].innerText = "Rp." + total;
}

// function removetotal(event){
//     var buttonClicked = event,target;
//     buttonClicked = 


}
}