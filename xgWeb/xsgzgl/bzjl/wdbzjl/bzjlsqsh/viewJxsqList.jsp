<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/comm/message.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/bzjl/wdbzjl/bzjlsqsh/js/jxsq.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">

			jQuery(function(){
				var gridSetting = {
					caption:"参评学生列表 ",
					pager:"pager",
					url:"bzjl_sqsh.do?method=viewJxsqList&type=query",
					colList:[
					   {label:'key',name:'sqid', index: 'sqid',hidden:true,key:true},
					   {label:'学号',name:'xh', index: 'xh',width:'13%',formatter:function(cell,rowObject){
						   return "<a href=\"javascript:ckSqb('"+rowObject["sqid"]+"');\" class='name'>"+cell+"</a>";
					   }},
					   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
					   {label:'专业班级',name:'bjmc', index: 'bjdm',width:'13%'},
					    <logic:equal name="xxdm" value="10466">
					   		{label:'宿舍号',name:'qsh',index:'qsh',width:'10%'},
					   	</logic:equal>
					   {label:'申请时间',name:'sqsj', index: 'sqsj',width:'15%'},
					   {label:'申请奖项',name:'xmmc', index: 'xmdm',width:'13%'},
					   {label:'项目代码',name:'xmdm', index: 'xmdm',width:'13%',hidden:true},
					   {label:'最终获得奖项',name:'tzhxmmc',index:'tzhxmdm',width:'13%'},
					    <logic:equal name="xxdm" value="10264">
					   		{label:'文体骨干生',name:'sfwtgg',index:'sfwtgg',width:'13%'},
					   	</logic:equal>
					   {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
					   {label:'splc',name:'splc', index: 'splc',hidden:true},
					   {label:'审核状态',name:'shztmc', index: 'shzt',width:'5%'},
					   {label:'isopen',name:'isopen', index: 'isopen',hidden:true}
					],
					sortname:"sqsj",
					sortorder:"desc"
					//radioselect:true
				};

				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);

				var btndr=jQuery("#btn_dr");
				//导入
				if(btndr!=null){
					btndr.click(function () {
						toImportData("IMPORT_N750602_PJJXSQ");
						return false;
					});
				}
			});

			/**
			*	下载登记表
			*/
			function getWord(){
				var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("请选择您要下载的记录！");
				 } else if (rows.length > 1){
					var ids = jQuery("#dataTable").getSeletIds();
					var url="xpj_pjxmsq.do?method=getDjbZip&value="+ids + "&actionFrom=sqsh";
					window.open(url);
				 } else {
					var url="xpj_pjxmsq.do?method=getDjbWord&sqid="+rows[0]["sqid"] + "&actionFrom=sqsh";
					window.open(url);
			     }
			}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<input type="hidden" id="SFBJPY_Y" value="${SFBJPY_Y }"/>
		<html:form action="/bzjl_sqsh" method="post" styleId="zcxmForm">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden styleId="xzdm" property="xzdm" value="${xzdm}"/>
			<div class="toolbox">	
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<logic:equal value="true" name="cssz" property="kgzt">
								<li>
									<a href="javascript:void(0);" class="btn_zj"
									   onclick="editSqb();return false;" 
									   title="点击该按钮，打开申请表填写页面。"
									>
										申请
									</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="xgSqb();return false;" class="btn_xg"
									   title="选中一条记录，点击该按钮可修改申请表。"
									>修改</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="qxsq();return false;" class="btn_sc"
									   title="只能删除未审核或退回的记录，已经在审核的不能删除。"
									>删除</a>
								</li>
							</logic:equal>
							<li>
								<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc"
								   title="对于已填写申请表未提交的记录进行提交申请操作。"
								>提交</a>
							</li>
							<logic:equal value="true" name="cssz" property="kgzt">
								<li>
									<a href="javascript:void(0);" onclick="cancleRst();return false;" class="btn_sr"
									   title="对于已提交未审核的记录进行撤销操作。"
									>撤销</a>
								</li>
							</logic:equal>
						<!-- 浙江大学-->
						<logic:equal name="xxdm" value="10335">
							<li><a href="#" class="btn_dr" id="btn_dr">导入</a></li>
						</logic:equal>
						</logic:equal>
							<li>
								<a href="javascript:void(0);" onclick="viewLcgz();return false;" 
								   title="选中一条记录，点击该按钮可以查看审核流程。"
								   class="btn_cs">流程跟踪</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">下载登记表</a>
							</li>
						<!-- 浙江大学 begin -->
						<logic:equal name="xxdm" value="10335">
							<logic:equal value="true" name="cssz" property="kgzt">
								<logic:equal value="xy" name="userType">
									<li><a href="jxmcmb_10335.xls" onclick="" class="btn_tj">奖项名称模版</a></li>
								</logic:equal>
							</logic:equal>
							<logic:equal value="zf01" name="userName">
								<li>
									<a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a>
								</li>
							</logic:equal>
						</logic:equal>
						<!-- 浙江大学 end -->
						
						<logic:notEqual value="10335" name="xxdm">
							<li>
								<a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a>
							</li>
						</logic:notEqual>

						<li><a href="javascript:void(0);" class="btn_xg" onclick="exceptionDataResolve();return false;">提交异常数据处理</a></li>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>奖项申请列表 </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
