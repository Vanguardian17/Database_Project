<?php
//include DatabaseHelper.php file
require_once('DatabaseHelper.php');

//instantiate DatabaseHelper class
$database = new DatabaseHelper();

//Grab variable id from POST request
$item_id = '';
if(isset($_POST['id'])){
    $item_id = $_POST['id'];
}

// Delete method
$error_code = $database->deleteItem( $item_id);

// Check result
if ($error_code == 1){
    echo "Item with ID: '{$item_id}' successfully deleted!'";
}
else{
    echo "Error can't delete Item with ID: '{$item_id}'. Errorcode: {$error_code}";
}
?>

<!-- link back to index page-->
<br>
<a href="index.php">
    go back
</a>