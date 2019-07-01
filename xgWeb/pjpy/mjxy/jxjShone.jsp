
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
       function shoneJxj(){
     	refreshForm("/xgxt/mjxyJxj.do?method=jxjShone&doType=modi");
     	showTips("�����У���ȴ�...")
        if($("buttonSave")){
          $("buttonSave").disabled=true;
        }
     }
</script>
	<%--		<input type="hidden" id="printpk" value="${printpk }"/>--%>
	<body>
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getStuDetails.js"></script>
		   <script language="javascript" src="js/pjpy/pjpy_zjsyzy.js"></script>
		   <html:form action="/mjxyJxj" method="post">
			<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj-jg-mzmc" />
			<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xh-xm-xb-xymc-nj-zymc-bjmc-jg-mzmc" />
			<input type="hidden" id="url" name="url" value="/nbty_rych.do?method=rychSq" />
			<input type="hidden" id="viewName" name="viewName" value="view_nbty_xsrychb" />
			<input type="hidden" id="tabName" name="tabName" value="nbty_xsrychb" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" name="message" id="message" value="${message }"/>
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
			<input type="hidden" name="save_xq" id="save_xq" value="${rs.xq }"/>
			<input type="hidden" name="save_xn" id="save_xn" value="${rs.xn }"/>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ��:${title }
				</div>
			</div>

			<logic:present name="result">
			<script>
				alert(''+$('message').value);
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
			</logic:present>

			<table class="tbstyle" width="100%">
				<tr style="height:22px">
						<td colspan="4" align="center">
							<b>��д�����</b>
						</td>
					</tr>
				<tr>
					<td align="right" width="16%">
						<font color="red">*</font>ѧ�ţ�
					</td>
					<td align="left" width="34%">
							<html:text name="rs" property="save_xh" readonly="true" styleId="xh" value="${rs.xh}" />
					</td>
					<td width="16%">
						<div align="right">
							������
						</div>
					</td>
					<td width="34%">
						${rs.xm }
					</td>
				</tr>
				<tr>
					<td align="right">
						�Ա�
					</td>
					<td>
						${rs.xb }
					</td>
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td>
						${rs.xymc }
					</td>
				</tr>
				<tr>
					<td align="right">
						רҵ��
					</td>
					<td>
						${rs.zymc }
					</td>
					<td align="right">
						�༶��
					</td>
					<td>
						${rs.bjmc }
					</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>ѧ�꣺
					</td>
					<td>
						${rs.xn}
					</td>
					<td align="right">
						��ѧ�����ƣ�
					</td>
					<td>
						${rs.jxjmc }
						<html:hidden property="save_jxjdm" value="${rs.jxjdm }" styleId="jxjdm"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						�������£�
					</td>
					<td>
						<bean:write name="rs" property="csrq"/>
					</td>
					<td align="right">
						���壺
					</td>
					<td>
						<bean:write name="rs" property="mzmc"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						������ò��
					</td>
					<td>
						<bean:write name="rs" property="zzmmmc"/>
					</td>
					<td align="right">
						רҵ������
					</td>
					<td>
						${zyrs}��
					</td>
				</tr>
				<tr>
					<td align="right">
						�꼶��
					</td>
					<td>
						${rs.nj}
					</td>
					<td align="right">
						�ֻ����룺
					</td>
					<td>
						<bean:write name="rs" property="sjhm"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						���п��ţ�
					</td>
					<td>
						<bean:write name="rs" property="yhkh" />
					</td>
					<td align="right">
						��ѧ����ְ�����
					</td>
					<td>
						<html:text name="rs" property="drzw"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						�ۺϲ����ɼ�������
					</td>
					<td>
						<html:text name="rs" property="zhszcpcjnjpm"/>
					</td>
					<td align="right">
						��ѧ�겹��������
					</td>
					<td>
						${bkms }��
					</td>
				</tr>
				<tr>
					<td align="right">
						��ѧ���ۿ�������
					</td>
					<td>
					   ${zkms}��
					</td>
					<td align="right">
						�������ڣ�
					</td>
					<td>
						${sqsj}
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						��ˣ�
					</td>
					<td align="left">
					<logic:equal name="userType" value="xy">
						<html:select property="save_xysh">
							<html:option value="δ���">δ���</html:option>
							<html:option value="ͨ��">ͨ��</html:option>
							<html:option value="��ͨ��">��ͨ��</html:option>
						</html:select>
					</logic:equal>
					<logic:equal name="userType" value="admin">
						<html:select property="save_xxsh">
							<html:option value="δ���">δ���</html:option>
							<html:option value="ͨ��">ͨ��</html:option>
							<html:option value="��ͨ��">��ͨ��</html:option>
						</html:select>
					</logic:equal>
					<logic:equal name="userType" value="xx">
						<html:select property="save_xxsh">
							<html:option value="δ���">δ���</html:option>
							<html:option value="ͨ��">ͨ��</html:option>
							<html:option value="��ͨ��">��ͨ��</html:option>
						</html:select>
					</logic:equal>
					</td>
					<td align="right">
						
					</td>
					<td align="left">
						
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						���˼�����
						<br />
						<span class="style1">(��400��)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" name="rs" property="xxjl" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�������
						<br />
						<span class="style1">(��400��)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" name="rs" style="width:98%" property="jfqk" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						��Ҫ�¼���
						<br />
						<span class="style1">(��400��)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" name="rs" property="zysj" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						����Ա�����
						<br />
						<span class="style1">(��400��)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" name="rs" property="save_fdyyj" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						Ժϵ�����
						<br />
						<span class="style1">(��400��)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" name="rs" property="save_xyshyj" onblur="chLeng(this,400)"/>
					</td>
				</tr>
			</table>
			<div class="buttontool" id="btn"  width="100%">	
				<logic:equal name="writeAble" value="yes">
					<logic:equal name="write" value="yes">
						<button class="button2" id="buttonSave" onclick="shoneJxj()" style="width:80px">
						��  �� 
						</button>
					</logic:equal>
				</logic:equal>			
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="Close();return false;" style="width:80px" id="buttonClose">
							�� ��
					</button>
			</div>
			<logic:equal name="done" value="true">
				<script>
			          alert("����ɹ���");
			        </script>
			</logic:equal>
			<logic:equal name="done" value="false">
				<script>
			          alert("����ʧ�ܣ�");
			        </script>
			</logic:equal>
			<logic:equal name="isExist" value="no">
				<script>
			        alert("�������ƺ�������,����ͨ����ز������\n�����������,�����ٴ����룡");			    
			        </script>
			</logic:equal>
			<logic:equal name="pass" value="no">
				<script>
			        alert("������������������ƺ�����������");			    
			        </script>
			</logic:equal>
		</html:form>
	</body>


</html>

