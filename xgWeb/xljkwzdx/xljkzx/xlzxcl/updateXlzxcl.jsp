<%@ page language="java" import="java.util.*,java.text.SimpleDateFormat" contentType="text/html; charset=GBK"%>
<%
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String datestr = sdf.format(new Date());
	sdf = new SimpleDateFormat("HH-mm");
	String timestr = sdf.format(new Date());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	  <%@ include file="/syscommon/head.ini"%>
	  <script type="text/javascript" src="js/calendar/calendar.js"></script>
	  <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	  <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	  <script type="text/javascript" src="js/check.js"></script>
	  <script type="text/javascript" src="xljkwzdx/xljkzx/js/xlzxcl.js"></script>
	  
  </head>
  
  <body>
    <html:form action="/xljk_xlzxcl" method="post" styleId="xlzxclForm">
    	<table width="100%" border="0" class="formlist">
    		<thead>
				<tr>
					<th colspan="4">
						<span>学生信息</span>
					</th>
				</tr>
			</thead>
			<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
			<thead>
				<tr>
					<th colspan="4">
						<span>咨询师信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th width="15%">
						职工号
					</th>
					<td width="35%">
						${zxsxx.zgh }
					</td>
					<th width="15%">
						咨询师
					</th>
					<td width="35%">
						${zxsxx.xm }
					</td>
				</tr>
				<tr>
					<th>
						性别
					</th>
					<td>
						${zxsxx.xb }
					</td>
					<th>
						年龄
					</th>
					<td>
						${zxsxx.nl }
					</td>
				</tr>
				<tr>
					<th>
						所在部门
					</th>
					<td>
						${zxsxx.bmmc }
					</td>
					<th>
						联系电话
					</th>
					<td>
						${zxsxx.lxdh }
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
			<tbody>
				<tr>
					<th>
						<span class="red">*</span>咨询安排日期
					</th>
					<td>
						<html:text  property="zzaprq"
									onclick="return showCalendar('zzaprq','yyyy-MM-dd',false,'zzaprqzxrq');"
									styleClass="text_nor" styleId="zzaprq" readonly="true" />
						<input type="hidden" id="zzaprqzxrq" value="<%=datestr %>"/>
						<html:hidden property="xh" styleId="xh" value="${jbxx.xh }"/>
						<html:hidden property="zxs" styleId="zxs" value="${zxsxx.zgh }"/>
						<html:hidden property="zxid" styleId="zxid"/>
					</td>
					<th>
						咨询时段
					</th>
					<td>
						<html:text  property="zxsdkssj"
									onclick="return showCalendar('zxsdkssj','HH:mm',false,'zxsdkssjzxsj');"
									styleClass="text_nor" styleId="zxsdkssj" readonly="true" style="width:65px;" />
						至		
						<html:text  property="zxsdjssj"
									onclick="return showCalendar('zxsdjssj','HH:mm',false,'zxsdkssjzxsj');"
									styleClass="text_nor" styleId="zxsdjssj" readonly="true" style="width:65px;" />
						<input type="hidden" id="zxsdkssjzxsj" value="<%=timestr %>"/>
					</td>
				</tr>
				<tr>
					<th>
						联系电话
					</th>
					<td colspan="3">
						<html:text property="zxslxdh" styleId="zxslxdh" styleClass="text_nor" maxlength="16"/>
					</td>
				</tr>
				<tr>
					<th>
						咨询地址
					</th>
					<td colspan="3">
						<html:text property="zxdz" styleId="zxdz" styleClass="text_nor" style="width:500px;" maxlength="50"/>
					</td>
				</tr>
				<tr>
					<th>
						备注
						<span class="red">(限500字)</span>
					</th>
					<td colspan="3">
						<html:textarea property="bz" styleId="bz" style="width:95%;" rows="5" onkeypress="checkLen(this,500);"></html:textarea>
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="4">
						<div class="bz">"<span class="red">*</span>"为必填项</div>
						<div class="btn">
							<button id="submit_button" type="button"  onclick="updateXlzxclAction();">
								保 存
							</button>
							<button type="button" name="关 闭" onclick="iFClose();">
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
