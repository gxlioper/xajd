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
    <base target="_self">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
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
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/commanFunction.js"></script>
		<script language="javascript" src="js/calendar.js"></script>
		<script language="javascript" src="js/calendar-zh.js"></script>
		<script language="javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/AjaxFunction.js"></script>		
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script>
			function commitInfo(url,param){
				var nullStr = param.split("-");
				for(var i=0; i<nullStr.length; i++){
					if($(nullStr)){
						var Str = document.getElementById(nullStr[i]).value; 
						if(Str==null || Str == ""){
							alert('�뽫��\*�ŵ���Ŀ��д����!');
							return false;
						}
					}
				}
				refreshForm(url);
			}
			
		function print(url){
			var pk = "!!";
			var xh = document.getElementById("xh").value;
			var xm = document.getElementById("xm").value;
			var xb = document.getElementById("xbm").value;
			var zkzh = document.getElementById("zkzh").value;
			var mz = document.getElementById("mz").value;
			var lxdh = document.getElementById("lxdh").value;
			var rxsj = document.getElementById("rxsj").value;
			var zcxxmc = document.getElementById("zcxxmc").value;
			
			var zczymc = document.getElementById("zczymc").value;
			var zcnj = document.getElementById("zcnj").value;
			var zcxz = document.getElementById("zcxz").value;
			var zcxlcc = document.getElementById("zcxlcc").value;
			var zrxxmc = document.getElementById("zrxxmc").value;
			var zrzymc = document.getElementById("zy").options[document.getElementById("zy").selectedIndex].text;
			var zrnj = document.getElementById("nj").value;
			var zrxz = document.getElementById("zrxz").value;
			var zrxlcc = document.getElementById("zrxlcc").value;
			var sqly = document.getElementById("sqly").value;
			
			url += "&xh=" + xh;
			url += "&xm=" + xm;
			url += "&xb=" + xb;
			url += "&zkzh=" + zkzh;
			url += "&mz=" + mz;
			url += "&lxdh=" + lxdh;
			url += "&rxsj=" + rxsj;
			url += "&zcxxmc=" + zcxxmc;
			
			url += "&zczymc=" + zczymc;
			url += "&zcnj=" + zcnj;
			url += "&zcxz=" + zcxz;
			url += "&zcxlcc=" + zcxlcc;
			
			url += "&zrxxmc=" + zrxxmc;
			url += "&zrzymc=" + zrzymc;
			url += "&zrnj=" + zrnj;
			url += "&zrxz=" + zrxz;
			url += "&zrxlcc=" + zrxlcc;
			
			url += "&sqly=" + sqly;
			
			window.open(url);
			}
		</script>
	<body>
		<html:form action="/xbemyStuStatus.do" method="post">
		<input type="hidden" name="xyV" value=""/>
		<input type="hidden" name="zyV" value=""/>
		<input type="hidden" name="bjV" value=""/>
		<input type="hidden" name="njV" value=""/>
		<html:hidden property="zxlx" name="rs"/>
		<html:hidden property="sqrq" styleId="sqrq"/>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã�ѧ����Ϣ - ѧ���䶯���� - תѧ����
				</div>
			</div>
				<fieldset>
					<legend>
						��д�����
					</legend>
					<table width="100%" height="541" class="tbstyle" id="rsT">
						<thead>
							<tr style="height:22px">
								<td colspan="5" align="center">
									<b>ת�뱾У����</b>
								</td>
							</tr>
						</thead>						
						<tr style="height:22px">
							<logic:equal name="userOnLine" value="teacher" scope="session">
								<td align="right">
									<font color="red">*</font>ѧ�ţ�
								</td>
								<td align="left">
								<html:text name="rs" property="xh" styleId="xh"/>									
								</td>
							</logic:equal>
							<logic:equal name="userOnLine" value="student" scope="session">
								<td width="99" align="right">
									<font color="red">*</font>ѧ�ţ�
								</td>
								<td colspan="2" align="left">
									<input type="text" id="xh" name="xh" value="<bean:write name="userName"/>" readonly="readonly" />
								</td>
							</logic:equal>
							<td align="right">
								<font color="red">*</font>׼��֤�ţ�
							</td>
							<td colspan="2" align="left">								
								<html:text name="rs" property="zkzh" styleId="zkzh"/>
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right" ><font color="red">*</font>������
							</td>
							<td align="left">
								<html:text name="rs"  property="xm" styleId="xm" />
							</td>							
							<td align="right">���壺</td>
							<td colspan="2" align="left">
								<html:select name="rs"  property="mzdm"  style="width:150px" styleId="mz">
								<html:option value=""></html:option>
								<html:options collection="mzList" labelProperty="mzmc" property="mzdm"/>							
								</html:select>
							</td>
						</tr>
						<tr>
						  <td align="right">�Ա�</td>
						  <td>
						  <html:radio name="rs" property="xb" value="��" styleId="xb" onclick="javascript: document.getElementById('xbm').value=this.value">��
						  </html:radio> 
						  <html:radio name="rs" property="xb" value="Ů" styleId="xb" onclick="javascript: document.getElementById('xbm').value=this.value">Ů
						  </html:radio>
                            <input type="hidden" id="xbm" name="xbm" value=""/></td>
						  <td align="right">��Դ�أ� </td>
						  <td colspan="2" align="left">
						  	<html:text name="rs" property="syd" styleId="syd"/>
						  </td>
					  </tr>
						<tr>
							<td align="right" style="width:120px">���֤�ţ�</td>
							<td>
								<html:text name="rs" property="sfzh" styleId="sfzh"/>
							</td>
							<td align="right">��ѧʱ�䣺
							</td>
							<td colspan="2" align="left">
								<html:text name="rs" property="rxsj" styleId="rxsj" readonly="true" onclick="return showCalendar('rxsj','y-mm-dd');"/>
							</td>
						</tr>
						<tr style="height:22px">	
							<td align="right">�������ڣ�</td>
							<td align="left">
								<html:text name="rs" property="csrq" styleId="csrq" readonly="true" onclick="return showCalendar('csrq','y-mm-dd');"/>
							</td>
						    <td align="left"><div align="right">��ϵ�绰��</div></td>
						    <td colspan="2" align="left">
							<html:text name="rs" property="lxdh" styleId="lxdh"/>
							</td>
						</tr>
						<thead>
							<tr style="height:22px">
								<td colspan="5" align="center">
									ת��ת����Ϣ
								</td>
							</tr>
						</thead>
						<tr>
						<td rowspan="3" align="center">
						ת<br/>
						��<br/>
						ѧ<br/>
						У<br/>
						</td>
						<td align="right">
							У����
						</td>
						<td>
						<html:text name="rs" property="zcxxmc" styleId="zcxxmc"/>
						</td>
						<td width="117"><div align="right">ת���꼶��</div></td>
						<td width="142"><html:text name="rs" property="zcnj" styleId="zcnj"/></td>
						</tr>
						<tr>
						<td align="right">
							רҵ��
						</td>
						<td>
						<html:text name="rs" property="zczymc" styleId="zczymc"/>
						</td>
						<td><div align="right">ѧ�ƣ�</div></td>
						<td><html:text name="rs" property="zcxz" styleId="zcxz"/></td>
						</tr>
						
						<tr>
						<td height="22" align="right">ѧ����Σ�
						  </td>
						<td colspan="3">
						<html:text name="rs" property="zcxlcc" styleId="zcxlcc" style="width:100%"/>
						</td>
						</tr>
						
						<tr>
						<td rowspan="18" align="center" >
						ת<br/>
						��<br/>
						ѧ<br/>
						У<br/>
						</td>
						<td align="right" width="162">
							У����
						</td>
						<td>
						<html:text name="rs" property="zrxxmc" styleId="zrxxmc"/>
						</td>
						<td><div align="right">ѧ��״̬��</div></td>
						<td>
						<html:select property="xjztm" name="rs">
						<html:option value=""></html:option>
						<html:option value="��ѧ��">��ѧ��</html:option>
						<html:option value="��ѧ��">��ѧ��</html:option>
						</html:select>
						</td>
						</tr>
						<tr>
						<td align="right"><font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />��</td>
						<td>
							<html:select property="zrxydm" name="rs" onchange="initZyList();initBjList()" styleId="xy" >
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
							</html:select>
						</td>
						<td align="right">��������</td>
						<td>
							<html:text name="rs" property="cym" styleId="cym"/>
						</td>
						</tr>
						<tr>
						  <td align="right"><font color="red">*</font>רҵ��</td>
						  <td>
