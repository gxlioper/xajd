<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="pjpy/jtpj/jtpjsh/js/jtpjsh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/shlc/js/splc.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
				<script type="text/javascript">
					jQuery(function(){
						var map = getSuperSearch();
						map["shzt"]="dsh";
						gridSetting["params"] = map;
						jQuery("#dataTable").initGrid(gridSetting);
						jQuery("#btn_qxsh").click(function (){
							var rows = jQuery("#dataTable").getSeletRow();
							if (rows.length != 1){
								showAlertDivLayer("请选择一条您要撤消的审核记录！");
								return false;
							}
							var obj=new Object(0);
							obj["data"]={splc:"splcid",sfkq:"1"};
							cxshnew_splc(obj);
						});
					});

					//南京高等职业技术学校
					function jtpjmddc(){
						setSearchTj();
						var xn_num = jQuery("a[name=a_name_xn]").length;
						var xq_num = jQuery("a[name=a_name_xq]").length;
						var xy_num = jQuery("a[name=a_name_xy]").length;
						var jxmc_num = jQuery("a[name=a_name_jxmc]").length;

						if(xn_num != 1){
							showAlertDivLayer("必须选择一个学年，且只能选择一个！");
						}else if (xq_num != 1){
							showAlertDivLayer("必须选择一个学期，且只能选择一个！ ");
						}else if (jxmc_num != 1){
							showAlertDivLayer("必须选择一个奖项名称，且只能选择一个！ ");
						}else{
							var flg = true;
							var yzUrl = 'jtpjsh.do?method=yzjtpjmddc';
							jQuery("form").eq(0).attr("id","jtpjshForm");
							
							jQuery.ajaxSetup({async:false});
								ajaxSubFormWithFun("jtpjshForm", yzUrl, function(data) {
									if(data["result"] != true){
										flg = false;
									}
								});
								if(!flg){
									showAlertDivLayer("无集体评奖班级可以导出，请另外选择过滤条件！ ");
									return false;
								}
							jQuery.ajaxSetup({async:true});
							
							var url = "jtpjsh.do?method=jtpjmddc";
							url = addSuperSearchParams(url);//设置高级查询参数	
							jQuery("form").eq(0).attr("action", url);
							jQuery("form").eq(0).submit();
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
	<html:form action="/qjsh?method=list&type=query">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<input type="hidden" value="dsh" id="shzt"/>
		<input type="hidden"  name="query" id="query" value="${query}"/>
		<input type="hidden" name="cancelPath" id="cancelPath" value="jtpjsh.do?method=cancelSh"/>
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<logic:equal name="writeAble" value="yes">	
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="jtpjsh();return false;" 
							   title="选中您要审核的记录，点击该按钮可以打开审核页面。"
							   class="btn_sh">审核</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" id="btn_qxsh"
							   title="选中一条记录，点击该按钮您可以撤消失误的审核操作。"
							   class="btn_qxsh">撤消</a>
						</li>
					</logic:equal>						
					<li><a href="javascript:void(0);" onclick="rcxwshLcinfo();return false;" 
						   title="选中一条记录，点击该按钮可以查看审核流程。"
						   class="btn_cs">流程跟踪</a></li>
					<logic:equal value="10874" name="xxdm">
						<li>
							<a href="#" class="btn_down" onclick="jtpjmddc();return false;">班级评奖审批表下载</a>
						</li>
					</logic:equal>	
				</ul>
			</div>
			<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			<!-- 过滤条件 end-->
			<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dsh');"><span>待处理</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ysh');"><span>已处理</span></a></li>
			      </ul>
			</div>
		</div>
		</html:form>
		<div class="toolbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span id="title"> 集体评奖待审核列表 </span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
