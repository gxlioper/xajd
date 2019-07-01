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
				<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script language="javascript">
	function getjlInfo(){
		var xxdm = document.getElementById('xxdm').value;
		if (xxdm=='10419'){
			var values = document.getElementById('hjqk').value;
			if (values=="" || values==null ||values==" " || values=="  ") {
				document.getElementById('hjqk').value='200  ��200��ѧ��ȵڡ�ѧ�ڡ��񡡵�';
			}
		}
	}
	function rychPrint(){
            var pk= document.getElementById("pk").value;
			if(pk ==""){
				alert("�����ύ���룬�ٴ�ӡ��");
				return false;
			}
            window.open('pjpyhxxywh.do?method=rychPrint&pk='+pk);
     } 
     function saveRyzs(){
     	var rychdm= document.getElementById("rychdm").value;
			if(rychdm ==""){
				alert("��ȷ�������ƺ����ͣ�");
				return false;
			}
     	refreshForm('pjpyhxxywh.do?method=rychSqSave');
     }
     function saveRyzs1(){
     	var rychdm= document.getElementById("rychdm").value;
			if(rychdm ==""){
				alert("��ȷ�������ƺ����ͣ�");
				return false;
		}
     	refreshForm('pjpyhxxywh.do?method=rychSqSave&rychEdit=yes&oldpk='+document.getElementById('oldpk').value);
     }
     
