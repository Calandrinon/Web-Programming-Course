var menu_link =  document.querySelectorAll(".menu_link");
var submenu_a = document.querySelectorAll(".submenu_a");

menu_link.forEach(function(aitem){

		aitem.addEventListener("mouseover", function(){
			menu_link.forEach(function(aitem){
				aitem.classList.remove("active");
			})
			submenu_a.forEach(function(submenu_item){
				submenu_item.classList.remove("active");
			})
			aitem.classList.add("active");
		})
})

submenu_a.forEach(function(submenu_item){

		submenu_item.addEventListener("mouseover", function(){
			submenu_a.forEach(function(submenu_item){
				submenu_item.classList.remove("active");
			})
			submenu_item.classList.add("active");
		})
})