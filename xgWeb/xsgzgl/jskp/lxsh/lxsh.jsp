<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
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
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${LxshxForm.sqid}&tt="+new Date().getTime());
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${LxshxForm.splcid}&shid=${LxshxForm.shid}",function(){
					jQuery("#shjg").change(function(){
						var tempvalue = this.value;
						if(tempvalue == "2" || tempvalue == "3"){
							jQuery("#pfjqtr").hide();
						}else{
							jQuery("#pfjqtr").show();
						}
					})
					});
			});
			function saveSh(){
				var shzt = jQuery("#shjg").val();
				if (jQuery("#shjg").val() == "" || jQuery("#shyj").val() == ""){
					showAlert("请将必填项填写完整！");
					return false;
				}
				if(shzt == "1" ){
					if(jQuery("#zxf").val()== "" || jQuery("#zdf").val() == ""){
						return showAlert("审核结果为通过时，评分区间必填！");
					}
					if(parseInt(jQuery("#zxf").val()) > parseInt(jQuery("#zdf").val())){
						return showAlert("评分区间最大分不能小于最低分！");
					}
					
				}
				var url = "jskp_lxsh.do?method=saveLxsh";
				ajaxSubFormWithFun("LxshxForm",url,function(data){
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
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/jskp_lxsh" method="post" styleId="LxshxForm">
		<html:hidden  property="sqid" styleId="sqid"/>
		<html:hidden  property="fzr" styleId="fzr"/>		
		<html:hidden  property="splcid" styleId="splcid"/>
			
		<html:hidden  property="shid" styleId="shid"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>立项信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:15%">项目名称</th>
							<td style="width:35%">
								${LxshxForm.xmmc}
							</td>
							<th style="width:15%">指导部门</th>
							<td style="width:35%">
								${bmmc}
							</td>
						</tr>	
						<tr>
							<th>项目类别</th>
							<td>
								${xmlbmc}
							</td>
							<th>立项时间</th>
							<td>
								${LxshxForm.lxsj}
							</td>
						</tr>
						<tr>
							<th>负责人</th>
							<td>
								<span>${fzrxm}</span>
							</td>
							<th>负责人联系方式</th>
							<td>
								${LxshxForm.fzrlxfs}
							</td>
						</tr>
						<tr>
							<th>指导老师</th>
							<td>
								${LxshxForm.zdls}
							</td>
							<th>指导老师联系方式</th>
							<td>
								${LxshxForm.zdlslxfs}
							</td>
						</tr>
						<tr>
							<th>评分区间</th>
							<td colspan="3">
								${LxshxForm.zxf}-${LxshxForm.zdf}
							</td>
						</tr>
						<tr>
							<th>参与人
							</th>
							<td colspan="3">
									<logic:iterate id="i" name="xhList">
										${i.xh}(${i.xm})&nbsp;
									</logic:iterate>
							</td>
						</tr>
						<tr>
							<th>
								附件
							</th>
							<td colspan="3">
							<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
								jQuery(function(){
									var gid = "${LxshxForm.filepath}";
									jQuery.MultiUploader_q({
										gid : gid,
										targetEl : 'commonfileupload-list-0'
									});
								});
								</script>
							</td>
						</tr>
						<tr>
							<th>
								立项理由
							</th>
							<td colspan="3">
								${LxshxForm.lxly}
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
						</tr>
						<tr id = "pfjqtr">
							<th><font class="red">*</font>评分区间</th>
							<td colspan="3">
								<input name="zxf" style="width:80px" type="text" maxlength="8" id="zxf" value="${shzdxx.zxf}" onkeyup="checkInput(this)" onblur="replaceAboveZero(this)" /> - <input name="zdf" style="width:80px" maxlength="8" value="${shzdxx.zdf}"   type="text" id="zdf" onkeyup="checkInput(this)" onblur="replaceAboveZero(this)" />
							</td>
						</tr>
						
						<tr>
							<th width="20%">
								<font color="red">*&nbsp;</font> 审核意见
								<br />
								<font color="red">(限200字)</font>
							</th>
							<td colspan="3">
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=lxsh&id=shyj" />
								<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
							</td>
						</tr>
					</tbody>	
				</table>
				</div>	
				<div style="height:30px;"></div>
				<%--;height:520px --%>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="保存"  onclick="saveSh();return false;">
										保 存
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