getLoginData();
renderRestaurants();
renderCartItems();

let cartIcon = document.querySelector('.cart');
let cart = document.querySelector('.cart-item');
let closeCart = document.querySelector('.cart-item-header .close-button');

let historyIcon = document.querySelector('.history');
let history = document.querySelector('.history-item');
let closeHistory = document.querySelector('.history-item-header .close-button');

cartIcon.onclick = () => {
    cart.classList.add('active');
};

closeCart.onclick = () => {
    cart.classList.remove('active');
};

historyIcon.onclick = () => {
    history.classList.add('historyactive');
};

closeHistory.onclick = () => {
    history.classList.remove('historyactive');
};

function getLoginData() {
    if (sessionStorage.getItem('login') === null || !sessionStorage.getItem('login')) {
        window.location.replace('/login');
    }
}

function renderRestaurants() {
    $.ajax({
        url: 'http://localhost:8080/api/restaurant/all',
        type: 'GET',
        dataType: 'json',
        success: (result) => {
            const restaurantList = result.payload.restaurant;
            const restaurant = document.querySelector('.restaurant');

            restaurantList.forEach((restaurantListItem) => {
                const restaurantID = restaurantListItem.restaurantID;

                const restaurantItem = document.createElement('div');
                restaurantItem.classList.add('restaurant-item');

                restaurant.appendChild(restaurantItem);

                renderRestaurantProfilePicture(restaurantID).then(profilePicture => {
                    const restaurantCard = document.createElement('div');
                    restaurantCard.classList.add('restaurant-card');

                    restaurantCard.innerHTML = `
                        <img src="${profilePicture}" />
    
                        <div class="restaurant-card-item">
                            <div class="view-button">
                                <button id="view-restaurant-button" data-id="${restaurantID}">View</button>
                            </div>
    
                            <div class="restaurant-card-item-detail">
                                <h1>${restaurantListItem.restaurantName}</h1>

                                <div class="restaurant-rating">
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <h3>4.5</h3>
                                </div>
                                
                                <div class="restaurant-location-text">
                                    <i class="fas fa-map-marker-alt"></i>
                                    <p>Universitas Ciputra Makassar</p>
                                </div>
                            </div>
                        </div>
                    `;
                    restaurantItem.appendChild(restaurantCard);

                    const restaurantMenus = document.createElement('div');
                    restaurantMenus.classList.add('restaurant-menus');

                    restaurantItem.appendChild(restaurantMenus);

                    renderMenu(restaurantID).then(response => {
                        response.forEach(menu => {
                            const restaurantMenusItem = document.createElement('div');
                            restaurantMenusItem.classList.add('restaurant-menus-item');

                            renderMenuImage(restaurantID, menu.menuID).then(menuImage => {
                                restaurantMenusItem.innerHTML = `
                                    <img src="${menuImage}" class="restaurant-menus-item-image" />
                        
                                    <div class="restaurant-menus-item-detail">
                                        <p>${menu.menuName}</p>
                            
                                        <div class="menu-rating">
                                            <span class="fa fa-star checked"></span>
                                            <h3>4</h3>
                                        </div>
                            
                                        <button class="add-button" id="buy-button" data-id="${menu.menuID}">Add</button>
                                    </div>
    
                                    <div class="restaurant-card-item-footer">
                                        <div class="menu-price">
                                            <p>Price</p>
                                            <p>Rp ${menu.price.toLocaleString()}</p>
                                        </div>
        
                                        <div class="garis"></div>
        
                                        <div class="menu-price">
                                            <p>Stock</p>
                                            <p>${menu.stock}</p>
                                        </div>
                                    </div>
                                `;
                                restaurantMenus.appendChild(restaurantMenusItem);
                            });
                        });
                    });
                });
            });
        }
    });
}

function renderRestaurantProfilePicture(restaurantID) {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: `http://localhost:8080/api/restaurant/${restaurantID}/profilepicture`,
            type: 'GET',
            xhrFields: {
                responseType: 'blob'
            },
            success: (result) => {
                const profilePicture = URL.createObjectURL(result);
                resolve(profilePicture);
            }
        });
    });
}

