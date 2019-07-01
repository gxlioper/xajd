<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/ystgl/rtgl/rtsq/js/rtsq.js"></script>
		<script type="text/javascript" src="xsgzgl/ystgl/rtgl/comm/js/comm.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			jQuery("#ystxmmc").attr({readonly:"readonly"});
			if(jQuery("#xh") != null){
				jQuery("#xh").attr({readonly:"readonly"});
			}
			
		});
		</script>
	</head>
	<body>
		<html:form action="/ystglRtsq" method="post" styleId="YstRtsqForm">
		<input type="hidden" id="usertype" value="${usertype}"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<logic:notEqual name="${usertype}" value="stu" >
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					</logic:notEqual>
					<logic:equal name="${usertype}" value="stu">
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					</logic:equal>
					<thead>
						<tr>
							<th colspan="4">
								<span>艺术团信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="ystxm_body">
						<tr>
							<th><font color="red">*</font>艺术团项目名称</th>
							<td colspan="3">
								<html:text property="ystxmmc" styleId="ystxmmc" style="width:124px;"/>
								<button class="btn_01" id="selxm" type="button" onclick="selYst()">选择</button>
								<input type="hidden" name="ystid" id ="ystid">
								<input type="hidden" id="splc" name="splc"/>
								<input type="hidden" name="xmlbdm" id ="xmlbdm"/>
							</td>
						</tr>
						<tr>
							<th>艺术团类别</th>
							<td name="ystlb">
								
							</td>
							<th>项目类别</th>
							<td name="xmlb">
							   
							</td>
							
						</tr>
						<tr>
							<th>联系电话</th>
							<td name="lxdh">
								
							</td>
							<th>挂靠单位</th>
							<td name="gkdwmc">
								
							</td> 
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
							<td name="fzrlb"></td>
							<th>负责人</th>
							<td name="stfzrxm"></td>
						</tr>
						<tr>
							<th>指导老师</th>
							<td name="zdlsxm"></td>
							<th>指导老师职称</th>
							<td name="zcmc"></td>
						</tr>
						<tr>
							<th>指导老师联系方式</th>
							<td name="zdlslxfs"></td>
							<th>所属部门</th>
							<td name="ssbmmc"></td>
						</tr>
						<tr>
							<th>艺术团成立时间</th>
							<td name="ystclsj"></td>
							<th>申请时间</th>
							<td name="sqsj"></td>
						</tr>
						<tr>
							<th>
								艺术团简介
							</th>
							<td colspan="3" name="ystjj">
								
							</td>
						</tr>
						<tr>
							<th>
								艺术团获奖情况
							</th>
							<td colspan="3" name="ysthjqk">
								
							</td>
						</tr>
					</tbody>
					<tbody>
						<tr>
							<th><font color="red">*</font>特长</br><font color="red">(限100字)</font></th>
							<td colspan="3">
								<html:textarea property="tc" styleId="tc" 
								   onkeyup="checkzs(this);" 
								   style="width:99%;" rows="3"></html:textarea>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>申请理由</br><font color="red">(限100字)</font></th>
							<td colspan="3">
								<html:textarea property="sqly" styleId="sqly" 
								   onkeyup="checkzs(this);" 
								   style="width:99%;" rows="3"></html:textarea>
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
								    <button type="button" id="bc" onclick="saveYstRtsq('save');">
										保存草稿
									</button>
									<button type="button" id="tjsq" onclick="saveYstRtsq('submit');">
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