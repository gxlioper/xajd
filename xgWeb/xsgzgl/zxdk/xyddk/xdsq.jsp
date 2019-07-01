<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/xyddk/js/xdxx.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function saveXdsq(url){
				if (jQuery("#xdly").val() == ""){
					showAlertDivLayer("请将必填项填写完整！");
					return false;
				}
				/*加入字数限制判断*/
				if(jQuery("#xdly").val().length > 400){
					showAlertDivLayer("续贷理由不能超过400字！");
					return false;
				}
				
				ajaxSubFormWithFun("xdsqForm",url,function(data){
					if (data["message"] == "保存成功！") {
						showAlertDivLayer(data["message"],{},{"clkFun":function(){
							document.location.href=document.location;
						}});
					}else{
						showAlertDivLayer(data["message"]);
						return false;
					}
				});
			}
			
			function showXdsq(){
				if(jQuery("#htbh").val() == "" || jQuery("#htbh").val() == null ||  jQuery("#htbh").val() == "null" ){
					showAlertDivLayer("无合同编号，不能进行续贷！");
					return false;
				}
				var xdxn = jQuery("#xdxn").val();
				var ysqxn = jQuery(":input[name=ysqxn][value="+xdxn+"]");
				
				if (ysqxn.size() > 0){
					showAlertDivLayer("当前学年已申请续贷，无需重复申请。");
					return false;
				}

				//西安科技大学个性化
				if(jQuery("#xxdm").val() == '10704'){
					var flg = true;
					jQuery.ajaxSetup({async:false});
					jQuery.post("zxdkDkjg.do?method=checkSfXd", {}, function(data) {
						if(!data['result']){
							flg = false;					
						}	      		
						}, 'json');
					jQuery.ajaxSetup({async:true});
					if(!flg){
						showAlertDivLayer("您上学年有单科成绩不及格，不予申请！");
						return false;
					}
				}
				
				jQuery("#xdsqTable").css("display","");
			}
			
		</script>
		<style type="text/css">
			.hiddenfont{width:155px;height:15px;display:block;white-space: nowrap;overflow: hidden;text-overflow: ellipsis;}
		</style>
	</head>
	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		
		<html:form action="/zxdkDkjg" method="post" styleId="xdsqForm" onsubmit="return false;">
			<html:hidden property="htbh" styleId="htbh" name="dkxx"/>
			<input type="hidden" id="xxdm" value="${xxdm}" />
			<%-- 
			<html:hidden property="splc" value="${splc}"/>
			--%>
			<div>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>贷款信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="2">
								<table style="width:100%;text-align: center;">
									<tr id="theadtr">
										<th style="text-align: center;">合同编号</th>
										<th style="text-align: center;">贷款总金额(元)</th>
										<th style="text-align: center;">累计放款金额(元)</th>
										<th style="text-align: center;">贷款期限(年)</th>
										<th style="text-align: center;">操作</th>
									</tr>
									<tr>
										<td>${dkxx.htbh }</td>
										<td id="dkze">${dkxx.dkje }</td>
										<td>${dkxx.fkze }</td>
										<td>${dkxx.dkqx }</td>
										<td>
											<button type="button" class="btn_01" onclick="showXdsq();">
												申请续贷
											</button>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="2">
								<span>续贷信息</span> 
								  <button type="button" style="margin-top:2px;margin-left:30px" onclick="update();return false;">修改</button>
				                  <button type="button"  onclick="submitBusi();return false;">提交</button>
				                  <button type="button" onclick="cancel();return false;">撤销</button>
				                  <button type="button" onclick="del();return false;">删除</button>
				                  <button type="button" onclick="lcgz();return false;">流程跟踪</button>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="2">
								<table style="width:100%;text-align: center;">
									<tr>
										<th style="text-align: center;" width = "5%"><input type="checkbox" name="chk" onclick="selectAll(this)"/></th>
										<th style="text-align: center;" width = "20%">合同编号</th>
										<th style="text-align: center;" width = "15%">续贷学年</th>
										<th style="text-align: center;" width = "20%">续贷金额(元)</th>
										<th style="text-align: center;" width = "20%">续贷理由</th>
										<th style="text-align: center;" width = "20%">审核状态</th>
									</tr>
									<logic:iterate id="x" name="xdsqList">
										<tr>
											<td><input type="checkbox" name="id" value="${x.id}" /></td>
											<td>${x.htbh }</td>
											<td>${x.xdxn }
												<input type="hidden" name="ysqxn" value="${x.xdxn }"/>
											</td>
											<td>
												<%-- 续贷金额是否可改，有待讨论 
												<logic:equal value="0" property="shzt" name="x">
													<input type="text" onkeyup="checkInput(this)" onblur="inputblur(this)" maxlength="10" name="yxdje" value="${x.xdje }"/>
												</logic:equal>
												<logic:equal value="3" property="shzt" name="x">
													<input type="text" onkeyup="checkInput(this)" onblur="inputblur(this)" maxlength="10" name="yxdje" value="${x.xdje }"/>
												</logic:equal>
												<logic:notEqual value="0"  property="shzt" name="x">
													<logic:notEqual value="3"  property="shzt" name="x">
														${x.xdje }
													</logic:notEqual>
												</logic:notEqual>
												--%>
												<label name="yxdje">${x.xdje }</label>
											</td>
											<td><label>
											<%--
												<logic:equal value="0" property="shzt" name="x">
													<input type="text" name="yxdly" onblur="inputblur(this)" maxlength="400" value="${x.xdly }"/>
												</logic:equal>
												<logic:equal value="3" property="shzt" name="x">
													<input type="text" name="yxdly" onblur="inputblur(this)" maxlength="400" value="${x.xdly }"/>
												</logic:equal>
												<logic:notEqual value="0"  property="shzt" name="x">
													<logic:notEqual value="3"  property="shzt" name="x">
														${x.xdly }
													</logic:notEqual>
												</logic:notEqual>
												--%>
											   <label class="hiddenfont" title="${x.xdly }">${x.xdly }</label>
											   <input type="hidden" name="yxdly" value="${x.xdly }"/>
											</td>
											<td>${x.shztmc}
												<input type="hidden" name="shzt" value="${x.shzt }"/>
												<input type="hidden" name="splc" value="${x.splc }"/>
											</td>
										</tr>
									</logic:iterate>
									<logic:empty name="xdsqList">
										<tr>
											<td colspan="6">
												无续贷申请记录！
											</td>
										</tr>
									</logic:empty>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table class="formlist"  style="display:none;" id="xdsqTable">
					<thead>
						<tr>
							<th colspan="2">
								<span>续贷确认</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">学年</th>
							<td>
								${dqxn }
								<input type="hidden" name="xdxn" value="${dqxn }" id="xdxn"/>
							</td>
						</tr>
						<tr>
							<th>续贷金额(元)</th>
							<td>
								${dkxx.mnje }
								<input type="hidden" name="xdje" value="${dkxx.mnje }"/>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>续贷理由
								<br/><font color="red">(限输入400字)</font>
							</th>
							<td>
								<html:textarea property="xdly" styleId="xdly" 
											   onblur="checkLen(this,400);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveXdsq('zxdkDkjg.do?method=saveXdsq&type=save');">
										保存草稿
									</button>
									<button type="button" onclick="saveXdsq('zxdkDkjg.do?method=saveXdsq&type=submit');">
										提交
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
				<table class="formlist"  style="display:none;" id="xdsqTable2">
					<thead>
						<tr>
							<th colspan="2">
								<span>续贷确认</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">学年</th>
							<td>
								<label id="dqxn"></label>
							   <input type="hidden" name="id2"  id="id2"/>
								
							</td>
						</tr>
						<tr>
							<th>续贷金额(元)</th>
							<td>
								<label id = "mnje" ></label>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>续贷理由
								<br/><font color="red">(限输入400字)</font>
							</th>
							<td>
								<textarea name="xdlys" id="xdlys" 
											   onblur="checkLen(this,400);"
											   style="width:99%;" rows="4"></textarea>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveXdsq2('update');">
										保存草稿
									</button>
									<button type="button" onclick="saveXdsq2('updatesubmit');">
										提交
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