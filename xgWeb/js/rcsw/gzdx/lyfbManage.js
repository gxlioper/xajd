function getXzfw(){
	var lylx=$("lylx").value;
	//心理咨询问题
	var userType=$("userType").value;
	var xxdm=$("xxdm").value;
	if(xxdm!="11355"){
		return false;
	}
	if(userType!="stu"){
		return false;
	}
	sfXzZxs.getXzZxs(lylx,function(data){
		if(data=="yes"){
			$("xzhffw").style.display="";
		}else{
			$("xzhffw").style.display="none";
		}
	});
}

function setAllZxs(){
	$('xlzxs').style.display='none';
	$('guolv').style.display='none';
	$("xlzxs").value="all";
}

function showLyfbDiv(){
		var dd_html="";
		dd_html += "<table width='300' class='formlist'>";
		dd_html += "<thead>";
		dd_html += "<tr height='30'>";
		dd_html += "<td colspan='2'><span>";
		dd_html += "选择条件";
		dd_html += "</span></td>";
		dd_html += "</tr>";
		dd_html += "</thead>";
		dd_html += "<tbody>";
	
		dd_html += "<tr height='30'  >";
		dd_html += "<td align='right' style='width:20%' >";
		dd_html += "请选择部门：";
		dd_html += "</td>";
		dd_html += "<td align='left' style='width:70%'>";
		dd_html += "<select name='bm' id ='bm' style='width:200px'>" 
		dd_html += $('bmdm').innerHTML;
		dd_html += "</td>";
		dd_html += "</tr>";
		
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right' width='100px'>";
		dd_html += "职工号：";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<input type='text' name='zgh' id ='zgh' style='width:200px'/>" 
		dd_html += "</td>";
		dd_html += "</tr>";
		
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right' width='100'>";
		dd_html += "姓名：";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<input type='text' name='zgxm' id ='zgxm' style='width:200px'/>" 
		dd_html += "</td>";
		dd_html += "</tr>";
	
		dd_html += "<tr bgcolor='EEF4F9'>";
		dd_html += "<td colspan='2' align='center'>";
		dd_html += "<button  onclick='getCxtj()'>确定</button>";
		dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		dd_html += "<button  onclick='closeStuDiv()'>取消</button>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tbody>";
		dd_html += "</table>";
		showTempDivForJs("",dd_html,400,300);
		
}

function closeStuDiv(){
	$('tempDiv').style.display='none';
	i = document.getElementsByTagName("select").length;

	for (j = 0; j < i; j++) {
		document.getElementsByTagName("select")[j].style.visibility = "";
		document.getElementsByTagName("select")[j].style.display = "";
	}
	$("bmdm").style.display="none";
}


function getCxtj(){
	var xm=$("zgxm").value;
	var zgh=$("zgh").value;
	var bmdm=$("bm").value;
	sfXzZxs.getXlzxsByTj(bmdm,zgh,xm,function(data){
		DWRUtil.removeAllOptions($("xlzxs"));		
		DWRUtil.addOptions($("xlzxs"),data,"dm","mc");
	});
	closeStuDiv();
}