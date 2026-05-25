:: ============================================================
:: Configuração do pool JDBC no GlassFish via asadmin
:: Procedimento 1 — Passos 1j a 1n
:: Executar no diretório: <glassfish>/bin
:: ============================================================

:: Passo 1j — Abrir o prompt do asadmin
:: asadmin

:: ---- Cole os comandos abaixo UM A UM no prompt do asadmin ----

:: Passo 1k — Criar o Connection Pool
create-jdbc-connection-pool ^
  --datasourceclassname com.microsoft.sqlserver.jdbc.SQLServerDataSource ^
  --restype javax.sql.DataSource ^
  --property driverClass=com.microsoft.sqlserver.jdbc.SQLServerDriver:portNumber=1433:password=loja:user=loja:serverName=localhost:databaseName=loja:trustServerCertificate=true:URL="jdbc\:sqlserver\://localhost\:1433\;databaseName\=loja\;encrypt\=true\;trustServerCertificate\=true\;" ^
  SQLServerPool

:: Passo 1m — Testar o pool
ping-connection-pool SQLServerPool

:: Passo 1n — Criar o registro JNDI
create-jdbc-resource --connectionpoolid SQLServerPool jdbc/loja

:: Saída esperada em cada comando:
::   create-jdbc-connection-pool → Command create-jdbc-connection-pool executed successfully.
::   ping-connection-pool        → Command ping-connection-pool executed successfully.
::   create-jdbc-resource        → Command create-jdbc-resource executed successfully.
