<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/jtff/js/jtff.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			jQuery("#selsz").click(function(){
				showDialog("选择社长", 770, 520, "stgl_zjsr.do?method=getStu&type=selsz");
			});
			jQuery("#selcwfzr").click(function(){
				showDialog("选择财务负责人", 770, 520, "stgl_zjsr.do?method=getStu&type=selcwfzr");
			});
			jQuery("#selzdls").click(function(){
				showDialog("选择指导老师", 770, 520, "stgl_zjsr.do?method=getTea");
			});
		});
		</script>
		<style type="text/css">
		</style>
	</head>
	<body>
		<html:form method="post" styleId="form"	action="/stgl_zjsr">
			<div	style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<!-- <thead>
						<tr>
							<th colspan="4">
								<span>社团管理</span>
							</th>
						</tr>
					</thead> -->
					
					<tbody>
						<tr>
							<th width="20%">
								<font color="red">*</font>社团名称
							</th>
							<td colspan="3">
								<html:text property="stmc" styleId="stmc" maxlength="50" style="width:98%"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>社团编号
							</th>
							<td width="30%">
								<html:text property="bh" styleId="bh" maxlength="15" style="width:98%"></html:text>
							</td>
							<th width="20%">
								<font color="red">*</font>有效状态
							</th>
							<td width="30%">
								<html:radio property="yxzt" value="1" styleId="yxzt">有效</html:radio>
								<html:radio property="yxzt" value="0" styleId="yxzt">无效</html:radio>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>社团类别
							</th>
							<td width="30%">
								<html:select property="stlb" styleId="stlb" >
									<html:options collection="stlbList" property="stlbdm" labelProperty="stlbmc" />
								</html:select>
							</td>
							<th width="20%">
								<font color="red">*</font>指导老师
							</th>
							<td width="30%">
								<html:text property="zdls" styleId="zdls" maxlength="25" style="width:98%"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>社长
							</th>
							<td width="30%">
								<input type="text"  style="width:100px;" id="szxm" readonly="true" />
								<html:hidden property="sz" styleId="sz" />
								<button class="btn_01" id="selsz" type="button">选择</button>
							</td>
							<th width="20%">
								<font color="red">*</font>财务负责人
							</th>
							<td width="30%">
								<input type="text"  style="width:100px;" id="cwfzrxm" readonly="true" />
								<html:hidden property="cwfzr" styleId="cwfzr" />
								<button class="btn_01" id="selcwfzr" type="button">选择</button>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>归属部门
							</th>
							<td colspan="3">
								<html:select property="zd1" styleId="zd1" >
									<html:options collection="bmList" property="bmmc" labelProperty="bmmc" />
								</html:select>
							</td>
						</tr>
						<tr id="fjtr">
							<th>
								附件
							</th>
							<td colspan="3">
								<html:hidden property="filepath" styleId="filepath" />
								<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
	                               <script type="text/javascript">
										//调用附件 
										jQuery(function(){
											jQuery.MultiUploader({
												maxcount : 3,//后缀
												accept : 'png|gif|jpg|zip|rar|doc|docx',//最大文件大小 单位M
												maxsize: 10,//存放附件的隐藏域的id
												elementid : 'filepath'
											});
										});
								</script>
							</td>
						</tr>
						<tr>
							<th>
								备注
								<br><font color="red">限500字以内</br></font>
							</th>
							<td colspan="3" >
								<html:textarea property="bz" style="width:94%;height:100px"  styleId="bz" onblur="chLengs(this,500);" ></html:textarea>
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
								</div>
								<div class="btn">
									<button type="button" onclick="save('stgl_zjsr.do?method=stglSave','stmc-bh-yxzt-stlb-zdls-sz-cwfzr-zd1');" id="buttonSave">
										保 存
									</button>
									<button type="button" onclick="iFClose();"  id="buttonClose">
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