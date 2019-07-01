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
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script type="text/javascript" src="js/sharedFunction.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>		
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript">	
	function chec(){
      for(i=0;i<document.getElementsByName("pk").length;i++){
      	document.getElementsByName("pk")[i].checked=document.getElementsByName("gwmc")[0].checked;
      }
    }
    
    function show(url){    	
    	var xh = curr_row.cells[0].innerText;
    	
    	url += "&xh=";
    	url += xh;
    	
    	showTopWin(url);
    }
</script>
	<body onload="check_user();">
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<center>

			<html:form action="/xbemyStuStatus.do" method="post">
				<input type="hidden" name="userType" value="<bean:write name="userType"/>" />
				<input type="hidden" name="tableName" value="view_xbemy_xjbdsqb" />
				<input type="hidden" name="realTable" value="xbemy_xjbdsqb" />
				<input type="hidden" name="xyV" value="" />
				<input type="hidden" name="zyV" value="" />
				<input type="hidden" name="bjV" value="" />
				<div class="title">
					<div class="title_img" id="title_m">
						当前位置：学生信息 - 学籍异动申请 - 学籍变动申请结果查询
					</div>
				</div>
				<fieldset>
					<legend>
						查 询 &amp; 操 作
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									学号：
									<html:text property="xh" style="width:120px"/>									
									&nbsp;&nbsp;姓名：
									<html:text property="xm" style="width:120px"/>	
									&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
									<html:select property="xydm"
										styleId="xy" style="width:180px" onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<td width="10" rowspan="2" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button class="button2" style="height:40px" id="search_go"
										onclick="allNotEmpThenGo('xbemyStuStatus.do?method=TransferStatusQuery')">
										查询
									</button>
								</td>
							</tr>
							<tr>
								<td>	
									专业：
									<html:select property="zydm"
										styleId="zy" style="width:180px" onchange="initBjList();">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
									&nbsp;&nbsp;班级：
									<html:select property="bjdm"
										styleId="bj" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
									&nbsp;&nbsp;变动类型：
									<html:select property="zxlx">
									<html:option value=""></html:option>
									<html:option value="转入">转入</html:option>
									<html:option value="转出">转出</html:option>
									</html:select>
								</td>
							</tr>
						</thead>
					</table>
				</fieldset>
				<logic:empty name="rs">
					<br />
					<br />
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
							<font color="blue">提示：双击一行查看详细信息；单击表头可以排序</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">									
									<logic:iterate id="tit" name="topTr" offset="0">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
									ondblclick="javascript:if(curr_row==null){alert('请选择要修改的记录!');return false} else{refreshForm('xbemyStuStatus.do?method=showTransferStuStatus&xh='+curr_row.cells[1].innerText+'&sqrq='+curr_row.cells[0].innerText)}">
									<logic:iterate id="v" name="s" offset="0">
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>	
				<logic:equal value="yes" name="writeAble" scope="request">
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
						<button class="button2" onclick="refreshForm('xbemyStuStatus.do?method=showTransferSpecialty')"
							style="width:80px">
							增 加
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="javascript:if(curr_row==null){alert('请选择要修改的记录!');return false} else{refreshForm('xbemyStuStatus.do?method=showTransferStuStatus&xh='+curr_row.cells[1].innerText+'&sqrq='+curr_row.cells[0].innerText)}" style="width:80px">
							修 改
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="javascript:if(curr_row==null){alert('请选择要修改的记录!');return false} else{if(confirm('您确定删除该记录吗？'))refreshForm('xbemyStuStatus.do?method=TransferStatusQuery&doType=del&xh='+curr_row.cells[1].innerText+'&sqrq='+curr_row.cells[0].innerText)}" style="width:80px">
							删 除
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2"
							onclick="impAndChkData();"
							style="width:80px">
							导入数据
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2"
							onclick="dataExport1('xbemyStuStatus.do?method=expData')"
							style="width:80px">
							导出数据
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="expTab('rsTable','学籍变动申请信息','')">
							打印列表
						</button>
					</div>
					<script language="javascript">
						document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
						document.getElementById("btn").style.width = "96%";
						window.setInterval("initBTNTool('btn')",1);
					</script>
				</logic:equal>
				<logic:notEmpty name="result">
				<logic:equal value="true" name="result">
					<script>
						alert("操作成功！");
						Close();
						document.getElementById('search_go').click();						
					</script>
				</logic:equal>
				<logic:equal name="result" value="false">
					<script>
						alert("操作失败!");
					</script>
				</logic:equal>
			</logic:notEmpty>
			</html:form>
		</center>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>

