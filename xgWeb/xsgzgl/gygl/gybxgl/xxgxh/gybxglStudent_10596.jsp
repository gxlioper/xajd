<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：zhangh -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%--<%@ include file="/syscommon/pagehead_V4.ini"%>
		--%><%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="dwr/interface/getCommGygl.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>

		<script type='text/javascript' src="js/xgutil.js"></script>
		<script language="javascript" defer="defer">
		//查询结果集
		function searchRs(){
			allNotEmpThenGo('gyglnew_gybxgl_gybxgl_stu.do');
		}

		function add(){
			jQuery.post('gyglnew_gybxgl.do?method=viewXsxx',{},function(data){
				var lddm = data.lddm;
	
				if(!lddm){
					alertError("您尚未入住公寓寝室，无法提交公寓报修申请！");
				}else{
					//showTopWin("gyglnew_gybxgl.do?method=gybxglAdd",800,600);
					showDialog("", 760, 400, "gyglnew_gybxgl.do?method=gybxglAdd");
				}					
			},'json');
		}
		
		// 修改操作
		function update(url){
			var size = jQuery("input[type='checkbox'][name='primarykey_checkVal']:checked").size();
			
			if(size != 1){
				alertInfo('请选择一行要操作记录！');
				return false;
			}

			var flag = false;
			
			jQuery("td:has(input[type='checkbox'][name='primarykey_checkVal']:checked) > input[id*='pjzt_']").each(function(){
				if("未处理" != jQuery(this).val()){
					flag = true;
				}
			});
			
			if(flag){
				alertInfo("您只可以对处理状态为'未处理'的信息进行评价操作，请重新选择！！");
			}else{
				url += "&pk="+curr_row.getElementsByTagName('input')[0].value;
				url += "&xh="+curr_row.getElementsByTagName('input')[1].value;
				showTopWin(url,800,600);
			}
		}	
		
		// 评价操作
		function pjUpdate(){
			var size = jQuery("input[type='checkbox'][name='primarykey_checkVal']:checked").size();
			
			if(size == 0){
				alertInfo('请选择要操作记录！');
				return false;
			}
			
			var flag = false;
			
			jQuery("td:has(input[type='checkbox'][name='primarykey_checkVal']:checked) > input[id*='pjzt_']").each(function(){
				if("已处理" != jQuery(this).val()){
					flag = true;
				}
			});
			
			if(flag){
				alertInfo("您只可以对处理状态为'已处理'或'不处理'的信息进行评价操作，请重新选择！！");
			}else{
				tipsWindown("满意程度调查","id:tempDiv","400","230","true","","true","id");
			}
		}
		
		// 删除操作
		function delBatch(){
			var size = jQuery("input[type='checkbox'][name='primarykey_checkVal']:checked").size();
			
			if(size == 0){
				alertInfo('请选择要操作记录！');
				return false;
			}
			
			var flag = false;
			
			jQuery("td:has(input[type='checkbox'][name='primarykey_checkVal']:checked) > input[id*='pjzt_']").each(function(){
				if("未处理" != jQuery(this).val()){
					flag = true;
				}
			});
			
			if(flag){
				alertInfo("您只可以对处理状态为'未处理'的信息进行删除操作，请重新选择！");
			}else{
				batchData('primarykey_checkVal','gyglnew_gybxgl.do?method=gybxglStudent&doType=del','del');
			}
		}
		</script>
		<style type="text/css">
			.leftMargin{margin-left: 25px;}
		</style>
	</head>
	<body>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>			
		<!-- 标题 end-->
		<!-- 提示信息 START-->
		<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>保修须知：</span>
				</h3><br/>
				<p>
					一、报修内容每次只能填报水、电、木、泥其中的一项，不能一单多报，如出现多处需维修的请按类别分单报修。如水工： 水龙头漏水。<br/>
					<span class="leftMargin">水电木泥分工如下：</span><br/>
					<span class="leftMargin">①水工维修内容：水龙头、水管漏水、水表、冲水阀等。</span><br/>
					<span class="leftMargin">②电工维修内容：灯管、节能灯、电表、电短路、电源插座、开关电风扇、保险丝、空开等。</span><br/>
					<span class="leftMargin">③木工维修内容：床、桌子、椅子、门、窗、柜子、锁、门扣、玻璃、油漆等。</span><br/>
					<span class="leftMargin">④泥工维修内容：天花板漏水、下水道堵塞、瓷砖、墙面等</span><br/>
					二、1、热水控制系统（即热水表）维修请在公寓值班室填报维修单。<br/>
					<span class="leftMargin">2、网络接口维修请到现代技术教育中心填报维修单。</span><br/>
					<span class="leftMargin">3、饮水机的维修与更换请与送水工联系。</span><br/>
					三、凡属水管爆裂、电路短路、保险被烧、下水道堵塞及其他应急的维修，均属急修，急修上班时间可以直接拨打报修电话：<br/>
					<span class="leftMargin">屏风校区：5896843      雁山校区：3696660；下班时间及周末请到公寓值班室报修，拨打维修部值班人员电话申请维修。</span><br/>
					四、一般不影响学生正常生活及学习秩序的零星维修，维修部门将视全校维修情况予以滞后安排，一般不超过七天。<br/>
					五、所维修设备设施属自然损坏的，由维修部门负责免费修理，学生人为损坏的，实行有偿维修。<br/>
				</p>
				<a class="close" title="隐藏"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
		<!-- 提示信息 end-->
		<html:form action="/gyglnew_gybxgl">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="search_go" name="search_go" onclick="searchRs();return false;"/>
			<!-- 隐藏域 -->

			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" onclick="add();return false;" class="btn_zj"> 新增申请 </a></li>
						<li><a href="#" onclick="update('gyglnew_gybxgl.do?method=gybxglView');return false;" class="btn_qb"> 详细信息</a></li>
						<li><a href="#" onclick="pjUpdate();return false;" class="btn_sh"> 评价 </a></li>
						<li><a href="#" onclick="update('gyglnew_gybxgl.do?method=gybxglAdd');return false;" class="btn_xg"> 修改 </a></li>
						<logic:equal name="userType" value="admin">
							<li><a href="#" onclick="delBatch();return false;" class="btn_sc"> 删除 </a></li>
						</logic:equal>
						<logic:equal name="userType" value="xx">
							<li><a href="#" onclick="delBatch();return false;" class="btn_sc"> 删除 </a></li>
						</logic:equal>
					</ul>
				</div>
				<!-- 按钮 end-->	
			</div>
			<!-- 多功能操作区 end -->
			
			<!-- 查询结果-->
			<div class="formbox">		
				<h3 class="datetitle_01">
					<span>
						查询结果
						<logic:empty name="rsArrList">
							&nbsp;&nbsp;<font color="red">未找到任何记录！</font> 
						</logic:empty>
						<logic:notEmpty name="rsArrList">
							&nbsp;&nbsp;
							<font color="blue">
								双击查看详细信息
							</font> 
						</logic:notEmpty>
					</span>
				</h3>				
				<div class="con_overlfow" style="min-height: 600px">
				<table summary="" class="dateline" width="100%">
					<!-- 表头 -->
					<thead>
						<tr align="center" style="cursor:hand">
							<td width="5px">
								<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
							</td>
							<logic:iterate id="tit" name="topTr" offset="0" >
								<td id="<bean:write name="tit" property="en"/>"
									onclick="tableSort(this)">
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<!-- 表头 end-->
					<!--内容 -->
					<logic:iterate name="rsArrList" id="s" indexId="index">
						<tr onclick="rowOnClick(this);" ondblclick="update('gyglnew_gybxgl.do?method=gybxglView');" style="cursor:hand">
							<td align="center" width="5px">
								<logic:iterate id="v" name="s" offset="0" length="1">
									<input type="checkbox" id="pk_${index }" name="primarykey_checkVal" value="${v }"/>
								</logic:iterate>
								
								<logic:iterate id="v" name="s" offset="1" length="1">
									<input type="hidden" value="${v }"/>
								</logic:iterate>
								
								<logic:iterate id="v" name="s" offset="7" length="1">
									<input type="hidden" value="${v }" id="pjzt_${index }"/>
								</logic:iterate>
							</td>
							<!-- 显示信息 -->
							<logic:iterate id="v" name="s" offset="2">
								<td align="left">
									${v }
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
					<%
					int rsNum = ((List)request.getAttribute("rsArrList")).size();
					int pageSize = (Integer)(request.getAttribute("pageSize"));
					if(rsNum < pageSize){
						for(int i=0; i<(pageSize-rsNum); i++){
					%>
					<tr>
						<td>
							<input type="checkbox" disabled="disabled"></input>
						</td>
						<logic:iterate id="tit" name="topTr" offset="0">
							<td>
								&nbsp;
							</td>
						</logic:iterate>
				 	</tr>
					<%}}%>
				</table>
				<!--分页-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglnewGybxglForm"></jsp:include>
				<!--分页end-->
				</div>
			</div>
			<!-- 查询结果 end-->
			
			<div id="tempDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>服务评价</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="35%">
									<span class="red">*</span>满意程度
								</th>
								<td>
									<select name="mycd">
										<option value="非常满意">非常满意</option>
										<option value="比较满意">比较满意</option>
										<option value="不满意">不满意</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>
									评价<br/><span>(限制字数200)</span>
								</th>
								<td>
									<textarea name="pj" rows="3" style="width: 90%" cols="1" onblur="chLeng(this,200);"></textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"<span class="red">*</span>"为必填项
									</div>
									<div class="btn">
										<button type="button" name="保存" onclick="saveData('gyglnew_gybxgl.do?method=gybxglStudent&doType=pj','mycd');">
											保 存
										</button>
										<button type="button" name="取消" onclick="closeWindown();return false;">
											取 消
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</html:form>
		<logic:present name="message">
			<input type="hidden" id="message" name="message" value="${message }"/>
			<script type="text/javascript">
				alertInfo($('message').value);
			</script>
		</logic:present>
	</body>
</html>