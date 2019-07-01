function loadjscssfile(filename, filetype){
	 if (filetype=="js"){ //if filename is a external JavaScript file
	  var fileref=document.createElement('script')
	  fileref.setAttribute("type","text/javascript")
	  fileref.setAttribute("src", filename)
	 }
	 else if (filetype=="css"){ //if filename is an external CSS file
	  var fileref=document.createElement("link")
	  fileref.setAttribute("rel", "stylesheet")
	  fileref.setAttribute("type", "text/css")
	  fileref.setAttribute("href", filename)
	 }
	 else if (filetype=="icon"){ //if filename is an external CSS file
	  var fileref=document.createElement("link")
	  fileref.setAttribute("rel", "con")
	  fileref.setAttribute("type", "image/x-icon")
	  fileref.setAttribute("href", filename)
	 }
	 if (typeof fileref!="undefined")
	  document.getElementsByTagName("head")[0].appendChild(fileref)
}

function onJs(url){
			var scriptlength = document.getElementsByTagName('script').length
			for(i=0;i<scriptlength-1;i++){
				var filename = document.getElementsByTagName("script")[i].getAttribute("src");
				if(filename.replace("/xgxt/","").indexOf('/xg')!=-1&&filename.indexOf('/xgutil')==-1){
					filename = filename.replace("/xgxt/","");
				loadjscssfile(url+filename.replace("/xg",""), "js");
				}
			}
			var linklength = document.getElementsByTagName('link').length
			for(i=0;i<linklength;i++){
				var filename = document.getElementsByTagName("link")[i].getAttribute("href");
				var type = document.getElementsByTagName("link")[i].getAttribute("type");
				if(type=="text/css"){
					if(filename.indexOf('/xg')!=-1){
					loadjscssfile((url+filename.replace("//","/").replace("/xg","")), "css");
					}
				}else if(type=="image/x-icon"){
					if(filename.indexOf('/xg')!=-1){
					loadjscssfile(url+filename.replace("/xg",""), "icon");
					}
				}
			}
			
}
//onJs($('realPathForJsCss').value+'/');