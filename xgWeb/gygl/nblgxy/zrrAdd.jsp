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
		<base target = "_self" />
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/gyglShareData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>		
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/gygl/gyglFunction.js"></script>
		<html:form action="/nblgxy_gygl" method="post">
		<input type="hidden" id="url" name="url" value="/nblgxy_gygl.do?method=zrrAdd" />			
		<input type="hidden" id="getStuInfo" name="getStuInfo"value="xm-xb-nj-xymc-zymc-bjmc" />								
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã���Ԣ���� - ��Ϣά�� - ��������ϵ��
				</div>
			</div>				 
				<table width="100%" class="tbstyle">
					<thead>
						<tr align="center">
							<td height="22" colspan="4">
								������������
							</td>
						</tr>
					</thead>
				<tr>
					<td height="22" align="right" style="width: 10%">
						<font color="red">*</font>�����ˣ�
					</td>
					<td height="22" align="left" style="width: 30%">
						<html:text property="xh" styleId="xh"
							onkeypress="autoFillStuInfo(event.keyCode,this)" />
						<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
							class="btn_01" id="buttonFindStu">
							ѡ��
						</button>
					</td>
					<td height="22" align="right" style="width: 15%">
						��ϵ��ʽ��
					</td>
					<td height="22" align="left">
						<html:text property="lxfs" maxlength="25"></html:text>
					</td>
				</tr>

				<tr>
				<td height="22" align="right">
						������
					</td>
					<td height="22" align="left">
						<bean:write name="rs" property="xm"/>
					</td>
					<td height="22" align="right">
						���䣺
					</td>
					<td height="22" align="left">
						<html:text property="dzyx" maxlength="25"></html:text>
					</td>
					
				</tr>
				<tr>    
						<td height="22" align="right">
							�Ա�
						</td>
						<td height="22" align="left">
							<bean:write name="rs" property="xb"/>
						</td>
						<td height="22" align="right">
							��ְ���ڣ�
						</td>
						<td height="22" align="left">
						<html:text  property="rq" styleId="rq"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('rq','y-mm-dd');" readonly="true" />
					    </td>
					</tr>
						<tr>
						<td height="22" align="right">
							�꼶��
						</td>
						<td height="22" align="left">
							<bean:write name="rs" property="nj"/>
						</td>
						<td height="22" align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td height="22" align="left">
						    <bean:write name="rs" property="xymc"/>
					    </td>
					</tr>
						<tr>
						<td height="22" align="right">
							רҵ��
						</td>
						<td height="22" align="left">
							<bean:write name="rs" property="zymc"/>
						</td>
						<td height="22" align="right">
							�༶��
						</td>
						<td height="22" align="left">
						   <bean:write name="rs" property="bjmc"/>
					    </td>
					</tr>		
					<tr>
					<td align="right" rowspan="2">
						<font color="red">*</font>��������
					</td>							
						<td colspan="3">	
						    ¥����
						<html:select property="lddm" style="width:120px" styleId="lddm" onchange="getSsbhLb()">
							<html:option value="">--��ѡ��--</html:option>
							<html:options collection="ldList" property="dm"
								labelProperty="mc" />
						</html:select>
						���Һţ�
						<html:select property="ssbh" style="width:120px" styleId="ssbh" onchange="selectPoint(this,'fzssbh','ssbhcn','lddm')">							
							<html:options collection="ssbhList" property="dm"
								labelProperty="mc" />
						</html:select>

					</td>						
					</tr>						
					<tr align="left">
					<td colspan="4">													
							���ұ�ţ�
							<br>
							<html:textarea property="fzssbh" styleId="fzssbh" rows="5"  style="width:95%" readonly="true"/>
							<br>
							����ע�ͣ�
							<br>
							<html:textarea  property="ssbhcn" styleId="ssbhcn" rows="5" style="width:95%" readonly="true"/>					
							</td>
					</tr>
					<tr align="left">
							<td align="right">
								��ע��
							</td>
							<td colspan="4">
								<html:textarea  property="bz"  style="width:60%"  style="width:95%"
									rows="4" />
							</td>
					</tr>
				</table>
				<div class="buttontool" id="button" align="center">
					<button class="button2"
						onclick="zrrAddSave('xh-fzssbh')"
						style="width:80px" id="buttonSave">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2"
						onclick="clearFzq()"
						style="width:80px" id="buttonClear">
						��ո�����
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2"
						onclick="Close();return false;"
						style="width:80px" id="buttonClose">
						�� ��
					</button>
				</div>					
		</html:form>
		<logic:equal value="true" name="done">
			<script type="text/javascript">
				alert("�����ɹ���");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
		<logic:equal value="false" name="done">
			<script type="text/javascript">
				alert("����ʧ�ܣ�");
			</script>
		</logic:equal>
	</body>
	<script type="text/javascript">
	     function zrrAddSave(mustFill){	           
	           var eles = mustFill.split("-");
	           for (i = 0; i < eles.length; i++) {
		           if (document.getElementById(eles[i]).value == "") {
			       alert("�뽫��\"*\"�ŵ���Ŀ����������");
			       return false;
		           }		
	           }	
              var dzyx = document.getElementById('dzyx').value;
	          if(!isEmail(dzyx) && dzyx!=""){
		          alert("��������ȷ�ĵ�������!");
		          return false;
	          }	           	                    
                refreshForm("/xgxt/nblgxy_gygl.do?method=zrrAdd&doType=Save");  
                $("buttonSave").disabled=true;           
	     }
	     function clearFzq(){
	         if($("fzssbh").value!==""||$("ssbhcn").value!==""){
	            if(confirm("ȷ��Ҫ���\"�����š�ע��\"�еĵ�ǰ���ݣ�")){
	                $("fzssbh").value="";
	                $("ssbhcn").value="";
	            }
	         }
	     }
	</script>
</html>
