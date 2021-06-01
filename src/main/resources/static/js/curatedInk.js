(function ($) {
    let request = $.ajax({'url': '/gallery.json'});
    request.done(function (images) {

        let html = '';
        // console.log(images);
        images.forEach(function (image) {
            let imageUserId = image.user.id;
            html += '<div class="card m-5" style="width: 24rem;">';
            html += '<div class="card-body">';
            html += '<img class="img-thumbnail rounded mx-auto d-block" src=" ' + image.imageUrl + '"' + '/>';
            // html += '<p>' + image.user.id + '</p>'
            html += '</div>';
            // html += <a href={/profile/}
            html += '<a class="btn detailed-modal" data-index-number="image.id" itemID={image.id}' +
                ' data-target="#gallery-detailed"' +
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
        images.forEach(function (image) {
            html += '<div class="card m-5" style="width: 24rem;">';
            html += '<div class="card-body">';
            // html += '<img class="img-thumbnail rounded mx-auto d-block" src=" ' + image[0].imageUrl + '"' + '/>';
            html += '<img class="img-thumbnail rounded mx-auto d-block" src=" ' + image.imageUrl + '"' + '/>';
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


(function ($) {
    let request = $.ajax({'url': '/gallery.json'});
    request.done(function (images) {
        $(".detailed-modal").click(function () {
        let imageId = $(this).attr(itemID);
        let html = '';
        $.each(images, function (key, val) {
            if (val.id == imageId) {
                html += '<div class="m-5" style="width: 24rem;">';
                // html += '<div class="">';
                html += '<img class="img-thumbnail rounded mx-auto d-block" src=" ' + image.imageUrl + '"' + '/>';
                html += '<p class="ml-2">' + image.creditedArtist + '</p>';
                html += '</div>';
                // html += '</div>';
            }
        });
        $('#detailed-work').html(html);
        })
    });
})(jQuery);


(function ($) {
    let request = $.ajax({'url': '/gallery.json'});
    request.done(function (images) {
        $("#txt-search").keyup(function () {
            let searchField = $(this).val();
            if (searchField === '') {
                $('#search-gallery').html('');
                return;
            }
            let regex = new RegExp(searchField, "i");
            let html = '<div class="row">';
            let count = 1;
            $.each(images, function (key, val) {
                if ((val.creditedArtist.search(regex) != -1) || (val.studioName.search(regex) != -1)) {
                    html += '<div class="m-5" style="width: 24rem;">';
                    html += '<div class="">';
                    html += '<img class="img-thumbnail rounded mx-auto d-block" src=" ' + val.imageUrl + '"' + '/>';
                    html += '<p class="ml-2">' + val.creditedArtist + '</p>';
                    html += '</div>';
                    html += '</div>';
                    if (count % 2 == 0) {
                        html += '</div><div class ="row">'
                    }
                    count++;
                }
            });
            html += '</div>';
            $('#search-gallery').html(html);
        });
    })
})(jQuery);
