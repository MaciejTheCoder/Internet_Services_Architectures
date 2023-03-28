import {clearElementChildren, createLinkCell, createButtonCell, createTextCell} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayDirectors();
});

function fetchAndDisplayDirectors() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayDirectors(JSON.parse(this.responseText))

        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/directors', true);
    xhttp.send();
}

function displayDirectors(directors) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    directors.directors.forEach(director => {
        tableBody.appendChild(createTableRow(director));
    })
    createButton();
}

function createTableRow(director) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(director.name));
    tr.appendChild(createLinkCell('view', '../directors_view/directors_view.html?director=' + director.id));
    tr.appendChild(createLinkCell('edit', '../director_edit/director_edit.html?director=' + director.id));
    tr.appendChild(createButtonCell('delete', () => deleteDirector(director.id)));
    return tr;
}

function deleteDirector(director) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayDirectors();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/directors/' + director, true);
    xhttp.send();
}

function createButton() {
    let butt = document.getElementById('butt');
    document.getElementById('butt').setAttribute('href', '../director_add/director_add.html');
    butt.href = '../director_add/director_add.html';
}