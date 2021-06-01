"use strict";

const options = {
    accept: ["image/*"],
    maxFiles: 1,
    onUploadDone:
        function (result) {
            $("#filestackImageUrl").val(result.filesUploaded[0].url);
        }
}
const filestackApiKey = [[${filestackApiKey}]];
const client = filestack.init(filestackApiKey);

$("#uploadImage").click(function () {
    client.picker(options).open()
});