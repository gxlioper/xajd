<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xthd/xmsq/js/xmsq.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#but_close").click(iFClose);
			});

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting1);
			});
		</script>
	</head>
	<body>
		<html:form method="post"
		 styleId="demoForm" action="/rcsw_txhd_xs_xmsq?method=xmsq">
		 <html:hidden property="xmdm"/>
		 <html:hidden property="xn"/>
		 <html:hidden property="xq"/>
		 <input type="hidden" name="sqr" value="${jbxx.xm}" id="sqr"/>
		 <input type="hidden" name="xh" value="${jbxx.xh}" id="xh"/>
		 <input type="hidden" name="sqid" value="${sqdata.sqid}" id="xh"/>
		<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/xthd/comm/viewStudent.jsp"%>
				<thead>
						<tr>
							<th colspan="4">
								<span>活动信息</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="10%">
							学年
						</th>
						<td align="left">
							${data.xn}
						</td>
						<th align="right">
							学期
						</th>
						<td align="left">
							${data.xqmc}
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							活动时间
						</th>
						<td align="left">
							${data.hdkssj} ~ ${data.hdjssj}
						</td>
						<th align="right">
							活动地点
						</th>
						<td align="left">
							${data.hddd}
						</td>
					</tr>
					<tr>
						<th align="right">
							活动说明
						</th>
						<td align="left">
							${data.hdsm }
						</td>
					</tr>
					<tr>
					<th align="right" width="10%">
							项目名称
						</th>
						<td colspan="3">
							${data.xmmc}
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							<span class="red">*</span>申请理由&nbsp;
							<br />
							<font color="red">(限200字)</font>	
						</th>
						<td colspan="3">
							<html:textarea rows="4" property="sqly" styleId="sqly" style="width:97%" onblur="checkLen(this,200);">${sqdata.sqly}</html:textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
					<tr>
							<td colspan="4" >
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" id = "but_save" onclick="updateForm('update');return false;">
										保存草稿
									</button>
									<button type="button" id="buttonSave"  onclick="updateForm('submit');return false;">
										提交申请
									</button>
									<button type="button" type="button" id= "but_close">
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
