<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zjdxzhcp/zcwh/js/zcwh.js"></script>
		<script type="text/javascript" src="js/json.js"></script>
		<script type="text/javascript" src="js/comm/message.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"综测班级列表",
				pager:"pager",
				url:"xpjpy_zcwh.do?method=getZcwhList&doType=query",
				colList:[
				   {label:'key',name:'id', index: 'id',hidden:true,key:true},
				   {label:'学院代码',name:'xydm', index: 'xydm',hidden:true},
				   {label:'学院',name:'xymc', index: 'xydm',width:'12%'},
				   {label:'参评人',name:'xyrs', index: 'xyrs',width:'6%',formatter:cprLink},
				   {label:'提交状态',name:'tjzt', index: 'tjzt',hidden:true},
				   {label:'提交状态',name:'tjztmc', index: 'tjztmc',width:'8%'},
				   {label:'项目大类代码',name:'xmdm', index: 'xmdm',width:'8%',hidden:true},
				   {label:'项目大类',name:'xmmc', index: 'xmmc',width:'8%'},
				   {label:'数据维护',name:'', index: '',width:'8%',formatter:zcczLink}
				],
				sortname: "tjzt,xymc",
			 	sortorder: "desc"
			};
			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			/*查询*/
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
		</script>
	</head>
	<body>
		<input type="hidden" value="${cssz.xn}" id="xn"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xpjpy_zcwh" >
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 提示信息 end-->
			
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal name="writeAble" value="yes">	
					<div class="buttonbox">
						<ul>
							<logic:equal value="1" name="cssz" property="pjkg">
								<li>
									<a href="javascript:void(0);" class="btn_xg" onclick="zcwhEdit();">评价数据维护</a>
								</li>
								<li>
									<a href="javascript:void(0);" class="btn_up" onclick="zcwhSubmit();">提交</a>
								</li>
								<!-- 只有管理员才可以取消提交综测分 -->
								<logic:equal value="xx" name="userType">
									<li>
										<a href="javascript:void(0);" class="btn_up" onclick="zcwhCancelSubmit();">取消提交</a>
									</li>
								</logic:equal>
							</logic:equal>
								<li>
									<a href="javascript:void(0);" class="btn_ck" onclick="zcwhView();">查看</a>
								</li>
							<!-- 此功能现在屏蔽不用，等需要的时候再重新构思
								在这里，您可以一键同步五颗之星学生相关分数。
								<li>
									<a href="javascript:void(0);" onclick="dataSynchronization();" class="btn_sx">同步综测项目分</a>
								</li>
							-->
						</ul>
					</div>
				</logic:equal>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span><font color="blue">${cssz.xn}&nbsp;</font>综测学院列表 </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
