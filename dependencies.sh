#Redis Container
docker run --name redis -p 6379:6379 -d redis

#SQL Server Container
docker run --name sqlserver -e "ACCEPT_EULA=Y" -e "MSSQL_SA_PASSWORD=comPlexPassWord!" -p 1433:1433 -d mcr.microsoft.com/mssql/server:2022-latest

# Exec into the container and create databases
docker exec -it sqlserver /opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P comPlexPassWord! -Q "CREATE DATABASE orders;"
docker exec -it sqlserver /opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P comPlexPassWord! -Q "CREATE DATABASE product_catalog;"
docker exec -it sqlserver /opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P comPlexPassWord! -Q "CREATE DATABASE product_recommendations;"
docker exec -it sqlserver /opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P comPlexPassWord! -Q "CREATE DATABASE users;"