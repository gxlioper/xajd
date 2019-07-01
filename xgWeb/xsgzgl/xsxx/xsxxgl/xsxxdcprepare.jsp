<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsgzgl/xsxx/xsxxgl/js/exportConfig.js"></script>
		<script type="text/javascript" src="js/jquery.dragsort-0.4.min.js"></script>
		<style>
		.choose_yx{display: block;height: 13px;position: absolute;right: -3px;top: 2px;width: 13px;cursor:pointer;background: url("<%=stylePath %>/images/blue/ico_90.gif") no-repeat 0 0 !important;}
		.choose_wx{display: block;height: 13px;position: absolute;right: -3px;top: 2px;width: 13px;cursor:pointer;background: url("<%=stylePath %>/images/blue/ico_91.gif") no-repeat 0 0 !important;}
		
		.select-all{margin-bottom:10px;}
		.unselect-all{margin-bottom:10px;}
		.pointer{cursor: pointer; 
				font-weight: bold;
				width:10px;
				}
		.pointer:HOVER {
			text-decoration: underline;
		}
		</style>
	</head>
	<body>
		<html:form action="/comm_export" method="post" >
			<logic:equal value="stu" name="lx">
				<html:hidden property="xhs" styleId="xhs" value="${xhs}"/>
			</logic:equal>
			<logic:equal value="tea" name="lx">
				<html:hidden property="zghs" styleId="zghs" value="${zghs}"/>
			</logic:equal>
				<table width="100%" class="fieldlist">
				<tbody>
					<tr>
						<td width="48%" >
							<div class="tab_box">
								<h3>
									可选择导出列
								</h3>
								<div class="demo_college" style="height: 400px; width: 100%;overflow-y:scroll;position:relative;z-index:1">
									<ul id="unselectUl" >
										<logic:iterate id="o" name="gndmlist" >
											<logic:equal  name="o" property="zt" value="0">
												<li style="position:relative">
													<label class="college_li college_checkbox" title="${o.zhmc}" style="height:20px;line-height:20px!important;font-size:12px!important;*height:28px;width:120px;">
														${o.zhmc}
														<input name="unselectCol" type="hidden"
															value="${o.flszid}@${o.zhmc}" />
													</label>
													<span class="choose_wx" onclick="select(this);"></span>
												</li>
											</logic:equal>
										</logic:iterate>
									</ul>
								</div>
							</div>
						</td>
						<td width="4%" style="padding:0 !important; text-align: center;">
							<div class="select-all"><span class="pointer">
								<button type="button" onclick="ec_selectAll();" style="padding: 0 5px" title="全选">></button>
							</span></div>
							<div class="unselect-all"><span class="pointer">
								<button type="button" onclick="ec_unselectAll();" style="padding: 0 5px" title="全不选"><</button>
							</span></div>
						</td>
						<td width="48%">
							<div class="tab_box" >
								<h3>
									已选择导出列<font color="red">（拖拽可以排序）</font>
								</h3>
								<div class="demo_college" style="height: 400px; width: 100%;overflow-y:scroll;position:relative;z-index:1">
									<ul id="selectUl">
										<logic:iterate id="o" name="gndmlist" >
											<logic:equal  name="o" property="zt" value="1">
												<li style="position:relative;z-index:1">
													<label class="college_li college_checkbox" title="${o.zhmc}" style="height:20px;line-height:20px!important;font-size:12px!important;*height:28px;width:120px;">
														${o.zhmc}
														<input name="selectCol" type="hidden"
															value="${o.flszid}@${o.zhmc}" />
													</label>
													<span class="choose_yx" onclick="unselect(this);"></span>
												</li>
											</logic:equal>
										</logic:iterate>
									</ul>
								</div>
							</div>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="3">
							<label>
								<input type="radio" name="exportWjgs" value="xls" checked="checked">word
							</label>
							<div class="btn" >
								<logic:equal value="stu" name="lx">
									<button type="button" onclick="saveConfig('stu');">保存设置并导出</button>
									<button type="button" id="exportButton" onclick="doExport('stu')">直接导出</button>
								</logic:equal>
								<logic:equal value="tea" name="lx">
									<button type="button" onclick="saveConfig('tea');">保存设置并导出</button>
									<button type="button" id="exportButton" onclick="doExport('tea')">直接导出</button>
								</logic:equal>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</html:form>
	</body>
</html>
