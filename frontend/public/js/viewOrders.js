const orders = [
    {
        id: 1,
        time: '09:30 AM',
        location: 'Jalan Raya No. 123, Jakarta',
        customer: 'John Doe',
        phone: '08123456789',
        orderType: 'rice bowl',
        note: 'Tidak ada catatan tambahan'
    },
    {
        id: 2,
        time: '10:00 AM',
        location: 'Jalan Baru No. 456, Jakarta',
        customer: 'Jane Smith',
        phone: '0876543210',
        orderType: 'ayam',
        note: 'Tambahkan sambal extra'
    },
    {
        id: 3,
        time: '10:30 AM',
        location: 'Jalan Lama No. 789, Jakarta',
        customer: 'Michael Johnson',
        phone: '0811112222',
        orderType: 'nasi goreng',
        note: 'Pengganti jika stok habis'
    },
    // Add more orders here...
];

const ordersContainer = document.getElementById('orders-container');

function generateOrderCard(order) {
    const orderCard = document.createElement('div');
    orderCard.className = 'order-card';

    const orderHeader = document.createElement('div');
    orderHeader.className = 'order-header';

    const orderTitle = document.createElement('div');
    orderTitle.className = 'order-title';
    const orderTitleHeading = document.createElement('h2');
    orderTitleHeading.textContent = `Order #${order.id}`;
    const orderTitleTime = document.createElement('p');
    orderTitleTime.textContent = order.time;
    orderTitle.appendChild(orderTitleHeading);
    orderTitle.appendChild(orderTitleTime);

    const orderLocation = document.createElement('div');
    orderLocation.className = 'order-location';
    const orderLocationAddress = document.createElement('p');
    orderLocationAddress.textContent = order.location;
    orderLocation.appendChild(orderLocationAddress);

    orderHeader.appendChild(orderTitle);
    orderHeader.appendChild(orderLocation);

    const orderDetails = document.createElement('div');
    orderDetails.className = 'order-details';

    const orderInfoCustomer = document.createElement('div');
    orderInfoCustomer.className = 'order-info';
    const orderInfoCustomerLabel = document.createElement('div');
    orderInfoCustomerLabel.className = 'info-label';
    orderInfoCustomerLabel.textContent = 'Nama Pelanggan:';
    const orderInfoCustomerValue = document.createElement('div');
    orderInfoCustomerValue.className = 'info-value';
    orderInfoCustomerValue.textContent = order.customer;
    orderInfoCustomer.appendChild(orderInfoCustomerLabel);
    orderInfoCustomer.appendChild(orderInfoCustomerValue);

    const orderInfoPhone = document.createElement('div');
    orderInfoPhone.className = 'order-info';
    const orderInfoPhoneLabel = document.createElement('div');
    orderInfoPhoneLabel.className = 'info-label';
    orderInfoPhoneLabel.textContent = 'No. Telepon:';
    const orderInfoPhoneValue = document.createElement('div');
    orderInfoPhoneValue.className = 'info-value';
    orderInfoPhoneValue.textContent = order.phone;
    orderInfoPhone.appendChild(orderInfoPhoneLabel);
    orderInfoPhone.appendChild(orderInfoPhoneValue);

    const orderInfoType = document.createElement('div');
    orderInfoType.className = 'order-info';
    const orderInfoTypeLabel = document.createElement('div');
    orderInfoTypeLabel.className = 'info-label';
    orderInfoTypeLabel.textContent = 'Pesanan:';
    const orderInfoTypeValue = document.createElement('div');
    orderInfoTypeValue.className = 'info-value';
    orderInfoTypeValue.textContent = order.orderType;
    orderInfoType.appendChild(orderInfoTypeLabel);
    orderInfoType.appendChild(orderInfoTypeValue);

    const orderInfoNote = document.createElement('div');
    orderInfoNote.className = 'order-info';
    const orderInfoNoteLabel = document.createElement('div');
    orderInfoNoteLabel.className = 'info-label';
    orderInfoNoteLabel.textContent = 'Catatan:';
    const orderInfoNoteValue = document.createElement('div');
    orderInfoNoteValue.className = 'info-value';
    orderInfoNoteValue.textContent = order.note;
    orderInfoNote.appendChild(orderInfoNoteLabel);
    orderInfoNote.appendChild(orderInfoNoteValue);

    const acceptBtn = document.createElement('button');
    acceptBtn.className = 'accept-btn';
    acceptBtn.textContent = 'Terima';

    const rejectBtn = document.createElement('button');
    rejectBtn.className = 'reject-btn';
    rejectBtn.textContent = 'Tolak';

    orderDetails.appendChild(orderInfoCustomer);
    orderDetails.appendChild(orderInfoPhone);
    orderDetails.appendChild(orderInfoType);
    orderDetails.appendChild(orderInfoNote);
    orderDetails.appendChild(acceptBtn);
    orderDetails.appendChild(rejectBtn);

    orderCard.appendChild(orderHeader);
    orderCard.appendChild(orderDetails);

    return orderCard;
}

for (let i = 0; i < orders.length; i++) {
    const orderCard = generateOrderCard(orders[i]);
    ordersContainer.appendChild(orderCard);
}
