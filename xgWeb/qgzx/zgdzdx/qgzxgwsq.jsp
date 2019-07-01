<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script language="javascript" src="/xgxt/dwr/interface/cqkjFunc.js"></script>
</head>
	<body>
		<html:form action="/data_search" method="post">
		<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" />
		<input type="hidden" id="disableEle" name="disableEle" value="xm-xb-xy-nj-zy-bj" />
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-xymc-nj-zymc-bjmc-kh" />
		<input type="hidden" id="pkValue" name="pkValue" value="xh-gwdm-gwsbsj"/>
		<input type="hidden" id="url" name="url" value="/post_stu_apply.do" />
		<input type="hidden" id="hmdMember" name="hmdMember" value="${hmdMember}" />
		<input type="hidden" id="doType" name="doType" value="${doType}" />
		<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>
		
		<logic:empty name="rs">
			<br />
			<br />
			<p align="center">
				�д�������
			</p>
		</logic:empty>
		<logic:notEmpty name="rs">
			<logic:equal name="rs" property="stuExists" value="no">
				<script>
   				alert("�������ѧ����Ч!");
   				</script>
			</logic:equal>
			<logic:equal name="sqsjFlag" value="1">
				<script>
   				alert("�����趨ʱ�䷶Χ��,�ݲ���������!");
   				location.href="about:blank";
   				</script>
			</logic:equal>
			<logic:equal name="gwExists" value="yes">
				<script>
   				alert("��ĿǰΪ�ٸ�״̬,�����ظ���������!");
   				location.href="about:blank";
   				</script>
			</logic:equal>
			<div class="tab">	
				<table width="100%" id="rsT" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��д�����</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<th><span class="red">*</span>ѧ��</th>
							<td>
								<html:text name='rs' property="xh" styleId="xh" readonly="true"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/stu_qgzx_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<th><span class="red">*</span>ѧ��</th>
							<td>
								<input type="hidden" name="xh" id="xh" value="${rs.xh}"/>
								<bean:write name='rs' property="xh" />
							</td>
						</logic:equal>
						<th>����</th>
						<td>
							<bean:write name='rs' property="xm" />
						</td>
					</tr>

					<tr>
						<th><span class="red">*</span>�������λ1</th>
						<td>
							<html:select name="rs" property="gwdmgwsbsj1" styleId="xmdm"
								style="width:150px">
								<html:option value=""></html:option>
								<html:options collection="gwList" property="gwdmgwsbsj"
									labelProperty="gwdm" />
							</html:select>
						</td>					
						<th>�������λ2</th>
						<td>
							<html:select name="rs" property="gwdmgwsbsj2" styleId="xmdm"
								style="width:150px">
								<html:option value=""></html:option>
								<html:options collection="gwList" property="gwdmgwsbsj"
									labelProperty="gwdm" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>�������λ3</th>
						<td colspan="3">
							<html:select name="rs" property="gwdmgwsbsj3" styleId="xmdm"
								style="width:150px">
								<html:option value=""></html:option>
								<html:options collection="gwList" property="gwdmgwsbsj"
									labelProperty="gwdm" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>������ò</th>
						<td>
