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
	function saveSh(){
		if (jQuery("#shzt").val() == "" || jQuery("#shzt").val() == null || jQuery("#shyj").val().trim() == "" || jQuery("#shyj").val().trim() == null){
			showAlert("请将必填项填写完整！");
			return false;
		}
		var url = "zyhd.do?method=BcDgsh";
		ajaxSubFormWithFun("qnzyhdForm",url,function(data){
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
	<html:form action="/zyhd" method="post" styleId="qnzyhdForm">
		<input type="hidden" name="hdid" value="${data.hdid}"/>
		<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
		<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
			<thead>
					<tr>
						<th colspan="4">
							<span>志愿者活动信息</span>
						</th>
					</tr>
			</thead>				
			<tbody>
				<tr>
					<th style="width:16%">
						活动名称
					</th>
					<td style="width:34%">
						${data.hdmc}
					</td>
					<th style="width:16%">
						服务类型
					</th>
					<td style="width:34%">
						${data.fwlxmc}
					</td>
				</tr>
				<tr>
					<th>
						基本服务工时
					</th>
					<td>
						${data.jbfwgs}
					</td>
					<th>
						活动地点
					</th>
					<td>
						${data.hddd}
					</td>
				</tr>
				<tr>
					<th>
						服务对象
					</th>
					<td>
						${data.fwdx}
					</td>
					<th>
						限定人数
					</th>
					<td>
						${data.xdrs}
					</td>
				</tr>
				<tr>
					<th>
						报名截止时间
					</th>
					<td>
						${data.bmjzsj}
					</td>
					<th>
						组织部门
					</th>
					<td>
						${data.zzbm}
					</td>
				</tr>
				<tr>
					<th>
						活动开始时间
					</th>
					<td>
						${data.hdkssj}
					</td>
					<th>
						活动结束时间
					</th>
					<td>
						${data.hdjssj}
					</td>
				</tr>
				<tr>
					<th>
						负责人姓名
					</th>
					<td>
						${data.fzrxm}
					</td>
					<th>
						负责人手机号
					</th>
					<td>
						${data.hdfzrlxfs}
					</td>
				</tr>
				<tr>
					<th>
						开放学院
					</th>
					<td colspan="3">
						${data.xymc}
					</td>
				</tr>
				<tr>
					<th>
						海报
					</th>
					<td colspan="3">
						<logic:notEmpty name="data" property="fjpath"> 
							<img src="${data.fjpath}" width="170px" height="130px"/>
						</logic:notEmpty>
						<logic:empty name="data" property="fjpath">
							<img src="default_dekt.jpg" width="170px" height="130px"/>
						</logic:empty>
					</td>
				</tr>
				<tr>
					<th>
						活动详情
					</th>
					<td colspan="3">
						${data.hdxq}
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
						<font color="red">*&nbsp;</font>审核结果
					</th>
					<td colspan="3">
						<select id="shzt" name="shzt">
							<option value="1">通过</option>
							<option value="2">不通过</option>
							<option value="3">退回</option>
						</select>
					</td>
				</tr>
			</tbody>
			<tbody>
				<tr>
					<th>
						<font color="red">*&nbsp;</font> 审核意见
						<br />
						<font color="red">(限200字)</font>
					</th>
					<td colspan="3">
						<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
					</td>
				</tr>
			</tbody>
			</table>
		</div>
		<div style="height: 30px"></div>
		<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">	
			<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="保存"  onclick="saveSh();return false;">
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
