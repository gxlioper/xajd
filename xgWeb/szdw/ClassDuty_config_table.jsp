<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
</head>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<html:form action="/hdzj_report_save" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>˼������-�༶���-ѧ���ɲ�ְ��</a>
				</p>
			</div>
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>
			        	<logic:notEmpty name="rs">
							<input type="hidden" name="pk" property="pk" value="<bean:write name="rs" property="pk"/>" />
			        		��дѧ���ɲ�ְ��ѡ��
			        	</logic:notEmpty>	
			        	<logic:empty name="rs">
							�д�������
						</logic:empty>
			        		</span>
			        	</th>
			        </tr>
			    </thead>
			     <tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			         <logic:notEqual name="userType" value = "xy" scope ="session">
			          		<button type="button" name="�ύ" onclick="sxjyCommonSave('/xgxt/research_answer_save.do?type=bjgbsz&pk=<bean:write name="rs" property="bjgbdm"/>','bjgbmc-zr','bjgbsz')">�� ��</button>
			          </logic:notEqual>
			          </div></td>
			      </tr>
			    </tfoot>
			    <logic:notEmpty name="rs">
				<tbody>
				
					<tr style="height:22px">
						<th align="right">
							<font color="red">*</font>ְ������
						</th>
						<td align="left" >
							<html:text name="rs" property="bjgbmc" style="width: 180px"/>
						</td>
						
					</tr>
						<tr style="height:22px">
						<th align="right">
							<font color="red">*</font>ְ������
						</th>
						<td align="left" >
							<html:text name="rs" property="zr" style="width: 400px"/>
						</td>
						
					</tr>
						<tr style="height:22px">
						<th align="right">
							��ע
						</th>
						<td align="left" >
							<html:text name="rs" property="bz" style="width: 400px"/>
						</td>
					</tr>
					</tbody>
					</logic:notEmpty>
				</table>
			
			</div>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("�ύ�ɹ���");
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("�ύʧ�ܣ�");
    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>