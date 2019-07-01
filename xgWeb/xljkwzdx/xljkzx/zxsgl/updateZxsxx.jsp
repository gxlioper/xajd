<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
   <head>
	  <%@ include file="/syscommon/head.ini"%>
	  <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	  <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	  <script type="text/javascript" src="js/check.js"></script>
	  <script type="text/javascript" src="xljkwzdx/xljkzx/js/zxsgl.js"></script>
	  <script type="text/javascript">
	  </script>
  </head>
  
  <body>
     <html:form action="/xljk_zxsgl" method="post" styleId="zxsxxForm">
    	<table width="100%" border="0" class="formlist">
    		<thead>
				<tr>
					<th colspan="4">
						<span>��ʦ������Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th width="18%">
						<span class="red">*</span>ְ����
					</th>
					<td width="32%">
						<html:text property="zgh" styleId="zgh" styleClass="text_nor" readonly="true" style="width:110px;" value="${zxsInfo.zgh}"/>
					</td>
					<th width="18%">
						����
					</th>
					<td width="32%">
						${zxsInfo.xm}
					</td>
				</tr>
				<tr>
					<th>
						�Ա�
					</th>
					<td>
						${zxsInfo.xb}
					</td>
					<th>
						����
					</th>
					<td>
						${zxsInfo.age}
					</td>
				</tr>
				<tr>
					<th>
						��ϵ�绰
					</th>
					<td>
						${zxsInfo.lxdh}
						<html:hidden property="lxdh" styleId="lxdh" value="${zxsInfo.lxdh}"/>
					</td>
					<th>
						����
					</th>
					<td>
						${zxsInfo.bmmc}
					</td>
				</tr>
			</tbody>
    	</table>
    	<table width="100%" border="0" class="formlist">
    		<thead>
				<tr>
					<th colspan="2">
						<span>��ѯʦ��Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th width="18%">
						<span class="red">*</span>�ڸ�״̬
					</th>
					<td>
						<html:select  property="status" styleId="status">
							<html:option value="1">�ڸ�</html:option>
							<html:option value="0">���ڸ�</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>
						��ԤԼ����
					</th>
					<td>
						<html:text property="kjdrs" styleId="kjdrs" styleClass="text_nor" style="width:110px;" onchange="checkInt(this)" maxlength="3"/>
						��&nbsp;&nbsp;<span style="color: blue;font-weight: bold;">(��������Ĭ��������)</span>
					</td>
				</tr>
				<tr>
					<th>
						��ѯ��ϸ��ַ
					</th>
					<td>
						<html:text property="address" styleId="address" styleClass="text_nor" style="width:500px;" maxlength="50"/>
					</td>
				</tr>
				<tr>
					<th>
						��ְ����
					</th>
					<td>
						<html:select  property="zxszg" styleId="zxszg">
							<html:option value="��">��</html:option>
							<html:option value="������ѯʦ�ʸ�֤�����">������ѯʦ�ʸ�֤�����</html:option>
							<html:option value="������ѯʦ�ʸ�֤������">������ѯʦ�ʸ�֤������</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<th align="right">
						���&nbsp;&nbsp;<br/>
						<span class="red">(��500��)</span>
					</th>
					<td>
						<html:textarea property="zxsjj" styleId="zxsjj" style="width:95%;" rows="5" onkeypress="checkLen(this,500);"></html:textarea>
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="2">
						<div class="bz">"<span class="red">*</span>"Ϊ������</div>
						<div class="btn">
							<button id="submit_button" type="button"  onclick="updateZxsxxAction();">
								�� ��
							</button>
							<button type="button" name="�� ��" onclick="iFClose();">
								�� ��
							</button>
						</div>
					</td>
				</tr>
			</tfoot>
    	</table>
    </html:form>
  </body>
</html>
