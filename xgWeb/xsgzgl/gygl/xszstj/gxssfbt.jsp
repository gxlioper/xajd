<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src="js/check.js"></script>
		
		<script	type="text/javascript">
		jQuery(function(){
			_w_table_rowspan("#gxssfb",1);
			_w_table_rowspan_merge("#gxssfb",16,2);
			_w_table_rowspan_merge("#gxssfb",1,6);
			_w_table_rowspan_merge("#gxssfb",16,7);
			_w_table_rowspan_merge("#gxssfb",1,8);
			_w_table_rowspan_merge("#gxssfb",16,9);
			_w_table_rowspan_merge_sum("#gxssfb",1,10);
			_w_table_rowspan_merge_sum("#gxssfb",16,11);
			_w_table_rowspan_merge_sum("#gxssfb",1,12);
			_w_table_rowspan_merge_sum("#gxssfb",16,13);
			_w_table_rowspan_merge_sum("#gxssfb",1,14);
			_w_table_rowspan_merge_sum("#gxssfb",16,15);
		});
		
		</script>
	</head>
	<body>
		<div style='width:100%;'>
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>各系宿舍分布图</span>
						</th>
					</tr>
				</thead>
			</table>
			<table id="gxssfb" width="100%" align="center" border="2">
				<tr align="center">
					<td rowspan="2">院系</td>
					<td rowspan="2">宿舍<br/>性别</td>
					<td colspan="3" align="center">宿舍楼分布</td>
					<td rowspan="2">入住率</td>
					<td rowspan="2">男/女<br/>入住率</td>
					<td rowspan="2">总<br/>人数</td>
					<td rowspan="2">男/女<br/>总人数</td>
					<td rowspan="2">总<br/>入住人数</td>
					<td rowspan="2">男/女<br/>入住人数</td>
					<td rowspan="2">空床位</td>
					<td rowspan="2">男/女<br/>空床位</td>
					<td rowspan="2">宿舍总数</td>
					<td rowspan="2">男/女<br/>宿舍数</td>
				</tr>
				<tr align="center">
					<td>宿舍楼</td>
					<td>楼层</td>
					<td>宿舍数</td>
				</tr>
				<logic:iterate id="tj" name="gxsstj" indexId="index01">
					<tr align="center">	
						<td>${tj.xymc }</td>
						<td>
						<logic:equal value="男" name="tj" property="xb">
							男
						</logic:equal>
						<logic:notEqual value="男" name="tj" property="xb">
							女
						</logic:notEqual>
						</td>	
						<td>${tj.ldmc }</td>
						<td>${tj.ch }</td>
						<td>${tj.sss}</td>
						<td>${tj.zrzl }%</td>
						<td>${tj.rzl }%</td>
						<td>${tj.xyzrs }</td>
						<td>${tj.zrs }</td>
						<td>${tj.rzrs}</td>
						<td>${tj.rzrs }</td>
						<td>${tj.kcw }</td>
						<td>${tj.kcw }</td>
						<td>${tj.sss }</td>
						<td>${tj.sss }</td>
						<td style="display: none">${tj.xymc}${tj.xb}</td>
					</tr>
				</logic:iterate>
			</table>
		</div>
		<%@ include file="/comm/other/tsxx.jsp"%>
	</body>
</html>

