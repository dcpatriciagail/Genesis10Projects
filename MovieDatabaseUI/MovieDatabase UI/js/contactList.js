$(document).ready(function () {

    loadContacts();

    $('#add-button').click(function(event){


      $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/api/movies',
        data: JSON.stringify({
            title: $('#add-title').val(),
            releaseDate: $('#add-releaseDate').val(),
            directorName: $('#add-directorName').val(),
            studio: $('#add-studio').val(),
            userRating: $('#add-userRating').val(),
            mpaarating: $('#add-mpaarating').val()
        }),
        headers: {
          'Accept' : 'application/json',
          'Content-Type' : 'application/json'
        },
        'dataType' : 'json',
        success: function(data, status) {
          $('#errorMessages').empty();
          $('#add-title').val('');
          $('#add-releaseDate').val('');
          $('#add-directorName').val('');
          $('#add-studio').val('');
          $('#add-userRating').val('');
          $('#add-mpaarating').val('');

          loadContacts(); // THIS WILL MAKE THE NEW CONTACT AVAILABLE
        },
        error: function() {
          $('#errorMessages')
              .append($('<li>')
              .attr({class: 'list-group-item list-group-item-danger'})
              .text('Error calling web services. Please try again later.'))
        }
      })

    });


    $('#edit-button').click(function(event){
      $.ajax({
        type: 'PUT',
        url: 'http://localhost:8080/api/movies/' + $('#edit-movie-id').val(),
        data: JSON.stringify({
          movieID: $('#edit-movie-id').val(),
          title: $('#edit-title').val(),
          releaseDate: $('#edit-releaseDate').val(),
          directorName: $('#edit-directorName').val(),
          studio: $('#edit-studio').val(),
          userRating: $('#edit-userRating').val(),
          mpaarating: $('#edit-mpaarating').val()
        }),
        headers: {
          'Accept' : 'application/json',
          'Content-Type' : 'application/json'
        },
        'dataType' : 'json',
        success: function(){
          $('#errorMessages').empty();
          hideEditForm();
          loadContacts();

        },
        error: function() {
          $('#errorMessages')
             .append($('<li>')
             .attr({class: 'list-group-item list-group-item-danger'})
             .text('Error calling web service.  Please try again later.'));

        }
      })
    });

    $('#search-button').click(function(event){
      clearSearchTable();
      var searchRows = $('#searchRows');
      $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/api/movies/searchmovie/' + $('#add-search').val(),
        success: function(searchArray) {
          $.each(searchArray, function(index,movies){
            var movieID = movies.movieID;
            var title = movies.title;
            var releaseDate = movies.releaseDate;
            var directorName = movies.directorName;
            var studio = movies.studio;
            var userRating = movies.userRating;
            var mpaarating = movies.mpaarating;

            var row = '<tr>';
                row += '<td>' + movieID + '</td>';
                row += '<td>' + title + '</td>';
                row += '<td>' + releaseDate + '</td>';
                row += '<td>' + directorName + '</td>';
                //row += '<td>' + studio + '</td>';
                row += '<td>' + userRating + '</td>';
                row += '<td>' + mpaarating + '</td>';
                row += '<td><a onclick="showEditForm(' + movieID + ')">Edit</a></td>';
                row += '<td><a onclick="deleteMovie(' + movieID + ')">Delete</a></td>';

                row += '</tr>';

                searchRows.append(row);
                $('#searchFormDiv').show();
          })
        }

      })
    });
});


function loadContacts() {
   clearContactTable();
  var contentRows = $('#contentRows');

  $.ajax({
    type: 'GET',
    url: 'http://localhost:8080/api/movies',
    success: function(moviesArray) {
      $.each(moviesArray, function(index, movies){
        var movieID = movies.movieID;
        var title = movies.title;
        var releaseDate = movies.releaseDate;
        var directorName = movies.directorName;
        var studio = movies.studio;
        var userRating = movies.userRating;
        var mpaarating = movies.mpaarating;

        var row = '<tr>';
            row += '<td>' + movieID + '</td>';
            row += '<td>' + title + '</td>';
            row += '<td>' + releaseDate + '</td>';
            row += '<td>' + directorName + '</td>';
            //row += '<td>' + studio + '</td>';
            row += '<td>' + userRating + '</td>';
            row += '<td>' + mpaarating + '</td>';
            row += '<td><a onclick="showEditForm(' + movieID + ')">Edit</a></td>';
            row += '<td><a onclick="deleteMovie(' + movieID + ')">Delete</a></td>';

            row += '</tr>';

            contentRows.append(row);

      });
    },
    error: function() {
      $('#errorMessages')
          .append($('<li>')
          .attr({class: 'list-group-item list-group-item-danger'})
          .text('Error calling web services. Please try again later.'))
    }
  });

}

function clearContactTable() {
  $('#contentRows').empty();
  $('#searchRows').empty();
}

function clearSearchTable() {
  $('#searchRows').empty();
}

function showEditForm(movieID) {
  $('#errorMessages').empty();
    $.ajax({
      type: 'GET',
      url: 'http://localhost:8080/api/movies/' + movieID,
      success: function(data, status) {
              $('#edit-title').val(data.title);
              $('#edit-releaseDate').val(data.releaseDate);
              $('#edit-directorName').val(data.directorName);
              $('#edit-studio').val(data.studio);
              $('#edit-userRating').val(data.userRating);
              $('#edit-mpaarating').val(data.mpaarating);
              $('#edit-movie-id').val(data.movieID);

      },
      error: function(){
      $('#errorMessages')
          .append($('<li>')
          .attr({class: 'list-group-item list-group-item-danger'})
          .text('Error calling web services. Please try again later.'))
    }
  });

  $('#contactTableDiv').hide();
  $('#editFormDiv').show();
}

function hideEditForm(){
  $('#errorMessages').empty();
  $('#edit-first-name').val('');
  $('#edit-last-name').val('');
  $('#edit-company').val('');
  $('#edit-phone').val('');
  $('#edit-email').val('');

    $('#editFormDiv').hide();
    $('#contactTableDiv').show();
}

function deleteMovie (movieID) {
  $.ajax ({
      type: 'DELETE',
      url: "http://localhost:8080/api/movies/" + movieID,
      success: function (status) {
          loadContacts();
      }
  });
}
