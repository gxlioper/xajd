<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsgzgl/dagl/qdmb/addDaqdmb.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript" src="js/jquery.dragsort-0.4.min.js"></script>
		<style>
		.choose_yx{display: block;height: 13px;position: absolute;right: -3px;top: 2px;width: 13px;cursor:pointer;background: url("<%=stylePath %>/images/blue/ico_90.gif") no-repeat 0 0 !important;}
		.choose_wx{display: block;height: 13px;position: absolute;right: -3px;top: 2px;width: 13px;cursor:pointer;background: url("<%=stylePath %>/images/blue/ico_91.gif") no-repeat 0 0 !important;}
		</style>
	</head>
	<body >
		<html:form action="/daqdmb" method="post"  onsubmit="return false;" styleId="form">
			<div style='width:100%;height:100%;overflow-y:auto;overflow-x:hidden'>
				<table  border="0" class="formlist">
					<thead>
						<tr width="100%">
							<th colspan="3">
								<span>档案清单材料${dddd}</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="18%">
								<span class="red">*</span>档案清单<br>模板名称
							</th>
							<td  colspan="2" >
								<html:text property="daqdmb_mc" styleId="daqdmb_mc"  style="width:80%" value="" maxlength="200" readonly="false" />
							</td>
						</tr>
						<tr>
							<th width="18%">
								<span class="red">*</span>启用状态
							</th>
							<td colspan="2">
								<html:hidden property="qyzt" styleId="qyzt"/>
								<label style='cursor:pointer'><input type="radio" id="startZt" name="qyztName" value="1" checked="checked">启用</input></label>
								<label style='cursor:pointer'><input type="radio" id="endZt" name="qyztName" value="0" >停用</input></label>
							</td>
						</tr>
						<tr>
							<th width="18%">
								<span class="red">*</span>绑定材料
							</th>
						<td width="41%">
							<div class="tab_box">
								<h3>
									可选择材料
								</h3>
								<div class="demo_college" style="height: 260px; width: 100%;overflow-y:scroll;*position:relative;*z-index:1">
									<div>
										<ul id="unselectUl" >
											<logic:iterate name="daqdclList" id="c" >
													<li style="position:relative">
														<label class="college_li college_checkbox" style="height:20px;line-height:20px!important;font-size:12px!important;*height:28px;padding:3px 0px;text-indent: 15px;">
															<input name="unselectCol" type="hidden" value="${c.clid}"/>${c.clmc}
														</label>
														<span class="choose_wx" onclick="select(this);"></span>
													</li>
											</logic:iterate>
										</ul>
									</div>
								</div>
							</div>
						</td>
						<td width="41%">
							<div class="tab_box" >
								<h3>
									已选择材料<font color="red">（拖拽可以排序）</font>
								</h3>
								<div class="demo_college" style="height: 260px; width: 100%;overflow-y:scroll;*position:relative;*z-index:1">
									<div >
										<ul id="selectUl">
										</ul>
									</div>
								</div>
							</div>
						</td>
					</tr>
					</tbody>
				</table>
			</div>
			<table border="0" class="formlist" >	
					<tfoot>
						<tr>
							<td colspan="3">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();return false;">
										保 存
									</button>
									<button type="button" type="button" onclick="iFClose();return false;">
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
