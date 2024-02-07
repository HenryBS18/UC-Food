const express = require('express');
const app = express();
const port = 3000;

app.use(express.static('public'));
app.use(express.json());

app.get('/', (req, res) => {
    res.sendFile('./views/index.html', { root: __dirname })
});

app.get('/login', (req, res) => {
    res.sendFile('./views/loginRegist.html', { root: __dirname });
});


// Customer Controller
app.get('/home', (req, res) => {
    res.sendFile('./views/customerDashboard.html', { root: __dirname });
});

app.get('/profile', (req, res) => {
    res.sendFile('./views/customerProfile.html', { root: __dirname });
});


// Restaurant Controller
app.get('/restaurant', (req, res) => {
    res.sendFile('./views/restaurantDashboard.html', { root: __dirname });
});

app.get('/restaurant/profile', (req, res) => {
    res.sendFile('./views/restaurantProfile.html', { root: __dirname });
});

app.get('/restaurant/menu/add', (req, res) => {
    res.sendFile('./views/addMenu.html', { root: __dirname });
});

app.get('/restaurant/menu/edit', (req, res) => {
    res.sendFile('./views/editMenu.html', { root: __dirname });
});

app.get('/restaurants', (req, res) => {
    res.sendFile('./views/viewAllRestaurant.html', { root: __dirname });
});

app.get('/restaurants/detail', (req, res) => {
    res.sendFile('./views/restaurantDetails.html', { root: __dirname });
});

app.get('/restaurant/orders', (req, res) => {
    res.sendFile('./views/viewOrders.html', { root: __dirname });
});

app.listen(port);
console.log(`Starting server: http://localhost:${port}/`);