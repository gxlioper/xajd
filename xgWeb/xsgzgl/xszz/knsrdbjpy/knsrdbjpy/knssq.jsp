<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript"
			src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="xsgzgl/xszz/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/knsrdbjpy/knsrdbjpy/js/knsrdbjpy.js"></script>
		<script type="text/javascript" defer="defer">

			jQuery(function(){
				
				var ishave = jQuery("#ishave").val();
				var type = jQuery("#type").val();
				
				if(!"type"==type&&"true" == ishave){
					showAlertDivLayer("当前周期已有申请记录，无需重复申请");
					lock();
					return false;
				}
				/*//是否弹出家庭情况调查填写页面
				var openJtqk = jQuery("#openJtqk").val();

				if ("true" == openJtqk){
					var xh = jQuery("#xh").val();

					showAlertDivLayer("请先填写家庭情况调查表！",{},{"clkFun":function(){
						editJtqk();
					}});
				}*/
				//加载下拉选项
				loadMkxxSelectOptions();
				//加载radio选项
				loadMkxxRadioOptions();

				var xh = jQuery("#xh").val();
				if (jQuery.trim(xh) != ""){
					jQuery("#div_jtqk").load("xszz_jtqkdc.do?method=jtqkInfo",{xh:xh});
				}

				jQuery(function(){
					var isopen = jQuery("#isopen").val();
					var shzt = jQuery("#shzt").val();
					if('3' != shzt && (isopen==null||isopen==''||"false" == isopen)){
						jQuery("#btn_submit").hide();
					}
				});
				
			});

			function lock(){
				jQuery(".btn").find("button").each(function(){
					jQuery(this).attr("disabled","disabled");
					jQuery(this).css({color:"#cccccc"});
				});
			}
			
		</script>
	</head>
	<body>
		<input type="hidden" id="openJtqk" value="${openJtqk }" />
		<input type="hidden" name="isopen" id="isopen" value="${isopen}" />
		<input type="hidden" name="sqsftxdc" id="sqsftxdc" value="${sqsftxdc}" />
		<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" />
		<input type="hidden" name="shzt" id="shzt" value="${mkxxForm.shzt }" />
		<button id="search_go" type="button" style="display: none" onclick="reloadWindow();"></button>

		<html:form action="/xszz_knsrdbjpy" method="post" styleId="knsrdbjpyForm" onsubmit="return false">
			<html:hidden property="guid" />
			<input type="hidden" id="ishave" value="${ishave}" />
			<input type="hidden" id="type" value="${type }" />
			<html:hidden property="shzt" styleId="shzt" />
			<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }" />

			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>

					<%@ include file="/xsgzgl/xszz/bdpz/selectStudentKnsrdsqBjpy.jsp"%>

					<thead>
						<tr>
							<th colspan="4">
								<span>家庭情况 <logic:notEqual value="" property="xh"
										name="knsrdbjpyForm">
										<a onclick="showJtqk(this);" class="up"
											href="javascript:void(0);"> <font color="blue">点击展开/收起</font>
										</a>
										|
										<a onclick="editJtqk();" class="btn_xg"
											href="javascript:void(0);"> <font color="blue">编辑家庭情况</font>
										</a>
									</logic:notEqual> </span>
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
								<span>困难生认定申请</span>
							</th>
						</tr>
					</thead>
					<tr>
						<th>
							<logic:equal value="12861" name="xxdm"><font color="red">*</font></logic:equal>附件信息
						</th>
						<td colspan="3">
							<html:hidden property="ylzd2" styleId="ylzd2" />
							<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
							<logic:equal value="12861" name="xxdm">
							   <script type="text/javascript">
										//调用附件 
										jQuery(function(){
											jQuery.MultiUploader({
												maxcount : 3,
												//后缀
												accept : 'png|gif|jpg',
												//最大文件大小 单位M
												maxsize: 10,
												//存放附件的隐藏域的id
												elementid : 'ylzd2'
												});
										});
										
									</script>
                            </logic:equal>
                            <logic:notEqual value="12861" name="xxdm">
                               <script type="text/javascript">
										//调用附件 
										jQuery(function(){
											jQuery.MultiUploader({
												maxcount : 3,
												//后缀
												accept : 'png|gif|jpg|zip|rar|doc|docx',
												//最大文件大小 单位M
												maxsize: 10,
												//存放附件的隐藏域的id
												elementid : 'ylzd2'
												});
										});
										
							</script>
                            </logic:notEqual>
							
						</td>
					</tr>
					<logic:equal value="1" name="sqsftxdc">
						<tbody>
							<tr>
								<th>
									<logic:equal value="10718" name="xxdm"><font color="red">* </font></logic:equal><logic:equal value="12861" name="xxdm"><font color="red">*</font></logic:equal>困难档次
								</th>
								<td colspan="3">
									<html:select property="ylzd1" styleId="ylzd1">
										<html:option value=""></html:option>
										<html:options collection="knsdcbjpyList" property="dcdm"
											labelProperty="dcmc" />
									</html:select>
								</td>
							</tr>
						</tbody>
					</logic:equal>
					<%@ include file="/xsgzgl/xszz/bdpz/mkxxUpdate.jsp"%>
				</table>
			</div>
			<div>	
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" id="save_button" type="button"
										onclick="saveKnssq('save');">
										保存草稿
									</button>
									<button type="button" id="btn_submit" type="button"
										onclick="saveKnssq('submit');">
										提交申请
									</button>
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