<!--							<html:select property="zzmmdm" name="rs" styleId="zzmmdm">-->
<!--								<html:option value=""></html:option>-->
<!--								<html:options collection="zzmmList" labelProperty="zzmmmc" property="zzmmdm"/>-->
<!--							</html:select>								-->
							${rs.zzmmmc}
						</td>
						<th>��ͥסַ</th>
						<td>
							<bean:write name='rs' property="jtdz" />
						</td>
					</tr>
					<tr>
						<th>�Ա�</th>
						<td>
							<bean:write name='rs' property="xb" />
						</td>
						<th>ѧ��</th>
						<td>
							<html:text name="rs" property="xn" readonly="true"></html:text>
						</td>
					</tr>
					<tr>
						<th>�꼶</th>
						<td>
							<bean:write name='rs' property="nj" />
						</td>
						<th>ѧ��</th>
						<td>
							${rs.xqmc}
							<html:hidden name="rs" property="xq"></html:hidden>
						</td>
					</tr>
					<tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<bean:write name='rs' property="xymc" />
						</td>
						<th>���</th>
						<td>
							<html:text name="rs" property="nd" readonly="true"></html:text>
						</td>						
					</tr>
					<tr>
						<th>רҵ</th>
						<td>
							<bean:write name='rs' property="zymc" />
						</td>
						<th>�Ƿ�ƶ����</th>
						<td>
							<bean:write name='rs' property="sfpks" />
						</td>
					</tr>
					<tr>
						<th>�༶</th>
						<td>
							<bean:write name='rs' property="bjmc" />
						</td>
						<th>������ϵ�绰</th>
						<td>
							${rs.lxdh} 
						<html:hidden  name="rs" property="lxdh"  styleId="lxdh" />
						</td>
					</tr>
					<tr>
						<th>��������</th>
						<td colspan="3">
							<html:textarea name='rs' property='xssq' styleId="sqly"
								style="width:99%" rows='5' />
						</td>
					</tr>
					<tr>
						<th>�س�</th>
						<td colspan="3">
							<html:textarea name='rs' property='yhtc' styleId="yhtc"
								style="width:99%" rows='5' />
						</td>
					</tr>
					<tr>
						<th>����ʱ��</th>
						<logic:present name="kxList">
							<td colspan="3">
								<table border="0" cellpadding="0" cellspacing="0" class="formlist">
									<tr>
										<th>
											ʱ��
										</th>
										<th>
											����һ
										</th>
										<th>
											���ڶ�
										</th>
										<th>
											������
										</th>
										<th>
											������
										</th>
										<th>
											������
										</th>
										<th>
											������
										</th>
										<th>
											������
										</th>
									</tr>
									<logic:iterate id="kxsj" name="kxList">
										<tr>
											<td>
												${kxsj.sj}
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}1" value="��" />
												<input type="hidden" name="index${kxsj.sjIndex}1"
													value="${kxsj.mon}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}2" value="��" />
												<input type="hidden" name="index${kxsj.sjIndex}2"
													value="${kxsj.tue}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}3" value="��" />
												<input type="hidden" name="index${kxsj.sjIndex}3"
													value="${kxsj.wed}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}4" value="��" />
												<input type="hidden" name="index${kxsj.sjIndex}4"
													value="${kxsj.thu}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}5" value="��" />
												<input type="hidden" name="index${kxsj.sjIndex}5"
													value="${kxsj.fri}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}6" value="��" />
												<input type="hidden" name="index${kxsj.sjIndex}6"
													value="${kxsj.sat}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}7" value="��" />
												<input type="hidden" name="index${kxsj.sjIndex}7"
													value="${kxsj.sun}" />
											</td>

										</tr>
									</logic:iterate>
								</table>
									<script language="javascript">
										for(var i=0;i<7;i++){
											for(var j=1;j<8;j++){
											 	if(document.getElementById("index"+i+j)){
											 		if(document.getElementById("index"+i+j).value==1){
														document.getElementById(i+""+j).checked="checked";
												    }
											 	}
											}
										}
									</script>
							</td>
						</logic:present>
						<logic:notPresent name="kxList">
							<td height="22" colspan="3">
								<table id="tbSj" class="formlist">
									<tr>
										<th>
											ʱ��
										</th>
										<th>
											����һ
										</th>
										<th>
											���ڶ�
										</th>
										<th>
											������
										</th>
										<th>
											������
										</th>
										<th>
											������
										</th>
										<th>
											������
										</th>
										<th>
											������
										</th>
									</tr>
									<logic:iterate id="kxsj" name="whkxList">
										<tr>
											<td>
												${kxsj.sj}
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}1" value="1" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}2" value="1" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}3" value="1" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}4" value="1" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}5" value="1" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}6" value="1" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}7" value="1" />
											</td>
										</tr>
									</logic:iterate>
								</table>
							</td>
						</logic:notPresent>
					</tr>
					<tr>
						<td colspan="4">
							<table width="100%" class="formlist">
								<tr>
									<td width="20">���������˵�λ���</td>
									<td valign="bottom">ǩ�����£�<br/><br/>���ڣ�</td>
									<td width="20">�ڹ���ѧ�칫�����</td>
									<td valign="bottom">ǩ�����£�<br/><br/>���ڣ�</td>
								</tr>
							</table>
						</td>
					</tr>					
					<tr>
						<th>��ע</th>
						<td colspan="3">
							<html:textarea name='rs' property='bz' styleId="bz"
								cols="80" rows='5' />
						</td>
					</tr>
					</tbody>
					<tfoot>
				      <tr>
				        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
				          <div class="btn">
				           	<button type="button" class="button2" onclick="zgdzdx_sqgw_chkisDataExist('xh-xmdm');" id="btn_save">
								�ύ����
							</button>

							<logic:present name="zdy">
							<button type="button" class="button2"
								onclick="printReport('qgzx_bb_gwsqb.do?gwdm=')">
								��ӡԤ��
							</button>
							</logic:present>
				          </div>
				        </td>
				      </tr>
				    </tfoot>
				</table>
			  </div>				
			</logic:notEmpty>

			<logic:present name="hmdMember">
				<script>
				document.getElementById('btn_save').disabled=true;
				alert("���ѱ��������������ʱ�������룡");
				</script>
			</logic:present>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
				    alert("�����ɹ���");
				    Close();
				    if(window.dialogArguments){
						window.dialogArguments.document.getElementById('search_go').click();
					}
				    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
					var html_reason= '<bean:write name="reason"/>';
				    alert("����ʧ�ܣ�"+html_reason);
				    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>