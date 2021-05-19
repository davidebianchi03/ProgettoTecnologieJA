$(document).ready(
    function() {
        disegnaGraficoTemperatura();
        disegnaGraficoUmidita();
    }
);

function disegnaGraficoTemperatura() {
    $.get("rilevazioniSerraLista.json", function(data) {
        //alert(data);
        var nval = data.length;
        //alert(nval);
        var date = new Array();
        var temperature = new Array();

        for (var i = 0; i < nval; i++) {
            temperature.push(data[i].temperaturaAria);
            date.push(data[i].oraRilevazione);
        }

        new Chart("graficoTemperatura", {
            type: "line",
            data: {
                labels: date,
                datasets: [{
                    fill: false,
                    lineTension: 0,
                    backgroundColor: "rgba(0,0,255,1.0)",
                    borderColor: "rgba(0,0,255,0.1)",
                    data: temperature
                }]
            },
            options: {
                legend: {
                    display: false
                },
                scales: {
                    yAxes: [{
                        ticks: {
                            min: -10,
                            max: 40
                        }
                    }],
                }
            }
        });
    });
}

function disegnaGraficoUmidita() {
    $.get("rilevazioniSerraLista.json", function(data) {
        //alert(data);
        var nval = data.length;
        //alert(nval);
        var date = new Array();
        var umidita = new Array();

        for (var i = 0; i < nval; i++) {
            umidita.push(data[i].umiditaAria);
            date.push(data[i].oraRilevazione);
        }

        new Chart("graficoUmidita", {
            type: "line",
            data: {
                labels: date,
                datasets: [{
                    fill: false,
                    lineTension: 0,
                    backgroundColor: "rgba(255,0,0,1.0)",
                    borderColor: "rgba(0,0,255,0.1)",
                    data: umidita
                }]
            },
            options: {
                legend: {
                    display: false
                },
                scales: {
                    yAxes: [{
                        ticks: {
                            min: 0,
                            max: 100
                        }
                    }],
                }
            }
        });
    });
}