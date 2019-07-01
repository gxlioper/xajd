<%@ page language="java" contentType="text/html; charset=GBK"%>
<script type="text/javascript" src="js/uicomm.js"></script>
<logic:present name="org.apache.struts.action.ERROR">
	<script type="text/javascript">
	 jQuery(function() {
		tipsWindown("系统提示","id:errorMessage","340","120","true","","true","id");
	 });
 	</script>
</logic:present>
<logic:present name="org.apache.struts.action.ERROR">
	<div id="errorMessage" style="display:none">
		<div class="open_prompt">
			<table width="100%" border="0" class="table01">
				<tr>
					<td width="109">
						<div class="img img_prompt"></div>
					</td>
					<th>
						<p>
							<html:errors />
						</p>
					</th>
				</tr>
				<tr>
					<td colspan="2" align="center" class="btn01">
						<input type="button" class="button" value="确 定" onclick="closeWindown();return false;"/>
					</td>
				</tr>
			</table>
		</div>
	</div>
</logic:present>


