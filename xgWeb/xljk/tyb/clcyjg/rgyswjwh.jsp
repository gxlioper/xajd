<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	</head>
<body onload="xyDisabled('xy');">
	<html:form action="/xljkXlcyjg.do" method="post">
		<input type="hidden" name="zyV" id="zyV" value="" />
		<input type="hidden" name="bjV" id="bjV" value="" />
		<input type="hidden" name="tableName" value="view_rgysyzdmb"/>
		<input type="hidden" name="realTable" value="dxsrgyscyb"/>
		<input type="hidden" name="userType" id="userType"
			value="${userType }" />
		<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }"/> 
		<input type="hidden" id="userName" name="userName" value="${userName }"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
		<div class="toolbox">
			 <!-- 按钮 -->
			 <logic:equal value="yes" name="writeAble" scope="request">
			 <div class="buttonbox">
			    <ul>
				<li> <a href="#"id="btn_add" onclick="showTopWin('xljk_tyb_addDxsrgyscy.do',800,600)" class="btn_zj"> 增加 </a> </li>
			      <li> <a href="#"  id="btn_xg" onclick="modiAndDel('xljk_tyb_updateDxsrgyscy.do?pkValue=','modi',800,600)" class="btn_xg"> 修改 </a> </li>
				<li> <a href="#" id="btn_sc" onclick="deldata('xljk_tyb_rgyswjwh.do?act=del')" class="btn_sc"> 删除 </a> </li>
				<li> <a href="#"  id="btn_dr" onclick="impAndChkData()" class="btn_dr"> 导入 </a> </li>
				<li> <a href="#" id="btn_dc" onclick="dataExport()" class="btn_dc"> 导出 </a> </li>
			    </ul>
			 </div>
			 </logic:equal>
			<div class="searchtab">
				<table width="100%" border="0">
			      <tfoot>
			        <tr>
			          <td colspan="6">
			            <div class="btn">
			              <button class="btn_cx" id="search_go" 
			              	onclick="refreshForm('xljk_tyb_rgyswjwh.do?act=qry')">
			              	查 询
			              </button>
			              <button class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
			              	重 置
			              </button>
			            </div>
			          </td>
			        </tr>
			      </tfoot>
			
			      <tbody>
					<tr>
						<th align="" nowrap="nowrap">
							学号
						</th>
						<td>
							<html:text property="xh" styleId="xh" styleClass="inputtext"
								style="width:100px" maxlength="20">
							</html:text>
						</td>
						<th>
							姓名
						</th>
						<td>
							<html:text property="xm" styleId="xm" styleClass="inputtext"
								style="width:100px" maxlength="10">
							</html:text>
						</td>
						<th>
							测试时间
						</th>
						<td>
							<html:text property="cssj" styleId="cssj" readonly="true" 
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('cssj','y-mm-dd');"></html:text>
						</td>
					</tr>
					<tr>
						<th align="left" nowrap>
							年级
						</th>
						<td>
							<html:select property="nj" styleId="nj" style="width:80px"
								styleClass="select" onchange="initZyList();initBjList()">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
						</td>
						<th>
							<bean:message key="lable.xsgzyxpzxy" />
						</th>
						<td>
							<html:select property="xydm" style="width:175px"
								styleClass="select" styleId="xy"
								onchange="initZyList();initBjList()">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						</td>
						<th>
							专业
						</th>
						<td>
							<html:select property="zydm" style="width:170px" styleId="zy"
								styleClass="select" onchange="initBjList()">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							班级
						</th>
						<td>
							<html:select property="bjdm" style="width:180px" styleId="bj"
								styleClass="select">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
						<td colspan="4">
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
			 		&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：单击表头可以进行排序；双击一行可以查看详细信息；</font>
			 		 </logic:notEmpty>
			 		
			    </span>
			    </h3>
			 
			<logic:notEmpty name="rs">
					<div class="con_overlfow">
					 <table summary="" class="dateline tablenowrap" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td >
									<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" >
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand;"
								align="center" ondblclick="modiAndDel('xljk_tyb_updateDxsrgyscy.do?act=view&pkValue=','modi',600,480)">
								<td>
									<input type="checkbox" id="cbv" name="cbv"
										value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
								</td>
								<logic:iterate id="v" name="s" offset="1">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
					</div>
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xljkXlcyjgActionForm"></jsp:include>
			</logic:notEmpty>
			</div>
			<div id="tmpdiv"></div>
	</html:form>
		
		<logic:present name="inserted">
			<logic:equal value="yes" name="inserted">
				<script>
					alert('操作成功!');
					document.getElementById('search_go').onclick();
				</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script>
					alert('操作失败!');
					document.getElementById('search_go').onclick();
				</script>
			</logic:equal>
		</logic:present>
</body>
