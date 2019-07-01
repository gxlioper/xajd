<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/xgutil.js"></script>
	<script language="javascript">
		function stringFormat(){
			var element = ['kcjqgzxsj','sqly','bz'];
			for(var i=0; i<element.length; i++){
				if(ele(element[i])){
					ele(element[i]).innerHTML = formatContentWidth(ele(element[i]).innerText,30);
				}
			}
		}
	</script>
</head>
<body onload="stringFormat()">
	<html:form action="/data_search" method="post">
		<input type="hidden" id="doType" name="doType" value="<bean:write name="doType" scope="request"/>" />
		<input type="hidden" id="pkValue" name="pkValue" value="<bean:write name="pkValue" scope="request"/>" />
		<input type="hidden" id="disableEle" name="disableEle" value="xm-xb-xy-nj-zy-bj" />
		<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-xymc-nj-zymc-bjmc" />
		<input type="hidden" id="url" name="url" value="/sjcz/xsgwxxb.jsp" />
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>勤工助学 - 申请 - 申请结果查询 - 详细信息</a>
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
			<div class="tab">
		  	<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>岗位信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th><span class="red">*</span>学号：
					</td>
					<td align="left">
						${rs.xh}
					</td>
					<th>
						<logic:equal value="11551" name="xxdm">
							<span class="red">*</span>申请岗位
						</logic:equal>
						<logic:notEqual value="11551" name="xxdm">
							<span class="red">*</span>岗位名称
						</logic:notEqual>						
					</th>
					<td>
						${rs.gwdm}
					</td>
				</tr>
				<tr>
					<th>姓名</th>
					<td>
						${rs.xm}
					</td>
					<th>年度</th>
					<td>
						${rs.nd}
					</td>
				</tr>
				<tr>
					<th>性别</th>
					<td>
						${rs.xb}
					</td>
					<th>学年</th>
					<td>
						${rs.xn}
					</td>
				</tr>
				<tr>
					<th>年级</th>
					<td>
						${rs.nj}
					</td>
					<th>学期</th>
					<td>
						${rs.xqmc}
					</td>
				</tr>
				<tr>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						${rs.xymc}
					</td>
					<th>联系电话</th>
					<td>
						${rs.lxdh}
					</td>
				</tr>
				<tr>
					<th>专业</th>
					<td>
						${rs.zymc}
					</td>
					<logic:notEqual value="11551" name="xxdm">
					<!--浙江科技学院-->
					<logic:equal value="11057" name="xxdm">
					<th>家庭地址</th>
					<td id="kcjqgzxsj">
						${rs.jtdz}
					</td>
					</logic:equal>
					<logic:notEqual value="11057" name="xxdm">
					<th>可参加勤工助学时间</th>
					<td id="kcjqgzxsj">
						${rs.kcjqgzxsj}
					</td>
					</logic:notEqual>
					</logic:notEqual>
					<logic:equal value="11551" name="xxdm">
					<th>负责人</th>
					<td>${rs.fzr}</td>
					</logic:equal>
				</tr>
				<tr style="height:22px">
					<th>班级</th>
					<td>
						${rs.bjmc}
					</td>
					<th>是否困难生</th>
					<td>
					    ${rs.sfpks}
					</td>
				</tr>
				<logic:equal value="10690" name="xxdm" scope="session">
				<tr>
					<th>政治面貌</th>
					<td>
						${rs.zzmmmc}
					</td>
					<th>家庭地址</th>
					<td>
						${rs.jtdz}
					</td>
				</tr>
				
				<tr>
					<th>民族</th>
					<td>
						${rs.mzmc}
					</td>
					<th>家庭电话</th>
					<td>
						${rs.jtdh}						
					</td>
				</tr>
				<tr>
				  <th>国家助学贷款</th>
				  <td>${rs.gjzxdk}</td>
				  <th>家庭邮编</th>
				  <td>${rs.jtyb}</td>
			  </tr>
				<tr>
				  <th>其它资助情况</th>
				  <td>${rs.zzqk}</td>
				  <th>家庭成员信息</th>
				  <td>${rs.jtcy}</td>
			  </tr>
				<tr>
				  <th>贫困等级</th>
				  <td>${rs.pkdj}</td>
				  <th>家庭月收入</th>
				  <td>${rs.jtysr}</td>
			  </tr>
				<tr>
				  <th>性格</th>
				  <td>${rs.xg} <br/> (内向为1→外向为10)</td>
				  <th>特长</th>
				  <td>${rs.yhtc}</td>
			  </tr>
				<tr>
				  <th>劳动经历</th>
				  <td>${rs.gzjl}</td>
				  <th>劳动意向</th>
				  <td>${rs.ldyx}</td>
			  </tr>
			  </logic:equal>
			   <!-- 湖北交通 -->
			  	<logic:equal value="12752" name="xxdm" scope="session">
			  		<tr>
			  			<th>
			  				辅导员审核
			  			</th>
			  			<td colspan="3">
			  				${rs.fdyyj}
			  			</td>
			  		</tr>
			  	</logic:equal>
			  	<!-- 浙江交通职业技术学院-->
			  	<logic:equal value="12036" name="xxdm" scope="session">
			  		<tr>
			  			<th>
			  				辅导员审核
			  			</th>
			  			<td colspan="3">
			  				${rs.fdyyj}
			  			</td>
			  		</tr>
			  	</logic:equal>
			  	<logic:equal value="11654" name="xxdm" scope="session">
			  		<tr>
			  			<th>
			  				辅导员审核
			  			</th>
			  			<td colspan="3">
			  				${rs.fdyyj}
			  			</td>
			  		</tr>
			  	</logic:equal>
				<tr>
					<logic:equal value="13022" name="xxdm" scope="session">
					<th><bean:message key="lable.xsgzyxpzxy" />审核</th>
					<td>
					    ${rs.xyyj}
					</td>
					</logic:equal>
					<logic:equal value="12862" name="xxdm" scope="session">
					<th>勤工管理员审核</th>
					<td colspan="3">
					    ${rs.xxyj}
					</td>
					</logic:equal>
					<logic:notEqual value="12862" name="xxdm" scope="session">
						<logic:notEqual value="13022" name="xxdm" scope="session">
						<th> 用人单位审核</th>
						<td>
						    ${rs.xyyj}
						</td>
						</logic:notEqual>
						<th>学校审核</th>
						<td>
							${rs.xxyj}
						</td>
					</logic:notEqual>
				</tr>
				<%--云南艺术--%>
				<logic:equal value="10690" name="xxdm" scope="session">
					<tr>
						<th>学生处审核</th>
						<td >${rs.xscyj}</td>
						<th>辅导员审核</th>
						<td>${rs.fdyyj}</td>
					</tr>
					<tr>
						<th>编号</th>
						<td>${rs.bh}</td>
						<th>工号</th>
						<td>${rs.gh}</td>
					</tr>
				</logic:equal>
				<tr>
					<th>申请理由</th>
					<td colspan="3" id="sqly">
						${rs.xssq}
					</td>
				</tr>
				<logic:equal value="10690" name="xxdm">
				<tr>
					<th>学校审核意见</th>
					<td colspan="3">
						${rs.xxshyj}
					</td>
				</tr>
				</logic:equal>
				<tr>
					<th>备注</th>
					<td colspan="3" id="bz">
						${rs.bz}
					</td>
				</tr>
				<logic:equal value="11551" name="xxdm">
				<tr>
				<th>可参加勤工助学时间</th>
				<td colspan="3">
				<logic:notEmpty name="kxList">
						<table border="0" cellpadding="0" cellspacing="0" align="center" class="formlist">
							<tr>
								<td align="center">时间</td>
								<td>星期一</td>
								<td>星期二</td>
								<td>星期三</td>
								<td>星期四</td>
								<td>星期五</td>
								<td>星期六</td>
								<td>星期日</td>
							</tr>								
							<logic:iterate id="kxsj" name="kxList">
							<tr>
								<td>${kxsj.sj}</td>
								<td align="center"><input type="checkbox" name="${kxsj.sjIndex}1" value="1"/><input type="hidden" name="index${kxsj.sjIndex}1" value="${kxsj.mon}"/></td>
								<td align="center"><input type="checkbox" name="${kxsj.sjIndex}2" value="1"/><input type="hidden" name="index${kxsj.sjIndex}2" value="${kxsj.tue}"/></td>
								<td align="center"><input type="checkbox" name="${kxsj.sjIndex}3" value="1"/><input type="hidden" name="index${kxsj.sjIndex}3" value="${kxsj.wed}"/></td>
								<td align="center"><input type="checkbox" name="${kxsj.sjIndex}4" value="1"/><input type="hidden" name="index${kxsj.sjIndex}4" value="${kxsj.thu}"/></td>
								<td align="center"><input type="checkbox" name="${kxsj.sjIndex}5" value="1"/><input type="hidden" name="index${kxsj.sjIndex}5" value="${kxsj.fri}"/></td>
								<td align="center"><input type="checkbox" name="${kxsj.sjIndex}6" value="1"/><input type="hidden" name="index${kxsj.sjIndex}6" value="${kxsj.sat}"/></td>
								<td align="center"><input type="checkbox" name="${kxsj.sjIndex}7" value="1"/><input type="hidden" name="index${kxsj.sjIndex}7" value="${kxsj.sun}"/></td>
								
							</tr>
							</logic:iterate>
							</table>
							<script>
									for(var i=0;i<7;i++){
										for(var j=1;j<8;j++){
										 	if(document.getElementById("index"+i+j)){
										 		if(document.getElementById("index"+i+j).value==1){
													document.getElementById(i+""+j).checked="checked";
											    }
										 	}
										}
									}
								</script>
							</logic:notEmpty>
				</td>
				</tr>
				</logic:equal>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4">
			          <div class="btn">
			            <button type="button" class="button2" id="btn_gb"
							onclick="Close();return false;"
							style="width:80px">
							关 闭
						</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
		</div>
		</logic:notEmpty>
	</html:form>
</body>
</html>