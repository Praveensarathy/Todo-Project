document.forms[0].onsubmit = function(){
    let xhr = new XMLHttpRequest();
    let input = document.getElementById("text").value;
    if(input.trim() !== ""){
    xhr.open("POST","/Todo_List_Project/todoform" , true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
        		const div = document.createElement("div");
        		div.setAttribute('class','div');
        		console.log(xhr.responseText);
        		div.setAttribute('id', xhr.responseText + "d");
        		const para = document.createElement("label");
        		para.id = xhr.responseText +"p";
        		const checkbox = document.createElement('input');
                checkbox.type = "checkbox";
                checkbox.name = "myCheckbox";
                checkbox.id = xhr.responseText;
                checkbox.setAttribute("class", "check");
                checkbox.onclick = checkfunc;
                const button = document.createElement('button');
                button.setAttribute("class","btn");
                button.innerHTML = "X";
                button.id  = xhr.responseText;
                
                 div.addEventListener("mouseover", function() {
				  div.appendChild(button);
				  button.style.display = "inline";
				});
                div.addEventListener("mouseout", function() {
				  button.style.display = "none";
				});
				button.onclick =deletefunc;
                const element = document.getElementById("response");
                let node = document.createTextNode(document.getElementById("text").value);
                para.appendChild(node);
                div.appendChild(checkbox);
                div.appendChild(para);
                element.appendChild(div);
                document.getElementById("text").value="";
        }
    };
    xhr.send("todo="+input);
    return false;
    }
    else{
		alert("input data");
		return false;
	}
    
};

function load() {
	let xhttp = new XMLHttpRequest();
	xhttp.onload = function() {
		if (xhttp.readyState === 4 && xhttp.status === 200){
			console.log(xhttp.responseText);
			let json = JSON.parse(xhttp.responseText);
			for(var key in json){	
				const div = document.createElement("div");
        		div.setAttribute('class','div');
        		div.setAttribute('id', json[key].id +"d");
        		const para = document.createElement("label");
        		para.id = json[key].id + "p";
        		const checkbox = document.createElement('input');
                checkbox.type = "checkbox";
                checkbox.name = "myCheckbox";
                checkbox.setAttribute("class", "check");
                checkbox.id = json[key].id;
                checkbox.onclick = checkfunc;
                const button = document.createElement('button');
                button.classList.add("btn");
                button.innerHTML = "X";
                button.id = json[key].id;
                div.addEventListener("mouseover", function() {
				  div.appendChild(button);
				  button.style.display = "inline";
				});
                div.addEventListener("mouseout", function() {
				  button.style.display = "none";
				});
				button.onclick =deletefunc;
                const element = document.getElementById("response");
                const node = document.createTextNode(json[key].todo);
                para.appendChild(node);
                if(json[key].check ==1){
					checkbox.checked = true;
					para.style.textDecoration = "line-through";
				}
                div.appendChild(checkbox);
                div.appendChild(para);
                element.appendChild(div);
                
			}			
		}
	};
	xhttp.open("GET", "/Todo_List_Project/load");
	xhttp.send();
	
}
Window.onload = load();




function deletefunc() {	
	let id = "id=" + this.id;
	console.log(this.id);
  	let xhttp = new XMLHttpRequest();
  	  	xhttp.open("POST", "/Todo_List_Project/delete", true);
  	  	let key = this.id;
  	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  	 xhttp.onreadystatechange = function () {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
  			let div = document.getElementById(key + "d");
			div.remove();
  		}	
  	}
  	xhttp.send(id);
 }
 
 
function checkfunc(){
	let xhttp = new XMLHttpRequest();
	let id = "id=" + this.id;
  	xhttp.open("POST", "/Todo_List_Project/check", true);
  	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  	let key = this.id;
  	 xhttp.onreadystatechange = function () {
       if (xhttp.readyState === 4 && xhttp.status === 200){
		   let para = document.getElementById(key + "p");
		   console.log(parseInt(xhttp.responseText));
		   if (parseInt(xhttp.responseText) === 1)
		       para.style.textDecoration = "line-through";
		   else 
		   		para.style.textDecoration = "none";
	   }
	}
	xhttp.send(id);
	
}
