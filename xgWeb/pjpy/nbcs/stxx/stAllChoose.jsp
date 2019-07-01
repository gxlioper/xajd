<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<p>
	<input  value="+" onclick="trAdd('all_flag','add')" style="width: 20px"/>
	<input  value="-" onclick="trDel('all_flag')" style="width: 20px"/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 生成&nbsp;&nbsp;
	<input type="text" name="numAdd" id="all_numAdd" style="width: 20px" onblur="trAdd('all_flag','madd')">
	&nbsp;行 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 清除&nbsp;&nbsp;
	<input type="text" name="numDel" id="all_numDel" style="width: 20px" onblur="trDelAll('all_flag')">
	&nbsp;行&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<br><font color="red">注：最多生成26个答案</font>
</p>				
<table class="tbstyle" align="center" width="100%" id="tTb">
	<tr>
		<td>
			<div class="mid_box">
				<table align="center" style="width: 100%" id="rsT" class="tbstyle">
					<thead style="height: 23px">
						<tr>
							<td align="center" nowrap="nowrap" style='width:5%'>
								选项
							</td>
							<td align="center" nowrap="nowrap" style=''>
								答案(限录入500字)
							</td>
							<td align="center" nowrap="nowrap" style='width:5%'>
								标准答案
							</td>
						</tr>
					</thead>		
					<tbody width="100%" class="tbstyle" id="all_flag">
					</tbody>	
				</table>
			</div>
		</td>
	</tr>
</table>