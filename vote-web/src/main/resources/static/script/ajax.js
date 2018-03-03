var id = 0;
var count = 0;

$(document).ready(function () {

    $("#add-vote-form").submit(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        addVote()

    });

    $("#reply_form").submit(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        sendReply();

    });

    var parts = location.pathname.match(/([a-z0-9_-]+)/ig);
    id = parts[2];
    getVote();

});

function getVote() {

    $.ajax({
        type : "GET",
        contentType : "application/json",
        url : " /vote/" + id,
        dataType : 'json',
        timeout : 100000,
        success: function (vote) {
            $("#topic").text(vote.topic);
            $("#description").text(vote.description);
            for (var i in vote.options) {

                $('<input>')
                    .attr('id', 'radio-btn-' + count)
                    .attr('type', 'radio')
                    .attr('name', 'option')
                    .attr('value', i)
                    .appendTo('#option-container');

                $('<span>')
                    .text(i)
                    .append(
                        $('<br>')
                    )
                    .appendTo('#option-container');
                count++;
            }
        }
    })
}

function addVote() {

    var map = {};
    for (var i = 1; i <= total; i++) {
        map[$("#input_option_" + i).val()] = 0;
    }

    var vote = {
        userName : $("#userName").val(),
        email : $("#email").val(),
        topic : $("#topic").val(),
        description : $("#description").val(),
        options : map
    };

    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : " /vote",
        data : JSON.stringify(vote),
        dataType : 'json',
        timeout : 100000,
        success : function(response) {
            console.log("SUCCESS: ", response);
            alert(response)
        },
        error : function(e) {
            console.log("ERROR: ", e);
        },
        done : function(e) {
            console.log("DONE");
        }
    });
}

function sendReply() {

    var reply = {
        id : id,
        value : $('input[name=option]:checked').val()
    };

    console.log(reply);

    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : " /vote/reply",
        data : JSON.stringify(reply),
        dataType : 'json',
        timeout : 100000,
        success : function(reply) {
            console.log("SUCCESS: ", reply);
        },
        error : function(e) {
            console.log("ERROR: ", e);
        },
        done : function(e) {
            console.log("DONE");
        }
    });
}

function removeVote(id, pwd) {

    $.ajax({
        type : "DELETE",
        url : "/vote/remove/" + id + "/" + pwd,
        success : function(data) {
            console.log("SUCCESS: ", data);
        },
        error : function(e) {
            console.log("ERROR: ", e);
        },
        done : function(e) {
            console.log("DONE");
        }
    });
}