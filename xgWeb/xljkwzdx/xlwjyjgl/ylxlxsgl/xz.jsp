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
				var xh = jQuery('#xh').val();
				var rzksrq = jQuery('#rksj').val();
				if (xh==""){
					showAlert("请选择一个学生！");
					return false;
				}
				var checkids = "mtkssj-mtjssj-mtdd-ftnr";
				
				if(!checkNotNull(checkids)){
					showAlert("请将带<font color='red'>*</font>的项目填写完整!");
					return false;
				}

				if(jQuery("input[name='lxdm']:checked").length != 1 || jQuery("input[name='gzdj']:checked").length != 1){
					showAlert("请将带<font color='red'>*</font>的项目填写完整!");
					return false;
				}

				if(jQuery('#ftnr').val().length > 500){
					showAlert("访谈内容最大字数不超过"+500+",请确认！");
					return false;
				}
			

				if(jQuery('#mtxjjjsfdcs').val().length > 500){
					showAlert("面谈小结及建设辅导措施最大字数不超过"+500+",请确认！");
					return false;
				}
				
				var url = "xljk_xlwjyjgl_ylxlxsglwh.do?method=xzAction";
					ajaxSubFormWithFun("ylxlxsglForm",url,function(data){
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
		<html:form action="/xljk_xlwjyjgl_ylxlxsglwh" method="post" styleId="ylxlxsglForm">
			<div style='tab;width:100%;height:510px;overflow-x:hidden;overflow-y:auto;'>
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
								<span>面谈记录信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><span class="red">*</span>面谈时间</th>
							<td  colspan="3">
								<html:text styleId="mtkssj" property="mtkssj" 
									onclick="return showCalendar('mtkssj','yyyy-MM-dd HH:mm',true , 'mtjssj');"  readonly="true"></html:text>
									至
									<html:text styleId="mtjssj" property="mtjssj" 
									onclick="return showCalendar('mtjssj','yyyy-MM-dd HH:mm',false , 'mtkssj');"  readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>面谈地点</th>
							<td colspan="3">
								<html:text styleId="mtdd" property="mtdd"  size="100" style="width:98%"></html:text>
							</td>
						</tr>
						<tr>
							<th>面谈老师</th>
							<td colspan="3">
								<html:hidden property="mtzgh"/>
								<html:text styleId="mtzghmc" property="mtzghmc"  readonly="true"  style="border-width:0"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>访谈内容
								<br />
								<font color="red">(限500字)</font>
							</th>
							<td colspan="3" style="word-break: break-all;">
								<html:textarea property="ftnr" styleId="ftnr" style="width:95%;" rows="4"></html:textarea>
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
							<td colspan="3">
								<logic:present name="gzdjList">
									<logic:iterate id="o" name="gzdjList"  indexId="idx">
										<html:radio property="gzdj"  value="${o.dm}" styleId="gzdj_${idx}"></html:radio>
										<label for="gzdj_${idx}">${o.mc}</label>
									</logic:iterate>								
								</logic:present>
							</td>
						</tr>
						<tr>
							<th>
								面谈小结及<br />
								建设辅导措施
								<br />
								<font color="red">(限500字)</font>
							</th>
							<td colspan="3" style="word-break: break-all;">
								<html:textarea property="mtxjjjsfdcs" styleId="mtxjjjsfdcs" style="width:95%;" rows="4"></html:textarea>
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

