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

<head>
	<base target="_self" />
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="������� zfsoft" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<%
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript">
		function checkStuInfo(){
			var xh = document.getElementById('xh').value;
			if(xh == ""){
				alert("ѧ�Ų���Ϊ��!");
				return ;
			}
			refreshForm("whlg_xszz.do?method=jtjjknssq&doType=add");
		}
		
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "";
			document.forms[0].submit();
		}
		
		function getYj(str,type){
			if(str == "xy"){
				if(type){
					document.getElementById("xyyj_pc").style.display = "";
				}else{
					document.getElementById("xyyj_pc").style.display = "none";
				}	
			}else if(str == "xx"){
				if(type){
					document.getElementById("xxyj_pc").style.display = "";
				}else{
					document.getElementById("xxyj_pc").style.display = "none";
				}	
			}
		}
		
		/**�ж�ѧ���Ƿ��������*/
		function checkStuHaveSqRecord(){
			var userOnLine = document.getElementById("userOnLine").value;
			if(userOnLine == "student"){
				alert("���Ѿ���������Ƿ��������");
			}
		}
	</script>
</head>
<body>
	<div class="title">
		<div class="title_img" id="title_m"> 
		��ǰ����λ�ã�ѧ������ - ���������� - ���������� 
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				���ڲ�������ʱ���ڣ�����
			</p>
		</center>
	</logic:equal>
	<html:form action="/whlg_xszz.do?method=jtjjknssq" method="post">
			<input type="hidden" id="url" name="url"
				value="/whlg_xszz.do?method=jtjjknssq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc-nj-zzmmmc-sfzh" />
			<input type="hidden" id="userOnLine" name="userOnLine"
				value="<bean:write name="userOnLine" scope="session"/>" />	
			<table class="tbstyle" width="100%">
			<tbody>
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="center" style="width:10%">
							<font color="red">*</font>ѧ��
						</td>
						<td align="left" >
							<html:text name='rs' property="xh" styleId="xh"
								onkeypress="autoFillStuInfo(event.keyCode,this)" readonly="true" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<td align="center" style="width:10%">
							<font color="red">*</font>ѧ��
						</td>
						<td align="left">
							<input type="text" id="xh" name="xh"
								value="<bean:write name='rs' property="xh" />" readonly="readonly"/>
						</td>
					</logic:equal>
					<td>
						<div align="center">
							������ò
						</div>
					</td>
					<td>
						<input type="text" id="zzmmmc" name="zzmmmc"				
							value="<bean:write name='rs' property="zzmmmc" />" readonly="readonly"/>					
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							�Ա�
						</div>
					</td>
					<td>
						<input type="text" id="xb" name="xb" 
							value="<bean:write name='rs' property="xb" />" readonly="readonly"/>
					</td>
					<td>
						<div align="center">
							���֤��
						</div>
					</td>
					<td>
						<input type="text" id="sfzh" name="sfzh" 
							value="<bean:write name='rs' property="sfzh" />" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td>
						<input type="text" id="xymc" name="xymc"
							value="<bean:write name='rs' property="xymc" />" readonly="readonly"/>
					</td>
					<td scope="row">
						<div align="center">
							������������
						</div>
					</td>
					<td>
						<input type="text" id="ghlmkh" name="ghlmkh"
							value="<bean:write name='rs' property="ghlmkh" />">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							����
						</div>
					</td>
					<td  scope="col">
						<input type="text" id="xm" name="xm"
							value="<bean:write name='rs' property="xm" />" readonly="readonly"/>
					</td>
					<td>
						<div align="center">
							��ͥ�˾�������(Ԫ)
						</div>
					</td>
					<td>
						<input type="text" id="jtrjnsr" name="jtrjnsr"	
							value="<bean:write name='rs' property="jtrjnsr" />">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							�༶
						</div>
					</td>
					<td>
						<input type="text" id="bjmc" name="bjmc"
							
							value="<bean:write name='rs' property="bjmc" />" readonly="readonly"/>
					</td>
					<td>
						<div align="center">
							��У��ϵ�绰
						</div>
					</td>
					<td>
						<input type="text" id="zxlxdh" name="zxlxdh"
							value="<bean:write name='rs' property="zxlxdh" />">
					</td>
				</tr>
				<tr style="height:150px">
					<td rowspan="10">
						<div align="center">ѧ<br>��<br>��<br>��<br>��<br>��<br>��<br>��<br>��<br>��</div>
					</td>
					<td rowspan="10" colspan="3">
						<div style="width:90%">(һ)  ��Ҫ������<br><br>
							��1����ͥ���ṩ���¾�����ѵ����人������������ &nbsp;&nbsp;
							<logic:notEmpty name="rs" property="is_shbzx">
							<logic:match value="��" name="rs" property="is_shbzx">
								<input type="radio" name="is_shbzx" value="��" checked>&nbsp;&nbsp;��
							    <input type="radio" name="is_shbzx" value="��">&nbsp;&nbsp;��
							</logic:match>
							<logic:notMatch value="��" name="rs" property="is_shbzx">
								<input type="radio" name="is_shbzx" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="is_shbzx" value="��" checked>&nbsp;&nbsp;��
							</logic:notMatch>
							</logic:notEmpty>
							<logic:empty name="rs" property="is_shbzx">
								<input type="radio" name="is_shbzx" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="is_shbzx" value="��" checked>&nbsp;&nbsp;��
							</logic:empty>
							<br>
							��2����У���������������ӣ��޸ߵ�����Ʒ&nbsp;&nbsp;
							<logic:notEmpty name="rs" property="is_shjp">
							<logic:match value="��" name="rs" property="is_shjp">
								<input type="radio" name="is_shjp" value="��" checked>&nbsp;&nbsp;��
							    <input type="radio" name="is_shjp" value="��">&nbsp;&nbsp;��
							</logic:match>
							<logic:notMatch value="��" name="rs" property="is_shjp">
								<input type="radio" name="is_shjp" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="is_shjp" value="��" checked>&nbsp;&nbsp;��
							</logic:notMatch>
							</logic:notEmpty>
							<logic:empty name="rs" property="is_shjp">
								<input type="radio" name="is_shjp" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="is_shjp" value="��" checked>&nbsp;&nbsp;��
							</logic:empty>
						</div><br>
						
						<div style="width:90%">��������Ҫ�ο�������<br>
							��3���¶�����ʿ��Ů&nbsp;&nbsp;
							<logic:notEmpty name="rs" property="is_lszn">
							<logic:match value="��" name="rs" property="is_lszn">
								<input type="radio" name="is_lszn" value="��" checked>&nbsp;&nbsp;��
							    <input type="radio" name="is_lszn" value="��">&nbsp;&nbsp;��
							</logic:match>
							<logic:notMatch value="��" name="rs" property="is_lszn">
								<input type="radio" name="is_lszn" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="is_lszn" value="��" checked>&nbsp;&nbsp;��
							</logic:notMatch>
							</logic:notEmpty>
							<logic:empty name="rs" property="is_lszn">
								<input type="radio" name="is_lszn" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="is_lszn" value="��" checked>&nbsp;&nbsp;��
							</logic:empty>
							<br>
							��4������м����ش󼲲�&nbsp;&nbsp;
							<logic:notEmpty name="rs" property="is_stcj">
							<logic:match value="��" name="rs" property="is_stcj">
								<input type="radio" name="is_stcj" value="��" checked>&nbsp;&nbsp;��
							    <input type="radio" name="is_stcj" value="��">&nbsp;&nbsp;��
							</logic:match>
							<logic:notMatch value="��" name="rs" property="is_stcj">
								<input type="radio" name="is_stcj" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="is_stcj" value="��" checked>&nbsp;&nbsp;��
							</logic:notMatch>
							</logic:notEmpty>
							<logic:empty name="rs" property="is_stcj">
								<input type="radio" name="is_stcj" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="is_stcj" value="��" checked>&nbsp;&nbsp;��
							</logic:empty>
							<br>
							��5�������ͥ����������Ȼ�ֺ����ش���&nbsp;&nbsp;
							<logic:notEmpty name="rs" property="is_zrzh">
							<logic:match value="��" name="rs" property="is_zrzh">
								<input type="radio" name="is_zrzh" value="��" checked>&nbsp;&nbsp;��
							    <input type="radio" name="is_zrzh" value="��">&nbsp;&nbsp;��
							</logic:match>
							<logic:notMatch value="��" name="rs" property="is_zrzh">
								<input type="radio" name="is_zrzh" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="is_zrzh" value="��" checked>&nbsp;&nbsp;��
							</logic:notMatch></logic:notEmpty>
							<logic:empty name="rs" property="is_zrzh">
								<input type="radio" name="is_zrzh" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="is_zrzh" value="��" checked>&nbsp;&nbsp;��
							</logic:empty>
						</div><br>
						<div style="width:90%">������һ��ο�������<br>
							��6��ƫԶɽ����ũ��&nbsp;&nbsp;
							<logic:notEmpty name="rs" property="is_pysq">
							<logic:match value="��" name="rs" property="is_pysq">
								<input type="radio" name="is_pysq" value="��" checked>&nbsp;&nbsp;��
							    <input type="radio" name="is_pysq" value="��">&nbsp;&nbsp;��
							</logic:match>
							<logic:notMatch value="��" name="rs" property="is_pysq">
								<input type="radio" name="is_pysq" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="is_pysq" value="��" checked>&nbsp;&nbsp;��
							</logic:notMatch></logic:notEmpty>
							<logic:empty name="rs" property="is_pysq">
								<input type="radio" name="is_pysq" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="is_pysq" value="��" checked>&nbsp;&nbsp;��
							</logic:empty>
							<br>
							��7������&nbsp;&nbsp;
							<logic:notEmpty name="rs" property="is_dq">
							<logic:match value="��" name="rs" property="is_dq">
								<input type="radio" name="is_dq" value="��" checked>&nbsp;&nbsp;��
							    <input type="radio" name="is_dq" value="��">&nbsp;&nbsp;��
							</logic:match>
							<logic:notMatch value="��" name="rs" property="is_dq">
								<input type="radio" name="is_dq" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="is_dq" value="��" checked>&nbsp;&nbsp;��
							</logic:notMatch></logic:notEmpty>
							<logic:empty name="rs" property="is_dq">
								<input type="radio" name="is_dq" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="is_dq" value="��" checked>&nbsp;&nbsp;��
							</logic:empty>
							<br>
							��8����ͥ����Ů��ѧ&nbsp;&nbsp;
							<logic:notEmpty name="rs" property="is_dznsx">
							<logic:match value="��" name="rs" property="is_dznsx">
								<input type="radio" name="is_dznsx" value="��" checked>&nbsp;&nbsp;��
							    <input type="radio" name="is_dznsx" value="��">&nbsp;&nbsp;��
							</logic:match>
							<logic:notMatch value="��" name="rs" property="is_dznsx">
								<input type="radio" name="is_dznsx" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="is_dznsx" value="��" checked>&nbsp;&nbsp;��
							</logic:notMatch></logic:notEmpty>
							<logic:empty name="rs" property="is_dznsx">
								<input type="radio" name="is_dznsx" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="is_dznsx" value="��" checked>&nbsp;&nbsp;��
							</logic:empty>
							<br>
							��9����ĸ���Ͷ�������ĸ˫�¸ڣ����޹̶�����&nbsp;&nbsp;
							<logic:notEmpty name="rs" property="is_fmxg">
							<logic:match value="��" name="rs" property="is_fmxg">
								<input type="radio" name="is_fmxg" value="��" checked>&nbsp;&nbsp;��
							    <input type="radio" name="is_fmxg" value="��">&nbsp;&nbsp;��
							</logic:match>
							<logic:notMatch value="��" name="rs" property="is_fmxg">
								<input type="radio" name="is_fmxg" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="is_fmxg" value="��" checked>&nbsp;&nbsp;��
							</logic:notMatch></logic:notEmpty>
							<logic:empty name="rs" property="is_fmxg">
								<input type="radio" name="is_fmxg" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="is_fmxg" value="��" checked>&nbsp;&nbsp;��
							</logic:empty>
						</div><br>
						<div>�����������أ�<font color="blue">��100���ַ�</font><br>
							<html:textarea property="is_qtys" name="rs" rows="5" cols="82"></html:textarea>
						</div>
					</td>
				</tr>
				</tbody>
				<tbody id="tableadddel">
				<tr style="height:70px">
					<td rowspan="2">
						<div align="center">��<br>��<br>��<br>��</div>
					</td>
					<td colspan="3" style="height:20px">
						�Ƽ�����:&nbsp;&nbsp;
							<input type="radio" name="mzpy_tjdc" value="A">&nbsp;&nbsp;A.��ͥ��������
							<input type="radio" name="mzpy_tjdc" value="B">&nbsp;&nbsp;B.��ͥ������������
							<input type="radio" name="mzpy_tjdc" value="C">&nbsp;&nbsp;C.��ͥ���ò�����
							<input type="hidden" name="mzpy_tjdc" id="mzpy_tjdc_check" value="<bean:write name="rs" property="mzpy_tjdc"/>"/>
							<script type="text/javascript">
								var mzpy_tjdc_var = document.getElementById("mzpy_tjdc_check").value;
								if(!(mzpy_tjdc_var == null || mzpy_tjdc_var == "")){
									var mzpy_tjdc_group = document.getElementsByName("mzpy_tjdc");
									for(var i=0;i<mzpy_tjdc_group.length;i++){
										if(mzpy_tjdc_var == mzpy_tjdc_group[i].value){
											mzpy_tjdc_group[i].checked = true;
										}
									}
								}
							</script>
					</td>
					</tr>
					<tr>
					<td colspan="3">
						<div>��������&nbsp;&nbsp;<font color="blue">��2000���ַ�</font></div>
					<html:textarea property="mzpy_csly" name="rs" rows="8" cols="82"></html:textarea>
					</td>
					</tr>
				</tbody>
				<tbody>
					<tr>
					<td rowspan="4">
						<div align="center">��<br>��<br>��<br>��</div>
					</td>	
					<td colspan="3">
					<div align="center"><font size="4"><bean:message key="lable.xsgzyxpzxy" />���: </font></div>
					</td>
				</tr>
				<tr>
					<td>
					������С���Ƽ�����Ժ��ϵ��������˺�<br><br>
						<logic:notEmpty name="rs" property="rdjd_xyyj">
							<logic:match value="��" name="rs" property="rdjd_xyyj">
								<input type="radio" name="rdjd_xyyj" value="��" onclick="getYj('xy',false)" checked="checked">&nbsp;&nbsp;ͬ������С�����<br>
							    <input type="radio" name="rdjd_xyyj" value="��" onclick="getYj('xy',true)">&nbsp;&nbsp;��ͬ������С�����
							</logic:match>
							<logic:notMatch value="��" name="rs" property="rdjd_xyyj">
								<input type="radio" name="rdjd_xyyj" value="��" onclick="getYj('xy',false)">&nbsp;&nbsp;ͬ������С�����<br>
							    <input type="radio" name="rdjd_xyyj" value="��" onclick="getYj('xy',true)" checked="checked">&nbsp;&nbsp;��ͬ������С�����
							</logic:notMatch></logic:notEmpty>
							<logic:empty name="rs" property="rdjd_xyyj">
								<input type="radio" name="rdjd_xyyj" value="��" onclick="getYj('xy',false)">&nbsp;&nbsp;ͬ������С�����<br>
							    <input type="radio" name="rdjd_xyyj" value="��" onclick="getYj('xy',true)">&nbsp;&nbsp;��ͬ������С�����
							</logic:empty>
							<input type="hidden" name="rdjd_xyyjHidden" value="<bean:write name="rs" property="rdjd_xyyj"/>" id="rdjd_xyyjHidden"/>						
					</td>
					<td colspan="2">
					<div style="display:none" id="xyyj_pc">
						��ͬ������ɣ�����Ϊ&nbsp;&nbsp;<font color="blue">��200���ַ�</font>
						<html:textarea property="rdjd_xyyj_pc" name="rs" rows="5" cols="40"></html:textarea>
						<script type="text/javascript">
							var rdjd_xyyjHidden = document.getElementById("rdjd_xyyjHidden").value;
							if(rdjd_xyyjHidden == "��"){
								document.getElementById("xyyj_pc").style.display = "";
							}
						</script>
					</div>
					</td>		
				</tr>
				<tr>
					<td colspan="3">
						<div align="center"><font size="4">ѧУѧ����������������: </font></div>
					</td>
				</tr>
				<tr>
					<td>��ѧ������Ժ��ϵ�����룬�����������ʵ��<br><br>
						<logic:notEmpty name="rs" property="rdjd_xxyj">
							<logic:match value="��" name="rs" property="rdjd_xxyj">
								<input type="radio" name="rdjd_xxyj" value="��" onclick="getYj('xx',false)" checked="checked">&nbsp;&nbsp;ͬ�⹤���������С�����<br>
							    <input type="radio" name="rdjd_xxyj" value="��" onclick="getYj('xx',true)">&nbsp;&nbsp;��ͬ�⹤���������С�����
							</logic:match>
							<logic:notMatch value="��" name="rs" property="rdjd_xxyj">
								<input type="radio" name="rdjd_xxyj" value="��" onclick="getYj('xx',false)">&nbsp;&nbsp;ͬ�⹤���������С�����<br>
							    <input type="radio" name="rdjd_xxyj" value="��" onclick="getYj('xx',true)" checked="checked">&nbsp;&nbsp;��ͬ�⹤���������С�����
							</logic:notMatch></logic:notEmpty>
							<logic:empty name="rs" property="rdjd_xxyj">
								<input type="radio" name="rdjd_xxyj" value="��" onclick="getYj('xx',false)">&nbsp;&nbsp;ͬ�⹤���������С�����<br>
							    <input type="radio" name="rdjd_xxyj" value="��" onclick="getYj('xx',true)">&nbsp;&nbsp;��ͬ�⹤���������С�����
							</logic:empty>
							<input type="hidden" name="rdjd_xxyjHidden" value="<bean:write name="rs" property="rdjd_xxyj"/>" id="rdjd_xxyjHidden"/>
							
					</td>
					<td colspan="2">
					<div style="display:none" id="xxyj_pc">
						��ͬ������ɣ�����Ϊ&nbsp;&nbsp;<font color="blue">��200���ַ�</font>
						<html:textarea property="rdjd_xxyj_pc" name="rs" rows="5" cols="40"></html:textarea>
						<script type="text/javascript">
								var rdjd_xxyjHidden = document.getElementById("rdjd_xxyjHidden").value;
								if(rdjd_xxyjHidden == "��"){
									document.getElementById("xxyj_pc").style.display = "";
								}	
						</script>
					</div>
					</td>
					
				</tr>
				</tbody>
				<tbody>
					<tr>
						<td colspan="4">
						<DIV align="center">
							<font color="red">ע���˱���д������͡�ѧ������ͥ��������һ��������С�������϶�</font>
						</DIV>
						</td>
					</tr>
				</tbody>
			</table>
			
			<div class="buttontool" id="btn" style="position: absolute;width:100%">
				<logic:notEqual value="view" name="rs" property="doType">
				<button type="button" class="button2" onClick="checkStuInfo();" style="width:80px">
					�ύ����
				</button>&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onClick="toPrintOut();" style="width:80px">
					�� ӡ
				</button>&nbsp;&nbsp;&nbsp;&nbsp;
				</logic:notEqual>
				<logic:notEqual value="student" scope="session" name="userOnLine">
					<button type="button" class="button2" onClick="Close();" style="width:80px">
						�ر�
					</button>
				</logic:notEqual>
			</div>	
		</html:form>
	</body>
	<logic:present name="ok">
		<logic:match value="ok" name="ok">
			<script language="javascript">
	   			alert("����ɹ���");
	   			if(window.dialogArguments){
	   				dialogArgumentsQueryChick();
	   				Close();
	   			}
	   		
	        </script>
		</logic:match>
		<logic:match value="no" name="ok">
			<script language="javascript">
	        	alert("����ʧ�ܣ�");
	        </script>
		</logic:match>
	</logic:present>
	<logic:present name="have">
		<logic:match value="have" name="have">
			<script language="javascript">
	         	alert("��ͨ����ˣ��������룡");
	         </script>
		</logic:match>
	</logic:present>
</html:html>
