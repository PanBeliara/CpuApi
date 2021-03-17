# CpuApi
 Spring backend CRUD server

Spring Boot powered REST API communicating with 2-table database I created as a result of learning this technology.
Check **\mysqlCreateQueries** for create queries. **Originally for mysql**.
Edit **\target\application.properties** to match your DBMS credentials. Executable jar is **\target\CpuApi.jar**, script to launch is **\target\start.bat**.

## tl;dr main features (or rather problems solved):
- Handled JSON serialization of the two-way 1:n relationship
- (Custom) exception handling
- Validation
- Custom findAll on SocketRepository

## more tl;dr:
- works :3

Check my Angular client app (which took me MUCH more time btw)

## Requirements to launch:
- jre1.8.0_221 or newer
