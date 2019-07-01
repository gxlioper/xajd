<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zhcp/zcfs/js/zcfs.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"已提交综测分班级列表",
				pager:"pager",
				url:"xpj_zcfs.do?method=viewZcfqxListYf&doType=query",
				colList:[
				   {label:'key',name:'id', index: 'id',hidden:true,key:true},
				   {label:'年级',name:'nj', index: 'nj',width:'6%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'19%'},
				   {label:'专业',name:'zymc', index: 'zydm',width:'20%'},
				   {label:'班级',name:'bjdm', index: 'bjdm',hidden:true},
				   {label:'班级',name:'bjmc', index: 'bjdm',width:'23%'},
				   {label:'班级人数',name:'bjrs', index: 'bjrs',width:'8%',
						formatter:function(cellValue,rowObject){
									var html = jQuery("<a href='javascript:void(0);' class='name'>"+cellValue+"</a>");
									html.bind("click",function(){
										showDialog("查看综测分",700,500,"xpj_zcfs.do?method=viewZcfsOfyf&id="+rowObject["id"]);
									});
									return html;
								 }
					   },
				   {label:'提交人',name:'tjr', index: 'tjr',width:'8',hidden:true},
				   {label:'提交人',name:'tjrxm', index: 'tjrxm',width:'12%'},
				   /*
				   {label:'提交时间',name:'tjsj', index: 'tjsj',width:'15%'},*/
				   {label:'综测年月',name:'ndyf', index: 'ndyf',width:'10%'}
				],
				sortname: "nj,xydm,zydm,bjdm,to_number(ndyf)",
			 	sortorder: "desc",
			 	radioselect:false
			};
			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			//取消提交综测分状态
			function cancelTj(){
				var id = jQuery("#dataTable").getSeletIds();
				if (id.length == 0){
					showAlertDivLayer("请选择您要取消的班级！");
					return false;
				} 
				showDialog("取消提交",400,250,"xpj_zcfs.do?method=cancelTjofYf&id="+id);
			
			}
			//前往取消调整记录
			function goQxtjjl(){
				var url = "pj_qxjl.do";
				refreshForm(url);
			}
		</script>
	</head>

	<body>
	
		<input type="hidden" value="${cssz.xn }" id="xn"/>
		<input type="hidden" value="${cssz.xq }" id="xq"/>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;"	onmousedown ="showHelpDiv()" >使用帮助</a>
			</p>
		</div>
		<html:form action="/xpj_zcfs" >
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 提示信息 end-->
			<div id="div_help" class="prompt" style="display: none">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					<span>
						对于已提交综测数据的班级，管理员可在此处进行<font color="red">取消提交</font>操作，取消提交的班级综测数据可<font color="red">重新调整修改</font>
					</span>
				</p>
				<a class="close" title="隐藏"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- 提示信息 end-->
			
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal name="writeAble" value="yes">	
				<div class="buttonbox">
					<ul>
						<logic:equal value="true" name="cssz" property="kgzt">
						<li><a href="javascript:void(0);" onclick="cancelTj();" class="btn_xg">取消提交</a></li>
						</logic:equal>
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
				<span><font color="blue">${cssz.zqmc}&nbsp;</font>已提交班级 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
