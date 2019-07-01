<%@ page language="java"
	import="java.util.*,xgxt.utils.String.StringUtils"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function save(){
			    var sqly = jQuery("#sqly").val();
			    if(sqly == ""){
                    showAlertDivLayer("请填写离职原因！");
                    return false;
				}
                showConfirmDivLayer("您确定提交该申请吗？",{"okFun":function(){
                    var url = "qgzx_wdgwsq.do?method=lzsq&type=save";
                    ajaxSubFormWithFun("demoForm",url,function(data){
                        showAlertDivLayer(data["message"],{},{"clkFun":function(){
                            if (parent.window){
                                refershParent();
                            }
                        }});
                    });
                }});
			}
		</script>
	</head>
	<body style="width: 99%">
		<html:form action="qgzx_wdgwsq.do?method=lzsq" method="post"
			styleId="demoForm">
			<div style='width: 100%; overflow-x: hidden; overflow-y: auto;'>
				<table width="100%" border="0" class="formlist">
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
								<input type="hidden" id="gwdm" name="gwdm"
									value="${model.gwdm }">
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
						<th>
							<span class="red">*</span>离职原因
							<br />
							<span class="red">(限1500字)</span>
						</th>
						<td  colspan="4">
							<textarea rows="5" style="width: 100%" onblur="checkLen(this,1500)" id="sqly" name="sqly"></textarea>
						</td>
					</tr>
					</tbody>
				</table>
			</div>
			<div style="height: 30px">
			</div>
			<table width="100%" border="0" class="formlist"
				style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">"<span class="red">*</span>"为必填项</div>
							<div class="btn">
								<button type="button" type="button" onclick="save();return false;">
									提交申请
								</button>
								<button type="button" type="button" onclick="iFClose();return false;" id="but_close">
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

