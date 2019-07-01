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
<html:html>
<base target="_self" />
<head>


	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="������� zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/xszzCommonN05DWR.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	
	<script language="javascript">
		function yz(){
			if(!filedNotNull(["xh", "hjje"])){
				alert('�뽫��*�ŵ���Ŀ��д������');
				return false;
			}
			//�ж�������Ϣ�Ƿ��Ѿ����ͨ��
			xszzCommonN05DWR.checkIsShgc(val('save_xh')+val("save_xn"), "zsfhj1" , val("shjb"),function(data){
				if(data){//���ͨ��
					alert("���ύ����Ϣ�Ѿ��������,����ʧ�ܣ�");
					return false;
				}else{
					//�ύ
					document.forms[0].action = "/xgxt/n05_xszz.do?method=zsfhj1Add&type=save";
					document.forms[0].submit();
				}
			});
			
		}
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ��
			var pkValue = val('save_xh') + val('save_xn');
			showOpenWindow("/xgxt/n05_xszz.do?method=zsfhj1sqb&pkValue=" + pkValue,700,500);
		}
		
		function knspd(){
			var xh = val('save_xh');
			if(xh != null && xh != ""){
				var bxkns = val("bxkns");
				if(bxkns == "1"){
					if(val('isKns') == "false"){
						alert("�������������ſɲ�����");
						return false;
					}
				}
			}
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - ס�޷ѻ���
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<p align="center">
			<font color="red">Ŀǰ��������ʱ�䷶Χ�ڣ��ݲ��������룡</font>
		</p>
	</logic:equal>
		<html:form action="n05_xszz.do" method="post">
			<input type="hidden" id="url" name="url" value="/n05_xszz.do?method=zsfhj1Add" />
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-xymc-bjmc" />	
			<input type="hidden" id="shjb" name="shjb" value="${shjb}" />
			<input type="hidden" id="isKns" name="isKns" value="${rs.isKns}" />
			<input type="hidden" id="bxkns" name="bxkns" value="${bxkns}" />
			<logic:present name="result">
				<input type="hidden" id="message" value="${message}"/>
				<script>
					alert(document.getElementById('message').value);
				</script>
			</logic:present>
			<table class="tbstyle" width="90%">
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="center" width="16%">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td align="left" width="34%">
							<html:text 
							    name='rs' 
							    property="save_xh" 
							    styleId="xh"
								readonly="true"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu">
										ѡ��
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<td align="center" width="16%">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td align="left" width="34%">
							<input type="text" id="xh" name="save_xh"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xh" />" readonly="true">
						</td>
					</logic:equal>
					<td width="16%">
						<div align="center">
							������
						</div>
					</td>
					<td width="34%">
						<input type="text" readonly="readonly" id="xm" name="xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xm"/>" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							�Ա�
						</div>
					</td>
					<td>
						<input type="text" id="xb" readonly="readonly" name="xb"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xb"/>">
					</td>
					<td>
						<div align="center">
							���֤�ţ�
						</div>
					</td>
					<td>
						<input type="text" id="sfzh" name="sfzh" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sfzh"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							���壺
						</div>
					</td>
					<td>
						<input type="text" id="mzmc" readonly="readonly" name="mzmc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzmc"/>">
					</td>
					<td>
						<div align="center">
							������ò��
						</div>
					</td>
					<td>
						<input type="text" id="zzmmmc" name="zzmmmc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zzmmmc"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							�꼶��
						</div>
					</td>
					<td>
						<input type="text" id="nj" readonly="readonly" name="nj"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="nj"/>">
					</td>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />���ƣ�
						</div>
					</td>
					<td>
						<input type="text" id="xymc" name="xymc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xymc"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							רҵ���ƣ�
						</div>
					</td>
					<td>
						<input type="text" id="zymc" readonly="readonly" name="zymc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zymc"/>">
					</td>
					<td>
						<div align="center">
							�༶���ƣ�
						</div>
					</td>
					<td>
						<input type="text" id="bjmc" name="bjmc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="bjmc"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							ѧ�꣺
						</div>
					</td>
					<td>
						<html:text property="save_xn" name="rs" styleId="xn" readonly="true" style="width:100%;heigh:100%"/>
					</td>	
					<td>
						<div align="center">
							����ʱ�䣺
						</div>
					</td>
					<td>
						<html:text property="save_sqsj" name="rs" styleId="sqsj" readonly="true" style="width:100%;heigh:100%"/>
					</td>				
				</tr>							
				<tr>					
					<td>
						<div align="center">
							<font color="red">*</font>������
						</div>
					</td>
					<td colspan="3">
						<html:text property="save_hjje" styleId="hjje" name="rs" onkeyup="value=value.replace(/[^\d|.]/g,'') " style="width:100%" maxlength="10"></html:text>
					</td>
				</tr>
				
				<tr>
					<td>
						<div align="center">
							Ԥ�ƽ���ʱ�䣺
						</div>
					</td>
					<td>
						<html:text property="save_yjjfsj" 
						           styleId="yjjfsj" 
						           onclick="return showCalendar('yjjfsj','y-mm-dd');" 
						           onblur="dateFormatChg(this)"
						           style="width:100%"
						           name="rs">
						</html:text>
					</td>
					<td>
						<div align="center">
							Ԥ�ƽ��ѽ�
						</div>
					</td>
					<td>
						<html:text name="rs" property="save_yjjfje" style="width:100%" onkeyup="value=value.replace(/[^\d|.]/g,'') " maxlength="10"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							���뻺����Ҫԭ��
						</div>
					</td>
					<td colspan="3"><html:textarea property="save_sqyy" name="rs" style="width:100%" rows="3" onblur="chLeng(this,300)"></html:textarea>
							
					<br></td>					
				</tr>
			</table>
			<div class="buttontool" id="btn" style="position: absolute;width:90%">				
				<button class="button2" id="buttonSave"
					onClick="yz();">
					�ύ����
				</button>
				&nbsp;&nbsp;
				<button class="button2"
					onClick="toPrintOut();">
					��&nbsp;&nbsp;&nbsp;&nbsp;ӡ
				</button>
			</div>
		</html:form>
	</body>
	<logic:equal name="sfksq" value="-1">
		<script language="javascript">
		  $("buttonSave").disabled=true;
		</script>
	</logic:equal>
</html:html>
