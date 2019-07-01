<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script language="javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<script type="text/javascript">
		function dataupdate() {
			if (curr_row == null) {
				alert("没有选择相应记录，请选择之后再进行操作！！");
				return false;
			} else {
				var pk = curr_row.getElementsByTagName('input')[0].value;
				var xh = pk.split("!!split!!")[0];
				var gwdmsbsj = 	pk.split("!!split!!")[1];
				var url = "qgzx_gwsqwh.do?method=xssqUpdate";
				url += "&save_xh=" + xh;
				url += "&gwdmsbsj="+gwdmsbsj;
				url += "&act=sh&gb=yes";
				showTopWin(url,800,600);
			}
		}
		function dataview() {

				var pk = curr_row.getElementsByTagName('input')[0].value;
				var xh = pk.split("!!split!!")[0];
				var gwdmsbsj = 	pk.split("!!split!!")[1];
				var url = "qgzx_gwsqwh.do?method=xssqUpdate";
				url += "&save_xh=" + xh;
				url += "&gwdmsbsj="+gwdmsbsj;
				url += "&act=sh&gb=yes";
				url += "&stuck=yes";
				showTopWin(url,800,600);
	
		}
	</script>
</head>
<body>
	<html:form action="/qgzx_gwsqwh.do" method="post">
		<input type="hidden" name="zyV" id="zyV" />
		<input type="hidden" name="bjV" id="bjV" />
		<input type="hidden" id="userType" name="userType" value="${userType }" />
		<input type="hidden" id="tableName" name="tableName" value="${tableName }" />
		<input type="hidden" id="realTable" name="realTable" value="${realTable }" />
		<input type="hidden" id="pk" name="pk" value="${pk }" />
		<input type="hidden" id="xxdm" name="xxdm" value="${xxdm }" />
		<input type="hidden" id="userName" name="userName" value="${userName }" />
		<input type="hidden" id="isFdy" name="isFdy" value="${isFdy}" />
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>

		<div class="toolbox">
		  <!-- 按钮 -->
		  <div class="buttonbox">
		    <ul>
				<logic:equal value="yes" name="writeAble">
					<logic:notEqual value="stu" name="userType">
						<li> <a href="#" onclick="refreshForm('qgzx_gwsqwh_xssq.do');" class="btn_zj">增 加</a> </li>
						<li> <a href="#" onclick="dataupdate()" class="btn_xg">修 改</a> </li>
						<li> <a href="#" onclick="deletedata('qgzx_gwsqwh_query.do?act=del')" class="btn_sc">删 除</a> </li>
						<li> <a href="#" onclick="impAndChkData()" class="btn_dr">导入数据</a> </li>		
						<li> <a href="#" onclick="showExportDIV('/xgxt/expData.do');" class="btn_dc">导出数据</a> </li>
					</logic:notEqual>
				</logic:equal>
		    </ul>
		  </div>
		  <!--查询条件-->
		  <div class="searchtab">
		    <table width="100%" border="0">
		      <tfoot>
		        <tr>
		          <td colspan="6">
		            <div class="btn">
		              <input type="hidden" name="act" value="query" />
					  <button type="button" class="btn_cx" id="search_go"
							onclick="refreshForm('qgzx_gwsqwh_query.do')">
							查询
					  </button>
					  <button type="button" id="cz"
							onclick="searchReset();return false;">
							重置
					  </button>
		            </div>
		          </td>
		        </tr>
		      </tfoot>
			  <tbody>
				<tr>
					<th>学年</th>
					<td>
						<html:select property="xn" styleId="xn" style="width:180px">
							<html:options collection="xnList" property="xn" labelProperty="xn" />
						</html:select>
					</td>
					<th>年度</th>
					<td>
						<html:select property="nd" styleId="nd" style="width:180px">
							<html:options collection="xnList" property="nd" labelProperty="nd" />
						</html:select>
					</td>
					<th>学期</th>
					<td>
						<html:select property="xq" styleId="xq" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
						</html:select>
					</td>			
				</tr>
		      	<tr>
		      	<logic:notEqual value="stu" name="userType">
					<th>年级</th>
					<td>
						<html:select property="nj" onchange="initZyList();initBjList()" styleId="nj" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj" labelProperty="nj" />
						</html:select>
					</td>			      					
					</logic:notEqual>
					<logic:equal value="stu" name="userType">
						<th>学号</th>
						<td>
							<html:text property="xh" styleId="xh" style="width:180px" maxlength="20" readonly="true"></html:text>
						</td>
						<th>姓名</th>
						<td>
							<html:text property="xm" styleId="xm" style="width:180px" maxlength="20" readonly="true"></html:text>
						</td>
						<th></th>
						<td></td>
					</logic:equal>
					<logic:notEqual value="stu" name="userType">
						<th>学号</th>
						<td>
							<html:text property="xh" styleId="xh" style="width:180px" maxlength="20" ></html:text>
						</td>
						<th>姓名</th>
						<td>
							<html:text property="xm" styleId="xm" style="width:180px" maxlength="20" ></html:text>
						</td>
					</logic:notEqual>
					
				</tr>
				<logic:notEqual value="stu" name="userType">
				<tr>
		      		<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						<html:select property="xydm" style="width:180px" styleId="xy"
							onchange="initZyList();initBjList()">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
						</html:select>
					</td>
					<th>专业</th>
					<td>
						<html:select property="zydm" style="width:180px" styleId="zy"
							onchange="initBjList()">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm"
								labelProperty="zymc" />
						</html:select>
					</td>
					<th>班级</th>
					<td>
						<html:select property="bjdm" style="width:180px" styleId="bj">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm"
								labelProperty="bjmc" />
						</html:select>
					</td>
				</tr>
				</logic:notEqual>
				<tr>
					<th>用人单位</th>
					<td>
						<html:select property="yrdwdm" styleId="yrdwdm" style="width:180px">
							<html:options collection="yrdwList" property="dm" labelProperty="mc" />
						</html:select>
					</td>			      					
					<th>岗位性质</th>
					<td>
						<html:select property="gwxz" styleId="gwxz" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="gwxzList" property="gwxzdm" labelProperty="gwxzmc"/>
						</html:select>
					</td>
					<th>岗位名称</th>
					<td>
						<html:text property="gwdm" styleId="gwdm" style="width:180px"></html:text>
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
					<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序！</font>
				</logic:notEmpty>
		    </span>
		    </h3>
			   
		  <logic:notEmpty name="rs">
		  <div class="con_overlfow">
		  <table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
		    <thead>
		      <tr>
				<td>
					<input type="checkbox" id="all" name="all" onclick="chec()" />
				</td>
				<logic:iterate id="tit" name="topTr" offset="2">
					<td id="${tit.en}"
						onclick="tableSort(this)" >
						${tit.cn}
					</td>
				</logic:iterate>
		      </tr>
		    </thead>
		    <tbody>
				<logic:iterate name="rs" id="s">
					<tr onclick="rowMoreClick(this,'',event);" ondblclick="dataview()" style="cursor: hand">
										<td>
											<input type="checkbox" id="cbv" name="primarykey_cbv"
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"
												<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>/>
										</td>
										<logic:iterate id="v" name="s" offset="2">
											<td>
												${v }
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
		    </tbody>			
		</table>
		</div>
		<!--分页显示-->
		   <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=qgzxXsgwsqForm"></jsp:include>
		<!--分页显示-->
		</logic:notEmpty>
		</div>		
		
		<!-- 提示信息 -->
		<logic:present name="flag">
			<script>
				alert("${flag}");
			</script>
		</logic:present>
	</html:form>
</body>
</html>
