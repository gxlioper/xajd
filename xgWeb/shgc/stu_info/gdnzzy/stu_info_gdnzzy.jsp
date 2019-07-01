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
			var ssbh = document.getElementById("ssbh").value;
			var ssch = document.getElementById("ssch").value;
			var zsrq = document.getElementById("zsrq").value;
			var zsjzrq = document.getElementById("zsjzrq").value;
			if(ssbh!=""){
				if(ssch==""){
					alert("���ᴲ�Ų���Ϊ�գ�");
					return false;
				}
				if(ssch.length>1){
					alert("���ᴲ��ֻ����һλ�ַ�!");
					return false;
				}
				
			}
			if(zsrq > zsjzrq){
				alert("ס�޽�ֹ����������ס���ڣ����������ã���");
				return false;
			} 	
			stuinfoSave("stu_info_add.do?method=stuInfoSave&oper=");	
		}
	</script>
</head>
	<body>		
		<html:form action="/stu_info_add" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>������Ϣ-ѧ����Ϣά��</a>
			</p>
		</div>
		<input type="hidden" value="${oper}" id="oper" />			
		<input type="hidden" name="url" id="url" value="/sjcz/stu_info_modify.jsp"/>
		<input type="hidden" name="variable" id="variable" value="ydinfo"/>
		<input type="hidden" name="redirect" id="redirect" value="true"/>
		<input type="hidden" name="notnull" id="notnull" value="xh-xm-bjdm-zydm-xydm-nj"/>
		<div class="tab">	
			<table width="100%" class="formlist">
				<thead>
					<tr>
						<th colspan="5">
							<span>ѧ��������Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th><span class="red">*</span>ѧ��</th>
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
					<th><span class="red">*</span>�꼶</th>
					<td>
						<html:select name="rs" property="nj" styleId="nj"
							style="width:90px"
							onchange="initZyList();initBjList();">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj"
								labelProperty="nj" />
						</html:select>
					</td>
					<td align="left" width="15%" rowspan="6">
						<img border="0" style="height:133px;width:100px;"
							src="/xgxt/pictures/${rs.xh}.jpg" id="pic"/>
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>����</th>
					<td>
						<html:text name="rs" property="xm" styleId="xm" maxlength="16"/>
					</td>
					<th><span class="red">*</span><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						<html:select name="rs" property="xydm" styleId="xy"
							style="width:180px"
							onchange="initZyList();initBjList()">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
						</html:select>
						<input type="hidden" name="xyV" value="<bean:write name="xydm" scope="request"/>"/>
					</td>
				</tr>
				<tr>
					<th>�Ա�</th>
					<td>
						<html:radio name="rs" property="xb" value="1">��</html:radio>
						<html:radio name="rs" property="xb" value="2">Ů</html:radio>
					</td>
					<th><span class="red">*</span>רҵ</th>
					<td>
						<html:select name="rs" property="zydm" style="width:180px"
							styleId="zy"
							onchange="initBjList();">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm"
								labelProperty="zymc" />
						</html:select>
						<input type="hidden" name="zyV" value="<bean:write name="zydm" scope="request"/>"/>
					</td>
				</tr>
				<tr>
					<th>����</th>
					<td>
						<html:select name="rs" property="mz" styleId="mz"
							style="width:150px">
							<html:options collection="mzList" property="mzdm"
								labelProperty="mzmc" />
						</html:select>
					</td>
					<th><span class="red">*</span>�༶</th>
					<td>
						<html:select name="rs" property="bjdm" style="width:180px"
							styleId="bj">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm"
								labelProperty="bjmc" />
						</html:select>
						<input type="hidden" name="bjV" value="<bean:write name="bjdm" scope="request"/>"/>
					</td>
				</tr>
				<tr>
					<th>������ò</th>
					<td>
						<html:select name="rs" property="zzmm" styleId="mz"
							style="width:150px">
							<html:options collection="zzmmList" property="zzmmdm"
								labelProperty="zzmmmc" />
						</html:select>
					</td>
					<th>ѧ��״̬</th>
					<td>
						<html:select name="rs" property="xjzt" style="width:150">	
						<html:option value=""></html:option>
						<html:options collection="xjztList" property="zxdmmc" labelProperty="zxdmmc"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>����ƴ��</th>
					<td>
						<html:text name="rs" property="xmpy" maxlength="64"/>
					</td>
					<th>���</th>
					<td>
						<html:text name="rs" property="sg" 
						onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="3"/>
						(cm)
					</td>
				</tr>
				<tr>
					<th>������</th>
					<td>
						<html:text name="rs" property="cym" maxlength="16"/>
					</td>
					<th>����</th>
					<td colspan="2">
						<html:text name="rs" property="tz" 
						onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="3"/>
						(kg)
					</td>
				</tr>
				<tr>
					<th>��������</th>
					<td>
						<html:text name="rs" property="csrq"
							onclick="return showCalendar('csrq','y-mm-dd');" styleId="csrq" readonly="true"/>
					</td>
					<th>�س�</th>
					<td colspan="2">
						<html:text name="rs" property="tc" maxlength="32"/>
					</td>
				</tr>
				<tr>
					<th>������ʽ</th>
					<td>
						<html:text name="rs" property="pyfs" maxlength="32"/>
					</td>
					<th>�������</th>
					<td colspan="2">
						<html:text name="rs" property="pycc" maxlength="32"/>
					</td>
				</tr>
				<tr>
					<th>��ѧ��ʽ</th>
					<td>
						<html:text name="rs" property="rxfs" maxlength="32"/>
					</td>
					<th>�������</th>
					<td colspan="2">
						<html:text name="rs" property="kslb" maxlength="32"/>
					</td>
				</tr>
				<tr>
					<th>���֤��</th>
					<td>
						<html:text name="rs" property="sfzh" styleId="sfzh"/>
					</td>
					<th>��������</th>
					<td colspan="2">
						<html:text name="rs" property="dzyx" maxlength="dzyx"/>
					</td>
				</tr>
				<tr>
					<th>��Դ����</th>
					<td>
						<html:text name="rs" property="syd" maxlength="25"/>
					</td>
					<th>��ϵ�绰</th>
					<td colspan="2">
						<html:text name="rs" property="lxdh" maxlength="13" 
						onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>
				</tr>
				<tr>
				  <th>��ѧ����</th>
				  <td>
				  	<html:text name="rs" property="rxrq" onclick="return showCalendar('rxrq','y-mm-dd');" styleId="rxrq" readonly="true"/>
				  </td>
				  <th>����Ա����</th>
				  <td colspan="2"><html:text name="rs" property="fdyxm" maxlength="10"/></td>
			  </tr>
				<tr>
					<th>����</th>
					<td>
						<html:text name="rs" property="jg" maxlength="10"/>
					</td>
					<th>�ֻ�����</th>
					<td colspan="2">
						<html:text name="rs" property="sjhm" onkeyup="value=value.replace(/[^\d]/g,'') "
						maxlength="11"/>
					</td>
				</tr>						
				<tr>
					<th>ѧ��</th>
					<td>
						<html:text name="rs" property="xz" onkeyup="value=value.replace(/[^\d]/g,'') "
						maxlength="1"/>��						
					</td>
					<th>�߿��ɼ�</th>
					<td colspan="3">						
						<html:text name="rs" property="gkcj" maxlength="10"/>			
					</td>					
				</tr>
				<tr>
					<th>��ҵʱ��</th>
					<td>
						<html:text property="byny" name="rs" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>
					<th>�Ƿ��ҵ��</th>
					<td colspan="2">
						<html:select property="sfbys" name="rs" style="width:120px">
							<html:option value=""></html:option>
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
				<th>��ͥ�������</th>
				<td colspan="4">
					<html:select property="jtjjqk" name="rs" >
							<html:option value=""></html:option>
							<html:option value="1��Ԫ����/��">1��Ԫ����/��</html:option>
							<html:option value="1-5��Ԫ/��">1-5��Ԫ/��</html:option>
							<html:option value="5-10��Ԫ/��">5-10��Ԫ/��</html:option>
							<html:option value="10��Ԫ����/��">10��Ԫ����/��</html:option>
						</html:select>	
				</td>
				</tr>
				<tr>
					<th>��ͥ��ϸ��ַ</th>
					<td colspan="4">
						<html:text property="jtdz" name="rs" style="width:680;height:30" maxlength="120"/>
					</td>
				</tr>		
				<tr>
				  <th>����״��</th>
				  <td colspan="4">
				  	<html:text property="jkzk" name="rs" style="width:680;height:30" maxlength="120"/>
				  </td>
			  </tr>
				<tr>
					<th>�����</th>
					<td>
						<html:text property="ssbh" name="rs" styleId="ssbh" readonly="true"/>
					</td>
					<th>���ᴲ��</th>
					<td colspan="2">
						<html:text name="rs" property="ssch" readonly="true"/>
					</td>
				</tr>
				<tr>
					<th>ס������</th>
					<td>
						<html:text name="rs" property="zsrq" styleId="zsrq" readonly="true"/>
					</td>
					<th>ס�޽�ֹ����</th>
					<td colspan="2">
						<html:text name="rs" property="zsjzrq" styleId="zsjzrq" readonly="true"/>
					</td>
				</tr>
				<tr>
					<th>ס����Ϣ��ע</th>
					<td  colspan="4">
						<html:textarea name="rs" property="ssbz" style="width:100%;height:100%" readonly="true"></html:textarea>
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			            <logic:notPresent name="details">
							<button class="button2" style="height:20px;width:80px" id="buttonSave"
								onclick="send();">
								�� ��
							</button>
						</logic:notPresent>
						<button class="button2" style="height:20px;width:80px"
							onclick="Close();return false;">
							�� ��
						</button>
						</div>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
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
