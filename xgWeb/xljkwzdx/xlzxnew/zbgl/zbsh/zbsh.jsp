<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xljkwzdx/xlzxnew/zbgl/zbsh/js/zbsh.js"></script>
		<script type="text/javascript" defer="defer">
		jQuery(function(){
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${ZbshForm.sbsqid}&tt="+new Date().getTime());
			jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${ZbshForm.splcid}&shid=${ZbshForm.shid}");
		})
			function saveSh(){
		var shzt = jQuery("#shjg").val();
		if (jQuery("#shjg").val() == "" || jQuery("#shyj").val() == ""){
			showAlert("请将必填项填写完整！");
			return false;
		}
		var url = "xlzxnew_zbsh.do?method=saveZbsh";
		ajaxSubFormWithFun("ZbshForm",url,function(data){
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
		<html:form action="/xlzxnew_zbsh" method="post" styleId="ZbshForm">
			<html:hidden property="sbsqid"/>
			<html:hidden property="splcid"/>
			    <div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>上报信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
							<tr>
								<th width="20%">
									学年
								</th>
								<td width="30%">
									${zbrc.xn}<input type="hidden" name="xn" />
								</td>
								<th width="20%">
									学期
								</th>
								<td width="30%">
									${xqmc}<input type="hidden" name="xq" />
								</td>	
							</tr>
							<tr>
								<th>
									周次
								</th>
								<td>
									${zbrc.zbzc}
								</td>
								<th>
									起止日期
								</th>
								<td>
									${zbrc.zbksrq} 至  ${zbrc.zbjsrq}
								</td>
							</tr>
							<tr>
								<th>班级名称</th>
								<td>${bjxx.bjmc}
									<input type="hidden" name="bjdm" id="bjdm" value="${bjxx.bjdm}"/>
								</td>
								<th>班主任</th>
								<td>${bjxx.xm}</td>
							</tr>
						<tr>
							<th>
								本周内班级发生的<br />
								重大事件<br />
							</th>
							<td colspan="3" style="word-break: break-all;">
								${ZbshForm.ztqk}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>个别情况描述</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th colspan="5">
								<table width="100%" >
									<thead>
										<tr>
											<th width='20%' style="text-align:center">学号</th>
											<th width='20%' style="text-align:center">姓名</th>
											<th width='20%' style="text-align:center">性别</th>
											<th width='40%' style="text-align:center">主要问题描述</th>
										</tr>
									</thead>
									<tbody id="tablebody">
										<logic:iterate id="i" name="wtryInfo">
											<tr>
												<td style="text-align:center">${i.xh}</td>
												<td style="text-align:center">${i.xm}</td>
												<td style="text-align:center">${i.xb}</td>
												<td style="text-align:center">${i.zbwtms}</td>
											</tr>
										</logic:iterate>
									</tbody>
								</table>
							</th>
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
						<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=zbshx&id=shyj" />
						<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
					</td>
				</tr>
			</tbody>	
				</table>
			</div>
			<div style="height:30px;"></div>
			<div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="保存"  onclick="saveSh();return false;">
										保 存
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

