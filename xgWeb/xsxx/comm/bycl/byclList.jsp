<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script language="javascript" src="xsxx/comm/bycl/js/bycl.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
			var gridSetting1 = {
				caption:"学生信息列表",
				pager:"pager",
				multiselect:true,
				rowNum:10,
				url:"bycl.do?method=byclList&type=query",
				colList:[
				   {label:'学号',name:'xh', index: 'xh',width:'13%',key:true,formatter:setXh},
				   {label:'姓名',name:'xm', index: 'xm',width:'13%'},
				   {label:'年级',name:'nj', index: 'nj',width:'6%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
                    {label:'书院',name:'symc', index: 'symc',width:'15%'},
				   {label:'行政班级',name:'bjmc', index: 'bjdm',width:'18%'},
                    {label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'18%'},
				   {label:'性别',name:'xb', index: 'xb',width:'6%'},
				   {label:'学制',name:'xz', index: 'xz',width:'5%'},
				   {label:'学籍类别',name:'xjlb', index: 'xjlb',width:'18%'},
				   {label:'是否在校',name:'sfzx', index: 'sfzx',width:'10%'}
				],
				sortname: "xymc,bjmc,xh",
			 	sortorder: "desc"
			}
			var gridSetting2 = {
					caption:"学生信息列表",
					pager:"pager",
					multiselect:true,
					rowNum:10,
					url:"bycl.do?method=byclList&type=query",
					colList:[
					   {label:'学号',name:'xh', index: 'xh',width:'13%',key:true,formatter:setXh},
					   {label:'姓名',name:'xm', index: 'xm',width:'13%'},
					   {label:'年级',name:'nj', index: 'nj',width:'6%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm'},
                        {label:'书院',name:'symc', index: 'symc',width:'15%'},
                        {label:'行政班级',name:'bjmc', index: 'bjdm',width:'18%'},
                        {label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'18%'},
					   {label:'性别',name:'xb', index: 'xb',width:'6%'},
					   {label:'学制',name:'xz', index: 'xz',width:'5%'},
					   {label:'毕业年月',name:'byny', index: 'byny',width:'10%'}
					],
					sortname: "xymc,bjmc,xh",
				 	sortorder: "desc"
				}
			jQuery(function(){
				jQuery("#li_qxby").hide();
				jQuery("#dataTable").initGrid(gridSetting1);
			});
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
			</p>
		</div>
		<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
		<!-- 提示信息 START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				检索后，不选择任何学生，即全选
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 END-->
		<html:form action="/xsxx_xsgl">
			<input type="hidden" id="sfyby" value=""/>
			<div class="toolbox">
				<div class="buttonbox">
				  <ul>
					<logic:equal value="yes" name="writeAble">
						<li id="li_by">
							<a href="javascript:void(0);" onclick="bycl();return false;" class="btn_zj" id="byButton">毕业处理</a>
						</li>
						<li id="li_qxby">
							<a href="javascript:void(0);" onclick="qxbycl();return false;" class="btn_sc" id="qxbyButton">取消毕业</a>
						</li>
						
						<li id="li_bydr">
							<a href="javascript:void(0);" class="btn_dr" onclick="byxsImport();return false;">导入</a>
						</li>
					</logic:equal>
					    <li>
						    <a href="javascript:void(0);" onclick="exportConfig();return false;"  class="btn_dc">导出</a>
					    </li>
					<logic:equal value="11318" name="xxdm">
							<li id="li_jcdy"><a href="#" class="btn_dy" id="jcxxButton" onclick="getJcXxWord();return false;">学生奖惩信息打印</a></li>
						</logic:equal>
				  </ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
				<!-- 按钮 -->
				<div class="comp_title" id="comp_title">
			      <ul style="width:70%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'0');"><span>未毕业学生</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'1');"><span>已毕业学生</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		<div class="formbox">
					<!--标题start-->
			<h3 class="datetitle_01">
				<span> 学生信息列表
				 </span>
			</h3>
		</div>
			<table id="dataTable" ></table>
			<div id="pager"></div>
	</body>
</html>
