<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->	
	<%@ include file="/syscommon/pagehead_V4.ini"%>	
	<script type="text/javascript" src="js/String.js"></script>
	<script type="text/javascript" src="js/stuinfoFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getXjydInfo.js'></script>
	<script type="text/javascript">
		function Close() {
			var ua = navigator.userAgent;
			var ie = navigator.appName == "Microsoft Internet Explorer" ? true : false;
			if (ie) {
				var IEversion = parseFloat(ua.substring(ua.indexOf("MSIE ") + 5, ua.indexOf(";", ua.indexOf("MSIE "))));
				if (IEversion < 5.5) {
					var str = "<object id=noTipClose classid=\"clsid:ADB880A6-D8FF-11CF-9377-00AA003B7A11\">";
					str += "<param name=\"Command\" value=\"Close\"></object>";
					document.body.insertAdjacentHTML("beforeEnd", str);
					document.all.noTipClose.Click();
				} else {
					window.opener = null;
					window.close();
				}
			} else {
				window.close();
			}
		}
		
		function send(){	
			stuinfoSave("stu_info_add.do?method=stuInfoSave&oper=");	
		}
	</script>
</head>
	<body>		
		<html:form action="/stu_info_add" method="post">
		
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰλ�ã�������Ϣ-ѧ����Ϣά��
			</div>
		</div>
			
			
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td colspan="5" align="center">
							<center>
								ѧ��������Ϣ
							</center>
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right" width="15%">
						<font color="red">*</font>ѧ�ţ�
					</td>
					<td>
						<logic:equal value="update" name="oper">
							<html:text name="rs" styleId="xh" property="xh" readonly="true"
								style="cursor:hand" />
						</logic:equal>
						<logic:equal value="add" name="oper">
							<html:text name="rs" property="xh" styleId="xh" 
							onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="20"/>
						</logic:equal>
					</td>
					<td align="right" width="15%">
						<font color="red">*</font>�꼶��
					</td>
					<td align="left" width="30%">
						<html:select name="rs" property="nj" styleId="nj"
							style="width:90px"
							onchange="initZyList();initBjList();">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj"
								labelProperty="nj" />
						</html:select>
						<br />
					</td>
					<logic:equal value="�㽭��ҵְҵ����<bean:message key="lable.xsgzyxpzxy" />" name="xxmc" scope="session">
						<td align="left" width="15%" rowspan="6">
							<img border="0" style="height:133px;width:100px;"
								src="/xgxt/pictures/<bean:write name="rs" property="sfzh" />.jpg">
						</td>
					</logic:equal>
					<logic:notEqual value="�㽭��ҵְҵ����<bean:message key="lable.xsgzyxpzxy" />" name="xxmc" scope="session">
						<td align="left" width="15%" rowspan="6">
							<img border="0" style="height:133px;width:100px;"
								src="/xgxt/pictures/<bean:write name="rs" property="xh" />.jpg">
						</td>
					</logic:notEqual>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>������
					</td>
					<td align="left">
						<html:text name="rs" property="xm" styleId="xm" maxlength="16"/>
						<br />
					</td>
					<td align="right">
						<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td align="left">
						<html:select name="rs" property="xydm" styleId="xy"
							style="width:180px"
							onchange="initZyList();initBjList()">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
						</html:select>
						<input type="hidden" name="xyV" value="<bean:write name="xydm" scope="request"/>"/>
						<br />
					</td>
				</tr>
				<tr>
					<td align="right">
						�Ա�
					</td>
					<td align="left">
						<html:radio name="rs" property="xb" value="1">��</html:radio>
						<html:radio name="rs" property="xb" value="2">Ů</html:radio>
						<br />
					</td>
					<td align="right">
						<font color="red">*</font>רҵ��</td>
					<td align="left">
						<html:select name="rs" property="zydm" style="width:180px"
							styleId="zy"
							onchange="initBjList();">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm"
								labelProperty="zymc" />
						</html:select>
						<input type="hidden" name="zyV" value="<bean:write name="zydm" scope="request"/>"/>
						<br />
					</td>
				</tr>
				<tr>
					<td align="right">
						���壺
					</td>
					<td align="left">
						<html:select name="rs" property="mz" styleId="mz"
							style="width:150px">
							<html:options collection="mzList" property="mzdm"
								labelProperty="mzmc" />
						</html:select>
						<br />
					</td>
					<td align="right">
						<font color="red">*</font>�༶��</td>
					<td align="left">
						<html:select name="rs" property="bjdm" style="width:180px"
							styleId="bj">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm"
								labelProperty="bjmc" />
						</html:select>
						<input type="hidden" name="bjV" value="<bean:write name="bjdm" scope="request"/>"/>
						<br />
					</td>
				</tr>
				<tr>
					<td align="right">
						������ò��
					</td>
					<td align="left">
						<html:select name="rs" property="zzmm" styleId="mz"
							style="width:150px">
							<html:options collection="zzmmList" property="zzmmdm"
								labelProperty="zzmmmc" />
						</html:select>
						<br />
					</td>
					<td align="right">
						ѧ��״̬��</td>
					<td align="left">
						<html:select name="rs" property="xjzt" style="width:150">	
						<html:option value=""></html:option>
						<html:options collection="xjztList" property="zxdmmc" labelProperty="zxdmmc"/>
