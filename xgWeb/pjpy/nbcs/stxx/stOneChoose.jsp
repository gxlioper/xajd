<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<p>
	<input  value="+" onclick="trAdd('one_flag','add')" style="width: 20px"/>
	<input  value="-" onclick="trDel('one_flag')" style="width: 20px"/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ����&nbsp;&nbsp;
	<input type="text" name="numAdd" id="one_numAdd" style="width: 20px" onblur="trAdd('one_flag','madd')">
	&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ���&nbsp;&nbsp;
	<input type="text" name="numDel" id="one_numDel" style="width: 20px" onblur="trDelAll('one_flag')">
	&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<br><font color="red">ע���������26����</font>
</p>				
<table class="tbstyle" align="center" width="100%" id="tTb">
	<tr>
		<td>
			<div class="mid_box">
				<table align="center" style="width: 100%" id="rsT" class="tbstyle">
					<thead style="height: 23px">
						<tr>
							<td align="center" nowrap="nowrap" style='width:5%'>
								ѡ��
							</td>
							<td align="center" nowrap="nowrap" style=''>
								��(��¼��500��)
							</td>
							<td align="center" nowrap="nowrap" style='width:5%'>
								��׼��
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