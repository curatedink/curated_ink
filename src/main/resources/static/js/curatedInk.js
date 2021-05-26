

// function getImagesJSON(){
//     var images = null;
//
//     $.ajax({
//         url: "/gallery.json",
//         method: "GET",
//         async: false
//     }).done(function(data){
//         images = data;
//     });
//
//     return images;
// }

// const URL = "/gallery.json"
// const getImages = fetch(URL)
//     .then(response => response.json())
//     .catch(console.error);

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