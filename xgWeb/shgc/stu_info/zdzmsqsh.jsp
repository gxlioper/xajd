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
		<html:form action="/prove_query" method="post">
		<input type="hidden" name="realTable" id="realTable" value="stu_zdzmsqsh"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>ѧ����Ϣ  - �ڶ�֤�� - �������</a>
			</p>
		</div>
		<div class="tab">	
			<table width="100%" align="center" class="formlist">
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
					<th>�������</th>
					<td>
						<bean:write name="rs" property="blfs" />
					</td>
				</tr>
				<tr>
					<th>�Ա�</th>
					<td>
						<bean:write name="rs" property="xb"/>
					</td>
					<th>�����ʽҪ��</th>
					<td>
						<bean:write name="rs" property="gsyq" />
					</td>
				</tr>
				<tr>
					<th>�꼶</th>
					<td>
						<bean:write name="rs" property="nj"/>
					</td>
					<th>��������</th>
					<td>
						<bean:write name="rs" property="sqrq" />
					</td>
				</tr>
				<tr>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>					
					<td>
						<bean:write name="rs" property="xymc" />
					</td>
					<th>��������</th>
					<td>
						<bean:write name="rs" property="cgrq"/>
					</td>
				</tr>
				<tr>
					<th>רҵ</th>
					<td>
						<bean:write name="rs" property="zymc"/>
					</td>
					<th>�������</th>
					<td>
						<bean:write name="rs" property="ggrq"/>
					</td>
				</tr>
				<tr>
					<th>�༶</th>
					<td>
						<bean:write name="rs" property="bjmc"/>
					</td>					
					<th>��������</th>
					<td>
						<bean:write name="rs" property="sqly"/>
					</td>
				</tr>	
				<tr>
					<th>֤������</th>
					<td colspan="3">
						<bean:write name="rs" property="zmlx"/>
						<input type="hidden" id="zmlx" name="zmlx" value="<bean:write name='rs' property="zmlx" />" />
					</td>	
				</tr>			
				<tr>					
					<th>���</th>
					<td colspan="3">					
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
							<html:textarea property="xyyj" name="rs" cols="60" rows="3" />
						</logic:equal>
						<logic:notEqual value="xy" name="userType" scope="request">						
							<html:textarea property="xyyj" name="rs" cols="60" rows="3" disabled="true" onblur="chLeng(this,100)"/>
						</logic:notEqual>
					</td>
				</tr>
				<tr>
					<th>ѧУ���</th>
					<td colspan="3">
						<logic:equal value="xx" name="userType" scope="request">
						<html:textarea property="xxyj" name="rs" cols="60" rows="3" onblur="chLeng(this,100)"/>
						</logic:equal>
						<logic:notEqual value="xx" name="userType" scope="request">						
						<html:textarea property="xxyj" name="rs" cols="60" rows="3" disabled="true"/>
						</logic:notEqual>
					</td>
				</tr>
				
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
		          <div class="btn">
		            <button
						onclick="viewAdd('prove_query.do?doType=auditing&type=save&zmlx='+$('zmlx').value+'&xh=','add');"
						style="width:80px" id="buttonSave">
						�� ��
					</button>
					<button onclick="window.close();return false;" style="width:80px"
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

		<logic:notEmpty name="result">
		<logic:equal value="true" name="result">
			<script>
				alert("�����ɹ�!");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
				<script>
				alert("����ʧ��!");				
			</script>
		</logic:equal>
		</logic:notEmpty>
	</body>
</html>
