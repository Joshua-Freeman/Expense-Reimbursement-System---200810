/*
 * 
 */
window.onload = function() {
	document.getElementById("b1").addEventListener("click", toggle);
	document.getElementById("b2").addEventListener("click", update);
	manager();
}



function toggle() {
	let doc = document.getElementById("ticket");
	if(doc.style.visibility == "visible"){
		doc.style.visibility = "hidden";
	}
	else{
		doc.style.visibility = "visible";
	}
	
}

function manager() {
	fetch("http://localhost:8080/Project01/revature/reimbursement/all").then(
		function(response) {
			return response.json();
		}, function() {
			console.log("something went wrong");
		}
	).then(function(json) {
		table(json);
	});
}

function table(data) {
	var table = document.getElementById('table');
	data.forEach(function(object) {
		var tr = document.createElement('tr');
		tr.innerHTML =
			'<td>' + object.id + '</td>' +
			'<td>' + object.amount + '</td>' +
			'<td>' + object.submitted + '</td>' +
			'<td>' + object.resolved + '</td>' +
			'<td>' + object.description + '</td>' +
			'<td>' + object.receipt + '</td>' +
			'<td>' + object.author + '</td>' +
			'<td>' + object.resolver + '</td>' +
			'<td>' + object.type + '</td>' +
			'<td>' + object.status + '</td>';
		table.appendChild(tr);
	});
}

function update() {
	let id = document.getElementById('id').value;
	let status = document.getElementById('status').value;

	var data = [{
		id: `${id}`,
		status: `${status}`
	}]

	fetch(`http://localhost:8080/Project01/revature/reimbursement/approve?id=${id}&status=${status}`, {
		method: 'POST',
	}).then(response => response.json()).then(data => {
		teardown();
		manager();
		toggle();
	}).catch((error) => {
		console.error('Error', error);
	});
}

function tableUpdate(object) {
	var table = document.getElementById('table');
	var tr = document.createElement('tr');
	tr.innerHTML =
		'<td>' + object.id + '</td>' +
		'<td>' + object.amount + '</td>' +
		'<td>' + object.submitted + '</td>' +
		'<td>' + object.resolved + '</td>' +
		'<td>' + object.description + '</td>' +
		'<td>' + object.receipt + '</td>' +
		'<td>' + object.author + '</td>' +
		'<td>' + object.resolver + '</td>' +
		'<td>' + object.type + '</td>' +
		'<td>' + object.status + '</td>';
	table.appendChild(tr);
}

function teardown(){
	var table = document.getElementById('table');
	while(table.hasChildNodes()){
		table.removeChild(table.firstChild);
	}
}