document.addEventListener('DOMContentLoaded', function() {
    populatePage();
}, false)

async function populatePage() {
    const request = new Request('http://localhost:8080/store/products/' + 2);

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

            let reviewsContainer = document.querySelector('.reviews-container');
            data.reviews.forEach(element => {
                reviewItem = document.createElement('div');
                reviewItem.className = 'review-item';

                reviewContent = document.createElement('p');
                reviewContent.className = 'review-content';
                reviewContent.innerHTML = element.content;
                reviewItem.appendChild(reviewContent);

                reviewDate = document.createElement('p');
                reviewDate.className = 'review-date';
                reviewDate.innerHTML = element.date;
                reviewItem.appendChild(reviewDate);

                reviewsContainer.appendChild(reviewItem);
            });
        })
}