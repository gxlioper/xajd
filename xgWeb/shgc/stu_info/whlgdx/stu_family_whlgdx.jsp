<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->	
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/stuinfoFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
</head>
	<body>
		<html:form action="/modi_stu_famil" method="post">
			<input type="hidden" value="${oper}" id="oper" />
			<input type="hidden" name="url" id="url" value="/sjcz/stu_family_modify.jsp"/>
			<input type="hidden" name="variable" id="variable" value="ydinfo"/>
			<input type="hidden" name="redirect" id="redirect" value="true"/>
			<input type="hidden" name="page" id="page" value="stuinfo"/>
			<input type="hidden" name="notnull" id="notnull" value="xh"/>

			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>ѧ����Ϣ - ѧ����ͥ��Ϣ - ��Ϣά��</a>
				</p>
			</div>

			<logic:equal name="userOnLine" value="student" scope="session">
				<br/>
				<br/>
				<br/>
				<center><p>��ҳ��ֻ��<bean:message key="lable.xsgzyxpzxy" />�û����Է��ʣ�</p></center>
			</logic:equal>

			<logic:notEqual name="userType" value="xy" scope="session">
				<br/>
				<br/>
				<br/>
				<center><p>��ҳ��ֻ��<bean:message key="lable.xsgzyxpzxy" />�û����Է��ʣ�</p></center>
			</logic:notEqual>
			
			<logic:equal name="userType" value="xy" scope="session">
			<div class="tab">
			<table width="100%" class="formlist">	
				<thead align="center">
					<tr>
						<th align="center">
							<span>ѧ����ͥ��Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>			
				<tr>
					<th>
						<span class="red">*</span>ѧ�ţ�
					</td>
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
						<html:text name="rs" property="jtszd" maxlength="25"
							style="width:100%" />
					</td>
					<th>��������</th>
					<td>
						<html:text name="rs" property="jtyb"
							onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="10"
							style="width:100%" />
					</td>
				</tr>
				<tr>
					<th>��ͥ�绰</th>
					<td colspan="3">
						<html:text name="rs" property="lxdh1" maxlength="25"
							style="width:100%" />
					</td>
				</tr>
			</tbody>
			</table>

			<table width="100%" class="formlist">
				<thead>
					<tr>
						<th style="cursor:hand"
							onclick="document.getElementById('jt1').style.display=(document.getElementById('jt1').style.display==''?'none':'')">
							ѧ����ͥ��Ա��Ϣ1
						</th>
					</tr>
				</thead>
				<tbody>
				<tr id="jt1">
					<td>
						<table width="100%" class="formlist">
							<thead>
								<th colspan="4">��ͥ��Ա1��Ϣ</th>
							</thead>
							<tbody>
							<tr>
								<th>����</th>
								<td>
									<html:text name="rs" property="jtcy1_xm" styleId="jtcy1_xm" />
								</td>
								<th>�뱾�˹�ϵ</th>
								<td>
									<html:text name="rs" property="jtcy1_gx" styleId="jtcy1_gx" />
								</td>
							</tr>
							<tr>	
								<th>��λ</th>
								<td>
									<html:text name="rs" property="jtcy1_gzdz" styleId="jtcy1_gzdz" />
								</td>
								<th>��ϵ�绰</th>
								<td>
									<html:text name="rs" property="jtcy1_lxdh1" styleId="jtcy1_lxdh1" />
								</td>
							</tr>								
							<tr>
								<th>��ַ</th>
								<td colspan="3">
									<html:text name="rs" property="jtcy1_jtdz" styleId="jtcy1_jtdz" />
								</td>
							</tr>
						</tbody>
					</table>
				</td>
				</tr>
				</tbody>
				<thead>
					<tr>
						<th style="cursor:hand"
							onclick="document.getElementById('jt2').style.display=(document.getElementById('jt2').style.display==''?'none':'')">
							ѧ����ͥ��Ա��Ϣ2
						</th>
					</tr>
				</thead>
				<tbody>
				<tr id="jt2">
					<td>
						<table width="100%" class="formlist">
							<thead>
								<th colspan="4">��ͥ��Ա2��Ϣ</th>
							</thead>
							<tbody>
							<tr>
								<th>����</th>
								<td>
									<html:text name="rs" property="jtcy2_xm" styleId="jtcy2_xm" />
								</td>
								<th>�뱾�˹�ϵ</th>
								<td>
									<html:text name="rs" property="jtcy2_gx" styleId="jtcy2_gx" />
								</td>
							</tr>
							<tr>	
								<th>��λ</th>
								<td>
									<html:text name="rs" property="jtcy2_gzdz" styleId="jtcy2_gzdz" />
								</td>
								<th>��ϵ�绰</th>
								<td>
									<html:text name="rs" property="jtcy2_lxdh1" styleId="jtcy2_lxdh1" />
								</td>
							</tr>								
							<tr>
								<th>��ַ</th>
								<td colspan="3">
									<html:text name="rs" property="jtcy2_jtdz" styleId="jtcy2_jtdz" />
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
			</tbody>
			<thead>
				<tr>
					<th style="cursor:hand" align="center"
						onclick="document.getElementById('jt3').style.display=(document.getElementById('jt3').style.display==''?'none':'')">
						ѧ����ͥ��Ա��Ϣ3
					</th>
				</tr>
			</thead>
			<tbody>
			<tr id="jt3" style="display:none">
				<td>
					<table width="100%" class="formlist">
						<thead>
							<th colspan="4">��ͥ��Ա3��Ϣ</th>
						</thead>
						<tbody>
						<tr>
							<th>����</th>
							<td>
								<html:text name="rs" property="jtcy3_xm" styleId="jtcy3_xm" />
							</td>
							<th>�뱾�˹�ϵ</th>
							<td>
								<html:text name="rs" property="jtcy3_gx" styleId="jtcy3_gx" />
							</td>
						</tr>
						<tr>	
							<th>��λ</th>
							<td>
								<html:text name="rs" property="jtcy3_gzdz" styleId="jtcy3_gzdz" />
							</td>
							<th>��ϵ�绰</th>
							<td>
								<html:text name="rs" property="jtcy3_lxdh1" styleId="jtcy3_lxdh1" />
							</td>
						</tr>								
						<tr>
							<th>��ַ</th>
							<td colspan="3">
								<html:text name="rs" property="jtcy3_jtdz" styleId="jtcy3_jtdz" />
							</td>
						</tr>
					</tbody>
					</table>
				</td>
			</tr>
			</tbody>
			<logic:notEqual value="student" name="user">
			<logic:equal value="yes" name="writeAble">
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
			</logic:equal>
			</logic:notEqual>
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
				<logic:equal value="false" name="result">
					<script>
					alert("����ʧ�ܣ�");
					</script>
				</logic:equal>
			</logic:notEmpty>

		</html:form>
	</body>
</html>
