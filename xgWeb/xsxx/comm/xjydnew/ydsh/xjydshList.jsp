<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type='text/javascript' src='dwr/engine.js'></script> 
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src='dwr/interface/exportData.js'></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script language="javascript" src="xsxx/comm/xjydnew/js/xjydsh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/shlc/js/splc.js"></script>
		<script type="text/javascript">
		//初始化查询
		var gridSetting = {
				caption:"学籍异动审核列表",
				pager:"pager",
				url:"xjydsh.do?method=xjydshList&type=query",
				colList:[
						   {label:'学号',name:'xh', index: 'xh',formatter:xhLink},
						   {label:'姓名',name:'xm', index: 'xm'},
						   {label:'原<bean:message key="lable.xb" />',name:'ydqxymc', index: 'ydqxymc'},
						   {label:'原班级',name:'ydqbjmc', index: 'ydqbjmc'},
						   {label:'异动类型',name:'ydlbmc', index: 'ydlbmc'},
						   {label:'申请时间',name:'sqsj', index: 'sqsj'},
						   {label:'审核状态',name:'shztmc', index: 'shztmc'},
						   {label:'shid',name:'shid', index: 'shid',hidden:true},
						   {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
						   {label:'ydlbdm',name:'ydlbdm', index: 'ydlbdm',hidden:true},
						   {label:'gwid',name:'gwid', index: 'gwid',hidden:true},
						   {label:'splc',name:'splc', index: 'splc',hidden:true},
						   {label:'xjydsqid',name:'xjydsqid', index: 'xjydsqid',key:true,hidden:true}
				],
				params:{shlx:"dsh"},
				sortname: "sqsj",
			 	sortorder: "desc"
		}
		var gridSetting2 = {
				caption:"学籍异动审核列表",
				pager:"pager",
				url:"xjydsh.do?method=xjydshList&type=query",
				colList:[
						   {label:'学号',name:'xh', index: 'xh',formatter:xhLink},
						   {label:'姓名',name:'xm', index: 'xm'},
						   {label:'原<bean:message key="lable.xb" />',name:'ydqxymc', index: 'ydqxymc'},
						   {label:'原班级',name:'ydqbjmc', index: 'ydqbjmc'},
						   {label:'异动类型',name:'ydlbmc', index: 'ydlbmc'},
						   {label:'申请时间',name:'sqsj', index: 'sqsj'},
						   {label:'审核状态',name:'shztmc', index: 'shztmc'},
						   {label:'shid',name:'shid', index: 'shid',hidden:true},
						   {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
						   {label:'ydlbdm',name:'ydlbdm', index: 'ydlbdm',hidden:true},
						   {label:'gwid',name:'gwid', index: 'gwid',hidden:true},
						   {label:'splc',name:'splc', index: 'splc',hidden:true},
						   {label:'xjydsqid',name:'xjydsqid', index: 'xjydsqid',key:true,hidden:true}
				],
				params:{shlx:"ysh"},
				sortname: "shsj",
			 	sortorder: "desc"
		}
			/*
			 * 审批流程撤销
			 * shid 审核id
			 * splc 审批流程id 
			 */
			function splc_cx(splcid,shid){
				confirmInfo("您确定要撤销操作吗?",function(ty){
					if(ty=="ok"){
						jQuery.post("xjydsh.do?method=cxsh",{splcid:splcid,shid:shid},function(data){
							showAlertDivLayer(data["message"],{},{"clkFun":function(){
								jQuery("#dataTable").reloadGrid();
							}});
						},'json');
					}
				});
			}
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
				jQuery("#btn_sh").click(go_sh);
				jQuery("#btn_cs").click(lcgz);
				jQuery("#btn_qxsh").click(cxshnew);
				jQuery("#btn_qxsh").hide();
			});
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
				<input type="hidden" name="shlx" id="shlx" value="dsh"/>
			</p>
		</div>
		<html:form action="/szdw_zwsh.do">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="cancelPath" id="cancelPath" value="${cancelPath}"/>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li><a href="javascript:void(0);" id="btn_sh" class="btn_sh">审核</a>
							<a href="javascript:void(0);" id="btn_qxsh" class="btn_qxsh">撤销</a>
							</li>
						</logic:equal>
						<li><a href="javascript:void(0);" id="btn_cs" class="btn_cs">流程跟踪</a></li>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="query(this,'dsh');"><span>待处理</span></a></li>
			        <li><a href="javascript:void(0);" onclick="query(this,'ysh');"><span>已处理</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>学籍异动审核列表</span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
