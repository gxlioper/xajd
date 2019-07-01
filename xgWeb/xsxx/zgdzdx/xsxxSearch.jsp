<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>
	<script type="text/javascript" src="js/String.js"></script>
	<script type="text/javascript" src="js/stuinfoFunction.js"></script>
	<script type="text/javascript" src="js/xgutil.js"></script>
	<script>
		//修改、显示详细信息
		function ljsdaUpdate(url,w,h){	
			var pk="";	
			if(curr_row == null ){
				alert("请选择一行记录！\n单击一行即可!");
				return false;
			} 	
					
			pk= curr_row.cells[0].getElementsByTagName("input")[0].value;			
			url+=pk;
			
			if(w==""||w==null){
				w = 800;
			}
			if(h==""||h==null){
				h = 600;
			}
			showTopWin(url,w,h);		
		}
		
		//获取主键
		function chec(){
	      for(i=0;i<document.getElementsByName("pk").length;i++){
	      	document.getElementsByName("pk")[i].checked=document.getElementsByName("xsxx")[0].checked;
	      }
	    }
	   
	       //批量操作
		function batch(url,oper){
			var RowsStr="!!";		
			for (i=0; i<document.getElementsByName("pk").length; i++){
		    	if(document.getElementsByName("pk")[i].checked){
		    		RowsStr+=document.getElementsByName("pk")[i].value+"!!";
		    	}
			}
			document.forms[0].delPk.value = RowsStr;
			
			if (RowsStr=="!!"){
				alert("请选择要批量操作的记录！");
				return false;
			}
			
			if (!confirm("确定要操作所选记录？")){
				return false;
			}
			if(oper=="del"){
				refreshForm(url);
				showTips('处理数据中，请等待......');
			}else{
				url += "&pk=";
				url += RowsStr;
				showTopWin(url,400,300);
			}
		}
		
		//批量操作
		function batchOper(url){
			var RowsStr="!!";		
				for (i=0; i<document.getElementsByName("pk").length; i++){
			    	if(document.getElementsByName("pk")[i].checked){
			    		RowsStr+=document.getElementsByName("pk")[i].value+"!!";
			    	}
				}
				document.forms[0].delPk.value = RowsStr;
				
				if (RowsStr=="!!"){
					alert("请选择要批量操作的记录！");
					return false;
				}
				
				if (!confirm("确定要操作所选记录？")){
					return false;
				}
				
				url += "&pk=";
				url += RowsStr;
				refreshForm(url);	
		}
		
		//判断用户类型
		function check_user(){
			var user=document.all['userType'].value;
			if("xy"==user){
				document.getElementById('xydm').disabled=true;
			}else if("xx"==user){
				document.getElementById('xydm').disabled=false;
			}
		}
		
		//显示更多条件
		function showTr(){
			var xxdm = document.getElementById("xxdm").value;
			var length = 12;
			for(var i=0; i<length; i++){
				if(ele('temDiv'+(i+1))){
					ele('temDiv'+(i+1)).style.display=(ele('temDiv'+(i+1)).style.display =='none')?'':'none';
				}
			}
		}
		
		//获取导出数据条件
		function writeCondition(){
			var xxdm = document.getElementById("xxdm").value;
			ele = 'xh-xm-sfzh-ssbh-jg-byny-nj-xy-zy-bj-mz-zzmm-xb-xjztm-xz-jtcyxm-ksh-xslb-xslx-nfby-ydlbm-xsqrxxbz';
			
			var strs = ele.split('-');			
			var tmp = "";
			for(var i=0; i<strs.length; i++){
				if(document.getElementById(strs[i])){
					if(document.getElementById(strs[i]).value != ''){
						
						if(strs[i] == 'xy'){
							tmp += "@xydm!!" + document.getElementById(strs[i]).value;
						}else if(strs[i] == 'zy'){
							tmp += "@zydm!!" + document.getElementById(strs[i]).value;
						}else if(strs[i] == 'bj'){
							tmp += "@bjdm!!" + document.getElementById(strs[i]).value;
						}else{
							tmp += "@" + strs[i]+ "!!" + document.getElementById(strs[i]).value;
						}
					}
				}
			}
			document.getElementById('condition').value = tmp;
		}
	
		//注册
	   	function allCtrl(lx){
	           var clinStr = "";
	           var pk= "";
	           if(lx == "ztzc"){
	           	clinStr = "此功能将实现：以某年级、<bean:message key="lable.xsgzyxpzxy" />、专业或班级为单位，\n\n将该单位内学生整体注册。\n\n注：该学生必须缴纳完全部费用。";
	           }else if(lx == "ztby"){
	         		clinStr = "此功能将实现：以某年级、<bean:message key="lable.xsgzyxpzxy" />、专业或班级为单位，\n\n将该单位内学生整体移送到毕业生历史库。\n\n注：转移的同时即清空该单位内的当前学生信息。";
	           }
	           var nj = document.forms[0].nj.value;
	           var xy = document.forms[0].xy.value;
	           var zy = document.forms[0].zy.value;
	           var bj = document.forms[0].bj.value;
	           var xymc = "";
	           var zymc ="";
	           var bjmc = "";
	
	           xymc = document.forms[0].xy.options[document.forms[0].xy.selectedIndex].text;
	           zymc = document.forms[0].zy.options[document.forms[0].zy.selectedIndex].text;
	           bjmc = document.forms[0].bj.options[document.forms[0].bj.selectedIndex].text;
	           var confirmTxt = "";
	           
	           if (nj==""&&xy==""
	                     &&zy==""
	                     &&bj==""){
	            alert(clinStr+"\n\n请选择要处理信息的年级、<bean:message key="lable.xsgzyxpzxy" />、专业或班级！");
	            return false;
	           }else{
	               confirmTxt = "将以 ";
	               if (nj!=""&&nj!="null"){
	               confirmTxt += " \'"+nj+"\'级";	        
		        }
		        if (xy!=""&&xy!="null"){
			       confirmTxt += "  \'"+xymc+"\'";	        
		        }
			    if (zy!=""&&zy!="null") {
				   confirmTxt += "  \'"+zymc+"\' 专业";        
			    } 
			    if(bj!=""&&bj!="null"){
			       confirmTxt += " \'"+bjmc+"\' 班级";        
			    }
			    if(lx == "ztzc"){
			    	confirmTxt += " 为单位，注册该单位的全部学生！"; 
			    }else if(lx == "ztby"){
	         			confirmTxt += " 为单位，将该单位的全部学生转移到学生历史库！"; 
				}					        		        
		     }
		  	if(confirm(confirmTxt)){
				if(lx == "ztzc"){
	           		var url = 'xsxxZgdzdx.do?method=stuAllRegister&nj='+nj+'&xy='+xy+'&zy='+zy+'&bj='+bj;
	           		window.open(url);
	           	}else if(lx == "ztby"){
	         			var url = 'xsxxZgdzdx.do?method=stuAllComplete&nj='+nj+'&xy='+xy+'&zy='+zy+'&bj='+bj;
	         			window.open(url);
				}
		    }else{
		       return false;
		    }	   
	    }	       	
	
		//新生入学登记表
	    function xsrxdjb(){
	    	if(curr_row == null ){
				alert("请选择一行记录！\n单击一行即可!");
				return false;
			} 	
			var pkvalue = curr_row.cells[0].getElementsByTagName("input")[0].value;
			var url = "/xgxt/stu_info_add.do?method=dzdxxsrxdjb&pkvalue="+pkvalue;
			 document.forms[0].action = url;
			 document.forms[0].target = "_blank";
			 document.forms[0].submit();
	    }
	    
	    function initXsqrxx(){
	    	if(confirm("初始化后学生是否确认信息为'否'，您确认该操作吗？")){
	    		refreshForm('xsxxZgdzdx.do?method=initXsqrxxbz');
	    	}
	    }
	</script>
