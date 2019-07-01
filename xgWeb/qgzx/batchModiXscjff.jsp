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
<html>
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="Copyright" content="������� zfsoft">
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/cjff.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script>
		function save(){
			var pkV = val('pkV');
			var nd = val('nd');
			var yf = val('yf');
			var gzsj = val('gzsj');
			var cjje = val('cjje');
			var yhkh = val('yhkh');
			var yhmc = val('yhmc');
			var bz = val('bz');
			var jfglkg = val('jfglkg');
			
			if(!(nd == "" || yf =="")){
				//�ж��޸ĺ�������Ƿ�����ݿ��е��������ظ�
				cjff.checkRepeat({pkV:pkV,nd:nd,yf:yf},function(data){
					if(data != null && data != ""){
						alert(data + "����ʧ�ܣ�");
						return false;
					}else{
						if(jfglkg == "1"){//���þ��ѹ�����
							//�ж�ʣ�ྭ���Ƿ����
							cjff.getBatchOperSyjf({pkV:pkV,nd:nd,yf:yf,cjje:cjje},function(data){
								if(data != null && data != ""){
									alert(data + "����ʧ�ܣ�");
									return false;
								}else{
									refreshForm("qgzxcjff.do?method=modiXscjffBatch");
								}
							});
						}else{
							refreshForm("qgzxcjff.do?method=modiXscjffBatch");
						}
						
					}
				});
			}else{
				alert("��ѡ��ʱ�䣡");
				return false;
			}
		}
	</script>
	<base target="_self">
	<body>
		<html:form action="/qgzxcjff.do">
			<input type="hidden" name="pkV" id="pkV" value="${pkV}">
			<input type="hidden" name="jfglkg" id="jfglkg" value="${config.jfglkg}">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã��ڹ���ѧ - ��𷢷�- �����޸�
				</div>
			</div>
			<fieldset>
				<legend>
					ѡ�񷢷�ʱ��
				</legend>
				<table width="100%" class="tbstyle" align="center">
					<thead align="center">
						<tr>
							<td align="center" colspan="4">
								&nbsp;&nbsp;
							</td>
						</tr>
					</thead>					
						<tr>
							<td style="width:50%">
								<div align="right">
									<font color="red">*</font>��ȣ�
								</div>
							</td>
							<td style="width:50%">
								<html:select property="nd" styleId="nd">
									<html:options collection="ndList" property="nd"
										labelProperty="nd" />
								</html:select>
							</td>						
						</tr>
						<tr>
							<td align="right">
								<font color="red">*</font>�·ݣ�
							</td>
							<td>
								<html:select property="yf" styleId="yf">
									<html:option value=""></html:option>
									<html:options collection="yfList" property="yf" labelProperty="yf" />
								</html:select>
							</td>						
						</tr>
					</table>
				</fieldset>

				<fieldset>
				<legend>
					��д�����Ƶ���Ϣ
				</legend>
				<table width="100%" class="tbstyle" align="center">
					<thead align="center">
						<tr>
							<td align="center" colspan="4">
								&nbsp;&nbsp;
							</td>
						</tr>
					</thead>
					<tr>
						<td align="right" width="50%">
							����ʱ�䣺
						</td>
						<td> 
							<html:text property="gzsj" maxlength="6" onkeyup="value=value.replace(/[^\d|.]/g,'')"></html:text>
						</td>						
					</tr>
					<tr>
						<td align="right" nowrap="nowrap">
							����
						</td>
						<td>
							<html:text property="cjje" maxlength="6" onkeyup="value=value.replace(/[^\d|.]/g,'')"></html:text>
						</td>						
					</tr>
					<tr>
						<td align="right">
							�������ƣ�
						</td>
						<td>
							<html:text property="yhmc" maxlength="15"></html:text>
						</td>						
					</tr>
					<tr>
						<td align="right">
							���п��ţ�
						</td>
						<td>
							<html:text property="yhkh" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'')" ></html:text>
						</td>
					</tr>
					<tr>
						<td align="right">
							��ע��
						</td>
						<td colspan="3">
							<html:textarea property="bz" styleId="bz" cols="30" rows="4" onblur="chLeng(this,'60')"></html:textarea>
						</td>
					</tr>
				</table>
				</fieldset>
				<center>
					<div class="buttontool" id="btn">
						<button type="button" class="button2"
							onclick="save();return false;"
							style="width:80px">
							�� ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="window.close();return false;"
							style="width:80px">
							�� ��
						</button>
					</div>
				</center>

			 <logic:notEmpty name="result">
				<logic:equal value="true" name="result">
					<script>
					alert("�����ɹ���");
					Close();
					window.dialogArguments.document.getElementById('search_go').click();		
				</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
					alert("����ʧ�ܣ�");
					Close();
				</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
