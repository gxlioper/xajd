<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/gwglnew/js/gwsq.js"></script>
		<script language="javascript" defer="defer">
		function getGridSettiong(){
			var colList = [
	                {label:'key',name:'sqid', index: 'sqid',hidden:true,key:true},
//					{label:'行号',name:'r', index: 'r',width:'6%'},
                    {label:'学号',name:'xh', index: 'xh'},
                {label:'姓名',name:'xm', index: 'xm'},
                {label:'类别',name:'pycc', index: 'pycc'},
                {label:'班级',name:'bjmc', index: 'bjmc'},
                {label:'岗位名称',name:'gwmc', index: 'gwmc',width:'16%'},
					{label:'用人部门',name:'yrdwmc', index: 'yrdwmc',width:'18%'},
//					{label:'岗位性质',name:'gwxzmc', index: 'gwxzmc',width:'12%'},
//					{label:'需求人数',name:'xqrs', index: 'xqrs',width:'8%'},
//					{label:'需求困难生数',name:'knsrs', index: 'knsrs',width:'8%'},
//					{label:'岗位有效时',name:'gwyxs', index: 'gwyxs',width:'11%'},
					{label:'申请时间',name:'sqsj', index: 'sqsj',width:'12%'},
					{label:'审核状态',name:'shztmc', index: 'shztmc',width:'8%'},
					/*{label:'岗位开始时间',name:'gwkssj', index: 'gwkssj',hidden:true},
					{label:'岗位结束时间',name:'gwjssj', index: 'gwjssj',hidden:true},
					{label:'是否有效岗位',name:'sfyxgw', index: 'sfyxgw',hidden:true},
					{label:'学年',name:'xn', index: 'xn',hidden:true},
					{label:'学期',name:'xq', index: 'xq',hidden:true},*/
					{label:'shzt',name:'shzt', index: 'shzt',hidden:true},
					{label:'splcid',name:'splcid', index: 'splcid',hidden:true}
					
				];
			var gridSetting = {
				caption:"申请列表",
				pager:"pager",
				url:"qgzx_gwglnew.do?method=gwsqCx&type=query",
				sortname: "sqsj",
			 	sortorder: "desc"
			}
			gridSetting["colList"] = colList;
			return gridSetting;
		}

		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}

		jQuery(document).ready(function(){ 
			var gridSetting = getGridSettiong();
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);
		});
		
		function yrdwgwsqExportConfig() {
			//DCCLBH导出功能编号,执行导出函数 
			customExport("qgzx_gwglnew_ajax.do", yrdwgwsqExportData);
		}
			
		// 导出方法
		function yrdwgwsqExportData() {
			setSearchTj();//设置高级查询条件
			var url = "qgzx_gwglnew_ajax.do?method=gwsqExportData&dcclbh=" + "qgzx_gwglnew_ajax.do";//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		
		//复制
		function copyGwxx(){
			var ids = jQuery("#dataTable").getSeletIds();
			var len = ids.length;
			if(len>=1){
				var num = 0;
				var str = "";
				var idList = "";
				var rows = jQuery("#dataTable").getSeletRow();
				for(var i=0;i<ids.length;i++){
					var xn = rows[i]["xn"];
					var qgzq = jQuery("#qgzq").val();
					if(qgzq == "xq" ){
						xn = xn+ rows[i]["xq"];
					}
					if(str.indexOf(xn)==-1){
						str += xn+"!!@@!!";
						num++;
					}
					var gwdm = rows[i]["gwdm"];
					idList += gwdm + "!!@@!!";
				}
				url = "qgzx_gwglnew_ajax.do?method=gwsqFz&str="+str+"&num="+num+"&len="+len +"&idList="+idList;
				showDialog('复制岗位', 380, 390, url)
			}else{
				showAlertDivLayer("请选择您要复制的记录！");
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


		<html:form action="/qgzx_gwglnew" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>

			<!-- 隐藏域 -->
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<logic:notEmpty name="splc">
							<li>
								<a href="javascript:void(0);" onclick="showAdd();return false;" class="btn_zj">申请</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="showModi('update','修改岗位申请');return false;" class="btn_xg">修改</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="deleteGwsq();return false;" class="btn_sc">删除</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="submitBusi();return false" class="btn_shuc" >提交</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="cancel();return false" class="btn_sr" >撤销</a>
							</li>
						</logic:notEmpty>
						<logic:equal name="xxdm" value="10351">
						<li>
							<a href="javascript:void(0);" onclick="copyGwxx();return false;" class="btn_fz">复制岗位</a>
						</li>
						</logic:equal>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" class="btn_ck" onclick="showModi('view','查看岗位申请');return false;">查看</a>
						</li>

						<li><a href="javascript:void(0);" onclick="yrdwgwsqExportConfig();return false;" class="btn_dc">导出</a></li>
						<li>
							<a href="javascript:void(0);" onclick="lcgz();return false;" 
								   title="选中一条记录，点击该按钮可以查看审核流程。"
								   class="btn_cs">流程跟踪</a>
						</li>	
						<logic:equal name="xxdm" value="11799">
							<li>
								<a href="#" onclick="showModiTg('ryxxZj');return false;" class="btn_zj">人员增减</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="showModiTg('ryxxTg','人员退岗');return false;" class="btn_sc">人员退岗</a>
							</li>
						</logic:equal>
						<logic:equal name="xxdm" value="10344">
							<li>
								<a href="javascript:void(0);" onclick="showModiTg('ryxxTg','人员退岗');return false;" class="btn_sc">人员退岗</a>
							</li>
						</logic:equal>
						<logic:equal name="xxdm" value="10344">
						<li>
							<a href="javascript:void(0);" class="btn_dy" onclick="printgwsqb();return false;">打印岗位申请表</a>
						</li>
	                    </logic:equal>
						<%--<li><a href="javascript:void(0);" onclick="configureExportData();return false;" class="btn_dc">导出数据</a></li>
					--%></ul>
				</div>
				
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				</div>
			</html:form>
			<!-- 多功能操作区 end-->

			<div class="main_box">
				<h3 class=datetitle_01>
					<span>申请列表&nbsp;&nbsp; </span>
				</h3>
				<div class="con_overlfow">
					<table id="dataTable" ></table>
					<div id="pager"></div>
				</div>
			</div>
	</body>
</html>