<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	
	<script language="javascript">
    </script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/fdyglFunction.js"></script>
	</head>
	<body >
		<html:form action="/fdywork_research" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>����Ա���� - ˼������ - ����Ա�����ʾ����</a>
				</p>
			</div>	
			<div class="toolbox">
			 <!-- ��ť -->
			 <div class="buttonbox">
			    <ul>
				<li> <a href="#" onclick="if(document.getElementById('fdy').value==''){alert('��ѡ�񸨵�Ա��');}else{showTopWin('/xgxt/wj_view.do?act=answer&zgh='+document.getElementById('fdy').value,'700','800')}" class="btn_zj"> ��д�ʾ� </a> </li>
			    </ul>
			 </div>
		<div class="searchtab">
			<table width="100%" border="0">
			<thead>				
			<logic:equal value="0" name="sfkf">
			<div align="center"><font color="red">�����ʾ���δ���ţ�</font></div>
			</logic:equal>
			<logic:equal value="1" name="sfkf">
						<bean:write name="fdyglForm" property="xn" />
						ѧ�� (
						<bean:write name="fdyglForm" property="nd" />
						���)�� ��
						<bean:write name="fdyglForm" property="xq" />
						ѧ�� ����Ա�����ʾ����
			</logic:equal>
				<tbody>
							<tr>
								<td align="left" colspan="4">
									<logic:notEqual value="stu" name="userType" scope="session">
	                                 <bean:message key="lable.xsgzyxpzxy" />�� 
	                               
	                                  <html:select property="xydm" style="width:230px" styleId="xy"
											onchange="refreshForm('fdywork_research.do')">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select> &nbsp;&nbsp;&nbsp;&nbsp; 
	                                </logic:notEqual>
									����Ա��
									<html:select property="zgh" style="width:230px" styleId="fdy"
										onchange="">
										<html:option value=""></html:option>
										<html:options collection="fdyList" property="zgh"
											labelProperty="xm" />
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>	
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script> 
	</body>	
</html>
