<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		
		
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybd.js"></script>
		<script type="text/javascript" src="js/calendar/calendar-setup.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/tyxs/sqsh/js/zzsq.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
	</head>
	<body>
		<html:form action="/tyxs_zzjg" method="post" styleId="tyxsZzjgForm">
			<html:hidden property="id" styleId="id"/>							
			<html:hidden property="xn"  styleId="xn"/>	
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" />
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span></span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					
					<thead>
						<tr>
							<th colspan="4">
								<logic:notEqual value="12688" name="xxdm" >
									<span>资助信息</span>
								</logic:notEqual>
								<logic:equal value="12688" name="xxdm" >
									<span>补偿信息</span>
								</logic:equal>
							</th>
						</tr>
					</thead>
					<tbody>
							<logic:equal name="xxdm" value="10511">
							<tr>
								<th>贷款类型</th>
					    		<td>
					    			${tyxsZzjgForm.dklx }
					    			<input type="hidden" id="dklx" value="${tyxsZzjgForm.dklx }" name="dklx"/>
					    		</td>
							</tr>
							</logic:equal>
							<tr>
								<th>
									<span class="red">*</span>申请学年
								</th>
								<td>
								<html:select  property="xn" styleId="xn" style="width:130px">
								<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
								</td>
								<th>
									<span class="red">*</span>申请总金额（元）
								</th>
								<td>
									<html:text property="sqxfzj" styleId="sqxfzj" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')"
									></html:text>
								</td>
							</tr>
														<logic:equal name="xxdm" value="10511">
							<tr>
							<%--<th><span class="red">*</span>贷款金额(元)</th>
							<td>
								 <html:text property="dkbj" styleId="dkbj" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>--%>
							<th>经办银行</th>
							<td>
							   <html:select property="yhdm" styleId="yhdm">
									<html:option value="">请选择</html:option>
									<html:options collection="yhlist" property="dm" labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>贷款合同号</th>
							<td>
								 <html:text property="dkhth"  styleId="dkhth"maxlength="20" ></html:text>
							</td>
							<th>贷款起止时间</th>
							<td>
							    <html:text property="dkkssj" styleId="dkkssj" readonly="true" style="width:80px;"
									onfocus="showCalendar('dkkssj','y-mm-dd');">
								</html:text>
								至
								<html:text property="dkjssj" styleId="dkjssj" readonly="true" style="width:80px;"
									onfocus="showCalendar('dkjssj','y-mm-dd',false,'dkkssj');">
								</html:text>
							</td>
						</tr>
							</logic:equal>
							<tr>
								
								<th>
									<span class="red">*</span>申请时间
								</th>
								<td>
									<html:text property="sqsj" styleId="sqsj" readonly="true"
									onfocus="showCalendar('sqsj','yyyy-MM-dd HH:mm:ss');"></html:text>
								</td>

								<%--<th>
									<span class="red">*</span>资助总金额(元)
								</th>
								<td>
									<html:text property="zzzje" maxlength="10" styleId="zzzje" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
								</td>--%>
							</tr>

							<tr>
								<th height="49px">
									<span class="red" style="height: 100px">*</span>申请理由
									<br/><span class="red" >(限250字)</span>
								</th>
								<td colspan="3">
									<html:textarea property="sqly" styleId="sqly"
										style="width:93%;" rows="4"></html:textarea>
								</td>

							</tr>
							<logic:notEqual value="10511" name="xxdm">
								<tr>
									<th align="right">
										附件信息
									</th>
									<td colspan="3">
										<html:hidden property="filepath" styleId="filepath" />
										<input type="file" id="filepath_f" name="filepath" />
										<script type="text/javascript">
											//调用附件 
											jQuery(function(){
												jQuery('#filepath_f').multiUploader({
													maxcount : 3,
													//后缀
													accept : 'png|gif|jpg|zip|rar|doc|docx',
													//最大文件大小 单位M
													maxsize: 10,
													//存放附件的隐藏域的id
													elementid : 'filepath',
					
													eid : 'filepath_f'
												});
											});
										</script>
									</td>
								</tr>
							</logic:notEqual>
						</tbody>
				</table>
				<div class="tab"  id="content" style="margin-top: 5px; overflow-x:hidden;" >
				</div>
				<div style="height:25px;"></div>
				<div>
				<table class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveZzsq('tyxs_zzjg.do?method=update');">
										保    存
									</button>
									<button type="button" onclick="iFClose();">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
	<script type="text/javascript" src="xsgzgl/zxdk/tyxs/sqsh/js/zzjg.js"></script>
		
</html>