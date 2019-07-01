<%@ page language="java" contentType="text/html; charset=gb2312"%>
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
		<title>��ҵ������Ϣϵͳ</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
    <script type="text/javascript" src="js/BatAlert.js"></script>
	<script language="javascript">
    function querygo(){
       var bynd1 = document.getElementById("bynd1").value;
       var bynd2 = document.getElementById("bynd2").value;
       
       if(bynd1==""||bynd2==""){
       alert("�������ò���Ϊ�գ�");
       return false;
       }
       
       
       if(bynd1==bynd2){
       alert("������������ͬ��ͳ�Ʋ�����");
       return false;
       }
     
       document.forms[0].action = "/xgxt/openonebuttonweb.do?doType=ok";
	   document.forms[0].submit();
    
    }
	
	
	
	
	</script>

	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<html:form action="/data_search" method="post">

			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã���ҵ���� - ͳ�Ʒ��� - ����һ��ͳ��
				</div>
			</div>
			<fieldset>
				<legend>
					ָ������
				</legend>
				<div align="center">
					<br>
					��ҵ���һ��
					<html:select name="rs1" property="bynd1" style="width:120px">
						<html:option value=""></html:option>
						<html:option value="2006">2006</html:option>
						<html:option value="2007">2007</html:option>
						<html:option value="2008">2008</html:option>
						<html:option value="2009">2009</html:option>
						<html:option value="2010">2010</html:option>
					</html:select>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ��ҵ��ȶ���
					<html:select name="rs1" property="bynd2" style="width:120px">
						<html:option value=""></html:option>
						<html:option value="2006">2006</html:option>
						<html:option value="2007">2007</html:option>
						<html:option value="2008">2008</html:option>
						<html:option value="2009">2009</html:option>
						<html:option value="2010">2010</html:option>
					</html:select>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="querygo();BatAlert.showTips('����ͳ�ƣ���ȴ�...');" style="width:80px">
						ͳ ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="expAppTab('rsTable','��ҵͳ�Ʊ�','')"
						style="width:80px">
						�� ӡ
					</button>
					<br>
					&nbsp;
				</div>
			</fieldset>

			<logic:empty name="list">
				<br />
				<br />
				<p align="center">
					δ�ҵ��κμ�¼��
				</p>
			</logic:empty>
			<logic:notEmpty name="list">
				<fieldset>
					<legend>
						<font color="blue">��ҵ����ͳ�Ʊ�</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<logic:iterate id="rs" name="list" offset="0">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="top" offset="0">
										<td>
											<bean:write name="tit" property="top" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<bean:size id="tcSize" name="rs"/>
							<tr style="cursor:hand">
								<logic:iterate id="s" name="rs" offset="0" length="1">
									<td rowspan="<%=tcSize+1%>">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<div align="center">
												<bean:write name="v" />
											</div>
										</logic:iterate>
									</td>
								</logic:iterate>

								<logic:iterate id="s" name="rs" offset="0" indexId="index">
									<tr>
										<logic:iterate id="v" name="s" offset="1">
											<td>
												<div align="center">
													<bean:write name="v" />
												</div>
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>