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
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="������� zfsoft" />
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
	<script language="javascript" src="/xgxt/dwr/interface/cqkjFunc.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript'src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script type='text/javascript'src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	
	<script>
		function changeGwmc(){
			var sqdw = document.getElementById("yrdwdm").value;
			cqkjFunc.getGwmcList(sqdw,function (data){
					if (data != null && typeof data == 'object') {
						DWRUtil.removeAllOptions("xmdm");			
						DWRUtil.addOptions("xmdm",data,"gwdm","gwmc");
					}
				});
		}
		function showNum(){
			var gwpk = document.getElementById("xmdm").value;			
				cqkjFunc.getGwrsxx(gwpk,function (data){
						if (data != null && typeof data == 'object') {
							document.all.rsNum.style.display='';
							document.getElementById("sqrs").innerText = data.sqrs;
							document.getElementById("tgrs").innerText = data.tgrs;
						}
					});
				
		}
	</script>
	<body>
		<html:form action="/data_search" method="post">
		<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
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
				<input type="hidden" id="xxdm" name="xxdm" value="<bean:write name="xxdm"/>" />
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="6" align="center">
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
							<td colspan="3" align="right">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left">
								<html:text name='rs' property="xh" styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onClick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<td align="right" colspan="3" >
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left">
								<input type="hidden" name="xh" id="xh" value="<bean:write name='rs' property="xh" />">
								<bean:write name='rs' property="xh" />
							</td>
						</logic:equal>
						<td align="right"><font color="red">*</font>���˵�λ��</td>
							<logic:equal value="modi" name="doType">
							<td align="left">
								<html:select name="rs" property="yrdwdm" styleId="yrdwdm"
									style="width:150px" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="yrdwList" property="yrdwdm"
										labelProperty="yrdwmc"/>
								</html:select>
							</td>
							</logic:equal>
							<logic:notEqual value="modi" name="doType">
							<td width="11" align="left">
								<html:select name="rs" property="yrdwdm" styleId="yrdwdm"
									style="width:150px" onchange="changeGwmc();">
									<html:option value=""></html:option>
									<html:options collection="yrdwList" property="yrdwdm"
										labelProperty="yrdwmc"/>
								</html:select>
							</td>
							</logic:notEqual>		
					</tr>
					<tr style="height:22px">
						<td colspan="3" align="right">
							������
						</td>
						<td align="left">
							<bean:write name='rs' property="xm" />
						</td>
						<td align="right"><font color="red">*</font>��λ���ƣ�
						</td>
						<logic:equal value="modi" name="doType">
							<td align="left">
							<input type="hidden" id="isModi" name="isModi"
									value="<bean:write name="doType" scope="request"/>" />
								<input type="hidden" name="xmdmmodi" id="xmdmmodi"
									value="<bean:write name='rs' property='xmdm'/>">
								<html:select name="rs" property="xmdm" styleId="xmdm"
									style="width:150px" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="gwList" property="gwdmgwsbsj"
										labelProperty="gwdm" />
								</html:select>
						</td>
						</logic:equal>
						<logic:notEqual value="modi" name="doType">
							<td width="11" align="left">
								<html:select name="rs" property="xmdm" styleId="xmdm" onchange="showNum()"
									style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="gwList" property="gwdmgwsbsj"
										labelProperty="gwdm" />
								</html:select>
							</td>
						</logic:notEqual>						
					</tr>
					<tr style="height:22px">
						<td colspan="3" align="right">
							�Ա�
						</td>
						<td align="left">
							<bean:write name='rs' property="xb" />
						</td>
						<td align="right">��ȣ�
						</td>
						<td align="left">
							<html:text name="rs" property="nd" readonly="true"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<td height="18" colspan="3" align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td align="left">
							<bean:write name='rs' property="xymc" />
						</td>
						<td align="right">ѧ�꣺
						</td>
						<td align="left">
							<html:text name="rs" property="xn" readonly="true"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<td colspan="3" align="right">
							רҵ��
						</td>
						<td align="left">						
							<bean:write name='rs' property="zymc" />							
						</td>
						<td align="right">ѧ�ڣ�
						</td>
						<td align="left">
							<html:text name='rs' property="xq" />
						</td>
					</tr>
					<tr style="height:22px">
						<td colspan="3" align="right">
							�༶��
						</td>
						<td align="left">
							<bean:write name='rs' property="bjmc" />
						</td>
						<td align="right">��ϵ�绰��
						</td>
						<td align="left">
							<html:text name='rs' property="lxdh" styleId="lxdh" readonly="true"/>
						</td>
					</tr>
					<tr style="height:22px">
					  <td colspan="3" align="right">�꼶��</td>
					  <td align="left"><bean:write name="rs" property="nj"/></td>
					  <td align="right">У԰�����ţ�</td>
					  <td align="left">
					  <html:text name='rs' property="kh" styleId="kh" readonly="true"/>
					  </td>
				  </tr>
					<tr style="height:22px">
					  <td colspan="3" align="right">������ò��</td>
					  <td align="left"><bean:write name='rs' property="zzmmmc" /></td>
					  <td align="right">���᣺</td>
					  <td align="left">
					  	<html:text name='rs' property="jg" styleId="jg" readonly="true"/>
					  </td>
				  </tr>
					 <tr style="height:22px;display:none" id="rsNum">
						  <td colspan="3" align="right">����������</td>
						  <td align="left">
						  <font color="red"><div id="sqrs"> </div></font>
						  </td>
						  <td align="right">ͨ��������</td>
						  <td align="left">
						  	<font color="red"><div id="tgrs"> </div></font>
						  </td>
					  </tr>
					<tr align="left" style="height:22px">
						<td colspan="3" align="right">
							�������ɣ�
						</td>
						<td colspan="3">
							<html:textarea name='rs' property='xssq' styleId="xssq"
								style="width:99%" rows='5' />
						</td>
					</tr>
					<tr align="left" style="height:22px">
						<td colspan="3" align="right">
							��ע��
						</td>
						<td colspan="3">
							<html:textarea name='rs' property='bz' styleId="bz"
								style="width:99%" rows='5' />
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
						<button type="button" class="button2" onclick="chkisDataExist('xh-xmdm')">
							�� �� �� ��
						</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"
						onclick="expAppTab('rsT','�ڹ���ѧ��λ�����','')">
						�� ӡ Ԥ ��
					</button>
				</div>
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
