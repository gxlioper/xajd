<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script type="text/javascript" src="js/xgutil.js"></script>
	<body>
		<html:form action="/stu_archives_history">
			<input type="hidden" name="url" id="url" value="/stu_archives_history.do?act=ljs_archive&doType=add&type=add"/>
			<input type="hidden" value="xh-xm-xb-nj-xymc-zymc-bjmc-sfzh-csrq-zzmmmc" id="getStuInfo" name="getStuInfo" />
			<input type="hidden" value="${oper}" id="oper" name="oper" />
			<input type="hidden" value="${xxdm}" id="xxdm" name="xxdm" />
			<input type="hidden" value="${userType}" id="userType" name="userType" />
			<%--����ְҵ����ѧԺ--%>
			<logic:equal value="10863" name="xxdm">
				<input type="hidden" value="xh" id="notnull" name="notnull" />
			</logic:equal>
			<%--end����ְҵ����ѧԺ--%>

			<%--����ʦ��ѧԺ--%>
			<logic:equal value="10477" name="xxdm">
				<input type="hidden" value="xh" id="notnull" name="notnull" />
			</logic:equal>
			<%--end����ʦ��ѧԺ--%>
			
			<!--�й�����ѧԺ-->
			<logic:equal value="10355" name="xxdm">
				<input type="hidden" value="xh-zddwmc-zysj-dasfzx-zdyy" id="notnull" name="notnull" />
			</logic:equal>
			<!--end�й�����ѧԺ-->

			<%--����--%>
			<logic:notEqual value="10863" name="xxdm">
			<logic:notEqual value="10355" name="xxdm">				
			<logic:notEqual value="10477" name="xxdm">
				<input type="hidden" value="xh-zddwmc-zysj-dasfzx-zdyy" id="notnull" name="notnull" />
			</logic:notEqual>
			</logic:notEqual>
			</logic:notEqual>

			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>ѧ����Ϣ - ѧ������ - ����ѧ������ - ά��</a>
				</p>
			</div>
			<logic:notEmpty name="msg">
				<center>
					<bean:write name="msg" />
				</center>
			</logic:notEmpty>

			<logic:empty name="msg">
				<div class="tab">
		  		<table width="100%" class="formlist">
					<thead>
						<tr>
							<th align="center" colspan="4">
								<span>����ѧ������</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<th><span class="red">*</span>ѧ��</th>
						<td>
							<logic:equal value="update" name="oper">
								<html:text name="rs" property="xh" styleId="xh" readonly="true" />
							</logic:equal>

							<logic:notEqual value="update" name="oper">
								<html:text name="rs" property="xh" styleId="xh"
									onkeypress="if(event.keyCode == 13) autoFillStuInfo2(this);" />
								<!--���й�����ѧԺ>-->
								<logic:notEqual value="10355" name="xxdm">
								<button class="btn_01"
									onclick="showTopWin('/xgxt/stu_info.do?oper=${oper}',750,550);"
									id="buttonFindStu">
									ѡ��
								</button>
								</logic:notEqual>
								<!--end���й�����ѧԺ-->

								<!--�й�����ѧԺ-->
								<logic:equal value="10355" name="xxdm">
									<button class="btn_01"
										onclick="showTopWin('/xgxt/stu_info.do?oper=<bean:write name="oper"/>&by=by',750,550);"
										style="width:20px" id="buttonFindStu">
										ѡ��
									</button>
								</logic:equal>
								<!--end�й�����ѧԺ-->
							</logic:notEqual>
						</td>
						<th>�꼶</th>
						<td>
							${rs.nj}
						</td>
					</tr>
					<tr>
						<th>����</th>
						<td>
							${rs.xm}
						</td>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							${rs.xymc}
						</td>
					</tr>
					<tr>
						<th>�Ա�</th>
						<td>
							${rs.xb}
						</td>
						<th>רҵ����</th>
						<td>
							${rs.zymc}
						</td>
					</tr>
					<tr>
						<th>��������</th>
						<td>
							${rs.csrq}
						</td>
						<th>�༶����</th>
						<td>
							${rs.bjmc}
						</td>
					</tr>
					<tr>
						<th>���֤��</th>
						<td>
							${rs.sfzh}
						</td>
						<th>ѧ��</th>
						<td>
							${rs.xz}
						</td>
					</tr>
					<tr>
						<th>����</th>
						<td>
							${rs.mzmc}
						</td>
						<th>������ò</th>
						<td>
							${rs.zzmmmc}
						</td>
					</tr>
					<%--����ʦ��--%>
					<logic:equal value="10477" name="xxdm">
						<%@ include file="/shgc/stu_info/xysfxy/stu_archives.jsp"%>
					</logic:equal>

					<%--������ʦ��--%>
					<logic:notEqual value="10477" name="xxdm">
					<tr>
						<th>��ҵʱ��</th>
						<td>
						<!--�й�����ѧԺ-->
						<logic:equal value="10355" name="xxdm">						
							${rs.bynf}					
						</logic:equal>
						<!--end�й�����ѧԺ-->
						
						<!--���й�����ѧԺ-->
						<logic:notEqual value="10355" name="xxdm">
							<html:text name="rs" styleId="bynf" property="bynf"
								onclick="return showCalendar('bynf','y-mm-dd');" />
						</logic:notEqual>
						<!--end���й�����ѧԺ-->
						</td>
						<%--����ְҵ����ѧԺ--%>
						<logic:equal value="10863" name="xxdm">
							<th>�����Ƿ���У</th>
						</logic:equal>
						<%--end����ְҵ����ѧԺ--%>

						<%--������ְҵ����ѧԺ--%>
						<logic:notEqual value="10863" name="xxdm">
							<th><span class="red">*</span>�����Ƿ���У</th>
						</logic:notEqual>
						<%--end������ְҵ����ѧԺ--%>
						<td>
							<html:select name="rs" property="dasfzx" styleId="dasfzx"
								style="width:160px">
								<html:option value="����У">����У</html:option>
								<html:option value="��У">��У</html:option>
							</html:select>
						</td>
					</tr>
					<tr>
						<th>������Դ</th>
						<td>
							<html:text name="rs" styleId="daly" property="daly" style="width:95%" maxlength="100" />
						</td>
						<th>����ȥ��</th>
						<td>
							<html:text name="rs" styleId="daqx" property="daqx" style="width:95%" maxlength="25" />
						</td>
					</tr>
					<tr>
						<%--����ְҵ����ѧԺ--%>
						<logic:equal value="10863" name="xxdm">
							<th>ת����λ����</th>
						</logic:equal>
						<%--end����ְҵ����ѧԺ--%>

						<%--������ְҵ����ѧԺ--%>
						<logic:notEqual value="10863" name="xxdm">
							<th><span class="red">*</span>ת����λ����</th>
						</logic:notEqual>
						<%--end������ְҵ����ѧԺ--%>

						<td>
							<html:text name="rs" styleId="zddwmc" property="zddwmc"
								maxlength="50" />
						</td>
						<th>ת����λ��ַ</th>
						<td>
							<html:text name="rs" property="zddwdz" styleId="xydm" maxlength="50" />
						</td>
					</tr>
					<logic:equal name="xxdm" value="11355">
					<tr>
						<th>��Ҫ��</th>
						<td colspan="3">
							<html:text name="rs" styleId="jyh" property="jyh" style="width:100%" maxlength="30" />
						</td>
					</tr>
					</logic:equal>					
					<tr style="height:30px">
						<%--����ְҵ����ѧԺ--%>
						<logic:equal value="10863" name="xxdm">
							<th>ת��ԭ��</th>
							<td>
								<html:select property="zdyy" name="rs">
								<html:option value=""></html:option>
								<html:option value="��ѧ"></html:option>
								<html:option value="��ҵ"></html:option>
								<html:option value="��ѧ"></html:option>
								<html:option value="����"></html:option>
								</html:select>							
							</td>
						</logic:equal>
						<%--end����ְҵ����ѧԺ--%>

						<%--������ְҵ����ѧԺ--%>
						<logic:notEqual value="10863" name="xxdm">
							<th><span class="red">*</span>ת��ԭ��</th>
							<td>
								<html:text name="rs" styleId="zdyy" property="zdyy" maxlength="100" />
							</td>
						</logic:notEqual>
						<%--����ְҵ����ѧԺ--%>
						<logic:equal value="10863" name="xxdm">
							<th>ת��ʱ��</th>
						</logic:equal>
						<%--end����ְҵ����ѧԺ--%>

						<%--������ְҵ����ѧԺ--%>
						<logic:notEqual value="10863" name="xxdm">
							<th><span class="red">*</span>ת��ʱ��</th>
						</logic:notEqual>
						<%--end������ְҵ����ѧԺ--%>
						<td>
							<html:text name="rs"
								onclick="return showCalendar('zysj','y-mm-dd');" property="zysj"
								styleId="zysj" readonly="true" />
						</td>
					</tr>
					<tr style="height:30px">
						<th>ת����λ�ʱ�</th>
						<td>
							<html:text name="rs" property="zddwyb" styleId="zddwyb"
								onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="10"/>
						</td>
						<th>��ִ��</th>
						<td>
							<html:text name="rs" property="hzh" styleId="hzh" maxlength="25" />
						</td>
					</tr>
					<tr>
						<th>ת����ʽ</th>
						<td>
							<html:text name="rs" property="zdfs" styleId="zdfs"
								maxlength="50" />
						</td>
						<th>��ϵ�绰</th>
						<td>
							<html:text name="rs" property="lxdh" styleId="lxdh"
								maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'') " />
						</td>
					</tr>
					<!--���ս�����ҵѧԺ-->
					<logic:present name="isAHJZGYXY">
						<logic:equal value="is" name="isAHJZGYXY">
							<tr>
								<th>�߿��ɼ�</th>
								<td>
									<html:text name="rs" styleId="gkcj" property="gkcj"
										onkeypress="return sztzNumInputValue(this,5,event)"
										onblur="chkInput(this,event)" />
								</td>
								<th>�߿�Ӣ��ɼ�</th>
								<td>
									<html:text name="rs" property="gkyycj" styleId="gkyycj"
										onkeypress="return sztzNumInputValue(this,5,event)"
										onblur="chkInput(this,event)" />
								</td>
							</tr>
							<tr>
								<th>��ѧӢ��ˮƽ</th>
								<td>
									<html:text name="rs" maxlength="15" styleId="dxyysp"
										property="dxyysp" />
								</td>
								<th>�����ˮƽ</th>
								<td>
									<html:text name="rs" maxlength="15" property="jsjsp"
										styleId="jsjsp" />
								</td>
							</tr>
							<tr>
								<th>����</th>
								<td>
									<html:text name="rs" styleId="jg" property="jg" maxlength="15" />
								</td>
								<th>���޴����¼</th>
								<td>
									<html:select name="rs" property="dkjl" styleId="dkjl"
										style="width:160px">
										<html:options collection="dkjlList" property="dkjl"
											labelProperty="dkjl" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>����༶����</th>
								<td colspan="3">
									<logic:empty name="rs" property="snbjpm">
										<html:textarea property='snbjpm' style="width:99%" rows='2'
											value="��һ���� ����������� ������������ �������ģ��� ��" />
									</logic:empty>
									<logic:notEmpty name="rs"property="snbjpm">
										<html:textarea name="rs" property='snbjpm' style="width:99%"
											rows='2' />
									</logic:notEmpty>
								</td>
							</tr>
						</logic:equal>
					</logic:present>
					<!--end���ս�����ҵѧԺ-->
					
					<!--���ݴ�ѧ-->
					<logic:equal value="10657" name="xxdm">
						<tr>
							<th>��Ҫ��</th>
							<td>
								<html:text name="rs" maxlength="15" styleId="jyh" property="jyh" />
							</td>
							<th>����������</th>
							<td>
								<html:text name="rs" maxlength="15" property="daclr" styleId="daclr" />
							</td>
						</tr>
						<tr>
							<th>������������ϵ��ʽ</th>
							<td colspan="3" >
								<html:text name="rs" maxlength="50" styleId="daclrlxfs"  property="daclrlxfs" />
							</td>
						</tr>
					</logic:equal>
					<!--���ݴ�ѧ-->
					</logic:notEqual>
					<!--��������-->
				<%@ include file="/xsxx/fjgcxy/ljxsda_fjgcxy.jsp"%>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
						<logic:notEqual value="true" name="view">
			            <button class="button2"
							onclick="historyArchivesSave('/xgxt/stu_archives_history.do?doType=savaArchive&type=')"
							style="width:80px">
							�� ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:notEqual>
						<button class="button2" onclick="window.close();return false;"
							style="width:80px">
							�� ��
						</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
			</logic:empty>

			<logic:notEmpty name="result">
				<logic:equal value="true" name="result">
					<script>
					alert("�����ɹ���");
					Close();
					if(window.dialogArguments){
						window.dialogArguments.document.getElementById('search_go').click();
					}		
				</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
					alert("����ʧ�ܣ�");
					Close();
				</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
