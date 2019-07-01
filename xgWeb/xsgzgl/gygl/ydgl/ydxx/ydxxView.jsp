<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
	
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
	<script type="text/javascript" src="xsgzgl/gygl/ydgl/js/ydxx.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
</head>
<body >
	<html:form action="/ydXxgl" styleId="ydxxForm"  method="post">
		<input type="hidden" id="ydxxid" value="${ydxxid }"/>
	<div class="tab" style="overflow-x:hidden;overflow-y:auto;height:410px;margin-bottom: 0px;">
		<table class="formlist" width="95%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>电费使用情况</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="16%">
					楼栋名称
				</th>
				<td align="left" width="30%" nowrap="nowrap">
					${rs.ldmc}
				</td>
				<th width="16%">层号</th>
				<td width="38%">
					${rs.ch}
				</td>
			</tr>

			<tr>
				<th width="16%">
					寝室号				
				</th>
				<td width="34%">
				${qsh}
				</td>
				<th width="16%">
					寝室人数			
				</th>
				<td width="34%">${rs.cws}</td>
			</tr>
			<tr>
				<th width="16%">
					用电年月				
				</th>
				<td width="34%">${ydyf}
				</td>
				<th width="16%">
					用电度数	
				</th>
				<td width="34%">${ds}
					</td>
			</tr>
			<tr>
				<th width="16%">
					电费				
				</th>
					<td width="34%">${df}
					</td>
				<th width="16%">
					电费余额		
				</th>
				<td width="34%">
				${dfye}
					</td>
			</tr>
				<tr>
				<th width="16%" rowspan="4">
					备注
				</th>
				<td width="34%" colspan="3" rowspan="4">
				${bz }
				</td>
			</tr>
			</tbody>
			</table>
			<h3 class="datetitle_01">
				<span>床位信息</span>
			</h3>
			<table class="formlist">
					<thead>
						<tr>
							<th>
								<div align="center" >学号</div>
							</th>
							<th>
								<div align="center" >姓名</div>
							</th>
							<th>
								<div align="center" >班级</div>
							</th>
							<th>
								<div align="center" >入住床位</div>
							</th>
						</tr>
					</thead>
					<tbody>
						<logic:iterate id="s" name="list">
							<tr class="alt">
								<logic:iterate id="v" name="s">
									<td align="center">${v }</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</tbody>
					
				</table>
				</div>
			<table class="formlist" width="97%">
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          	<button type="button" name="提交" id="buttonSave"  onclick="saveUpdate()">保存</button>
			            <button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
	
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">
		 showAlert("操作成功",{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
		</script>
	</logic:present>
</body>
</html>
