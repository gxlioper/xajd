<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsgzgl/xszz/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/syddk/js/dksq.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybd.js"></script>
		
		<script type="text/javascript">
			jQuery(function(){		
				//��������ѡ��
				loadViewMkxxSelectOptions();
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${zxdkSydksqshForm.id}&tt="+new Date().getTime());
			});
		</script>
	</head>
	<body>
		<html:form action="/zxdk_sydk" method="post" styleId="zxdkSydksqshForm">
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:470px;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��������</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>����ѧ��</th>
							<td>
								${zxdkSydksqshForm.xn }
							</td>
							<th>�����ܽ�Ԫ��</th>
							<td>
								${zxdkSydksqshForm.dkje }
							</td>
						</tr>
						<tr>
							<th>ס�޷�Ӧ������Ԫ��</th>
							<td>
								${zxdkSydksqshForm.zsysf }
							</td>
							<th>ѧ��Ӧ������Ԫ��</th>
							<td>
								${zxdkSydksqshForm.xfysf }
							</td>
						</tr>
						<tr>
							<th>��������</th>
							<td>
								${zxdkSydksqshForm.yhmc }
							</td>
							<th>�������ޣ��£�</th>
							<td>
								${zxdkSydksqshForm.dkqx }
							</td>
						</tr>
						<tr>
							<th>��������</th>
							<td colspan="3">
								${zxdkSydksqshForm.sqly }
							</td>
						</tr>
						<tr>		
							</td>
							<th>��ͬ���</th>
							<td>
								${zxdkSydksqshForm.htbh}
							</td>
							<th>���ʼʱ��</th>
							<td>
								${zxdkSydksqshForm.dkkssj}
							</td>
						</tr>
						<tr>
							<th>��ִ������</th>
							<td colspan="3">
								${zxdkSydksqshForm.hzjym}
							</td>
						</tr>
						<!-- 
						<tr>
							<td colspan="4">
								<table style="width:100%;text-align:center;" >
									<thead>
										<tr>
											<td align="center">ѧ��</td>
											<td align="center">ѧ�ѣ�Ԫ��</td>
											<td align="center">ס�޷ѣ�Ԫ��</td>
											<td align="center">����ѣ�Ԫ��</td>
										</tr>
										<logic:present name="dkxxList">
											<logic:iterate id="d" name="dkxxList">
											 <tbody>
												<tr>
													<td>
														${d.xn }
													</td>
													<td>
														${d.xf }
													</td>
													<td>
														${d.zsf }
													</td>
													<td>
														${d.shf }
													</td>
												</tr>
											  </tbody>
											</logic:iterate>
										</logic:present>
									</thead>
								</table>
							</td>
						</tr>
						 -->
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4" id="shlccx">
							
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="iFClose();">
										�ر�
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