<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript"
		src="/xgxt/dwr/interface/getStuDetails.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript">
	 function changePage(obj){
	    	var id = obj.parentNode.id;
	    	obj.parentNode.className = 'ha';
	    	var nodeId = obj.id.substring(0,obj.id.length-2);
	    	var elements =$('ul1').children;
	    	for(var i=0; i<elements.length; i++){
	    		if(elements[i].id!= id){
	    			elements[i].className = '';
	    			var trId = elements[i].id.substring(0,elements[i].id.length-2);
	    			if(document.getElementById(trId)){
	    				document.getElementById(trId).style.display = "none";	    			
	    			}
	    		}
	    	}
	    	loadData(nodeId);
	   }
   
   	function loadchange(){
			var tab = document.getElementById("titName").value;
			document.getElementById(tab+"li").className = "ha";
	}
    
     function loadData(id){
	    	refreshForm('bysjbxxb_input.do?titName='+id);
	  }

    
    function add(url){
        
        var xh = document.getElementById("xh").value;
        var xm = document.getElementById("xm").value;
    	var sfzh = document.getElementById("sfzh").value;
    	var zydm = document.getElementById("zydm").value;
    	var zymc = document.getElementById("zymc").value;
    	var xldm = document.getElementById("xldm").value;
    	var sydqdm;
    	if(document.getElementById("sydqdm")){
    	  sydqdm = document.getElementById("sydqdm").value;
    	}
    	var pyfsdm = document.getElementById("pyfsdm").value;
    	var xz = document.getElementById("xz").value;
    	var titName = document.getElementById("titName").value;
    	
    	if(xh==""){
    	alert("学号必须填写！");
    	return false;
    	}
    	if(xm==""){
    	alert("姓名必须填写！");
    	return false;
    	}
    	if(document.getElementById('xscc') && document.getElementById('xscc').value==''){     
    	alert("学生层次必须填写！");
    	return false;
    	}
    	if(zydm==""){
    	alert("专业代码必须填写！");
    	return false;
    	}	
    	if(zymc==""){
    	alert("专业名称必须填写！");
    	return false;
    	}
    	if(xldm==""){
    	alert("学历代码必须填写！");
    	return false;
    	}
    	if(sydqdm==""){
    	alert("生源地区代码必须填写！");
    	return false;
    	}
    	
    	if(pyfsdm==""){
    	alert("培养方式代码必须填写！");
    	return false;
    	}	
    	if(checkSfzh(sfzh)){
		 		document.forms[0].action = "/xgxt/bysjbxxbSave.do?doType=save&act=cancle";
		 		document.forms[0].submit();
        }
    }
    
    function reinputagain(url){
    		
            document.forms[0].action = "/xgxt/bysjbxxbSave.do?act=cancle";
		 	document.forms[0].submit();
    }
    
    function checkSfzh(sfzh) {
    sfzh=sfzh.toUpperCase()
	var OldID;
	if(sfzh.length == 15){
		OldID = sfzh;
		return true;
	}else if(sfzh.length == 18){
		OldID = sfzh.substring(0, 6) + sfzh.substring(8,sfzh.length-1);
	}else{
		alert("请输入正确的身份证号码！","",function(){
			obj.select();
			obj.focus();
		});
		return false;
	}
	var W = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
	var A = new Array("1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2");
	var i, j, S;
	var NewID, ID, strNF;
	NewID = OldID.substring(0, 6) + "19" + OldID.substring(6,OldID.length);
	S = 0;
	for( i = 0; i <= 16; i++){
		j = NewID.substring(i, i+1) * W[i];
		S = S + j;
	}
	S = S % 11;
	if(sfzh != NewID + A[S]){
		alert("请输入正确的身份证号码！","",function(){
			obj.select();
			obj.focus();
		});
		return false;
	}
	return true;
    }

function isCard(s){ 
	s = trim(s); 
	var p = /^\d{15}(\d{2}[xX0-9])?$/; 
	return p.test(s);
}
		   
function isNumber(s){
	var p = /^(-|\+)?\d+$/;
	return p.test(s); 
}

function queryxh(){
	var xh = document.getElementById("xh").value;
	
	 document.forms[0].action = "/xgxt/bysjbxxbSave.do?act=query&xh="+xh;
     document.forms[0].submit();
}
function loadShi(){
	var shen = document.getElementById("jgshen").value;	
	getStuDetails.getShiList(shen,function(data){
		if (data.shiList != null) {
				var objId = "jgshi";				
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);					
					DWRUtil.addOptions(objId,data.shiList,"shidm","shimc");
				}
			}else{
				showMsgWin("有错误出现：远程数据读取失败！");
			}	
		if (data.xianList !=null){
			var objId = "jgxian";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);					
					DWRUtil.addOptions(objId,data.xianList,"xiandm","xianmc");
				}
		}
	});
}
function loadXian(){
	var shi = document.getElementById("jgshi").value;	
	getStuDetails.getXianList(shi,function(data){
		if (data != null) {
				var objId = "jgxian";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);							
					DWRUtil.addOptions(objId,data,"xiandm","xianmc");
				}
			}else{
				showMsgWin("有错误出现：远程数据读取失败！");
			}		
	});
}


	</script>
	</head>
	<body onload="loadchange()">
		<html:form action="/bysjbxxb_input.do" method="post">
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>就业管理 - 学生信息 - 学生信息上报</a>
				</p>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
				    alert("您输入的学号无效!");
				    </script>
				</logic:equal>
				<input type="hidden" id="titName" name="titName"
					value="<bean:write name="titName" scope="request" />" />
				<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb" />
				<input type="hidden" id="url" name="url" value="/bysjbxxb_input.do" />

