<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/uicomm.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="xsgzgl/wjcf/cfjgglnew/cfjg/js/cfjg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<!-- 导入功能需要-->
		<script language="javascript" defer="defer">	
		jQuery(function(){
			
			 var gridSetting = {
		    		 caption:"处分结果信息列表",
						pager:"pager",
						url:"wjcf_cfjg.do?method=cxCfjgList&type=query",
						params:getSuperSearch(),
						colList:[
						   {label:'处分id',name:'cfid', index: 'cfid',width:'10%',key:true, hidden:true},
						   {label:'学年',name:'xn', index: 'xn',width:'10%'},
						   {label:'学期',name:'xqmc', index: 'xqmc',width:'5%'},
						   {label:'学号',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
						   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
						   {label:'行政班级',name:'bjmc', index: 'bjmc',width:'15%'},
                           {label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'15%'},
						   {label:'处分类别',name:'cflbmc', index: 'cflbmc',width:'12%'},
						   {label:'处分原因',name:'cfyymc', index: 'cfyymc',width:'10%'},
						   {label:'发文时间',name:'fwsj', index: 'fwsj',width:'10%'},
						   {label:'发文结果',name:'fwjg', index: 'fwjg',width:'10%'},
						   {label:'处分文号',name:'cfwh', index: 'cfwh',width:'10%'},
						   {label:'sjly',name:'sjly', index: 'sjly',hidden:true},
						   {label:'jcwh',name:'jcwh', index: 'jcwh',hidden:true}
						],
						sortname: "fwsj",
					 	sortorder: "desc"
					}
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
		})
		
		function searchRs() {
	           var map = getSuperSearch();
	          jQuery("#dataTable").reloadGrid(map);
	          }
		
		function sjkwhExportConfig() {
			//DCCLBH导出功能编号,执行导出函数 
			customExport("wjcf_cfjg.do", sjkwhExportData);
		}
			
			
		// 导出方法
		function sjkwhExportData() {
			setSearchTj();//设置高级查询条件
			var url = "wjcf_cfjg.do?method=sjkwhExportData&dcclbh=" + "wjcf_cfjg.do";//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}


		//下载处分决定书(玉林师范学院)
		function printCfjdsWord() {
			var rows = jQuery("#dataTable").getSeletRow();			
			if (rows.length != 1) {
				showAlertDivLayer("请<font color='blue'>勾选一条</font>您希望下载的记录！");
				return false;
			}
			var cfid = rows[0]["cfid"];
			var url ="wjcfCfshwh_cfsjwh.do?method=getCfjdsb&&cfid="+cfid;
			window.open(url);
		}

		//下载拟处分告知书(玉林师范学院)
		function printNcfgzsWord() {
			var rows = jQuery("#dataTable").getSeletRow();	
			if (rows.length != 1) {
				showAlertDivLayer("请<font color='blue'>勾选一条</font>您希望下载的记录！");
				return false;
			}
			var cfid = rows[0]["cfid"];
			var url ="wjcfCfshwh_cfsjwh.do?method=getNcfgzsb&&cfid="+cfid;
			window.open(url);
		}

		//下载解除处分文件(玉林师范学院)
		function printJccfwjb() {
			var ids = jQuery("#dataTable").getSeletIds();
			var rows = jQuery("#dataTable").getSeletRow();
			var len = ids.length;
			if (len == 1) {
				var url = "wjcf_cfjcsq.do?method=getJccfwjb";
				url += "&xh=" + ids+"&cfid="+rows[0]["cfid"];
				window.open(url);
			} else {
				showAlertDivLayer("请<font color='blue'>勾选一条</font>您希望下载的记录！");
				return false;
			}
		}
		//下载处分意见书（河北工业大学）
		function printCftzswjb() {
			var rows = jQuery("#dataTable").getSeletRow();	
			if (rows.length != 1) {
				showAlertDivLayer("请<font color='blue'>勾选一条</font>您希望下载的记录！");
				return false;
			}
			var cfid = rows[0]["cfid"];
			var url ="wjcfCfshwh_cfsjwh.do?method=getCftzswjb&&cfid="+cfid;
			window.open(url);
		}
		// 通用打印方法，后台xxdm判断
		function getWordWjcfComman(type){
			 var rows = jQuery("#dataTable").getSeletRow();
	
			 if (rows.length == 0){
				showAlertDivLayer("请选择您要下载的记录！");
			 } else if (rows.length > 1){
				if(type == 'cfjctzs'){ 
					var flag = false;
					for(var i = 0; i < rows.length; i++){
						if((rows[i]["jcwh"] == '' || rows[i]["jcwh"] == null)){
							 flag = true;
						 }
					}
					if(flag){
						showAlertDivLayer("请选择解除处分的记录！");
						return false;
					}
				}
				if("cfjgdy"==type){
					var flag = false;
					 for(var i = 0; i < rows.length; i++){
							if(rows[i]["fwjg"] != '解除处分' && rows[i]["fwjg"] != '处分成立'){
								 flag = true;
								 break;
							 }
						}
						if(flag){
							showAlertDivLayer("请选择解除处分或处分成立的记录！");
							return false;
						}
					 }
				var ids = jQuery("#dataTable").getSeletIds();
				var fwjgArray = new Array();
				for(var i = 0; i < rows.length; i++){
					fwjgArray.push(rows[i]["fwjg"]);
				}
				var url="wjcf_cfsbgl.do?method=getDjbZip&value="+ids+"&type="+type+"&fwjgArray="+fwjgArray;
				window.open(url);
			 }else{
				 if(type == 'cfjctzs' && (rows[0]["jcwh"] == '' || rows[0]["jcwh"] == null)){
					 showAlertDivLayer("请选择解除处分的记录！");
					 return false;
				 }
				 if("cfjgdy"==type&&rows[0]["fwjg"] != '解除处分' && rows[0]["fwjg"] != '处分成立'){
					 showAlertDivLayer("请选择解除处分或处分成立的记录！");
					 return false;
				 }
				var url="wjcf_cfsbgl.do?method=getDjbWord&cfid="+rows[0]["cfid"]+"&type="+type+"&fwjg="+rows[0]["fwjg"];
				window.open(url);
		 	}
		}
			/*石家庄铁路职业学院-学生处分决定书*/
			function printCfjds() {
				var rows = jQuery("#dataTable").getSeletRow();
				var ids = jQuery("#dataTable").getSeletIds();
				var len = ids.length;
				if (len == 1) {
					var url = "wjcf_cfjg.do?method=getCfjdsDjb";
					url += "&xh=" + rows[0]["xh"] + "&cfid=" + rows[0]["cfid"];
					window.open(url);
				} else if (len == 0) {
					showAlertDivLayer("请选择您要下载的记录！");
					return false;
				} else {
					var url = "wjcf_cfjg.do?method=getCfjdsDjbZip";
					url += "&value=" + ids;
					window.open(url);
				}
			}

			/*青岛酒店管理职业技术学院13011*/
			function getCfjdsForQdjd(){
				var ids = jQuery("#dataTable").getSeletIds();
				var rows = jQuery("#dataTable").getSeletRow();
				var len = rows.length;
				if(0 == len){
					showAlertDivLayer("请选择您要下载的记录！");
					return false;
				}else{
					/*只能下载处分成立的记录*/
					for(var i = 0; i < rows.length; i++){
						if(rows[i]["fwjg"] != '处分成立'){
							showAlertDivLayer("请选择处分成立的记录！");
							return false;
						 }
					}
					var url="wjcf_cfsbgl.do?method=getCfjdsForQdjd&ids="+ids;
					window.open(url);
				}
			}
			//下载登记表(上海戏剧学院)
			function printCfWord(type) {
				var rows = jQuery("#dataTable").getSeletRow();			
				var ids = jQuery("#dataTable").getSeletIds();
				var len = rows.length;
				if (len == 1) {
					var url = "wjcf_cfjg.do?method=getCfForShxj&type="+type;
					url += "&xh=" + rows[0]["xh"] + "&cfid=" + rows[0]["cfid"];
					window.open(url);
				} else if (len == 0) {
					showAlertDivLayer("请选择您要下载的记录！");
					return false;
				} else {
					var url = "wjcf_cfjg.do?method=getCfForShxjZip&type="+type;
					url += "&value=" + ids;
					window.open(url);
				}
			}

        function getWord(){
            var rows = jQuery("#dataTable").getSeletRow();
            if (rows.length == 0) {
                showAlertDivLayer("请选择您要下载的记录！");
            } else if (rows.length > 1) {
                var ids = "";
                var xhs = "";
                for (var i = 0; i < rows.length; i++) {
                    ids += rows[i]["cfid"];
                    xhs += rows[i]["xh"];
                    if (i < rows.length - 1) {
                        ids += ",";
                        xhs += ",";
                    }
                }
                var url = "wjcf_cfsssq.do?method=getWjcfjdsZip&value=" +ids+"&xhs="+ xhs;
                window.open(url);
            }else {
                var url="wjcf_cfsssq.do?method=getWjcfjdsWord";
                var url= url+"&cfid="+rows[0]["cfid"]+"&xh="+rows[0]["xh"];
                window.open(url);
            }
        }

        function getCfjcWord() {
            var rows = jQuery("#dataTable").getSeletRow();

            if (rows.length == 0) {
                showAlertDivLayer("请选择您要下载的记录！");
            } else if (rows.length > 1) {
                var ids = "";
                var xhs = "";
                for (var i = 0; i < rows.length; i++) {
                    var fwzt = rows[i]["fwjg"];
                    if(fwzt!="解除处分")
                    {
                        showAlertDivLayer("请选择已解除处分的记录！");
                        return false;
                    }
                    ids += rows[i]["cfid"];
                    xhs += rows[i]["xh"];
                    if (i < rows.length - 1) {
                        ids += ",";
                        xhs += ",";
                    }

                }
                var url = "wjcf_cfsbgl.do?method=getCfjcZip&value="+ids+"&xhs="+xhs;
                window.open(url);
            } else {
                var fwzt = rows[0]["fwjg"];
                if(fwzt!="解除处分")
                {
                    showAlertDivLayer("请选择已解除处分的记录！");
                    return false;
                }
                var url = "wjcf_cfsbgl.do?method=getCfjcWord&cfid=" + rows[0]["cfid"] + "&xh=" + rows[0]["xh"];
                window.open(url);
            }
        }
		</script>
	</head>
	<body>

		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置： </em><a>${title }</a>
			</p>
		</div>
		<html:form action="/wjcf_cfjg" method="post">
			<input type="hidden" id="text"
				value="<bean:message key="wjcf.text" />" />

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />

			<!-- 多功能操作区 -->
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->

				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="#" onclick="showDialog('增加处分结果', 800, 500,'wjcf_cfjg.do?method=cfjgZj');return false;"class="btn_zj">增加</a>
							</li>
							<li>
								<a href="#" onclick="cfjgXg();return false;" class="btn_xg">修改</a>
							</li>
							<li>
								<a href="#" onclick="cfjgSc();return false;" class="btn_sc">删除</a>
							</li>
							<li>
								<a href="#" onclick="cfsscl();return false;" class="btn_xg">处分申诉</a>
							</li>
							<li>
								<a href="#" onclick="cfjccl();return false;" class="btn_xg">处分<bean:message key="wjcf.text" /> </a>
							</li>
							<li>
								<a href="#" onclick="cfzzcl();return false;" class="btn_xg">处分终止</a>
							</li>
							<li>
								<a href="#" onclick="toImportData( 'IMPORT_N100109');return false;" class="btn_dr">导入数据</a>
							</li>
						</logic:equal>
						<logic:equal value="10606" name="xxdm">
							<li><a href="#" class="btn_down" onclick="printCfjdsWord();return false;">处分文件下载</a></li>
							<li><a href="#" class="btn_down" onclick="printNcfgzsWord();return false;">拟处分告知书下载</a></li>
						</logic:equal>
						<logic:equal value="10080" name="xxdm">
							<li><a href="#" class="btn_down" onclick="printCftzswjb();return false;">处分意见下载</a></li>
						</logic:equal>
						<%--上海戏剧学院 --%>
						<logic:equal value="10279" name="xxdm">
							<li><a href="#" class="btn_down" onclick="printCfWord('cfjds');return false;">处分决定书下载</a></li>
							<li><a href="#" class="btn_down" onclick="printCfWord('cfsds');return false;">处分送达书签收单下载</a></li>
						</logic:equal>
						<logic:notEqual value="10606" name="xxdm">
							<li>
								<a href="#" onclick="showView(); return false;" class="btn_ck">查看</a>
							</li>
							<li>
								<a href="#" class="btn_dc" onclick="sjkwhExportConfig();return false;">导出</a>
							</li>
							<%--
							
							<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">导出数据</a></li>--%>
							<!-- 温州大学 -->
							<logic:equal value="10351" name="xxdm">
								<li>
									<a href="#" class="btn_down" onclick="getWord();return false;">下载<bean:message key="wjcf.text" />处分申请</a>
								</li>
							</logic:equal>
							<!-- 上海中侨职业技术学院 -->
							<logic:equal value="12915" name="xxdm">
								<li>
									<a href="#" class="btn_dy" onclick="getWjCfWord();return false;">违纪处分通知书打印</a>
								</li>
							</logic:equal>
							<!-- 苏州工业 -->
							<logic:equal value="12686" name="xxdm">
								<li>
									<a href="#" class="btn_down" onclick="getWordWjcfComman('cfjgdy');return false;">处分结果单打印</a>
								</li>
							</logic:equal>	
							<logic:equal value="12865" name="xxdm">
								<li>
									<a href="#" class="btn_down" onclick="getCfjdWord();return false;">处分决定书下载</a>
								</li>
							</logic:equal>	
						</logic:notEqual>	
						<!-- 南通工贸技师学院 begin -->
						<logic:equal name="xxdm" value="5002">	
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('wjcftzs');return false;" class="btn_down">下载处分通知书</a></li>
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('cfjctzs');return false;" class="btn_down">下载撤销处分通知书</a></li>
						</logic:equal>
						<!-- 南通工贸技师学院 end -->	
						<!-- 广西职业技术学院 begin -->
						<logic:equal name="xxdm"  value="11773">
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('wjcfsq');return false;" class="btn_down">下载违纪调查处理申报表</a></li>
						</logic:equal>
						<!-- 广西职业技术学院end -->
						<!-- 黑龙江农垦职业学院 begin -->
						<logic:equal name="xxdm" value="12727">	
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('cfjcsq');return false;" class="btn_down">下载解除处分审批表</a></li>
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('wjcfsq');return false;" class="btn_down">下载违纪处分审批表</a></li>
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('wjcftzs');return false;" class="btn_down">下载处分决定书</a></li>
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('wjcfqsd');return false;" class="btn_down">下载处分决定书签收单</a></li>
						</logic:equal>
						<!-- 黑龙江农垦职业学院 end -->	
						<!-- 徐州医药高等职业学校 begin -->
						<logic:equal name="xxdm"  value="70002">
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('wjcfsq');return false;" class="btn_down">下载违纪处分审批表</a></li>
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('cfjgdy');return false;" class="btn_down">下载处分撤销申请表</a></li>
						</logic:equal>
						<!-- 徐州医药高等职业学校 end -->
						<!-- 重庆三峡医药高等专科学校 begin -->
						<logic:equal name="xxdm" value="14008">	
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('cfsssq');return false;" class="btn_down">下载处分申诉申请表</a></li>
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('cfjcsq');return false;" class="btn_down">下载处分撤销申请表</a></li>
						</logic:equal>
						<!-- 重庆三峡医药高等专科学校 end -->
						<!-- 四川职业技术学院begin -->
						<logic:equal name="xxdm" value="12970">	
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('wjcfsq');return false;" class="btn_down">纪律处理登记表</a></li>
						</logic:equal>	
						<!-- 四川职业技术学院end -->
						<!-- 北京经济管理职业学院begin -->
						<logic:equal name="xxdm" value="14073">	
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('wjcfjds');return false;" class="btn_down">违纪处分决定书</a></li>
						</logic:equal>	
						<logic:equal name="xxdm" value="13871">	
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('wjcfjds');return false;" class="btn_down">违纪处分决定书</a></li>
						</logic:equal>	
						<!-- 北京经济管理职业学院end -->
							
						<!-- 青岛酒店管理职业技术学院 -->
						<logic:equal value="13011" name="xxdm">
							<li><a href="javascript:void(0);" class="btn_down" onclick="getCfjdsForQdjd();return false;">处分决定书下载</a></li>
						</logic:equal>
						
						<!-- 乌海职业技术学院 -->
						<logic:equal value="13915" name="xxdm">
							<li><a href="javascript:void(0);" class="btn_down" onclick="cfwjDownload('wjcf_cfjg.do?method=cfjdsDownload');return false;">处分决定书下载</a></li>
							<li><a href="javascript:void(0);" class="btn_down" onclick="cfwjDownload('wjcf_cfjg.do?method=cfspbDownload');return false;">处分审批表下载</a></li>
						</logic:equal>		
						
						<!-- 石家庄铁路职业技术学院-学生处分决定书 -->
						<logic:equal value="12424" name="xxdm">
							<li><a href="javascript:void(0);" onclick="printCfjds();return false;" class="btn_down">处分决定书</a></li>
						</logic:equal>
						
						<!-- 浙江警官职业学院，个性化打印学生处分审批表 -->
						<logic:equal name="xxdm" value="12869">
							<li><a href="javascript:void(0);" onclick="printXscfspb();return false;" class="btn_down">学生处分审批表</a></li>
						</logic:equal>
						<!-- 河北民族师范学院 begin -->
						<logic:equal name="xxdm" value="10098">
							<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">违纪处分决定书下载</a></li>
						</logic:equal>
						<!-- 河北民族师范学院 end -->
                        <!-- 河北民族师范学院 begin -->
                        <logic:equal name="xxdm" value="10098">
                            <li><a href="javascript:void(0);" onclick="getCfjcWord();return false;" class="btn_down">解除处分审批表下载</a></li>
                        </logic:equal>
                        <!-- 河北民族师范学院 end -->
					</ul>
				</div>
				<logic:equal value="10606" name="xxdm">
				<div class="buttonbox">
					<ul>							
						<li>
							<a href="javascript:void(0);" onclick="printJccfwjb();return false;"
								class="btn_down"><bean:message key="wjcf.text" />处分文件下载</a>
						</li>
						<li>
							<a href="#" onclick="showView(); return false;" class="btn_ck">查看</a>
						</li>
						<li>
							<a href="#" class="btn_dc" onclick="sjkwhExportConfig();return false;">导出</a>
						</li>
					</ul>
				</div>
				</logic:equal>

				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
		<div class="toolbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 查询结果</span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
