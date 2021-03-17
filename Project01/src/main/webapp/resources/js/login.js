/**
 * 
 */

window.onload = function(){
	document.getElementById("login").addEventListener("click", login);
}


function login(){
	let username = document.getElementById('username').value;
	let password = document.getElementById('password').value;
	fetch(`http://localhost:8080/Project01/revature/user/login?username=${username}&password=${password}`).then(
		function(response){
			return response.json();
		},function(){
			console.log('panic');
		}
	).then(function(myJSON){
		location.href = "employee.html";
		console.log(myJSON);
	});
}