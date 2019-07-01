<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript"  src="js/pjpy/pjpyFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getWjdcDAO.js'></script>
		<script language="javascript"  src="js/wjdc/wjdc.js"></script>
		<script language="javascript"  src="js/wjdc/wjdcMk.js"></script>
	</head>
	<body onload="">
		<html:form action="/wjdc">
			<!-- 隐藏域 -->
			<%@ include file="/wjdc/hiddenValue.jsp"%>
			<!-- 隐藏域 end-->
			
			<div class="tab">
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button name="关 闭" onclick="window.close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<td colspan="4">
								<span>基本信息</span>
							</td>
						</tr>
					</thead>
					<tbody>
					<tr>
						<th width="15%">
							学年
						</th>
						<td width="35%">
							<html:hidden name="rs" property="xn"/>
							${rs.xn }
						</td>
						<th width="15%">
							学期
						</th>
						<td>
							<html:hidden name="rs" property="xq"/>
							${rs.xqmc }
						</td>
					</tr>
					<tr>
						<th>
							年度
						</th>
						<td>
							<html:hidden name="rs" property="nd"/>
							${rs.nd }
						</td>
						<th>
							建立时间
						</th>
						<td>
							<html:hidden name="rs" property="jlsj"/>
							${rs.jlsjmc }
						</td>
					</tr>
					</tbody>
					<thead>
						<tr>
							<td colspan="4">
								<span>问卷信息</span>
							</td>
						</tr>
					</thead>
					<tbody>
					<tr>
						<th>
							<font color="red">*</font>问卷名称
							<br/>
							<font color="red">(限50字)</font>
						</th>
						<td colspan="3">
							<html:text name="rs" property="wjmc" styleId="wjmc" style="width: 90%" maxlength="50" readonly="true"/>			
						</td>
					</tr>
					<tr>
						<th>
							备注
							<br/>
							<font color="red">(限500字)</font>
						</th>
						<td colspan="3">
							<html:textarea name="rs" property="bz" rows="5" styleId="bz" readonly="true"
								onblur="chLeng(this,500)" style="width: 90%"/>
						</td>
					</tr>
					</tbody>
					<thead>
						<tr>
							<td colspan="4">
								<span>回答内容</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<table width="100%" class="tbstyle">
									<logic:iterate name="zjlxList" id="lx">
										<thead>
											<tr>
												<td align="center" colspan="2">
													${lx.lxmc}
												</td>
											</tr>
										</thead>
										<!-- 单选题 -->
										<logic:equal name="lx" property="lxdm" value="oneChoose">
											<%@ include file="stxx/pjOneChoose.jsp"%>
										</logic:equal>
										<!-- 多选题 -->
										<logic:equal name="lx" property="lxdm" value="allChoose">
											<%@ include file="stxx/pjAllChoose.jsp"%>
										</logic:equal>
											<!-- 问答题 -->
										<logic:equal name="lx" property="lxdm" value="questions">
											<%@ include file="stxx/pjQueChoose.jsp"%>
										</logic:equal>
									</logic:iterate>		
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('操作成功！');
						dialogArgumentsQueryChick();
						window.close();
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert('操作失败！');
					</script>
				</logic:equal>
			</logic:present>	
		</html:form>
	</body>
</html>
