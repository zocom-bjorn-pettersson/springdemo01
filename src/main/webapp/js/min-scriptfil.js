// Vad skriver vi i den här filen då?
// En bra introduktion finns på W3School: https://www.w3schools.com/jquery/default.asp
// En dokumentation om allt som går att göra finns på https://jquery.com/

// OBS: Det här är inte en kurs i JavaScript. Det här är ingenting ni behöver
// ha stenkoll på för den här kursen, men jag tror det kan vara väldigt
// motiverande för flera av er, och därför har jag tagit med det här.
//
// OBS: JavaScript har inte speciellt mycket att göra med Java. Det är två helt
// olika programmeringsspråk.

// Om $(document).ready(): https://www.w3schools.com/jquery/jquery_syntax.asp
// Om $("button"), eller $("button#getMovies"): https://www.w3schools.com/jquery/jquery_selectors.asp

$(document).ready(function(){
    $.get("/api/v1/movies/list", function(data, status){
        $("p#movie-text").text("Det finns " + data.length + " film(er).");

        $.each(data, function(index, movie) {
            console.log("Adding movie " + movie + " to list");
            $("ul#movies").append("<li> [" + movie.id + "] " + movie.title + ". (" + movie.rating + "/10)</li>");
        });
    });

  // Var ska information hämtas?
  // Observera att i JavaScript måste vi inte säga vad det är för sorts variabel.
  // (men det är fortfarande, ibland, skillnad på variabel="0" och variabel=0.)
  // (se videon Wat igen: https://www.youtube.com/watch?v=3se2-thqf-A)
  //
  $("button#clearMovies").click(function(){
    console.log("button#clearMovies pressed");

    $("ul#movies").empty();

    // Skicka en API-förfrågan till en metod som rensar listan av filmer
    $.post("/api/v1/movies/clear", []);
  });

  // https://api.jquery.com/jQuery.post/
  $("button#addDummaMej").click(function (){
    $.ajax({
        url: "/api/add_movie",
        type: "POST",
        contentType:"application/json; charset=utf-8",
        dataType: "json",
        data: '{ "title": "Dumma Mej", "rating": 10 }',
        success: function() {}
    });
  })

    $("form#create_movie button[type=submit]").click(function (){
        createMovieForm       = $("#create_movie");
        createMovieFormTitle = createMovieForm.children("input[name=title]").val();
        createMovieFormRating = createMovieForm.children("input[name=rating]").val();

        var myFormData = {};
        myFormData["title"] = createMovieFormTitle;
        myFormData["rating"] = createMovieFormRating;


        $.ajax({
            url: "/api/v1/movies/create",
            type: "POST",
            contentType:"application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(myFormData),
            success: function(responseData) {
                // We got successful response, meaning the movie was added
                // Because we configured the server to return what was added, we
                // can use that information to add it to the list using javascript
                $("ul#movies").append("<li> [" + responseData.id + "] " + responseData.title + ". (" + responseData.rating + "/10)</li>");
            },
            error: function() {
                alert("Something went wrong adding the movie.")
            }
        });

        // Return false to not reload the page after form is posted
        return false;
    })
});
