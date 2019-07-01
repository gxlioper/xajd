<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
</head>
<body>
	<html:form action="/stu_archives_history">
		<input type="hidden" name="url" id="url" value="/shgc/stu_info/stu_archives_info.jsp"/>
		<input type="hidden" name="variable" id="variable" value="ydinfo"/>
		<input type="hidden" name="redirect" id="redirect" value="true"/>
		<logic:equal value="10856" name="xxdm" scope="session">
			<input type="hidden" name="page" id="redirect" value="archives" />
		</logic:equal>
		<logic:notEqual value="10856" name="xxdm" scope="session">
			<input type="hidden" name="page" id="redirect" value="xsxx"/>
		</logic:notEqual>
		<input type="hidden" value="${oper}" id="oper" />
		<%--����ְҵ����ѧԺ--%>
		<logic:equal value="10863" name="xxdm">
			<input type="hidden" value="xh" id="notnull" name="notnull" />
		</logic:equal>
		<%--end����ְҵ����ѧԺ--%>

		<%--������ְҵ����ѧԺ--%>
		<logic:notEqual value="10863" name="xxdm">
			<input type="hidden" value="xh-zddwmc-zysj" id="notnull"
				name="notnull" />
		</logic:notEqual>
		<%--end������ְҵ����ѧԺ--%>

		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>ѧ����Ϣ����Уѧ������������ά��</a>
			</p>
		</div>
		<logic:notEmpty name="message">
			<center>
				${message}
			</center>
		</logic:notEmpty>

		<logic:empty name="message">
		<div class="tab">
	 	 <table width="100%" border="0" class="formlist">
			<thead align="center">
				<tr>
					<th colspan="4">
						<span>��Уѧ������</span>
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
						<logic:equal value="add" name="oper">
							<html:text name="rs" property="xh" styleId="xh"
								onkeypress="if(event.keyCode == 13) autoFillStuInfo2(this);" />
							<button class="btn_01"
								onclick="showTopWin('/xgxt/stu_info.do?oper=<bean:write name="oper"/>',750,550);"
								id="buttonFindStu">
								ѡ��
							</button>
							<%--�Ϻ����̼�����ѧ--%>
							<logic:equal value="10856" name="xxdm">
								<button align="left" class="button2"
									onclick="checkArchivesExist()" style="width:85px"
									id="buttonCheck">
									����ܷ�ת��
								</button>
							</logic:equal>
						</logic:equal>
					</td>
					<th></th>
					<td rowspan="5">
					<logic:equal value="�㽭��ҵְҵ����ѧԺ" name="xxmc" scope="session">
							<img border="0" style="height:133px;width:100px;" src="/xgxt/pictures/${rs.sfzh}.jpg"/>
					</logic:equal>
					<logic:notEqual value="�㽭��ҵְҵ����ѧԺ" name="xxmc" scope="session">
						<logic:equal value="12872" name="xxdm">
							<logic:present name="showsfzh">
								<img border="0" style="height:133px;width:100px;" src="/xgxt/pictures/${rs.sfzh}.jpg"/>
							</logic:present>
							<logic:present name="showxh">
								<img border="0" style="height:133px;width:100px;" src="/xgxt/pictures/${rs.xh}.jpg"/>
							</logic:present>
						</logic:equal>
						<logic:notEqual value="12872" name="xxdm">
							<%--�㽭����ְҵ����ѧԺ--%>
							<logic:equal value="12861" name="xxdm">
								<img src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh}"
									border="0" style="width:140;height:160" />
							</logic:equal>
							<%--end�㽭����ְҵ����ѧԺ--%>

							<%--���㽭����ְҵ����ѧԺ--%>
							<logic:notEqual value="12861" name="xxdm">
								<%--�ǽ�����Ϣְҵ����ѧԺ--%>
								<logic:notEqual value="13108" name="xxdm">
									<img src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh}" border="0" align="absmiddle" style="width:140;height:160" />
								</logic:notEqual>
								<%--������Ϣְҵ����ѧԺ--%>
								<logic:equal value="13108" name="xxdm">
										<img border="0" style="height:133px;width:100px;" src="/xgxt/pictures/${rs.sfzh}.jpg"/>
								</logic:equal>
							</logic:notEqual>
							<%--end���㽭����ְҵ����ѧԺ--%>
						</logic:notEqual>
					</logic:notEqual>
					</td>
				</tr>

				<tr>
					<th>����</th>
					<td>
						${rs.xm}
						<input type="hidden" name="xm" id="xm" value="${rs.xm}" />
					</td>
					<th></th>
				</tr>
				<tr>
					<th>�Ա�</th>
					<td>
						${rs.xb}
						<input type="hidden" name="xb" id="xb" value="${rs.xb}" />
					</td>
					<th></th>
				</tr>					
				<tr>
					<th>ѧ��</th>
					<td>
						${rs.xz}
					</td>
					<th></th>
				</tr>
				<tr>
					<th>��������</th>
					<td>
						${rs.csrq}
						<input type="hidden" name="csrq" id="csrq" value="${rs.csrq}" />
					</td>
					<th></th>
				</tr>
				<tr>
					<th>����</th>
					<td>
						${rs.mzmc}
						<input type="hidden" name="mz" id="mz" value="${rs.mz}" />
					</td>
					<th>������ò</th>
					<td>
						${rs.zzmmmc}
					</td>
				</tr>
				<tr>
					<th>���֤��</th>
					<td>
						${rs.sfzh}
						<input type="hidden" name="sfzh" id="sfzh" value="${rs.sfzh}" />
					</td>
					<th>��ϵ�绰</th>
					<td>
						${rs.lxdh}
						<input type="hidden" name="lxdh" id="lxdh" value="${rs.lxdh}" />
					</td>
				</tr>
				<tr>
					<th>�꼶</th>
					<td>
						${rs.nj}
					</td>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						${rs.xymc}
						<input type="hidden" name="xydm" id="xydm" value="${rs.xydm}" />
					</td>
				</tr>
				<tr>
					<th>רҵ����</th>
					<td>
						${rs.zymc}
						<input type="hidden" name="zydm" id="zydm"
							value="${rs.zydm}" />
					</td>
					<th>�༶����</th>
					<td>
						${rs.bjmc}
						<input type="hidden" name="bjdm" id="bjdm" value="${rs.bjdm}" />
					</td>
				</tr>
				<%--����ʦ��--%>
				<logic:equal value="10477" name="xxdm">
					<%@ include file="/shgc/stu_info/xysfxy/stu_archives_info.jsp"%>
				</logic:equal>

				<%--������ʦ��--%>
				<logic:notEqual value="10477" name="xxdm">
				<!--���ݴ�ѧ-->
				<logic:notEqual value="10657" name="xxdm">
				<tr style="height:30px">
					<%--����ְҵ����ѧԺ--%>
					<logic:equal value="10863" name="xxdm">
						<th>ת��ԭ��</th>
						<td>
							<html:select property="zdyy" name="rs">
								<html:option value=""></html:option>
								<html:option value="��ѧ">��ѧ</html:option>
								<html:option value="��ҵ">��ҵ</html:option>
								<html:option value="��ѧ">��ѧ</html:option>
								<html:option value="����">����</html:option>
							</html:select>
						</td>
					</logic:equal>
					<%--end����ְҵ����ѧԺ--%>

					<%--������ְҵ����ѧԺ--%>
					<logic:notEqual value="10863" name="xxdm">
						<th><span class="red">*</span>ת��ԭ��</th>
						<td>
							<html:text name="rs" property="zdyy" styleId="zdyy"
								maxlength="100" />
						</td>
					</logic:notEqual>
					<%--end������ְҵ����ѧԺ--%>

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
						<html:text name="rs" property="zddwmc" styleId="zddwmc"
							maxlength="50" />
					</td>
					<th>ת����λ��ַ</th>
					<td>
						<html:text name="rs" property="zddwdz" styleId="zddwdz"
							maxlength="50" />
					</td>
				</tr>
				<%--�㽭����ְҵ����ѧԺ--%>
				<logic:equal value="12866" name="xxdm">
					<tr>
						<th>������Դ</th>
						<td colspan="3">
							<html:text name="rs" property="daly" styleId="daly"
								style="width:100%" maxlength="100" />
						</td>
					</tr>
				</logic:equal>	
				<tr style="height:30px">
					<th>ת����λ�ʱ�</th>
					<td>
						<html:text name="rs" property="zddwyb" styleId="zddwyb"
							maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'') " />
					</td>
					<%--����ְҵ����ѧԺ--%>
					<logic:equal value="10863" name="xxdm">
						<th>���е����Ƿ���У</th>
					</logic:equal>
					<%--end����ְҵ����ѧԺ--%>

					<%--������ְҵ����ѧԺ--%>
					<logic:notEqual value="10863" name="xxdm">
						<th><span class="red">*</span>���е����Ƿ���У</th>
					</logic:notEqual>
					<%--end������ְҵ����ѧԺ--%>
					<td>
						<html:select property="gzdasfzx" styleId="gzdasfzx" name="rs">
							<html:option value="����У">����У</html:option>
							<html:option value="��У">��У</html:option>
						</html:select>
					</td>
				</tr>										
				<tr>
					<th>������</th>
					<td>
						<html:text name="rs" property="dah" styleId="dah" maxlength="32" />
					</td>
					<th>��������λ��</th>
					<td>
						<html:text name="rs" property="dabcwz" styleId="dabcwz"
							maxlength="112" />
					</td>
				</tr>
				<tr>
					<th>����������</th>
					<td>
						<html:text name="rs" property="jsr" styleId="jsr" maxlength="10" />
					</td>
					<th>
					<%--��������ѧԺ--%>
					<logic:equal value="10388" name="xxdm">
						����ת����
					</logic:equal>
					<%--end��������ѧԺ--%>
					<logic:notEqual value="10388" name="xxdm">
						����������
					</logic:notEqual>					
					</th>
					<td>
						<html:text name="rs" property="glr" styleId="glr" maxlength="10" />
					</td>					
				</tr>
				<tr>
					<th>��������</th>
					<td colspan="3">
						<html:text name="rs" property="jsnr" styleId="jsnr" style="height:45;width:90%" maxlength="112" />
					</td>
				</tr>	
				</logic:notEqual>	
				<!--���ݴ�ѧ-->
				<logic:equal value="10657" name="xxdm">
				<tr>
						<th>�������</th>
						<td>
							<html:text name="rs" property="dah" styleId="dah" maxlength="32" />
						</td>
						<th>������ŵ�</th>
						<td>
							<html:text name="rs" property="dabcwz" styleId="dabcwz" maxlength="112" />
						</td>
				</tr>
				<tr>					
					<th>������Ϣ</th>
					<td colspan="3">
						<html:text name="rs" property="jsnr" styleId="jsnr" style="height:45;width:90%" maxlength="112" />
					</td>
				</tr>
				<tr>
					<th>�����Ǽ�</th>
					<td colspan="3">
						<logic:iterate name="dadjList" id="s">							
								<input type="checkbox" name="dadj"
									<logic:iterate id="v" name="s" offset="2" length="1">${v}</logic:iterate>	
									 value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" 
									  ><logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>
								
						</logic:iterate>
					</td>
				</tr>
				</logic:equal>	
				<!--end���ݴ�ѧ-->
				<!--��������-->
				<%@ include file="/xsxx/fjgcxy/zxxsda_fjgcxy.jsp"%>
				</logic:notEqual>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
		          <div class="btn">
		            <logic:notEqual value="student" name="user">
					<logic:notEqual value="true" name="view">
					<button class="button2"
						onclick="archiveSave('/xgxt/stu_archives_now.do?doType=saveArchives&type=');"
						style="width:80px">
						�� ��
					</button>
					</logic:notEqual>
					</logic:notEqual>
					<button class="button2" onclick="Close();return false;"
						style="width:80px">
						�� ��
					</button>					
		          </div>
		        </td>
		      </tr>
		    </tfoot>
		</table>
		</div>
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
				</script>
			</logic:equal>
		</logic:notEmpty>
	</html:form>
</body>
</html>
