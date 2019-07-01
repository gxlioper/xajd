<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<%@ include file="/syscommon/autocomplete.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript" src="xsgzgl/jskp/xspjjg/js/xspjjg.js"></script>
		<script type="text/javascript">
			function changeZdbmmc(){
				jQuery("#bmmc").val("");
				jQuery("#zdbmdm").val("");
				//取得数据表：zxbz_xxbmdm; 字段：bmmc																							
				var autoSetting = {
					dataTable:"zxbz_xxbmdm",
					dataField:"bmmc",
					dataFieldKey:"bmdm",
					dataFieldKeyId:"zdbmdm",
					scrollHeight:135										
				}
				// 模糊搜索下拉【项目名称】
				jQuery("#bmmc").setAutocomplete(autoSetting);
			}
			jQuery(function(){
				changeZdbmmc();
			})
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/xspj_xspjjg" method="post" styleId="xspjjgForm">
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>评价结果</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:15%">
								<font color="red">*</font>项目名称
							</th>
							<td style="width:35%">
								<input type="text" name="xmmc" id="xmmc" maxlength="64"/>
							</td>
							<th style="width:15%">
								<font color="red">*</font>指导部门
							</th>
							<td style="width:35%">
								<input type="text" name="bmmc" id="bmmc" />
								<input type="hidden" id="zdbmdm" name="zdbmdm" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>项目类别
							</th>
							<td>
								<html:select property="xmlbdm" styleId="xmlbdm" style="width:173px;">
									<html:option value="">---&nbsp;请选择项目类别&nbsp;---</html:option>
									<html:options collection ="xmlbList" property="xmlbdm" labelProperty="xmlbmc" />
								</html:select>
							</td>
							<th>
								<font color="red">*</font>参与时间
							</th>
							<td>
								<input type="hidden" name="maxtime" id="maxtime" maxlength="10" value="${maxtime}"/>
								<html:text property="cysj" styleId="cysj" onclick="return showCalendar('cysj','y-mm-dd',true,'maxtime');"  maxlength="10" ></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>参与人(学号)
							</th>
							<logic:equal value="stu" name="userType">
								<td>
									<input type="text" name="xh" id="xh" value="${userName}"/>
								</td>
							</logic:equal>
							<logic:notEqual value="stu" name="userType">
								<td>
									<html:text property="xh" styleId="xh"  maxlength="10" onkeyup="checkInputLxfx(this)"></html:text>
								</td>
							</logic:notEqual>
							<th>
								<font color="red">*</font>学年
							</th>
							<td>
								<html:select property="xn" styleId="xn" style="width:173px;">
										<html:options collection="xnList" labelProperty="xn" property="xn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>短学期
							</th>
							<td>
								<html:select property="dxqdm" styleId="dxqdm" style="width:173px;">
									<html:option value="">---&nbsp;请选择短学期&nbsp;---</html:option>
									<html:options collection ="dxqList" property="dxqdm" labelProperty="dxqmc" />
								</html:select>
							</td>
							<logic:notEqual value="stu" name="userType">
								<th>
									分值
								</th>
								<td>
									<html:text property="fz" styleId="fz"  maxlength="4" onblur="checkInputNum(this)"></html:text>
								</td>
							</logic:notEqual>
						</tr>
						<tr>
							<th>
								负责人
							</th>
							<td>
								<html:text property="fzrxm" styleId="fzrxm" maxlength="16"></html:text>
							</td>
							<th>
								负责人&nbsp;<br/>联系方式
							</th>
							<td>
								<html:text property="fzrlxfs" styleId="fzrlxfs" maxlength="12" onkeyup="checkInputLxfx(this)"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								指导老师
							</th>
							<td>
								<html:text property="zdlsxm" styleId="zdlsxm"  maxlength="16" ></html:text>
							</td>
							<th>
								指导老师<br/>联系方式
							</th>
							<td>
								<html:text property="zdlslxfs" styleId="zdlslxfs"  maxlength="12" onkeyup="checkInputLxfx(this)"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>附件上传
							</th>
							<td colspan="3">
								<html:hidden property="fjid" styleId="fjid" />
								<input type="file" id="filepath_f" name="filepath_f" />
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
											elementid : 'fjid',
											eid : 'filepath_f'
										});
									});
								</script>
							</td>
						</tr>
						<tr>
							<th>
								申请理由
								<br/><font color="red">(限200字)</font>
							</th>
							<td colspan="3">
								<html:textarea property="sqly" styleId="sqly" onkeyup="checkLen(this,200);"  style="width:99%;" rows="5"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>	
			<div style="height:30px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="xspjjgSave();">
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
		</html:form>
	</body>
</html>