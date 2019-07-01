<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
</head>
<body>
	<html:form action="/stu_archives_history">
		<input type="hidden" name="url" id="url" value="/shgc/stu_info/stu_archives_info.jsp"/>
		<input type="hidden" name="variable" id="variable" value="ydinfo"/>
		<input type="hidden" name="redirect" id="redirect" value="true"/>
		<logic:equal value="10856" name="xxdm" scope="session">
			<input type="hidden" name="page" id="redirect" value="archives" />
		</logic:equal>
		<logic:notEqual value="10856" name="xxdm" scope="session">
			<input type="hidden" name="page" id="redirect" value="xsxx"/>
		</logic:notEqual>
		<input type="hidden" value="${oper}" id="oper" />
		<%--宁波职业技术学院--%>
		<logic:equal value="10863" name="xxdm">
			<input type="hidden" value="xh" id="notnull" name="notnull" />
		</logic:equal>
		<%--end宁波职业技术学院--%>

		<%--非宁波职业技术学院--%>
		<logic:notEqual value="10863" name="xxdm">
			<input type="hidden" value="xh-zddwmc-zysj" id="notnull"
				name="notnull" />
		</logic:notEqual>
		<%--end非宁波职业技术学院--%>

		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>学生信息－在校学生档案－档案维护</a>
			</p>
		</div>
		<logic:notEmpty name="message">
			<center>
				${message}
			</center>
		</logic:notEmpty>

		<logic:empty name="message">
		<div class="tab">
	 	 <table width="100%" border="0" class="formlist">
			<thead align="center">
				<tr>
					<th colspan="4">
						<span>在校学生档案</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th><span class="red">*</span>学号</th>
					<td>
						<logic:equal value="update" name="oper">
							<html:text name="rs" property="xh" styleId="xh" readonly="true" />
						</logic:equal>
						<logic:equal value="add" name="oper">
							<html:text name="rs" property="xh" styleId="xh"
								onkeypress="if(event.keyCode == 13) autoFillStuInfo2(this);" />
							<button class="btn_01"
								onclick="showTopWin('/xgxt/stu_info.do?oper=<bean:write name="oper"/>',750,550);"
								id="buttonFindStu">
								选择
							</button>
							<%--上海过程技术大学--%>
							<logic:equal value="10856" name="xxdm">
								<button align="left" class="button2"
									onclick="checkArchivesExist()" style="width:85px"
									id="buttonCheck">
									检查能否转档
								</button>
							</logic:equal>
						</logic:equal>
					</td>
					<th></th>
					<td rowspan="5">
					<logic:equal value="浙江商业职业技术学院" name="xxmc" scope="session">
							<img border="0" style="height:133px;width:100px;" src="/xgxt/pictures/${rs.sfzh}.jpg"/>
					</logic:equal>
					<logic:notEqual value="浙江商业职业技术学院" name="xxmc" scope="session">
						<logic:equal value="12872" name="xxdm">
							<logic:present name="showsfzh">
								<img border="0" style="height:133px;width:100px;" src="/xgxt/pictures/${rs.sfzh}.jpg"/>
							</logic:present>
							<logic:present name="showxh">
								<img border="0" style="height:133px;width:100px;" src="/xgxt/pictures/${rs.xh}.jpg"/>
							</logic:present>
						</logic:equal>
						<logic:notEqual value="12872" name="xxdm">
							<%--浙江机电职业技术学院--%>
							<logic:equal value="12861" name="xxdm">
								<img src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh}"
									border="0" style="width:140;height:160" />
							</logic:equal>
							<%--end浙江机电职业技术学院--%>

							<%--非浙江机电职业技术学院--%>
							<logic:notEqual value="12861" name="xxdm">
								<%--非江苏信息职业技术学院--%>
								<logic:notEqual value="13108" name="xxdm">
									<img src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh}" border="0" align="absmiddle" style="width:140;height:160" />
								</logic:notEqual>
								<%--江苏信息职业技术学院--%>
								<logic:equal value="13108" name="xxdm">
										<img border="0" style="height:133px;width:100px;" src="/xgxt/pictures/${rs.sfzh}.jpg"/>
								</logic:equal>
							</logic:notEqual>
							<%--end非浙江机电职业技术学院--%>
						</logic:notEqual>
					</logic:notEqual>
					</td>
				</tr>

				<tr>
					<th>姓名</th>
					<td>
						${rs.xm}
						<input type="hidden" name="xm" id="xm" value="${rs.xm}" />
					</td>
					<th></th>
				</tr>
				<tr>
					<th>性别</th>
					<td>
						${rs.xb}
						<input type="hidden" name="xb" id="xb" value="${rs.xb}" />
					</td>
					<th></th>
				</tr>					
				<tr>
					<th>学制</th>
					<td>
						${rs.xz}
					</td>
					<th></th>
				</tr>
				<tr>
					<th>出生日期</th>
					<td>
						${rs.csrq}
						<input type="hidden" name="csrq" id="csrq" value="${rs.csrq}" />
					</td>
					<th></th>
				</tr>
				<tr>
					<th>民族</th>
					<td>
						${rs.mzmc}
						<input type="hidden" name="mz" id="mz" value="${rs.mz}" />
					</td>
					<th>政治面貌</th>
					<td>
						${rs.zzmmmc}
					</td>
				</tr>
				<tr>
					<th>身份证号</th>
					<td>
						${rs.sfzh}
						<input type="hidden" name="sfzh" id="sfzh" value="${rs.sfzh}" />
					</td>
					<th>联系电话</th>
					<td>
						${rs.lxdh}
						<input type="hidden" name="lxdh" id="lxdh" value="${rs.lxdh}" />
					</td>
				</tr>
				<tr>
					<th>年级</th>
					<td>
						${rs.nj}
					</td>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						${rs.xymc}
						<input type="hidden" name="xydm" id="xydm" value="${rs.xydm}" />
					</td>
				</tr>
				<tr>
					<th>专业名称</th>
					<td>
						${rs.zymc}
						<input type="hidden" name="zydm" id="zydm"
							value="${rs.zydm}" />
					</td>
					<th>班级名称</th>
					<td>
						${rs.bjmc}
						<input type="hidden" name="bjdm" id="bjdm" value="${rs.bjdm}" />
					</td>
				</tr>
				<%--信阳师范--%>
				<logic:equal value="10477" name="xxdm">
					<%@ include file="/shgc/stu_info/xysfxy/stu_archives_info.jsp"%>
				</logic:equal>

				<%--非信阳师范--%>
				<logic:notEqual value="10477" name="xxdm">
				<!--贵州大学-->
				<logic:notEqual value="10657" name="xxdm">
				<tr style="height:30px">
					<%--宁波职业技术学院--%>
					<logic:equal value="10863" name="xxdm">
						<th>转档原因</th>
						<td>
							<html:select property="zdyy" name="rs">
								<html:option value=""></html:option>
								<html:option value="升学">升学</html:option>
								<html:option value="毕业">毕业</html:option>
								<html:option value="退学">退学</html:option>
								<html:option value="其它">其它</html:option>
							</html:select>
						</td>
					</logic:equal>
					<%--end宁波职业技术学院--%>

					<%--非宁波职业技术学院--%>
					<logic:notEqual value="10863" name="xxdm">
						<th><span class="red">*</span>转档原因</th>
						<td>
							<html:text name="rs" property="zdyy" styleId="zdyy"
								maxlength="100" />
						</td>
					</logic:notEqual>
					<%--end非宁波职业技术学院--%>

					<%--宁波职业技术学院--%>
					<logic:equal value="10863" name="xxdm">
						<th>转档时间</th>
					</logic:equal>
					<%--end宁波职业技术学院--%>

					<%--非宁波职业技术学院--%>
					<logic:notEqual value="10863" name="xxdm">
						<th><span class="red">*</span>转档时间</th>
					</logic:notEqual>
					<%--end非宁波职业技术学院--%>

					<td>
						<html:text name="rs"
							onclick="return showCalendar('zysj','y-mm-dd');" property="zysj"
							styleId="zysj" readonly="true" />
					</td>
				</tr>
				<tr>
					<%--宁波职业技术学院--%>
					<logic:equal value="10863" name="xxdm">
						<th>转档单位名称</th>
					</logic:equal>
					<%--end宁波职业技术学院--%>

					<%--非宁波职业技术学院--%>
					<logic:notEqual value="10863" name="xxdm">
						<th><span class="red">*</span>转档单位名称</th>
					</logic:notEqual>
					<%--end非宁波职业技术学院--%>

					<td>
						<html:text name="rs" property="zddwmc" styleId="zddwmc"
							maxlength="50" />
					</td>
					<th>转档单位地址</th>
					<td>
						<html:text name="rs" property="zddwdz" styleId="zddwdz"
							maxlength="50" />
					</td>
				</tr>
				<%--浙江经济职业技术学院--%>
				<logic:equal value="12866" name="xxdm">
					<tr>
						<th>档案来源</th>
						<td colspan="3">
							<html:text name="rs" property="daly" styleId="daly"
								style="width:100%" maxlength="100" />
						</td>
					</tr>
				</logic:equal>	
				<tr style="height:30px">
					<th>转档单位邮编</th>
					<td>
						<html:text name="rs" property="zddwyb" styleId="zddwyb"
							maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'') " />
					</td>
					<%--宁波职业技术学院--%>
					<logic:equal value="10863" name="xxdm">
						<th>高中档案是否在校</th>
					</logic:equal>
					<%--end宁波职业技术学院--%>

					<%--非宁波职业技术学院--%>
					<logic:notEqual value="10863" name="xxdm">
						<th><span class="red">*</span>高中档案是否在校</th>
					</logic:notEqual>
					<%--end非宁波职业技术学院--%>
					<td>
						<html:select property="gzdasfzx" styleId="gzdasfzx" name="rs">
							<html:option value="不在校">不在校</html:option>
							<html:option value="在校">在校</html:option>
						</html:select>
					</td>
				</tr>										
				<tr>
					<th>档案号</th>
					<td>
						<html:text name="rs" property="dah" styleId="dah" maxlength="32" />
					</td>
					<th>档案保存位置</th>
					<td>
						<html:text name="rs" property="dabcwz" styleId="dabcwz"
							maxlength="112" />
					</td>
				</tr>
				<tr>
					<th>档案接收人</th>
					<td>
						<html:text name="rs" property="jsr" styleId="jsr" maxlength="10" />
					</td>
					<th>
					<%--福建工程学院--%>
					<logic:equal value="10388" name="xxdm">
						档案转递人
					</logic:equal>
					<%--end福建工程学院--%>
					<logic:notEqual value="10388" name="xxdm">
						档案管理人
					</logic:notEqual>					
					</th>
					<td>
						<html:text name="rs" property="glr" styleId="glr" maxlength="10" />
					</td>					
				</tr>
				<tr>
					<th>接收内容</th>
					<td colspan="3">
						<html:text name="rs" property="jsnr" styleId="jsnr" style="height:45;width:90%" maxlength="112" />
					</td>
				</tr>	
				</logic:notEqual>	
				<!--贵州大学-->
				<logic:equal value="10657" name="xxdm">
				<tr>
						<th>档案编号</th>
						<td>
							<html:text name="rs" property="dah" styleId="dah" maxlength="32" />
						</td>
						<th>档案存放地</th>
						<td>
							<html:text name="rs" property="dabcwz" styleId="dabcwz" maxlength="112" />
						</td>
				</tr>
				<tr>					
					<th>档案信息</th>
					<td colspan="3">
						<html:text name="rs" property="jsnr" styleId="jsnr" style="height:45;width:90%" maxlength="112" />
					</td>
				</tr>
				<tr>
					<th>档案登记</th>
					<td colspan="3">
						<logic:iterate name="dadjList" id="s">							
								<input type="checkbox" name="dadj"
									<logic:iterate id="v" name="s" offset="2" length="1">${v}</logic:iterate>	
									 value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" 
									  ><logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>
								
						</logic:iterate>
					</td>
				</tr>
				</logic:equal>	
				<!--end贵州大学-->
				<!--福建工程-->
				<%@ include file="/xsxx/fjgcxy/zxxsda_fjgcxy.jsp"%>
				</logic:notEqual>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
		          <div class="btn">
		            <logic:notEqual value="student" name="user">
					<logic:notEqual value="true" name="view">
					<button class="button2"
						onclick="archiveSave('/xgxt/stu_archives_now.do?doType=saveArchives&type=');"
						style="width:80px">
						保 存
					</button>
					</logic:notEqual>
					</logic:notEqual>
					<button class="button2" onclick="Close();return false;"
						style="width:80px">
						关 闭
					</button>					
		          </div>
		        </td>
		      </tr>
		    </tfoot>
		</table>
		</div>
		</logic:empty>
		<logic:notEmpty name="result">
			<logic:equal value="true" name="result">
				<script>
					alert("操作成功！");
					Close();
					if(window.dialogArguments){
						window.dialogArguments.document.getElementById('search_go').click();
					}
				</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
					alert("操作失败！");
				</script>
			</logic:equal>
		</logic:notEmpty>
	</html:form>
</body>
</html>