</head>
	<body onload="check_user()" style="height:800px">	
		<html:form action="/xsxxZgdzdx.do?method=xsxxSearch" method="post">	
				<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}" />
				<input type="hidden" id="userType" name="userType" value="${userSpeType}" />
				<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx}" />
				<input type="hidden" id="userName" name="userName" value="${userName}"/>
				<input type="hidden" name="jgz" value="" />
				<input type="hidden" name="mes" value="${mes}" />
				<input type="hidden" name="condition" value="" id='condition' />
				<input type="hidden" id="delPk" name="delPk" value="pk" />
				<input type="hidden" name="xyV" value="" />
				<input type="hidden" name="zyV" value="" />
				<input type="hidden" name="bjV" value="" />
				<logic:notEmpty name="sfyby">
					<input type="hidden" id="nfby" value="${sfyby}"/>
					<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName"/>" />
					<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable"/>" />
				</logic:notEmpty>
				<logic:equal value="yes" name="userOper">
					<input type="hidden" id="realTable" name="realTable" value="xsxxb"/>
				</logic:equal>
				<logic:equal value="no" name="userOper">
					<input type="hidden" id="realTable" name="realTable" value="bks_xsjbxx"/>
				</logic:equal>
				
				<div class="tab_cur">
					<p class="location">
						<em>您的当前位置:</em><a>${title}</a>
					</p>
				</div>
				<div class="toolbox">
				  <!-- 按钮 -->				  
				  <div class="buttonbox">
				    <ul>
					  <!--读写权-->
					  <logic:equal value="yes" name="writeAble">
					  <logic:notEmpty name="userOper">
					  <%--非学院用户--%>
						<logic:notPresent name="isXY">
							<li> <a href="#" onclick="showTopWin('stu_info_add.do?method=showStuInfo&oper=add',800,600,false);" class="btn_zj">增 加</a> </li>
							<li> <a href="#" onclick="ljsdaUpdate('stu_info_add.do?method=showStuInfo&oper=update&xh=');" class="btn_xg">修 改</a> </li>
							<li> <a href="#" onclick="batch('stu_info_add.do?method=stuInfoDelete','del');" class="btn_sc">删 除</a> </li>
							<li> <a href="#" onclick="viewTempDiv('毕业信息设置','bycfDiv',450,500);" class="btn_csh">毕业处理</a> </li>
							<li> <a href="#" onclick="viewTempDiv('毕业时间设置','bysjDiv',400,300);" class="btn_csh">毕业时间初始化</a> </li>
							<li> <a href="#" onclick="initXsqrxx();" class="btn_csh">学生确认初始化</a> </li>
							<li> <a href="#" onclick="impAndChkData();" class="btn_dr">导入数据</a> </li>
						</logic:notPresent>
					  </logic:notEmpty>
					  </logic:equal>
						<li> <a href="#" onclick="writeCondition();refreshForm('xsxxgl.do?method=showExportPage');" class="btn_dc">导出数据</a> </li>
						<li> <a href="#" onclick="ljsdaUpdate('stu_info_add.do?method=showStuInfo&type=details&oper=update&xh=',800,600);" class="btn_ck">详细信息</a> </li>
					</ul>
				  </div>
				  <!-- 按钮 -->				  
				  <div class="buttonbox">
					<ul>
						<li> <a href="#" onclick="xsrxdjb();" class="btn_dy">新生入学登记表</a> </li>
					</ul>
				  </div>
				  <div class="searchtab">
					<table width="100%" border="0">
				      <tfoot>
				        <tr>
						  <th nowrap="nowrap">
							<input type="checkbox" name="type" value="type" checked="checked" onclick="showTr()"></input>
							更多条件
						  </th>
				          <td colspan="5">
				            <div class="btn">
							  	<input type="hidden" name="go" value="a" />
								<logic:equal value="yes" name="sfyby">
									<button type="button" class="button2" id="search_go"
										onclick="allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo&sfyby=yes');">
										查询
									</button>
								</logic:equal>
								<logic:notEqual value="yes" name="sfyby">
									<button type="button" class="button2" id="search_go"
										onclick="allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');">
										查询
									</button>
								</logic:notEqual>
				            </div>
				          </td>
				        </tr>
				      </tfoot>
					  <tbody>
					  <tr id="gdDiv1">
						<th>年级</th>
						<td>
							<html:select property="nj"  styleId="nj" style="width:180px"
								onchange="initZyList();initBjList();">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
						</td>
						<th>学号</th>
						<td>
							<html:text style="width:180px" property="xh"
								onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');"
								styleId='xh' />									
						</td>
						<th>姓名</th>
						<td>
							<html:text style="width:180px" property="xm"
								onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');"
								styleId='xm' />
						</td>
					  </tr>
					  <tr id="gdDiv2">
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<html:select property="xydm"  styleId="xy" style="width:180px"
								onchange="initZyList();initBjList();">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						</td>
						<th>专业</th>
						<td>
							<html:select property="zydm"  styleId="zy" style="width:180px"
								onchange="initBjList();">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
						</td>
						<th>班级</th>
						<td>
							<html:select property="bjdm" styleId="bj" style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
					  </tr>
					  <tr id="temDiv1">
						<th>性别</th>
						<td>
							<html:select property="xb" style="width:180px" styleId="xb">
								<html:option value=""></html:option>
								<html:option value="男">男</html:option>
								<html:option value="女">女</html:option>
							</html:select>									
						</td>
						<th>民族</th>
						<td>
							<html:select property="mz"  styleId="mz" style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="mzList" property="mzdm"
									labelProperty="mzmc" />
							</html:select>
						</td>
						<th>政治面貌</th>
						<td>
							<html:select property="zzmm" styleId="zzmm" style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="zzmmList" property="zzmmdm"
									labelProperty="zzmmmc" />
							</html:select>
						</td>
					  </tr>
					  <tr id="temDiv2">
						<th>籍贯</th>
						<td>
							<html:text property="jg" style="width:180px"
								onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');"
								styleId='jg' />
						</td>
						<th>身份证号</th>
						<td>
							<html:text property="sfzh" style="width:180px"
								onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');"
								styleId="sfzh" />
						</td>
						<th>考生号</th>
						<td>
							<html:text property="ksh" styleId="ksh" style="width:180px"/>
						</td>
					  </tr>
					  <tr id="temDiv3">
						<th>学制</th>
						<td>
							<html:text property="xz" style="width:180px"
								onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');"
								styleId="xz" />
						</td>
						<th>学籍状态</th>
						<td>
							<html:select property="xjzt" styleId="xjztm" style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="xjztList" property="zxdmmc" labelProperty="zxdmmc"/>
							</html:select>
						</td>
						<th>宿舍编号</th>
						<td>
							<html:text property="ssbh" style="width:180px"
								onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');"
								styleId='ssbh' />
						</td>
					  </tr>
					  <tr id="temDiv4">
						<th>家庭成员姓名</th>
						<td>
							<html:text style="width:180px" property="jtcyxm" 
								onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');" />
						</td>
						<th>毕业时间</th>
						<td>
							<html:text property="byny" styleId="byny" style="width:180px"/>
						</td>
						<th>学生是否确认</th>
						<td>
							<html:select property="xsqrxxbz" styleId="xsqrxxbz" style="width:180px">
								<html:option value=""></html:option>
								<html:option value="是">是</html:option>
								<html:option value="否">否</html:option>
							</html:select>
						</td>
					  </tr>
					  <tr id="temDiv5">
						<th>学生类别</th>
						<td>	
							<html:select property="xslb" style="width:180px" styleId="xslb" >
								<html:option value=""></html:option>
								<html:options collection="xsLbList" property="dm"
									labelProperty="mc" />
							</html:select>  
						</td>
						<th>学生类型</th>
						<td>
							<html:select property="xslx" style="width:180px" styleId="xslx">
								<html:option value=""></html:option>
								<html:options collection="xsLxList" property="dm"
									labelProperty="mc" />
							</html:select> 
						</td>
						<th>异动类别</th>
						<td>
							<html:select property="ydlbm" styleId="ydlbm" style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="ydlbList" property="dm"
									labelProperty="mc" />
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
							<font color="blue">提示：双击可以查看详细信息！</font>
						</logic:notEmpty>
				    </span>
				    </h3>			 
				<logic:notEmpty name="rs">
				<div class="con_overlfow">
				<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
				<thead>
			      <tr>
			        <logic:equal value="yes" name="userOper">
						<td>
							<input type="checkbox" name="xsxx" value="all"
								onclick="chec()"/>
						</td>
					</logic:equal>

					<logic:iterate id="tit" name="topTr" offset="0" length="1">
						<td id="<bean:write name="tit" property="en"/>"
							onclick="tableSort(this)" nowrap="nowrap">
							<bean:write name="tit" property="cn" />
						</td>
					</logic:iterate>

					<logic:iterate id="tit" name="topTr" offset="2">
						<td id="<bean:write name="tit" property="en"/>"
							onclick="tableSort(this)" nowrap="nowrap">
							<bean:write name="tit" property="cn" />
						</td>
					</logic:iterate>
			      </tr>
			    </thead>
				<tbody>
			      <logic:iterate name="rs" id="s">
					<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
						ondblclick="stuInfoWin(this)">

						<logic:equal value="yes" name="userOper">
							<td align="center">
								<logic:iterate id="v" name="s" offset="0" length="1">
									<input type="checkbox" name="pk"
										value="<bean:write name="v"/>"/>
								</logic:iterate>
							</td>
						</logic:equal>
						<td>
							<logic:iterate id="v" name="s" offset="0" length="1">
								<bean:write name="v" />
							</logic:iterate>
							<input type="hidden" value="<bean:write name="v" />" />
						</td>
						<td>
							<logic:iterate id="v" name="s" offset="2" length="1">
								<bean:write name="v" />
							</logic:iterate>
						</td>
						<logic:iterate id="v" name="s" offset="3">
							<td align="left">
								<bean:write name="v" />
							</td>
						</logic:iterate>						
					</tr>
				</logic:iterate>
			    </tbody>
				</table>
				</div>
				<!--分页显示-->
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xsxxZgdzdxForm"></jsp:include>
			 	<!--分页显示-->
				</logic:notEmpty>
				</div>
				<div id="tmpdiv"></div>
				<logic:notEmpty name="result">
					<logic:equal value="true" name="result">
						<logic:notEmpty name="mes">
							<script>
								alert(document.getElementById('mes').value);
							</script>
						</logic:notEmpty>
						<logic:empty name="mes">
							<script>
								alert("操作成功！");
							</script>
						</logic:empty>
						<script>
							Close();
							document.getElementById('search_go').click();						
						</script>
					</logic:equal>

					<logic:equal name="result" value="false">
						<logic:present name="mes">
							<logic:notEmpty name="mes">
								<script>
									alert(document.getElementById('mes').value);
								</script>
							</logic:notEmpty>
							<logic:empty name="mes">
								<script>
									alert("操作失败!");
								</script>
							</logic:empty>
						</logic:present>
						<logic:notPresent name="mes">
							<script>
								alert("操作失败!");
							</script>
						</logic:notPresent>
						<script>
								document.getElementById('search_go').click();
							</script>
					</logic:equal>
				</logic:notEmpty>

	<div id='bycfDiv' style="display:none">
	<table class='formlist'>
	<tbody>
	<tr>
		<th>
			请选择修改方式：
		</th>
		<td>
			<input type='radio' name='configtype' value='2' checked="checked"/>&nbsp;按条件修改(您在查询栏中选择的学籍条件)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;		
			</br>
			<input type='radio' name='configtype' value='1'/>&nbsp;修改选中的数据(您在查询结果列表中勾选的数据)
		</td>
	</tr>
	</tbody>
	
	<tbody>		
	<thead>
		<tr>
			<th colspan='2'>
				<span>学生毕业相关信息</span>
			</th>
		</tr>
	</thead>	
	<tr>
	<th>
		学籍状态：
	</th>
	<td>
		<html:select property="xjzt" styleId="select_newxjztm" style="width:180px">
			<html:option value=""></html:option>
			<html:options collection="xjztList" property="zxdmmc" labelProperty="zxdmmc"/>
		</html:select>
	</td>
	</tr>
	<!--中国地质大学-->
	<logic:equal value="10491" name="xxdm">
		<tr>
			<th>
				是否在校：
			</th>
			<td>
				<select id='select_newsfzx' name='sfzx'><option></option><option value='在校'>在校</option><option value='不在校'>不在校</option></select>
			</td>
		</tr>
		<tr>
			<th>
				能否毕业：
			</th>
			<td>
				<select id='select_newnfby' name='nfby'><option></option><option value='是'>是</option><option value='否'>否</option></select>
			</td>
		</tr>
		<tr>
			<th>
				是否已毕业：
			</th>
			<td>
				<select id='select_newsfyby' name='sfyby'><option></option><option value='是'>是</option><option value='否'>否</option></select>
			</td>
		</tr>	
	</logic:equal>
	<tr>
		<th>
			是否毕业生：
		</th>
		<td>
			<select id='select_newsfbys' name='sfbys'><option></option><option value='是'>是</option><option value='否'>否</option></select>
		</td>
	</tr>
	<tr>
		<th>
			毕业时间：
		</th>
		<td>
			<input type='text' id='newbyny' name='byny' onclick="return showCalendar('newbyny','y-mm-dd');"/>
		</td>
	</tr>
	</tbody>
	<tfoot>
	<tr>
		<td colspan='2'> 
			<div class='btn'>
				<span class='red'>您输入的与学生毕业相关的信息,请慎重操作！</span>
				<button type="button" onclick='byxxConfig()'>确定</button>
				<button type="button" onclick="hiddenMessage(true,true);">关闭</button>
			</div>
		</td>
	</tr>
	</tfoot>
	</table>
	</div>

	<div id="bysjDiv" style="display:none">
	<table width='350' class='formlist'>
	<thead>
	<tr>
	 <th align='center'>
	  	请选择初始化方式
	 </th>
	</tr>
	</thead>
	<tbody>
	<tr>
		<td>
			<input type='radio' name='configtype2' value='0'checked/>&nbsp;初始化全部&nbsp;&nbsp;&nbsp;&nbsp;
			<input type='radio' name='configtype2' value='2'/>&nbsp;按条件初始化&nbsp;&nbsp;&nbsp;&nbsp;		
			<input type='radio' name='configtype2' value='1'/>&nbsp;初始化选中的数据
		</td>
	</tr>
	</tbody>

	<thead>
	<tr>
	 <th>
	 	 输入毕业月份和日期
	 </th>
	</tr>
	</thead>
	<tbody>
	<tr>
		<td>
			<input type='text' id='yf' name='yf' value='' style='width:80px' maxlength='2' onkeyup="this.value=value.replace(/[^\d]/g,'');if(value>12){ value=12}"/>月<input type='text' id='rq' name='rq' value='' maxlength='2' style='width:80px' onkeyup="this.value=value.replace(/[^\d]/g,'');if(value>31){value=31}"/>日
		</td>
	</tr>
	</tbody>

	</tfoot>
	<tr>
		<td>
			<div class='btn'>
				<button type="button" class='button2' onclick='dataConfig()'>确定</button>&nbsp;&nbsp;
				<button type="button" class='button2' onclick="hiddenMessage(true,true);">关闭</button>
			</div>
		</td>
	</tr>
	</tfoot>
	</table>
	</div>	
</html:form>
	<div id="tmpdiv"></div>
	</body>
</html>

