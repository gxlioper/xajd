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
			${xmxx.xmmc }
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
			${xmxx.ywmc }
		</td>
	</tr>
	<tr>
		<th>
			����ѧ��
		</th>
		<td>
			${pjxtsz.pjxn }
		</td>
		
		<th>
			����ѧ��
		</th>
		<td>
			${pjxtsz.pjxqmc }
		</td>
	</tr>
	<tr>
		<th>
			�������
		</th>
		<td>
			${pjxtsz.pjnd }
		</td>
		
		<th>
			��Ŀ����
		</th>
		<td>
			<html:select property="xmxz"  disabled="true" value="${xmxx.xmxz}">
				<html:options collection="xmxzList" property="dm" labelProperty="mc"/>
			</html:select>
		</td>
	</tr>
	<tr>
		<th>
			��Ŀ��Χ
		</th>
		<td>
			<html:select property="xmfw" value="${xmxx.xmfw }" disabled="true">
				<html:options collection="xmfwList" property="dm" labelProperty="mc"/>
			</html:select>
		</td>
		
		<th>
			��Ŀ����
		</th>
		<td>
			<logic:equal name="xmxx" property="xmlx" value="01">
				��ѧ��
			</logic:equal>
			<logic:equal name="xmxx" property="xmlx" value="02">
				�����ƺ�
			</logic:equal>
		</td>	
	</tr>
	<tr>
		<th>
			��Ŀ���
		</th>
		<td>
			${xmxx.xmje }
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
			<textarea rows="3" cols="5" style="width:90%" disabled="disabled">${xmxx.xmsm }</textarea>
		</td>
	</tr>
</tbody>
