<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
<body>
	<html:form action="/pjpyahjgwh.do" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				<bean:message bundle="pjpyahjg" key="pjpy_ahjg_txsqb" />
			</div>
		</div>
		<table class="tbstyle" width="100%">
			<thead>
				<tr style="height:20px">
					<logic:equal name="xxdm" value="10878">
					<td colspan="3" align="center">
						${title }
					</td>
					</logic:equal>
					<logic:notEqual name="xxdm" value="10878">
					<td colspan="2" align="center">
						${title }
					</td>
					</logic:notEqual>
				</tr>
			</thead>
			<tr style="height: 20px">
				<td align="center">
					 <b>�γ�����	</b>
				</td>
				<logic:equal name="xxdm" value="10878">
				<td align="center">
					<B>ѧ��</B>
				</td>
				<td align="center">
					<B>�ɼ�</B>
				</td>
				</logic:equal>
				<logic:notEqual name="xxdm" value="10878">
				<td align="center">
					<B>�ɼ�</B>
				</td>
				</logic:notEqual>
			</tr>
			<logic:notEmpty name="rs">
				<logic:iterate name="rs" id="s">
					<tr style="cursor:hand;">
						<logic:iterate id="v" name="s" >
							<td align="center">
								<bean:write name="v"/>
								</td>
						</logic:iterate>
					</tr>
				</logic:iterate>
			</logic:notEmpty>
			<logic:empty name="rs">
				<tr>
					<td align="center" colspan="2">
						δ�ҵ��κμ�¼��
					</td>
				</tr>
			</logic:empty>
		</table>
		<br/>
		<div id="tmp" align="center">
		<button id="btn_close" class="button2" 
		style="width:80px" onclick="window.close();return false;">
			�ر�
		</button>
		</div>
	</html:form>
</body>