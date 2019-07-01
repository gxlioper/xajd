<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/stuinfoFunction.js"></script>
</head>
<body>
	<html:form action="/studentMessage_conf" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>ѧ����Ϣ - ��������</a>
			</p>
		</div>	
		<div class="tab">
			<table width="80%" class="formlist">
				<thead>
					<tr>
						<th colspan="2">
							<span>ѧ����Ϣ��������</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th><span class="red">*</span>��ǰ���</th>
					<td>
						<html:select name="rs" property="nd">
							<html:options collection="xnList" property="nd"
								labelProperty="nd" />
						</html:select>
					</td>						
				</tr>
				<tr>
					<th><span class="red">*</span>��ǰѧ��</th>
					<td>							
						<html:select name="rs" property="xn">
							<html:options collection="xnList" property="xn"
								labelProperty="xn" />
						</html:select>
					</td>						
				</tr>
				<tr>
					<th><span class="red">*</span>��ǰѧ��</th>
					<td>
						<html:select name="rs" property="xq">
							<html:options collection="xqList" property="xqdm"
								labelProperty="xqmc" />
						</html:select>
					</td>						
				</tr>
				<tr>
					<th><span class="red">*</span>�Ƿ���Ҫʱ������</td>
					<td>
						<html:select name="rs" property="isSz">
							<html:option value="no">��</html:option>
							<html:option value="yes">��</html:option>
						</html:select>
					</td>						
				</tr>
				<tr>
					<th><span class="red">*</span>ѧ���޸���Ϣ��ʼʱ��</th>
					<td>
						<html:text name="rs" property="kssj" onclick="return showCalendar('kssj','y-mm-dd');" readonly="true" size="10" styleId="kssj"></html:text>
						&nbsp;<html:text name="rs" property="kssjH" size="2"  styleId="kssjH" maxlength="2"></html:text>:
						<html:text name="rs" property="kssjM" size="2"  styleId="kssjM" maxlength="2"></html:text>:
						<html:text name="rs" property="kssjS" size="2" styleId="kssjS" maxlength="2"></html:text>
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>ѧ���޸���Ϣ����ʱ��</th>
					<td>
					 	<html:text name="rs" property="jssj" onclick="return showCalendar('jssj','y-mm-dd');" readonly="true" size="10" styleId="jssj"></html:text>
						&nbsp;<html:text name="rs" property="jssjH" size="2" styleId="jssjH" maxlength="2"></html:text>:
						<html:text name="rs" property="jssjM" size="2"   styleId="jssjM" maxlength="2"></html:text>:
						<html:text name="rs" property="jssjS" size="2"  styleId="jssjS" maxlength="2"></html:text>
					</td>
				</tr>	
				<tr>
					<th>�ɼ����浥����ʱ��</th>
					<td>
					 	<html:text name="rs" property="cjbgdffsj" onclick="return showCalendar('cjbgdffsj','y-mm-dd');" readonly="true" styleId="cjbgdffsj"></html:text>
					</td>
				</tr>
				<tr>
					<th>��ѧʱ��</th>
					<td>
					 	<html:text name="rs" property="kxsj" onclick="return showCalendar('kxsj','y-mm-dd');" readonly="true" styleId="kxsj"></html:text>
					</td>
				</tr>
				<tr>
					<th>����ʱ��</th>
					<td>
					 	<html:text name="rs" property="bdsj" onclick="return showCalendar('bdsj','y-mm-dd');" readonly="true" styleId="bdsj"></html:text>
					</td>
				</tr>
				<tr>
					<th>������ʼʱ��</th>
					<td>
					 	<html:text name="rs" property="bkkssj" onclick="return showCalendar('bkkssj','y-mm-dd');" readonly="true" styleId="bkkssj"></html:text>
					</td>
				</tr> 
				<tr>
					<th>��������ʱ��</thd>
					<td>
					 	<html:text name="rs" property="bkjssj" onclick="return showCalendar('bkjssj','y-mm-dd');" readonly="true" styleId="bkjssj"></html:text>
					</td>
				</tr>                                 
				<tr>
					<th>Ӧ��ѧ�ӷ�</th>
					<td>
					 	<html:text name="rs" property="xzf" styleId="xzf" maxlength="5" onkeyup="value=value.replace(/[^\d]/g,'') "></html:text>Ԫ
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="2"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			            <button name="Submit2" class="button2"
							onclick="doSaveConf('/xgxt/studentMessage_conf.do?doType=save','nd-xn-xq-kssj-kssjH-kssjM-kssjS-jssj-jssjH-jssjM-jssjS')">
							�� ��
						</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
		</div>
		<logic:notEmpty name="oper">
			<logic:equal value="true" name="result">
				<script>alert("����ɹ���");</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>alert("����ʧ�ܣ�");</script>
			</logic:equal>	
		</logic:notEmpty>				
	</html:form>
</body>
</html>
