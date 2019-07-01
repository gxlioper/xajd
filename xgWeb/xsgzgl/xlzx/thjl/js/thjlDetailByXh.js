function init(){
	var knlxdm = $("knlxdm").value;
	var knlxList = knlxdm.split(",");
	for(var i=0;i<knlxList.length;i++){
		jQuery("input[type='checkbox'][name=knlxBoxList][value='"+knlxList[i]+"']").attr("checked",true);
	}
	jQuery("input[type='checkbox'][name=knlxBoxList]").attr("disabled",true);
}
		
function deploy(id){
	document.getElementById(id).style.display = (document.getElementById(id).style.display == 'none') ? '' : 'none';  
}