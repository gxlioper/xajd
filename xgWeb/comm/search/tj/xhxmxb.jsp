<%@ page language="java" contentType="text/html; charset=GBK"%>
<!-- ѧ�� -->
<logic:present name="rs" property="xh">				
	<tr>
		<th width="10%">
			ѧ��
		</th>
		<td>
			<input type="text" name="input_xh" id="input_xh" value="${xh }" 
				style="width: 90%" 
				onfocus="displayMsgDiv('input_xh_msg')" 
				onblur="hideMsgDiv('input_xh_msg')"/>
			<logic:notEqual name="userType" value="stu">
			<div id="input_xh_msg" class="msg_prompt" style="left: 80px;top: 55px;">
				<div class="prompcon" style="width: 250px">
					<p>����¼����ѧ�ţ��԰�ǿո�����</p>	
				</div>
			</div>
			</logic:notEqual>
		</td>
	</tr>
</logic:present>
<!-- ѧ�� end-->
				
<!-- ���� -->
<logic:present name="rs" property="xm">		
	<tr>
		<th width="10%">
			����
		</th>
		<td>
			<input type="text" name="input_xm" id="input_xm" value="" 
				style="width: 90%"
				onfocus="displayMsgDiv('input_xm_msg')" 
				onblur="hideMsgDiv('input_xm_msg')"/>					
			<div id="input_xm_msg" class="hide" style="left: 80px;top: 90px;">
				<div class="prompcon" style="width: 250px">
					<p>����¼�����������԰�ǿո�����</p>	
				</div>
			</div>
		</td>
	</tr>
</logic:present>
<!-- ���� end-->
				
<!-- �Ա� -->
<logic:present name="rs" property="xb">		
	<tr>
		<th width="10%">
			�Ա�
		</th>
		<td>
			<logic:iterate name="xbList" id="xb">
				<a style="color: black;cursor:hand;" onclick="clickTj('xb','${xb.en }')" id="tj_xb_${xb.en }" name="tj_xb">
					${xb.cn }
				</a>&nbsp;&nbsp;
			</logic:iterate>	
		</td>
	</tr>
</logic:present>
<!-- �Ա� end-->