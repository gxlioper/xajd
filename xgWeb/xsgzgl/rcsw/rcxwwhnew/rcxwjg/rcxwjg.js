function showInfo(obj) {		
	var className = jQuery(obj).attr("class");
	var newClass = className == "up" ? "down" : "up";
	
	jQuery(obj).attr("class",newClass);
	var data = jQuery(obj).attr("data");
	if("down"==newClass){
		getRcxwxx(data);
		}
	
	jQuery("#tab_rcxw").find("[data='" + data + "'][type='detail-data']").toggle();
	}
function getRcxwxx(xwxxObj){
	var url = "rcsw_rcxwwhnew_rcxwjggl.do?method=getRcxwMx";
	
	var xwxx=xwxxObj.split("_");
	var xwdlObj=jQuery("#tab_rcxw").find("[data='" + xwxxObj + "'][type='detail-data']");
	xwdlObj.children().children(".tbody_rcxw").html("");
	var html="";
	jQuery.post(url, {xh:xwxx[0],xq:xwxx[1],xn:xwxx[2],rcxwlbdm:xwxx[3]}, function(data) {
		if(0==data.length){
			html += "<tr><td colspan='4' align='center'>无行为记录信息！</td>";
		}
		else{
			for ( var int = 0; int < data.length; int++) {
				html += "<tr><td align='center'>" + data[int]["rcxwlbdlmc"] + "</td>";
				html += "<td align='center'>" + data[int]["rcxwlbxlmc"] + "</td>";
				html+="<td align='center'>" + data[int]["fssj"] + "</td>";
				html+="<td align='center'>" + data[int]["fz"] + "</td></tr>";
			}
		}
		xwdlObj.children().children(".tbody_rcxw").append(html);
		}, 'json');
	}
function printXfjl() {
	var rows = jQuery("#dataTable").getSeletRow();
	var xh = "";
	var rcxwlbdm="";
	if(rows.length ==0 ){
		showAlertDivLayer("请选择您需要下载的记录！");
		return false;	
	}
	else if(rows.length == 1){
		xh = rows[0]["xh"];
		rcxwlbdm+=rows[0]["rcxwlbdm"];
		var url = "rcsw_rcxwwhnew_rcxwjggl.do?method=printXfjl&xh="+xh+"&rcxwlbdm="+rcxwlbdm;
		window.open(url);
	}
	else{
		for ( var i = 0; i < rows.length; i++) {
				if(i==rows.length-1){
					xh +=rows[i]["xh"];
					rcxwlbdm+=rows[i]["rcxwlbdm"];
				}else{
					xh +=rows[i]["xh"]+",";
					rcxwlbdm+=rows[i]["rcxwlbdm"]+",";
				}
			 }
		var url ="rcsw_rcxwwhnew_rcxwjggl.do?method=printXfjlZip&xh="+xh+"&rcxwlbdm="+rcxwlbdm;
		window.open(url);
	}
}