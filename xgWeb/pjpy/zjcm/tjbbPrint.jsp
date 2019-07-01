<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/pjpy/pjpy_szyqxy.js"></script>
	<script language="javascript" src="js/sztzFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getSzPjpyDAO.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/cpzZjcmDAO.js'></script>
	<script type="text/javascript">	
		function printBb(){
			var xn = $("xn").value;
			var xq = $("xq").value;
			var bblx = $("bblx").value;
			var xydm = $("xydm").value;
			var bjdm = $("bjdm").value;
			if(bblx == ""){
				alert("请确定报表类型！！");
				return false;
			}
			if(xn == "" || xq == ""){
				alert("评奖学年与学期不能为空！！");
				return false;
			}
			if(bblx == "xyzh"){
				if(bjdm == ""){
					alert("请确定班级名称！！");
					return false;
				}
			}
			
			if( bblx == "pjmd"){
				if(xydm == ""){
					alert("请确定<bean:message key="lable.xsgzyxpzxy" />名称！！");
					return false;
				}
			}
			var url = "/xgxt/zjcm_tjsz.do?method=tjbbPrint&doType=print";
			
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
			//window.open(url);
		}
	</script>
	</head>
	<body onload="">
		<html:form action="/zjcm_cpz">
			<input type="hidden" name="njV" id="njV" />
			<input type="hidden" name="ndV" id="ndV" />
			<input type="hidden" name="xyV" id="xyV" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a><bean:write name="title" /></a>
				</p>
			</div>
			<div class="tab">
			<table class="formlist" border="0" id="rsTable" align="center" style="width: 50%">
				<thead>
					<tr>
						<th colspan="2">
							<span>统计报表</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr style="height: 23px">
					<th align="right" width="25%">
						评奖学年
					</th>
					<td align="left" width="">
						<html:select property="xn" style="" styleClass="select"styleId="xn">
							<html:options collection="xnList" property="xn" labelProperty="xn" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th align="right" width="">
						评奖学期
					</th>
					<td align="left" width="">
						<html:select property="xq" style="" styleClass="select"styleId="xq">
							<html:option value=""></html:option>
							<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th align="right" width="">
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td align="left">
						<html:select property="xydm" onchange="initZyList();initBjList()"
						styleClass="select" style="" styleId="xy">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th align="right">
					 	专业
					</th>
					<td>
						<html:select property="zydm" style="" styleId="zy"
							onchange="initBjList()">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm"
								labelProperty="zymc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th align="right" width="">
						班级
					</th>
					<td align="left">
						<html:select property="bjdm" style="" styleId="bj">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm"
								labelProperty="bjmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th align="right">
					 	报表类型
					</th>
					<td>
						<html:select property="bblx" style="">
							<html:option value=""></html:option>
							<html:option value="xyzh"><bean:message key="lable.xsgzyxpzxy" />综合素质测评表</html:option>
							<html:option value="pjmd">评奖评优名单汇总表</html:option>
						</html:select>
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="2">
			          <div class="btn">
			          	<button id="buttonSave" onclick="printBb()">
							打印
						</button>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
			</div>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('操作成功！');
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert('操作失败！');
					</script>
				</logic:equal>
			</logic:present>
			<logic:present name="msg">
				<script>
					alert($("msg").value);
				</script>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
