<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
<body>
	<div class="title">
		<div class="title_img" id="title_m"> 
			ǰ����λ�ã��ڹ���ѧ - �ڹ���ѧ�������� - �����������	
		</div>
	</div>
		<html:form action="/gzdxQgzx.do" method="post">
			<table class="tbstyle" width="100%">
				<tr>
					<td align="center" width="16%">
							ѧ�ţ�
						</td>
						<td align="left" width="34%">
							${rs.xh}						
						</td>
					<td width="16%">
						<div align="center">
							������
						</div>
					</td>
					<td width="34%">
						${rs.xm}
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							�Ա�
						</div>
					</td>
					<td>
						${rs.xb}
					</td>
					<td>
						<div align="center">
							���֤�ţ�
						</div>
					</td>
					<td>
						${rs.sfzh}
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							���壺
						</div>
					</td>
					<td>
						${rs.mzmc}
					</td>
					<td>
						<div align="center">
							������ò��
						</div>
					</td>
					<td>
						${rs.zzmmmc}
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							�꼶��
						</div>
					</td>
					<td>
						${rs.nj}
					</td>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />���ƣ�
						</div>
					</td>
					<td>
						${rs.xymc}
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							רҵ���ƣ�
						</div>
					</td>
					<td>
						${rs.zymc}
					</td>
					<td>
						<div align="center">
							�༶���ƣ�
						</div>
					</td>
					<td>
						${rs.bjmc}
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							ѧ�꣺
						</div>
					</td>
					<td colspan="3">
						${rs.xn}
					</td>					
				</tr>
				<tr>
					<td>
						<div align="center">
							��ȣ�
						</div>
					</td>
					<td colspan="3">
						${rs.nd}
					</td>					
				</tr>				
				<tr>
					<td>
						<div align="center">
							ѧ�ڣ�
						</div>
					</td>
					<td colspan="3">
						${rs.xqmc}
					</td>
				</tr>						
				
				<tr>
					<td>
						<div align="center">
							����Ա��ˣ�
						</div>
					</td>
					<td colspan="3">
						${rs.save_fdysh}
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							����Ա��������
						</div>
					</td>
					<td colspan="3">
						${rs.save_fdyshyj}
					</td>
				</tr>

				<tr>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />��ˣ�
						</div>
					</td>
					<td colspan="3">
						${rs.save_xysh}
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />��������
						</div>
					</td>
					<td colspan="3">
						${rs.save_xyshyj}
					</td>
				</tr>

				<tr>
					<td>
						<div align="center">
							ѧУ��ˣ�
						</div>
					</td>
					<td colspan="3">
						${rs.save_xxsh}
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							ѧУ��������
						</div>
					</td>
					<td colspan="3">
						${rs.save_xxshyj}
					</td>
				</tr>	
			</table>
			<div class="buttontool" id="btn" style="position: absolute;width:100%">
				<logic:notEqual value="view" name="type">
				<button type="button" class="button2" id="buttonSave"
					onClick="yz();">
					��&nbsp;&nbsp;&nbsp;&nbsp;��
				</button>
				&nbsp;&nbsp;
				</logic:notEqual>
				<button type="button" class="button2"
					onClick="Close()">
					��&nbsp;&nbsp;&nbsp;&nbsp;��
				</button>
			</div>
		</html:form>
	</body>	
