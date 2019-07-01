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
	<base target="_self" />
	<script type="text/javascript" src="js/BatAlert.js"></script>
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
	
	function print1(){
		var xxdm = document.getElementById('xxdm').value;
		if(xxdm == "13022"){//������
		
			var sqdw   = document.getElementById('sqdw').value;			
			var lxdh = document.getElementById('lxdh').value;
			var gznr = document.getElementById('gznr').value;
			var xyrs = document.getElementById('xyrs').value;
			var gzsj = document.getElementById('gzsj').value;
			var bz = document.getElementById('bz').value;
			var gwdm = document.getElementById('gwdm').value;
			var myqgzxsj = document.getElementById('myqgzxsj').value;
			var mssj = document.getElementById('mssj').value;
			var tsyq = document.getElementById('tsyq').value;
			var dwzp = document.getElementById('dwzp').value;
			var rylsqk = document.getElementById('rylsqk').value;
			var gwxz = document.getElementById('gwxz').options[document.getElementById('gwxz').selectedIndex].text;
			var fzr = document.getElementById('lxr').value;
		
			var url = "/xgxt/qgzxNblg.do?method=printGwsqb";
			if(gwxz !=null && gwxz.indexOf('�̶�')>-1){
				gwxz = 'gdgw';
			}else if (gwxz !=null && gwxz.indexOf('��ʱ')>-1){
				gwxz = 'lsgw';
			}else {
				alert('��λ����Ϊ��ѡ��');
				return false;
			}
			
			url += "&sqdw=";
			url += sqdw;
			url += "&lxdh=";
			url += lxdh;
			url += "&gznr=";
			url += gznr;
			url += "&xyrs=";
			url += xyrs;
			url += "&gzsj=";
			url += gzsj;
			url += "&bz=";
			url += bz;
			url += "&gwdm=";
			url += gwdm;
			url += "&myqgzxsj=";
			url += myqgzxsj;
			url += "&mssj=";
			url += mssj;
			url += "&tsyq=";
			url += tsyq;
			url += "&dwzp=";
			url += dwzp;
			url += "&rylsqk=";
			url += rylsqk;
			url += "&gwxz=";
			url += gwxz;
			url += "&lxr=";
			url+= fzr;			
			window.open(url);
		}else{
			expAppTab('rsT','�ڹ���ѧ��λ�걨��','');
		}
	}
	
</script>
	<body>

		<html:form action="/comm_pub" method="post">
			<input type="hidden" id="xxdm" name="xxdm"
				value="<bean:write name="xxdm"/>" />
			<logic:present name="gwsbsj">
				<input type="hidden" id="gwsbsj" name="gwsbsj"
					value="<bean:write name="gwsbsj"/>" />
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
			<logic:notEqual value="0" name="timeFlag">
				<br />
				<br />
				<p align="center">
					���ڲ��ڷ�����λ��ʱ�䷶Χ�ڣ���ʱ�����ܷ�����λ��
				</p>
			</logic:notEqual>
			<logic:equal value="0" name="timeFlag">
			<logic:notEmpty name="rs">
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="knsbl" name="knsbl"
					value="<bean:write name="rs" property="knsbl"/>" />
				<input type="hidden" id="xueqi" name="xueqi"
					value="<bean:write name="rs" property="xueqi"/>" />
				<input type="hidden" id="url" name="url" value="/sjcz/gwxxb.jsp" />
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr align="center">
							<td height="22" colspan="4">
								&nbsp;
							</td>
						</tr>
					</thead>
					<tr>
						<td height="22" align="right">
							У����
						</td>
						<td height="22" align="left">
							<html:select name="rs" property="xqdm" style="width:120px"
								styleId="xqdm">
								<html:option value=""></html:option>
								<html:options collection="xqList1" property="dm"
									labelProperty="xqmc" />
							</html:select>
						</td>
						<td height="22" align="right">
							<font color="red">*</font>��λ���ƣ�
						</td>
						<td height="22" align="left">
							<html:text name="rs" property="gwdm" styleId="gwdm"
								onkeyup="value=value.replace('-','')" />
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
							<html:text name="rs" property="xyknsrs" styleId="xyknsrs" />
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							<font color="red">*</font>�Ƴ귽ʽ��
						</td>
						<td height="22" align="left">
							<html:select name="rs" property="jcfs" onchange="subloadPost();loadJcbz(this.value);">
									<html:options collection="jcfsList" property="dm" labelProperty="mc"/>
<!--								<html:option value="">------��ѡ��------</html:option>-->
<!--								<html:option value="h">��Сʱ</html:option>-->
<!--								<html:option value="d">����</html:option>-->
<!--								<html:option value="w">����</html:option>-->
<!--								<html:option value="m">����</html:option>									-->
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
							<td height="22" align="right">
								<font color="red">*</font>����ʱ�䣺
							</td>
								<td height="22" align="left">
									<html:text name="rs" property="gzsj" styleId="gzsj" />
									(������һ�ϣ��ܶ���...)
									<span id="gzsjDw"></span>
								</td>
							
							
								<td height="22" align="right">
									��ϵ�绰��
								</td>
								<td height="22" align="left">
									<html:text name="rs" property="lxdh" styleId="lxdh"
										maxlength="15" />
								</td>
						</tr>
						<tr>
							<td height="22" align="right">
								<font color="red">*</font>�������ݣ�
							</td>
							<td height="22" colspan="3" align="left">
								<html:textarea name="rs" property="gznr" styleId="gznr"
									style="width:100%" rows="5"></html:textarea>
							</td>
						</tr>					
					<tr>
						<td height="22" align="right">
							��ԱҪ��
						</td>
						<td height="22" colspan="3" align="left">
							<html:textarea name="rs" property="ryyq" style="width:100%"
								rows="5"></html:textarea>
						</td>
					</tr>
						<tr>
							<td height="22" align="right">
								���뵥λ�����
							</td>
							<td height="22" colspan="3" align="left">
								<html:textarea name="rs" property="sqdwyj" style="width:100%"
									rows="5"></html:textarea>
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
					<font color="red">��ʾ:У�ڹ̶���λʹ���������������õ���<bean:write name="rs"
							property="knsbl" />%</font>

				<br />
				<logic:present name="writeAble">
					<logic:match value="yes" name="writeAble">
						<div id="button" align="center" class="buttontool">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						    <button type="button" class="button2"
								onclick="if(dataDoSavePubGw('/xgxt/comm_pub.do?doType=save&tableName=view_gwxx&Value=','gwdm-sqdw-xyrs-xyknsrs-jcfs-jybcbz-gznr-gzjssj-gzsj')) BatAlert.showTips('���ڲ����У����Ե�...'); "
								style="width:80px" id="buttonSave">
								�� ��
							</button>
							<logic:notPresent name="zdy">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="print1();">
									�� ӡ Ԥ ��
								</button>
							</logic:notPresent>
							<logic:present name="zdy">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2"
									onclick="printReport('qgzx_bb_gwsbb.do?gwdm=')">
									�� ӡ Ԥ ��
								</button>
							</logic:present>							
						</div>
					</logic:match>
				</logic:present>
			</logic:notEmpty>
			</logic:equal>
		</html:form>
	</body>
</html>
