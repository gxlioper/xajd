<%@ page language="java" contentType="text/html; charset=GBK"%>
<thead onclick="hiddenMk('mk_xmxx')">
	<tr style="height:22px" style="cursor:hand">
		<th colspan="4">
			<span>��Ŀ������Ϣ</span>
		</th>
	</tr>
</thead>
<tbody id="mk_xmxx">
	<tr>
		<th width="20%">
			��Ŀ����
		</th>
		<td width="30%">
			${wdpjXssqInfo.xmInfo.xmmc }
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
			Ӣ������
		</th>
		<td width="30%">
			${wdpjXssqInfo.xmInfo.ywmc }
		</td>
	</tr>
	<tr>
		<th>
			����ѧ��
		</th>
		<td>
			${pjxn }
		</td>
		
		<th>
			����ѧ��
		</th>
		<td>
			${pjxqmc }
		</td>
	</tr>
	<tr>
		<th>
			�������
		</th>
		<td>
			${pjnd }
		</td>
		
		<th>
			��Ŀ����
		</th>
		<td>
			${wdpjXssqInfo.xmInfo.xmxz }
		</td>
	</tr>
	<tr>
		<th>
			��Ŀ��Χ
		</th>
		<td>
			${wdpjXssqInfo.xmInfo.xmfw }
		</td>
		
		<th>
			��Ŀ����
		</th>
		<td>
			${wdpjXssqInfo.xmInfo.xmlxxx }
		</td>	
	</tr>
	<tr>
		<th>
			��Ŀ���
		</th>
		<td>
			${wdpjXssqInfo.xmInfo.xmje }
		</td>
		<th>
		</th>
		<td>
		</td>
	</tr>
	<tr>
		<th>
			��Ŀ˵��
		</th>
		<td colspan="3">
			<textarea rows="3" cols="5" style="width:90%" disabled="disabled">${wdpjXssqInfo.xmInfo.xmsm }</textarea>
		</td>
	</tr>
</tbody>
