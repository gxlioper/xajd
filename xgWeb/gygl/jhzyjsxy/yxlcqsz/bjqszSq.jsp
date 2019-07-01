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
	  <script type='text/javascript'src='/xgxt/dwr/interface/getSztzData.js'></script>
    <script type='text/javascript'src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript">
		function dataSave(){
		    var act = "";
			var xh = "";
			var xn = "";				
			var qsjsqk="";
            
			if($("act")){					
	           act = $("act").value;
	        }
	        if($("xh")){					
	           xh = $("xh").value;
	        }
	        if($("xn")){					
	           xn = $("xn").value;
	        }	
	        if($("qsjsqk")){
	           qsjsqk = $("qsjsqk").value;
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
			if(qsjsqk.length>1000){
			  	alert("���ҽ����������������1000����!");
				return false;
			}
			
			var pkV = xh+xn;
			if(act=="modi"){
			   if(confirm("�ύ�޸ĺ󣬸������轫���½�����ˣ�\n\nȷ��Ҫ�ύ��")){
			      refreshForm("/xgxt/jhzy_yxlcqsz.do?method=bjqszSq&doType=save");			     
			   }
			}else{
			   getSztzData.getInfoEx("bjqszxxb","xh||xn",pkV," (fdysh<>'δ���' or xysh<>'δ���' or xxsh<>'δ���')",function(bool){
			       if(bool){
			           alert("���������뱾���\"�ټ����ҳ�\"��\n\n��ͨ����ز�����˻���������У������ٴ����룡 ");
			           return false;
			       }else{
			          refreshForm("/xgxt/jhzy_yxlcqsz.do?method=bjqszSq&doType=save");			          
			       }
			    });				   
			}				
		}		
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/jhzy_yxlcqsz.do?method=sqbPrint&xmk=bjqsz";
			document.forms[0].target = "_blank";
	        document.forms[0].submit();
	        document.forms[0].target = "_self";
		}
	</script>
</head>
<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã���Ԣ���� - ����¥�����ҳ����� - ���� - �ټ����ҳ�
		</div>
	</div>
	<html:form action="/jhzy_yxlcqsz" method="post">
<logic:present name="noPass">
	       <p align="center" style="color: red">����δ�����ι����ҳ������ܽ��б���ȡ��ټ����ҳ������룡</p>
	    </logic:present>	
		<input type="hidden" name="qshV" id="qshV" />
		<input type="hidden" id="url" name="url"
			value="/jhzy_yxlcqsz.do?method=bjqszSq" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-bjmc" />
		<input type="hidden" id="act" name="act"
			value="${act}" />
		<input type="hidden" name="ldmc" value="<bean:write name="ldmc"/>">
		<input type="hidden" name="qsh" value="<bean:write name="qsh"/>">					
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
							<button onclick="showTopWin('/xgxt/stu_LdQsInfo.do',750,550);"
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
							onchange="refreshForm('/xgxt/jhzy_rych.do?method=sjyxbysRychSq')">
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
					<bean:write name="rs" property="xm"/>
				</td>
				<td width="16%">
					<div align="center">
						¥��
					</div>
				</td>
				<td width="34%">
					<html:select property="lddm" styleId="lddm" onchange="getLcList()" disabled="true">						
						<html:options collection="ldList" property="dm"
							labelProperty="mc" />
					</html:select>
				</td>
				
			</tr>
			<tr>
				<td>
					<div align="center">
						�Ա�
					</div>
				</td>
				<td>
					<bean:write name="rs" property="xb" />
				</td>

				<td>
					<div align="center">
						¥��
					</div>
				</td>
				<td>
					<html:select property="lc" styleId="lc" onchange="getQshList2()" disabled="true">
						<html:options collection="lcList" property="dm" labelProperty="mc" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						����
					</div>
				</td>
				<td>
					<bean:write name="rs" property="mzmc"/>
				</td>
				
				<td width="16%">
					<div align="center">
						����
					</div>
				</td>
				<td width="34%">
					<html:select property="qsh" styleId="qsh" disabled="true">
						<html:options collection="qshList" property="dm"
							labelProperty="mc" />
					</html:select>
				</td>
			</tr>
			<tr>
				
				<td>
					<div align="center">
						������ò
					</div>
				</td>
				<td>
					<bean:write name="rs" property="zzmmmc"/>
				</td>
				<td>
					<div align="center">
						�꼶
					</div>
				</td>
				<td>
					<bean:write name="rs" property="nj"/>
				</td>
				
			</tr>
			
			<tr>
				<td>
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />
					</div>
				</td>
				<td>
					<bean:write name="rs" property="xymc"/>
				</td>
				<td>
					<div align="center">
						רҵ
					</div>
				</td>
				<td>
					<bean:write name="rs" property="zymc"/>
				</td>
			</tr>
			<tr>				
				<td>
					<div align="center">
						�༶
					</div>
				</td>
				<td>
                    <bean:write name="rs" property="bjmc"/>
				</td>
				<td>
					<div align="center">
						��ϵ�绰
					</div>
				</td>
				<td>
					<input type="text" id="lxdh" name="lxdh"  maxlength="20"
						style="180px;heigh:100%"
						value="<bean:write name="rsBjQsz" property="lxdh"/>">
				</td>
			</tr>					
			<tr>
				<td>
					<div align="center">
						���ҽ������
						<br>
						<font color="red"><��1000��>
						</font>
					</div>
				</td>
				<td colspan="3">
					<html:textarea name="rsBjQsz" property="qsjsqk" rows='5'
						style="width:100%" />
				</td>
			</tr>						
		</table>
		<div class="buttontool" id="btn" style="position: absolute;width:90%">
		<logic:notEqual value="view" name="act">
			<button class="button2" onClick="dataSave()" id="buttonSave"
			<logic:present name="noPass"> 
			disabled='true'
			</logic:present>
			>
				�ύ����
			</button>
		</logic:notEqual>
			<button class="button2" onClick="toPrintOut();">
				��&nbsp;&nbsp;&nbsp;ӡ
			</button>
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
