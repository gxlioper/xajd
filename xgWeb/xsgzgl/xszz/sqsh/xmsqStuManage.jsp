<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/sqsh/js/sqsh.js"></script>
		<script type="text/javascript">
		function jesfkt(cellValue,rowObject){
			var jesfxssq=rowObject.jesfxssq;
			var value='';
			if(jesfxssq=='1'){
				value=cellValue+"<font color='red'>(调)</font>";
			}else{
				value=cellValue;
			}
			return value;
		}
		function limFn(cellVal , rowObj){
						if(cellVal == null)
							return "<font color='green'>无限制条件</font>";
						else{
							var html = "";
							for(i = 0 ; i < cellVal.length ; i++ ){
								var item = cellVal[i];
								var result = item.result;
								var msg = item.sqts;
								if(result == 'true'){
									var htmli = "<img src='images/ico_38.gif' title='符合条件' /> " + (i + 1) + "、" + msg + "<br/>";
									html += htmli;
								}else{
									var htmli = "<img src='images/ico_39.gif'name='faidImg'  title='不符合条件' /> " + (i + 1) + "、" + msg + "<br/>";
									html += htmli;
								}
							}
							return html;
						}

				}
			var gridSetting1 = {
					caption:"资助项目申请列表 ",
					pager:"pager",
					url:"xszz_sqsh.do?method=xmsqStuManage&type=query&sqType=wsq",
					colList:[
					   {label:'key',name:'xmdm', index: 'xmdm',hidden:true,key:true},
					   {label:'guid',name:'guid', index: 'guid',hidden:true},
					   {label:'项目名称',name:'xmmc', index: 'xmmc',width:'15%',formatter:setXmsm},
					   {label:'项目说明',name:'xmsm', index: 'xmsm',width:'15%',hidden:true},
					   {label:'项目类别',name:'lbmc', index: 'lbmc',width:'12%'},
					   {label:'金额',name:'je', index: 'je',width:'8%',formatter:jesfkt},
					   {label:'是否可选', name:'checkable', index: 'checkable',hidden:true},
					   {label:'限制条件',name:'conditionCheckResult', index: 'conditionCheckResult',width:'42%' , formatter:limFn},
					   {label:'金额是否学生申请',name:'jesfxssq', index:'jesfxssq',hidden:true},
					   {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
					   {label:'shlc',name:'shlc', index: 'shlc',hidden:true},
					   {label:'申请开始时间',name:'sqkssj', index: 'sqkssj',hidden:true},
					   {label:'申请结束时间',name:'sqjssj', index: 'sqjssj',hidden:true},
					   {label:'开关状态',name:'sqkg', index: 'sqkg',hidden:true,formatter:setSqkg},
					   {label:'开关状态',name:'kgzt', index: 'kgzt',width:'10%'},
					   {label:'申请状态',name:'shztmc', index: 'shztmc',width:'10%'}
					],
					params:{shzt:"wsq"},//未申请
					sortname:"sqkg desc,xmmc",
					sortorder:"asc",
					checkboxFormatter:function(rowObj){
						var check = rowObj['checkable'];
						return check == 'false' ? false : true;
						},
					radioselect:true
				};

			var gridSetting2 = {
					caption:"资助项目申请列表 ",
					pager:"pager",
					url:"xszz_sqsh.do?method=xmsqStuManage&type=query&sqType=ysq",
					colList:[
					   {label:'key',name:'guid', index: 'guid',hidden:true,key:true},
					   {label:'学年',name:'xn', index: 'xn',width:'13%'},
					   {label:'学期',name:'xqmc', index: 'xq',width:'10%'},
					   {label:'申请奖项',name:'xmmc', index: 'xmdm',width:'13%',formatter:setXmsm},
					   {label:'项目说明',name:'xmsm', index: 'xmsm',width:'15%',hidden:true},
					   {label:'项目类别',name:'lbmc', index: 'lbmc',width:'12%'},
					   {label:'金额',name:'je', index: 'je',width:'8%',formatter:jesfkt},
					   {label:'金额是否学生申请',name:'jesfxssq', index:'jesfxssq',hidden:true},
					   {label:'申请时间',name:'sqsj', index: 'sqsj',width:'25%'},
					   {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
					   {label:'shlc',name:'shlc', index: 'shlc',hidden:true},
					   {label:'审核状态',name:'shztmc', index: 'shzt',width:'5%'}
					],
					params:{shzt:"ysq"},//已申请
					sortname:"sqsj",
					sortorder:"desc",
					radioselect:true
				};

			/**
			*添加搜索条件
			**/
			function query(){
				var map = {};
				map["lbmc"]= jQuery("#lbmc").val();
				jQuery("#dataTable").reloadGrid(map);
			}

			/*
			 *申请开关 
			 */
			function setSqkg(cellValue,rowObject){
				
				var xmdm = rowObject.xmdm;
				var value = "关闭申请";
				var status = '0';
				var color;
				if(cellValue == '1'){
					var currDate = jQuery("#currDate").val();
					if((rowObject.sqkssj ==null || currDate >= rowObject.sqkssj) && (rowObject.sqjssj==null || currDate <= rowObject.sqjssj) ){
						value = "开放申请";
						status = '1';
					}
				}
				rowObject.sqkg=status;
				return value;
			}

			/**
			 *奖项项目说明
			 */
			function setXmsm(cellValue,rowObject){

				if(rowObject['xmsm'] == null) {
					var value = "<a title=''>"+cellValue+"</a>";
				}else {
					var xmsm = rowObject["xmsm"];
					xmsm = xmsm.replaceAll("<br/>","\n");
					var value = "<a title='"+xmsm+"'>"+cellValue+"</a>";
				}
		
				return value;	
				
			}
			
			/**
			 * 切换tab页
			 * @param obj
			 * @param shzt
			 * @return
			 */
			function selectTab(obj,shzt){
				jQuery("#shzt").val(shzt);

				if (shzt == "wsq"){
					jQuery("#li_sq").css("display","");
					jQuery("#li_xg").css("display","");
					jQuery("#li_sc").css("display","");
					jQuery("#li_tj").css("display","");
					jQuery("#li_ts").css("display","");

					jQuery("#li_cx").css("display","none");
					jQuery("#li_lc").css("display","none");
					jQuery("#li_xz").css("display","none");
					
					jQuery("#dataTable").initGrid(gridSetting1);
				} else {

					jQuery("#li_sq").css("display","none");
					jQuery("#li_xg").css("display","none");
					jQuery("#li_sc").css("display","none");
					jQuery("#li_tj").css("display","none");
					jQuery("#li_ts").css("display","none");
					
					jQuery("#li_cx").css("display","");
					jQuery("#li_lc").css("display","");
					jQuery("#li_xz").css("display","");
					
					jQuery("#dataTable").initGrid(gridSetting2);
				}
				
				jQuery(".ha").removeClass("ha");
				jQuery(obj).parent().addClass("ha");
			}


			/**
			 ********初始化。。。
			*/
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting1);
			});


			/**
			 * 修改申请表
			 * @returns {Boolean}
			 */
			function xgSqbStu(){
					
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					showAlertDivLayer("请选择一条您要取消的记录！");
				} else {
					if(rows[0]["guid"] == '' || rows[0]["guid"] == null){
						showAlertDivLayer("该项目未填写申请，不能修改，请先填写！");
						return false;
					}
					showDialog("资助项目修改",715,393,"xszz_sqsh.do?method=updateXmsqStu&guid="+rows[0]["guid"]);
				}
			}
				

			/**
			 * 填写申请表
			 */
			function xmsqStu(){

				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length == 0){
					showAlertDivLayer("请至少选择一个申请项目！");
					return false;
				}

				for ( var i = 0; i < rows.length; i++) {
					if(rows[i]["sqkg"]!="1"){
						showAlertDivLayer("所选项目申请状态未开放，请重新选择");
						return false;
					}
					if(rows[i]["guid"] != '' && rows[i]["guid"] != null){
						showAlertDivLayer("所选项目存在已填写未提交或已退回记录，请确认！");
						return false;
					}
				}
				
				
				//兼得检查
				var selectedIds = jQuery("#dataTable").getSeletIds();
				jQuery.post("xszz_sqsh.do?method=xszzXmsqChkJdStu" , 
					{
						xmdmids : selectedIds.join(',')
					} , 
					function(data){
						var isSuccess = data['success'];
						var msg = data['message'];
						if(isSuccess === 'true'){
							var xxdm = jQuery("#xxdm").val();
							if(xxdm != '10335'){
								showDialog("资助项目申请",715,450,"xszz_sqsh.do?method=xszzXmsqStu");
							}else{
								showDialog("项目说明",500,350,"xszz_sqsh.do?method=showXmxx_10335&xmdm="+selectedIds);
							}
								
							}else{
								showAlertDivLayer(msg);
							}
						} ,
					"json");
				//兼得检查
			}	
		</script>
	</head>

	<body>
		<input type="hidden" name="currDate" id="currDate" value="${currDate}"/>
		<input type="hidden" id="SFBJPY_Y" value="${SFBJPY_Y }"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
	
		<html:form action="/xszz_sqsh" method="post" styleId="zcxmForm">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				
					<div class="buttonbox">
						<ul>
							<logic:equal name="writeAble" value="yes">	
								<li id="li_sq">
									<a href="javascript:void(0);" class="btn_zj"
									   onclick="xmsqStu();return false;" 
									   title="点击该按钮，打开申请表填写页面。">
									申请
									</a>
								</li>
								<li id="li_xg" >
									<a href="javascript:void(0);" onclick="xgSqbStu();return false;" class="btn_xg"
							   			title="只能修改未审核或退回的记录，已被在审核的不能删除。"
										>修改</a>
								</li>
								
								<li id="li_sc" >
									<a href="javascript:void(0);" onclick="xmsqDeleteStu();return false;" class="btn_sc"
							   			title="只能取消未审核或退回的记录，已被在审核的不能取消。"
										>删除</a>
								</li>
								
								<li id="li_tj" >
									<a href="javascript:void(0);" onclick="submitBusiStu();return false;" class="btn_shuc"
							   			title="只能取消未审核或退回的记录，已被在审核的不能取消。"
										>提交</a>
								</li>
								
								<li id="li_cx" style="display: none;">
									<a href="javascript:void(0);" onclick="cancle();return false;" class="btn_sr"
							   			title="只能取消未审核或退回的记录，已被在审核的不能取消。"
										>撤销</a>
								</li>
						</logic:equal>
								<li id="li_lc" style="display: none;">
									<a href="javascript:void(0);" onclick="xmsqLcgz();return false;" 
							   			title="选中一条记录，点击该按钮可以查看审核流程。"
							   			class="btn_cs">流程跟踪</a>
							   	</li>	

								<li id="li_xz" style="display: none;">
									<a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">下载登记表</a>
								</li>
								
							<logic:equal name="xxdm" value="10530">	
								<li><a href="javascript:void(0);" onclick="printSqlct();return false;" class="btn_down">申请流程图</a></li>
							</logic:equal>	
						</ul>
					</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchAreaStu.jsp"%>
				<!-- 过滤条件 end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'wsq');"><span>未申请项目</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ysq');"><span>已申请项目</span></a></li>
			      </ul>
			    </div>
			</div>
			<div class="searchtab" >
			<!-- 过滤条件 -->
				<table width="100%" border="0">
					<tr>
						<th width="10%">项目类别</th>
						<td>
							<html:select property="lbmc" styleId="lbmc" style="width:150px;">
							<html:option value=""></html:option>
							<html:options collection="xmlbList" property="mc"
								labelProperty="mc" />
							</html:select>
						</td>
						<td>
							<div class="btn">
								<button type="button" class="btn_cx" id="search_go" onclick="query()">
									查 询
								</button>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span><li id="li_ts" ><font color="blue">${xnxqmc}</font> </li>资助项目列表 </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>

		</div>
	</body>
</html>
