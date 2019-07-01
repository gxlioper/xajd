<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xszy/xsqshf/js/xsqshf.js"></script>
		<script type="text/javascript" src="xsgzgl/xszy/comm/js/comm.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"寝室划分",
				pager:"pager",
				url:"xszyXsqshf.do?method=getQshfxxList&type=query",
				colList:[
					{label:'lddm',name:'lddm', index: 'lddm',hidden:true},
					{label:'dldm',name:'dldm', index: 'dldm',hidden:true},
					{label:'nj',name:'nj', index: 'nj',hidden:true},
					{label:'qsgxid',name:'qsgxid', index: 'qsgxid',hidden:true},
					{label:'寝室号',name:'qsh', index: 'qsh',width:'8%',formatter:qshLink},
					{label:'楼栋名称',name:'ldmc', index: 'ldmc',width:'12%'},
					{label:'学园',name:'xymc', index: 'xymc',width:'14%'},
					{label:'性别',name:'qsxb', index: 'qsxb',width:'6%'},
					{label:'大类',name:'dl', index: 'dl',width:'17%'},
					{label:'班级',name:'bjmc', index: 'bjmc',width:'17%'},
					{label:'入住人数',name:'rzrs', index: 'rzrs',width:'5%'},
					{label:'是否混</br>合寝室',name:'sfhhqs', index: 'sfhhqs',width:'5%'},
					{label:'操作',name:'', index: '',width:'16%',noSort:true,formatter:sdfpLink}
					],
				params:{fpzt:"dfp"},
				
			 	radioselect:false
		}

		var gridSetting2 = {
				caption:"寝室划分",
				pager:"pager",
				url:"xszyXsqshf.do?method=getQshfxxList&type=query",
				colList:[
					{label:'lddm',name:'lddm', index: 'lddm',hidden:true},
					{label:'dldm',name:'dldm', index: 'dldm',hidden:true},
					{label:'qsgxid',name:'qsgxid', index: 'qsgxid',hidden:true},
					{label:'nj',name:'nj', index: 'nj',hidden:true},
					{label:'id',name:'id', index: 'id',hidden:true,key:true},
					{label:'寝室号',name:'qsh', index: 'qsh',width:'8%',formatter:qshLink},
					{label:'楼栋名称',name:'ldmc', index: 'ldmc',width:'12%'},
					{label:'学园',name:'xymc', index: 'xymc',width:'12%'},
					{label:'性别',name:'qsxb', index: 'qsxb',width:'5%'},
					{label:'大类',name:'dl', index: 'dl',width:'18%'},
					{label:'入住人数',name:'rzrs', index: 'rzrs',width:'5%'},
					{label:'是否混合寝室',name:'sfhhqs', index: 'sfhhqs',width:'5%'},
					{label:'所属院系',name:'ssyxdm', index: 'ssyxdm',hidden:true},
					{label:'所属院系',name:'ssyxmc', index: 'ssyxmc',width:'16%'},
					{label:'操作',name:'', index: '',width:'9%',noSort:true,formatter:tzfpLink}
					],
				params:{fpzt:"yfp"},
				
			 	radioselect:false
		}
		var gridSetting3 = {
				caption:"寝室划分",
				pager:"pager",
				url:"xszyXsqshf.do?method=getQshfxxList&type=query",
				colList:[
					{label:'lddm',name:'lddm', index: 'lddm',hidden:true},
					{label:'dldm',name:'dldm', index: 'dldm',hidden:true},
					{label:'qsgxid',name:'qsgxid', index: 'qsgxid',hidden:true},
					{label:'nj',name:'nj', index: 'nj',hidden:true},
					{label:'寝室号',name:'qsh', index: 'qsh',width:'5%',formatter:qshLink},
					{label:'楼栋名称',name:'ldmc', index: 'ldmc',width:'12%'},
					{label:'学园',name:'xymc', index: 'xymc',width:'10%'},
					{label:'性别',name:'qsxb', index: 'qsxb',width:'5%'},
					{label:'大类',name:'dl', index: 'dl',width:'17%'},
					{label:'班级',name:'bjmc', index: 'bjmc',width:'17%'},
					{label:'入住人数',name:'rzrs', index: 'rzrs',width:'5%'},
					{label:'是否混</br>合寝室',name:'sfhhqs', index: 'sfhhqs',width:'5%'},
					{label:'退回人',name:'thr', index: 'thr',width:'8%'},
					{label:'操作',name:'', index: '',width:'16%',noSort:true,formatter:sdfpLink}
					],
				params:{fpzt:"yth"},
				
			 	radioselect:false
		}
			
		jQuery(function(){
			var map = getSuperSearch();
			map["fpzt"]="dfp";
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});
	
			
		
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xszyXsqshf">
			<input type="hidden" id="fpzt" value="dfp"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						<li id="li_plfp">
							<a href="javascript:void(0);" onclick="plfp();return false;" 
							   title="批量分配至院系"
							   class="btn_zj">批量分配至院系</a>
						</li>						
						<li id="li_qxfp" style="display: none;">
							<a href="javascript:void(0);" onclick="qxFp();return false;" 
							   title="选中一条记录，点击该按钮您可以撤消失误的分配操作。"
							   class="btn_sr">取消分配</a>
						</li>
						<li id="li_fpxq" style="display: none;">
							<a href="xszyXsqshf.do?method=fpxqCk"
							   title="选中一条记录，点击该按钮您可以查看分配详情。"
							   class="btn_ck">分配详情</a>
						</li>
						<li id="li_fptj" style="display: none;">
							<a href="xszyXsqshf.do?method=fptjCk"
							   class="btn_ck">学园分配统计</a>
						</li>			
						</logic:equal>					
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dfp');"><span>待分配</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'yth');">
			        <logic:equal value="xx" name="userStatus">
			        <span>学园退回</span>
			        </logic:equal>
			        <logic:notEqual value="xx" name="userStatus">
			        <span>已退回</span>
			        </logic:notEqual>
			        </a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'yfp');"><span>已分配</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>寝室划分列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
