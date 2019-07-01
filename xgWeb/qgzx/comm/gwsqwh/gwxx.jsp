<%@ page language="java" contentType="text/html; charset=GBK"%>
<thead onclick="hiddenMk('mk_xmxx')">
	<tr style="height:22px" style="cursor:hand">
		<th colspan="4">
			<span>岗位基本信息</span>
		</th>
	</tr>
</thead>
<tbody id="mk_xmxx">
	<tr>
		<th width="20%">
			岗位名称
		</th>
		<td width="30%">
			<input type="hidden" id="gwdmsbsj" name="gwdmsbsj" value="${rs.gwdmsbsj }"/>
			<input type="hidden" id="sqsyrs" name="sqsyrs" value="${rs.sqsyrs }"/>
			<input type="hidden" id="syknss" name="syknss" value="${rs.syknss }"/>
			<!-- 用于通用保存(save_) -->
			<input type="hidden" id="save_gwdm" name="save_gwdm" value="${rs.gwdm }"/>
			<input type="hidden" id="save_gwsbsj" name="save_gwsbsj" value="${rs.gwsbsj }"/>
			<input type="hidden" id="save_xn" name="save_xn" value="${rs.xn }"/>
			<input type="hidden" id="save_nd" name="save_nd" value="${rs.nd }"/>
			<input type="hidden" id="save_xq" name="save_xq" value="${rs.xueqi }"/>
			
			${rs.gwdm }
			<input type="text" id="realFocus" readonly="readonly"
				style="dipalay:none;color:white;text-align:left;ime-mode:disabled;width:10%;border:0;"/>
			<script language="javascript" defer="defer">
			
			function setFocus(){
				$('realFocus').focus();
			}
				setTimeout("setFocus()",100);
			</script>
		</td>
			
		<th width="20%">
			岗位性质
		</th>
		<td width="30%">
			${rs.gwxzmc }
		</td>
	</tr>
	<tr>
		<th>
			用人单位
		</th>
		<td>
			${rs.yrdwmc }
		</td>
		
		<th>
			学年
		</th>
		<td>
			${rs.xn }
		</td>
	</tr>
	<tr>
		<th>
			学期
		</th>
		<td>
			${rs.xueqimc }
		</td>
		
		<th>
			年度
		</th>
		<td>
			${rs.nd }
		</td>
	</tr>
	<tr>
		<th>
			工作开始日期
		</th>
		<td>
			${rs.gzksrq }
		</td>
		<th>
			工作结束日期
		</th>
		<td>
			${rs.gzjsrq }
		</td>
	</tr>
	<tr>
		<th>
			需求人数
		</th>
		<td>
			${rs.xyrs }
		</td>
		<th>
			需要困难生人数
		</th>
		<td>
			${rs.syknss }
		</td>
	</tr>
	<tr>
		<th>
			工作时间
		</th>
		<td>
			${rs.gzsj }
		</td>
		<th>
			联系电话
		</th>
		<td>
			${rs.yrdwlxdh }
		</td>
	</tr>
	<tr>
		<th>
			工作内容
		</th>
		<td colspan="3">
			<textarea rows="3" cols="5" style="width:90%" disabled="disabled">${rs.gznr }</textarea>
		</td>
	</tr>
	<tr>
		<th>
			岗位要求
		</th>
		<td colspan="3">
			<textarea rows="3" cols="5" style="width:90%" disabled="disabled">${rs.gwtsyq }</textarea>
		</td>
	</tr>
</tbody>
