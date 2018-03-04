$(document).ready(function () {
    var parts = location.pathname.match(/([a-z0-9_-]+)/ig);
    id = parts[2];
    getVote();
});