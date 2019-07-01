<%@ page language="java" contentType="text/html; charset=GBK"%>
<!-- 文件名 -->
<logic:present name="rs" property="filemc">				
	<tr>
		<th width="10%">
			文件名
		</th>
		<td>
			<input type="text" name="input_filemc" id="input_filemc" value="${filemc }" 
				style="width: 90%" 
				onfocus="displayMsgDiv('input_filemc_msg')" 
				onblur="hideMsgDiv('input_filemc_msg')"/>
			<div id="input_filemc_msg" class="msg_prompt" style="left: 100px;top: 55px;">
				<div class="prompcon" style="width: 250px">
					<p>可以录入多个文件名，以半角空格区分</p>	
				</div>
			</div>
		</td>
	</tr>
</logic:present>
<!-- 文件名 end-->
				
<!-- 文件类型 -->
<logic:present name="rs" property="filelx">		
	<tr>
		<th width="10%">
			文件类型
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
<!-- 文件类型 end-->