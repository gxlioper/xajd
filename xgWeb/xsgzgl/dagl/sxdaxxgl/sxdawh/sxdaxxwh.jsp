<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/dagl/sxdaxxgl/js/sxdaxxgl.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "学生列表",
				pager : "pager",
				url : "sxDaxxgl.do?method=sxdaxxwh&type=query",
				colList : [ {
					label : 'key',
					name : 'xsid',
					index : 'xsid',
					key : true,
					hidden : true
				}, {
					label : '学号',
					name : 'xh',
					index : 'xh',
					width : '10%'
				}, {
					label : '姓名',
					name : 'xm',
					index : 'xm',
					width : '6%'
				},{
					label : '生源地',
					name : 'syd',
					index : 'syd',
					width : '6%'
				},{
					label : '快递单号',
					name : 'kddh',
					index : 'kddh',
					width : '15%',
					formatter : dhLink
				},{
					label : '邮寄地址',
					name : 'yjdz',
					index : 'yjdz',
					width : '15%',
					formatter : dzLink
				}, {
					label : '邮寄人',
					name : 'yjr',
					index : 'yjr',
					width : '6%',
					formatter : yjrLink
				}, {
					label : '时间',
					name : 'sj',
					index : 'sj',
					width : '10%',
					formatter : sjLink
				}, {
					label : '备注',
					name : 'bz',
					index : 'bz',
					width : '10%',
					formatter : bzLink
				},{
					label : '班级',
					name : 'bjdm',
					index : 'bjdm',
					hidden : true
				}],
				sortname: "ywh,xh",
		 		sortorder: "asc",
				multiselect:false//禁掉勾选
			}
			var map = getSuperSearch();
			map['bjdms']=jQuery("#bjdms").val();
			map['xh']=jQuery('#xh').val();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
			
		})
		</script>
	</head>

	<body>
		<input type="hidden" name="bjdms" id="bjdms" value="${bjdms}"/>
		<input type="hidden" name="bjid" id="bjid" value="${bjid}"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/sxDaxxgl" onsubmit="return false;" >
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
                             <a href="javascript:void(0);" onclick="document.location.href='sxdaxxgl.do';" class="btn_fh">返回</a>	</li>
					</ul>
				
					
				
				</div>
				<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="12%">学号 / 姓名</th>
						<td>
							<input type="text" id="xh" name="xh" maxleng="20" onkeypress="if(event.keyCode==13){doQuery();}"/>
						</td>
						<th width="12%">维护状态</th>
						<td>
								<select id="ywh" name="ywh"  style="width:100px">
								<option value=''></option>
								<option value='0'>已维护</option>
								<option value='1'>未维护</option>
								<select>
							</td>
						<td>
							<div class="btn">
								<button type="button"  class="btn_cx" id="search_go" onclick="doQuery();">
									查 询
								</button>
							</div>
						</td>
					</tr>					
				</table>
			</div>
				<!-- 过滤条件 -->	
				<div style="display:none">
					<%@ include file="/comm/search/superSearchArea.jsp"%>
					<html:select property="pj" styleId="pjhtml" >
						<html:option value=""></html:option>
<%--						<html:options collection="pylist" property="cxdjdm" labelProperty="cxdjmc"/>--%>
					</html:select>
				</div>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>
					<font color = "blue">${xymc}&nbsp;&nbsp;${zymc}&nbsp;&nbsp;${nj}&nbsp;&nbsp;${bjmc}</font> 
						学生列表&nbsp;&nbsp;已维护：<font color = "blue" id="ywhs">${ywh}</font>人&nbsp;&nbsp;
						未维护：<font color = "blue" id="wwhs">${wwh}</font>人
				</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
