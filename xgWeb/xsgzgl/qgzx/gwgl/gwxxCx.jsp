<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：yyp -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>		
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/qgzx/gwgl/gwgl.js"></script>
		<script language="javascript" defer="defer">
		//初始化
		jQuery(document).ready(function(){ 
			searchRs();
		});

		//复制
		function copyGwxx1(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			var blog=true;
			if(len>=1){	
				var array = jQuery("[name=div_pkValue]:checked");
				var num = 0;
				var str = "";
				for (var i=0;i<array.length;i++) {
					var pkValue = jQuery(array[i]).parent().parent().find("td").eq(2).text();
					if(str.indexOf(pkValue)==-1){
						str += pkValue+"!!@@!!";
						num++;
					}
				}
				var idList = "";
				jQuery("input[type='checkbox'][name='div_pkValue']:checked").each(function(){
					idList += jQuery(this).val() + "!!@@!!";        
				});
				url = "qgzx_gwgl_ajax.do?method=gwxxFz&str="+str+"&num="+num+"&len="+len +"&idList="+idList;
				showDialog('', 380, 200, url)
			}else{
				alertInfo("请勾选需要复制的记录！",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
			}
		}
		</script>
	</head>
	<body>

		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/qgzx_gwgl" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
            <input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<!-- 隐藏域 -->
			<!-- 多功能操作区 -->
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li> 
							<a href="#" onclick="showDialog('', 760, 520, 'qgzx_gwgl.do?method=gwxxZj');return false;" class="btn_zj">增加岗位</a>
						</li>
						<li>
							<a href="#" onclick="showModi('gwxxXg');return false;" class="btn_xg">修改岗位</a>
						</li>
						<li>
							<a href="#" onclick="deleteGwxx();return false;" class="btn_sc">删除岗位</a>
						</li>
						<li>
							<a href="#" onclick="copyGwxx1();return false;" class="btn_fz">复制岗位</a>
						</li>
						<logic:equal value="10407" name="xxdm">
                        <li>
                            <a href="javascript:;" onclick="gwxxDr();return false;" id="btn_dr" class="btn_dr">岗位导入</a>
                       </li>
						 <li>
                            <a href="javascript:;" onclick="gwryDr();return false;" id="btn_dr" class="btn_dr">岗位人员导入</a>
                       </li>
						</logic:equal>
						</logic:equal>
						<logic:equal name="xxdm" value="10052">
						<li>
							<a href="#" onclick="showModi('ryxxZj');return false;" class="btn_zj">人员增减</a>
						</li>
						<li>
							<a href="#" onclick="showModi('ryxxTg');return false;" class="btn_sc">人员退岗</a>
						</li>
						</logic:equal>
						<logic:notEqual name="xxdm" value="10052">
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="#" onclick="showModi('ryxxZj');return false;" class="btn_zj">人员增减</a>
						</li>
						<li>
							<a href="#" onclick="showModi('ryxxTg');return false;" class="btn_sc">人员退岗</a>
						</li>
						</logic:equal>
						</logic:notEqual>
						<li>
							<a href="#" class="btn_ck" onclick="showModi('gwxxCk');return false;">查看明细</a>
						</li>
					</ul>
				</div>
				
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<!-- 多功能操作区 end-->

			<!-- 内容显示区开始 -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- 查询得到的数据量显示区域 -->
						</p>
					</div>
				</div>
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; </span>
				</h3>
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--分页显示-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=qgzxGwglForm"></jsp:include>
			</div>
			<div id="div_detail" style="display:none">
			</div>		
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			<div id="tempDiv" style="display: none">
				<!-- 提示信息 -->
				<div class="prompt" id="promptTs">
					<h3>
						<span>提示：</span>
					</h3>
					<p>
						仅能将选中岗位复制到非岗位所属学年中
					</p>
					<a class="close" title="隐藏"
					   onclick="this.parentNode.style.display='none'"></a>
				</div>
				<!-- 提示信息 end-->	
				<table align="center" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>复制岗位信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								已选岗位
							</th>
							<td>
								<font id="yxgw"></font>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>目标学年
							</th>
							<td>
								<html:select property="xn" styleId="xn" >
								</html:select>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" name="保存" onclick="gwxxDivSave();">
										保存
									</button>
									<button type="button" name="取消" onclick="closeWindown();return false;">
										取 消
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>