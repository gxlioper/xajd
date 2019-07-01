<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/wjcfFuction.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getWjcfInfo.js"></script>
		<script type="text/javascript" src="wjcf/csmz/csmzJs/csmzJs.js"></script>
		<script language="javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript">
		function bbprint() {
			var xxdm = document.getElementById('xxdm').value;
			if ('13022'==xxdm) {
				var url = 'wjcf_nblg_cxprint.do';
				var pk = document.getElementById('pk').value;
				var xh = document.getElementById('xh').value;
				if (pk==null || pk=='') {
					url +='?xh=';
					url += xh;
				} else {
					url +='?pk=';
					url += pk;
				}
				
				window.open(url);
			} else {
				//expAppTab('rsTable','学生解除处分申请表','')
			}
		}
		
		function xscfcx() {
			
				var url = 'wjcf_nblg_cdtyWjcx.do?pkValue=';
				
				var pk = $("xh").value+$("cfwh").value+$("cfsj").value
				
				url += pk;
				window.open(url);
			
		}
	</script>
	</head>
	<body>

		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a> 
				<logic:notEqual value="10827" name="xxdm">
						<logic:notEqual value="13022" name="xxdm">
							违纪处分 -
								<logic:equal value="10827" name="xxdm">
									解除
								</logic:equal>
							<logic:notEqual value="10827" name="xxdm">解除处分管理 - 解除</logic:notEqual>申请
						</logic:notEqual>
					</logic:notEqual>
						<logic:equal value="13022" name="xxdm">
						违纪处分 - 解除察看期申请 - 填写申请表
					</logic:equal>
				</a>
			</p>
		</div>


		<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" />
		<input type="hidden" name="pk" value="${pk }" id="pk" />
		<html:form action="/wjcfcsmzwh" method="post">
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
				<input type="hidden" id="url" name="url"
					value="/csmz_cxcfsqdefault.do" />
				<input type="hidden" id="pkValue" name="pkValue" value="${pkVal }" />
				<input type="hidden" id="tabType" name="tabType" value="wjcf_cxcfb" />


				<div class="tab">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>填写申请表</span>
								</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="btn">
										<button type="button" id="btn_save"
											onclick="savesqinfo('xh','cfwh','savecxcfsqinfo.do')">
											提交申请
										</button>
										<logic:equal value="13022" name="xxdm">
										<button type="button" onclick="bbprint()">
											打印/预览
										</button>
										</logic:equal>
										<logic:equal name="print" value="true">
											<button type="button" class="button2" onclick="xscfcx()">
												撤销处分登记表
											</button>
										</logic:equal>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
								<tr >
									<logic:equal name="userOnLine" value="teacher" scope="session">
										<th width="15%">
											<font color="red">*</font>学号
										</th>
										<td align="left" width="25%">
											<html:text name='rs' property="xh" styleId="xh"
												onkeypress="autoFillStuInfo(event.keyCode,this);"
												maxlength="20" />
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
												readonly="true" />
										</td>
									</logic:equal>
									<th width="15%">
										<font color="red">*</font> 处分文件号
									</th>
									<td align="left" width="30%">
										<html:text name='rs' property="cfwh" styleId="cfwh"
											readonly="true" />
										<button type="button" onclick="wjcfInfoTo('cx')" class="button2"
											id="buttonFindWjcf">
											选择
										</button>
									</td>

								</tr>
								<tr >
									<th align="right">
										姓名
									</th>
									<td align="left">
										<html:text name='rs' property="xm" readonly="true" />
									</td>
									<th>
										学年
									</th>
									<td align="left">
										<html:text name='rs' property="xn" readonly="true" />
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
										学期
									</th>
									<td align="left">
										<html:text name='rs' property="xq" readonly="true" />
									</td>
								</tr>
								<tr>
									<th>
										年级
									</th>
									<td align="left">
										<html:text name='rs' property="nj" readonly="true" />
									</td>
									<th>
										处分时间
									</th>
									<td align="left">
										<html:text name='rs' property="cfsj" readonly="true" />
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
										处分类别
									</th>
									<td align="left" colspan="">
										<html:text name='rs' property="cflbmc" readonly="true" />
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
										处分事由
									</th>
									<td align="left" colspan="">
										<html:text name='rs' property="cfyymc" readonly="true" />
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
										申请时间
									</th>
									<td align="left">
										<html:text name='rs' property="cxsj" readonly="true"></html:text>
									</td>
								</tr>
								<tr>
									<th>
										申请理由<br />
										或现实表现
									</th>
									<td align="left" colspan="3">
										<html:textarea property="bz" styleId="bz"
											styleClass="inputtext;" rows="8" style="width:100%;word-break:break-all;"></html:textarea>
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
                    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
                      alert("提交失败,该生此处分撤消已申请过!");                   
                    </script>
				</logic:equal>
			</logic:notEmpty>
			<logic:equal value="no" name="isHG">
				<script>
                      alert("该生申请条件不合格，处分年限满一年才能申请!");                   
                    </script>
			</logic:equal>
		</html:form>
</html>
