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
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					<thead>
						<tr >
							<th colspan="4">
								<span>咨询师信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="zxsInfo">
						<tr style="height:10px">
							<th width="16%">
								职工号
							</th>
							<td width="34%">
								${zxsInfo.zgh}
							</td>
							
							<th  width="16%">
								姓名
							</th>
							<td  width="34%">
								${zxsInfo.xm}
							</td>
						</tr>
						<tr style="height:10px">
							<th  width="16%">
								性别
							</th>
							<td  width="34%">
								${zxsInfo.xb }
							</td>
							<th width="16%">
								年龄
							</th>
							<td  width="34%">
								${zxsInfo.age}
							</td>
						</tr>
						<tr style="height:10px">
							<th width="16%">
								联系电话
							</th>
							<td  width="34%">
								${zxsInfo.lxdh }
								
							</td>
							<th width="16%">
								所在部门
							</th>
							<td  width="34%">
								${zxsInfo.bmmc }
								
							</td>
						</tr>						
					</tbody>			
							
					
					<thead>
						<tr>
							<th colspan="4">
								<span>咨询安排信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="dealInfo">
					<tr style="height:10px" >
							<th>
								<span class="red">*</span>咨询安排日期
							</th>
							<td >
									<html:text property="zxrq" styleId="zxrq"  value="" style="width:100px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  onchange="delValidate();"/>
							</td>
							<th  width="16%">
								咨询时段
							</th>
							<td  width="34%" >
								<html:text property="zxqssj" styleId="zxqssj" style="width:30%"  value="" onfocus="WdatePicker({dateFmt:'HH:mm'})" />&nbsp;至&nbsp;
								<html:text property="zxjssj" styleId="zxjssj" style="width:30%"  value="" onfocus="WdatePicker({dateFmt:'HH:mm'})" />
							</td>
						</tr>
						<tr>
							<th>
								咨询联系电话
							</th>
							<td colspan="3">
								<html:text property="zxtell" styleId="zxtell"  value="${zxsInfo.lxdh}"  maxlength="11" onblur="checkInputData(this);"/>
							</td>
						</tr>
						<tr>
							<th>
								咨询地址
							</th>
							<td colspan="3">
								<html:text property="zxdz" styleId="zxdz" style="width:90%"  maxlength="50"  value="${zxsInfo.address }"  />
							</td>
						</tr>
						<tr style="height:10px" name="yyfkId">
							<th  width="16%">
								备注<br/>
								<font color="red"><B>(限500字)</B></font>
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
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button id="buttonSave" onclick="saveYysqInfo();return false;">
									保 存
								</button>
								<button onclick="Close();return false;">
									关 闭
								</button>

							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		
		</html:form>
	</body>
</html>

