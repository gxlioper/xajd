<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/jjgl/xqsh/script/xqsh.js"></script>
		<script type="text/javascript" src="xsgzgl/jjgl/xqwh/js/xqwh.js"></script>
		<script type="text/javascript">
			var gridSetting = {
					caption:"家教需求列表",
					pager:"pager",
					multiselect:true,
					rowNum:20,
					url:"jjgl_xqwhgl.do?method=queryXqList&type=0",
					colList:[
					   {label:'家教编号',name:'xqid', index: 'xqid',key:true,formatter:xqidLink},
					   {label:'家教科目',name:'jjxk', index: 'jjxk'},
					   {label:'家教年级',name:'jjnj', index: 'jjnj'},
					   {label:'家教地点',name:'jjdd', index: 'jjdd'},
					   {label:'登记人',name:'djr', index: 'djr'},
					   {label:'登记日期',name:'sqsj', index: 'sqsj'},
					   {label:'状态',name:'jjztmc', index: 'jjztmc'},
					   {label:'申请信息',name:'xssqid', index: 'xssqid', formatter:sqrLink}
					],
					sortname: "xqid",
					sortorder: "desc"
				};

			function tsLink(v,r){
				//无投诉
				if(v === '0'){
						return "暂无";
				}else{
					var onclickfn = "onclick=\"" + "showDialog('家教投诉信息' , 720 , 450 , 'jjgl_xqwhgl.do?method=viewTsxx&xqid=" + r['xqid'] + "')" + "\"";
					
					var href = "href = 'javascript:void(0);'";

					var el = "<a " + href + " class='name' " + onclickfn + " >有投诉(" + v + ")</a>";
					
					return el;
				}
			}

			function pjLink(v,r){
				//无评价
				if(v === '0'){
						return "未评价";
				}else{
					var onclickfn = "onclick=\"" + "showDialog('家教评价信息' , 600 , 280 , 'jjgl_xqwhgl.do?method=viewPjxx&xqid=" + r['xqid'] + "')" + "\"";
					
					var href = "href = 'javascript:void(0);'";

					var el = "<a " + href + " class='name' " + onclickfn + " >已评价</a>";
					
					return el;
				}
			}
			
			function sqrLink(v,r){
				
				if(v == null){
						return "无";
				}else{
					var onclickfn = "onclick=\"" + "showDialog('家教学生申请信息' , 720 , 450 , 'jjgl_xqwhgl.do?method=assign&xqid=" + r['xqid'] + "')" + "\"";
					
					var href = "href = 'javascript:void(0);'";

					var el = "<a " + href + " class='name' " + onclickfn + " >已申请</a>";
					
					return el;
				}
			}

            function xqidLink(v,r){

                return "<a class='name' href='javascript:void(0);' onclick='ck("+v+")'>"+v+"</a>";
            }

			
			function fbrLink(v,r){
				var onclickfn = "onclick=\"" + "showDialog('家教发布人信息' , 720 , 450 , 'jjgl_zcyhgl.do?method=viewZcyh&yhm=" + r['sqr'] + "')" + "\"";
				
				var href = "href = 'javascript:void(0);'";

				var el = "<a " + href + " class='name' " + onclickfn + " >" + v + "</a>";
				
				return el;
			}

			function jjlsxmLink(v,r) {
			    v = v||"";
                return "<a class='name' onclick='showDialog(\"家教老师信息\",700,500,\"jjgl_jjzg.do?method=jjzgView&xh="+r["jjlsxh"]+"\")'>"+v+"</a>";
            }
			
			function searchRs(){
				var map = {};
				map["xqid"] = jQuery("#xqid").val();
				map["jjlsxm"] = jQuery("#jjlsxm").val();
				map["jjxk"] = jQuery("#jjxk").val();
				map["jjnj"] = jQuery("#jjnj").val();
                map["sfypjj"] = jQuery("#sfypjj").val();
                map["sfyjxys"] = jQuery("#sfyjxys").val();
                jQuery("#dataTable").reloadGrid(map);
			}

			
			
			/**
			 * 页签切换
			 * @return
			 */
			function selectTab(obj,query_type){
				
				gridSetting['url'] =  "jjgl_xqwhgl.do?method=queryXqList&type=" + query_type;
				//家教招聘列表
				if(query_type == "0"){

                    jQuery("#zjLinkLi").css("display","");
                    jQuery("#xgLinkLi").css("display","");
                    jQuery("#scLinkLi").css("display","");
					jQuery("#xqfplsLinkLi").css("display","");
					jQuery("#xqztxgLinkLi").css("display","");
                    jQuery("#sjycLinkLi").css("display","none");
                    jQuery("#xysxzLinkLi").css("display","none");
                    jQuery("#sf").css("display","none");
                    gridSetting['colList'] = [
					   					   {label:'家教编号',name:'xqid', index: 'xqid',key:true,formatter:xqidLink},
										   {label:'家教科目',name:'jjxk', index: 'jjxk'},
										   {label:'家教年级',name:'jjnj', index: 'jjnj'},
										   {label:'家教地点',name:'jjdd', index: 'jjdd'},
										   {label:'登记人',name:'djr', index: 'djr'},
										   {label:'登记日期',name:'sqsj', index: 'sqsj'},
										   {label:'状态',name:'jjztmc', index: 'jjztmc'}
										   /*{label:'申请信息',name:'xssqid', index: 'xssqid', formatter:sqrLink}*/
										];
				//已派家教列表
				} else if(query_type == "1"){
                    jQuery("#zjLinkLi").css("display","none");
                    jQuery("#xgLinkLi").css("display","none");
                    jQuery("#scLinkLi").css("display","none");
					jQuery("#xqfplsLinkLi").css("display","none");
					jQuery("#xqztxgLinkLi").css("display","");
                    jQuery("#sjycLinkLi").css("display","none");
                    jQuery("#xysxzLinkLi").css("display","none");
                    jQuery("#sf").css("display","none");
                    gridSetting['colList'] = [
											{label:'家教编号',name:'xqid', index: 'xqid',key:true,formatter:xqidLink},
											{label:'家教科目',name:'jjxk', index: 'jjxk'},
											{label:'家教年级',name:'jjnj', index: 'jjnj'},
											{label:'家教地点',name:'jjdd', index: 'jjdd'},
											{label:'登记人',name:'djr', index: 'djr'},
											{label:'登记日期',name:'sqsj', index: 'sqsj'},
											{label:'家教老师学号',name:'jjlsxh', index: 'jjlsxh',hidden:true},
											{label:'家教老师',name:'jjlsxm', index: 'jjlsxm',formatter:jjlsxmLink},
											{label:'派出时间',name:'pcsj', index: 'pcsj'}
										];
				//超期家教列表
				}else if(query_type == "2"){
                    jQuery("#zjLinkLi").css("display","none");
                    jQuery("#xgLinkLi").css("display","none");
                    jQuery("#scLinkLi").css("display","none");
					jQuery("#xqfplsLinkLi").css("display","none");
					jQuery("#xqztxgLinkLi").css("display","");
                    jQuery("#sjycLinkLi").css("display","");
                    jQuery("#xysxzLinkLi").css("display","none");
                    jQuery("#sf").css("display","none");
                    gridSetting['colList'] = [
												{label:'家教编号',name:'xqid', index: 'xqid',key:true,formatter:xqidLink},
												{label:'家教科目',name:'jjxk', index: 'jjxk'},
												{label:'家教年级',name:'jjnj', index: 'jjnj'},
												{label:'家教地点',name:'jjdd', index: 'jjdd'},
												{label:'登记人',name:'djr', index: 'djr'},
												{label:'登记日期',name:'sqsj', index: 'sqsj'},
                        						{label:'家教老师学号',name:'jjlsxh', index: 'jjlsxh',hidden:true},
												{label:'家教老师',name:'jjlsxm', index: 'jjlsxm',formatter:jjlsxmLink},
												{label:'派出时间',name:'pcsj', index: 'pcsj'}
											];
				//已交协议书列表
				}else if(query_type == "3"){
                    jQuery("#zjLinkLi").css("display","none");
                    jQuery("#xgLinkLi").css("display","none");
                    jQuery("#scLinkLi").css("display","none");
					jQuery("#xqfplsLinkLi").css("display","none");
					jQuery("#xqztxgLinkLi").css("display","");
                    jQuery("#sjycLinkLi").css("display","none");
                    jQuery("#xysxzLinkLi").css("display","");
                    jQuery("#sf").css("display","none");
                    gridSetting['colList'] = [
												{label:'家教编号',name:'xqid', index: 'xqid',key:true,formatter:xqidLink},
												{label:'家教科目',name:'jjxk', index: 'jjxk'},
												{label:'家教年级',name:'jjnj', index: 'jjnj'},
												{label:'家教地点',name:'jjdd', index: 'jjdd'},
												{label:'登记人',name:'djr', index: 'djr'},
												{label:'登记日期',name:'sqsj', index: 'sqsj'},
                        						{label:'家教老师学号',name:'jjlsxh', index: 'jjlsxh',hidden:true},
												{label:'家教老师',name:'jjlsxm', index: 'jjlsxm',formatter:jjlsxmLink},
												{label:'交协议书时间',name:'jxyssj', index: 'jxyssj'}
											];
				//已关闭家教列表
				}else if(query_type == "4"){
                    jQuery("#zjLinkLi").css("display","none");
                    jQuery("#xgLinkLi").css("display","none");
                    jQuery("#scLinkLi").css("display","none");
                    jQuery("#xqfplsLinkLi").css("display","none");
                    jQuery("#xqztxgLinkLi").css("display","");
                    jQuery("#sjycLinkLi").css("display","none");
                    jQuery("#xysxzLinkLi").css("display","");
                    jQuery("#sf").css("display","");
                    gridSetting['colList'] = [
                        {label:'家教编号',name:'xqid', index: 'xqid',key:true,formatter:xqidLink},
                        {label:'家教科目',name:'jjxk', index: 'jjxk'},
                        {label:'家教年级',name:'jjnj', index: 'jjnj'},
                        {label:'家教地点',name:'jjdd', index: 'jjdd'},
                        {label:'登记人',name:'djr', index: 'djr'},
                        {label:'登记日期',name:'sqsj', index: 'sqsj'},
                        {label:'家教老师学号',name:'jjlsxh', index: 'jjlsxh',hidden:true},
                        {label:'家教老师',name:'jjlsxm', index: 'jjlsxm',formatter:jjlsxmLink},
                        {label:'是否已派家教',name:'sfypjj', index: 'sfypjj'},
                        {label:'是否已交协议书',name:'sfyjxys', index: 'sfyjxys'},
                        {label:'关闭时间',name:'gbsj', index: 'gbsj'}
                    ];
                }
				jQuery("#dataTable").initGrid(gridSetting);
				jQuery(".ha").removeClass("ha");
				jQuery(obj).parent().addClass("ha");
			}

			/**
			*分配家教老师
			**/
			function xqfpls(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					showAlertDivLayer("请选择一条记录！");
				} else {
					var url = "jjgl_xqwhgl.do?method=assignManual&xqid="+jQuery("#dataTable").getSeletIds()[0];
					var title = "分配家教老师";
					showDialog(title,850,570,url);
				}
			}

			/**
			*查看
			**/
			function ck(value){

				var url = "jjgl_xqwhgl.do?method=ck&xqid="+value;
				var title = "查看需求信息";
				showDialog(title,800,400,url);
			}
			
			/**
			*修改状态
			**/
			function xqztxg(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					showAlertDivLayer("请选择一条您要修改的记录！");
				} else {
					var url = "jjgl_xqwhgl.do?method=changeJJzt&xqid="+jQuery("#dataTable").getSeletIds()[0];
					var title = "家教状态修改";
					showDialog(title,680,350,url);
				}
			}

			function xz() {
                var url = "jjgl_xqwhgl.do?method=downXys"
                window.open(url);
            }

			
			/**
			*初始化
			*/
			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			/**
			*重新加载数据
			*/
			function reloadWindow(){
				jQuery("#dataTable").reloadGrid();
			}

            /**
			 * 增加家教信息
             */
            function zj() {
                var url = "jjgl_xqwhgl.do?method=xqwhAdd";
                var title = "家教信息增加";
                showDialog(title, 800, 550, url);
            }

            /**
			 * 修改家教信息
             */
            function xg() {
                var rows = jQuery("#dataTable").getSeletRow();
                if (rows.length != 1) {
                    showAlertDivLayer("请选择一条您要修改的记录！");
                } else {
                    var url = 'jjgl_xqwhgl.do?method=xqwhEdit&xqid=' + rows[0]["xqid"];
                    var title = "家教信息修改";
                    showDialog(title, 800, 550, url);
                }
            }

            /**
			 * 删除家教信息
             */
            function sc() {
                var ids = jQuery("#dataTable").getSeletIds();
                if (ids.length == 0) {
                    showAlertDivLayer("请选择您要删除的记录！");
                    return false;
                }

                var rows = jQuery("#dataTable").getSeletRow();
                for(var i=0;i<rows.length;i++){

                    /*if(rows[i]["jjzt"] != '0' || rows[i]["xssqid"] != '4'){
                        showAlertDivLayer("只能删除未派出或已关闭的家教信息！");
                        return false;
					}*/

                    if (rows[i]["xssqid"] != null) {
                        showAlertDivLayer("只能删除未被申请的家教信息！");
                        return false;
                    }
                }

                showConfirmDivLayer("您确定要删除选择的记录吗？", {
                    "okFun" : function() {
                        jQuery.post("jjgl_xqwhgl.do?method=xqwhDel", {
                                values : ids.toString()
                            },
                            function(data) {
                                showAlertDivLayer(data["message"]);
                                jQuery("#dataTable").reloadGrid();
                            }, 'json');
                    }
                });
            }
		</script>
	</head>
	<body>
		<button id="search_go" type="button" style="display:none" onclick="reloadWindow();"></button>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<div class="toolbox">
			<!-- 过滤条件 -->
			<div class="searchtab">
				<html:form action="/jjgl_xqwhgl" method="post" styleId="jjglXqwhForm">
					<!-- 按钮 -->
					<div class="buttonbox">
						<ul>
							<li id="zjLinkLi"><a href="javascript:void(0);" onclick="zj();" class="btn_zj" id="zjLink">增加</a></li>
							<li id="xgLinkLi"><a href="javascript:void(0);" onclick="xg();" class="btn_xg" id="xgLink">修改</a></li>
							<li id="scLinkLi"><a href="javascript:void(0);" onclick="sc();" class="btn_sc" id="scLink">删除</a></li>
							<li id="xqfplsLinkLi"><a href="javascript:void(0);" onclick="xqfpls();" class="btn_csh" id="xqfplsLink">指派老师</a></li>
							<li id="xqztxgLinkLi"><a href="javascript:void(0);" onclick="xqztxg();" class="btn_xg" id="xqztxgLink">家教操作</a></li>
							<li id="sjycLinkLi" style="display:none"><a href="javascript:void(0);" onclick="sjyc();" class="btn_xg" id="sjycLink">试教延长</a></li>
							<li id="dcLinkLi"><a href="javascript:void(0);" onclick="dc();" class="btn_dc" id="dcLink">导出</a></li>
							<li id="xysxzLinkLi" style="display:none"><a href="javascript:void(0);" onclick="xz();" class="btn_dy" id="xysxzLink">协议书下载</a></li>
						</ul>
					</div>
					<div class="comp_title" id="comp_title">
				      <ul style="width:90%" id="tabUl">
				      	<li class="ha" >
				      		<a href="javascript:void(0);" onclick="selectTab(this,'0');"><span>家教招聘列表</span></a>
				      	</li>
				      	<li>
				      		<a href="javascript:void(0);" onclick="selectTab(this,'1');"><span>已派家教列表</span></a>
				      	</li>
						<li ><a href="javascript:void(0);" onclick="selectTab(this,'2');"><span>超期家教列表</span></a></li>
						
						<li ><a href="javascript:void(0);" onclick="selectTab(this,'3');"><span>已交协议书列表</span></a></li>
						<li ><a href="javascript:void(0);" onclick="selectTab(this,'4');"><span>已关闭家教列表</span></a></li>
				      </ul>
				    </div>
					
					<table width="100%" border="0">
						<tr>
							<th width="10%">家教编号</th>
							<td>
								<html:text property="xqid" styleId="xqid" ></html:text>
							</td>
							<th width="10%">家教老师</th>
							<td>
								<html:text property="jjlsxm" styleId="jjlsxm" ></html:text>
							</td>
						</tr>
						<tr>
						<th width="10%">家教科目</th>
							<td>
								<html:select property="jjxk" styleId="jjxk" style="width:173px">
					    			<html:option value=""></html:option>
					    			<html:options collection="jjxkList" property="jjxk" labelProperty="jjxk"/>
					    		</html:select>
							</td>
							<th width="10%">年级</th>
							<td>
								<html:select property="jjnj" styleId="jjnj" style="width:173px">
					    			<html:option value=""></html:option>
					    			<html:options collection="jjnjList" property="jjnj" labelProperty="jjnj"/>
					    		</html:select>
							</td>
						</tr>
						<tr id="sf" style="display: none">
							<th width="10%">是否已派家教</th>
							<td>
								<html:select property="sfypjj" styleId="sfypjj">
									<html:option value=""></html:option>
									<html:option value="是">是</html:option>
									<html:option value="否">否</html:option>
								</html:select>
							</td>
							<th width="10%">是否已交协议书</th>
							<td>
								<html:select property="sfyjxys" styleId="sfyjxys">
									<html:option value=""></html:option>
									<html:option value="是">是</html:option>
									<html:option value="否">否</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" class="btn_cx" id="search_go" onclick="searchRs()">
										查 询
									</button>
								</div>
							</td>
						</tr>
					</table>
				</html:form>
			</div>
		</div>
		<div class="formbox">
			<div>
				<h3 class="datetitle_01">
					<span> 
						需求列表
					</span>
				</h3>
			</div>
			<table id="dataTable"></table>
		</div>
		<div id="pager"></div>
		
	</body>
</html>
