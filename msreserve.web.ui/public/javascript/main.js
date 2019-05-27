window.addEventListener('DOMContentLoaded', function() {
	initInternal();
	addMoreBtnEvent();
	addToTopBtnEvent();
});

function initInternal() {
	initAreaGnb();
	configureCategory();
	addPromotions();
}

function initAreaGnb() {
	var btnToMain= document.getElementById('areaBtnToMain');
	btnToMain.addEventListener('click', function() {
		window.location.href= '/';
	});
}

function addPromotions() {
	var element= document.getElementById('areaPromotion');
	var htmlTemplate= document.querySelector('#promotionTemplate').innerHTML;
	
	sendGetAjaxRequest('/api/promotions', function() {
		if (this.status == 200) {
			var txt = this.responseText;
			var promotioned= JSON.parse(txt).items;
			
			var children= [];
			var leng= promotioned.length;
			
			if(leng == 1) {
				promotioned[1]= JSON.parse(JSON.stringify(promotioned[0]));
				leng= 2;
			}
			
			for(var i=0; i<leng; i++) {
				var tmp= document.createElement('div');
				
				var template= Handlebars.compile(htmlTemplate);
				var context= {path: promotioned[i].productImageUrl};
				tmp.innerHTML= template(context);
				children[i]= tmp.firstElementChild;
			}
			

			if(children.length > 0) {
				animate(element, children, template, promotioned, 0, 1, 0);
			}
		}
	});
}

function animate(element, children, template, items, firstIdx, secondIdx, cnt) {
	window.requestAnimationFrame(function() {
		var firstEle= children[firstIdx];
		var secondEle= children[secondIdx];
		
		if(cnt == 0) {
			firstEle.style.left= "0px";
			firstEle.style.top= "0px";
			secondEle.style.left= "600px";
			secondEle.style.top= "-200px";
			element.appendChild(firstEle);
			element.appendChild(secondEle);
			cnt= cnt+1;
		} else if(cnt == 600) {
			element.innerHTML= "";
			firstIdx= (firstIdx === items.length-1) ? 0 : firstIdx+1;
			secondIdx= (secondIdx === items.length-1) ? 0 : secondIdx+1;
			cnt= 0;
		} else {
			firstEle.style.left= parseInt(firstEle.style.left) - 1 + "px";
			secondEle.style.left= parseInt(secondEle.style.left) - 1 + "px";
			cnt= cnt+1;
		}
		animate(element, children, template, items, firstIdx, secondIdx, cnt);
	});
}

var size= 4;
var selectedCd;

function configureCategory() {
	sendGetAjaxRequest('/api/categories', function() {
		if(this.status == 200) {
			var response= this.responseText;
			var categories= JSON.parse(response).items;
			var ele= document.getElementById('areaCategory');
			
			var htmlTemplate= document.getElementById("categoryTemplate").innerText;
//			categories.forEach(function(item, index, array) {
//				var name= item.name;
//				var categoryId= item.id;
//				
//				var child= document.createElement('template');
//				var innerHTML= htmlTemplate.replace("${categoryId}", categoryId)
//										   .replace("${name}", name);
//				child.innerHTML= innerHTML.trim();
//				ele.appendChild(child.content.firstChild);
//			});
			var leng= categories.length;
			var template= Handlebars.compile(htmlTemplate);
			for(var i=0; i<leng; i++ ) {
				var item= categories[i];
				var name= item.name;
				var categoryId= item.id;
				
				var context={categoryId: categoryId, name: name};
				var child= document.createElement('template');
				var innerHTML= template(context);
				child.innerHTML= innerHTML.trim();
				ele.appendChild(child.content.firstChild);
			};
			
			addCategoryEvent();
			document.getElementsByClassName("categoryBtn")[0].click();
		}
	});
}

