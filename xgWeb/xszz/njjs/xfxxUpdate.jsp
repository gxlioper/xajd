<%@ page language="java" contentType="text/html; charset=GBK"%>
<script language="javascript">
function yzJe(obj,num){
	var yjje_id="yjje"+num;
	var jmje_id="jmje"+num;
	
	if($(yjje_id) && $(jmje_id)){
		var yjje = $(yjje_id).value;
		var jmje = $(jmje_id).value;
		
		if(jmje !=""){
			if(yjje != ""){
				if(parseInt(jmje) > parseInt(yjje)){
					alert("减免金额大于应缴金额，请确认！");
					obj.focus();
				}
			}else{
				alert("该项目无需缴费，所以无需减免，请确认！");
				obj.focus();
			}
		}
	}
}
</script>
<%@ include file="/syscommon/pagehead.ini"%>
<tr>
	<table class="tbstyle" width="90%" align="center">
		<thead>
			<tr>
				<td align="center" colspan="3">
					收费情况
				</td>
			</tr>
		</thead>
		<!-- 无收费信息 -->
		<logic:empty name="sfList">
			<tr>
				<td align="center" colspan="3">
					无收费信息
				</td>
			</tr>
		</logic:empty>
		<!-- 有收费信息 -->
		<logic:notEmpty name="sfList">
			<tr>
				<td>
					收费项目
				</td>
				<td>
					费用标准
				</td>
				<td>
					减免金额
				</td>
			</tr>
			<logic:iterate name="sfList" id="sf" indexId="num">
				<tr>
					<td>
						${sf.xfsfxm }
						<input type="hidden" name="xfsfxm" value="${sf.xfsfxm }">
					</td>
					<td>
						${sf.xfyjje }
						<input type="hidden" name="xfyjje" id="yjje${num }"  value="${sf.xfyjje }">
					</td>
					<td>
						<input type="hidden" name="xfsjje" value="${sf.xfsjje }">
						<input type="hidden" name="xfsfqf" value="${sf.xfsfqf }">
						<input type="text" name="xfjmje" value="${sf.xfjmje }" onblur="yzJe(this,${num })"
						onkeypress="return onlyNum(this,5)" maxlength="5" 
						style="ime-mode:disabled" id="jmje${num }">
					</td>
				</tr>
			</logic:iterate>
		</logic:notEmpty>
	</table>
</tr>

