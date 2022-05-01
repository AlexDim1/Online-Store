id = 4;

document.addEventListener('DOMContentLoaded', function() {
    populatePage();
}, false)

function populatePage() {
    const request = new Request('http://localhost:8080/store/products/' + id);

    fetch(request)
        .then(response => response.json())
        .then(data => {
            document.querySelector('title').innerHTML = data.name;
            document.querySelector('.product-photo').setAttribute('src', '../resources/images/products/' + data.id + '.jpg');
            document.querySelector('#price').innerHTML = data.price.toFixed(2) + ' лв.';
            document.querySelector('.product-name').innerHTML = data.name;
            document.querySelector('#author').innerHTML = data.author;
            document.querySelector('#description').innerHTML = data.description;
            document.querySelector('#page-count').innerHTML = data.pageCount;

            data.reviews.forEach(element => {
                reviewItem = document.createElement('div');
                reviewItem.className = 'review-item';

                reviewContent = document.createElement('p');
                reviewContent.className = 'review-content';
                reviewContent.innerHTML = element.content;
                reviewItem.appendChild(reviewContent);

                date = new Date(element.date);
                reviewDate = document.createElement('p');
                reviewDate.className = 'review-date';
                reviewDate.innerHTML = date.toLocaleString();
                reviewItem.appendChild(reviewDate);

                document.querySelector('#reviews-heading').after(reviewItem);
            });
        })
}

function buyProduct() {
    requestBody = {
        buyAmount:document.querySelector('#quantity').value,
        buyerName:document.querySelector('#name-input').value,
        buyerAddress:document.querySelector('#address-input').value
    };

    fetch('http://localhost:8080/store/products/' + id + '/buy', {
        method: 'POST',
        body: JSON.stringify(requestBody),
        headers: {
            'Content-type': 'application/json'
        }
    }).then(response => {
        return response.json();
    }).then(data => {
        createBuyMessage(data);
    })
}

function postReview() {
    requestBody = {
        content: document.querySelector('#new-review-content').value
    };

    fetch('http://localhost:8080/store/products/' + id + '/reviews/add', {
        method: 'POST',
        body: JSON.stringify(requestBody),
        headers: {
            'Content-type': 'application/json'
        }
    }).then(response => {
        location.reload(true);
        return response.json();
    });
}

function createBuyMessage(response) {
    container = document.querySelector('.photo-and-buy-form-container');

    if(document.querySelector('.buy-message-container') !== null) {
        document.querySelector('.buy-message').innerHTML = response;
        return;
    }
        
    buyMessageContainer = document.createElement('div');
    buyMessageContainer.className = 'buy-message-container';

    buyMessage = document.createElement('p');
    buyMessage.className = 'buy-message';
    buyMessage.innerHTML = response;
    buyMessageContainer.appendChild(buyMessage);

    container.after(buyMessageContainer);
}