<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zjdxwdpj/xmsq/js/xmsq.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
		<script type="text/javascript">
            var gridSetting = {
                caption:"项目申请列表",
                pager:"pager",
                url:"xpjpy_xmsq.do?method=getXmsqListData",
                colList:[
                    {label:'key',name:'id',index:'id',key:true,hidden:true },
                    {label:'xmdm',name:'xmdm', index: 'xmdm',hidden:true},
                    {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
                    {label:'splc',name:'splc', index: 'splc',hidden:true},
                    {label:'学号',name:'xh', index: 'xh',width:'6%',formatter:xhLink},
                    {label:'姓名',name:'xm', index: 'xm',width:'8%'},
                    {label:'学院',name:'xymc', index: 'xydm',width:'11%'},
                    {label:'班级',name:'bjmc', index: 'bjdm',width:'11%'},
                    {label:'申请时间',name:'sqsj', index: 'sqsj',width:'9%'},
                    {label:'申请奖项',name:'xmmc', index: 'xmmc',width:'9%'},
                    {label:'审核状态',name:'shztmc', index: 'shzt',width:'4%'}
                ],
                sortname : "crsj",
                sortorder : "desc" }

				jQuery(function(){
					var map = getSuperSearch();
					gridSetting["params"] = map;
					jQuery("#dataTable").initGrid(gridSetting);
				})
		</script>
	</head>
	<body>
		<input type="hidden" name="isopen" id="isopen" value="${cssz.kgzt}"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title}</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
			</p>
		</div>
		<!-- 提示信息 -->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
					1、当奖项未开放申请，只能提交【已退回】的记录！<br/>
				</span>
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<html:form action="/xpjpy_xmsq">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="javascript:void(0);" class="btn_zj" onclick="xmsqAdd();return false;">奖项申请</a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn_xg" onclick="xmsqUpdate();return false;">修改</a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn_sc" onclick="xmsqDelete();return false;">删除</a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn_shuc" onclick="xmsqSubmit();return false;">提交</a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn_xg" onclick="xmsqRevoke();return false;">撤销</a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn_cs" onclick="xmsqTrack();return false;">流程跟踪</a>
							</li>
							<logic:notEqual name="userType" value="stu">
								<li>
									<a href="javascript:void(0);" class="btn_dr" onclick="importPjjg();return false;" >导入</a>
								</li>
							</logic:notEqual>
						</logic:equal>
						
						<li>
							<a href="javascript:void(0);" class="btn_dc" onclick="xmsqExport();return false;" >导出</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="xmsqDownload();return false;" class="btn_down">下载登记表</a>
						</li>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>奖项申请列表&nbsp;&nbsp;</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>