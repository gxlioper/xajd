<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/jtff/jtff/js/jtff.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#ffny").change(function(){
					getData();
				});
				if("${exist}" == "1"){
					showAlert("该月发放记录已生成，请注意!",{},{"clkFun":function(){
						jQuery("#jtsc").text("重新生成");
						return false;
					}});
				}
				if("${zrs}" == '0'){
					showAlert("无津贴名单维护信息!",{},{"clkFun":function(){
			    		jQuery("#jtsc").hide();
						return false;
					}});
				}
			});
		</script>
		<style type = "text/css">
		   #datatable table tr th{text-align:center}
		</style>
	</head>
	<body>
		<html:form action="/jtff_jtff" method="post" styleId="JtffForm" onsubmit="return false;">
			<input type="hidden" name = "jtlb" value = "gd"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="9">
								<span>津贴发放</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th  width="20%"><font color="red">*</font>发放年月</th>
							<td colspan="8" width="80%">
								<html:select property="ffny" styleId="ffny" style="width:200px" >
									<html:options property="ny" labelProperty="ny" collection="nylist"/>
								</html:select>
							</td>
						</tr>
				    </tbody>
					<thead>
						<tr>
							<th colspan="9">
								<span>津贴明细</span><label style="margin-left:20px;color:blue;line-height:22px">合计：教师人数<font id="zrs">${zrs}</font>人，津贴总额<font id="zje">${zje}</font>元</label>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr style="width:100%">
							<td id="datatable" colspan="9" width="100%">
								<table width="100%">
								<tbody width="100%" id="thnr">
								<tr>
									<th>序号</th>
									<th>职工号</th>
									<th>姓名</th>
									<th>性别</th>
									<th>工作部门</th>
									<th>岗位</th>
									<th>所带学生数</th>
									<th>发放月份</th>
									<th><font color="red">*</font>金额(元)</th>
							    </tr>
								<logic:iterate id="i" name="datalist">
									<tr>
									  <td>${i.rownum}</td>
									  <td>
									   ${i.zgh}
									   <input type="hidden" name="zgh" value="${i.zgh}"/>
									  </td>
									  <td>${i.xm}</td>
									  <td>${i.xb}</td>
									  <td>${i.bmmc}</td>
									  <td>${i.gw}</td>
									  <td>
									  		${i.dbrs}
									  		<input type="hidden" name="dbrs" value="${i.dbrs}"/>
									  </td>
									  <td>${i.ffny}</td>
									  <td>
									  	<logic:equal value="zc" property="jtlb" name="i">
									  		<input type="text" style="width:80px;" name="ffje" value="${i.xsje}" onkeyup="checkMoneyBykeyUp(this);" maxlength="10"/>
									  	</logic:equal>
									  	<logic:equal  value="gd" property="jtlb" name="i">
									  	<input type="hidden" style="width:80px;" name="ffje" value="${i.xsje}" />
									  		${i.xsje}
									  	</logic:equal>
									  	<input type="hidden" name="gw" value="${i.gw}"/>
									  	<input type="hidden" name="jsje" value="${i.jsje}"/>
									  </td>
									</tr>
								</logic:iterate>
								</tbody>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
				</div>	
				<div style="height:30px;"></div>
				<%--;height:520px --%>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" id="jtsc" onclick="savejtsc();">
										生成津贴
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