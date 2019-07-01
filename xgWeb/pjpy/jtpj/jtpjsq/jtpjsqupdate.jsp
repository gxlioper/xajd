<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="pjpy/jtpj/jtpjsq/js/jtpjsq.js"></script>
		<script type="text/javascript" src="pjpy/jtpj/js/jtpjyz.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				jQuery("#jxid").bind("change",function(){
					loadJxxx();
					 jxidChange();
				});
				jQuery("#jxid").change();
				
			});

			/**
			 * 保存
			 * 
			 * @param url
			 * @param checkId
			 * @return
			 */
			function update(url,type,checkId) {
				setJtpjmc();
				if (!checkNull(checkId)) {
					return false;
				}
				if (!checkother()) {
					return false;
				}
				if(!bjyzts()){
					return false;
				}
				lock();
				url+=type;

			     // 原审核状态
			     var shztGet = jQuery("#shzt").val();
			     // 非退回的提交
			     if(shztGet != '3' && 'update'==type){
			    	// 验证是否可提交
						jQuery.post(action + "?method=checkSfktj", {
							jxid:jQuery("#jxid").val()
						}, function(data) {
							if(data ==null || data=="false"){
								showAlertDivLayer("您申请的奖项已关闭申请，无法提交，详情请联系管理员。");
								return false;
							}else{								
								jQuery("#form").ajaxSubmit( {
									url : url,
									type : "post",
									dataType : "json",
									success : function(data) {
									 if(data["message"] == "提交成功！"){
											showAlert(data["message"], {}, {
												"clkFun" : function() {
													if (parent.window) {
														refershParent();
													}
												}
											});
									 }else{
										 showAlert(data["message"]);
									 }
									
									},
									contentType : "application/x-www-form-urlencoded;charset=utf-8"
								});
							}
						});
			     }else{
					jQuery("#form").ajaxSubmit( {
						url : url,
						type : "post",
						dataType : "json",
						success : function(data) {
							showAlert(data["message"], {}, {
								"clkFun" : function() {
									if (parent.window) {
										refershParent();
									}
								}
							});
						},
						contentType : "application/x-www-form-urlencoded;charset=utf-8"
					});
			     }
				unlock();
			}
		</script>
	</head>
	<body>
		<html:form method="post" styleId="form" action="/jtpjsq.do?method=add&type=save">
			<html:hidden property="splcid" styleId="splcid"/>
			<html:hidden property="shzt" styleId="shzt"/>
			<html:hidden property="sqid" styleId="sqid" value="${data.sqid}"/>
			<input type="hidden" id="mrpjjtiid" value="${data.pjjtid}"/> 
			<html:hidden property="type" value="update" styleId="type"/>
			<input type="hidden" id="first" value="1"/>
			<div style='tab;width:100%;height:460px;overflow-x:hidden;overflow-y:auto;'>
				<table width="" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>集体奖项申请信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								申请学年
							</th>
							<td width="30%" id="sqxn">
								${data.sqxn}
							</td>
							<th width="20%">
								申请学期
							</th>
							<td width="30%" id="sqxq">
								${xqmc}
							</td>
						</tr>
						<tr>
							<th >
								<span class="red">*</span>申请奖项
							</th>
							<td>
								<html:select property="jxid" styleId="jxid"	style="width:200px;" value="${data.jxid}">
									<html:option value=""></html:option>
									<html:options collection="jxList" property="jxid" labelProperty="jxmc" />
								</html:select>
							</td>
							<th >
								评定周期
							</th>
							<td id="pdzq">
							</td>
						</tr>
						<tr>
							<th>
								奖项说明
							</th>
							<td colspan="3" id="jxsm">
							</td>
						</tr>
					</tbody>
				</table>
				<div id="jtpjxx">
				
				</div>
				<div id="divyzts">
					
				</div>
				<table width="" border="0" class="formlist">
					<tr>
						<th>
							附件
						</th>
						<td colspan="3">
							<html:hidden property="filepath" value="${data.filepath}" styleId="fjid"/>
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
										elementid : 'fjid'
										});
								});
							</script>						
						</td>
					</tr>	
					<tr>
						<th width="20%" style="border-top: 0px;">
							<font color='red'>*</font>申请理由
							<br />
							<font color="red">限500字</font>
						</th>
						<td colspan="3" style="border-top: 0px;">
							<html:textarea property="sqly" rows="4" cols="70" styleId="sqly" onblur="checkLen(this,500);"></html:textarea>
						</td>
					</tr>
				</table>
			</div>
			<div style="hight: 30px"></div>
			<div>
				<table width="100%" border="0" class="formlist"
					style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" style="display:none"
										onclick="update('jtpjsq.do?method=update&type=','save','jxid-sqly');return false;"
										id="buttonSave">
										保存草稿
									</button>
									<button type="button"
										onclick="update('jtpjsq.do?method=tj&type=','update','jxid-sqly');return false;"
										id="buttonSave">
										提交申请
									</button>
									<button type="button" onclick="iFClose();" id="buttonClose">
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
