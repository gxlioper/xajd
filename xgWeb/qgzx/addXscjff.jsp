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
	<script type="text/javascript" src="/xgxt/dwr/interface/qgzxgzkh.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/cjff.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script>
		function save(){
			var pkValue = val('xh') + val('gwdm') + val('nd') + val('yf');
			var xmdm = val("gwdm");
			var gwdm = xmdm.split("-")[0];
			var gwsbsj = xmdm.split("-")[1];
			var jfglkg = val('jfglkg');
			
			if(filedNotNull('xh-gwdm-nd-yf-gzsj-cjje','-')){
				if(jfglkg == "1"){//���þ��ѹ�����
					//�ж�����Ƿ���
					cjff.getSyjf({xh:val('xh'),nd:val('nd'),yf:val('yf'),gwdm:gwdm,gwsbsj:gwsbsj},function(data){
						if(parseFloat(data)-parseFloat(val('cjje'))<=0){
							alert("���Ѳ��㣬��ʱ���ܷ��ų�𣡿ɷ��Ž�" + data);
							return false;
						}else{
							//�жϼ�¼�Ƿ����
							qgzxgzkh.checkExists('xscjffb',"xh||gwdm||'-'||sqsj||nd||yf",pkValue,function(data){
								if(data == true){
									if(confirm("����ӵļ�¼�Ѿ�����,����������ִ���޸Ĳ�����")){
										refreshForm('qgzxcjff.do?method=xscjffAdd');
									}
								}else{
									refreshForm('qgzxcjff.do?method=xscjffAdd');
								}
							});
						}
					});
				}else{
					//�жϼ�¼�Ƿ����
					qgzxgzkh.checkExists('xscjffb',"xh||gwdm||'-'||sqsj||nd||yf",pkValue,function(data){
						if(data == true){
							if(confirm("����ӵļ�¼�Ѿ�����,����������ִ���޸Ĳ�����")){
								refreshForm('qgzxcjff.do?method=xscjffAdd');
							}
						}else{
							refreshForm('qgzxcjff.do?method=xscjffAdd');
						}
					});
				}
				
			} else {
				alert ('�뽫��\*�ŵ���Ŀ��д������');
				return false;
			}
		}
	</script>
	<base target="_self">
	<body>
		<html:form action="/qgzxcjff.do">
			<input type="hidden" name="url" id="url" value="/qgzxcjff.do?method=addXscjff">
			<input type="hidden" value="xh-xm-xb-nj-xymc-zymc-bjmc" id="getStuInfo" name="getStuInfo" />
			<input type="hidden" value="${config.jfglkg }" id="jfglkg" name="jfglkg" />
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã��ڹ���ѧ - ��𷢷�- �����Ϣ
				</div>
			</div>
				<table width="100%" class="tbstyle">
					<thead align="center">
						<tr>
							<td align="center" colspan="4">
								ѧ����𷢷���Ϣ
							</td>
						</tr>
					</thead>

					<tr>
						<td align="right">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td>
							<html:text name="rs" property="xh" styleId="xh" onkeypress="if(event.keyCode == 13) autoFillStuInfo2(this);" />
							<button type="button" class="button2"
								onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								style="width:20px" id="buttonFindStu">
								ѡ��
							</button>
						</td>
						<td>
							<div align="right">
								<font color="red">*</font>��λ��
							</div>
						</td>
						<td>
							<html:select property="gwdm" styleId="gwdm">
								<html:options collection="gwList" property="gwdmgwsbsj"
									labelProperty="gwdm" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							������
						</td>
						<td>
							<bean:write name="rs" property="xm" />
						</td>
						<td>
							<div align="right">
								<font color="red">*</font>��ȣ�
							</div>
						</td>
						<td>
							<html:select property="nd" styleId="nd">
								<html:options collection="ndList" property="nd"
									labelProperty="nd" />
							</html:select>
						</td>						
					</tr>
					<tr>
						<td align="right">
							�Ա�
						</td>
						<td>
							<bean:write name="rs" property="xb" />
						</td>
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
					<tr>
						<td align="right" nowrap="nowrap">
							�꼶��
						</td>
						<td>
							<bean:write name="rs" property="nj" />
						</td>
						<td align="right" >
							<font color="red">*</font>����ʱ�䣺
						</td>
						<td> 
							<html:text property="gzsj" maxlength="6" onkeyup="value=value.replace(/[^\d|.]/g,'')"></html:text>
						</td>						
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td>
							<bean:write name="rs" property="xymc" />
						</td>
						<td align="right" nowrap="nowrap">
							<font color="red">*</font>����
						</td>
						<td>
							<html:text property="cjje" maxlength="6" onkeyup="value=value.replace(/[^\d|.]/g,'')"></html:text>
						</td>						
					</tr>
					<tr>
						<td align="right">
							רҵ��
						</td>
						<td>
							<bean:write name="rs" property="zymc" />
						</td>
						<td align="right">
							�������ƣ�
						</td>
						<td>
							<html:text name="rs" property="yhmc" maxlength="15" readonly="true"></html:text>
						</td>						
					</tr>
					<tr>
						<td align="right">
							�༶��
						</td>
						<td>
							<bean:write name="rs" property="bjmc" />
						</td>
						<td align="right">
							���п��ţ�
						</td>
						<td>
							<html:text name="rs" property="yhkh" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'')" readonly="true" ></html:text>
						</td>
					</tr>
					<tr>
						<td align="right">
							��ע��
						</td>
						<td colspan="3">
							<html:textarea property="bz" styleId="bz" cols="70" rows="4" onblur="chLeng(this,'60')"></html:textarea>
						</td>
					</tr>
				</table>
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
