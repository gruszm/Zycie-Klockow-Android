// Pobieramy wszystkie elementy z klasą "closeLink" jako kolekcję elementów
var closeLinks = document.getElementsByClassName("closeLink");

for (var i = 0; i < closeLinks.length; i++) {
    closeLinks[i].addEventListener("click", function(event) {
        // Pobieramy rodzica (czyli div) linku, który jest aktualnie kliknięty
        var div = this.parentNode;

        // Ukrywamy div, ustawiając jego styl na "display: none"
        div.style.display = "none";

        // Zatrzymujemy domyślne działanie odnośnika (opcjonalnie)
        event.preventDefault();
    });
}
