(function ($) {
    let request = $.ajax({'url': '/gallery.json'});
    request.done(function (images) {
        let html = '';
        // console.log(images);
        images.forEach(function (image) {
            html += '<div class="card m-5" style="width: 24rem;">';
            html += '<div class="card-body">';
            html += '<img class="img-thumbnail rounded mx-auto d-block" src=" ' + image.imageUrl + '"' + '/>';
            html += '</div>';
            html += '<a class="btn" data-index-number=${image.id} data-target="#gallery-detailed"' +
                ' data-toggle="modal">View' +
                ' Details</a>'
            html += '</div>';
        });
        $('#images').html(html);
    });
})(jQuery);

(function ($) {
    let request = $.ajax({'url': '/curated-gallery.json'});
    request.done(function (images) {
        let html = '';
        let lengthOfArray = images.length;
        // console.log(images, "curated");
        // for (let i = 0; i < lengthOfArray; i++) {
        images.forEach(function (image) {

            console.log(image[0].imageUrl);

            html += '<div class="card m-5" style="width: 24rem;">';
            html += '<div class="card-body">';
            html += '<img class="img-thumbnail rounded mx-auto d-block" src=" ' + image[0].imageUrl + '"' + '/>';
            html += '</div>';
            html += '<a class="btn" ' +
                ' data-index-number=${image.id} data-target="#gallery-detailed"' +
                ' data-toggle="modal">View' +
                ' Details</a>'
            html += '</div>';
        });
        $('#curated-gallery').html(html);
    });
})(jQuery);

// console.log(images);


(function ($) {
    let request = $.ajax({'url': '/gallery.json'});
    request.done(function (images) {
        let html = '';
        images.forEach(function (image) {
            html += '<div class="m-5" style="width: 24rem;">';
            // html += '<div class="">';
            html += '<img class="img-thumbnail rounded mx-auto d-block" src=" ' + image.imageUrl + '"' + '/>';
            html += '<p class="ml-2">' + image.creditedArtist + '</p>';
            html += '</div>';
            // html += '</div>';
        });
        $('#detailed-work').html(html);
    });
})(jQuery);
