<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
</head>
<body>
	<html:form action="/data_search" method="post">
		<input type="hidden" id="tableName" name="tableName" value="${tableName}" />
		<input type="hidden" id="act" name="act" value="<bean:write name="act" scope="request"/>" />
		<input type="hidden" id="realTable" name="realTable" value="${realTable}" />
		<input type="hidden" id="pk" name="pk" value="${pk}" />
		<input type="hidden" id="dxq" name="dxq" value="${writeAble}" />

		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${tips}</a>
			</p>
		</div>
		<div class="toolbox">	
			<!-- 按钮 -->
			  <div class="buttonbox">
			    <ul>
					<!--读写权-->
					<logic:equal value="yes" name="writeAble">
						<li> <a href="#" onclick="refreshForm('work_outlay_add.do')" class="btn_zj">增 加</a> </li>
						<li> <a href="#" onclick="viewMore('modi')" class="btn_xg">修 改</a> </li>
						<li> <a href="#" onclick="viewMore('del')" class="btn_sc">删 除</a> </li>
						<li> <a href="#" onclick="impAndChkData();" class="btn_zj">导入数据</a> </li>
					</logic:equal>
					<!--end读写权-->
					<li> <a href="#" onclick="dataExport()" class="btn_dc">导出数据</a> </li>
			    </ul>
			  </div>			 
			 <!--查询条件-->
			<div class="searchtab">
		    <table width="100%" border="0">
		      <tfoot>
		        <tr>
		          <td colspan="6">
		            <div class="btn">
					  	<input type="hidden" name="go" value="a" />
						<button type="button" class="btn_cx" 
								id="search_go"
								onclick="allNotEmpThenGo('/xgxt/work_outlay_search.do?act=workOutlay')">
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
						<html:select property="xn" style="width:180px" styleId="xn">
							<html:option value=""></html:option>
							<html:options collection="xnList" property="xn"
												labelProperty="xn" />
						</html:select>
					</td>
					<th>年度</th>
					<td>
						<html:select property="nd" style="width:180px" styleId="nd">
							<html:option value=""></html:option>
							<html:options collection="xnList" property="nd"
												labelProperty="nd" />
						</html:select>	
					</td>	
					<th>学期</th>
					<td>
						<html:select property="xq" style="width:180px">
						<html:option value=""></html:option>
							<html:options collection="xqList" property="xqdm"
								labelProperty="xqmc" />
						</html:select>
					</td>				
				</tr>						
				<tr>
					<th>用人单位</th>
					<td>
						<html:select property="yrdwdm" style="width:180px" styleId="yrdwdm">
							<html:option value=""></html:option>
							<html:options collection="yrdwList" property="yrdwdm"
								labelProperty="yrdwmc" />
						</html:select>
					</td>
					<th>划拨类别</th>
					<td>
						<html:select property="hblb" style="width:180px" styleId="hblb">
							<html:option value=""></html:option>
							<html:option value="常规">常规</html:option>
							<html:option value="增拨">增拨</html:option>
						</html:select>
					</td>
					<logic:present name="showbjlh">
					<th>查询方式</th>
					<td>
						<html:select property="cxfs" style="width:180px" styleId="cxfs">
							<html:option value="详细">详细</html:option>
							<html:option value="汇总">汇总</html:option>
						</html:select>
					</td>
					</logic:present>
					<logic:notPresent name="showbjlh">
					<th></th>
					<td></td>
					</logic:notPresent>
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
					<font color="blue">提示：单击表头可以排序！</font> 
		 		 </logic:notEmpty>
		    </span>
		    </h3>
			<logic:notEmpty name="rs">
			  <div class="con_overlfow">
			  <table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
			    <thead>
			      <tr>
				   <logic:iterate id="tit" name="topTr" offset="1">
						<td id="<bean:write name="tit" property="en"/>"
							onclick="tableSort(this)">
							<bean:write name="tit" property="cn" />
						</td>
					</logic:iterate>
			      </tr>
			    </thead>
			    <tbody>
					<logic:iterate name="rs" id="s">
						<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">
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
			   </div>
				<!--分页显示-->
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
			    <!--分页显示-->
					<script type="text/javascript">
						$('choose').className="hide";
					</script>
			</logic:notEmpty>
			</div>
	</html:form>
</body>
</html>