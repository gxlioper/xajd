<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="js/wjcfFuction.js"></script>
</head>
<body>
	<html:form action="/wjcfzjlgwh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
		<input type="hidden" name="failinfo" id="failinfo" value="${failinfo}"/>
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>Υ�ʹ��� - �����У�쿴 - ��� - �������</a>
			</p>
		</div>

		<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�������</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<!-- ���ƶ���ѧУ��˵�<bean:message key="lable.xsgzyxpzxy" />�����ٲ��� -->
									<logic:notEqual value="no" name="writable">
									<button type="button" class="" id="btn_save" 
										onclick="saveinfo('wjcf_zjlg_LxckxxDgsh.do?operType=save','');">
										�� ��
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:notEqual>
									<button type="button" class="" id="btn_close" onclick="window.close();return false;" >
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
			<tr style="height:23px">
				<th align="right" width="15%">
					ѧ��
				</th>
				<td align="left" width="35%">
					${rs.xh }
				</td>
				<th align="right" width="15%">
					<font color="red">*</font>ѧ��
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
					<font color="red">*</font>���
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
					����ѧ�����
				</th>
				<td align="left">
					${rs.cfxn }/${rs.cfnd }
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
					�����ĺ�
				</th>
				<td align="left">
					${rs.cfwh }
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
					����ʱ��
				</th>
				<td align="left">
					${rs.cfsj }
				</td>
			</tr>
		<tr style="height:23px">
				<th align="right">
					������У�쿴ʱ��
				</th>
				<td align="left" colspan="">
					${rs.lxcksj }
				</td>
				<th align="right">
					���
				</th>
				<td align="left">
					<html:select property="sh" styleId="sh">
						<html:options collection="shList" property="en" labelProperty="cn"/>
					</html:select>
				</td>
			</tr>
		<logic:notEqual value="xy" name="userType">
			<tr style="height:23px">
				<th align="right">
					�����У�쿴ʱ��
				</th>
				<td align="left" colspan="">
					<html:text property="jcsj" styleId="jcsj" onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('jcsj','y-mm-dd');" readonly="true"></html:text>
				</td>
				<th align="right">
					�����У�쿴�ĺ�
				</th>
				<td align="left">
					<html:text property="jcwh" styleId="jcwh"></html:text>
				</td>
			</tr>
			<tr style="height:23px">
				<th align="right">
					�����У�쿴���
				</th>
				<td align="left" colspan="">
					<html:select property="jcjg" styleId="jcjg">
						<html:option value=""></html:option>
						<html:options collection="jcList" property="en" labelProperty="cn"/>
					</html:select>
				</td>
				<th align="right">
					
				</th>
				<td align="left">
					
				</td>
			</tr>
		</logic:notEqual>
		<tr style="height:23px">
				<th align="right">
					��У�쿴�ڼ����
				</th>
				<td align="left" colspan="3">
					<html:textarea property="xsbx" styleId="xsbx" name="rs" rows="7" style="width:95%" disabled="true"></html:textarea>
				</td>
			</tr>
		<logic:equal value="11647" name="xxdm">
			<tr style="height:23px">
				<th align="right">
					������Ҫ��ǰ<br/>�����У�쿴��<br/>����Ҫ��ǰ��<br/>����ע�����ɣ�
				</th>
				<td align="left" colspan="3">
					<html:textarea property="tqjcly" styleId="tqjcly" rows="5" name="rs" style="width:95%" disabled="true"></html:textarea>
				</td>
		</tr>
		</logic:equal>
		<logic:equal value="xy" name="userType">
			<tr style="height:23px">
				<th align="right">
					���������
				</th>
				<td align="left" colspan="3">
					<html:textarea property="fdyjdyj" styleId="fdyjdyj" rows="5" style="width:95%" ></html:textarea>
				</td>
		</tr>
		</logic:equal>
		<tr style="height:23px">
				<th align="right">
					<logic:equal value="xy" name="userType"><logic:notEqual value="true" name="isFdy"><bean:message key="lable.xsgzyxpzxy" /></logic:notEqual></logic:equal><logic:notEqual value="xy" name="userType">ѧУ</logic:notEqual>������
				</th>
				<td align="left" colspan="3">
					<html:textarea property="yj" styleId="yj" rows="5" style="width:95%" ></html:textarea>
				</td>
			</tr>
			</tbody>
		</table>
		</div>
		<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>
