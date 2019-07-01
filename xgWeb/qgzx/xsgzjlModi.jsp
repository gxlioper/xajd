<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>		
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/qgzxgzkh.js"></script>
	<script>
		function save(){
			if(filedNotNull('zgzsj','-')){
				refreshForm('qgzxkh.do?method=modiXsgzjl');
			} else {
				alert ('请将带\*号的项目填写完整！');
				return false;
			}
		}
	</script>
</head>
	<body>
		<html:form action="/qgzxkh.do">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>勤工助学 - 考核 - 学生工作记录 - 修改信息</a>
				</p>
			</div>
			<div class="tab">
				<table width="100%" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生工作记录信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<th>
							<span class="red">*</span>学号
							<input type="hidden" name="xh" id="xh" value="${rs.xh}"/>
						</th>
						<td>
							${rs.xh}							
						</td>
						<th><span class="red">*</span>岗位</th>
						<td>
							${rs.gwdm}
							<input type="hidden" name="gwdm" id="rq" value="${rs.gwdm}"/>
							<input type="hidden" name="gwsbsj" id="rq" value="${rs.gwsbsj}"/>
						</td>
					</tr>
					<tr>
						<th>姓名</th>
						<td>
							${rs.xm}
						</td>
						<th><span class="red">*</span>年度</th>
						<td>
							${rs.nd}
							<input type="hidden" name="nd" id="rq" value="${rs.nd}"/>
						</td>						
					</tr>
					<tr>
						<th>性别</th>
						<td>
							${rs.xb}
						</td>
						<th><span class="red">*</span>月份</th>
						<td>
							${rs.yf}
							<input type="hidden" name="yf" id="yf" value="${rs.yf}"/>
						</td>						
					</tr>
					<tr>
						<th>年级</th>
						<td>
							${rs.nj}
						</td>
						<th><span class="red">*</span>日期</th>
						<td> 
							${rs.rq}
							<input type="hidden" name="rq" id="rq" value="${rs.rq}"/>
						</td>						
					</tr>
					<tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							${rs.xymc}
						</td>
						<th>工作开始时间</th>
						<td>
							<html:text name="rs" property="gzkssj" maxlength="10" onkeyup="value=value.replace(/[^\d|:]/g,'')" ></html:text>
						</td>						
					</tr>
					<tr>
						<th>专业</th>
						<td>
							${rs.zymc}
						</td>
						<th>工作结束时间</th>
						<td>
							<html:text name="rs" property="gzjssj" maxlength="10" onkeyup="value=value.replace(/[^\d|:]/g,'')" ></html:text>
						</td>						
					</tr>
					<tr>
						<th>班级</th>
						<td>
							${rs.bjmc}
						</td>
						<th><span class="red">*</span>总工作时间</th>
						<td>
							<html:text name="rs" property="zgzsj" maxlength="4" onkeyup="value=value.replace(/[^\d|.]/g,'')" ></html:text>
						</td>
					</tr>
					<tr>
						<th>工作内容</th>
						<td colspan="3">
							<html:textarea name="rs" property="gznr" styleId="gznr" cols="80" rows="4" onblur="chLeng(this,'300')"></html:textarea>
						</td>
					</tr>
					</tbody>
					<tfoot>
				      <tr>
				        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
				          <div class="btn">
							<logic:notEqual value="view" name="doType">
				            <button type="button"
								onclick="save();return false;"
								style="width:80px">
								保 存
							</button>
							</logic:notEqual>
							<button type="button" onclick="window.close();return false;"
								style="width:80px">
								关 闭
							</button>
				          </div>
				        </td>
				      </tr>
				    </tfoot>
				</table>
				</div>
			 <logic:notEmpty name="result">
				<logic:equal value="true" name="result">
					<script>
					alert("操作成功！");
					Close();
					window.dialogArguments.document.getElementById('search_go').click();		
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
