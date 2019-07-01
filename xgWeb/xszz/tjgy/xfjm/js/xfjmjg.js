
/**
 * 学号链接
 * @param cellValue
 * @param rowObject
 * @return
 */
function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='xmsqView(\""+rowObject["guid"]+"\");'>"+cellValue+"</a>";
}



function searchRs(){
	var map = getSuperSearch();
	var shzt = jQuery("#shzt").val();
	
	if (shzt != ""){
		map["shzt"] = shzt;
	}
	jQuery("#dataTable").reloadGrid(map);
}



//打印word
function getWord(sqjb){	
	var checkBoxArr = document.getElementsByName("primarykey_cbv");
	var flag = false;
	var index = 0;
	var sum = 0;
	var value = "";
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
			index = i;
			sum = sum + 1;
			value += document.getElementsByName("primarykey_cbv")[i].value
			value += ";"
			value += document.getElementsByName("h_xh")[i].value
			value += ","
		}
	}
	 if (!flag ){
		showAlertDivLayer("请选择记录！");
	 } 
	 if(sum == 1){
		var url = "tjgy_xfjm.do?method=downloadWord";		
		var pkValue = document.getElementsByName("primarykey_cbv")[index].value;
		var xh =  document.getElementsByName("h_xh")[index].value;
		url += "&pkValue="+pkValue+"&xh="+xh+"&bz="+sqjb;
		window.open(url);
	 }
	 if(sum > 1){
			var url = "tjgy_xfjm.do?method=downloadZip";		
			url += "&value="+value+"&sqjb="+sqjb;
			window.open(url);
		 }
	 
}



