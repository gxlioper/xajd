<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
<body>
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							<b>��д�����</b>
						</td>
					</tr>
				</thead>
				<tbody>
					<th  width="16%">
						<font color="red">*</font>ѧ��
					</th>
					<td align="left" width="34%">
						<logic:notEqual name="doType" value="modi">
							<logic:notEqual name="doType" value="view">
							
							<logic:equal name="userOnLine" value="teacher">
							<html:text name="rs" property="save_xh" styleId="xh" value="${rs.xh}" 
								onkeypress="autoFillStuInfo(event.keyCode,this)"
								onblur="chkIsStu(this,'view_xsjbxx','xh')"
							/>
							</logic:equal>
							
							<logic:equal name="userType" value="stu">
							<html:text name="rs" property="save_xh" readonly="true" styleId="xh" value="${rs.xh}" 
								onkeypress="autoFillStuInfo(event.keyCode,this)"
								onblur="chkIsStu(this,'view_xsjbxx','xh')"
							/>
							</logic:equal>
							
							<logic:notEqual value="stu" name="userType">
								<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									 id="buttonFindStu">
									ѡ��
								</button>
							</logic:notEqual>
							</logic:notEqual>
						</logic:notEqual>
						<logic:equal name="doType" value="modi">
							<bean:write name="rs" property="xh"/>
							<html:hidden property="save_xh" value="${xh}"/>
						</logic:equal>
						<logic:equal name="doType" value="view">
							<bean:write name="rs" property="xh"/>
							<html:hidden property="save_xh" value="${xh}"/>
						</logic:equal>
					</td>
					<th width="16%">
						<div align="right">
							����
						</div>
					</th>
					<td width="34%">
						${rs.xm }
					</td>
				</tr>
				<tr>
					<th>
						�Ա�
					</th>
					<td>
						${rs.xb }
					</td>
					<th>
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td>
						${rs.xymc }
					</td>
				</tr>
				<tr>
					<th>
						רҵ
					</td>
					<td>
						${rs.zymc }
					</td>
					<th>
						�༶
					</th>
					<td>
						${rs.bjmc }
					</td>
				</tr>
				<tr>
					<th align="right">
						<font color="red">*</font>ѧ��
					</th>
					<td>
						${xn}
					</td>
					<th align="right">
					<logic:notEmpty name="jxjList">
						<font color="red">*</font>��ѧ������
					</logic:notEmpty>
					<logic:notEmpty name="rychList">
						<font color="red">*</font>�����ƺ�����
					</logic:notEmpty>
					</th>
					<td>
					
					<logic:notEmpty name="jxjList">
					  <logic:notEqual name="doType"  value="view">
						<logic:notEqual name="doType"  value="modi">
						<html:select property="save_jxjdm" styleId="jxjdm">
							<html:options collection="jxjList" property="jxjdm"
								labelProperty="jxjmc" />
						</html:select>
						</logic:notEqual>
					  </logic:notEqual>
						
						<logic:equal name="doType" value="modi">
							<bean:write name="rs" property="jxjmc"/>
							<html:hidden property="save_jxjdm" value="${rs.jxjdm }" styleId="jxjdm"/>
						</logic:equal>
						<logic:equal name="doType" value="view">
							<bean:write name="rs" property="jxjmc"/>
							<html:hidden property="save_jxjdm" value="${rs.jxjdm }" styleId="jxjdm"/>
						</logic:equal>
					</logic:notEmpty>
				
					<logic:notEmpty name="rychList">
						<logic:notEqual name="doType"  value="view">
						<logic:notEqual name="doType"  value="modi">
						<html:select property="save_rychdm" styleId="rychdm">
							<html:options collection="rychList" property="dm"
								labelProperty="mc" />
						</html:select>
						</logic:notEqual>
						</logic:notEqual>
						
						<logic:equal name="doType" value="modi">
							<bean:write name="rs" property="rychmc"/>
							<html:hidden property="save_rychdm" value="${rs.rychdm }" styleId="rychdm"/>
						</logic:equal>
						<logic:equal name="doType" value="view">
							<bean:write name="rs" property="rychmc"/>
							<html:hidden property="save_rychdm" value="${rs.rychdm }" styleId="rychdm"/>
						</logic:equal>
					</logic:notEmpty>
					
					</td>
				</tr>
				<tr>
					<th>
						��������
					</th>
					<td>
						<bean:write name="rs" property="csrq"/>
					</td>
					<th>
						����
					</th>
					<td>
						<bean:write name="rs" property="mzmc"/>
					</td>
				</tr>
				<tr>
					<th>
						������ò
					</th>
					<td>
						<bean:write name="rs" property="zzmmmc"/>
					</td>
					<th >
						רҵ����
					</th>
					<td>
						${zyrs}��
					</td>
				</tr>
				<tr>
					<th>
						�꼶
					</th>
					<td>
						${rs.nj}
					</td>
					<th>
						�ֻ�����
					</th>
					<td>
						<bean:write name="rs" property="sjhm"/>
					</td>
				</tr>
				<tr>
					<th>
						���п���
					</th>
					<td>
						<bean:write name="rs" property="yhkh" />
					</td>
					<logic:notEmpty name="jxjList">
					<th>
						��ѧ����ְ���
					</th>
					<td>
						<html:text name="rs" property="save_drzw"/>
					</td>
					</logic:notEmpty>
					<logic:notEmpty name="rychList">
					<th>
						��ѧ����ְ���
					</th>
					<td>
						<html:text name="rs" property="drzw"/>
					</td>
					</logic:notEmpty>
				</tr>
				<tr>
					<th>
						�ۺϲ����ɼ�����
					</th>
					<td>
						<logic:notEmpty name="zcfpm" property="fs">
						������${zcfpm.fs}��
						</logic:notEmpty>
						<logic:notEmpty name="zcfpm" property="pm">
						������${zcfpm.pm }��
						</logic:notEmpty>
					</td>
					<th>
						��ѧ�겹������
					</th>
					<td>
						${bkms }��
					</td>
				</tr>
				<tr>
					<th>
						��ѧ���ۿ�����
					</th>
					<td>
					   ${zkms}��
					</td>
					<th>
						��������
					</th>
					<td>
						${sqsj}
					</td>
				</tr>
</body>