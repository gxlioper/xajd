<%@ page language="java" contentType="text/html; charset=GBK"%>
<input type="hidden" name="save_sqsj" id="sqsj" value="${xmInfo.sqsj }"/>
<input type="hidden" name="save_xmdm" id="xmdm" value="${xmInfo.xmdm }"/>
<input type="hidden" name="shjb" id="shjb"  value="${xmInfo.shjb }"/>
<input type="hidden" name="save_xh" value="${xmInfo.xh }"/>
			
<input type="hidden" name="xmb" id="xmb" value="${xmInfo.xmb }"/>
<input type="hidden" name="xh" id="xh"  value="${xmInfo.xh }"/>
<input type="hidden" name="lx" id="lx"  value="${lx }"/>
<input type="hidden" name="save_bzrsh" id="bzrsh" value="${xmInfo.bzrsh }"/>
<input type="hidden" name="save_fdysh" id="fdysh" value="${xmInfo.fdysh }"/>
<input type="hidden" name="save_xysh" id="xysh" value="${xmInfo.xysh }"/>
<input type="hidden" name="save_xxsh" id="xxsh" value="${xmInfo.xxsh }"/>
	<logic:empty name="xmnr" property="nrList">
		<logic:empty name="xmfjqkList">
			<table class="formlist" border="0" align="center" style="width: 100%">	
				<thead>
					<tr>
						<th>
							<span>������</span>				
						</th>
					</tr>
				</thead>
				<tr style="height: 23px">
					<td align="center">
						�������Ϣ
					</td>
				</tr>
			</table>
		</logic:empty>
	</logic:empty>
	<table class="formlist" border="0" align="center" style="width: 100%">
		<thead>
			<tr>
				<th colspan="4">
					<span>������</span>				
				</th>
			</tr>
		</thead>
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
										style="ime-mode:disabled" onblur="checkJe(this)"
										readonly="readonly"
										value="${fj.fjqdje }"/>
									<input type="hidden" name="qdje" id="qdje" value="${fj.fjqdje }"/>
								</logic:notEqual>
								<logic:equal name="fj" property="fjqdje" value="��">
									<input type="text" name="save_xmzzje" id="xmzzje" 
										onkeypress="return sztzNumInputValue(this,10,event)" 
										style="ime-mode:disabled" onblur="checkJe(this)"
										readonly="readonly"
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
						<html:select property="save_xmzzjb"  styleId="xmzzjb" disabled="true">
							<html:option value="">----��ѡ��----</html:option>
							<html:options collection="xmfjqkList" property="fjmc" labelProperty="fjmc" />
						</html:select>	
					</td>
					<logic:notEqual name="fj" property="fjqdje" value="��">
						<th align="right" width="15%">
							<font color="red">*</font>���
						</th>
						<td align="left" width="35">
							<input type="text" name="save_xmzzje" id="xmzzje" 
								readonly="readonly"/>
							<input type="hidden" name="xxje" id="xxje" value=""/>
							<input type="hidden" name="sxje" id="sxje" value=""/>
							<input type="hidden" name="qdje" id="qdje" value=""/>
						</td>
					</logic:notEqual>
					<logic:equal name="fj" property="fjqdje" value="��">
						<td align="right" colspan="2">

						</td>
					</logic:equal>
				</tr>
			</logic:notEqual>
		</logic:notEmpty>
		<!-- ������� -->
		<logic:equal name="xmInfo" property="shjb" value="�������">
			<tr style="height: 23px">
				<td align="left" colspan="4">
					����Ŀ�������
				</td>
			</tr>
		</logic:equal>
		
		<logic:notEmpty name="xmnr" property="nrList">
			<logic:iterate  name="xmnr" property="nrList" id="nr">
				<!-- һ����� -->
				<logic:equal name="xmInfo" property="shjb" value="һ�����">
					<tr style="height: 23px">
						<th align="right" width="15%">
							������
						</th>
						<td align="left" colspan="3">
							<logic:empty name="nr" property="shzt1yj">
								<textarea rows="5" style="width:90%" readonly="readonly" type="_moz"></textarea>
							</logic:empty>
							<logic:notEmpty name="nr" property="shzt1yj">
								<textarea rows="5" style="width:90%" name="save_shzt1yj" id="shzt1yj" readonly="readonly" type="_moz">${nr.shzt1yj }</textarea>
							</logic:notEmpty>
						</td>
					</tr>
				</logic:equal>
				<!-- ������� -->
				<logic:equal name="xmInfo" property="shjb" value="�������">
					<tr style="height: 23px">
						<th align="right" width="15%">
							һ��������
						</th>
						<td align="left" colspan="3">
							<logic:empty name="nr" property="shzt1yj">
								<textarea rows="5" style="width:90%" readonly="readonly" type="_moz"></textarea>
							</logic:empty>
							<logic:notEmpty name="nr" property="shzt1yj">
								<textarea rows="5" style="width:90%" name="save_shzt1yj" id="shzt1yj" readonly="readonly" type="_moz">${nr.shzt1yj }</textarea>
							</logic:notEmpty>
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right" width="15%">
							����������
						</th>
						<td align="left" colspan="3">
							<logic:empty name="nr" property="shzt2yj">
								<textarea rows="5" style="width:90%" readonly="readonly" type="_moz"></textarea>
							</logic:empty>
							<logic:notEmpty name="nr" property="shzt2yj">
								<textarea rows="5" style="width:90%" name="save_shzt2yj" id="shzt2yj" readonly="readonly" type="_moz">${nr.shzt2yj }</textarea>
							</logic:notEmpty>
						</td>
					</tr>
				</logic:equal>
				<!-- ������� -->
				<logic:equal name="xmInfo" property="shjb" value="�������">
					<tr align="right" width="15%">
						<th>
							һ��������
						</th>
						<td align="left" colspan="3">
							<logic:empty name="nr" property="shzt1yj">
								<textarea rows="5" style="width:90%" readonly="readonly" type="_moz"></textarea>
							</logic:empty>
							<logic:notEmpty name="nr" property="shzt1yj">
								<textarea rows="5" style="width:90%" name="save_shzt1yj" id="shzt1yj" readonly="readonly" type="_moz">${nr.shzt1yj }</textarea>
							</logic:notEmpty>
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right" width="15%">
							����������
						</th>
						<td align="left" colspan="3">
							<logic:empty name="nr" property="shzt2yj">
								<textarea rows="5" style="width:90%" readonly="readonly" type="_moz"></textarea>
							</logic:empty>
							<logic:notEmpty name="nr" property="shzt2yj">
								<textarea rows="5" style="width:90%" name="save_shzt2yj" id="shzt2yj" readonly="readonly" type="_moz">${nr.shzt2yj }</textarea>
							</logic:notEmpty>
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right" width="15%">
							����������
						</th>
						<td align="left" colspan="3">
							<logic:empty name="nr" property="shzt3yj">
								<textarea rows="5" style="width:90%" readonly="readonly" type="_moz"></textarea>
							</logic:empty>
							<logic:notEmpty name="nr" property="shzt3yj">
								<textarea rows="5" style="width:90%" name="save_shzt3yj" id="shzt3yj" readonly="readonly" type="_moz">${nr.shzt3yj }</textarea>
							</logic:notEmpty>										
						</td>
					</tr>
				</logic:equal>
			</logic:iterate>
		</logic:notEmpty>
	</table>