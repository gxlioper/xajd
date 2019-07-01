<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<table class="formlist" border="0" align="center" style="width: 100%">
	<thead>
		<tr>
			<th colspan="4">
				<span>兼得设置</span>
			</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<th width="15%">不可兼得项目</th>
			<td colspan="3">
				<span onmousemove="showTsDiv('jdts')" onmouseout="hiddTsDiv('jdts')">
					<font color="blue">提示：</font>	
					<font color="blue" id="jdts" style="display : none">在一个申请周期内同一个学生不可以同时获得当前项目和以下所勾选的项目。</font>
				</span>
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<logic:iterate id="x" name="xmList" indexId="i">
					<%
						if(i%5==0 && i != 0){
					 %>
					</td></tr><tr><td colspan="4">
					 <%} %>
					<input type="checkbox" value="${x.xmdm }" name="bkjdxm"/>${x.xmmc }&nbsp;&nbsp;
				</logic:iterate>
			</td>
		</tr>
	</tbody>
</table>

<script type="text/javascript">
<!--
	//不可兼得项目打勾操作
	jQuery(function(){
		var xmdm = jQuery('input[name=xmdm]').val();
		if (xmdm != ''){
			jQuery.post('xszzAjax.do?method=getBkjdxm',{xmdm:xmdm},function(data){
				jQuery.each(data,function(i,n){
					jQuery('input[name=bkjdxm][value='+n+']').attr('checked',true);
				});
			},'json');
		}
	});	
//-->
</script>
