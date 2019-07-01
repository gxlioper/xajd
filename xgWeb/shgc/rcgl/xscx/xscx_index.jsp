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
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/lrh_new_js.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="JavaScript">
		function rcgl_xscx_view()
		{
			var xn_id=curr_row.cells[0].getElementsByTagName("input")[0].value;
			showTopWin("/xgxt/rcgl_xscx.do?act=xscx&doType=xscx_view&xn_id="+xn_id,525,400);
		}
		function rcgl_xscx_add()
		{
			showTopWin("/xgxt/rcgl_xscx.do?act=xscx&doType=xscx_add",550,425);
		}
		
		function rcgl_xscx_del()
		{
			if (curr_row == null) {
				alert("��ѡ��Ҫɾ�������ݣ�\n��������Ӧ���У�");
				return false;
				} 
			else {
					if (confirm("ȷ��Ҫɾ������������")) {
						var xn_id=curr_row.cells[0].getElementsByTagName("input")[0].value;
						refreshForm("/xgxt/rcgl_xscx.do?act=xscx&doType=xscx_del&xn_id="+xn_id);
						return true;
					} 
					else {
						return false;
					}
				}
		}
		
		function rcgl_xscx_modi()
		{
			
			if (curr_row == null) {
				alert("��ѡ��Ҫ�޸ĵ����ݣ�\n��������Ӧ���У�");
				return false;
			} else {
				var xn_id=curr_row.cells[0].getElementsByTagName("input")[0].value;
				showTopWin("/xgxt/rcgl_xscx.do?act=xscx&doType=xscx_modi&xn_id="+xn_id,525,400);
				return true;
			}
		}
		
	</script>
	<body>
		<html:form action="/rcgl_xscx" method="post">
		   <div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã��ճ���Ϊ��Ϣ���� - ������Ϣ					
				</div>
			    </div>
			<fieldset>
				<legend>
					��ѯ
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								ѧ�꣺
								<html:select property="xn" style="width:90px;background-color:#FFFFFF" styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;ѧ�ڣ�
								<html:select property="xq" style="width:50px;background-color:#FFFFFF" styleId="xq" >
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
								</html:select>
								&nbsp;&nbsp;
								��Ϊģ�飺
								<html:select property="xwmkdm" style="width:150px" styleId="xwmkdm"
									onchange="">
									<html:option value=""></html:option>
									<html:options collection="rcxwList" property="XN_ID"
										labelProperty="MKMC" />
								</html:select>
							</td>
							<td width="10" rowspan="3" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button class="button2" style="height:40px;" id="search_go" 
									onclick="allNotEmpThenGo('/xgxt/rcgl_xscx.do?act=xscx&doType=xscx_search')">
									��ѯ
								</button>
								<button class="button2" style="height:40px;display:none;" id="search_go1" 
									onclick="refreshForm('/xgxt/rcgl_xscx.do?act=xscx&doType=xscx_search')">
								</button>
							</td>
						</tr>
						<tr>
							<td align="left">
							����ģ�飺
							<html:select property="cxmkdm" style="width:150px" styleId="cxmkdm"
									onchange="">
									<html:option value=""></html:option>
									<html:options collection="cxList" property="XN_ID"
										labelProperty="MKMC" />
							</html:select>	
							&nbsp;&nbsp;
							���ڣ�
							<html:text style="cursor:hand; width:75px;" styleId="dateF"
									property="rq"
									onclick="return showCalendar('dateF','y-mm-dd');"
									readonly="readonly" />
							&nbsp;&nbsp;
							ѧ�ţ�
							<html:text property="xh" styleId="xh" style="width:110px;"/>			
							</td>
						</tr>	
						<tr>
							<td align="left" nowrap>
								<bean:message key="lable.xsgzyxpzxy" />��
								<html:select property="xydm" style="width:180px" styleId="xy"
									onchange="refreshForm('/xgxt/rcgl_xscx.do?act=xscx')">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;�༶��
								<html:select property="bjdm" style="width:180px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
								&nbsp;&nbsp;
								������
								<html:text property="xm"  styleId="xm"/>
							</td>
						</tr>
					</thead>
				</table>
			</fieldset>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					δ�ҵ��κμ�¼��
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						��¼����
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
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
							<tr onclick="rowOnClick(this)" style="cursor:hand;background-color:" ondblclick="rcgl_xscx_view()">	
									<td>
										<input type="hidden" id="xn_id" name="xn_id"
										value="<bean:write name="s" property="XN_ID"/>" />
										<bean:write name="s" property="XH"/>
									</td>
									<td>
										<bean:write name="s" property="XM"/>
									</td>
									<td>
										<bean:write name="s" property="XB"/>
									</td>
									<td>
										<bean:write name="s" property="MKMC"/>
									</td>
									<td>
										<bean:write name="s" property="CXMKMC"/>
									</td>
									<td>
										<bean:write name="s" property="XYMC"/>
									</td>
									<td>
										<bean:write name="s" property="BJMC"/>
									</td>
									<td>
										<bean:write name="s" property="JLR"/>
									</td>
									<td>
										<bean:write name="s" property="RQ"/>
									</td>
							</tr>
					   </logic:iterate>
					</table>
				</fieldset>	
			</logic:notEmpty>
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				<logic:equal value="yes" name="writeAble" scope="request">
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="rcgl_xscx_add()"
								style="width:100px">
								����
							</button>
							&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="rcgl_xscx_modi()"
								style="width:100px">
								�޸�
							</button>
							&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="rcgl_xscx_del()"
								style="width:100px">
								ɾ��
							</button>
						</div>
					</center>
			</logic:equal>
			<logic:notEmpty name="message">
					<logic:equal name="message" value="del_success">
						<script>
							alert("ɾ���ɹ�!");
							document.getElementById("search_go1").click();
						</script>
					</logic:equal>					
			</logic:notEmpty>
		</html:form>
			<script language="javascript">
					document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
					document.getElementById("btn").style.width = "96%";
					window.setInterval("initBTNTool('btn')",1);
		</script>
	</body>
</html>
