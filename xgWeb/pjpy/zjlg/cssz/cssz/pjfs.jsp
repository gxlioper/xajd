<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
</head>

<body>
	<div class="tab_cur">
		<p class="location">
			<em>���ĵ�ǰλ��:</em><a>�������� - �������� - ������ʽ</a>
		</p>
	</div>
		<html:form action="/zjlgPjpy" method="post">
			<logic:present name="updated">
				<logic:equal value="yes" name="updated">
					<script language="javascript">
	         	alert("����ɹ���");
	         	</script>
				</logic:equal>
				<logic:equal value="no" name="updated">
					<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	</script>
				</logic:equal>
			</logic:present>
			<div class="tab">
			<table class="formlist" width="100%">
				<tbody>
				<tr>
					<th>
						<b>�꼶</b>
					</th>
					<td>
						<b>������ʽ</b>
					</td>
				</tr>
				<logic:iterate id="s" name = "rs">
							<tr>
							<th>
								<bean:write name = "s" property="nj"/>
								<input type = "hidden" name = "njz" value = "<bean:write name = "s" property="nj"/>" />
							</th>
							<td>
								<html:select name = "s" property="pjfsz"> 
									<html:option value=""></html:option>
									<html:option value="5">ѧϰ������</html:option>
									<html:option value="100">ѧ�ּ�Ȩƽ����</html:option>
								</html:select>
							</td>
							</tr>
				</logic:iterate>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4">
			          <div class="btn">
						  <button type="button" onclick="refreshForm('zjlgPjpy.do?method=savePjfs');" id="buttonSave">
							�� ��
						</button>
						  <button type="button" name="�ر�" onclick="window.close();return false;" id="buttonClose">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
			</div>
		</html:form>
</body>
</html>
