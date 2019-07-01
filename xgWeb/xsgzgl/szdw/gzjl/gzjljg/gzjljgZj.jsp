<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/gzjl/gzjljg/gzjljg.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/gzjl/comm/comm.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		function thxszj(html){
			jQuery("#tbody_dhryxx").append(html);	
			}
		</script>
	</head>
	<body>
		<html:form action="/gzjljg" method="post" styleId="GzjljgForm">
		<logic:equal value="11842" name="xxdm">
			<input type="hidden" id="objStr" name="objStr"/>
			<html:hidden property="zgh" styleId="zgh"/>
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}"/>
		</logic:equal>		
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<logic:equal value="11842" name="xxdm">
						<%@ include file="/xsgzgl/szdw/gzjl/comm/viewTeacher.jsp" %>
					</logic:equal>
					<logic:notEqual value="11842" name="xxdm">
						<%@ include file="/xsgzgl/szdw/gzjl/comm/selectTeacher.jsp" %>
					</logic:notEqual>					
					<thead>
						<tr>
							<th colspan="4">
								<span>工作记录</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>工作时间</th>
							<td>
							<html:text property="gzsj" styleId="gzsj" onfocus="showCalendar('gzsj','y-mm-dd');" />
							</td>
							<th>记录时间</th>
							<td>
							<input type="hidden" value="${jlsj}" name="jlsj" id="jlsj"/>
								${jlsj}
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>工作类别</th>
							<td>
								<html:select  property="lbdm" styleId="lbdm" style="width:200px" onchange="getLbdm(this)">
								    <html:option value="">--请选择--</html:option>
									<html:options collection="gzlbList" property="gzlbdm" labelProperty="gzlbmc" />
								</html:select>
							</td>
							<th>类别编号</th>
							<td>
								<html:text property="lbbh" styleId="lbbh" maxlength="50"  readonly="true"></html:text>
								
							</td>
						</tr>
						<logic:equal value="11842" name="xxdm">
							<tr>
								<th><font color="red">*</font>六困生</th>
								<td>
									<html:select  property="lks" styleId="lks" style="width:200px">				    
										<html:options collection="lksList" property="lksdm" labelProperty="lksmc" />
									</html:select>
								</td>
							</tr>
						</logic:equal>
						<tr>
						
							<th><span class="red">*</span>工作摘要
								</br><font color="red">(限1000字)</font></th>
							<td colspan="3">
								<html:textarea property="gzzy" styleId="gzzy" 
											   onblur="checkLen(this,1000);"  
											   style="width:99%;" rows="6"></html:textarea>
							</td>
						</tr>
						<tr>
						
							<th>备注</br><font color="red">(限500字)</font></th>
							<td colspan="3">
								<html:textarea property="bz" styleId="bz" 
											   onblur="checkLen(this,500);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
						</tr>
					</tbody>
					<logic:equal value="11842" name="xxdm">
						<thead>
							<tr>
								<th colspan="4">
									<span>谈话对象</span>
								</th>
							</tr>
						</thead>
					</logic:equal>
				</table>
			</div>
			<logic:equal value="11842" name="xxdm">
			<div style="height:200px;overflow-y:auto;">
				<table width="100%" border="0" class="datelist" style="margin:2px auto;">
					
					<thead>
						<tr>
							<td colspan="7">
							<button type="button" id="btn_getXsxx" onclick="bcZjRyxx();" style="display: none;"></button>
							<button type="button" onclick="addThxs();return false;" class="btn_01">增加学生</button>
							<button type="button" onclick="delThxs();return false;" class="btn_01">删除学生</button>
							</td>
						</tr>
						<tr>
							<td width='5%'><input type="checkbox" name="th" onclick="selectAll(this);" /></td>
							<td width='15%'>学号</td>
							<td width='10%'>姓名</td>
							<td width='10%'>性别</td>
							<td width='20%'>学院</td>
							<td width='20%'>专业</td>
							<td width='20%'>班级</td>
						</tr>
					</thead>
					<tbody id="tbody_dhryxx">
						
					</tbody>
				</table>
				</div>
				<div style="height:30px"></div>
			</logic:equal>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="savegzjljg('save');">
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