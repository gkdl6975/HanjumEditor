<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css">
<script type="text/javascript" charset="UTF-8" src="//cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
<script type="text/javascript">
$(document).ready( function () {
    $('#write_table').DataTable();
} );
 
</script>
</head>
<body>
<table id="write_table">

  <thead>
 	<tr>
	<th>1</th>
	<th>2</th>
	</tr>
  </thead>
  <tbody>
	<tr>
		<td>5</td>  
		<td>6</td>
	</tr>
	<tr>
		<td>7</td>  
		<td>8</td>
	</tr>
  </tbody>

</table>

</body>
</html>