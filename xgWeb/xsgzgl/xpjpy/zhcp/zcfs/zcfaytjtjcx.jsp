<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript">
			function showCk(yf,tjzt){
				 var url = "xpj_zcfs.do?method=viewBjzcListCk&yf="+yf+"&tjzt="+tjzt;
				 var title = "综测提交情况查看";
				 showDialog(title, 770, 550, url);
			}
		</script>
		<style type="text/css">
			.center{text-align:center}
		</style>
	</head>

	<body>
		<div class="toolbox">
		<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="document.location.href='pj_zcflr.do';" class="btn_fh">返回</a></li>	
				</ul>
		</div>
		</div>
		<div class="formbox">
			<h3 class="datetitle_01">
		    	<span>${xn} &nbsp;${xqmc}<font color="blue"></font>
		    		&nbsp;&nbsp;<font color="color"></font>



		    	</span>
		    </h3>
			<table class="dateline" width="100%">
				<thead>
					<tr>
						<th rowspan="2" style="text-align: center;">综测年月</th>
						<th colspan="3" style="text-align: center;">统计情况</th>
					</tr>
					<tr>


						<th style="text-align: center;">已提交班级数</th>
						<th style="text-align: center;">未提交班级数</th>
					</tr>
					
				</thead>
				<tbody>
					<logic:iterate name="zcaytjlist" id="i">
						<tr>
							<td class="center" name="yf">${i.yf}
							</td>
							<td class="center"><a href="javascript:void(0)" style="color:blue;text-decoration:underline" onclick="showCk('${i.yf}','1')">${i.tjbjs}</a></td>
							<td class="center"><a href="javascript:void(0)" style="color:blue;text-decoration:underline" onclick="showCk('${i.yf}','0')">${i.wtjbjs}</a></td>
						</tr>
					</logic:iterate>
					
					
				</tbody>
			</table>
		</div>
		
	</body>
</html>
