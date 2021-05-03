# Run Command "Set-ExecutionPolicy RemoteSigned" and press A
echo "#############################################################"
echo "#################STOP ALL DOCKER CONTAINERS##################"
echo "#############################################################"
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)

pause