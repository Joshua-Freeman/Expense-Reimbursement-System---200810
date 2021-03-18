/**
 * 
 */

window.onload = function() {
	document.getElementById('b1').addEventListener('click', register);
}


function register() {

	let username = document.getElementById('username').value;
	let firstName = document.getElementById('firstName').value;
	let lastName = document.getElementById('lastName').value;
	let email = document.getElementById('email').value;
	let password = document.getElementById('password').value;
	const data = {
		"username": `${username}`,
		"password": `${firstName}`,
		"firstName": `${lastName}`,
		"lastName": `${email}`,
		"email": `${password}`
	};

	fetch('http://localhost:8080/Project01/revature/user/register', {
		method: 'POST', // or 'PUT'
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(data),
	})
		.then(response => {
			
			//return response.json();
			})
/*		.then(data => {
			//console.log('Success:', data);
			//location.href = "index.html";
		})
		.catch((error) => {
			//console.error('Error:', error);
		});*/

}
