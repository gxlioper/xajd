<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/xnxj/10511/js/xnxj.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"学年小结申请表",
				pager:"pager",
				url:"xsxx_xnxj_xjtxgl.do?method=viewXnxjList&type=query",
				colList:[
				   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
				   {label:'学号',name:'xh', index: 'xh',width:'11%' , formatter:xhLink1},
				   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'13%'},
				   {label:'splid',name:'splid', index: 'splid',hidden:true},
				   {label:'班级',name:'bjmc', index: 'bjdm',width:'13%'},
				   {label:'学年',name:'xn', index: 'xn',width:'10%'},
				   {label:'填写时间',name:'txsj', index: 'txsj',width:'9%'},
				   {label:'txzt',name:'txzt', index: 'txzt',hidden:true},
				   {label:'填写状态',name:'txztmc', index: 'txztmc',width:'8%'},
				   {label:'shjg',name:'shjg', index: 'shjg',hidden:true},
				   {label:'审核状态',name:'shztmc', index: 'shzt',width:'6%'}
				],
				sortname: "txsj",
			 	sortorder: "desc"
			}
			
			jQuery(function(){
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);
				
			});

			function searchRs(){
				var search_xn = jQuery("a[name='a_name_xn']");
				
				if(search_xn.length != 1){
					if(jQuery('#userType').val() != 'stu'){
						showAlertDivLayer("请选择一个学年！",{},{});
						return false;
						}
					
				}
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			/**
			 * 学号链接
			 * @param cellValue
			 * @param rowObject
			 * @return
			 */

			function xhLink1(cellValue,rowObject){
				//return "<a href='javascript:void(0);' class='name' onclick='knsView(\""+rowObject["id"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
//				var onclickfn = "onclick=\"" + "showDialog('学生详细信息' , 720 , 350 , 'xsxx_xnxj_xjtxgl.do?method=xnxjjdck&xh=" + cellValue + "')" + "\"";
				var onclickfn = "onclick=\"" + "showDialog('学生详细信息' , 720 , 350 , 'xsxx_xnxj_xjtxgl.do?method=xnxjjdck&xh=" + cellValue + "&xn=" + rowObject['xn'] + "')" + "\"";
				
				var href = "href = 'javascript:void(0);'";

				var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue + "</a>";
				
				return el;
			}
		</script>
	</head>

	<body>
		<input type="hidden" name="kgzt" id="kgzt" value="${jcszModel.kg }"/>
		
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
			</p>
		</div>
		<!-- 提示信息 end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
					取消申请：仅可取消“未审核”的学年小结填写
				</span>
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		<html:form action="/xszz_jtqkdc">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="xnxjsq();return false;"  title="点击该按钮，打开申请表填写页面。">填写小结</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="xnxjUpdate();return false;" class="btn_xg" title="选中一条记录，点击该按钮可修改申请表。">修改小结</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="xnxjDelete();return false;" class="btn_sc" title="只能取消未审核的记录，已经在审核的不能取消。" >删除小结</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">提交</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancle();return false;" class="btn_sr">撤销</a>
						</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="xnxjLcinfo();return false;" 
							   title="选中一条记录，点击该按钮可以查看审核流程。"
							   class="btn_cs">流程跟踪</a>
						</li>		
						<li><a href="javascript:void(0);" onclick="printXnxjSq('xsxx_xnxj_xjtxgl.do?method=doPrint');return false;" class="btn_down">下载小结表</a></li>						
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>学年小结申请表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
