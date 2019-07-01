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
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript">
	</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script type="text/javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
		<html:form action="/wxszXjjt" method="post">
		<input type="hidden" name="realTable" id="realTable" value="xjjtb"/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：${tip }
				</div>
			</div>
			<div class="rightcontent">
					<fieldset>
						<legend>
							查 询
						</legend>
			<table width="100%" class="tbstyle">
				<thead>
					<thead>
						<tr>
							<td align="left">
								学年：
								<html:select property="xn" style=""
									onchange="">
								<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;学期：
								<html:select property="xq" style="" onchange="">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>
								&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />:
								<html:select property="xydm">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
												  labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;集体名称:
								<html:text property="jtmc" name="pj"></html:text>	
							</td>
							<td width="10" rowspan="3" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<logic:notEqual value="sh" name="doType" scope="request">
									<button class="button2" style="height:40px" id="search_go"
										onclick="allNotEmpThenGo('/xgxt/wxsz_xjjt_cx.do');">
										查询
									</button>
								</logic:notEqual>
								<logic:equal value="sh" name="doType" scope="request">
									<button class="button2" style="height:40px" id="search_go"
										onclick="allNotEmpThenGo('/xgxt/wxsz_xjjt_sh.do');">
										查询
									</button>
								</logic:equal>
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
						<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
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
							<tr align="center" onclick="rowMoreClick(this,'',event);" style="cursor:hand"
								ondblclick="showXjjt('query');">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" name="pkValue" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="2">
									<td><bean:write name="v" /></td>
								</logic:iterate>
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
										page="/sjcz/turnpage.jsp?form=xjjtForm"></jsp:include>
							</TD>
						</TR>
						<TR>
							<TD style="height:3"></TD>
						</TR>
					</TABLE>
				</fieldset>
			</logic:notEmpty>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				<logic:notEqual value="sh" name="doType" scope="request">
					<logic:notEqual value="stu" name="userType">
					<div class="buttontool" align="center" id="btn" style="position:absolute;width:95%;top:100px">
						<button class="button2"
							style="width:80px" onclick="showXjjt('add')">
							增加
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="showXjjt('modi')"
							style="width:80px">
							修 改
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="showXjjt('del')"
							style="width:80px">
							删 除
						</button>
					</div>	
				</logic:notEqual>
				</logic:notEqual>			
				<logic:equal value="sh" name="doType" scope="request">
					<logic:equal value="xy" name="userType">
					<div class="buttontool" align="center" id="btn" style="position:absolute;width:95%;top:100px">
						<button class="button2"
							style="width:80px" onclick="showXjjt('sh1')">
							审核通过
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2"
							style="width:80px" onclick="showXjjt('sh2')">
							审核不通过
						</button>
					</div>	
				</logic:equal>
				<logic:equal value="xx" name="userType">
					<div class="buttontool" align="center" id="btn" style="position:absolute;width:95%;top:100px">
						<button class="button2"
							style="width:80px" onclick="showXjjt('sh1')">
							审核通过
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2"
							style="width:80px" onclick="showXjjt('sh2')">
							审核不通过
						</button>
					</div>	
				</logic:equal>
				<logic:equal value="admin" name="userType">
					<div class="buttontool" align="center" id="btn" style="position:absolute;width:95%;top:100px">
						<button class="button2"
							style="width:80px" onclick="showXjjt('sh1')">
							审核通过
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2"
							style="width:80px" onclick="showXjjt('sh2')">
							审核不通过
						</button>
					</div>	
				</logic:equal>
				</logic:equal>
			</div>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
		<script type="text/javascript">
			function showXjjt(type){
			    if (type=='add'){
					showTopWin('wxsz_xjjt_sq.do',900,550);
					return;
				}
				if (curr_row == null || curr_row == '') {
					alert('请先选择要操作的行数据,单击一行即可!');
					return;
				}
				var pkValue = curr_row.getElementsByTagName('input')[0].value;
				var xysh = curr_row.cells[4].innerText;
				var url = '';
				if(type=='query'){
					url = 'wxsz_xjjt_sq.do?pkValue='+pkValue+'&doType=query';
					showTopWin(url,900,550);
				}else if(type=='modi'){
					if(xysh == '已通过'){
						alert('<bean:message key="lable.xsgzyxpzxy" />审核已通过，不能修改');
						return false;
					}
					url = 'wxsz_xjjt_sq.do?pkValue='+pkValue+'&doType=modi';
					showTopWin(url,900,550);
				}else if(type=='del'){
					refreshForm('wxsz_xjjt_cx.do?pkValue='+pkValue+"&doType=del&go=go");
				}else if (type=='sh1'){
					refreshForm('wxsz_xjjt_sh.do?pkValue='+pkValue+"&doType=sh1&go=go");
				}else if (type=='sh2'){
					refreshForm('wxsz_xjjt_sh.do?pkValue='+pkValue+"&doType=sh2&go=go");
				}else {
					return ;
				}
				
			}
		</script>
	</body>
</html>
