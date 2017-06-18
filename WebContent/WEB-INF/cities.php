<?php
function get_data($url)
{
    $homepage = file_get_contents('http://ec2-54-186-234-240.us-west-2.compute.amazonaws.com:8080/gmfws/v1/cities');
	return $homepage;
}

$data=get_data($_POST['url']);
echo "cities/".$data;
?>
