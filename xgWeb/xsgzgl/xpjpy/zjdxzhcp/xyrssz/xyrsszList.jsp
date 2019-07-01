<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zjdxzhcp/xyrssz/xyrssz.js"></script>
		<script type="text/javascript">
		var gridSetting = {
			caption : "项目列表",
			pager : "pager",
			url : "xpjpy_xyrssz.do?method=getXyrsszList&type=query",
			colList : [
					    {label : '项目代码',name : 'xmdm',index : 'xmdm',key : true,hidden : true}, 
					    {label : '申请开始时间',name : 'sqkssj',index : 'sqkssj',hidden : true},
					    {label : '申请结束时间',name : 'sqjssj',index : 'sqjssj',hidden : true},
					    {label : '审核开始时间',name : 'shkssj',index : 'shkssj',hidden : true},
					    {label : '审核结束时间',name : 'shjssj',index : 'shjssj',hidden : true},
					    {label : '人数控制范围',name : 'rsfpfs',index : 'rsfpfs',hidden : true},
						{label : '项目名称',name : 'xmmc',index : 'xmmc',width : '20%'}, 
						{label : '项目类型',name : 'lxmc',index : 'lxmc',width : '12%'},
						{label : '项目性质',name : 'xzmc',index : 'xzmc',width : '12%'},
						{label : '金额',name : 'xmje',index : 'to_number(xmje)',width : '8%'},
						{label : '人数设置',name : 'rssz',index : 'rssz',width : '8%',formatter:setRsszXy}
						],
			sortname : "xssx",
			sortorder : "asc"
		}
		
		jQuery(function() {
			jQuery("#dataTable").initGrid(gridSetting);
		});

		
		</script>
	</head>
	<body>	
		<html:form action="/xpjpy_xyrssz">
		<input type="hidden" name="currDate" id="currDate" value="${currDate}"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;"	onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>
		<!-- 提示信息 end-->
		<div  id="div_help" class="prompt" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
					默认显示当前评奖周期（<font color="red">${pjzq}</font>）的评奖项目数据</br>
					人数设置：此处设置当前项目的获奖<font color="red">人数上限</font>，根据“人数控制范围”设置相应范围内获奖人数上限，在审核中约束获奖人数</br>
				</span>
			</p>
			<a class="close" title="隐藏"
			   onclick="this.parentNode.style.display='none';">
			</a>
		</div>
		<!-- 提示信息 end-->
		<div class="toolbox">
			<!-- 过滤条件 -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="10%">项目名称</th>
						<td>
							<input type="text" id="xmmc" name="xmmc" maxleng="50" style="width: 100px"/>
						</td>
						<th>项目类型</th>
						<td>
							<html:select property="lxdm" styleId="lxdm" style="width:100px">
							<html:option value=""></html:option>
							<html:options collection="xmlxList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
						<th>项目性质</th>
						<td>
							<html:select property="xzdm" styleId="xzdm" style="width:100px">
							<html:option value=""></html:option>
							<html:options collection="xmxzList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
						<th>申请开关</th>
						<td>
							<html:select property="sqkg" styleId="sqkg" style="width:100px">
							<html:option value=""></html:option>
							<html:option value="0">未开启</html:option>
							<html:option value="1">已开启</html:option>
							</html:select>
						</td>
						<td>
							<div class="btn">
								<button type="button" class="btn_cx" id="search_go" onclick="query()">
									查 询
								</button>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 
					<font color="blue">${pjzq}&nbsp;&nbsp;</font>奖学金名额调整
					（学院奖学金总额<font color="red">${jxjjeMap.jxjze}</font>元， 
					已调整金额<font color="red">${jxjjeMap.jxsjze}</font> 元）
			 	</span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
		</html:form>
	</body>
</html>