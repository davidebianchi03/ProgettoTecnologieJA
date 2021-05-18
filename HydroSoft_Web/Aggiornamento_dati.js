$(document).ready(
    function () {
        $.get("rilevazioniSerraSingola.json", function (rilevazione) {
            document.getElementById("temperatura").innerHTML = rilevazione.temperaturaAria;
            document.getElementById("umidita").innerHTML = rilevazione.umiditaAria;
            if (rilevazione.bagnato == true) {
                document.getElementById("stato_terreno").src = "Immagini/terrenoUmido.png";
            } else {
                document.getElementById("stato_terreno").src = "Immagini/terrenoSecco.png";
            }
            if (rilevazione.aperto == true) {
                document.getElementById("stato_serra").src = "Immagini/serraAperta.png";
            } else {
                document.getElementById("stato_serra").src = "Immagini/serraChiusa.png";
            }

            document.getElementById("tipoPianta").innerHTML = rilevazione.tipoPianta;
            document.getElementById("oraRilevazione").innerHTML = rilevazione.oraRilevazione;
        });
    }
);