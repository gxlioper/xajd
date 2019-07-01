<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/ystgl/rtgl/rtsh/js/rtsh.js"></script>
		<script type="text/javascript" src="xsgzgl/ystgl/rtgl/comm/js/comm.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		jQuery(function(){
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${form.rtid}&tt="+new Date().getTime());
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${form.splc}&shid=${form.shid}");
		});
		function saveSh(){
			
			var shzt = jQuery("#shjg").val();
			if (jQuery("#shjg").val() == "" || jQuery("#shyj").val().trim() == ""){
				showAlert("请将必填项填写完整！");
				return false;
			}
			var url = "ystglRtsh.do?method=Rtdgsh&type=save";
			ajaxSubFormWithFun("YstRtshForm",url,function(data){
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
		<html:form action="/ystglRtsh" method="post" styleId="YstRtshForm">
				<html:hidden  name ="form" property="rtid" styleId="rtid"/>
				<html:hidden  name ="form" property="xh" styleId="xh"/>
				<html:hidden  name ="form" property="splc" styleId="splc" value="${ystxx.splc}"/>
				<html:hidden  name ="form" property="sqsj" styleId="sqsj"/>
				<html:hidden  name ="form" property="shid" styleId="shid"/>
				<html:hidden  name ="form" property="shzt" styleId="shzt"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>艺术团信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>艺术团项目名称</th>
							<td>${ystxx.ystxmmc}</td>
							<th></th>
							<td></td>
						</tr>
						<tr>
							<th>艺术团类别</th>
							<td>${ystxx.ystlbmc}</td>
							<th>项目类别</th>
							<td>${ystxx.xmlbmc}</td>
							
						</tr>
						<tr>
							<th>有效学年</th>
							<td>${ystxx.xn }</td>
							<th>挂靠单位</th>
							<td>${ystxx.gkdwmc}</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
							   <a style="text-align: left;" onclick="showYstmx(this);" class="down"
									href="javascript:void(0);"> <font color="blue">点击展开/收起</font>
								</a>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_toggle" style="display:none">
						<tr>
							<th>负责人类别</th>
							<td>${ystxx.fzrlb }</td>
							<th>艺术团负责人</th>
							<td>${ystxx.stfzrxm }</td>
						</tr>
						<tr>
							<th>指导老师</th>
							<td>${ystxx.zdlsxm }</td>
							<th>指导老师职称</th>
							<td>${ystxx.zcmc }</td>
						</tr>
						<tr>
							<th>指导老师联系方式</th>
							<td>${ystxx.zdlslxfs }</td>
							<th>所属部门</th>
							<td>${ystxx.ssbmmc }</td>
						</tr>
						<tr>
							<th>艺术团联系电话</th>
							<td>${ystxx.lxdh }</td>
							
							<th>建团人</th>
							<td>${ystxx.jtr }</td> 
						</tr>
						<tr>
							<th>艺术团成立时间</th>
							<td>${ystxx.ystclsj }</td>
							<th>申请时间</th>
							<td>${ystxx.sqsj }</td>
						</tr>
						<tr>
							<th>
								艺术团简介
							</th>
							<td colspan="3" name="stsm">
								${ystxx.ystjj}
							</td>
						</tr>
						<tr>
							<th>
								艺术团获奖情况
							</th>
							<td colspan="3" name="ysthjqk">
								${ystxx.ysthjqk}
							</td>
						</tr>
					</tbody>
					<tbody>
						<tr>
							<th>申请理由</th>
							<td colspan="3">${ystxx.sqly}</td>
						</tr>
						<tr>
							<th>特长</th>
							<td colspan="3">${ystxx.tc}</td>
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
				
				<tr>
					<th width="20%">
						<font color="red">*&nbsp;</font> 审核意见
						<br />
						<font color="red">(限200字)</font>
					</th>
					<td colspan="3">
						<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=ystrtsh&id=shyj" />
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