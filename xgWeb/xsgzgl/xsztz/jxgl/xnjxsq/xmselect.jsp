<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsgzgl/xsztz/jxgl/xnjxsq/js/xnjxsq.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				
			});
		</script>
	</head>
	<body>
		<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
				<tbody>
					<tr>
						<td>
							<div class="con_overlfow" style="width:100%;overflow-x:hidden;overflow-y:auto;vertical-align: top;" id="xmList">
								<table class="dateline" width="100%">
									<thead>
										<tr id="titleTr">
											<td width="2%"></td>
											<td style='width: 30%'>
												项目名称
											</td>
											<td style='width: 20%'>
												项目类别
											</td>
											<td style='width: 20%'>
												项目所属科目
											</td>
											<td style='width: 5%'>
												是否设立奖项
											</td>
											<td style='width: 5%'>
												基础学分
											</td>
										</tr>
									</thead>
										<logic:present name="xmsqInfoList">
											<logic:iterate name="xmsqInfoList" id="info" indexId="i">
													<tr name="checkxm">
														<td>
															<input name="checkbox" type="checkbox" value="${info.xmdm }"/>
														</td>
														<td>
															${info.xmmc }
														</td>
														<td>
															${info.xmjbmc }
														</td>
														<td>
															${info.sskmmc }
														</td>
														<td>
															${info.sfsljxmc }
														</td>
														<td>
															${info.jcxf }
														</td>
														<td name="syme" style="display:none">
															<input name="jgid" value="${info.jgid }" type="hidden"/>
															<input name="xmdm" value="${info.xmdm }" type="hidden"/>
														    <input name="xn" value="${info.xn }" type="hidden"/>
														    <input name="xq" value="${info.xq }" type="hidden"/>
														    <input name="xqmc" value="${info.xqmc }" type="hidden"/>
														    <input name="sbbmmc" value="${info.sbbmmc }" type="hidden"/>
														    <input name="lxdh" value="${info.lxdh }" type="hidden"/>
														    <input name="sskmmc" value="${info.sskmmc }" type="hidden"/>
														    <input name="sskmdm" value="${info.sskmdm }" type="hidden"/>
														    <input name="xmjbdm" value="${info.xmjbdm }" type="hidden"/>
														    <input name="xmjbmc" value="${info.xmjbmc }" type="hidden"/>
														    <input name="splc" value="${info.splc }" type="hidden"/>
														    <input name="jcxf" value="${info.jcxf }" type="hidden"/>
														    <input name="sbbmdm" value="${info.sbbmdm}" type="hidden"/>
														    <input name="sbbmmc" value="${info.sbbmmc}" type="hidden"/>
														    <input name="kcyrs" value="${info.kcyrs}" type="hidden"/>						    
														    <input name="xmmc" value="${info.xmmc}" type="hidden"/>
														    <input name="smkssj" value="${info.smkssj}" type="hidden"/>
														    <input name="sbrxm" value="${info.sbrxm}" type="hidden"/>
														    <input name="sfsljxmc" value="${info.sfsljxmc}" type="hidden"/>
														    <input name="xmkssj" value="${info.xmkssj}" type="hidden"/>			
														</td>
													</tr>
											</logic:iterate>
										</logic:present>
									</tbody>									
								</table>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
			</div>
			 <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td>
							<div class="btn">
								<button type="button" onclick="selectXm();">
									确定
								</button>
								<button type="button" name="关 闭" onclick="iFClose();">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
				</table>
		
	</body>
</html>
