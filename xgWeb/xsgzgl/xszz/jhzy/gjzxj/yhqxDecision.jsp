<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//�����û�����
		function saveUserType(){		
			var userType=jQuery("[name=yhqx]:checked").eq(0).val();				
			var url = "jhzy_gjzxj.do?method=zxjshSearch&userType="+userType;
			refreshForm(url);
		}
		</script>
	</head>
	<body onload="" >
		<html:form action="/general_pjpy" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4"> 
								<span>����渨��Ա�Ͱ�����������λ����ѡ���Ժθ�λ���н���ȥ����˹���</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr >
							<td>
								<input type="radio" name="yhqx" id="bzr" value="bzr" checked="checked"/>
								�����Ρ��ø�λ��ѧ�������¼���е�һ����ˡ�
							</td>
						</tr>
						<tr >
							<td>
								<input type="radio" name="yhqx" id="fdy" value="fdy"/>
								����Ա���ø�λ��ѧ�������¼���еڶ�����ˡ�
							</td>
						</tr>
						<tr >
							<td>
								ע���л���һ��֮�󣬾ͽ��̶�ס����ѡ�����ݣ�����������һ��ݵĹ�������ѡ��ϵͳLOGO�Ե�����л�
							</td>
						</tr>
					</tbody>		
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="saveUserType()" id="buttonSave">
										ȷ��
									</button>			           
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>