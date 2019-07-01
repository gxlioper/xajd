<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%--<%@ include file="/syscommon/pagehead_V4.ini"%>--%>
	<%@ include file="/syscommon/head.ini"%>
	<script type='text/javascript' src='/xgxt/dwr/interface/exportData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script language="javascript">
		function gxzjbxx(url,doType){
			url+="&doType="+doType;
			showTips('处理数据中，请等待......');
			refreshForm(url);
		}
		
		function choiceDisabled(){
			var userType = $('userType').value;
			
			if('stu' == userType){
				$('xhs').disabled = true;
			}else if('xy' == userType){
				$('xy').disabled = true;
			}
		}
	</script>
	</head>
	
	<body onload="choiceDisabled();">
		<html:form action="/czxxDtjsDyxx" method="post">
			<input type="hidden" id="realTable" name="realTable" value="${realTable }" />
			<input type="hidden" id="tableName" name="tableName" value="${tableName }" />
			<input type="hidden" id="title" name="title" value="${title }" />
			<input type="hidden" id="writeAble" name="writeAble" value="${writeAble }" />
			<input type="hidden" name="xyV" id="xyV" value="" />
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="njV" id="njV" value="" />
			<input type="hidden" id="isFdy" name="isFdy" value="<bean:write name="isFdy" scope="session"/>"/>
			<input type="hidden" id="userName" name="userName" value="<bean:write name="userName" scope="session"/>"/>
			<input type="hidden" id="isBzr" name="isBzr" value="<bean:write name="bzrQx" scope="session"/>" />
			<input type="hidden" id="userType" name="userType" value="${userType }" />
			
			<logic:equal name="userType" value="stu">
				<input type="hidden" name="xh" value="${userName }" />
			</logic:equal>
			
			<logic:equal name="userType" value="xy">
				<input type="hidden" name="xydm" value="${userDep }" />
			</logic:equal>
			
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			
			<logic:notEmpty name="msg">
				<div align="center">
					<font color="red" size="6"><bean:write name="msg" /></font>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
				<div class="rightcontent">
						<logic:equal value="yes" name="writeAble">
						<div class="toolbox">
						<div class="buttonbox">
							<ul>
								<li><a href="#" class="btn_zj" onclick="showTopWin('/xgxt/czxxDtjsDyxx.do?method=zsdyUpdate',800,600);return false;">增加</a></li>
								<li><a href="#" class="btn_xg" onclick="showInfo('/xgxt/czxxDtjsDyxx.do?method=zsdyUpdate','update',800,600);return false;">修改</a></li>
								<li><a href="#" class="btn_sc" onclick="sumitInfo('/xgxt/czxxDtjsDyxx.do?method=zsdyManage','del');return false;">删除</a></li>
						
								<li><a href="#" class="btn_dr" onclick="impAndChkData();return false;">导入</a></li>
								<li><a href="#" class="btn_dc" onclick="dataExport();return false;">导出</a></li>
								<logic:present name="sfxs_gxzjbxx">
									<logic:equal value="是" name="sfxs_gxzjbxx">
										<li><a href="#" class="btn_dc" onclick="gxzjbxx('/xgxt/czxxDtjsDyxx.do?method=zsdyManage','gxzjbxx');return false;">更新至基本信息</a></li>
									</logic:equal>
								</logic:present>
							</ul>
						</div>
						</div>
						</logic:equal>
						
<!--==================================================================================================-->
					<div class="searchtab">
						<table width="100%" border="0">
						<tfoot>
		        		<tr>
		          			<td colspan="6">
		            		<div class="btn">
		              		<input type="hidden" name="go" value="a" />
		              		<button type="button" class="btn_cx" id="search_go" 
		              		onclick="allNotEmpThenGo('/xgxt/czxxDtjsDyxx.do?method=zsdyManage');">
		              		查 询
		              		</button>
		             		 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
		              			重 置
		             		 </button>
		            		</div>
		          		</td>
		       			</tr>
		     			</tfoot>
		              	<tbody>
		              	<tr>
		              		<th>年级</th>
		              		<td><html:select property="nj" style="width: 60px" onchange="initZyList();initBjList()" styleId="nj">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj" labelProperty="nj" />
										</html:select></td>
							<th>学年</th>
							<td><html:select property="xn" style="width: 120px" styleId="xn"
											onchange="">
											<html:options collection="xnList" property="xn" labelProperty="xn" />
										</html:select>
							</td>
							<th>年度</th>
							<td><html:select property="nd" style="width: 60px" styleId="nd"
											onchange="">
											<html:options collection="ndList" property="nd" labelProperty="nd" />
										</html:select></td>
							
		              	</tr>
		             	<tr>
		             		<th>学期</th>
							<td><html:select property="xq" style="width: 60px" onchange="" styleId="xq">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
										</html:select></td>
							<logic:equal name="xxdm" value="11057">
								<th>状态</th>
							</logic:equal>
							<logic:notEqual name="xxdm" value="11057">
								<th>在职状态</th>
							</logic:notEqual>
							<td><html:select property="zzzt" style="width:60px" styleId="zzzt">
											<html:option value="all">&nbsp;</html:option>
											<html:option value="yes">是</html:option>
											<html:option value="no">否</html:option>
										</html:select></td>
							<th><bean:message key="lable.xsgzyxpzxy" /></th>
							<td colspan="2"><html:select property="xydm" style="width: 150px" styleId="xy" onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select></td>
		             	</tr> 	
		         		<tr>
		         			<th>专业</th>
							<td><html:select property="zydm" style="width: 150px" styleId="zy" onchange="initBjList()">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select></td>
		         			<th>班级</th>
		         			<td><html:select property="bjdm" style="width:150px" styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select></td>
										<th></th><td></td>
		         		</tr>
		         		<tr>
		         			<th>学号</th>
		         			<td><html:text property="xh" style="" maxlength="20" styleId="xhs"/></td>
		         			<th>姓名</th>
		         			<td><html:text property="xm" style="" maxlength="20" styleId="xm"/></td>
		         			<th></th><td></td>
		         		</tr>
		              	</tbody>
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
			  <table summary="" class="dateline" width="100%">
			    <thead>
			      <tr>
			        <td>
						<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
					</td>
					<logic:iterate id="tit" name="topTr" offset="1">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)">
												<bean:write name="tit" property="cn" />
											</td>
					</logic:iterate>
			      </tr>
			    </thead>
			    <tbody>
			      <logic:iterate name="rs" id="s" indexId="index">
				      <tr onclick="rowOnClick(this);" style="cursor:hand" 
						ondblclick="showInfo('/xgxt/czxxDtjsDyxx.do?method=zsdyUpdate','view',800,600)">
					<td align="center">
						<input type="checkbox" id="checkVal" name="checkVal" 
							value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
					</td>
					<logic:iterate id="v" name="s" offset="1">
						<td align="left">
						<bean:write name="v" />
						</td>
					</logic:iterate>
				    </tr>
			      </logic:iterate>
			    </tbody>
			  </table>
			<div id="tmpdiv1"></div>
			  <!--分页显示-->
			     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=czxxDtjsForm"></jsp:include>
			  <!--分页显示-->
			  </logic:notEmpty>
			</div></div>	
			</logic:empty></html:form>
					
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
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
