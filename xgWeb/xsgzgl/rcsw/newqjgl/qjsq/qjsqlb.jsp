<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/newqjgl/qjsq/js/qjsq.js"></script>
				<script type="text/javascript">
					jQuery(function(){
						function bs(cellValue, rowObject){
							var sfcs = rowObject["sfcs"];
							if(sfcs == '1'){
								return '<font color="red">'+cellValue+'</font>';
							}else{
								return cellValue;
							}
						}
						
						function csFormat(cellValue, rowObject){
							var sfcs = rowObject["sfcs"];
							if(sfcs == '1'){
								var tds = jQuery(rowObject).find('td');
								return '<span><font color="red">审核超时</font></span>';
							}else{
								return cellValue;
							}
						}

						function xhFormat(cellValue, rowObject){
							var sfcs = rowObject["sfcs"];
							var qjsqid = rowObject["qjsqid"];
							if(sfcs == '1'){
								return  "<a href='javascript:void(0);' onclick=\"ckxx('" + qjsqid
								+ "')\" class='name'><font color=\"red\">" + cellValue + "</font></a>";
							}else{
								return "<a href='javascript:void(0);' onclick=\"ckxx('" + qjsqid
								+ "')\" class='name'>" + cellValue + "</a>";
							}
						}
						
					     var gridSetting = {
									caption:"请假申请列表",
									pager:"pager",
									url:"qjsq.do?method=list&type=query",
									params:getSuperSearch(),
									colList:[
									   {label:'请假申请id',name:'qjsqid', index: 'qjsqid',key:true,hidden:true},
									   {label:'审批流程',name:'splcid', index: 'splcid',hidden:true},
                                       {label:'xydm',name:'xydm', index: 'xydm',hidden:true},
									   {label:'学年',name:'xn', index: 'xn'},
									   {label:'学期',name:'xqmc', index: 'xqmc'},
									   {label:'学号',name:'xh', index: 'xh',formatter:dcmcLink},
									   {label:'姓名',name:'xm', index: 'xm'},
									   {label:'请假类型',name:'qjlxmc', index: 'qjlxmc'},
									   {label:'请假天数',name:'qjts', index: 'qjts'},
									   {label:'申请时间',name:'sqsj', index: 'sqsj'},
									   {label:'状态',name:'qjzt', index: 'qjzt',hidden:true},
									   {label:'审核状态',name:'shztmc', index: 'shzt'},
									   {label:'审核状态',name:'shzt', index: 'shzt',hidden:true},
									   {label:'审核状态',name:'qjlxid', index: 'qjlxid',hidden:true},
									   {label:'销假状态',name:'xjztmc', index: 'xjztmc'}
									],
									sortname: "sqsj",
								 	sortorder: "desc"
								}

					     var gridSetting1 = {
									caption:"请假申请列表",
									pager:"pager",
									url:"qjsq.do?method=list&type=query",
									params:getSuperSearch(),
									colList:[
									   {label:'请假申请id',name:'qjsqid', index: 'qjsqid',key:true,hidden:true},
									   {label:'审批流程',name:'splcid', index: 'splcid',hidden:true},
									   {label:'学年',name:'xn', index: 'xn',formatter:bs},
									   {label:'学期',name:'xqmc', index: 'xqmc',formatter:bs},
									   {label:'学号',name:'xh', index: 'xh',formatter:xhFormat},
									   {label:'姓名',name:'xm', index: 'xm',formatter:bs},
									   {label:'请假类型',name:'qjlxmc', index: 'qjlxmc',formatter:bs},
									   {label:'请假天数',name:'qjts', index: 'qjts',formatter:bs},
									   {label:'申请时间',name:'sqsj', index: 'sqsj',formatter:bs},
									   {label:'状态',name:'qjzt', index: 'qjzt',hidden:true},
									   {label:'审核状态',name:'shztmc', index: 'shzt',formatter:csFormat},
									   {label:'审核状态',name:'shzt', index: 'shzt',hidden:true},
									   {label:'审核状态',name:'qjlxid', index: 'qjlxid',hidden:true},
									   {label:'sfcs',name:'sfcs', index: 'sfcs',hidden:true},
									   {label:'销假状态',name:'xjztmc', index: 'xjztmc',formatter:bs}
									],
									sortname: "sqsj",
								 	sortorder: "desc"
								}
						var gridSetting3 = {
									caption:"请假申请列表",
									pager:"pager",
									url:"qjsq.do?method=list&type=query",
									params:getSuperSearch(),
									colList:[
									   {label:'请假申请id',name:'qjsqid', index: 'qjsqid',key:true,hidden:true},
									   {label:'审批流程',name:'splcid', index: 'splcid',hidden:true},
									   {label:'学年',name:'xn', index: 'xn'},
									   {label:'学期',name:'xqmc', index: 'xqmc'},
									   {label:'学号',name:'xh', index: 'xh',formatter:dcmcLink},
									   {label:'姓名',name:'xm', index: 'xm'},
									   {label:'楼栋名称',name:'ldmc', index: 'lddm'},
									   {label:'寝室号',name:'qsh', index: 'qsh'},
									   {label:'请假类型',name:'qjlxmc', index: 'qjlxmc'},
									   {label:'请假天数',name:'qjts', index: 'qjts'},
									   {label:'申请时间',name:'sqsj', index: 'sqsj'},
									   {label:'状态',name:'qjzt', index: 'qjzt',hidden:true},
									   {label:'审核状态',name:'shztmc', index: 'shzt'},
									   {label:'审核状态',name:'shzt', index: 'shzt',hidden:true},
									   {label:'审核状态',name:'qjlxid', index: 'qjlxid',hidden:true},
									   {label:'销假状态',name:'xjztmc', index: 'xjztmc'}
									],
									sortname: "sqsj",
								 	sortorder: "desc"
								}
							if('12866' == jQuery("#xxdm").val()){
								jQuery("#dataTable").initGrid(gridSetting1);
							}else if('12303' == jQuery("#xxdm").val()){
								jQuery("#dataTable").initGrid(gridSetting3);
							}else{
								jQuery("#dataTable").initGrid(gridSetting);
							}
					});
				</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
	<html:form action="/qjsq?method=list&type=query">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<input type="hidden" id="swtjs" value="${swtjs}"/>
		<input type="hidden" id="search_go" onclick="reload()"/>
		<input type="hidden"  name="query" id="query" value="${query}"/>
		<input type="hidden" id = "usertype" value = "${usertype}"/>
		<input type="hidden" id = "qjxy" value="${qjxy}"/>
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<logic:equal name="writeAble" value="yes">	
						<li>
							<a href="javascript:void(0);" onclick="qjxyCk();return false;" class="btn_zj">申请</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="pltj();return false;" class="btn_tj">提交</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancle();return false;" class="btn_sr">撤销</a>
						</li>
						</logic:equal>
						<li><a href="javascript:void(0);" onclick="rcxwshLcinfo();return false;" 
							   title="选中一条记录，点击该按钮可以查看审核流程。"
							   class="btn_cs">流程跟踪</a></li>
						<li>
							<a href="javascript:void(0);" onclick="printQjsqb('qjsq.do?method=printQjsqb');return false;" class="btn_down">下载请假申请表</a>
						</li>
					
					<!-- 浙江警官职业学院，个性化打印请假审批表 -->	   
					<logic:equal name="xxdm" value="12869">
						<li>
							<a href="javascript:void(0);" onclick="printXsqjspb();return false;" class="btn_down">请假审批表</a>
						</li>
					</logic:equal>
				</ul>
			</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
		</div>
		</html:form>
		<div class="toolbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span style="width: 80%"> 请假申请列表
				<logic:equal value="12866" name="xxdm">								
						<font color="red">（已超过开始时间的未提交和未审核完的将标红显示） </font>					
					<a onclick="this.parentNode.style.display='none';" title="隐藏" class="close"></a>
				</logic:equal><a id="title" href="javascript:;" style="float:right;margin-right:30px;color: blue"></a> </span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
