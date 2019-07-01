<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/stuinfoFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
	</head>
	<body onload="checkWinType();">
		<html:form action="/data_search" method="post">
		<input type="hidden" name="realTable" id="realTable" value="stu_archives_auditing"/>	
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>ѧ����Ϣ - ��ҵ��������-��ת�����</a>
				</p>
			</div>
			<div class="tab">
				<table width="100%"  border="0" class="formlist">
				 <thead>
    				<tr>
        				<th colspan="6"><span>�������</span></th>
        			</tr>
   				 </thead>
   				 <tbody>
				<tr style="height:22px">
					<th align="right">
						ѧ��
					</th>
					<td align="left">
						<bean:write name="rs" property="xh" />
						<input type="hidden" value="<bean:write name="rs" property="xh" />" name="xh" id="xh"/>
					</td>
					<th align="right">
						��Уʱ��
					</th>
					<td align="left">
						<bean:write name="rs" property="rxsj" />						
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						����
					</th>
					<td align="left">
						<bean:write name="rs" property="xm" />
					</td>
					<th align="right">
						��ҵʱ��
					</th>
					<td align="left">
						<bean:write name="rs" property="bysj" />						
					</td>
				</tr>				
				<tr style="height:22px">
					<th align="right">
						�꼶
					</th>
					<td align="left">
						<bean:write name="rs" property="nj" />	
					</td>
					<th align="right">
						������������
					</th>
					<td align="left">
						<bean:write name="rs" property="hkssqx" />
					</td>
					
				</tr>
				<tr style="height:22px">
					<th align="right">
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td align="left">
						<bean:write name="rs" property="xymc" />
					</td>
					<th align="right">
						���������ֵ�
					</th>
					<td align="left">
						<bean:write name="rs" property="hkssjd" />
					</td>					
				</tr>
				<tr style="height:22px">
					<th align="right">
						רҵ
					</th>
					<td align="left">
						<bean:write name="rs" property="zymc"/>
					</td>
					<th align="right">
						������ϸ��ַ
					</th>
					<td align="left">
						<bean:write name="rs" property="hkxxdz"/>
					</td>					
				</tr>
				<tr style="height:22px">
					<th align="right">
						�༶
					</th>
					<td align="left">
						<bean:write name="rs" property="bjmc"/>
					</td>
					<th align="right">
						��ϵ��ʽ
					</th>
					<td align="left">
						<bean:write name="rs" property="lxfs"/>
					</td>						
					
				</tr>
				<tr style="height:22px">
					<th align="right">
						��������
					</th>
					<td align="left">
						<bean:write name="rs" property="sqrq"/>
					</td>
					<th align="right">
						��������
					</th>
					<td align="left">
						<bean:write name="rs" property="sqly"/>
					</td>	
				</tr>
				<tr  style="height:22px">
					
					<th align="right">
						���
					</th>
					<td align="left" colspan="3">
					<html:select property="yesNo" name="rs">
							<html:option value="δ���">δ���</html:option>
							<html:option value="ͨ��">ͨ��</html:option>
							<html:option value="��ͨ��">��ͨ��</html:option>
						</html:select>
					</td>
				</tr>	
				<tr  style="height:22px">					
					<th align="right">
						��ע
					</th>
					<td align="left" colspan="3">
					<html:textarea property="bz" name="rs" style="width:100%;height:45px"></html:textarea>
					</td>
				</tr>
				</tbody>
				<tfoot>
				 <tr>
					<td colspan="6"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
					<div class="btn">
						<logic:equal value="yes" name="writeAble">
						<button 
							onclick="refreshForm('business.do?method=gradArchivesAuding&doType=auditing')"
							 id="buttonSave">
							�� ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button  onclick="Close();return false;" 
							id="buttonClose">
							�� ��
						</button>
						</logic:equal>
					</div>
					</td>
				</tr>
			</tfoot>
			</table>
			</div
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("�����ɹ�");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>
	</body>
</html>
