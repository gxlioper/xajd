<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="/xgxt/wjcf/shgc/shgcjs/shgcjs.js"></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
	</head>
	<body onload="">
		<html:form action="/wjcfjgsdxwh.do" method="post">
			<input type="hidden" id="pkValue" value="${pkValue }"/>
			<div class="tab_cur" id="title_m">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>
						<logic:equal value="13022" name="xxdm">
						Υ�ʹ��� - ����쿴������ - ��ѯ
						</logic:equal>
						<logic:notEqual value="13022" name="xxdm">
							Υ�ʹ��� - ������ֹ��� - ������
						</logic:notEqual>
					</a>
				</p>
			</div>
			
		<div class="tab">
			<table width="100%"  border="0" class="formlist">
				<thead>
    				<tr>
        				<th colspan="4"><span>�������</span></th>
        			</tr>
   				</thead>
   				<tfoot>
   					 <tr>
				       <td colspan="6">
				          <div class="btn">
				          		<logic:equal value="modi" name="act">
									<button type="button" name="����" onclick="refreshForm('cxcfdetails.do?act=save&pkValue='+document.getElementById('pkValue').value);BatAlert.showTips('���ڲ��������Ե�...');"  id="buttonSave">
										�� ��
									</button>
								</logic:equal>
								 	<button type="button" name="�ر�" onclick="Close();return false;" id="buttonClose">�� ��</button>			           
				          </div>
				          </td>
				      </tr>
   				</tfoot>
				<tr style="height:22px">
					<th align="right" style="width: 25%">
						ѧ��
					</th>
					<td align="left" style="width: 25%">
						<bean:write name="rs"  property="xh"/>
					</td>
					<th align="right" style="width: 25%">
						���
					</th>
					<td align="left" style="width: 25%">
						<bean:write name="rs"  property="nd"/>
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						����
					</th>
					<td align="left">
						<bean:write name="rs" property="xm"/>
					</td>
					<th align="right">
						ѧ��
					</th>
					<td align="left">
						<bean:write name="rs" property="xn"/>
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						�Ա�
					</th>
					<td align="left">
						<bean:write name="rs" property="xb" />
					</td>
					<th align="right">
						�������
					</th>
					<td align="left">
						<bean:write name="rs" property="cflbmc"/>
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						�꼶
					</th>
					<td align="left">
						<bean:write name="rs" property="nj"/>
					</td>
					<th align="right">
						����ԭ��
					</th>
					<td align="left">
						<bean:write name="rs" property="cfyymc"/>
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td align="left">
						<bean:write name="rs" property="xymc"/>
					</td>
					<th align="right">
					�����ĺ�
					</th>
					<td align="left">
					<bean:write name="rs" property="cfwh"/>
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						רҵ��
					</th>
					<td align="left">
						<bean:write name="rs"  property="zymc"/>
					</td>
						<th align="right">
						����ʱ��
						</th>
						<td align="left">
						<bean:write name="rs" property="cfsj"/>
						</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						�༶
					</th>
					<td align="left">
						<bean:write name="rs" property="bjmc"/>
					</td>
					<logic:equal value="11647" name="xxdm">
						<th align="right">
						���ʱ��
						</th>
						<td align="left">
						${rs.jcsj }
						</td>
						</logic:equal>
					
				</tr>
				<tr style="height:22px">
					<th align="right">����ʱ��</th>
					<td align="left"><bean:write name="rs" property="cxsj"/></td>
					
					<logic:equal value="10827" name="xxdm">
						<th align="right">
						����Ա���
					</th>
					<td align="left">
						<bean:write name="rs" property="fdysh"/>
					</td>
					</logic:equal>
					<logic:notEqual value="10827" name="xxdm">
						<th align="right">
						���ʱ��
					</th>
					<td align="left">
						${rs.jcsj }
					</td>
					</logic:notEqual>
				</tr>
				<logic:equal value="13022" name="xxdm">
					<tr>
						<th align="right">
							����ĺ�
						</th>
						<td align="left">
							${rs.jcwh }
						</td>
						<th align="right">
							������
						</th>
						<td align="left">
							${rs.cxjg }
						</td>
					</tr>
				</logic:equal>
				<logic:notEqual value="13022" name="xxdm">
				<tr style="height:22px">
					<th align="right">
						<bean:message key="lable.xsgzyxpzxy" />���
					</th>
					<td align="left">
					<bean:write name="rs" property="xysh"/>
					</td>
					<th align="right">
						ѧУ���
					</th>
					<td align="left">
					<bean:write name="rs" property="xxsh"/>
					</td>
				</tr>
				</logic:notEqual>
				<tr>
					<th align="right">
						��������&nbsp;&nbsp;&nbsp;&nbsp;<br/>����ʵ����
					</th>
					<td colspan="3" align="left"><html:textarea rows="5"  style="word-break:break-all;width:90%" name="rs" property="bz"  styleId="a" />
					</td>
				</tr>
				
			</table>
			<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
		</html:form>
	</body>
</html>
