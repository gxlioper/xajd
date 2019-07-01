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
<html:html>
<base target="_self" />
<head>


	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="������� zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/xszzCommonN05DWR.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	
	<script language="javascript">
		function yz(){
			var userType = val('userType');
			var xxsh = val("xxsh");
			var xysh = val("xysh");
			var shjb = val('shjb');
			var isFdy = val('isFdy');
			
			if(("δ���" != xxsh ) && (userType == "xy")){
				alert("ѧУ����ˣ��������޸���˽��!");
	          	return false;
			}
			if(("δ���" != xysh ) && (userType == "fdy") && shjb == "3"){
				alert("<bean:message key="lable.xsgzyxpzxy" />����ˣ��������޸���˽��!");
	          	return false;
			}
			//�ύ
			document.forms[0].action = "/xgxt/n05_xszz.do?method=xfhj3ShOne&type=save";
			document.forms[0].submit();
			
		}
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ��
			var pkValue = val("pkValue");
			showOpenWindow( "/xgxt/n05_xszz.do?method=xfhj3sqb&pkValue" + pkValue, 700,500);
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m"> 
			ǰ����λ�ã�ѧ������ - ѧ�ѻ��� 
		</div>
	</div>
		<html:form action="n05_xszz.do" method="post">
			<input type="hidden" id="shjb" name="shjb" value="${shjb}" />
			<input type="hidden" id="isFdy" name="isFdy" value="${isFdy}" />
			<input type="hidden" id="xysh" name="xysh" value="${rs.xysh}" />
			<input type="hidden" id="xxsh" name="xxsh" value="${rs.xxsh}" />
			<input type="hidden" id="userType" name="userType" value="${userType}" />
			<input type="hidden" id="pkValue" name="pkValue" value="${rs.pkValue}" />
			<logic:present name="result">
				<input type="hidden" id="message" value="${message}"/>
				<script>
					alert(document.getElementById('message').value);
				</script>
			</logic:present>
			<table class="tbstyle" width="100%">
				<tr>
					<td align="center" width="16%">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td align="left" width="34%">
							${rs.xh}						
						</td>
					<td width="16%">
						<div align="center">
							������
						</div>
					</td>
					<td width="34%">
						${rs.xm}
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							�Ա�
						</div>
					</td>
					<td>
						${rs.xb}
					</td>
					<td>
						<div align="center">
							���֤�ţ�
						</div>
					</td>
					<td>
						${rs.sfzh}
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							���壺
						</div>
					</td>
					<td>
						${rs.mzmc}
					</td>
					<td>
						<div align="center">
							������ò��
						</div>
					</td>
					<td>
						${rs.zzmmmc}
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							�꼶��
						</div>
					</td>
					<td>
						${rs.nj}
					</td>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />���ƣ�
						</div>
					</td>
					<td>
						${rs.xymc}
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							רҵ���ƣ�
						</div>
					</td>
					<td>
						${rs.zymc}
					</td>
					<td>
						<div align="center">
							�༶���ƣ�
						</div>
					</td>
					<td>
						${rs.bjmc}
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							ѧ�꣺
						</div>
					</td>
					<td colspan="3">
						${rs.xn}
					</td>					
				</tr>
				<tr>
					<td>
						<div align="center">
							������ֽ�����
						</div>
					</td>
					<td colspan="3">
						${rs.hjjl}
					</td>					
				</tr>				
				<tr>
					<td>
						<div align="center">
							�����ȵڣ�
						</div>
					</td>
					<td>
						${rs.dydd}
					</td>
					<td>
						<div align="center">
							�Ͷ��ڼ��ۼƲ�������޿�������
						</div>
					</td>
					<td>
					${rs.ljbjgbxkms}
					</td>
				</tr>							
				<tr>
					<td>
						<div align="center">
							����ʱ�䣺
						</div>
					</td>
					<td>
						${rs.sqsj}
					</td>
					<td>
						<div align="center">
							������
						</div>
					</td>
					<td>
						${rs.hjje}
					</td>
				</tr>
				
				<tr>
					<td>
						<div align="center">
							Ԥ�Ƶ�һ�ν��ѽ�
						</div>
					</td>
					<td>
						${rs.yjdycjfje}
					</td>
					<td>
						<div align="center">
							Ԥ�Ƶ�һ�ν���ʱ�䣺
						</div>
					</td>
					<td>
						${rs.yjdycjfsj}
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							Ԥ�Ƶڶ��ν��ѽ�
						</div>
					</td>
					<td>
						${rs.yjdecjfje}
					</td>
					<td>
						<div align="center">
							Ԥ�Ƶڶ��ν���ʱ�䣺
						</div>
					</td>
					<td>
						${rs.yjdecjfsj}
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��ͥ�����������֤����
						</div>
					</td>
					<td colspan="3">
						${rs.save_tkz}
						${rs.save_zdshbzz}
						${rs.save_shfzz}
						${rs.save_zxzm}
					</td>					
				</tr>
				<tr>
					<td>
						<div align="center">
							��ͥ���ڵ����������ߣ�
						</div>
					</td>
					<td colspan="3">
						${rs.save_jtszdzdshbzx}
					</td>					
				</tr>
				<tr>
					<td>
						<div align="center">
							�ڵ������ܾ������Ѳ��������<br/>
							(����˵���������ʡ�����)
						</div>
					</td>
					<td colspan="3">
						${rs.save_xsjjknbzqk}
					</td>					
				</tr>
				<tr>
					<td>
						<div align="center">
							��ĸ�Ƿ�֪��ѧ�ѻ�����
						</div>
					</td>
					<td colspan="3">
						${rs.save_fmsfzxxfhj}
					</td>					
				</tr>
				<tr>
					<td>
						<div align="center">
							����ѧ�ѻ�����Ҫԭ��
						</div>
					</td>
					<td colspan="3">
						${rs.save_sqyy}
					</td>					
				</tr>
				<logic:equal value="3" name="shjb">					
						<tr>
						<td>
							<div align="center">
								����Ա��ˣ�
							</div>
						</td>
						<td colspan="3">
							<!--����Ա���-->
							<logic:equal value="true" name="fdyQx">
							<html:select property="save_fdysh" name="rs">
								<html:option value=""></html:option>
								<html:options collection="chkList" property="en" labelProperty="cn"/>
							</html:select>
							<input type="hidden" id="shsj" name="save_fdyshsj" value="${sj}"/>
							</logic:equal>
							<!--end����Ա���-->
							<logic:notEqual value="true" name="fdyQx">
								${rs.save_fdysh}
							</logic:notEqual>
						</td>
						</tr>
						<tr>
						<td>
							<div align="center">
								����Ա��������
							</div>
						</td>
						<td colspan="3">
							<!--����Ա���-->
							<logic:equal value="true" name="fdyQx">
							<html:textarea property="save_fdyshyj" onblur="chLeng(this,50)" name="rs"></html:textarea>
							</logic:equal>
							<!--end����Ա���-->
							<logic:notEqual value="true" name="fdyQx">
								${rs.save_fdyshyj}
							</logic:notEqual>
						</td>
						</tr>

						<tr>
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />��ˣ�
							</div>
						</td>
						<td colspan="3">
							<logic:notEqual value="true" name="fdyQx">
							<logic:equal value="xy" name="userType">
								<html:select property="save_xysh" name="rs">
								<html:option value=""></html:option>
								<html:options collection="chkList" property="en" labelProperty="cn"/>
							</html:select>
							<input type="hidden" id="shsj" name="save_xyshsj" value="${sj}"/>
							</logic:equal>
							</logic:notEqual>
							
							<logic:equal value="true" name="fdyQx">
							${rs.save_xysh}
							</logic:equal>

							<logic:notEqual value="true" name="fdyQx">
								<logic:notEqual value="xy" name="userType">
									${rs.save_xysh}
								</logic:notEqual>
							</logic:notEqual>
						</td>
						</tr>
						<tr>
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />��������
							</div>
						</td>
						<td colspan="3">
							<logic:notEqual value="true" name="fdyQx">
							<logic:equal value="xy" name="userType">
								<html:textarea property="save_xyshyj" onblur="chLeng(this,50)" name="rs" style="width:100%"></html:textarea>
							</logic:equal>
							</logic:notEqual>
							
							<logic:equal value="true" name="fdyQx">
							${rs.save_xyshyj}
							</logic:equal>

							<logic:notEqual value="true" name="fdyQx">
								<logic:notEqual value="xy" name="userType">
									${rs.save_xyshyj}
								</logic:notEqual>
							</logic:notEqual>
						</td>
						</tr>

						<tr>
						<td>
							<div align="center">
								ѧУ��ˣ�
							</div>
						</td>
						<td colspan="3">
							<logic:notEqual value="true" name="fdyQx">
							<logic:notEqual value="xy" name="userType">
								<html:select property="save_xxsh" name="rs">
								<html:option value=""></html:option>
								<html:options collection="chkList" property="en" labelProperty="cn"/>
							</html:select>
							<input type="hidden" id="shsj" name="save_xxshsj" value="${sj}"/>
							</logic:notEqual>
							</logic:notEqual>
							
							<logic:equal value="true" name="fdyQx">
							${rs.save_xxsh}
							</logic:equal>

							<logic:notEqual value="true" name="fdyQx">
								<logic:equal value="xy" name="userType">
									${rs.save_xxsh}
								</logic:equal>
							</logic:notEqual>
						</td>
						</tr>
						<tr>
						<td>
							<div align="center">
								ѧУ��������
							</div>
						</td>
						<td colspan="3">
							<logic:notEqual value="true" name="fdyQx">
							<logic:notEqual value="xy" name="userType">
								<html:textarea property="save_xxshyj" onblur="chLeng(this,50)" name="rs" style="width:100%"></html:textarea>
							</logic:notEqual>
							</logic:notEqual>
							
							<logic:equal value="true" name="fdyQx">
							${rs.save_xxshyj}
							</logic:equal>

							<logic:notEqual value="true" name="fdyQx">
								<logic:equal value="xy" name="userType">
									${rs.save_xxshyj}
								</logic:equal>
							</logic:notEqual>
						</td>
						</tr>
				</logic:equal>
				
				<logic:notEqual value="3" name="shjb">	
						<tr>
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />��ˣ�
							</div>
						</td>
						<td colspan="3">
							<logic:equal value="xy" name="userType">
								<html:select property="save_xysh" name="rs">
								<html:option value=""></html:option>
								<html:options collection="chkList" property="en" labelProperty="cn"/>
							</html:select>
							<input type="hidden" id="shsj" name="save_xyshsj" value="${sj}"/>
							</logic:equal>
							<logic:notEqual value="xy" name="userType">
								${rs.save_xysh}
							</logic:notEqual>
						</td>
						</tr>
						<tr>
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />��������
							</div>
						</td>
						<td colspan="3">
							<logic:equal value="xy" name="userType">
								<html:textarea property="save_xyshyj" onblur="chLeng(this,50)" name="rs" style="width:100%"></html:textarea>
							</logic:equal>
							<logic:notEqual value="xy" name="userType">
								${rs.save_xyshyj}
							</logic:notEqual>
						</td>
						</tr>

						<tr>
						<td>
							<div align="center">
								ѧУ��ˣ�
							</div>
						</td>
						<td colspan="3">
							<logic:notEqual value="xy" name="userType">
								<html:select property="save_xxsh" name="rs">
								<html:option value=""></html:option>
								<html:options collection="chkList" property="en" labelProperty="cn"/>
							</html:select>
							<input type="hidden" id="shsj" name="save_xxshsj" value="${sj}"/>
							</logic:notEqual>
							<logic:equal value="xy" name="userType">
								${rs.save_xxsh}
							</logic:equal>
						</td>
						</tr>
						<tr>
						<td nowrap="nowrap">
							<div align="center">
								ѧУ��������
							</div>
						</td>
						<td colspan="3">
							<logic:notEqual value="xy" name="userType">
								<html:textarea property="save_xxshyj" onblur="chLeng(this,50)" name="rs" style="width:100%"></html:textarea>
							</logic:notEqual>
							<logic:equal value="xy" name="userType">
								${rs.save_xxshyj}
							</logic:equal>
						</td>
						</tr>
					</logic:notEqual>	
			</table>
			<div class="buttontool" id="btn" style="position: absolute;width:100%">
				<button class="button2" id="buttonSave"
					onClick="yz();">
					��&nbsp;&nbsp;&nbsp;&nbsp;��
				</button>
				&nbsp;&nbsp;
				<button class="button2"
					onClick="Close()">
					��&nbsp;&nbsp;&nbsp;&nbsp;��
				</button>
			</div>
		</html:form>
	</body>	
</html:html>
