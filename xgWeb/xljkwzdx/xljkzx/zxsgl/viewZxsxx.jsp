<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
   <head>
	  <%@ include file="/syscommon/head.ini"%>
	  <script type="text/javascript" src="xljkwzdx/xljkzx/js/zxsgl.js"></script>
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
						ְ����
					</th>
					<td width="32%">
						${zxsInfo.zgh}
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
						�ڸ�״̬
					</th>
					<td>
						<bean:write name="zxsxxForm" property="status"/>
					</td>
				</tr>
				<tr>
					<th>
						��ԤԼ����
					</th>
					<td>
						<bean:write name="zxsxxForm" property="kjdrs"/>
					</td>
				</tr>
				<tr>
					<th>
						��ѯ��ϸ��ַ
					</th>
					<td>
						<bean:write name="zxsxxForm" property="address"/>
					</td>
				</tr>
				<tr>
					<th>
						��ְ����
					</th>
					<td>
						<bean:write name="zxsxxForm" property="zxszg"/>
					</td>
				</tr>
				<tr>
					<th align="right">
						���
					</th>
					<td height="40px">
						<bean:write name="zxsxxForm" property="zxsjj" filter="false"/>
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="2">
						<div class="btn">
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
