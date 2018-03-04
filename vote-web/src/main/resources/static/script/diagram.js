window.chartColors = {
    red: 'rgb(255, 99, 132)',
    orange: 'rgb(255, 159, 64)',
    yellow: 'rgb(255, 205, 86)',
    green: 'rgb(75, 192, 192)',
    blue: 'rgb(54, 162, 235)',
    purple: 'rgb(153, 102, 255)',
    grey: 'rgb(201, 203, 207)'
};

window.onload = function() {

    var parts = location.pathname.match(/([a-z0-9_-]+)/ig);
    var id = parts[3];

    $.ajax({
        type : "GET",
        contentType : "application/json",
        url : " /vote/" + id,
        dataType : 'json',
        timeout : 100000,
        success: function (vote) {
            var options = [];
            var values = [];
            for (var i in vote.options) {
                options.push(i);
                values.push(vote.options[i]);
            }
            var ctx = document.getElementById('chart-area').getContext('2d');
            window.myPie = new Chart(ctx, getconfig(options, values));
        }
    });
};

function getconfig(options, values) {
    var config = {
        type: 'pie',
        data: {
            labels: options,
            datasets: [{
                data: values,
                backgroundColor: [
                    window.chartColors.red,
                    window.chartColors.orange,
                    window.chartColors.yellow,
                    window.chartColors.green,
                    window.chartColors.blue
                ],
                label: 'Dataset 1'
            }]
        },
        options: {
            responsive: true,
            legend: {
                position: 'right',
                fullWidth: false
            },

            animation: {
                animateRotate: false,
                animateScale: true
            }
        }
    };

    return config;
}