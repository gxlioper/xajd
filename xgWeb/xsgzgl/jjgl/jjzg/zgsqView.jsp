<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<%@ include file="/syscommon/head.ini"%>
  </head>
  
  <body>
  		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<!-- ���� end-->
		
		<html:form action="/jjgl_jjzg" method="post" styleId="zgsqForm">
			<html:hidden property="id" />
			<html:hidden property="xh" value="${userName }"/>
			<html:hidden property="shzt"/>
		
			<div class='tab'>
				<table width="100%" border="0" class="formlist">
					<tbody>
						<tr style="height: 45px;">
							<td colspan="5">
								���״̬��
								<span style="color:red;">
									<logic:equal value="0" name="jjzgForm" property="shzt">
										δ���
									</logic:equal>
									<logic:equal value="1" name="jjzgForm" property="shzt">
										ͨ��
									</logic:equal>
									<logic:equal value="2" name="jjzgForm" property="shzt">
										��ͨ��
									</logic:equal>
									<logic:equal value="5" name="jjzgForm" property="shzt">
										�����
									</logic:equal>
								</span>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="5">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr style="height: 45px;">
							<th width="15%">ѧ��</th>
							<td width="25%">${jbxx.xh }</td>
							<th width="15%">����</th>
							<td width="25%">${jbxx.xm }</td>
							<td rowspan="3" align="center">
								<img src="xsxx_xsgl.do?method=showPhoto&xh=${jbxx.xh }" width="100" height="120" >
							</td>
					    </tr>
					    <tr style="height: 45px;">
					    	<th>�Ա�</th>
					    	<td>${jbxx.xb }</td>
					    	<th>�꼶</th>
					    	<td>${jbxx.nj }</td>
					    </tr>
					    <tr style="height: 45px;">
					    	<th>ѧԺ</th>
					    	<td>${jbxx.xymc }</td>
					    	<th>�༶</th>
					    	<td>${jbxx.bjmc }</td>
					    </tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="5">
								<span>�ҽ���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr style="height: 45px;">
					    	<th>�ó���Ŀ</th>
					    	<td colspan="4">
					    		${jjzgForm.xkmca } ��${jjzgForm.xkmcb } ��${jjzgForm.xkmcc }
					    	</td>
					    </tr>
						<tr style="height: 45px;">
					    	<th>����꼶</th>
					    	<td>
					    		${jjzgForm.jjnjmc }
					    	</td>
					    	<th>��ϵ�绰</th>
					    	<td colspan="2">
					    		${jjzgForm.lxdh }
					    	</td>
					    </tr>
					    <tr style="height: 45px;">
					    	<th>��ע</th>
					    	<td colspan="4">
					    		${jjzgForm.bz }
					    	</td>
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
							<td colspan="5">
								<table style="width:100%;text-align:center;">
									<thead>
										<tr>
											<td>���</td>
											<td>��˸�λ</td>
											<td>�����</td>
											<td>��˽��</td>
											<td>���ʱ��</td>
											<td>������</td>
										</tr>
									</thead>
									<tbody>
										<logic:present name="shxxList">
											<logic:iterate id="s" name="shxxList" indexId="i">
												<tr>
													<td>${i+1 }</td>
													<td>${s.mc }</td>
													<td>${s.shr }</td>
													<td>
														<logic:equal value="0" name="s" property="shzt">
															δ���
														</logic:equal>
														<logic:equal value="1" name="s" property="shzt">
															ͨ��
														</logic:equal>
														<logic:equal value="2" name="s" property="shzt">
															��ͨ��
														</logic:equal>
														<logic:equal value="3" name="s" property="shzt">
															���˻�
														</logic:equal>
														<logic:equal value="5" name="s" property="shzt">
															�����
														</logic:equal>
													</td>
													<td>${s.shsj }</td>
													<td>${s.shyj }</td>
												</tr>
											</logic:iterate>
										</logic:present>
										<logic:notPresent name="shxxList">
											<tr>
												<td colspan="6">
													����˼�¼								
												</td>
											</tr>
										</logic:notPresent>
									</tbody>
								</table>						
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
  </body>
</html>
