<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/xfjs.js'></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<script>
		function getWjInfo(){
			var pkValue = val('xh')+val('xn')+ val('xq')+val('ccrq')+val('jclxdm')+val('bjdm');	
			xfjs.getWjxx(pkValue,function(data){
				if(data != null && data !=""){
					if(data.cf != null && data.cf != ""){
						document.getElementById('tsTr').style.display='';
						document.getElementById('zkkjs').innerText = data.kkzjs;
						document.getElementById('cfmc').innerText = data.cf;
					}
				}
				document.getElementById('dccckkzjs').innerText = data.kkzjs == null ? "" : data.kkzjs;
			});
		}
	</script>
	</head>
	<body onload="getWjInfo()">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a><bean:write name="title" />
				</a>
			</p>
		</div>

		<html:form action="/pjpyxfjs" method="post">
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>班级学风抽查</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="window.close();return false;" id="buttonClose">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th>
								学号
							</th>
							<td align="left">
								${model.xh}
								<input type="hidden" id="xh" value="${model.xh}" />
							</td>
							<th>
								学年
							</th>
							<td align="left">
								${model.xn}
								<input type="hidden" value="${model.xn}" name="xn" />
							</td>
						</tr>
						<tr>
							<th>
								姓名
							</th>
							<td align="left">
								${model.xm}
							</td>
							<th>
								学期
							</th>
							<td align="left">
								${model.xqmc}
								<input type="hidden" id="xq" value="${model.xq}" />
							</td>

						</tr>
						<tr>
							<th>
								班级
							</th>
							<td align="left">
								${model.bjmc}
								<input type="hidden" id="bjdm" value="${model.bjdm}" />
							</td>
							<th>
								抽查日期
							</th>
							<td align="left">
								${model.ccrq}
								<input type="hidden" id="ccrq" value="${model.ccrq}" />
							</td>
						</tr>

						<tr>
							<th>
								抽查类型
							</th>
							<td align="left">
								${model.jclxmc}
								<input type="hidden" id="jclxdm" value="${model.jclxdm}" />
							</td>
							<th>
								违纪
							</th>
							<td align="left">
								${model.wjlxmc}
							</td>
						</tr>
						<tr>
							<th>
								旷课节数
							</th>
							<td align="left">
								${model.wjcs}
							</td>
							<th>
								请假
							</th>
							<td align="left">
								${model.qjlxmc}
							</td>
						</tr>
						<tr>
							<th>
								当次抽查旷课总次数
							</th>
							<td align="left">
								<div id="dccckkzjs"></div>
							</td>
							<th>
								抽查用户类型
							</th>
							<td align="left">
								<logic:equal value="xx" name="model" property="ccyhlx">
						学校
					</logic:equal>
								<logic:equal value="xy" name="model" property="ccyhlx">
									<bean:message key="lable.xsgzyxpzxy" />
								</logic:equal>
							</td>
						</tr>
						<tr>
							<th>
								备注
							</th>
							<td align="left" colspan="3">
								${model.bz}
							</td>
						</tr>
						<tr id="tsTr" style="display:none">
							<td align="right" colspan="4">
								<font color="red">提示：该学生当次抽查总旷课节数为：<b id="zkkjs"></b>,应该处以<b
									id="cfmc"></b>处分！</font>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>
