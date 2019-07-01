<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/cqkjFunc.js'></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script type="text/javascript">
		function printYyhz(){
			var xn = document.getElementById('xn').value;
			var xq = document.getElementById('xq').value;
			if(xn == null || xn == '' || xq == null || xq == ''){
				alert('学年，学期为必选！');
				return false;
			}
			document.forms[0].action = "/xgxt/qgzxNblg.do?method=printYyhz";
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		
		//学生学号链接
		function queryOne(xh) {
			var url = "/xgxt/stu_info_details.do?xh="+xh;
			showTopWin(url, 800, 600);
		}
		
		//增加
		function add(){
			var xxdm = document.getElementById('xxdm').value;
			if(xxdm == '10402'){//漳州师范<bean:message key="lable.xsgzyxpzxy" />
				showTopWin('qgzxZzsf.do?method=addXsgwxx&type=add',700,500);
			}else{
				viewMore('add');
			}
		}
		//修改
		function modi(){
			var xxdm = document.getElementById('xxdm').value;
			if(xxdm == '10402'){//漳州师范<bean:message key="lable.xsgzyxpzxy" />
				if(curr_row == null){
					alert('请选择一行您要修改的记录！');
					return false;
				}
				var xmdm =  curr_row.cells[1].getElementsByTagName("input")[0].value;
				showTopWin('qgzxZzsf.do?method=modiXsgwxx&type=modi'+ '&xmdm='+xmdm,700,500);
			}else if(xxdm == '10628#'){//西昌学院
				if(curr_row == null){
					alert('请选择一行您要修改的记录！');
					return false;
				}
				var xmdm =  curr_row.cells[1].getElementsByTagName("input")[0].value;
				showTopWin('qgzxXcxy.do?method=modiXsgwxx&type=modi'+ '&xmdm='+xmdm,700,500);
			}else{
				if (curr_row == null) {
					alert("请选择要修改的数据！\n（单击相应的行）");
					return false;
				}
				if(xxdm=='11654'){
					if(trim(curr_row.cells[10].innerHTML)=='通过' || trim(curr_row.cells[11].innerHTML)=='通过' || trim(curr_row.cells[12].innerHTML)=='通过'){
						alert('该条申请记录正在审核，不能修改！');
						return false;
					}
				}
				//浙江交通职业技术学院
				if(xxdm=='12036'){
					if(trim(curr_row.cells[10].innerHTML)=='通过' || trim(curr_row.cells[11].innerHTML)=='通过' || trim(curr_row.cells[12].innerHTML)=='通过'){
						alert('该条申请记录正在审核，不能修改！');
						return false;
					}
				}
				
				var url = "/xgxt/post_stu_apply.do?realTable=";
				var tmp = document.forms[0].realTable.value;
				var pkValue = curr_row.getElementsByTagName("input")[0].value;
				url += tmp;
				url += "&doType=modi";
				url += "&tableName=";
				url += document.forms[0].tableName.value;
				url += "&pk=";
				url += document.forms[0].pk.value;
				url += "&from=";
				url += document.forms[0].act.value;
				url += "&pkValue=";
				url += pkValue;
				showTopWin(url,800,600);
				//viewMore('modi');
			}
		}
		//删除
		function dataDel(url){
			var xxdm = document.getElementById('xxdm').value;
			var RowsStr="!!";	
			var realTable = document.getElementById('realTable').value;	
			var mes = "确定要操作所选记录？";
			var shzt = '';
			for (i=0; i<document.getElementsByName("pkV").length; i++){
		    	if(document.getElementsByName("pkV")[i].checked){
		    		RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
		    		//武汉商业
		    		if(xxdm=='11654'){
			    		shzt += trim(document.getElementsByName("pkV")[i].parentNode.parentNode.childNodes[10].innerHTML);
			    		shzt += trim(document.getElementsByName("pkV")[i].parentNode.parentNode.childNodes[11].innerHTML);
			    		shzt += trim(document.getElementsByName("pkV")[i].parentNode.parentNode.childNodes[12].innerHTML);
		    		}
		    		//浙江交通职业技术学院
		    		if(xxdm=='12036'){
		    			shzt += trim(document.getElementsByName("pkV")[i].parentNode.parentNode.childNodes[10].innerHTML);
			    		shzt += trim(document.getElementsByName("pkV")[i].parentNode.parentNode.childNodes[11].innerHTML);
			    		shzt += trim(document.getElementsByName("pkV")[i].parentNode.parentNode.childNodes[12].innerHTML);
		    		}
		    	}
			}
			if (RowsStr=="!!"){
				alert("请选择要批量操作的记录！");
				return false;
			}
			//武汉商业
			if(xxdm=='11654'){
				if(shzt.indexOf('通过')>=0){
					alert('选择申请记录中有部分正在审核，不能删除！');
					return false;
				}
			}
			//浙江交通职业技术学院
			if(xxdm=='12036'){
				if(shzt.indexOf('通过')>=0){
					alert('选择申请记录中有部分正在审核，不能删除！');
					return false;
				}
			}
			
			if(realTable != null && "gwxxb" == realTable){
				mes = "删除岗位将删除岗位下的所有学生信息，确定删除吗？";
			}
			
			if (!confirm(mes)){
				return false;
			}
			
			url += "&pkString=";
			url += RowsStr;
			url += "&page=";
			url += "page";//跳转到岗位查询页面
			refreshForm(url);
		}
		
		//打印报表
		function printBb(url){
			
			var len = jQuery("[name=pkV]:checked").length;
			
			if(len!=1){
				alertInfo("请勾选一条需要打印的记录!");
			}else{
				
				var pkValue=jQuery("[name=pkV]:checked").val();
				
				var ie = 'ie';
				
				url+="&pkValue="+pkValue;
				
				if(ie == "5.6"){
					confirmInfo("您的IE版本未IE6，版本过低，可能导致打印样式问题，建议使用IE8，请问是否要在官网进行升级（取消可以继续用本浏览器打印）",askInfo);
				}else{
					document.forms[0].action = url;
					document.forms[0].target = "_blank";
					document.forms[0].submit();
					document.forms[0].target = "_self";
					
				}
				
			}
		}
		
		
	</script>
</head>
<body   
	<logic:equal value="13108" name="xxdm">onload="xyDisabled('xy-xy');"</logic:equal><logic:equal value="11654" name="xxdm">onload="xyDisabled('xy');"</logic:equal> >
	<html:form action="/qgzxLogic.do" method="post">
		
		<logic:equal value="11654" name="xxdm" scope="session">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="act" name="act" value="<bean:write name="act" scope="request"/>" />
		</logic:equal>
		<logic:notEqual value="11654" name="xxdm" scope="session">
			<!-- 浙江交通职业技术学院 -->
			<logic:equal value="12036" name="xxdm" scope="session">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="act" name="act" value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" id="isFdy" name="isFdy" value="${FdyQx}" />
			<input type="hidden" id="isBzr" name="isBzr" value="${BzrQx}" />
			</logic:equal>
			<!-- 浙江交通职业技术学院end -->
			<logic:notEqual value="12036" name="xxdm" scope="session">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType"/>" />
			<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="act" name="act" value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk" value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="xxdm" name="xxdm" value="<bean:write name="xxdm"/>" />
			<input type="hidden" id="userName" name="userName" value="<bean:write name="userName" scope="session"/>" />
			<input type="hidden" id="isFdy" name="isFdy" value="${FdyQx}" />
			<input type="hidden" id="isBzr" name="isBzr" value="${BzrQx}" />
			</logic:notEqual>
		</logic:notEqual>
		
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em>
				<a>
					${title}<logic:empty name="title"> 勤工助学-申请-申请结果查询</logic:empty>
				</a>
			</p>
		</div>
		<logic:equal value="view_xslxfszsxx" name="tableName">
			<logic:equal value="stu" name="userType">
				此页面只有学校和<bean:message key="lable.xsgzyxpzxy" />用户可以访问
			</logic:equal>
		</logic:equal>

		<div class="toolbox">
		  <!-- 按钮 -->
		  <div class="buttonbox">
		    <ul>
				<logic:equal value="yes" name="writeAble">
				<!--非广州大学-->
				<logic:notEqual value="11078" name="xxdm">
					<li> <a href="#" onclick="add()" class="btn_zj">增 加</a> </li>
					<li> <a href="#" onclick="modi()" class="btn_xg">修 改</a> </li>
				</logic:notEqual>
				<!--end非广州大学-->
				<logic:equal value="11078" name="xxdm">
					<li> <a href="#" onclick="dataDel('qgzxLogic.do?method=modiSfyxFlag')" class="btn_sz">标记为无效</a> </li>
				</logic:equal>
					<li> <a href="#" onclick="dataDel('qgzxLogic.do?method=delStuPost')" class="btn_sc">删 除</a> </li>
				<logic:notEqual value="no" name="xydel">
<!--					<li> <a href="#" onclick="Alldel()" class="btn_sc">全部删除</a> </li>-->
				</logic:notEqual>
				<%--杭州职业技术学院--%>
				<logic:equal value="12872" name="xxdm">
					<logic:notEqual value="xy" name="userType">
						<li> <a href="#" onclick="impAndChkData()" class="btn_dr">导入数据</a> </li>
					</logic:notEqual>
				</logic:equal>
				<%--end杭州职业技术学院--%>
				<logic:notEqual value="12872" name="xxdm">
					<li> <a href="#" onclick="impAndChkData()" class="btn_dr">导入数据</a> </li>
				</logic:notEqual>
				<li> <a href="#" onclick="showExportDIV('/xgxt/expData.do');" class="btn_dc">导出数据</a> </li>
				<%--湖南工业大学勤工助学--%>
				<logic:equal value="11535" name="xxdm">
				<logic:equal value="xx" name="userType1">
					<li> <a href="#" onclick="getExcel()" class="btn_dc">花 名 册</a> </li>
					<li> <a href="#" onclick="modiBzInfo()" class="btn_xg">修改备注</a> </li>
				</logic:equal>
				<logic:equal value="admin" name="userType1">
					<li> <a href="#" onclick="getExcel()" class="btn_dc">花 名 册</a> </li>
					<li> <a href="#" onclick="modiBzInfo()" class="btn_xg">修改备注</a> </li>
					
				</logic:equal>
				</logic:equal>
				<%--end湖南工业大学勤工助学--%>
				</logic:equal>

				<%--浙江大学宁波理工学院--%>					
				<logic:equal name="xxdm" value="13022">
					<li> <a href="#" onclick="" class="btn_sz">人员汇总表</a> </li>
				</logic:equal>
				<logic:equal name="xxdm" value="66666">
					<li> <a href="#" onclick="printBb('qgzxGxls.do?method=printQgzxsq')" class="btn_dy">打 印</a> </li>
				</logic:equal>
		    </ul>
		  </div>
		  <!--查询条件-->
		  <logic:notEqual value="student" name="userOnLine" scope="session">
		  <div class="searchtab">
		    <table width="100%" border="0">
		      <tfoot>
		        <tr>
		          <td colspan="6">
		            <div class="btn">
		              <input type="hidden" name="go" value="a" />
					  <button type="button" class="btn_cx" id="search_go"
							onclick="allNotEmpThenGo('qgzxLogic.do?method=searchXsgwxx');">
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
					<th>年级</th>
					<td>
						<html:select property="nj" onchange="initZyList();initBjList()" styleId="nj" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj" labelProperty="nj" />
						</html:select>
					</td>			      					
					<th>学号</th>
					<td>
						<html:text property="xh" style="width:180px"></html:text>
					</td>
					<th>姓名</th>
					<td>
						<html:text property="xm" style="width:180px"></html:text>
					</td>
				</tr>
				<tr>
		      		<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						<logic:equal value="xy" name="userType">
							<logic:equal value="true" name="isFdy">
								<logic:equal value="true" name="isBzr">
									<html:select property="xydm" style="width:180px" styleId="xy"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</logic:equal>
								<logic:notEqual value="true" name="isBzr">
									<html:select property="xydm" style="width:180px" styleId="xy"
										onchange="initZyList();initBjList()">
										<html:option value="" ></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</logic:notEqual>
							</logic:equal>
							<logic:notEqual value="true" name="isFdy">
								<logic:equal value="true" name="isBzr">
									<html:select property="xydm" style="width:180px" styleId="xy"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm" 
											labelProperty="xymc" />
									</html:select>
								</logic:equal>
								<logic:notEqual value="true" name="isBzr">
									<html:select property="xydm" style="width:180px" styleId="xy"
								onchange="initZyList();initBjList()" disabled="true">
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								<input type="hidden" name="xydm" id="xydm" value="<bean:write name="userDep1"/>"/>
								</logic:notEqual>
							</logic:notEqual>
						</logic:equal>
						<logic:notEqual value="xy" name="userType">
							<html:select property="xydm" style="width:180px" styleId="xy"
								onchange="initZyList();initBjList()">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						</logic:notEqual>
						
					</td>
					<logic:notEqual value="xyhj" name="xyhjType">
					<th>专业</th>
					<td>
						<!-- 武汉商业辅导员时控制所管辖数据范围 -->
						<logic:equal value="11654" name="xxdm" scope="session">
							<html:select property="zydm"  styleId="zy" style="width:200px"
								onchange="initBjList();">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
						</logic:equal>
						<logic:notEqual value="11654" name="xxdm" scope="session">
							<!-- 浙江交通职业技术学院 -->
							<logic:equal value="12036" name="xxdm" scope="session">
								
								<html:select property="zydm"  styleId="zy" style="width:200px"
									onchange="initBjList();">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</logic:equal>
							<!-- 浙江交通职业技术学院 end -->
							<logic:notEqual value="12036" name="xxdm" scope="session">
								<html:select property="zydm" style="width:180px" styleId="zy"
								onchange="initBjList()">
									<logic:notEqual value="true" name="isFdy">
										<html:option value=""></html:option>
								     </logic:notEqual>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
							</logic:notEqual>
						</logic:notEqual>
					</td>
					<th>班级</th>
					<td>
						<!-- 武汉商业辅导员时控制所管辖数据范围 -->
						<logic:equal value="11654" name="xxdm" scope="session">
							<html:select property="bjdm"  styleId="bj" style="width:200px">
								<logic:notEqual value="yes" name="isBzr">
								<html:option value=""></html:option>
								</logic:notEqual>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</logic:equal>
						<logic:notEqual value="11654" name="xxdm" scope="session">
							<!-- 浙江交通职业技术学院 -->
							<logic:equal value="12036" name="xxdm" scope="session">
								<html:select property="bjdm"  styleId="bj" style="width:200px">
								<logic:notEqual value="yes" name="isBzr">
								<html:option value=""></html:option>
								</logic:notEqual>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
							</logic:equal>
							<!-- 浙江交通职业技术学院 end -->
							<logic:notEqual value="12036" name="xxdm" scope="session">
								<html:select property="bjdm" style="width:180px" styleId="bj">
									<logic:notEqual value="yes" name="isBzr">
										<logic:notEqual value="true" name="isFdy">
										<html:option value=""></html:option>
										</logic:notEqual>
									</logic:notEqual>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</logic:notEqual>
						</logic:notEqual>
					</td>
					</logic:notEqual>
				</tr>
				<tr>
					<th>用人单位</th>
					<td>
						<html:select property="yrdwdm" styleId="yrdwdm" onchange="loadGwByYrdw(this.value,'gwdm')" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="yrdwList" property="yrdwdm" labelProperty="yrdwmc" />
						</html:select>
					</td>			      					
					<th>岗位名称</th>
					<td>
						<html:text property="gwdm" styleId="gwdm" style="width:180px"></html:text>
					</td>
					<!--广州大学-->
					<logic:equal value="11078" name="xxdm">
					<th>是否有效</th>
					<td>
						<html:select property="sfyx" styleId="sfyx">
						<html:option value=""></html:option>
						<html:option value="有效">有效</html:option>
						<html:option value="无效">无效</html:option>
						</html:select>
					</td>
					</logic:equal>	
					<!--end广州大学-->
					<logic:notEqual value="11078" name="xxdm">
					<th>岗位性质</th>
					<td>
						<html:select property="gwxz" styleId="gwxz">
							<html:option value=""></html:option>
							<html:options collection="gwxzList" property="gwxzdm" labelProperty="gwxzmc"/>
						</html:select>
					</td>
					</logic:notEqual>
				</tr>
				<!--闽江学院-->
					<logic:equal value="10395" name="xxdm">
					<tr>
			      		<th>岗位类别</th>
						<td>
							<html:select property="gwflag" style="width:180px" styleId="gwflag">
								<html:option value=""></html:option>
								<html:option value="xngw">校内岗位</html:option>
								<html:option value="xwgw">校外岗位</html:option>
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
			  </tbody>
			</table>
	      </div>	
		</logic:notEqual>	
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
				<logic:iterate id="tit" name="topTr" offset="1">
					<td id="${tit.en}"
						onclick="tableSort(this)" >
						${tit.cn}
					</td>
				</logic:iterate>
		      </tr>
		    </thead>
		    <tbody>
				<%--中国矿业大学--%>
				<logic:equal value="10290" name="xxdm">
					<logic:iterate name="rs" id="s">
					<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
						ondblclick="viewMore('view');">
						<td>
							<logic:iterate id="v" name="s" offset="0" length="1">
								<input type="hidden" name="" value="${v}"/>
								<input type="checkbox" name="pkV" value=""/>								
							</logic:iterate>
						</td>
						<td>
							<logic:iterate id="v" name="s" offset="0" length="1">
								<input type="hidden" value="${v}" />
							</logic:iterate>
							<logic:iterate id="v" name="s" offset="1" length="1">
								${v}
							</logic:iterate>
						</td>
						<logic:iterate id="v" name="s" offset="2" length="2">
							<td>
								${v}
							</td>
						</logic:iterate>
						<logic:iterate id="v" name="s" offset="4" length="1">
							<td>
								<a href="#" onclick="queryOne('${v}')">${v}</a>
							</td>
						</logic:iterate>
						<logic:iterate id="v" name="s" offset="5">
							<td>
								${v}
							</td>
						</logic:iterate>
					</tr>
				</logic:iterate>
				</logic:equal>	
				<%--end中国矿业大学--%>
				
				<%--非中国矿业大学--%>
				<logic:notEqual value="10290" name="xxdm">
				<!-- 浙江交通职业技术学院 -->
					<logic:equal value="12036" name="xxdm">
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
								ondblclick="viewMore('view');">
								<td>
									<input type="checkbox" <logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate> name="pkV" value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>"/>
								</td>
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="${v}" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="2" length="1">
										${v}
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="3">
									<td>
										${v}
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</logic:equal>
				<!-- 浙江交通职业技术学院end -->
				<logic:notEqual value="12036" name="xxdm">
					<logic:iterate name="rs" id="s">
					<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
						ondblclick="viewMore('view');">
						<td>
							<logic:iterate id="v" name="s" offset="0" length="1">
								<input type="checkbox" name="pkV" value="${v}"/>
							</logic:iterate>
						</td>
						<td>
							<logic:iterate id="v" name="s" offset="0" length="1">
								<input type="hidden" value="${v}" />
							</logic:iterate>
							<logic:iterate id="v" name="s" offset="1" length="1">
								${v}
							</logic:iterate>
						</td>
						<logic:iterate id="v" name="s" offset="2">
							<td>
								${v}
							</td>
						</logic:iterate>
					</tr>
				</logic:iterate>
				</logic:notEqual>
				</logic:notEqual>
				<%--end非中国矿业大学--%>
		    </tbody>			
		</table>
		</div>
		<!--分页显示-->
		   <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
		<!--分页显示-->
		</logic:notEmpty>
		</div>		
		
	<div id="tmpdiv"></div>
	</html:form>
	<logic:equal name="result" value="true">
		<script language="javascript">
     				alert("操作成功！");
  		</script>
	</logic:equal>
	<logic:equal name="result" value="false">
		<logic:notEmpty name="mes">
			<input name="mes" id="mes" value="${mes}" />
			<script>
				alert(document.getElementById('mes').value);
			</script>
		</logic:notEmpty>
		<logic:empty name="mes">
			<script language="javascript">
  				alert("操作失败! ");
  		</script>
		</logic:empty>
	</logic:equal>
	<!--页面最下面：-->
	<script language="javascript"  defer="defer">
		var b = false;;
		if(document.getElementById('rsTable')){
			for (i=0; i<document.getElementById("rsTable").rows[0].cells.length; i++){
		  		if(document.getElementById("rsTable").rows[0].cells[i].id == "xh"){
		  			b = true;
		  			break;
		  		}
		  	}
		  	if (b) {
		  		for (j=1; j<document.getElementById("rsTable").rows.length; j++){
		  		    var xhTd = document.getElementById("rsTable").rows[j].cells[i];
		  		    var xhStr = xhTd.innerText.replace(/^\s+/g,"").replace(/\s+$/g,"").replace(/\n/g,"");
		  		    var html_content = "<a href=\"javascript:queryOne('"+xhStr+"');\">"+xhStr+"</a>";
		  			xhTd.innerHTML = html_content;
		  		}
		  	}
		}
   </script>
</body>
</html>
