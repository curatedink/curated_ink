
(function ($) {
    var request = $.ajax({'url': '/gallery.json'});
    request.done(function (images) {
        var html = '';
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
    var request = $.ajax({'url': '/gallery.json'});
    request.done(function (images) {
        var html = '';
        images.forEach(function (image) {
            html += '<div class="card m-5" style="width: 24rem;">';
            html += '<div class="card-body">';
            html += '<img class="img-thumbnail rounded mx-auto d-block" src=" ' + image.imageUrl + '"' + '/>';
            html += '</div>';
            html += '<a class="btn" id="detailed-work" data-index-number=${image.id} data-target="#exampleModal"' +
                ' data-toggle="modal">View' +
                ' Details</a>'
            html += '</div>';
        });
        $('#curated-gallery').html(html);
    });
})(jQuery);