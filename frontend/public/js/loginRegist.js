getLoginData();

function getLoginData() {
  if (sessionStorage.getItem('login') !== null || sessionStorage.getItem('login')) {
    const userType = sessionStorage.getItem('userType');

    if (userType === 'customer') {
      window.location.replace('/home');
    } else if (userType === 'restaurant') {
      window.location.replace('/restaurant');
    } else {
      window.location.replace('/login');
    }
  }
}

const sign_in_btn = document.querySelector("#sign-in-btn");
const sign_up_btn = document.querySelector("#sign-up-btn");
const container = document.querySelector(".container");

sign_up_btn.addEventListener("click", () => {
  container.classList.add("sign-up-mode");

  const userType = document.getElementById('select-type-register');
  const restaurantNameRegister = document.querySelector('.restaurant-name');
  const restaurantDescriptionRegister = document.querySelector('.restaurant-description');
  const restaurantProfilePictureRegister = document.querySelector('.restaurant-profile-picture');

  let userTypeSelect = userType.value;

  if (userTypeSelect == 'customer') {
    restaurantNameRegister.style.display = 'none';
    restaurantDescriptionRegister.style.display = 'none';
    restaurantProfilePictureRegister.style.display = 'none';
  }

  userType.addEventListener('change', () => {
    userTypeSelect = userType.value;

    if (userTypeSelect == 'restaurant') {
      restaurantNameRegister.removeAttribute('style');
      restaurantDescriptionRegister.removeAttribute('style');
      restaurantProfilePictureRegister.removeAttribute('style');
    } else {
      restaurantNameRegister.style.display = 'none';
      restaurantDescriptionRegister.style.display = 'none';
      restaurantProfilePictureRegister.style.display = 'none';
    }
  });
});

sign_in_btn.addEventListener("click", () => {
  container.classList.remove("sign-up-mode");
});

const submitRegisterButton = document.getElementById('register-button');
const submitLoginButton = document.getElementById('login-button');

submitRegisterButton.addEventListener("click", () => {
  submitRegister();
});

submitLoginButton.addEventListener('click', () => {
  submitLogin();
});

function submitLogin() {
  const selectOptions = document.getElementById('select-type-login');
  let selectType = selectOptions.value;

  selectOptions.addEventListener('change', () => {
    selectType = selectOptions.value;
  });

  const email = document.getElementById('emailLogin');
  const password = document.getElementById('passwordLogin')

  if (!validator.isEmail(email.value)) {
    email.setCustomValidity('Please enter a valid email.');
  } else if (validator.isEmpty(password.value)) {
    password.setCustomValidity('Please enter a password.');
  } else {
    email.setCustomValidity('');
    password.setCustomValidity('');
  }

  $.ajax({
    url: `http://localhost:8080/api/login/${selectType}`,
    type: 'POST',
    dataType: 'json',
    data: {
      email: email.value,
      password: password.value
    },
    success: (result) => {
      console.log(result.messages[0]);

      if (result.status) {
        sessionStorage.setItem('userType', selectType);
        sessionStorage.setItem('login', result.payload.login);

        if (selectType === 'customer') {
          sessionStorage.setItem('userID', result.payload.customer[0].customerID);
          // window.location.replace('/home');
        } else {
          sessionStorage.setItem('userID', result.payload.restaurant[0].restaurantID);
          // window.location.replace('/restaurant');
        }
      }
    },
    error: (err) => {
      const error = JSON.parse(err.responseText);
      console.log(error);
    }
  });
}

function submitRegister() {
  const userType = document.getElementById('select-type-register');
  const name = document.getElementById('nameRegister');
  const email = document.getElementById('emailRegister');
  const phoneNumber = document.getElementById('phoneNumberRegister');
  const password = document.getElementById('passwordRegister');

  if (!validator.isEmail(email.value)) {
    email.setCustomValidity('Please enter a valid email');
  } else if (!validator.isMobilePhone(phoneNumber.value, 'id-ID')) {
    phoneNumber.setCustomValidity('Please enter a valid phone number');
  } else {
    email.setCustomValidity('');
    phoneNumber.setCustomValidity('');
  }

  let selectType = userType.value;

  userType.addEventListener('change', () => {
    selectType = userType.value;
  });

  if (selectType === 'customer') {
    $.ajax({
      url: `http://localhost:8080/api/register/customer`,
      type: 'POST',
      dataType: 'json',
      data: {
        name: name.value,
        email: email.value,
        phoneNumber: phoneNumber.value,
        password: password.value
      },
      success: (result) => {
        if (result.status) {
          sessionStorage.setItem('userType', 'customer');
          sessionStorage.setItem('login', true);
          sessionStorage.setItem('userID', result.payload.customer[0].customerID);

          window.location.replace('/home');
        }
      },
      error: (err) => {
        console.log(JSON.parse(err.responseText));
      }
    });
  } else {
    const restaurantName = document.getElementById('restaurantNameRegister').value;
    const restaurantDescription = document.getElementById('restaurantDescriptionRegister').value;
    const restaurantProfilePicture = document.getElementById('restaurantProfilePictureRegister').files[0];

    const formData = new FormData();
    formData.append('ownerName', name);
    formData.append('email', email);
    formData.append('phoneNumber', phoneNumber);
    formData.append('password', password);
    formData.append('restaurantName', restaurantName);
    formData.append('restaurantDescription', restaurantDescription);
    formData.append('restaurantProfilePicture', restaurantProfilePicture);

    $.ajax({
      url: 'http://localhost:8080/api/register/restaurant',
      type: 'POST',
      dataType: 'json',
      data: formData,
      processData: false,
      contentType: false,
      success: (result) => {
        if (result.status) {
          sessionStorage.setItem('userType', 'restaurant');
          sessionStorage.setItem('login', true);
          sessionStorage.setItem('userID', result.payload.restaurant[0].restaurantID);

          window.location.replace('/restaurant');
        }
      },
      error: (err) => {
        console.log(JSON.parse(err.responseText));
      }
    });
  }
}

const form = document.getElementById('login-button');
const emailInput = document.getElementById('emailLogin');
const passwordInput = document.getElementById('passwordLogin');

form.addEventListener('click', function (event) {

});

function validateForm() {
  // Check if the fields are empty
  if (emailInput.value.trim() === '' || passwordInput.value.trim() === '') {
    return false; // Form is not valid
  }

  return true; // Form is valid
}