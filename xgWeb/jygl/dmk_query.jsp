<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.ArrayList" />
<jsp:directive.page import="java.util.Iterator" />
<jsp:directive.page import="xgxt.jygl.form.Dmk" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
	        function yz(){
			var sjzd = document.from.sjzd.value;
			if(sjzd == ""){
				alert("请先选择要查询的代码库!");
			}else{
				document.from.submit();
			}
		}
	    </script>
	</head>
	<body>
		<form action="/xgxt/dmkquery.do" method="post" name="from">
		<div class="tab">
		<table width="100%" border="0" class="formlist">
			<thead>
				<tr style="height:22px">
					<td colspan="6">
						<span>代码库查询器</span>
					</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th colspan="1">
						请选择要查询的代码库
					</th>
					<td colspan="3">
						<select id="sjzd" name="sjzd">
							<option value="">
								-------请选择-------
							</option>
							<option value="bzgzzy">
								本专高职专业代码
							</option>
							<option value="yjszy">
								研究生专业代码
							</option>
							<option value="xb">
								性别代码
							</option>
							<option value="xl">
								学历代码
							</option>
							<option value="sydq">
								生源地区代码
							</option>
							<option value="bzgzpyfs">
								本专培养方式代码
							</option>
							<option value="yjspyfs">
								研究生培养方式代码
							</option>
							<option value="zzmm">
								政治面貌代码
							</option>
							<option value="dwdq">
								单位地区代码
							</option>
							<option value="byqx">
								毕业去向代码
							</option>
							<option value="jzzbz">
								居住证或蓝表标志位
							</option>
							<option value="sydzgdw">
								生源地主管部门代码
							</option>
							<option value="dwxz">
								单位性质代码
							</option>
							<option value="zgbm">
								主管部门代码
							</option>
							<option value="dwxz2">
								单位性质代码2
							</option>
							<option value="dqlx">
								地区流向代码
							</option>
							<option value="hyfl">
								行业分类代码
							</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>

						名称
						<input type="text" value="" name="mc" />
					</td>
					<td>
						代码
						<input type="text" value="" name="dm" />
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="radio" name="myselect" value="1"/>
						&nbsp;精确查询&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="myselect" value="2" checked/>
						&nbsp;模糊查询
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button  onclick=yz()
							 value="查  询" >查  询</button>
						<button value="重  填"  type="reset"
							>重  填</button>
					</td>
				</tr>
			</table>
		</form>
		<div class="formbox">
				<h3 class="datetitle_01">
					<font color="blue">注明：查询条件为空时，查询结果为该代码库所有数据。</font>
				</h3>
				<table summary="" class="dateline" align="" id="rsTable" width="100%">
				<thead>
					<%
					if (request.getAttribute("dmkmc") != null) {
					%>
					<tr>
						<td colspan="2" align="center">
							<div align="center">
								<font color="blue"><b><%=request.getAttribute("dmkmc")%></b></font>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								 记录数：<%=request.getAttribute("rsNum")%>
							</div>
						</td>
					</tr>
					<%
					}
					%>
					<tr>
						<th align="center">
							代&nbsp;&nbsp;&nbsp;&nbsp;码
						</th>
						<th align="center">
							名&nbsp;&nbsp;&nbsp;&nbsp;称
						</th>
					</tr>
				</thead>
				<tbody>
			<%
			if (request.getAttribute("list") != null) {
			%>
			<%
			ArrayList arrayList = (ArrayList) request.getAttribute("list");
			%>
			<%
						for (Iterator it = arrayList.iterator(); it.hasNext();) {
						Dmk dmk = (Dmk) it.next();
			%>
			<tr>
				<td align="center">
					<%=dmk.getDmkdm()%>
				</td>
				<td align="center">
					<%=dmk.getDmkmc()%>
				</td>
			</tr>
			<%
			}
			%>
			<%
			}
			%>
			</tbody>
		</table>
	</body>
</html>
