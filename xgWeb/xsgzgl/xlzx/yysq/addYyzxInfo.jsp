<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" src="js/check.js"></script>
			<script language="javascript" src="xsgzgl/xlzx/yysq/js/addYyzxInfo.js"></script>
	</head>
	<body>
		<input type="hidden" name="path" id="path" value="${path}" />
		<input type="hidden" name="zgh" id="zgh" value="${zxsInfo.zgh}" />
		<input type="hidden" name="sjhm" id="sjhm" value="${xsInfo.sjhm}" />
		<html:form action="/xlzx_zxyycl" method="post">
			<div style='width:100%; height:480px; overflow-y:auto;overflow-x:hidden'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					<thead>
						<tr >
							<th colspan="4">
								<span>��ѯʦ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="zxsInfo">
						<tr style="height:10px">
							<th width="16%">
								ְ����
							</th>
							<td width="34%">
								${zxsInfo.zgh}
							</td>
							
							<th  width="16%">
								����
							</th>
							<td  width="34%">
								${zxsInfo.xm}
							</td>
						</tr>
						<tr style="height:10px">
							<th  width="16%">
								�Ա�
							</th>
							<td  width="34%">
								${zxsInfo.xb }
							</td>
							<th width="16%">
								����
							</th>
							<td  width="34%">
								${zxsInfo.age}
							</td>
						</tr>
						<tr style="height:10px">
							<th width="16%">
								��ϵ�绰
							</th>
							<td  width="34%">
								${zxsInfo.lxdh }
								
							</td>
							<th width="16%">
								���ڲ���
							</th>
							<td  width="34%">
								${zxsInfo.bmmc }
								
							</td>
						</tr>						
					</tbody>			
							
					
					<thead>
						<tr>
							<th colspan="4">
								<span>��ѯ������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="dealInfo">
					<tr style="height:10px" >
							<th>
								<span class="red">*</span>��ѯ��������
							</th>
							<td >
									<html:text property="zxrq" styleId="zxrq"  value="" style="width:100px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  onchange="delValidate();"/>
							</td>
							<th  width="16%">
								��ѯʱ��
							</th>
							<td  width="34%" >
								<html:text property="zxqssj" styleId="zxqssj" style="width:30%"  value="" onfocus="WdatePicker({dateFmt:'HH:mm'})" />&nbsp;��&nbsp;
								<html:text property="zxjssj" styleId="zxjssj" style="width:30%"  value="" onfocus="WdatePicker({dateFmt:'HH:mm'})" />
							</td>
						</tr>
						<tr>
							<th>
								��ѯ��ϵ�绰
							</th>
							<td colspan="3">
								<html:text property="zxtell" styleId="zxtell"  value="${zxsInfo.lxdh}"  maxlength="11" onblur="checkInputData(this);"/>
							</td>
						</tr>
						<tr>
							<th>
								��ѯ��ַ
							</th>
							<td colspan="3">
								<html:text property="zxdz" styleId="zxdz" style="width:90%"  maxlength="50"  value="${zxsInfo.address }"  />
							</td>
						</tr>
						<tr style="height:10px" name="yyfkId">
							<th  width="16%">
								��ע<br/>
								<font color="red"><B>(��500��)</B></font>
							</th>
							<td  width="34%" colspan="3">
								<html:textarea  property='bz' styleId="bz" value="" style="word-break:break-all;width:99%" onblur="chLeng(this,500);" rows='4' />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<table width="100%" border="0" class="formlist">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button id="buttonSave" onclick="saveYysqInfo();return false;">
									�� ��
								</button>
								<button onclick="Close();return false;">
									�� ��
								</button>

							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		
		</html:form>
	</body>
</html>

