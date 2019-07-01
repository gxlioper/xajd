<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xthd/xmsz/js/xmsz.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				onShow();
			});
		</script>
	</head>
	<body>
		<html:form action="/rcsw_txhd_xmszCx" method="post" styleId="form1">
		<html:hidden property="xmdm" styleId="xmdm" />
		<input type="hidden" name="spzt" id="spzt" value="${spzt }" />
		<div class="prompt">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					当前设置项目为：<font color="red">${xmmc} &nbsp;	
				</font>
				</p>
				<p class="promptYsq" style="display:none;">
					<font color="red">
						当前项目已有学生申请，部分项不允许修改
					</font>
				</p>
				<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
			<div style='tab;width:100%;height:370px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>时间设置</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">

						<tr>
							<th>
								开放申请时间
							</th>
							<td>
								<html:text property="sqkssj" styleId="sqkssj"
								onfocus="showCalendar('sqkssj','yyyy-MM-dd HH:mm',true,'sqjssj');" />
								&nbsp;至
								<html:text property="sqjssj" styleId="sqjssj"
								onfocus="showCalendar('sqjssj','yyyy-MM-dd HH:mm',false,'sqkssj');" />
							</td>
						</tr>		

						<tr>
							<th>
								申请开关
							</th>
							<td>
								<logic:present name="onoffList">
									<logic:iterate id="o" name="onoffList" >
										<label>
											<html:radio property="sqkg" value="${o.dm}">${o.mc}</html:radio>
										</label>
									</logic:iterate>								
								</logic:present>
							</td>
						</tr>
						<tr>
							<th>申请开关备注
								<br /><font color="red">(限100字)</font>
							</th>
							<td >
								<html:textarea property="kgbz" styleId="kgbz"  style="width:98%"  rows="5" onblur="checkLen(this,100);"/>
							</td>
						</tr>

						<tr>
							<th>
								开放审核时间
							</th>
							<td>
								<html:text property="shkssj" styleId="shkssj"
								onfocus="showCalendar('shkssj','yyyy-MM-dd HH:mm',true,'shjssj');" />
								&nbsp;至
								<html:text property="shjssj" styleId="shjssj"
								onfocus="showCalendar('shjssj','yyyy-MM-dd HH:mm',false,'shkssj');" />
							</td>
						</tr>		

						<tr>
							<th>
								审核开关
							</th>
							<td>
								<logic:present name="onoffList">
									<logic:iterate id="o" name="onoffList" >
										<label>
											<html:radio property="shkg" value="${o.dm}">${o.mc}</html:radio>
										</label>
									</logic:iterate>								
								</logic:present>
							</td>
						</tr>	
						<tr>
							<th  width="20%">
								<font color="red">*</font>审核流程
							</th>
							<td  width="80%" colspan="3">
								<html:select property="shlc" styleId="shlc" >
								<html:option value=""></html:option>
								<html:options collection="shlcList" property="splc"
									labelProperty="lcxx" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<html:hidden property="rskzjb" styleId="rskzjb" />
								<font color="red">*</font>人数控制级别
							</th>
							<td id="rskzjbTd" colspan="3">
							</td>				
						</tr>					
					</tbody>
				</table>
			</div>
			<div>
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
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

