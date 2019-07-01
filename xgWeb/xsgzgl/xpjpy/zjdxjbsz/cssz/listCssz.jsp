<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zjdxjbsz/cssz/js/cssz.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var gridSetting = {
					pager:"pager",
					url:"xpjpy_cssz.do?method=getCsszList&type=query",
					colList:[
						{label: 'key', name : 'xmdm', index : 'xmdm',key : true, hidden : true },
						{label: 'xn', name : 'xn', index : 'xn',hidden:true},
						{label: 'fjdm', name : 'fjdm', index : 'fjdm',hidden:true},
						{label: '综测项目', name : 'xmmc', index : 'xmmc', width : '6%',formatter:xmmcFormatter},
						{label: '项目类型', name : 'xmlx', index : 'xmlx', width : '6%'},
						{label: '选项或分值', name : 'result', index : 'result', width : '6%'}
					  	]}
					jQuery("#dataTable").initGrid(gridSetting);
				});
			
			function xmmcFormatter(cellValue, rowObject){
				var fjdm = rowObject["fjdm"];
				if(fjdm == "top"){
			   		return "<font><b>"+cellValue+"</b></font>";
				}else{
					return "<font>&nbsp;&nbsp;&nbsp;&nbsp;"+cellValue+"</font>";
				}
			}
		</script>
	</head>
	<body>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xpjpy_cssz">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="zcxmCount" id="zcxmCount" value="${zcxmCount}"/>
			<div class='tab'>
				<table width="100%" border="0" class="formlist">
					<tbody> 
					    <tr>
							<th width="16%">
								评奖开关
							</th>
							<td width="20%">
								<logic:iterate id="k" name="pjkgList">
									<lable><html:radio property="pjkg" value="${k.dm}" onclick="displayQzsj();"></html:radio>${k.mc}</lable>
								</logic:iterate>
							</td>
							<th width="16%">
					    		评奖周期
					    	</th>
					    	<td colspan="3">
								<html:select property="xn" styleId="xn">
									<html:option value="">---请选择---</html:option>
									<html:options collection="pjzqList" property="pjzq" labelProperty="pjzq"/>
								</html:select>
							</td>
					    </tr>
					    <tr id="qzsjTr">
					    	<th width="16%">
								起止时间
							</th>
							<td colspan="3">
								<html:text  property="kssj" styleId="kssj"
											onfocus="showCalendar('kssj','yyyy-MM-dd HH:mm',true,'jssj');" 
											onchange="saveCssz('kssj',this.value)"
											readonly="true" style="width:120px;" >
								</html:text>
								至
								<html:text  property="jssj" styleId="jssj"
											onfocus="showCalendar('jssj','yyyy-MM-dd HH:mm',false,'kssj');" 
									 		onchange="saveCssz('jssj',this.value)"
									 		readonly="true" style="width:120px;">
								</html:text>
							</td>
					    </tr>
					</tbody>
				</table>
			</div>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
						<ul>
							<li>
								<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;" >增加&nbsp;&nbsp;</a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn_xg" onclick="update();return false;" >修改&nbsp;&nbsp;</a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn_sc" onclick="del();return false;" >删除&nbsp;&nbsp;</a>
							</li>
						</ul>
				</div>
			</div>
			<button type="button" class="btn_cx" id="search_go" onclick="searchRs();return false;" style="display:none;">
			</button>
		</html:form>
		<div class="main_box">
			<div class="con_overlfow">
				<table id="dataTable" ></table>
			</div>
		</div>
	</body>
</html>