document.addEventListener('DOMContentLoaded', populatePage, false);

function populatePage() {
    fetch('http://localhost:8080/admin/data')
        .then(response => {
            return response.json();
        }).then(data => {
            data.products.sort((a, b) => {
                return a.id - b.id;
            });
            populateTableProducts(data.products);
            populateTableOrders(data.orders);
        })
}

function populateTableProducts(products) {
    const tableProducts = document.querySelector('#products-body');

    products.forEach(product => {
        row = document.createElement('tr');
        row.className = 'product-row';
        row.setAttribute('id', product.id);

        for (i = 1; i <= 7; i++) {
            td = document.createElement('td');
            td.className = i < 6? 'table-info': 'product-button';

            switch (i) {
                case 1:
                    td.innerHTML = product.id;
                    break;
                case 2:
                    td.innerHTML = product.name;
                    break;
                case 3:
                    td.innerHTML = product.author;
                    break;
                case 4:
                    td.innerHTML = product.price.toFixed(2) + ' лв.';
                    td.setAttribute('id', 'price');
                    break;
                case 5:
                    td.innerHTML = product.timesBought;
                    break;
                case 6:
                    button = document.createElement('button');
                    button.className = 'table-button';
                    button.addEventListener('click', () => {
                        editProduct(product.id)
                    }, false);
                    button.innerHTML = 'Редактирай';
                    td.appendChild(button);
                    break;
                case 7:
                    button = document.createElement('button');
                    button.className = 'table-button';
                    button.addEventListener('click', removeProduct, false);
                    button.innerHTML = 'Изтрий';
                    td.appendChild(button);
                    break;
            }

            row.appendChild(td);
        }

        tableProducts.appendChild(row);
    })
}

function populateTableOrders(orders) {
    const tableOrders = document.querySelector('#orders-body');

    orders.forEach(order => {
        row = document.createElement('tr');
        row.className = 'order-row';
        row.setAttribute('id', order.id);

        for (i = 1; i <= 7; i++) {
            td = document.createElement('td');
            td.className = 'table-info';

            switch (i) {
                case 1:
                    td.innerHTML = order.id;
                    break;
                case 2:
                    td.innerHTML = order.productName;
                    break;
                case 3:
                    td.innerHTML = order.buyerName;
                    break;
                case 4:
                    td.innerHTML = order.buyerAddress;
                    break;
                case 5:
                    date = new Date(order.date)
                    td.innerHTML = date.toLocaleString();
                    td.setAttribute('id', 'date');
                    break;
                case 6:
                    td.innerHTML = order.buyAmount;
                    break;
                case 7:
                    td.innerHTML = order.totalPrice.toFixed(2) + ' лв.';
                    td.setAttribute('id', 'price');
                    break;
            }

            row.appendChild(td);
        }

        tableOrders.appendChild(row);
    })
}

function showTab(event, tab) {
    tabcontent = document.querySelectorAll('.tab-content');
    for (i = 0; i < tabcontent.length; i++)
        tabcontent[i].style.display = 'none';

    tablinks = document.querySelectorAll('.tab-link');
    for (i = 0; i < tablinks.length; i++)
        tablinks[i].className = tablinks[i].className.replace(' active', '');

    document.querySelector('#' + tab).style.display = "block";
    event.currentTarget.className += ' active';
}

function editProduct(id) {
    fetch('http://localhost:8080/admin/' + id)
        .then(response => {
            return response.json();
        }).then(product => {
            // TODO: fill form with info
        })
}

function removeProduct() {
    
}