</script>
			<body>
			<script language="javascript" src="js/function.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
			<script language="javascript" src="js/pjpy/pjpy_zjsyzy.js"></script>
			 <input type="hidden" name="oldpk" id="oldpk" value="${oldpk }"/>
			<html:form action="/pjpyhxxywh" method="post">
				<div class="title">
					<div class="title_img" id="title_m">
						��ǰλ�ã��������� - �����ƺ����� - ��д�����
					</div>
				</div>
				<logic:empty name="rs">
					<br />
					<br />
					<p align="center">
						�д�������
					</p>
				</logic:empty>
				<logic:notEmpty name="rs">
					<logic:equal name="rs" property="stuExists" value="no">
						<script>
    alert("�������ѧ����Ч!");
    </script>
					</logic:equal>
					<input type="hidden" id="disableEle" name="disableEle"
						value="xm-xb-xy-nj-zy-bj" />
					<input type="hidden" id="getStuInfo" name="getStuInfo"
						value="xm-xb-xymc-nj-zymc-bjmc" />
					<html:hidden name='rs' property="pk" styleId="pk" />
					<input type="hidden" id="url" name="url"
						value="/pjpy/whlghxxy/rychsq.jsp" />
					<input type="hidden" id="stab" name="stab" value="stab" />
					<input type="hidden" id="xxdm" name="xxdm" value="${xxdm }" />
					<logic:notPresent name="rychEdit">
						<table width="99%" id="rsT" class="tbstyle">
						<thead>
							<tr style="height:22px">
								<td colspan="4" align="center">
									<b>��д�����</b>
								</td>
							</tr>
						</thead>
						<tr style="height:22px">
							<logic:equal name="userOnLine" value="teacher" scope="session">
								<td align="right">
									<font color="red">*</font>ѧ�ţ�
								</td>
								<td align="left">
									<html:text name='rs' property="xh" styleId="xh"
										onkeypress="autoFillStuInfo(event.keyCode,this)" />
									<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu">
										ѡ��
									</button>
								</td>
							</logic:equal>
							<logic:equal name="userOnLine" value="student" scope="session">
								<td align="right">
									<font color="red">*</font>ѧ�ţ�
								</td>
								<td align="left">
									<html:hidden name='rs' property="xh" styleId="xh" />
									<bean:write name='rs' property="xh" />
								</td>
							</logic:equal>
							<td align="right">
								��ȣ�
							</td>
							<td align="left">
								<html:text name='rs' property="nd"/>
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								������
							</td>
							<td align="left">
								<bean:write name='rs' property="xm" />
							</td>
							<td align="right">
								ѧ�꣺
							</td>
							<td align="left">
								<html:text name='rs' property="xn"/>
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								�Ա�
							</td>
							<td align="left">
								<bean:write name='rs' property="xb" />
							</td>
							<td align="right">
								<font color="red">*</font>���������ƺţ�
							</td>
							<td align="left">
								<html:select name='rs' property="rychdm" styleId="rychdm">
									<option value=""></option>
									<html:options collection="rychList" property="rychdm"
										labelProperty="rychmc" />
								</html:select>
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								�꼶��
							</td>
							<td align="left">
								<bean:write name='rs' property="nj" />
							</td>
							<td align="right">
									������ò��
								</td>
								<td align="left">
									<bean:write name='rs' property="zzmmmc" />
								</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />��
							</td>
							<td align="left">
								<bean:write name='rs' property="xymc" />
							</td>
							<td align="right">
								���壺
							</td>
							<td align="left">
								<bean:write name='rs' property="mzmc" />
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								רҵ��
							</td>
							<td align="left">
								<bean:write name='rs' property="zymc" />
							</td>
							<td align="right">
								����ˮƽ��
							</td>
							<td align="left">
								<html:text name='rs' property="wydj"/>
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								�༶��
							</td>
							<td align="left">
								<bean:write name='rs' property="bjmc" />
							</td>
							<td align="right">
									�������£�
								</td>
								<td>
									<bean:write name='rs' property="csrq" />
								</td>
						</tr>
						
							<tr>
								<td align="right">
									�绰��
								</td>
								<td align="left">
									<bean:write name='rs' property="lxdh" />
								</td>
								<td align="right">
									��ѧʱ�䣺
								</td>
								<td>
									<bean:write name='rs' property="rxrq" />
								</td>
							</tr>
						<tr style="height:22px">
							<td align="right">
								��Ҫ�¼���
								<br/>
							<span class="style1">(������800����)</span>
							</td>
							<td colspan="3" align="left">
								<html:textarea rows="5" name='rs' style="width:98%"
									property="zysj" styleId="a" />
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								������Ṥ�������
								<br/>
							<span class="style1">(������800����)</span>
							</td>
							<td colspan="3" align="left">
								<html:textarea rows="5" name='rs' style="width:98%"
									property="drshgzqk" styleId="a" />
							</td>
						</tr>
						</table>
						<div class="buttontool" align="center">
						<button class="button2" <logic:equal value="no" name="jxjsq">disabled="disabled"</logic:equal>
							onclick="saveRyzs();">
							�� �� �� ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2"
							onclick="rychPrint();">
							�� ӡ �� ��
						</button>
						</div>
						</logic:notPresent>
						<logic:present name="rychEdit">
						<table width="99%" id="rsT" class="tbstyle">
						<thead>
							<tr style="height:22px">
								<td colspan="4" align="center">
									<b>��д�����</b>
								</td>
							</tr>
						</thead>
							<tr style="height:22px">
								<td align="right">
									<font color="red">*</font>ѧ�ţ�
								</td>
								<td align="left">
									<html:hidden name='rs' property="xh" styleId="xh" />
									<bean:write name='rs' property="xh" />
								</td>
							<td align="right">
								��ȣ�
							</td>
							<td align="left">
								<html:text name='rs' property="nd"/>
								<html:hidden name='rs' property="nd" styleId="nd" />
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								������
							</td>
							<td align="left">
								<bean:write name='rs' property="xm" />
							</td>
							<td align="right">
								ѧ�꣺
							</td>
							<td align="left">
								<html:text name='rs' property="xn"/>
								<html:hidden name='rs' property="xn" styleId="xn" />
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								�Ա�
							</td>
							<td align="left">
								<bean:write name='rs' property="xb" />
							</td>
							<td align="right">
								<font color="red">*</font>���������ƺţ�
							</td>
							<td align="left">
								<html:select name='rs' property="rychdm" styleId="rychdm">
									<option value=""></option>
									<html:options collection="rychList" property="rychdm"
										labelProperty="rychmc" />
								</html:select>
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								�꼶��
							</td>
							<td align="left">
								<bean:write name='rs' property="nj" />
							</td>
							<td align="right">
									������ò��
								</td>
								<td align="left">
									<bean:write name='rs' property="zzmmmc" />
								</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />��
							</td>
							<td align="left">
								<bean:write name='rs' property="xymc" />
							</td>
							<td align="right">
								���壺
							</td>
							<td align="left">
								<bean:write name='rs' property="mzmc" />
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								רҵ��
							</td>
							<td align="left">
								<bean:write name='rs' property="zymc" />
							</td>
							<td align="right">
								����ˮƽ��
							</td>
							<td align="left">
								<html:text name='rs' property="wydj"/>
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								�༶��
							</td>
							<td align="left">
								<bean:write name='rs' property="bjmc" />
							</td>
							<td align="right">
									�������£�
								</td>
								<td>
									<bean:write name='rs' property="csrq" />
								</td>
						</tr>
						
							<tr>
								<td align="right">
									�绰��
								</td>
								<td align="left">
									<bean:write name='rs' property="lxdh" />
								</td>
								<td align="right">
									��ѧʱ�䣺
								</td>
								<td>
									<bean:write name='rs' property="rxrq" />
								</td>
							</tr>
						<tr style="height:22px">
							<td align="right">
								��Ҫ�¼���
								<br/>
							<span class="style1">(������800����)</span>
							</td>
							<td colspan="3" align="left">
								<html:textarea rows="5" name='rs' style="width:98%"
									property="zysj" styleId="a" />
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								������Ṥ�������
								<br/>
							<span class="style1">(������800����)</span>
							</td>
							<td colspan="3" align="left">
								<html:textarea rows="5" name='rs' style="width:98%"
									property="drshgzqk" styleId="a" />
							</td>
						</tr>
						</table>
						<div class="buttontool" align="center">
						<button class="button2" <logic:equal value="no" name="jxjsq">disabled="disabled"</logic:equal>
							onclick="saveRyzs1();">
							�� ��
						</button>
						<logic:equal name="userOnLine" value="student" scope="session">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2"
							onclick="rychPrint();">
							�� ӡ �� ��
						</button>
						</logic:equal>
					</div>
						</logic:present>
						
				</logic:notEmpty>
				<logic:present name="inserted">
					<logic:equal name="inserted" value="ok">
						<script>
    						alert("����ɹ���");
    							if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
    							window.dialogArguments.document.all("search_go").click();
    							Close();
    							}
    					</script>
					</logic:equal>
					<logic:equal name="inserted" value="no">
						<script>
    						alert("����ʧ�ܣ���ȷ��");
   					 </script>
					</logic:equal>
					<logic:equal name="inserted" value="okk">
						<script>
							var xh= document.getElementById("xh").value;
							var xn= document.getElementById("xn").value;
							var pk = xn+xh;
						//	document.getElementById("pk").value=pk;
    						alert("����ɹ���");
    					</script>
					</logic:equal>
				</logic:present>
			</html:form>
		</body>
</html>