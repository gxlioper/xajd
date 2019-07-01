<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/mrgzkh/khsq/khsq.js"></script>
		<script type="text/javascript">

		jQuery(function(){
			var gridSetting = {
				caption : "考核申请列表",
				pager : "pager",
				url : "mrgzkhKhsq.do?method=getKhsqList&type=query",
				colList : [ {
					label : 'key',
					name : 'sqid',
					index : 'sqid',
					key : true,
					hidden : true
				},{
					label : 'gwdm',
					name : 'gwdm',
					index : 'gwdm',
					hidden : true
				},{
					label : '审批流程',
					name : 'splc',
					index : 'splc',
					hidden : true
				}, {
					label : '学号',
					name : 'xh',
					index : 'xh',
					width : '12%',
					formatter : xhLink
				}, {
					label : '学年',
					name : 'xn',
					index : 'xn',
					width : '11%'
				}, {
					label : '姓名',
					name : 'xm',
					index : 'xm',
					width : '6%'
				}, {
					label : '用人单位',
					name : 'yrdwmc',
					index : 'yrdwmc',
					width : '12%'
				},{
					label : '岗位名称',
					name : 'gwmc',
					index : 'gwmc',
					width : '12%'
				},{
                    label : '岗位类别',
                    name : 'gwxzmc',
                    index : 'gwxzmc',
                    width : '7%'
                }, {
					label : '工作时间',
					name : 'gzsj',
					index : 'gzsj',
					width : '21%'
				}, {
					label : '工时',
					name : 'gs',
					index : 'gs',
					width : '5%'
				},{
					label : '申请时间',
					name : 'sqsj',
					index : 'sqsj',
					width : '20%'
				}, {
					label : '审核状态',
					name : 'shztmc',
					index : 'shztmc',
					width : '4%'
				}, {
					label : '审核状态',
					name : 'shzt',
					index : 'shzt',
					hidden : true
				}],
				sortname : "sqsj",
				sortorder : "desc"
			}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);

			var isopen = jQuery("#sqkg").val();
			if(isopen==null||isopen==''){
				jQuery("#prompt_isopen").show();
				jQuery("#prompt_null_isopen").show();
				return false;
			}
			if ("0" == isopen){
				jQuery("#prompt_isopen").show();
				jQuery("#prompt_false_isopen").show();
				return false;
			}
		})
				
		</script>
	</head>

	<body>
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<div class="prompt" id="prompt_isopen" style="display:none;">
			<h3>
				<span>提示：</span>
			</h3>
			<p id="prompt_null_isopen" style="display:none;">
				<bean:message key="lable.jcszwcsh_prompt" />
			</p>
			<p id="prompt_false_isopen" style="display:none;">
				<bean:message key="lable.dqwkfsq_prompt" />
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<html:form action="/mrgzkhKhsq">
			<input type="hidden" name="sqkg" id="sqkg" value="${sqkg}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;"  >填写</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">提交</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">撤销</a>
						</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="lcgz();return false;" 
								   title="选中一条记录，点击该按钮可以查看审核流程。"
								   class="btn_cs">流程跟踪</a>
						</li>	
										
							<%--<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	--%>
						
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>每日工作考核申请列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
