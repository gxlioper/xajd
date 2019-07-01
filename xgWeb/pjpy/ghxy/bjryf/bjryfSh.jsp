<%@ page language="java" contentType="text/html; charset=GBK"%>

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
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />

	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getInsureInfo.js"></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>

	<script language="javascript">
		function modi(url){
			if(curr_row != null){
				showOpenWindow(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
				return true;
			}else{
				alert('请选择要修改的数据行！');
				return false;
			}
		}
		var shjg="";
		var url="";
		function chkData(){
			var userType="";
			var isNjbzr="";
			userType=$("userType").value;
			shjg=$("shjg").value;
			var dj="";
			isNjbzr=$("isNjbzr").value;
			if($("stdj")){
				dj=$("stdj").value;
			}
			if(dj!=""){
			    url="ghxyBjryf.do?method=bjryfSh&doType=modi&shjg="+shjg;
			    url+="&dj="+dj;
				dataBatch(url);
			}else{
				
				if(userType=="xx" || userType=="admin"){
				     url="ghxyBjryf.do?method=bjryfSh&doType=modi&shjg="+shjg;
					dataBatch(url);
				}else{
				    alert("请先选择班级荣誉分等级！");
				}
				return false;
			}
		}
		function tg(){
			$("shjg").value='通过';
		}
		function btg(){
			$("shjg").value='不通过';
		}
		function checkQx(){
			var isNjbzr="";
			if($("isNjbzr")){
				isNjbzr=$("isNjbzr").value;
			}
			if(isNjbzr=="true"){
			 	djzh();
			}else{
			 	chkData();
			}
			
		}
		function djzh(){
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
		dd_html += "----------------请选择等级---------------";
		dd_html += "</td>";
			dd_html += "</tr>";
		dd_html += "</thead>";
		dd_html += "<tbody>";
	
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right' width='30%'>";
		dd_html += "请选择表:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<select name='stdj' id ='stdj'>" 
		dd_html += $('dj').innerHTML;
		dd_html += "</td>";
		dd_html += "</tr>";
	
		dd_html += "<tr bgcolor='EEF4F9'>";
		dd_html += "<td colspan='2' align='center'>";
		dd_html += "<button type='button' class='button2' onclick='chkData()'>确定</button>";
		dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
		dd_html += "<button type='button' class='button2' onclick='closeAdd1()'>关闭</button>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tbody>";
		dd_html += "</table>";
		dd_html += "</div>";
		document.getElementById('tmpdiv1').innerHTML = dd_html;
	}
	</script>
	<body>
		<html:form action="ghxyBjryf" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<logic:equal name="isFdy" value="true">
				<logic:notEqual name="isNjzr" value="true">
					<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
				</logic:notEqual>
			</logic:equal>
			<input type="hidden" name="userName" id="userName" value="${userName}" />
			<input type="hidden" name="realTable" id="realTable" value="nbty_xszz_jtjjkns" />
			<input type="hidden" name="shjg" id="shjg" styleId="shjg"/>
			<input type="hidden" name="userType" id="userType" value="${userType}" styleId="userType"/>
			<input type="hidden" name="isNjbzr" id="isNjbzr" value="${isNjbzr}" styleId="isNjbzr"/>
			<div class="title">
				<div class="title_img" id="title_m">
					所在位置：${title }		
				</div>
			</div>			
			<fieldset>
				<legend>
					查询条件
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								<!-- 班级荣誉等级隐藏域 -->
								<html:select property="save_djdm" style="display : none" styleId='dj' onchange="">
									<html:options collection="ryfdjList" property="djdm"
										labelProperty="djmc" />
								</html:select>
								&nbsp;&nbsp;学年：
								<html:select property="xn" disabled="true" value="${xn}">
									<html:option value=""></html:option>
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								<html:hidden property="queryequals_xn"  value="${xn}"/>
								&nbsp;&nbsp;学期：
								<html:select property="xq" disabled="true" value="${xq}">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
								<html:hidden property="queryequals_xq"  value="${xq}"/>
								&nbsp;&nbsp;年级：
								<html:select property="queryequals_nj"  styleId="nj"
										onchange="initZyList();initBjList();">
									<logic:notEqual name="isNjbzr" value="true">
										<html:option value=""></html:option>
									</logic:notEqual>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								&nbsp;&nbsp;批次：
								<html:select property="queryequals_pc"  >
									<html:options collection="ryfPcList" property="pcdm"
										labelProperty="pcmc" />
								</html:select>
								
							</td>
							<td width="10" align="center" valign="middle" rowspan="2">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" id="search_go"
									onclick="allNotEmpThenGo('ghxyBjryf.do?method=bjryfSh&doType=qry')" style="height: 40px">
									查 询
								</button>
								
							</td>
						</tr>
						<tr>
						<td>
							<!-- 年级主任 -->
							<logic:equal name="isNjzr" value="true">
							  	&nbsp;&nbsp;<bean:message key="lable.xb" />：
							  	<html:select  property="queryequals_xydm" style="width:180px"
							  		 styleId="xy" onchange="initZyList();initBjList();">
							     	<html:option value=""></html:option>
							     	<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
								 </html:select>
							 </logic:equal>
							 
							 <!-- 非年级主任 -->
							 <logic:notEqual name="isNjzr" value="true">
							 	<!-- 辅导员 -->
							 	<logic:equal name="userType" value="xy">
							 		&nbsp;&nbsp;<bean:message key="lable.xb" />：
							 		<html:select  property="xydm" disabled="true" style="width:180px" 
							 		 value="${userDep}" styleId="xy" onchange="initZyList();initBjList();">
							    		 <html:option value=""></html:option>
							    		 <html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								 </html:select>
								  <html:hidden property="queryequals_xydm" value="${userDep}"/>
							 	</logic:equal>
							 	<!-- 非辅导员 -->
								 <logic:notEqual name="userType" value="xy">
								 &nbsp;&nbsp;<bean:message key="lable.xb" />：
								  <html:select  property="queryequals_xydm" style="width:180px" 
							 		  styleId="xy" onchange="initZyList();initBjList();">
							    		 <html:option value=""></html:option>
							    		 <html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								 </html:select>
								
								 </logic:notEqual>
							 </logic:notEqual>
							 
							&nbsp;&nbsp;专业：
							<html:select property="queryequals_zydm" style="width:180px" styleId="zy"
								onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
							&nbsp;&nbsp;班级：
							<html:select property="queryequals_bjdm" style="width:180px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
						</tr>
						
					</thead>
				</table>
			</fieldset>
			<logic:empty name="rsNum">
				<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					未找到任何记录！
				</p>
				</logic:empty>
			</logic:empty>
			<div id="tmpdiv1">
				
				</div>
			<logic:notEmpty name="rsNum">
				<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						记录数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：单击表头可以排序</font>
					</legend>
					<table width="99%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox"  name="all" value="all" onclick="chec()">
									
								</td>
								<logic:iterate id="tit" name="topTr" offset="4" indexId="index">
									
										<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
							style="cursor:hand">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="checkbox" name="primarykey_cbv" id="pkV" 
											   value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" 
											   <logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>>
											   	<input type="hidden" name="xqArr" value="<logic:iterate id="v" name="s" offset="3" length="1">${v}</logic:iterate>"/>
												<input type="hidden" name="xnArr" value="<logic:iterate id="v" name="s" offset="4" length="1">${v}</logic:iterate>"/>
												<input type="hidden" name="checkVal" value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>"/>
												<input type="hidden" name="plbjdm" value="<logic:iterate id="v" name="s" offset="2" length="1">${v}</logic:iterate>"/>
									</logic:iterate>
								</td>
								
								<logic:iterate id="v" name="s" offset="4">
									<td nowrap>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
				<TABLE width="99%" id="Table" class="tbstyle">
					<TR>
						<TD height=3></TD>
					</TR>
					<TR>
						<TD>
							<jsp:include flush="true"
								page="/sjcz/turnpage.jsp?form=ghxyBjryfForm"></jsp:include>
						</TD>
					</TR>
					<TR>
						<TD height=3></TD>
					</TR>
				</TABLE>
				</logic:notEmpty>
			</logic:notEmpty>
			   <div class="buttontool" id="btn" style="position: absolute;left:1%;top:100px" width="100%">
			   	<logic:equal name="writeAble" value="yes">
			 		<button type="button" class="button2" onclick="modi('ghxyBjryf.do?method=bjryfShOne&doType=view')"
						style="width:80px">
						单个审核
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="tg();checkQx();"
						style="width:80px">
						审核通过
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="btg();checkQx();"
						style="width:80px">
						审核不通过
					</button>
				</logic:equal>
					</div>
				<script language="javascript">
						document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
						document.getElementById("btn").style.width = "96%";
						window.setInterval("initBTNTool('btn')",1);
				</script>
				<logic:present name="result">
				<logic:equal name="bjbl" value="no">
					<script>
						alert('超出该等级限定的班级比例！');
					</script>		
				</logic:equal>
				<logic:equal value="true" name="result">
					<script>
						alert('操作成功！');
					</script>			
				</logic:equal>
				<logic:notEqual value="true" name="result">
					<script>
						alert('操作失败！');
					</script>
				</logic:notEqual>
				</logic:present>
		</html:form>
	</body>
</html>
