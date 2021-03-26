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
			if(!response.ok){
				document.getElementById("test").innerText = "Wrong username of password";	
			}
			else{
				return response.json();
			}
		},function(){
			if(!response.ok){
				document.getElementById("test").innerText = "Wrong username or password";	
			}
			console.log('panic');
		}
	).then(function(myJSON){
		//location.href = "employee.html";
		if(myJSON['role'] == 'MANAGER'){
			location.href = "manager.html";
		}
		else{
			location.href = "employee.html";
		}
	});
}