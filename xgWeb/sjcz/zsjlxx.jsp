<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		
		<script type='text/javascript'src='/xgxt/dwr/interface/getOtherData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/xsgyglFunction.js"></script>
		<script language="javascript">		
			function chDate(){
				if($("gzkssj").value!=""&&$("gzjssj").value!=""){
					if($("gzkssj").value > $("gzjssj").value){
						alert("时间段前后顺序不正确，请确定！");
						return false;
					}
				}
				return true;
			}
			
			function mydormDataExp(url){
				document.forms[0].action = url;
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";
			}
			
			function choiceDisabled(ele) {
				var tmp = ele.split("-");
				for (i = 0; i < tmp.length; i++) {
					if (document.getElementById(tmp[i])) {
						document.getElementById(tmp[i]).disabled = true;
					}
				}
			}
		</script>
	</head>
	
	<body onload="bjLimit('bj');">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a><bean:write name="title" scope="request" /></a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/zsjl" method="post">
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="dxq" name="dxq"
				value="<bean:write name="writeAble" scope="request"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="xxdm" name="xxdm"
				value="<bean:write name="xxdm" scope="request"/>" />
			<input type="hidden" name="lcV" id="lcV" value="" />
			<input type="hidden" name="qshV" id="qshV" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="isFdy " id="isFdy" value="<bean:write name="fdyQx" scope="session"/>" />	

			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#"
									onclick="viewMore('add')"
									class="btn_zj">增加</a>
							</li>
							<li>
								<a href="#"
									onclick="viewMore('modi')"
									class="btn_xg">修改</a>
							</li>
							<li>
								<a href="#"
									onclick="viewMore('del')"
									class="btn_sc">删除</a>
							</li>
							<li>
								<a href="#" 
									onclick="impAndChkData()"
									class="btn_dr">导入</a>
							</li>		
						</logic:equal>
						<logic:equal value="yes" name="writeAble">
						<li>
							<a href="#" 
								onclick="dataExport()"
								class="btn_dc">导出</a>
						</li>
						</logic:equal>
						<logic:equal value="10822" name="xxdm">
						<!--广东白云<bean:message key="lable.xsgzyxpzxy" />  -->
						<li>
							<a href="#" 
								onclick="mydormDataExp('/xgxt/XsGyGlLogic.do?method=gdby_dormJlTj')"
								class="btn_dc">宿舍纪律记录表</a>
						</li>
					</logic:equal>
					</ul>
				</div>
				<!-- 按钮 end-->	
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="btn_cx" id="search_go"
											onclick="if(chDate()){allNotEmpThenGo('/xgxt/zsjl.do');this.disabled=true}">
											查 询
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- 第一行 -->
							<tr>
								<th>
									年级
								</th>
								<td>
									<html:select property="nj"  onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>												
								</td>
								<th>
									学年
								</th>
								<td>
									<html:select property="xn" styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
									</html:select>
								</td>
								<th>
									学期
								</th>
								<td>
									<html:select property="xq"  styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
									</html:select>			
								</td>
							</tr>
							<!-- 第二行 -->
							<tr>
								<th>
									院系
								</th>
								<td>
									<html:select property="xydm"  styleId="xy" onchange="initZyList();initBjList()" style="width: 150px">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
									</html:select>				
								</td>
								
								<logic:equal value="xy" name="userType">
									<input type="hidden" name="xydm" value="${userDep }"/>
									<script type="text/javascript">
										choiceDisabled('xy');
									</script>
								</logic:equal>
								
								<th>
									专业
								</th>
								<td>
									<html:select property="zydm"  styleId="zy" onchange="initBjList()" style="width: 150px">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="bjdm"  styleId="bj" style="width: 150px">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
									</html:select>			
								</td>
							</tr>
							<!-- 第三行 -->
							<tr>
								<th>
									楼栋名	
								</th>
								<td>
									<html:select property="lddm"  styleId="lddm"
										onchange="getLcList()">

										<html:options collection="ldList" property="lddm"
											labelProperty="ldmc" />
									</html:select>	
								</td>
								<th>
									楼层
								</th>
								<td>
									<html:select property="lc"  styleId="lc"
										onchange="getQshList2()">
										<html:options collection="lcList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
								<th>
									寝室号
								</th>
								<td>
									<html:select property="qsh"  styleId="qsh">
										<html:options collection="qshList" property="dm"
											labelProperty="mc" />
									</html:select>	
								</td>
							</tr>
							<!-- 第四行 -->
							<logic:present name="showxbemy">
							<tr>
								<th>
									性别
								</th>
								<td>
									<html:select property="xb1" styleId="xb1">
										<html:option value=""></html:option>
										<html:option value="男">男</html:option>
										<html:option value="女">女</html:option>
									</html:select>
								</td>
								<th>
									民族
								</th>
								<td>
									 <html:select property="mz" style="width:100px" styleId="mz">
										<html:option value=""></html:option>
										<html:options collection="mzList" property="mzdm"
											labelProperty="mzmc" />
									</html:select>			
								</td>
								<th>
									籍贯
								</th>
								<td>
									<html:text property="jg" styleId="jg" style="width:80px"></html:text>
								</td>
							</tr>
							</logic:present>	
							<!-- 第五行 -->
							<tr>
								<th>
									<logic:notEqual name="xxdm" value="11641">
									纪律类别
									</logic:notEqual>
									<logic:equal name="xxdm" value="11641">
									违纪原因
									</logic:equal>
								</th>
								<td>
									<html:select property="wjlbdm" styleId="wjlbdm">
									<html:option value=""></html:option>
									<html:options collection="wjlbList" property="wjlbdm"
										labelProperty="wjlbmc" />
									</html:select>
								</td>
								<th>
									学号
								</th>
								<td>
									<html:text property="xh" style="width: 90px" styleId="xh" />
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="xm" styleId="xm" style="width:80px"></html:text>
								</td>
							</tr>
							<!-- 第六行 -->
							<tr>
								<th>
									时间段
								</th>
								<td>
									<html:text property="gzkssj" 
										onblur="dateFormatChg(this)"
										onclick="return showCalendar('gzkssj','y-mm-dd');"
										style="width:80px;cursor:hand " />
										--
									<html:text property="gzjssj" 
										onblur="dateFormatChg(this)"
										onclick="return showCalendar('gzjssj','y-mm-dd');"
										style="width:80px;cursor:hand " />
								</td>
								<th>
									
								</th>
								<td>
									
								</td>
								<th>
									
								</th>
								<td>
									
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- 过滤条件 end-->
				<!-- 查询结果-->
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
							查询结果&nbsp;&nbsp;<font color="blue">双击一行可以查看详细信息；单击表头可以排序；按ctrl可多选进行删除。</font> 
						</span>
					</h3>
						<table summary="" class="dateline" align="" width="100%">
							<!-- 表头 -->
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<!-- 表头 end-->
							<!--内容 -->
							<logic:iterate name="rs" id="s">
								<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
									ondblclick="viewMore('view')">
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<!--内容 end-->
						</table>
						<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
						<script type="text/javascript">
							$('choose').className="hide";
						</script>
					</logic:notEmpty>
				</div>
				<!-- 查询结果 end-->
			</div>
		</html:form>
		<logic:present name="msg">
			<input type="hidden" id="msg" value="${msg }"/>
			
			<script type="text/javascript">
				alert($('msg').value);
			</script>
			
		</logic:present>		
	</body>
</html>
