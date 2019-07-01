<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title>就业管理信息系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
    <script type="text/javascript" src="js/BatAlert.js"></script>
    <script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script language="javascript">
	  function initZyList(){
			var xydm = document.getElementById("xydm").value;
				GetListData.getZyListnoxy(xydm,function initTjList(data){
					if (data != null && typeof data == 'object') {
						var objId = data[0].dm;
						if($(objId) && $(objId).tagName.toLowerCase() == "select"){
							DWRUtil.removeAllOptions(objId);			
							DWRUtil.addOptions(objId,data,"dm","mc");			
							$(objId).options[0].value = "";
							if(objId){
							
								for(var i = 1;i < $(objId).options.length-1; i++){
										$(objId).options[i].selected = true;
										return true;
								
							}
							}
						}
					}else{
						showMsgWin("有错误出现：远程数据读取失败！");
					}
				});
		}
    function querygo(type){
      // var tjxm = document.getElementById("tjxm").value;
       var nd  = document.getElementById("nd").value;
       var xydm  = document.getElementById("xydm").value;
       //if(tjxm=="123"){
       //	 	alert("统计项目不能为空！");
       //		return false;
       //}
       
       if(nd==""){
       	 	alert("毕业年度不能为空！");
       		return false;
       }
       if(xydm==""){
      	 	alert("<bean:message key="lable.xsgzyxpzxy" />不能为空！");
      		return false;
      }
       if(type == 'tj'){
       		document.forms[0].action = "/xgxt/byssytj.do?tjxm=tjxm&type="+type;
			document.forms[0].submit();
	   }else{
			window.open('/xgxt/byssytj.do?tjxm='+tjxm+'&nd='+nd+'&type='+type);
	   }
    }
	function expTabdy(the_table, tabTit, titSpan) {
		/*var HKEY_Root="HKEY_CURRENT_USER";
		var HKEY_Path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
		var Wsh=new ActiveXObject("WScript.Shell");
		var HKEY_Key="header";
		    Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");
		    HKEY_Key="footer";
		    Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,""); */  
		var table_title = (titSpan == null || titSpan == "") ? tabTit : document.getElementById(titSpan).outerHTML;	
		var the_content = "<style media='print'>\n";
		the_content += ".noPrin{\n";
		the_content += "display:none;}\n";
		the_content += "</style>\n";
		the_content += "<link rel='stylesheet' rev='stylesheet' href='skin1/style/main.css' type='text/css' media='all' />\n";
		the_content += "<object id=\"WebBrowser\" width=0 height=0 classid=\"CLSID:8856F961-340A-11D0-A96B-00C04FD705A2\"></object>\n";
		the_content += "<center><h3><b>";
		the_content += table_title;
		the_content += "</b></h3>";		
		the_content += document.all(the_table).outerHTML;			
		the_content = the_content.replace(/ style=\"[^\"]*\"/g, "");
		the_content = the_content.replace(/ onclick=\"[^\"]*\"/g, "");
		the_content = the_content.replace(/ mode=\"(false|true)"/g, "");
		the_content = the_content.replace(/ oBgc=\"[\w#\d]*\"/g, "");
		the_content = the_content.replace(/ oFc=\"[\w#\d]*\"/g, "");
		the_content = the_content.replace(/<span>(5|6)<\/span>/gi, "");
		the_content = the_content.replace(/<DIV contentEditable=false>(.*)<\/DIV>/ig, "$1");
		the_content += "\n<br><div class='noPrin'><input type='button' class='button2' value='页面设置' onclick=\"WebBrowser.ExecWB(8,1)\">";
		the_content += "<input type='button' class='button2' value='打印预览' onclick=\"WebBrowser.ExecWB(7,1)\">";
		the_content += "<input type='button' class='button2' value='直接打印' onclick=\"WebBrowser.ExecWB(6,6)\">";
		the_content += "<\/div>";
		//confirm(the_content);
		var newwin = window.open("about:blank", "_blank", "");
		newwin.document.open();
		newwin.document.write(the_content);
		newwin.document.close();
		newwin = null;
	}
	function prtlt(){
		var prtname = $('tjtitle').innerText;
		expTabdy('rsTable',prtname,'');
	}
	</script>

	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<html:form action="/byssytj.do" method="post">

			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：就业管理 - 统计分析 - 毕业生生源统计
				</div>
			</div>
			<fieldset>
				<legend>
					统计查询
				</legend>
				<div>
					<br>
					&nbsp;&nbsp;毕业年度：
					<html:select property="nd" style="width:80px" styleId="nd">
						<html:options collection="ndList" labelProperty="nd" property="nd"/>
					</html:select>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="xydm" onchange="initZyList();"
								styleClass="select" style="width:180px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								<html:select property="zydm" onchange="" style="width:180px;display: none" 
								styleClass="select" styleId="zy">
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="querygo('tj');" style="width:80px">
						统 计
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<!--					<button class="button2" onclick="querygo('dc');"-->
<!--						style="width:80px">-->
<!--						导 出-->
<!--					</button>-->
					<br>
					&nbsp;
				</div>
			</fieldset>

			<logic:empty name="rssydn">
				<br />
				<br />
				<p align="center">
					未找到任何记录！
				</p>
			</logic:empty>
			<logic:notEmpty name="rssydn">
			
				<fieldset>
					<legend align="center">
						<font color="blue">
							<span id="tjtitle"></span>
						</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<td rowspan="1" nowrap="nowrap">
										人数
									</td>
									<td rowspan="2" nowrap="nowrap">
										合计
									</td>
									<td rowspan="2" nowrap="nowrap">
										
									</td>
									<td rowspan="2" nowrap="nowrap">
										北京
									</td>
									<td rowspan="2" nowrap="nowrap">
										天津
									</td>
									<td rowspan="2" nowrap="nowrap">
										黑龙江
									</td>
									<td rowspan="2" nowrap="nowrap">
										吉林
									</td>
									<td rowspan="2" nowrap="nowrap">
										辽宁
									</td>
									<td rowspan="2" nowrap="nowrap">
										山西
									</td>
									<td rowspan="2" nowrap="nowrap">
										山东
									</td>
									<td rowspan="2" nowrap="nowrap">
										河北
									</td>
									<td rowspan="2" nowrap="nowrap">
										河南
									</td>
									<td rowspan="2" nowrap="nowrap">
										陕西
									</td>
									<td rowspan="2" nowrap="nowrap">
										甘肃
									</td>
									<td rowspan="2" nowrap="nowrap">
										内蒙
									</td>
									<td rowspan="2" nowrap="nowrap">
										新疆
									</td>
									<td rowspan="2" nowrap="nowrap">
										上海
									</td>
									<td rowspan="2" nowrap="nowrap">
										江西
									</td>
									
									<td rowspan="2" nowrap="nowrap">
										江苏
									</td>
									<td rowspan="2" nowrap="nowrap">
										浙江
									</td>
									<td rowspan="2" nowrap="nowrap">
										安徽
									</td>
									<td rowspan="2" nowrap="nowrap">
										福建
									</td>
									<td rowspan="2" nowrap="nowrap">
										湖北
									</td>
									<td rowspan="2" nowrap="nowrap">
										湖南
									</td>
									<td rowspan="2" nowrap="nowrap">
										四川
									</td>
									<td rowspan="2" nowrap="nowrap">
										重庆
									</td>
									<td rowspan="2" nowrap="nowrap">
										云南
									</td>
									<td rowspan="2" nowrap="nowrap">
										广东
									</td>
									<td rowspan="2" nowrap="nowrap">
										广西
									</td>
									<td rowspan="2" nowrap="nowrap">
										贵州
									</td>
									<td rowspan="2" nowrap="nowrap">
										海南
									</td>
								</tr>
								<tr>
									<td rowspan="2" nowrap="nowrap">
										专业
									</td>
								</tr>
							</thead>
							<logic:iterate id="rszy1" name="rssydn">
							<logic:notEqual name="rszy1" property="zymc" value="">
							<tr>
								<td><bean:write name="rszy1" property="zymc"/></td>
								<td><bean:write name="rszy1" property="hj"/></td>
								<td><bean:write name="rszy1" property="xb"/></td>
								<td><bean:write name="rszy1" property="bj"/></td>
								<td><bean:write name="rszy1" property="tj"/></td>
								<td><bean:write name="rszy1" property="hlj"/></td>
								<td><bean:write name="rszy1" property="jl"/></td>
								<td><bean:write name="rszy1" property="ll"/></td>
								<td><bean:write name="rszy1" property="sxty"/></td>
								<td><bean:write name="rszy1" property="sd"/></td>
								<td><bean:write name="rszy1" property="hb"/></td>
								<td><bean:write name="rszy1" property="hn"/></td>
								<td><bean:write name="rszy1" property="sxxa"/></td>
								<td><bean:write name="rszy1" property="gs"/></td>
								<td><bean:write name="rszy1" property="nm"/></td>
								<td><bean:write name="rszy1" property="xj"/></td>
								<td><bean:write name="rszy1" property="sh"/></td>
								<td><bean:write name="rszy1" property="jx"/></td>
								<td><bean:write name="rszy1" property="js"/></td>
								<td><bean:write name="rszy1" property="zj"/></td>
								<td><bean:write name="rszy1" property="ah"/></td>
								<td><bean:write name="rszy1" property="fj"/></td>
								<td><bean:write name="rszy1" property="hbwh"/></td>
								<td><bean:write name="rszy1" property="hncs"/></td>
								<td><bean:write name="rszy1" property="sc"/></td>
								<td><bean:write name="rszy1" property="cq"/></td>
								<td><bean:write name="rszy1" property="yn"/></td>
								<td><bean:write name="rszy1" property="gd"/></td>
								<td><bean:write name="rszy1" property="gx"/></td>
								<td><bean:write name="rszy1" property="gz"/></td>
								<td><bean:write name="rszy1" property="hnd"/></td>
								</tr>
								</logic:notEqual>
							</logic:iterate>
							<logic:iterate id="rszy1" name="rssyvn" length="1">
							<tr>
							<td><bean:write name="rszy1" property="bkhj"/></td>
								<td><bean:write name="rszy1" property="hj"/></td>
								<td><bean:write name="rszy1" property="xb"/></td>
								<td><bean:write name="rszy1" property="bj"/></td>
								<td><bean:write name="rszy1" property="tj"/></td>
								<td><bean:write name="rszy1" property="hlj"/></td>
								<td><bean:write name="rszy1" property="jl"/></td>
								<td><bean:write name="rszy1" property="ll"/></td>
								<td><bean:write name="rszy1" property="sxty"/></td>
								<td><bean:write name="rszy1" property="sd"/></td>
								<td><bean:write name="rszy1" property="hb"/></td>
								<td><bean:write name="rszy1" property="hn"/></td>
								<td><bean:write name="rszy1" property="sxxa"/></td>
								<td><bean:write name="rszy1" property="gs"/></td>
								<td><bean:write name="rszy1" property="nm"/></td>
								<td><bean:write name="rszy1" property="xj"/></td>
								<td><bean:write name="rszy1" property="sh"/></td>
								<td><bean:write name="rszy1" property="jx"/></td>
								<td><bean:write name="rszy1" property="js"/></td>
								<td><bean:write name="rszy1" property="zj"/></td>
								<td><bean:write name="rszy1" property="ah"/></td>
								<td><bean:write name="rszy1" property="fj"/></td>
								<td><bean:write name="rszy1" property="hbwh"/></td>
								<td><bean:write name="rszy1" property="hncs"/></td>
								<td><bean:write name="rszy1" property="sc"/></td>
								<td><bean:write name="rszy1" property="cq"/></td>
								<td><bean:write name="rszy1" property="yn"/></td>
								<td><bean:write name="rszy1" property="gd"/></td>
								<td><bean:write name="rszy1" property="gx"/></td>
								<td><bean:write name="rszy1" property="gz"/></td>
								<td><bean:write name="rszy1" property="hnd"/></td>
								</tr>
							</logic:iterate>
							<logic:iterate id="rszy1" name="rssydv1" length="1">
							<tr>
							<td><bean:write name="rszy1" property="bkhj"/></td>
								<td><bean:write name="rszy1" property="hj"/></td>
								<td><bean:write name="rszy1" property="xb"/></td>
								<td><bean:write name="rszy1" property="bj"/></td>
								<td><bean:write name="rszy1" property="tj"/></td>
								<td><bean:write name="rszy1" property="hlj"/></td>
								<td><bean:write name="rszy1" property="jl"/></td>
								<td><bean:write name="rszy1" property="ll"/></td>
								<td><bean:write name="rszy1" property="sxty"/></td>
								<td><bean:write name="rszy1" property="sd"/></td>
								<td><bean:write name="rszy1" property="hb"/></td>
								<td><bean:write name="rszy1" property="hn"/></td>
								<td><bean:write name="rszy1" property="sxxa"/></td>
								<td><bean:write name="rszy1" property="gs"/></td>
								<td><bean:write name="rszy1" property="nm"/></td>
								<td><bean:write name="rszy1" property="xj"/></td>
								<td><bean:write name="rszy1" property="sh"/></td>
								<td><bean:write name="rszy1" property="jx"/></td>
								<td><bean:write name="rszy1" property="js"/></td>
								<td><bean:write name="rszy1" property="zj"/></td>
								<td><bean:write name="rszy1" property="ah"/></td>
								<td><bean:write name="rszy1" property="fj"/></td>
								<td><bean:write name="rszy1" property="hbwh"/></td>
								<td><bean:write name="rszy1" property="hncs"/></td>
								<td><bean:write name="rszy1" property="sc"/></td>
								<td><bean:write name="rszy1" property="cq"/></td>
								<td><bean:write name="rszy1" property="yn"/></td>
								<td><bean:write name="rszy1" property="gd"/></td>
								<td><bean:write name="rszy1" property="gx"/></td>
								<td><bean:write name="rszy1" property="gz"/></td>
								<td><bean:write name="rszy1" property="hnd"/></td>
								</tr>
							</logic:iterate>
							<logic:iterate id="rszy1" name="rssydv2" length="1">
							<tr>
							<td><bean:write name="rszy1" property="bkhj"/></td>
								<td><bean:write name="rszy1" property="hj"/></td>
								<td><bean:write name="rszy1" property="xb"/></td>
								<td><bean:write name="rszy1" property="bj"/></td>
								<td><bean:write name="rszy1" property="tj"/></td>
								<td><bean:write name="rszy1" property="hlj"/></td>
								<td><bean:write name="rszy1" property="jl"/></td>
								<td><bean:write name="rszy1" property="ll"/></td>
								<td><bean:write name="rszy1" property="sxty"/></td>
								<td><bean:write name="rszy1" property="sd"/></td>
								<td><bean:write name="rszy1" property="hb"/></td>
								<td><bean:write name="rszy1" property="hn"/></td>
								<td><bean:write name="rszy1" property="sxxa"/></td>
								<td><bean:write name="rszy1" property="gs"/></td>
								<td><bean:write name="rszy1" property="nm"/></td>
								<td><bean:write name="rszy1" property="xj"/></td>
								<td><bean:write name="rszy1" property="sh"/></td>
								<td><bean:write name="rszy1" property="jx"/></td>
								<td><bean:write name="rszy1" property="js"/></td>
								<td><bean:write name="rszy1" property="zj"/></td>
								<td><bean:write name="rszy1" property="ah"/></td>
								<td><bean:write name="rszy1" property="fj"/></td>
								<td><bean:write name="rszy1" property="hbwh"/></td>
								<td><bean:write name="rszy1" property="hncs"/></td>
								<td><bean:write name="rszy1" property="sc"/></td>
								<td><bean:write name="rszy1" property="cq"/></td>
								<td><bean:write name="rszy1" property="yn"/></td>
								<td><bean:write name="rszy1" property="gd"/></td>
								<td><bean:write name="rszy1" property="gx"/></td>
								<td><bean:write name="rszy1" property="gz"/></td>
								<td><bean:write name="rszy1" property="hnd"/></td>
								</tr>
							</logic:iterate>
					</table>
				</fieldset>
				<script language="javascript">
					$('tjtitle').innerText = $('nd').options[$('nd').selectedIndex].text+'届'+$('xydm').options[$('xydm').selectedIndex].text;
				</script>
			</logic:notEmpty>
			 
			<div class="buttontool" id="btn"
				style="position: absolute;left:0px;top:100px" width="98%">
				  &nbsp;&nbsp;&nbsp;&nbsp;
				 <button class="button2" onclick="prtlt();" style="width: 100px">
				打印列表
 				 </button>
			</div>

		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>