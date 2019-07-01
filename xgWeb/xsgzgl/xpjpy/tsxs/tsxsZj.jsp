<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/tsxs/js/tsxsZj.js"></script>
	</head>
	<body>
		<html:form action="/xpj_tsxs" method="post" styleId="form1">
		<input type="hidden" id="xn" value="${currXn}"/>
		<input type="hidden" id="xq" value="${currXq}"/>
<%--		<html:hidden styleId="xn" value="${currXn}"/>--%>
<%--		<html:hidden styleId="xq" value="${currXq}"/>--%>
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学号生成</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="20%">
								特殊人员周期
							</th>
							<td width="30%">
								${currXnXqmc}
							</td>
							<th width="20%">
								<span class="red">*</span>特殊人员类型
							</th>
							<td width="30%">
								<select id="lxdm" name="lxdm" style="width:155px"></select>
							</td>
						</tr>
						<tr>
							<th >
								特殊类型说明
							</th>
							<td  id="lxsmTd">								
							</td>
							<th >
								类型属性
							</th>
							<td  id="lxsxTd">								
							</td>
						</tr>
						<tr>
							<th>
								是否手动输入
							</th>
							<td >
								<input type="radio"  name="zjfs"  value="1" onclick="changeZjfs();">是</input>
								<input type="radio"  name="zjfs"  value="0" onclick="changeZjfs();" checked="checked">否</input>
							</td>
							<th>
								批量增加学生
							</th>
							<td>
								<button id="plzjbtn" class="btn_01" type="button"  
										onclick="plzjxs()">选择</button>
							</td>
						</tr>
						<tr id="sdsrtr">
							<th>
								<span class="red">*</span>特殊人员学号
							</th>
							<td colspan="3">
								<textarea name="tsxsxh" id="tsxsxh" style="width:95%;height:400px" rows="25" >
								</textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height:35px"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot id="sdsrbtn">
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" onclick="saveForm('${mklx}');">
										保 存
									</button>
									<button type="button" type="button" onclick="iFClose();" id="closeButton">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		   </div>
		</html:form>
		<script type="text/javascript">
			function plzjxs(){
				var lxdm = jQuery("#lxdm").val();
				var xn = jQuery("#xn").val();
				var xq = jQuery("#xq").val();
				if(lxdm == '' || lxdm == null){
					showAlertDivLayer("请先选择特殊人员类型!");
				}else{
					//showDialog('特殊学生选择',800,500,'xpj_tsxs.do?method=tsxsXx&xn='+xn+'&xq='+xq+'&lxdm='+lxdm);
					//jQuery("#closeButton").click();
					location='xpj_tsxs.do?method=tsxsXx&xn='+xn+'&xq='+xq+'&lxdm='+lxdm;
				}		
			}
		</script>
	</body>
</html>

