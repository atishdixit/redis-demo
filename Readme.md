Launch a local Redis Server instance using docker
To download/Install the redis image if already not existed in local
```
docker run --name redis-cache -d redis
```
To run redis container and expose to external port 6379

```
docker run -p 6379:6379 redis
```
Otherwise download the server and setup on local https://redis.io/docs/clients/java/

##Not Relevant
 ```
create table eventdemo(
id INT primary key auto_increment,
message varchar(225) not null,
create_At datetime not null
);

CREATE EVENT AddData on schedule every 10 minute 
do insert into eventdemo(message, create_AT)
values('Hello !!', now()); 

select * from eventdemo;

Drop event if exists AddData;

show events from hotels;
```