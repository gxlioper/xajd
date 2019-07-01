<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<%@ include file="/syscommon/autocomplete.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xthd/xmjg/js/xmjg.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript">
		jQuery(function(){	

<%--			 项目名称关联和学年学期有关，暂时关闭  																						--%>
<%--			//取得数据表：xg_pjpy_new_pjxmb; 字段：xmmc																							--%>
<%--			var autoSetting = {--%>
<%--				dataTable:"xg_rcsw_txhd_xmwh",--%>
<%--				dataField:"xmmc",--%>
<%--				scrollHeight:135										--%>
<%--			}--%>
<%--			// 模糊搜索下拉【项目名称】--%>
<%--			jQuery("#xmmc").setAutocomplete(autoSetting);--%>
			
		});
		</script>
	</head>
	<body>
		<html:form method="post" styleId="form" action="/rcsw_txhd_xmjg.do" enctype="multipart/form-data">

		<% String xxdm = (String) request.getAttribute("xxdm"); %>
		<div style='tab;width:100%;height:455px;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/xthd/comm/selectStudent.jsp" %>
				<thead>
						<tr>
							<th colspan="4">
								<span>活动信息</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="10%">
							<span class="red">*</span>学年
						</th>
						<td align="left">
							<html:select property="xn" styleId="xn" disabled="false" 
									style="width:125px;">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
							</html:select>
						</td>
						<th align="right">
							<span class="red">*</span>学期
						</th>
						<td align="left">
							<html:select property="xq" styleId="xq" disabled="false" 
									style="width:125px;">
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>活动名称</th>
						<td >
							<html:text  property="xmmc" styleId="xmmc"  style="width:180px;" maxlength="20" styleClass="text_nor"></html:text>
						</td>
						<th width="16%">
							<font color="red">*</font>活动时间
						</th>
						<td width="34%" >
							<html:text property="hdkssj" styleId="hdkssj"  style="width: 80px;"  onclick="return showCalendar('hdkssj','yyyy-MM-dd');" readonly="true" ></html:text>至
							<html:text property="hdjssj" styleId="hdjssj"  style="width: 80px;"  onclick="return showCalendar('hdjssj','yyyy-MM-dd');" readonly="true" ></html:text>
						</td>
					</tr>
					<tr>
						<th align="right">
							<span class="red">*</span>活动类别
						</th>
						<td width="34%">
							<html:select property="lbdm" styleId="lbdm" style="width:155px">
							<html:options collection="hdlbList" property="lbdm"
								labelProperty="lbmc" />
							</html:select>
						</td>
						<th align="right" width="10%">
						<span class="red">*</span>活动地点
						</th>
						<td align="left">
							<html:text  property="hddd" styleId="hddd"  style="width:120px;" styleClass="text_nor"></html:text>
						</td>
					</tr>
					<tr>
					   <th align="right">
							负责人姓名
						</th>
						<td width="34%">
							<html:text  property="fzrxm" styleId="fzrxm"  style="width:120px;" styleClass="text_nor"></html:text>
						</td>
						<th align="right" width="10%">
						           联系电话
						</th>
						<td align="left">
							<html:text  property="lxdh" styleId="lxdh"  onkeyup="value=value.replace(/[^\d]/g,'')"   style="width:120px;" styleClass="text_nor"></html:text>
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
						           承办单位
						</th>
						<td align="left" colspan="3" >
							<html:text  property="cbdw" styleId="cbdw"  style="width:97%" styleClass="text_nor"></html:text>
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">活动主题</th>
						<td align="left" colspan="3" >
							<html:text  property="hdzt" styleId="hdzt"  style="width:97%" styleClass="text_nor"></html:text>
						</td>
					</tr>
					<tr>
					<th align="right" width="10%">
							活动目的及意义
						</th>
						<td colspan="3">
							<html:textarea rows="2" property="hdmdyy" styleId="hdmdyy" style="width:97%" onblur="checkLen(this,200);"></html:textarea>
						</td>
					</tr>
					<tr>
					<th align="right" width="10%">
							活动方案
						</th>
						<td colspan="3">
							<html:textarea rows="4" property="hdfa" styleId="hdfa" style="width:97%" onblur="checkLen(this,500);"></html:textarea>
						</td>
					</tr>
					<% if("13023".equals(xxdm)){ %>
					<tr>
						<th width="16%">
							授予学分
						</th>
						<td width="34%" >
							<html:text property="syxf"  styleId="syxf"  maxlength="5" onkeyup="checkInputNum(this);"></html:text>
						</td>
						<th width="16%">
						</th>
						<td width="34%" >
						</td>
					</tr>
					<% } %>
					<tr>
						<th align="right" width="10%">活动说明</th>
						<td align="left" colspan="3" >
							<html:text  property="hdsm" styleId="hdsm"  style="width:97%" styleClass="text_nor"></html:text>
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							<span class="red">*</span>申请理由&nbsp;
							<br />
							<font color="red">(限200字)</font>	
						</th>
						<td colspan="3">
							<html:textarea rows="4" property="sqly" styleId="sqly" style="width:97%" onblur="checkLen(this,200);"></html:textarea>
						</td>
					</tr>
				</tbody>
				<logic:equal value="12309" name="xxdm">
				<thead>
						<tr>
							<th colspan="4">
								<span>物资申请</span>
							</th>
						</tr>
				</thead>
				<tbody>
				<tr>
				<th align="right" width="10%">申请物资</th>
						<td align="left" colspan="3" >
							<html:text  property="sqwz" styleId="sqwz"  style="width:97%" styleClass="text_nor"></html:text>
						</td>
				</tr>
					<tr>
					   <th align="right">
							使用时间
						</th>
						<td width="34%">
							<html:text property="wzsysj" styleId="wzsysj"  onclick="return showCalendar('wzsysj','yyyy-MM-dd HH:mm');" readonly="true" ></html:text>
						</td>
						<th align="right" width="10%">
						          归还时间
						</th>
						<td align="left">
							<html:text  property="wzghsj" styleId="wzghsj"  onclick="return showCalendar('wzghsj','yyyy-MM-dd HH:mm');" readonly="true" ></html:text>
						</td>
					</tr>
				</tbody>
				<thead>
						<tr>
							<th colspan="4">
								<span>宣传申请</span>
							</th>
						</tr>
				</thead>
				<tbody>
				<tr>
				<th align="right" width="10%">宣传方式</th>
						<td align="left">
							<html:text  property="xcfs" styleId="xcfs" style="width:180px" styleClass="text_nor"></html:text>
						</td>
				<th align="right" width="10%">张贴（悬挂地点）</th>
						<td align="left" colspan="3" >
							<html:text  property="xgdd" styleId="xgdd" style="width:180px" styleClass="text_nor"></html:text>
						</td>
				</tr>
					<tr>
					<th align="right" width="10%">起止时间</th>
					<td>
					   <html:text property="xckssj" styleId="xckssj"  style="width: 100px;"  onclick="return showCalendar('xckssj','yyyy-MM-dd HH');" readonly="true" ></html:text>至
							<html:text property="xcjssj" styleId="xcjssj"  style="width: 100px;"  onclick="return showCalendar('xcjssj','yyyy-MM-dd HH');" readonly="true" ></html:text>
					</td>
					<th align="right" width="10%">海报（横幅）数量</th>
					<td>
					   <html:text property="hbsl" maxlength="3" onkeyup="value=value.replace(/[^\d]/g,'')"  styleId="hbsl" styleClass="text_nor"></html:text>
						
					</td>
					</tr>
					<tr>
					<th align="right" width="10%">宣传内容</th>
					<td colspan="3">
					   <html:text property="xcnr" styleId="xcnr" style="width:97%" styleClass="text_nor"></html:text>
					</td>
					
					</tr>
					
				</tbody>
				</logic:equal>
			</table>
		</div>
		
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button"  onclick="save('rcsw_txhd_xmjg.do?method=add&type=save','xh-hdkssj-hdjssj-hddd-xmmc-sqly','false');return false;" id="buttonSave">
									保 存
								</button>
								<button type="button"  onclick="iFClose();" id="buttonClose">
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
