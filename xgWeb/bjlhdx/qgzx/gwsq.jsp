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
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>	
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>	
	<script language="javascript" src="/xgxt/dwr/interface/cqkjFunc.js"></script>
	<script>	
		function saveValue(){
			var RowStr="!!#!!";
			var length=document.getElementsByName("gwdm").length;
			var doType = "";
			if($('doOper')){
				doType = document.getElementById('doOper').value;
			}
			
			for(var i=0;i<length;i++){
				if(document.getElementsByName("gwdm")[i].checked){
					if(doType== "modi"){
						RowStr+=document.getElementsByName("gwdm")[i].value + "-" + document.getElementsByName("sqdw")[i].value+"!!#!!";
					}else{
						RowStr+=document.getElementsByName("gwdm")[i].value+"!!#!!";
					}
				}
			}
			
			if(document.forms[0].xh.value=="" || document.forms[0].xh.value==null){
				alert("�뽫��\"*\"�ŵ���Ŀ��д������");
				return false;
			}
			if(RowStr=="!!#!!"){
				alert("��ѡ���λ��");
				return false;
			}
			document.getElementById("pkValue").value=RowStr;
			return true;
		}
		
		function commit(){
			var xh = document.getElementById('xh').value;
			var gwdm = document.getElementById('gwdm').value;
			var doType = "";
			if($('doOper')){
				doType = document.getElementById('doOper').value;
			}
			if(saveValue()){
				cqkjFunc.checkStuHavePost(xh,gwdm,doType,function(data){
					if(data == true || data == 'true'){
						alert('���Ѿ��������λ�ˣ������ظ����룡');
						return false;
					}else{
						refreshForm('/xgxt/applySave.do');
					}
				});
			}
			//refreshForm('/xgxt/applySave.do')
		}
	</script>
	<body>		
		<html:form action="/data_search" method="post">
		<input type="hidden" name="tab" value="gwsq">
		<input type="hidden" name="pkValue" value="gwdm">
		<input type="hidden" id="gwsbsj" name="gwsbsj" />
			<div class="title">
			   <logic:equal name="do" value="no">
				<div class="title_img" id="title_m">
					��ǰλ�ã��ڹ���ѧ - ��λ���� - ��д�����
				</div>
				</logic:equal>
				<logic:equal name="do" value="yes">
				<div class="title_img" id="title_m">
					��ǰλ�ã��ڹ���ѧ - ��λ���� - �޸������
				</div>
				</logic:equal>
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
				<logic:equal name="sqsjFlag" value="1">
					<script>
    				alert("�����趨ʱ�䷶Χ��,�ݲ���������!");
    				location.href="about:blank";
    				</script>
				</logic:equal>
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/post_stu_apply.do" />
				<input type="hidden" id="gwsbsj" name="gwsbsj" />
