function choiceXn(){
	var d_width = document.body.clientWidth;
	var d_height = document.body.clientHeight ;
	var d_left = 0;
	var d_top = 0;
	var d_color = "#EEF4F9";
	var d_width_top = 250;
	var d_height_top = 120;
	var d_left_top = (d_width - d_width_top) / 2;
	var d_top_top = (d_height - d_height_top)/ 2;
	var d_color_top = "#EEF4F9";
	dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
	dd_html += "</div>";
	dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
	dd_html += "<table width='300' class='tbstyle'>";
	dd_html += "<thead>";
	dd_html += "<tr height='30'>";
	dd_html += "<td colspan='2'>";
	dd_html += "----------------请选择---------------";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "</thead>";
	dd_html += "<tbody>";
	
	dd_html += "<tr height='30'>";
	dd_html += "<td align='right' width='30%'>";
	dd_html += "选择学年:";
	dd_html += "</td>";
	dd_html += "<td align='left'>";
	dd_html += "<select name='xn' id ='zhdj' onchange='changeXn(this.value)'>" 
	dd_html += $('xn').innerHTML;
	dd_html += "</td>";
	dd_html += "</tr>";
	
	dd_html += "<tr bgcolor='EEF4F9'>";
	dd_html += "<td colspan='2' align='center'>";
	dd_html += "<button class='button2' onclick='save();return false;'>确定</button>";
	dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
	dd_html += "<button class='button2' onclick='closeAdd1()'>取消</button>";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tbody>";
	dd_html += "</table>";
	dd_html += "</div>";
	$('tmpdiv1').innerHTML = dd_html;
}

function save(){
	location="hndx_zycp.do?method=zycpwh&doType=tb&tbxn="+$('tbxn').value;
}

function changeXn(xn){
	$('tbxn').value=xn;
}