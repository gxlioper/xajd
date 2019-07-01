<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<script language="javascript" src="js/xgutil.js"></script>
<script language="javascript" src="js/xszz/xszzFunction.js"></script>
<script language="javascript">
function setKgzt(value,num){
	var id = "kgzt"+num;
	$(id).value = value;
}

function setAllKgzt(value,num){
	var s = document.getElementsByTagName('input');
	for(var i=0; i<s.length;i++){
		if(s[i].type=="radio"){
			if(s[i].name != "kg"){
				if(s[i].value == value){
					s[i].checked = true;
				}
			}
		}else if(s[i].type =="hidden"){
			if(s[i].name == "sqzt"){
				s[i].value = value;
			}
		}
	}
}

function saveSqzt(){
	//判断是否有数据
	if(ele('rsTable') == null || ele('rsTable') == undefined){
		alert('没有可保存的数据！');
		return false;
	}
	if (confirm('确定保存是否可申请信息吗？')){
		saveUpdate('/xgxt/pjpyzjkj.do?method=rychsqxsManage&doType=save','');
	}
}
</script>
</head>
	<body onload="">
		<html:form action="/pjpyzjkj.do?method=rychsqxsManage">
			<!-- 隐藏域 -->
			<%@ include file="/xszz/hiddenValue.jsp"%>
			<!-- 隐藏域 end-->
			<!-- 标题 -->
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			
			<!-- 标题 end-->
			<!-- 错误信息 -->
			<logic:notEmpty name="msg">
				<div align="center">
					<FONT color="red" size="6"><bean:write name="msg" /></FONT>
				</div>
			</logic:notEmpty>
			<!-- 错误信息 end-->
			<!-- 无错误 -->
			<logic:empty name="msg">
				<div class="compTab" id="card" style="width: 100%">
					<div class="comp_title" id="div1">
						<ul id="ul1">
							<li id="li_jxj">
								<a href="#" onclick="refreshForm('pjpyzjkj.do?method=jxjsqxsManage')">
									<span>奖学金</span>
								</a>
							</li>
							<li id="li_rych"  class="ha">
								<a href="#" onclick="refreshForm('pjpyzjkj.do?method=rychsqxsManage')">
									<span>荣誉称号</span>
								</a>
							</li>						
						</ul>				
					</div>
				</div>
				<div class="toolbox">
				  <!-- 按钮 -->				  
				  <div class="buttonbox">
				    <ul>
					  <!--读写权-->
					  <logic:equal value="yes" name="writeAble">
						<logic:notEmpty name="yhInfo">
							<li> <a href="#" onclick="saveSqzt();" class="btn_ccg" disabled="disabled">保存</a> </li>
						</logic:notEmpty>	
						<logic:empty name="yhInfo">
							<li> <a href="#" onclick="saveSqzt();" class="btn_ccg">保存</a> </li>
						</logic:empty>		
					  </logic:equal>
						<li> <a href="#" onclick="wjcfDataExport('pjpyzjkj.do?method=rychsqxsManage&doType=exp');" class="btn_dc">导出数据</a> </li>
					</ul>					
				  </div>

				  <div class="searchtab">
					<table width="100%" border="0">
				      <tfoot>
				        <tr>				
						  <td colspan="">
							<!--操作开关提示-->
							<logic:notEmpty name="yhInfo">
								<font color="red">提示：${ yhInfo}</font>
							</logic:notEmpty>
							<!--end操作开关提示-->
						  </td>		  
				          <td colspan="5">
				            <div class="btn">
							  	<input type="hidden" name="go" value="a" />
								<button class="button2" id="search_go"
									onclick="allNotEmpThenGo('/xgxt/pjpyzjkj.do?method=rychsqxsManage&doType=query');">
									查询
								</button>
								<button class="button2" id="cz"
									onclick="czSearchCond('nj-xh-xm-xy-zy-bj-sfksq');return false;">
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
							<html:select property="queryequals_xn" 
								styleId="xn" disabled="true">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
						<th>年度</th>
						<td>
							<html:select property="queryequals_nd" 
								styleId="xn" disabled="true">
								<html:options collection="ndList" property="nd"
									labelProperty="nd" />
							</html:select>
						</td>
						<th>学期</th>
						<td>
							<html:select property="queryequals_xq" 
								styleId="xn" disabled="true">
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
						</td>
					  </tr>		
					  <tr>
						<th>年级</th>
						<td>
							<html:select property="queryequals_nj"
								onchange="initZyList();initBjList()" styleId="nj">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
						</td>
						<th>学号</th>
						<td>
							<html:text property="querylike_xh" maxlength="20" styleId="xh" style="width:80px"></html:text>						
						</td>
						<th>姓名</th>
						<td>
							<html:text property="querylike_xm" maxlength="20" styleId="xm" style="width:80px"></html:text>
						</td>
					  </tr>		
					  <tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<html:select property="queryequals_xydm"
								onchange="initZyList();initBjList()" styleId="xy"
								style="width:160px">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						</td>
						<th>专业</th>
						<td>
							<html:select property="queryequals_zydm" onchange="initBjList()"
								styleId="zy" style="width:160px">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>						
						</td>
						<th>班级</th>
						<td>
							<html:select property="queryequals_bjdm" styleId="bj"
								style="width:160px">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
					  </tr>
		  			  <tr>			
						<th>荣誉称号</th>
						<td>
							<html:select property="queryequals_rychdm" styleId="rychdm">
								<html:options collection="rychList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
						<th>是否可申请</th>
						<td>
							<html:select property="queryequals_sfksq" styleId="sfksq">
								<html:option value="">----请选择----</html:option>
								<html:options collection="flagList" property="en"
									labelProperty="cn" />
							</html:select>					
						</td>
						<th></th>
						<td>
							
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
							<font color="blue">提示：单击表头可以排序！</font>
						</logic:notEmpty>
				    </span>
				    </h3>			 
				<logic:notEmpty name="rs">
				<div class="con_overlfow">
				<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
				<thead>
			      <tr>
			        <td width="5%">
