<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gyjc/jcrc/js/jcrc.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
			})
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/gyjc_jcrc" method="post" styleId="JcrcForm">
			<div style="tab;overflow-x:hidden;overflow-y:hidden;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>设置</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>学年</th>
							<td>${xn}<input type="hidden" name="xn" id="xn" value="${xn}"/> </td>
							<th>学期</th>
							<td>${xqmc}<input type="hidden" name="xq" id="xq" value="${xq}"/> </td>
						</tr>
						<tr>
							<th><font color="red">*</font>检查日期</th>
							<td>
								<html:text property="ccrq" value="${minDate}" styleId="ccrq" onclick="return showCalendar('ccrq','y-mm-dd',true,'jzrq');"  maxlength="10" ></html:text>
							</td>
							<th><font color="red">*</font>截止维护时间</th>
							<td>
								<html:text  property="jzrq" styleId="jzrq" maxlength="10" onclick="return showCalendar('jzrq','y-mm-dd',false,'ccrq');" ></html:text>
							</td>
						</tr>
						<tr>
							<th>角色</th>
							<td>
								${jsmc}
							</td>
								<th><font color="red">*</font>本次检查项</th>
							<td>
								
								<input type="checkbox" name="wsjc" value="1" id="wsjc" checked="checked"/>卫生检查</label>
								<input type="checkbox" name="aqjc" value="2" id="aqjc" checked="checked"/>安全检查</label>
								<input type="checkbox" name="jljc" value="3" id="jljc" checked="checked"/>纪律检查</label>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>保存并生成抽查结果</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4" style="border-bottom: 0px;padding: 0;">
							<table style="width: 98%;" >
							     <thead >
										<tr>
											<th style="text-align:left;width:40%">学院</th>
											<th style="text-align:left;width:30%">当前学期已抽</th>
											<th style="text-align:left;width:30%">检查比例</th>
										</tr>
									</thead>
							</table>
							</td>
						     </tr>
						<tr>
							<td colspan="4" style="border-top: 0px;padding: 0;" >
								<div style="height:300px;overflow-y:scroll;overflow-x:hidden">
								<table style="width:100%;" id="innertable">
									<tbody>
										<logic:iterate id="i" name="jcmxList">
											<tr>
												<td style="width:40%">${i.xymc}</td>
												<td style="width:30%">
												<p>
												第<font class="red">${i.ls}</font>轮<font class="red" id="jcblupdate">${i.jcbl}</font>%&nbsp;&nbsp;&nbsp;共<font class="red">${i.zs}</font>个寝室
													<a href="javascript:void(0);" style="color:blue;float:right" onclick="selLd(this);">选楼栋</a>
												</p>
												<table id="${i.xydm}" width="100%">
													
												</table>
												
												</td>
												<td style="width:30%">
													<input type="text" style="width: 90%" maxlength="5" name="jcbl" onkeyup="checkMoneyForKeyup(this)" onblur="calBfbOver(this)"/>
													<input type="hidden" name="xydm" value="${i.xydm}" />
													<input type="hidden" name="ls" value="${i.ls}" />
													<input type="hidden" name="ztxbl" value="${i.jcbl}"/>
												</td>
											</tr>
										</logic:iterate>
									</tbody>
								</table>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
				<%--;height:520px --%>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveJcrc();">
										保    存
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