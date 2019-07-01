<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
		<script language="javascript" src="js/qtsjFunction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getOtherData.js'></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript">
		function changeZpzw(flg,obj){
			if ('sel'==flg){
				if(obj.checked){$('zydm').style.display='';}{$('txtZpzw').style.display='none'}
			}else {
				if(obj.checked){$('zydm').style.display='none';}{$('txtZpzw').style.display=''}
			}
		}
		</script>
	</head>
	<body>
		<html:form action="/jyweb.do" method="post">
			<input type="hidden" name="pkValue" value="${param.pkValue }" />
			<input type="hidden" name="save_fbsj" value="${fbsj }"  id="fbsj"/>
			<input type="hidden" name="save_fblx" value="单位" />
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>招聘信息发布</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<logic:notEqual value="view" name="operation">
										<button
											onclick="saveDataShowTips('jywebUseCheckSession.do?method=workAdd&doType=save',
              'zpzw-gwxz-gzdd-zprs-xb-lxr-yddh-yxqx','正在处理数据请稍候!')">
											提 交
										</button>
										<button onclick="Close();return false;">
											关闭
										</button>
									</logic:notEqual>

								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="60px">
								公司名称
							</th>
							<td>	${companyInfo.dwmc}
									<html:hidden property="save_gsmc" value="${companyInfo.dwmc}" />
							</td>
							<th width="60px">
								公司地址
							</th>
							<td>
								${companyInfo.dz }
							</td>
						</tr>
						<tr>
							<th>
								公司性质
							</th>
							<td>
								${companyInfo.dwxzmc }
							</td>
							<th>
								行业分类
							</th>
							<td>
								${companyInfo.hyflmc }
							</td>
						</tr>
						<tr>
							<th>
								传 真
							</th>
							<td>
								${companyInfo.cz }
							</td>
							<th>
								网 址
							</th>
							<td>
								${companyInfo.wz }
							</td>
						</tr>
						<tr>
							<th>
								公司简介
							</th>
							<td colspan="3" style="word-break:break-all;">
								${companyInfo.dwjj }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<td colspan="4">
								<span>岗位信息</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>招聘类型
							</th>
							<td colspan="3">
								<input type="radio" name="save_zplx" value="zy"
									onclick="changeZpzw('sel',this);" checked="checked"/>专业
								<input type="radio" name="save_zplx" value="gw"
									onclick="changeZpzw('',this);" />岗位
							</td>
						</tr>
						<tr>
							<th width="70px">
								<font color="red">*</font>专业/岗位名称
							</th>
							<td>
								<html:hidden property="save_zpzw" styleId="zpzw"/>

									<html:text property="zpzw" styleId="txtZpzw"
										onchange="$('zpzw').value=this.value" style="display:none" />

									<html:select property="zydm" styleId="zydm" style="display:''"
										onchange="$('zpzw').value=this.value">
										<html:options collection="zyList" property="dm"
											labelProperty="mc" />
									</html:select>
							</td>
							<th>
								<font color="red">*</font>岗位性质
							</th>
							<td>
								<!--                  	<html:text property="save_gwxz" styleId="gwxz" /><span class="correct"></span>-->
								<html:select property="save_gwxz" styleId="gwxz">
									<html:option value="全职">全职</html:option>
									<html:option value="兼职">兼职</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>工作地点
							</th>
							<td>
								<html:text property="save_gzdd" styleId="gzdd" />
							</td>
							<th>
								<font color="red">*</font>招聘人数
							</th>
							<td>
								<html:text property="save_zprs" onblur="checkInputData(this);"
									maxlength="5" styleId="zprs" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>性别要求
							</th>
							<td>
								<html:select property="save_xb" styleId="xb">
									<html:options collection="xbList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
							<th>
								<font color="red">*</font>学历要求
							</th>
							<td>
								<html:text property="save_xlyq" styleId="xlyq" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>外语要求
							</th>
							<td>
								<html:text property="save_wyyq" styleId="wyyq" />
							</td>
							<th>
								<font color="red">*</font>有效期限
							</th>
							<td>
								<html:text property="save_yxqx" styleId="yxqx"
									 onblur="dateFormatChg(this);if(this.value < document.getElementById('fbsj').value){alert('有效期不能早于当前时间！'); this.value=''}" 
									 onclick="showCalendar(this.id,'y-mm-dd')"
									/>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>联系人
							</th>
							<td>
								<html:text property="save_lxr" styleId="lxr" />
							</td>
							<th>
								<font color="red">*</font>联系电话
							</th>
							<td>
								<html:text property="save_lxdh" onblur="checkInputData(this);"
									styleId="lxdh" />
							</td>
						</tr>
						<tr>
							<th>
								招聘时间
							</th>
							<td>
								<html:text property="save_zpsj" styleId="zpsj"
									onclick="showCalendar(this.id,'y-mm-dd')"
									onblur="dateFormatChg(this);" />
							</td>
							<th>
								招聘地点
							</th>
							<td>
								<html:text property="save_zpdd" styleId="zpdd" maxlength="50"/>
							</td>
						</tr>
						<tr>
							<th valign="top">
								岗位职责
							</th>
							<td colspan="3">
								<html:textarea property="save_gwzz" styleId="gwzz" rows="7"
									style="float:left; width: 70%">
								</html:textarea>
								<span style="color: red">（请详细描述职责范围、工作任务以及取得的成绩等限1500个中文字）</span>
							</td>
						</tr>
						<tr>
							<th valign="top">
								职位要求
							</th>
							<td colspan="3">
								<html:textarea property="save_zwyq" styleId="zwyq" rows="7"
									style="float:left;width: 70%"></html:textarea>
								<span  style="color: red">（请详细描述职责范围、工作任务以及取得的成绩等限1500个中文字）</span>
							</td>
						</tr>
					</tbody>
				</table>
			</div>

			<logic:present name="message">
				<script>
		 			alert("${message}");
		 			
		 			if(window.dialogArguments){
		 				close();
		 				dialogArgumentsQueryChick();
		 			}
		 			
		 		</script>
			</logic:present>
		</html:form>
	</body>
</html>
