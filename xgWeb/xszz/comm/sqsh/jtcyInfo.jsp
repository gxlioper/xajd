<%@ page language="java" contentType="text/html; charset=GBK"%>
<table class="formlist" border="0" align="center" style="width: 100%">
	<thead>
		<tr>
			<th colspan="4">
				<span>��ͥ��Ա</span>				
			</th>
		</tr>
	</thead>
	<tbody>
		<!-- ��ͥ��Ա��� -->
		<tr>
			<td colspan="4" style="" id="jtcyTrId">
				<p id="jtcyCtrl">
				<input  value="+" onclick="trAdd('jtcyqk','add','cyAdd')" style="width: 20px"/>
				<input  value="-" onclick="trDel('jtcyqk','delRow')" style="width: 20px"/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ����&nbsp;&nbsp;
				<input type="text" name="cyAdd" id="cyAdd" style="width: 20px" onblur="trAdd('jtcyqk','madd','cyAdd')">
				&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ���&nbsp;&nbsp;
				<input type="text" name="cyDel" id="cyDel" style="width: 20px" onblur="trDelAll('jtcyqk','cyDel')">
				&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</p>	
				<table class="tbstyle" align="center" width="100%" id="tTb">
					<tr>
						<td>
							<div class="mid_box">
								<table align="center" style="width: 100%" id="rsT" class="tbstyle">
									<thead style="height: 23px">
										<tr>
											<logic:iterate name="jtcyTitleList" id="jtcyTitle">
												<td align="center" nowrap="nowrap" style='2%'>
													${jtcyTitle.cn }
												</td>
											</logic:iterate>
										</tr>
									</thead>		
									<tbody width="100%" class="tbstyle" id="jtcyqk">
			
									</tbody>	
								</table>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<!-- ��ͥ��Ա��� end-->
	</tbody>
</table>
	