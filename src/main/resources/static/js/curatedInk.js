
(function ($) {
    let request = $.ajax({'url': '/gallery.json'});
    request.done(function (images) {
        let html = '';
        images.forEach(function (image) {
            html += '<div class="card m-5" style="width: 24rem;">';
            html += '<div class="card-body">';
            html += '<img class="img-thumbnail rounded mx-auto d-block" src=" ' + image.imageUrl + '"' + '/>';
            html += '</div>';
            html += '<a class="btn" id="detailed-work" data-index-number=${image.id} data-toggle="modal">View' +
                ' Details</a>'
            html += '</div>';
        });
        $('#images').html(html);
    });
})(jQuery);

(function ($) {
    let request = $.ajax({'url': '/gallery.json'});
    request.done(function (images) {
        let html = '';
        images.forEach(function (image) {
            html += '<div class="card m-5" style="width: 24rem;">';
            html += '<div class="card-body">';
            html += '<img class="img-thumbnail rounded mx-auto d-block" src=" ' + image.imageUrl + '"' + '/>';
            html += '</div>';
            html += '<a class="btn" id="detailed-work" class="identifyingClass" ' +
                ' data-index-number=${image.id} data-target="#exampleModal"' +
                ' data-toggle="modal">View' +
                ' Details</a>'
            html += '</div>';
        });
        $('#curated-gallery').html(html);
    });
})(jQuery);

// console.log(images);

function showTattooDetails(images) {
    let html = '<div>';
    html += '<h2>' + image.imageUrl + '</h2>';
    html += '<p class="ml-2">' + image.creditedArtist + '</p>';
    html += '</div>';
    return html;
}

(function ($) {
    let request = $.ajax({'url': '/gallery.json'});
    request.done(function (images) {
        let html = '';
        images.forEach(function (image) {
            html += '<div class="card m-5" style="width: 24rem;">';
            html += '<div class="card-body">';
            html += '<img class="img-thumbnail rounded mx-auto d-block" src=" ' + image.imageUrl + '"' + '/>';
            html += '</div>';
            html += '</div>';
        });
        $('#detailed-work').on('click', function() {
            $(this).html(html);
        });
        // $('#detailed-work').on(html);
    });
})(jQuery);
