<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
	<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<script type="text/javascript" src="pjpy/zjcm/cssz/cssz.js"></script>
	<script type="text/javascript" src="pjpy/zjkjxy/cssz.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
</head>

<body onload="xyDisabled('xy');dispconf('fs')">
	<html:form action="/pjpyzjkj.do?method=rychRsszManage" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
    	<input type="hidden" id="zyV" name="zyV" value=""/>
    	<input type="hidden" id="bjV" name="bjV" value=""/>
		<input type="hidden" id="doType" name="doType" value=""/>
    	<input type="hidden" id="userType" name="userType" value="${userType}"/>
		<input type="hidden" id="message" name="message" value="${message}"/>
		<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
		<div class="compTab" id="card" style="width: 100%">
			<div class="comp_title" id="div1">
				<ul id="ul1">				
				<li class="">
					<a href="#" onclick="refreshForm('pjpyzjkj.do?method=rsszManage')">
						<span>奖学金比例设置</span>
					</a>
				</li>
				<li class="ha">
					<a href="#" onclick="refreshForm('pjpyzjkj.do?method=rychRsszManage')">
						<span>荣誉称号比例设置</span>
					</a>
				</li>
				
				</ul>
			 </div>
		</div>
					
		<div class="toolbox">
		  <!-- 按钮 -->				  
		  <div class="buttonbox">
		  <logic:equal value="yes" name="writeAble" scope="request">
		    <ul>
			  <logic:notEqual value="xy" name="userType">
			  <li>
              	<a class="btn_ck"
					onclick="showTopWin('viewTotStuNum.do',700,500)"
					style="width:100px">
					查看参评人数
				</a>
			</li>
			<li>
				<a class="btn_csh" onclick="baseDataInit('rych')"
				style="width:100px">
					初始化数据
				</a>
			</li>
			<li>
				<a class="btn_sz" id="btn_modi" style="width:90px"
					onclick="showTopWin('pjpyzjkj.do?method=rychblPlsz',500,415)">
					批量设置比例
				</a>
			</li>
			</logic:notEqual>
			<!--end非学院用户-->
			<li>
				<a class="btn_sz" id="btn_modi" style="width:80px"
					onclick="rstz('pjpyzjkj.do?method=rychrstz&act=qry','rych');">
					人数调整
				</a>
			</li>
			</ul>					
			</logic:equal>
		  </div>

		  <div class="searchtab">
			<table width="100%" border="0">
		      <tfoot>
		        <tr>						  
		          <td colspan="6">
		            <div class="btn">
					  	<input type="hidden" name="go" value="a" />
						<button class="" id="search_go"
							onclick="refreshForm('pjpyzjkj.do?method=rychRsszManage&act=qry');">
							查询
						</button>
		            </div>
		          </td>
		        </tr>
		      </tfoot>
			  <tbody>
			  <tr>
				<!--评奖周期为学年-->
				<logic:equal value="xn" name="pjzq">
				<th>学年</th>
				<td>
				<html:select property="xn" style="width:90px" styleClass="select"
						styleId="xn" disabled="true">
						<html:options collection="xnList" property="xn"
							labelProperty="xn" />
					</html:select>
				<html:hidden property="xn"/>
				</td>
				</logic:equal>
				<!--end评奖周期为学年-->
				<!--评奖周期为学期-->
				<logic:equal value="xq" name="pjzq">
				<th>学年</th>
				<td>
				<html:select property="xn" style="width:90px" styleClass="select"
						styleId="xn" disabled="true">
						<html:options collection="xnList" property="xn"
							labelProperty="xn" />
					</html:select>
				<html:hidden property="xn"/>
				</td>
				<th>
				学期</th>
				<td>
				<html:select property="xq" styleId="xq" disabled="true">
					<html:option value=""></html:option>
					<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
				</html:select>
				<html:hidden property="xq"/>
				</td>
				</logic:equal>							
				<!--end评奖周期为学期-->

				<!--评奖周期为年度-->
				<logic:equal value="nd" name="pjzq">
				<th>年度</th>
				<td>
				<html:select property="nd" styleId="nd" disabled="true">
					<html:options collection="ndList" property="nd" labelProperty="nd"/>
				</html:select>
				<html:hidden property="nd"/>
				</td>
				</logic:equal>
				<!--end评奖周期为年度-->
				<th>
				荣誉称号</th>
				<td>
				<html:select property="jxjdm" styleId="dm" onchange="refreshForm('pjpyzjkj.do?method=rychRsszManage&act=qry');">
					<html:options collection="dmList" property="dm" labelProperty="mc"/>
				</html:select>
				</td>
				<th>
				显示范围</th>
				<td>
				<html:select property="fs" styleId="fs" onchange="dispconf('fs');refreshForm('pjpyzjkj.do?method=rychRsszManage&act=qry');" >
					<html:options collection="cpfwList" property="dm" labelProperty="mc"/>
				</html:select>
				</td>
							
				</tr>
				<tr>
				<th>年级</th>
				<td>
				<html:select property="nj" styleId="nj" onchange="initZyList();initBjList();refreshForm('pjpyzjkj.do?method=rychRsszManage&act=qry');"
				 styleClass="select" style="width:90px">
					<html:option value=""></html:option>
					<html:options collection="njList" property="nj" labelProperty="nj"/>
				</html:select>
				</td>
				<th><bean:message key="lable.xsgzyxpzxy" /></th>
				<td>
					<html:select property="xydm" onchange="initZyList();initBjList()"
					styleClass="select" style="width:180px" styleId="xy">
						<html:option value=""></html:option>
						<html:options collection="xyList" property="xydm"
							labelProperty="xymc" />
					</html:select>
				</td>
				<th></th>
				<td></td>
				</tr>
				<tr>		
					<th>专业</th>
					<td>
						<html:select property="zydm" onchange="initBjList();refreshForm('pjpyzjkj.do?method=rychRsszManage&act=qry');" style="width:180px" 
						styleClass="select" styleId="zy">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm"
								labelProperty="zymc" />
						</html:select>
					</td>
					<th>班级</th>
					<td>
						<html:select property="bjdm" style="width:180px" 
						styleClass="select" styleId="bj" onchange="refreshForm('pjpyzjkj.do?method=rychRsszManage&act=qry');">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm"
								labelProperty="bjmc" />
						</html:select>
					</td>
					<th></th>
					<td></td>
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
							<font color="blue">记录数：${rsNum}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;提示：双击一行可以查看详细信息;单击表头可以进行排序;</font>
						</logic:notEmpty>
				    </span>
				    </h3>			 
				<logic:notEmpty name="rs">
				<div class="con_overlfow">
				<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
				<thead>
			      <tr align="center" style="cursor:hand">
									    <td nowrap>
											<input type="checkbox" id="cb" name="cb" onclick="selectAll()" style="height: 17.5px"/>
										</td>
										<logic:iterate id="tit" name="topTr" offset="1" scope="request">
											<td id="${tit.en}"
												onclick="tableSort(this)" nowrap>
												${tit.cn}
											</td>
										</logic:iterate>
									</tr>
			    </thead>
				<tbody>
			      <logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										style="cursor:hand;" 
										ondblclick="modiAndDel('pjpy_zjcm_rychrstz.do?act=view&pkValue=','modi','620','520');">
										<td align=center><input type="checkbox" id="cbv" name="cbv" style="height: 17.5px"
										 value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
										<input type="hidden" name="checkVal" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
									    </td>
										<logic:iterate id="v" name="s" offset="1" length="10">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="11">
											<td align=center>
												<input type="text" id="jxjrs" name="jxjtzrs" value="${v}"/>													
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
			    </tbody>
				</table>
				</div>
				<!--分页显示-->
			   <jsp:include flush="true"
														page="/sjcz/turnpage.jsp?form=pjpyZjkjxyActionForm"></jsp:include>
			 	<!--分页显示-->
				</logic:notEmpty>
			</div>			
			
			<div id="tmpdiv"></div>
	</html:form>
	<!-- 提示信息 -->
	<%@ include file="tsxx.jsp"%>
	<!-- 提示信息 end-->
</body>