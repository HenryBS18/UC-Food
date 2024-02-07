const login = sessionStorage.getItem('login');

if (!login) {
    window.location.replace('/login');
}

const historyIcon = document.querySelector(".history");
const history = document.querySelector(".history-item");
const closeHistory = document.querySelector(".history-item-header .close-button");

historyIcon.addEventListener('click', () => {
    history.classList.add("historyactive");
});

closeHistory.addEventListener('click', () => {
    history.classList.remove("historyactive");
});

const restaurantID = sessionStorage.getItem('userID');

$.ajax({
    url: `http://localhost:8080/api/restaurant/${restaurantID}/menu/all`,
    type: 'GET',
    dataType: 'json',
    success: (result) => {
        const menus = result.payload.menu;
        const totalMenu = menus.length;

        const totalMenuText = document.getElementById('totalMenuText');
        totalMenuText.innerHTML = `
            <p>Total Menu</p>
            <p>${totalMenu}</p>
        `

        const menuItems = document.querySelector('.menu-items');

        menus.forEach(menu => {
            const menuID = menu.menuID;
            $.ajax({
                url: `http://localhost:8080/api/restaurant/${restaurantID}/menu/${menuID}/image`,
                type: 'GET',
                xhrFields: {
                    responseType: 'blob'
                },
                success: (result) => {
                    const image = URL.createObjectURL(result);
                    const menuName = menu.menuName;
                    const price = menu.price;
                    const stock = menu.stock;

                    const menuItem = document.createElement('a');
                    menuItem.classList.add("menu-item");
                    menuItem.href = '/restaurant/menu/edit';

                    menuItem.innerHTML = `
                        <img src="${image}">
                        <p>${menuName}</p>
            
                        <div class="menu-item-detail">
                            <div class="menu-price">
                                <p>Price : </p>
                                <p>Rp ${price.toLocaleString()}</p>
                            </div>
                
                            <div class="menu-stock">
                                <p>Stock :</p>
                                <p>${stock.toLocaleString()}</p>
                            </div>
                        </div>
                    `;
                    menuItems.appendChild(menuItem);
                }
            });
        });
    },
    error: (err) => {
        console.log(err);
        console.log(JSON.parse(err.responseText));
    }
});

