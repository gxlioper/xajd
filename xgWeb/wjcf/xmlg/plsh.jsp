<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="js/wjcfFuction.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/commWjcfDAO.js"></script>
		
		<script type="text/javascript" >
			//保存信息
function savecfinfo(url,val) {
	var arrayList = val.split('-');
	for (var i=0; i<arrayList.length;i++) {
		if (document.getElementById(arrayList[i])) {
			if (document.getElementById(arrayList[i]).value=='') {
				alert("请将带\"*\"号的项目输入完整！");
				return false;
			}
		}
	}
	//先检测同一学生同一处分文号和时间是否有多条处分
	var pks = document.getElementById('keys').value;
	var cfsj = document.getElementById('cfsj').value;
	var cfwh = document.getElementById('cfwh').value;
	commWjcfDAO.checkStuWjcfIsExistsBypl(pks, cfsj, cfwh, function(data) {
		if (data != null && data != "") {
			var array = data.split("!@");
			var str = "";
			for (var i=0;i<array.length;i++) {
				if ("1" == array[i]) {
					str+=(i+1) + ",";
				}
			}
			if (str != "") {
				str = str.substr(0,str.length-1);
				alert("当前要发文的记录中，第"+str+"条已使用处分文号为:'" + cfwh+ "'，处分时间为:'" + cfsj+"'进行发文。\n同一学生同一处分时间与文号下面不能有二次处分，请更换处分时间和文号。");
				return false;
			} else {
				document.getElementById('btn_save').disabled = true;
				refreshForm(url);
			}
		}
	});
}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>违纪处分 - 处分审核 - 批量审核</a>
			</p>
		</div>

		<html:form action="/wjcfxmlgwh" method="post">
			<input type="hidden" name="userType" id="userType"
				value="${userType }" />
			<input type="hidden" name="keys" id="keys" value="${keys }" />

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>批量处分审核</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" id="btn_save"
										onclick="savecfinfo('wjcf_xmlg_plsh.do?operType=save','cfwh-cfsj-sh');">
										保 存
									</button>
									<button type="button" id="btn_close" onclick="window.close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="30%">

								<logic:equal value="11078" name="xxdm">
					违纪处分决定书文号
					</logic:equal>
								<logic:notEqual value="11078" name="xxdm">
						<font color="red">*</font>处分文号
					</logic:notEqual>
							</th>
							<td width="80%">
								<html:text property="cfwh" styleId="cfwh"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>处分时间
							</th>
							<td align="left">
								<html:text property="cfsj" styleId="cfsj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('cfsj','y-mm-dd');"></html:text>
							</td>
						</tr>
						<logic:notEqual value="12645" name="xxdm">
						<logic:equal value="11062" name="xxdm">
						
						
							<tr>
								<th>
									解除留校<br/>察看时间

								</th>
								<td align="left">
									<html:text property="lxcksj" styleId="lxcksj"
										onblur="dateFormatChg(this)" style="cursor:hand;"
										onclick="return showCalendar('lxcksj','y-mm-dd');"></html:text>
									<br />
									<font color="red">(只有受留校察看处分的需录入该数据)</font>
								</td>
							</tr>
							</logic:equal>
						</logic:notEqual>
						<tr >
							<th>
								<font color="red">*</font>审核
							</th>
							<td align="left">
								<html:select property="sh" styleId="sh">
									<html:options collection="chkList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								审核处理意见
							</th>
							<td align="left">
								<html:textarea property="yj" rows="4" style="width:90%">
								</html:textarea>
							</td>
						</tr>
						</tbody>
					</table>
					</div>
					<!-- 保存后提示页面 -->
					<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
		</html:form>
	</body>