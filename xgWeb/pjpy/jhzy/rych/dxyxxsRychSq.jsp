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
	<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript">
		function dataSave(){
		    var act = "";
			var xh = "";
			var xn = "";
			var brjl="";
			var zysj="";	
			var zxqjhjqk="";
			var bz="";
			if($("act")){					
	           act = $("act").value;
	        }
	        if($("xh")){					
	           xh = $("xh").value;
	        }
	        if($("xn")){					
	           xn = $("xn").value;
	        }
	        if($("brjl")){
	           brjl = $("brjl").value;
	        }
	        if($("zysj")){
	           zysj = $("zysj").value;
	        }
	        if($("zxqjhjqk")){
	           zxqjhjqk = $("zxqjhjqk").value;
	        }
	        if($("bz")){
	           bz = $("bz").value;
	        }
			if((xh == null) || (xh == "")){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			if((xn == null) || (xn == "")){
				alert("ѧ�겻��Ϊ��!");
				return false;
			}
			if(brjl.length>1000){
			  	alert("���˼�������������1000����!");
				return false;
			}
			if(zysj.length>1000){
			  	alert("��Ҫ�¼�����������1000����!");
				return false;
			}
			if(zxqjhjqk.length>500){
			  	alert("���������������500����!");
				return false;
			}
			if(bz.length>300){
			  	alert("��ע����������300����!");
				return false;
			}	
			var pkV = xh+xn;
			if(act=="modi"){
			   if(confirm("�ύ�޸ĺ󣬸������轫���½�����ˣ�\n\nȷ��Ҫ�ύ��")){
			      refreshForm("/xgxt/jhzy_rych.do?method=dxyxxsRychSq&doType=save");			     
			   }
			}else{
			   getSztzData.getInfoEx("jhzy_dxyxxsb","xh||xn",pkV," (fdysh<>'δ���' or xysh<>'δ���' or xxsh<>'δ���')",function(bool){
			       if(bool){
			           alert("����������"+xn+"ѧ�굥������ѧ�������ƺţ�\n\n��ͨ����ز�����˻���������У������ٴ����룡 ");
			           return false;
			       }else{
			          refreshForm("/xgxt/jhzy_rych.do?method=dxyxxsRychSq&doType=save");			          
			       }
			    });				   
			}				
		}		
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/jhzy_rych.do?method=sqbPrint&xmk=sjyxbys";
			document.forms[0].target = "_blank";
	        document.forms[0].submit();
	        document.forms[0].target = "_self";
		}
	</script>
