<%@ page language="java" contentType="text/html; charset=GBK"%>
<table class="tbstyle" align="center" width="100%" id="queTb">
	<thead style="height: 23px">
		<tr>
			<td align="center">
				参考答案(限录入500字,也可以为空)						
			</td>
		</tr>
	</thead>
	<tr>
		<td align="center">
			<input type="hidden" name="dabh" value="answer">
			<input type="hidden" name="bzda" value="">
			<textarea rows="5" style="width:90%" cols="" id="" name="damc" onblur="chLeng(this,500)"></textarea>
		</td>
	</tr>
</table>