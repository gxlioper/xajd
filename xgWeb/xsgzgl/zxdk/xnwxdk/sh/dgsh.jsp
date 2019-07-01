<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		
	<script type="text/javascript">
	jQuery(function(){
		jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${zsjgxx.sqid}&tt="+new Date().getTime());
		jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${zsjgxx.splc}&shid=${zsjgxx.shid}");
	});
	function saveSh(){
		var shzt = jQuery("#shjg").val();
		if (jQuery("#shjg").val() == "" || jQuery("#shyj").val() == "" || jQuery("#zd3").val() == ""){
			showAlert("请将必填项填写完整！");
			return false;
		}

		if(parseInt(jQuery("#zd3").val()) > parseInt('${jesx}')){
			showAlert("超出申请金额上限"+'${jesx}'+"！");
			return false;
		}
		var url = "xnwxdk_sh.do?method=xnwxdkDgsh&type=save";
		ajaxSubFormWithFun("XnwxdkShModel",url,function(data){
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
	<html:form action="/xnwxdk_sh" method="post" styleId="XnwxdkShModel">
		
		<html:hidden  property="sqid" styleId="sqid"/>
		<html:hidden  property="xh" styleId="xh"/>		
		<html:hidden name="zsjgxx" property="splc" styleId="splc"/>
		<html:hidden name="zsjgxx" property="sqsj" styleId="sqsj"/>
			
		<html:hidden name="zsjgxx" property="shid" styleId="shid"/>
	     <%-- 
		<html:hidden  property="cjbz" styleId="cjbz"/>
	        --%>
		<html:hidden name="zsjgxx"  property="xn" styleId="xn"/>
  		<html:hidden name="zsjgxx"  property="xq" styleId="xq"/>
		<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
		   <table width="100%" border="0" class="formlist">			
			<thead>
				<tr>
					<th colspan="4">
						<span>学生信息</span>
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
							<th>学年</th>
							<td>
								${zsjgxx.xn}
							</td>
							<th>学期</th>
							<td>
							    ${xqmc}
							</td>
						</tr>
						<tr>
							<th>家庭提供（元）</th>
							<td>
								${zsjgxx.jttg}
							</td>
							<th>助学金（元）</th>
							<td>
								${zsjgxx.zxj}
							</td>
						</tr>
						<tr>
							<th>奖学金（元）</th>
							<td>
							    ${zsjgxx.jxj}
							</td>
							<th>勤工助学收入（元）</th>
							<td>
							    ${zsjgxx.qgzxsr}
							</td>
						</tr>
						<tr>
							<th>校内无息借款  （元）</th>
							<td>
							    ${zsjgxx.xnwxjk}
							</td>
							<th>其他收入 （元）</th>
							<td>
							     ${zsjgxx.qtsr}
							</td>
						</tr>
						<tr>
							<th>助学贷款金额（元）</th>
							<td>
							     ${zsjgxx.zxdksqje}
							</td>
							<th>助学贷款时间 </th>
							<td>
							    ${zsjgxx.zxdksqsj}
							</td>
						</tr>
						<tr>
							<th>发放金额（元）</th>
							<td>
							    ${zsjgxx.ffje}
							</td>
							<th>发放时间 </th>
							<td>
							    ${zsjgxx.ffsj}
							</td>
						</tr>
						<tr>
							<th>本次申请金额（元）</th>
							<td>
							     ${zsjgxx.sqje}
							</td>
						</tr>
						<tr>
							<th>申请理由</th>
							<td colspan="3">
								 ${zsjgxx.sqly}
							</td>
						</tr>			
						<tr>
							<th align="right" width="10%">
								附件信息
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										var gid = "${zsjgxx.filepath}";
										jQuery.MultiUploader_q({
											gid : gid,
											targetEl : 'commonfileupload-list-0'
										});
									});
								</script>
							</td>
						</tr>
					</tbody>
			<thead>
				<tr>
					<th colspan="4">
						<span>审批信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="4" id="shlccx">
					
					</td>
				</tr>
			</tbody>
			<thead>
				<tr>
					<th colspan="4">
						<span>审核信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th >
						审核结果
					</th>
					<td id="shjgSpan">
						
					</td>
					<th >
						<font color="red">*</font>批准金额(元)
					</th>
					<td>
						<html:text property="zd3" styleId="zd3" style="width:90px;" onkeyup="value=value.replace(/[^\d]/g,'');" value=""></html:text>&nbsp;&nbsp;<font color="blue">上限金额：${jesx}<font/>
						<html:hidden property="zd1" styleId="zd1" value="批准金额"/>
					</td>
				</tr>
				
				<tr>
					<th width="20%">
						<font color="red">*&nbsp;</font> 审核意见
						<br />
						<font color="red">(限200字)</font>
					</th>
					<td colspan="3">
						<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=zxdkxnwxdk&id=shyj" />
						<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
					</td>
				</tr>
			</tbody>	
		  </table>
		</div>
		<div style="height: 30px"></div>
		<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">	
			<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="保存"  onclick="saveSh();return false;">
									保 存
								</button>
								<button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
		</table>
		<!-- 提示信息 -->
		<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
</body>
</html>
