<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<%@ taglib uri="/WEB-INF/customTag.tld" prefix="customTag"%>
<!-- 头文件 -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="js/xgutil.js"></script>
<script>
	//提交保存
	function tjbc(){
		var shjb = val('shjb');//审核级别
		if(shjb == "3"){//三级
			if(val('isFdy') == "true"){//辅导员
				if(val('xyshjg') == "通过"){
					alert("<bean:message key="lable.xsgzyxpzxy" />审核已经通过，暂时不能进行审核操作！");
					return false;
				}
			}else if(val('userType') == "xy" && val('xxshjg') == "通过"){
				alert('学校审核已经通过，暂时不能进行审核操作！');
				return false;
			}			
		}
		if(shjb == "2"){
			if(val('userType') == "xy" && val('xxshjg') == "通过"){
				alert('学校审核已经通过，暂时不能进行审核操作！');
				return false;
			}
		}
		
		saveinfo('pjpyTybZhszcp.do?method=zhszcpShOne&act=save&shjb='+shjb,'');
    }
</script>
<body>
	<html:form action="/pjpyTybZhszcp" method="post">
		<input type="hidden" name="userType" id="userType" value="${userType }" />
		<input type="hidden" name="bm" id="bm" value="${bm }"/>
		<input type="hidden" name="shjb" id="shjb" value="${shjb}"/>
		<input type="hidden" name="pkValue" id="pkValue" value="${rs.pkValue}"/>
		<input type="hidden" name="fdyshjg" id="fdyshjg" value="${rs.fdysh}"/>
		<input type="hidden" name="xyshjg" id="xyshjg" value="${rs.xysh}"/>
		<input type="hidden" name="xxshjg" id="xxshjg" value="${rs.xxsh}"/>
		<input type="hidden" name="isFdy" id="isFdy" value="${isFdy}"/>
		
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a></a>
			</p>
		</div>

		<table class="formlist" width="100%">
			<thead>
				<tr>
					<th colspan="4">
						<span>单个审核</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr style="height:23px">
				<th align="right" style="width: 25%">
					<font color="red">*</font>学号
				</th>
				<td align="left"  style="width: 25%">
					${rs.xh}
					
				</td>
				<th align="right"  style="width: 25%">
					姓名
				</th>
				<td align="left"  style="width: 25%">
					${rs.xm }
				</td>
			</tr>
			<tr style="height:23px">
				<th align="right">
					性别
				</th>
				<td align="left">
					${rs.xb }
				</td>
				<th align="right">
					年级
				</th>
				<td align="left">
					${rs.nj }
				</td>

			</tr>
			<tr style="height:23px">
				<th align="right">
					<bean:message key="lable.xsgzyxpzxy" />
				</th>
				<td align="left">
					${rs.xymc }
				</td>
				<th align="right">
					专业
				</th>
				<td align="left">
					${rs.zymc }
				</td>
			</tr>
			<tr style="height:23px">

				<th align="right">
					班级
				</th>
				<td align="left">
					${rs.bjmc }
				</td>
				<th align="right">
					学制
				</th>
				<td align="left">
					${rs.xz }
				</td>
			</tr>

			<tr>
				<logic:equal value="nd" name="pjzq">
					<th align="right">
						年度
					</th>
					<td align="left">
						${rs.nd }
					</td>
					<td></td>
					<td></td>
				</logic:equal>
				<logic:equal value="xn" name="pjzq">
					<th align="right">
						学年
					</th>
					<td align="left">
						${rs.xn }
					</td>
					<td></td>
					<td></td>
				</logic:equal>
				<logic:equal value="xq" name="pjzq">
					<th align="right">
						学年
					</th>
					<td align="left">
						${rs.xn }
					</td>
					<th align="right">
						学期
					</th>
					<td align="left">
						${rs.xqmc }
					</td>
				</logic:equal>				
			</tr>
			<customTag:customTable  rsname="rs" nodeslist = "pjpyZhszcpwhActionForm" doType="view" scope="request"/>
			<!--审核信息-->
			<!--三级审核-->
			<logic:equal value="3" name="shjb">
				<!--辅导员-->
				<logic:equal value="true" name="isFdy">				
					<tr>
						<th align="right">
							辅导员审核
						</th>
						<td colspan="3">
							<html:select property="save_fdysh" name="rs">
								<html:option value=""></html:option>
								<html:options collection="chkList" property="en" labelProperty="cn"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<th align="right">
							辅导员意见
						</th>
						<td colspan="3">
							<html:textarea property="save_fdyyj" name="rs" onblur="chLeng(this,1000)" rows="5" style="width:550px"></html:textarea>
						</td>
					</tr>
					<tr>
						<th align="right">
							<bean:message key="lable.xsgzyxpzxy" />审核
						</th>
						<td colspan="">
							${rs.xysh}
						</td>
						<th align="right">
							<bean:message key="lable.xsgzyxpzxy" />意见
						</th>
						<td colspan="">
							${rs.xyyj}
						</td>
					</tr>
			
					<tr>
						<th align="right">
							学校审核
						</th>
						<td colspan="">
							${rs.xxsh}
						</td>
						<th align="right">
							学校意见
						</th>
						<td colspan="">
							${rs.xxyj}
						</td>
					</tr>
					
				</logic:equal>
				<!--end辅导员-->
				<!--非辅导员-->
				<logic:notEqual value="true" name="isFdy">
					<!--<bean:message key="lable.xsgzyxpzxy" />-->
					<logic:equal value="xy" name="userType">
					<tr>
						<th align="right">
							辅导员审核
						</th>
						<td colspan="">
							${rs.fdysh}
						</td>
						<th align="right">
							辅导员意见
						</th>
						<td colspan="">
							${rs.fdyyj}
						</td>
					</tr>
					
					<tr>
						<th align="right">
							<bean:message key="lable.xsgzyxpzxy" />审核
						</th>
						<td colspan="3">
							<html:select property="save_xysh" name="rs">
								<html:option value=""></html:option>
								<html:options collection="chkList" property="en" labelProperty="cn"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<th align="right">
							<bean:message key="lable.xsgzyxpzxy" />意见
						</th>
						<td colspan="3">
							<html:textarea property="save_xyyj" name="rs" onblur="chLeng(this,1000)" rows="5" style="width:550px"></html:textarea>
						</td>
					</tr>
					<tr>
						<th align="right">
							学校审核
						</th>
						<td colspan="">
							${rs.xxsh}
						</td>
						<th align="right">
							学校意见
						</th>
						<td colspan="">
							${rs.xxyj}
						</td>
					</tr>
			
					</logic:equal>
					<!--end<bean:message key="lable.xsgzyxpzxy" />-->
					<!--学校-->
					<logic:notEqual value="xy" name="userType">
					<tr>
						<th align="right">
							辅导员审核
						</th>
						<td colspan="">
							${rs.fdysh}
						</td>
						<th align="right">
							辅导员意见
						</th>
						<td colspan="">
							${rs.fdyyj}
						</td>
					</tr>
		
					<tr>
						<th align="right">
							<bean:message key="lable.xsgzyxpzxy" />审核
						</th>
						<td colspan="">
							${rs.xysh}
						</td>
						<th align="right">
							<bean:message key="lable.xsgzyxpzxy" />意见
						</th>
						<td colspan="">
							${rs.xyyj}
						</td>
					</tr>
					<tr>
						
					</tr>
					<tr>
						<th align="right">
							学校审核
						</th>
						<td colspan="3">
							<html:select property="save_xxsh" name="rs">
								<html:option value=""></html:option>
								<html:options collection="chkList" property="en" labelProperty="cn"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<th align="right">
							学校意见
						</th>
						<td colspan="3">
							<html:textarea property="save_xxyj" name="rs" onblur="chLeng(this,1000)" rows="5" style="width:550px"></html:textarea>
						</td>
					</tr>	
					</logic:notEqual>
					<!--end学校-->
				</logic:notEqual>
				<!--end非辅导员-->			
			</logic:equal>
			<!--end三级审核-->	

			
			<!--二级审核-->
			<logic:equal value="2" name="shjb">
			<!--<bean:message key="lable.xsgzyxpzxy" />审核-->
			<logic:equal value="xy" name="userType">
				<tr>
					<th align="right">
						<bean:message key="lable.xsgzyxpzxy" />审核
					</th>
					<td colspan="3">
						<html:select property="save_xysh" name="rs">
							<html:option value=""></html:option>
							<html:options collection="chkList" property="en" labelProperty="cn"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th align="right">
						<bean:message key="lable.xsgzyxpzxy" />意见
					</th>
					<td colspan="3">
						<html:textarea property="save_xyyj" name="rs" onblur="chLeng(this,1000)" rows="5" style="width:550px"></html:textarea>
					</td>
				</tr>
				<tr>
					<th align="right">
						学校审核
					</th>
					<td colspan="3">
						${rs.xxsh}
					</td>
				</tr>
				<tr>
					<th align="right">
						学校意见
					</th>
					<td colspan="3">
						${rs.xxyj}
					</td>
				</tr>
			</logic:equal>
			<!--end<bean:message key="lable.xsgzyxpzxy" />审核-->
			<!--学校审核-->
			<logic:notEqual value="xy" name="userType">
			<tr>
					<th align="right">
						<bean:message key="lable.xsgzyxpzxy" />审核
					</th>
					<td colspan="3">
						${rs.xysh}
					</td>
				</tr>
				<tr>
					<th align="right">
						<bean:message key="lable.xsgzyxpzxy" />意见
					</th>
					<td colspan="3">
						${rs.xyyj}
					</td>
				</tr>
				<tr>
					<th align="right">
						学校审核
					</th>
					<td colspan="3">
						<html:select property="save_xxsh" name="rs">
							<html:option value=""></html:option>
							<html:options collection="chkList" property="en" labelProperty="cn"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th align="right">
						学校意见
					</th>
					<td colspan="3">
						<html:textarea property="save_xxyj" name="rs" onblur="chLeng(this,1000)" rows="5" style="width:550px"></html:textarea>
					</td>
				</tr>
			</logic:notEqual>
			<!--end学校审核-->
			</logic:equal>
			<!--end二级审核-->
			
			<!--一级审核-->
			<logic:equal value="1" name="shjb">
			<tr>
				<th align="right">
					学校审核
				</th>
				<td colspan="3">
					<html:select property="save_xxsh" name="rs">
						<html:option value=""></html:option>
						<html:options collection="chkList" property="en" labelProperty="cn"/>
					</html:select>
				</td>
			</tr>
			<tr>
				<th align="right">
					学校意见
				</th>
				<td colspan="3">
					<html:textarea property="save_xxyj" name="rs" onblur="chLeng(this,1000)" rows="5" style="width:550px"></html:textarea>
				</td>
			</tr>	
			</logic:equal>		
			<!--end一级审核-->
			<tfoot>
				      <tr>
				        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
				          <div class="btn">
				            <logic:notEqual value="view" name="flag">
								<button type="button" class="" id="btn_save"
					onclick="tjbc()">
					保 存
				</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:notEqual>
							<button type="button" class="" id="btn_close" onclick="Close();return false;"id="buttonClose">
								关 闭
							</button>		
				          </div>
				        </td>
				      </tr>
				    </tfoot>	
		</table>	
		
		<!-- 保存提示信息 -->
		<jsp:include flush="true" page="/syscommon/ty_saveprompt.jsp"></jsp:include>
	</html:form>
</body>

