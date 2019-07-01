<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
		<script type="text/javascript" src="xsgzgl/zxdk/xyddk/js/dkjg.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		
			jQuery(function(){
				var gridSetting = null;
				if("10335" == ${xxdm}){
					gridSetting = {
							caption:"助学贷款列表 ",
							pager:"pager",
							url:"zxdkDkjg.do?method=getDkjgList",
							colList:[
							   {label:'key',name:'id', index: 'id',hidden:true,key:true},
							   {label:'学号',name:'xh', index: 'xh',width:'13%',formatter:function(cell,rowObject){
								   return "<a href=\"javascript:ckDkjg('"+rowObject["id"]+"');\" class='name'>"+cell+"</a>";
							   }},
							   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
							   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm'},
                                {label:'书院',name:'symc', index: 'sydm',width:'13%'},
                                {label:'行政班级',name:'bjmc', index: 'bjdm',width:'13%'},
                                {label:'专业班级',name:'zybjmc', index: 'zybj',width:'13%'},
							   {label:'贷款学年',name:'xn', index: 'xn'},
							   {label:'贷款总金额',name:'dkje', index: 'dkje'},
							   {label:'贷款年限',name:'xzf', index: 'xzf'},
							   {label:'贷款期限',name:'dkqx', index: 'dkqx'},
							   {label:'是否违约',name:'sfwy', index: 'sfwy'},
							   {label:'申请时间',name:'sqsj',index:'sqsj'}
							],
							sortname:"sqsj",
							sortorder:"desc",
							radioselect:true
						};
				}else{
					gridSetting = {
							caption:"助学贷款列表 ",
							pager:"pager",
							url:"zxdkDkjg.do?method=getDkjgList",
							colList:[
							   {label:'key',name:'id', index: 'id',hidden:true,key:true},
							   {label:'学号',name:'xh', index: 'xh',width:'13%',formatter:function(cell,rowObject){
								   return "<a href=\"javascript:ckDkjg('"+rowObject["id"]+"');\" class='name'>"+cell+"</a>";
							   }},
							   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
							   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm'},
                                {label:'书院',name:'symc', index: 'sydm',width:'13%'},
                                {label:'行政班级',name:'bjmc', index: 'bjdm',width:'13%'},
                                {label:'专业班级',name:'zybjmc', index: 'zybj',width:'13%'},
							   {label:'贷款学年',name:'xn', index: 'xn'},
							   {label:'贷款总金额',name:'dkje', index: 'dkje'},
							   {label:'放款总额',name:'fkze', index: 'fkze'},
							   {label:'续贷次数',name:'xdcs', index: 'xdcs'},
							   {label:'sjly',name:'sjly', index: 'sjly',hidden:true},
							   {label:'是否违约',name:'sfwy', index: 'sfwy'},
							   {label:'贷款期限',name:'dkqx', index: 'dkqx'},
							   {label:'申请时间',name:'sqsj',index:'sqsj'}
							],
							sortname:"sqsj",
							sortorder:"desc",
							radioselect:true
						};
				}
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function importDkxx(){
				toImportDataNew("ZXDK_XYDDK_DKXX");
				return false;
			}
			
			function importFdxx(){
				toImportDataNew("ZXDK_XYDDK_FDXX");
				return false;
			}
			
			function printSqb(){
				var url = "zxdkDkjg.do?method=printSqb";
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length <=0) {
					showAlertDivLayer("请选择一条记录！");
				} else {
					var guid = jQuery("#dataTable").getSeletIds();
					var url = url + "&ids=" +guid;
					window.open(url);
				}
			}

			function dkjgtjDc(){
				var url = "zxdkDkjg.do?method=getDkjgtj";
				document.forms[0].action = url;
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";	
			}
			function jsdkxxcj(){
				var url = "zxdkDkjg.do?method=printJsxxcj";
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length <=0) {
					showAlertDivLayer("请选择一条记录！");
				} else {
					var guid = jQuery("#dataTable").getSeletIds();
					var url = url + "&id=" +guid;
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
	
		<html:form action="/zxdkXyddk" method="post" styleId="zxdkXyddkForm">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				
					<div class="buttonbox">
						<ul>
							<logic:notEqual value="zgyh" name="userName">
								<logic:equal name="writeAble" value="yes">
									<logic:equal value="1" name="cssz" property="xydKg">
										<li>
											<a href="javascript:void(0);" class="btn_zj"
											   onclick="addDkjg();return false;" 
											>
											增加
											</a>
										</li>
										<li>
											<a href="javascript:void(0);" onclick="editDkjg();return false;" class="btn_xg"
											>修改</a>
										</li>
										<li>
											<a href="javascript:void(0);" onclick="delDkjg();return false;" class="btn_sc"
											>删除</a>
										</li>
									</logic:equal>
									<logic:notEqual value="10511" name="xxdm">
										<li><a href="#" class="btn_dr" onclick="importDkxx();return false;">导入贷款信息</a></li>
									   <li><a href="#" class="btn_dr" onclick="importFdxx();return false;">导入放贷记录</a></li>
									 </logic:notEqual>
	<%--							<li><a href="#" class="btn_dr" onclick="importWyxx();return false;">导入违约名单</a></li>--%>
							    </logic:equal>
							    
							    <logic:equal value="10335" name="xxdm">
							    	<logic:equal value="zf01" name="userName">
							    		<li>
							    			<a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a>
							    		</li>
							    	</logic:equal>
							    </logic:equal>
							    <logic:notEqual value="10335" name="xxdm">
							    	<li>
						    			<a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a>
							    	</li>
							    </logic:notEqual>
							    	
							    	<li><a href="javascript:void(0);" onclick="printSqb();" class="btn_dy">打印申请表</a></li>
							    	<logic:equal value='10335' name="xxdm">
							    		<li><a href="#" onclick="dkjgtjDc();return false;" class="btn_sh">汇总表下载 </a></li>
							    	</logic:equal>
						    </logic:notEqual>
						    <logic:equal value="zgyh" name="userName">
						    	<li><a href="#" class="btn_dr" onclick="importFdxx();return false;">导入放贷记录</a></li>
						    </logic:equal>
						    <logic:equal value="12688" name="xxdm">
								<li>
									<a href="#" class="btn_dc" onclick="jsdkxxcj();return false;">江苏省生源地信用助学贷款信息采集表</a>
								</li>
							</logic:equal>
							
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
				<span>助学贷款申请列表 </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