function addCategoryEvent() {
	var elements = document.getElementsByClassName("categoryBtn");
	for (var i=0; i<elements.length; i++) {
		var ele = elements[i];
		ele.addEventListener("click", function() {
			for (var j = 0; j < elements.length; j++) {
				elements[j].style.color = "black";
				elements[j].style.textDecoration = "none";
			}
			this.style.color = "green";
			this.style.textDecoration = "underline";
			code= this.getAttribute('categoryId');
			selectedCd= code;
			
			var qs= 'categoryId=' + code +'&start=0';
			sendGetAjaxRequest('/api/products?' + qs, function() {
				if(this.status == 200) {
					var response= this.responseText;
					var products= JSON.parse(response);
					setProductCount(products);
					displayProduct(products);
				}
			});
		}, false);
	}
}

function setProductCount(products) {
	var cnt= products.totalCount;
	var messageFormat= document.getElementById('label.product.count').innerText;
	var element= document.getElementById('areaCount');
	element.innerText= messageFormat.replace('{0}', cnt);
}

function displayProduct(products) {
	var html= document.querySelector('#productTemplate').innerHTML;
	var items= products.items;
	var area= document.getElementsByClassName('areaProduct');
	area[0].innerHTML= '';
	area[1].innerHTML= '';
	
	var template= Handlebars.compile(html);
	var leng= items.length < 4 ? items.length : 4;
	for(var i=0; i<leng; i++) {
		var child= document.createElement('div');
		child.setAttribute('style', 'margin-bottom: 15px;');
		child.setAttribute('class', 'product');
		child.setAttribute('productId', items[i].productId);
		child.setAttribute('displayInfoId', items[i].displayInfoId);
		child.setAttribute('style', 'cursor: pointer;');
		child.addEventListener("click", function() {
			var qs= 'displayInfoId=' + this.getAttribute('displayInfoId');
			window.location.href= '/detail?' + qs
		});
		
		var description= items[i].productContent;
		description= description.length > 50 ? description.substring(0, 50) + '...' : description
				
		var context= {path: items[i].productImageUrl
					  , name: items[i].productDescription
					  , place: items[i].placeName
					  , description: description};
		child.innerHTML= template(context);
		area[i%2].appendChild(child);
	}
	
	var ele= document.getElementById('areaBtnMore');
	ele.style.display= '';
}

function sendGetAjaxRequest(url, callback) {
	var req = new XMLHttpRequest();
	req.addEventListener("load", callback);
	req.open("GET", url, true);
	req.send();
}

function addMoreBtnEvent() {
	var ele= document.getElementById('areaBtnMore');
	ele.addEventListener("click", function() {
		var displayedProducts= document.getElementsByClassName('product');
		size= displayedProducts.length;
		
		var qs= 'categoryId=' + selectedCd + "&start=" + size;
		
		/* 다음 건이 있는지 확인하기 위해 1건 더 조회한다 */
		sendGetAjaxRequest('/api/products?' + qs, function() {
			var response= this.responseText;
			var products= JSON.parse(response);
			var items= products.items;
			
			if(products.totalCount <= (size + 4)) {
				ele.style.display= 'none';
			}
			
			var area= document.getElementsByClassName('areaProduct');
			var html= document.querySelector('#productTemplate').innerHTML;
			var template= Handlebars.compile(html);
			for(var i=0; i<items.length; i++) {
				var child= document.createElement('div');
				child.setAttribute('style', 'margin-bottom: 15px;')
				child.setAttribute('class', 'product');
				var description= items[i].productContent;
				description= description.length > 50 ? description.substring(0, 50) + '...' : description
						
						
				var context= {path: items[i].productImageUrl
							  , name: items[i].productDescription
							  , place: items[i].placeName
							  , description: description};
				child.innerHTML= template(context);
				
				area[i%2].appendChild(child);
			}
		});
	});
}

function addToTopBtnEvent() {
	var ele= document.getElementById('areaBtnToTop');
	ele.addEventListener("click", function() {
		window.scrollTo(0, 0);
	});
}