<%@ page language="java" contentType="text/html; charset=GBK"%>
<table class="formlist" border="0" align="center" style="width: 100%">
	<thead>
		<tr>
			<th colspan="4">
				<span>��Ŀ����<font color="red">(ע������Ƶ�����ʾ�������ϣ�����ʾ��Ӧ����ʾ��Ϣ)</font></span>
			</th>
		</tr>
	</thead>
	<tbody>		
		<tr style="height: 23px">
			<th align="right" width="15%">
				�Ƿ��漰��
			</th>
			<td align="left" width="35%">
				<html:radio name="rs" property="sfje" styleId="bje" onclick="showXmwh()" value="����Ҫ"/>����Ҫ
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<html:radio name="rs" property="sfje" styleId="je" onclick="showXmwh()" value="��Ҫ"/>��Ҫ									
			</td>
			<td align="left" colspan="2" >
				<span onmousemove="showTsDiv('jets')" onmouseout="hiddTsDiv('jets')">
					<font color="blue">��ʾ��</font>
					<font color="blue" id="jets" style="display : none">
					���ø���Ŀ�Ƿ�����ҹ������޽����ͥ������飩����ѡ����Ҫ��
					</font>
				</span>
			</td>
		</tr>
		<tr>
			<th align="right">
				�Ƿ�ּ���
			</th>
			<td>												
				<html:radio name="rs" property="sffj" styleId="bfj" value="���ּ�" onclick="showXmwh()"/>���ּ�
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<html:radio name="rs" property="sffj" styleId="fj" value="�ּ�" onclick="showXmwh()"/>�ּ�
			</td>
			<td align="left" colspan="2">
				<span onmousemove="showTsDiv('fjts')" onmouseout="hiddTsDiv('fjts')">
					<font color="blue">��ʾ��</font>
					<font color="blue" id="fjts" style="display : none">
					���ø���Ŀ�Ƿ���Ҫ�ּ�������Ҫ�������Ѳ�����һ�����ȣ�����ѡ��ּ���
					</font>
				</span>
			</td>
		</tr>
		<tr id="jelxTr" style="display : none">
			<th align="right">
				������ͣ�
			</th>
			<td>
				<html:radio name="rs" property="jelx" styleId="qdz" value="ȷ��ֵ" onclick="showXmwh()"/>ȷ��ֵ
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<html:radio name="rs" property="jelx" styleId="qj" value="����" onclick="showXmwh()"/>����
				</td>
				<td align="left" colspan="2">
				<span onmousemove="showTsDiv('jezts')" onmouseout="hiddTsDiv('jezts')">
					<font color="blue">��ʾ��</font>
					<font color="blue" id="jezts" style="display : none">
					���ý������ͣ�����ȷ��ֵ�Ļ������ʱ��ʦ�������ٶԻ�ý������޸ġ�
						</font>
				</span>
			</td>
		</tr>
		<!-- ȷ��ֵ��� -->
		<tr id="qdjeTr" style="display : none">
			<th align="right">
				��
			</th>
			<td>
				<html:text property="nofjje" 
					onkeydown="return onlyNum(this,5)"
					onmousedown="return onlyNum(this,5)"
					style="width:30%;ime-mode:disabled"/>
			</td>
			<td>
				
			</td>
			<td>				
			</td>
		</tr>
		<!-- ������ -->
		<tr id="qjTr" style="display : none">
			<th align="right">
				������ޣ�
			</th>
			<td>
				<html:text property="nofjxx" 
					onkeydown="return onlyNum(this,5)"
					onmousedown="return onlyNum(this,5)"
					style="width:30%;ime-mode:disabled"/>
			</td>
			<th align="right" align="right" width="15%">
				������ޣ�
			</th>
			<td>
				<html:text property="nofjsx"
					onkeydown="return onlyNum(this,5)"
					onmousedown="return onlyNum(this,5)"
					style="width:30%;ime-mode:disabled"/>
			</td>
		</tr>
		<!-- �޽��ּ� -->
		<tr id="noje" style="display : none">
			<td colspan="4">
				<p>
					<input  value="+" onclick="trAdd('jbnr','add','jbnrAdd')" style="width: 20px"/>
					<input  value="-" onclick="trDel('jbnr','delRow1')" style="width: 20px"/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ����&nbsp;&nbsp;
					<input type="text" name="jbnrAdd" id="jbnrAdd" style="width: 20px" onblur="trAdd('jbnr','madd','jbnrAdd')">
					&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ���&nbsp;&nbsp;
					<input type="text" name="jbnrDel" id="jbnrDel" style="width: 20px" onblur="trDelAll('jbnr','jbnrDel')">
					&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</p>				
				<table align="center" style="width: 100%" id="rsT" class="tbstyle">
					<thead style="height: 23px">
						<tr>
							<td align="center" nowrap="nowrap" style='width : 5%'>
								��ѡ��
							</td>				
							<td align="center" nowrap="nowrap" style=''>
								��������
							</td>
						</tr>
					</thead>		
					<tbody width="100%" class="tbstyle" id="jbnr">
					</tbody>	
				</table>			
			</td>
		</tr>
		<!-- ȷ�����ּ� -->
		<tr id="qdje" style="display : none">
			<td colspan="4">
				<p>
					<input  value="+" onclick="trAdd('qdnr','add','qdnrAdd')" style="width: 20px"/>
					<input  value="-" onclick="trDel('qdnr','delRow2')" style="width: 20px"/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ����&nbsp;&nbsp;
					<input type="text" name="jbnrAdd" id="qdnrAdd" style="width: 20px" onblur="trAdd('qdnr','madd','qdnrAdd')">
					&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ���&nbsp;&nbsp;
					<input type="text" name="jbnrDel" id="qdnrDel" style="width: 20px" onblur="trDelAll('qdnr','qdnrDel')">
					&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</p>				
				<table align="center" style="width: 100%" id="rsT" class="tbstyle">
					<thead style="height: 23px">
						<tr>
							<td align="center" nowrap="nowrap" style='width : 5%'>
								��ѡ��
							</td>				
							<td align="center" nowrap="nowrap" style='width : 40%'>
								��������
							</td>
							<td align="center" nowrap="nowrap" style=''>
								���
							</td>
						</tr>
					</thead>		
					<tbody style="height: 23px" id="qdnr">
					</tbody>	
				</table>			
			</td>
		</tr>
		<!-- ������ּ� -->
		<tr id="qjje" style="display : none">
			<td colspan="4">
				<p>
					<input  value="+" onclick="trAdd('qjnr','add','qjnrAdd')" style="width: 20px"/>
					<input  value="-" onclick="trDel('qjnr','delRow3')" style="width: 20px"/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ����&nbsp;&nbsp;
					<input type="text" name="jbnrAdd" id="qjnrAdd" style="width: 20px" onblur="trAdd('qjnr','madd','qjnrAdd')">
					&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ���&nbsp;&nbsp;
					<input type="text" name="jbnrDel" id="qjnrDel" style="width: 20px" onblur="trDelAll('qjnr','qjnrDel')">
					&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</p>				
				<table align="center" style="width: 100%" id="rsT" class="tbstyle">
					<thead style="height: 23px">
						<tr>
							<td align="center" nowrap="nowrap" style='width : 5%'>
								��ѡ��
							</td>				
							<td align="center" nowrap="nowrap" style='width : 45%'>
								��������
							</td>
							<td align="center" nowrap="nowrap" style='25%'>
								�������
							</td>
							<td align="center" nowrap="nowrap" style='25%'>
								�������
							</td>
						</tr>
					</thead>		
					<tbody width="100%" class="tbstyle" id="qjnr">
					</tbody>	
				</table>			
			</td>
		</tr>	
	</tbody>
</table>