<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
		<script type="text/javascript" src="js/pjpy/pjpy_szyqxy.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
			function selectAllss(){
			var checkBoxArr = document.getElementsByName("pk");
			var selall = document.getElementById('cbv');
			if(selall.checked==true){
				for(var i=0;i<checkBoxArr.length;i++){
						checkBoxArr[i].checked = true;
				}
			} else {
				for(var i=0;i<checkBoxArr.length;i++){
					checkBoxArr[i].checked = false;
				}
			}
			}
		function zznlHzb(){
		    
		    var xymc ="";
		    if($("xydm").value!=""){ 
		      xymc=document.forms[0].xy.options[document.forms[0].xy.selectedIndex].text;
		    }
		    var zymc ="";
		    if($("zydm").value!=""){ 
		      zymc = document.forms[0].zy.options[document.forms[0].zy.selectedIndex].text;
		    }
		    var bjmc = "";
		    if($("bjdm").value!=""){ 
		      bjmc = document.forms[0].bj.options[document.forms[0].bj.selectedIndex].text;
		    }
		    var xqmc = "";
		    if($("xq").value!=""){
		       xqmc = document.forms[0].xq.options[document.forms[0].xq.selectedIndex].text;
		    }
		    var url = "/xgxt/pjpyszyqwh.do?method=shsjHzb&printType=zznl";
		    url+="&xqmc="+xqmc;
		    url+="&xy="+xymc;
		    url+="&zy="+zymc;
		    url+="&bj="+bjmc;
		    document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		</script>
	</head>
	<body onload="xyDisabled('xy');">
	<html:form action="/pjpyszyqwh" method="post">
    	<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>综合素质-学生综合素质养成课-组织能力查询</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>			
		<!-- 标题 end-->
		<!-- 提示信息 START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				鼠标移动到右上角<font color="blue">帮助中心</font>，可查看本模块的相关说明。</br>
				<span id="div_help" style="display: none">
				1.以下所有操作都是基于<font color="blue">${xn }</font>学年，<font color="blue">${xqmc }</font>学期 展开的。</br>
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
    	<input type="hidden" id="zyV" name="zyV" value=""/>
    	<input type="hidden" id="bjV" name="bjV" value=""/>
    	<input type="hidden" id="userType" name="userType" value="${userType}"/>
    	<input type="hidden" id="userName" name="userName" value="${userName}"/>
    	<input type="hidden" id="tableName" name="tableName" value="${tableName}"/>
    	<input type="hidden" id="realTable" name="realTable" value="${realTable}"/>
    	<input type="hidden" id="tmp" name="tmp" value="${tmp }"/>
    	<input type="hidden" name="pkstring" value="" />
    	<!-- 批量删除信息提示 -->
    	<input type="hidden" id="failInfo" name="failInfo" value="${failinfo }"/>
		
			<div class="comp_title">
		      <ul>
		        <li class="ha"><a href="javascript:refreshForm('szyc_zznlManage.do');"><span>查询</span></a></li>
		        <li><a href="javascript:refreshForm('pjpyszyqwh.do?method=szyc_zznlBzrAdd');"><span>增加</span></a></li>
		      </ul>
		    </div>
		    
		    <logic:equal value="yes" name="writeAble">
				<div class="toolbox">
					<div class="buttonbox">	
						<ul>
							<li><a href="#" class="btn_xg" onclick="modiTab('pjpyszyqwh.do?method=szyc_zznlUpdate&pkValue=','800','500');return false;">修改</a></li>
							<li><a href="#" class="btn_tj" onclick="zznlHzb();return false;">汇总</a></li>
						</ul>
					</div>
				</div>
			</logic:equal>
		    
			<div class="searchtab">
				<table width="100%">
					<tbody>
						<tr>
							<th>年级</th>
							<td>
								<html:select property="nj" styleId="nj" style="width:90px"
								onchange="initZyList();initBjList()" styleClass="select">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj" labelProperty="nj"/>
								</html:select>
							</td>
							<th>学年</th>
							<td><html:select property="xn" style="width:90px" styleClass="select"
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
							<th>学期</th>
							<td>
								<html:select property="xq" style="width:90px" styleClass="select"
									styleId="xn">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>学号</th>
							<td>
								<logic:equal name = "userType" value="stu" scope="session">
									<input type="text" name = "xh" value="<bean:write name="userName" scope="session"/>" readonly="true" style="width:100px" class="inputtext"/>
								</logic:equal>
								<logic:notEqual name = "userType" value="stu" scope="session">
								<html:text property="xh" styleId="xh" styleClass="inputtext"
								 style="width:100px"></html:text>
								 </logic:notEqual>
							</td>
							<th>
								姓名
							</th>
							<td>
								<html:text property="xm" styleId="xm" styleClass="inputtext"
								 style="width:100px"></html:text>
								 &nbsp;&nbsp;
							</td>
							<th></th>
							<td>
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								<html:select property="xydm" onchange="initZyList();initBjList()"
								styleClass="select" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
							<th>
								专业
							</th>
							<td>
								<html:select property="zydm" onchange="initBjList()" 
								styleClass="select" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
							<th>班级</th>
							<td>
								<html:select property="bjdm"  
								styleClass="select" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
					</tbody>
					
					<tfoot>
						<tr>
							<td colspan="6">
							<div class="btn">
							<input type="hidden" name="go" value="a" />
							<button type="button" id="search_go" onclick="refreshForm('pjpyszyqwh.do?method=szyc_zznlQuery&go=go');">
								查询
							</button>
							 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
								重置
							 </button>
							</div>
							</td>
						</tr>
					</tfoot>
				</table>
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
							查询结果&nbsp;&nbsp;<font color="blue">双击一行可以查看详细信息；单击表头可以排序</font> 
						</span>
					</h3>		
							<table width="99%" id="rsTable" class="dateline">
								<thead>
									<tr align="center" style="cursor:hand">
									    <td style="display: none">
											<input type="hidden" id="cbv" name="cbv" onclick="selectAllss()" />
										</td>
										<logic:iterate id="tit" name="topTr" offset="1" scope="request">
											<td id="${tit.en}"
												onclick="tableSort(this)">
												${tit.cn}
											</td>
										</logic:iterate>
									</tr>
								</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor: hand;"
									ondblclick="modiTab('pjpyszyqwh.do?method=szyc_zznlUpdate&pkValue=','800','500')">
									<td style="display: none">
										<input type="hidden" id="pk" name="ok"
											value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
									
									<logic:iterate id="v" name="s" offset="1" length="1">
											<input type="hidden" name="xsxh" value="<bean:write name='v'/>" />
									</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="1">
										<td align=center>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
					</logic:notEmpty>
					
					<div id="tmpdiv"></div>
				</div>
	</html:form>
	 <script type="text/javascript" src="js/bottomButton.js"></script>
	 <!-- 操作提示 -->
	 <jsp:include flush="true" page="/syscommon/deleteprompt.jsp"></jsp:include>
</body>