function renderMenu(restaurantID) {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: `http://localhost:8080/api/restaurant/${restaurantID}/menu/all`,
            type: 'GET',
            dataType: 'json',
            success: (result) => {
                if (result.status) {
                    const menu = result.payload.menu;
                    resolve(menu);
                }
            }
        });
    });
}

function renderMenuImage(restaurantID, menuID) {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: `http://localhost:8080/api/restaurant/${restaurantID}/menu/${menuID}/image`,
            type: 'GET',
            xhrFields: {
                responseType: 'blob'
            },
            success: (result) => {
                const image = URL.createObjectURL(result);
                resolve(image);
            }
        });
    });
}

function renderCartItems() {
    const customerID = sessionStorage.getItem('userID');

    $.ajax({
        url: `http://localhost:8080/api/customer/${customerID}/cart/all`,
        type: 'GET',
        success: (result) => {
            const cartItemBody = document.querySelector('.cart-item-body');
            const customerCartItems = result.payload.customerCart;
            let totalPrice = 0;

            cartItemBody.innerHTML = ``;

            customerCartItems.forEach((customerCartItem) => {
                const cartItems = document.createElement('div');
                cartItems.classList.add('cart-items');

                const customerCartItemID = customerCartItem.customerCartItemID;
                const menuID = customerCartItem.menuID;
                const menuName = customerCartItem.menuName;
                const price = customerCartItem.price;
                const itemQuantity = customerCartItem.itemQuantity;

                renderMenuImage(10, menuID).then(menuImage => {
                    cartItems.innerHTML = `
                        <img src="${menuImage}" class="cart-items-image" />

                        <div class="cart-items-detail">
                            <p>${menuName}</p>
                            <p>Rp ${price.toLocaleString()}</p>
                            <input type="number" value="${itemQuantity}" min="1" max="10" class="quantity" />
                        </div>
        
                        <img src="images/Icons/icon-trash.svg" id="delete-button" class="remove-button" data-id="${customerCartItemID}" />
                    `;
                });
                cartItemBody.appendChild(cartItems);
                totalPrice += (price * itemQuantity);
            });

            const cartItemFooter = document.querySelector('.cart-item-footer');

            cartItemFooter.innerHTML = `
                <p>Total</p>
                <p>Rp ${totalPrice.toLocaleString()}</p>
            `;
        }
    });
}

//
const restaurant = document.querySelector('.restaurant');

restaurant.addEventListener('click', (e) => {
    console.log(e.target.id);
    if (e.target.id === 'view-restaurant-button') {
        let restaurantID = e.target.getAttribute('data-id');

        console.log(restaurantID);
    }

    if (e.target.id === 'buy-button') {
        let menuID = e.target.getAttribute('data-id');
        const customerID = sessionStorage.getItem('userID');

        $.ajax({
            url: `http://localhost:8080/api/customer/${customerID}/cart/item/${menuID}`,
            type: 'GET',
            success: (result) => {
                let itemQuantity = result.payload.itemQuantity + 1;

                if (result.status) {
                    $.ajax({
                        url: `http://localhost:8080/api/customer/${customerID}/cart`,
                        type: 'PUT',
                        data: {
                            menuID,
                            itemQuantity
                        },
                        success: (result) => {
                            console.log(result);
                            renderCartItems();
                        }
                    });
                }
            },
            error: (err) => {
                console.log(JSON.parse(err.responseText));
                $.ajax({
                    url: `http://localhost:8080/api/customer/${customerID}/cart/add`,
                    type: 'POST',
                    data: {
                        menuID,
                        itemQuantity: 1
                    },
                    success: (result) => {
                        console.log(result);
                        renderCartItems();
                    }
                });
            }
        });
    }
});

const cartItemBody = document.querySelector('.cart-item-body');

cartItemBody.addEventListener('click', (e) => {
    if (e.target.id === 'delete-button') {
        let itemID = e.target.getAttribute('data-id');
        const customerID = sessionStorage.getItem('userID');

        $.ajax({
            url: `http://localhost:8080/api/customer/${customerID}/cart/item/${itemID}/delete`,
            type: 'DELETE',
            success: (result) => {
                console.log(result);
                renderCartItems();
            }
        });
    }
})