<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getInsureInfo.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/mdqrDWR.js"></script>	
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript" src="js/qtsjFunction.js"></script>
		<script language="javascript">
			function modi(url){
				if(curr_row != null){
					showOpenWindow(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
					return true;
				}else{
					alert('请选择要修改的数据行！');
					return false;
				}
			}
			
			function getXm(){
				if($("xmlbdm")){
					var xmlbdm=$("xmlbdm").value;
					var yhqx=$("yhqx").value;
					mdqrDWR.getXmListByShqx(xmlbdm,yhqx,function(data){
						DWRUtil.removeAllOptions($("xmdm"));		
						DWRUtil.addOptions($("xmdm"),data,"dm","mc");
					});
				}
			}
			
			function checkXmlx(){
				
				if($("xmlbdm").value==""){
					alert("请选择项目类别！");
					return false;
				}
				
				if($("xmdm").value==""){
					alert("请选择项目！");
					return false;
				}
				return true;
			}
			
			function changeSzzq(){
				var xmdm=$("xmdm").value;
				mdqrDWR.getSzzq(xmdm,function(data){
					if(data=="xn"){
						$("showTr").style.display="";
						$("showXn").style.display="";
						$("showXq").style.display="none";
						$("dxq").disabled="true";
						$("dnd").disabled="true";
						$("dxn").disabled="";
						$("showNd").style.display="none";
					}else if(data=="xq"){
						$("showTr").style.display="";
						$("showXn").style.display="";
						$("showXq").style.display="";
						$("dnd").disabled="true";
						$("dxn").disabled="";
						$("dxq").disabled="";
						$("showNd").style.display="none";
					}else if(data=="nd"){
						$("showTr").style.display="";
						$("showXn").style.display="none";
						$("showXq").style.display="none";
						$("showNd").style.display="";
						$("dxn").disabled="true";
						$("dxq").disabled="true";
						$("dnd").disabled="";
					}else if(data=="wzq"){
						$("showTr").style.display="none";
						$("dxn").disabled="true";
						$("dxq").disabled="true";
						$("dnd").disabled="true";
					}else if(data=="jyc"){
						$("showTr").style.display="none";
						$("dxn").disabled="true";
						$("dxq").disabled="true";
						$("dnd").disabled="true";
					}
				})
			}
			
			
		</script>
	</head>
	<body onload="changeSzzq()">
		
		<html:form action="/mdqr" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="userName" id="userName" value="${userName}" />
		    <input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
		    <input type="hidden" name="yhqx" id="yhqx" value="${yhqx}" />
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>

			<div class="toolbox">
			 <!-- 按钮 -->
			 <logic:equal name="writeAble" value="yes">
			 <div class="buttonbox">
			    <ul>
				<li> <a href="#" onclick="dataBatch('mdqr.do?method=mdqrSh&doType=mdsz&gnmk=${gnmk }&shjg=通过')" class="btn_shtg"> 审核通过 </a> </li>
				<li> <a href="#" onclick="dataBatch('mdqr.do?method=mdqrSh&doType=mdsz&gnmk=${gnmk }&shjg=不通过')" class="btn_shbtg"> 审核不通过 </a> </li>
			    </ul>
			 </div>
			<div class="searchtab">
			</logic:equal>
			<table width="100%" border="0">
			      <tfoot>
			        <tr>
			          <td colspan="6">
			            <div class="btn">
			              <button class="btn_cx" id="search_go" 
			              	onclick="if(checkXmlx()){allNotEmpThenGo('/xgxt/mdqr.do?method=mdqrSh&gnmk=${gnmk }&doType=query')}">
			              	查 询
			              </button>
			              &nbsp;&nbsp;&nbsp;&nbsp;
			              <button class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
			              	重 置
			              </button>
			            </div>
			          </td>
			        </tr>
			      </tfoot>

					<tbody>
						<tr>
							<th >
								学号
							</th>
							<td>
								<html:text property="querylike_xh" styleId="xh"/>
							</td>
							<th>
								姓名
							</th>
							<td>
								<html:text property="querylike_xm" styleId="xm"/>
							</td>
							<th>
								年级
							</th>
							<td>
								<html:select property="queryequals_nj" styleId="nj"  
										onchange="initZyList();initBjList();" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xb" />
							</th>
							<td>
								<logic:equal name="userType" value="xy">
									<html:select property="xydm" styleId="xy"  style="width:150px"
										onchange="initZyList();initBjList();" disabled="true" value="${userDep }">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
									</html:select>
									<html:hidden property="queryequals_xydm" value="${userDep}"/> 
								</logic:equal>
								<logic:notEqual name="userType" value="xy">
									<html:select property="queryequals_xydm" styleId="xy"  style="width:150px"
										onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</logic:notEqual>
							</td>
							<th>
								专业
							</th>
							<td>
								<html:select property="queryequals_zydm" styleId="zy" 
										onchange="initBjList();" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
							<th>
								班级
							</th>
							<td>
								<html:select property="queryequals_bjdm" styleId="bj"  style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>	
						<tr>
							<th>
								项目类别
							</th>
							<td>
								<html:select property="queryequals_xmlbdm" styleId="xmlbdm"  style="width:150px"
											onchange="getXm()">
										<html:option value=""></html:option>
										<html:options collection="xmlbList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								项目
							</th>
							<td>
								<html:select property="queryequals_xmdm" styleId="xmdm"  style="width:150px"
										onchange="changeSzzq()">
										<html:options collection="xmList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<td colspan="2">
								<!-- 学院审核级别 -->
								<logic:equal name="userType" value="xy">
								<bean:message key="lable.xb" />审核
								<html:select property="queryequals_xysh" styleId="xysh" >
										<html:option value="" ></html:option>
										<html:option value="未审核" >未审核</html:option>
										<html:option value="通过" >通过</html:option>
										<html:option value="不通过" >不通过</html:option>
								</html:select>
								</logic:equal>
								<!-- 学院 学校 审核级别 -->
								<logic:equal name="userType" value="xx">
								&nbsp;&nbsp;&nbsp;&nbsp;
								学校审核
								<html:select property="queryequals_xxsh" styleId="xxsh" >
										<html:option value="" ></html:option>
										<html:option value="未审核" >未审核</html:option>
										<html:option value="通过" >通过</html:option>
										<html:option value="不通过" >不通过</html:option>
								</html:select>
								</logic:equal>
								<logic:equal name="userType" value="admin">
								&nbsp;&nbsp;&nbsp;&nbsp;
								学校审核
								<html:select property="queryequals_xxsh" styleId="xxsh" >
										<html:option value="" ></html:option>
										<html:option value="未审核" >未审核</html:option>
										<html:option value="通过" >通过</html:option>
										<html:option value="不通过" >不通过</html:option>
								</html:select>
								</logic:equal>
							</td>
							
						</tr>	
							<tr id="showTr"  style="display:none">
							<td colspan="6" >
								<span id="showXn" style="display:none">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学年
								<html:select property="save_xn" styleId="xn"  style="width:150px"
										disabled="true">
										<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								<html:hidden property="queryequals_xn" styleId="dxn" />
								</span>
								<span id="showXq" style="display:none">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学期
								<html:select property="save_xq" styleId="xq"  style="width:150px"
										disabled="true">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
								<html:hidden property="queryequals_xq" styleId="dxq"/>
								</span>
								<span id="showNd" style="display:none">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年度
								<html:select property="save_nd" styleId="nd"  style="width:150px"
										disabled="true">
										<html:options collection="ndList" property="nd"
										labelProperty="nd" />
								</html:select>
								<html:hidden property="queryequals_nd" styleId="dnd"/>
								</span>
								申报时间
								<html:text property="kssj" styleId="kssj" style="width:90px"
								onclick="return showCalendar('kssj','y-mm-dd');" 
								onblur="dateFormatChg(this)" />--
								<html:text property="jssj" styleId="jssj" 
								onclick="return showCalendar('jssj','y-mm-dd');" style="width:90px"
								onblur="dateFormatChg(this)"/>
							</td>
						</tr>	                       
					</tbody>
				</table>
			</div>
			</div>
			<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	查询结果&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">未找到任何记录！</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs">
			 		 	<font color="blue">提示：单击表头可以排序</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
	
			<logic:notEmpty name="rs">
					<div class="con_overlfow">
					 <table summary="" id="rsTable" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox"  name="all" value="all" onclick="chec()"/>
									
								</td>
								<logic:iterate id="tit" name="topTr" offset="2" indexId="index">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								style="cursor:hand">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" name="save_xh" id="save_xh"  value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>"/>
										<input type="checkbox" name="primarykey_cbv" id="pkV" 
											   value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>"
											   <logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate> />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="2">
									<td nowrap>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
					</div>
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=mdqrForm"></jsp:include>
			</logic:notEmpty>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('操作成功！');
					</script>
				</logic:equal>
				<logic:notEqual value="true" name="result">
					<script>
						alert('操作失败！');
					</script>
				</logic:notEqual>
			</logic:present>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>	
  </body>
</html>
