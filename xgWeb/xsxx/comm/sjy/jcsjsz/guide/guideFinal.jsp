<%@ page language="java" contentType="text/html; charset=GBK"%>
<script language="javascript">

</script>

<div><b>确认如下字段的设置内容，确认后，请完成“保存”操作</b></div>
<div id="div_step2_nr" style="width:100%;height:450px;overflow-x:hidden;overflow-y:auto;">
<table style="width:100%;" id="tb_sznr">
	<tr>
		<td>
			字段名
		</td>
		<td>
			页面显示
		</td>
		<td>
			学工为准
		</td>
		<td>
			录入限制
		</td>
		<td>
			为空限制
		</td>
		<td>
			录入形式
		</td>
		<td>
			数据来源
		</td>
		<td>
			是否启用
		</td>
	</tr>
	<logic:iterate name="kczzdList" id="rs">
	<tr>
		<td>
			${rs.zdm }
		</td>
		<td>
			${rs.xsmc }
		</td>
		<td>
			${rs.xgwz }
		</td>
		<td>
			${rs.lrxz }
		</td>
		<td>
			${rs.wkxz }
		</td>
		<td>
			${rs.lrxs }
		</td>
		<td>
			<logic:notEmpty name="rs" property="lybm">
				${rs.lybm }
			</logic:notEmpty>
			<logic:empty name="rs" property="lybm">
				无需来源表
			</logic:empty>
		</td>
		<td>
			${rs.sfqy }
		</td>
	</tr>
	</logic:iterate>
	<!-- 补行数 -->
	<logic:notEmpty name="rowNum">
	<%int rowNum = Integer.parseInt(request.getAttribute("rowNum").toString());%>
	<%for(int i=0;i<rowNum;i++){ %>
		<tr>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
		</tr>
		<%}%>
	</logic:notEmpty>
</table>
</div>
	

