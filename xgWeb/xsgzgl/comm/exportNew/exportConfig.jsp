<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@ include file="/WEB-INF/pages/globalweb/head/pagehead_v4.ini"%>
		<script type="text/javascript"
			src="<%=systemPath%>/js/jquery/dragsort/jquery.dragsort-0.4.min.js"></script>
		<style>
		.choose_yx{display: block;height: 13px;position: absolute;right: -3px;top: 2px;width: 13px;cursor:pointer;background: url("<%=stylePath %>/images/blue/ico_90.gif") no-repeat 0 0 !important;}
		.choose_wx{display: block;height: 13px;position: absolute;right: -3px;top: 2px;width: 13px;cursor:pointer;background: url("<%=stylePath %>/images/blue/ico_91.gif") no-repeat 0 0 !important;}
		</style>
		<script type="text/javascript" src="<%=systemPath%>/js/zfxg/comp/export.js"></script>
		<script type="text/javascript">
			var isModify = false;
		
			jQuery(function() {
				jQuery("#unselectUl, #selectUl").dragsort({
					dragSelector : "label",
					dragBetween : true,
					dragEnd : saveOrder,
					placeHolderTemplate : "<li><label class='college_li college_checkbox' style='border:#155FBE 1px dotted;background:#CBE4F8;height:20px;line-height:20px!important;*height:28px;width:140px;padding:3px 0px;text-indent: 15px;'></label></li>"
				});
			});
		
			//拖动后
			function saveOrder() {
				isModify = true;
				jQuery("#unselectUl").find(":input").attr("name","unselectCol");
				
				var unspan = jQuery("#unselectUl").find(".choose_yx");
				unspan.parent().append("<span class='choose_wx' onclick='select(this)'></span>");
				unspan.remove();
				
				var span = jQuery("#selectUl").find(".choose_wx");
				span.parent().append("<span class='choose_yx' onclick='unselect(this)'></span>");
				span.remove();
			};
			
		</script>
	</head>

	<body>
		<s:form id="exportForm" theme="simple">
			<s:hidden name="dcclbh" id="dcclbh"/>
				<table width="100%" class="fieldlist">
				<tbody>
					<tr>
						<td width="50%" >
							<div class="tab_box">
								<h3>
									可选择导出列
								</h3>
								<div class="demo_college" style="height: 400px; width: 100%;overflow-y:scroll;*position:relative;*z-index:1">
									<div style="height: 400px; width: 100%;">
										<ul id="unselectUl" >
											<s:iterator value="configList" id="c" status="config">
												<s:if test="#c.zt==0">
													<li style="position:relative">
														<label class="college_li college_checkbox" style="height:20px;line-height:20px!important;font-size:12px!important;*height:28px;width:140px;padding:3px 0px;text-indent: 15px;">
															<s:property value="#c.zdmc" />
															<input name="unselectCol" type="hidden"
																value="<s:property value="#c.zd"/>@<s:property value="#c.zdmc"/>" />
														</label>
														<span class="choose_wx" onclick="select(this);"></span>
													</li>
												</s:if>
											</s:iterator>
										</ul>
									</div>
								</div>
							</div>
						</td>
						<td width="50%">
							<div class="tab_box" >
								<h3>
									已选择导出列<font color="red">（拖拽可以排序）</font>
								</h3>
								<div class="demo_college" style="height: 400px; width: 100%;overflow-y:scroll;*position:relative;*z-index:1">
									<div style="height: 400px; width: 100%; ">
										<ul id="selectUl">
											<s:iterator value="configList" id="c" status="config">
												<s:if test="#c.zt==1">
													<li style="position:relative">
														<label class="college_li college_checkbox" style="height:20px;line-height:20px!important;font-size:12px!important;*height:28px;width:140px;padding:3px 0px;text-indent: 15px;">
															<s:property value="#c.zdmc" />
															<input name="selectCol" type="hidden"
																value="<s:property value="#c.zd"/>@<s:property value="#c.zdmc"/>" />
														</label>
														<span class="choose_yx" onclick="unselect(this);"></span>
													</li>
												</s:if>
											</s:iterator>
										</ul>
									</div>
								</div>
							</div>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2">
							<div class="btn" >
								<button type="button" onclick="saveConfig();">保存设置并导出</button>
								<button type="button" id="exportButton" onclick="doExport()">直接导出</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</s:form>
	</body>
</html>
