<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/String.js"></script>
</head>
	<body>
		<html:form action="/qgzxZgdzdx.do" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>У���ڹ���ѧ - ��λ��Ϣ��ѯ - ��λ��ϸ��Ϣ</a>
				</p>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>

			<logic:notEmpty name="rs">		
				<div class="tab">
				<table width="100%" id="rsT" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��λ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<th>��λ����</th>
						<td>
							<bean:write name="rs" property="gwmc"/>						
						</td>
						<th>��λ����</th>
						<td>
							<bean:write name="rs" property="sqdwmc"/>	
						</td>
					</tr>
					<tr>
						<th>��λ��ַ</th>
						<td colspan="3">
							<bean:write name="rs" property="sqdwdz"/>	
						</td>
					</tr>
					<tr>
						<th>ѧ��</th>
						<td>
							<bean:write name="rs" property="xn"/>	
						</td>
						<th>���</th>
						<td>
							<bean:write name="rs" property="nd"/>							
						</td>
					</tr>
					<tr>
						<th>ѧ��</th>
						<td>
							<bean:write name="rs" property="xq"/>	
						</td>
						<th>��ϵ�绰</th>
						<td>
							<bean:write name="rs" property="lxdh"/>	
						</td>
					</tr>
					<tr>
						<th>������ʼ����</th>
						<td>
							<bean:write name="rs" property="gzkssj"/>	
						</td>
						<th>������������</th>
						<td>
							<bean:write name="rs" property="gzjssj"/>	
						</td>
					</tr>
					<tr>
						<th>��������</th>
						<td><bean:write name="rs" property="xyrs"/></th>
						<th>ʹ����������</th>
						<td>
							<bean:write name="rs" property="xyknsrs"/>	
						</td>
					</tr>
					<tr>
						<th>�Ƴ귽ʽ</th>
						<td>
							<bean:write name="rs" property="jcfsmc"/>	
						</td>
						<th>���鱨���׼</th>
						<td>
							<bean:write name="rs" property="jcbz"/>	
						</td>
					</tr>	
					<tr>
						<th><font color="red">���ͨ������</font></th>
						<td>
							<font color="red"><bean:write name="rs" property="shtgrs"/>	</font>
						</td>
						<th><font color="red">δ�������</font></th>
						<td>
							<font color="red"><bean:write name="rs" property="wshrs"/></font>	
						</td>
					</tr>		
					<tr>
					  <th>����ʱ��</th>
					  <td colspan="3">
					  	<bean:write name="rs" property="gzsj"/>	
					  </td>
				    </tr>
					<tr>
					  <th>��������</th>
					  <td colspan="3">
					  	<bean:write name="rs" property="gznr"/>	
					  </td>
				  	</tr>		
				    <tr>
					  <th>����Ҫ��</th>
					  <td colspan="3">
					  	<bean:write name="rs" property="gzyq"/>	
					  </td>
				    </tr>			
					<tr>
						<th>��ע</th>
						<td colspan="3">
							<bean:write name="rs" property="bz"/>	
						</td>
					</tr>
					</tbody>
				    <tfoot>
				      <tr>
				        <td colspan="4">
				          <div class="btn">
				            <button type="button" class="button2"
								onclick="Close();return false;"
								style="width:80px" id="buttonSave">
								�� ��
							</button>
				          </div>
				        </td>
				      </tr>
				    </tfoot>
				</table>
				</div>
			</logic:notEmpty>			
		</html:form>
	</body>
</html>
