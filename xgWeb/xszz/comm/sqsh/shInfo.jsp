<%@ page language="java" contentType="text/html; charset=GBK"%>

<input type="hidden" name="save_sqsj" id="sqsj" value="${xmInfo.sqsj }"/>
<input type="hidden" name="save_xmdm" id="xmdm" value="${xmInfo.xmdm }"/>
<input type="hidden" name="shjb" id="shjb"  value="${xmInfo.shjb }"/>
<input type="hidden" name="save_xh" value="${xmInfo.xh }"/>
			
<input type="hidden" name="xmb" id="xmb" value="${xmInfo.xmb }"/>
<input type="hidden" name="xh" id="xh"  value="${xmInfo.xh }"/>
<input type="hidden" name="lx" id="lx"  value="${lx }"/>

<table class="formlist" border="0" align="center" style="width: 100%">
	<thead>
		<tr>
			<th colspan="4">
				<span>������</span>				
			</th>
		</tr>
	</thead>
	<tbody>
		<logic:notEmpty name="xmfjqkList">
			<logic:equal name="fjNum" value="1">
				<logic:iterate  name="xmfjqkList" id="fj">
					<logic:notPresent name="noJe">
						<tr style="height: 23px">
							<th align="right" width="15%">
								<logic:equal name="fj" property="fjmc" value="��">
									<font color="red">*</font>���
								</logic:equal>
							</th>
							<td align="left" colspan="3">
								<logic:notEqual name="fj" property="fjqdje" value="��">
									<input type="text" name="save_xmzzje" id="xmzzje" 
										onkeypress="return sztzNumInputValue(this,10,event)" 
										style="ime-mode:disabled" onblur="checkJe(this);"
										value="${fj.fjqdje }"/>
									<input type="hidden" name="qdje" id="qdje" value="${fj.fjqdje }"/>
								</logic:notEqual>
								<logic:equal name="fj" property="fjqdje" value="��">
									<input type="text" name="save_xmzzje" id="xmzzje" 
										onkeypress="return sztzNumInputValue(this,10,event)" 
										style="ime-mode:disabled" onblur="checkJe(this)"
										value=""/>
									<input type="hidden" name="xxje" id="xxje" value="${fj.fjxxje }"/>
									<input type="hidden" name="sxje" id="sxje" value="${fj.fjsxje }"/>
								</logic:equal>
							</td>
						</tr>
					</logic:notPresent>
				</logic:iterate>
			</logic:equal>	
			<logic:notEqual name="fjNum" value="1">
				<tr style="height: 23px">
					<th align="right" width="15%">
						<logic:iterate  name="xmfjqkList" id="fj" indexId="index">
							<logic:equal name="index" value="0">
								<font color="red">*</font>
								<logic:equal name="fj" property="fjmc" value="��">
									���
								</logic:equal>
								<logic:notEqual name="fj" property="fjmc" value="��">
									����
								</logic:notEqual>
							</logic:equal>
						</logic:iterate>
					</th>
					<td align="left" width="35">
						<html:select property="save_xmzzjb"  styleId="xmzzjb" onchange="setJeqk(this.value)">
							<html:option value="">----��ѡ��----</html:option>
							<html:options collection="xmfjqkList" property="fjmc" labelProperty="fjmc" />
						</html:select>
					</td>
					<th align="right" width="15%">
						<font color="red">*</font>���
					</th>
					<td align="left" width="35">
						<input type="text" name="save_xmzzje" id="xmzzje" 
							onkeypress="return sztzNumInputValue(this,10,event)"
							style="ime-mode:disabled" onblur="checkJe(this);"/>
						<input type="hidden" name="xxje" id="xxje" value=""/>
						<input type="hidden" name="sxje" id="sxje" value=""/>
						<input type="hidden" name="qdje" id="qdje" value=""/>
					</td>
				</tr>
			</logic:notEqual>
		</logic:notEmpty>
		<logic:notEmpty name="xmnr" property="nrList">
			<logic:iterate  name="xmnr" property="nrList" id="nr">
				<!-- һ����� -->
				<logic:equal name="nr" property="shjb" value="һ�����">
					<tr style="height: 23px">
						<th align="right" width="15%">
							��˽��
						</th>
						<td align="left">
							${nr.shzt1 }						
						</td>
						<th>���ʱ��</th>
						<td>
							<input type="text" 
							       name="save_shsj1" 
							       id="shsj1" 
							       value="${nr.shsj1 }" 
							       readonly="true"
							       onclick="return showCalendar(this.id,'yyyy��M��d��')"/>
						</td>
						
					</tr>	
					<tr style="height: 23px">
						<th align="right" width="15%">
							������
							<input type="hidden" name="save_shzt1" id="shzt1" value="${nr.shzt1 }"/>
							<input type="hidden" name="save_bzrsh" id="bzrsh" value="${nr.bzrsh}"/>
							<input type="hidden" name="save_fdysh" id="fdysh" value="${nr.fdysh}"/>
							<input type="hidden" name="save_xysh" id="xysh" value="${nr.xysh}"/>
							<input type="hidden" name="save_xxsh" id="xxsh" value="${nr.xxsh}"/>
						</th>
						<td align="left" colspan="3">
							<textarea rows="5" style="width:600px" name="save_shzt1yj" id="shzt1yj" onblur="chLeng(this,500)" type="_moz"></textarea>
						</td>
					</tr>					
				</logic:equal>
				<!-- һ����� end-->
				<!-- ������� -->
				<logic:equal name="nr" property="shjb" value="�������">
					<!-- �����Ϊһ�� -->
					<logic:equal name="nr" property="jb" value="Lv1">
						<tr style="height: 23px">
							<th align="right" width="15%">
								��˽��
							</th>
							<td align="left">
								${nr.shzt1 }						
							</td>
							<th>���ʱ��</th>
							<td>
								<input type="text" 
								       name="save_shsj1" 
								       id="shsj1" 
								       value="${nr.shsj1 }" 
								       readonly="true"
								       onclick="return showCalendar(this.id,'yyyy��M��d��')"/>
							</td>
						</tr>
						<tr style="height: 23px">
							<th align="right" width="15%">
								������
								<input type="hidden" id="jb" value="Lv1"/>
								<input type="hidden" id="jbdm1" value="${nr.jbdm1 }"/>
								<input type="hidden" id="jbdm2" value="${nr.jbdm2 }"/>
								<input type="hidden" name="save_shzt1" id="shzt1" value="${nr.shzt1 }"/>
								<input type="hidden" name="save_shzt2" id="shzt2" value="${nr.shzt2 }"/>
								<input type="hidden" name="save_bzrsh" id="bzrsh" value="${nr.bzrsh}"/>
								<input type="hidden" name="save_fdysh" id="fdysh" value="${nr.fdysh}"/>
								<input type="hidden" name="save_xysh" id="xysh" value="${nr.xysh}"/>
								<input type="hidden" name="save_xxsh" id="xxsh" value="${nr.xxsh}"/>
								<br>
								��${nr.jbmc1 }��
								<br>
								<font color="red">(����¼��500��)</font>
							</th>
							<td align="left" colspan="3">
								<textarea rows="5" style="width:600px" name="save_shzt1yj" id="shzt1yj" onblur="chLeng(this,500)" type="_moz"></textarea>
							</td>
						</tr>
					</logic:equal>
					<!-- �����Ϊ���� -->
					<logic:equal name="nr" property="jb" value="Lv2">
						<tr style="height: 23px">
							<th align="right" width="15%">
								������
								<input type="hidden" id="jb" value="Lv2"/>
								<input type="hidden" id="jbdm1" value="${nr.jbdm1 }"/>
								<input type="hidden" id="jbdm2" value="${nr.jbdm2 }"/>
								<input type="hidden" name="save_shzt1" id="shzt1" value="${nr.shzt1 }"/>
								<input type="hidden" name="save_shzt2" id="shzt2" value="${nr.shzt2 }"/>
								<input type="hidden" name="save_bzrsh" id="bzrsh" value="${nr.bzrsh}"/>
								<input type="hidden" name="save_fdysh" id="fdysh" value="${nr.fdysh}"/>
								<input type="hidden" name="save_xysh" id="xysh" value="${nr.xysh}"/>
								<input type="hidden" name="save_xxsh" id="xxsh" value="${nr.xxsh}"/>
								<br>
								��${nr.jbmc1 }��
							</th>
							<td align="left" colspan="3">
								<textarea rows="5" style="width:600px" name="save_shzt1yj" id="shzt1yj" readonly="readonly" type="_moz"></textarea>
							</td>	
						</tr>	
						<tr style="height: 23px">
							<th align="right" width="15%">
								��˽��
							</th>
							<td align="left">
								${nr.shzt2 }						
							</td>
							<th>���ʱ��</th>
							<td>
								<input type="text" 
								       name="save_shsj2" 
								       id="shsj2" 
								       value="${nr.shsj2 }" 
								       readonly="true"
								       onclick="return showCalendar(this.id,'yyyy��M��d��')"/>
							</td>
						</tr>					
						<tr style="height: 23px">
							<th align="right" width="15%">
								������
								<br>
								��${nr.jbmc2 }��
								<br>
								<font color="red">(����¼��500��)</font>
							</th>
							<td align="left" colspan="3">
								<textarea rows="5" style="width:600px" name="save_shzt2yj" id="shzt2yj" onblur="chLeng(this,500)" type="_moz"></textarea>
							</td>
						</tr>						
					</logic:equal>			
				</logic:equal>
				<!-- ������� end-->
				<!-- ������� -->
				<logic:equal name="nr" property="shjb" value="�������">
					<!-- �����Ϊһ�� -->
					<logic:equal name="nr" property="jb" value="Lv1">
						<tr style="height: 23px">
							<th align="right" width="15%">
								��˽��
							</th>
							<td align="left">
								${nr.shzt1 }						
							</td>
							<th>���ʱ��</th>
							<td>
								<input type="text" 
								       name="save_shsj1" 
								       id="shsj1" 
								       value="${nr.shsj1 }" 
								       readonly="true"
								       onclick="return showCalendar(this.id,'yyyy��M��d��')"/>
							</td>
						</tr>
						<tr style="height: 23px">					
							<th align="right" width="15%">
								������
								<input type="hidden" id="jb" value="Lv1"/>
								<input type="hidden" id="jbdm1" value="${nr.jbdm1 }"/>
								<input type="hidden" id="jbdm2" value="${nr.jbdm2 }"/>
								<input type="hidden" id="jbdm3" value="${nr.jbdm3 }"/>
								<input type="hidden" name="save_shzt1" id="shzt1" value="${nr.shzt1 }"/>
								<input type="hidden" name="save_shzt2" id="shzt2" value="${nr.shzt2 }"/>
								<input type="hidden" name="save_shzt3" id="shzt3" value="${nr.shzt3 }"/>
								<input type="hidden" name="save_bzrsh" id="bzrsh" value="${nr.bzrsh}"/>
								<input type="hidden" name="save_fdysh" id="fdysh" value="${nr.fdysh}"/>
								<input type="hidden" name="save_xysh" id="xysh" value="${nr.xysh}"/>
								<input type="hidden" name="save_xxsh" id="xxsh" value="${nr.xxsh}"/>
								<br>
								��${nr.jbmc1 }��
								<br>
								<font color="red">(����¼��500��)</font>
							</th>
							<td align="left" colspan="3">
								<textarea rows="5" style="width:600px" name="save_shzt1yj" id="shzt1yj" onblur="chLeng(this,500)" type="_moz"></textarea>
							</td>
						</tr>						
					</logic:equal>
					<!-- �����Ϊ���� -->
					<logic:equal name="nr" property="jb" value="Lv2">
						<tr style="height: 23px">
							<th align="right" width="15%">
								������
								<input type="hidden" id="jb" value="Lv2"/>
								<input type="hidden" id="jbdm1" value="${nr.jbdm1 }"/>
								<input type="hidden" id="jbdm2" value="${nr.jbdm2 }"/>
								<input type="hidden" id="jbdm3" value="${nr.jbdm3 }"/>
								<input type="hidden" name="save_shzt1" id="shzt1" value="${nr.shzt1 }"/>
								<input type="hidden" name="save_shzt2" id="shzt2" value="${nr.shzt2 }"/>
								<input type="hidden" name="save_shzt3" id="shzt3" value="${nr.shzt3 }"/>
								<input type="hidden" name="save_bzrsh" id="bzrsh" value="${nr.bzrsh}"/>
								<input type="hidden" name="save_fdysh" id="fdysh" value="${nr.fdysh}"/>
								<input type="hidden" name="save_xysh" id="xysh" value="${nr.xysh}"/>
								<input type="hidden" name="save_xxsh" id="xxsh" value="${nr.xxsh}"/>
								<br>
								��${nr.jbmc1 }��
							</th>
							<td align="left" colspan="3">
								<textarea rows="5" style="width:600px" name="save_shzt1yj" id="shzt1yj" readonly="readonly" type="_moz"></textarea>
							</td>
						</tr>
						<tr style="height: 23px">
							<th align="right" width="15%">
								��˽��
							</th>
							<td align="left">
								${nr.shzt2 }						
							</td>
							<th>���ʱ��</th>
							<td>
								<input type="text" 
								       name="save_shsj2" 
								       id="shsj2" 
								       value="${nr.shsj2 }" 
								       readonly="true"
								       onclick="return showCalendar(this.id,'yyyy��M��d��')"/>
							</td>
						</tr>
						<tr style="height: 23px">
							<th align="right" width="15%">
								������
								<br>
								��${nr.jbmc2 }��
								<br>
								<font color="red">(����¼��500��)</font>
							</th>
							<td align="left" colspan="3">
								<textarea rows="5" style="width:600px" name="save_shzt2yj" id="shzt2yj" onblur="chLeng(this,500)" type="_moz"></textarea>
							</td>
						</tr>
					</logic:equal>	
					<!-- �����Ϊ���� -->
					<logic:equal name="nr" property="jb" value="Lv3">
						<tr style="height: 23px">
							<th align="right" width="15%">
								������
								<input type="hidden" id="jb" value="Lv3"/>
								<input type="hidden" id="jbdm1" value="${nr.jbdm1 }"/>
								<input type="hidden" id="jbdm2" value="${nr.jbdm2 }"/>
								<input type="hidden" id="jbdm3" value="${nr.jbdm3 }"/>
								<input type="hidden" name="save_shzt1" id="shzt1" value="${nr.shzt1 }"/>
								<input type="hidden" name="save_shzt2" id="shzt2" value="${nr.shzt2 }"/>
								<input type="hidden" name="save_shzt3" id="shzt3" value="${nr.shzt3 }"/>
								<input type="hidden" name="save_bzrsh" id="bzrsh" value="${nr.bzrsh}"/>
								<input type="hidden" name="save_fdysh" id="fdysh" value="${nr.fdysh}"/>
								<input type="hidden" name="save_xysh" id="xysh" value="${nr.xysh}"/>
								<input type="hidden" name="save_xxsh" id="xxsh" value="${nr.xxsh}"/>
								<br>
								��${nr.jbmc1 }��
							</th>
							<td align="left" colspan="3">
								<textarea rows="5" style="width:600px" name="save_shzt1yj" id="shzt1yj" readonly="readonly" type="_moz"></textarea>
							</td>
						</tr>
						<tr style="height: 23px">
							<th align="right" width="15%">
								������
								<br>
								��${nr.jbmc2 }��
							</th>
							<td align="left" colspan="3">
								<textarea rows="5" style="width:600px" name="save_shzt2yj" id="shzt2yj" readonly="readonly" type="_moz"></textarea>
							</td>
						</tr>
						<tr style="height: 23px">
							<th align="right" width="15%">
								��˽��
							</th>
							<td align="left">
								${nr.shzt3 }						
							</td>
							<th>���ʱ��</th>
							<td>
								<input type="text" 
								       name="save_shsj3" 
								       id="shsj3" 
								       value="${nr.shsj3 }" 
								       readonly="true"
								       onclick="return showCalendar(this.id,'yyyy��M��d��')"/>
							</td>
						</tr>	
						<tr style="height: 23px">
							<th align="right" width="15%">
								������
								<br>
								��${nr.jbmc3 }��
								<br>
								<font color="red">(����¼��500��)</font>
							</th>
							<td align="left" colspan="3">
								<textarea rows="5" style="width:600px" name="save_shzt3yj" id="shzt3yj" onblur="chLeng(this,500)" type="_moz"></textarea>
							</td>
						</tr>
					</logic:equal>	
				</logic:equal>
				<!-- ������� end-->
			</logic:iterate>
		</logic:notEmpty>
	</tbody>
</table>