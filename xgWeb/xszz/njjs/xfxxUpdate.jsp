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
					alert("���������Ӧ�ɽ���ȷ�ϣ�");
					obj.focus();
				}
			}else{
				alert("����Ŀ����ɷѣ�����������⣬��ȷ�ϣ�");
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
					�շ����
				</td>
			</tr>
		</thead>
		<!-- ���շ���Ϣ -->
		<logic:empty name="sfList">
			<tr>
				<td align="center" colspan="3">
					���շ���Ϣ
				</td>
			</tr>
		</logic:empty>
		<!-- ���շ���Ϣ -->
		<logic:notEmpty name="sfList">
			<tr>
				<td>
					�շ���Ŀ
				</td>
				<td>
					���ñ�׼
				</td>
				<td>
					������
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