<%--				<input type="text" id="sqdw" name="sqdw" value="${rs.sqdw}"/>--%>
				<logic:present name="showmodi">
					<input type="hidden" id="doOper" name="doOper" value="modi" />
				</logic:present>
				<table width="100%" id="rsT" class="tbstyle">
				<tr><td>
				<table width="100%" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="4" align="center">
							<logic:equal name="do" value="no">
								<b>��д�����</b>
							</logic:equal>
							<logic:equal name="do" value="yes">
							   <b>�޸������</b>
							</logic:equal>
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
								<bean:write name='rs' property="xh"/>
								<input type="hidden" id="xh" name="xh" value="<bean:write name="rs" property="xh"/>"/>
							</td>
						</logic:equal>
						<td align="right">
							���
						</td>
						<td align="left">
							<html:text name="rs" property="nd" readonly="true"></html:text>
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
							<html:text name="rs" property="xn" readonly="true"></html:text>
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
							ѧ�ڣ�
						</td>
						<td align="left">
							<html:text name="rs" property="xq" readonly="true"></html:text>
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
							��ϵ�绰��
						</td>
						<td align="left">
							<html:text name='rs' property="lxdh" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td align="left">
							<bean:write name='rs' property="xymc" />
						</td>
						<td align="right" rowspan="3">
							�������ɣ�
						</td>
						<td align="left" rowspan="3">
							<html:textarea name='rs' property='xssq' styleId="xssq"
								style="width:100%" rows='5' />
						</td>
					</tr>
						<tr style="height:22px">
						<td align="right">
							רҵ��
						</td>
						<td align="left">
							<bean:write name='rs' property="zymc" />
						</td>						
					</tr>
					<tr style="height:22px">
						<td align="right">
							�༶��
						</td>
						<td align="left">
							<bean:write name='rs' property="bjmc" />
						</td>						
					</tr>	
				</table>
				</td>
				</tr>
				<tr><td>
				<table width="100%" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td align="center" colspan="6">							
								��Ч��λ
							</td>
						</tr>
						<tr>
							<td align="left">��λ����</td>
							<td align="left">������λ</td>
							<td align="left">��������</td>							
							<td align="left">��λ����</td>
							<td align="left">����ʱ��</td>
							<td align="left">��������</td>
						</tr>
					</thead>
					<logic:iterate id="gwxx" name="gwList">
					<tr>
						<td>
							<input id="gwdm" name="gwdm" type="radio"  value="<bean:write name="gwxx" property="gwdm"/>"/>
							<input id="sqdw" name="sqdw" type="hidden"  value="<bean:write name="gwxx" property="sqdw"/>"/>
							<logic:present name="showmodi">
								<font color="blue"><bean:write name="gwxx" property="gwdm"/></font>
							</logic:present>
							<logic:notPresent name="showmodi">
								<font color="blue"><bean:write name="gwxx" property="gwmc"/></font>
							</logic:notPresent>
						</td>
						<td>
							<bean:write name="gwxx" property="sqdw"/>
						</td>
						<td>
							<bean:write name="gwxx" property="bmmc"/>
						</td>
						<td>
							<bean:write name="gwxx" property="gwxzmc"/>
						</td>	
						
						<td>
							<bean:write name="gwxx" property="gzsj"/>
						</td>
						<td>
							<bean:write name="gwxx" property="gznr"/>
						</td>
					</tr>
					</logic:iterate>
<%--					<logic:iterate id="gwxx" name="gwList">--%>
<%--						<tr>--%>
<%--						<td>						--%>
<%--						<input type="checkbox" name="gwxx" value="<bean:write name="gwxx" property="gwdm"/>">	--%>
<%--						<bean:write name="gwxx" property="sqdw"/>--%>
<%--						</td>--%>
<%--						<td><bean:write name="gwxx" property="bmmc"/></td>--%>
<%--						<td><bean:write name="gwxx" property="gwxzmc"/></td>--%>
<%--						<td>--%>
<%--						<logic:present name="showmodi">--%>
<%--							<font color="blue"><bean:write name="gwxx" property="gwdm"/></font>--%>
<%--						</logic:present>--%>
<%--						<logic:notPresent name="showmodi">--%>
<%--							 <font color="blue"><bean:write name="gwxx" property="gwmc"/></font>--%>
<%--						</logic:notPresent>--%>
<%--						--%>
<%--						</td>--%>
<%--						<td><bean:write name="gwxx" property="gzsj"/></td>--%>
<%--						<td><bean:write name="gwxx" property="gznr"/></td>--%>
<%--						</tr>--%>
<%--					</logic:iterate>--%>
				</table>
				</td>
				</tr>
				</table>
				<div class="buttontool" align="center">
					<button class="button2"
						onclick="commit()">
						�� �� �� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="expTab('rsT','�ڹ���ѧ��λ�����','')">
						�� ӡ Ԥ ��
					</button>					
				</div>
			</logic:notEmpty>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("����ɹ���");
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("����ʧ�ܣ�");
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="nopks">
					<script>
    alert("����ʧ�ܣ�������ƶ������������");
    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
