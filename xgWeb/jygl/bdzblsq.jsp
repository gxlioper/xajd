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
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/bdzblgl.js'></script>
	<script>
		var num=0;
		var val1 ='';
		var val2 ='';
		function save(){
			var pkValue = val('xh');
			if(filedNotNull('xh-bdzlx-bdzkwdwmc-dajwdwmc','-')){
				refreshForm('bdzbl.do?method=saveBdzblsq');
			} else {
				alert ('�뽫��\*�ŵ���Ŀ��д������');
				return false;
			}
		}
		
		function loadDz(bdzlx){
			ele('bm').style.display= 'none';
			ele('dajwdwbm').style.display = 'none';
			var xh = val('xh');
			if(xh!= null && xh != ""){
				if("��ԭ������֤" == bdzlx){
					setVal('bdzkwdwmc',"");
					setVal('dajwdwmc',"");
					ele('bdzkwdwmc').readOnly = false;				
					ele('dajwdwmc').readOnly = false;
					bdzblgl.getSyd(xh,function(data){
						if(data.syd != null && data.syd != ""){
							setVal('bdzkwdwmc',(data.syd)+"���¾�");
							setVal('dajwdwmc',(data.syd)+"���¾�");
						}else{
							alert('��Դ����Ϣû����д��');
						}
					});
					num++;
				}
				if("�����˵�λ����֤" == bdzlx){
					setVal('bdzkwdwmc',"");
					setVal('dajwdwmc',"");
					ele('bdzkwdwmc').readOnly = true;				
					ele('dajwdwmc').readOnly = true;
					bdzblgl.getBdzkwdwmc(xh,function(data){
						if(data != null){
							if(data.mc != null && data.mc !=""){
								setVal('bdzkwdwmc',data.mc);
								setVal('dajwdwmc',data.mc);
								alert('�������֤�ĵ�λ���ƻ򵵰����յ�λ���Ʋ���ȷ�����ѯ��ҵЭ������Ϣ����е����ݣ�');
							}else{
								alert('����롮��ҵЭ������Ϣ��ˡ���д��ҵЭ�������ݣ�');
								return false;
							}
						}
					});
					num++;
				}
				if("���˹ҿ��˲��г�����֤" == bdzlx){
					if(num!=0){
						setVal('bdzkwdwmc',val1);
						setVal('dajwdwmc',val2);
						num++;
					}else{
						val1 = val('bdzkwdwmc');
						val2 = val('dajwdwmc');
					}
					ele('bdzkwdwmc').readOnly = false;				
					ele('dajwdwmc').readOnly = false;
					ele('bm').style.display='';
					ele('dajwdwbm').style.display = '';				
				}
			}
		}
		
		function fileDa(value){
			var bdzlx = val('bdzlx');
			if("���˹ҿ��˲��г�����֤" == bdzlx){
				setVal('dajwdwmc',value);				
			}
		}
		
	</script>
	<base target="_self">
	<body onload="if(ele('bdzlx')){if(val('bdzlx')=='���˹ҿ��˲��г�����֤'){loadDz(val('bdzlx'))}}">
		<html:form action="/bdzbl.do">
			<input type="hidden" name="url" id="url" value="/bdzbl.do?method=bdzblsq">
			<input type="hidden" value="xh-xm-xb-nj-xymc-zymc-bjmc" id="getStuInfo" name="getStuInfo" />
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã���ҵ���� - ����֤������� - ����֤��������
				</div>
			</div>
			<logic:present name="exists">
				<logic:equal value="true" name="exists">
					<br/><br/>
					<center>�����Ѿ���ʼ��ˣ���ʱ�����ٴ����룡</center>
				</logic:equal>
			</logic:present> 
			<logic:notEqual value="true" name="exists">
				<table width="100%" class="tbstyle">
					<thead align="center">
						<tr>
							<td align="center" colspan="4">
								����֤����������Ϣ
							</td>
						</tr>
					</thead>

					<tr>
						<td align="right">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td>
							<logic:equal value="student" name="userType">
								<html:text name="rs" property="xh" styleId="xh" readonly="true"/>
							</logic:equal>
							<logic:notEqual value="student" name="userType">
								<html:text name="rs" property="xh" styleId="xh" onkeypress="if(event.keyCode == 13) autoFillStuInfo2(this);" />
								<button class="button2"
									onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									style="width:20px" id="buttonFindStu">
									ѡ��
								</button>
							</logic:notEqual>
							
						</td>
						<td>
							<div align="right">
								��ȣ�
							</div>
						</td>
						<td>
							<html:text name="rs" property="nd" styleId="nd" readonly="true"/>
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
								<font color="red">*</font>����֤���ͣ�
							</div>
						</td>
						<td>
							<html:select property="bdzlx" styleId="bdzlx" name="rs" onchange="loadDz(this.value)">
								<html:option value=""></html:option>
								<html:option value="��ԭ������֤">��ԭ������֤</html:option>
								<html:option value="�����˵�λ����֤">�����˵�λ����֤</html:option>
								<html:option value="���˹ҿ��˲��г�����֤">���˹ҿ��˲��г�����֤</html:option>
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
						
						<td>
							<div align="right">
								<font color="red">*</font>����֤������
							</div>
						</td>
						<td>
							<html:text name="rs" property="bdzkwdwmc" maxlength="200" onchange="fileDa(this.value)"/>
						</td>						
					</tr>
					<tr>
						<td align="right" nowrap="nowrap">
							�꼶��
						</td>
						<td>
							<bean:write name="rs" property="nj" />
						</td>
						
						<td align="right">
							<font color="red">*</font>����������
						</td>
						<td nowrap="nowrap">
							<html:text property="dajwdwmc" name="rs" maxlength="200"/>
							<span style="display:none" id='bm'>���ţ�</span>
							<html:text property="dajwdwbm" name="rs" maxlength="200" style="display:none"/>
						</td>						
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td>
							<bean:write name="rs" property="xymc" />
						</td>
						
						<td align="right" >
							������ϵ��ַ��
						</td>
						<td> 
							<html:text property="lxdz" name="rs" maxlength="200"/>
						</td>					
					</tr>
					<tr>
						<td align="right">
							רҵ��
						</td>
						<td>
							<bean:write name="rs" property="zymc" />
						</td>
						<td align="right" nowrap="nowrap">
							������ϵ�ʱࣺ
						</td>
						<td>
							<html:text property="lxyb" name="rs" maxlength="6" onkeyup="value=value.replace(/[^\d]/g,'') "/>
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
							���˳�����ϵ��ʽ��
						</td>
						<td>
							<html:text property="lxfs" name="rs" maxlength="150"/>
						</td>	
					</tr>
					<tr>		
						<td align="right">
							ѧ�ƣ�
						</td>
						<td>
							<bean:write name="rs" property="xz" />
						</td>				
						<td align="right">
							�ֻ����룺
						</td>
						<td>
							<html:text property="sjhm" name="rs" maxlength="11" onkeyup="value=value.replace(/[^\d]/g,'') "/>
						</td>						
					</tr>
				</table>
				
				<center>
					<div class="buttontool" id="btn">
						<button class="button2"
							onclick="save();return false;"
							style="width:80px">
							�� ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="window.close();return false;"
							style="width:80px">
							�� ��
						</button>
					</div>
				</center>
				
			</logic:notEqual>
			 <logic:notEmpty name="result">
				<logic:equal value="true" name="result">
					<script>
					alert("�����ɹ���");
					Close();
					if(window.dialogArguments){
						window.dialogArguments.document.getElementById('search_go').click();
					}		
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
