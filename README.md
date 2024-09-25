### Executar o container do mysql
- docker exec -it my-mysql bash
- mysql -u root -p

### Configs para executar no container do docker caso o acesso não seja concedido para o user 'root'
- CREATE USER 'root'@'%' IDENTIFIED BY 'root';
- GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' WITH GRANT OPTION;
- FLUSH PRIVILEGES;

### Limpar a base de dados do spring batch

delete from BATCH_JOB_EXECUTION_CONTEXT;
delete from BATCH_JOB_EXECUTION_PARAMS;
delete from BATCH_JOB_EXECUTION_SEQ;

delete from BATCH_STEP_EXECUTION_CONTEXT;
delete from BATCH_STEP_EXECUTION_SEQ;
delete from BATCH_STEP_EXECUTION;

delete from BATCH_JOB_EXECUTION;
delete from BATCH_JOB_INSTANCE;
delete from BATCH_JOB_SEQ;