<%--				<div class="xxk">--%>
<%--					<logic:notEmpty name="pages">--%>
<%--						<logic:iterate id="card" name="pages" scope="request">--%>
<%--							<ul>--%>
<%--								<li id="<bean:write name='card' property='en'/>l"--%>
<%--									class="xxk_off_l"></li>--%>
<%--								<li id="<bean:write name='card' property='en'/>m"--%>
<%--									onclick="changePage(this)" class="xxk_off_m">--%>
<%--									&nbsp;--%>
<%--									<bean:write name='card' property='cn' />--%>
<%--									&nbsp;--%>
<%--								</li>--%>
<%--								<li id="<bean:write name='card' property='en'/>r"--%>
<%--									class="xxk_off_r"></li>--%>
<%--							</ul>--%>
<%--						</logic:iterate>--%>
<%--					</logic:notEmpty>--%>
<%--				</div>--%>
				<div class="compTab" id="card" style="width: 100%">
				<div class="comp_title" id="div1">
				<ul id="ul1">
					<logic:notEmpty name="pages">
						<logic:iterate id="card" name="pages" scope="request" indexId="s">							
							<li id="${card.en}li">
								<a href="#" onclick="changePage(this)" id="${card.en}_a">
									<span>${card.cn}</span>
								</a>
							</li>
						</logic:iterate>						
					</logic:notEmpty>
				</ul>				
				</div>
				</div>
				<div class="tab">
					<table width="80%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>学生信息录入</span>
								</th>
							</tr>
						</thead>
						<tbody>
						<tr>
						<logic:equal name="userOnLine" value="teacher" scope="session">

							<logic:equal name="nochange" value="yes">
								<th>
									<font color="red">*</font>学号
								</th>
								<td align="left">
									<html:text name='rs' property="xh" styleId="xh"
										onkeypress="if(window.event.keyCode==13) queryxh();"
										readonly="true" />
									<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu">
										选择
									</button>
								</td>
							</logic:equal>

							<logic:equal name="nochange" value="no">
								<th>
									<font color="red">*</font>学号
								</th>
								<td>
									<html:text name='rs' property="xh" styleId="xh"
										onkeypress="if(window.event.keyCode==13) queryxh();" />
									<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu">
										选择
									</button>
								</td>
							</logic:equal>



						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<th>
								<font color="red">*</font>学号
							</th>
							<td>
								<html:text property="xh" name="rs" styleId="xh" readonly="true"></html:text>
							</td>
						</logic:equal>
						<th>
							<font color="red">*</font>身份证号
						</th>
						<td align="left">
							<html:text name='rs' property="sfzh" />
						</td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font>姓名
						</th>
						<td align="left">
							<html:text name='rs' property="xm" readonly="true" />
						</td>
						<th>
							<font color="red">*</font>性别
						</th>
						<td align="left">
							<html:hidden name='rs' property="xbm"/>
							<input type="text" value="${rs.xb}" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font>学校代码
						</th>
						<td>
							<html:text name='rs' property="xxdm" readonly="true" />
						</td>
						<th >
							<font color="red">*</font>学校名称
						</th>
						<td >
							<html:text name='rs' property="xxmc" readonly="true" />

						</td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font><bean:message key="lable.xb" />代码
						</th>
						<td>
							<html:text name='rs' property="xydm" readonly="true" />
						</td>
						<th >
							<font color="red">*</font><bean:message key="lable.xb" />名称
						</th>
						<td >
							<html:text name='rs' property="xymc" readonly="true" />

						</td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font>专业代码
						</th>
						<td>
							<html:text name="rs" property="zydm" readonly="true" />
						</td>
						<th>
							<font color="red">*</font>专业名称
						</th>
						<td>
							<html:text name='rs' property="zymc" readonly="true" />
						</td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font>学历
						</th>
						<td>
							<logic:equal name="Listxl" value="zks">
								<html:select name="rs" property="xldm" styleId="xldm"
									style="width:150px">
									<html:option value=""></html:option>
									<html:option value="41">专科生毕业</html:option>
									<html:option value="43">专科生结业</html:option>
									<html:option value="49">专科生肆业</html:option>
									<html:option value="61">高职</html:option>
								</html:select>
							</logic:equal>
							<logic:equal name="Listxl" value="bks">
								<html:select name="rs" property="xldm" styleId="xldm"
									style="width:150px">
									<html:option value=""></html:option>
									<html:option value="25">双学位毕业</html:option>
									<html:option value="31">本科生毕业</html:option>
									<html:option value="33">本科生结业</html:option>
									<html:option value="39">本科生肆业</html:option>
									<html:option value="61">高职</html:option>
								</html:select>
							</logic:equal>
							<logic:equal name="Listxl" value="yjs">
								<html:select name="rs" property="xldm" styleId="xldm"
									style="width:150px">
									<html:option value=""></html:option>
									<html:option value="11">硕士生毕业</html:option>
									<html:option value="13">硕士生结业</html:option>
									<html:option value="19">硕士生肆业</html:option>
									<html:option value="21">硕班毕业</html:option>
								</html:select>
							</logic:equal>
						</td>
						<th>
							<font color="red">*</font>学制
						</th>
						<td >
							<html:text name='rs' property="xz" readonly="true" />
						</td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font>生源地区
						</th>
						<logic:equal value="10355" name="xxdm" scope="session">
							<td>

								<html:select name="rs" property="sydqdm" onchange="loadShi()"
									styleId="jgshen">
									<html:option value="">--请选择--</html:option>
									<html:options collection="ssList" property="ssdm"
										labelProperty="ssmc" />
								</html:select>
								<br/>
								<html:select name="rs" property="jgshi" styleId="jgshi"
									onchange="loadXian()">
									<html:options collection="shiList" property="shidm"
										labelProperty="shimc" />
								</html:select>
								<br/>
								<html:select name="rs" property="jgx" styleId="jgxian">
									<html:options collection="xianList" property="xiandm"
										labelProperty="xianmc" />
								</html:select>
							</td>
						</logic:equal>
						<logic:notEqual value="10355" name="xxdm" scope="session">
							<td align="left">
								<html:select name="rs" property="sydqdm" styleId="sydqdm"
									style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="sydqdmList" property="sydqdm"
										labelProperty="sydq" />
								</html:select>
							</td>
						</logic:notEqual>
						<th>
							<font color="red">*</font>入学年度
						</th>
						<td >
							<html:text name="rs" property="nd" readonly="true" />
						</td>

					</tr>
					<tr >
						<logic:notEqual name="xxdm" value="12620">
						<!-- 非南国商学院 -->
						<th>
							<font color="red">*</font> 培养方式
						</th>
						<td align="left">
							<html:select name="rs" property="pyfsdm" styleId="pyfsdm"
								style="width:150px">
								<html:option value=""></html:option>
								<html:options collection="pyfsList" property="pyfsdm"
									labelProperty="pyfs" />
							</html:select>
						</td>
						<th>
							备注
						</th>
						<td>
							<html:text name="rs" property="memo" maxlength="10" />
						</td>
						</logic:notEqual>
						<!-- 非南国商学院 end-->
						
						<!-- 南国商学院 -->
						<logic:equal name="xxdm" value="12620">
						<th >
							备注
						</th>
						<td  colspan="3">
							<html:text name="rs" property="memo" size="82"   maxlength="10"
							/>
						</td>
						<!-- 南国商学院 end -->
						</logic:equal>
					</tr>
					<logic:equal value="10863" name="xxdm">
						<tr align="center" style="cursor:hand">
							<th align="right">
								<font color="red">*</font>学生层次
							</th>
							<td align="left">
								<html:select name="rs" property="xscc" styleId="xscc"
									style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="xsccList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
							</th>
							<td>
							</td>
						</tr>
					</logic:equal>
					</tbody>
					<tfoot
							<logic:equal value="view" name="view">
					style="display: none"
					</logic:equal>>
							<tr>
								<td colspan="6">
									<div class="bz">
										"
										<span class="red">*</span>"为必填项
									</div>
									<div class="btn">
									<logic:equal value="10355" name="xxdm">
										<logic:equal value="stu" name="userType" scope="session">
											<button onclick="if(confirm('请您仔细核对所有信息，无误请按“确定”键，返回修改页面请按“取消”键。按“确定”键后您将不能修改本页面信息')){add('/xgxt/bysjbxxbSave.do')}return false;"
												>
												提 交
											</button>
										</logic:equal>
										<logic:notEqual value="stu" name="userType" scope="session">
											<button  onclick="add('/xgxt/bysjbxxbSave.do')">
												提 交
											</button>
										</logic:notEqual>
									</logic:equal>
									<logic:notEqual value="10355" name="xxdm">
										<button  onclick="add('/xgxt/bysjbxxbSave.do')" >
											提 交
										</button>
									</logic:notEqual>
									<button  onclick="reinputagain('/xgxt/bysjbxxb_input.do')" type="reset"
										>
										重 填
									</button>
									</div>
								</td>
							</tr>
						</tfoot>
				</table>
				</div>
			</logic:notEmpty>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
                      alert("提交成功！");
                    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
                      alert("重复提交！操作失败!");
                    </script>
				</logic:equal>
				<logic:equal name="inserted" value="que">
					<script>
                      alert("所有选项必须填写！");
                    </script>
				</logic:equal>
				<logic:equal name="yjtj" value="yjtj">
					<script>
                      alert("你已经提交了，请到院级以上去修改");
                    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
</html>
