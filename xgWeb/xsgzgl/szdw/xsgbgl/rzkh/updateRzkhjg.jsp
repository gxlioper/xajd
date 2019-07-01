<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/xsgbgl/js/rzkh.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#grzp").val("${khjg.grzp}");
				jQuery("#zgdwyj").val("${khjg.zgdwyj}");
			})
		</script>
		<style type = "text/css">
			#xxdz{width:250px;}
			.fontstyl{float:left}
			.fontstyl1{margin-left:5px}
		</style>
	</head>
	<body>
		<html:form action="/szdw_xsgb_rzkhjg" method="post" styleId="rzkhjgForm">
				<html:hidden property="khjgid"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:500px;margin-bottom:0px;" >
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
								<span>学生干部职务信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>职务名称</th>
							<td>
								<html:select   property="zwmc" styleId="zwmc"  style="width:150px;">
									<html:options collection="zwmclist" property="zwid" labelProperty="zwmc" />
								</html:select>
							</td>
							<th><font color="red">*</font>任职时间</th>
							<td>
								<html:text property="rzsj" styleId="rzsj" maxlength="10" onclick="return showCalendar('rzsj','y-mm-dd');" onblur="dateFormatChg(this);"></html:text>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>学年</th>
							<td>
								<html:select   property="xn" styleId="xn"  style="width:150px;">
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
								<%-- 
								<html:text property="xl" styleId="xl" maxlength="10"></html:text>--%>
							</td>
							<th><font color="red">*</font>学期</th>
							<td>
								<html:select   property="xq" styleId="xq"  style="width:150px;">
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
						<tr>	
						<tr>
							<th>个人述职
								</br><font color="red">(限50字)</font></th>
							<td colspan="3">
								<html:textarea property="grsz" styleId="grsz" 
								   onkeyup=";"
								   style="width:99%;" rows="2"></html:textarea>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>考核意见</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>个人自评</th>
							<td>
								<html:select property="grzp" styleId="grzp"  style="width:150px;">
									<option value="优秀">优秀</option>
									<option value="称职">称职</option>
									<option value="基本称职">基本称职</option>
									<option value="不称职">不称职</option>
								</html:select>
							</td>
							<th><font color="red">*</font>主管单位评审意见</th>
							<td>
								<html:select property="zgdwyj" styleId="zgdwyj"  style="width:150px;">
									<option value="优秀">优秀</option>
									<option value="称职">称职</option>
									<option value="基本称职">基本称职</option>
									<option value="不称职">不称职</option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>区队意见
								</br><font color="red">(限500字)</font></th>
							<td colspan="3">
								<html:textarea property="qdyj" styleId="qdyj" 
								   onkeyup=";"
								   style="width:99%;" rows="2"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>所在单位意见
								</br><font color="red">(限500字)</font></th>
							<td colspan="3">
								<html:textarea property="szdwyj" styleId="szdwyj" 
								    onkeyup=";"
								   style="width:99%;" rows="2"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>大队意见
								</br><font color="red">(限500字)</font></th>
							<td colspan="3">
								<html:textarea property="ddyj" styleId="ddyj" 
								   onkeyup=";"
								   style="width:99%;" rows="2"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>学生工作处意见
								</br><font color="red">(限500字)</font></th>
							<td colspan="3">
								<html:textarea property="xsgzcyj" styleId="xsgzcyj" 
								   onkeyup=";"
								   style="width:99%;" rows="2"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>备注
								</br><font color="red">(限500字)</font></th>
							<td colspan="3">
								<html:textarea property="bz" styleId="bz" 
								    onkeyup=";"
								   style="width:99%;" rows="2"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
				</div>	
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveKhjg('update');">
										保    存
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