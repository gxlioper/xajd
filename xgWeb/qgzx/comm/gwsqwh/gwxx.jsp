<%@ page language="java" contentType="text/html; charset=GBK"%>
<thead onclick="hiddenMk('mk_xmxx')">
	<tr style="height:22px" style="cursor:hand">
		<th colspan="4">
			<span>��λ������Ϣ</span>
		</th>
	</tr>
</thead>
<tbody id="mk_xmxx">
	<tr>
		<th width="20%">
			��λ����
		</th>
		<td width="30%">
			<input type="hidden" id="gwdmsbsj" name="gwdmsbsj" value="${rs.gwdmsbsj }"/>
			<input type="hidden" id="sqsyrs" name="sqsyrs" value="${rs.sqsyrs }"/>
			<input type="hidden" id="syknss" name="syknss" value="${rs.syknss }"/>
			<!-- ����ͨ�ñ���(save_) -->
			<input type="hidden" id="save_gwdm" name="save_gwdm" value="${rs.gwdm }"/>
			<input type="hidden" id="save_gwsbsj" name="save_gwsbsj" value="${rs.gwsbsj }"/>
			<input type="hidden" id="save_xn" name="save_xn" value="${rs.xn }"/>
			<input type="hidden" id="save_nd" name="save_nd" value="${rs.nd }"/>
			<input type="hidden" id="save_xq" name="save_xq" value="${rs.xueqi }"/>
			
			${rs.gwdm }
			<input type="text" id="realFocus" readonly="readonly"
				style="dipalay:none;color:white;text-align:left;ime-mode:disabled;width:10%;border:0;"/>
			<script language="javascript" defer="defer">
			
			function setFocus(){
				$('realFocus').focus();
			}
				setTimeout("setFocus()",100);
			</script>
		</td>
			
		<th width="20%">
			��λ����
		</th>
		<td width="30%">
			${rs.gwxzmc }
		</td>
	</tr>
	<tr>
		<th>
			���˵�λ
		</th>
		<td>
			${rs.yrdwmc }
		</td>
		
		<th>
			ѧ��
		</th>
		<td>
			${rs.xn }
		</td>
	</tr>
	<tr>
		<th>
			ѧ��
		</th>
		<td>
			${rs.xueqimc }
		</td>
		
		<th>
			���
		</th>
		<td>
			${rs.nd }
		</td>
	</tr>
	<tr>
		<th>
			������ʼ����
		</th>
		<td>
			${rs.gzksrq }
		</td>
		<th>
			������������
		</th>
		<td>
			${rs.gzjsrq }
		</td>
	</tr>
	<tr>
		<th>
			��������
		</th>
		<td>
			${rs.xyrs }
		</td>
		<th>
			��Ҫ����������
		</th>
		<td>
			${rs.syknss }
		</td>
	</tr>
	<tr>
		<th>
			����ʱ��
		</th>
		<td>
			${rs.gzsj }
		</td>
		<th>
			��ϵ�绰
		</th>
		<td>
			${rs.yrdwlxdh }
		</td>
	</tr>
	<tr>
		<th>
			��������
		</th>
		<td colspan="3">
			<textarea rows="3" cols="5" style="width:90%" disabled="disabled">${rs.gznr }</textarea>
		</td>
	</tr>
	<tr>
		<th>
			��λҪ��
		</th>
		<td colspan="3">
			<textarea rows="3" cols="5" style="width:90%" disabled="disabled">${rs.gwtsyq }</textarea>
		</td>
	</tr>
</tbody>
