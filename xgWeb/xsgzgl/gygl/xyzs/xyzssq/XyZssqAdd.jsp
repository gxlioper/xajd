<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/xyzs/xyzssq/js/xyzssq.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#xl").append("<option value=''selected >--请选择--</option>");
				jQuery("input[name=zwjzyy]").each(function(){
					if(jQuery(this).val() == '002'){
						jQuery(this).addClass("fontstyl");
					}
				})
			})
		</script>
		<style type = "text/css">
			#xxdz{width:250px;}
			.fontstyl{float:left}
		</style>
	</head>
	<body>
		<html:form action="/gygl_xyzssqgl" method="post" styleId="XyzsSqForm">
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>校外住宿信息</span>&nbsp;<lable style="line-height:20px">(${xn}&nbsp;学年)</lable>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>申请起始时间</th>
							<td>
								
								<html:text property="sqsjstart" styleId="start" onclick="return showCalendar('start','y-mm-dd',true,'end');"  maxlength="10" onblur="dateFormatChg(this);"></html:text>
								
							</td>
							<th><font color="red">*</font>申请结束时间</th>
							<td>
								<html:text property="sqsjend" styleId="end" maxlength="10" onclick="return showCalendar('end','y-mm-dd',false,'start');" onblur="dateFormatChg(this);"></html:text>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>学历</th>
							<td>
								<html:select   property="xl" styleId="xl"  style="width:150px;">
									<html:options collection="xl" property="xldm" labelProperty="xlmc" />
								</html:select>
								<%-- 
								<html:text property="xl" styleId="xl" maxlength="10"></html:text>--%>
							</td>
							<th><font color="red">*</font>联系电话</th>
							<td>
								<html:text property="lxdh" styleId="lxdh" maxlength="12" onkeyup="checkInputLxfx(this)"></html:text>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>家长联系方式</th>
							<td>
								
								<html:text property="parentslxdy" styleId="parentslxdy"  maxlength="12" onkeyup="checkInputLxfx(this)"></html:text>
								
							</td>
							<th><font color="red">*</font>校外住宿的详细地址</th>
							<td>
								
								<html:text property="xxdz" styleId="xxdz"  maxlength="25" ></html:text>
								
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>在外居住原因</th>
							<td colspan="3">
								<div style="height: 150px;">
									<logic:iterate id="zwjz"  name="zwjzyy">
							    		<html:radio idName="zwjz"  property="zwjzyy" value="dm">
							          		${zwjz.mc}
							         	</html:radio><br/>
							 		</logic:iterate>	
								</div>
							</td>
						</tr>
						<tr>
							<th>备注
								</br><font color="red">(限50字)</font></th>
							<td colspan="3">
								<html:textarea property="bz" styleId="bz" 
											   onkeyup="checkzs();"
											   style="width:99%;" rows="2"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								<logic:equal value="13871" name="xxdm">
									<font color="red">*</font>
								</logic:equal>
								附件
							</th>
							<td colspan="3">
								<html:hidden property="filepath" styleId="filepath"/>
								<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										jQuery.MultiUploader({
											maxcount : 3,
											//后缀
											accept : 'png|gif|jpg|zip|rar|doc|docx',
											//最大文件大小 单位M
											maxsize: 10,
											//存放附件的隐藏域的id
											elementid : 'filepath'
											});
									});
								</script>						
							</td>
						</tr>
					</tbody>
				</table>
				</div>	
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveZsjg('save');">
										保存草稿
									</button>
									<button type="button" onclick="saveZsjg('submit');">
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
		</html:form>
	</body>
	
</html>