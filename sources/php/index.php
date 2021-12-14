<!DOCTYPE html>
<html>
<head>
	<style>
	
	body{
	margin-top: 0px; 
    margin-bottom: 0px; 
    margin-left: 0px; 
    margin-right: 0px;
    padding: 20px;	
	}
	h1 {
		text-align: center;
	}
	
	table, tr, td {
		border: 1px solid black;
		border-collapse: collapse;
	}
	
	button {
	  background-color: #108b94;
	  border: none;
	  color: white;
	  padding: 15px 32px;
	  text-align: center;
	  text-decoration: none;
	  display: inline-block;
	  font-size: 16px;
	  border-radius: 25px;
	  
	}
	
	.bg {
		background-image: url('sneak1.webp');
		height: 100%; 
        background-position: center;
		background-repeat: no-repeat;
		background-size: cover;
	}
	
	.down{
		margin-bottom: 10px;
	}
	
	.wrapper {
    text-align: center;
	}

	.button {
		position: absolute;
		top: 50%;
	}
	</style>
</head>

<body>
    <div class="bg">
	<h1>Sneakers Shop </h1>
	<div class="wrapper">
		<a href="showItems.php"><button>View Items</button></a>		
		<a href="showBoughts.php"><button>View Bought Items</button></a>
	</div>
	
	<!--Person TABLE 
	<h2>Person </h2>
	<table style="width:100%">
		<tr>
			<th>Name</th>
			<th>Adress</th>
			<th>Size of the owner</th>
		</tr>
		<?php					
										 
			$conn = oci_connect('a11825950','dbs20','lab');
										
			if (!$conn) {
			$e = oci_error();
			trigger_error(htmlentities($e['message'], ENT_QUOTES), E_USER_ERROR);
			}
										
			$stid = oci_parse($conn, 'SELECT * FROM Profile');
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
	-->
	

	<!-- Form for Delte Item -->
	<h2> Delete Item </h2>
	<br>
	<form method="post" action="delItem.php">
		<!-- Name textbox -->
		<div>
			<label for="new_name">ItemID:</label>
			<input id="new_name" name="id" type="text" maxlength="20">
		</div>
		<br>

		<!-- Submit button -->
		<div>
			<button type="submit">
				Delete Item
			</button>
		</div>
	</form>
	
	<!-- Form for Insert Item -->
	<h2> Insert Item </h2>
	<br>
	<form method="post" action="addItem.php">
		<!-- Name textbox -->
		<div>
			<div class="down">
				<label for="new_name">Name:</label>
				<input id="new_name" name="Name" type="text" maxlength="30">
			</div>	
				
			<div class="down">	
				<label for="new_name">Description:</label>
				<input id="new_name" name="Description" type="text" maxlength="100">
			</div>		
				
			<div class="down">	
				<label for="new_name">Price:</label>
				<input id="new_name" name="Price" type="text" maxlength="20">
			</div>		
				
				<label for="new_name">Owner Name:</label>
				<input id="new_name" name="Seller_Name" type="text" maxlength="30">
		</div>
		<br>

		<!-- Submit button -->
		<div>
			<button type="submit">
				Add Item
			</button>
		</div>
	</form>
	
	
	<!-- Form for Update Item -->
	<h2> Update Item </h2>
	<br>
	<form method="post" action="updateItem.php">
		<!-- Name textbox -->
		<div>
			<div class="down">
				<label for="new_name">Price:</label>
				<input id="new_name" name="Price" type="text" maxlength="20">
				<br>
			</div>	
				<label for="new_name">Item ID:</label>
				<input id="new_name" name="itemid" type="text" maxlength="20">
				
		</div>
		<br>

		<!-- Submit button -->
		<div>
			<button type="submit">
				Update Item
			</button>
		</div>
	</form>
	
	<!-- Form for Search Item -->
	<h2> Search Item </h2>
	<br>
	<form method="post" >
		<!-- Name textbox -->
		<div>
			<label for="itemidos">Item ID:</label>
			<input id="itemidos" name="itemidos" type="text" maxlength="20">
		</div>
		<br>

		<!-- Submit button -->
		<div>
			<button type="submit">
				Search Item
			</button>
		</div>
	</form>
	<br>
	<h2>Found Item </h2>
	<table style="width:100%">
		<tr>
			<th>Name of the Item</th>
			<th>Description</th>
			<th>Price</th>
			<th>ItemID</th>
			<th>Name of the Seller</th>
		</tr>
		<?php		

			$itemidos = '';
            if(isset($_POST['itemidos'])){
               $itemidos = $_POST['itemidos'];
            }
			
			$stid = oci_parse($conn, "SELECT * FROM ITEM WHERE ItemID = '{$itemidos}'");
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
	
	
	
	
</body>
</html>