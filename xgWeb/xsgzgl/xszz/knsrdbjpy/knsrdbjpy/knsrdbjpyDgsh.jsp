<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/knsrdbjpy/knsrdbjpy/js/knsrdbjpy.js"></script>
		<script type="text/javascript">

			jQuery(function(){
				
				//加载下拉选项
				loadViewMkxxSelectOptions();
				//加载radio选项
				loadViewMkxxRadioOptions();

				var xh = jQuery("#xh").val();
				if (jQuery.trim(xh) != ""){
					jQuery("#div_jtqk").load("xszz_jtqkdc.do?method=jtqkInfo",{xh:xh},function(){
					})
				}

				//前一级认定档次
				//var beforeRddc = jQuery("input[name=tjdc][value !=]").last().val();
				//jQuery("#rddc").val(beforeRddc);

				//非第一级审核有退回按钮
				//var firstGwid = jQuery("input[name=gwid]").first().val();
				//if (firstGwid != jQuery("#xtgwid").val()){
				//	jQuery("#btn_th").css("display","");
				//}

				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid="+jQuery("#guid").val()+"&tt="+new Date().getTime());
				
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid="+jQuery("#shlc").val()+"&shid="+jQuery("#shid").val());
				
			});
			function jgcxView(){
				var url = "xszz_knsrdbjpy_jgcxgl.do?method=jgcxView&xn=${mkxxForm.xn}&xq=${mkxxForm.xq}&sqr=${mkxxForm.xh}&shztbjpy=99";
				var title = "查看评议详情";
				showDialog(title,800,500,url);
			}
		</script>
	</head>
	<body>
		<html:form action="/xszz_knsrdbjpy" method="post" styleId="knsrdbjpyForm">
			<html:hidden property="guid" styleId="guid"/>
			<html:hidden property="xtgwid" styleId="xtgwid"/>
			<html:hidden property="xh" styleId="xh"/>
			<html:hidden property="shzt" styleId="shzt"/>
			<html:hidden property="shlc" styleId="shlc"/>
			<html:hidden property="shid" styleId="shid"/>
			<input type="hidden" value="${xxdm}" id="xxdm"/>
			<div style='tab;width:100%;height:460px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xszz/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>认定历史记录
									<a onclick="showLsjl(this);" class="down" 
									   href="javascript:void(0);">
									   <font color="blue">点击展开/收起</font>	
									</a>
								</span>
							</th>
						</tr>
					</thead>
					<tbody id="t_rdlsjl" style="display: table-row-group;">
						<tr>
							<td colspan="4">
							<table class="dateline" width="100%">
								<thead align="left">
									<tr align="left">
										<td ><b>学年</b></td>
										<td ><b>学期</b></td>
										<td><b>认定档次</b></td>
										<td ><b>认定时间</b></td>
									</tr>
								</thead>
								<tbody align="left">
							<logic:notEmpty name="rdlsjlList">
							<logic:iterate name="rdlsjlList" id="s">
										<tr  style="cursor:hand">
										<td >
												${s.xn}
											</td>
											<td >
												${s.xqmc}
											</td>
											<td >
												${s.dcmc}
											</td>
											<td >
												${s.sqsj}
											</td>
										</tr>
								</logic:iterate>
							</logic:notEmpty>
							</tbody>
							</table>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>家庭情况
									<a onclick="showJtqk(this);" class="up" 
									   href="javascript:void(0);">
									   <font color="blue">点击展开/收起</font>	
									</a>
								</span>
							</th>
						</tr>
					</thead>
					<tbody id="t_jtqk" style="display: none;">
						<tr>
							<td colspan="4">
								<div id="div_jtqk">
								
								</div>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>困难生认定申请信息</span>
							</th>
						</tr>
					</thead>
					
					<tr>
						<th>
							附件信息
						</th>
						<td colspan="3">
							<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
							<html:hidden property="ylzd2" styleId="fjid"/>
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
					
					<logic:equal value="1" name="sqsftxdc">
						<tbody>
							<tr>
								<th align="right">申请档次</th>
								<td colspan="3">
									${mkxxForm.sqdcmc }
								</td>
							</tr>
						</tbody>
					</logic:equal>
					<%@ include file="/xsgzgl/xszz/bdpz/mkxxView.jsp" %>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>班级评议结果<a href='javascript:void(0);' class='name' onclick='jgcxView();return false;'>查看评议详情</a></span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								评议小组成员
							</th>
							<td colspan="3">
								${mkxxForm.bjpyxzcyxms }
							</td>
						</tr>
						<tr>
							<th>
								评议小组代表
							</th>
							<td colspan="3">
								${mkxxForm.bjpyxzdbxms }
							</td>
						</tr>
						<tr>
							<th>
								推荐档次
							</th>
							<td colspan="3">
								${mkxxForm.pddcmc }
							</td>
						</tr>
						<tr>
							<th>
								认定理由
							</th>
							<td colspan="3" style="word-break: break-all;">
								${mkxxForm.bjpyjgrdly }
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
							<td colspan="4" id="shlccx">
							
							</td>
						</tr>
						<tr>
							<th align="right"><font color="red">*</font>推荐档次</th>
							<td colspan="3">
								<html:select property="rddc" styleId="rddc">
									<html:option value=""></html:option>
									<html:options collection="knsdcbjpyList" property="dcdm" labelProperty="dcmc"/>
								</html:select>
							</td>
						</tr>
						<logic:equal value="10335" name="xxdm">
						<tr>
							<th align="right"><font color="red">*</font>无偿资助金额</th>
							<td colspan="3">
								<html:select property="ylzd3" styleId="ylzd3">
									<html:option value=""></html:option>
									<html:options collection="wczzjeList" property="zzjedm" labelProperty="zzjemc"/>
								</html:select>
							</td>
							
						</tr>
						</logic:equal>
						<tr>
							<th>
								<font color="red">*</font>审核结果
							</th>
							<td colspan="3" id="shjgSpan">
								
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>
								审核意见
								<br/>
								<font color="red">(限200字)</font>
							</th>
							<td colspan="3">
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=knsrdbjpy&id=shyj" />
								<html:textarea property="shyj" style="width:98%;margin-top:5px" rows="5"
											   onblur="checkLen(this,200);" styleId="shyj"
								></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
							<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
			
								<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" id="btqd" onclick="saveKnssh();">
										保 存
									</button>
<%--									<button type="button" onclick="saveKnssh('1','通过');">--%>
<%--										通过--%>
<%--									</button>--%>
<%--									<button type="button" onclick="saveKnssh('2','不通过');">--%>
<%--										不通过--%>
<%--									</button>--%>
<%--									<button type="button" onclick="saveKnssh('3','退回');" id="btn_th" style="display:none;">--%>
<%--										退回--%>
<%--									</button>--%>
									<button type="button" name="关 闭" onclick="iFClose();">
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

