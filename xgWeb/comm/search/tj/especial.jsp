<%@ page language="java" contentType="text/html; charset=GBK"%>
<!-- �ļ��� -->
<logic:present name="rs" property="filemc">				
	<tr>
		<th width="10%">
			�ļ���
		</th>
		<td>
			<input type="text" name="input_filemc" id="input_filemc" value="${filemc }" 
				style="width: 90%" 
				onfocus="displayMsgDiv('input_filemc_msg')" 
				onblur="hideMsgDiv('input_filemc_msg')"/>
			<div id="input_filemc_msg" class="msg_prompt" style="left: 100px;top: 55px;">
				<div class="prompcon" style="width: 250px">
					<p>����¼�����ļ������԰�ǿո�����</p>	
				</div>
			</div>
		</td>
	</tr>
</logic:present>
<!-- �ļ��� end-->
				
<!-- �ļ����� -->
<logic:present name="rs" property="filelx">		
	<tr>
		<th width="10%">
			�ļ�����
		</th>
		<td>
			<logic:iterate name="filelxList" id="filelx">
				<a style="color: black;cursor:hand;" onclick="clickTj('filelx','${filelx.dm }')" id="tj_filelx_${filelx.dm }" name="tj_filelx">
					${filelx.mc }
				</a>&nbsp;&nbsp;
			</logic:iterate>	
		</td>
	</tr>
</logic:present>
<!-- �ļ����� end-->