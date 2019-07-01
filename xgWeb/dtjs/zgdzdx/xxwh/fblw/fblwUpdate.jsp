<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body>
		<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/GetFdyList.js"></script>
		<html:form action="/zgdzdxXxwh" method="post" enctype="multipart/form-data">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a><span>发表论文单个维护</span></a>
				</p>
			</div>
			
			<logic:notEmpty name="rs">
				<input type="hidden" id="pk" name="pk"
					value="<bean:write name="rs" property="pk" scope="request"/>" />
					
					<div class="tab">
					<table width="100%" class="formlist">
						<thead>
						<tr><th colspan="4"><span>发表论文</span></th></tr>
						</thead>
						
						<tbody>
						<tr>
							<th>
								<font color="red">*</font>部门
							</th>
							<td align="left">
								<html:select name = "rs" property="bmdm" style="width:180px" styleId="xy" onchange="getFdyList();">
										<html:option value=""></html:option>
										<html:options collection="bmList" property="bmdm"
											labelProperty="bmmc" />
								</html:select>
							</td>
							<th>
								<font color="red">*</font>作者
							</th>
							<td align="left">
								<html:select name = "rs" property="zgh"  styleId="zgh" style="width:150px" >
										<html:option value=""></html:option>
										<html:options collection="fdyList" property="zgh"
											labelProperty="xm" />
								</html:select>
							<input type="hidden" name="zghV" value="<bean:write name="rs" property="zgh" scope="request"/>"/>
							</td>
						</tr>
						<tr>
						<logic:equal name="rs" property="pk" value="">
							<th>
								<font color="red">*</font>序号
							</th>
							<td align="left">
								系统生成
							</td>
						</logic:equal>
						<logic:notEqual name="rs" property="pk" value="">
							<th>
								<font color="red">*</font>序号
							</th>
							<td align="left">
								<bean:write name="rs" property="xh"/>
							</td>
						</logic:notEqual>
							<th>
								发表时间
							</th>
							<td align="left">
								<html:text name='rs' property="fbsj" styleId="fbsj" readonly="true"
									onblur="dateFormatChg(this)" style="width:180px;cursor:hand;"
									onclick="return showCalendar('fbsj','y-mm-dd');" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>论文类别
							</th>
							<td align="left">
									<html:select name= "rs" property="lwlbdm" style="width:180px" styleId="lwlbdm" >
										<html:option value=""></html:option>
										<html:options collection="fblwlbList" property="lwlbdm"
											labelProperty="lwlbmc" />
									</html:select>
							</td>
							<th>
								发表期刊名称
							</th>
							<td align="left">
								<html:text name='rs' property="fbqkmc" styleId="fbqkmc" maxlength="100" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>论文题目
							</th>
							<td align="left" colspan="3">
								<html:text name='rs' property="lwtm" styleId="lwtm" maxlength="50" style="width:90%"/>
							</td>
						</tr>
						<tr align="left" id="zy">
							<th>
								摘要
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='zy' style="width:99%"
									rows='5' />
							</td>
						</tr>
						<logic:notPresent name = "type">
						<tr>
							<th>
								附件上传
							</th>
							<td align=left colspan="3"> 
							<input type="file" name="uploadFile" style="width:60%" id="kk"/>
							&nbsp;&nbsp;
							<font color="red">(文件大小小于&lt;10M&gt;)</font>
							</td>
						</tr>
						</logic:notPresent>
						<logic:notEmpty name = "rs" property='xzlj'>
						<tr>
							<th>
								附件下载
							</th>
							<td align=left colspan="3"> 
							<a href="zgdzdxXxwh.do?method=downLoadFile&dir=<bean:write name = "rs" property='xzlj'/>" target="_blank" >下载附件</a>
							</td>
						</tr>
						</logic:notEmpty>
						</tbody>
						
						<tfoot>
					      <tr>
					        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
					          <div class="btn">
					         <logic:notPresent name = "type">
								<button type="button" onclick="dtjsCommonSave('zgdzdxXxwh.do?method=fblwSave','zy','500','zgh-bmdm-lwlbdm-lwtm');" 
									id="buttonSave">
									保 存
								</button>
							</logic:notPresent>
					            <button type="button" name="关闭" onclick="window.close();return false;">关闭</button>
					          </div></td>
					      </tr>
					    </tfoot>
					</table>
					</div>
			</logic:notEmpty>
			<logic:present name="update">
				<logic:equal name="update" value="yes">
					<script>
    alert("提交成功！");
    dialogArgumentsQueryChick();
	Close()
    </script>
				</logic:equal>
				<logic:equal name="update" value="no">
					<script>
    alert("提交失败！");
    </script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
