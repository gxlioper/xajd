

//根据模块加载功能点
//作者：qph
function setGnbList(obj){
	
	var id = "tabname";
	var options = [{dm:"",mc:""}];

	dwr.engine.setAsync(false);

	DWRUtil.removeAllOptions(id);
	DWRUtil.addOptions(id,options,"dm","mc");

	if ("" != obj.value){
		bdsz.getGnmcListByMkdm(obj.value,function(data){
			DWRUtil.addOptions(id,data,"gnb","gnmc");
		});
	} else {
		bdsz.getGnmcList(function(data){
			DWRUtil.addOptions(id,data,"gnb","gnmc");
		});
	}
	
	dwr.engine.setAsync(true);
}


function expZdyData() {
	document.forms[0].action = "/xgxt/bdsz.do?method=expData";
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}