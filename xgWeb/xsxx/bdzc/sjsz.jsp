<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
	<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
	<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript" src="js/xsxx/bdzc.js"></script>
</head>
	<body onload="xyDisabled('xy');">		
		<html:form action="/bdzcgl" method="post">
			<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }" />
			<input type="hidden" id="realTable" name="realTable" value="${realTable }" />
			<input type="hidden" id="tableName" name="tableName" value="view_xsxx_bdzc_sjsz" />
			<input type="hidden" id="userType" name="userType" value="${userType }" />
			<input type="hidden" id="userName" name="userName" value="${userName }" />
			<input type="hidden" id="message" name="message" value="${message }" />
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>			
			
			<input type="hidden" name="isComm" value="true"/>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title}</a>
				</p>
			</div>
			<div class="toolbox">
				  <!--读写权-->
				  <logic:equal value="yes" name="writeAble">
				  <!-- 按钮 -->				  
				  <div class="buttonbox">
					   <ul>
							<li> <a href="#" onclick="refreshForm('/xgxt/bdzcgl.do?method=sjsz&doType=save')" class="btn_ccg">保存</a> </li>
							<li> <a href="#" onclick="showPlszDiv();" class="btn_sz">批量设置</a> </li>
							<li> <a href="#" onclick="if (confirm('你确定要初始化吗?')){showTips('处理数据中，请等待......'); refreshForm('/xgxt/bdzcgl.do?method=sjsz&doType=tb')}" class="btn_csh">注册初始化</a> </li>
							<li> <a href="#" onclick="impAndChkData();" class="btn_dr">导入数据</a> </li>
							<li> <a href="#" onclick="configureExportData();return false;" class="btn_dc">导出数据</a> </li>
					  		<li>
								<a href="#" class="btn_qx" onclick="choiceFields();return false;" id="btn_qx">导出字段确认</a>
							</li>
					   </ul>
				  </div>
			      </logic:equal>

				  <logic:notEqual value="student" name="user">
				  <div class="searchtab">
					<table width="100%" border="0">
				      <tfoot>
				        <tr>						  
				          <td colspan="6">
				            <div class="btn">
							  	<input type="hidden" name="go" value="a" />
								<button type="button" id="search_go"
									onclick="allNotEmpThenGo('/xgxt/bdzcgl.do?method=sjsz&doType=query')">
									查询
								</button>
				            </div>
				          </td>
				        </tr>
				      </tfoot>
					  <tbody>
					  <tr>
						<th>学年</th>
						<td>
							<html:select property="queryequals_xn" value="${xn }" disabled="true" style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="xnList" property="xn" labelProperty="xn"/>
							</html:select>
						</td>
						<th>学期</th>
						<td colspan="3">
							<html:select property="queryequals_xq" value="${xq }" disabled="true" style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
							</html:select>
						</td>
					  </tr>

					  <tr>
						<th>年级</th>
						<td>
							<html:select property="queryequals_nj" onchange="initZyList();initBjList()" styleId="nj" style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj" labelProperty="nj"/>
							</html:select>
						</td>
						<th>学号</th>
						<td>
							<logic:equal value="stu" name="userType" scope="session">
								<html:text property="querylike_xh" styleId="xh" maxlength="20" style="width:180px" readonly="true" value="${userName }"></html:text>
							</logic:equal>
							<logic:notEqual value="stu" name="userType" scope="session">
								<html:text property="querylike_xh" styleId="xh" maxlength="20" style="width:180px"></html:text>
							</logic:notEqual>					
						</td>
						<th>姓名</th>
						<td>
							<html:text property="querylike_xm" maxlength="20" style="width:180px"></html:text>
						</td>
					  </tr>
					  <tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<html:select property="queryequals_xydm" onchange="initZyList();initBjList()"  styleId="xy" style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						</td>
						<th>专业</th>
						<td>
							<html:select property="queryequals_zydm" onchange="initBjList()"  styleId="zy" style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
						</td>
						<th>班级</th>
						<td>
							<html:select property="queryequals_bjdm"  styleId="bj" style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
					  </tr>
					  </tbody>
					</table>
				</div>
				</logic:notEqual>
				</div>

				<div class="formbox" id="result">
					<h3 class="datetitle_01">
				    <span>
				    	查询结果&nbsp;&nbsp;
				    	<logic:empty name="rs">
							<font color="red">未找到任何记录！</font> 
				 		 </logic:empty>
				    </span>
				    </h3>			 
				<logic:notEmpty name="rs">
				<div class="con_overlfow">
				<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
				<thead>
			      <tr>
			        <td>
						<input type="checkbox" name="cb" onclick="selectAll()" style="height:17.5px"/>
					</td>
					<logic:iterate id="tit" name="topTr" offset="1" scope="request">
						<td id="${tit.en}"
							onclick="tableSort(this)">
							${tit.cn}
						</td>
					</logic:iterate>
			      </tr>
			    </thead>
				<tbody>
			      <logic:iterate name="rs" id="s">
					<tr onclick="rowOnClick(this)" style="cursor:hand;"
						>
						<td align=center>
							<input type="checkbox" id="cbv" name="primarykey_cbv" 
							value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
					    	<input type="hidden" name="pkValue" value="${v }"/>
					    </td>
						<logic:iterate id="v" name="s" offset="1" length="7">
							<td align=center>
								<bean:write name="v" />
							</td>
						</logic:iterate>
						<logic:iterate id="v" name="s" offset="8" length="1">
							<td>
								<input type="text" style="width:90px" name="zckssj" maxlength="20" onblur="dateFormatChg(this)" value="${v }"/>
							</td>
						</logic:iterate>
						<logic:iterate id="v" name="s" offset="9" length="1">
							<td>
								<input type="text" style="width:90px" name="zckjsj" maxlength="20" onblur="dateFormatChg(this)" value="${v }"/>
							</td>
						</logic:iterate>
						<logic:iterate id="v" name="s" offset="10" length="1">
							<td>
								<input type="text" style="width:90px" name="hzcjssj" maxlength="20" onblur="dateFormatChg(this)" value="${v }"/>
							</td>
						</logic:iterate>
					</tr>
				</logic:iterate>
			    </tbody>
				</table>
				</div>
				<!--分页显示-->
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=bdzcForm"></jsp:include>
			 	<!--分页显示-->
				</logic:notEmpty>
				</div>	

				<div id = "sjszDiv" style="display:none">
					<table width='300' class='formlist'>
					<tbody>
						<tr>
							<td align='right'><font color='red'>*</font>注册开始时间:</td>
							<td><input type='text' id='sz_zckssj' onclick='showCalen(this.id);' onblur='dateFormatChg(this)'/></td>
						</tr>
						<tr>
							<td align='right'><font color='red'>*</font>注册结束时间:</td>
							<td><input type='text' id='sz_zcjssj'  onclick='showCalen(this.id)' onblur='dateFormatChg(this)'/></td>
						</tr>
						<tr>
							<td align='right'>缓注册结束时间:</td>
							<td><input type='text' id='sz_hzcjssj'  onclick='showCalen(this.id)' onblur='dateFormatChg(this)'/></td>
						</tr>
						<tr>
							<td align='center' colspan='2'>
								<button type="button" class='button2'  onclick='checkSjsz();'>确&nbsp;&nbsp;定</button> &nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button" class='button2' onclick='hiddenMessage(true,true);'>关&nbsp;&nbsp;闭</button>
						</td>
						</tr>
					</tbody>
					</table>
				</div>		
		</html:form>

		<div id="tempDiv"></div>
		<logic:present name="result">
			<logic:equal value="true" name="result">
				<script>
					alert('操作成功');
					document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
					alert('操作失败');
					document.getElementById('search_go').click();
				</script>
			</logic:equal>
		</logic:present>			
	</body>
</html>