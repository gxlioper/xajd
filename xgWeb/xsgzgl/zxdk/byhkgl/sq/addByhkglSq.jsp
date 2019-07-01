<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/byhkgl/sq/js/byhkglSq.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type='text/javascript'>
			function sfzhanqi(){
				
				var sfzq = jQuery("#sfzq").val();
				if (sfzq == "是"){				
					jQuery('.sfzhanqi').css("display","");
				} else {		
					jQuery('.sfzhanqi').css("display","none");
				}
			}
			
			jQuery(function(){
				sfzhanqi();
			})
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/byhkgl_sq" method="post" styleId="byhkglSqForm" onsubmit="return false;">
			<input type="hidden" name="xh" id="xh" value="${xh}"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp"%>
					<thead>
						<tr>
							<th colspan="4">
								<span>还款信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								<span class="red">*</span>还款金额（元）
							</th>
							<td width="30%">
								<html:text property="hkje" styleId="hkje" onkeyup="checkInput(this)" onblur="ismoney(this)" value = "${yhje}" maxlength="10"></html:text>
							</td>
							</td>
							<th>
								<span class="red">*</span>是否展期
							</th>
							<td>
								<select id="sfzq" name="sfzq" onclick="sfzhanqi();" style="width:90px">
									<option value=''></option>
									<option value="是">是</option>
									<option value="否">否</option>
								</select>
							</td>
						</tr>
						<tr class="sfzhanqi">
					    	<th>
					    		<span class="red">*</span>展期原因
					    	</th>
					    	<td>
								<html:select  property="zqyy" styleId="zqyy" style="width:130px">
									<option value=''></option>
									<html:options collection="zqyyList" property="zqyydm" labelProperty="zqyymc" />
								</html:select>
							</td>
							<th>
					    		<span class="red">*</span>展期期限（月）
					    	</th>
					    	<td>
								<html:text property="zqqx" styleId="zqqx" onkeyup="checkInput(this)" onblur="isyf(this)"  maxlength="2"></html:text>
							</td>
					    </tr>
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
											accept : 'png|gif|jpg|jpeg|doc|docx',
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
						<tr>
							<th>
								备注
								<br /><font color="red">&lt;限500字&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='bz' style="width:98%" styleId="bz" rows='8' onblur="checkLen(this,500);"/>
							</td>
			      		</tr>						
					</tbody>
				 </table>			
				</div>
			  <div style="height:35px"></div>   
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button" onclick="saveByhkgl('save');">
										保存草稿
									</button>
									<button type="button" onclick="saveByhkgl('submit');">
										提交申请
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

