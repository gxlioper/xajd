<%@ page language="java" contentType="text/html; charset=GBK"%>
<script language="javascript">

</script>

<div><b>ȷ�������ֶε��������ݣ�ȷ�Ϻ�����ɡ����桱����</b></div>
<div id="div_step2_nr" style="width:100%;height:450px;overflow-x:hidden;overflow-y:auto;">
<table style="width:100%;" id="tb_sznr">
	<tr>
		<td>
			�ֶ���
		</td>
		<td>
			ҳ����ʾ
		</td>
		<td>
			ѧ��Ϊ׼
		</td>
		<td>
			¼������
		</td>
		<td>
			Ϊ������
		</td>
		<td>
			¼����ʽ
		</td>
		<td>
			������Դ
		</td>
		<td>
			�Ƿ�����
		</td>
	</tr>
	<logic:iterate name="kczzdList" id="rs">
	<tr>
		<td>
			${rs.zdm }
		</td>
		<td>
			${rs.xsmc }
		</td>
		<td>
			${rs.xgwz }
		</td>
		<td>
			${rs.lrxz }
		</td>
		<td>
			${rs.wkxz }
		</td>
		<td>
			${rs.lrxs }
		</td>
		<td>
			<logic:notEmpty name="rs" property="lybm">
				${rs.lybm }
			</logic:notEmpty>
			<logic:empty name="rs" property="lybm">
				������Դ��
			</logic:empty>
		</td>
		<td>
			${rs.sfqy }
		</td>
	</tr>
	</logic:iterate>
	<!-- ������ -->
	<logic:notEmpty name="rowNum">
	<%int rowNum = Integer.parseInt(request.getAttribute("rowNum").toString());%>
	<%for(int i=0;i<rowNum;i++){ %>
		<tr>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
		</tr>
		<%}%>
	</logic:notEmpty>
</table>
</div>
	

