<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/zwzxkqgl/js/kqsh.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/zwzxkqgl/js/comm.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		function qqxszj(html){
			jQuery("#tbody_qqryxx").append(html);	
			}

		jQuery(function(){
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.sqid}&tt="+new Date().getTime());
			jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${model.splc}&shid=${model.shid}");
		});
		function saveSh(){
			var shzt = jQuery("#shjg").val();
			if (jQuery("#shzt").val() == "" || jQuery("#shyj").val() == ""){
				showAlert("请将必填项填写完整！");
				return false;
			}
			var url = "zwzxKqsh.do?method=kqDgsh&type=save";
			ajaxSubFormWithFun("ZwzxKqshForm",url,function(data){
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
	<body style="width: 100%">
		<html:form action="/zwzxKqsh" method="post" styleId="ZwzxKqshForm" onsubmit="return false;">
		<html:hidden name="model" property="sqid" styleId="sqid"/>
		<html:hidden name="model" property="xh" styleId="xh"/>		
		<html:hidden name="model" property="splc" styleId="splc"/>
		<html:hidden name="model" property="sqsj" styleId="sqsj"/>
		<html:hidden name="model" property="shid" styleId="shid"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span><font id="gnmkmc_prompt_span"></font></span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								学年
							</th>
							<td width="30%">
								${rs.xn}
							</td>
							<th width="20%">
								<span id="ccrq_span"></span>
							</th>
							<td width="30%">
								${rs.ccrq}
							</td>
						</tr>
						<tr>
							<th width="20%">
								学期
							</th>
							<td width="30%">
								${rs.xqmc}
							</td>
							<th width="20%">
								<span id="cclx_span"></span>
							</th>
							<td width="30%">
								${rs.cclxmc}
							</td>
						</tr>
							<tr>
								<th width="20%">
									班级
								</th>
								<td width="30%">
									${rs.bjmc}
								</td>
								<th width="20%">
									应到人数
								</th>
								<td width="30%">
									${rs.ydrs}
								</td>
							</tr>
							<tr>
								<th width="20%">
									实到人数
								</th>
								<td width="30%">
									${rs.sdrs}
								</td>
								<logic:equal name="xxdm" value="12970">
								<th width="20%">
									未到人数
								</th>
								</logic:equal>
								<logic:notEqual name="xxdm" value="12970">
								<th width="20%">
									缺勤人数
								</th>
								</logic:notEqual>
								<td width="30%">
									${rs.qqrs}
								</td>
							</tr>
						<tr>
							<th width="20%" id="jlf_th">
								纪律分
							</th>
							<td width="30%" id="jlf_td">
								${rs.jlf}
							</td>
							<th width="20%">
								<span id="jlr_span"></span>
							</th>
							<td width="30%" id="jlr_td">
								${rs.jlrxm }
							</td>
						</tr>
						<tr>
						    <th>
								备注
							</th>
							<td colspan="3">
								${rs.bz}
							</td>
						</tr>
						<logic:equal value="11647" name="xxdm">
						<tr>
							<th>
								除缺勤外的违纪人数
								<br />
								<div align="center">
									(如:吃饭,睡觉等...)
								</div>
							</th>
							<td align="left">
								${rs.wjrs}
							</td>
							</tr>
							</logic:equal>
					</tbody>
					<thead>
						<tr>
						<logic:equal name="xxdm" value="12970">
							<th colspan="4">
								<span>未到学生信息</span>
							</th>
						</logic:equal>
						<logic:notEqual name="xxdm" value="12970">
							<th colspan="4">
								<span>缺勤学生信息</span>
							</th>
						</logic:notEqual>
						</tr>
					</thead>
				 </table>
				 </div>
				 <div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="datelist" style="margin:2px auto;">
					
					<thead>
						<tr>
							<td width='15%'>学号</td>
							<td width='15%'>姓名</td>
								<logic:notEqual name="xxdm" value="12970">
									<td width='15%'><font color="red">*</font>缺勤类型</td>
									<td width='15%'>旷课节数</td>
									<td width='35%'>缺勤备注</td>
								</logic:notEqual>
							<logic:equal name="xxdm" value="12970">
								<td width='35%'>未到原因</td>
							</logic:equal>
						</tr>
					</thead>
					<tbody id="tbody_qqryxx">
						<logic:iterate id="i" name="qqxsList" indexId="index01">
						<tr>
						<td name="xhArr">${i.xh}</td>
						<td>${i.xm}</td>
							<logic:notEqual value="12970" name="xxdm">
								<td>${i.qqlxmc}</td>
								<td>${i.kkjs}</td>
							</logic:notEqual>
						<td>${i.ylzd1}</td>
						</tr>
						</logic:iterate>
						</tbody>
					</table>
					</div>
				<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
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
					<tr>
						<th >
							审核结果
						</th>
						<td id="shjgSpan">
							
						</td>
					</tr>
			</tr>
			<tr>
				<th width="20%">
					<font color="red">*&nbsp;</font> 审核意见
					<br />
					<font color="red">(限200字)</font>
				</th>
				<td colspan="3">
					<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=zwzxkq&id=shyj" />
					<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
				</td>
			</tr>
			</tbody>
			</table>
			</div>
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

