<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
<body>
	<html:form action="/pjpyLsxy" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>	
			<div class="title_img" id="title_m">
				��ǰ����λ��: ${title }
			</div>
		</div>
		<table class="tbstyle" width="100%">
			<thead>
				<tr style="height:23px">
					<td colspan="9" align="center">
						ѧ���ɼ���ϸ��Ϣ
					</td>
				</tr>
				<tr style="height:23px">
					<td align="center">
						ѧ��
					</td>
					<td align="center">
						ѧ��
					</td>
					<td align="center">
						ѧ��
					</td>
					<td align="center">
						����
					</td>
					<td align="center">
						�༶
					</td>
					<td align="center">
						�꼶	
					</td>
					<td align="center">
						�γ�����
					</td>
					<td align="center">
						�ɼ�
					</td>
					<td align="center">
						��ѧʱ
					</td>
				</tr>	
			</thead>
			<logic:iterate name="rs" id="s">
				<tr onclick="rowOnClick(this)"
					style="cursor:hand;">					
					<logic:iterate id="v" name="s">
						<td align=center nowrap="nowrap">
							<bean:write name="v" />
						</td>
					</logic:iterate>
				</tr>
			</logic:iterate>
		</table>
		<div class="buttontool" align="center">			
			<button class="button2" id="btn_close" onclick="Close();return false;" style="width:80px"
				id="buttonClose">
				�� ��
			</button>
		</div>
	</html:form>
</body>
