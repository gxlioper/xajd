<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" defer="defer">
		function save(){
			
			var jssfty = jQuery("input:radio[name='jssfty']:checked").val();
			var jshfxx = jQuery("#jshfxx").val();
			if(jssfty == null || jssfty == '' || jshfxx == '' || jshfxx == null){
				return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
			}
			var sqid = jQuery("#sqid").val();
			var url = "dekt_jspjglyyxx.do?method=szdwjssfty_10698";	
			jQuery.post(url,{"jssfty":jssfty, "jshfxx":jshfxx, "sqid":sqid},function(result){
			 	showAlert(result,{},{"clkFun":function(){
    				if (parent.window){
    					refershParent();
    				}
    			}});
			});					
		}
		</script>
	</head>
	<body>
		<html:form method="post" styleId="jspjyyxxForm" action="/dekt_jspjglyyxx"
			enctype="multipart/form-data">
			<html:hidden property="sqid"  styleId="sqid"/>
			<input type="hidden" value="${jshf.jssfty}" id="jssfty"/>
			
			<input type="hidden" value="${jshf.sqid}" id="sqid"/>
			<div style='width: 100%; overflow-x: hidden; overflow-y: auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right" width="15%">
								学号
							</th>
							<td align="left" width="20%">
								${rs.xh}
							</td>
							<th align="right" width="20%">
								姓名
							</th>
							<td align="left" width="30%">
								${rs.xm}
							</td>
						</tr>
						<tr>
							<th align="right">
								性别
							</th>
							<td align="left">
							 ${rs.xb}
							</td>
							<th align="right">
								身份证号
							</th>
							<td align="left">
							 ${rs.sfzh}
							</td>
						</tr>
						<tr>
							<th align="right">
								年级
							</th>
							<td align="left">
							 ${rs.nj}
							</td>
							<th align="right">
								学院
							</th>
							<td align="left">
							 ${rs.xymc}
							</td>
						</tr>
						<tr>
							<th align="right">
								专业
							</th>
							<td align="left">
							 ${rs.zymc}
							</td>
							<th align="right">
								班级
							</th>
							<td align="left">
							 ${rs.bjmc}
							</td>
						</tr>
						<tr>
							<th align="right">
								政治面貌
							</th>
							<td align="left">
							 ${rs.zzmmmc}
							</td>
							<th align="right">
								联系电话
							</th>
							<td align="left">
							 ${rs.sjhm}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>预约信息</span>
							</th>
						</tr>
					</thead>	
					<tbody>
						<tr>
							<th align="right" width="10%">
								预约信息&nbsp;
							</th>  
							<td colspan="3">
								 ${jshf.xsyyxx}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>回复信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right" >
								<font color="red">*</font>回复结果&nbsp;
							</th>  
							<td colspan="3">
								<html:radio property="jssfty" value="1">同意</html:radio>
								<html:radio property="jssfty" value="0">不同意</html:radio>
							</td>
						</tr>
						<tr>
							<th align="right" >
								<font color="red">*</font>回复内容
								<br />
								<font color="red"><B>(限200字)</B>
								</font>
							</th>
							<td align="left" colspan="3">
								<html:textarea property='jshfxx' styleId="jshfxx" style="width:500px" rows='5' value="${jshf.jshfxx}"
									onblur="checkLen(this,200)" />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height:50px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									</button>
										<button type="button" onclick="save();" id="buttonClose">
											提交
										</button>
									</button>
										<button type="button" onclick="iFClose();" id="buttonClose">
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
