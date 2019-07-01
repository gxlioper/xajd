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
				if('${disabled}' == "disabled"){
					jQuery(":input").not("#ccrq").not("#jzrq").not("button").not("#guid").attr("disabled","disabled");
					jQuery("#update").show();
					jQuery("#insert").hide();
				}else{
					jQuery("#update").hide();
					jQuery("#insert").show();
				}
			})
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/gyjc_jcrc" method="post" styleId="JcrcForm">
			<html:hidden property="guid" styleId="guid"/>
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
							<td>${xn}
								<html:hidden property="xn" styleId="xn" />
							</td>
							<th>学期</th>
							<td>${xqmc}
								<html:hidden property="xq" styleId="xq" />
							 </td>
						</tr>
						<tr>
							<th><font color="red">*</font>检查日期</th>
							<td>
								<html:text property="ccrq"  styleId="ccrq" onclick="return showCalendar('ccrq','y-mm-dd',true,'jzrq');" />
							</td>
							<th><font color="red">*</font>截止维护时间</th>
							<td>
								<html:text  property="jzrq" styleId="jzrq" onclick="return showCalendar('jzrq','y-mm-dd',false,'ccrq');" />
							</td>
						</tr>
						<tr>
							<th>角色</th>
							<td>
								${jsmc}
							</td>
								<th><font color="red">*</font>本次检查项</th>
							<td>
								<html:checkbox property="wsjc" styleId="wsjc" value="1"/><label for="wsjc">卫生检查</label>
								<html:checkbox property="aqjc" styleId="aqjc" value="2"/><label for="aqjc">安全检查</label>
								<html:checkbox property="jljc" styleId="jljc" value="3"/><label for="jljc">纪律检查</label>
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
											<th style="text-align:left;width:22%">学院</th>
											<th style="text-align:left;width:68%">当前学期已抽</th>
											<th style="text-align:left;width:10%">检查比例</th>
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
												<td style="width:22%">${i.xymc}</td>
												<td style="width:68%">
												<p>
												第<font class="red">${i.ls}</font>轮<font class="red" >${i.jcbl}</font>%&nbsp;&nbsp;&nbsp;共<font class="red">${i.zs}</font>个寝室&nbsp;&nbsp;&nbsp;
												(本次抽取了<font class="red">${i.bcrs}</font>个寝室；抽取比例为<font class="red">${i.bcjcbl}</font>%)
												<logic:notEqual value="disabled" name="disabled">
													<a href="javascript:void(0);" style="color:blue;float:right" onclick="selLd(this);">选楼栋</a>
												</logic:notEqual>
												</p>
												<table id="${i.xydm}" width="100%">
													<logic:iterate id="j" name="i" property="ld">
													<tr>
														<td width="90%">
															${j.ldmc}&nbsp;本次<font color="red">${j.cnt}</font>个寝室
															<input type="hidden" name="lddm" value="${j.lddm}-${j.xydm}"/>
														</td>
													</tr>
													</logic:iterate>
												</table>
												</td>
												<td style="width:10%">
													<input type="text" style="width: 90%" name="jcbl" value="${i.jcblinput}" maxlength="5" onkeyup="checkMoneyForKeyup(this)" onblur="calBfbOver(this)"/>
													<input type="hidden" name="xydm" value="${i.xydm}" />
													<input type="hidden" name="ls" value="${i.ls}" />
													<input type="hidden" name="ztxbl" value="${i.jcbl}"/>
                                                    <input type="hidden" name="bcjcbl" value="${i.bcjcbl}"/>
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
									<button type="button" id="insert" onclick="saveJcrc();">
										保    存
									</button>
									<button type="button" id="update" onclick="saveJcrcUpdate();">
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