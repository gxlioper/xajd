<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
	    <script type="text/javascript" src="xsgzgl/xszz/hjxf/sq/js/hjxf.js"></script>		
	    <script type="text/javascript" src="xsgzgl/xszz/hjxf/js/comm.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript"
			src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" defer="defer"></script>
		<script type="text/javascript">
			jQuery(function(){
				var xh = jQuery("#xh").val();
				var xn = jQuery("#xn").val();
				var xq = jQuery("#xq").val();
				if (jQuery.trim(xh) != ""){
					jQuery("#div_jtqk").load("xszz_jtqkdc.do?method=jtqkInfo",{xh:xh});
					var xn = jQuery("#xn").val();
					var xq = jQuery("#xq").val();

					selectXsYzFz(xh,xn,xq,"qb");
				}
			});
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/hjxf_sq" method="post" styleId="HjxfSqForm">
		    <html:hidden property="checksj" value="${jqjzsj}" styleId="checksj"/>
		    <html:hidden property="pkdj" styleId="pkdj"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:520px;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>家庭情况 <logic:notEqual value="" property="xh"
										name="HjxfSqForm">
										<a onclick="showJtqk(this,'t_jtqk');" class="up"
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
								<span>历史申请缓交信息 <logic:notEqual value="" property="xh"
										name="HjxfSqForm">
										<a onclick="showJtqk(this,'t_lsxx');" class="up"
											href="javascript:void(0);"> <font color="blue">点击展开/收起</font>
										</a>
									</logic:notEqual> </span>
							</th>
						</tr>
					</thead>
					<tbody id="t_lsxx" style="display: none;" style="width:100%">
						<tr style="width:100%">
							<td width="100%" colspan="4">
									<table width="100%">
										<tbody id="tbody_lsxx" width="100%">
											
										</tbody>
									</table>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>本学年申请缓交信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>学年</th>
							<td>
								${xn}
								<html:hidden property="xn" styleId="xn"/>
								
							</td>
							<th>学期</th>
							<td>
							    ${xqmc}
								<html:hidden property="xq" styleId="xq"/>
							</td>
						</tr>
						<tr>
							<th>贫困认定等级</th>
							<td id="knsdj">
							</td>
							<th></th>
							<td>
							</td>
						</tr>
						<tr>
							<th><font class="red">*</font>贷款情况</th>
							<td>
								<html:select property="dkqk" styleId="dkqk">
									<html:option value="无">无</html:option>
									<html:option value="高校国家助学贷款">高校国家助学贷款</html:option>
									<html:option value="生源地助学贷款">生源地助学贷款</html:option>
								</html:select>
							</td>
							<th><font class="red">*</font>往年欠费金额(元)</th>
							<td>
								<html:text property="wnqfje" styleId="wnqfje" onkeyup="checkMoneyBykeyUp(this)"    maxlength="10"></html:text>
							</td>
						</tr>
						<tr>
							<th><font class="red">*</font>应缴金额 (元) </th>
							<td>
							    <html:text property="yjje" styleId="yjje" onkeyup="checkMoneyBykeyUp(this)"   maxlength="10"></html:text>
							</td>
							<th><font class="red">*</font>缓交学费(元) </th>
							<td>
								<html:text property="hjje" styleId="hjje" onkeyup="checkMoneyBykeyUp(this)"   maxlength="10"></html:text>
							</td>
						</tr>
						<tr>
							<th><font class="red">*</font>缴清截止时间 </th>
							<td>
								<html:text property="jqjzsj" styleId="jqjzsj" onclick="return showCalendar('jqjzsj','y-mm-dd');"  maxlength="10" onblur="dateFormatChg(this);"></html:text>
							</td>
								<th></th>
							<td>
							</td>
						</tr>
						<tr>
							<th><font class="red">*</font>家庭经济收入主要来源及困难情况
								</br><font color="red">(限500字)</font></th>
							</th>
							<td colspan="3">
							  <html:textarea property="sqyy" styleId="sqyy" 
											   onkeyup="checkzs();"
											   style="width:99%;" rows="5" ></html:textarea>
							</td>
						</tr>
						<tr>
							<th>附件</th>
							<td colspan="3">
							<html:hidden property="filepath" styleId="filepath" />
							<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
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
												elementid : 'filepath'
												});
										});
										
							</script>
							
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
								<div class="bz">
									"<font class="red">*</font>"为必填项
								</div>
								<div class="btn">
									<button type="button" id="bccg" onclick="saveHjxfSq('save');">
										保存草稿
									</button>
									<button type="button" id="tjsq" onclick="saveHjxfSq('submit');">
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