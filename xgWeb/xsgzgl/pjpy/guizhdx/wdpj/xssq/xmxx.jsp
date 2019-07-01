<%@ page language="java" contentType="text/html; charset=GBK"%>
<thead onclick="hiddenMk('mk_xmxx')">
	<tr style="height:22px" style="cursor:hand">
		<th colspan="4">
			<span>项目基本信息</span>
		</th>
	</tr>
</thead>
<tbody id="mk_xmxx">
	<tr>
		<th width="20%">
			项目名称
		</th>
		<td width="30%">
			${wdpjXssqInfo.xmInfo.xmmc }
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
			英文名称
		</th>
		<td width="30%">
			${wdpjXssqInfo.xmInfo.ywmc }
		</td>
	</tr>
	<tr>
		<th>
			评奖学年
		</th>
		<td>
			${pjxn }
		</td>
		
		<th>
			评奖学期
		</th>
		<td>
			${pjxqmc }
		</td>
	</tr>
	<tr>
		<th>
			评奖年度
		</th>
		<td>
			${pjnd }
		</td>
		
		<th>
			项目性质
		</th>
		<td>
			${wdpjXssqInfo.xmInfo.xmxz }
		</td>
	</tr>
	<tr>
		<th>
			项目范围
		</th>
		<td>
			${wdpjXssqInfo.xmInfo.xmfw }
		</td>
		
		<th>
			项目类型
		</th>
		<td>
			${wdpjXssqInfo.xmInfo.xmlxxx }
		</td>	
	</tr>
	<tr>
		<th>
			项目金额
		</th>
		<td>
			${wdpjXssqInfo.xmInfo.xmje }
		</td>
		<th>
		</th>
		<td>
		</td>
	</tr>
	<tr>
		<th>
			项目说明
		</th>
		<td colspan="3">
			<textarea rows="3" cols="5" style="width:90%" disabled="disabled">${wdpjXssqInfo.xmInfo.xmsm }</textarea>
		</td>
	</tr>
</tbody>
