<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/ystgl/rtgl/rtsq/js/rtsq.js"></script>
		<script type="text/javascript" src="xsgzgl/ystgl/rtgl/comm/js/comm.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption:"艺术团项目明细",
				pager:"pager",
				rowNum:10,
				url:"ystglRtsq.do?method=getYstxmList&type=query"+"&xh="+jQuery("#xh").val(),
				params:getSuperSearch(),
				colList:[
				   {label:'ystid',name:'ystid', index: 'ystid',key:true,hidden:true},
				   {label:'有效学年',name:'xn', index: 'xn',width:'8%'},
				   {label:'艺术团项目名称',name:'ystxmmc', index: 'ystxmmc',width:'8%'},
				   {label:'艺术团类别',name:'ystlbmc', index: 'ystlbmc',width:'5%'},
				   {label:'项目类别',name:'xmlbmc', index: 'xmlbdm',width:'8%'},
				   {label:'指导老师',name:'zdlsxm', index: 'zdlsxm',width:'8%'},
				   {label:'sqkg',name:'sqkg', index: 'sqkg',hidden:true},
				   {label:'gkdw',name:'gkdw', index: 'gkdw',hidden:true},
				   {label:'gkdwmc',name:'gkdwmc', index: 'gkdwmc',hidden:true},
				   {label:'sqsj',name:'sqsj', index: 'sqsj',hidden:true},
				   {label:'jtr',name:'jtr', index: 'jtr',hidden:true},
				   {label:'fzr',name:'fzr', index: 'fzr',hidden:true},
				   {label:'stfzrxm',name:'stfzrxm', index: 'stfzrxm',hidden:true},
				   {label:'zcmc',name:'zcmc', index: 'zcmc',hidden:true},
				   {label:'zdlslxfs',name:'zdlslxfs', index: 'zdlslxfs',hidden:true},
				   {label:'lxdh',name:'lxdh', index: 'lxdh',hidden:true},
				   {label:'ssbmmc',name:'ssbmmc', index: 'ssbmmc',hidden:true},
				   {label:'fzrlb',name:'fzrlb', index: 'fzrlb',hidden:true},
				   {label:'ystclsj',name:'ystclsj', index: 'ystclsj',hidden:true},
				   {label:'ysthjqk',name:'ysthjqk', index: 'ysthjqk',hidden:true},
				   {label:'sqsj',name:'sqsj', index: 'sqsj',hidden:true},
				   {label:'ystjj',name:'ystjj', index: 'ystjj',hidden:true}
				],
				sortname: "xn",
			 	sortorder: "desc",
			 	radioselect:true
			}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
			
			
		});

			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			function ystZj() {
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length == 0) {
					showAlertDivLayer("请选择一个艺术团项目！");
					return false;
				}else if(rows[0]['sqkg'] != '1'){
					showAlertDivLayer("该项目申请已经关闭，无法选择！");
					return false;
				}
			    setYst(rows);
			
			}
		</script>
	</head>

	<body>
		<html:form method="post" styleId="form" action="/ystglRtsq">
		<input type="hidden" name="xh" id="xh" value="${xh}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
				<ul>
						<li>
							<a href="javascript:void(0);" onclick="ystZj();return false;" class="btn_zj">添加</a>
						</li>
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
				<span>艺术团项目明细
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>
