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
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/xsgyglFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<html:form action="/nblgxy_gygl" method="post">
			<input type="hidden" name="pkValue" id="pkValue"
				value="<bean:write name="pkValue" scope="request"/>">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã� ��Ԣ���� - ����Ա�±� - �±���д
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
			
			<table width="100%" class="tbstyle" id="rsT">
				<thead>
					<tr>
						<td colspan="4" align="center">
							����Ա�±���Ϣ
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right" width="15%">
						��ݣ�
					</td>
					<td>
						<html:select name="rs" property="nf" disabled="true">
							<html:options collection="nfList" property="nd"
								labelProperty="nd"  />
						</html:select>
					</td>
					<td align="right" width="15%">
						�·ݣ�
					</td>
					<td align="left">
						<html:select name="rs"  property="yf" disabled="true">
							<html:option value="1">1</html:option>
							<html:option value="2">2</html:option>
							<html:option value="3">3</html:option>
							<html:option value="4">4</html:option>
							<html:option value="5">5</html:option>
							<html:option value="6">6</html:option>
							<html:option value="7">7</html:option>
							<html:option value="8">8</html:option>
							<html:option value="9">9</html:option>
							<html:option value="10">10</html:option>
							<html:option value="11">11</html:option>
							<html:option value="12">12</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
						������
					</td>
					<td align="left">
						<html:text name="rs"  property="xm" readonly="true" styleId="xm" disabled="true"></html:text>
					</td>

					<td align="right">
						����ʱ��Σ�
					</td>
					<td>
						<html:text name="rs"  property="gzksrq" styleId="gzksrq"
							onblur="dateFormatChg(this)" style="cursor:hand;width:75px"
							onclick="return showCalendar('gzksrq','y-mm-dd');"
							readonly="true" />
						--
						<html:text name="rs" property="gzjsrq" styleId="gzjsrq"
							onblur="dateFormatChg(this)" style="cursor:hand;width:75px"
							onclick="return showCalendar('gzjsrq','y-mm-dd');"
							readonly="true" />
					</td>
				</tr>
				<tr>
					<td align="right">
						����¥����
					</td>
					<td align="left">
						<html:text name="rs"  property="fzld"></html:text>
					</td>

					<td align="right">
						��ס¥�����ң�
					</td>
					<td>
						<html:text name="rs"  property="rzqsh"></html:text>
					</td>
				</tr>
			</table>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td width="5%" align="center">
							��Ҫ��������
						</td>
					</tr>
				</thead>
				<tr>
					<td>
						<div onclick="showHide('jyfk')" style="cursor: hand"
							title="����������ʾ�������ı������">
							ÿ��һ�������ղ�ʱ�䡢���ݡ������Ա�������������Ҫ������
						</div>
					</td>
				</tr>
				<tr id="jyfk" style="display: none">
					<td>
						<html:textarea name="rs"  property='gznr_jyfk' style="width:99%;;"
							rows='8' />
					</td>
				</tr>
				<tr>
					<td>
						<div onclick="showHide('jyjl')" style="cursor: hand"
							title="����������ʾ�������ı������">
							ÿ��ֵ��ʱ���ڽӴ�ѧ����̸�ġ��߷����ҵȵļ�Ҫ��¼��
						</div>
					</td>
				</tr>

				<tr id="jyjl" style="display: none">
					<td>
						<html:textarea name="rs"  property='jgznr_jyjl' title=""
							style="width:99%;;" rows='8' />
					</td>
				</tr>
				<tr>
					<td>
						<div onclick="showHide('jhynr')" style="cursor: hand"
							title="����������ʾ�������ı������">
							�ٿ�¥�㳤��ѧ���Ǹɻ����ʱ�䡢�ص����Ҫ�������ݣ�
						</div>
					</td>
				</tr>

				<tr id="jhynr" style="display: none">
					<td>
						<html:textarea name="rs"  property='gznr_jhynr' title=""
							style="width:99%;;" rows='8' />
					</td>
				</tr>
				<tr>
					<td>
						<div onclick="showHide('jxsdt')" style="cursor: hand"
							title="����������ʾ�������ı������">
							һ��������Ҫ������¥��ѧ����̬��
						</div>
					</td>
				</tr>

				<tr id="jxsdt" style="display: none">
					<td>
						<html:textarea name="rs"  property='gznr_jxsdt' title=""
							style="width:99%;;" rows='8' />
					</td>
				</tr>
				<tr>
					<td>
						<div onclick="showHide('jqtnr')" style="cursor: hand"
							title="����������ʾ�������ı������">
							�����������ݣ�
						</div>
					</td>
				</tr>

				<tr id="jqtnr" style="display: none">
					<td>
						<html:textarea name="rs"  property='gznr_jqtnr'
							style="width:99%;;" rows='8' />
					</td>
				</tr>
			</table>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="center">
							��Ҫ����������͹�������
						</td>
					</tr>
				</thead>
				<tr>
					<td>
						<div onclick="showHide('xgbfk')" style="cursor: hand"
							title="����������ʾ�������ı������">
							������ѧ�����������ڵ����ѡ��������顢�ص㹤�������������Ҫ������ͻ���¼�����ȣ�
						</div>
					</td>
				</tr>

				<tr id="xgbfk" style="display: none">
					<td>
						<html:textarea name="rs"  property='fkyj_xgbfk' title=""
							style="width:99%;;" rows='8' />
					</td>
				</tr>
				<tr>
					<td>
						<div onclick="showHide('xyfk')" style="cursor: hand"
							title="����������ʾ�������ı������">
							��������Ժ��������ѧ����̬�����ȶ���ͷ�����עѧ���ȣ�ע����Ժ��
						</div>
					</td>
				</tr>

				<tr id="xyfk" style="display: none">
					<td>
						<html:textarea name="rs"  property='fkyj_xyfk' title=""
							style="width:99%;;" rows='10' />
					</td>
				</tr>
				<tr>
					<td>
						<div onclick="showHide('fwzxfk')" style="cursor: hand"
							title="����������ʾ�������ı������">
							����������԰������������ģ����漰�ճ�����������ҪЭͬ�Ĺ�����
						</div>
					</td>
				</tr>

				<tr id="fwzxfk" style="display: none">
					<td>
						<html:textarea name="rs"  property='fkyj_fwzxfk' title=""
							style="width:99%;;" rows='8' />
					</td>
				</tr>
				<tr>
					<td>
						��ע��
						<html:textarea name="rs"  property='bz' style="width:99%;;"
							rows='5' />
					</td>
				</tr>

			</table>
			<div class="buttontool" align="center">
				<button class="button2" onclick="ybAdd()" style="width:80px"
					id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" id="buttonReset"
					style="width:80px">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" style="width:80px" onclick="showValues()">
					����Ԥ��
				</button>						
			</div>
			<logic:equal value="false" name="done">
			<script type="text/javascript">
			     alert('�޸�ʧ�ܣ�');
			</script>
			</logic:equal>
						<logic:equal value="true" name="done">
			<script type="text/javascript">
			    alert('�޸ĳɹ���');
				Close();
				dialogArgumentsQueryChick();			     
			</script>
			</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
	<script type="text/javascript">
    function ybAdd(){
               if($("nf").value==""){
                  alert('��ѡ����ݣ�');
                  return false;
               }
               if($("yf").value==""){
                  alert('��ѡ���·ݣ�');
                  return false;
               }
               var nf = "";
               var yf = "";
               var dlm ="";
               if($("nf"))nf=$("nf").value;
               if($("yf"))yf=$("yf").value;
               if($("dlm"))dlm=$("dlm").value;
              var pkValue=nf+yf+dlm;
               if(confirm("�ύǰ��Ԥ����������±���\n\n���\"ȷ��\"���������ݣ����\"ȡ��\"�����������棻\n\n"+"ȷ��Ҫ����"+$('nf').value+"��"+$('yf').value+"�¹��������޸ģ�")){
                    refreshForm('/xgxt/nblgxy_gygl.do?method=ybModi&doType=save');
               }else{
                   return false;
               }
               
    }
    function showHide(obj){		
      if(document.getElementById(obj).style.display==''){
		  document.getElementById(obj).style.display='none';		 
      }else{
		  document.getElementById(obj).style.display='';		 
	  }		
    }
   </script>
</html>
