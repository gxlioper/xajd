<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/pjmd/js/pjmd.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"评奖名单确认",
				pager:"pager",
				url:"xpj_cpmd.do?method=viewCpxsList&type=query",
				colList:[
				   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
				   {label:'学号',name:'xh', index: 'xh',width:'13%'},
				   {label:'姓名',name:'xm', index: 'xm',width:'9%'},
				   {label:'性别',name:'xb', index: 'xb',width:'5%'},
				   {label:'参评年级',name:'nj', index: 'nj',width:'5%'},
				   {label:'参评<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'18%'},
				   {label:'参评专业',name:'zymc', index: 'zydm',width:'21%'},
				   {label:'参评班级',name:'bjmc', index: 'bjdm',width:'25%',formatter:function(cell,rowObject){

							if (rowObject["sfdh"] == "是"){
								return "<a class='name' href='javascript:void(0);' style='text-decoration:none;' title='原班级："+rowObject["ybj"]+"'>"+cell+"</a>";
							} else {
								return cell;
							}
					   
					   }},
				   {label:'是否调换',name:'sfdh', index: 'sfdh',width:'4',hidden:true},
				   {label:'调整人',name:'tzr', index: 'tzr',width:'4',hidden:true},
				   {label:'调整人姓名',name:'tzrxm',index:'tzrxm',whedth:'5',hidden:true},
				   {label:'原班级', name:'ybj', index:'ybj',width:'25',hidden:true},
				   {label:'提交状态',name:'tjzt', index: 'tjzt',width:'4',hidden:true}
				],
				sortname: "nj,xydm,zydm,bjdm",
			 	sortorder: "asc"
			}

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			
		</script>
		
	</head>

	<body style="min-height: 800px;">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xpj_cpmd">
		<input type="hidden" id="userName" value="${userName}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal name="writeAble" value="yes">	
				<div class="buttonbox">
					<ul>
						<li id="li_zj">
							<a href="javascript:void(0);" onclick="cpxsZj();return false;" 
							   title="增加一条不在自己数据范围内的评奖人员。"
							   class="btn_zj">增加参评学生</a>
						</li>
						<li id="li_tz">
							<a href="javascript:void(0);" onclick="cpxsTz();return false;" 
							   title="勾选一条记录，将参评人员调整为不参评或调整到自己当前的数据范围。"
							   class="btn_xg">调整参评学生</a>
						</li>
						<li >
							<a href="javascript:void(0);" onclick="cpxsDelete();return false;" 
							   title="选中一条或多条记录，取消勾选学生的参评状态。"
							   class="btn_sc">取消参评学生</a>
						</li>
					</ul>
				</div>
				</logic:equal>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a ><span>参评人员</span></a></li>
			        <li ><a href="javascript:void(0);" onclick="goTzjl();return false;"><span>调整记录</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span><font color="blue">${zqmc }</font>&nbsp;&nbsp;参评人员&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
