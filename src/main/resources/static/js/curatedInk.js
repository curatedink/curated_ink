(function ($) {
    let request = $.ajax({'url': '/gallery.json'});
    request.done(function (images) {
        let html = '';
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
    let request = $.ajax({'url': '/gallery.json'});
    request.done(function (images) {
        let html = '';
        // console.log(images.length);

        images.forEach(function (image) {
            //logic for curated - if image.user.followers = user.id from list of followers...
            // console.log(image);
            let lengthOfFollowerList = image.user.followerList.length;
            for (let i = 0; i < lengthOfFollowerList; i++){

            console.log(image.user.followerList[0].id, "Follower Id");
            console.log(image.user, "Image.user");
            console.log(image.user.followerList, "Image.user.followerList");
            console.log(image.user.followerList[0], "Image.user.followerList[0]");

            if (image.user.id !== image.user.followerList[i]) {

                html += '<div class="card m-5" style="width: 24rem;">';
                html += '<div class="card-body">';
                html += '<img class="img-thumbnail rounded mx-auto d-block" src=" ' + image.imageUrl + '"' + '/>';
                html += '</div>';
                html += '<a class="btn" ' +
                    ' data-index-number=${image.id} data-target="#gallery-detailed"' +
                    ' data-toggle="modal">View' +
                    ' Details</a>'
                html += '</div>';
            }
            }
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
            html += '<div class="">';
            html += '<img class="img-thumbnail rounded mx-auto d-block" src=" ' + image.imageUrl + '"' + '/>';
            html += '<p class="ml-2">' + image.creditedArtist + '</p>';
            html += '</div>';
            html += '</div>';
        });
        $('#detailed-work').html(html);
    });
})(jQuery);
