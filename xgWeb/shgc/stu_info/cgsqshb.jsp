<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/stuinfoFunction.js"></script>
</head>
	<body onload="checkWinType();">		
		<html:form action="/data_search" method="post">
		<input type="hidden" name="realTable" id="realTable" value="xscgsqsh"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>ѧ����Ϣ - ѧ��������ѧ��ˡ�-���������</a>
			</p>
		</div>
		<div class="tab">	
			<table width="100%" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>�������</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th>ѧ��</th>
					<td>
						<bean:write name="rs" property="xh" />
						<input type="hidden" value="<bean:write name="rs" property="xh" />" name="xh" id="xh"/>
					</td>
					<th>���</th>
					<td>
						<bean:write name="rs" property="nd" />
						<input type="hidden" value="<bean:write name="rs" property="nd" />" name="nd" id="nd"/>
					</td>
				</tr>
				<tr>
					<th>����</th>
					<td>
						<bean:write name="rs" property="xm" />
					</td>
					<th>������ò</th>
					<td>
						<bean:write name="rs" property="zzmm" />
					</td>
				</tr>
				<tr>
					<th>�Ա�</th>
					<td>
						<bean:write name="rs" property="xb"/>
					</td>
					<th>��ͥסַ</th>
					<td>
						<bean:write name="rs" property="xb" />
					</td>
				</tr>
				<tr>
					<th>�꼶</th>
					<td>
						<bean:write name="rs" property="nj"/>
					</td>
					<th>��ϵ�绰</th>
					<td>
						<bean:write name="rs" property="lxdh" />
					</td>
				</tr>
				<tr>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						<bean:write name="rs" property="xymc" />
					</td>
					<th>����ʱ��</th>
					<td>
						<bean:write name="rs" property="sqrq"/>
					</td>
				</tr>
				<tr>
					<th>רҵ</th>
					<td>
						<bean:write name="rs" property="zymc"/>
					</td>
					<th>ȥ�ι��ң�ѧУ��</th>
					<td>
						<bean:write name="rs" property="gj"/>
					</td>
				</tr>
				<tr>
					<th>�༶</th>
					<td>
						<bean:write name="rs" property="bjmc"/>
					</td>					
					<th>����ԭ��</th>
					<td>
						<bean:write name="rs" property="cgyy" />
					</td>	
				</tr>
				<tr>
					<th>���õ������</th>
					<td>
						<bean:write name="rs" property="jjdbqk" />
					</td>
					<th>���</th>
					<td>
						<logic:equal value="xx" name="userType" scope="request">
						<html:select name="rs" styleId="yesNo" property="xxsh">
								<html:option value="δ���">δ���</html:option>
								<html:option value="ͨ��">ͨ��</html:option>
								<html:option value="��ͨ��">��ͨ��</html:option>
							</html:select>
						</logic:equal>
						<logic:equal value="xy" name="userType" scope="request">
						<html:select name="rs" styleId="yesNo" property="xysh">
								<html:option value="δ���">δ���</html:option>
								<html:option value="ͨ��">ͨ��</html:option>
								<html:option value="��ͨ��">��ͨ��</html:option>
							</html:select>
						</logic:equal>
					</td>
				</tr>
				<tr>
					<th><bean:message key="lable.xsgzyxpzxy" />���</th>
					<td colspan="3">
						<logic:equal value="xy" name="userType" scope="request">
						<html:textarea property="xyyj" name="rs" style="height:80;width:100%"/>
						</logic:equal>
						<logic:notEqual value="xy" name="userType" scope="request">						
						<html:textarea property="xyyj" name="rs" style="height:80;width:100%;" readonly="true"/>
						</logic:notEqual>
					</td>
				</tr>
				<tr>
					<th>ѧ�������</th>
					<td colspan="3">
						<logic:equal value="xx" name="userType">
						<html:textarea name="rs" property="xscyj" style="height:80;width:100%"/>
						</logic:equal>
						<logic:notEqual value="xx" name="userType">
						<html:textarea name="rs" property="xscyj" style="height:80;width:100%" readonly="true"/>
						</logic:notEqual>
					</td>
				</tr>
				<tr>
					<th>У�쵼���</th>
					<td colspan="3">
						<logic:equal value="xx" name="userType" scope="request">
						<html:textarea property="xxyj" name="rs" style="height:80;width:100%"/>
						</logic:equal>
						<logic:notEqual value="xx" name="userType" scope="request">						
						<html:textarea property="xxyj" name="rs" style="height:80;width:100%;" readonly="true"/>
						</logic:notEqual>
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			           <button
							onclick="viewAdd('/xgxt/abroad_query.do?act=cgsq_sh&doType=save','add');"
							style="width:80px" id="buttonSave">
							�� ��
						</button>
						<button onclick="Close();return false;" style="width:80px"
							id="buttonClose">
							�� ��
						</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>				
			</table>
		</div>
		</html:form>

		<logic:equal value="true" name="result">
			<script>
				alert("�����ɹ�");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>
	</body>
</html>
