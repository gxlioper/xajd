<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script language="javascript" src="js/pjpy/pjpyFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type='text/javascript'
		src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getPjpyInfo.js'></script>
	<script type='text/javascript' src="js/check.js"></script>
	<script language="javascript" src="js/BatAlert.js"></script>
	<script type="text/javascript" src="js/qtsj/xywhgl.js">
<script type="text/javascript" >
	jQuery(function(){
		onShow();
	})
</script>
	<body >
		<html:form action="/gdby_xywhgl.do" enctype="multipart/form-data">
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：${title }
				</div>
			</div>

			<input type="hidden" id="doType" name="doType" value="" />
			<input type="hidden" id="pkValue" name="pkValue" value="${pkValue }" />
			<fieldset>
				<legend>
					校园文化管理
				</legend>
				<table class="tbstyle" border="0" id="rsTable" align="center"
					style="width: 100%">
					<thead>
						<tr style="height:22px">
							<td colspan="4" align="center">
								<b>校园文化发布</b>
							</td>
						</tr>

						<tr>
							<td align="right" width="20%">
								<font color="red">*</font>活动代码：
							</td>
							<td align="left" width="30%">
								<html:text property="hddm"></html:text>
							</td>
							<td align="right" width="20%">
								<font color="red">*</font>活动名称：
							</td>
							<td align="left" width="30%">
								<html:text property="hdmc"></html:text>
							</td>
						</tr>
						<tr>
							<logic:notPresent name="fbxx">
								<td align="right">
									附件上传：
								</td>
								<td align=left colspan="3">
									<input type="file" name="uploadFile" style="width:60%" id="kk">
									&nbsp;&nbsp;
									<logic:present name="filebig"><font color="red">${filebig }</font></logic:present>
									<logic:notPresent name="filebig">
									<font color="red">(文件大小小于&lt;10M&gt;)</font>
									</logic:notPresent>
								</td>
							</logic:notPresent>


							<logic:present name="fbxx">
								<logic:empty name="fbxx" property="fj">
									<td align="right">
										附件上传：
									</td>
									<td align=left colspan="3">
										<input type="file" name="uploadFile" style="width:60%" id="kk">
										&nbsp;&nbsp;
									<logic:present name="filebig"><font color="red">${filebig }</font></logic:present>
									<logic:notPresent name="filebig">
									<font color="red">(文件大小小于&lt;10M&gt;)</font>
									</logic:notPresent>
									</td>
								</logic:empty>
								<logic:notEmpty name="fbxx" property="fj">
									<td align="right">
										下载附件：
									</td>
									<td align=left colspan="3">
										<a href="czxxDtjsDyxx.do?method=downLoadFile&dir=${fbxx.fj }"
											target="_blank" />点击下载</a>
<%--											&nbsp;&nbsp;--%>
<%--										<a href="javascript:refreshForm('/xgxt/czxxDtjsDyxx.do?method=fileDel')" />点击删除</a>--%>
									</td>
								</logic:notEmpty>
							</logic:present>

						</tr>
						<tr>
							<td align="right">
								活动描述：<br/>
								<font color="red">(限制在400字内)</font>
							</td>
							<td align="left" colspan="3">
								<html:textarea property="hdms" style="width:99%" rows='10' onblur="chLeng(this,400)"></html:textarea>
							</td>
						</tr>


					</thead>
				</table>
			</fieldset>
			<fieldset>
				<legend>
					活动场地
				</legend>

				<p>
					<input type="button" value="+"
						onclick="trAdd('flag1','add','numAdd1','rzqk');"
						style="width: 20px" />
					<input type="button" value="-" onclick="trDel('flag1','delRow1');"
						style="width: 20px" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 生成&nbsp;&nbsp;
					<input type="text" name="numAdd" id="numAdd1" style="width: 20px"
						onblur="trAdd('flag1','madd','numAdd1','rzqk')">
					&nbsp;行 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 清除&nbsp;&nbsp;
					<input type="text" name="numDel" id="numDel1" style="width: 20px"
						onblur="trDelAll('flag1','numDel1')">
					&nbsp;行&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</p>
				<table class="tbstyle" align="center" width="100%" id="tTb">
					<tr>
						<td>
							<div class="mid_box">
								<table align="center" style="width: 100%" id="rsT"
									class="tbstyle">
									<!-- 打印时第一行不显示- -->
									<thead style="height: 23px">
										<tr>
											<td align="center" nowrap="nowrap" style='width:6%'>
												选定删除行
											</td>
											<td align="center" nowrap="nowrap" style='width:17%'>
												活动场地
											</td>
										</tr>
									</thead>
									<tbody width="100%" align="center" class="tbstyle" id="flag1">
									</tbody>
								</table>
							</div>
						</td>
					</tr>
				</table>
			</fieldset>

			<fieldset>
				<legend>
					活动物资
				</legend>
				<p>
					<input type="button" value="+"
						onclick="trAdd('flag2','add','numAdd2','hjqk');"
						style="width: 20px" />
					<input type="button" value="-" onclick="trDel('flag2','delRow2');"
						style="width: 20px" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 生成&nbsp;&nbsp;
					<input type="text" name="numAdd2" id="numAdd" style="width: 20px"
						onblur="trAdd('flag2','madd','numAdd2','hjqk')">
					&nbsp;行 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 清除&nbsp;&nbsp;
					<input type="text" name="numDel" id="numDel2" style="width: 20px"
						onblur="trDelAll('flag2','numDel2')">
					&nbsp;行&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</p>
				<table class="tbstyle" align="center" width="100%" id="tTb">
					<tr>
						<td>
							<div class="mid_box">
								<table align="center" style="width: 100%" id="rsT"
									class="tbstyle">
									<!-- 打印时第一行不显示- -->
									<thead style="height: 23px">
										<tr>
											<td align="center" nowrap="nowrap" style='width:6%'>
												选定删除行
											</td>
											<td align="center" nowrap="nowrap" style='width:17%'>
												物资名称
											</td>
										</tr>
									</thead>
									<tbody width="100%" align="center" class="tbstyle" id="flag2">
									</tbody>
								</table>
							</div>
						</td>
					</tr>
				</table>
			</fieldset>


			<div class="buttontool" align="center">
				<logic:equal value="yes" name="writeAble">
				<button class="button2" id="buttonSave"
					onclick="save('/xgxt/gdby_xywhgl.do?method=xywhfb&doType=save');"
					style="width: 80px">
					保 存
				</button>
				</logic:equal>
				&nbsp;&nbsp;

				<%--						<button class="button2" id="buttonClose" onclick="window.close();return false;"--%>
				<%--							style="width: 80px" id="buttonClose">--%>
				<%--							关 闭--%>
				<%--						</button>--%>
			</div>
			<logic:present name="msg">
				<hidden type="hidden" id="msg" value="${msg}" />
				<script>
					alert($("msg").value);
				</script>
			</logic:present>
		</html:form>
		<logic:present name="result">
			<script type="text/javascript">
			Close();
			if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
				window.dialogArguments.document.getElementById('search_go').click();	
			}
		</script>
		</logic:present>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
