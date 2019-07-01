<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/dtjs/zzgxzc/zcsq/js/zcsq.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "",
				pager : "pager",
				url : "dzzgxsq.do?method=dzzGxJsSq&type=query",
				colList : [ {
					label : 'key',
					name : 'sqid',
					index : 'sqid',
					key : true,
					hidden : true
				}, {
					label : '学号',
					name : 'xh',
					index : 'xh',
					width : '7%',
					formatter : xhLink
				}, {
					label : '姓名',
					name : 'xm',
					index : 'xm',
					width : '6%'
				}, {
					label : '学院',
					name : 'xymc',
					index : 'xydm',
					width : '8%'
				},{
					label : '班级',
					name : 'bjmc',
					index : 'bjdm',
					width : '10%'
				},{
					label : '政治面貌',
					name : 'jc',
					index : 'jc',
					width : '5%'
				},{
					label : '所在党支部',
					name : 'dzbmc',
					index : 'dzbmc',
					width : '8%'
				}, {
					label : '接收本人组织关系的党组织',
					name : 'jsdzz',
					index : 'jsdzz',
					width : '6%'
				},{
					label : '审核状态',
					name : 'shztmc',
					index : 'shztmc',
					width : '3%'
				},{
					label : '申请时间',
					name : 'sqsj',
					index : 'sqsj',
					width : '9%'
				},
				{
					name : 'shzt',
					index : 'shzt',
					hidden : true
				},
				{
					name : 'splcid',
					index : 'splcid',
					hidden : true
				}
				],
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
		//党组织关系导出
		 function dzzgxdc(){
				var ids = jQuery("#dataTable").getSeletIds();
				var rows = jQuery("#dataTable").getSeletRow();
				var len = ids.length;
				if (len == 1) {
					if(rows[0]["shzt"] != '1'){
						return showAlertDivLayer("只能选择已通过的记录！");
					}
					var url = "dtjs_xxjg.do?method=zzgxdjbDc";
					url += "&id=" + ids;
					window.open(url);
				} else if (len == 0) {
					showAlertDivLayer("请选择您要下载的记录！");
					return false;
				} else {
					var message = "";
					jQuery(rows).each(function(i,row){
						if(row["shzt"] != '1'){
							message = "只能选择已通过的记录！";
							return;
						}
					});
					if(message != ""){
						return showAlertDivLayer(message);
					}
					var url = "dtjs_xxjg.do?method=zzgxdjbDcTy";
					url += "&value=" + ids;
					window.open(url);
				}
		  }
		</script>
	</head>

	<body>
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
		
		<html:form action="/dzzgxsq">
			<input type="hidden" name="sqkg" id="sqkg" value="${sqkg}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<logic:equal name="sqkg" value = "1">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;" >申请</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >删除</a>
						</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false" class="btn_shuc" >提交</a>
						</li>
						<logic:equal name="sqkg" value = "1">
						<li>
							<a href="javascript:void(0);" onclick="cancel();return false" class="btn_sr" >撤销</a>
						</li>
						</logic:equal>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="lcgz();return false;" 
								   title="选中一条记录，点击该按钮可以查看审核流程。"
								   class="btn_cs">流程跟踪</a>
						</li>	
						<logic:equal value="12309" name="xxdm">
							<li><a href="#" class="btn_dc" onclick="dzzgxdc();return false;">团员组织关系介绍信</a></li>
						</logic:equal>		
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>信息登记列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
