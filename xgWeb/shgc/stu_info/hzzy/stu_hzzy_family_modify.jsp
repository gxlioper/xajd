<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->	
	<%@ include file="/syscommon/pagehead_V4.ini"%>	
	<script type="text/javascript" src="js/stuinfoFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script type="text/javascript" src="js/xgutil.js"></script>
</head>
	<body>
		<html:form action="/modi_stu_famil" method="post">
			<input type="hidden" value="${oper}" id="oper" />
			<input type="hidden" name="url" id="url" value="/sjcz/stu_family_modify.jsp"/>
			<input type="hidden" name="variable" id="variable" value="ydinfo"/>
			<input type="hidden" name="redirect" id="redirect" value="true"/>
			<input type="hidden" name="page" id="page" value="stuinfo"/>
			<input type="hidden" name="notnull" id="notnull" value="xh"/>
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}"/>
			<input type="hidden" name="yhjs" id="yhjs" value="${userType}"/>
			
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>ѧ����Ϣ - ѧ����ͥ��Ϣ - ��Ϣά��</a>
				</p>
			</div>
			
			<logic:equal name="userOnLine" value="student" scope="session">
				<br/>
				<br/>
				<br/>
				<center><p>��ҳ��ֻ��ѧУ�͹���Ա�û����Է��ʣ�</p></center>
			</logic:equal>

			<logic:equal name="userType" value="xy" scope="session">
				<br/>
				<br/>
				<br/>
				<center><p>��ҳ��ֻ��ѧУ�͹���Ա�û����Է��ʣ�</p></center>
			</logic:equal>
			
			<logic:equal name="userOnLine" value="teacher" scope="session">
			<logic:notEqual value="xy" name="userType" scope="session">		
				<div class="tab">	
				<table width="100%" class="formlist">	
					<thead>
						<tr>
						  <th colspan="4">
								<span>ѧ����ͥ��Ϣ</span>
						   </td>
						</tr>		
					</thead>
					<tbody>	
					<tr>
						<th><span class="red">*</span>ѧ��</th>
						<td>
							<logic:equal value="update" name="oper">
								<html:text property="xh" readonly="true" styleId="xh"
									style="cursor:hand" />
							</logic:equal>
							<logic:equal value="add" name="oper">
								<html:text name="rs" property="xh" styleId="xh"
									onkeypress="if(event.keyCode == 13) autoFillStuInfo2(this);" />
								<button 
									onclick="showTopWin('/xgxt/stu_info.do?oper=<bean:write name="oper"/>',750,550);"
									id="buttonFindStu">
									ѡ��
								</button>
							</logic:equal>
						</td>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<bean:write name="rs" property="xymc" />
						</td>
					</tr>
					<tr>
						<th>�꼶</th>
						<td>
							<bean:write name="rs" property="nj" />
						</td>
						<th>רҵ</th>
						<td>
							<bean:write name="rs" property="zymc" />
						</td>
					</tr>
					<tr>
						<th>����</th>
						<td>
							<bean:write name="rs" property="xm"/>
						</td>				
						<th>�༶</th>
						<td>
							<bean:write name="rs" property="bjmc" />
						</td>
					</tr>				
					<tr>
						<th>��ͥ��ַ</th>
						<td>
							<html:text name="rs" property="jtszd" maxlength="25"/>
						</td>
						<th>��������</th>
						<td>
							<html:text name="rs" property="jtyb" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'') "/>
						</td>
					</tr>
					<tr>
						<th>��ͥ�绰</th>
						<td colspan="3">
							<html:text name="rs" property="lxdh1" maxlength="25"/>
						</td>
					</tr>
					<tr>
						<th>��ͥ����״��</th>
						<td colspan="3">
							<html:textarea name="rs" property="jjzk" cols="60" rows="4" styleId="jjzk"/>
						</td>
					</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>��ͥ��Ա��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
	                   <td colspan="4">
							<table width="100%" class="formlist">								
								<tbody>
								  <tr>
								    <th>����</th>
								    <th>��ϵ</th>
								    <th>��ͥסַ</th>
								    <th>�ֻ��绰</th>
								    <th>������λ</th>
								    <th>��λ�绰</th>
								  </tr>
								  <tr>
								    <td><html:text name="rs" property="jtcy1_xm"/></td>
								    <td><html:text name="rs" property="jtcy1_gx"/></td>
								    <td><html:text name="rs" property="jtcy1_jtdz"/></td>
								    <td><html:text name="rs" property="jtcy1_lxdh1"/></td>
								    <td><html:text name="rs" property="jtcy1_gzdz"/></td>
								    <td><html:text name="rs" property="jtcy1_lxdh2"/></td>
								  </tr>
								 <tr>
								    <td><html:text name="rs" property="jtcy2_xm"/></td>
								    <td><html:text name="rs" property="jtcy2_gx"/></td>
								    <td><html:text name="rs" property="jtcy2_jtdz"/></td>
								    <td><html:text name="rs" property="jtcy2_lxdh2"/></td>
								    <td><html:text name="rs" property="jtcy2_gzdz"/></td>
								    <td><html:text name="rs" property="jtcy2_lxdh2"/></td>
								  </tr>
								  <tr>
								    <td><html:text name="rs" property="jtcy3_xm"/></td>
								    <td><html:text name="rs" property="jtcy3_gx"/></td>
								    <td><html:text name="rs" property="jtcy3_jtdz"/></td>
								    <td><html:text name="rs" property="jtcy3_lxdh3"/></td>
								    <td><html:text name="rs" property="jtcy3_gzdz"/></td>
								    <td><html:text name="rs" property="jtcy3_lxdh3"/></td>
								  </tr>
								</tbody>
							</table>	
					   </td>       
					</tr>
					</tbody>
					<logic:notEqual value="student" name="user">
					<tfoot>
				      <tr>
				        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
				          <div class="btn">
				            <button style="width:80px"
								onclick="stuinfoSave('modi_stu_famil.do?doType=modify&oper=','family')">
								�� ��
							</button>
							<button style="width:80px"
								onclick="Close();return false;">
								�� ��
							</button>
				          </div>
				        </td>
				      </tr>
				    </tfoot>
					</logic:notEqual>
				</table>
			</div>
			</logic:notEqual>
			</logic:equal>
			
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
					</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
