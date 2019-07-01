<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/xyddk/xdsq/js/xdsq.js"></script>
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
				
				ajaxSubFormWithFun("HsdxdForm",url,function(data){
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
			
			function showXdsq(obj){
				var parentObj = jQuery(obj).parent().parent();
				var xdxn = jQuery(parentObj).find("[name='xn']").val();
				var xdje = jQuery(parentObj).find("[name='dkze']").val();
				var id = jQuery(parentObj).find("[name='jgid']").val();
				var htbh = jQuery(parentObj).find("[name='htbh']").text();
				jQuery("#addxn").text(xdxn);
				jQuery("#xdxn").val(xdxn);
				jQuery("#id").val(id);
				jQuery("#addxdje").text(xdje)
				jQuery("#xdje").val(xdje);
				jQuery("#htbh").val(htbh);
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
		
		<html:form action="/gjdk_xdsqnew" method="post" styleId="HsdxdForm" onsubmit="return false;">
			<%-- 
			<html:hidden property="splc" value="${splc}"/>
			--%>
			<div>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>贷款信息<font color='blue'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(单位:元)</font></span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								贷款期数
							</th>
							<td width="34%">
								${dkxx.dkqs}
							</td>
							<th width="16%">
								贷款期限(月)
							</th>
							<td width="34%">
								${dkxx.dkqx}
							</td>
						</tr>
						<tr>
							<th>
								贷款总金额
							</th>
							<td>
								${dkxx.dkje}
							</td>
							<th>
								累计放贷金额
							</th>
							<td>
								${fkzh}
							</td>
						</tr>
							<tr>
							<td colspan="4">
								<table style="width:100%;text-align: center;">
									<tr>
										<th style="text-align: center;" width = "10%">学年</th>
										<th style="text-align: center;" width = "12%">住宿费贷款额</th>
										<th style="text-align: center;" width = "10%">学费贷款额</th>
										<th style="text-align: center;" width = "12%">生活费贷款额</th>
										<th style="text-align: center;" width = "14%">年住宿费应缴额</th>
										<th style="text-align: center;" width = "12%">年学费应缴额</th>
										<th style="text-align: center;" width = "10%">合同编号</th>
										<th style="text-align: center;" width = "10%">放款状态</th>
										<th style="text-align: center;" width = "10%">操作状态</th>
									</tr>
									<logic:iterate id="x" name="xdxxList">
										<tr>
											<td>
												${x.xn}
												<input type="hidden" name="jgid" value="${x.jgid}"/>
												<input type="hidden" name="dkze" value="${x.dkze}"/>
												<input type="hidden" name="xn" value="${x.xn}"/>
											</td>
											<td>${x.nzsfdk}</td>
											<td>${x.nxfdk}
											</td>
											<td>
												${x.nshfdk}
											</td>
											<td>
												${x.nzsfyje}
											</td>
											<td>
												${x.nxfyje}
											</td>
											<td name="htbh">${x.htbh}</td>
											<td>
												${x.fkztmc}
											</td>
											<td>
												<logic:equal value="czan" name="x" property="cznr">
													<button type="button" style="width:50px" onclick="showXdsq(this);return false;">申请</button>
				                                    <button type="button" style="width:50px;margin-top:5px" onclick="qxsq(this);return false;">放弃</button>	
												</logic:equal>
												<logic:notEqual value="czan" name="x" property="cznr">
													${x.cznr}
												</logic:notEqual>
											</td>
										</tr>
									</logic:iterate>
								</table>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
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
							<td colspan="4" width="100%">
								<table style="width:100%;text-align: center;">
									<tr>
										<th style="text-align: center;"><input type="checkbox" name="selectAll" onclick="selectAll(this)" width="5%" /></th>
										<th style="text-align: center;" width = "19%">合同编号</th>
										<th style="text-align: center;" width = "19%">续贷学年</th>
										<th style="text-align: center;" width = "19%">续贷金额(元)</th>
										<th style="text-align: center;" width = "19%">续贷理由</th>
										<th style="text-align: center;" width = "19%">审核状态</th>
									</tr>
									<logic:iterate id="x" name="xdsqList">
										<tr>
											<td><input type="checkbox" name="sqid" value="${x.id}"  />
												<input name="shzt" type="hidden" value="${x.shzt}"/>
												<input type="hidden" name="splc" value="${x.splc }"/>
											</td>
											<td>${x.htbh}</td>
											<td>${x.xdxn}<input name="xn" type="hidden" value="${x.xdxn}"/></td>
											<td>${x.xdje}<input name="xdjes" type="hidden" value="${x.xdje}"/></td>
											<td id="xdlyss">${x.xdly}</td>
											<td>
												${x.shztmc}
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
							<th colspan="4">
								<span>续贷确认</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">学年</th>
							<td colspan="3" width="84%">
								<label id="addxn"></label>
								<input type="hidden" name="xdxn" id="xdxn"/>
								<input type="hidden" name="id" id="id"/>
								<input type="hidden" name="htbh" id="htbh"/>
							</td>
						</tr>
						<tr>
							<th width="16%">续贷金额(元)</th>
							<td colspan="3" width="84%">
								<label id="addxdje"></label>
								<input type="hidden" name="xdje" id='xdje'/>
							</td>
						</tr>
						<tr>
							<th width="16%"><font color="red">*</font>续贷理由
								<br/><font color="red">(限输入400字)</font>
							</th>
							<td colspan="3" width="84%">
								<html:textarea property="xdly" styleId="xdly" 
											   onblur="checkLen(this,400);"
											   style="width:99%;" rows="4"/>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
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
							<th colspan="4">
								<span>续贷确认</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">学年</th>
							<td colspan="3" width="84%">
								<label id="updatexdxn"></label>
							   <input type="hidden" name="id2"  id="id2"/>
								
							</td>
						</tr>
						<tr>
							<th width="16%">续贷金额(元)</th>
							<td colspan="3" width="84%">
								<label id = "updatexdje" ></label>
							</td>
						</tr>
						<tr>
							<th width="16%"><font color="red">*</font>续贷理由
								<br/><font color="red">(限输入400字)</font>
							</th>
							<td colspan="3" width="84%">
								<textarea name="xdlys" id="xdlys" 
											   onblur="checkLen(this,400);"
											   style="width:99%;" rows="4"/>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
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