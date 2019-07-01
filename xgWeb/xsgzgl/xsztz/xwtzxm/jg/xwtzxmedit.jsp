<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/xwtzxm/jg/js/xwtzxm.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
	
			jQuery(function(){
			});
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/xwtzgl_xmjg" method="post" styleId="XwTzXmJgForm">
			<input name="sqid" value="${hdmap.sqid}" type="hidden" />
			<input name="xh" value="${xmjbxx.xh}" type="hidden" />
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
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
								<span>项目信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>项目名称</th>
							<td>
								<html:text property="xmmc" styleId="xmmc" ></html:text>
							</td>
							<th><font color="red">*</font>项目级别</th>
							<td >
								<html:select   property="xmjbdm" styleId="xmjbdm" style="width:154px" >
									<html:options collection="xmjb" property="xmjbdm" labelProperty="xmjbmc" />
								</html:select>
                            </td>
						</tr>
						<tr>
							<th><font color="red">*</font>学年</th>
							<td>
								<html:select property="xn" styleId="xn" style="width:154px">
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
							</td>
							<th><font color="red">*</font>学期</th>
							<td>
								<html:select   property="xq" styleId="xq" style="width:154px">
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>所属科目</th>
							<td>
								<html:select   property="sskmdm" styleId="sskmdm" style="width:154px" >
									<html:options collection="sskm" property="sskmdm" labelProperty="sskmmc" />
								</html:select>
							</td>
							<th>项目开始时间</th>
							<td>
								<html:text property="xmkssj" styleId="xmkssj" onclick="return showCalendar('xmkssj','y-mm-dd');"  maxlength="10" onblur="dateFormatChg(this);"></html:text>
							</td>
						</tr>
						<tr>
							<th>参加地点</th>
							<td >
								<html:text property="cjdd" styleId="cjdd" ></html:text>
							</td>
							<th>联系方式
							</th>
							<td>
								<html:text property="lxfs" styleId="lxfs" maxlength="11" onkeyup="checkInput(this)" ></html:text>
							</td>
						</tr>
						<tr>
							<th>获得奖项</th>
							<td >
								<html:text property="hdjx" styleId="hdjx" ></html:text>
							</td>
							<th><font color="red">*</font>获得学分</th>
							<td >
								<html:text property="zxf" styleId="zxf"  maxlength="3" onkeyup="checkInputNum(this)"></html:text>
							</td>
						</tr>
						<tr>
							<th>申请理由</br><font color="red">(限500字)</font></th>
							<td colspan="3">
								<html:textarea property="sqly" styleId="sqly" 
								   onkeyup=";" 
								   style="width:99%;" rows="3"></html:textarea>
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
									<button type="button" id="bc" onclick="saveSqjgUpdate('update');">
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