<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<html:form action="/szdwfzjy" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a><span>发展教育-发展教育相关-申报时间设置</span></a>
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
				<div class="tab">
			  		<table width="100%"  border="0" class="formlist">
			  	 	 <thead>
			    		<tr>
			        	<th colspan="4"><span>发展教育申请时间设置</span></th>
			  	     </tr>
			   		 </thead>
					 <tfoot>
					      <tr>
					        <td colspan="4">
					          <div class="btn">
					          <logic:notEqual name="doType" value="view">
					          		<button type="button" name="提交" onclick="refreshForm('szdwfzjy.do?method=saveSbsjsz');" id="buttonSave">保 存</button>
					          </logic:notEqual>
					            <button type="button" name="关闭" onclick="Close();return false;" id="buttonClose">关 闭</button>
					          </div></td>
					      </tr>
					    </tfoot>
					    <tbody>
								<tr align="center" style="cursor:hand">
										<th id="xmdm"  nowrap>
											<div align="left">
											模块名称
											</div>
										</th>
										<th id="xmdm"  nowrap>
											<div align="left">
											开始时间
											</div>
										</th>
										<th id="xmdm" nowrap>
											<div align="left">
											结束时间
											</div>
										</th>
								</tr>
							<logic:iterate name="rs" id="s">
								<tr style="cursor:hand">
									<td>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
										<input type="hidden" name="mkmcs" value="<bean:write name='v'/>" scope="request" />
										<logic:iterate id="t" name="s" offset="0" length="1">
											<input type="hidden" name="mkdms" value="<bean:write name='t'/>" scope="request" />
										</logic:iterate>
										
									</td>
										<logic:iterate id="v" name="s" offset="2" length="1">
										<td>
											<input type="text" name="kssjs" value="<bean:write name='v'/>" onclick="return showCalendar('<bean:write name='t'/>','y-mm-dd');" onblur="dateFormatChg(this)" style="cursor:hand;" id="<bean:write name='t'/>">
										</td>
										</logic:iterate>
									<logic:iterate id="v" name="s" offset="3">
										<td align="left">
											<input type="text" name="jssjs" value="<bean:write name='v'/>" onclick="return showCalendar('<bean:write name='t'/>2','y-mm-dd');" onblur="dateFormatChg(this)" style="cursor:hand;" id="<bean:write name='t'/>2">
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							</tbody>
						</table>
			</logic:notEmpty>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    				alert("提交成功！");
   				 </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    				alert("提交失败！");
    			</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
