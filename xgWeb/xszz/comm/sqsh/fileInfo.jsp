<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<fieldset>
	<legend>
		附件上传
	</legend>
	<table class="tbstyle" border="0" align="center" style="width: 100%">	
		<logic:iterate name="xmnr" property="nrList" id="zdxx">
			<logic:equal name="zdxx" property="zdlx" value="file">
				<logic:iterate id="nr" name="zdxx" property="zdList" indexId="index">	
					<logic:equal name="nr" property="zdlx" value="file">
						<tr style="height: 23px">						
							<td align="right" width="15%">
								${nr.zdm }：
								<logic:equal name="nr" property="bxlr" value="是">
									<input type="hidden" name="bxlr" value="${nr.zdm }!!@@!!${nr.zd }">
								</logic:equal>
							</td>
							<td align="left" colspan="3">
								<a href="javascript:showFileUpload('${nr.zd }')">点击上传</a>
							</td>				
						</tr>
					</logic:equal>
				</logic:iterate>		
			</logic:equal>
		</logic:iterate>
	</table>
</fieldset>

