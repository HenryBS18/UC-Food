const userType = sessionStorage.getItem('userType');

if (userType === 'customer') {
    window.location.replace('/home');
} else if (userType === 'restaurant') {
    window.location.replace('/restaurant');
} else {
    window.location.replace('/login');
}