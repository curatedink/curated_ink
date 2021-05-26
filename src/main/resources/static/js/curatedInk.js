

(function($) {
    var request = $.ajax({'url': '/gallery.json'});
    request.done(function (images) {
        var html = '';
        images.forEach(function(image) {
            html += '<div>';
            html += '<h1>' + image.creditedArtist + '</h1>';
            html += '<p>' + image.studioName + '</p>';
            // html += '<p>Published by ' + ad.owner.username + '</p>';
            html += '</div>';
        });
        $('#images').html(html);
    });
})(jQuery);

(function($) {
    var request = $.ajax({'url': '/gallery.json'});
    request.done(function (images) {
        var html = '';
        images.forEach(function(image) {
            html += '<div>';
            html += '<h1>' + image.imageUrl + '</h1>';
            // html += '<p>' + image.studioName + '</p>';
            // html += '<p>Published by ' + ad.owner.username + '</p>';
            html += '</div>';
        });
        $('#curated-gallery').html(html);
    });
})(jQuery);