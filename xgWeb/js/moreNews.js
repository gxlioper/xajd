function loadchange(){
			var tab = document.getElementById("titName").value;
			document.getElementById($(tab+"_a").parentNode.id).className = "ha";
	    }
	    
	    function changePage(obj){
	    	var id = obj.parentNode.id;
	    	obj.parentNode.className = 'ha';
	    	
	    	var nodeId = obj.id;
	    	var elements = ele('ul1').children;
	    	for(var i=0; i<elements.length; i++){
	    		if(elements[i].id!= id){
	    			elements[i].className = '';
	    			var trId = elements[i].id.substring(0,elements[i].id.length-2);
	    			if(document.getElementById(trId)){
	    				document.getElementById(trId).style.display = "none";	    			
	    			}
	    		}
	    	}
	    	document.getElementById(nodeId).style.display = "";
	    	loadData(nodeId);
	    }
	    
	    
	  function loadData(id){
	  		var nodeId = id.substring(0,id.length-2);
	    	refreshForm('moreNews.do?newspart='+nodeId);
	  }
	  
	  function loadDataByFy(){
	  		var nodeId =$("titName").value;
	  		refreshForm('moreNews.do?newspart='+nodeId);
	  }