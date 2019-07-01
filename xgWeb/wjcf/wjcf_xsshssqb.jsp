<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/wjcfFuction.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getWjcfInfo.js"></script>
		<script language="javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script language="javascript" src="js/check.js"></script>
		
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
		<link id="csssDate" rel="stylesheet" rev="stylesheet"
			href="js/calendar.css" type="text/css" media="all" />
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<logic:equal value="11049" name="xxdm">
					<em>您的当前位置:</em>
					<a>违纪处理 - 学生申诉 - 申请</a>
				</logic:equal>
				<logic:notEqual value="11049" name="xxdm">
					<em>您的当前位置:</em>
					<a> 违纪处分 - 学生申诉申请 - 申请</a>
				</logic:notEqual>
			</p>
		</div>

		<html:form action="/wjcf_xsshssq" method="post"
			enctype="multipart/form-data">
			<logic:empty name="rs">
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
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/wjcf_xsshssq.do" />
				<input type="hidden" id="cfid" name="cfid" value="${cfid }" />
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="tabType" name="tabType" value="wjcf_xsssb" />
				<input type="hidden" name="flag" id="flag" value="${flag }" />
				<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" />
				<input type="hidden" name="pk" id="pk" value="${pk }" />
				<input type="hidden" name="xxsh" id="xxsh" value="${rs.jd }"/>
 



					<div class="formbox">
						<h3 class="datetitle_01">
							<span> 查询结果&nbsp;&nbsp; <logic:empty name="rswj">
									<font color="red"> 暂无材料或证明附件</font>
								</logic:empty> </span>
						</h3>

						<logic:notEmpty name="rswj">
							<table summary="" class="dateline" align="" width="100%">
								<tbody>
									<logic:iterate id="list" name="rswj">
										<tr onmouseover="rowOnClick(this)">
											<td>
												<a
													href="downloadfilewj.do?len=<bean:write name="list" property="len"/>&wjsclj=<bean:write name="list" property="wjsclj"/>"
													target="_black">下载 <input type="hidden" name="lj"
														id="lj"
														value="<bean:write name="list" property="wjsclj"/>" /> </a>
											</td>
											<td>
												<bean:write name="list" property="cfwh" />
											</td>
											<td>
												<bean:write name="list" property="cflbmc" />
											</td>
											<td>
												<bean:write name="list" property="cfyymc" />
											</td>
											<td>
												<bean:write name="list" property="sssj" />
											</td>
										</tr>
									</logic:iterate>
								</tbody>
							</table>
						</logic:notEmpty>
					</div>

				<div class="tab">
					<table width="100%" border="0" class="formlist" id="rsTable">
						<thead>
							<tr>
								<th colspan="5">
									<span><bean:write name="tit" scope="request" /> </span>
								</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="5">
									<div class="btn">
									<logic:equal value="未决定" name="rs" property="jd">
										<button type="button" id="btn_save"
											onclick="addShsApply('xh-cfwh','');">
											提交
										</button>
									</logic:equal>
									<logic:notEqual value="未决定" name="rs" property="jd">
										<logic:notEqual value="modi" name="doType">
										<button type="button" id="btn_save"
											onclick="addShsApply('xh-cfwh','');">
											提交
										</button>
										</logic:notEqual>
									</logic:notEqual>
										<logic:equal value="modi" name="doType">
										<button type="button" onclick="Close();return false;">
										关闭
									</button>
										</logic:equal>
										<logic:notEqual value="modi" name="doType">
										<button type="button" type="reset">
										重置
									</button>
										</logic:notEqual>
									</div>
								</td>
							</tr>
						</tfoot>
						<thead>
							
							<logic:equal value="11078" name="xxdm">
								<tr>
									<td colspan="5" align="center">
										<font color="red">
											提示：1、须在网上点击提交本页内容。&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br />
											2、须打印书面申述材料，并由本人手写签名后递交学校学生申诉委员会（联系电话及具体地点看决定书提示）。 </font>
									</td>
								</tr>
							</logic:equal>
						</thead>
						<tbody>
							<tr align="center" style="cursor:hand">
								<logic:equal name="userOnLine" value="teacher" scope="session">
									<th width="10%">
										<font color="red">*</font>学号
									</th>
									<td align="left" width="25%">
										
											
											<logic:equal value="modi" name="doType">
											<html:text name='rs' property="xh" styleId="xh"
											maxlength="20" readonly="true"/>
											</logic:equal>
											<logic:notEqual value="modi" name="doType">
											<html:text name='rs' property="xh" styleId="xh"
											onkeypress="autoFillStuInfo(event.keyCode,this);"
											maxlength="20" />
											</logic:notEqual>
											
										<logic:notEqual value="domodi" name="modi">
											<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
												class="btn_01" id="buttonFindStu">
												选择
											</button>
										</logic:notEqual>
									</td>
								</logic:equal>
								<logic:equal name="userOnLine" value="student" scope="session">
									<th width="15%">
										<font color="red">*</font>学号
									</th>
									<td align="left" width="25%">
										<input type="text" id="xh" name="xh"
											value="<bean:write name='rs' property="xh"  />"
											readonly="readonly" />
									</td>
								</logic:equal>
								<th width="20%">
									<font color="red">*</font> 处分文号
								</th>
								<td align="left" width="25%">
									<html:text name='rs' property="cfwh" styleId="cfwh"
										readonly="true" />
									<button type="button" onclick="wjcfInfoTo()" class="btn_01"
										id="buttonFindWjcf">
										选择
									</button>
								</td>
								<td align="left" width="15%" rowspan="5">
									<div class="open_img">
									<img
										src="<%=request.getContextPath()%>/stuPic.jsp?xh=<bean:write name="rs" property="xh" />"
										border="0" align="absmiddle" width="96" height="128" />
									</div>
								</td>
							</tr>
							<tr>
								<th>
									姓名
								</th>
								<td>
									<html:text name='rs' property="xm" readonly="true" />
								</td>
								<th>
									年度
								</th>
								<td align="left">
									<html:text name='rs' property="nd" readonly="true" />
								</td>
							</tr>
							<tr>
								<th>
									性别
								</th>
								<td align="left">
									<html:text name='rs' property="xb" readonly="true" />
								</td>
								<th>
									学年
								</th>
								<td align="left">
									<html:text name='rs' property="xn" readonly="true" />
								</td>
							</tr>
							<tr align="center" style="cursor:hand">
								<th>
									年级
								</th>
								<td align="left">
									<html:text name='rs' property="nj" readonly="true" />
								</td>
								<th>
									学期
								</th>
								<td align="left">
								
									<html:hidden name='rs' property="xq"  />
									
									
									<html:select property="xq" name="rs" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc"/>
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />

								</th>
								<td align="left">
									<html:text name='rs' property="xymc" readonly="true" />
								</td>
								<th>
									处分时间
								</th>
								<td align="left">
									<html:text name='rs' property="cfsj" styleId="cfsj"
										readonly="true" />
								</td>
							</tr>

							<tr>
								<th>
									专业
								</th>
								<td align="left">
									<html:text name='rs' property="zymc" readonly="true" />
								</td>
								<th>
									处分类别
								</th>
								<td align="left" colspan="2">
									<html:text name='rs' property="cflbmc" readonly="true" />
								</td>
							</tr>
							<tr>
								<th>
									班级
								</th>
								<td align="left">
									<html:text name='rs' property="bjmc" readonly="true" />
								</td>
								<th>
									处分事由
								</th>
								<td align="left" colspan="2">
									<html:text name='rs' property="cfyymc" readonly="true" />
									<input type="hidden" id="cfyy" name="cfyy"
										value="<bean:write name="rs" property="cfyy"/>" />
								</td>
							</tr>
							<tr>
								<th>
									联系地址
								</th>
								<td align="left">
									<html:text name='rs' property="dz" maxlength="50" />
								</td>
								<th>
									<logic:present name="isZJJDZYJSXY">
					    申诉/解除申请时间
					    </logic:present>
									<logic:notPresent name="isZJJDZYJSXY">
					       申诉时间
					     </logic:notPresent>
								</th>
								<td align="left" colspan="2">
									<html:text name="rs" property="sssj" readonly="true" />
								</td>
							</tr>
							<tr>
								<th>
									邮政编码
								</th>
								<td align="left">
									<html:text name='rs' property="yb" maxlength="6"
										onkeyup="chkIsNum(this)" />
								</td>
								<th>
									联系电话
								</th>
								<td align="left" colspan="2">
									<html:text name='rs' property="lxdh" maxlength="14"
										onblur="checkPhone(this)" />
								</td>
							</tr>
							<logic:present name="isZJJDZYJSXY">
								<tr style="height:30px">
									<th>
										处分改为
									</th>
									<td align="left" colspan="4">
										<html:text name='rs' property="cfxg" maxlength="80"
											style="width:300px" />
									</td>
								</tr>
							</logic:present>
							<tr style="height:30px">
								<th>
									<logic:present name="isZJJDZYJSXY">
					    						申诉/解除<br />
												申请理由 
											<br />
									</logic:present>
									<logic:notPresent name="isZJJDZYJSXY">
										<logic:equal value="11078" name="xxdm">
							申诉陈述理由<br />
										</logic:equal>
										<logic:notEqual value="11078" name="xxdm">
										<logic:equal value="10653" name="xxdm">
										申诉理由
										</logic:equal>
										<logic:notEqual value="10653" name="xxdm">
											现实表现或<br/>改变<bean:message key="lable.xsgzyxpzxy" />处分要求<br />
										</logic:notEqual>
										</logic:notEqual>

									</logic:notPresent>
									<font color="red">(限制在2000字以内)</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;
								</th>
								<td align="left" colspan="4">
									<html:textarea rows="5" name='rs' style="width:600px"
										property="yq" styleId="yq" onkeyup="checkLen(this,2000)" />
								</td>
							</tr>
							<tr>
								<th>
									材料或&nbsp;&nbsp;&nbsp;
									<br />
									证明附件
								</th>
								<td align="left" colspan="4">
									<input type="file" name="uploadFile" id="cfwj"
										contentEditable="false" style="width:60%" />
									<logic:equal name="xxdm" value="10628#">
      				 	&nbsp;&nbsp;<a
											href="downloadfiletemplate.do?filename=西昌<bean:message key="lable.xsgzyxpzxy" />学生校内申诉书.doc"
											target="_blank">西昌<bean:message key="lable.xsgzyxpzxy" />学生校内申诉书下载</a>
										<font color="red">(请下载填写后上传)</font>
									</logic:equal>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</logic:notEmpty>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
                      alert("提交成功！");
                     if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
    					window.dialogArguments.document.all("search_go").click();
    					
    				}   
    				Close();             
                    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
                      alert("提交失败!");                   
                    </script>
				</logic:equal>
				<logic:equal name="inserted" value="upFail">
					<script>
                      alert("提交失败!");                   
                    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
		</body>
</html>
