<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/ypzl/jg/js/ypzljg.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/ypzl_jg" method="post" styleId="ypzljgForm" onsubmit="return false;">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
			<html:hidden property="jgid" styleId="jgid"/>
			<input type="hidden" id="xxdm" value="${xxdm}"/>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>申请人经济情况（本学年）</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								学年
							</th>
							<td width="30%">
								${xnsr}
							</td>
							</td>
							<th>学期</th>
							<td>
								${xqsr}
							</td>
						</tr>				
						<logic:notEqual value="10511" name="xxdm">
						<tr>
					    	<th><span class="red">*</span>申请金额（元）</th>
					    	<td>
								<select id="sqje" name="sqje" style="width:200px">
									<option value="6000">6000</option>
									<option value="10000">10000</option>
								</select>
							</td>
						</tr>
						</logic:notEqual>
						<logic:equal value="10511" name="xxdm">
						<tr>
							<th><span class="red">*</span>补助金额（元）</th>
					    	<td>
								<html:text styleId="sqje" property="sqje" onblur="replaceAboveZero(this)" maxlength="4" onkeyup="checkInput(this)" />
							</td>
							<th><span class="red">*</span>申请原因类别</th>
							<td>
								<html:select  property="ytlb" styleId="ytlb" style="width:200px">
									<html:option value=""></html:option>
									<html:options collection="lbList" labelProperty="lbmc" property="lbdm"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								材料上传
							</th>
							<td colspan="3">
								<html:hidden property="ytms" styleId="ytms" />
				                 <%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
						           <script type="text/javascript">
										//调用附件 
										jQuery(function(){
											jQuery.MultiUploader({
												maxcount : 3,
												//后缀
												accept : 'pdf|doc|docx|xls|xlsx|jpg|jpeg',
												//最大文件大小 单位M
												maxsize: 10,
												//存放附件的隐藏域的id
												elementid : 'ytms'
												});
										});
										
							</script>
									</td>
								</tr>
							</logic:equal>
					    </tr>			    
					    <logic:notEqual value="10511" name="xxdm">					    	
					    	<tr>
								<th align="right" width="16%">
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
						<tr>
							<th><span><font color="red">*</font></span>
								申请理由
								<br /><font color="red">&lt;限500字&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='sqly' style="width:98%" styleId="sqly" rows='8' onblur="checkLen(this,500);"/>
							</td>
			      		</tr>						
					</tbody>
				 </table>			
				</div>
			  <div style="height:30px"></div>   
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button" onclick="saveYpzljg('update');">
										保存
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
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

