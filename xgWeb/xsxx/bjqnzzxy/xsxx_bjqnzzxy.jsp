<%@ page language="java" pageEncoding="GBK"%>
<%--������������ѧԺ--%>
<logic:equal value="11626" name="xxdm">
	<tr>
		<th>ԭ��ҵ<bean:message key="lable.xb" /></th>
		<td>
			<html:text property="rxqdw" name="rs" styleId="rxqdw" maxlength="127" />
		</td>
		<th>ԭ��ҵ<bean:message key="lable.xb" />�ʱ�</th>
		<td colspan="2">
			<html:text property="rxqdwyb" name="rs" styleId="rxqdwyb" maxlength="6" onkeyup="value=value.replace(/[^\d]/g,'') "/>
		</td>		
	</tr>
	<tr>
		<th>ԭ��ҵ<bean:message key="lable.xb" />ͨ�ŵ�ַ</th>
		<td colspan="4">
			<html:text property="rxqdwdz" name="rs" styleId="rxqdwdz" maxlength="100" style="width:600px"/>
		</td>
	</tr>	
	<tr>
		<th>���н׶α������</th>
		<td colspan="4">
			<html:textarea property="gzbx" name="rs" styleId="gzbx" cols="60" rows="4" onblur="chLeng(this,500)"></html:textarea>
		</td>
	</tr>
	<tr>
		<td colspan="5">
			<div class="con_overlfow floatleft">
				<table style="width: 100%" id="rsT" class="formlist">					
					<!-- ��ӡʱ��һ�в���ʾ- -->
					<thead style="height: 23px">
						<tr>
							<th colspan="9">
								<span><font color="blue"><b>����ѧ������</b></font></span>
							</th>
						</tr>
						<tr>
							<td colspan="9">
								<p align="left">
									<!-- ��ѯ�õ�����������ʾ���� -->
									<button type="button" onclick="trAdd('flag','add')">+</button>
									<button type="button" onclick="trDel('flag')">-</button>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ����&nbsp;&nbsp;
									<input type="text" name="numAdd" id="numAdd" style="width: 20px"
										onblur="trAdd('flag','madd')">
									&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ���&nbsp;&nbsp;
									<input type="text" name="numDel" id="numDel" style="width: 20px"
										onblur="trDelAll('flag','numDel')">
									&nbsp;��
								</p>
							</td>
						</tr>
						<tr>
							<th nowrap="nowrap">
								���
							</th>										
							<th nowrap="nowrap">
								��ʼʱ��
							</th>		
							<th nowrap="nowrap">
								����ʱ��
							</th>	
							<th nowrap="nowrap">
								����
							</th>
							<th nowrap="nowrap">
								����
							</th>
							<th nowrap="nowrap">
								ְλ
							</th>								
							<th nowrap="nowrap">
								֤����
							</th>
							<th nowrap="nowrap">
								֤���˵�λ
							</th>
							<th nowrap="nowrap">
								֤����ְ��
							</th>																																									
						</tr>
					</thead>
					<tbody id="flag">
					
					</tbody>
				</table>
			</div>
		</td>
	</tr>
	<tr>
		<td colspan="5">
			<div class="con_overlfow floatleft">					
				<table style="width: 100%" id="rsT" class="formlist">
					<!-- ��ӡʱ��һ�в���ʾ- -->
					<thead style="height: 23px">
						<tr>
							<th colspan="7">
								<span><font color="blue"><b>����ϵ</b></font></span>
							</th>
						</tr>
						<tr>
							<td colspan="7">
								<p align="left">
									<!-- ��ѯ�õ�����������ʾ���� -->
									<button type="button" onclick="shgxtrAdd('shgx','add')">+</button>
									<button type="button" onclick="trDel('shgx')">-</button>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ����&nbsp;&nbsp;
									<input type="text" name="shgx_numAdd" id="numAdd" style="width: 20px"
										onblur="shgxtrAdd('shgx','madd')">
									&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ���&nbsp;&nbsp;
									<input type="text" name="shgx_numDel" id="numDel" style="width: 20px"
										onblur="trDelAll('shgx','shgx_numDel')">
									&nbsp;��
								</p>
							</td>
						</tr>
						<tr>
							<th nowrap="nowrap">
								���
							</th>										
							<th nowrap="nowrap">
								����
							</th>		
							<th nowrap="nowrap">
								����
							</th>	
							<th nowrap="nowrap">
								������ò
							</th>
							<th nowrap="nowrap">
								�ֹ�����λ
							</th>
							<th nowrap="nowrap">
								����ְ��
							</th>								
							<th nowrap="nowrap">
								��ϵ�绰
							</th>																																								
						</tr>
					</thead>
					<tbody id="shgx">
					
					</tbody>
				</table>
		    </div>
		</td>
	</tr>
</logic:equal>