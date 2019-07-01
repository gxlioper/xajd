<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript">
	jQuery(function(){
		jQuery.ajaxSetup({async:false});
		jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${rs.sqid}&tt="+new Date().getTime());
		getSpje();
		jQuery.ajaxSetup({async:true});
		jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${rs.splc}&shid=${rs.shid}");
	});

	//获取审批金额
	function getSpje(){
		if(jQuery("#shlccx_table tr").length>2){
			jQuery("#zd3").val(jQuery.trim(jQuery("#shlccx_table tr").eq(-2).find("td").eq(-1).text()));
		}else{
			jQuery("#zd3").val(jQuery("#sqje").val());
		}
	}
	
	function saveSh(){
		var shzt = jQuery("#shjg").val();
		if (jQuery("#shzt").val() == "" || jQuery("#shyj").val() == ""){
			showAlert("请将必填项填写完整！");
			return false;
		}
		var xxdm = jQuery("#xxdm").val();
		var zd3 = jQuery("#zd3").val();	
		if(xxdm == "10511"){
			if(shzt == "1"){
				if(zd3 == ""){
					showAlert("请填写批准金额！");
					return false;
				}
				if(parseInt(zd3) == 0){
					showAlert("批准金额不能为0！");
					return false;
				}
				if(parseInt(zd3) > 1000){
					showAlert("批准金额上限为1000！");
					return false;
				}
			}
		}
		
		
		var url = "ypzl_sh.do?method=sbDgsh&type=save";
		ajaxSubFormWithFun("ypzlshForm",url,function(data){
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
	<html:form action="/ypzl_sh" method="post" styleId="ypzlshForm">
		<html:hidden name="rs" property="sqid" styleId="sqid"/>
		<html:hidden name="rs" property="splc" styleId="splc"/>
		<input type="hidden"  id="xxdm" value="${xxdm}"/>
		<input type="hidden" name="sqje" id="sqje" value="${rs.sqje}"/>
		<html:hidden property="shid" styleId="shid"/>
		<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
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
								${rs.xn}
							</td>
							</td>
							<th>学期</th>
							<td>
								${xqsr}
							</td>
						</tr>
						<tr>
					    	<th>申请金额（元）</th>
					    	<td>
								${rs.sqje}
							</td>
							<logic:equal value="10511" name="xxdm">
								<th>申请原因类别</th>
								<td>
									${ytmc}
								</td>
							</logic:equal>
					    </tr>
					    <logic:equal value="10511" name="xxdm">
				    		<tr>
								<th>
									材料上传
								</th>
								<td colspan="3">
										<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
						            <html:hidden property="ytms" styleId="ytms" value="${rs.ytms}" />
						            <script type="text/javascript">
								        //调用附件 
										jQuery(function(){
											var gid = jQuery('#ytms').val();
											jQuery.MultiUploader_q({
												gid : gid
												});
										});
									</script>
								</td>
							</tr>
					    </logic:equal>
					    <logic:notEqual value="10511" name="xxdm">
							<tr>
								<th align="right" width="10%">
									附件信息
								</th>
								<td colspan="3">
									<div id="commonfileupload-list-0" style="padding: 5px;"></div>
									<script type="text/javascript">
										//调用附件 
										jQuery(function(){
											var gid = "${rs.filepath}";
											jQuery.MultiUploader_q({
												gid : gid,
												targetEl : 'commonfileupload-list-0'
											});
										});
									</script>
								</td>
							</tr>	
						</logic:notEqual>
						<tr>
							<th>
								申请理由
							</th>
							<td colspan="3">
								${rs.sqly}
							</td>
			      		</tr>	
					</tbody>
				 </table>
		
			<table width="100%" border="0" class="formlist">
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
					<tr>
						<th >
							审核结果
						</th>
						<td id="shjgSpan">
							
						</td>
						<logic:equal value="10511" name="xxdm">
							<th >
						<font color="red">*</font>批准金额(元)
					</th>
					<td>
						<html:text property="zd3" styleId="zd3" style="width:90px;" onblur="replaceAboveZero(this)" maxlength="4" onkeyup="checkInput(this)"></html:text>
						<html:hidden property="zd1" styleId="zd1" value="批准金额(元)"/>
					</td>
						</logic:equal>
					
					</tr>
			</tr>
			<tr>
				<th width="20%">
					<font color="red">*&nbsp;</font> 审核意见
					<br />
					<font color="red">(限200字)</font>
				</th>
				<td colspan="3">
					<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=ypzlsh&id=shyj" />
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