<!--							<html:option value="��ѧ��">��ѧ��</html:option>-->
<!--							<html:option value="��ѧ��">��ѧ��</html:option>-->
						</html:select>
						<br />
					</td>

				</tr>
				<tr>
					<td align="right">
						����ƴ����
					</td>
					<td align="left">
						<html:text name="rs" property="xmpy" maxlength="64"/>
						<br />
					</td>
					<td align="right">
						�����ţ�</td>
					<td align="left">
					  <html:text name="rs" property="ksh" />
					   <br />
				     </td>
				</tr>
				<tr>
					<td align="right">
						��������
					</td>
					<td align="left">
						<html:text name="rs" property="cym" maxlength="16"/>
						<br />
					</td>
					<td align="right">
						�������ڣ�</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="zsjj" />
						<br />				  </td>

				</tr>
				<tr>
					<td align="right">
						�������ڣ�
					</td>
					<td align="left">
						<html:text name="rs" property="csrq"
							onclick="return showCalendar('csrq','y-mm-dd');" styleId="csrq" readonly="true"/>
						<br />
					</td>
					<td align="right">
						��ǰ���ڼ���</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="dqszj" maxlength="32"/>
						<br />
					</td>
				</tr>
				<tr>
					<td align="right">
						������ʽ��
					</td>
					<td align="left">
						<html:text name="rs" property="pyfs"/>
						<br />
					</td>
					<td align="right">
						������Σ�</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="pycc"/>
						<br />
					</td>
				</tr>
				<tr>
				  <td align="right">��������</td>
				  <td align="left"><html:text name="rs" property="pyfx"/></td>
				  <td align="right">�����أ�</td>
				  <td align="left" colspan="2"><html:text name="rs" property="csd"/></td>
			  </tr>
				<tr>
					<td align="right">
						��ѧ��ʽ��
					</td>
					<td align="left">
						<html:text name="rs" property="rxfs" maxlength="32"/>
						<br />
					</td>
					<td align="right">
						�������
					</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="kslb" maxlength="32"/>
						<br />
					</td>
				</tr>
				<tr>
					<td align="right">
						���֤�ţ�
					</td>
					<td align="left">
						<html:text name="rs" property="sfzh" styleId="sfzh"/>
						<br />
					</td>
					<td align="right">
						�������䣺				  </td>
					<td align="left" colspan="2">
						<html:text name="rs" property="dzyx" styleId="dzyx"/>
						<br />
					</td>
				</tr>
				<tr>
				
					<td align="right">
						��Դ������
					</td>
					<td align="left">
						<html:text name="rs" property="syd" maxlength="25"/>
						<br />
					</td>
					<td align="right">
						��ϵ�绰��
					</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="lxdh" maxlength="13" onkeyup="value=value.replace(/[^\d]/g,'') "/>
						<br />
					</td>			
				</tr>
				<tr>				
				
					<td align="right">
						���᣺
					</td>
					<td align="left">
						<html:text name="rs" property="jg" maxlength="10"/>
						<br />
					</td>
					<td align="right">
						�ֻ����룺
					</td>
					<td align="left" colspan="2">
						<html:text name="rs" property="sjhm" maxlength="11"
						onkeyup="value=value.replace(/[^\d]/g,'') "/>
						<br />
					</td>
				
				</tr>
				<tr>
					<td align="right">
						ѧ�ƣ�
					</td>
					<td >
						<html:text name="rs" property="xz" onkeyup="value=value.replace(/[^\d]/g,'') "
						maxlength="1"/>��						
					</td>
						<td><div align="right">רҵ����								 
						</div></td>
						<td colspan="2">
						<html:text name="rs" property="zyfx"/>
						</td>	
				</tr>	
				<tr>
				  <td align="right">��ѧ���ڣ�</td>
				  <td ><html:text name="rs" property="rxrq" styleId="rxrq" 
				  onclick="return showCalendar('rxrq','y-mm-dd');" readonly="true"/></td>
				  <td><div align="right">רҵ���</div></td>
				  <td colspan="2"><html:text name="rs" property="zylb"/></td>
			  </tr>
				<tr>
				  <td align="right">����רҵ��</td>
				  <td ><html:text name="rs" property="fxzy"/></td>
				  <td><div align="right">����רҵ����</div></td>
				  <td colspan="2"><html:text name="rs" property="fxzyfx"/></td>
			  </tr>
				<tr>
				  <td align="right">��ѧ��ʽ��</td>
				  <td ><html:text name="rs" property="bxxs"/></td>
				  <td><div align="right">��ѧ���ͣ�</div></td>
				  <td colspan="2"><html:text name="rs" property="bxlx"/></td>
			  </tr>
				<tr>
				  <td align="right">ѧϰ��ʽ��</td>
				  <td ><html:text name="rs" property="xxxs"/></td>
				  <td><div align="right">��ҵ���ڣ�</div></td>
				  <td colspan="2"><html:text name="rs" property="byny" styleId="byrq" 
				  onclick="return showCalendar('byrq','y-mm-dd');" readonly="true"/></td>
			  </tr>
				<tr>
				  <td align="right">֤���ţ�</td>
				  <td ><html:text name="rs" property="zsbh"/></td>
				  <td><div align="right">֤�����кţ�</div></td>
				  <td colspan="2"><html:text name="rs" property="zsxlh"/></td>
			  </tr>
				<tr>
				  <td align="right">ѧλ��</td>
				  <td ><html:text name="rs" property="xw"/></td>
				  <td><div align="right">ѧλ֤���ţ�</div></td>
				  <td colspan="2"><html:text name="rs" property="xwzsbh"/></td>
			  </tr>
				<tr>
				  <td align="right">ѧλ֤�����кţ�</td>
				  <td ><html:text name="rs" property="xwzsxlh"/></td>
				  <td><div align="right">�Ͻ�ҵ���ۣ�</div></td>
				  <td colspan="2"><html:text name="rs" property="bjyjl"/></td>
			  </tr>
				<tr>
				  <td align="right">ѧУ��ַ��</td>
				  <td ><html:text name="rs" property="xxszd"/></td>
				  <td><div align="right">У��������</div></td>
				  <td colspan="2"><html:text name="rs" property="xzxm"/></td>
			  </tr>
				<tr>
				  <td align="right">��˱�ǣ�</td>
				  <td ><html:text name="rs" property="shbj"/></td>
				  <td><div align="right">��ӡ��ǣ�</div></td>
				  <td colspan="2"><html:text name="rs" property="dybj"/></td>
			  </tr>
				<tr>
					<td align="right">�滻��ʶ��</td>
					<td ><html:text name="rs" property="thbs"/></td>
						<td>&nbsp;</td>
						<td colspan="2">&nbsp;</td>	
				</tr>							
				<tr height="80">
					<td align="right">
						��ע��
					</td>
					<td align="left" colspan="4">
						<html:textarea name="rs" property="bz"
							style="width:100%;height:100%"></html:textarea>
					</td>
				</tr>
			</table>
			<div class="buttontool" id="btn" width="100%">
			<logic:notPresent name="details">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" style="height:20px;width:80px"
					onclick="send();">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
			</logic:notPresent>
				<button class="button2" style="height:20px;width:80px"
					onclick="Close();return false;">
					�� ��
				</button>
			</div>
			<logic:notEmpty name="result">
				<logic:equal value="true" name="result">
					<script>
						alert("�����ɹ���");
						Close();
						window.dialogArguments.document.getElementById('search_go').click();						
					</script>
				</logic:equal>
				<logic:equal name="result" value="false">
					<script>
						alert("����ʧ��!");
					</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
