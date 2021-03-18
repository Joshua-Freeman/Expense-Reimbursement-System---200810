/*
 * 
 */
window.onload = function() {
	document.getElementById("b1").addEventListener("click", toggle);
	document.getElementById("b2").addEventListener("click", newReim);
	employee();
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

function employee() {
	fetch("http://localhost:8080/Project01/revature/reimbursement/users").then(
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

function newReim() {
	let amount = document.getElementById('amount').value;
	let type = document.getElementById('type').value;
	let description = document.getElementById('desc').value;
	var data = [{
		amount: `${amount}`,
		description: `${description}`,
		type: `${type}`
	}]

	fetch(`http://localhost:8080/Project01/revature/reimbursement/submit?amount=${amount}&type=${type}&description=${description}`, {
		method: 'POST',
	}).then(response => response.json()).then(data => {
		tableUpdate(data);
		toggle();
	}).catch((error) => {
		console.error('Error', error);
	});
}

function tableUpdate(object) {
	var table = document.getElementById('table');
	var tr = document.createElement('tr');
	tr.innerHTML =
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