<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/pjpy/pjpyFunction.js"></script>
	<script language="javascript" src="js/pjpy/pjpy_zjlg.js"></script>
	<script language="javascript">
		function changeYq(){
			if($("mklx")){
				var mklx=$("mklx").value;
				if(mklx!=""){
					if(mklx=="jxj"){
						$("li_jxj").className="ha";
						$("li_rych").className="";
						$("li_xjbj").className="";
					}else if(mklx=="rych"){
						$("li_jxj").className="";
						$("li_rych").className="ha";
						$("li_xjbj").className="";
					}else if(mklx=="xjbj"){
						$("li_jxj").className="";
						$("li_rych").className="";
						$("li_xjbj").className="ha";
					}
				}
			}
		}
		
		function setSzlx(mklx){
			$("szlx").value=mklx;
		}
	</script>
	</head>
	<body onload="changeYq()">
		<html:form action="/guizdxPjpy" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/pjpy/hiddenValue.jsp"%>
			<html:hidden property="queryequals_szlx" styleId="szlx"  value="${mklx}"/>
			<input type="hidden" name="mklx" id="mklx" value="${mklx}"/>
			<html:select property="xn" style="width:120px;display :none" disabled="true"styleId="xn">
					<html:options collection="xnList" property="xn" labelProperty="xn" />
			</html:select>
			<!-- 隐藏域 end-->
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<logic:notEmpty name="msg">
				<div align="center">
					<FONT color="red" size="6"><bean:write name="msg" /></FONT>
				</div>
			</logic:notEmpty>
			<div class="compTab" id="card" style="width: 100%">
				<div class="comp_title" id="div1">
					<ul id="ul1">
						<li class="ha" id="li_jxj">
							<a href="#" onclick=" setSzlx('jxj');refreshForm('guizdxPjpy.do?method=rsszXxManage&mklx=jxj');">
								<span>奖学金</span>
							</a>
						</li>
						<li id="li_rych">
							<a href="#" onclick="setSzlx('rych');refreshForm('guizdxPjpy.do?method=rsszXxManage&mklx=rych');" id="${card.en}_a">
								<span>荣誉称号</span>
							</a>
						</li>
						<li id="li_xjbj">
							<a href="#" onclick="setSzlx('xjbj');refreshForm('zjlgPjpy.do?method=cssz&xxk=xjbj');" id="${card.en}_a">
								<span>先进班级</span>
							</a>
						</li>
					</ul>				
				</div>
			</div>
			<logic:empty name="msg">
			<div class="toolbox">
				  <!-- 按钮 -->		
				  <logic:equal name="writeAble" value="yes">		  
				  <div class="buttonbox">
				    <ul>
						<li> <a href="#"  onclick="showTopWin('pjpy_tyb_pjsjsz.do',450,330)" class="btn_csh">调奖周期设置</a> </li>
						<li> <a href="#"  onclick="showTopWin('/xgxt/guizdxPjpy.do?method=rsszXxUpdate&mklx='+$('mklx').value,'800','400')" class="btn_sz">设置</a> </li>		
						<li> <a href="#" onclick="impAndChkData()" class="btn_dr">导入数据</a> </li>
						<li> <a href="#" onclick="wjcfDataExport('guizdxPjpy.do?method=rsszXxManage&doType=exp')" class="btn_dc">导出数据</a> </li>
					</ul>					
				  </div>
				  </logic:equal>
				  
				 <div class="searchtab">
					<table width="100%" border="0">
				      <tfoot>
				        <tr>						  
				          <td colspan="6">
				            <div class="btn">
							  	<input type="hidden" name="go" value="a" />
								<button id="search_go"
									onclick="allNotEmpThenGo('/xgxt/guizdxPjpy.do?method=rsszXxManage');">
									查询
								</button>
				            </div>
				          </td>
				        </tr>
				      </tfoot>
				      <tbody>
								<tr>
									<th>
										学年
									</th>
									<td>
										<html:hidden property="queryequals_xn"/>
										<html:select property="queryequals_xn" style="width:120px" disabled="true"
											styleId="xn">
											<html:options collection="xnList" property="xn" labelProperty="xn" />
										</html:select>
									</td>
									<th>
										年级
									</th>
									<td>
										<html:select property="queryequals_nj" style="" onchange="">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj" labelProperty="nj" />
										</html:select>
									</td>
									<th>	
										<bean:message key="lable.xsgzyxpzxy" />
									</th>
									<td>
										<html:select property="queryequals_xydm" style="" styleId="xy" onchange="">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<!-- 奖学金 -->
									<logic:equal name="mklx" value="jxj">
									<th>
										奖学金名称
									</th>
									<td>
										<html:select property="queryequals_jxjdm" style="" styleId="jxjdm">
											<html:options collection="jxjList" property="dm" labelProperty="mc" />
										</html:select>
									</td>
									</logic:equal>
									<!-- 奖学金 end-->
									<!--荣誉称号 -->
									<logic:equal name="mklx" value="rych">
									<th>
										荣誉称号名称
									</th>
									<td>
										<html:select property="queryequals_jxjdm" style="" styleId="jxjdm">
											<html:options collection="rychList" property="dm" labelProperty="mc" />
										</html:select>
									</td>
									</logic:equal>
									<!-- 荣誉称号 end-->
								<th></th><td></td>
								<th></th><td></td>
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
							<font color="blue">记录数：
								<bean:write name="rsNum" />
							</font>
						</logic:notEmpty>
				    </span>
				    </h3>			 
					
					<logic:notEmpty name="rs">
					<div class="con_overlfow">
					<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
								<thead>	
									<tr align="center" style="cursor:hand">
										<logic:iterate id="tit" name="topTr" offset="0">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowOnClick(this);" style="cursor:hand">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<td align="left">
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="6">
											<td align="left">
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
							<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=pjpyTyForm"></jsp:include>
					</div>
					</logic:notEmpty>
					<div id="tmpdiv1"></div>
				
<%--					<div class="buttontool" id="btn"--%>
<%--						style="position: absolute;left:1%;top:100px" width="100%">--%>
<%--						<logic:equal name="writeAble" value="yes">--%>
<%--							<button class="button2"--%>
<%--								onclick="chgPriseXn()" style="width:80px">--%>
<%--								调整学年--%>
<%--							</button>--%>
<%--							&nbsp;&nbsp;--%>
<%--							<button id="btn_" class="button2" onclick="showTopWin('pjpy_tyb_pjsjsz.do',450,330)">调奖周期设置</button>--%>
<%--							--%>
<%--							&nbsp;&nbsp;--%>
<%--							<button class="button2"--%>
<%--								onclick="showTopWin('/xgxt/guizdxPjpy.do?method=rsszXxUpdate&mklx='+$('mklx').value,'800','400')"--%>
<%--								style="width:80px">--%>
<%--								设&nbsp;&nbsp;置--%>
<%--							</button>--%>
<%--							&nbsp;&nbsp;--%>
<%--							<button class="button2" onclick="impAndChkData()"--%>
<%--								style="width:80px">--%>
<%--								导入数据--%>
<%--							</button>--%>
<%--							&nbsp;&nbsp;--%>
<%--							<button class="button2" onclick="wjcfDataExport('guizdxPjpy.do?method=rsszXxManage&doType=exp')"--%>
<%--								style="width:80px">--%>
<%--								导出数据--%>
<%--							</button>--%>
<%--						</logic:equal>--%>
				</div>
			</logic:empty>
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("操作成功!");
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("操作失败");
			</script>
		</logic:equal>
		<logic:present name="message">
			<script>
				alert(''+document.getElementById('message').value);
			</script>
		</logic:present>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
