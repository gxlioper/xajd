<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.sqid}&tt="+new Date().getTime());
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${model.splcid}&shid=${model.shid}",function(){
					jQuery("#shjg").change(function(){
						var tempvalue = this.value;
						if(tempvalue == "2" || tempvalue == "3"){
							jQuery("#fs").val("");
							jQuery("#pftr").hide();
						}else{
							jQuery("#pftr").show();
						}
					})
				});
			});
			
			/**
			 * 单个审核保存
			 * @return
			 */
			function xspjshSingleSave(){
				
				var shjg = jQuery("#shjg").val();
				
				if (jQuery("#shjg").val() == "" || jQuery("#shyj").val() == ""|| (shjg == "1" && jQuery("#fs").val() == "")){
					showAlert("请将带<font color='red'>*</font>项填写完整！");
					return false;
				}
				/*判断分数是否大于0小于10*/
				if ("1" == shjg){
					var fs = jQuery("#fs").val();
					if(parseFloat(fs) > 9999 || parseFloat(fs) < 0){
						showAlert("分数不在评分区间内！");
						return false;
					}
				}

				var url = "xspj_xspjsh.do?method=xspjshSingleSave";
				ajaxSubFormWithFun("xspjshForm",url,function(data){
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
	<html:form action="/xspj_xspjsh" method="post" styleId="xspjshForm">
		<html:hidden name="model" property="sqid" styleId="sqid"/>
		<html:hidden name="model" property="xh" styleId="xh"/>		
		<html:hidden name="model" property="splcid" styleId="splcid" value="${rs.splcid}"/>
		<html:hidden name="model" property="shid" styleId="shid"/>
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
							<span>项目信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
						<tr>
							<th>项目名称</th>
							<td>${rs.xmmc}</td>
							<th>指导部门</th>
							<td>${rs.zdbmmc}</td>
						</tr>
							<th>项目类别</th>
							<td>${rs.xmlbmc}</td>
							<th>参与时间</th>
							<td>${rs.cysj}</td>
						<tr>
							<th>参与人(学号)</th>
							<td>${rs.xh}</td>
							<th>学年</th>
							<td>${rs.xn}</td>
						</tr>
						<tr>
							<th>短学期</th>
							<td>${rs.dxqmc}</td>
							<th>分值</th>
							<td>${rs.fz}</td>
						</tr>
						<tr>
							<th>负责人</th>
							<td>${rs.fzrxm}</td>
							<th>负责人<br/>联系方式</th>
							<td>${rs.fzrlxfs}</td>
						</tr>
						<tr>
							<th>指导老师</th>
							<td>${rs.zdlsxm}</td>
							<th>指导老师<br/>联系方式</th>
							<td>${rs.zdlslxfs}</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								附件信息
							</th>
							<td colspan="3">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<html:hidden name="rs" property="fjid" styleId="fjid"/>
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
						<font color="red">*</font>审核结果
					</th>
					<td id="shjgSpan" colspan="3">&lt;
						
					</td>
					
				</tr>
				<tr id="pftr">
					<th >
						<font color="red">*</font>评分
					</th>
					<td colspan="3">
						<html:text style="width:60px" property="fs" styleId="fs"  value="${shxxLevel.zd3}" maxlength="4" onkeyup="checkInputNum(this)"></html:text>
					</td>
				</tr>
			<!-- 因学校要求，  审核时候需要增加导入人的工号，知道数据是哪里来的-->
				<tr>
					<th>数据来源方式</th>
					<td colspan="3">
						<font color="blue">${rs.sjlyfs}</font>
					</td>
				</tr>
				<tr>
					<th width="20%">
						<font color="red">*&nbsp;</font> 审核意见
						<br />
						<font color="red">(限200字)</font>
					</th>
					<td colspan="3">
						<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=sbsh&id=shyj" />
						<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
					</td>
				</tr>
			</tbody>
			</table>
		</div>
		<div style="height: 50px"></div>
		<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">	
			<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button" name="保存"  onclick="xspjshSingleSave();return false;">
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