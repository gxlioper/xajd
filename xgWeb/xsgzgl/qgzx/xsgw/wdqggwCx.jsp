<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			var gridSetting1 = {
					caption:"我的岗位列表",
					radioselect:true,
					pager:"pager",
					url:"qgzx_wdgwsq.do?method=wdqggwCx&type=query",
					colList:[
                        {label:'学年',name:'xn', index: 'xn',width:'7%'},
					   {label:'岗位代码',name:'gwdm', index: 'gwdm',hidden:true},
                        {label:'岗位名称',name:'gwmc', index: 'gwmc',width:'10%'},
                        {label:'工作性质',name:'gwxzdm', index: 'gwxzdm',width:'8%',formatter:function(value,row){
                            if(value=="0"){
                                return "临时";
                            } else {
                                return "正式";
                            }
						}},
					   {label:'用人单位',name:'yrdwmc', index: 'yrdwmc',width:'15%'},
					   {label:'单位类别',name:'dwlb', index: 'dwlb',width:'8%',formatter:function(value,row){
					       if(value=="01"){
					           return "校内单位";
						   } else {
                               return "校外单位";
						   }
					   }},
                        {label:'录用时间',name:'sgsj', index: 'sgsj',width:'12%'},
					   {label:'在岗状态',name:'zgztmc', index: 'zgztmc',width:'8%'},
					   {label:'总酬金(元)',name:'zcj', index: 'zcj',width:'8%'},
                        {label:'申请编号',name:'sqbh', index: 'sqbh',width:'8%',hidden:true}
					],
					sortname: "gwmc",
				 	sortorder: "desc",

					};

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting1);
			});

			function lzsq(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					showAlertDivLayer("请选择一条记录！");
					return false;
				}

				if(rows[0]["sqbh"] != "" && rows[0]["sqbh"] != null){
                    showAlertDivLayer("已申请，请勿重复提交！");
                    return false;
				}
				showDialog("离职申请",700,400,"qgzx_wdgwsq.do?method=lzsq&gwdm="+rows[0]["gwdm"]);
			}

            function gwmx(){
                var rows = jQuery("#dataTable").getSeletRow();
                if (rows.length != 1){
                    showAlertDivLayer("请选择一条记录！");
                    return false;
                }

                showDialog("岗位明细",700,400,"qgzx_wdgwsq.do?method=gwmx&gwdm="+rows[0]["gwdm"]);
            }

            function gzmx(){
                var rows = jQuery("#dataTable").getSeletRow();
                if (rows.length != 1){
                    showAlertDivLayer("请选择一条记录！");
                    return false;
                }

                showDialog("岗位明细",700,400,"qgzx_wdgwsq.do?method=gzmx&gwdm="+rows[0]["gwdm"]);
            }

		</script>
	</head>

	<body>
		<input type="hidden" name="curXn" id="curXn" value="${curXn}"/>
		<input type="hidden" name="isQgzxStu" id="isQgzxStu" value="${isQgzxStu}"/>
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
		
		<input type="hidden" id="xssqkg" value="${cssz.xssqkg }" />
		<html:form action="/qgzx_wdgwsq" method="post" styleId="wdgwsqForm">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
					<div class="buttonbox">
						<ul>
							<logic:equal name="writeAble" value="yes">
								<li><a href="javascript:void(0);" id="btn_sc" onclick="gwmx();return false;" class="btn_ck">岗位明细</a></li>
								<li><a href="javascript:void(0);" id="btn_xzxys" class="btn_xg" onclick="lzsq();return false;">离职申请</a></li>
								<li><a href="javascript:void(0);" onclick="gzmx();return false;" class="btn_ck">工资明细</a></li>
							</logic:equal>
						</ul>

					</div>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchAreaStu.jsp"%>
				<!-- 过滤条件 end-->

			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>我的岗位列表</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>

		</div>
	</body>
</html>
