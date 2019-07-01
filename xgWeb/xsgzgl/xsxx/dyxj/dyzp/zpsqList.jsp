<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/dyxj/dyzp/js/dyzp.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var gridSetting = {
					pager:"pager",
					url:"xsxxDyxjDyzp.do?method=getZpsqList",
					colList:[
					   {label:'key',name:'id', index: 'id',hidden:true,key:true},
					   {label:'学号',name:'xh', index: 'xh',width:'13%',formatter:function(cell,rowObject){
						   return "<a href=\"javascript:cksq('"+rowObject["id"]+"');\" class='name'>"+cell+"</a>";
					   }},
					   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
					   {label:'班级',name:'bjmc', index: 'bjdm'},
					   {label:'学年',name:'xn', index: 'xn'},
					   {label:'学期',name:'xqmc', index: 'xq'},
					   {label:'评议时间',name:'pysj',index:'pysj'},
					   {label:'shzt',name:'shzt',index:'shzt',hidden:true},
					   {label:'splcid',name:'splcid',index:'splcid',hidden:true},
					   {label:'审核状态',name:'shztmc',index:'shztmc'}
					],
					sortname:"pysj",
					sortorder:"desc"
				};

				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function cksq(id){
				showDialog("查看申请表",800,480,"xsxxDyxjDyzp.do?method=cksq&id="+id);
			}

			function exporDyxj(){
				var ids = jQuery("#dataTable").getSeletIds();
			    var rows = jQuery("#dataTable").getSeletRow();
				var len = ids.length;
				if (len == 1) {
				//	if(rows[0]["shzt"] != '1'){
				//		showAlertDivLayer("请选择通过的记录！");
				//		return false;
				//	}
					var url = "xsxxDyxjZpjg.do?method=getZpjgxjb";
					url += "&id=" + ids+"&flag=sq";
					window.open(url);
				} else if (len == 0) {
					showAlertDivLayer("请选择您要下载的记录！");
					return false;
				} else {
					/*
					var flag = true;
					for(var i=0;i<rows.length;i++){
						if(rows[i]["shzt"] != 1){
							flag = false;
							break;
						}
					}
				    if(!flag){
				    	showAlertDivLayer("请选择通过的记录！");
				    	return false;
				    }*/
					var url = "xsxxDyxjZpjg.do?method=getZpjgTy";
					url += "&value=" + ids+"&flag=sq";
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
	
		<html:form action="/xsxxDyxjDyzp" method="post">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<logic:equal name="writeAble" value="yes">	
					<div class="buttonbox">
						<ul>
							<logic:equal value="1" name="cssz" property="sqkg">
								<li>
									<a href="javascript:void(0);" class="btn_zj"
									   onclick="zpsq();return false;" 
									>
									增加
									</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="xgsq();return false;" class="btn_xg"
									>修改</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="qxsq();return false;" class="btn_sc"
									>删除</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc"
										title="对于已填写申请表未提交的记录进行提交申请操作。"
									>提交</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="cancelSubmit();return false;" class="btn_sr"
										 title="对于已提交未审核的记录进行撤销操作。"
									>撤销</a>
								</li>
							</logic:equal>
							<li>
							     <a href="javascript:void(0);" onclick="lcgz();return false;" 
								   title="选中一条记录，点击该按钮可以查看审核流程。"
								   class="btn_cs">流程跟踪</a>
						     </li>
							<li><a href="javascript:void(0);" onclick="exporDyxj();" class="btn_dy">德育小结</a></li>	
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
				<span>德育自评申请列表  </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
