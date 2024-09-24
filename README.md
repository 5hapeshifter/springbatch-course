### Executar o container do mysql
- docker exec -it my-mysql bash
- mysql -u root -p

### Configs para executar no container do docker caso o acesso não seja concedido para o user 'root'
- CREATE USER 'root'@'%' IDENTIFIED BY 'root';
- GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' WITH GRANT OPTION;
- FLUSH PRIVILEGES;