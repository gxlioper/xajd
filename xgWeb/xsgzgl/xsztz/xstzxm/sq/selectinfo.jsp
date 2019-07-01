<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsgzgl/rcsw/xthd/xmsqjs/js/xmsqjs.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/xstzxm/sq/js/xsxmsqsearch.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				auotSetCanCheck();
			});
		</script>
	</head>
	<body>
	
		<div class="comp_title" id="comp_title">
	      <ul style="width:90%">
	        <li class="ha"><a href="javascript:void(0);" onclick="selectSqxm(this,'wsq');"><span>未申请</span></a></li>
	        <li><a href="javascript:void(0);" onclick="selectSqxm(this,'ysq');"><span>已申请</span></a></li>
	      </ul>
	    </div>
		<div style='tab;width:100%;overflow-x:auto;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
				<tbody>
					<tr>
						<td>
							<div class="con_overlfow" style="width:100%;overflow-x:auto;overflow-y:auto;vertical-align: top;" id="xmList">
								<table class="dateline" width="100%">
									<thead>
										<tr id="titleTr">
											<td width="2%"></td>
											<td style='width: 20%'>
												项目名称
											</td>
											<td style='width: 15%'>
											          申报部门
											</td>
											<td style='width: 15%'>
												所属科目
											</td>
											<td style='width: 20%'>
												项目开始时间
											</td>
											<td style='width: 10%'>
												可参与人数
											</td>
											<td style='width: 10%'>
												基础学分
											</td>
											<td style='width: 10%'>
												通过人数
											</td>
										</tr>
									</thead>
									<tbody id="wsq">
										<logic:present name="xmsqInfoList">
											<logic:iterate name="xmsqInfoList" id="info" indexId="i">
												<logic:equal value="0" name="info" property="sfsq">
												<logic:equal value="1" name="info" property="sfksq">
													<tr name="checkxm">
														<td>
															<input name="checkbox" type="checkbox" value="${info.xmdm }"/>
														</td>
														<td>
															${info.xmmc }
															<logic:equal value="1" property="sfrm" name="info">
															<font color="red">【热门】</font>
															</logic:equal>
														</td>
														<td>
															${info.sbbmmc }
														</td>
														<td>
															${info.sskmmc }
														</td>
														<td name="xmkssj">
															${info.xmkssj }
														</td>
														<td name="kcyrs">
															${info.kcyrs }
														</td>
														<td>
															${info.jcxf }
														</td>
														<td name="syme">
															${info.tgrs }
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
														    <input name="sqrs" value="${info.sqrs}" type="hidden"/>
														    <input name="xmmc" value="${info.xmmc}" type="hidden"/>
														</td>
													</tr>
													</logic:equal>
												</logic:equal>
											</logic:iterate>
										</logic:present>
									</tbody>
									
									<tbody id="ysq" style="display:none;">
										<logic:present name="xmsqInfoList">
											<logic:iterate name="xmsqInfoList" id="info" indexId="i">
												<logic:equal value="1" name="info" property="sfsq">
													<tr>
														<td>
															<input type="checkbox" value="${info.xmdm }" disabled="disabled"/>
														</td>
														<td>
															${info.xmmc }
														</td>
														<td>
															${info.sbbmmc }
														</td>
														<td>
															${info.sskmmc }
														</td>
														<td>
															${info.xmkssj }
														</td>
														<td>
															${info.kcyrs }
														</td>
														<td>
															${info.jcxf }
														</td>
													</tr>
												</logic:equal>
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
			<div style="height:30px;"></div>
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
