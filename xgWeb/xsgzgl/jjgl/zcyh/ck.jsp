<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<%@ include file="/syscommon/head.ini"%>
  </head>
  
  <body>
		<html:form action="/jjgl_zcyhgl" method="post" styleId="jjglZcyhForm">
			<div class='tab' style='width:100%;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="5">
								<span>�û�������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
					    	<th width="20%">�ҳ����</th>
					    	<td width="30%">${zcyhxxMap.yhm }</td>
					    	<th width="20%">����</th>
					    	<td width="30%">${zcyhxxMap.xm }</td>
					    </tr>
					    <tr>
					    	<th>���֤��</th>
					    	<td>${zcyhxxMap.sfzh }</td>
					    	<th>��ϵ�绰</th>
					    	<td>${zcyhxxMap.lxdh }</td>
					    </tr>
					    <tr>
					    	<th>��ͥסַ</th>
					    	<td colspan="3">${zcyhxxMap.jtzz }</td>
					    </tr>
					    <tr>
					    	<th>������λ</th>
					    	<td colspan="3">${zcyhxxMap.gzdw }</td>
					    </tr>
					    <tr>
					    	<th>�Ǽ�ʱ��</th>
					    	<td colspan="3">${zcyhxxMap.zcsj }</td>
					    </tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="5">
								<span>��Ů��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<table width="100%">
					    			<thead>
										<tr>
											<td>
												����
											</td>
											<td>
												�Ա�
											</td>
											<td>
												��������
											</td>
											<td>
												�꼶
											</td>
											<td>
												�ڶ�ѧУ
											</td>
										</tr>
									</thead>
					    			<tbody>
					    				<logic:empty name="znxxMapList">
					    					<tr>
					    						<td colspan="5" style="text-align:center;">
					    							����!
					    						</td>
					    					</tr>
					    				</logic:empty>
										<logic:notEmpty name="znxxMapList">
											<logic:iterate id="znxx" name="znxxMapList">
											<tr>
					    						<td>${znxx.xm}</td>
					    						<td>${znxx.xb}</td>
					    						<td>${znxx.csrq}</td>
					    						<td>${znxx.nj}</td>
					    						<td>${znxx.zdxx}</td>
					    						</tr>
					    					</logic:iterate>
										</logic:notEmpty>
					    			</tbody>
					    		</table>
							</td>
						</tr>
					 </tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="�ر�" id="buttonClose" onclick="Close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
  </body>
</html>
