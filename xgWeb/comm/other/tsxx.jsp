<%@ page language="java" contentType="text/html; charset=GBK"%>
<!-- 等待ing -->
<div id="divWaiting" style="display: none;  left: 25%; right: 25%; position: absolute;
     text-align: center; width: 50%; height: 50px;left: expression((this.offsetParent.clientWidth/2)-(this.clientWidth/2)+this.offsetParent.scrollLeft);
     top: expression((this.offsetParent.clientHeight/2)-(this.clientHeight/2)+this.offsetParent.scrollTop);">
   <img src="<%=stylePath%>images/ico_loading.gif"/>
   <p id="p_tsxx"><B>数据处理中，请稍后。。。</B></p>
</div>

<div id="divDisable" style="display: none;width:expression(document.body.offsetWidth);height:expression(document.body.offsetHeight); z-index: 2000; position: absolute;left: 0px; top: 0px;filter:alpha(opacity=50); background-color:White"></div>

<input type="hidden" name="showMessage" id="showMessage" value="${showMessage }"/>
<input type="hidden" id="refreshParent" value="yes" />

<logic:present name="message">
	<script defer="defer">
		if($("message") && $("message").value != ""){
			
			alertInfo($("message").value);
			
			$("message").value = "";
			if($("doType")){
				$("doType").value = "";
			}
			if($("importResult")){
				$("importResult").value = "";
			}
		}
	</script>
</logic:present>

<logic:present name="showMessage">
	<script defer="defer">
		if($("showMessage") && $("showMessage").value != ""){
			alertInfo($("showMessage").value);
		}
	</script>
</logic:present>

<logic:present name="org.apache.struts.action.ERROR">
	<div style="display:none;" id="errorDiv">
		<table width='100%' class="formlist">
			<tbody>
				<tr>
					<td>
						<html:errors/>
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td>
						<div class="btn">
							<button type="button" onclick="hiddenMessage(true,true);return false;">
							确定
							</button>
						</div>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
	<script defer="defer">
		viewTempDiv('友情提示','errorDiv',300,110);
	</script>
</logic:present>

<!-- 等待Div-->
<div id="waitDiv" style="display: none">
	<div id="div_tsxx" align="center">
		</br></br></br></br></br></br>
		<img src="<%=stylePath%>images/ico_loading.gif"/>
		</br>
		<img src="<%=stylePath%>images/loadingli.gif"/>
		<p id="p_tsxx"><B>数据处理中，请稍后。。。</B></p>
	</div>
</div>
<!-- 等待Div end-->

