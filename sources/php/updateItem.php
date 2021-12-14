<?php
//include DatabaseHelper.php file
require_once('DatabaseHelper.php');

//instantiate DatabaseHelper class
$database = new DatabaseHelper();

//Grab variable id from POST request
$Price = '';
if(isset($_POST['Price'])){
	$Price = $_POST['Price'];
}				

					  								
$ItemId = '';
if(isset($_POST['itemid'])){
	$ItemId = $_POST['itemid'];
}

// update method
$error_code = $database->updateItem( $Price, $ItemId);

// Check result
if ($error_code == 1){
    echo "Item with ID: '{$ItemId}' successfully updated! New Price: '{$Price}'";
}
else{
    echo "Error can't update Item with ID: '{$ItemId}'. Price: '{$Price}' Not set. Errorcode: {$error_code}";
}
?>

<!-- link back to index page-->
<br>
<a href="index.php">
    go back
</a>

