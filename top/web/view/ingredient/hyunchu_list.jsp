<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.3/jquery.min.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<script src="https://code.highcharts.com/modules/accessibility.js"></script>

<script type="text/javascript">
	function f_link(ingID) {
		alert(ingID);
		
        var ds = encodeURI(ingID);
        location.replace('uupdate.top?'+'ingID='+encodeURI(ds,"UTF-8"));

		//location.href = "uupdate.top?ingID=" + ingID;
	}
	
</script>
<script type="text/javascript"> 
var message = '${msg}'; 
var returnUrl = '${url}'; 
alert(mag); 
document.location.href = url; 
</script>

<div class="row">
	<div class="col-md-6">
		<div class="card">

			<c:forEach var="i" items="${ingredientListData }">

				<div class="card-header">

					<div class="ingID">ingID : ${i.ingID }</div>
				</div>
				<div class="card-body">
					<div class="form-group">
						<label for="ingCategory">ingCategory : ${i.ingCategory }</label>

					</div>
					<div class="form-group">
						<label for="ingName">ingName : ${i.ingName }</label>

					</div>
					<div class="form-group">
						<label for="ingPrice">ingUnit:${i.ingPrice }</label>

					</div>
					<div class="form-group">
						<label for="ingUnit">ingUnit:${i.ingUnit }</label>

					</div>
					<div class="form-group">
						<label for="ingBrand">ingBrand:${i.ingBrand }</label>

					</div>
					<div class="form-group">
						<label for="ingType">ingType:${i.ingType }</label>
					</div>

					<div class="form-group">
						<label for="ingWeight"> ingWeight:${i.ingWeight } </label>

					</div>
					<div class="form-group">
						<label for="ingRegDate"> ingRegDate:${i.ingRegDate } </label>
					</div>
					<div class="form-group">
						<label for="ingLink"> ingLink:${i.ingLink } </label>
					</div>

					<div class="form-group">
						<label for="ingLinkCount"> ingLinkCount:${i.ingLinkCount }
						</label>
					</div>
					<div class="form-group">
						<label for="ingImgName"> ingImgName:${i.ingImgName } </label>
					</div>


					<div class="card-action">
						<button class="btn btn-success">####</button>
						<button class="btn btn-danger" onclick="f_link('${i.ingID}');">update</button>


					</div>
				</div>

			</c:forEach>
		</div>
	</div>
</div>
