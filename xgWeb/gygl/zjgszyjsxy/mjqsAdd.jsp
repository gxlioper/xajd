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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript'src='/xgxt/dwr/interface/gyglShareData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/gygl/gyglFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<body >
		<html:form action="/zjgszy_gygl" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã� ��Ԣ���� - ��Ϣά�� - �����������
				</div>
			</div>
				<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td colspan="4" align="center">
							���������Ϣ
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right" width="20%">
						<font color="red">*</font>ѧ�꣺
					</td>
					<td align="left" width="25%">
						<html:select property="xn" style="width:150px" styleId="xn">
							<html:options collection="xnList" property="xn" 
								labelProperty="xn" />
						</html:select>
					</td>
					<td align="right" width="20%">
						<font color="red">*</font>ѧ�ڣ�
					</td>
					<td align="left">
						<html:select property="xq" style="width:150px" styleId="xq">
							<html:option value=""></html:option>
							<html:options collection="xqList" property="xqdm"
								labelProperty="xqmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>¥����
					</td>
					<td align="left">
						<html:select property="lddm" style="width:150px" onchange="getSsbhLb()" styleId="lddm">
							<html:option value=""></html:option>
							<html:options collection="ldList" property="lddm"
								labelProperty="ldmc" />
						</html:select>
					</td>
					<td align="right">
						<font color="red">*</font>���Һţ�
					</td>
					<td align="left">
						<html:select property="ssbh" style="width:150px" styleId="ssbh">							
							<html:options collection="qshList" property="dm"
								labelProperty="mc" />
						</html:select>
					</td>
				</tr>
				<tr>
				  <td align="right">
						
					</td>
					<td align="left">
						
					</td>
					<td align="right">
						¼��ʱ�䣺
					</td>
					<td align="left">
						<html:text property="rq" onblur="dateFormatChg(this)"  style="cursor:hand;" styleId="rq" 
								onclick="return showCalendar('rq','y-mm-dd');" readonly="true" ></html:text>
					</td>			
				</tr>
				<tr>
					<td align="right">
						��ע��<br>
						(��100����)
					</td>
					<td align="left" colspan="3">
						<html:textarea property='bz' styleId="bz" style="width:400px" 
							rows='5' />
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button class="button2" onclick="mjqsSave('xn-xq-lddm-ssbh')"
					style="width:80px" id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonSave">
					�� ��
				</button>
			</div>
		</html:form>
		<logic:equal value="true" name="done">
			<script type="text/javascript">
	          alert("�����ɹ���");
	          Close();
			  window.dialogArguments.document.getElementById('search_go').click();
	    </script>
		</logic:equal>
		<logic:equal value="false" name="done">
			<script type="text/javascript">
	          alert("����ʧ��,������Ѵ������\"*\"����Ŀ��ͬ�ļ�¼��������������ݺ����ύ��");
	    </script>
		</logic:equal>
	</body>
	<script type="text/javascript">
     function mjqsSave(mustFill){      
	    var eles = mustFill.split("-");
	    for (i = 0; i < eles.length; i++) {
		    if (document.getElementById(eles[i]).value == "") {
			alert("�뽫��\"*\"�ŵ���Ŀ����������");
			return false;
		    }		
	    } 
	    if($("bz").value.length>100){
	       alert("��ע�������ܴ���100�֣�");
	       return false;
	    }
	    refreshForm("/xgxt/zjgszy_gygl.do?method=mjqsAdd&doType=save");
	    $("buttonSave").disabled=true;
     }
  </script>
</html>
