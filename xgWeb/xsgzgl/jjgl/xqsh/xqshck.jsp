<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<%@ include file="/syscommon/head.ini"%>
  </head>
  <body>
  		<html:form action="/jjgl_xqshgl" method="post" styleId="jjglXqshForm">
			<html:hidden property="xqid" value="${xqModelMap.xqid }"/>
			<html:hidden property="shzt" styleId="shzt"/>
			<div class='tab' style='width:100%;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="5">
								<span>�ҽ���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
					    	<th width="20%">������</th>
					    	<td width="30%">${xqModelMap.sqr }</td>
					    	<th width="20%">����ʱ��</th>
					    	<td width="30%">${xqModelMap.sqsj }</td>
					    </tr>
					    <tr>
					    	<th>�ҽ�ѧ��</th>
					    	<td>${xqModelMap.jjxkmc }</td>
					    	<th>ѧ���꼶</th>
					    	<td>${xqModelMap.jjnjmc }</td>
					    </tr>
					    <tr>
					    	<th>�ҽ�ʱ��</th>
					    	<td>${xqModelMap.jjsj }</td>
					    	<th>�ҽ̵ص�</th>
					    	<td>${xqModelMap.jjdd }</td>
					    </tr>
					    <tr>
					    	<th>�ҽ���ʦҪ��</th>
					    	<td colspan="3">${xqModelMap.jjlsyq }</td>
					    </tr>
					    <tr>
					    	<th>��ע</th>
					    	<td colspan="3">${xqModelMap.bz }</td>
					    </tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="5">
								<span>�ҽ���Ů��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
					    	<th width="20%">����</th>
					    	<td width="30%">${znxxMap.xm }</td>
					    	<th width="20%">�Ա�</th>
					    	<td width="30%">${znxxMap.xb }</td>
					    </tr>
					    <tr>
					    	<th width="20%">��������</th>
					    	<td width="30%">${znxxMap.csrq }</td>
					    	<th width="20%">�꼶</th>
					    	<td width="30%">${znxxMap.nj }</td>
					    </tr>
					    <tr>
					    	<th width="20%">�ڶ�ѧУ</th>
					    	<td colspan="3">${znxxMap.zdxx }</td>
					    </tr>
					 </tbody>
					 <thead>
						<tr>
							<th colspan="5">
								<span>�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
					    	<th width="20%">���״̬</th>
					    	<td colspan="3">
					    		${xqModelMap.shztmc }
					    	</td>
					    </tr>
					    <tr>
					    	<th width="20%">������</th>
					    	<td colspan="3">
					    		${xqModelMap.ztbz }
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
