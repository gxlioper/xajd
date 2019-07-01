<%@ page language="java" contentType="text/html; charset=GBK"%>
<p>
	<input type="button" value="+" onclick="trAdd('one_flag','add')" class="btn_01"/>
	<input type="button" value="-" onclick="trDel('one_flag')" class="btn_01"/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 生成&nbsp;&nbsp;
	<input type="text" name="numAdd" id="one_numAdd" style="width: 20px" onblur="trAdd('one_flag','madd')">
	&nbsp;行 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 清除&nbsp;&nbsp;
	<input type="text" name="numDel" id="one_numDel" style="width: 20px" onblur="trDelAll('one_flag')">
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
					<tbody width="100%" class="tbstyle" id="one_flag">
					</tbody>	
				</table>
			</div>
		</td>
	</tr>
</table>