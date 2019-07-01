<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">
		//查询结果集
		function searchRs(){
			allNotEmpThenGo('bjgm_fdykh_bzrkh.do?method=bzrkhManage');
		}

		//删除batchData(checkname,url,type)
		function del(){
			var url="bjgm_fdykh_bzrkhDelete.do";
			var checkname="primarykey_checkVal";
			var type="del";
			batchData(checkname,url,type);
		}


		//查看班主任考核信息
		function viewBzrkh(){
			var ids;
			if(curr_row != null){
				ids=curr_row.getElementsByTagName('input')[0].value;
			}else{
				alertInfo('请选择要操作的数据行！');
				return false;
			}
			var url="bjgm_fdykh_bzrkhView.do?method=bzrkhView&pkValue="+ids;
			showTopWin(url,750,600);
		}
		</script>
	</head>
	<body>
		<html:form action="/bjgm_fdykh" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="ids" id="ids"/>
			<!-- 隐藏域 -->
			<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>${title}</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
				</p>
			</div>			
			<!-- 标题 end-->
			<!-- 提示信息 START-->
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					个人得分=出勤+材料+年级+学生课<br />
					班级得分=卫生安全+板报+升旗+纪律+人数+奖励+课间操(注:当前班主任所有班级总分)<br />
					个人总分=当前班主任所有班级得分之和/班级数+个人得分
				</p>
				<a class="close" title="隐藏"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- 提示信息 end-->	
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" onclick="showTopWin('bjgm_fdykh_bzrkhAdd.do?method=bzrkhAdd',750,600);return false;" class="btn_zj"> 增加 </a></li>
						<li><a href="#" onclick="modi('bjgm_fdykh_bzrkhEdit.do?method=bzrkhEdit',750,600);return false;" class="btn_xg"> 修改 </a></li>
						<li><a href="#" onclick="del();return false;" class="btn_sc"> 删除 </a></li>
						<li><a href="#" onclick="viewBzrkh();return false;" class="btn_ck"> 查看 </a></li>
						<li><a href="#" class="btn_dr" onclick="impAndChkData();return false;">导入</a></li>
						<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">导出</a></li>
						<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">导出字段确认</a></li>
					</ul>
				</div>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span>
							查询结果&nbsp;&nbsp;<font color="blue">单击表头可以排序;双击床位显示床位基本信息</font>
						</span>
					</h3>
					<table width="99%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec()"/>
								</td>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="${tit }" onclick="tableSort(this)">
										${tit }
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rs">
								<logic:iterate id="r" name="rs" offset="0">
									<tr onclick="rowOnClick(this);" style="cursor:hand" >
										<td>
											<input type="checkbox" name="primarykey_checkVal" id="pkV"
											value="${r.xn};${r.xq};${r.zgh}" />
										</td>
										<td>
											${r.xn}
										</td>
										<td>
											<logic:present name="r">
												<logic:equal name="r" property="xq" value="01">
													春
												</logic:equal>
												<logic:equal name="r" property="xq" value="02">
													秋
												</logic:equal>
											</logic:present>
										</td>
										<td style="width:25%;">
											${r.bmmc}
										</td>
										<td>
											${r.zgh}
										</td>
										<td>
											${r.xm}	
										</td>
										<td>
											${r.cq}	
										</td>
										<td>
											${r.cl}	
										</td>
										<td>
											${r.nj}	
										</td>
										<td>
											${r.xsk}	
										</td>
										<td>
											${r.grdf}	
										</td>
										<td>
											${r.bjdf}	
										</td>
										<td>
											${r.grzf}	
										</td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
						</tbody>
				</table>
				<logic:present name="message">
					<script>
						alertInfo('${message}');
					</script>
				</logic:present>
				<!--分页显示-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=bjgmBzrkhForm"></jsp:include>
				<!--分页显示-->
			</div>
		</html:form>
	</body>
</html>
