<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">

			function xssqUpdate(opera,obj){
				obj.parentNode.parentNode.click();
				var url = "/xgxt/pjpy_comm_xmsq.do?method=xssqUpdate&opera=" + opera;
				url += "&xmdm="+curr_row.getElementsByTagName('input')[0].value;
				showTopWin(url,800,600);
			}

			function plsz(){
				var url = "/xgxt/pjpy_comm_xmsq.do?method=rsszUpdate&doType";
				url += "&szfw=" + $('szfw').value;
				url += "&xmdm=" + $('xmdm').value;

				showTopWin(url,800,600);
			}
			
			function showYy(id){
				alertInfo($(id).value);
			}
			
			//前往项目上报
			function goXmsb(){
				var url = "pjpyXmsb.do?method=xmsbManage";
					url+= "&xmdm="+$("xmdm").value;
					url+= "&bjdm="+$("bjdm").value;
				
				showWaitingDiv("30000");
				
				refreshForm(url);
			}
			
			//显示项目申请列表
			function showXmsqList(){
				var onload = $("onload").value;
				if(onload == ""){
					allNotEmpThenGo('/xgxt/pjpy_comm_xmsq.do?method=xssqManage');
				}
			}
		</script>
	</head>
	<body onload="showXmsqList()">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>评奖评优 - 我的评奖 - 学生申请</a>
			</p>
			
			<!-- 在线帮助 -->
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
			<!-- 在线帮助 end -->
			
			<!-- 相关功能 -->
			<p class="other" style="position:relative;">
				<a href="#" onclick="return false;" 
					onmouseover ="displayDiv(['liucheng_bar','liucheng_bar'],'')"
					style="display:block;height:30px;">相关功能</a>
			</p>
			<!-- 相关功能 end-->
		</div>
		
		<!-- 提示信息 end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				1.结果集中列出本评奖周期中，申请方式为<font color="blue">学生申请</font>的所有项目。</br>
				<logic:equal name="userType" value="stu">
				2.如果您不满足该项目的相关条件，则该项目<font color="blue">不可申请</font>，点击可查看详细。</br>
				3.如果目前不在该项目的申请时间范围内，则该项目<font color="blue">不可申请</font>，点击可查看详细。</br>
				4.如果您想申请该项目，请点击<font color="blue">申请</font>操作，在展示页面填写相关申请内容。</br>
				5.如果您已经申请了某项目，并且该项目尚未被任一岗位审核，可以点击<font color="blue">修改</font>。
				</logic:equal>
				<logic:notEqual name="userType" value="stu">
				2.如果您想替某学生申请某项目，请点击<font color="blue">申请</font>，在展示页面中挑选学生，完成操作。</br>
				</logic:notEqual>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->	
		
		<!-- 快捷方式 -->
		<div class="liucheng_xg_pj" id="div_other_gnmk" style=""
			onmouseover="displayDiv(['liucheng_bar','liucheng_bar'],'')"
			onmouseout="displayDiv(['liucheng_bar','liucheng_bar'],'none')">
		
			<div class="liucheng_bar" id="liucheng_bar" style="position:absolute;top:30px;right:0px;width:auto;background:#fff;display:none">
				<div class="con">
				
					<div class="liucheng_font floatleft">
				    	<a href="#" onclick="goOtherGnmk('pjpy_mypj.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function79.png" />
							<p>我的评奖</p>
						</a>   	
					</div>
					
					<logic:notEqual name="userType" value="stu">
					<div class="liucheng_font floatleft">
						<a href="#" onclick="showTopWin('/xgxt/pjpyXmsb.do?method=sbfwChoose',600,550);return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function46.png" />
							<p>老师上报</p>
						</a>
					</div>
					</logic:notEqual>
					
					<div class="liucheng_font floatleft">
						<a href="#" onclick="goOtherGnmk('pjpy_pjlc_jgcx.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function12.png" />
							<p>结果查询</p>
						</a>
					</div>
			
				</div>
			</div>
		</div>
		<!-- 快捷方式 end-->
		
		<html:form action="/pjpy_comm_xmsq" method="post">
			<input type="hidden" name="onload" value="${onload }"/>
			<input type="hidden" name="pjxn" value="${jbsz.pjxn }"/>
			<input type="hidden" name="pjxq" value="${jbsz.pjxq }"/>
			<input type="hidden" name="pjnd" value="${jbsz.pjnd }"/>
			<input type="hidden" name="bjdm" id="bjdm" value=""/>
			<input type="hidden" name="xmdm" id="xmdm" value="" />
			<button type="button" id="forward" onclick="goXmsb()" style="display: none">跳转</button>
			<div class="toolbox">
			<div class="searchtab">
				<table>
					<tbody>
						<tr>
							<th>评奖学年</th>
							<td>
								<html:select property="pjxn" style="width: 150px" styleId="xn" value="${jbsz.pjxn}" disabled="true">
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
							</td>
							<th>评奖学期</th>
							<td>
								<html:select property="pjxq" style="width: 150px" styleId="xq" value="${jbsz.pjxq}" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>
							</td>
							<th>评奖年度</th>
							<td>
								<html:select property="pjnd" style="width: 150px" styleId="nd" value="${jbsz.pjnd}" disabled="true">
									<html:options collection="ndList" property="nd" labelProperty="nd" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>项目类型</th>
							<td>
								<html:select property="xmlx" style="width: 150px">
									<html:option value=""></html:option><!-- 2011.11.9 qlj 陈FF要求 -->
									<html:option value="01">奖学金</html:option>
									<html:option value="02">荣誉称号</html:option>
								</html:select>
							</td>
							<th>项目性质</th>
							<td>
								<html:select property="xmxz" style="width: 150px">
									<html:option value=""></html:option>
									<html:options collection="xmxzList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
							<th>项目范围</th>
							<td>
								<html:select property="xmfw" style="width: 150px">
									<html:option value=""></html:option>
									<html:options collection="xmfwList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<input type="hidden" name="go" value="a" />
									<button type="button" id="search_go" onclick="allNotEmpThenGo('/xgxt/pjpy_comm_xmsq.do?method=xssqManage');">
										查 询
									</button>
									 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
										重 置
							 		</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			</div>
			<div class="formbox">
				<logic:empty name="rs">
				    <h3 class="datetitle_01">
				    <span>
				    	查询结果&nbsp;&nbsp;
							<font color="red">未找到任何记录！</font> 
				    </span>
				    </h3>
				 </logic:empty>
				<logic:notEmpty name="rs">
					<h3 class="datetitle_01">
						<span>
							查询结果&nbsp;&nbsp;<font color="blue">单击表头可以排序;</font> 
						</span>
					</h3>
					<table width="99%" id="rsTable" class="dateline">
						<thead>
							<tr style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">&nbsp;
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="map">
							<tr onclick="rowOnClick(this);" 
								ondblclick="" style="cursor:hand">
								<td>
									<input type="hidden" name="xmdm" value="${map.xmdm }" />
									${map.xmmc}
								</td>
								<td>	
									<logic:equal name="map" property="xmlx" value="01">
										奖学金
									</logic:equal>
									<logic:equal name="map" property="xmlx" value="02">
										荣誉称号
									</logic:equal>
								</td>
								<td>
									${map.shlc }
								</td>
								<logic:equal value="stu" name="user" property="userStatus">
									<td>
										${map.mqshzt }
									</td>
								</logic:equal>
								<td>
									<logic:equal name="map" property="cz" value="bkxg">
										<input type="hidden" id="yy_${map.xmdm }" value="${map.yy }"/>
										<button type="button" class="btn_01" onclick="showYy('yy_${map.xmdm}');" style="width:80px;height: 20px">
											不可申请
										</button>
									</logic:equal>
									<logic:equal name="map" property="cz" value="ksq">
										<button type="button" class="btn_01" onclick="xssqUpdate('add',this);" style="width:80px;height: 20px">
											申&nbsp;&nbsp;&nbsp;&nbsp;请
										</button>
									</logic:equal>
									<logic:equal name="map" property="cz" value="kxg">
										<button type="button" class="btn_01" onclick="xssqUpdate('update',this);" style="width:80px;height: 20px">
											修&nbsp;&nbsp;&nbsp;&nbsp;改
										</button>
									</logic:equal>
								</td>
							</tr>
						</logic:iterate>
					</table>
				</logic:notEmpty>
			</div>
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>