</head>
<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã��������� - �����ƺ����� - ��������ѧ��
		</div>
	</div>
	<%--	<logic:equal name="sfksq" value="-1">--%>
	<%--		<center>--%>
	<%--			<p>--%>
	<%--				���ڲ�������ʱ���ڣ�--%>
	<%--			</p>--%>
	<%--		</center>--%>
	<%--	</logic:equal>--%>
	<html:form action="/jhzy_rych" method="post">
	    <logic:present name="noPass">
	       <p align="center" style="color: red">����Ŀǰ������<bean:write name="jhzyPjpyForm" property="xn"/>ѧ��"��������ѧ��"�����ƺ�����������</p>
	    </logic:present>
		<input type="hidden" id="url" name="url"
			value="/jhzy_rych.do?method=dxyxxsRychSq" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-bjmc" />
		<input type="hidden" id="act" name="act"
			value="${act}" />		
		<table class="tbstyle" width="90%">
			<tr>
				<logic:equal name="userOnLine" value="teacher" scope="session">
					<td align="center" width="16%">
						<font color="red">*</font>ѧ��
					</td>
					<td align="left" width="34%">
						<html:text name='rs' property="xh" styleId="xh" readonly="true"
							onkeypress="autoFillStuInfo(event.keyCode,this)" />
						<logic:empty name="act">
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
						</logic:empty>
					</td>
				</logic:equal>
				<logic:equal name="userOnLine" value="student" scope="session">
					<td align="center" width="16%">
						<font color="red">*</font>ѧ��
					</td>
					<td align="left" width="34%">
						<input type="text" id="xh" name="xh" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xh" />" readonly="true">
					</td>
				</logic:equal>
				<td width="16%">
					<div align="center">
						ѧ��
					</div>
				</td>
				<logic:empty name="act">
					<td width="34%">
						<html:select property="xn" styleId="xn"
							onchange="refreshForm('/xgxt/jhzy_rych.do?method=dxyxxsRychSq')">
							<html:options collection="xnList" property="xn"
								labelProperty="xn" />
						</html:select>
					</td>
				</logic:empty>
				<logic:notEmpty name="act">
					<td width="34%">
						<html:text property="xn" readonly="true" />
					</td>
				</logic:notEmpty>
			</tr>
			<tr>
				<td width="16%">
					<div align="center">
						����
					</div>
				</td>
				<td width="34%">
					<input type="text" readonly="readonly" id="xm" name="xm"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="xm"/>" readonly="readonly">
				</td>
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
			</tr>
			<tr>
				<td>
					<div align="center">
						����
					</div>
				</td>
				<td>
					<input type="text" id="mzmc" readonly="readonly" name="mzmc"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="mzmc"/>">
				</td>
				<td>
					<div align="center">
						������ò
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
						��������
					</div>
				</td>
				<td>
					<input type="text" id="sfzh" readonly="readonly" name="csrq"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="csrq"/>">
				</td>
				<td>
					<div align="center">
						ְ��
					</div>
				</td>
				<td>
					<input type="text" id="zw" name="zw" readonly="readonly"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="zw"/>">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						��Դ��
					</div>
				</td>
				<td>
					<input type="text" id="syd" readonly="readonly" name="syd"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="syd"/>">
				</td>
				<td>
					<div align="center">
						��ϵ�绰
					</div>
				</td>
				<td>
					<input type="text" id="sjhm" name="sjhm" readonly="readonly"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="sjhm"/>">
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						�꼶
					</div>
				</td>
				<td>
					<input type="text" id="nj" readonly="readonly" name="nj"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="nj"/>">
				</td>
				<td>
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />����
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
						רҵ����
					</div>
				</td>
				<td>
					<input type="text" id="zymc" readonly="readonly" name="zymc"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="zymc"/>">
				</td>
				<td>
					<div align="center">
						�༶����
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
						��ͥ��ַ
					</div>
				</td>
				<td colspan="3">
					<input type="text" id="jtdz" name="jtdz" readonly="readonly"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="jtdz"/>">

				</td>
			</tr>

			<tr>
				<td>
					<div align="center">
						���˼���
						<br>
						<font color="red"><��1000��>
						</font>
					</div>
				</td>
				<td colspan="3">
					<html:textarea name="rsRych" property="brjl" rows='5'
						style="width:100%" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						��Ҫ�¼�
						<br>
						<font color="red"><��1000��>
						</font>
					</div>
				</td>
				<td colspan="3">
					<html:textarea name="rsRych" property="zysj" rows='5'
						style="width:100%" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						�����
						<br>
						<font color="red"><��500��>
						</font>
					</div>
				</td>
				<td colspan="3">
					<html:textarea name="rsRych" property="zxqjhjqk" rows='5'
						style="width:100%" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						��ע
						<br>
						<font color="red"><��300��> </font>
					</div>
				</td>
				<td colspan="3">
					<html:textarea name="rsRych" property="bz" rows='5' style="width:100%" />
				</td>
			</tr>
		</table>
		<%--			<logic:equal name="sfksq" value="1">--%>
		<%--				<logic:notPresent name="msg">--%>
		<div class="buttontool" id="btn" style="position: absolute;width:90%">
		<logic:notEqual value="view" name="act">
			<button type="button" class="button2" onClick="dataSave();" id="buttonSave"
			<logic:present name="noPass"> 
			disabled='true'
			</logic:present>
			>
				�ύ����
			</button>
		</logic:notEqual>
		<logic:notEmpty name="act">
			<button type="button" class="button2" onClick="Close()">
			   ��  ��
			</button>
		</logic:notEmpty>	
		</div>
 
	</html:form>
				<logic:equal value="true" name="done">
			  <script type="text/javascript">
			    alert('����ɹ���');
			    Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
			  </script>
			</logic:equal>	
			<logic:equal value="false" name="done">
			  <script type="text/javascript">
			    alert('����ʧ�ܣ�');
			    Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
			  </script>
			</logic:equal>
</body>
<logic:present name="msg">
	<script>
			alert(''+document.getElementById('msg').value);
		</script>
</logic:present>
</html:html>
