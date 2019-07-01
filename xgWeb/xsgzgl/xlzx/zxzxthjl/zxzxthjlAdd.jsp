<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src='js/calendar/calendar-setup.js'></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src='xsgzgl/xlzx/zxzxthjl/js/zxzxthjlList.js'></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#ybwtlb1").hide();
				jQuery("#zajb1").hide();
				jQuery("#zajbsmzx1").hide();
				jQuery("#cbpgjg1").hide();
				jQuery("#sfzj1").hide();			
			});
		</script>
	</head>
	<body>
		<html:form action="/xlzx_thjl_zxzxthjlgl.do" styleId="zxzxthjlForm" method="post" >
		<input type="hidden" name="ytr" id="ytr" value="${jsInfo.zgh}" />
		<%@ include file="/comm/hiddenValue.jsp"%>
			<div style='tab;width:100%;height:480px;overflow-x:hidden;overflow-y:auto;'>
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
							<th style="width:15%"><font color="red">*</font>学号</th>
							<td style="width:35%"><html:text property="xh" readonly="true" styleId="xh" style="width:60%" />
							<button class="btn_01" type="button"  
										onclick="showDialog('请选择一个学生',780,500,'xsxx_xsgl.do?method=showStudents&goto=${path}');return false;">选择</button>
							</td>
							<th style="width:15%">姓名</th>
							<td style="width:35%">
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="xm"/>
								</logic:present>
							</td>
					</tr>
						<tr>
							<th>性别</th>
							<td>
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="xb"/>
								</logic:present>
							</td>
							<th>班级</th>
							<td>
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="bjmc"/>
								</logic:present>
							</td>
						</tr>
					<tr>
						<th>年级</th>
						<td>
							<logic:present name="jbxx">
								<bean:write name="jbxx" property="nj"/>
							</logic:present>
						</td>
						<th>学院</th>
						<td>
							<logic:present name="jbxx">
								<bean:write name="jbxx" property="xymc"/>
							</logic:present>
						</td>
					</tr>
					<tr>
						<th>专业</th>
						<td>
							<logic:present name="jbxx">
								<bean:write name="jbxx" property="zymc"/>
							</logic:present>
						</td>
						<th>联系电话</th>
						<td>
							<logic:present name="jbxx">
								<bean:write name="jbxx" property="sjhm"/>
							</logic:present>
						</td>
					</tr>
					</tbody>
				<thead>
						<tr >
							<th colspan="4">
								<span>谈话教师信息</span>
							</th>
						</tr>
				</thead>
				<tbody id="jsInfo">
					<tr style="height:10px">
						<th  width="16%">谈话老师</th>
						<td  width="34%">${jsInfo.xm}</td>
						<th  width="16%">职工号</th>
						<td  width="34%">${jsInfo.zgh}</td>
					</tr>
					<tr style="height:10px">
						<th  width="16%">性别</th>
						<td  width="34%">${jsInfo.xb}</td>
						<th  width="16%">部门</th>
						<td  width="34%">${jsInfo.bmmc}</td>
					</tr>
					</tbody>
				<thead>
					<tr> 
						<th colspan="4">
							<span>谈话详情</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="16%">
							<font color="red">*</font>谈话时间
						</th>
						<td width="34%" colspan="3">
								<html:text property="thsj" styleId="thsj"   onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
						</td>
					</tr>
					<tr style="height:100px;">
						<th>
							<font color="red">*</font>基本情况描述
							<br><font color="red">(限200字)</font><br/>
						</th>
						<td colspan="3">
							<html:textarea  property='jbqkms' styleId="jbqkms" onblur="chLengs(this,200);" style="word-break:break-all;width:99%"  rows='4' />
						</td>
					</tr>
					<tr>
						<th width="20%">
							<font color="red">*</font>初步评估
						</th>
						<td colspan="3">
							<html:select  property="cbpgdm" styleId="cbpgdm" style="width:155px" onchange="javascript:change_cbpgdm();return false;">
								<option></option>
								<option value="1">没有问题，无需咨询</option>
    							<option value="2">一般心理问题</option>
    							<option value="3">心理障碍和精神疾病</option>
							</html:select>
						</td>
					</tr>
					<tr id="ybwtlb1">
							<th ><font color="red">*</font>一般心理问题类别</th>
							<td colspan="3" >
							<logic:notEmpty name="ybxlwtlbList">
							<logic:iterate name="ybxlwtlbList" id="s"  indexId="i">
								<label><input type="checkbox" name="ybwtlb" value="${s.mc}" onclick="javascript:click_ybwtlb(this);"/>${s.mc}</label>
							</logic:iterate>
							</logic:notEmpty>
							</td>
				    </tr>
					<tr id="zajb1">
							<th><font color="red">*</font>心理障碍和</br>精神疾病</th>
							<td colspan="3" >
								<input type="checkbox" name="zajb" value="初步评估" onclick="javascript:click_zajbcbpg(this);">初步评估
								<input type="checkbox" name="zajb" value="建议咨询" onclick="javascript:click_zajbjyzx(this);">建议咨询
								<input type="checkbox" name="zajb" value="其他建议" onclick="javascript:click_zajbqtjy(this);">其他建议
							</td>
				    </tr>
					<tr id="cbpgjg1">
						<th width="16%">
							<font color="red">*</font>初步评估结果
							<br><font color="red">(限50字)</font><br/>
						</th>
						<td width="34%" colspan="3">
								<html:textarea property="cbpgjg" styleId="cbpgjg" onblur="chLengs(this,50);" style="word-break:break-all;width:99%" rows='2'/>
						</td>
					</tr>
					<tr id="zajbsmzx1">
						<th>
							<font color="red">*</font>是否预约咨询时间
						</th>
						<td colspan="3">
							<html:select  property="zajbsmzx" styleId="zajbsmzx" style="width:90px;">
								<html:option value="2">---请选择---</html:option>
								<html:option value="1">是</html:option>
								<html:option value="0">否</html:option>
							</html:select>				
						</td>
					</tr>
					<tr id="sfzj1">
						<th>
							<font color="red">*</font>是否转介
						</th>
						<td colspan="3">
							<html:select  property="sfzj" styleId="sfzj" style="width:90px;">
								<html:option value="2">---请选择---</html:option>
								<html:option value="1">是</html:option>
								<html:option value="0">否</html:option>
							</html:select>				
						</td>
					</tr>
					<tr style="height:100px;">
						<th>
							备注<br/>是否需要进一步诊断
							<br><font color="red">(限500字)</font><br/>
						</th>
						<td colspan="3">
							<html:textarea  property='bz' onblur="chLengs(this,500);"  styleId="bz" style="word-break:break-all;width:99%"  rows='4' />
						</td>
					</tr>
				</tbody>		
			</table>
			</div>
			<table width="100%" border="0" class="formlist">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">"<span class="red">*</span>"为必填项</div>
							<div class="btn">
								<button type="button" onclick="save();">
									保 存
								</button>
								<button type="button" onclick="refreshParent2();">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</html:form>
	</body>
</html>

