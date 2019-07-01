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
    function check() {
    		var xh = document.getElementById('xh').value;
    		var xn = document.getElementById('xn').value;
			var xq = document.getElementById('xq').value;
			var jxjdm = document.getElementById('jxjdm').value;
			
			if (xh =='' || xn=='' || jxjdm=='') {
				alert("��*���ֶ�Ϊ�����");
				return false;
			}
			
			//���ѧ�����뽱ѧ�������Ƿ�����
			CzxxJxjDao.checkJxjSqtj(xh,xn,xq,jxjdm,'jxj', function (data) {
				if (data != null) {
					if (data[0] == 'true') {//Υ�ʹ��ּ��ʧ��
						alert("����ʧ�ܣ�����������ѧ��ѧ���ڳ���Υ�ʹ��ּ�¼�������Ͻ�ѧ�������������");
						return false;
					} else if (data[1] == 'true') {//�۲��������ʧ��
						alert("����ʧ�ܣ�" + data[2]);
						return false;
					} else {
						saveinfo('pjpy_czxx_jxjModi.do?act=save','');
					}
				}
			});
    	}
     
    
    </script>
	<body >
		<html:form action="/czxxPjpyJxj" method="post">
			<input type="hidden" name="message" id="message" value="${message }"/>
			<input type="hidden" name="pkValue" id="pkValue" value="${rs.pkValue }"/>
		<div class="title"> 
				<div class="title_img" id="title_m">
					��ǰ����λ�ã��������� - ��ѧ������ - ��������ѯ
				</div>
			</div>
			<input type="hidden" name="save_xn" id="xn" value="${xn }"/>
			<input type="hidden" name="save_xq" id="xq" value="${xq }"/>
			<input type="hidden" name="save_nd" id="nd" value="${nd }"/>
			<table width="100%" class="tbstyle">
				<thead>
					<tr align="center">
						<td height="22" colspan="4">
							�޸�
						</td>
					</tr>
				</thead>
				<tr>
					<td height="22" align="right" style="width:25%">
						<font color="red">*</font>ѧ�ţ�
					</td>
					<td height="22" align="left" style="width:25%">
						<html:text property="save_xh" styleId="xh" name="rs" readonly="true"></html:text>
					</td>
					<td height="22" align="right" style="width:25%">
						ѧ�꣺
					</td>
					<td height="22" align="left" style="width:25%">
						${xn }
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						<font color="red">*</font>������
					</td>
					<td height="22" align="left">
						<html:text property="xm" styleId="xm" name="rs" disabled="true"></html:text>
					</td>
					<td height="22" align="right">
						ѧ�ڣ�
					</td>
					<td height="22" align="left">
						${xqmc }
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						�꼶��
					</td>
					<td height="22" align="left">
						<html:text property="nj" styleId="nj" name="rs" disabled="true"></html:text>
					</td>
					<td height="22" align="right">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td height="22" align="left">
						<html:text property="xymc" styleId="xymc" name="rs" disabled="true"></html:text>
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						רҵ��
					</td>
					<td height="22" align="left">
						<html:text property="zymc" styleId="zymc" name="rs" disabled="true"></html:text>
					</td>
					<td height="22" align="right">
						�༶��
					</td>
					<td height="22" align="left">
						<html:text property="bjmc" styleId="bjmc" name="rs" disabled="true"></html:text>
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
					<font color="red">*</font>��ѧ�����ƣ�
					</td>
					<td height="22" align="left">
						<html:select property="save_jxjdm" styleId="jxjdm" value="${rs.jxjdm}">
							<html:options collection="jxjList" property="jxjdm" labelProperty="jxjmc"/>
						</html:select>
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
						<br/>
						<font color="red">1000������&nbsp;&nbsp;&nbsp;&nbsp;</font>
					</td>
					<td height="22" align="left" colspan="3">
						<html:textarea property="save_zysj" rows="5" value="${rs.zysj}" styleId="zysj" rows="6" style="width:540px" onkeyup="checkLen(this,1000)">
						</html:textarea>
					</td>
					
				</tr>
			</table>
			<br />
			<div class="buttontool" id="button" align="center">
			<logic:notEqual value="view" name="writable">
			<button type="button"  class="button2" onclick="check();"  style="width:80px"
					id="btn_save">
					�� ��
				</button>
				&nbsp;&nbsp;
				</logic:notEqual>
				<button type="button" class="button2" onclick="window.close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
		</html:form>
		<logic:present name="result">
			<logic:equal value="true" name="result">
				<script>
					alert("" + $('message').value);
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
					alert("����ʧ��!�����ý�ѧ���Ѿ�����,��ز��������,�����ظ�����!" +);
				</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
