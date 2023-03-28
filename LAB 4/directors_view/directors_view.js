import {
    getParameterByName,
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    createImageCell,
    setTextNode
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayDirector();
    fetchAndDisplayMovies();
    createButton();
});

function fetchAndDisplayMovies() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayCharacters(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/directors/' + getParameterByName('director') + '/movies', true);
    xhttp.send();
}

function displayCharacters(movies) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    movies.movies.forEach(movie => {
        tableBody.appendChild(createTableRow(movie));
    })
}

function createTableRow(movie) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(movie.title));
    tr.appendChild(createLinkCell('edit', '../movie_edit/movie_edit.html?director='
        + getParameterByName('director') + '&movie=' + movie.id));
    tr.appendChild(createButtonCell('delete', () => deleteMovie(movie.id)));
    return tr;
}

function deleteMovie(movie) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayMovies();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/directors/' + getParameterByName('director')
        + '/movies/' + movie, true);
    xhttp.send();
}

function fetchAndDisplayDirector() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayDirector(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/directors/' + getParameterByName('director'), true);
    xhttp.send();
}

function displayDirector(director) {
    setTextNode('id', director.id);
    setTextNode('name', director.name);
    setTextNode('age', director.age);
    setTextNode('nationality', director.nationality);
}

function createButton() {
    let butt = document.getElementById('butt');
    document.getElementById('butt').setAttribute('href', '../movie_add/movie_add.html?director=' 
    + getParameterByName('director'));
    butt.href = '../movie_add/movie_add.html?director=' + getParameterByName('director');
}

