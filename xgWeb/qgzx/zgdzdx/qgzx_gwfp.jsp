<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/qgzxFunction.js"></script>
</head>
	<body>
		<html:form action="/qgzxZgdzdx" method="post">
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}" />
			<input id="pk" name="pk" value="<bean:write name="pk"/>" type="hidden"/>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>�ڹ���ѧ - ֱ�ӷ����λ - ��λ����</a>
				</p>
			</div>

			<div class="tab">
			<table width="100%" id="rsT" class="formlist">
				<thead>
					<tr>
						<th colspan="2">
							<span>��λ����</span>
						</th>
					</tr>
				</thead>	
				<tbody>			
				<tr>
					<th>ѧ��</th>
					<td >
						<bean:write name="xn"/>
					</td>
				</tr>
				<tr>
					<th>���</th>
					<td >
						<bean:write name="nd"/>
					</td>
				</tr>
				<tr>
					<th>ѧ��</th>
					<td >
						<bean:write name="xq"/>
					</td>
				</tr>
				<tr>
					<th>��λ</th>
					<td>
						<html:select property="gwdm">
						<html:options collection="gwList" labelProperty="gwdm" property="gwdmgwsbsj"/>
						</html:select>
					</td>
				</tr>	
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4">
			          <div class="btn">
			            <button type="button" class="button2" onclick="refreshForm('qgzxZgdzdx.do?method=saveGwfp')">
							�� ��
						</button>
						<button type="button" class="button2" onclick="Close();return false;">
							�� ��
						</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>	
			</table>
			</div>

			<logic:present name="result">
				<logic:equal value="true" name="result">
					<logic:empty name="msg">
					<script>
						alert('�����ɹ�!');
					</script>
				</logic:empty>
				<logic:notEmpty name="msg">
				<input id="msg" name="msg" value="<bean:write name="msg"/>" type="hidden"/>
				<script>
					alert(document.getElementById('msg').value);
				</script>
				</logic:notEmpty>
				<script>
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
				</logic:equal>
				<logic:equal value="false" name="result">
				<logic:empty name="msg">
					<script>
						alert('����ʧ��');
					</script>
				</logic:empty>
				<logic:notEmpty name="msg">
				<input id="msg" name="msg" value="<bean:write name="msg"/>" type="hidden"/>
				<script>
					alert(document.getElementById('msg').value);
				</script>
				</logic:notEmpty>
				<script>
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
