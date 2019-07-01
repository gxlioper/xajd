<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsgzgl/dagl/qdmb/updateDaqdmb.js"></script>
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
		<html:hidden property="daqdmb_id" styleId="daqdmb_id" />
		<input type="hidden" name="oldDaqdmbmc" id="oldDaqdmbmc" value="${daqdmbForm.daqdmb_mc}"/>
			<div style='width:100%;height:100%;overflow-y:auto;overflow-x:hidden'>
				<table  border="0" class="formlist">
					<thead>
						<tr width="100%">
							<th colspan="3">
								<span>�����嵥ģ��</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								<span class="red">*</span>�����嵥<br>ģ������
							</th>
							<td  colspan="2" >
								<html:text property="daqdmb_mc" styleId="daqdmb_mc"  style="width:80%" maxlength="200" readonly="false" />
							</td>
						</tr>
						<tr>
							<th width="16%">
								<span class="red">*</span>����״̬
							</th>
							<td colspan="2">
								<html:hidden property="qyzt" styleId="qyzt"/>
								<label style='cursor:pointer'><input type="radio" id="startZt" name="qyztName" value="1" checked="checked">����</input></label>
								<label style='cursor:pointer'><input type="radio" id="endZt" name="qyztName" value="0" >ͣ��</input></label>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<span class="red">*</span>�󶨲���
							</th>
						<td width="42%">
							<div class="tab_box">
								<h3>
									��ѡ�����
								</h3>
								<div class="demo_college" style="height: 260px; width: 100%;overflow-y:scroll;*position:relative;*z-index:1">
									<div>
										<ul id="unselectUl" >
											<logic:iterate name="daqdclList" id="c" >
												<logic:equal name="c" property="zt" value="0">
													<li style="position:relative">
														<label class="college_li college_checkbox" style="height:20px;line-height:20px!important;font-size:12px!important;*height:28px;width:140px;padding:3px 0px;text-indent: 15px;">
															<input name="unselectCol" type="hidden" value="${c.daqdcl_id}"/>${c.daqdcl_mc}
														</label>
														<span class="choose_wx" onclick="select(this);"></span>
													</li>
												</logic:equal>
											</logic:iterate>
										</ul>
									</div>
								</div>
							</div>
						</td>
						<td width="42%">
							<div class="tab_box" >
								<h3>
									��ѡ�����<font color="red">����ק��������</font>
								</h3>
								<div class="demo_college" style="height: 260px; width: 100%;overflow-y:scroll;*position:relative;*z-index:1">
									<div >
										<ul id="selectUl">
											<logic:iterate name="daqdclList" id="c" >
												<logic:equal name="c" property="zt" value="1">
													<li style="position:relative">
														<label class="college_li college_checkbox" style="height:20px;line-height:20px!important;font-size:12px!important;*height:28px;width:140px;padding:3px 0px;text-indent: 15px;">
															<input name="selectCol" type="hidden" value="${c.daqdcl_id}"/>${c.daqdcl_mc}
														</label>
														<span class="choose_yx" onclick="unselect(this);"></span>
													</li>
												</logic:equal>
											</logic:iterate>
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
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();return false;">
										�� ��
									</button>
									<button type="button" type="button" onclick="iFClose();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		
		</html:form>
	</body>
</html>
