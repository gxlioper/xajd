<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		 <SCRIPT LANGUAGE="JavaScript"> 
		<!-- Hide 
		function killErrors() { 
		return true; 
		} 
		window.onerror = killErrors; 
		// --> 
		</SCRIPT>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript">
function tableSort111(the_td) {//
	arrowUp = document.createElement("SPAN");
	arrowUp.innerHTML = "5";
	arrowUp.style.cssText = "PADDING-RIGHT: 0px; MARGIN-TOP: -3px; PADDING-LEFT: 0px; FONT-SIZE: 10px; MARGIN-BOTTOM: 2px; PADDING-BOTTOM: 2px; OVERFLOW: hidden; WIDTH: 10px; COLOR: blue; PADDING-TOP: 0px; FONT-FAMILY: webdings; HEIGHT: 11px";
	arrowDown = document.createElement("SPAN");
	arrowDown.innerHTML = "6";
	arrowDown.style.cssText = "PADDING-RIGHT: 0px; MARGIN-TOP: -3px; PADDING-LEFT: 0px; FONT-SIZE: 10px; MARGIN-BOTTOM: 2px; PADDING-BOTTOM: 2px; OVERFLOW: hidden; WIDTH: 10px; COLOR: blue; PADDING-TOP: 0px; FONT-FAMILY: webdings; HEIGHT: 11px";
	the_td.mode = !the_td.mode;
	
	var cur_col = 0;
	
	var the_table = getPapaElement(the_td, "table");
	if (the_table.rows.length > 200) {
		if (!confirm("当前表的行数超过200行,排序将耗费比较长的时间,确定要排序吗?")) {
			return false;
		}
	}
	for(var ttt=0;ttt<the_table.rows[0].cells.length;ttt++){
		if(the_table.rows[0].cells[ttt] == the_td){
			cur_col = ttt;
			break;
		}
	}
	if (sort_col != null) {
		with (the_table.rows[0].cells[sort_col]) {
			removeChild(lastChild);
		}
	}
	with (the_table.rows[0].cells[cur_col]) {
		appendChild(the_td.mode ? arrowUp : arrowDown);
	}
	sort_tab(the_table, cur_col, the_td.mode);
	sort_col = cur_col;
}
function expTab111(the_table, tabTit, titSpan) {
	var table_title = (titSpan == null || titSpan == "") ? tabTit : document.getElementById(titSpan).outerHTML;
	var the_content = "<style media='print'>\n";
	the_content += ".noPrin{\n";
	the_content += "display:none;}\n";
	the_content += "</style>\n";
	the_content += "<link rel='stylesheet' rev='stylesheet' href='skin1/style/main.css' type='text/css' media='all' />\n";
	the_content += "<object id=\"WebBrowser\" width=0 height=0 classid=\"CLSID:8856F961-340A-11D0-A96B-00C04FD705A2\"></object>\n";
	the_content += "<script language='javascript' src='js/function.js'>";
	the_content += "</sc";
	the_content += "ript>\n";
	
	the_content += "<title>/title>";
	
	the_content += "<center><h3><b>";
	the_content += table_title;
	the_content += "</b></h3>";
	the_content += document.all(the_table).outerHTML;
	the_content = the_content.replace(/ onclick=\"[^\"]*\"/g, "");
	the_content = the_content.replace(/ mode=\"(false|true)"/g, "");
	the_content = the_content.replace(/ oBgc=\"[\w#\d]*\"/g, "");
	the_content = the_content.replace(/ oFc=\"[\w#\d]*\"/g, "");
	the_content = the_content.replace(/<span>(5|6)<\/span>/gi, "");
	the_content = the_content.replace(/<DIV contentEditable=false>(.*)<\/DIV>/ig, "$1");
	the_content += "\n<br><div class='noPrin btn'><button onclick=\"try{WebBrowser.ExecWB(8,1)}catch(e){alert('请设置安全级别，启用ActiveX控件和插件！')}\">页面设置</button>";
	the_content += "<button onclick=\"try{WebBrowser.ExecWB(7,1)} catch(e){alert('请设置安全级别，启用ActiveX控件和插件！')}\">打印预览</button>";
	the_content += "<button onclick=\"try{WebBrowser.ExecWB(6,6)}catch(e){alert('请设置安全级别，启用ActiveX控件和插件！')}\">直接打印</button>";
	the_content += "<\/div>";
	//confirm(the_content);
	var newwin = window.open("about:blank", "_blank", "");
	newwin.document.open();
	newwin.document.write(the_content);
	newwin.document.title="学生工作管理系统";
	newwin.document.close();
	newwin = null;
}
</script>
	</head>

	<body onload="getColumeList('do_data_query.do')">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a><bean:write name="titName" scope="request" />
					- 查询结果</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form styleId="form1" action="/do_data_query">
			
			<input type="hidden" value="<bean:write name="tabName"/>" id="cxbm" name="cxbm" />
			<input type="hidden" name="titName" id="titName"
				value="<bean:write name="titName" scope="request"/>" />
			<input type="hidden" value="<bean:write name="sql"/>" name="sql1" />
			<input type="hidden" value="<bean:write name="cols"/>" name="cols1" />
			<input type="hidden" value="" name="colName" id="colName" />
			<input type="hidden" value="1" name="page" />
			<div class="buttontool" id="btn"
					style="position: absolute;left:1%;top:100px" width="100%">
			<input type="button" onclick="showList()" name="showcollist" style="display:none"
						class="button2" value="打开列选" />
			</div>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
							<li>
								<a href="#"
									onclick="showList()" name="showcollist"
									class="btn_xg"><div id="lxButton">打开列选</div></a>
							</li>
							<!-- <li>
								<a href="#"
									onclick="expTab111('rsTable','查询结果')"
									class="btn_dy">打印数据</a>
							</li>   -->
							<li>
								<a href="#" 
									onclick="dataExpForQ()"
									class="btn_dc">导出数据</a>
							</li>		
					</ul>
				</div>	
				<!-- 按钮 end-->	
				<div class="formbox">
					<%@ include file="table.jsp"%>
				</div>
				<p class="searchfont" id="resultTitle" style="margin-top:8px" />
				<div id="colListArea" style="display:none" align="left"></div>	
		</html:form>
		<div id="tmpdiv"></div>
	</body>
</html>
