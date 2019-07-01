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
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
		function chkValue(doType)
		{
			var xh=document.getElementById("xh").value;
			var zc=document.getElementById("zc").value;
			var jlr=document.getElementById("jlr").value;
			var rq=document.getElementById("rq").value;

			if (xh==''||zc==''||jlr==''||rq==''){
				alert("��ɫ*����ϢΪ������ٴ���д��ȷ�ϣ�");
			}else{
				refreshForm('wjcf_ZtBhGl_oper.do?doType='+doType);
			}
		}
		function chkNum()
		{
			if ((event.keyCode >= 48 && event.keyCode <= 57))
			{
				event.returnValue = true;
			}
			else
				event.returnValue = false;
		}
		function getRqVal()
		{
			var rq=document.getElementById("rq").value;
			if (rq!="")
			{
				rqs=rq.split("-");
				rq="";
				for (var i=0;i<rqs.length;i++)
				{
					rq+=rqs[i];
				}
				document.getElementById("rq").value=rq;
			}
		}
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/wjcf_ZtBhGl_oper" method="post">
		 <input type="hidden" id="disableEle" name="disableEle"
					     value="xm-xb-xy-nj-zy-bj" />
	    <input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-xymc-nj-zymc-bjmc" />
		<input type="hidden" id="url" name="url" value="/wjcf_ZtBhGl_load.do" />
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow">
						<logic:equal value="11049" name="xxdm">
						
					Υ�ʹ��� - ����ά�� - ������
					</logic:equal>
					<logic:notEqual value="11049" name="xxdm">
					
					Υ�ʹ��� - ����ά�� - ���ֺ����
					</logic:notEqual>
					</span>
				</div>
			</div>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td colspan="4" align="center">
								
								<logic:equal value="11049" name="xxdm">
						
					�������¼
					</logic:equal>
					<logic:notEqual value="11049" name="xxdm">
					
					���ֺ���ֻ����¼
					</logic:notEqual>
							</td>
						</tr>
					</thead>
					<tr>
						<td align="right">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td align="left">
						<logic:present name="rs">
							<input type="text" name="xh" style="width:85px"  id="xh" value="<bean:write name="rs" property="xh"/>" onkeypress="autoFillStuInfo(event.keyCode,this)">
							</logic:present>
							<logic:notPresent name="rs">
								<input type="text" name="xh" style="width:85px" id="xh" >
							</logic:notPresent>
							<button type="button" onclick="showTopWin('/xgxt/wjcf_stuinfo.do?doType=load&xh=',800,525);"
									class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
						</td>
						<td align="right">
							�꼶��
						</td>
						<td align="left">
							<logic:present name="rs">
							<input type="text" name="nj" id="nj" value="<bean:write name="rs" property="nj"/>" readonly="readonly">
							</logic:present>
							<logic:notPresent name="rs">
							<input type="text" name="nj" id="nj" readonly="readonly">
							</logic:notPresent>
						</td>
					</tr>
					<tr>
						<td align="right">
							������
						</td>
						<td align="left">
							<logic:present name="rs">
							<input type="text" name="xm" id="xm" value="<bean:write name="rs" property="xm"/>" readonly="readonly">
							</logic:present>
							<logic:notPresent name="rs">
							<input type="text" name="xm" id="xm" readonly="readonly">
							</logic:notPresent>
						</td>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td align="left">
							<logic:present name="rs">
							<input type="text" name="xymc" id="xymc" value="<bean:write name="rs" property="xymc"/>" readonly="readonly">
							</logic:present>
							<logic:notPresent name="rs">
							<input type="text" name="xymc" id="xymc" readonly="readonly">
							</logic:notPresent>
						</td>
					</tr>
					<tr>
						<td align="right">
							רҵ��
						</td>
						<td align="left">
							<logic:present name="rs">
							<input type="text" name="zymc" id="zymc" value="<bean:write name="rs" property="zymc"/>" readonly="readonly">
							</logic:present>
							<logic:notPresent name="rs">
							<input type="text" name="zymc" id="zymc" readonly="readonly">
							</logic:notPresent>
						</td>
						<td align="right">
							�༶��
						</td>
						<td align="left">
							<logic:present name="rs">
							<input type="text" name="bjmc" id="bjmc" value="<bean:write name="rs" property="bjmc"/>" readonly="readonly">
							</logic:present>
							<logic:notPresent name="rs">
							<input type="text" name="bjmc" id="bjmc" readonly="readonly">
							</logic:notPresent>
						</td>
					</tr>
					<tr>
						<td align="right">
							<font color="red">*</font>�����ˣ�
						</td>
						<td align="left">
							<input type="text" name="zc" id="zc">
						</td>
						<td align="right">
							��ϯ������
						</td>
						<td align="left">
							<input type="text" name="cxrs" id="cxrs" onkeypress="chkNum()" maxlength="4">
						</td>
					</tr>
					<tr>
						<td align="right">
							<font color="red">*</font>��¼�ˣ�
						</td>
						<td align="left">
							<input type="text" name="jlr" id="jlr">
						</td>
						<td align="right">
							<font color="red">*</font>���ڣ�
						</td>
						<td align="left">
							<input type="text" name="rq" id="rq" onclick="return showCalendar('rq','y-mm-dd');" onblur="getRqVal()">
						</td>
					</tr>
					<tr align="left">
						<td align="right">
							��ϯ��Աǩ����
						</td>
						<td colspan="3">
				
							<textarea rows="3" cols="" style="width:99%" name="cxryqd" id="cxryqd"></textarea>
						</td>
					</tr>
					<tr align="left">
						<td align="right">
							�����¼��
						</td>
						<td colspan="3">
							<textarea rows="10" cols="" style="width:99%" name="hyjl" id="hyjl"></textarea>
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button type="button" class="button2"
						onclick="chkValue('save')"
						style="width:80px" id="buttonSave">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>

		</html:form>
		<logic:equal value="ok" name="done">
<script language="javascript">
alert("�����ɹ���");
Close();
window.dialogArguments.document.getElementById('search_go').click();   
</script>
</logic:equal>
<logic:equal value="no" name="done">
<script language="javascript" >
  alert("����ʧ�ܣ�ԭ������Ǹ���Ϣ�����ݿ����Ѵ���! \n ������ϸ�˶�!��");
Close();
window.dialogArguments.document.getElementById('search_go').click();   
</script>
</logic:equal>
	</body>
</html>
