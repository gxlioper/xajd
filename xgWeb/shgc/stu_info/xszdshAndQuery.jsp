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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="/xgxt/js/function.js"></script>
	<script language="javascript" src="/xgxt/js/stuinfoFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/GetListData.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript">
		var pkVals = "!@!";
		function checkOpertion(){
				var num = 0;			
				var pks = document.getElementsByName('pkV');
				for(var i=0; i<pks.length; i++){
					if(pks[i].checked == true){
						num++;
						pkVals +=pks[i].value+"!@!"; 
					}
				}
				if(num == 0){
					alert("请选择你要操作的记录！");
					return  false;
				}
				return true;
		}
		function auditing(shjg){
			if(checkOpertion()){
				$('pkStr').value= pkVals;
				refreshForm('/xgxt/archives_apply_query.do?doType=auditing&act=auditing&shjg='+shjg);
			}
		}
		function deleteZdxx(){
			if(checkOpertion()){
				$('pkStr').value= pkVals;
				refreshForm('/xgxt/archives_apply_query.do?doType=delete&act=auditing');
			}
		}
		function chec1(){
      		for(i=0;i<document.getElementsByName("pkV").length;i++){
      			document.getElementsByName("pkV")[i].checked=document.getElementsByName("all")[0].checked;
      		}
    	}
    	function viewzdxx(){
    		var array = curr_row.getElementsByTagName('input');
    		var zdlb = $('zdlb').value;
    		var xh = '';
    		if(array.length >1){
    			xh = array[1].value;
    		}else{
    			xh = array[0].value;
    		}
    		showTopWin('/xgxt/stu_archives_apply.do?doType=view&xh='+xh+'&zdlb='+zdlb,600,500);
    	}
    	function viewshjg(){
    		var xh= curr_row.getElementsByTagName('input')[0].value;
			showTopWin('/xgxt/archives_deal.do?doType=view&pkStr='+xh,600,500);
    	}
	</script>
	<body>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<center>
			<html:form action="/archives_apply_query" method="post">
				<input type="hidden" id="act" name="act"
					value="<bean:write name="act" scope="request"/>" />
				<input type="hidden" id="pkStr" name="pkStr" value="" />
				<input type="hidden" id="zdlb" name="zdlb" value="${zdlb }" />
				<input type="hidden" id="tableName" name="tableName" value="${tableName }" />
				<input type="hidden" id="realTable" name="realTable" value="${realTable }" />
				<input type="hidden" name="xyV" value="" />
				<input type="hidden" name="zyV" value="" />
				<input type="hidden" name="bjV" value="" />

				<div class="title">
					<div class="title_img" id="title_m">
						当前位置:学生信息 - 学生档案 -
						<logic:equal value="query" name="act">
							转档申请查询
						</logic:equal>
						<logic:equal value="auditing" name="act">
							转档申请审核
						</logic:equal>
					</div>
				</div>
				<logic:notEqual value="student" name="userOnLine" scope="session">
					<fieldset>
						<legend>
							查 询
						</legend>
						<table width="100%" class="tbstyle">
							<thead>
								<tr>
									<td align="left">
										年度：
										<html:select property="nd" style="width:90px" styleId="nd">
											<html:option value=""></html:option>
											<html:options collection="ndList" property="nd"
												labelProperty="nd" />
										</html:select>
										&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
										<logic:equal name="userType" value="xy" scope="session">
											<html:select property="xydm" style="width:200px"
												 disabled="true"
												styleId="xy">
												<html:option value=""></html:option>
												<html:options collection="xyList" property="xydm"
													labelProperty="xymc" />
											</html:select>
										</logic:equal>
										<logic:notEqual name="userType" value="xy" scope="session">
											<html:select property="xydm" style="width:200px"
												onchange="initZyList();initBj();" 
												styleId="xy">
												<html:option value=""></html:option>
												<html:options collection="xyList" property="xydm"
													labelProperty="xymc" />
											</html:select>
										</logic:notEqual>
										&nbsp;&nbsp;专业：
										<html:select property="zydm" style="width:220px" styleId="zy"
											onchange="initBj();">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
									</td>
									<td width="10" rowspan="2" align="center" valign="middle">
										<button class="button2" style="height:40px" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/archives_apply_query.do?doType=query')">
											查询
										</button>
									</td>
								</tr>
								<tr>
									<td align="left" nowrap>
										班级：
										<html:select property="bjdm" style="width:220px" styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
										&nbsp;&nbsp;学号：
										<html:text property="xh" style="width:100px"/>
										&nbsp;&nbsp;姓名：
										<html:text property="xm" style="width:120px"/>
										<logic:equal value="auditing" name="act">
											&nbsp;&nbsp;学校审核
											<html:select property="xxsh" style="width:80px"
												styleId="xxsh">
												<html:option value=""></html:option>
												<html:option value="未审核">未审核</html:option>
												<html:option value="通过">通过</html:option>
												<html:option value="不通过">不通过</html:option>
											</html:select>
										</logic:equal>
									</td>
								</tr>
							</thead>
						</table>
					</fieldset>
				</logic:notEqual>
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
							<font color="blue">提示：双击一行可以查看相关信息；单击表头可以排序</font>
						</legend>
						<table width="100%" id="rsT" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:equal value="auditing" name="act">
										<td align="center">
											<input type="checkbox" name="all" value="all" id="all"
												onclick="chec1()">
										</td>
									</logic:equal>
									<logic:iterate id="tit" name="topTr" offset="0">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								
									<tr onclick="rowOnClick(this)"
										style="cursor:hand;"
										ondblclick="
										<logic:equal value="auditing" name="act">
											viewzdxx();
										</logic:equal>
										<logic:notEqual value="auditing" name="act">
											viewshjg();
										</logic:notEqual>
										">
										<logic:equal value="auditing" name="act">
											<td>
												<input type="checkbox" value="${s.xh}" name="pkV" id="pkV"/>
											</td>
										</logic:equal>
											<td>
												<input type="hidden" value="${s.xh}" name="xsxh"/>
												${s.xh}
											</td>
											<td>
												${s.xm}
											</td>
											<td>
												${s.xymc}
											</td>
											<td>
												${s.zymc}
											</td>
											<td>
												${s.bjmc}
											</td>
											<td>
												${s.zddwmc}
											</td>
											<td>
												${s.zddwdz}
											</td>
											<td>
												${s.zdlb}
											</td>
											<td>
												${s.xxsh}
											</td>
											<td>
												${s.zcbh}
											</td>
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
									page="/sjcz/turnpage.jsp?form=ShgcForm"></jsp:include>
							</TD>
						</TR>
						<TR>
							<TD height=3></TD>
						</TR>
					</TABLE>
				</logic:notEmpty>
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				<div class="buttontool" id="btn"
					style="position: absolute;left:1%;top:100px" width="100%">
					<logic:equal value="auditing" name="act">
						<button class="button2"
									onclick="auditing('通过');"
									style="width:80px">
									审核通过
								</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2"
									onclick="auditing('不通过');"
									style="width:80px">
									审核不通过
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2"
									onclick="deleteZdxx();"
									style="width:80px">
									删 除
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2"
									onclick="impAndChkData()"
									style="width:80px">
									导 入
						</button>
					</logic:equal>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2"
									onclick="dataExport();"
									style="width:80px">
									导 出
					</button>
				</div>
				<script language="javascript">
						document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
						document.getElementById("btn").style.width = "98%";
						window.setInterval("initBTNTool('btn')",1);
				</script>
				<logic:notEmpty name="result">
						<logic:equal value="true" name="result">
							<script>
							alert("操作成功");
						</script>
						</logic:equal>
						<logic:equal value="false" name="result">
							<script>
							alert("操作失败");							
						</script>
						</logic:equal>
				</logic:notEmpty>
			</html:form>
		</center>
	</body>
</html>
