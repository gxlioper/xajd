<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="js/wjcfFuction.js"></script>
	</head>
<body>
	<html:form action="/wjcfzjlgwh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
		<input type="hidden" name="failinfo" id="failinfo" value="${failinfo}"/>
		<input type="hidden" name="pkString" id="pkString" value="${pkString }"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>违纪处分 - 处分审核 - 批量处分审核</a>
			</p>
		</div>
		
		<div class="tab">
		<table class="formlist" width="99%">
			<thead>
				<tr style="height:23px">
					<th colspan="4">
						<span>批量处分审核</span>
					</th>
				</tr>
			</thead>
			<tr style="height:23px">
				<th>
					<font color="red">*</font>处分文号
				</th>
				<td align="left">
					<html:text property="cfwh" styleId="cfwh"></html:text>
				</td>
			</tr>
			<tr>
				<th>
					<font color="red">*</font>处分时间
				</th>
				<td align="left">
					<html:text property="cfsj" styleId="cfsj" onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('cfsj','y-mm-dd');" readonly="true"></html:text>
				</td>
			</tr>
			<tr style="height:23px">
					<logic:equal value="xy" name="userType">
						<th>
						<bean:message key="lable.xsgzyxpzxy" />处理<br/>意见
						</th>
						<td align="left" colspan="">
							<html:textarea property="xyyj" rows="5" styleId="xyyj" style="width:98%" ></html:textarea>
						</td>
					</logic:equal>
					<logic:notEqual value="xy" name="userType">
						<th>
							学校处理<br/>意见
						</th>
						<td align="left" colspan="">
							<html:textarea property="xxclyj" rows="5" styleId="xxclyj" style="width:98%" ></html:textarea>
						</td>
					</logic:notEqual>
			</tr>
			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
		          <div class="btn">
					<button type="button" id="btn_save" onclick="saveinfo('wjcf_zjlg_cfxxplsh.do?operType=save','cfwh-cfsj');" >
						保存
					</button>
					<button type="button" id="btn_close" onclick="window.close();return false;" >
						关闭
					</button>
		          </div></td>
		      </tr>
		    </tfoot>
		</table>
		</div>
				<!-- 保存后提示页面 -->	
		<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>
</html>
