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
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">	
	function addWsgl(){
		showTopWin('/xgxt/zgdd_jxgl.do?method=gfswsglUpdate&doType=add',550,450)
	}
	function editWsgl(){
		if(curr_row == null){
			alert('请选择要修改的信息!');
			return false;
		}
		var xh = curr_row.cells[1].innerText;
		var xn = curr_row.cells[5].innerText;
		var xq = curr_row.cells[6].innerText;
		var zs = curr_row.cells[9].innerText;
		var pk=xh+xn+xq+zs;
		showTopWin('/xgxt/zgdd_jxgl.do?method=gfswsglUpdate&doType=edit&pk='+pk,550,450)
	}
	function viewWsgl(){
		if(curr_row == null){
			alert('请选择要修改的信息!');
			return false;
		}
		var xh = curr_row.cells[1].innerText;
		var xn = curr_row.cells[5].innerText;
		var xq = curr_row.cells[6].innerText;
		var zs = curr_row.cells[9].innerText;
		var pk=xh+xn+xq+zs;
		showTopWin('/xgxt/zgdd_jxgl.do?method=gfswsglUpdate&doType=view&pk='+pk,550,450)
	}
	function delWsgl(){
		var num=$("num").value;
		var flg = true;
		if(num != null && num >0){
			for(var i=0;i<num;i++){
				if($("checkVal"+i)){
					if ($("checkVal"+i).checked){
						flg = false;;
						break;
					}
				}
			}
		}
		if(flg){
			alert('请勾选要删除的信息!');
			return false;
		}
		if (confirm("确认要删除勾选的信息吗?")) {
			refreshForm('/xgxt/zgdd_jxgl.do?method=gfswsgl&doType=del');
		}
	}
	function printWsgl(){
		var xn = $("xn").value;
		var xq = $("xq").value;
		var zs = $("zs").value;	
		var kssj = $("kssj").value;
		var jssj = $("jssj").value;
		
		if(xn == "" || xq == "" || zs == ""){
			alert("请确认需要统计的学年，学期以及周数");
			return false；
		}
		if(kssj == "" || jssj == ""){
			alert("请确认需要导出的时间段");
			return false；
		}
		var url = "/xgxt/zgdd_jxgl.do?method=gfswsgl&doType=print&xn="+xn+"&xq="+xq+"&zs="+zs+"&kssj="+kssj+"&jssj="+jssj;
		if($("xh").value != ""){
			url += "&xh="+$("xh").value;
		}
		if($("nj").value != ""){
			url += "&nj="+$("nj").value;
		}
		if($("xm").value != ""){
			url += "&xm="+$("xm").value;
		}
		if($("xy").value != ""){
			url += "&xydm="+$("xy").value;
		}
		if($("zy").value != ""){
			url += "&zydm="+$("zy").value;
		}
		if($("bj").value != ""){
			url += "&bjdm="+$("bj").value;
		}
		if($("lddm").value != ""){
			url += "&lddm="+$("lddm").value;
		}
		if($("wsqk").value != ""){
			url += "&wsqk="+$("wsqk").value;
		}
		window.open(url);
	}
	function disabled() {
        if($("userType")){
            var ele="";
	        if ($("userType").value == "xy") {
	             ele ="xy";
	             var tmp = ele.split("-");
		         for (i = 0; i < tmp.length; i++) {
		 	       document.getElementById(tmp[i]).disabled = true;
		         }		      
	        }else if($("userType").value == "stu"){
	             ele ="nj-xy-zy-bj-xh-xm";
	             var tmp = ele.split("-");
		         for (i = 0; i < tmp.length; i++) {
		 	       document.getElementById(tmp[i]).disabled = true;
		         }	        
	        }

        }
    }
	</script>
	<body onload="disabled();">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/pjpy/pjpy_zjlg.js"></script>
		<script type="text/javascript" src="js/jxglFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getJxLdjzList.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getJxglDAO.js'></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<html:form action="/zgdd_jxgl" method="post">
			<input type="hidden" name="num" id="num" value="<bean:write name="rsNum"/>"/>
			<input type="hidden" id="userName" name="userName" value="<bean:write name="userName" scope="session"/>"/>
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" name="xyV" id="xyV" value=""/>
			<input type="hidden" name="zyV" id="zyV" value=""/>
			<input type="hidden" name="bjV" id="bjV" value=""/>
			<input type="hidden" name="njV" id="njV" value=""/>
			<input type="hidden" name="gfsxx" id="gfsxx" value=""/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：<bean:write name="title" />
				</div>
			</div>
			<div class="rightcontent">
				<fieldset>
					<legend>
						查 询
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									年级：
									<html:select property="nj" 
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
									学年：
									<html:select property="xn" >
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
									学期：
									<html:select property="xq" >
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
									&nbsp;&nbsp;&nbsp;学号：
									<html:text property="xh" style="width:85px" maxlength="20"></html:text>
									&nbsp;&nbsp;&nbsp;姓名：
									<html:text property="xm" style="width:85px" maxlength="20"></html:text>								
								</td>
								<td width="10" rowspan="3" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button type="button" class="button2" style="height:40px" id="search_go"
										onclick="allNotEmpThenGo('/xgxt/zgdd_jxgl.do?method=gfswsgl')">
										查询
									</button>
								</td>
							</tr>
							<tr>
								<td align="left" nowrap>
									<bean:message key="lable.xsgzyxpzxy" />：
									<html:select property="xydm"  styleId="xy"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									&nbsp;&nbsp;专业：
									<html:select property="zydm"  styleId="zy"
										onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
									&nbsp;&nbsp;班级：
									<html:select property="bjdm"  styleId="bj">
										<html:option value=""></html:option>
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td align="left" nowrap>
									连队名称：
									<html:select property="lddm"  styleId="lddm">
										<html:option value=""></html:option>
										<html:options collection="ldList" property="dm"
											labelProperty="mc" />
									</html:select>
									&nbsp;&nbsp;卫生情况：
									<html:select property="wsqk"  styleId="wsqk"
										onchange="">
										<html:option value=""></html:option>
										<html:options collection="wsPfList" property="dm"
											labelProperty="mc" />
									</html:select>
									&nbsp;&nbsp;周数：
									<html:select property="zs"  styleId="zs">
										<html:option value=""></html:option>
										<html:options collection="zsList" property="dm"
											labelProperty="mc" />	
									</html:select>
									&nbsp;&nbsp;检查时间:
								    <html:text property="kssj" styleId="kssj" onblur="dateFormatChg(this)"
									style="cursor:hand;width:80px" 
									onclick="return showCalendar('kssj','y-mm-dd','aa');" />--
									<html:text property="jssj" styleId="jssj" onblur="dateFormatChg(this)"
									style="cursor:hand;width:80px"
									onclick="return showCalendar('jssj','y-mm-dd','aa');" />
								</td>
							</tr>
						</thead>
					</table>
				</fieldset>
				<logic:empty name="rs">
					<p align="center">
						未找到任何记录！
					</p>
				</logic:empty>
				<logic:notEmpty name="rs">
					<fieldset>
						<legend>
							记录数：
							<bean:write name="rsNum" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="blue">提示：单击表头可以排序</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<td nowrap>
										
									</td>
									<logic:iterate id="tit" name="topTr" offset="0">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s" indexId="index">
								<tr onclick="rowOnClick(this);" style="cursor:hand"
									ondblclick="viewWsgl();">
									<td align=center>
										<input type="checkbox" id="checkVal${index}" name="checkVal" value="<bean:write name="s" property="xh"/><bean:write name="s" property="xn"/><bean:write name="s" property="xq"/><bean:write name="s" property="zs"/>" />
									</td>
									<td align="center">
										<bean:write name="s" property="xh" />
									</td>
									<td align="center">
										<bean:write name="s" property="xm" />
									</td>
									<td align="center">
										<bean:write name="s" property="xb" />
									</td>
									<td align="center">
										<bean:write name="s" property="nj" />
									</td>
									<td align="center">
										<bean:write name="s" property="xn" />
									</td>
									<td align="center">
										<bean:write name="s" property="xqmc" />
									</td>
									<td align="center">
										<bean:write name="s" property="bjmc" />
									</td>
									<td align="center">
										<bean:write name="s" property="ldmc" />
									</td>
									<td align="center">
										第<bean:write name="s" property="zs" />周
									</td>
									<td align="center">
										<bean:write name="s" property="wsqk" />
									</td>
								</tr>
							</logic:iterate>
						</table>
						<TABLE width="100%" id="Table" class="tbstyle">
							<TR>
								<TD height=3></TD>
							</TR>
							<TR>
								<TD>
									<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=zgddJxglForm"></jsp:include>
								</TD>
							</TR>
							<TR>
								<TD height=3></TD>
							</TR>
						</TABLE>
					</fieldset>
				</logic:notEmpty>
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				<center>
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">		
						<logic:equal name="userType" value="gfb">				
							<logic:equal name="writeAble" value="yes">
								<button type="button" class="button2"
									onclick="addWsgl()"
									style="width:80px">
									增 加
								</button>
								&nbsp;
								<button type="button" class="button2"
									onclick="editWsgl();"
									style="width:80px">
									修改
								</button>
								&nbsp;
								<button type="button" class="button2"
									onclick="delWsgl()"
									style="width:80px">
									删 除
								</button>
							&nbsp;
							<button type="button" class="button2" onclick="impAndChkData()"
								style="width:80px">
								导入数据
							</button>
							&nbsp;							
							</logic:equal>
							</logic:equal>
							<button type="button" class="button2" onclick="printWsgl()"
								style="width:80px">
								导出数据
							</button>													
					</div>	
				</center>
				<div id="tmpdiv"></div>
			</div>
		</html:form>
		<logic:equal value="yes" name="result">
			<script>
				//alert("操作成功!");
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				//alert("操作失败");
			</script>
		</logic:equal>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
