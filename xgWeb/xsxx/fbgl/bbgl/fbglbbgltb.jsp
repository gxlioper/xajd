<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsxx/fbgl/bbgl/js/fbglbbgl.js"></script>
		<script type="text/javascript" src="xsxx/fbgl/gzsd/gzyl.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				preview();
				loadTbxx();
			});
		</script>
	</head>
	<body>
		<div>
		<html:form action="/fbglbbgl.do?method=add&type=query">
			<input type="hidden" id="pzgzid" value="${pzgzid}" />
			<input type="hidden" id="pk" value="${pk}" />
            <input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<table width="100%" border="0" class="formlist">
				<tr>
					<td>
					<div style="" id="div_help" class="prompt">
						<h3>
							<span>提示：</span>
						</h3>
						<p>
							<span>当前编班规则示例： <span id="tjpz"></span>
							</span>
							<span style="text-align: right; margin-left:20px;">
								<a
									href="#" onclick="ckxx();return false;"
									style="text-decoration: underline;color: red;">编班规则查看:&nbsp;&nbsp;${pzgzmc}
								</a>
							</span>
						</p>
					</div>
					</td>
				</tr>
			</table>
			<table width="100%" border="0" class="dateline">
				<tr class="nowrap" style="background: none repeat scroll 0 0 #F3F5F8;">
					<th>
						年级
					</th>
					<th>
						<bean:message key="lable.xb" />
					</th>
					<th>
						专业名称
					</th>
					<th>
						专业人数
					</th>
					<th>
						层次
					</th>
					<th>
						学制
					</th>
					<th>
						班级个数
					</th>
				</tr>
				<tr>
					<td>
						${data.nj}
					</td>
					<td>
						${data.bmmc}
					</td>
					<td>
						${data.zymc}
					</td>
					<td>
						${data.rs}
					</td>
					<td>
						${data.pyccmc}
					</td>
					<td>
						${data.xz}
					</td>
					<td>
						${bjgs}
					</td>
				</tr>
			</table>
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="5">
							<span>已编班信息</span>
						</th>
					</tr>
				</thead>
			</table>
			<table id="table" width="100%" border="0" class="formlist">
			
			</table>
		</html:form>
		</div>
		<div style="height:35px;"></div>
		<div>
			<table width="100%" border="0" class="formlist" id="below" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
					<tr>
						<td colspan="2">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button"  onclick="saveTbxx();return false;" id="buttonSave">
									保  存
								</button>							
								<button type="button"  onclick="iFClose();" id="buttonClose">
									关  闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</body>
</html>
