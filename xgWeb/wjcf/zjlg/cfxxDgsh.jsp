<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="js/wjcfFuction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getStuDtiaInfo.js'></script>
		<script type="text/javascript" src="pjpy/pjpyjs/pjpy.js"></script>
	</head>
<body>
	<html:form action="/wjcfzjlgwh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
		<input type="hidden" name="failinfo" id="failinfo" value="${failinfo}"/>
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>Υ�ʹ��� - ������� - �����������</a>
			</p>
		</div>
		<div class="tab">
		<table class="formlist" width="99%">
			<thead>
				<tr style="height:23px">
					<td colspan="4">
						<span>�����������</span>
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<th width="15%">
					<font color="red">*</font>ѧ��
				</td>
				<td width="35%">
					<html:text property="xh" name="rs" styleId="xh" readonly="true"></html:text>
				</td>
				<th align="right" width="15%">
					ѧ��
				</th>
				<td align="left" width="35%">
					${rs.xn }
				</td>
			</tr>
			<tr style="height:23px">
				<th align="right">
					����
				</th>
				<td align="left">
					${rs.xm }
				</td>
				<th align="right">
					���
				</th>
				<td align="left">
					${rs.nd }
				</td>
			</tr>
			<tr style="height:23px">
				<th align="right">
					�Ա�
				</th>
				<td align="left">
					${rs.xb }
				</td>
				<th align="right">
					�������
				</th>
				<td align="left">
					${rs.cflbmc }
				</td>
			</tr>
			<tr style="height:23px">
				<th align="right">
					�꼶
				</th>
				<td align="left">
					${rs.nj }
				</td>
				<th align="right">
					����ԭ��
				</th>
				<td align="left">
					${rs.cfyymc }
				</td>
			</tr>
			<tr style="height:23px">
				<th align="right">
					<bean:message key="lable.xsgzyxpzxy" />
				</th>
				<td align="left">
					${rs.xymc }
				</td>
				<th align="right">
					���������򸽼�
				</th>
				<td align="left">
					<logic:notEmpty name="rs" property="fjsclj">
						<a title="�����ļ�����" href="downloadfilewj.do?len=&wjsclj=${rs.fjsclj }" target="_blank">����</a>
					</logic:notEmpty>
					<logic:empty name="rs" property="fjsclj">��</logic:empty>
				</td>
			</tr>
			<tr style="height:23px">
				<th align="right">
					רҵ
				</th>
				<td align="left">
					${rs.zymc }
				</td>
				<th align="right">
					<font color="red">*</font>�����ĺ�
				</th>
				<td align="left">
					<html:text property="cfwh" styleId="cfwh"></html:text>
				</td>
			</tr>
			<tr style="height:23px">
				<th align="right">
					�༶
				</th>
				<td align="left" colspan="">
					${rs.bjmc }
				</td>
				<th align="right">
					<font color="red">*</font>����ʱ��
				</th>
				<td align="left">
					<html:text property="cfsj" styleId="cfsj" onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('cfsj','y-mm-dd');" readonly="true"></html:text>
				</td>
			</tr>
			<tr style="height:23px">
				<th align="right">
					������ò
				</th>
				<td align="left" colspan="">
					${rs.zzmmmc }
				</td>
				<th align="right">
					Υ��ʱ��
				</th>
				<td align="left">
					${rs.wjsj }
				</td>
			</tr>
			
			<!-- ��ʷ������Ϣ -->
<%--			<tr>--%>
<%--				<td align="right" colspan="4">--%>
<%--					<table width="100%" border="1" class="formlist">--%>
<%--						<thead>--%>
<%--							<tr>--%>
<%--								<th>--%>
<%--									<span>��ʷΥ�ʹ�����Ϣ</span>--%>
<%--								</th>--%>
<%--							</tr>--%>
<%--						</thead>--%>
<%--					</table>--%>
<%--				</td>--%>
<%--			</tr>--%>
			<tr>
				<td colspan="4">
					<div id="child4" style="display:none">
						<table width="100%" border="1" align="center" class="dateline">
						<thead>
							<tr>
								<td align="center" width="80px">
									ѧ��
								</td>
								<td align="center" width="110px">
									�������
								</td>
								<td align="center" width="110px">
									����ԭ��
								</td>
								<td align="center" width="80px">
									����ʱ��
								</td>
								<td align="center" width="110px">
									�����ĺ�
								</td>
								<td align="center" width="80px">
									Υ��ʱ��
								</td>
								<td align="center">
									Υ����ʵ
								</td>
							</tr>
							</thead>
							<!-- ������ͨ��DWR���е��õ� -->
							<tbody id="cfxx" align="center"></tbody>
						</table>
					</div>
				</td>
			</tr>
			
			<logic:equal value="yes" name="lxck" >
				<tr style="height:23px">
				<th>
					��У�鿴<br/>���ʱ��
				</th>
				<td align="left" colspan="">
					<html:text property="lxcksj" styleId="lxcksj" onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('lxcksj','y-mm-dd');" readonly="true"></html:text>
				</td>
				<td align="right">
					
				</td>
				<td align="left">
				
				</td>
			</tr>
			</logic:equal>
			<tr style="height:23px">
				<th>
					Υ����ʵ
				</th>
				<td align="left" colspan="3">
					<html:textarea property="bz" name="rs" rows="5" styleId="bz" style="width:98%" disabled="true"></html:textarea>
				</td>
			</tr>
<%--			<tr style="height:23px">--%>
<%--				<td align="right">--%>
<%--					��ʷΥ�ͼ�¼--%>
<%--				</td>--%>
<%--				<td align="left" colspan="3">--%>
<%--					<html:textarea property="lswjjl" name="rs" rows="5" styleId="lswjjl" style="width:98%" disabled="true"></html:textarea>--%>
<%--				</td>--%>
<%--			</tr>--%>
			<tr style="height:23px">
				<th>
					ϵԺ���
				</th>
				<td align="left" colspan="3">
					<html:textarea property="xyclyj" name="rs" rows="3" styleId="xyclyj" style="width:98%" disabled="true"></html:textarea>
				</td>
			</tr>
			<tr style="height:23px">
					<logic:equal value="xy" name="userType">
						<th>
							<bean:message key="lable.xsgzyxpzxy" />�������
						</th>
						<td align="left" colspan="3">
							<html:textarea property="xyyj" rows="5" styleId="xyyj" style="width:98%" ></html:textarea>
						</td>
					</logic:equal>
					<logic:notEqual value="xy" name="userType">
						<th>
							ѧУ�������
						</th>
						<td align="left" colspan="3">
							<html:textarea property="xxclyj" rows="5" styleId="xxclyj" style="width:98%" ></html:textarea>
						</td>
					</logic:notEqual>
			</tr>
			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
		          <div class="btn">
					 <logic:notEqual value="no" name="writable">
						<button type="button" name="�ύ" id="btn_save" onclick="saveinfo('wjcf_zjlg_cfxxdgsh.do?operType=save','cfwh-cfsj');">����</button>
					  </logic:notEqual>
					  <button type="button" name="�ر�" onclick="window.close();return false;">�ر�</button>
		          </div></td>
		      </tr>
		    </tfoot>
		</table>
		</div>
				<!-- �������ʾҳ�� -->	
		<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>