<!--							<input type="checkbox" id="selall" name="selall" onclick="selAll()">-->
					</td>					
					<!--申请周期只有一个字段-->
					<logic:equal value="1" name="sqzqLength">										
					<logic:iterate id="tit" name="topTr" offset="6" length="6">
						<td id="<bean:write name="tit" property="en"/>"
							onclick="tableSort(this)">
							<bean:write name="tit" property="cn" />
						</td>
					</logic:iterate>
					<logic:iterate id="tit" name="topTr" offset="12" length="1">
						<td id="<bean:write name="tit" property="en"/>"
							>
							<bean:write name="tit" property="cn" />
							(
							<logic:iterate name="flagList" id="kg" indexId="titleIndex">
								${kg.en }
								<input type="radio" value="${kg.en }" name="kg" onclick="setAllKgzt(this.value,${titleIndex })"/>
							</logic:iterate>
							)
						</td>
					</logic:iterate>
					</logic:equal>
					<!--end申请周期只有一个字段-->

					<!--申请周期两个字段-->
					<logic:equal value="2" name="sqzqLength">										
					<logic:iterate id="tit" name="topTr" offset="6" length="7">
						<td id="<bean:write name="tit" property="en"/>"
							onclick="tableSort(this)">
							<bean:write name="tit" property="cn" />
						</td>
					</logic:iterate>
					<logic:iterate id="tit" name="topTr" offset="13" length="1">
						<td id="<bean:write name="tit" property="en"/>">
							<bean:write name="tit" property="cn" />
							<logic:iterate name="flagList" id="kg">
								${kg.en }
								<input type="radio" value="${kg.en }" name="kg${index }" onclick="setAllKgzt(this.value,${index })"/>
							</logic:iterate>
						</td>
					</logic:iterate>
					</logic:equal>
					<!--end申请周期两个字段-->

					<!--申请周期无字段-->
					<logic:empty name="sqzqLength">																				
					<logic:iterate id="tit" name="topTr" offset="6" length="5">
						<td id="<bean:write name="tit" property="en"/>"
							onclick="tableSort(this)">
							<bean:write name="tit" property="cn" />
						</td>
					</logic:iterate>
					<logic:iterate id="tit" name="topTr" offset="11" length="1">
						<td id="<bean:write name="tit" property="en"/>">
							<bean:write name="tit" property="cn" />
							<logic:iterate name="flagList" id="kg">
								${kg.en }
								<input type="radio" value="${kg.en }" name="kg${index }" onclick="setAllKgzt(this.value,${index })"/>
							</logic:iterate>
						</td>
					</logic:iterate>
					</logic:empty>
					<!--end申请周期无字段-->
			      </tr>
			    </thead>
				<tbody>
				<!--内容 -->
				<logic:iterate name="rs" id="s" indexId="index">
					<tr onclick="rowOnClick(this);" 
						style="cursor:hand">
						<!-- checkbox -->								
						<logic:iterate id="v" name="s" offset="0" length="1">
							<td align="center">
								<input type="hidden" name="checkVal" value="${v }"/>
								<logic:iterate id="mrxm" name="s" offset="1" length="1">
									<input type="checkbox" id="checkVal"  name="primarykey_checkVal" disabled="disabled"/>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="1" length="1">
									<input type="hidden" name="xhV" value="${v }"/>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="2" length="1">
									<input type="hidden" name="xnV" value="${v }"/>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="3" length="1">
									<input type="hidden" name="ndV" value="${v }"/>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="4" length="1">
									<input type="hidden" name="xqV" value="${v }"/>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="5" length="1">
									<input type="hidden" name="xmdmV" value="${v }"/>
								</logic:iterate>
							</td>
						</logic:iterate>
						<!--申请周期只有一个字段-->
						<logic:equal value="1" name="sqzqLength">
						<!-- 项目信息 -->		
						<logic:iterate id="v" name="s" offset="6" length="6">
							<td align="left" nowrap="nowrap">								
								<bean:write name="v" />
							</td>
						</logic:iterate>
						<!-- 开关 -->		
						<logic:iterate id="v" name="s" offset="12" length="1">
							<td align="center">
								<logic:iterate name="flagList" id="kg" indexId="ind">
									${kg.en }
									<logic:equal name="kg" property="en" value="${v }">
										<input type="radio" value="${kg.en }" name="kg${index }" id="kg${ind}" checked="checked" onclick="setKgzt(this.value,${index })"/>
										&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:equal>
									<logic:notEqual name="kg" property="en" value="${v }">
										<input type="radio" value="${kg.en }" name="kg${index }" id="kg${ind}" onclick="setKgzt(this.value,${index })"/>
										&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:notEqual>
								</logic:iterate>
								<input type="hidden" name="sqzt" id="kgzt${index }" value="${v }"/>
							</td>
						</logic:iterate>
						</logic:equal>
						<!--end申请周期只有一个字段-->

						<!--申请周期两个字段-->
						<logic:equal value="2" name="sqzqLength">
						<!-- 项目信息 -->		
						<logic:iterate id="v" name="s" offset="6" length="7">
							<td align="left" nowrap="nowrap">								
								<bean:write name="v" />
							</td>
						</logic:iterate>
						<!-- 开关 -->		
						<logic:iterate id="v" name="s" offset="13" length="1">
							<td align="center">
								<logic:iterate name="flagList" id="kg" indexId="ind">
									${kg.en }
									<logic:equal name="kg" property="en" value="${v }">
										<input type="radio" value="${kg.en }" name="kg${index }" id="kg${ind}" checked="checked" onclick="setKgzt(this.value,${index })"/>
										&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:equal>
									<logic:notEqual name="kg" property="en" value="${v }">
										<input type="radio" value="${kg.en }" name="kg${index }" id="kg${ind}" onclick="setKgzt(this.value,${index })"/>
										&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:notEqual>
								</logic:iterate>
								<input type="hidden" name="sqzt" id="kgzt${index }" value="${v }"/>
							</td>
						</logic:iterate>
						</logic:equal>
						<!--end申请周期两个字段-->

						<!--申请周期无字段-->
						<logic:empty name="sqzqLength">
							<!-- 项目信息 -->		
						<logic:iterate id="v" name="s" offset="6" length="5">
							<td align="left" nowrap="nowrap">								
								<bean:write name="v" />
							</td>
						</logic:iterate>
						<!-- 开关 -->		
						<logic:iterate id="v" name="s" offset="11" length="1">
							<td align="center">
								<logic:iterate name="flagList" id="kg" indexId="ind">
									${kg.en }
									<logic:equal name="kg" property="en" value="${v }">
										<input type="radio" value="${kg.en }" name="kg${index }" id="kg${ind}" checked="checked" onclick="setKgzt(this.value,${index })"/>
										&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:equal>
									<logic:notEqual name="kg" property="en" value="${v }">
										<input type="radio" value="${kg.en }" name="kg${index }" id="kg${ind}" onclick="setKgzt(this.value,${index })"/>
										&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:notEqual>
								</logic:iterate>
								<input type="hidden" name="sqzt" id="kgzt${index }" value="${v }"/>
							</td>
						</logic:iterate>	
						</logic:empty>
					</tr>
				</logic:iterate>
			</table>
			<!--内容 end -->
			    </tbody>
				</table>
				</div>
				<!--分页显示-->
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=pjpyZjkjxyActionForm"></jsp:include>
			 	<!--分页显示-->
				</logic:notEmpty>
				</div>
			</logic:empty>
			<!-- 无错误 end-->
		</html:form>
		<!-- 提示信息 -->
		<%@ include file="tsxx.jsp"%>
		<!-- 提示信息 end-->
	</body>
</html>