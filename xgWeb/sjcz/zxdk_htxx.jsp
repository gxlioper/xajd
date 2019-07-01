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
	<script language="javascript">
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript">
		function dataDoSave(mustFill,dkje) {
			var eles = mustFill.split("-");
			var sfzh = 	document.getElementById('sfzh').value;
			for (i = 0; i < eles.length; i++) {
				if (document.getElementById(eles[i]).value == "") {
					alert("�뽫��\"*\"�ŵ���Ŀ����������");
					return false;
				}
			}
			if(!isNumber(dkje)){
				alert("��ͬ�ܽ�����Ϊ������!");
				return false;
			}
			if(!checkSfzh(sfzh)){
				return false;
			}
			var url = "/xgxt/modiData.do?realTable=";
			url += window.dialogArguments.document.forms[0].realTable.value;
			url += "&doType=save";
			url += "&tableName=";
			url += window.dialogArguments.document.forms[0].tableName.value;
			url += "&pk=";
			url += window.dialogArguments.document.forms[0].pk.value;
			url += "&pkValue=";
			url += document.forms[0].pkValue.value;
			url += "&from=";
			url += window.dialogArguments.document.forms[0].act.value;
			document.forms[0].action = url;
			document.forms[0].submit();
		}
		
		function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		}
		
		function checkSfzh(sfzh) {
   			sfzh=sfzh.toLowerCase()
			var OldID;
			if(sfzh.length == 15){
				OldID = sfzh;
				return true;
			}else if(sfzh.length == 18){
				OldID = sfzh.substring(0, 6) + sfzh.substring(8,sfzh.length-1);
			}else{
				alert("��������ȷ�����֤���룡","",function(){
					obj.select();
					obj.focus();
				});
				return false;
			}
			var W = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
			var A = new Array("1", "0", "x", "9", "8", "7", "6", "5", "4", "3", "2");
			var i, j, S;
			var NewID, ID, strNF;
			NewID = OldID.substring(0, 6) + "19" + OldID.substring(6,OldID.length);
			S = 0;
			for( i = 0; i <= 16; i++){
				j = NewID.substring(i, i+1) * W[i];
				S = S + j;
			}
			S = S % 11;
			if(sfzh != NewID + A[S]){
				alert("��������ȷ�����֤���룡","",function(){
					obj.select();
					obj.focus();
				});
				return false;
			}
			return true;
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>
		<logic:notEmpty name="isNULL">
		<logic:equal name="isNULL" value="is">
		<script language="javascript">
	         	alert("��ѧ����Ϣ���л�û�и�ѧ������Ϣ�����ȵ���ѧ������˴���ѧ����Ϣ��ѯ������ѧ����Ϣ!");
	         	</script>
		</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="isFULL">
		<logic:equal name="isFULL" value="is">
		<script language="javascript">
	         	alert("��ѧ���ĺ�ͬ��������");
	         	</script>
		</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="ok">
				<logic:equal value="ok" name="ok">
					<script language="javascript">
	         	alert("����ɹ���");
	         	</script>
				</logic:equal>
				<logic:equal value="no" name="ok">
					<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	</script>
				</logic:equal>
			</logic:notEmpty>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    alert("�������ѧ����Ч!");
    </script>
				</logic:equal>
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-sfzh" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-sfzh" />
				<input type="hidden" id="url" name="url" value="/sjcz/zxdk_htxx.jsp" />
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td colspan="4" align="center">
								��ͬ��Ϣ
							</td>
						</tr>
					</thead>
					<tr>
						<td align="right">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td align="left">
							<html:text name='rs' property="xh" readonly="readonly" 
								styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
						</td>
						<td align="right">
							������
						</td>
						<td align="left">
							<html:text name='rs' property="xm" styleId="xm" />
						</td>
					</tr>
					<tr>
						<td align="right">
							<font color="red">*</font>��ͬ�ţ�
						</td>
						<td align="left">
							<html:text name='rs' property="hth"  maxlength="40"/>
						</td>
						<td align="right">
							<font color="red">*</font>���֤�ţ�
						</td>
						<td align="left">
							<html:text name='rs' property="sfzh"  maxlength="18"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							��ͬ������ڻ�����
						</td>
						<td align="left">
							<html:text name='rs' property="htjbjrjg"  maxlength="40"/>
						</td>
						<td align="right">
							��ͬ��֧�������ƣ�
						</td>
						<td align="left">
							<html:text name="rs" property="htfzjgmc" maxlength="40" />
						</td>
					</tr>
					<tr>
						<td align="right">
							��ͬ��׼���ڣ�
						</td>
						<td align="left">
							<html:text name='rs' property="htpzrq" styleId="htpzrq"
								style="cursor:hand;"
								onclick="return showCalendar('htpzrq','y-mm-dd');" />
						</td>
						<td align="right">
							��ͬ�ܽ�
						</td>
						<td align="left">
							<html:text name='rs' property='htzje' maxlength="10" />
						</td>
					</tr>
					<tr>
						<td align="right">
							��ͬ����Ա��
						</td>
						<td align="left">
							<html:text name='rs' property="htjby" maxlength="40" />
						</td>
						<td align="right">
							��ͬ��׼�г���
						</td>
						<td align="left">
							<html:text name='rs' property="htpzhz" maxlength="40" />
						</td>
					</tr>
					<tr>
						<td align="right">
							��ͬ���ʼ���ڣ�
						</td>
						<td align="left">
							<html:text name='rs' property="hthkksrq" styleId="hthkksrq"
								style="cursor:hand;"
								onclick="return showCalendar('hthkksrq','y-mm-dd');" />
						</td>
						<td align="right">
							��ͬ�����ֹ���ڣ�
						</td>
						<td align="left">
							<html:text name='rs' property="hthkjzrq" styleId="hthkjzrq"
								style="cursor:hand;"
								onclick="return showCalendar('hthkjzrq','y-mm-dd');" />
						</td>
					</tr>
					<tr>
						<td align="right">
							��ͬչ�����ɣ�
						</td>
						<td align="left">
							<html:text name='rs' property="htzqly" maxlength="100" />
						</td>
						<td align="right">��ͬչ��ʱ�䣺</td>
						<td align="left">
							<html:text name='rs' property="htzqsj" maxlength="50" />
						</td>
					</tr>
					<tr>
						<td align="right">
							��ͬ���ʽ��
						</td>
						<td align="left">
							<html:text name='rs' property="htdkfs" maxlength="40" />
						</td>
						<td align="right">��ͬ���ʽ��</td>
						<td align="left">
							<html:text name='rs' property="hthkfs" maxlength="40" />
						</td>
					</tr>
					<tr>
						<td align="right">
							��ͬ����������ƣ�
						</td>
						<td align="left">
							<html:text name='rs' property="hthkjzmc" maxlength="40" />
						</td>
						<td align="right">��ͬ��������ʺţ�</td>
						<td align="left">
							<html:text name='rs' property="hthkjzzh" maxlength="40" />
						</td>
					</tr>
					<tr>
						<td align="right">
							��ͬ��ǷӦ����Ϣ��
						</td>
						<td align="left">
							<html:text name='rs' property="httqyhbxje" maxlength="10" />
						</td>
						<td align="right">��ͬ��Ƿ��ֹʱ�䣺</td>
						<td align="left">
							<html:text name='rs' property="httqjzsj" styleId="httqjzsj"
								style="cursor:hand;"
								onclick="return showCalendar('httqjzsj','y-mm-dd');" />
						</td>
					</tr>
					<tr>
						<td align="right">
							��ͬ��Ƿ��ע��
						</td>
						<td align="left" colspan="3">
							<html:text name='rs' style="width:100%" maxlength="100" property="httqbz" />
						</td>
					</tr>
					<tr>
						<td align="right">
							�������ʣ�
						</td>
						<td align="left">
							<html:text name='rs' property="dkll" maxlength="40" />
						</td>
						<td align="right">�������</td>
						<td align="left">
							<html:text name='rs' property="dklb" maxlength="40" />
						</td>
					</tr>
					<tr>
						<td align="right">
							��ע��
						</td>
						<td align="left" colspan="3">
							<html:text style="width:100%" name='rs' maxlength="100" property="bz" />
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button type="button" class="button2" onclick="dataCanModi(true)"
						style="width:80px" id="buttonModi">
						�� ��
					</button>
					<button type="button" class="button2"
						onclick="dataDoSave('xh-hth',document.getElementById('htzje').value);"
						style="width:80px" id="buttonSave">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="window.dialogArguments.document.getElementById('search_go').click();Close();" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
