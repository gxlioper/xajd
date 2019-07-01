<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="js/qtsjFunction.js"></script>
<script type="text/javascript" src="js/jsFunction.js"></script>
<script type="text/javascript" src="js/xgutil.js"></script>
<script type="text/javascript" src="js/check.js"></script>
<script type="text/javascript" src="js/commit.js"></script>
<script type="text/javascript">	  
function checkSxf(fs,sxf){
	var sxfs = eval(sxf);
	var lrf = fs.value;
	if(lrf>sxfs){
		alert("您输入的分数超过上限分");
		fs.value="";
	}
}

function checkInputNum(obj){
	 obj.value = obj.value.replace(/[^(\d|\.)]/g,'');
}

function dataExport() {
	document.forms[0].action = "/xgxt/pjpyZcsjwh.do?method=dataExport";
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}

function initZcf(){
	if(confirm("初始化操作将删除该"+jQuery("#xbmc").val()+"当前评奖周期的数据，并给以初始的默认分！")){
		if(confirm("您确定要执行初始化操作？")){
			saveData('pjpyZcsjwh.do?method=initZcf','');
		}
		
	}
}
</script>

</head>
<body onload="xyDisabled('xy');">
	<html:form action="/pjpyZcsjwh" method="post">
		<input type="hidden" name="zyV" id="zyV" />
		<input type="hidden" name="bjV" id="bjV" />
		<input type="hidden" name="userType" id="userType" value="${userType}" />
		<input type="hidden" name="userName" id="userName" value="${userName }" />
		<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }" />
		<input type="hidden" id="dm" name="dm" value="${dm }" />
		<input type="hidden" id="dmlb" name="dmlb" value="${dmlb }" />
		<input type="hidden" id="queryCol" name="queryCol" value="xn!!xq!!nd!!nj!!xydm!!zydm!!bjdm!!fdysh!!xysh!!xxsh" />
		<input type="hidden" id="likeCol" name="likeCol" value="xh!!xm" />
		
        <input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
		
		<logic:equal value="xy" name="userType">
			<logic:notEqual value="true" name="isFdy">
			<logic:notEqual value="true" name="isBzr">
				<html:hidden property="xydm"/>
			</logic:notEqual>
			</logic:notEqual>
		</logic:equal>
		
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>

		<div class="toolbox">
			<!-- 按钮 -->				  
		  <div class="buttonbox">
		    <ul>
			  <!--读写权-->
			  <logic:equal value="yes" name="writeAble">
					<!-- 如果是不需要进行数据维护的项目只能查询，不能进行可写操作 -->
					<logic:empty name="yhInfo">
					<logic:equal value="xy" name="userType">
						<logic:notEqual value="true" name="isFdy">
						<logic:notEqual value="true" name="isBzr">
							<li id="licsh" > <a href="#" onclick="initZcf();" class="btn_csh">默认分初始化</a> </li>
						</logic:notEqual>
						</logic:notEqual>
					</logic:equal>
					
					<li id="lisave" > <a href="#" onclick="saveData('pjpyZcsjwh.do?method=zcfsSave','');" class="btn_ccg">保 存</a> </li>
					<li id="lisc"> <a href="#" onclick="batchData('pkV','pjpyZcsjwh.do?method=zcfsDel','del');" class="btn_sc">删 除</a> </li>
					<li id="lidc"><a href="#" class="btn_dc" onclick="dataExport()">导出</a></li>
					<!-- END -->
					</logic:empty>
			  </logic:equal>	
			</ul>					
		  </div>
	
		  <!-- 按钮 -->				  
		  <div class="searchtab">
			<table width="100%" border="0">
		      <tfoot>
		        <tr>			
				  		  
		          <td colspan="6">
		          <div class="bz">
		          <logic:notEmpty name="yhInfo">
						<font color="red"><b>提示：${yhInfo}</b></font>
					</logic:notEmpty>
					</div>
		            <div class="btn">
					  	<input type="hidden" name="go" value="a" />
						<button type="button" id="search_go"
							onclick="refreshForm('pjpy_tyb_zcsjwhQuery.do');">
							查询
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
												重置
											</button>
		            </div>
		          </td>
		        </tr>
		      </tfoot>
			  <tbody>
			  <logic:equal value="nd" name="pjzq">
				<tr>
				<th>年度</th>
				<td>
					<html:select property="nd" styleId="nd" style="width:150px">
						<html:options collection="ndList" property="nd"
							labelProperty="nd" />
					</html:select>
				</td>
				<th></th>
				<td>		
				</td>
				<th></th>
				<td>
					
				</td>
			  </tr>
			</logic:equal>
			<logic:equal value="xn" name="pjzq">
				<tr>
				<th>学年</th>
				<td>
					<html:select property="xn" styleId="xn" style="width:150px">
						<html:options collection="xnList" property="xn"
							labelProperty="xn" />
					</html:select>
				</td>
				<th></th>
				<td>		
				</td>
				<th></th>
				<td>
				</td>
			  </tr>
			  </logic:equal>
			  <logic:equal value="xq" name="pjzq">
				<tr>
				<th>学年</th>
				<td>
					<html:select property="xn" styleId="xn" style="width:150px">
						<html:options collection="xnList" property="xn"
							labelProperty="xn" />
					</html:select>
				</td>
				<th>学期</th>
				<td>
					<html:select property="xq" styleId="xq" style="width:150px">
						<html:option value=""></html:option>
						<html:options collection="xqList" property="xqdm"
							labelProperty="xqmc" />
					</html:select>		
				</td>
				<th></th>
				<td>
					
				</td>
			  </tr>
			  </logic:equal>
			  
			  <tr>
				<th>年级</th>
				<td>
					<html:select property="nj" onchange="initZyList();initBjList();" style="width:150px">
						<html:option value=""></html:option>
						<html:options collection="njList" property="nj"
							labelProperty="nj" />
					</html:select>
				</td>
				<th>学号</th>
				<td>	
					<html:text property="xh"  styleId="xh" maxlength="19"></html:text>	
				</td>
				<th>姓名</th>
				<td>
					<html:text property="xm" styleId="xm" maxlength="10"></html:text>		
				</td>
			  </tr>
			  <tr>
				<th><bean:message key="lable.xsgzyxpzxy" /></th>
				<td>
					<html:select property="xydm" styleId="xy"
						onchange="initZyList();initBjList()" style="width:150px">
						<html:option value=""></html:option>
						<html:options collection="xyList" property="xydm"
							labelProperty="xymc" />
					</html:select>
				</td>
				<th>专业</th>
				<td>
					<html:select property="zydm" styleId="zy"
						onchange="initBjList()" style="width:150px">
						<html:option value=""></html:option>
						<html:options collection="zyList" property="zydm"
							labelProperty="zymc" />
					</html:select>		
				</td>
				<th>班级</th>
				<td>
					<html:select property="bjdm" styleId="bj" style="width:150px">
						<html:option value=""></html:option>
						<html:options collection="bjList" property="bjdm"
							labelProperty="bjmc" />
					</html:select>		
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
							<font color="blue">提示：单击表头可以排序;
								<logic:equal value="xy" name="userType">
									<logic:notEqual value="true" name="isFdy">
									<logic:notEqual value="true" name="isBzr">
										录入分数前请先初始化默认分！
									</logic:notEqual>
									</logic:notEqual>
								</logic:equal>
							</font>
						</logic:notEmpty>
				    </span>
				    </h3>			 
				<logic:notEmpty name="rs">
				<div class="con_overlfow">
				<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
				<thead>
			      <tr>
			       <td>
						<input type="checkbox" name="all" onclick="chec()" style="height:17.5px" />
					</td>
					<logic:iterate id="tit" name="topTr" offset="1">
						<td id="<bean:write name="tit" property="en"/>"
							onclick="tableSort(this)" nowrap>
							<bean:write name="tit" property="cn" />
						</td>
					</logic:iterate>
					<logic:notEmpty name="xmList">
						<logic:iterate id="map" name="xmList">
							<td>
								${map.MC }
								<font color="red">
								<br/>
								(上限分为${map.XZF })</font>
							</td>
						</logic:iterate>
					</logic:notEmpty>
			      </tr>
			    </thead>
				<tbody>
			      <logic:iterate name="rs" id="s">
						<tr onclick="rowOnClick(this);" style="cursor:hand;">
							<td align="center">
								<input type="checkbox" id="pkV" name="primarykey_cbv" style="height:17.5px"
									value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"
									<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate> />
							</td>
							<logic:iterate id="v" name="s" offset="1" length="${dmNum }">
								<td align="center">
									<bean:write name="v" />
								</td>
							</logic:iterate>
							<logic:iterate name="xmList" id="map"  indexId="index">
								<td align="center">
									<input type="hidden" name="xhs" id="xhs" value="<logic:iterate name="s" id="v" offset="2" length="1">${v }</logic:iterate>"/>
									<input type="hidden" name="dms" id="dms" value="${map.DM }"/>
									<input type="text" style="width:50px" name="fs" maxlength="8" id="fs" 
										onkeyup="checkInputNum(this);" onblur="checkSxf(this,${map.XZF});"
										value="<logic:iterate id="v" name="s" offset="${index+dmNum+1}" length="1">${v }</logic:iterate>"/>
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
			    </tbody>
				</table>
				</div>
				<!--分页显示-->
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=pjpyZhszcpwhActionForm"></jsp:include>
			 	<!--分页显示-->
				</logic:notEmpty>
				</div>
			
	</html:form>
	<logic:present name="result">
		<logic:equal value="true" name="result">
			<script>
				alert("操作成功!");
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("操作失败!");				
			</script>
		</logic:equal>
	</logic:present>
</body>
