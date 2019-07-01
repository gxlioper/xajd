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
				url += "&type=outTransfer";
				refreshForm(url);
			}
			
		function print(url){
			var pk = "!!";
			var xh = document.getElementById("xh").value;
			var xm = document.getElementById("xm").value;
			var xb = document.getElementById("xbm").value;
			var zkzh = document.getElementById("zkzh").value;
			var mz = document.getElementById("mz").options[document.getElementById("mz").selectedIndex].text;
			var lxdh = document.getElementById("lxdh").value;
			var rxsj = document.getElementById("rxsj").value;
			var zcxxmc = document.getElementById("zcxxmc").value;			
			var zczymc = document.getElementById("zczydm").options[document.getElementById("zczydm").selectedIndex].text;
			var zcnj = document.getElementById("zcnj").value;
			var zcxz = document.getElementById("zcxz").value;
			var zcxlcc = document.getElementById("zcxlcc").value;
			var zrxxmc = document.getElementById("zrxxmc").value;
			var zrzymc = document.getElementById("zrzymc").value;
			var zrnj = document.getElementById("zrnj").value;
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
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-mzmc-lxdh" />
		<input type="hidden" id="url" name="url" value="/shgc/stu_info/xbemy/stuTransferApp.jsp" />
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
					<table width="100%" id="rsT" class="tbstyle">
						<thead>
							<tr style="height:22px">
								<td colspan="4" align="center">
									<b>ת����У����</b>
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
								 <html:text name="rs" property="xh" styleId="xh" readonly="true"/>
								</td>
							</logic:equal>
							<td align="right">
								<font color="red">*</font>׼��֤�ţ�
							</td>
							<td align="left">								
								<html:text name="rs" property="zkzh" styleId="zkzh"/>
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right" ><font color="red">*</font>������
							</td>
							<td align="left">
							<html:text property="xm" name="rs" styleId="xm"/>
							</td>							
							<td align="right">���壺
							</td>
							<td align="left">
								<html:select name="rs" property="mzdm" style="width:150px" styleId="mz">
								<html:option value=""></html:option>
								<html:options collection="mzList" labelProperty="mzmc"
									property="mzdm" />
							</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">�Ա�</td>
							<td>
								<html:text property="xb" name="rs" styleId="xbm"/>
							</td>
							<td align="right">��ѧʱ�䣺</td>
							<td align="left">
								<html:text name="rs" property="rxsj" styleId="rxsj" readonly="true" onclick="return showCalendar('rxsj','y-mm-dd');"/>
							</td>
						</tr>
						<tr style="height:22px">	
							<td align="right">��ϵ�绰��
							</td>
							<td align="left"  colspan="3">
								<html:text property="lxdh" name="rs" styleId="lxdh" style="width:100%"/>
							</td>
						</tr>
						<thead>
							<tr style="height:22px">
								<td colspan="4" align="center">
									ת��ת����Ϣ
								</td>
							</tr>
						</thead>
						<tr>
						<td rowspan="7" align="center">
						ת<br/>
						��<br/>
						ѧ<br/>
						У<br/>
						</td>
						<td align="right">
							У����
						</td>
						<td colspan="2">
						<html:text property="zcxxmc" name="rs" styleId="zcxxmc"/>
						</td>
						</tr>
						<tr>
						<td align="right">
							<font color="red">*</font>ת���꼶��
						</td>
						<td colspan="2">
						<html:select property="zcnj" name="rs" styleId="nj" onchange="initZyList();initBjList()">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj" labelProperty="nj"/>
							</html:select>
<%--						<html:text property="zcnj" name="rs" styleId="zcnj" />--%>
						</td>						  
					  </tr>
						<tr>
						<td align="right"><font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />��</td>
						  <td colspan="2">
						  <html:select property="zcxydm" name="rs" onchange="initZyList();initBjList()" styleId="xy" >
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
							</html:select>
						  </td>						
						</tr>						
						<tr>
						<td align="right">
							<font color="red">*</font>רҵ��
						</td>
						<td colspan="2">
						<html:select property="zczydm" name="rs" styleId="zy" onchange="initBjList()">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm" labelProperty="zymc"/>
							</html:select>
							
						</td>
						</tr>
						<tr>
						<td align="right">
							<font color="red">*</font>�༶��
						</td>
						<td colspan="2">
						<html:select property="zcbjdm" name="rs" styleId="bj">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm" labelProperty="bjmc"/>
							</html:select>
						</td>
						</tr>
						<tr>
						<td align="right">
							ѧ�ƣ�
						</td>
						<td colspan="2">
						<html:text property="zcxz" name="rs" styleId="zcxz"/>
						</td>
						</tr>
						<tr>
						<td align="right">
							ѧ����Σ�
						</td>
						<td colspan="2">
						<html:text property="zcxlcc" name="rs" styleId="zcxlcc"/>
						</td>
						</tr>
						<tr>
						<td rowspan="6" align="center" width="150px">
						ת<br/>
						��<br/>
						ѧ<br/>
						У<br/>
						</td>
						<td align="right">
							У����
						</td>
						<td colspan="2">
						<html:text name="rs" property="zrxxmc" styleId="zrxxmc"/>
						</td>
						</tr>
						<tr>
						  <td align="right"><bean:message key="lable.xsgzyxpzxy" />��</td>
						  <td colspan="2">
						  <html:text name="rs" property="zrxymc" styleId="zrxymc"/>
						  </td>
					  </tr>
						<tr>
						<td align="right">
							רҵ��
						</td>
						<td colspan="2">
						<html:text name="rs" property="zrzymc" styleId="zrzymc"/>
						</td>
						</tr>
						<tr>
						<td align="right">
							ת���꼶��
						</td>
						<td colspan="2">
						<html:text name="rs" property="zrnj" styleId="zrnj"/>
						</td>
						</tr>
						<tr>
						<td align="right">
							ѧ�ƣ�
						</td>
						<td colspan="2">
						<html:text name="rs" property="zrxz" styleId="zrxz"/>
						</td>
						</tr>
						<tr>
						<td align="right">
							ѧ����Σ�
						</td>
						<td colspan="2">
						<html:text name="rs" property="zrxlcc" styleId="zrxlcc"/>
						</td>
						</tr>
						<tr>
						<td align="right">תѧ���루���ɣ���</td>
						<td colspan="3">
						<html:textarea name="rs" property="sqly" styleId="sqly" style="width:100%;height:45px"/>
						</td>
						</tr>
					</table>
					<logic:equal value="yes" name="writeAble">
					<div class="buttontool" align="center">
						<button class="button2"
							onclick="commitInfo('xbemyStuStatus.do?method=showTransferApp&doType=save','xh-xm-zkzh-xy-zy-bj-zcnj')">
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
