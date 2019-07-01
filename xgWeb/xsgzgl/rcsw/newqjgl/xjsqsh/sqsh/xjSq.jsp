<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript">
			jQuery(function() {
			});
			//保存表单
			function saveForm(type){
				var checkWriteStr = "sjqjts-sjkssj-sjjssj-xjbz";
				if(!checkNotNull(checkWriteStr)){
					return showAlert("请将必填项填写完整！");
				}
				var url = "qjgl_xjsh.do?method=saveXjsq&type="+type;
				ajaxSubFormWithFun("form", url, function(data) {
					 if(data["message"]=="保存成功！"){
			    		 showAlert(data["message"],{},{"clkFun":function(){
								if (parent.window){
									refershParent();
								}
							}});
			    	 }else{
			    		 showAlert(data["message"]);
			    		}
					});
			}
		</script>
	</head>
	<body>
		<html:form method="post" styleId="form" action="/qjgl_xjsh">
		 <html:hidden property="ywid"/>
		 <html:hidden property="qjjgid" name="data"/>
		<div style="tab;width:100%;overflow-x:hidden;overflow-y:auto;">
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
								<span>请假信息</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="16%">
							学年
						</th>
						<td align="left"  width="34%">
							${data.xn}
						</td>
						<th align="right"  width="16%">
							学期
						</th>
						<td align="left"  width="34%">
							${data.xqmc}
						</td>
					</tr>
					<tr>
						<th align="right">
							请假天数
						</th>
						<td align="left">
							${data.qjts}&nbsp;&nbsp;天&nbsp;&nbsp;
						</td>
						<th align="right">
							请假类型
						</th>
						<td align="left">
							${qjlxmc}
						</td>
					</tr>
					<tr>
						<th align="right">
							请假开始时间
						</th>
						<td align="left">
							${data.kssj }
						</td>
						<th align="right">
							请假结束时间
						</th>
						<td align="left">
							${data.jssj }
						</td>
					</tr>
					<logic:equal name="xxdm" value="12727">
						<tr>
							<th align="right" width="10%">
								请假节次
							</th>
							<td colspan="3">
								${data.mdd }
							</td>
						</tr>
					</logic:equal>
					<logic:equal name="xxdm" value="12688">
						<tr>
							<th align="right" width="10%">
								请假节次
							</th>
							<td colspan="3">
								${data.mdd }
							</td>
						</tr>
					</logic:equal>
					<logic:equal name="xxdm" value="70002">
						<tr>
							<th align="right">
								校内校外
							</th>
							 <td align="left">
							 	${data.xnxw }
							 </td>
							 <th></th>
							 <td></td>
						</tr>
					</logic:equal>
					<logic:equal name="xxdm" value="12866">
						<tr>
							<th align="right" width="10%">
								家长电话
								
							</th>
							<td colspan="3">
								${data.jzdh }
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								备注&nbsp;
							</th>
							<td colspan="3">
								${data.bz}
							</td>
						</tr>
						</logic:equal>
					<tr>
						<th align="right">
							请假事由
						</th>
						<td colspan="3">
							${data.qjsy}
						</td>
					</tr>
					
<%-- 						<tr>
							<th align="right" width="10%">
								附件信息
							</th>
							<td colspan="3">
								<a href="czxxDtjsDyxx.do?method=downLoadFile&dir=${data.filepath}" target="_blank" style="color:blue">点击下载查看</a>
							</td>
						</tr> --%>
					<tr>
							<th align="right" width="10%">
								附件信息
							</th>
							<td colspan="3">
							<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
							<html:hidden property="filepath" styleId="fjid" value="${data.filepath}"/>
							<script type="text/javascript">
								//调用附件 
								jQuery(function(){
									var gid = jQuery('#fjid').val();
									jQuery.MultiUploader_q({
										gid : gid
										});
								});
							</script>
						</td>
						</tr>
				</tbody>
			</table>
			<table width="100%" border="0" class="formlist">
				<thead>
						<tr>
							<th colspan="4">
								<span>销假信息</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="16%">
							<span class="red">*</span>实际请假天数
						</th>
						<td align="left"  width="34%">
							<logic:notEmpty  name="XjsqshForm" property="sjqjts">
								<html:text property="sjqjts" styleId="sjqjts" style="width:50%" maxlength="4" onkeyup="value=value.replace(/(?:\D*)?(\d*)?(\.)?(5)?(?:\d*)?/ig,'$1$2$3')"></html:text>&nbsp;&nbsp;天&nbsp;&nbsp;
							</logic:notEmpty>
							<logic:empty  name="XjsqshForm" property="sjqjts">
								<html:text property="sjqjts" styleId="sjqjts" style="width:50%" maxlength="4" value="${data.qjts}" onkeyup="value=value.replace(/(?:\D*)?(\d*)?(\.)?(5)?(?:\d*)?/ig,'$1$2$3')"></html:text>&nbsp;&nbsp;天&nbsp;&nbsp;
							</logic:empty>
						</td>
						<th align="right" width="16%">
							<span class="red">*</span>实际请假时间
						</th>
					   <td align="left"  width="34%">
					   		<logic:notEmpty name="XjsqshForm" property="sjkssj">
						   		<html:text property="sjkssj"  styleId="sjkssj" onfocus="return showCalendar(this.id,'yyyy-MM-dd HH:mm',true,'sjjssj');" style="width:43%"/>
								~
								<html:text property="sjjssj"  styleId="sjjssj" onfocus="return showCalendar(this.id,'yyyy-MM-dd HH:mm',false,'sjkssj');" style="width:43%"/>
					   		</logic:notEmpty>
					   		<logic:empty name="XjsqshForm" property="sjkssj">
					   			<html:text property="sjkssj" value="${data.kssj }" styleId="sjkssj" onfocus="return showCalendar(this.id,'yyyy-MM-dd HH:mm',true,'sjjssj');" style="width:43%"/>
								~
								<html:text property="sjjssj" value="${data.jssj }" styleId="sjjssj" onfocus="return showCalendar(this.id,'yyyy-MM-dd HH:mm',false,'sjkssj');" style="width:43%"/>
					   		</logic:empty>
						
					   </td>
					</tr>
					<tr id="fjtr">
							<th>
								附件
							</th>
							<td colspan="3">
								<html:hidden property="xjfilepath" styleId="xjfilepath" />
								<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
	                               <script type="text/javascript">
										//调用附件 
										jQuery(function(){
											jQuery.MultiUploader({
												maxcount : 3,//后缀
												accept : 'png|gif|jpg|zip|rar|doc|docx',//最大文件大小 单位M
												maxsize: 10,//存放附件的隐藏域的id
												elementid : 'xjfilepath'
											});
										});
								</script>
							</td>
						</tr>
					<tr>
						<th align="right"  width="16%">
							<span class="red">*</span>销假信息&nbsp;
							<br />
							<font color="red">(限250字)</font>
						</th>
						<td colspan="3"  width="34%">
							<html:textarea rows="5"  property="xjbz" styleId="xjbz" style="width:98%" onblur="checkLen(this,250);"></html:textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div style="height:50px;">
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
								<button type="button"  onclick="saveForm('save');return false;" id="buttonSave">
									保存草稿
								</button>
								<button type="button"  onclick="saveForm('submit');return false;" id="buttonSave">
									提交申请
								</button>
								<button type="button"  onclick="iFClose();" id="buttonClose">
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
