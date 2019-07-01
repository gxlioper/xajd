<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/szdwfzjy" method="post">
			<input type="hidden" id="method" name="method"
				value="ycjySq" />
			
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��չ����-��չ�������-Ӣ�Ź���</a>
				</p>
			</div>
			
			<logic:notEmpty name="rs">
			<logic:equal name="rs" property="stuExists" value="no">
					<script>
    					alert("�������ѧ����Ч!");
    				</script>
				</logic:equal>
				<input type="hidden" id="pk" name="pk"
					value="<bean:write name="rs" property="pk" scope="request"/>" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/szdwfzjy.do?method=xssbshOne&tableName=view_fzjyycjyb" />
			<input type="hidden" id="xh" name="xh"
				value="<bean:write name='rs' property='xh' />" />
				<div class="tab">
				  	<table width="100%"  border="0" class="formlist">
					    <thead>
					    	<tr>
					        	<th colspan="4"><span>Ӣ�Ź���</span></th>
					        </tr>
					    </thead>
						<tfoot>
					      <tr>
					        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
					          <div class="btn">
					          		<button type="button" name="����" onclick="szsxDataDoSave('szdwfzjy.do?method=ycjySh','xh-sbjhmc-xn-nd');">�� ��</button>
					          </div></td>
					      </tr>
					    </tfoot>

					<tbody>
						<tr>
							<th align="right">
								<font color="red">*</font>ѧ�꣺
							</th>
							<td align="left">
								<html:select name = "rs" property="xn" style="width:120px" 
									styleId="xn">
								<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
							<th align="right">
								<font color="red">*</font>��ȣ�
							</th>
							<td align="left">
								<html:select name = "rs" property="nd" style="width:90px"
										styleId="nd">
								<html:options collection="xnList" property="nd"
										labelProperty="nd" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th align="right">
								�걨�ƻ�����:
							</th>
							<td colspan="3">
								<html:text name = "rs" property="sbjhmc" style="width:99%"  styleId="sbjhmc"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								<font color="red">*</font>ѧ�ţ�
							</th>
							<td align="left">
								<html:text name='rs' property="xh" readonly="readonly"
									styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu" >
									ѡ��
								</button>
							</td>
							<th align="right">
								������
							</th>
							<td align="left">
								<bean:write name='rs' property="xm"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								�Ա�
							</th>
							<td align="left">
								<bean:write name='rs' property="xb"/>
							</td>
							<th align="right">
								�꼶��
							</th>
							<td align="left">
								<bean:write name='rs' property="nj"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								<bean:message key="lable.xsgzyxpzxy" />��
							</th>
							<td align="left">
								<bean:write name='rs' property="xymc" />
							</td>
							<th align="right">
								רҵ��
							</th>
							<td align="left">
								<bean:write  name='rs' property="zymc"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								�༶��
							</th>
							<td align="left">
								<bean:write name='rs' property="bjmc"/>
							</td>
							<th align="right">
								�����ˮƽ:
							</th>
							<td align="left">
								<html:text name='rs' property="jsjsp" />
							</td>
						</tr>
						<tr>
							<th align="right">
								��ϵ�绰��
							</th>
							<td align="left">
								<html:text name='rs' property="lxdh" />
							</td>
							<th align="right">
								E-mail:
							</th>
							<td align="left">
								<html:text name='rs' property="email" />
							</td>
						</tr>
						<tr>
							<th align="right">
								���������ְ��:
							</th>
							<td colspan="3">
								<html:text name = "rs" property="cdrshzw" style="width:99%"  />
							</td>
						</tr>
						<tr>
							<th align="right">
								ѧ�ּ��㣺
							</th>
							<td align="left">
								<html:text name='rs' property="xfjj" />
							</td>
							<th align="right">
								�ۺϲ�������:
							</th>
							<td align="left">
								<html:text name='rs' property="zhcppm" />
							</td>
						</tr>
						<tr>
							<th align="right">
								��ʦ������
							</th>
							<td align="left">
								<html:text name='rs' property="dsxm" />
							</td>
							<th align="right">
								��ʦE-mail:
							</th>
							<td align="left">
								<html:text name='rs' property="dsemail" />
							</td>
						</tr>
						<tr>
							<th align="right">
								��ʦ��λ:
							</th>
							<td colspan="3">
								<html:text name = "rs" property="dsdw" style="width:99%"  />
							</td>
						</tr>
						<logic:equal  name="userType" value = "xy" scope = "session">
						<tr>
							<th align="right">
								<bean:message key="lable.xsgzyxpzxy" />��ˣ�
							</th>
							<td align="left">
								<html:select name = "rs" property="xysh" style="width:120px" styleId="xysh" >
										<html:option value=""></html:option>
										<html:option value="δ���">δ���</html:option>
										<html:option value="ͨ��">ͨ��</html:option>
										<html:option value="��ͨ��">��ͨ��</html:option>
								</html:select>
							</td>
							<th align="right">								
							`	ѧУ��ˣ�
							</th>
							<td align="left">
								<html:select name = "rs"  property="xxsh" style="width:120px" styleId="xxsh" disabled = "true">
										<html:option value=""></html:option>
										<html:option value="δ���">δ���</html:option>
										<html:option value="ͨ��">ͨ��</html:option>
										<html:option value="��ͨ��">��ͨ��</html:option>
								</html:select>
								<input type = "hidden" name = "xxsh" value="<bean:write name = "rs"  property="xxsh"/>" />
							</td>
						</tr>
						</logic:equal>
						<logic:notEqual name="userType" value = "xy" scope = "session">
						<tr>
							<th align="right">
								<bean:message key="lable.xsgzyxpzxy" />��ˣ�
							</th>
							<td align="left">
								<html:select  name = "rs"  property="xysh" style="width:120px" styleId="xysh" disabled = "true">
										<html:option value=""></html:option>
										<html:option value="δ���">δ���</html:option>
										<html:option value="ͨ��">ͨ��</html:option>
										<html:option value="��ͨ��">��ͨ��</html:option>
								</html:select>
								<input type = "hidden" name = "xysh" value="<bean:write name = "rs"  property="xysh"/>" />
							</td>
							<th align="right">
								ѧУ��ˣ�
							</th>
							<td align="left">
								<html:select  name = "rs"  property="xxsh" style="width:120px" styleId="xxsh">
										<html:option value=""></html:option>
										<html:option value="δ���">δ���</html:option>
										<html:option value="ͨ��">ͨ��</html:option>
										<html:option value="��ͨ��">��ͨ��</html:option>
								</html:select>
							</td>
						</tr>
						</logic:notEqual>
						<tr>
							<th align="right">
								�����:
							</th>
							<td colspan="3">
								<html:textarea name = "rs" property="hjqk" style="width:99%"  rows="3"/>
							</td>
						</tr>
						<tr align="left">
							<th align="right">
								�ƻ���������
							</th>
							<td colspan="3">
								<html:textarea name = "rs" property="jhsxnr" style="width:99%" rows="5" />
							</td>
						</tr>
						
					</table>
					</div>
			</logic:notEmpty>
			<logic:present name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("����ɹ���");
    dialogArgumentsQueryChick();
	Close()
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("����ʧ�ܣ�");
    </script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
