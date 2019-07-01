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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet" 
	    href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript"></script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="checkWinType();changezje()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript">
			function batchWorkPay(url){			
			var pk = document.forms[0].pk.value;
			var pkValue = document.forms[0].pkValue.value;
			var gwxz = document.forms[0].gwxz.value;
			var yrdw = document.forms[0].yrdw.value;
			var yf = document.getElementById("dqyf").value;
			url += "&pk=";
			url += pk;
			url += "&pkValue=";
			url += pkValue;
			url += "&gwxz=";
			url += gwxz;
			url += "&yrdw=";
			url += yrdw;
			url += "&yf=";
			url +=yf;
			
			refreshForm(url,800,600);
			
		}
		function commit(mustFill){
			var ele = mustFill.split('-');
			for(var i=0; i<ele.length; i++){
				if(document.getElementById(ele[i]).value==''){
					alert('�뽫��*�ŵ���Ŀ��д������');
					return false;
				}
			}
			if(parseFloat(document.forms[0].syjf.value)-parseFloat(document.getElementById('zje').innerText)<0){
				alert('���Ѳ��㣬����ʧ�ܣ�');
				Close();
			} else {
				refreshForm('qgzxLogic.do?method=saveSingleWorkPay');			
			}
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"> �ڹ���ѧ - ��𷢷� - ��𷢷� - ��𷢷��굥</span>
				</div>
			</div>
			<input type="hidden" id="yrdw" name="yrdw" value="<bean:write name="workInfo" property="yrdwmc" />" />
			<input type="hidden" id="doType" name="doType" value="" />
			<input type="hidden" id="pkValue" name="pkValue" value="<bean:write name="pkValue"/>" />
			<input type="hidden" id="pk" name="pk" value="<bean:write name="pk"/>" />
			<input type="hidden" id="disableEle" name="disableEle" value="" />
			<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="" />
			<input type="hidden" id="url" name="url" value="/sjcz/xspxxxb.jsp" />
			<input type="hidden" id="gwdm" name="gwdm" value="<bean:write name="workInfo" property="gwdm"/>" />
			<input type="hidden" id="gwsbsj" name="gwsbsj" value="<bean:write name="workInfo" property="gwsbsj"/>" />
			<input type="hidden" id="jybcbz" name="jybcbz" value="<bean:write name="workInfo" property="jybcbz"/>" />
			<input type="hidden" id="count" name="count" value="<bean:write name="count"/>" />
			<input type="hidden" id="xn" name="xn" value="<bean:write name="workInfo" property="xn"/>" />
		    <input type="hidden" id="nd" name="nd" value="<bean:write name="workInfo" property="nd"/>" />
			<input type="hidden" id="xq" name="xq"  value="<bean:write name="workInfo" property="xq"/>" />
			<input type="hidden" id="yf" name="yf" value="<bean:write name="workInfo" property="yf"/>" />
			<input type="hidden" id="gwxz" name="gwxz" value="<bean:write name="workInfo" property="gwxz"/>" />
			<input type="hidden" id="syjf" name="syjf" value="<bean:write name="workInfo" property="syjf"/>" />
			<input type="hidden" id="xxdm" name="xxdm" value="<bean:write name="xxdm"/>" />
			<fieldset>
				<legend>
					<logic:notPresent name="showbjlh">
						ѧ��:<bean:write name="workInfo" property="xn"/>&nbsp;&nbsp;&nbsp;���:<bean:write name="workInfo" property="nd"/>&nbsp;&nbsp;&nbsp;ѧ��:<bean:write name="workInfo" property="xq"/>&nbsp;&nbsp;&nbsp;�·�:<bean:write name="workInfo" property="yf"/>
					</logic:notPresent>
					<logic:present name="showbjlh">
						���:<bean:write name="workInfo" property="xn"/>&nbsp;&nbsp;&nbsp;ѧ��:<bean:write name="workInfo" property="xq"/>&nbsp;&nbsp;&nbsp;�·�:<bean:write name="workInfo" property="yf"/>
					</logic:present>
				</legend>
				<div></div>
				<table width="100%" class="tbstyle" id="rsT">
					<tr>
						<td height="27" align="center">
							���˵�λ
						</td>
						<td align="center">
							<bean:write name="workInfo" property="yrdwmc" />
						</td>
						<td height="27" align="center">
							��������
						</td>
						<td align="center">
							<bean:write name="workInfo" property="xymc" />
						</td>
					</tr>					
					<tr>
						<td height="27" align="center">
							��������
						</td>
						<td align="center">
							<bean:write name="workInfo" property="gznr" />
						</td>
						<td height="27" align="center">
							��λ����
						</td>
						<td align="center">
							<bean:write name="workInfo" property="gwxzmc" />
						</td>
					</tr>
					<tr>
						<td height="27" align="center">
							�Ƴ��׼
						</td>
						<td align="center">
							<bean:write name="workInfo" property="jybcbz" />
							<bean:write name="workInfo" property="jcfs" />
						</td>
						<td height="27" align="center">
							ʣ�ྭ��
						</td>
						<td align="center">
							<bean:write name="workInfo" property="syjf"/>����λ:Ԫ��
						</td>
					</tr>
					<tr>
						<td height="27" align="center">
							<font color="red">*</font>�����·�
						</td>
						<td align="center">
							<html:select property="yf" name="workInfo" styleId="dqyf"  onchange="batchWorkPay('qgzxLogic.do?method=showSinglePage',this)">
								<html:option value=""></html:option>
								<html:option value="01">01</html:option>
								<html:option value="02">02</html:option>
								<html:option value="03">03</html:option>
								<html:option value="04">04</html:option>
								<html:option value="05">05</html:option>
								<html:option value="06">06</html:option>
								<html:option value="07">07</html:option>
								<html:option value="08">08</html:option>
								<html:option value="09">09</html:option>
								<html:option value="10">10</html:option>
								<html:option value="11">11</html:option>
								<html:option value="12">12</html:option>
							</html:select>
						</td>
						<td height="27" align="center">
							<font color="red">*</font>����ʱ��
						</td>
						<td align="center">
							<html:text name="workInfo" property="ffsj" onclick="return showCalendar('ffsj','y-mm-dd');" styleId="ffsj"/>
						</td>
					</tr>
					<tr>
						<td align="center">
							��
							<br />
							��
							<br />
							��
							<br />
							Ա
							<br />
							��
							<br />
							��
						</td>
						<td valign="top" align="center" colspan="3">
							<table width="100%" class="tbstyle">
								<thead>
									<tr>
										<td width="14%">
											<div align="center">
												ѧ��
											</div>
										</td>
										<td width="14%">
											<div align="center">
												����
											</div>
										</td>
										<td width="16%">
											<div align="center">
												<bean:message key="lable.xsgzyxpzxy" />
											</div>
										</td>										
										<td width="10%">
											<div align="center">
												����
											</div>
										</td>
										<td width="15%">
											<div align="center">
												����ʱ��(��λ:
												<bean:write name="workInfo" property="gzsjdw" />
												)
											</div>
										</td>
										<td width="8%">
											<div align="center">
												���
											</div>
										</td>
										<logic:present name="showjsxx">
										<td width="10%">
											<div align="center">
												ǩ��
											</div>
										</td>
										</logic:present>
										<logic:notEqual value="10856" name="xxdm">
											<td width="16%">
											<div align="center">
												��ע
											</div>
										</td>
										</logic:notEqual>
										
										
									</tr>
								</thead>
								<logic:empty name="ffList">
									<tr>
									<logic:equal value="10865" name="xxdm">
									<td colspan="8">
											�޲μӸø�λ��ѧ����¼!
									</td>
									</logic:equal>
									<logic:notEqual value="10865" name="xxdm">
									<td colspan="6">
											�޲μӸø�λ��ѧ����¼!
									</td>									
									</logic:notEqual>
									</tr>
								</logic:empty>
								<logic:notEmpty name="ffList">
									<logic:iterate name="ffList" id="ffList" indexId="index">
										<tr>
											<td>
												<div align="center">
													<input type="text"
														name="xh${index}"
														value="<bean:write name="ffList" property="xh"/>"
														style="width:95%" readonly />
												</div>
											</td>
											<td>
												<div align="center">
													<bean:write name="ffList" property="xm" />
												</div>
											</td>
											<td>
												<div align="center">
													<bean:write name="ffList" property="bjmc" />
												</div>
											</td>											
											<td>
												<div align="center">
													<bean:write name="ffList" property="kh" />
												</div>
											</td>
																						
											<logic:notPresent name="bjlh">
											<td>
												<div align="center">
													<input type="text"
														id="gzsj${index}"
														name="gzsj${index}"
														value="<bean:write name="ffList" property="gzsj"/>"
														style="width:95%" onblur="changecjje(this);document.all(this.id.replace('gzsj','cjje')).focus();" />
												</div>
											</td>
											</logic:notPresent>											
											<logic:notPresent name="bjlh">
											<logic:equal value="10856" name="xxdm">
											<td>
												<div align="center">
													<input type="text"
														id="cjje${index}"
														name="cjje${index}"
														value="<bean:write name="ffList" property="cjje"/>"
														style="width:95%" onblur="changezje();"/>
												</div>
											</td>											
											</logic:equal>
											<logic:notEqual value="10856" name="xxdm">
											<td>
												<div align="center">
													<input type="text"
														id="cjje${index}"
														name="cjje${index}"
														value="<bean:write name="ffList" property="cjje"/>"
														style="width:95%" onblur="changezje();if(this.value>300&&document.forms[0].gwxz.value == '001') {alert('����,����д��Ӧ��ע!');document.all(this.id.replace('cjje','bz')).focus();}"/>
												</div>
											</td>
											</logic:notEqual>
											
											</logic:notPresent>
											<logic:present name="showjsxx">
											<td>
												<div align="center">
													&nbsp;
												</div>
											</td>
											</logic:present>
										</tr>
									</logic:iterate>
								</logic:notEmpty>
							</table>
						</td>
					</tr>
					<tr>
						<td height="27">
							<div align="center">
								����ܼƣ���λ:Ԫ��
							</div>
						</td>
						<td colspan="3">
							<div align="center" id="zje"></div>
						</td>
					</tr>
				</table>
			</fieldset>
			<div class="buttontool">				
				<button type="button" class="button2" onclick="commit('dqyf-ffsj');"
					style="width:80px" id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;				
				<button type="button" class="button2" style="width:80px" onclick="expAppTab('rsT','��𷢷ŵ�','')">
						�� ӡ �� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();return false;"
					style="width:80px" id="buttonSave">
					�� ��
				</button>
			</div>
			<logic:present name="result">
			<logic:equal value="true" name="result">
			<script>
				alert("�����ɹ�!");
				Close();
			</script>
			</logic:equal>
			<logic:equal value="false" name="false">
			<script>
				alert("����ʧ��");
			</script>
			</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>

