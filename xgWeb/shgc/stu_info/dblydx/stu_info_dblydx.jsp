<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<!-- ͷ�ļ� -->
	<script type="text/javascript" src="js/String.js"></script>
	<script type="text/javascript" src="js/stuinfoFunction.js"></script> 
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
</head>
<body>		
	<html:form action="/stu_info_add" method="post">
		<input type="hidden" value="${oper}" id="oper" />
		<input type="hidden" name="url" id="url" value="/sjcz/stu_info_modify.jsp"/>
		<input type="hidden" name="variable" id="variable" value="ydinfo"/>
		<input type="hidden" name="redirect" id="redirect" value="true"/>
		<input type="hidden" name="notnull" id="notnull" value="xh-xm-bjdm-zydm-xydm-nj"/>

		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>������Ϣ-ѧ����Ϣά��</a>
			</p>
		</div>
		
		<div class="tab">
		  <table width="100%" border="0" class="formlist">
			<thead>
				<tr>
					<td colspan="5">
						<center>ѧ��������Ϣ</center>
					</td>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th><span class="red">*</span>ѧ��</th>
				<td>
					<logic:equal value="update" name="oper">
						<html:text name="rs" styleId="xh" property="xh" readonly="true"
							style="cursor:hand" maxlength="20"/>
					</logic:equal>
					<logic:equal value="add" name="oper">
						<html:text name="rs" property="xh" styleId="xh" maxlength="20" 
						onkeyup="value=value.replace(/[^\d]/g,'') " onkeydown=""/>
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
					<input type="hidden" name="xyV" value="${xydm}"/>
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
				<th><span class="red">*</span>רҵ</th>
				<td>
					<html:select name="rs" property="zydm" style="width:180px"
						styleId="zy"
						onchange="initBjList();">
						<html:option value=""></html:option>
						<html:options collection="zyList" property="zydm"
							labelProperty="zymc" />
					</html:select>
					<input type="hidden" name="zyV" value="${zydm}"/>
				</td>
			</tr>
			<tr>
				<th>������ò</th>
				<td>
					<html:select name="rs" property="zzmm" styleId="zzmm"
						style="width:150px">
						<html:options collection="zzmmList" property="zzmmdm"
							labelProperty="zzmmmc" />
					</html:select>
				</td>
				<th><span class="red">*</span>�༶</th>
				<td align="left">
					<html:select name="rs" property="bjdm" style="width:180px"
						styleId="bj">
						<html:option value=""></html:option>
						<html:options collection="bjList" property="bjdm"
							labelProperty="bjmc" />
					</html:select>
					<input type="hidden" name="bjV" value="${bjdm}"/>
				</td>
			</tr>
			<tr>
				<th>�Ա�</th>
				<td>
					<html:radio name="rs" property="xb" value="1">��</html:radio>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<html:radio name="rs" property="xb" value="2">Ů</html:radio>
				</td>
				<th>����</th>
				<td>
					<html:text name="rs" property="ah" maxlength="100"/>
				</td>
			</tr>
			<tr>
				<th>��������</th>
				<td>
					<html:text name="rs" property="csrq"
						onclick="return showCalendar('csrq','y-mm-dd');" styleId="csrq" maxlength="20" readonly="true"/>
				</td>
				<th>�س�</th>
				<td align="left" colspan="2">
					<html:text name="rs" property="tc" maxlength="32"/>
				</td>
			</tr>
			<tr>
				<th>���֤��</th>
				<td align="left">
					<html:text name="rs" property="sfzh" maxlength="18" styleId="sfzh"/>
				</td>
				<th>��������</th>
				<td align="left" colspan="2">
					<html:text name="rs" property="dzyx" maxlength="64" styleId="dzyx"/>
				</td>
			</tr>
			<tr>
				<th>�ֻ�����</th>
				<td align="left">
					<html:text name="rs" property="sjhm" maxlength="11"
					onkeyup="value=value.replace(/[^\d]/g,'') "/>
				</td>
				<th>�Ƿ����</th>
				<td align="left" colspan="2">						
					<html:radio name="rs" property="sfdk" value="0">��</html:radio>
					<html:radio name="rs" property="sfdk" value="1">��</html:radio>
				</td>
			</tr>
			<tr>
				<th>��ϵ�绰</th>
				<td>
					<html:text property="lxdh" name="rs" maxlength="13"/>
				</td>
				<th>ѧ��״̬</th>
				<td colspan="2">
					<html:select property="xjzt" name="rs">
						<html:option value=""></html:option>		
						<html:options collection="xjztList" property="zxdmmc" labelProperty="zxdmmc"/>
					</html:select>
				</td>
			</tr>
			<tr>
				<th>����</th>
				<td>
					<html:text property="jg" name="rs" maxlength="10"/>
				</td>
				<th>��Դ����</th>
				<td colspan="2">
					<html:text property="syd" name="rs" maxlength="25"/>
				</td>
			</tr>
			<tr>
				<th>�뵳ʱ��</th>
				<td align="left">
					<html:text name="rs" property="jrgcdsj" styleId="jrgcdsj"
					 onclick="return showCalendar('jrgcdsj','y-mm-dd');" readonly="true"/>
				</td>
				<th>����ʱ��</th>
				<td align="left" colspan="2">
					<html:text name="rs" property="jrgqtsj" styleId="jrgqtsj"
					onclick="return showCalendar('jrgqtsj','y-mm-dd');" readonly="true"/>
				</td>
			</tr>
			<tr>
				<th>���Һ�</th>
				<td align="left">
					<html:text property="ssbh" name="rs" styleId="ssbh" readonly="true"/>
				</td>
				<th>���ҵ绰</th>
				<td colspan="2">
					<html:text name="rs" property="qsdh" styleId="qsdh" readonly="true"/>
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
				<th>��ͥ�ʱ�</th>
				<td align="left">
					<html:text name="rs" property="jtyb" maxlength="20"/>
				</td>
				<th>��ͥ�绰</th>
				<td align="left" colspan="2">
					<html:text name="rs" property="jtdh" maxlength="20"/>
				</td>
			</tr>
			<tr>
				<th>��ͥ��ϸ<br/>��ַ</th>
				<td align="left" colspan="4">
					<html:text property="jtdz" name="rs" style="width:680;height:30" maxlength="127"/>
				</td>
			</tr>		
			<tr>
				<th>��ͥ����<br/>���</th>
				<td colspan="4">
					<html:radio name="rs" property="jtjjqk" value="��ԣ">��ԣ</html:radio>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<html:radio name="rs" property="jtjjqk" value="һ��">һ��</html:radio>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<html:radio name="rs" property="jtjjqk" value="����">����</html:radio>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<html:radio name="rs" property="jtjjqk" value="����">����</html:radio>
				</td>
			</tr>
			
			<tr>
				<th>���˼���ͥ<br/>������<br/>(100��)</th>
				<td align="left" colspan="4">
					<html:textarea name="rs" property="jtqkjj"
						style="width:680px;height:80px">
					</html:textarea>
				</td>
			</tr>
			<tr>
				<th>����ý���<br/>(�м�����)</th>
				<td colspan="4">
					<html:textarea name="rs" property="jlcfjl"
						style="width:680px;height:80px">
					</html:textarea>
				</td>
			</tr>
			<tr>
				<th>��ע</th>
				<td colspan="4">
					<html:textarea name="rs" property="bz"
						style="width:680px;height:80px">
					</html:textarea>
				</td>
			</tr>
			<thead>
			<tr>			
				<td colspan="5" style="cursor:hand" 
					onclick="document.getElementById('jt3').style.display=(document.getElementById('jt3').style.display==''?'none':'')">
					����������Ҫ����ϵ
				</td>			
			</tr>
			</thead>
			<tr id="jt3" style="display:none">
				<td colspan="5">
					<div class="formbox">
					<table class="dateline">
						<tr>
							<td>
								����
							</td>
							<td>
								�뱾�˹�ϵ
							</td>
							<td>
								������λ
							</td>
							<td>
								ְ��
							</td>
							<td>
								��λ�绰
							</td>
							<td>
								�ֻ�����
							</td>
						</tr>
						<tr>
							<td>
								<html:text name="rs" property="shgxxm1" maxlength="10"/>
							</td>
							<td>
								<html:text name="rs" property="shgxgx1" maxlength="32"/>
							</td>
							<td>
								<html:text name="rs" property="shgxgzdw1" maxlength="127"/>
							</td>
							<td>
								<html:text name="rs" property="shgxzw1" maxlength="32"/>
							</td>
							<td>
								<html:text name="rs" property="shgxdwdh1" maxlength="20"/>
							</td>
							<td>
								<html:text name="rs" property="shgxsjhm1" maxlength="20"/>
							</td>
						</tr>
						<tr>
							<td>
								<html:text name="rs" property="shgxxm2" maxlength="10"/>
							</td>
							<td>
								<html:text name="rs" property="shgxgx2" maxlength="32"/>
							</td>
							<td>
								<html:text name="rs" property="shgxgzdw2" maxlength="127"/>
							</td>
							<td>
								<html:text name="rs" property="shgxzw2" maxlength="32"/>
							</td>
							<td>
								<html:text name="rs" property="shgxdwdh2" maxlength="20"/>
							</td>
							<td>
								<html:text name="rs" property="shgxsjhm2" maxlength="20"/>
							</td>
						</tr>
					</table>
					</div>
				</td>
			</tr>
			</tbody>

			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
		          <div class="btn">
		             <logic:notPresent name="details">
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button style="height:20px;width:80px" 
								id="buttonSave"
								onclick="stuinfoSave('stu_info_add.do?method=stuInfoSave&oper=');">
							�� ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notPresent>
					<button style="height:20px;width:80px"
							onclick="Close();return false;">
						�� ��
					</button>
		          </div>
		        </td>
		      </tr>
		    </tfoot>
		</table>
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
			<logic:equal name="result" value="false">
				<script>
					alert("����ʧ��!");
				</script>
			</logic:equal>
		</logic:notEmpty>

	</html:form>
</body>
</html>
