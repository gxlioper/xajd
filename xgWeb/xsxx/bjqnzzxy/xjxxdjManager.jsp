<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="js/xgutil.js"></script>
	<script language="javascript">
		function modi(url){
			if(curr_row != null){
				showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
				return true;
			}else{
				alert('请选择要修改的数据行！');
				return false;
			}
		}
		
		function printXsdjb(url){
			if(curr_row != null){
				wjcfDataExport(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value);
				return true;
			}else{
				alert('请选择数据打印！');
				return false;
			}
		}
	</script>
</head>
	<body onload="xyDisabled('xy')">
		<html:form action="/xjxxdj.do" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
			<input type="hidden" name="userName" id="userName" value="${userName}" />
			<input type="hidden" name="userType" id="userType" value="${userType}" />
			<input type="hidden" name="realTable" id="realTable" value="${realTable}" />
			<input type="hidden" name="tableName" id="tableName" value="view_${realTable}" />
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
					  		<li> <a href="#" onclick="showTopWin('xjxxdj.do?method=xjxxdjAdd',800,600);" class="btn_zj">增 加</a> </li>
							<li> <a href="#" onclick="modi('xjxxdj.do?method=xjxxdjModi&type=modi');" class="btn_xg">修 改</a> </li>
							<li> <a href="#" onclick="dataBatch('xjxxdj.do?method=xjxxdj&type=del');" class="btn_sc">删 除</a> </li>
							<li> <a href="#" onclick="impAndChkData();" class="btn_dr">导入数据</a> </li>
					  </logic:equal>
						<li> <a href="#" onclick="showExportDIV('xjxxdj.do?method=xjxxdjExp',600,400);" class="btn_dc">导出数据</a> </li>
					</ul>					
				  </div>
				  <div class="searchtab">
					<table width="100%" border="0">
				      <tfoot>
				        <tr>						  
				          <td colspan="6">
				            <div class="btn">
							  	<input type="hidden" name="go" value="a" />
								<button type="button" id="search_go"
									onclick="allNotEmpThenGo('/xgxt/xjxxdj.do?method=xjxxdj')">
									查询
								</button>
								<button type="button" id="cz"
									onclick="czSearchCond('nj-xh-xm-xy-zy-bj');">
									重置
							    </button>
				            </div>
				          </td>
				        </tr>
				      </tfoot>
					  <tbody>
					  <tr>
						<th>年级</th>
						<td>
							<html:select property="queryequals_nj" styleId="nj">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
						</td>
						<th>学号</th>
						<td>
							<logic:equal value="stu" name="userType">
								<html:text property="querylike_xh" readonly="true"></html:text>						
							</logic:equal>
							<logic:notEqual value="stu" name="userType">
								<html:text property="querylike_xh" styleId="xh"></html:text>
							</logic:notEqual>							
						</td>
						<th>姓名</th>
						<td>							
							<html:text property="querylike_xm" styleId="xm"></html:text>		
						</td>
					  </tr>
					  <tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<logic:notEqual value="true" name="fdyQx">
							<logic:equal value="xy" name="userType">							
								<html:hidden property="queryequals_xydm" value="${userDep}"/>
							</logic:equal>
							</logic:notEqual>
							<html:select property="queryequals_xydm" style="width:180px"
								styleId="xy" onchange="initZyList();initBjList();">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						</td>
						<th>专业</th>
						<td>
							<html:select property="queryequals_zydm" style="width:180px" styleId="zy"
								onchange="initBjList()">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>					
						</td>
						<th>班级</th>
						<td>
							<html:select property="queryequals_bjdm" style="width:180px" styleId="bj">
								<logic:notEqual value="yes" name="isBzr">
									<html:option value=""></html:option>
								</logic:notEqual>
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
							<font color="red">未找到任何记录！(由于信息过多，查询可能较慢，请耐心等待！)</font> 
				 		 </logic:empty>
						<logic:notEmpty name="rs">
							<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
						</logic:notEmpty>
				    </span>
				    </h3>			 
				<logic:notEmpty name="rs">
				<div class="con_overlfow">
				<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
				<thead>
			      <tr>
			        <td>
						<input type="checkbox" name="all" value="all" onclick="chec()">
					</td>
					<logic:iterate id="tit" name="topTr" offset="1">
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
						ondblclick="modi('xjxxdj.do?method=xjxxdjModi&type=view')" style="cursor:hand">
						<td>
							<logic:iterate id="v" name="s" offset="0" length="1">
								<input type="checkbox" name="primarykey_cbv" id="pkV" value="<bean:write name="v"/>"/>
							</logic:iterate>
						</td>
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
			    </tbody>
				</table>
				
				<!--分页显示-->
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xsxxdjForm"></jsp:include>
			 	<!--分页显示-->
				</logic:notEmpty>
				</div>
			</div>
		</html:form>

		<logic:present name="result">
		<logic:equal value="true" name="result">
			<script>
				alert('操作成功！');
				document.getElementById('search_go').onclick();
			</script>			
		</logic:equal>
		<logic:notEqual value="true" name="result">
			<script>
				alert('操作失败！');
				document.getElementById('search_go').onclick();
			</script>
		</logic:notEqual>
		</logic:present>
	</body>
</html>
