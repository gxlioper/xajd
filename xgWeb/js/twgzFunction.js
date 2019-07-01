/* 团委工作 - 代码维护*/
function twgzCodeConf(flag){
	var url = "/xgxt/codeConf.do?tName=";
	if($("xxdm")){
		xxdm=document.forms[0].xxdm.value;
	}
	var tab_name = document.getElementById("tableName").value;
	url += document.forms[0].tname.value;
	if (tag == "modi" && curr_row == null) {
		alert("请选择要修改的记录！\n（单击相应的行）");
		return false;
	}
	if (tag == "del") {
		if (curr_row == null) {
			alert("请选择要删除的记录！\n（单击相应的行）");
			return false;
		} else {
			if (confirm("（请勿轻易删除该行）\n确定要删除选中的行吗？")) {
				document.forms[0].delV.value = replaceChar(curr_row.cells[1].innerText," ","");
				document.forms[0].delKey.value = rsTable.rows[0].cells[1].id;
				if(document.getElementById("realTable").value=="bjdydygxb"){
					document.forms[0].delV.value = replaceChar(curr_row.cells[1].innerText," ","")+replaceChar(curr_row.cells[2].innerText," ","");
					document.forms[0].delKey.value = rsTable.rows[0].cells[1].id+"!!"+rsTable.rows[0].cells[2].id;
				}
				document.forms[0].action = url + "&doType=del";
				document.forms[0].submit();
				return true;
			} else {
				return false;
			}
		}
		return;
	}
	var num = rsTable.rows[0].cells.length;
	var dd_html = "<br>";
	var w = 0;
	var h = 0;
	if (tag == "modi") {
		dd_html += "<table width='98%' align='center' class='tbstyle'><thead><tr align='center'><td colspan='2'> * 修 改 代 码 * <input type='hidden' name='doType' value='modi'></td></tr></thead>";
	} else {
		dd_html += "<table width='98%' align='center' class='tbstyle'><thead><tr align='center'><td colspan='2'> * 添 加 代 码 * <input type='hidden' name='doType' value='add'></td></tr></thead>";
	}
	if (num == 3) {
		dd_html += "<tr><td align='right' width='35%'>";
		dd_html += rsTable.rows[0].cells[1].innerText;
		dd_html += "：</td><td align='left'><input type='text' maxlength='30' style='width:98%;cursor:hand;color:#999999' name='newCode' value='";
		if (tag == "modi") {
			dd_html += replaceChar(curr_row.cells[1].innerText, " ", "");
			dd_html += "' readonly='true";
		}
		dd_html += "'></td></tr><tr><td align='right'>";
		dd_html += rsTable.rows[0].cells[2].innerText;
		dd_html += "：</td><td align='left'><input type='text' maxlength='30' style='width:98%' name='newCode' value='";
		if (tag == "modi") {
			dd_html += replaceChar(curr_row.cells[2].innerText, " ", "");
		}
		dd_html += "'></td></tr>";
		w = 450;
		h = 270;
	}
	dd_html += "</table><br>";
	document.forms[0].htmlContent.value = dd_html;
	if((tab_name != null && tab_name!="") && tab_name == "bjdydygxb"){
		url = "/xgxt/bjdydygxb.do";
		if(tag =="add"){
			url += "?act=add";//+"&codeType="+window.parent.document.getElementById("codeType").value;
			url += "&codeType="+document.getElementById("codeType").value;
		} else if(tag == "modi"){
			url += "?act=modi&values=";
			url += curr_row.cells[1].innerText.replace(" ","");
			for (var i=2; i < curr_row.cells.length; i++){
				url += "!!"+curr_row.cells[i].innerText.replace(" ","");
			}
			url += "&codeType="+document.getElementById("codeType").value;
		}
	}
	showTopWin(url, w, h);
}