# Run Command "Set-ExecutionPolicy RemoteSigned" and press A
echo "#############################################################"
echo "########Creating Grafana and Influx DB containers############"
echo "#############################################################"
cd C:\Users\bhargamu\Desktop\compose\grafana
docker-compose up -d
echo "Sleeping for 10 seconds"
Start-Sleep -s 10
echo "#############################################################"
echo "###############Creating Sonarqube containers#################"
echo "#############################################################"
cd C:\Users\bhargamu\Desktop\compose\sonarqube
docker-compose up -d
echo "Sleeping for 10 seconds"
Start-Sleep -s 10

echo "#############################################################"
echo "###############Creating Zalenium containers##################"
echo "#############################################################"
cd C:\Users\bhargamu\Desktop\compose\Zalenium
docker-compose up -d
echo "Sleeping for 10 seconds"
Start-Sleep -s 10

echo "#############################################################"
echo "############Creating ReportPortal containers#################"
echo "#############################################################"
cd C:\Users\bhargamu\Desktop\compose\reportportal2
docker-compose -p reportportal up -d --force-recreate
cd $HOME
ls
pause