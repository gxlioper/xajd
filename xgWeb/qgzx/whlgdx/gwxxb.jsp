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
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script type="text/javascript" src="js/String.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>		
	<script type='text/javascript' src='dwr/util.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/cqkjFunc.js'></script>
	<script type="text/javascript" src="js/xgutil.js"></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>

	<script language="javascript">
	function getValue(){
		var value="";
		var syrs=document.getElementById("xyrs").value;
		var knsbl=document.getElementById("knsbl").value;
		//alert(syrs);
		value=Math.round(syrs*(knsbl*0.01));
		document.getElementById("xyknsrs").value=value;
	}
	
	function checkModi(){
		
		if($("shjg")){
			var shjg = document.getElementById("shjg").value.trim();
			if(shjg=="ͨ��"){
				document.getElementById("buttonSave").disabled="true";
			}
		}
	}
	
</script>
	<base target="_self" />
	<body onload="checkModi();">		
		<html:form action="/whlggwgl" method="post">
			<input type="hidden" id="xxdm" name="xxdm" value="<bean:write name="xxdm"/>" />
			<input type="hidden" id="dyxmdm" name="dyxmdm" value="001" />
			<input type="hidden" id="tabName" name="tabName" value="view_gwxx" />
			<logic:present name="gwsbsj">
				<input type="hidden" id="gwsbsj" name="gwsbsj" value="<bean:write name="gwsbsj"/>" />
			</logic:present>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã��ڹ���ѧ - ��λ���� - ��λ��Ϣ����
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
				<input type="hidden" id="doType" name="doType" value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue" value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="knsbl" name="knsbl" value="<bean:write name="rs" property="knsbl"/>" />
				<input type="hidden" id="xueqi" name="xueqi" value="<bean:write name="rs" property="xueqi"/>" />
				<input type="hidden" id="url" name="url" value="/sjcz/gwxxb.jsp" />
				<logic:present name="rs" property="shjg">
				<input type="hidden" id="shjg" name="shjg" value="<bean:write name="rs" property="shjg"/>" />
				</logic:present>
				<html:hidden property="xueqi" name="rs"/>
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr align="center">
							<td height="22" colspan="4">&nbsp;
								��λ��Ϣ
							</td>
						</tr>
					</thead>
					<tr>
						<td width="26%" height="22" align="right">
							У����
						</td>
						<td width="40%" height="22" align="left">
							<html:select name="rs" property="xqdm" style="width:120px"
								styleId="xqdm">
								<html:option value=""></html:option>
								<html:options collection="xqList1" property="dm"
									labelProperty="xqmc" />
							</html:select>
						</td>
						<td width="31%" height="22" align="right">
							<font color="red">*</font>��λ���ƣ�
						</td>
						<td width="1%" height="22" align="left">
						<logic:equal value="add" name="doType">
							<html:text name="rs" property="gwdm" styleId="gwdm" onkeyup="value=value.replace('-','') "/>
						</logic:equal>
						<logic:equal value="modi" name="doType">
							<html:text name="rs" property="gwdm" styleId="gwdm" readonly="true"/>
						</logic:equal>							
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							��λ���ʣ�
						</td>
						<td height="22" align="left">
							<html:select name="rs" property="gwxz" style="width:120px"
								styleId="gwxz">
								<html:option value=""></html:option>
								<html:options collection="gwxzList" property="gwxzdm"
									labelProperty="gwxzmc" />
							</html:select>
						</td>
						<td height="22" align="right">
							<font color="red">*</font>���뵥λ��
						</td>
						<td height="22" align="left">
							<html:select name="rs" property="sqdw" styleId="sqdw"
								style="width:120px" onchange="getYrdwInfo()">
								<html:option value=""></html:option>
								<html:options collection="sqdwList" property="yrdwdm"
									labelProperty="yrdwmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							ѧ�꣺
						</td>
						<td height="22" align="left">
							<html:text name="rs" property="xn" style="width: 90px"
								readonly="true" />
						</td>
						<td height="22" align="right">
							��ȣ�
						</td>
						<td height="22" align="left">
							<html:text name="rs" property="nd" style="width: 90px"
								readonly="true" />							
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							������ʼ���ڣ�
						</td>
						<td height="22" align="left">
							<html:text name='rs' property="gzkssj" styleId="gzkssj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('gzkssj','y-mm-dd');" />
						</td>
						<td height="22" align="right">
							<font color="red">*</font>�����������ڣ�
						</td>
						<td height="22" align="left">
							<html:text name='rs' property="gzjssj" styleId="gzjssj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('gzjssj','y-mm-dd');" />
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							<font color="red">*</font>����������
						</td>
						<td height="22" align="left">
							<html:text name="rs" property="xyrs" styleId="xyrs" />
						</td>
						<td height="22" align="right">
							<font color="red">*</font>ʹ������������
						</td>
						<td height="22" align="left">
							<html:text name="rs" property="syknss" styleId="xyknsrs" />
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							<font color="red">*</font>�Ƴ귽ʽ��
						</td>
						<td height="22" align="left">
							<html:select name="rs" property="jcfs" onchange="subloadPost();loadJcbz(this.value);">
									<html:options collection="jcfsList" property="dm" labelProperty="mc"/>

							</html:select>
						</td>
						<td height="22" align="right">
							<font color="red">*</font>���鱨���׼��
						</td>
						<td height="22" align="left">
							<html:text name="rs" property="jybcbz" styleId="jybcbz" />
							<span id="jybcbzDw"></span>
						</td>
					</tr>
					<tr>
					  <td height="22" align="right">����ʱ��Σ�</td>
					  <td height="22" align="left">
					 	 <html:text name="rs" property="mssjd" styleId="mssjd"/>
					  </td>
					  <td height="22" align="right">������ʦ��</td>
					  <td height="22" align="left">
					  	<html:text name="rs" property="fzr" styleId="lxr" />
					  </td>
				  </tr>
					<tr>
					  <td height="22" align="right">���Եص㣺</td>
					  <td height="22" align="left">
					  	<html:text name="rs" property="msdd" styleId="msdd"/>
					  </td>
					  <td height="22" align="right">��ϵ�绰��</td>
					  <td height="22" align="left">
					  	<html:text name="rs" property="lxdh" styleId="lxdh" />
					  </td>
				  </tr>
					<tr>
					  <td height="22" align="right">��λ�����ص㣺</td>
					  <td height="22" colspan="3" align="left">
					  <html:textarea name="rs" property="gzdd" style="width:100%" styleId="gzdd"/>
					  </td>
				  </tr>
					<tr>
					  <td height="22" align="right"><font color="red">*</font>����ʱ�䣺</td>
					  <td height="22" colspan="3" align="left">
					  	<html:textarea name="rs" property="gzsj" style="width:100%" styleId="gzsj"/>
					  </td>
				  </tr>
					<tr>
					  <td height="22" align="right">�������ݣ�</td>
					  <td height="22" colspan="3" align="left">
					  	<html:textarea name="rs" property="gznr" style="width:100%" styleId="gznr"/>
					  </td>
				  </tr>
					<tr>
						<td height="22" align="right">���öȣ�
						</td>
						<td height="22" colspan="3" align="left">
						<html:select name="rs" property="xyddm" styleId="xyddm">
							<html:option value=""></html:option>
							<html:options collection="xydList" property="xyddm" labelProperty="xydmc"/>
						</html:select>
						</td>
					</tr>
					<tr>
					  <td height="22" colspan="4" align="right" bgcolor="#D0E0EE"><div align="center">��ְҪ��</div></td>
				  </tr>
					<tr>
                      <td height="22" align="right">�꼶�� </td>
                      <td height="22" align="left"> 
                      <html:text name="rs" property="rzyq_nj" styleId="rzyq_nj"/> 
                      <span id="gzsjDw"></span> 
                      </td>
                      <td height="22" align="right">�Ա�</td>
                      <td height="22" align="left">
                      <html:select name="rs" property="rzyq_xb" styleId="rzyq_xb">
                      <html:option value=""></html:option>
					  <html:option value="��">��</html:option>			
					  <html:option value="Ů">Ů</html:option>
                      </html:select>
                      </td>
				  </tr>
					<tr>
					  <td height="22" align="right">רҵҪ��</td>
					  <td height="22" align="left">
					  	<html:select name="rs" property="rzyq_zy" styleId="rzyq_zy">
					  	<html:option value=""></html:option>
					  	<html:options collection="zydmList" property="zydm" labelProperty="zymc"/>
					  	</html:select>
					  </td>
					  <td height="22" align="right">����Ҫ��</td>
					  <td height="22" align="left">
					  	<html:text name="rs" property="rzyq_wyyq" styleId="rzyq_wyyq"/>
					  </td>
				  </tr>
					<tr>
					  <td height="22" align="right"> ��������Ҫ�� </td>
					  <td height="22" colspan="3" align="left">
					  	<html:textarea name="rs" property="rzyq_gzjn" style="width:100%" styleId="rzyq_gzjn"/>
					  </td>
				  </tr>
					<tr>
					  <td height="22" align="right"> ����Ҫ�� </td>
					  <td height="22" colspan="3" align="left">
					  	<html:textarea name="rs" property="rzyq_qt" style="width:100%" styleId="rzyq_qt"/>
					  </td>
				  </tr>
					<tr>
					  <td height="22" colspan="4" align="right" bgcolor="#D0E0EE"><div align="center">����ְ��</div></td>
				  </tr>
					<tr>
					  <td height="22" align="right"> ����Ŀ�ģ� </td>
					  <td height="22" colspan="3" align="left">
					  	<html:textarea name="rs" property="gzmd" style="width:100%" styleId="gzmd"/>
					  </td>
				  </tr>
					<tr>
					  <td height="22" align="right"> ����Ҫ�㣺 </td>
					  <td height="22" colspan="3" align="left">
					  	<html:textarea name="rs" property="gzyd" style="width:100%" styleId="gzyd"/>
					  </td>
				  </tr>
					<tr>
					  <td height="22" align="right">�����ѵ㣺</td>
					  <td height="22" colspan="3" align="left">
					  	<html:textarea name="rs" property="gznd" style="width:100%" styleId="gznd"/>
					  </td>
				  </tr>
					<tr>
					  <td height="22" align="right"> �������ɣ� </td>
					  <td height="22" colspan="3" align="left">
					  	<html:textarea name="rs" property="gzjj" style="width:100%" styleId="gzjj"/>
					  </td>
				  </tr>
					<tr>
					  <td height="22" align="right">ÿ�����������</td>
					  <td height="22" colspan="3" align="left">
					  	<html:textarea name="rs" property="mtbzgz" style="width:100%" styleId="mtbzgz"/>
					  </td>
				  </tr>
					<tr>
					  <td height="22" align="right"> ���ڱ���������  </td>
					  <td height="22" colspan="3" align="left">
					  	<html:textarea name="rs" property="dqbzgz" style="width:100%" styleId="dqbzgz"/>
					  </td>
				  </tr>
					<tr>
						<td height="22" align="right">
							��ע��
						</td>
						<td height="22" colspan="3" align="left">
							<html:textarea name="rs" property="bz" style="width:100%"
								rows="5"></html:textarea>
						</td>
					</tr>
				</table>
				<font color="red">��ʾ:У�ڹ̶���λʹ���������������õ���<bean:write name="rs" property="knsbl" />%</font>
				<br />
				<logic:present name="writeAble">
					<logic:match value="yes" name="writeAble">
						<div id="button" align="center" class="buttontool">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
								onclick="if(dataDoSavePubGw('/xgxt/whlggwgl.do?method=addGwxx&doType='+document.getElementById('doType').value+'&pkValue='+document.getElementById('pkValue').value+'&value=','gwdm-sqdw-xyrs-xyknsrs-jcfs-jybcbz-gzjssj-gzsj')) BatAlert.showTips('���ڲ����У����Ե�...'); "
								style="width:80px" id="buttonSave">
								�� ��
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="expAppTab('rsT','�ڹ���ѧ��λ������Ϣ','')">
								��ӡ����
							</button>
						</div>
					</logic:match>
				</logic:present>
			</logic:notEmpty>
			<logic:present name="result">
			<logic:equal value="true" name="result">
			<script>
				alert("�����ɹ���");			
			</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				alert("����ʧ�ܣ�");		
			</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
