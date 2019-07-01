<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script type="text/javascript" src="js/xgutil.js"></script>
	<body>
		<html:form action="/stu_archives_history">
			<input type="hidden" name="url" id="url" value="/stu_archives_history.do?act=ljs_archive&doType=add&type=add"/>
			<input type="hidden" value="xh-xm-xb-nj-xymc-zymc-bjmc-sfzh-csrq-zzmmmc" id="getStuInfo" name="getStuInfo" />
			<input type="hidden" value="${oper}" id="oper" name="oper" />
			<input type="hidden" value="${xxdm}" id="xxdm" name="xxdm" />
			<input type="hidden" value="${userType}" id="userType" name="userType" />
			<%--宁波职业技术学院--%>
			<logic:equal value="10863" name="xxdm">
				<input type="hidden" value="xh" id="notnull" name="notnull" />
			</logic:equal>
			<%--end宁波职业技术学院--%>

			<%--信阳师范学院--%>
			<logic:equal value="10477" name="xxdm">
				<input type="hidden" value="xh" id="notnull" name="notnull" />
			</logic:equal>
			<%--end信阳师范学院--%>
			
			<!--中国美术学院-->
			<logic:equal value="10355" name="xxdm">
				<input type="hidden" value="xh-zddwmc-zysj-dasfzx-zdyy" id="notnull" name="notnull" />
			</logic:equal>
			<!--end中国美术学院-->

			<%--其它--%>
			<logic:notEqual value="10863" name="xxdm">
			<logic:notEqual value="10355" name="xxdm">				
			<logic:notEqual value="10477" name="xxdm">
				<input type="hidden" value="xh-zddwmc-zysj-dasfzx-zdyy" id="notnull" name="notnull" />
			</logic:notEqual>
			</logic:notEqual>
			</logic:notEqual>

			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>学生信息 - 学生档案 - 历届学生档案 - 维护</a>
				</p>
			</div>
			<logic:notEmpty name="msg">
				<center>
					<bean:write name="msg" />
				</center>
			</logic:notEmpty>

			<logic:empty name="msg">
				<div class="tab">
		  		<table width="100%" class="formlist">
					<thead>
						<tr>
							<th align="center" colspan="4">
								<span>历届学生档案</span>
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

							<logic:notEqual value="update" name="oper">
								<html:text name="rs" property="xh" styleId="xh"
									onkeypress="if(event.keyCode == 13) autoFillStuInfo2(this);" />
								<!--非中国美术学院>-->
								<logic:notEqual value="10355" name="xxdm">
								<button class="btn_01"
									onclick="showTopWin('/xgxt/stu_info.do?oper=${oper}',750,550);"
									id="buttonFindStu">
									选择
								</button>
								</logic:notEqual>
								<!--end非中国美术学院-->

								<!--中国美术学院-->
								<logic:equal value="10355" name="xxdm">
									<button class="btn_01"
										onclick="showTopWin('/xgxt/stu_info.do?oper=<bean:write name="oper"/>&by=by',750,550);"
										style="width:20px" id="buttonFindStu">
										选择
									</button>
								</logic:equal>
								<!--end中国美术学院-->
							</logic:notEqual>
						</td>
						<th>年级</th>
						<td>
							${rs.nj}
						</td>
					</tr>
					<tr>
						<th>姓名</th>
						<td>
							${rs.xm}
						</td>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							${rs.xymc}
						</td>
					</tr>
					<tr>
						<th>性别</th>
						<td>
							${rs.xb}
						</td>
						<th>专业名称</th>
						<td>
							${rs.zymc}
						</td>
					</tr>
					<tr>
						<th>出生日期</th>
						<td>
							${rs.csrq}
						</td>
						<th>班级名称</th>
						<td>
							${rs.bjmc}
						</td>
					</tr>
					<tr>
						<th>身份证号</th>
						<td>
							${rs.sfzh}
						</td>
						<th>学制</th>
						<td>
							${rs.xz}
						</td>
					</tr>
					<tr>
						<th>民族</th>
						<td>
							${rs.mzmc}
						</td>
						<th>政治面貌</th>
						<td>
							${rs.zzmmmc}
						</td>
					</tr>
					<%--信阳师范--%>
					<logic:equal value="10477" name="xxdm">
						<%@ include file="/shgc/stu_info/xysfxy/stu_archives.jsp"%>
					</logic:equal>

					<%--非信阳师范--%>
					<logic:notEqual value="10477" name="xxdm">
					<tr>
						<th>毕业时间</th>
						<td>
						<!--中国美术学院-->
						<logic:equal value="10355" name="xxdm">						
							${rs.bynf}					
						</logic:equal>
						<!--end中国美术学院-->
						
						<!--非中国美术学院-->
						<logic:notEqual value="10355" name="xxdm">
							<html:text name="rs" styleId="bynf" property="bynf"
								onclick="return showCalendar('bynf','y-mm-dd');" />
						</logic:notEqual>
						<!--end非中国美术学院-->
						</td>
						<%--宁波职业技术学院--%>
						<logic:equal value="10863" name="xxdm">
							<th>档案是否在校</th>
						</logic:equal>
						<%--end宁波职业技术学院--%>

						<%--非宁波职业技术学院--%>
						<logic:notEqual value="10863" name="xxdm">
							<th><span class="red">*</span>档案是否在校</th>
						</logic:notEqual>
						<%--end非宁波职业技术学院--%>
						<td>
							<html:select name="rs" property="dasfzx" styleId="dasfzx"
								style="width:160px">
								<html:option value="不在校">不在校</html:option>
								<html:option value="在校">在校</html:option>
							</html:select>
						</td>
					</tr>
					<tr>
						<th>档案来源</th>
						<td>
							<html:text name="rs" styleId="daly" property="daly" style="width:95%" maxlength="100" />
						</td>
						<th>档案去向</th>
						<td>
							<html:text name="rs" styleId="daqx" property="daqx" style="width:95%" maxlength="25" />
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
							<html:text name="rs" styleId="zddwmc" property="zddwmc"
								maxlength="50" />
						</td>
						<th>转档单位地址</th>
						<td>
							<html:text name="rs" property="zddwdz" styleId="xydm" maxlength="50" />
						</td>
					</tr>
					<logic:equal name="xxdm" value="11355">
					<tr>
						<th>机要号</th>
						<td colspan="3">
							<html:text name="rs" styleId="jyh" property="jyh" style="width:100%" maxlength="30" />
						</td>
					</tr>
					</logic:equal>					
					<tr style="height:30px">
						<%--宁波职业技术学院--%>
						<logic:equal value="10863" name="xxdm">
							<th>转档原因</th>
							<td>
								<html:select property="zdyy" name="rs">
								<html:option value=""></html:option>
								<html:option value="升学"></html:option>
								<html:option value="毕业"></html:option>
								<html:option value="退学"></html:option>
								<html:option value="其它"></html:option>
								</html:select>							
							</td>
						</logic:equal>
						<%--end宁波职业技术学院--%>

						<%--非宁波职业技术学院--%>
						<logic:notEqual value="10863" name="xxdm">
							<th><span class="red">*</span>转档原因</th>
							<td>
								<html:text name="rs" styleId="zdyy" property="zdyy" maxlength="100" />
							</td>
						</logic:notEqual>
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
					<tr style="height:30px">
						<th>转档单位邮编</th>
						<td>
							<html:text name="rs" property="zddwyb" styleId="zddwyb"
								onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="10"/>
						</td>
						<th>回执号</th>
						<td>
							<html:text name="rs" property="hzh" styleId="hzh" maxlength="25" />
						</td>
					</tr>
					<tr>
						<th>转档方式</th>
						<td>
							<html:text name="rs" property="zdfs" styleId="zdfs"
								maxlength="50" />
						</td>
						<th>联系电话</th>
						<td>
							<html:text name="rs" property="lxdh" styleId="lxdh"
								maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'') " />
						</td>
					</tr>
					<!--安徽建筑工业学院-->
					<logic:present name="isAHJZGYXY">
						<logic:equal value="is" name="isAHJZGYXY">
							<tr>
								<th>高考成绩</th>
								<td>
									<html:text name="rs" styleId="gkcj" property="gkcj"
										onkeypress="return sztzNumInputValue(this,5,event)"
										onblur="chkInput(this,event)" />
								</td>
								<th>高考英语成绩</th>
								<td>
									<html:text name="rs" property="gkyycj" styleId="gkyycj"
										onkeypress="return sztzNumInputValue(this,5,event)"
										onblur="chkInput(this,event)" />
								</td>
							</tr>
							<tr>
								<th>大学英语水平</th>
								<td>
									<html:text name="rs" maxlength="15" styleId="dxyysp"
										property="dxyysp" />
								</td>
								<th>计算机水平</th>
								<td>
									<html:text name="rs" maxlength="15" property="jsjsp"
										styleId="jsjsp" />
								</td>
							</tr>
							<tr>
								<th>籍贯</th>
								<td>
									<html:text name="rs" styleId="jg" property="jg" maxlength="15" />
								</td>
								<th>有无贷款记录</th>
								<td>
									<html:select name="rs" property="dkjl" styleId="dkjl"
										style="width:160px">
										<html:options collection="dkjlList" property="dkjl"
											labelProperty="dkjl" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>四年班级排名</th>
								<td colspan="3">
									<logic:empty name="rs" property="snbjpm">
										<html:textarea property='snbjpm' style="width:99%" rows='2'
											value="大一：第 名　大二：第 名　大三：第 名　大四：第 名" />
									</logic:empty>
									<logic:notEmpty name="rs"property="snbjpm">
										<html:textarea name="rs" property='snbjpm' style="width:99%"
											rows='2' />
									</logic:notEmpty>
								</td>
							</tr>
						</logic:equal>
					</logic:present>
					<!--end安徽建筑工业学院-->
					
					<!--贵州大学-->
					<logic:equal value="10657" name="xxdm">
						<tr>
							<th>机要号</th>
							<td>
								<html:text name="rs" maxlength="15" styleId="jyh" property="jyh" />
							</td>
							<th>档案处理人</th>
							<td>
								<html:text name="rs" maxlength="15" property="daclr" styleId="daclr" />
							</td>
						</tr>
						<tr>
							<th>档案处理人联系方式</th>
							<td colspan="3" >
								<html:text name="rs" maxlength="50" styleId="daclrlxfs"  property="daclrlxfs" />
							</td>
						</tr>
					</logic:equal>
					<!--贵州大学-->
					</logic:notEqual>
					<!--福建工程-->
				<%@ include file="/xsxx/fjgcxy/ljxsda_fjgcxy.jsp"%>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
						<logic:notEqual value="true" name="view">
			            <button class="button2"
							onclick="historyArchivesSave('/xgxt/stu_archives_history.do?doType=savaArchive&type=')"
							style="width:80px">
							保 存
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:notEqual>
						<button class="button2" onclick="window.close();return false;"
							style="width:80px">
							关 闭
						</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
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
					Close();
				</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
