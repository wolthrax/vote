$(document).ready(function () {
    $("#remove-vote-form").submit(function (event) {

        var parts = location.pathname.match(/([a-z0-9_-]+)/ig);
        var id = parts[3];
        var pwd = parts[4];

        //stop submit the form, we will post it manually.
        event.preventDefault();

        var conf = confirm("Вы действительно хотите удалить голосование?");
        if(conf) {
            removeVote(id, pwd);
        }
    });
});