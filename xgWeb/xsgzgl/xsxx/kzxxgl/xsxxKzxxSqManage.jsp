<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			jQuery(function(){
			     var gridSetting = {
							caption:"扩展信息申请列表",
							pager:"pager",
							url:"xsxx_kzxxgl.do?method=list&type=query",
							params:getSuperSearch(),
							colList:[
							   {label:'申请id',name:'sqid', index: 'sqid',key:true,hidden:true},
							   {label:'审批流程',name:'splc', index: 'splc',hidden:true},
							   {label:'学号',name:'xh', index: 'xh',formatter:xhLink},
							   {label:'姓名',name:'xm', index: 'xm'},
							   {label:'性别',name:'xb', index: 'xb'},
							   {label:'学院',name:'xymc', index: 'xymc'},
							   {label:'专业',name:'zymc', index: 'zymc'},
							   {label:'班级',name:'bjmc', index: 'bjmc'},
							   {label:'申请时间',name:'sqsj', index: 'sqsj'},
							   {label:'审核状态',name:'shztmc', index: 'shzt'},
							   {label:'审核状态',name:'shzt', index: 'shzt',hidden:true}
							],
							sortname: "sqsj",
						 	sortorder: "desc"
						}
					jQuery("#dataTable").initGrid(gridSetting);
			});
			
			/**
			 * 学号链接
			 * @param cellValue
			 * @param rowObject
			 * @return
			 */
			function xhLink(cellValue,rowObject){
				var onclickfn = "onclick=\"" + "showDialog('学生详细信息' , 720 , 550 , 'xsxx_kzxxgl.do?method=ck&xh=" + cellValue + "')" + "\"";
				var href = "href = 'javascript:void(0);'";
				var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue + "</a>";
				return el;
			}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown="showHelpDiv()">使用帮助</a>
			</p>
		</div>
		<!-- 提示信息 end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span> 取消申请：仅可取消“未审核”的学年小结填写 </span>
			</p>
			<a class="close" title="隐藏"
				onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->

		<html:form action="xsxx_kzxxgl.do?method=list&type=query">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="javascript:void(0);" onclick="add();return false;"
									class="btn_zj">申请</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="update();return false;"
									class="btn_xg">修改</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="del();return false;"
									class="btn_sc">删除</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="pltj();return false;"
									class="btn_tj">提交</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="cancle();return false;"
									class="btn_sr">撤销</a>
							</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);"
								onclick="showLcinfo();return false;" title="查看审核流程。"
								class="btn_cs">流程跟踪</a>
						</li>
					</ul>
				</div
					<!-- 过滤条件 -->
					<%@ include file="/comm/search/superSearchArea.jsp"%>
					<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="main_box">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 学生扩展信息申请列表</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
