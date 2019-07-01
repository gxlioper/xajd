<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<script type="text/javascript">
			function tj(url){
				if($('nj').value == ""){
					alert('请选择年级！');	
					return false;
				}

				if($('xn').value == ""){
					alert('请选择学年！');
					return false;
				}

				if($('xq').value == ""){
					alert('请选择学期！');
					return false;
				}

				if($('xy').value == ""){
					alert("请选择"+jQuery("#xbmc").val()+"！");
					return false;
				}

				if($('zy').value == ""){
					alert('请选择专业！');
					return false;
				}

				if($('bj').value == ""){
					alert ('请选择班级！');
					return false;
				}

				document.forms[0].action = '/xgxt/commWsjc.do?method=xsfsTj&doType=print&bjmc=' + selText('bj');
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";
<%--				refreshForm('/xgxt/commWsjc.do?method=xsfsTj&doType=print');--%>
			}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>统计条件选择</a>
			</p>
		</div>
		<html:form action="/commWsjc" method="post">
			<input type="hidden" name="xyV" id="xyV" value="" />
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="njV" id="njV" value="" />
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<table class="formlist">
				<thead>
					<tr>
						<th colspan="2"><span>条件信息</span></th>
					</tr>
				</thead>
				<tr>
					<th>年级</th>
					<td>
						<html:select property="nj" onchange="initZyList();initBjList()" styleId="nj">
							<html:options collection="njList" labelProperty="nj" property="nj"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>学年</th>
					<td>
						<html:select property="xn" styleId="xn">
							<html:options collection="xnList" labelProperty="xn" property="xn"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>学期</th>
					<td>
						<html:select property="xq" styleId="xq">
							<html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td colspan="2">
						<html:select property="xydm" style="width: 200px" styleId="xy" onchange="initZyList();initBjList();" onmouseover="">
						<html:option value=""></html:option>
						<html:options collection="xyList" property="xydm" labelProperty="xymc" />
					</html:select></td>
		       	</tr> 	
         		<tr>
         			<th>专业</th>
					<td>
						<html:select property="zydm" style="width: 200px" styleId="zy" onchange="initBjList()" onmouseover="">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm"
								labelProperty="zymc" />
						</html:select>
					</td>
				</tr>
				<tr>
         			<th>班级</th>
         			<td>
         				<html:select property="bjdm" style="width: 200px" styleId="bj" onmouseover="">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm"
								labelProperty="bjmc" />
						</html:select>
					</td>
         		</tr>
         		<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz"><span class="red">所有选项皆为必填项！</span></div>
			          <div class="btn">
						  <button type="button" name="提交" onclick="tj();return false;">提交</button>	
						  <button type="button" name="关闭" onclick="window.close();return false;">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
		</html:form>
	</body>
</html>