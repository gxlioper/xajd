<%@ page language="java" contentType="text/html; charset=GBK"%>
<table class="formlist" border="0" align="center" style="width: 100%">
	<thead>
		<tr>
			<th colspan="4">
				<span>��Ŀ�������</span>
			</th>
		</tr>
	</thead>
	<tbody>		
					<tr style="height: 23px">
						<th align="right" width="15%">
							����״̬��
						</th>
						<td align="left" colspan="3">
							<html:radio name="rs" property="kgzt" onclick="" value="��������"/>��������
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<html:radio name="rs" property="kgzt" onclick="" value="�ر�����"/>�ر�����			
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<html:radio name="rs" property="kgzt" onclick="" value="��Ŀ�ر�"/>��Ŀ�ر�						
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="left" colspan="4">
							<span onmousemove="showTsDiv('kgts')" onmouseout="hiddTsDiv('kgts')">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">��ʾ��</font>
								<font color="blue" id="kgts" style="display : none">
								���뿪�Źر��漰��ѧ���Ƿ�������룬��Ŀ�ر��������룬��ˣ������ѯ��ģ�鶼���ٳ��ָ���Ŀ��
								</font>
							</span>
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right" width="15%">
							��˼���
						</th>
						<td width="35%">
							<html:select name="rs" property="shjb" style="" styleId="shjb" onchange="showShjb()">
								<html:options collection="shjbList" property="en" labelProperty="cn" />
							</html:select>
						</td>
						<td align="right" colspan="2">
							
						</td>
					</tr>
					<tr id="shjbTr" style="display : none">
						<td colspan="4">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="checkbox" name="shr" id="bjsh" onclick="clickBjsh()" value="�༶���"/>�༶���
							(
							<html:radio name="rs" property="lssh" styleId="bzrsh" value="������" onclick="clickBzrFdy()"/>������
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<html:radio name="rs" property="lssh" styleId="fdysh" value="����Ա" onclick="clickBzrFdy()"/>����Ա		
							)
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="checkbox" name="shr" id="xysh" value="<bean:message key="lable.xb" />���"><bean:message key="lable.xsgzyxpzxy" />���
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="checkbox" name="shr" id="xxsh" value="ѧУ���">ѧУ���
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="left" colspan="4">
							<span onmousemove="showTsDiv('shts')" onmouseout="hiddTsDiv('shts')">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">��ʾ��</font>	
								<div id="shts" style="display : none">		
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="blue">������Ŀ��Ҫ������ˡ�</font><br>				
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="blue">1����ѡ��һ����˶�δָ�����弶�������⼶�����Ȩ�޶���Ŀ������ˡ�</font><br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="blue">2����ѡ�������˶�δָ�����弶����Ĭ�����˳��Ϊ(<bean:message key="lable.xb" />-->ѧУ),����ָ��<bean:message key="lable.xb" />��ˣ�˳��Ϊ(<bean:message key="lable.xb" />-->ѧУ),</font><br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="blue">����ָ���༶��ˣ�˳��Ϊ(�༶--><bean:message key="lable.xb" />��ѧУ)������ָ��ѧУ��ˣ�˳��Ϊ(�༶��<bean:message key="lable.xb" />-->ѧУ)��</font><br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="blue">3����ѡ��������˶�δָ�����弶����Ĭ�����˳��Ϊ(����Ա--><bean:message key="lable.xb" />-->ѧУ)��</font><br>
								</div>
							</span>
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right" width="">
							�������ƣ�
						</th>
						<td width="">
							<html:select name="rs" property="rskz" style="" styleId="rskz" onchange="showRsjb();showKzjb()">
								<html:options collection="rsjbList" property="en" labelProperty="cn" />
							</html:select>
						</td>
						<th align="right">
							���Ʒ�Χ��
						</th>
						<td width="">
							<html:select name="rs" property="kzjb" style="" styleId="kzjb" onchange="">
								<html:options collection="kzjbList" property="en" labelProperty="cn" />
							</html:select>
						</td>
					</tr>
					<tr id="rsjbTr" style="display : none">
						<td colspan="4">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="kzr" id="bjkz" value="�༶���"/>�༶���
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="kzr" id="xykz" value="<bean:message key="lable.xb" />���"/><bean:message key="lable.xb" />���
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="kzr" id="xxkz" value="ѧУ���"/>ѧУ���
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="left" colspan="4">
							<span onmousemove="showTsDiv('jbts')" onmouseout="hiddTsDiv('jbts')">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">��ʾ��</font>	
								<div id="jbts" style="display : none">		
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="blue">������Ŀ�Ƿ���Ҫ�����������޿���,��������Ҫ���Ƶ������</font><br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="blue">1�������������ô����������о�������ã���ѡ����Ҫ���ƶ���δ���þ�����������ѧ���޷����롣</font><br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="blue">2����ѡ����Ƶķ�Χ��Ĭ�ϸ���<bean:message key="lable.xb" />������ͳ��������</font><br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="blue">3����ѡ��ͳ�Ƶ�������Ĭ����ͳ��<bean:message key="lable.xb" />���Ϊͨ����ѧ������</font><br>
								</div>
							</span>
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right" width="">
							�������ޣ�
						</th>
						<td width="">
							<html:text name="rs" property="rssx"  
							styleId="rssx"
							onkeydown="return onlyNum(this,5)"
							onmousedown="return onlyNum(this,5)"
							style="width:30%;ime-mode:disabled"/>��<span id=""><font color="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(��¼������)</font></span>
						</td>
						<td colspan="2">
							
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="left" colspan="4">
							<span onmousemove="showTsDiv('rsts')" onmouseout="hiddTsDiv('rsts')">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">��ʾ��</font>	
								<div id="rsts" style="display : none">		
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="blue">���ø���Ŀ�Ƿ�����������ޣ��������������Ƹ������в�ͬ����ע�⣩��</font><br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="blue">���磺���Ѳ�����Ȼ��Ҫ����ÿ��<bean:message key="lable.xb" />��������������Ŀ�����û���������ޣ���ֵΪ�ա�</font><br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="blue">���ҽ�ѧ�����һ��ֻ����500�ˣ�����ÿ��<bean:message key="lable.xb" />�ж��ٲ�����ô���ò��ܳ�����ֵ�����ֵά��Ϊ500��</font><br>
								</div>
							</span>
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right" width="">
							�������ڣ�
						</th>
						<td align="left" colspan="3">
							<html:radio name="rs" property="sqzq" onclick="" value="ѧ��"/>ѧ��
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<html:radio name="rs" property="sqzq" onclick="" value="ѧ��"/>ѧ��	
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<html:radio name="rs" property="sqzq" onclick="" value="���"/>���
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<html:radio name="rs" property="sqzq" onclick="" value="������"/>������
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<html:radio name="rs" property="sqzq" onclick="" value="��һ��"/>��һ��
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="left" colspan="4">
							<span onmousemove="showTsDiv('zqts')" onmouseout="hiddTsDiv('zqts')">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">��ʾ��</font>	
								<div id="zqts" style="display : none">		
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="blue">���ø���Ŀ��������ÿѧ��һ�λ���ÿѧ�ڣ���ȣ�һ�Σ������ڵĻ�ÿ��������롣</font><br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="blue">ע��:���ѧ���Ѿ������˸���Ŀ��У��ͻȻ�޸����������ڣ���ô�Ѿ������ߵ���Ϣ���ܻ����쳣����С�ġ�</font><br>
								</div>
							</span>
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right" width="">
							����ʱ�䣺
						</th>
						<td align="left" colspan="3">
							<html:select name="rs" property="pdsj" style="" styleId="pdsj" onchange="">
								<html:options collection="pdsjList" property="en" labelProperty="cn" />
							</html:select>
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="left" colspan="4">
							<span onmousemove="showTsDiv('sjts')" onmouseout="hiddTsDiv('sjts')">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">��ʾ��</font>		
								<font color="blue" id="sjts" style="display : none">�����������Ŀ��ʱ��Ϊ��ѧ�껹����һѧ�꣨�������ѧ�꣬ѧ�ڻ�����ȣ������������ڶ�����</font>
							</span>
						</td>
					</tr>
	</tbody>
</table>
