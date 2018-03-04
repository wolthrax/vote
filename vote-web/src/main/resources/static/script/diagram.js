window.chartColors = {
    red: 'rgb(255, 99, 132)',
    orange: 'rgb(255, 159, 64)',
    yellow: 'rgb(255, 205, 86)',
    green: 'rgb(75, 192, 192)',
    blue: 'rgb(54, 162, 235)',
    purple: 'rgb(153, 102, 255)',
    grey: 'rgb(201, 203, 207)',
    pink: 'rgb(255, 192, 203)',
    violet: 'rgb(238, 130, 238)',
    burlyWood: 'rgb(222, 184, 135)',
    chocolate: 'rgb(210, 105, 30)',
    teal: 'rgb(240, 128, 128)',
    aqua: 'rgb(205, 92, 92)',
    rosyBrown: 'rgb(188, 143, 143)',
    indigo: 'rgb(75, 0, 130)',
    orangeRed: 'rgb(255, 69, 0)',


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
                    window.chartColors.blue,
                    window.chartColors.pink,
                    window.chartColors.violet,
                    window.chartColors.burlyWood,
                    window.chartColors.chocolate,
                    window.chartColors.teal,
                    window.chartColors.aqua,
                    window.chartColors.rosyBrown,
                    window.chartColors.indigo,
                    window.chartColors.orangeRed
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