<%--						  	<html:text name="rs" property="zrzydm" styleId="zrzymc"/>--%>
						  	<html:select property="zrzydm" name="rs" styleId="zy" onchange="initBjList()">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm" labelProperty="zymc"/>
							</html:select>
						  	</td>
						  <td><div align="right">�����ţ�</div></td>
						  <td>
						  	<html:text name="rs" property="ksh" styleId="ksh"/>
						  </td>
					  </tr>
						<tr>
						  <td align="right"><font color="red">*</font>�༶��</td>
						  <td>
						  	<html:select property="zrbjdm" name="rs" styleId="bj">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm" labelProperty="bjmc"/>
							</html:select>
						  </td>
						  <td><div align="right">���᣺</div></td>
						  <td>
						  <html:text name="rs" property="jg" styleId="jg"/>
						  </td>
					  </tr>
						<tr>
						  <td align="right"><font color="red">*</font>�꼶��</td>
						  <td>
						  <html:select property="zrnj" name="rs" styleId="nj">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj" labelProperty="nj"/>
							</html:select>
						  </td>
						  <td><div align="right">�����أ�</div></td>
						  <td>
						  <html:text name="rs" property="csd" styleId="csd"/>
						  </td>
					  </tr>
						<tr>
						<td align="right"><font color="red">*</font>ѧ�ƣ�
						  </td>
						<td>
						<html:text name="rs" property="zrxz" styleId="zrxz"/>
						</td>
						<td><div align="right">��ҵ���ڣ�</div></td>
						<td>
						<html:text name="rs" property="byrq" styleId="byrq"/>
						</td>
						</tr>
						<tr>
						<td align="right">������Σ�
						  </td>
						<td>
						<html:text name="rs" property="zrxlcc" styleId="zrxlcc"/>
						</td>
						<td><div align="right">�Ͻ�ҵ���ۣ�</div></td>
						<td>
						<html:text name="rs" property="bjyjl" styleId="bjyjl"/>
						</td>
						</tr>
						<tr>
						  <td align="right">רҵ���</td>
						  <td>
						  <html:text name="rs" property="zylb" styleId="zylb"/>
						  </td>
						  <td><div align="right">֤���ţ�</div></td>
						  <td>
						  <html:text name="rs" property="zsbh" styleId="zsbh"/>
						  </td>
					  </tr>
						<tr>
						  <td align="right">רҵ����</td>
						  <td>
						  <html:text name="rs" property="zyfx" styleId="zyfx"/>
						  </td>
						  <td><div align="right">֤�����кţ�</div></td>
						  <td>
						  <html:text name="rs" property="zsxlh" styleId="zsxlh"/>
						  </td>
					  </tr>
						<tr>
						  <td align="right">��������</td>
						  <td>
						  <html:text name="rs" property="pyfx" styleId="pyfx"/>
						  </td>
						  <td><div align="right">ѧλ��</div></td>
						  <td>
						  <html:text name="rs" property="xw" styleId="xw"/>
						  </td>
					  </tr>
						<tr>
						  <td align="right">����רҵ��</td>
						  <td>
						  <html:select property="fxzy" name="rs" styleId="fxzy">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm" labelProperty="zymc"/>
							</html:select>
						  </td>
						  <td><div align="right">ѧλ֤���ţ�</div></td>
						  <td>
						  <html:text name="rs" property="xwzsbh" styleId="xwzsbh"/>
						  </td>
					  </tr>
						<tr>
						  <td align="right">����רҵ����</td>
						  <td>
						  <html:text name="rs" property="fxzyfx" styleId="fxzyfx"/>
						  </td>
						  <td><div align="right">ѧλ֤�����кţ�</div></td>
						  <td>
						  <html:text name="rs" property="xwzsxlh" styleId="xwzsxlh"/>
						  </td>
					  </tr>
						<tr>
						  <td align="right">��ѧ���ͣ�</td>
						  <td>
						  <html:text name="rs" property="bxlx" styleId="bxlx"/>
						  </td>
						  <td><div align="right">У��������</div></td>
						  <td>
						  <html:text name="rs" property="xzxm" styleId="xzxm"/>
						  </td>
					  </tr>
						<tr>
						  <td align="right">��ѧ��ʽ��</td>
						  <td>
						  <html:text name="rs" property="bxxs" styleId="bxxs"/>
						  </td>
						  <td><div align="right">��˱�ǣ�</div></td>
						  <td>
						  <html:text name="rs" property="shbj" styleId="shbj"/>
						  </td>
					  </tr>
						<tr>
						  <td align="right">ѧϰ��ʽ��</td>
						  <td>
						  <html:text name="rs" property="xxxs" styleId="xxxs"/>
						  </td>
						  <td><div align="right">��ӡ��ǣ�</div></td>
						  <td>
						  <html:text name="rs" property="dybj" styleId="dybj"/>
						  </td>
					  </tr>
						<tr>
						  <td align="right">�������ڣ�</td>
						  <td>
						  <html:text name="rs" property="zsjj" styleId="zsjj"/>
						  </td>
						  <td><div align="right">�滻��ʶ��</div></td>
						  <td>
						  <html:text name="rs" property="thbs" styleId="thbs"/>
						  </td>
					  </tr>
						<tr>
						  <td align="right">������ò��</td>
						  <td >
						  <html:select property="zzmm" name="rs">
						  <html:option value=""></html:option>
						  <html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
						  </html:select>
						  </td>
						  <td align="right">ѧУ���ڵأ�</td>
						  <td >
						  <html:text name="rs" property="xxszd" styleId="xxszd" style="width:100%"/>
						  </td>						  
					  </tr>
						<tr>
						  <td align="right">��ע��</td>
						  <td colspan="3">
						  <html:textarea property="bz" name="rs" style="width:100%">
						  
						  </html:textarea>
						  </td>
					  </tr>
						<tr>
						<td align="right">תѧ���루���ɣ���</td>
						<td colspan="4">
						<html:textarea name="rs" property="sqly" styleId="sqly" style="width:100%;height:45px"/>
						</td>
						</tr>
					</table>
					<logic:equal value="yes" name="writeAble">
					<div class="buttontool" align="center">
						<button class="button2"
							onclick="commitInfo('xbemyStuStatus.do?method=showTransferApp&doType=save','xh-xm-zkzh-xy-xy-bj-nj-zrxz')">
							�� �� �� ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="print('xbemyStuStatus.do?method=printTransferApp')">
							�� ӡ �� ��
						</button>
					</div>
					</logic:equal>
				</fieldset>
				
				<logic:notEmpty name="result">
				<logic:equal value="true" name="result">
				<script>
					alert("����ɹ���");
				</script>
				</logic:equal>
				<logic:equal value="false" name="result">
				<script>
					alert("����ʧ�ܣ�");
				</script>
				</logic:equal>
				</logic:notEmpty>
		</html:form>
	</body>
</html>
