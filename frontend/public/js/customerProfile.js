getLoginData();
getUserData();

function getLoginData() {
    if (sessionStorage.getItem('login') === null || !sessionStorage.getItem('login')) {
        window.location.replace('/login');
    }
}

function getUserData() {
    const customerID = sessionStorage.getItem('userID');

    $.ajax({
        url: `http://localhost:8080/api/customer/${customerID}`,
        type: 'GET',
        success: (result) => {
            const data = result.payload.customer[0];

            const inputName = document.getElementById('input-name');
            const inputEmail = document.getElementById('input-email');
            const inputPhoneNumber = document.getElementById('input-phone-number');

            inputName.value = data.name;
            inputEmail.value = data.email;
            inputPhoneNumber.value = data.phoneNumber;
        }
    });
}
