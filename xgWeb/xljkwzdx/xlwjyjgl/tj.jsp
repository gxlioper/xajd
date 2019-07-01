<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" defer="defer">

			function addAction(){
				var rzksrq = jQuery('#rksj').val();
				var checkids = "rksj";
				
				if(!checkNotNull(checkids)){
					showAlert("请将带<font color='red'>*</font>的项目填写完整!");
					return false;
				}

				if(jQuery("input[name='lxdm']:checked").length != 1 || jQuery("input[name='gzdj']:checked").length != 1){
					showAlert("请将带<font color='red'>*</font>的项目填写完整!");
					return false;
				}
				
				var url = "xljk_xlwjyjgl_xlwjyjkglwh.do?method=tjAction";
					ajaxSubFormWithFun("xlwjyjkForm",url,function(data){
						showAlertDivLayer(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
					});
			}
		</script>
	</head>
	<body>
		<button id="search_go" type="button" style="display:none" onclick="reloadWindow();"></button>
		<html:form action="/xljk_xlwjyjgl_xlwjyjkglwh" method="post" styleId="xlwjyjkForm">
			<div style='tab;width:100%;height:270px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>提交心理危机预警库</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								已选择学生数
							</th>
							<td colspan="3">
								${rkrs} 人 
									(
									${xhList}
									)				
									<html:hidden property="xhs" value="${xhList}"/>				
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>心理问题类型
							</th>
							<td colspan="3">
								<logic:present name="xlwtList">
									<logic:iterate id="o" name="xlwtList"  indexId="idx">
										<html:radio property="lxdm"  value="${o.lxdm}" styleId="lxdm_${idx}"></html:radio>
										<label for="lxdm_${idx}">${o.lxmc}</label>
									</logic:iterate>								
								</logic:present>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>关注等级
							</th>
							<td colspan="1">
								<logic:present name="gzdjList">
									<logic:iterate id="o" name="gzdjList"  indexId="idx">
										<html:radio property="gzdj"  value="${o.dm}" styleId="gzdj_${idx}"></html:radio>
										<label for="gzdj_${idx}">${o.mc}</label>
									</logic:iterate>								
								</logic:present>
							</td>
							<th>
								<span class="red">*</span>入库时间
							</th>
							<td colspan="1">
									<html:text styleId="rksj" property="rksj" 
									onclick="return showCalendar('rksj','yyyy-MM-dd',false);"  readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								备注
								<br />
								<font color="red">(限500字)</font>
							</th>
							<td colspan="3" style="word-break: break-all;">
								<html:textarea property="bz" styleId="bz" style="width:95%;" rows="5"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
			<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button id="save_button" type="button"  onclick="addAction();">
										保存
									</button>
									<button type="button" name="关 闭" onclick="iFClose();">
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

