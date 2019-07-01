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
	<body onload="xyDisabled('xy');removeXnXq()">
		<script language="javascript">
		</script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：
					<bean:write name="tips" scope="request" />
				</div>
			</div>
			<logic:notEqual value="stu" name="userType">
				<div class="rightcontent">

					<fieldset>
						<legend>
							查 询
						</legend>
						<input type="hidden" id="userType" name="userType"
							value="<bean:write name="userType" scope="request"/>" />
						<input type="hidden" id="tableName" name="tableName"
							value="<bean:write name="tableName" scope="request"/>" />
						<input type="hidden" id="act" name="act"
							value="<bean:write name="act" scope="request"/>" />
						<input type="hidden" id="realTable" name="realTable"
							value="<bean:write name="realTable" scope="request"/>" />
						<input type="hidden" id="pk" name="pk"
							value="<bean:write name="pk" scope="request"/>" />
						<input type="hidden" id="xxdm" name="xxdm"
							value="<bean:write name="xxdm" scope="request"/>" />
						<input type="hidden" id="dxq" name="dxq"
							value="<bean:write name="writeAble" scope="request"/>" />
							<input type="hidden" id="xxdm" name="xxdm"
								value="no" />	
						<table width="100%" class="tbstyle">
							<thead>
								<tr>
									<td align="left">
										年级：
										<html:select property="nj" style="width:80px"
											onchange="refreshForm('/xgxt/data_search.do')">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>											
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学年：											
										<html:select property="xn" style="width:100px" styleId="xn" onchange="genNdList(this)">
											<html:options collection="xnList" property="xn"
														                    labelProperty="xn" />
										</html:select>
										
										<logic:equal value="view_zhszcp" name="tableName">
											&nbsp;&nbsp;&nbsp;年度：
											<select id='ndList' name="ndList" width=20%>
												<option>  </option>
											</select>
										</logic:equal>
										&nbsp;&nbsp;&nbsp;&nbsp;荣誉称号：
											<html:select property="rychdm" styleId="rychdm">
													<html:option value=""></html:option>
													<html:options collection="xmlist" property="rychdm"
														labelProperty="rychmc" />
												</html:select>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学期：
										<html:select property="xq" style="width:90px" styleId="xq">
														<html:option value=""></html:option>
														<html:options collection="xqList" property="xqdm"
															labelProperty="xqmc" />
										</html:select>
										&nbsp;&nbsp;&nbsp;学号：
										<html:text property="xh" style="width:85px"></html:text>						
									</td>
									<td width="10" rowspan="2" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="button2" style="height:40px" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/data_search.do')">
											查询
										</button>
									</td>
									<td width="10" rowspan="2" align="center" valign="middle">
											<button type="button" class="button2" style="height:40px;width:40px"
												onclick="">
												&nbsp;选择&nbsp;
											</button>
									</td>
								</tr>
								<tr>
									<td align="left" nowrap>
										<bean:message key="lable.xsgzyxpzxy" />：
										<html:select property="xydm" style="width:180px" styleId="xy"
											onchange="refreshForm('/xgxt/data_search.do')">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										<logic:notEqual value="xyhj" name="xyhjType">
										&nbsp;&nbsp;专业：
										<html:select property="zydm" style="width:180px" styleId="zy"
											onchange="refreshForm('/xgxt/data_search.do')">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
										&nbsp;&nbsp;班级：
										<html:select property="bjdm" style="width:180px" styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
										</logic:notEqual>
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
								<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序;按住Ctrl可以选择多条记录</font>
							</legend>
							<table width="100%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
										<logic:iterate id="tit" name="topTr" offset="1">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
										ondblclick="xz_viewMore(this)">
										<td>
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="1" length="1">
												<bean:write name="v" />
											</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="2">
										<td nowrap>
											<bean:write name="v" />
										</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
						</fieldset>
					</logic:notEmpty>
					<div id="toolTipLayer"
						style="position:absolute; visibility: hidden" /></div>
						<center>
							<div class="buttontool" id="btn"
								style="position: absolute;left:1%;top:100px" width="100%">
							<logic:equal value="yes" name="writeAble" scope="request">
								<logic:present name="showzdjs">
									<button type="button" class="button2"
										onclick="AutoAccountCj('/xgxt/AutoAccount.do')"
										style="width:80px">
										自动计算
									</button>
								</logic:present>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button" class="button2" onclick="viewMore('add')"
									style="width:80px">
									增 加
								</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="viewMore('modi')"
								style="width:80px">
								修 改
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="viewMore('del')"
								style="width:80px">
								删 除
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:equal>
						<logic:equal value="yes" name="writeAble" scope="request">
						<button type="button" class="button2"
							onclick="impAndChkData();"
							style="width:80px">
							导入数据
						</button>
						</logic:equal>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="dataExport()" style="width:80px">
									导出数据
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
						</center>
					</div>
			</logic:notEqual>
			<div id="tmpdiv"></div>
			<logic:present name="autoCj">
				<logic:equal name="autoCj" value="ok">
					<script language="javascript">
      						alert("自动计算完成！");
	  					</script>
				</logic:equal>
				<logic:equal name="autoCj" value="no">
					<script language="javascript">
	  						alert("自动计算失败! ");
	  					</script>
				</logic:equal>
			</logic:present>
		</html:form>
		<logic:equal name="result" value="ok">
			<script language="javascript">
      				alert("操作成功！");
	  		</script>
		</logic:equal>
		<logic:equal name="result" value="no">
			<script language="javascript">
	  				alert("操作失败! ");
	  		</script>
		</logic:equal>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>

