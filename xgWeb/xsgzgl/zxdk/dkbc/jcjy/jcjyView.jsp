<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
	</head>
	<body>
		<html:form action="/jcjy_bcdc" method="post" styleId="jcjyModel">
			<html:hidden property="juid" styleId="juid"/>
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>�����</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    	<tr>
					    		<th>�������</th>
					    		<td>
					    			<span>${model.dclb}</span>
					    		</td>
					    		<th>�����Ƿ���ȫ</th>
					    		<td>
					    			<span>${model.clsfqq}</span>
					    		</td>
					    	</tr>
					    	<tr>
					    		<th>��ҵʱ��</th>
					    		<td>
					    			<span>${model.bysj}</span>
					    		</td>
					    		<th>������ϵ��ʽ</th>
					    		<td>
					    			<span>${model.jrlxfs}</span>
					    		</td>
					    	</tr>
					    	<tr>
					    		<th>��ҵ��λ����</th>
					    		<td>
					    			<span>${model.jydwmc}</span>
					    		</td>
					    		<th>��ҵ��λ��ϸ��ַ</th>
					    		<td>
					    			<span>${model.jydwdz}</span>
					    		</td>
					    	</tr>
					    	<tr>
					    		<th>�Ƿ�Ϊ������<br/>���ڵ�</th>
					    		<td>
					    			<span>${model.sfwxzfsfz}</span>
					    		</td>
					    		<th>��ҵ���</th>
					    		<td>
					    			<span>${hylbmc}</span>
					    		</td>
					    	</tr>
					    	<tr>
					    		<th>��ҵ��λ����<br />������ϵ�绰</th>
					    		<td>
					    			<span>${model.jydwlxdh}</span>
					    		</td>
					    		<th>��ǩ����������</th>
					    		<td>
					    			<span>${model.qdfwnx}</span>
					    		</td>
					    	</tr>
					    	<tr>
					    		<th>Ӧ����ѧ��<br/>��Ԫ��</th>
					    		<td>
					    			<span>${model.yjnxf}</span>
					    		</td>
					    		<th>ʵ�ʽ���ѧ��<br/>��Ԫ��</th>
					    		<td>
					    			<span>${model.sjjnxf}</span>
					    		</td>
					    	</tr>
					    	<tr>
					    		<th>������Ԫ��</th>
					    		<td>
					    			<span>${model.dkje}</span>
					    		</td>
					    		<th>������������</th>
					    		<td>
					    			<span>${model.yh}</span>
					    		</td>
					    	</tr>
					    	<tr>
					    		<th>�����ͬ��</th>
					    		<td>
					    			<span>${model.hth}</span>
					    		</td>
					    		<th>������ֹʱ��</th>
					    		<td>
					    			<span>${model.dkkssj}</span>
					    		</td>
					    	</tr>
					    	<tr>
					    		<th>���벹����Ԫ��</th>
					    		<td>
					    			<span>${model.sqbcje}</span>
					    		</td>
					    		<th>��׼��������<br/>��Ԫ��</th>
					    		<td>
					    			<span>${model.pzbcdcje}</span>
					    		</td>
					    	</tr>
					    	<tr>
					    		<th>��һ�δ������</th>
					    		<td>
					    			<span>${model.dicdc}</span>
					    		</td>
					    		<th>��һ�δ���ʱ��</th>
					    		<td>
					    			<span>${model.dicdcsj}</span>
					    		</td>
					    	</tr>
					    	<tr>
					    		<th>�ڶ��δ������</th>
					    		<td>
					    			<span>${model.decdc}</span>
					    		</td>
					    		<th>�ڶ��δ���ʱ��</th>
					    		<td>
					    			<span>${model.decdcsj}</span>
					    		</td>
					    	</tr>
					    	<tr>
					    		<th>�����δ������</th>
					    		<td>
					    			<span>${model.dscdc}</span>
					    		</td>
					    		<th>�����δ���ʱ��</th>
					    		<td>
					    			<span>${model.dscdcsj}</span>
					    		</td>
					    	</tr>
					    	<tr>
					    		<th>�Ƿ���ְ�ڸ�</th>
					    		<td>
					    			<span>${model.sfzzzg}</span>
					    		</td>
					    		<th>��ְ����Ƿ�Ϊ����<br/>��������Ρ�����<br/>��Ҫ����</th>
					    		<td>
					    			<span>${model.lzsfzc}</span>
					    		</td>
					    	</tr>
					    	<tr>
					    		<th>������Ϣ�ز����</th>
					    		<td>
					    			<span>${model.ylzd1}</span>
					    		</td>
					    		<th>������Ϣ�ز�ʱ��</th>
					    		<td>
					    			<span>${model.ylzd2}</span>
					    		</td>
					    	</tr>
					    	<tr>
					    		<th>�����Ƿ�����ȫ����</th>
					    		<td>
					    			<span>${model.dksfwqch}</span>
					    		</td>
					    		<th></th>
					    		<td></td>
					    	</tr>
					    	<tr>
					    		<th>��ע</th>
					    		<td colspan="3">
					    			<span>${model.bz}</span>
					    		</td>
					    	</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" type="button" onclick="iFClose();">
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