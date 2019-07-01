<%@ page language="java" import="java.util.*,xgxt.utils.String.StringUtils" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/xsgw/js/xsgwsh.js"></script>
		
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type="text/javascript" src="js/xgservice.js"></script>
		<script type='text/javascript' src="js/moveDiv.js"></script>
		<script type='text/javascript' src="js/AjaxFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script> 
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript' src="js/messageFunction.js"></script>

		<script type="text/javascript">
			jQuery(function(){
				jQuery("#but_close").click();
				jQuery("#but_save").click();
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.sqbh}&tt="+new Date().getTime());
                jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${model.splc}&shid=${model.shid}");
			});
            function save_sh(){
                var shzt=jQuery("#shjg").val();
                jQuery("#shzt").val(shzt);
                if (jQuery("#shyj").val() == ""){
                    showAlertDivLayer("请填写审核意见！");
                    return false;
                }
                if (jQuery("#shyj").val().length>200){
                    showAlertDivLayer("审核意见不能超过200字");
                    return false;
                }
                //提交审核
                showConfirmDivLayer("您确定审核该申请吗？",{"okFun":function(){
                    var url = "qgzx_xsgwsh.do?method=xslzSh&type=save&tt="+new Date();
                    ajaxSubFormWithFun("demoForm",url,function(data){
                        showAlertDivLayer(data["message"],{},{"clkFun":function(){
                            //if (parent.window){
                            refershParent();
                            //}
                        }});
                    });
                }});
            }
		</script>
	</head>
	<body style="width:100%">
		<html:form action="/qgzx_xsgwsh.do?method=xslzSh&type=save" method="post" styleId="demoForm">
			<input type="hidden" name="sqbh" value="${model.sqbh}"/>
			<input type="hidden" name="splc" value="${model.splc}"/>
			<input type="hidden" name="xh" value="${xh}"/>
			<textarea name="sqly" style="display: none;">${model.sqly}</textarea><%--退岗理由--%>
			<div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
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
								<span>岗位信息</span> 
							</th>
						</tr>
					</thead>
					<tbody id="tbody_gwxx">
					<tr>
						<th width="16%">
							学年
							<input type="hidden" id="gwdm" name="gwdm" value="${model.gwdm }">
						</th>
						<td width="34%">
								${model.xn }
						</td>
						<th width="16%">
							用人单位
						</th>
						<td width="34%">
								${model.yrdwmc }
						</td>
					</tr>
					<tr>
						<th width="16%">
							岗位名称
						</th>
						<td width="34%">
								${model.gwmc }
						</td>
						<th width="16%">
							工作性质
						</th>
						<td width="34%">
							<logic:equal name="model" property="gwxzdm" value="0"> 临时</logic:equal>
							<logic:equal name="model" property="gwxzdm" value="1">正式</logic:equal>
						</td>
					</tr>
					<tr>
						<th width="16%">
							招聘人数
						</th>
						<td width="34%">
								${model.xqrs}人
						</td>

						<th width="16%">
							岗位类型
						</th>
						<td>
							<logic:equal name="model" property="gwlx" value="0">临时</logic:equal>
							<logic:equal name="model" property="gwlx" value="1">长期</logic:equal>
						</td>
					</tr>
					<tr>
						<th>
							岗位类别
						</th>
						<td >
								${model.gwxzmc}
						</td>
						<th>
							岗位薪酬上限
						</th>
						<td>
								${model.gwcjsx}元
						</td>
					</tr>
					<tr>
						<th>
							工时上限
						</th>
						<td colspan="3">
								${model.gssx}小时
							<span id="label"></span>
						</td>
					</tr>
					<tr>
						<th>
							招聘开始时间
						</th>
						<td>
								${model.zpkssj}
						</td>
						<th>
							招聘结束时间
						</th>
						<td>
							<logic:equal name="model" property="cq" value="1">长期</logic:equal>
							<logic:notEqual name="model" property="cq" value="1">${model.zpjssj}</logic:notEqual>
						</td>
					</tr>
					<tr>
						<th>
							招聘要求
						</th>
						<td colspan="3">
								${model.gwryyq}
						</td>
					</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>离职信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red"></font>离职申请时间
							</th>
							<td colspan="3">
								${model.sqsj}
							</td>
						</tr>
						<tr>
							<th width="16%">
								离职原因
							</th>
							<td colspan="3">
								${model.sqly}
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
							<th>
								<font color="red">*</font>审核结果
							</th>
							<td colspan="3" id="shjgSpan">
								
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>审核意见
								<br />
								<font color="red">限200字</font>
							</th>
							<td  colspan="3">
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=xsgw&id=shyj" />
								<textarea rows="5" style="width: 100%;margin-top: 5px" id="shyj" name="shyj"></textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<table width="100%" border="0" class="formlist">
			<tfoot>
						<tr>
							<td colspan="4" >
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
								<button type="button" id="btqd" onclick="save_sh();">
									确定
								</button>
								<button type="button" name="关 闭" onclick="iFClose();">
									关 闭
								</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
	</body>
</html>

