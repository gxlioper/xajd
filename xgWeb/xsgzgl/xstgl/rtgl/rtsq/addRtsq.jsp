<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xstgl/rtgl/rtsq/js/rtsq.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			jQuery("#stxmmc").attr({readonly:"readonly"});
			if(jQuery("#xh") != null){
				jQuery("#xh").attr({readonly:"readonly"});
			}
			jQuery("#selxm").unbind('click').bind('click', function(){
			    var xh = jQuery("#xh").val() || jQuery("a[class='name']").text();
				if(xh == ''){
					showAlert("请先填写基本信息！");
					return false;
				}else{
				  var url = "stglRtsq.do?method=getStxmList&xh="+xh;
					showDialog('社团项目选择',770,550,url);
				}
		   });
		});
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/stglRtsq" method="post" styleId="RtsqForm">
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
								<span>社团项目维护</span>
							</th>
						</tr>
					</thead>
					<tbody id="stxm_body">
						<tr>
							<th><font color="red">*</font>社团项目名称</th>
							<td>
								<html:text property="stxmmc" styleId="stxmmc" style="width:124px;"/>
								<button class="btn_01" id="selxm" type="button" >选择</button>
								<input type="hidden" name="stid" id ="stid">
								<input type="hidden" id="splc" name="splc"/>
								<input type="hidden" name="xmlbdm" id ="xmlbdm"/>
							</td>
							<th></th>
							<td></td>
						</tr>
						<tr>
							<th>社团类别</th>
							<td name="stlb">
								
							</td>
							<th>项目类别</th>
							<td name="xmlb">
							   
							</td>
							
						</tr>
						<tr>
							<th>有效学年</th>
							<td name="xn">
								
							</td>
							<th>挂靠单位</th>
							<td name="gkdw">
								
							</td> 
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
							   <a style="text-align: left;" onclick="showPfzmx(this);" class="down"
									href="javascript:void(0);"> <font color="blue">点击展开/收起</font>
								</a>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_toggle" style="display:none">
					<!--  版本升级去除kssj和jssj
						<tr>
							<th>社团有效开始时间</th>
							<td name="kssj"></td>
							<th>社团有效截止时间</th>
							<td name="jssj"></td>
						</tr>
					-->
						<tr>
							<th>负责人类别</th>
							<td name="fzrlb"></td>
							<th>社团负责人</th>
							<td name="stfzrxm"></td>
						</tr>
						<%--<tr>--%>
							<%--<th>指导老师</th>--%>
							<%--<td name="zdlsxm"></td>--%>
							<%--<th>指导老师职称</th>--%>
							<%--<td name="zdlszc"></td>--%>
						<%--</tr>--%>
						<%--<tr>--%>
							<%--<th>指导老师联系方式</th>--%>
							<%--<td name="zdlslxfs"></td>--%>
							<%--<th>所属部门</th>--%>
							<%--<td name="ssbm"></td>--%>
						<%--</tr>--%>

						<tr>
							<th>社团联系电话</th>
							<td name="lxdh"></td>
							<th>建团人</th>
							<td name="jtr"></td>
						</tr>
						<tr>
							<th>社团成立时间</th>
							<td name="stclsj"></td>
							<th>申请时间</th>
							<td name="sqsj"></td>
						</tr>
						<tr>
							<th>
								社团简介
							</th>
							<td colspan="3" name="stsm">
								
							</td>
						</tr>
						<tr>
							<th>
								社团获奖情况
							</th>
							<td colspan="3" name="sthjqk">
								
							</td>
						</tr>
					<tr>
					<thead id="zdlsthead" style="display:none">
					<tr>
						<th colspan="4">
							<span>指导老师</span>
						</th>
					</tr>
					</thead>
					<tbody id="zdlstbody" style="display:none">
					<tr colspan="4">
						<td width="100%" colspan="4">
							<table width="100%" id="tablebody">
								<tbody id="nr">
								<tr>
									<th width="30%" style="text-align:left;">指导老师姓名</th>
									<th width="20%" style="text-align:left;">所属部门</th>
									<th width="20%" style="text-align:left;">联系电话</th>
									<th width="20%" style="text-align:left;">职称</th>
								</tr>
								</tbody>
							</table>
						</td>
					</tr>
					</tbody>
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
							<!-- 
							<th>社团人员类别</th>
							<td>
								<html:select property="rylbdm" styleId="rylbdm" style="width:50%">
									<html:options collection="rylblist" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
							 -->
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
								    <button type="button" id="bc" onclick="saveRtSq('save');">
										保    存
									</button>
									<button type="button" id="tjsq" onclick="saveRtSq('submit');">
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