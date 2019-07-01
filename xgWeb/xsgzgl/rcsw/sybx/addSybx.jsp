<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/sybx/js/addSybx.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("th",jQuery("#sybxForm")).css("width","17%");
			});
		</script>
	</head>
	<body style="width:710px">
		
		<html:form action="/rcsw_sybx" method="post" styleId="sybxForm">
			
			
			<div style='tab'>
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
						<tr>
							<th colspan="4">
								<span>商业保险信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th><span class="red">*</span>学年</th>
							<td>
								<html:select  property="xn" styleId="xn" style="width:152px">
								<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
							</td>
							<th><span class="red">*</span>城镇居民医疗<br/>保险金额</th>
							<td>
								<html:text property="czjmylbxje" styleId="czjmylbxje" maxlength="8" onkeyup="checkInputNum(this);changeBxje();" />
							</td>
					    </tr>
					    <tr>
							<th><span class="red">*</span>城镇居民医疗保险参保起始日期</th>
							<td>
								<html:text property="czjmylbxcbqsrq" styleId="czjmylbxcbqsrq"  value="${czjmylbxcbqsrq }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true"/>
							</td>
							<th>城镇居民医疗保险参保结束日期</th>
							<td>
								<html:text property="czjmylbxcbjsrq" styleId="czjmylbxcbjsrq"  value="${czjmylbxcbjsrq }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true"/>
							</td>
					    </tr>
					    <tr>
					    	<th><span class="red">*</span>商业保险金额</th>
							<td>
								<html:text property="sybxje" styleId="sybxje" maxlength="8" onkeyup="checkInputNum(this);changeBxje();" />
							</td>
							<th><span class="red">*</span>商业保险参保<br/>起始日期</th>
							<td>
								<html:text property="sybxcbqsrq" styleId="sybxcbqsrq"  value="${sybxcbqsrq }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'green'})" readonly="true"/>
							</td>
					    </tr>
					    <tr>
							<th>商业保险参保<br/>结束日期</th>
							<td>
								<html:text property="sybxcbjsrq" styleId="sybxcbjsrq"  value="${sybxcbjsrq }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true"/>
							</td>
							<th>保险总金额<input type="hidden" name="bxje" value="${bxje }" id="bxje"/></th>
							<td id="bxjeTd">
							</td>
					    </tr>
					    <tr>
					    	<th><span class="red">*</span>增加原因</th>
							<td>
								<html:select property="zjyy" styleId="zjyy"  style="width: 152px" value="${zjyy}">
									<html:options collection="zjyyList" labelProperty="mc" property="dm"/>
								</html:select>
							</td>
							<th><span class="red">*</span>参保人员类别</th>
							<td>
								<html:select property="cbrylb" styleId="cbrylb"  style="width: 152px" value="${cbrylb}">
									<html:options collection="cbrylbList" labelProperty="mc" property="dm"/>
								</html:select>
							</td>
					    </tr>
					    <tr>
							<th><span class="red">*</span>缴费人员类别</th>
							<td>
								<html:select property="jfrylb" styleId="jfrylb"  style="width: 152px" value="${jfrylb}">
									<html:options collection="jfrylbList" labelProperty="mc" property="dm"/>
								</html:select>
							</td>
							<th><span class="red">*</span>身份证签发机关</th>
							<td>
								<html:text property="sfzqfjg" styleId="sfzqfjg" maxlength="100" style=""/>
							</td>
					    </tr>
					    <tr>
							<th><span class="red">*</span>身份证有效期限起始日期</th>
							<td>
								<html:text property="sfzyxqxqsrq" styleId="sfzyxqxqsrq"  value="${sfzyxqxqsrq }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true"/>
							</td>
							<th><span class="red">*</span>身份证有效期限截止日期</th>
							<td>
								<html:text property="sfzyxqxjzrq" styleId="sfzyxqxjzrq"  value="${sfzyxqxjzrq }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true"/>
							</td>
					    </tr>
					    <tr>
							<th>监护人姓名</th>
							<td>
								<html:text property="jhrxm" styleId="jhrxm" maxlength="20" />
							</td>
							<th width="18%">监护人身份证号</th>
							<td>
								<html:text property="jhrsfzh" styleId="jhrsfzh" maxlength="18" onblur="if(!checkSfzh(this)){this.focus();}"/>
							</td>
					    </tr>
					    <tr>
							<th>
								<span class="red">*</span>通讯地址
							</th>
							<td colspan="4">
								<html:text property="txdz" styleId="txdz" maxlength="50" style="width:550px" />
							</td>
			     		 </tr>
					    <tr>
							<th>
								备注
								<br /><font color="red">(限制在500字内)</font>
							</th>
							<td colspan="4">
								<html:textarea property='bz' style="width:95%" styleId="bz" rows='5' onblur="checkLen(this,500);"/>
							</td>
			     		 </tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" onclick="saveForm();">
										保 存
									</button>
									<button type="button" onclick="iFClose();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>