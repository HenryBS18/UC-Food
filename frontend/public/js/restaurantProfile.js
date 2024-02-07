getLoginData();
getUserData();

function getLoginData() {
    if (sessionStorage.getItem('login') === null || !sessionStorage.getItem('login')) {
        window.location.replace('/login');
    }
}

function getUserData() {
    const restaurantID = sessionStorage.getItem('userID');

    $.ajax({
        url: `http://localhost:8080/api/restaurant/${restaurantID}`,
        type: 'GET',
        success: (result) => {
            const data = result.payload.restaurant[0];

            const inputName = document.getElementById('input-name');
            const inputEmail = document.getElementById('input-email');
            const inputPhoneNumber = document.getElementById('input-phone-number');
            const inputRestaurantName = document.getElementById('input-restaurant-name');
            const inputRestaurantDescription = document.getElementById('input-restaurant-description');
            const restaurantProfilePicture = document.getElementById('profile-picture');

            $.ajax({
                url: `http://localhost:8080/api/restaurant/${restaurantID}/profilepicture`,
                type: 'GET',
                xhrFields: {
                    responseType: 'blob'
                },
                success: (result) => {
                    const image = URL.createObjectURL(result);
                    console.log(result);

                    inputName.value = data.ownerName;
                    inputEmail.value = data.email;
                    inputPhoneNumber.value = data.phoneNumber;
                    inputRestaurantName.value = data.restaurantName;
                    inputRestaurantDescription.value = data.restaurantDescription;
                    restaurantProfilePicture.src = image;
                }
            });
        }
    });
}

const saveChangesButton = document.getElementById('save-changes-button');

saveChangesButton.addEventListener('click', () => {
    const restaurantProfilePicture = document.getElementById('profile-picture').src;
    const restaurantID = sessionStorage.getItem('userID');
    const inputName = document.getElementById('input-name');
    const inputEmail = document.getElementById('input-email');
    const inputPhoneNumber = document.getElementById('input-phone-number');
    const inputRestaurantName = document.getElementById('input-restaurant-name');
    const inputRestaurantDescription = document.getElementById('input-restaurant-description');

    if (restaurantProfilePicture === '' || !restaurantProfilePicture) {
        $.ajax({
            url: `http://localhost:8080/api/restaurant/${restaurantID}/profilepicture`,
            type: 'GET',
            xhrFields: {
                responseType: 'blob'
            },
            success: (result) => {
                const image = URL.createObjectURL(result);

                const formData = new FormData();
                formData.append('ownerName', inputName);
                formData.append('email', inputEmail);
                formData.append('phoneNumber', inputPhoneNumber);
                formData.append('restaurantName', inputRestaurantName);
                formData.append('restaurantDescription', inputRestaurantDescription);
                formData.append('restaurantProfilePicture', image);

                $.ajax({
                    url: `http://localhost:8080/api/restaurant/${restaurantID}/profile`,
                    type: 'PUT',
                    dataType: 'json',
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: (result) => {
                        if (result.status) {
                            console.log('success');
                            window.location.replace('/restaurant');
                        }
                    },
                    error: (err) => {
                        console.log(JSON.parse(err.responseText));
                    }
                });
            }
        });
    } else {
        const inputProfilePicture = document.getElementById('formFile').files[0];
        const formData = new FormData();
        formData.append('ownerName', inputName);
        formData.append('email', inputEmail);
        formData.append('phoneNumber', inputPhoneNumber);
        formData.append('restaurantName', inputRestaurantName);
        formData.append('restaurantDescription', inputRestaurantDescription);
        formData.append('restaurantProfilePicture', inputProfilePicture);

        $.ajax({
            url: `http://localhost:8080/api/restaurant/${restaurantID}/profile`,
            type: 'PUT',
            dataType: 'json',
            data: formData,
            processData: false,
            contentType: false,
            success: (result) => {
                if (result.status) {
                    console.log('success');
                    window.location.replace('/restaurant');
                }
            },
            error: (err) => {
                console.log(JSON.parse(err.responseText));
            }
        });
    }
});