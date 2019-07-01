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
		<base target="_self" />
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
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/CzxxJxjDao.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script type="text/javascript">
    	
    </script>
	<body >
		<html:form action="/czxxPjpyRych" method="post">
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		<div class="title"> 
				<div class="title_img" id="title_m">
					��ǰ����λ�ã��������� - ��� - �����ƺ����
				</div>
			</div>
			<table width="100%" class="tbstyle">
				<thead>
					<tr align="center">
						<td height="22" colspan="4">
							�������
						</td>
					</tr>
				</thead>
				<tr>
					<td height="22" align="right" style="width:25%">
						ѧ�ţ�
					</td>
					<td height="22" align="left" style="width:25%">
					${rs.xh }
					</td>
					<td height="22" align="right">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td height="22" align="left">
						${rs.xymc }
					</td>
					
				</tr>
				<tr>
					<td height="22" align="right">
						������
					</td>
					<td height="22" align="left">
						${rs.xm }
					</td>
					<td height="22" align="right">
						רҵ��
					</td>
					<td height="22" align="left">
						${rs.zymc }
					</td>
					
				</tr>
				<tr>
				
					<td height="22" align="right">
						�꼶��
					</td>
					<td height="22" align="left">
						${rs.nj }
					</td>
				<td height="22" align="right" style="width:25%">
						ѧ�꣺
					</td>
					<td height="22" align="left" style="width:25%">
						${rs.xn }
					</td>	
				</tr>
				<tr>
					
					<td height="22" align="right">
						�༶��
					</td>
					<td height="22" align="left">
						${rs.bjmc }
					</td>
					<td height="22" align="right">
						ѧ�ڣ�
					</td>
					<td height="22" align="left">
						${rs.xqmc }
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						�����ɼ���
					</td>
					<td height="22" align="left">
						${rs.dcj }
					</td>
					<td height="22" align="right">
						�����ɼ���
					</td>
					<td height="22" align="left">
						${rs.zcj}
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						�����ɼ���
					</td>
					<td height="22" align="left">
						${rs.tcj }
					</td>
					<td height="22" align="right">
						�۲��ܳɼ���
					</td>
					<td height="22" align="left">
						${rs.zxf}
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						�����ɼ��༶������
					</td>
					<td height="22" align="left">
						${rs.dpm }
					</td>
					<td height="22" align="right">
						�����ɼ��༶������
					</td>
					<td height="22" align="left">
						${rs.zpm}
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						�����ɼ��༶������
					</td>
					<td height="22" align="left">
						${rs.tpm }
					</td>
					<td height="22" align="right">
						�۲��ܳɼ��༶������
					</td>
					<td height="22" align="left">
						${rs.zfpm}
					</td>
				</tr>
				
				<tr>
					<td height="22" align="right">
					�����ƺ����ƣ�
					</td>
					<td height="22" align="left">
						${rs.rychmc }
					</td>
					<td height="22" align="right">
						
					</td>
					<td height="22" align="left">
						
					</td>
				</tr>
				
				<tr>
				<td align="right" colspan="4">
					<table width="100%" border="1" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main4" style="color:blue;cursor:hand"
									onclick="document.all.child4.style.display=(document.all.child4.style.display =='none')?'':'none';">
									<div align="center" class="style1 style3">
										<strong>Υ�ʹ�����Ϣ</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div id="child4" style="display:none">
						<table width="100%" border="1" align="center" class="tbstyle">
						<thead>
							<tr>
								<td align="center" width="80px">
									ѧ��
								</td>
								<td align="center" width="80px">
									ѧ��
								</td>
								<td align="center" width="110px">
									�������
								</td>
								<td align="center" width="110px">
									����ԭ��
								</td>
								<td align="center" width="80px">
									����ʱ��
								</td>
								<td align="center" width="110px">
									�����ĺ�
								</td>
							</tr>
							</thead>
							<logic:empty name="cfList">
								<tr>
								<td align="center" colspan="6">
									���޼�¼��
								</td>
								</tr>
							</logic:empty>
							<logic:notEmpty name="cfList">
								<logic:iterate id="s" name="cfList" >
									<tr onclick="rowOnClick(this);" style="cursor:hand;" >
										<logic:iterate id="v" name="s" >
											<td align="center">
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
						</table>
					</div>
				</td>
			</tr>
			
			<tr>
				<td align="right" colspan="4">
					<table width="100%" border="1" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main5" style="color:blue;cursor:hand"
									onclick="document.all.child5.style.display=(document.all.child5.style.display =='none')?'':'none';">
									<div align="center" class="style1 style3">
										<strong>ѧ���γ̳ɼ���Ϣ</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div id="child5" style="display:none">
						<table width="100%" border="1" align="center" class="tbstyle">
						<thead>
							<tr>
								<td align="center" width="80px">
									ѧ��
								</td>
								<td align="center" width="80px">
									ѧ��
								</td>
								<td align="center" width="110px">
									�γ�����
								</td>
								<td align="center" width="110px">
									�ɼ�
								</td>
								<td align="center" width="80px">
									�γ�����
								</td>
								<td align="center" width="110px">
									�����ɼ�
								</td>
								<td align="center" width="110px">
									���޳ɼ�
								</td>
							</tr>
							</thead>
							<logic:empty name="cjList">
								<tr>
								<td align="center" colspan="7">
									���޼�¼��
								</td>
								</tr>
							</logic:empty>
							<logic:notEmpty name="cjList">
								<logic:iterate id="s" name="cjList" >
									<tr onclick="rowOnClick(this);" style="cursor:hand;" >
										<logic:iterate id="v" name="s" >
											<td align="center">
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
						</table>
					</div>
				</td>
			</tr>
				<tr>
					<td height="22" align="right">
						��Ҫ�¼����������
					</td>
					<td height="22" align="left" colspan="3">
						<html:textarea name="rs" property="zysj" rows="5" styleId="zysj" rows="6" style="width:540px;overflow:auto" readonly="true">
						</html:textarea>
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						<font color="red">*</font>���״̬��
						<br/>
					</td>
					<td height="22" align="left" colspan="3">
						<html:select property="sh" styleId="sh" >
									<html:option value="δ���">δ���</html:option>
									<html:option value="ͨ��">ͨ��</html:option>
									<html:option value="��ͨ��">��ͨ��</html:option>
								</html:select>
					</td>
				</tr>
				
				<logic:equal value="true" name="fdyQx">
					<tr>
						<td height="22" align="right">
							��������
							<br/>
						</td>
						<td height="22" align="left" colspan="3">
							<html:textarea property="yj" styleId="yj" rows="3" style="width:540px"></html:textarea>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							<bean:message key="lable.xsgzyxpzxy" />��������
							<br/>
						</td>
						<td height="22" align="left" colspan="3">
							<html:textarea name="rs" property="xyyj" styleId="xyyj" rows="3" readonly="true" style="width:540px;overflow:auto"></html:textarea>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							ѧУ��������
							<br/>
						</td>
						<td height="22" align="left" colspan="3">
							<html:textarea name="rs" property="xxyj" styleId="xxyj" rows="3" readonly="true" style="width:540px;overflow:auto"></html:textarea>
						</td>
					</tr>
				</logic:equal>
				<logic:notEqual value="true" name="fdyQx">
					<logic:equal value="xy" name="userType">
						<tr>
						<td height="22" align="right">
							����Ա��������
							<br/>
						</td>
						<td height="22" align="left" colspan="3">
							<html:textarea name="rs" property="fdyyj" styleId="fdyyj" rows="3" readonly="true" style="width:540px;overflow:auto"></html:textarea>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							��������
							<br/>
						</td>
						<td height="22" align="left" colspan="3">
							<html:textarea property="yj" styleId="yj" rows="3" style="width:540px;"></html:textarea>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							ѧУ��������
							<br/>
						</td>
						<td height="22" align="left" colspan="3">
							<html:textarea name="rs" property="xxyj" styleId="xxyj" rows="3" readonly="true" style="width:540px;overflow:auto"></html:textarea>
						</td>
					</tr>
					</logic:equal>
					<logic:notEqual value="xy" name="userType">
					<tr>
						<td height="22" align="right">
							����Ա��������
							<br/>
						</td>
						<td height="22" align="left" colspan="3">
							<html:textarea name="rs" property="fdyyj" styleId="fdyyj" rows="3" readonly="true" style="width:540px;overflow:auto"></html:textarea>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							<bean:message key="lable.xsgzyxpzxy" />��������
							<br/>
						</td>
						<td height="22" align="left" colspan="3">
							<html:textarea name="rs" property="xyyj" styleId="xyyj" rows="3" readonly="true" style="width:540px;overflow:auto"></html:textarea>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							��������
							<br/>
						</td>
						<td height="22" align="left" colspan="3">
							<html:textarea property="yj" styleId="yj" rows="3" style="width:540px;"></html:textarea>
						</td>
					</tr>
					</logic:notEqual>
				</logic:notEqual>
			</table>
			<br />
			<div class="buttontool" id="button" align="center">
			<logic:notEqual value="view" name="writable">
			<logic:notEqual value="�ύ" name="tjzt">
			<button type="button" class="button2" onclick="saveinfo('pjpy_czxx_rychDggrsh.do?act=save','')" style="width:80px"
					id="btn_save">
					�� ��
				</button>
				&nbsp;&nbsp;
				</logic:notEqual>
				</logic:notEqual>
				<logic:equal value="�ύ" name="tjzt">
					<div align="center">
						<font color="red">��ز��������,�����ٽ��в���!</font>
					</div>
				</logic:equal>
				<button type="button" class="button2" onclick="window.close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
		</html:form>
		<logic:present name="inserted">
			<logic:equal value="yes" name="inserted">
				<script>
					alert("�����ɹ�!");
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script>
					alert("����ʧ��!" + $('message').value);
				</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
