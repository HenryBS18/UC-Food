const submitButton = document.getElementById('submit-button');
const inputMenuImage = document.getElementById('formFile');
const menuImage = document.getElementById('menu-image');

inputMenuImage.addEventListener('change', () => {
    if (inputMenuImage.files && inputMenuImage.files[0]) {
        const reader = new FileReader();
        reader.onload = function (e) {
            menuImage.src = e.target.result;
        };
        reader.readAsDataURL(inputMenuImage.files[0]);
    }
});

submitButton.addEventListener('click', () => {
    const restaurantID = sessionStorage.getItem('userID');
    const menuName = document.getElementById('input-name').value;
    const price = document.getElementById('input-price').value;
    const stock = document.getElementById('input-stock').value;
    const menuImage = inputMenuImage.files[0];

    const formData = new FormData();
    formData.append('menuName', menuName);
    formData.append('price', price);
    formData.append('stock', stock);
    formData.append('menuImage', menuImage);

    if (menuName !== null && price !== null && stock !== null && menuImage !== null) {
        $.ajax({
            url: `http://localhost:8080/api/restaurant/${restaurantID}/menu`,
            type: 'POST',
            dataType: 'json',
            data: formData,
            processData: false,
            contentType: false,
            success: (result) => {
                if (result.status) {
                    window.location.replace('/restaurant');
                }
            },
            error: (err) => {
                console.log(JSON.parse(err.responseText));
            }
        });
    }
});
