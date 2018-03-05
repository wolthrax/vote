var id = 0;
var count = 0;

$(document).ready(function () {

    $("#add-vote-form").submit(function (event) {
        event.preventDefault();
        addVote()
    });

    $("#reply_form").submit(function (event) {
        event.preventDefault();
        sendReply();
    });
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

                $('<p>')
                    .append(
                        $('<input>')
                            .attr('id', 'radio-btn-' + count)
                            .attr('type', 'radio')
                            .attr('name', 'option')
                            .attr('value', i),

                        $('<label>')
                            .attr('for', 'radio-btn-' + count)
                            .text(i)
                        )
                    .appendTo('#option-container');
                count++;
            }
        },
        error : function(e) {
            console.log(e);
            $('#reply_form').remove();
            $('#errorContainer').remove();
            $('<span>')
                .attr('id', 'errorContainer')
                .text(e.responseText)
                .appendTo('.errors');
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
            $('.container').remove();
            printLinks(response);
        },
        error : function(e) {
            console.log("ERROR: ", e);

            $('#errorContainer').remove();
            $('<div>')
                .attr('id', 'errorContainer')
                .appendTo('.errors');

            printErrors(JSON.parse(e.responseText));
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

    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : " /vote/reply",
        data : JSON.stringify(reply),
        timeout : 100000,
        success : function(vote) {
            console.log("SUCCESS");
            document.location.href = '/vote/view/results/' + id;
        },
        error : function(e) {
            console.log("ERROR: ", e);

            $('#errorContainer').remove();
            $('<div>')
                .attr('id', 'errorContainer')
                .appendTo('.errors');

            printErrors(JSON.parse(e.responseText));
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
            alert('Голосование успешно удалено');
            document.location.href = '/vote';
        },
        error : function(e) {
            console.log("ERROR: ", e);
            alert('Голосование не было удалено');
        },
        done : function(e) {
            console.log("DONE");
        }
    });
}

function getTop10() {

    $.ajax({
        type : "GET",
        contentType : "application/json",
        url : " /vote/top",
        dataType : 'json',
        timeout : 100000,
        success: function (data) {

            for (var i = 0; i < data.length; i++) {
                var href = '/vote/view/' + data[i].id;
                $("<p>")
                    .text(data[i].userName + ": ")
                    .append(
                        $('<a>')
                            .attr('href', href)
                            .text(data[i].topic + " ")
                    )
                    .appendTo('#vote-container');
            }
        },
        error : function(e) {
            console.log(e)
        }
    })
}

function printErrors(errors) {

    for (var i = 0; i < errors.length; i++) {
        $('<p>')
            .text(errors[i])
            .append(
                $('<br>')
            )
            .appendTo('#errorContainer');
    }
}

function printLinks(response) {

    var str = "Ссылка на голосование: " + response.voteLink + '\n'
                + "Ссылка для удаления: " + response.removeLink + '\n'
                + "Ссылка на результаты: " + response.resultLink;

    $('<div>')
        .attr('class', 'container')
        .append(
            $('<h3>').text("Голосование успешно создано"),
            $('<p>')
                .text("Ссылка на голосование: ")
                .append(
                    $('<a>')
                        .attr('href', response.voteLink)
                        .text(response.voteLink)
                ),
            $('<p>')
                .text("Ссылка для удаления: ")
                .append(
                    $('<a>')
                        .attr('href', response.removeLink)
                        .text(response.removeLink)
                ),

            $('<p>')
                .text("Ссылка на результаты: ")
                .append(
                    $('<a>')
                        .attr('href', response.resultLink)
                        .text(response.resultLink)
                ),
            $('<input>')
                .attr('id', 'download-btn')
                .attr('type', 'submit')
                .attr('value', 'Скачать')
        )
        .appendTo('#addBody');

    $("#download-btn").on("click", download);

    function download() {
        var element = document.createElement('a');
        element.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(str));
        element.setAttribute('download', 'links.txt');

        element.style.display = 'none';
        document.body.appendChild(element);

        element.click();

        document.body.removeChild(element);
    }
}