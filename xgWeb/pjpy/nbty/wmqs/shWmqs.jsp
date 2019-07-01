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
				showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
				return true;
			}else{
				alert('请选择要修改的数据行！');
				return false;
			}
		}
		function checkAjax(){
			if($("lc").value=="null"){
				$("lc").value="";
			}
			if($("qsh").value=="null"){
				$("qsh").value="";
			}
			refreshForm('/xgxt/nbtyWmqs.do?method=shWmqs&doType=qry');
		}
	</script>
	<body>
		<html:form action="nbtyWmqs" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
			<input type="hidden" name="userName" id="userName" value="${userName}" />
			
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：评奖评优 - 审核 - 文明寝室审核					
				</div>
			</div>			
			<fieldset>
				<legend>
					基本参数
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								&nbsp;学年：
								<html:select  property="xn"  styleId="xn">
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
								    &nbsp;楼栋名：
									<html:select property="lddm"  styleId="lddm"
										onchange="getLcList()">
										
										<html:options collection="ldList" property="lddm"
											labelProperty="ldmc" />
									</html:select>
										&nbsp;楼层：
										<html:select property="cs" styleId="lc"
										onchange="getQshList2()">
										
										<html:options collection="lcList" property="dm"
											labelProperty="mc" />
									</html:select>
										&nbsp;寝室号：
										<html:select property="qsh"  styleId="qsh">
										
										<html:options collection="qshList" property="dm"
											labelProperty="mc" />
									</html:select>
							</td>
							<td width="10" rowspan="3" align="center" valign="middle">
								<input type="hidden" name="go" value="go" />
								<button type="button" class="button2" style="height:40px" id="search_go" 
										onclick="checkAjax()">
									查询
								</button>
							</td>
						</tr>
						<tr>
							<td align="left" >	
										&nbsp;<bean:message key="lable.xsgzyxpzxy" />:
							        <logic:equal name="isBzr" value="true">
											<html:select property="xydm" styleId="xy"  style="width:180px"
													onchange="initZyList();initBjList()">
													<html:option value=""></html:option>
													<html:options collection="xyList" property="xydm"
													labelProperty="xymc" />
											</html:select>
										</logic:equal>
										<logic:notEqual name="isBzr" value="true">
											<logic:equal name="isFdy"  value="true">
												<html:select property="xydm" styleId="xy"  style="width:180px"
													onchange="initZyList();initBjList()">
													<html:option value=""></html:option>
													<html:options collection="xyList" property="xydm"
													labelProperty="xymc" />
												</html:select>
											</logic:equal>
											<logic:notEqual name="isFdy" value="true">
												<logic:equal name="userType" value="xy">
													<html:select disabled="true" property="xydm" styleId="xy"  style="width:180px"
													onchange="initZyList();initBjList()">
													<html:option value=""></html:option>
													<html:options collection="xyList" property="xydm"
														labelProperty="xymc" />
													</html:select>
													<html:hidden property="xydm" value="${userDep}"/>	
												</logic:equal>
												<logic:notEqual name="userType" value="xy">
													<html:select property="xydm" styleId="xy"  style="width:180px"
													onchange="initZyList();initBjList()">
													<html:option value=""></html:option>
													<html:options collection="xyList" property="xydm"
													labelProperty="xymc" />
												</html:select>
												</logic:notEqual>
											</logic:notEqual>
										</logic:notEqual>
										&nbsp;专业：
										<html:select property="zydm" styleId="zy" style="width:180px"
											onchange="initBjList();">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>	
										&nbsp;班级：
										<html:select property="bjdm" styleId="bj" style="width:180px">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>				
							</td>							
						</tr>	
						<tr>
							<td>
								<!-- 是班主任 -->
								<logic:equal name="isBzr" value="true">
									&nbsp;班主任审核:
									<html:select property="bzrsh" style="width:180px">
											<html:option value=""></html:option>
											<html:option value="未审核"></html:option>
											<html:option value="通过"></html:option>
											<html:option value="不通过"></html:option>
									</html:select>	
									&nbsp;辅导员审核:
									<html:select property="fdysh" style="width:180px">
											<html:option value=""></html:option>
											<html:option value="未审核"></html:option>
											<html:option value="通过"></html:option>
											<html:option value="不通过"></html:option>
									</html:select>
									&nbsp;<bean:message key="lable.xsgzyxpzxy" />审核:	
									<html:select property="xysh" style="width:180px">
											<html:option value=""></html:option>
											<html:option value="未审核"></html:option>
											<html:option value="通过"></html:option>
											<html:option value="不通过"></html:option>
									</html:select>	
									&nbsp;学校审核:
									<html:select property="xxsh" style="width:180px">
											<html:option value=""></html:option>
											<html:option value="未审核"></html:option>
											<html:option value="通过"></html:option>
											<html:option value="不通过"></html:option>
									</html:select>	
								</logic:equal>
								<logic:notEqual name="isBzr" value="true">
									<logic:equal name="isFdy" value="true">
										&nbsp;班主任审核:
										<html:select property="bzrsh" style="width:180px">
											<html:option value=""></html:option>
											<html:option value="未审核"></html:option>
											<html:option value="通过"></html:option>
											<html:option value="不通过"></html:option>
										</html:select>	
										&nbsp;辅导员审核:
										<html:select property="fdysh" style="width:180px">
											<html:option value=""></html:option>
											<html:option value="未审核"></html:option>
											<html:option value="通过"></html:option>
											<html:option value="不通过"></html:option>
										</html:select>
										&nbsp;<bean:message key="lable.xsgzyxpzxy" />审核:	
										<html:select property="xysh" style="width:180px">
											<html:option value=""></html:option>
											<html:option value="未审核"></html:option>
											<html:option value="通过"></html:option>
											<html:option value="不通过"></html:option>
										</html:select>	
										&nbsp;学校审核:
										<html:select property="xxsh" style="width:180px">
											<html:option value=""></html:option>
											<html:option value="未审核"></html:option>
											<html:option value="通过"></html:option>
											<html:option value="不通过"></html:option>
										</html:select>	

									</logic:equal>
									<logic:notEqual name="isFdy" value="true">
										<logic:equal name="userType" value="xy">
											&nbsp;<bean:message key="lable.xsgzyxpzxy" />审核:	
											<html:select property="xysh" style="width:180px">
												<html:option value=""></html:option>
												<html:option value="未审核"></html:option>
												<html:option value="通过"></html:option>
												<html:option value="不通过"></html:option>
											</html:select>
											&nbsp;学校审核:	
											<html:select property="xxsh" style="width:180px">
												<html:option value=""></html:option>
												<html:option value="未审核"></html:option>
												<html:option value="通过"></html:option>
												<html:option value="不通过"></html:option>
											</html:select>	
										</logic:equal>
										<logic:notEqual name="userType" value="xy">
											&nbsp;学校审核:	
											<html:select property="xxsh" style="width:180px">
												<html:option value=""></html:option>
												<html:option value="未审核"></html:option>
												<html:option value="通过"></html:option>
												<html:option value="不通过"></html:option>
											</html:select>	
										</logic:notEqual>
									</logic:notEqual>
								</logic:notEqual>
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
						<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
					</legend>
					<table width="99%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox"  name="all" value="all" onclick="chec()">
									
								</td>
								<logic:iterate id="tit" name="topTr" offset="1" indexId="index">
									<logic:notEqual name="index" value="1">
										<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
									</logic:notEqual>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								ondblclick="modi('nbtyWmqs.do?method=shOneWmqs&doType=save')" style="cursor:hand">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="checkbox" name="primarykey_cbv" id="pkV" 
											   value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" 
											   <logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>>
									</logic:iterate>
								</td>
								<td>
									<logic:iterate id="v" name="s" offset="2" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="2" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="3">
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
								page="/sjcz/turnpage.jsp?form=nbtyWmqsForm"></jsp:include>
						</TD>
					</TR>
					<TR>
						<TD height=3></TD>
					</TR>
				</TABLE>
			</logic:notEmpty>
				<logic:equal name="writeAbled">
		    <div class="buttontool" id="btn" style="position: absolute;left:1%;top:100px" width="100%">	
					<button type="button" class="button2" onclick="dataBatch('nbtyWmqs.do?method=shWmqs&doType=modi&shjg=通过')"
						style="width:80px">
						审核通过
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="dataBatch('nbtyWmqs.do?method=shWmqs&doType=modi&shjg=不通过')"
						style="width:80px">
						审核不通过
					</button>
					</div>
				</logic:equal>
				<script language="javascript">
						document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
						document.getElementById("btn").style.width = "96%";
						window.setInterval("initBTNTool('btn')",1);
				</script>
				<logic:present name="result">
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
