<html>
	<head>
		<style>
		h1 {
			text-align: center;
		}
		
		table, tr, td {
			border: 1px solid black;
			border-collapse: collapse;
		}

		</style>
	</head>
<!--ITEMS TABLE -->
	<h1>Sneakers Shop </h1>
	<a href="index.php">
    go back
</a>
	<h2>Bought Items </h2>
	<table style="width:100%">
		<tr>
			<th>Item ID</th>
			<th>Seller Name</th>
			<th>Buyer Name</th>
			<th>Card Number</th>		
		</tr>
		<?php					
				
			$conn = oci_connect('a11825950','dbs20','lab');
										
			if (!$conn) {
			$e = oci_error();
			trigger_error(htmlentities($e['message'], ENT_QUOTES), E_USER_ERROR);
			}	
			
			$stid = oci_parse($conn, 'SELECT * FROM Buy_Item ORDER BY ItemID');
			oci_execute($stid);
		
			while ($row = oci_fetch_array($stid, OCI_ASSOC+OCI_RETURN_NULLS)) {
				echo "<tr>\n";
				foreach ($row as $item) {
				echo "<td>" . ($item !== null ? htmlentities($item, ENT_QUOTES) : "&nbsp;") . "</td>\n";
				}	
			}
			oci_free_statement($stid);
		?>
	</table>
</html>	